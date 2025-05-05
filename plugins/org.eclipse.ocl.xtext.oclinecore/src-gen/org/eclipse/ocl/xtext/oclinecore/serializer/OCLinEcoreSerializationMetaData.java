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
package org.eclipse.ocl.xtext.oclinecore.serializer;

import com.google.inject.Inject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport;
import org.eclipse.ocl.xtext.base.serializer.EClassValue;
import org.eclipse.ocl.xtext.base.serializer.EClassValue.EReference_TargetGrammarRuleVector;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue.EnumerationValueMultiple;
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
import org.eclipse.ocl.xtext.base.serializer.SerializationSegment.CustomSerializationSegment;
import org.eclipse.ocl.xtext.base.serializer.SerializationStep;
import org.eclipse.ocl.xtext.base.serializer.SubstringStep;
import org.eclipse.ocl.xtext.base.serializer.TerminalRuleValue;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;

/******* This file is 100% auto-generated - do not edit it *******/

/**
 * The OCLinEcoreSerializationMetaData singleton provides the metadata to support a
 * model to text serialization of a parsed Xtext semantic model or to re-format an Xtext text node model.
 */
public class OCLinEcoreSerializationMetaData extends AbstractSerializationMetaData
{
	/**
	 * The Provider supports injected creation of the OCLinEcoreSerializationMetaData singleton.
	 */
	public static class Provider implements SerializationMetaData.Provider
	{
		private static @Nullable OCLinEcoreSerializationMetaData INSTANCE = null;

		@Inject
		private GrammarProvider grammarProvider;

		@Override
		public synchronized @NonNull SerializationMetaData get() {
			// synchronized synchronizes the creation of this singleton.
			// It does not imply that the overall application is threadsafe.
			OCLinEcoreSerializationMetaData instance = INSTANCE;
			if (instance == null) {
				assert grammarProvider != null;
				Grammar grammar = grammarProvider.getGrammar(Provider.class);
				assert grammar != null;
				INSTANCE = instance = new OCLinEcoreSerializationMetaData(grammar);
			}
			return instance;
		}
	}

	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[68];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[24];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[131];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[74];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[355];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[241];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[150];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [23] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[287];
	private final @NonNull SubstringStep @NonNull [] substringSteps = new @NonNull SubstringStep[10];
	private final @Nullable String @Nullable [] multipleLineCommentMidfixes = new @Nullable String[] {" *"};
	private final @NonNull String @Nullable [] multipleLineCommentPrefixes = new @NonNull String[] {"/*"};
	private final @NonNull String @Nullable [] multipleLineCommentSuffixes = new @NonNull String[] {"*/"};
	private final @NonNull String @Nullable [] singleLineCommentPrefixes = new @NonNull String[] {"--"};

	private OCLinEcoreSerializationMetaData(@NonNull Grammar grammar) {
		super(grammar);
		initGrammarRuleVectors();
		initEnumerationValues();
		initMatchTerms();
		initMatchSteps();
		initSerializationSegments();
		initSerializationSteps();
		initSerializationRules0();
		initSerializationRules1();
		initSerializationRules2();
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
		return 120;
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
		return 119;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 185;
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
		eClassValues[0] = new EClassValue(BaseCSPackage.Literals.ANNOTATION_CS,
			createSerializationRules(
				81 /* AnnotationCS-0: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] ';' */,
				82 /* AnnotationCS-1: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:+] (AnnotationCS::ownedContents+=ModelElementCS)[V4:*] (AnnotationCS::ownedReferences+=ModelElementRefCS)[V5:*] '}' */,
				83 /* AnnotationCS-2: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (AnnotationCS::ownedContents+=ModelElementCS)[V4:+] (AnnotationCS::ownedReferences+=ModelElementRefCS)[V5:*] '}' */,
				84 /* AnnotationCS-3: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (AnnotationCS::ownedContents+=ModelElementCS)[V4:*] (AnnotationCS::ownedReferences+=ModelElementRefCS)[V5:+] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					45) /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					6) /* DetailCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					18) /* ModelElementRefCS */
			}
		);
		eClassValues[1] = new EClassValue(BaseCSPackage.Literals.ATTRIBUTE_CS,
			createSerializationRules(
				89 /* AttributeCS-4: 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V1:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V3:+] '}')[V2:?] ';' */,
				85 /* AttributeCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				87 /* AttributeCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				90 /* AttributeCS-5: 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V1:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V3:+] '}')[V2:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V5:?] ';')[V4:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] '}' */,
				86 /* AttributeCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */,
				88 /* AttributeCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					39) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					59) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[2] = new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
			createSerializationRules(
				16 /* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			), null
		);
		eClassValues[3] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
			createSerializationRules(
				18 /* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					3) /* CollectionLiteralPartCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					4) /* CollectionTypeCS */
			}
		);
		eClassValues[4] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
			createSerializationRules(
				20 /* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */,
				19 /* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[5] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
			createSerializationRules(
				21 /* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				69 /* TypeExpCS-4: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					29) /* PatternExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					4) /* CollectionTypeCS */
			}
		);
		eClassValues[6] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
			createSerializationRules(
				22 /* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				66 /* TypeExpCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				73 /* TypeLiteralWithMultiplicityCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				144 /* TypedMultiplicityRefCS-4: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					56) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[7] = new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
			createSerializationRules(
				34 /* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[8] = new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				23 /* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					38) /* ShadowPartCS */
			}
		);
		eClassValues[9] = new EClassValue(BaseCSPackage.Literals.DATA_TYPE_CS,
			createSerializationRules(
				95 /* DataTypeCS-4: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '}')[V3:?] ';' */,
				91 /* DataTypeCS-0: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '!serializable' '}')[V3:?] ';' */,
				93 /* DataTypeCS-2: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' DataTypeCS::isSerializable?='serializable' '}')[V3:?] ';' */,
				96 /* DataTypeCS-5: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				92 /* DataTypeCS-1: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '!serializable' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				94 /* DataTypeCS-3: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' DataTypeCS::isSerializable?='serializable' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					13) /* InvariantConstraintCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					49) /* TemplateSignatureCS */
			}
		);
		eClassValues[10] = new EClassValue(BaseCSPackage.Literals.DETAIL_CS,
			createSerializationRules(
				97 /* DetailCS-0: NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING '=' (DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[V0:*] */
			), null
		);
		eClassValues[11] = new EClassValue(BaseCSPackage.Literals.DOCUMENTATION_CS,
			createSerializationRules(
				98 /* DocumentationCS-0: 'documentation' (DocumentationCS::value=SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					6) /* DetailCS */
			}
		);
		eClassValues[12] = new EClassValue(BaseCSPackage.Literals.ENUMERATION_CS,
			createSerializationRules(
				103 /* EnumerationCS-4: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '}')[V2:?] ';' */,
				99 /* EnumerationCS-0: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '!serializable' '}')[V2:?] ';' */,
				101 /* EnumerationCS-2: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' EnumerationCS::isSerializable?='serializable' '}')[V2:?] ';' */,
				104 /* EnumerationCS-5: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				100 /* EnumerationCS-1: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '!serializable' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				102 /* EnumerationCS-3: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' EnumerationCS::isSerializable?='serializable' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					13) /* InvariantConstraintCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					8) /* EnumerationLiteralCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					49) /* TemplateSignatureCS */
			}
		);
		eClassValues[13] = new EClassValue(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS,
			createSerializationRules(
				107 /* EnumerationLiteralCS-2: NamedElementCS::name=EnumerationLiteralName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] ';' */,
				105 /* EnumerationLiteralCS-0: 'literal' NamedElementCS::name=UnrestrictedName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] ';' */,
				108 /* EnumerationLiteralCS-3: NamedElementCS::name=EnumerationLiteralName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V2:*] '}' */,
				106 /* EnumerationLiteralCS-1: 'literal' NamedElementCS::name=UnrestrictedName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V2:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
			}
		);
		eClassValues[14] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
			createSerializationRules(
				131 /* SpecificationCS-0: ExpSpecificationCS::ownedExpression=ExpCS */,
				132 /* SpecificationCS-1: SpecificationCS::exprString=UNQUOTED_STRING */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[15] = new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
			createSerializationRules(
				26 /* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					7) /* ElseIfThenExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[16] = new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
			createSerializationRules(
				24 /* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[17] = new EClassValue(BaseCSPackage.Literals.IMPLICIT_OPPOSITE_CS,
			createSerializationRules(
				109 /* ImplicitOppositeCS-0: 'opposite' NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypedMultiplicityRefCS ('{' (TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique')[V1:+] '}')[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					59) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[18] = new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
			createSerializationRules(
				110 /* ImportCS-0: 'import' (NamedElementCS::name=UnrestrictedName ':')[V0:?] ImportCS::ownedPathName=URIPathNameCS (ImportCS::isAll?='::*')[V1:?] ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					64) /* URIPathNameCS */
			}
		);
		eClassValues[19] = new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
			createSerializationRules(
				25 /* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					67) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[20] = new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
			createSerializationRules(
				27 /* InvalidLiteralExpCS-0: 'invalid' */
			), null
		);
		eClassValues[21] = new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
			createSerializationRules(
				28 /* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[22] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
			createSerializationRules(
				29 /* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					14) /* LetVariableCS */
			}
		);
		eClassValues[23] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
			createSerializationRules(
				30 /* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					36) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					52) /* TypeExpCS */
			}
		);
		eClassValues[24] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
			createSerializationRules(
				31 /* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					15) /* MapLiteralPartCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					16) /* MapTypeCS */
			}
		);
		eClassValues[25] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
			createSerializationRules(
				32 /* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[26] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
			createSerializationRules(
				33 /* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				67 /* TypeExpCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				74 /* TypeLiteralWithMultiplicityCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				145 /* TypedMultiplicityRefCS-5: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					52) /* TypeExpCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					52) /* TypeExpCS */
			}
		);
		eClassValues[27] = new EClassValue(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS,
			createSerializationRules(
				113 /* ModelElementRefCS-0: 'reference' ModelElementRefCS::ownedPathName=PathNameCS ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
					28) /* PathNameCS */
			}
		);
		eClassValues[28] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */,
				4 /* MultiplicityCS-2: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] ']' */,
				2 /* MultiplicityCS-0: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] '|?' ']' */,
				3 /* MultiplicityCS-1: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] MultiplicityCS::isNullFree?='|1' ']' */
			), null
		);
		eClassValues[29] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				7 /* MultiplicityCS-5: '[' MultiplicityStringCS::stringBounds='*|+|?' ']' */,
				5 /* MultiplicityCS-3: '[' MultiplicityStringCS::stringBounds='*|+|?' '|?' ']' */,
				6 /* MultiplicityCS-4: '[' MultiplicityStringCS::stringBounds='*|+|?' MultiplicityCS::isNullFree?='|1' ']' */,
				8 /* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			), null
		);
		eClassValues[30] = new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
			createSerializationRules(
				35 /* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					5) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					28) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					36) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					40) /* SquareBracketedClauseCS */
			}
		);
		eClassValues[31] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
			createSerializationRules(
				40 /* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				36 /* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				39 /* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				37 /* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				38 /* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				41 /* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */,
				45 /* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				44 /* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				42 /* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				43 /* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				46 /* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					2) /* CoIteratorVariableCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					70) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					52) /* TypeExpCS */
			}
		);
		eClassValues[32] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				47 /* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[33] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				48 /* NullLiteralExpCS-0: 'null' */
			), null
		);
		eClassValues[34] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				49 /* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[35] = new EClassValue(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS,
			createSerializationRules(
				111 /* InvariantConstraintCS-0: (OCLinEcoreConstraintCS::isCallable?='callable')[V0:?] ConstraintCS::stereotype='invariant' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V2:?])[V1:?] ';' */,
				112 /* InvariantConstraintCS-1: (OCLinEcoreConstraintCS::isCallable?='callable')[V0:?] ConstraintCS::stereotype='invariant' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V2:?])[V1:?] ':' (ConstraintCS::ownedSpecification=SpecificationCS)[V3:?] ';' */,
				123 /* PostconditionConstraintCS-0: ConstraintCS::stereotype='postcondition' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' (ConstraintCS::ownedSpecification=SpecificationCS)[V2:?] ';' */,
				124 /* PreconditionConstraintCS-0: ConstraintCS::stereotype='precondition' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' (ConstraintCS::ownedSpecification=SpecificationCS)[V2:?] ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					39) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					39) /* SpecificationCS */
			}
		);
		eClassValues[36] = new EClassValue(BaseCSPackage.Literals.OPERATION_CS,
			createSerializationRules(
				118 /* OperationCS-4: 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V3:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V5:*])[V4:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V7:+] '}')[V6:?] ';' */,
				114 /* OperationCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] ';' */,
				116 /* OperationCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] ';' */,
				119 /* OperationCS-5: 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V3:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V5:*])[V4:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V7:+] '}')[V6:?] '{' ('body' ':' (OperationCS::ownedBodyExpressions+=SpecificationCS)[V9:?] ';')[V8:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V10:*] (OperationCS::ownedPreconditions+=PreconditionConstraintCS)[V11:*] (OperationCS::ownedPostconditions+=PostconditionConstraintCS)[V12:*] '}' */,
				115 /* OperationCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] '{' ('body' ':' (OperationCS::ownedBodyExpressions+=SpecificationCS)[V10:?] ';')[V9:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V11:*] (OperationCS::ownedPreconditions+=PreconditionConstraintCS)[V12:*] (OperationCS::ownedPostconditions+=PostconditionConstraintCS)[V13:*] '}' */,
				117 /* OperationCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] '{' ('body' ':' (OperationCS::ownedBodyExpressions+=SpecificationCS)[V10:?] ';')[V9:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V11:*] (OperationCS::ownedPreconditions+=PreconditionConstraintCS)[V12:*] (OperationCS::ownedPostconditions+=PostconditionConstraintCS)[V13:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					39) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					27) /* ParameterCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					31) /* PostconditionConstraintCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					32) /* PreconditionConstraintCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					49) /* TemplateSignatureCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					59) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[37] = new EClassValue(BaseCSPackage.Literals.PACKAGE_CS,
			createSerializationRules(
				120 /* PackageCS-0: 'package' NamedElementCS::name=UnrestrictedName (':' PackageCS::nsPrefix=UnrestrictedName)[V0:?] ('=' PackageCS::nsURI=URI)[V1:?] ';' */,
				121 /* PackageCS-1: 'package' NamedElementCS::name=UnrestrictedName (':' PackageCS::nsPrefix=UnrestrictedName)[V0:?] ('=' PackageCS::nsURI=URI)[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V2:*] (PackageOwnerCS::ownedPackages+=PackageCS)[V3:*] (PackageCS::ownedClasses+=ClassCS)[V4:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					44) /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					26) /* PackageCS */
			}
		);
		eClassValues[38] = new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
			createSerializationRules(
				122 /* ParameterCS-0: NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('{' (TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique')[V2:+] '}')[V1:?] ('{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] '}')[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					59) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[39] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */,
				9 /* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */,
				78 /* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */
			), null
		);
		eClassValues[40] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				77 /* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */
			), null
		);
		eClassValues[41] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				14 /* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				58 /* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */,
				79 /* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					63) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
			}
		);
		eClassValues[42] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				50 /* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					52) /* TypeExpCS */
			}
		);
		eClassValues[43] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				51 /* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				52 /* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					68) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[44] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
			createSerializationRules(
				53 /* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				65 /* TypeExpCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				72 /* TypeLiteralWithMultiplicityCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				140 /* TypedMultiplicityRefCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */
			}
		);
		eClassValues[45] = new EClassValue(BaseCSPackage.Literals.REFERENCE_CS,
			createSerializationRules(
				129 /* ReferenceCS-4: 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V0:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				125 /* ReferenceCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] ';' */,
				127 /* ReferenceCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] ';' */,
				130 /* ReferenceCS-5: 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V0:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V10:*] ';')[V9:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V11:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V12:*] '}' */,
				126 /* ReferenceCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V9:?] ';')[V8:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V11:*] ';')[V10:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V12:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V13:*] '}' */,
				128 /* ReferenceCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V9:?] ';')[V8:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V11:*] ';')[V10:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V12:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V13:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					39) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					11) /* ImplicitOppositeCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					59) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[46] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				54 /* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					22) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[47] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				55 /* SelfExpCS-0: 'self' */
			), null
		);
		eClassValues[48] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				57 /* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */,
				56 /* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[49] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				59 /* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[50] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				60 /* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			), null
		);
		eClassValues[51] = new EClassValue(BaseCSPackage.Literals.STRUCTURED_CLASS_CS,
			createSerializationRules(
				133 /* StructuredClassCS-0: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'class' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] ('extends' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V3:*])[V2:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V4:?] ('{' (StructuredClassCS::isInterface?='interface')[V6:?] '}')[V5:?] ';' */,
				134 /* StructuredClassCS-1: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'class' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] ('extends' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V3:*])[V2:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V4:?] ('{' (StructuredClassCS::isInterface?='interface')[V6:?] '}')[V5:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V7:*] (StructuredClassCS::ownedOperations+=OperationCS)[V8:*] (StructuredClassCS::ownedProperties+=StructuralFeatureCS)[V9:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V10:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					46) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					13) /* InvariantConstraintCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					25) /* OperationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					43) /* AttributeCS|ReferenceCS|StructuralFeatureCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					49) /* TemplateSignatureCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[52] = new EClassValue(OCLinEcoreCSPackage.Literals.SYS_MLCS,
			createSerializationRules(
				136 /* SysMLCS-1: 'sysml' AnnotationElementCS::ownedDetails+=DetailCS ';' */,
				135 /* SysMLCS-0: 'sysml' '{' (AnnotationElementCS::ownedDetails+=DetailCS ';')[V0:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					6) /* DetailCS */
			}
		);
		eClassValues[53] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					48) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[54] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					73) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[55] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				137 /* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */,
				138 /* TemplateSignatureCS-1: '<' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] '>' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					57) /* TypeParameterCS */
			}
		);
		eClassValues[56] = new EClassValue(OCLinEcoreCSPackage.Literals.TOP_LEVEL_CS,
			createSerializationRules(
				139 /* TopLevelCS-0: ('module')[V0:?] (RootCS::ownedImports+=ImportCS)[V1:*] (PackageOwnerCS::ownedPackages+=PackageCS)[V2:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					12) /* ImportCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					26) /* PackageCS */
			}
		);
		eClassValues[57] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				61 /* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					50) /* TupleLiteralPartCS */
			}
		);
		eClassValues[58] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
			createSerializationRules(
				62 /* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					52) /* TypeExpCS */
			}
		);
		eClassValues[59] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				63 /* TuplePartCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					52) /* TypeExpCS */
			}
		);
		eClassValues[60] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
			createSerializationRules(
				64 /* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				70 /* TypeExpCS-5: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
				75 /* TypeLiteralWithMultiplicityCS-3: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
				146 /* TypedMultiplicityRefCS-6: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					51) /* TuplePartCS */
			}
		);
		eClassValues[61] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				71 /* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					55) /* TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[62] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
			createSerializationRules(
				68 /* TypeExpCS-3: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				76 /* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					5) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					28) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[63] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				13 /* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[64] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				149 /* TypedTypeRefCS-2: TypedTypeRefCS::ownedPathName=PathNameCS */,
				147 /* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS '(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')' */,
				148 /* TypedTypeRefCS-1: TypedTypeRefCS::ownedPathName=PathNameCS '<' TypedTypeRefCS::ownedBinding=TemplateBindingCS '>' */,
				141 /* TypedMultiplicityRefCS-1: TypedTypeRefCS::ownedPathName=PathNameCS (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				142 /* TypedMultiplicityRefCS-2: TypedTypeRefCS::ownedPathName=PathNameCS '(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				143 /* TypedMultiplicityRefCS-3: TypedTypeRefCS::ownedPathName=PathNameCS '<' TypedTypeRefCS::ownedBinding=TemplateBindingCS '>' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					47) /* TemplateBindingCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					28) /* PathNameCS */
			}
		);
		eClassValues[65] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				80 /* UnlimitedNaturalLiteralExpCS-0: '*' */
			), null
		);
		eClassValues[66] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				17 /* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					52) /* TypeExpCS */
			}
		);
		eClassValues[67] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				15 /* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
			}
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// 0: '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'
		enumerationValues[0] = new EnumerationValueMultiple(new @NonNull String[]{"!composes", "!derived", "!ordered", "!readonly", "!resolve", "!transient", "!unique", "!unsettable", "!volatile", "composes", "derived", "ordered", "readonly", "resolve", "transient", "unique", "unsettable", "volatile"});
		// 1: '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'
		enumerationValues[1] = new EnumerationValueMultiple(new @NonNull String[]{"!derived", "!id", "!ordered", "!readonly", "!transient", "!unique", "!unsettable", "!volatile", "derived", "id", "ordered", "readonly", "transient", "unique", "unsettable", "volatile"});
		// 2: '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'
		enumerationValues[2] = new EnumerationValueMultiple(new @NonNull String[]{"!derived", "!ordered", "!transient", "!unique", "derived", "ordered", "transient", "unique"});
		// 3: '!ordered|!unique|ordered|unique'
		enumerationValues[3] = new EnumerationValueMultiple(new @NonNull String[]{"!ordered", "!unique", "ordered", "unique"});
		// 4: '*|+|?'
		enumerationValues[4] = new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		// 5: ','
		enumerationValues[5] = new EnumerationValueSingle(",");
		// 6: '::*'
		enumerationValues[6] = new EnumerationValueSingle("::*");
		// 7: ';'
		enumerationValues[7] = new EnumerationValueSingle(";");
		// 8: '@'
		enumerationValues[8] = new EnumerationValueSingle("@");
		// 9: 'Map'
		enumerationValues[9] = new EnumerationValueSingle("Map");
		// 10: 'Tuple'
		enumerationValues[10] = new EnumerationValueSingle("Tuple");
		// 11: 'abstract'
		enumerationValues[11] = new EnumerationValueSingle("abstract");
		// 12: 'callable'
		enumerationValues[12] = new EnumerationValueSingle("callable");
		// 13: 'definition'
		enumerationValues[13] = new EnumerationValueSingle("definition");
		// 14: 'false|true'
		enumerationValues[14] = new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		// 15: 'interface'
		enumerationValues[15] = new EnumerationValueSingle("interface");
		// 16: 'invariant'
		enumerationValues[16] = new EnumerationValueSingle("invariant");
		// 17: 'postcondition'
		enumerationValues[17] = new EnumerationValueSingle("postcondition");
		// 18: 'precondition'
		enumerationValues[18] = new EnumerationValueSingle("precondition");
		// 19: 'primitive'
		enumerationValues[19] = new EnumerationValueSingle("primitive");
		// 20: 'serializable'
		enumerationValues[20] = new EnumerationValueSingle("serializable");
		// 21: 'static'
		enumerationValues[21] = new EnumerationValueSingle("static");
		// 22: '|'
		enumerationValues[22] = new EnumerationValueSingle("|");
		// 23: '|1'
		enumerationValues[23] = new EnumerationValueSingle("|1");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = createParserRuleValue(1, "AnnotationCS", -1,
			createSerializationRules(
				81	/* AnnotationCS-0: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] ';' */,
				82	/* AnnotationCS-1: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:+] (AnnotationCS::ownedContents+=ModelElementCS)[V4:*] (AnnotationCS::ownedReferences+=ModelElementRefCS)[V5:*] '}' */,
				83	/* AnnotationCS-2: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (AnnotationCS::ownedContents+=ModelElementCS)[V4:+] (AnnotationCS::ownedReferences+=ModelElementRefCS)[V5:*] '}' */,
				84	/* AnnotationCS-3: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (AnnotationCS::ownedContents+=ModelElementCS)[V4:*] (AnnotationCS::ownedReferences+=ModelElementRefCS)[V5:+] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {AnnotationCS} : [value] | [value] */,
			(0 << 16) | 11	/* "annotation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=(UnrestrictedName|SINGLE_QUOTED_STRING)? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives+ : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedContents+=ModelElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedReferences+=ModelElementRefCS : [value] | [value] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[2] = createParserRuleValue(2, "AnnotationElementCS", 46 /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
			createSerializationRules(
				81	/* AnnotationCS-0: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] ';' */,
				82	/* AnnotationCS-1: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:+] (AnnotationCS::ownedContents+=ModelElementCS)[V4:*] (AnnotationCS::ownedReferences+=ModelElementRefCS)[V5:*] '}' */,
				83	/* AnnotationCS-2: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (AnnotationCS::ownedContents+=ModelElementCS)[V4:+] (AnnotationCS::ownedReferences+=ModelElementRefCS)[V5:*] '}' */,
				84	/* AnnotationCS-3: 'annotation' (NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (AnnotationCS::ownedContents+=ModelElementCS)[V4:*] (AnnotationCS::ownedReferences+=ModelElementRefCS)[V5:+] '}' */,
				98	/* DocumentationCS-0: 'documentation' (DocumentationCS::value=SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] ';' */,
				135	/* SysMLCS-0: 'sysml' '{' (AnnotationElementCS::ownedDetails+=DetailCS ';')[V0:*] '}' */,
				136	/* SysMLCS-1: 'sysml' AnnotationElementCS::ownedDetails+=DetailCS ';' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* AnnotationCS : [value] | [value] */,
			(0 << 16) | 0	/* DocumentationCS : [value] | [value] */,
			(0 << 16) | 0	/* SysMLCS : [value] | [value] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "AttributeCS", -1,
			createSerializationRules(
				85	/* AttributeCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				86	/* AttributeCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */,
				87	/* AttributeCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				88	/* AttributeCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */,
				89	/* AttributeCS-4: 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V1:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V3:+] '}')[V2:?] ';' */,
				90	/* AttributeCS-5: 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V1:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V3:+] '}')[V2:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V5:?] ';')[V4:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="static" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="definition"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="definition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "attribute" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* default=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="id" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!id" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="readonly" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!readonly" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="unsettable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!unsettable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="volatile" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!volatile" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 11	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "initial" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "derivation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 13	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[4] = createDataTypeRuleValue(4, "BinaryOperatorName", 11 /* [soft-space, value, soft-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '?->' : [no-space, value, no-space] */,
			3	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[5] = createParserRuleValue(5, "BooleanLiteralExpCS", -1,
			createSerializationRules(
				16	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* symbol="true" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* symbol="false" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[6] = createParserRuleValue(6, "ClassCS", 44 /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */,
			createSerializationRules(
				91	/* DataTypeCS-0: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '!serializable' '}')[V3:?] ';' */,
				92	/* DataTypeCS-1: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '!serializable' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				93	/* DataTypeCS-2: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' DataTypeCS::isSerializable?='serializable' '}')[V3:?] ';' */,
				94	/* DataTypeCS-3: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' DataTypeCS::isSerializable?='serializable' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				95	/* DataTypeCS-4: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '}')[V3:?] ';' */,
				96	/* DataTypeCS-5: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				99	/* EnumerationCS-0: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '!serializable' '}')[V2:?] ';' */,
				100	/* EnumerationCS-1: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '!serializable' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				101	/* EnumerationCS-2: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' EnumerationCS::isSerializable?='serializable' '}')[V2:?] ';' */,
				102	/* EnumerationCS-3: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' EnumerationCS::isSerializable?='serializable' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				103	/* EnumerationCS-4: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '}')[V2:?] ';' */,
				104	/* EnumerationCS-5: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				133	/* StructuredClassCS-0: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'class' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] ('extends' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V3:*])[V2:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V4:?] ('{' (StructuredClassCS::isInterface?='interface')[V6:?] '}')[V5:?] ';' */,
				134	/* StructuredClassCS-1: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'class' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] ('extends' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V3:*])[V2:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V4:?] ('{' (StructuredClassCS::isInterface?='interface')[V6:?] '}')[V5:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V7:*] (StructuredClassCS::ownedOperations+=OperationCS)[V8:*] (StructuredClassCS::ownedProperties+=StructuralFeatureCS)[V9:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V10:*] '}' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* StructuredClassCS : [value] | [value] */,
			(0 << 16) | 0	/* DataTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* EnumerationCS : [value] | [value] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "CoIteratorVariableCS", -1,
			createSerializationRules(
				17	/* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[8] = createParserRuleValue(8, "CollectionLiteralExpCS", -1,
			createSerializationRules(
				18	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[9] = createParserRuleValue(9, "CollectionLiteralPartCS", -1,
			createSerializationRules(
				19	/* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */,
				20	/* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedLastExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=PatternExpCS : [value] | [value] */
		);
		grammarRuleValues[10] = createParserRuleValue(10, "CollectionPatternCS", -1,
			createSerializationRules(
				21	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "++" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* restVariableName=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[11] = createParserRuleValue(11, "CollectionTypeCS", -1,
			createSerializationRules(
				22	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* name=CollectionTypeIdentifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedCollectionMultiplicity=MultiplicityCS? : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[12] = createDataTypeRuleValue(12, "CollectionTypeIdentifier", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[13] = createParserRuleValue(13, "CurlyBracketedClauseCS", -1,
			createSerializationRules(
				23	/* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {CurlyBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[14] = new TerminalRuleValue(14, "DOUBLE_QUOTED_STRING");
		grammarRuleValues[15] = createParserRuleValue(15, "DataTypeCS", -1,
			createSerializationRules(
				91	/* DataTypeCS-0: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '!serializable' '}')[V3:?] ';' */,
				92	/* DataTypeCS-1: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '!serializable' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				93	/* DataTypeCS-2: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' DataTypeCS::isSerializable?='serializable' '}')[V3:?] ';' */,
				94	/* DataTypeCS-3: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' DataTypeCS::isSerializable?='serializable' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				95	/* DataTypeCS-4: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '}')[V3:?] ';' */,
				96	/* DataTypeCS-5: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* isPrimitive?="primitive"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "datatype" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* instanceClassName=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 11	/* isSerializable?="serializable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "!serializable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-new-line, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedConstraints+=InvariantConstraintCS : [value] | [value] */,
			(0 << 16) | 13	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[16] = createParserRuleValue(16, "DetailCS", -1,
			createSerializationRules(
				97	/* DetailCS-0: NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING '=' (DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[V0:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* name=(UnrestrictedName|SINGLE_QUOTED_STRING) : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)* : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "DocumentationCS", -1,
			createSerializationRules(
				98	/* DocumentationCS-0: 'documentation' (DocumentationCS::value=SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {DocumentationCS} : [value] | [value] */,
			(0 << 16) | 11	/* "documentation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* value=SINGLE_QUOTED_STRING? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[18] = new TerminalRuleValue(18, "ESCAPED_CHARACTER");
		grammarRuleValues[19] = new TerminalRuleValue(19, "ESCAPED_ID");
		grammarRuleValues[20] = createParserRuleValue(20, "ElseIfThenExpCS", -1,
			createSerializationRules(
				24	/* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 22	/* "elseif" : [value] | [soft-new-line, pop, soft-space, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=ExpCS : [value] | [value] */,
			(0 << 16) | 14	/* "then" : [value] | [pop, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[21] = createParserRuleValue(21, "EnumerationCS", -1,
			createSerializationRules(
				99	/* EnumerationCS-0: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '!serializable' '}')[V2:?] ';' */,
				100	/* EnumerationCS-1: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '!serializable' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				101	/* EnumerationCS-2: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' EnumerationCS::isSerializable?='serializable' '}')[V2:?] ';' */,
				102	/* EnumerationCS-3: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' EnumerationCS::isSerializable?='serializable' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				103	/* EnumerationCS-4: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '}')[V2:?] ';' */,
				104	/* EnumerationCS-5: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* "enum" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* instanceClassName=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 11	/* isSerializable?="serializable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "!serializable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-new-line, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedLiterals+=EnumerationLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedConstraints+=InvariantConstraintCS : [value] | [value] */,
			(0 << 16) | 13	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[22] = createParserRuleValue(22, "EnumerationLiteralCS", -1,
			createSerializationRules(
				105	/* EnumerationLiteralCS-0: 'literal' NamedElementCS::name=UnrestrictedName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] ';' */,
				106	/* EnumerationLiteralCS-1: 'literal' NamedElementCS::name=UnrestrictedName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V2:*] '}' */,
				107	/* EnumerationLiteralCS-2: NamedElementCS::name=EnumerationLiteralName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] ';' */,
				108	/* EnumerationLiteralCS-3: NamedElementCS::name=EnumerationLiteralName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V2:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "literal" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=EnumerationLiteralName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* literal=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* value=SIGNED : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS* : [value] | [value] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[23] = createDataTypeRuleValue(23, "EnumerationLiteralName", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[24] = createDataTypeRuleValue(24, "EssentialOCLInfixOperatorName", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[25] = createDataTypeRuleValue(25, "EssentialOCLNavigationOperatorName", 6 /* [no-space, value, no-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '?->' : [no-space, value, no-space] */,
			3	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[26] = createDataTypeRuleValue(26, "EssentialOCLReservedKeyword", 11 /* [soft-space, value, soft-space] */,
			4	/* 'else' : [soft-new-line, pop, value, push, soft-space] */,
			5	/* 'endif' : [soft-new-line, pop, value, soft-space] */,
			6	/* 'if' : [soft-new-line, value, push, soft-space] */,
			7	/* 'in' : [soft-space, pop, value, soft-new-line] */,
			8	/* 'let' : [soft-space, value, push] */,
			9	/* 'then' : [pop, soft-space, value, push, soft-space] */);
		grammarRuleValues[27] = createDataTypeRuleValue(27, "EssentialOCLUnaryOperatorName", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[28] = createDataTypeRuleValue(28, "EssentialOCLUnreservedName", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[29] = createDataTypeRuleValue(29, "EssentialOCLUnrestrictedName", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[30] = createParserRuleValue(30, "ExpCS", 69 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				16	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				18	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				25	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				26	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				27	/* InvalidLiteralExpCS-0: 'invalid' */,
				28	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				29	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				31	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				35	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				47	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				48	/* NullLiteralExpCS-0: 'null' */,
				49	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				51	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				52	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				55	/* SelfExpCS-0: 'self' */,
				60	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				61	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				71	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* {InfixExpCS} : [value] | [value] */,
			(0 << 16) | 11	/* name=BinaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedLetExpCS : [value] | [value] */
		);
		grammarRuleValues[31] = createParserRuleValue(31, "FirstPathElementCS", -1,
			createSerializationRules(
				0	/* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 11	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[32] = createDataTypeRuleValue(32, "ID", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[33] = new TerminalRuleValue(33, "INT");
		grammarRuleValues[34] = createDataTypeRuleValue(34, "INTEGER", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[35] = createDataTypeRuleValue(35, "Identifier", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[36] = createParserRuleValue(36, "IfExpCS", -1,
			createSerializationRules(
				26	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 17	/* "if" : [value] | [soft-new-line, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 20	/* "then" : [value] | [pop, soft-space, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedIfThenExpressions+=ElseIfThenExpCS* : [value] | [value] */,
			(0 << 16) | 21	/* "else" : [value] | [soft-new-line, pop, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedElseExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 15	/* "endif" : [value] | [soft-new-line, pop, value, soft-space] */
		);
		grammarRuleValues[37] = createParserRuleValue(37, "ImplicitOppositeCS", -1,
			createSerializationRules(
				109	/* ImplicitOppositeCS-0: 'opposite' NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypedMultiplicityRefCS ('{' (TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique')[V1:+] '}')[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* "opposite" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[38] = createParserRuleValue(38, "ImportCS", -1,
			createSerializationRules(
				110	/* ImportCS-0: 'import' (NamedElementCS::name=UnrestrictedName ':')[V0:?] ImportCS::ownedPathName=URIPathNameCS (ImportCS::isAll?='::*')[V1:?] ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* "import" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "library" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=URIPathNameCS : [value] | [value] */,
			(0 << 16) | 11	/* isAll?="::*"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[39] = createDataTypeRuleValue(39, "InfixOperatorName", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[40] = createParserRuleValue(40, "InvalidLiteralExpCS", -1,
			createSerializationRules(
				27	/* InvalidLiteralExpCS-0: 'invalid' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {InvalidLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 11	/* "invalid" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "InvariantConstraintCS", -1,
			createSerializationRules(
				111	/* InvariantConstraintCS-0: (OCLinEcoreConstraintCS::isCallable?='callable')[V0:?] ConstraintCS::stereotype='invariant' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V2:?])[V1:?] ';' */,
				112	/* InvariantConstraintCS-1: (OCLinEcoreConstraintCS::isCallable?='callable')[V0:?] ConstraintCS::stereotype='invariant' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V2:?])[V1:?] ':' (ConstraintCS::ownedSpecification=SpecificationCS)[V3:?] ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* isCallable?="callable"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* stereotype="invariant" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[42] = new TerminalRuleValue(42, "LETTER_CHARACTER");
		grammarRuleValues[43] = createDataTypeRuleValue(43, "LOWER", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[44] = createParserRuleValue(44, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				28	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[45] = createParserRuleValue(45, "LetExpCS", -1,
			createSerializationRules(
				29	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 9	/* "let" : [value] | [soft-space, value, push] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 18	/* "in" : [value] | [soft-space, pop, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedInExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[46] = createParserRuleValue(46, "LetVariableCS", -1,
			createSerializationRules(
				30	/* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[47] = new TerminalRuleValue(47, "ML_COMMENT");
		grammarRuleValues[48] = new TerminalRuleValue(48, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[49] = createParserRuleValue(49, "MapLiteralExpCS", -1,
			createSerializationRules(
				31	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=MapTypeCS : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[50] = createParserRuleValue(50, "MapLiteralPartCS", -1,
			createSerializationRules(
				32	/* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedKey=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValue=ExpCS : [value] | [value] */
		);
		grammarRuleValues[51] = createParserRuleValue(51, "MapTypeCS", -1,
			createSerializationRules(
				33	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* name="Map" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedKeyType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValueType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[52] = createParserRuleValue(52, "Model", -1,
			createSerializationRules(
				34	/* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[53] = createParserRuleValue(53, "ModelElementCS", 45 /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
			createSerializationRules(
				85	/* AttributeCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				86	/* AttributeCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */,
				87	/* AttributeCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				88	/* AttributeCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */,
				89	/* AttributeCS-4: 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V1:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V3:+] '}')[V2:?] ';' */,
				90	/* AttributeCS-5: 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V1:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V3:+] '}')[V2:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V5:?] ';')[V4:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] '}' */,
				91	/* DataTypeCS-0: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '!serializable' '}')[V3:?] ';' */,
				92	/* DataTypeCS-1: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '!serializable' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				93	/* DataTypeCS-2: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' DataTypeCS::isSerializable?='serializable' '}')[V3:?] ';' */,
				94	/* DataTypeCS-3: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' DataTypeCS::isSerializable?='serializable' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				95	/* DataTypeCS-4: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '}')[V3:?] ';' */,
				96	/* DataTypeCS-5: (DataTypeCS::isPrimitive?='primitive')[V0:?] 'datatype' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V2:?] ('{' '}')[V3:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				99	/* EnumerationCS-0: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '!serializable' '}')[V2:?] ';' */,
				100	/* EnumerationCS-1: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '!serializable' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				101	/* EnumerationCS-2: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' EnumerationCS::isSerializable?='serializable' '}')[V2:?] ';' */,
				102	/* EnumerationCS-3: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' EnumerationCS::isSerializable?='serializable' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				103	/* EnumerationCS-4: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '}')[V2:?] ';' */,
				104	/* EnumerationCS-5: 'enum' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V1:?] ('{' '}')[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] (EnumerationCS::ownedLiterals+=EnumerationLiteralCS)[V4:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V5:*] '}' */,
				105	/* EnumerationLiteralCS-0: 'literal' NamedElementCS::name=UnrestrictedName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] ';' */,
				106	/* EnumerationLiteralCS-1: 'literal' NamedElementCS::name=UnrestrictedName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V2:*] '}' */,
				107	/* EnumerationLiteralCS-2: NamedElementCS::name=EnumerationLiteralName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] ';' */,
				108	/* EnumerationLiteralCS-3: NamedElementCS::name=EnumerationLiteralName (':' EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING)[V0:?] ('=' EnumerationLiteralCS::value=SIGNED)[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V2:*] '}' */,
				114	/* OperationCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] ';' */,
				115	/* OperationCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] '{' ('body' ':' (OperationCS::ownedBodyExpressions+=SpecificationCS)[V10:?] ';')[V9:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V11:*] (OperationCS::ownedPreconditions+=PreconditionConstraintCS)[V12:*] (OperationCS::ownedPostconditions+=PostconditionConstraintCS)[V13:*] '}' */,
				116	/* OperationCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] ';' */,
				117	/* OperationCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] '{' ('body' ':' (OperationCS::ownedBodyExpressions+=SpecificationCS)[V10:?] ';')[V9:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V11:*] (OperationCS::ownedPreconditions+=PreconditionConstraintCS)[V12:*] (OperationCS::ownedPostconditions+=PostconditionConstraintCS)[V13:*] '}' */,
				118	/* OperationCS-4: 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V3:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V5:*])[V4:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V7:+] '}')[V6:?] ';' */,
				119	/* OperationCS-5: 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V3:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V5:*])[V4:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V7:+] '}')[V6:?] '{' ('body' ':' (OperationCS::ownedBodyExpressions+=SpecificationCS)[V9:?] ';')[V8:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V10:*] (OperationCS::ownedPreconditions+=PreconditionConstraintCS)[V11:*] (OperationCS::ownedPostconditions+=PostconditionConstraintCS)[V12:*] '}' */,
				120	/* PackageCS-0: 'package' NamedElementCS::name=UnrestrictedName (':' PackageCS::nsPrefix=UnrestrictedName)[V0:?] ('=' PackageCS::nsURI=URI)[V1:?] ';' */,
				121	/* PackageCS-1: 'package' NamedElementCS::name=UnrestrictedName (':' PackageCS::nsPrefix=UnrestrictedName)[V0:?] ('=' PackageCS::nsURI=URI)[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V2:*] (PackageOwnerCS::ownedPackages+=PackageCS)[V3:*] (PackageCS::ownedClasses+=ClassCS)[V4:*] '}' */,
				125	/* ReferenceCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] ';' */,
				126	/* ReferenceCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V9:?] ';')[V8:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V11:*] ';')[V10:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V12:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V13:*] '}' */,
				127	/* ReferenceCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] ';' */,
				128	/* ReferenceCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V9:?] ';')[V8:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V11:*] ';')[V10:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V12:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V13:*] '}' */,
				129	/* ReferenceCS-4: 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V0:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				130	/* ReferenceCS-5: 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V0:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V10:*] ';')[V9:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V11:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V12:*] '}' */,
				133	/* StructuredClassCS-0: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'class' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] ('extends' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V3:*])[V2:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V4:?] ('{' (StructuredClassCS::isInterface?='interface')[V6:?] '}')[V5:?] ';' */,
				134	/* StructuredClassCS-1: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'class' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] ('extends' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V3:*])[V2:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V4:?] ('{' (StructuredClassCS::isInterface?='interface')[V6:?] '}')[V5:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V7:*] (StructuredClassCS::ownedOperations+=OperationCS)[V8:*] (StructuredClassCS::ownedProperties+=StructuralFeatureCS)[V9:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V10:*] '}' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ClassCS : [value] | [value] */,
			(0 << 16) | 0	/* EnumerationLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* OperationCS : [value] | [value] */,
			(0 << 16) | 0	/* PackageCS : [value] | [value] */,
			(0 << 16) | 0	/* StructuralFeatureCS : [value] | [value] */
		);
		grammarRuleValues[54] = createParserRuleValue(54, "ModelElementRefCS", -1,
			createSerializationRules(
				113	/* ModelElementRefCS-0: 'reference' ModelElementRefCS::ownedPathName=PathNameCS ';' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "reference" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[55] = createParserRuleValue(55, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 11	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[56] = createParserRuleValue(56, "MultiplicityCS", -1,
			createSerializationRules(
				2	/* MultiplicityCS-0: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] '|?' ']' */,
				3	/* MultiplicityCS-1: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] MultiplicityCS::isNullFree?='|1' ']' */,
				4	/* MultiplicityCS-2: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] ']' */,
				5	/* MultiplicityCS-3: '[' MultiplicityStringCS::stringBounds='*|+|?' '|?' ']' */,
				6	/* MultiplicityCS-4: '[' MultiplicityStringCS::stringBounds='*|+|?' MultiplicityCS::isNullFree?='|1' ']' */,
				7	/* MultiplicityCS-5: '[' MultiplicityStringCS::stringBounds='*|+|?' ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityBoundsCS : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityStringCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 6	/* "|?" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 6	/* isNullFree?="|1" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[57] = createParserRuleValue(57, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			),
			(0 << 16) | 11	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[58] = createDataTypeRuleValue(58, "NUMBER_LITERAL", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[59] = createParserRuleValue(59, "NameExpCS", -1,
			createSerializationRules(
				35	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedSquareBracketedClauses+=SquareBracketedClauseCS* : [value] | [value] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* isPre?="@" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "pre" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[60] = createParserRuleValue(60, "NavigatingArgCS", -1,
			createSerializationRules(
				36	/* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				37	/* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				38	/* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				39	/* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				40	/* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 11	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[61] = createParserRuleValue(61, "NavigatingArgExpCS", 70 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				16	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				18	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				25	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				26	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				27	/* InvalidLiteralExpCS-0: 'invalid' */,
				28	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				29	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				31	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				35	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				47	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				48	/* NullLiteralExpCS-0: 'null' */,
				49	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				51	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				52	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				55	/* SelfExpCS-0: 'self' */,
				60	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				61	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				71	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* ExpCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[62] = createParserRuleValue(62, "NavigatingBarArgCS", -1,
			createSerializationRules(
				41	/* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* prefix="|" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[63] = createParserRuleValue(63, "NavigatingCommaArgCS", -1,
			createSerializationRules(
				42	/* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				43	/* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				44	/* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				45	/* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* prefix="," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 11	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[64] = createParserRuleValue(64, "NavigatingSemiArgCS", -1,
			createSerializationRules(
				46	/* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* prefix=";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[65] = createDataTypeRuleValue(65, "NavigationOperatorName", 11 /* [soft-space, value, soft-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '?->' : [no-space, value, no-space] */,
			3	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[66] = createParserRuleValue(66, "NestedExpCS", -1,
			createSerializationRules(
				47	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 4	/* "(" : [value] | [value, no-space] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[67] = createParserRuleValue(67, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */
			),
			(0 << 16) | 11	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[68] = createParserRuleValue(68, "NullLiteralExpCS", -1,
			createSerializationRules(
				48	/* NullLiteralExpCS-0: 'null' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {NullLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 11	/* "null" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[69] = createParserRuleValue(69, "NumberLiteralExpCS", -1,
			createSerializationRules(
				49	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			),
			(0 << 16) | 2	/* symbol=NUMBER_LITERAL : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[70] = createParserRuleValue(70, "OperationCS", -1,
			createSerializationRules(
				114	/* OperationCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] ';' */,
				115	/* OperationCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] '{' ('body' ':' (OperationCS::ownedBodyExpressions+=SpecificationCS)[V10:?] ';')[V9:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V11:*] (OperationCS::ownedPreconditions+=PreconditionConstraintCS)[V12:*] (OperationCS::ownedPostconditions+=PostconditionConstraintCS)[V13:*] '}' */,
				116	/* OperationCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] ';' */,
				117	/* OperationCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V4:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V6:*])[V5:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V8:+] '}')[V7:?] '{' ('body' ':' (OperationCS::ownedBodyExpressions+=SpecificationCS)[V10:?] ';')[V9:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V11:*] (OperationCS::ownedPreconditions+=PreconditionConstraintCS)[V12:*] (OperationCS::ownedPostconditions+=PostconditionConstraintCS)[V13:*] '}' */,
				118	/* OperationCS-4: 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V3:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V5:*])[V4:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V7:+] '}')[V6:?] ';' */,
				119	/* OperationCS-5: 'operation' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] NamedElementCS::name=UnrestrictedName '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V3:?] ('throws' OperationCS::ownedExceptions+=TypedRefCS (',' OperationCS::ownedExceptions+=TypedRefCS)[V5:*])[V4:?] ('{' (TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique')[V7:+] '}')[V6:?] '{' ('body' ':' (OperationCS::ownedBodyExpressions+=SpecificationCS)[V9:?] ';')[V8:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V10:*] (OperationCS::ownedPreconditions+=PreconditionConstraintCS)[V11:*] (OperationCS::ownedPostconditions+=PostconditionConstraintCS)[V12:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="static" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="definition"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="definition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "operation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "throws" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExceptions+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExceptions+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 11	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPreconditions+=PreconditionConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "body" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedBodyExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedPostconditions+=PostconditionConstraintCS : [value] | [value] */,
			(0 << 16) | 13	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[71] = createParserRuleValue(71, "PackageCS", -1,
			createSerializationRules(
				120	/* PackageCS-0: 'package' NamedElementCS::name=UnrestrictedName (':' PackageCS::nsPrefix=UnrestrictedName)[V0:?] ('=' PackageCS::nsURI=URI)[V1:?] ';' */,
				121	/* PackageCS-1: 'package' NamedElementCS::name=UnrestrictedName (':' PackageCS::nsPrefix=UnrestrictedName)[V0:?] ('=' PackageCS::nsURI=URI)[V1:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V2:*] (PackageOwnerCS::ownedPackages+=PackageCS)[V3:*] (PackageCS::ownedClasses+=ClassCS)[V4:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* "package" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* nsPrefix=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* nsURI=URI : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-new-line, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 5	/* ownedPackages+=PackageCS : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 5	/* ownedClasses+=ClassCS : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 13	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[72] = createParserRuleValue(72, "ParameterCS", -1,
			createSerializationRules(
				122	/* ParameterCS-0: NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('{' (TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique')[V2:+] '}')[V1:?] ('{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V4:*] '}')[V3:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS* : [value] | [value] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[73] = createParserRuleValue(73, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[74] = createParserRuleValue(74, "PatternExpCS", -1,
			createSerializationRules(
				50	/* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* patternVariableName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPatternType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[75] = createParserRuleValue(75, "PostconditionConstraintCS", -1,
			createSerializationRules(
				123	/* PostconditionConstraintCS-0: ConstraintCS::stereotype='postcondition' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' (ConstraintCS::ownedSpecification=SpecificationCS)[V2:?] ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* stereotype="postcondition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 8	/* ":" : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[76] = createParserRuleValue(76, "PreconditionConstraintCS", -1,
			createSerializationRules(
				124	/* PreconditionConstraintCS-0: ConstraintCS::stereotype='precondition' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' (ConstraintCS::ownedSpecification=SpecificationCS)[V2:?] ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* stereotype="precondition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 8	/* ":" : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[77] = createParserRuleValue(77, "PrefixedLetExpCS", 34 /* LetExpCS|PrefixedLetExpCS */,
			createSerializationRules(
				29	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				51	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 11	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedLetExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LetExpCS : [value] | [value] */
		);
		grammarRuleValues[78] = createParserRuleValue(78, "PrefixedPrimaryExpCS", 67 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				16	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				18	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				26	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				27	/* InvalidLiteralExpCS-0: 'invalid' */,
				28	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				31	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				35	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				47	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				48	/* NullLiteralExpCS-0: 'null' */,
				49	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				52	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				55	/* SelfExpCS-0: 'self' */,
				60	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				61	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				71	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 11	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrimaryExpCS : [value] | [value] */
		);
		grammarRuleValues[79] = createParserRuleValue(79, "PrimaryExpCS", 66 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				16	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				18	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				26	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				27	/* InvalidLiteralExpCS-0: 'invalid' */,
				28	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				31	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				35	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				47	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				48	/* NullLiteralExpCS-0: 'null' */,
				49	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				55	/* SelfExpCS-0: 'self' */,
				60	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				61	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				71	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* NestedExpCS : [value] | [value] */,
			(0 << 16) | 0	/* IfExpCS : [value] | [value] */,
			(0 << 16) | 0	/* SelfExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* MapLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LambdaLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* NameExpCS : [value] | [value] */
		);
		grammarRuleValues[80] = createParserRuleValue(80, "PrimitiveLiteralExpCS", 65 /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				16	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				27	/* InvalidLiteralExpCS-0: 'invalid' */,
				48	/* NullLiteralExpCS-0: 'null' */,
				49	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				60	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* NumberLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* StringLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* BooleanLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* UnlimitedNaturalLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* InvalidLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* NullLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[81] = createParserRuleValue(81, "PrimitiveTypeCS", -1,
			createSerializationRules(
				53	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */
			),
			(0 << 16) | 11	/* name=PrimitiveTypeIdentifier : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[82] = createDataTypeRuleValue(82, "PrimitiveTypeIdentifier", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[83] = createParserRuleValue(83, "ReferenceCS", -1,
			createSerializationRules(
				125	/* ReferenceCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] ';' */,
				126	/* ReferenceCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V9:?] ';')[V8:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V11:*] ';')[V10:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V12:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V13:*] '}' */,
				127	/* ReferenceCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] ';' */,
				128	/* ReferenceCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V9:?] ';')[V8:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V11:*] ';')[V10:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V12:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V13:*] '}' */,
				129	/* ReferenceCS-4: 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V0:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				130	/* ReferenceCS-5: 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V0:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V10:*] ';')[V9:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V11:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V12:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="static" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="definition"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="definition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "property" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "#" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 11	/* referredOpposite=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* default=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* qualifiers+="composes" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!composes" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="readonly" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!readonly" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="resolve" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!resolve" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="unsettable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!unsettable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="volatile" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* qualifiers+="!volatile" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 11	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "key" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* referredKeys+=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 11	/* referredKeys+=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "initial" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "derivation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedImplicitOpposites+=ImplicitOppositeCS : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 13	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[84] = createParserRuleValue(84, "RoundBracketedClauseCS", -1,
			createSerializationRules(
				54	/* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {RoundBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=NavigatingArgCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)* : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[85] = createDataTypeRuleValue(85, "SIGNED", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[86] = new TerminalRuleValue(86, "SIMPLE_ID");
		grammarRuleValues[87] = new TerminalRuleValue(87, "SINGLE_QUOTED_STRING");
		grammarRuleValues[88] = new TerminalRuleValue(88, "SL_COMMENT");
		grammarRuleValues[89] = createParserRuleValue(89, "SelfExpCS", -1,
			createSerializationRules(
				55	/* SelfExpCS-0: 'self' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SelfExpCS} : [value] | [value] */,
			(0 << 16) | 11	/* "self" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[90] = createParserRuleValue(90, "ShadowPartCS", -1,
			createSerializationRules(
				56	/* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */,
				57	/* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* referredProperty=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 0	/* ownedInitExpression=StringLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[91] = createParserRuleValue(91, "SimplePathNameCS", -1,
			createSerializationRules(
				58	/* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */
			),
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */
		);
		grammarRuleValues[92] = createParserRuleValue(92, "SpecificationCS", -1,
			createSerializationRules(
				131	/* SpecificationCS-0: ExpSpecificationCS::ownedExpression=ExpCS */,
				132	/* SpecificationCS-1: SpecificationCS::exprString=UNQUOTED_STRING */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 11	/* exprString=UNQUOTED_STRING : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[93] = createParserRuleValue(93, "SquareBracketedClauseCS", -1,
			createSerializationRules(
				59	/* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[94] = createDataTypeRuleValue(94, "StringLiteral", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[95] = createParserRuleValue(95, "StringLiteralExpCS", -1,
			createSerializationRules(
				60	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			),
			(0 << 16) | 2	/* segments+=StringLiteral+ : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[96] = createParserRuleValue(96, "StructuralFeatureCS", 43 /* AttributeCS|ReferenceCS|StructuralFeatureCS */,
			createSerializationRules(
				85	/* AttributeCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				86	/* AttributeCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */,
				87	/* AttributeCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				88	/* AttributeCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */,
				89	/* AttributeCS-4: 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V1:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V3:+] '}')[V2:?] ';' */,
				90	/* AttributeCS-5: 'attribute' NamedElementCS::name=UnrestrictedName (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V0:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V1:?] ('{' (TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile')[V3:+] '}')[V2:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V5:?] ';')[V4:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] '}' */,
				125	/* ReferenceCS-0: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] ';' */,
				126	/* ReferenceCS-1: TypedElementCS::qualifiers+='definition' (TypedElementCS::qualifiers+='static')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V9:?] ';')[V8:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V11:*] ';')[V10:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V12:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V13:*] '}' */,
				127	/* ReferenceCS-2: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] ';' */,
				128	/* ReferenceCS-3: TypedElementCS::qualifiers+='static' (TypedElementCS::qualifiers+='definition')[V0:?] 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V1:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V2:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V3:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V5:+] '}')[V4:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V7:?] ';')[V6:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V9:?] ';')[V8:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V11:*] ';')[V10:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V12:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V13:*] '}' */,
				129	/* ReferenceCS-4: 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V0:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] ';' */,
				130	/* ReferenceCS-5: 'property' NamedElementCS::name=UnrestrictedName ('#' ReferenceCS::referredOpposite=UnrestrictedName)[V0:?] (':' TypedElementCS::ownedType=TypedMultiplicityRefCS)[V1:?] ('=' StructuralFeatureCS::default=SINGLE_QUOTED_STRING)[V2:?] ('{' (TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile')[V4:+] '}')[V3:?] '{' ('derivation' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V6:?] ';')[V5:*] ('initial' ':' (StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS)[V8:?] ';')[V7:*] ('key' ReferenceCS::referredKeys+=UnrestrictedName (',' ReferenceCS::referredKeys+=UnrestrictedName)[V10:*] ';')[V9:*] (ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS ';')[V11:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V12:*] '}' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* AttributeCS : [value] | [value] */,
			(0 << 16) | 0	/* ReferenceCS : [value] | [value] */
		);
		grammarRuleValues[97] = createParserRuleValue(97, "StructuredClassCS", -1,
			createSerializationRules(
				133	/* StructuredClassCS-0: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'class' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] ('extends' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V3:*])[V2:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V4:?] ('{' (StructuredClassCS::isInterface?='interface')[V6:?] '}')[V5:?] ';' */,
				134	/* StructuredClassCS-1: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'class' NamedElementCS::name=UnrestrictedName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] ('extends' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V3:*])[V2:?] (':' ClassCS::instanceClassName=SINGLE_QUOTED_STRING)[V4:?] ('{' (StructuredClassCS::isInterface?='interface')[V6:?] '}')[V5:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V7:*] (StructuredClassCS::ownedOperations+=OperationCS)[V8:*] (StructuredClassCS::ownedProperties+=StructuralFeatureCS)[V9:*] (ClassCS::ownedConstraints+=InvariantConstraintCS)[V10:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* isAbstract?="abstract"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "class" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSuperTypes+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSuperTypes+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* instanceClassName=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* isInterface?="interface"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-new-line, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedOperations+=OperationCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedProperties+=StructuralFeatureCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedConstraints+=InvariantConstraintCS : [value] | [value] */,
			(0 << 16) | 13	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[98] = createParserRuleValue(98, "SysMLCS", -1,
			createSerializationRules(
				135	/* SysMLCS-0: 'sysml' '{' (AnnotationElementCS::ownedDetails+=DetailCS ';')[V0:*] '}' */,
				136	/* SysMLCS-1: 'sysml' AnnotationElementCS::ownedDetails+=DetailCS ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SysMLCS} : [value] | [value] */,
			(0 << 16) | 11	/* "sysml" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[99] = createParserRuleValue(99, "TemplateBindingCS", -1,
			createSerializationRules(
				11	/* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[100] = createParserRuleValue(100, "TemplateParameterSubstitutionCS", -1,
			createSerializationRules(
				12	/* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[101] = createParserRuleValue(101, "TemplateSignatureCS", -1,
			createSerializationRules(
				137	/* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */,
				138	/* TemplateSignatureCS-1: '<' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] '>' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "<" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 11	/* ">" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[102] = createParserRuleValue(102, "TopLevelCS", -1,
			createSerializationRules(
				139	/* TopLevelCS-0: ('module')[V0:?] (RootCS::ownedImports+=ImportCS)[V1:*] (PackageOwnerCS::ownedPackages+=PackageCS)[V2:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {TopLevelCS} : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "module" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName : [value] | [value] */,
			(0 << 16) | 3	/* ownedImports+=ImportCS* : [value] | [value, half-new-line] */,
			(0 << 16) | 5	/* ownedPackages+=PackageCS* : [value] | [half-new-line, value, half-new-line] */
		);
		grammarRuleValues[103] = createParserRuleValue(103, "TupleLiteralExpCS", -1,
			createSerializationRules(
				61	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* "Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 7	/* "," : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[104] = createParserRuleValue(104, "TupleLiteralPartCS", -1,
			createSerializationRules(
				62	/* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 11	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[105] = createParserRuleValue(105, "TuplePartCS", -1,
			createSerializationRules(
				63	/* TuplePartCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[106] = createParserRuleValue(106, "TupleTypeCS", -1,
			createSerializationRules(
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* name="Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[107] = createParserRuleValue(107, "TypeExpCS", -1,
			createSerializationRules(
				65	/* TypeExpCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				66	/* TypeExpCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				67	/* TypeExpCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				68	/* TypeExpCS-3: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				69	/* TypeExpCS-4: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				70	/* TypeExpCS-5: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[108] = createParserRuleValue(108, "TypeExpWithoutMultiplicityCS", 56 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				21	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				22	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				33	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				53	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				76	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeNameExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionPatternCS : [value] | [value] */
		);
		grammarRuleValues[109] = createDataTypeRuleValue(109, "TypeIdentifier", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[110] = createParserRuleValue(110, "TypeLiteralCS", 54 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */,
			createSerializationRules(
				22	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				33	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				53	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */
		);
		grammarRuleValues[111] = createParserRuleValue(111, "TypeLiteralExpCS", -1,
			createSerializationRules(
				71	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			(0 << 16) | 2	/* ownedType=TypeLiteralWithMultiplicityCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[112] = createParserRuleValue(112, "TypeLiteralWithMultiplicityCS", -1,
			createSerializationRules(
				72	/* TypeLiteralWithMultiplicityCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				73	/* TypeLiteralWithMultiplicityCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				74	/* TypeLiteralWithMultiplicityCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				75	/* TypeLiteralWithMultiplicityCS-3: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[113] = createParserRuleValue(113, "TypeNameExpCS", -1,
			createSerializationRules(
				76	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedPatternGuard=ExpCS : [value] | [value] */,
			(0 << 16) | 12	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[114] = createParserRuleValue(114, "TypeParameterCS", -1,
			createSerializationRules(
				13	/* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 11	/* "&&" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[115] = createParserRuleValue(115, "TypeRefCS", 73 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				22	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				33	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				53	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				147	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS '(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')' */,
				148	/* TypedTypeRefCS-1: TypedTypeRefCS::ownedPathName=PathNameCS '<' TypedTypeRefCS::ownedBinding=TemplateBindingCS '>' */,
				149	/* TypedTypeRefCS-2: TypedTypeRefCS::ownedPathName=PathNameCS */,
				15	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[116] = createParserRuleValue(116, "TypedMultiplicityRefCS", -1,
			createSerializationRules(
				140	/* TypedMultiplicityRefCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				141	/* TypedMultiplicityRefCS-1: TypedTypeRefCS::ownedPathName=PathNameCS (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				142	/* TypedMultiplicityRefCS-2: TypedTypeRefCS::ownedPathName=PathNameCS '(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				143	/* TypedMultiplicityRefCS-3: TypedTypeRefCS::ownedPathName=PathNameCS '<' TypedTypeRefCS::ownedBinding=TemplateBindingCS '>' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				144	/* TypedMultiplicityRefCS-4: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				145	/* TypedMultiplicityRefCS-5: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				146	/* TypedMultiplicityRefCS-6: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[117] = createParserRuleValue(117, "TypedRefCS", 61 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				22	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				33	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				53	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				147	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS '(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')' */,
				148	/* TypedTypeRefCS-1: TypedTypeRefCS::ownedPathName=PathNameCS '<' TypedTypeRefCS::ownedBinding=TemplateBindingCS '>' */,
				149	/* TypedTypeRefCS-2: TypedTypeRefCS::ownedPathName=PathNameCS */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[118] = createParserRuleValue(118, "TypedTypeRefCS", -1,
			createSerializationRules(
				147	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS '(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')' */,
				148	/* TypedTypeRefCS-1: TypedTypeRefCS::ownedPathName=PathNameCS '<' TypedTypeRefCS::ownedBinding=TemplateBindingCS '>' */,
				149	/* TypedTypeRefCS-2: TypedTypeRefCS::ownedPathName=PathNameCS */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 11	/* "<" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 11	/* ">" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[119] = new TerminalRuleValue(119, "UNQUOTED_STRING");
		grammarRuleValues[120] = createDataTypeRuleValue(120, "UPPER", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[121] = createDataTypeRuleValue(121, "URI", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[122] = createParserRuleValue(122, "URIFirstPathElementCS", -1,
			createSerializationRules(
				77	/* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */,
				78	/* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 11	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PathElementWithURICS} : [value] | [value] */,
			(0 << 16) | 11	/* referredElement=URI : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[123] = createParserRuleValue(123, "URIPathNameCS", -1,
			createSerializationRules(
				79	/* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=URIFirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[124] = createDataTypeRuleValue(124, "UnaryOperatorName", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[125] = createParserRuleValue(125, "UnlimitedNaturalLiteralExpCS", -1,
			createSerializationRules(
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {UnlimitedNaturalLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 11	/* "*" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[126] = createDataTypeRuleValue(126, "UnreservedName", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[127] = createParserRuleValue(127, "UnreservedPathNameCS", -1,
			createSerializationRules(
				14	/* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[128] = createDataTypeRuleValue(128, "UnrestrictedName", 11 /* [soft-space, value, soft-space] */);
		grammarRuleValues[129] = new TerminalRuleValue(129, "WS");
		grammarRuleValues[130] = createParserRuleValue(130, "WildcardTypeRefCS", -1,
			createSerializationRules(
				15	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WildcardTypeRefCS} : [value] | [value] */,
			(0 << 16) | 11	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 11	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends=TypedRefCS : [value] | [value] */
		);
	}

	/**
	 * Initialize bit vectors of useful grammar rule combinations.
	 */
	private void initGrammarRuleVectors() {
		// 0: AnnotationElementCS
		grammarRuleVectors[0] = new GrammarRuleVector(0x4L);
		// 1: ClassCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x40L);
		// 2: CoIteratorVariableCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x80L);
		// 3: CollectionLiteralPartCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x200L);
		// 4: CollectionTypeCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x800L);
		// 5: CurlyBracketedClauseCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x2000L);
		// 6: DetailCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x10000L);
		// 7: ElseIfThenExpCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x100000L);
		// 8: EnumerationLiteralCS
		grammarRuleVectors[8] = new GrammarRuleVector(0x400000L);
		// 9: ExpCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x40000000L);
		// 10: FirstPathElementCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x80000000L);
		// 11: ImplicitOppositeCS
		grammarRuleVectors[11] = new GrammarRuleVector(0x2000000000L);
		// 12: ImportCS
		grammarRuleVectors[12] = new GrammarRuleVector(0x4000000000L);
		// 13: InvariantConstraintCS
		grammarRuleVectors[13] = new GrammarRuleVector(0x20000000000L);
		// 14: LetVariableCS
		grammarRuleVectors[14] = new GrammarRuleVector(0x400000000000L);
		// 15: MapLiteralPartCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x4000000000000L);
		// 16: MapTypeCS
		grammarRuleVectors[16] = new GrammarRuleVector(0x8000000000000L);
		// 17: ModelElementCS
		grammarRuleVectors[17] = new GrammarRuleVector(0x20000000000000L);
		// 18: ModelElementRefCS
		grammarRuleVectors[18] = new GrammarRuleVector(0x40000000000000L);
		// 19: MultiplicityCS
		grammarRuleVectors[19] = new GrammarRuleVector(0x100000000000000L);
		// 20: NavigatingArgExpCS
		grammarRuleVectors[20] = new GrammarRuleVector(0x2000000000000000L);
		// 21: NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[21] = new GrammarRuleVector(0xc000000000000000L,0x1L);
		// 22: NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[22] = new GrammarRuleVector(0xd000000000000000L,0x1L);
		// 23: NextPathElementCS
		grammarRuleVectors[23] = new GrammarRuleVector(0x0L,0x8L);
		// 24: FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[24] = new GrammarRuleVector(0x80000000L,0x8L);
		// 25: OperationCS
		grammarRuleVectors[25] = new GrammarRuleVector(0x0L,0x40L);
		// 26: PackageCS
		grammarRuleVectors[26] = new GrammarRuleVector(0x0L,0x80L);
		// 27: ParameterCS
		grammarRuleVectors[27] = new GrammarRuleVector(0x0L,0x100L);
		// 28: PathNameCS
		grammarRuleVectors[28] = new GrammarRuleVector(0x0L,0x200L);
		// 29: PatternExpCS
		grammarRuleVectors[29] = new GrammarRuleVector(0x0L,0x400L);
		// 30: ExpCS|PatternExpCS
		grammarRuleVectors[30] = new GrammarRuleVector(0x40000000L,0x400L);
		// 31: PostconditionConstraintCS
		grammarRuleVectors[31] = new GrammarRuleVector(0x0L,0x800L);
		// 32: PreconditionConstraintCS
		grammarRuleVectors[32] = new GrammarRuleVector(0x0L,0x1000L);
		// 33: PrefixedLetExpCS
		grammarRuleVectors[33] = new GrammarRuleVector(0x0L,0x2000L);
		// 34: LetExpCS|PrefixedLetExpCS
		grammarRuleVectors[34] = new GrammarRuleVector(0x200000000000L,0x2000L);
		// 35: PrefixedPrimaryExpCS
		grammarRuleVectors[35] = new GrammarRuleVector(0x0L,0x4000L);
		// 36: RoundBracketedClauseCS
		grammarRuleVectors[36] = new GrammarRuleVector(0x0L,0x100000L);
		// 37: ML_SINGLE_QUOTED_STRING|SINGLE_QUOTED_STRING
		grammarRuleVectors[37] = new GrammarRuleVector(0x1000000000000L,0x800000L);
		// 38: ShadowPartCS
		grammarRuleVectors[38] = new GrammarRuleVector(0x0L,0x4000000L);
		// 39: SpecificationCS
		grammarRuleVectors[39] = new GrammarRuleVector(0x0L,0x10000000L);
		// 40: SquareBracketedClauseCS
		grammarRuleVectors[40] = new GrammarRuleVector(0x0L,0x20000000L);
		// 41: StringLiteralExpCS
		grammarRuleVectors[41] = new GrammarRuleVector(0x0L,0x80000000L);
		// 42: StructuralFeatureCS
		grammarRuleVectors[42] = new GrammarRuleVector(0x0L,0x100000000L);
		// 43: AttributeCS|ReferenceCS|StructuralFeatureCS
		grammarRuleVectors[43] = new GrammarRuleVector(0x8L,0x100080000L);
		// 44: ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS
		grammarRuleVectors[44] = new GrammarRuleVector(0x208040L,0x200000000L);
		// 45: AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS
		grammarRuleVectors[45] = new GrammarRuleVector(0x20000000608048L,0x3000800c0L);
		// 46: AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS
		grammarRuleVectors[46] = new GrammarRuleVector(0x20006L,0x400000000L);
		// 47: TemplateBindingCS
		grammarRuleVectors[47] = new GrammarRuleVector(0x0L,0x800000000L);
		// 48: TemplateParameterSubstitutionCS
		grammarRuleVectors[48] = new GrammarRuleVector(0x0L,0x1000000000L);
		// 49: TemplateSignatureCS
		grammarRuleVectors[49] = new GrammarRuleVector(0x0L,0x2000000000L);
		// 50: TupleLiteralPartCS
		grammarRuleVectors[50] = new GrammarRuleVector(0x0L,0x10000000000L);
		// 51: TuplePartCS
		grammarRuleVectors[51] = new GrammarRuleVector(0x0L,0x20000000000L);
		// 52: TypeExpCS
		grammarRuleVectors[52] = new GrammarRuleVector(0x0L,0x80000000000L);
		// 53: TypeExpWithoutMultiplicityCS
		grammarRuleVectors[53] = new GrammarRuleVector(0x0L,0x100000000000L);
		// 54: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
		grammarRuleVectors[54] = new GrammarRuleVector(0x8000000000800L,0x440000020000L);
		// 55: TypeLiteralWithMultiplicityCS
		grammarRuleVectors[55] = new GrammarRuleVector(0x0L,0x1000000000000L);
		// 56: CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[56] = new GrammarRuleVector(0x8000000000c00L,0x2540000020000L);
		// 57: TypeParameterCS
		grammarRuleVectors[57] = new GrammarRuleVector(0x0L,0x4000000000000L);
		// 58: TypeRefCS
		grammarRuleVectors[58] = new GrammarRuleVector(0x0L,0x8000000000000L);
		// 59: TypedMultiplicityRefCS
		grammarRuleVectors[59] = new GrammarRuleVector(0x0L,0x10000000000000L);
		// 60: TypedRefCS
		grammarRuleVectors[60] = new GrammarRuleVector(0x0L,0x20000000000000L);
		// 61: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[61] = new GrammarRuleVector(0x8000000000800L,0x60440000020000L);
		// 62: NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[62] = new GrammarRuleVector(0x0L,0x400000000000008L);
		// 63: FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[63] = new GrammarRuleVector(0x80000000L,0x400000000000008L);
		// 64: URIPathNameCS
		grammarRuleVectors[64] = new GrammarRuleVector(0x0L,0x800000000000000L);
		// 65: BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[65] = new GrammarRuleVector(0x10000000020L,0x2000000080010030L);
		// 66: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[66] = new GrammarRuleVector(0x802111000000120L,0x2000808082018034L);
		// 67: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[67] = new GrammarRuleVector(0x802111000000120L,0x200080808201c034L);
		// 68: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[68] = new GrammarRuleVector(0x802311000000120L,0x200080808201e034L);
		// 69: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[69] = new GrammarRuleVector(0x802311040000120L,0x200080808201e034L);
		// 70: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[70] = new GrammarRuleVector(0x2802311040000120L,0x200080808201e034L);
		// 71: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[71] = new GrammarRuleVector(0x802311040000120L,0x200080808201e434L);
		// 72: SINGLE_QUOTED_STRING|UnrestrictedName
		grammarRuleVectors[72] = new GrammarRuleVector(0x0L,0x800000L,0x1L);
		// 73: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[73] = new GrammarRuleVector(0x8000000000800L,0x68440000020000L,0x4L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// 0: assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(150);
		// 1: assert (|AnnotationElementCS::ownedDetails| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(151);
		// 2: assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(153);
		// 3: assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(156);
		// 4: assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(157);
		// 5: assert (|CollectionPatternCS::ownedType| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(159);
		// 6: assert (|CollectionTypeCS::name| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(160);
		// 7: assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(161);
		// 8: assert (|ConstraintCS::stereotype.'postcondition'| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(162);
		// 9: assert (|ConstraintCS::stereotype.'precondition'| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(163);
		// 10: assert (|ContextCS::ownedExpression| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(164);
		// 11: assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(167);
		// 12: assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(168);
		// 13: assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(169);
		// 14: assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(170);
		// 15: assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(171);
		// 16: assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(172);
		// 17: assert (|ImportCS::ownedPathName| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(173);
		// 18: assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(174);
		// 19: assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(175);
		// 20: assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(176);
		// 21: assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(180);
		// 22: assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(181);
		// 23: assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(182);
		// 24: assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(183);
		// 25: assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(184);
		// 26: assert (|ModelElementRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(186);
		// 27: assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(187);
		// 28: assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(188);
		// 29: assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(189);
		// 30: assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(190);
		// 31: assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(191);
		// 32: assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(192);
		// 33: assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(193);
		// 34: assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(194);
		// 35: assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(195);
		// 36: assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(196);
		// 37: assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(197);
		// 38: assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(198);
		// 39: assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(199);
		// 40: assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(205);
		// 41: assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(206);
		// 42: assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(207);
		// 43: assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(208);
		// 44: assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(209);
		// 45: assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(212);
		// 46: assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[46] = createMatchStep_Assert(213);
		// 47: assert (|SpecificationCS::exprString| - 1) == 0
		serializationMatchSteps[47] = createMatchStep_Assert(214);
		// 48: assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[48] = createMatchStep_Assert(221);
		// 49: assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[49] = createMatchStep_Assert(224);
		// 50: assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[50] = createMatchStep_Assert(227);
		// 51: assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[51] = createMatchStep_Assert(228);
		// 52: assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[52] = createMatchStep_Assert(231);
		// 53: assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0
		serializationMatchSteps[53] = createMatchStep_Assert(236);
		// 54: assert (|TypedElementCS::qualifiers.'static'| - 1) == 0
		serializationMatchSteps[54] = createMatchStep_Assert(237);
		// 55: assert (|TypedTypeRefCS::ownedBinding| - 1) == 0
		serializationMatchSteps[55] = createMatchStep_Assert(238);
		// 56: assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[56] = createMatchStep_Assert(239);
		// 57: assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[57] = createMatchStep_Assert(240);
		// 58: assert |AnnotationCS::ownedContents| == 0
		serializationMatchSteps[58] = createMatchStep_Assert(8);
		// 59: assert |AnnotationCS::ownedReferences| == 0
		serializationMatchSteps[59] = createMatchStep_Assert(9);
		// 60: assert |ClassCS::ownedConstraints| == 0
		serializationMatchSteps[60] = createMatchStep_Assert(13);
		// 61: assert |CollectionLiteralPartCS::ownedLastExpression| == 0
		serializationMatchSteps[61] = createMatchStep_Assert(17);
		// 62: assert |CollectionPatternCS::ownedPatternGuard| == 0
		serializationMatchSteps[62] = createMatchStep_Assert(19);
		// 63: assert |ConstraintCS::ownedSpecification| == 0
		serializationMatchSteps[63] = createMatchStep_Assert(26);
		// 64: assert |CurlyBracketedClauseCS::value| == 0
		serializationMatchSteps[64] = createMatchStep_Assert(32);
		// 65: assert |DataTypeCS::isSerializable| == 0
		serializationMatchSteps[65] = createMatchStep_Assert(35);
		// 66: assert |EnumerationCS::isSerializable| == 0
		serializationMatchSteps[66] = createMatchStep_Assert(39);
		// 67: assert |EnumerationCS::ownedLiterals| == 0
		serializationMatchSteps[67] = createMatchStep_Assert(40);
		// 68: assert |ExpSpecificationCS::ownedExpression| == 0
		serializationMatchSteps[68] = createMatchStep_Assert(43);
		// 69: assert |IfExpCS::isImplicit| == 0
		serializationMatchSteps[69] = createMatchStep_Assert(44);
		// 70: assert |LetExpCS::isImplicit| == 0
		serializationMatchSteps[70] = createMatchStep_Assert(55);
		// 71: assert |ModelElementCS::ownedAnnotations| == 0
		serializationMatchSteps[71] = createMatchStep_Assert(66);
		// 72: assert |MultiplicityCS::isNullFree| == 0
		serializationMatchSteps[72] = createMatchStep_Assert(71);
		// 73: assert |NamedElementCS::name| == 0
		serializationMatchSteps[73] = createMatchStep_Assert(73);
		// 74: assert |NavigatingArgCS::ownedCoIterator| == 0
		serializationMatchSteps[74] = createMatchStep_Assert(74);
		// 75: assert |NavigatingArgCS::ownedInitExpression| == 0
		serializationMatchSteps[75] = createMatchStep_Assert(75);
		// 76: assert |NavigatingArgCS::ownedNameExpression| == 0
		serializationMatchSteps[76] = createMatchStep_Assert(76);
		// 77: assert |NavigatingArgCS::ownedType| == 0
		serializationMatchSteps[77] = createMatchStep_Assert(77);
		// 78: assert |NavigatingArgCS::prefix| == 0
		serializationMatchSteps[78] = createMatchStep_Assert(81);
		// 79: assert |OCLinEcoreConstraintCS::isCallable| == 0
		serializationMatchSteps[79] = createMatchStep_Assert(85);
		// 80: assert |OperationCS::ownedBodyExpressions| == 0
		serializationMatchSteps[80] = createMatchStep_Assert(86);
		// 81: assert |OperationCS::ownedPostconditions| == 0
		serializationMatchSteps[81] = createMatchStep_Assert(89);
		// 82: assert |OperationCS::ownedPreconditions| == 0
		serializationMatchSteps[82] = createMatchStep_Assert(90);
		// 83: assert |PackageCS::ownedClasses| == 0
		serializationMatchSteps[83] = createMatchStep_Assert(94);
		// 84: assert |PackageOwnerCS::ownedPackages| == 0
		serializationMatchSteps[84] = createMatchStep_Assert(95);
		// 85: assert |ReferenceCS::ownedImplicitOpposites| == 0
		serializationMatchSteps[85] = createMatchStep_Assert(101);
		// 86: assert |ReferenceCS::referredKeys| == 0
		serializationMatchSteps[86] = createMatchStep_Assert(102);
		// 87: assert |RootCS::ownedImports| == 0
		serializationMatchSteps[87] = createMatchStep_Assert(104);
		// 88: assert |SelfExpCS::name| == 0
		serializationMatchSteps[88] = createMatchStep_Assert(105);
		// 89: assert |ShadowPartCS::referredProperty| == 0
		serializationMatchSteps[89] = createMatchStep_Assert(107);
		// 90: assert |SpecificationCS::exprString| == 0
		serializationMatchSteps[90] = createMatchStep_Assert(108);
		// 91: assert |StructuralFeatureCS::ownedDefaultExpressions| == 0
		serializationMatchSteps[91] = createMatchStep_Assert(112);
		// 92: assert |StructuredClassCS::ownedMetaclass| == 0
		serializationMatchSteps[92] = createMatchStep_Assert(115);
		// 93: assert |StructuredClassCS::ownedOperations| == 0
		serializationMatchSteps[93] = createMatchStep_Assert(116);
		// 94: assert |StructuredClassCS::ownedProperties| == 0
		serializationMatchSteps[94] = createMatchStep_Assert(117);
		// 95: assert |SysMLCS::value| == 0
		serializationMatchSteps[95] = createMatchStep_Assert(119);
		// 96: assert |TypeLiteralExpCS::ownedPathName| == 0
		serializationMatchSteps[96] = createMatchStep_Assert(128);
		// 97: assert |TypedElementCS::qualifiers| == 0
		serializationMatchSteps[97] = createMatchStep_Assert(141);
		// 98: assert |TypedRefCS::ownedMultiplicity| == 0
		serializationMatchSteps[98] = createMatchStep_Assert(142);
		// 99: assert |TypedTypeRefCS::isTypeof| == 0
		serializationMatchSteps[99] = createMatchStep_Assert(143);
		// 100: assert |TypedTypeRefCS::ownedBinding| == 0
		serializationMatchSteps[100] = createMatchStep_Assert(144);
		// 101: assert |VariableCS::ownedInitExpression| == 0
		serializationMatchSteps[101] = createMatchStep_Assert(146);
		// 102: assert |WildcardTypeRefCS::ownedSuper| == 0
		serializationMatchSteps[102] = createMatchStep_Assert(149);
		// 103: assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[103] = createMatchStep_Assign(0, 155);
		// 104: assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchSteps[104] = createMatchStep_Assign(0, 166);
		// 105: assign V0 = (|LetExpCS::ownedVariables| - 1)
		serializationMatchSteps[105] = createMatchStep_Assign(0, 177);
		// 106: assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[106] = createMatchStep_Assign(0, 179);
		// 107: assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[107] = createMatchStep_Assign(0, 207);
		// 108: assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[108] = createMatchStep_Assign(0, 215);
		// 109: assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[109] = createMatchStep_Assign(0, 220);
		// 110: assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[110] = createMatchStep_Assign(0, 222);
		// 111: assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[111] = createMatchStep_Assign(0, 223);
		// 112: assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[112] = createMatchStep_Assign(0, 226);
		// 113: assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[113] = createMatchStep_Assign(0, 230);
		// 114: assign V0 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
		serializationMatchSteps[114] = createMatchStep_Assign(0, 235);
		// 115: assign V0 = 0
		serializationMatchSteps[115] = createMatchStep_Assign(0, 0);
		// 116: assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchSteps[116] = createMatchStep_Assign(0, 7);
		// 117: assign V0 = |AnnotationElementCS::ownedDetails|
		serializationMatchSteps[117] = createMatchStep_Assign(0, 10);
		// 118: assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchSteps[118] = createMatchStep_Assign(0, 17);
		// 119: assign V0 = |CollectionPatternCS::restVariableName|
		serializationMatchSteps[119] = createMatchStep_Assign(0, 21);
		// 120: assign V0 = |CollectionTypeCS::ownedType|
		serializationMatchSteps[120] = createMatchStep_Assign(0, 24);
		// 121: assign V0 = |DataTypeCS::isPrimitive.'primitive'|
		serializationMatchSteps[121] = createMatchStep_Assign(0, 33);
		// 122: assign V0 = |DetailCS::values|
		serializationMatchSteps[122] = createMatchStep_Assign(0, 36);
		// 123: assign V0 = |DocumentationCS::value|
		serializationMatchSteps[123] = createMatchStep_Assign(0, 37);
		// 124: assign V0 = |EnumerationLiteralCS::literal|
		serializationMatchSteps[124] = createMatchStep_Assign(0, 41);
		// 125: assign V0 = |IfExpCS::ownedIfThenExpressions|
		serializationMatchSteps[125] = createMatchStep_Assign(0, 47);
		// 126: assign V0 = |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchSteps[126] = createMatchStep_Assign(0, 58);
		// 127: assign V0 = |MapTypeCS::ownedValueType|
		serializationMatchSteps[127] = createMatchStep_Assign(0, 65);
		// 128: assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[128] = createMatchStep_Assign(0, 69);
		// 129: assign V0 = |NamedElementCS::name|
		serializationMatchSteps[129] = createMatchStep_Assign(0, 73);
		// 130: assign V0 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[130] = createMatchStep_Assign(0, 74);
		// 131: assign V0 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[131] = createMatchStep_Assign(0, 75);
		// 132: assign V0 = |NavigatingArgCS::ownedType|
		serializationMatchSteps[132] = createMatchStep_Assign(0, 77);
		// 133: assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'|
		serializationMatchSteps[133] = createMatchStep_Assign(0, 84);
		// 134: assign V0 = |PackageCS::nsPrefix|
		serializationMatchSteps[134] = createMatchStep_Assign(0, 92);
		// 135: assign V0 = |PatternExpCS::patternVariableName|
		serializationMatchSteps[135] = createMatchStep_Assign(0, 99);
		// 136: assign V0 = |ReferenceCS::referredOpposite|
		serializationMatchSteps[136] = createMatchStep_Assign(0, 103);
		// 137: assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[137] = createMatchStep_Assign(0, 110);
		// 138: assign V0 = |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchSteps[138] = createMatchStep_Assign(0, 113);
		// 139: assign V0 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[139] = createMatchStep_Assign(0, 124);
		// 140: assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[140] = createMatchStep_Assign(0, 130);
		// 141: assign V0 = |TypedElementCS::ownedType|
		serializationMatchSteps[141] = createMatchStep_Assign(0, 134);
		// 142: assign V0 = |TypedElementCS::qualifiers.'definition'|
		serializationMatchSteps[142] = createMatchStep_Assign(0, 139);
		// 143: assign V0 = |TypedElementCS::qualifiers.'static'|
		serializationMatchSteps[143] = createMatchStep_Assign(0, 140);
		// 144: assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[144] = createMatchStep_Assign(0, 142);
		// 145: assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[145] = createMatchStep_Assign(0, 147);
		// 146: assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[146] = createMatchStep_Assign(0, 148);
		// 147: assign V1 = (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchSteps[147] = createMatchStep_Assign(1, 152);
		// 148: assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[148] = createMatchStep_Assign(1, 154);
		// 149: assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[149] = createMatchStep_Assign(1, 158);
		// 150: assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[150] = createMatchStep_Assign(1, 165);
		// 151: assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[151] = createMatchStep_Assign(1, 178);
		// 152: assign V1 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[152] = createMatchStep_Assign(1, 204);
		// 153: assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[153] = createMatchStep_Assign(1, 226);
		// 154: assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[154] = createMatchStep_Assign(1, 229);
		// 155: assign V1 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
		serializationMatchSteps[155] = createMatchStep_Assign(1, 235);
		// 156: assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[156] = createMatchStep_Assign(1, 6);
		// 157: assign V1 = |ClassCS::instanceClassName|
		serializationMatchSteps[157] = createMatchStep_Assign(1, 12);
		// 158: assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[158] = createMatchStep_Assign(1, 23);
		// 159: assign V1 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[159] = createMatchStep_Assign(1, 25);
		// 160: assign V1 = |EnumerationLiteralCS::value|
		serializationMatchSteps[160] = createMatchStep_Assign(1, 42);
		// 161: assign V1 = |ImportCS::isAll.'::*'|
		serializationMatchSteps[161] = createMatchStep_Assign(1, 51);
		// 162: assign V1 = |NamedElementCS::name|
		serializationMatchSteps[162] = createMatchStep_Assign(1, 73);
		// 163: assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[163] = createMatchStep_Assign(1, 74);
		// 164: assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[164] = createMatchStep_Assign(1, 75);
		// 165: assign V1 = |PackageCS::nsURI|
		serializationMatchSteps[165] = createMatchStep_Assign(1, 93);
		// 166: assign V1 = |ReferenceCS::referredOpposite|
		serializationMatchSteps[166] = createMatchStep_Assign(1, 103);
		// 167: assign V1 = |RootCS::ownedImports|
		serializationMatchSteps[167] = createMatchStep_Assign(1, 104);
		// 168: assign V1 = |StructuralFeatureCS::default|
		serializationMatchSteps[168] = createMatchStep_Assign(1, 111);
		// 169: assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[169] = createMatchStep_Assign(1, 120);
		// 170: assign V1 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[170] = createMatchStep_Assign(1, 124);
		// 171: assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[171] = createMatchStep_Assign(1, 132);
		// 172: assign V1 = |TypedElementCS::ownedType|
		serializationMatchSteps[172] = createMatchStep_Assign(1, 134);
		// 173: assign V1 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
		serializationMatchSteps[173] = createMatchStep_Assign(1, 138);
		// 174: assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[174] = createMatchStep_Assign(1, 142);
		// 175: assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[175] = createMatchStep_Assign(1, 147);
		// 176: assign V10 = (|ReferenceCS::referredKeys| - 1)
		serializationMatchSteps[176] = createMatchStep_Assign(10, 210);
		// 177: assign V10 = (|ReferenceCS::referredKeys| > 0)
		serializationMatchSteps[177] = createMatchStep_Assign(10, 211);
		// 178: assign V10 = |ClassCS::ownedConstraints|
		serializationMatchSteps[178] = createMatchStep_Assign(10, 13);
		// 179: assign V10 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[179] = createMatchStep_Assign(10, 66);
		// 180: assign V10 = |OperationCS::ownedBodyExpressions|
		serializationMatchSteps[180] = createMatchStep_Assign(10, 86);
		// 181: assign V11 = (|ReferenceCS::referredKeys| - 1)
		serializationMatchSteps[181] = createMatchStep_Assign(11, 210);
		// 182: assign V11 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[182] = createMatchStep_Assign(11, 66);
		// 183: assign V11 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[183] = createMatchStep_Assign(11, 90);
		// 184: assign V11 = |ReferenceCS::ownedImplicitOpposites|
		serializationMatchSteps[184] = createMatchStep_Assign(11, 101);
		// 185: assign V12 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[185] = createMatchStep_Assign(12, 66);
		// 186: assign V12 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[186] = createMatchStep_Assign(12, 89);
		// 187: assign V12 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[187] = createMatchStep_Assign(12, 90);
		// 188: assign V12 = |ReferenceCS::ownedImplicitOpposites|
		serializationMatchSteps[188] = createMatchStep_Assign(12, 101);
		// 189: assign V13 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[189] = createMatchStep_Assign(13, 66);
		// 190: assign V13 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[190] = createMatchStep_Assign(13, 89);
		// 191: assign V2 = (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchSteps[191] = createMatchStep_Assign(2, 151);
		// 192: assign V2 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[192] = createMatchStep_Assign(2, 203);
		// 193: assign V2 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[193] = createMatchStep_Assign(2, 204);
		// 194: assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchSteps[194] = createMatchStep_Assign(2, 219);
		// 195: assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[195] = createMatchStep_Assign(2, 225);
		// 196: assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
		serializationMatchSteps[196] = createMatchStep_Assign(2, 233);
		// 197: assign V2 = 0
		serializationMatchSteps[197] = createMatchStep_Assign(2, 0);
		// 198: assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[198] = createMatchStep_Assign(2, 4);
		// 199: assign V2 = |ClassCS::instanceClassName|
		serializationMatchSteps[199] = createMatchStep_Assign(2, 12);
		// 200: assign V2 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[200] = createMatchStep_Assign(2, 25);
		// 201: assign V2 = |ConstraintCS::ownedSpecification|
		serializationMatchSteps[201] = createMatchStep_Assign(2, 26);
		// 202: assign V2 = |EnumerationCS::isSerializable.'serializable'|
		serializationMatchSteps[202] = createMatchStep_Assign(2, 38);
		// 203: assign V2 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[203] = createMatchStep_Assign(2, 66);
		// 204: assign V2 = |PackageOwnerCS::ownedPackages|
		serializationMatchSteps[204] = createMatchStep_Assign(2, 95);
		// 205: assign V2 = |StructuralFeatureCS::default|
		serializationMatchSteps[205] = createMatchStep_Assign(2, 111);
		// 206: assign V2 = |TypedElementCS::ownedType|
		serializationMatchSteps[206] = createMatchStep_Assign(2, 134);
		// 207: assign V2 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
		serializationMatchSteps[207] = createMatchStep_Assign(2, 138);
		// 208: assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[208] = createMatchStep_Assign(2, 142);
		// 209: assign V3 = (|ModelElementCS::ownedAnnotations| > 0)
		serializationMatchSteps[209] = createMatchStep_Assign(3, 185);
		// 210: assign V3 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[210] = createMatchStep_Assign(3, 203);
		// 211: assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchSteps[211] = createMatchStep_Assign(3, 218);
		// 212: assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
		serializationMatchSteps[212] = createMatchStep_Assign(3, 232);
		// 213: assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
		serializationMatchSteps[213] = createMatchStep_Assign(3, 233);
		// 214: assign V3 = 0
		serializationMatchSteps[214] = createMatchStep_Assign(3, 0);
		// 215: assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[215] = createMatchStep_Assign(3, 3);
		// 216: assign V3 = |ConstraintCS::ownedSpecification|
		serializationMatchSteps[216] = createMatchStep_Assign(3, 26);
		// 217: assign V3 = |DataTypeCS::isSerializable.'serializable'|
		serializationMatchSteps[217] = createMatchStep_Assign(3, 34);
		// 218: assign V3 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[218] = createMatchStep_Assign(3, 66);
		// 219: assign V3 = |PackageOwnerCS::ownedPackages|
		serializationMatchSteps[219] = createMatchStep_Assign(3, 95);
		// 220: assign V3 = |StructuralFeatureCS::default|
		serializationMatchSteps[220] = createMatchStep_Assign(3, 111);
		// 221: assign V3 = |TypedElementCS::ownedType|
		serializationMatchSteps[221] = createMatchStep_Assign(3, 134);
		// 222: assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
		serializationMatchSteps[222] = createMatchStep_Assign(3, 136);
		// 223: assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[223] = createMatchStep_Assign(3, 142);
		// 224: assign V4 = (|OperationCS::ownedExceptions| > 0)
		serializationMatchSteps[224] = createMatchStep_Assign(4, 202);
		// 225: assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
		serializationMatchSteps[225] = createMatchStep_Assign(4, 232);
		// 226: assign V4 = |AnnotationCS::ownedContents|
		serializationMatchSteps[226] = createMatchStep_Assign(4, 8);
		// 227: assign V4 = |ClassCS::instanceClassName|
		serializationMatchSteps[227] = createMatchStep_Assign(4, 12);
		// 228: assign V4 = |EnumerationCS::ownedLiterals|
		serializationMatchSteps[228] = createMatchStep_Assign(4, 40);
		// 229: assign V4 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[229] = createMatchStep_Assign(4, 66);
		// 230: assign V4 = |PackageCS::ownedClasses|
		serializationMatchSteps[230] = createMatchStep_Assign(4, 94);
		// 231: assign V4 = |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchSteps[231] = createMatchStep_Assign(4, 112);
		// 232: assign V4 = |TypedElementCS::ownedType|
		serializationMatchSteps[232] = createMatchStep_Assign(4, 134);
		// 233: assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
		serializationMatchSteps[233] = createMatchStep_Assign(4, 135);
		// 234: assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
		serializationMatchSteps[234] = createMatchStep_Assign(4, 136);
		// 235: assign V5 = (|OperationCS::ownedExceptions| - 1)
		serializationMatchSteps[235] = createMatchStep_Assign(5, 201);
		// 236: assign V5 = (|OperationCS::ownedExceptions| > 0)
		serializationMatchSteps[236] = createMatchStep_Assign(5, 202);
		// 237: assign V5 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
		serializationMatchSteps[237] = createMatchStep_Assign(5, 216);
		// 238: assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0)
		serializationMatchSteps[238] = createMatchStep_Assign(5, 217);
		// 239: assign V5 = |AnnotationCS::ownedReferences|
		serializationMatchSteps[239] = createMatchStep_Assign(5, 9);
		// 240: assign V5 = |ClassCS::ownedConstraints|
		serializationMatchSteps[240] = createMatchStep_Assign(5, 13);
		// 241: assign V5 = |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchSteps[241] = createMatchStep_Assign(5, 112);
		// 242: assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
		serializationMatchSteps[242] = createMatchStep_Assign(5, 135);
		// 243: assign V6 = (|OperationCS::ownedExceptions| - 1)
		serializationMatchSteps[243] = createMatchStep_Assign(6, 201);
		// 244: assign V6 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
		serializationMatchSteps[244] = createMatchStep_Assign(6, 216);
		// 245: assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
		serializationMatchSteps[245] = createMatchStep_Assign(6, 234);
		// 246: assign V6 = 0
		serializationMatchSteps[246] = createMatchStep_Assign(6, 0);
		// 247: assign V6 = |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchSteps[247] = createMatchStep_Assign(6, 112);
		// 248: assign V6 = |StructuredClassCS::isInterface.'interface'|
		serializationMatchSteps[248] = createMatchStep_Assign(6, 114);
		// 249: assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
		serializationMatchSteps[249] = createMatchStep_Assign(7, 216);
		// 250: assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
		serializationMatchSteps[250] = createMatchStep_Assign(7, 234);
		// 251: assign V7 = 0
		serializationMatchSteps[251] = createMatchStep_Assign(7, 0);
		// 252: assign V7 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[252] = createMatchStep_Assign(7, 66);
		// 253: assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
		serializationMatchSteps[253] = createMatchStep_Assign(7, 137);
		// 254: assign V8 = (|OperationCS::ownedBodyExpressions| > 0)
		serializationMatchSteps[254] = createMatchStep_Assign(8, 200);
		// 255: assign V8 = 0
		serializationMatchSteps[255] = createMatchStep_Assign(8, 0);
		// 256: assign V8 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[256] = createMatchStep_Assign(8, 66);
		// 257: assign V8 = |StructuredClassCS::ownedOperations|
		serializationMatchSteps[257] = createMatchStep_Assign(8, 116);
		// 258: assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
		serializationMatchSteps[258] = createMatchStep_Assign(8, 137);
		// 259: assign V9 = (|OperationCS::ownedBodyExpressions| > 0)
		serializationMatchSteps[259] = createMatchStep_Assign(9, 200);
		// 260: assign V9 = (|ReferenceCS::referredKeys| > 0)
		serializationMatchSteps[260] = createMatchStep_Assign(9, 211);
		// 261: assign V9 = 0
		serializationMatchSteps[261] = createMatchStep_Assign(9, 0);
		// 262: assign V9 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[262] = createMatchStep_Assign(9, 66);
		// 263: assign V9 = |OperationCS::ownedBodyExpressions|
		serializationMatchSteps[263] = createMatchStep_Assign(9, 86);
		// 264: assign V9 = |StructuredClassCS::ownedProperties|
		serializationMatchSteps[264] = createMatchStep_Assign(9, 117);
		// 265: check-rule basecs::AnnotationCS.ownedContents : 3|6|15|21|22|53|70|71|83|96|97
		serializationMatchSteps[265] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, 45/*AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS*/);
		// 266: check-rule basecs::AnnotationCS.ownedReferences : 54
		serializationMatchSteps[266] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, 18/*ModelElementRefCS*/);
		// 267: check-rule basecs::AnnotationElementCS.ownedDetails : 16
		serializationMatchSteps[267] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 6/*DetailCS*/);
		// 268: check-rule basecs::ClassCS.ownedConstraints : 41
		serializationMatchSteps[268] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/*InvariantConstraintCS*/);
		// 269: check-rule basecs::ConstraintCS.ownedMessageSpecification : 92
		serializationMatchSteps[269] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 39/*SpecificationCS*/);
		// 270: check-rule basecs::ConstraintCS.ownedSpecification : 92
		serializationMatchSteps[270] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 39/*SpecificationCS*/);
		// 271: check-rule basecs::EnumerationCS.ownedLiterals : 22
		serializationMatchSteps[271] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 8/*EnumerationLiteralCS*/);
		// 272: check-rule basecs::ImportCS.ownedPathName : 123
		serializationMatchSteps[272] = createMatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 64/*URIPathNameCS*/);
		// 273: check-rule basecs::ModelElementCS.ownedAnnotations : 1|2|17|98
		serializationMatchSteps[273] = createMatchStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 46/*AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS*/);
		// 274: check-rule basecs::ModelElementRefCS.ownedPathName : 73
		serializationMatchSteps[274] = createMatchStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, 28/*PathNameCS*/);
		// 275: check-rule basecs::OperationCS.ownedBodyExpressions : 92
		serializationMatchSteps[275] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 39/*SpecificationCS*/);
		// 276: check-rule basecs::OperationCS.ownedExceptions : 11|51|81|106|110|117|118
		serializationMatchSteps[276] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 61/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS*/);
		// 277: check-rule basecs::OperationCS.ownedParameters : 72
		serializationMatchSteps[277] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 27/*ParameterCS*/);
		// 278: check-rule basecs::OperationCS.ownedPostconditions : 75
		serializationMatchSteps[278] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 31/*PostconditionConstraintCS*/);
		// 279: check-rule basecs::OperationCS.ownedPreconditions : 76
		serializationMatchSteps[279] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 32/*PreconditionConstraintCS*/);
		// 280: check-rule basecs::PackageCS.ownedClasses : 6|15|21|97
		serializationMatchSteps[280] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 44/*ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS*/);
		// 281: check-rule basecs::PackageOwnerCS.ownedPackages : 71
		serializationMatchSteps[281] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 26/*PackageCS*/);
		// 282: check-rule basecs::PathNameCS.ownedPathElements : 31
		serializationMatchSteps[282] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 10/*FirstPathElementCS*/);
		// 283: check-rule basecs::PathNameCS.ownedPathElements : 67
		serializationMatchSteps[283] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 23/*NextPathElementCS*/);
		// 284: check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37
		serializationMatchSteps[284] = createMatchStep_RuleCheck(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, 11/*ImplicitOppositeCS*/);
		// 285: check-rule basecs::RootCS.ownedImports : 38
		serializationMatchSteps[285] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/*ImportCS*/);
		// 286: check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92
		serializationMatchSteps[286] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 39/*SpecificationCS*/);
		// 287: check-rule basecs::StructuredClassCS.ownedOperations : 70
		serializationMatchSteps[287] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 25/*OperationCS*/);
		// 288: check-rule basecs::StructuredClassCS.ownedProperties : 3|83|96
		serializationMatchSteps[288] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 43/*AttributeCS|ReferenceCS|StructuralFeatureCS*/);
		// 289: check-rule basecs::StructuredClassCS.ownedSuperTypes : 11|51|81|106|110|117|118
		serializationMatchSteps[289] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 61/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS*/);
		// 290: check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56
		serializationMatchSteps[290] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 19/*MultiplicityCS*/);
		// 291: check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100
		serializationMatchSteps[291] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 48/*TemplateParameterSubstitutionCS*/);
		// 292: check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 11|51|81|106|110|115|117|118|130
		serializationMatchSteps[292] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 73/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS*/);
		// 293: check-rule basecs::TemplateSignatureCS.ownedParameters : 114
		serializationMatchSteps[293] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 57/*TypeParameterCS*/);
		// 294: check-rule basecs::TemplateableElementCS.ownedSignature : 101
		serializationMatchSteps[294] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/*TemplateSignatureCS*/);
		// 295: check-rule basecs::TupleTypeCS.ownedParts : 105
		serializationMatchSteps[295] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 51/*TuplePartCS*/);
		// 296: check-rule basecs::TypeParameterCS.ownedExtends : 11|51|81|106|110|117|118
		serializationMatchSteps[296] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 61/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS*/);
		// 297: check-rule basecs::TypedElementCS.ownedType : 107
		serializationMatchSteps[297] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 52/*TypeExpCS*/);
		// 298: check-rule basecs::TypedElementCS.ownedType : 116
		serializationMatchSteps[298] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/*TypedMultiplicityRefCS*/);
		// 299: check-rule basecs::TypedRefCS.ownedMultiplicity : 56
		serializationMatchSteps[299] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/*MultiplicityCS*/);
		// 300: check-rule basecs::TypedTypeRefCS.ownedBinding : 99
		serializationMatchSteps[300] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 47/*TemplateBindingCS*/);
		// 301: check-rule basecs::TypedTypeRefCS.ownedPathName : 73
		serializationMatchSteps[301] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 28/*PathNameCS*/);
		// 302: check-rule basecs::WildcardTypeRefCS.ownedExtends : 11|51|81|106|110|117|118
		serializationMatchSteps[302] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 61/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS*/);
		// 303: check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13
		serializationMatchSteps[303] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/*CurlyBracketedClauseCS*/);
		// 304: check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73
		serializationMatchSteps[304] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 28/*PathNameCS*/);
		// 305: check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84
		serializationMatchSteps[305] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 36/*RoundBracketedClauseCS*/);
		// 306: check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93
		serializationMatchSteps[306] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 40/*SquareBracketedClauseCS*/);
		// 307: check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9
		serializationMatchSteps[307] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 3/*CollectionLiteralPartCS*/);
		// 308: check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11
		serializationMatchSteps[308] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 4/*CollectionTypeCS*/);
		// 309: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[309] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 310: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74
		serializationMatchSteps[310] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 29/*PatternExpCS*/);
		// 311: check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[311] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 312: check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74
		serializationMatchSteps[312] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 29/*PatternExpCS*/);
		// 313: check-rule essentialoclcs::CollectionPatternCS.ownedType : 11
		serializationMatchSteps[313] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 4/*CollectionTypeCS*/);
		// 314: check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56
		serializationMatchSteps[314] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 19/*MultiplicityCS*/);
		// 315: check-rule essentialoclcs::CollectionTypeCS.ownedType : 10|11|51|81|106|108|110|113
		serializationMatchSteps[315] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 56/*CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS*/);
		// 316: check-rule essentialoclcs::ContextCS.ownedExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[316] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 317: check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90
		serializationMatchSteps[317] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 38/*ShadowPartCS*/);
		// 318: check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[318] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 319: check-rule essentialoclcs::IfExpCS.ownedCondition : 5|8|30|36|40|44|45|49|59|66|68|69|74|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[319] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 71/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 320: check-rule essentialoclcs::IfExpCS.ownedElseExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[320] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 321: check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
		serializationMatchSteps[321] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 7/*ElseIfThenExpCS*/);
		// 322: check-rule essentialoclcs::IfExpCS.ownedThenExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[322] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 323: check-rule essentialoclcs::IfThenExpCS.ownedCondition : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[323] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 324: check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[324] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 325: check-rule essentialoclcs::InfixExpCS.ownedLeft : 5|8|36|40|44|49|59|66|68|69|78|79|80|89|95|103|111|125
		serializationMatchSteps[325] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 67/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 326: check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[326] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 327: check-rule essentialoclcs::LetExpCS.ownedInExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[327] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 328: check-rule essentialoclcs::LetExpCS.ownedVariables : 46
		serializationMatchSteps[328] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 14/*LetVariableCS*/);
		// 329: check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84
		serializationMatchSteps[329] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 36/*RoundBracketedClauseCS*/);
		// 330: check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50
		serializationMatchSteps[330] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 15/*MapLiteralPartCS*/);
		// 331: check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51
		serializationMatchSteps[331] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 16/*MapTypeCS*/);
		// 332: check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[332] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 333: check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[333] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 334: check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107
		serializationMatchSteps[334] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 52/*TypeExpCS*/);
		// 335: check-rule essentialoclcs::MapTypeCS.ownedValueType : 107
		serializationMatchSteps[335] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 52/*TypeExpCS*/);
		// 336: check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7
		serializationMatchSteps[336] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 2/*CoIteratorVariableCS*/);
		// 337: check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[337] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 338: check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 5|8|30|36|40|44|45|49|59|61|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[338] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 70/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 339: check-rule essentialoclcs::NavigatingArgCS.ownedType : 107
		serializationMatchSteps[339] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 52/*TypeExpCS*/);
		// 340: check-rule essentialoclcs::NestedExpCS.ownedExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[340] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 341: check-rule essentialoclcs::OperatorExpCS.ownedRight : 45|77
		serializationMatchSteps[341] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 34/*LetExpCS|PrefixedLetExpCS*/);
		// 342: check-rule essentialoclcs::OperatorExpCS.ownedRight : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[342] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 343: check-rule essentialoclcs::OperatorExpCS.ownedRight : 5|8|36|40|44|49|59|66|68|69|78|79|80|89|95|103|111|125
		serializationMatchSteps[343] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 67/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 344: check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107
		serializationMatchSteps[344] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 52/*TypeExpCS*/);
		// 345: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 5|8|30|36|40|44|45|49|59|66|68|69|74|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[345] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 71/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 346: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95
		serializationMatchSteps[346] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 41/*StringLiteralExpCS*/);
		// 347: check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[347] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 348: check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104
		serializationMatchSteps[348] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 50/*TupleLiteralPartCS*/);
		// 349: check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112
		serializationMatchSteps[349] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 55/*TypeLiteralWithMultiplicityCS*/);
		// 350: check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13
		serializationMatchSteps[350] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/*CurlyBracketedClauseCS*/);
		// 351: check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73
		serializationMatchSteps[351] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 28/*PathNameCS*/);
		// 352: check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[352] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 353: check-rule essentialoclcs::VariableCS.ownedInitExpression : 5|8|30|36|40|44|45|49|59|66|68|69|77|78|79|80|89|95|103|111|125
		serializationMatchSteps[353] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 69/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 354: check-rule essentialoclcs::VariableCS.ownedType : 107
		serializationMatchSteps[354] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 52/*TypeExpCS*/);
	}

	/**
	 * Initialize expression terms used during the matching process.
	 */
	private void initMatchTerms() {
		// 0: 0
		serializationMatchTerms[0] = createSerializationMatchTermInteger(0);
		// 1: 1
		serializationMatchTerms[1] = createSerializationMatchTermInteger(1);
		// 2: V0
		serializationMatchTerms[2] = createSerializationMatchTermVariable(0);
		// 3: |AbstractNameExpCS::isPre.'@'|
		serializationMatchTerms[3] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 8 /* '@' */);
		// 4: |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[4] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// 5: |AbstractNameExpCS::ownedPathName|
		serializationMatchTerms[5] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		// 6: |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchTerms[6] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// 7: |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchTerms[7] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		// 8: |AnnotationCS::ownedContents|
		serializationMatchTerms[8] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
		// 9: |AnnotationCS::ownedReferences|
		serializationMatchTerms[9] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
		// 10: |AnnotationElementCS::ownedDetails|
		serializationMatchTerms[10] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		// 11: |BooleanLiteralExpCS::symbol.'false|true'|
		serializationMatchTerms[11] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 14 /* 'false|true' */);
		// 12: |ClassCS::instanceClassName|
		serializationMatchTerms[12] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
		// 13: |ClassCS::ownedConstraints|
		serializationMatchTerms[13] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		// 14: |CollectionLiteralExpCS::ownedParts|
		serializationMatchTerms[14] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		// 15: |CollectionLiteralExpCS::ownedType|
		serializationMatchTerms[15] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		// 16: |CollectionLiteralPartCS::ownedExpression|
		serializationMatchTerms[16] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		// 17: |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchTerms[17] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		// 18: |CollectionPatternCS::ownedParts|
		serializationMatchTerms[18] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		// 19: |CollectionPatternCS::ownedPatternGuard|
		serializationMatchTerms[19] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD);
		// 20: |CollectionPatternCS::ownedType|
		serializationMatchTerms[20] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		// 21: |CollectionPatternCS::restVariableName|
		serializationMatchTerms[21] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		// 22: |CollectionTypeCS::name|
		serializationMatchTerms[22] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		// 23: |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchTerms[23] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		// 24: |CollectionTypeCS::ownedType|
		serializationMatchTerms[24] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		// 25: |ConstraintCS::ownedMessageSpecification|
		serializationMatchTerms[25] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		// 26: |ConstraintCS::ownedSpecification|
		serializationMatchTerms[26] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		// 27: |ConstraintCS::stereotype.'invariant'|
		serializationMatchTerms[27] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 16 /* 'invariant' */);
		// 28: |ConstraintCS::stereotype.'postcondition'|
		serializationMatchTerms[28] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 17 /* 'postcondition' */);
		// 29: |ConstraintCS::stereotype.'precondition'|
		serializationMatchTerms[29] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 18 /* 'precondition' */);
		// 30: |ContextCS::ownedExpression|
		serializationMatchTerms[30] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		// 31: |CurlyBracketedClauseCS::ownedParts|
		serializationMatchTerms[31] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		// 32: |CurlyBracketedClauseCS::value|
		serializationMatchTerms[32] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__VALUE);
		// 33: |DataTypeCS::isPrimitive.'primitive'|
		serializationMatchTerms[33] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, 19 /* 'primitive' */);
		// 34: |DataTypeCS::isSerializable.'serializable'|
		serializationMatchTerms[34] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, 20 /* 'serializable' */);
		// 35: |DataTypeCS::isSerializable|
		serializationMatchTerms[35] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE);
		// 36: |DetailCS::values|
		serializationMatchTerms[36] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DETAIL_CS__VALUES);
		// 37: |DocumentationCS::value|
		serializationMatchTerms[37] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		// 38: |EnumerationCS::isSerializable.'serializable'|
		serializationMatchTerms[38] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, 20 /* 'serializable' */);
		// 39: |EnumerationCS::isSerializable|
		serializationMatchTerms[39] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE);
		// 40: |EnumerationCS::ownedLiterals|
		serializationMatchTerms[40] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		// 41: |EnumerationLiteralCS::literal|
		serializationMatchTerms[41] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
		// 42: |EnumerationLiteralCS::value|
		serializationMatchTerms[42] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
		// 43: |ExpSpecificationCS::ownedExpression|
		serializationMatchTerms[43] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		// 44: |IfExpCS::isImplicit|
		serializationMatchTerms[44] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__IS_IMPLICIT);
		// 45: |IfExpCS::ownedCondition|
		serializationMatchTerms[45] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		// 46: |IfExpCS::ownedElseExpression|
		serializationMatchTerms[46] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		// 47: |IfExpCS::ownedIfThenExpressions|
		serializationMatchTerms[47] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		// 48: |IfExpCS::ownedThenExpression|
		serializationMatchTerms[48] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		// 49: |IfThenExpCS::ownedCondition|
		serializationMatchTerms[49] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		// 50: |IfThenExpCS::ownedThenExpression|
		serializationMatchTerms[50] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		// 51: |ImportCS::isAll.'::*'|
		serializationMatchTerms[51] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, 6 /* '::*' */);
		// 52: |ImportCS::ownedPathName|
		serializationMatchTerms[52] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		// 53: |InfixExpCS::ownedLeft|
		serializationMatchTerms[53] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		// 54: |LambdaLiteralExpCS::ownedExpressionCS|
		serializationMatchTerms[54] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		// 55: |LetExpCS::isImplicit|
		serializationMatchTerms[55] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__IS_IMPLICIT);
		// 56: |LetExpCS::ownedInExpression|
		serializationMatchTerms[56] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		// 57: |LetExpCS::ownedVariables|
		serializationMatchTerms[57] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		// 58: |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchTerms[58] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// 59: |MapLiteralExpCS::ownedParts|
		serializationMatchTerms[59] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		// 60: |MapLiteralExpCS::ownedType|
		serializationMatchTerms[60] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		// 61: |MapLiteralPartCS::ownedKey|
		serializationMatchTerms[61] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		// 62: |MapLiteralPartCS::ownedValue|
		serializationMatchTerms[62] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		// 63: |MapTypeCS::name.'Map'|
		serializationMatchTerms[63] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 9 /* 'Map' */);
		// 64: |MapTypeCS::ownedKeyType|
		serializationMatchTerms[64] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		// 65: |MapTypeCS::ownedValueType|
		serializationMatchTerms[65] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		// 66: |ModelElementCS::ownedAnnotations|
		serializationMatchTerms[66] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		// 67: |ModelElementRefCS::ownedPathName|
		serializationMatchTerms[67] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
		// 68: |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[68] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// 69: |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[69] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// 70: |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[70] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 23 /* '|1' */);
		// 71: |MultiplicityCS::isNullFree|
		serializationMatchTerms[71] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE);
		// 72: |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[72] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 4 /* '*|+|?' */);
		// 73: |NamedElementCS::name|
		serializationMatchTerms[73] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// 74: |NavigatingArgCS::ownedCoIterator|
		serializationMatchTerms[74] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		// 75: |NavigatingArgCS::ownedInitExpression|
		serializationMatchTerms[75] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		// 76: |NavigatingArgCS::ownedNameExpression|
		serializationMatchTerms[76] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		// 77: |NavigatingArgCS::ownedType|
		serializationMatchTerms[77] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		// 78: |NavigatingArgCS::prefix.','|
		serializationMatchTerms[78] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 5 /* ',' */);
		// 79: |NavigatingArgCS::prefix.';'|
		serializationMatchTerms[79] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 7 /* ';' */);
		// 80: |NavigatingArgCS::prefix.'|'|
		serializationMatchTerms[80] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 22 /* '|' */);
		// 81: |NavigatingArgCS::prefix|
		serializationMatchTerms[81] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX);
		// 82: |NestedExpCS::ownedExpression|
		serializationMatchTerms[82] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		// 83: |NumberLiteralExpCS::symbol|
		serializationMatchTerms[83] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		// 84: |OCLinEcoreConstraintCS::isCallable.'callable'|
		serializationMatchTerms[84] = createSerializationMatchTermEAttributeSize(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, 12 /* 'callable' */);
		// 85: |OCLinEcoreConstraintCS::isCallable|
		serializationMatchTerms[85] = createSerializationMatchTermEStructuralFeatureSize(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE);
		// 86: |OperationCS::ownedBodyExpressions|
		serializationMatchTerms[86] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		// 87: |OperationCS::ownedExceptions|
		serializationMatchTerms[87] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		// 88: |OperationCS::ownedParameters|
		serializationMatchTerms[88] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		// 89: |OperationCS::ownedPostconditions|
		serializationMatchTerms[89] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		// 90: |OperationCS::ownedPreconditions|
		serializationMatchTerms[90] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		// 91: |OperatorExpCS::ownedRight|
		serializationMatchTerms[91] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		// 92: |PackageCS::nsPrefix|
		serializationMatchTerms[92] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		// 93: |PackageCS::nsURI|
		serializationMatchTerms[93] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		// 94: |PackageCS::ownedClasses|
		serializationMatchTerms[94] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		// 95: |PackageOwnerCS::ownedPackages|
		serializationMatchTerms[95] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		// 96: |PathElementCS::referredElement|
		serializationMatchTerms[96] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// 97: |PathNameCS::ownedPathElements|
		serializationMatchTerms[97] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// 98: |PatternExpCS::ownedPatternType|
		serializationMatchTerms[98] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		// 99: |PatternExpCS::patternVariableName|
		serializationMatchTerms[99] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		// 100: |PrimitiveTypeRefCS::name|
		serializationMatchTerms[100] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		// 101: |ReferenceCS::ownedImplicitOpposites|
		serializationMatchTerms[101] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
		// 102: |ReferenceCS::referredKeys|
		serializationMatchTerms[102] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		// 103: |ReferenceCS::referredOpposite|
		serializationMatchTerms[103] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
		// 104: |RootCS::ownedImports|
		serializationMatchTerms[104] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		// 105: |SelfExpCS::name|
		serializationMatchTerms[105] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SELF_EXP_CS__NAME);
		// 106: |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[106] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// 107: |ShadowPartCS::referredProperty|
		serializationMatchTerms[107] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// 108: |SpecificationCS::exprString|
		serializationMatchTerms[108] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		// 109: |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[109] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// 110: |StringLiteralExpCS::segments|
		serializationMatchTerms[110] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// 111: |StructuralFeatureCS::default|
		serializationMatchTerms[111] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		// 112: |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchTerms[112] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		// 113: |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchTerms[113] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, 11 /* 'abstract' */);
		// 114: |StructuredClassCS::isInterface.'interface'|
		serializationMatchTerms[114] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, 15 /* 'interface' */);
		// 115: |StructuredClassCS::ownedMetaclass|
		serializationMatchTerms[115] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_METACLASS);
		// 116: |StructuredClassCS::ownedOperations|
		serializationMatchTerms[116] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		// 117: |StructuredClassCS::ownedProperties|
		serializationMatchTerms[117] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		// 118: |StructuredClassCS::ownedSuperTypes|
		serializationMatchTerms[118] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		// 119: |SysMLCS::value|
		serializationMatchTerms[119] = createSerializationMatchTermEStructuralFeatureSize(OCLinEcoreCSPackage.Literals.SYS_MLCS__VALUE);
		// 120: |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[120] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// 121: |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[121] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// 122: |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[122] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// 123: |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[123] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// 124: |TemplateableElementCS::ownedSignature|
		serializationMatchTerms[124] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		// 125: |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[125] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// 126: |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[126] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 10 /* 'Tuple' */);
		// 127: |TupleTypeCS::ownedParts|
		serializationMatchTerms[127] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// 128: |TypeLiteralExpCS::ownedPathName|
		serializationMatchTerms[128] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_PATH_NAME);
		// 129: |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[129] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// 130: |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[130] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// 131: |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[131] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// 132: |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[132] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// 133: |TypeParameterCS::ownedExtends|
		serializationMatchTerms[133] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// 134: |TypedElementCS::ownedType|
		serializationMatchTerms[134] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		// 135: |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
		serializationMatchTerms[135] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */);
		// 136: |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
		serializationMatchTerms[136] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */);
		// 137: |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
		serializationMatchTerms[137] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */);
		// 138: |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
		serializationMatchTerms[138] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 3 /* '!ordered|!unique|ordered|unique' */);
		// 139: |TypedElementCS::qualifiers.'definition'|
		serializationMatchTerms[139] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 13 /* 'definition' */);
		// 140: |TypedElementCS::qualifiers.'static'|
		serializationMatchTerms[140] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 21 /* 'static' */);
		// 141: |TypedElementCS::qualifiers|
		serializationMatchTerms[141] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		// 142: |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[142] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// 143: |TypedTypeRefCS::isTypeof|
		serializationMatchTerms[143] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF);
		// 144: |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[144] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// 145: |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[145] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// 146: |VariableCS::ownedInitExpression|
		serializationMatchTerms[146] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		// 147: |VariableCS::ownedType|
		serializationMatchTerms[147] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		// 148: |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[148] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// 149: |WildcardTypeRefCS::ownedSuper|
		serializationMatchTerms[149] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_SUPER);
		// 150: (|AbstractNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[150] = createSerializationMatchTermSubtract(5, 1);
		// 151: (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchTerms[151] = createSerializationMatchTermSubtract(10, 1);
		// 152: (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchTerms[152] = createSerializationMatchTermGreaterThan(10, 0);
		// 153: (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
		serializationMatchTerms[153] = createSerializationMatchTermSubtract(11, 1);
		// 154: (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[154] = createSerializationMatchTermSubtract(14, 1);
		// 155: (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[155] = createSerializationMatchTermGreaterThan(14, 0);
		// 156: (|CollectionLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[156] = createSerializationMatchTermSubtract(15, 1);
		// 157: (|CollectionLiteralPartCS::ownedExpression| - 1)
		serializationMatchTerms[157] = createSerializationMatchTermSubtract(16, 1);
		// 158: (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchTerms[158] = createSerializationMatchTermSubtract(18, 1);
		// 159: (|CollectionPatternCS::ownedType| - 1)
		serializationMatchTerms[159] = createSerializationMatchTermSubtract(20, 1);
		// 160: (|CollectionTypeCS::name| - 1)
		serializationMatchTerms[160] = createSerializationMatchTermSubtract(22, 1);
		// 161: (|ConstraintCS::stereotype.'invariant'| - 1)
		serializationMatchTerms[161] = createSerializationMatchTermSubtract(27, 1);
		// 162: (|ConstraintCS::stereotype.'postcondition'| - 1)
		serializationMatchTerms[162] = createSerializationMatchTermSubtract(28, 1);
		// 163: (|ConstraintCS::stereotype.'precondition'| - 1)
		serializationMatchTerms[163] = createSerializationMatchTermSubtract(29, 1);
		// 164: (|ContextCS::ownedExpression| - 1)
		serializationMatchTerms[164] = createSerializationMatchTermSubtract(30, 1);
		// 165: (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchTerms[165] = createSerializationMatchTermSubtract(31, 1);
		// 166: (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchTerms[166] = createSerializationMatchTermGreaterThan(31, 0);
		// 167: (|ExpSpecificationCS::ownedExpression| - 1)
		serializationMatchTerms[167] = createSerializationMatchTermSubtract(43, 1);
		// 168: (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[168] = createSerializationMatchTermSubtract(45, 1);
		// 169: (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[169] = createSerializationMatchTermSubtract(46, 1);
		// 170: (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[170] = createSerializationMatchTermSubtract(48, 1);
		// 171: (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[171] = createSerializationMatchTermSubtract(49, 1);
		// 172: (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[172] = createSerializationMatchTermSubtract(50, 1);
		// 173: (|ImportCS::ownedPathName| - 1)
		serializationMatchTerms[173] = createSerializationMatchTermSubtract(52, 1);
		// 174: (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[174] = createSerializationMatchTermSubtract(53, 1);
		// 175: (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[175] = createSerializationMatchTermSubtract(54, 1);
		// 176: (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[176] = createSerializationMatchTermSubtract(56, 1);
		// 177: (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[177] = createSerializationMatchTermSubtract(57, 1);
		// 178: (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[178] = createSerializationMatchTermSubtract(59, 1);
		// 179: (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[179] = createSerializationMatchTermGreaterThan(59, 0);
		// 180: (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[180] = createSerializationMatchTermSubtract(60, 1);
		// 181: (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[181] = createSerializationMatchTermSubtract(61, 1);
		// 182: (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[182] = createSerializationMatchTermSubtract(62, 1);
		// 183: (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[183] = createSerializationMatchTermSubtract(63, 1);
		// 184: (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[184] = createSerializationMatchTermSubtract(64, 2);
		// 185: (|ModelElementCS::ownedAnnotations| > 0)
		serializationMatchTerms[185] = createSerializationMatchTermGreaterThan(66, 0);
		// 186: (|ModelElementRefCS::ownedPathName| - 1)
		serializationMatchTerms[186] = createSerializationMatchTermSubtract(67, 1);
		// 187: (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[187] = createSerializationMatchTermSubtract(68, 1);
		// 188: (|MultiplicityCS::isNullFree.'|1'| - 1)
		serializationMatchTerms[188] = createSerializationMatchTermSubtract(70, 1);
		// 189: (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[189] = createSerializationMatchTermSubtract(72, 1);
		// 190: (|NamedElementCS::name| - 1)
		serializationMatchTerms[190] = createSerializationMatchTermSubtract(73, 1);
		// 191: (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[191] = createSerializationMatchTermSubtract(74, 1);
		// 192: (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[192] = createSerializationMatchTermSubtract(75, 1);
		// 193: (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[193] = createSerializationMatchTermSubtract(76, 1);
		// 194: (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[194] = createSerializationMatchTermSubtract(77, 1);
		// 195: (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[195] = createSerializationMatchTermSubtract(78, 1);
		// 196: (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[196] = createSerializationMatchTermSubtract(79, 1);
		// 197: (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[197] = createSerializationMatchTermSubtract(80, 1);
		// 198: (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[198] = createSerializationMatchTermSubtract(82, 1);
		// 199: (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[199] = createSerializationMatchTermSubtract(83, 1);
		// 200: (|OperationCS::ownedBodyExpressions| > 0)
		serializationMatchTerms[200] = createSerializationMatchTermGreaterThan(86, 0);
		// 201: (|OperationCS::ownedExceptions| - 1)
		serializationMatchTerms[201] = createSerializationMatchTermSubtract(87, 1);
		// 202: (|OperationCS::ownedExceptions| > 0)
		serializationMatchTerms[202] = createSerializationMatchTermGreaterThan(87, 0);
		// 203: (|OperationCS::ownedParameters| - 1)
		serializationMatchTerms[203] = createSerializationMatchTermSubtract(88, 1);
		// 204: (|OperationCS::ownedParameters| > 0)
		serializationMatchTerms[204] = createSerializationMatchTermGreaterThan(88, 0);
		// 205: (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[205] = createSerializationMatchTermSubtract(91, 1);
		// 206: (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[206] = createSerializationMatchTermSubtract(96, 1);
		// 207: (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[207] = createSerializationMatchTermSubtract(97, 1);
		// 208: (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[208] = createSerializationMatchTermSubtract(98, 1);
		// 209: (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[209] = createSerializationMatchTermSubtract(100, 1);
		// 210: (|ReferenceCS::referredKeys| - 1)
		serializationMatchTerms[210] = createSerializationMatchTermSubtract(102, 1);
		// 211: (|ReferenceCS::referredKeys| > 0)
		serializationMatchTerms[211] = createSerializationMatchTermGreaterThan(102, 0);
		// 212: (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[212] = createSerializationMatchTermSubtract(106, 1);
		// 213: (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[213] = createSerializationMatchTermSubtract(107, 1);
		// 214: (|SpecificationCS::exprString| - 1)
		serializationMatchTerms[214] = createSerializationMatchTermSubtract(108, 1);
		// 215: (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[215] = createSerializationMatchTermSubtract(109, 1);
		// 216: (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
		serializationMatchTerms[216] = createSerializationMatchTermGreaterThan(112, 0);
		// 217: (|StructuredClassCS::isInterface.'interface'| > 0)
		serializationMatchTerms[217] = createSerializationMatchTermGreaterThan(114, 0);
		// 218: (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchTerms[218] = createSerializationMatchTermSubtract(118, 1);
		// 219: (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchTerms[219] = createSerializationMatchTermGreaterThan(118, 0);
		// 220: (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[220] = createSerializationMatchTermSubtract(121, 1);
		// 221: (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[221] = createSerializationMatchTermSubtract(122, 1);
		// 222: (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[222] = createSerializationMatchTermSubtract(123, 1);
		// 223: (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[223] = createSerializationMatchTermSubtract(125, 1);
		// 224: (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[224] = createSerializationMatchTermSubtract(126, 1);
		// 225: (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[225] = createSerializationMatchTermSubtract(127, 1);
		// 226: (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[226] = createSerializationMatchTermGreaterThan(127, 0);
		// 227: (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[227] = createSerializationMatchTermSubtract(129, 1);
		// 228: (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[228] = createSerializationMatchTermSubtract(131, 1);
		// 229: (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[229] = createSerializationMatchTermSubtract(133, 1);
		// 230: (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[230] = createSerializationMatchTermGreaterThan(133, 0);
		// 231: (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[231] = createSerializationMatchTermSubtract(134, 1);
		// 232: (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
		serializationMatchTerms[232] = createSerializationMatchTermGreaterThan(135, 0);
		// 233: (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
		serializationMatchTerms[233] = createSerializationMatchTermGreaterThan(136, 0);
		// 234: (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
		serializationMatchTerms[234] = createSerializationMatchTermGreaterThan(137, 0);
		// 235: (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
		serializationMatchTerms[235] = createSerializationMatchTermGreaterThan(138, 0);
		// 236: (|TypedElementCS::qualifiers.'definition'| - 1)
		serializationMatchTerms[236] = createSerializationMatchTermSubtract(139, 1);
		// 237: (|TypedElementCS::qualifiers.'static'| - 1)
		serializationMatchTerms[237] = createSerializationMatchTermSubtract(140, 1);
		// 238: (|TypedTypeRefCS::ownedBinding| - 1)
		serializationMatchTerms[238] = createSerializationMatchTermSubtract(144, 1);
		// 239: (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[239] = createSerializationMatchTermSubtract(145, 1);
		// 240: (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[240] = createSerializationMatchTermSubtract(146, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS-0(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] = createSerializationRule("FirstPathElementCS-0", 31,
			createSerializationMatchSteps(
				41		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				283		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::MultiplicityBoundsCS-0(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { ".." upperBound=UPPER }[?] }
		serializationRules[1] = createSerializationRule("MultiplicityBoundsCS-0", 55,
			createSerializationMatchSteps(
				72		/* assert |MultiplicityCS::isNullFree| == 0 */,
				128		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				27		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				130		/* '..' || no-space value no-space */,
				116		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-0(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" }
		serializationRules[2] = createSerializationRule("MultiplicityCS-0", 56,
			createSerializationMatchSteps(
				72		/* assert |MultiplicityCS::isNullFree| == 0 */,
				128		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				27		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				141		/* '[' || no-space value no-space */,
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				130		/* '..' || no-space value no-space */,
				116		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				182		/* '|?' || no-space value no-space */,
				142		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-1(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree?="|1" "]" }
		serializationRules[3] = createSerializationRule("MultiplicityCS-1", 56,
			createSerializationMatchSteps(
				28		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				128		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				27		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				141		/* '[' || no-space value no-space */,
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				130		/* '..' || no-space value no-space */,
				116		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				7		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				142		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(23/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-2(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" }
		serializationRules[4] = createSerializationRule("MultiplicityCS-2", 56,
			createSerializationMatchSteps(
				72		/* assert |MultiplicityCS::isNullFree| == 0 */,
				128		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				27		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				141		/* '[' || no-space value no-space */,
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				130		/* '..' || no-space value no-space */,
				116		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				142		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-3(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "|?" "]" }
		serializationRules[5] = createSerializationRule("MultiplicityCS-3", 56,
			createSerializationMatchSteps(
				72		/* assert |MultiplicityCS::isNullFree| == 0 */,
				29		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				141		/* '[' || no-space value no-space */,
				113		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				182		/* '|?' || no-space value no-space */,
				142		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(4/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-4(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} isNullFree?="|1" "]" }
		serializationRules[6] = createSerializationRule("MultiplicityCS-4", 56,
			createSerializationMatchSteps(
				28		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				29		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				141		/* '[' || no-space value no-space */,
				113		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				7		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				142		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(23/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(4/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-5(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "]" }
		serializationRules[7] = createSerializationRule("MultiplicityCS-5", 56,
			createSerializationMatchSteps(
				72		/* assert |MultiplicityCS::isNullFree| == 0 */,
				29		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				141		/* '[' || no-space value no-space */,
				113		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				142		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(4/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityStringCS-0(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		serializationRules[8] = createSerializationRule("MultiplicityStringCS-0", 57,
			createSerializationMatchSteps(
				72		/* assert |MultiplicityCS::isNullFree| == 0 */,
				29		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				113		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(4/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::NextPathElementCS-0(basecs::PathElementCS): referredElement=UnreservedName
		serializationRules[9] = createSerializationRule("NextPathElementCS-0", 67,
			createSerializationMatchSteps(
				41		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				282		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::PathNameCS-0(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[10] = createSerializationRule("PathNameCS-0", 73,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				268		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */,
				190		/* V00*2-steps || value */,
				133		/* '::' || no-space value no-space */,
				269		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 24/* FirstPathElementCS,NextPathElementCS */,
					(31/*FirstPathElementCS*/ << 4) | 0 /*[1]*/,
					(67/*NextPathElementCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TemplateBindingCS-0(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] = createSerializationRule("TemplateBindingCS-0", 99,
			createSerializationMatchSteps(
				290		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : MultiplicityCS */,
				291		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : TemplateParameterSubstitutionCS */,
				169		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				109		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			),
			createSerializationSteps(
				89		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				190		/* V00*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				89		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				196		/* V01*1-steps || value */,
				59		/* TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 48/* TemplateParameterSubstitutionCS */,
					(100/*TemplateParameterSubstitutionCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TemplateParameterSubstitutionCS-0(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] = createSerializationRule("TemplateParameterSubstitutionCS-0", 100,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				292		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
				48		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			),
			createSerializationSteps(
				20		/* TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 58/* TypeRefCS */,
					(115/*TypeRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::TypeParameterCS-0(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[13] = createSerializationRule("TypeParameterCS-0", 114,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				296		/* check-rule basecs::TypeParameterCS.ownedExtends : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				113		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				154		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				154		/* 'extends' || soft-space value soft-space */,
				43		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */,
				200		/* V01*2-steps || value */,
				122		/* '&&' || soft-space value soft-space */,
				43		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::UnreservedPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=NextPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[14] = createSerializationRule("UnreservedPathNameCS-0", 127,
			createSerializationMatchSteps(
				283		/* check-rule basecs::PathNameCS.ownedPathElements : NextPathElementCS */,
				107		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			),
			createSerializationSteps(
				269		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */,
				190		/* V00*2-steps || value */,
				133		/* '::' || no-space value no-space */,
				269		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 23/* NextPathElementCS */,
					(67/*NextPathElementCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::WildcardTypeRefCS-0(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[15] = createSerializationRule("WildcardTypeRefCS-0", 130,
			createSerializationMatchSteps(
				102		/* assert |WildcardTypeRefCS::ownedSuper| == 0 */,
				302		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				146		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			),
			createSerializationSteps(
				138		/* '?' || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				154		/* 'extends' || soft-space value soft-space */,
				44		/* WildcardTypeRefCS::ownedExtends=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::BooleanLiteralExpCS-0(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[16] = createSerializationRule("BooleanLiteralExpCS-0", 5,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				2		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			),
			createSerializationSteps(
				114		/* BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, false,
					(14/*'false|true'*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CoIteratorVariableCS-0(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[17] = createSerializationRule("CoIteratorVariableCS-0", 7,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				101		/* assert |VariableCS::ownedInitExpression| == 0 */,
				354		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				145		/* assign V0 = |VariableCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				100		/* VariableCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralExpCS-0(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[18] = createSerializationRule("CollectionLiteralExpCS-0", 8,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				307		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS */,
				308		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS */,
				3		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				103		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				148		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				94		/* CollectionLiteralExpCS::ownedType=CollectionTypeCS || value */,
				181		/* '{' || soft-space value push soft-new-line */,
				192		/* V00*4-steps || value */,
				67		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				200		/* V01*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				67		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 3/* CollectionLiteralPartCS */,
					(9/*CollectionLiteralPartCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 4/* CollectionTypeCS */,
					(11/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionLiteralPartCS-0(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] }
		serializationRules[19] = createSerializationRule("CollectionLiteralPartCS-0", 9,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				309		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				311		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				118		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				4		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				37		/* CollectionLiteralPartCS::ownedExpression=ExpCS || value */,
				189		/* V00*2-steps || value */,
				130		/* '..' || no-space value no-space */,
				55		/* CollectionLiteralPartCS::ownedLastExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralPartCS-1(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
		serializationRules[20] = createSerializationRule("CollectionLiteralPartCS-1", 9,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				61		/* assert |CollectionLiteralPartCS::ownedLastExpression| == 0 */,
				310		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : PatternExpCS */,
				4		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				38		/* CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 29/* PatternExpCS */,
					(74/*PatternExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionPatternCS-0(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" }
		serializationRules[21] = createSerializationRule("CollectionPatternCS-0", 10,
			createSerializationMatchSteps(
				98		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				62		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				312		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				313		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				119		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				149		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				5		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				95		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				181		/* '{' || soft-space value push soft-new-line */,
				194		/* V00*6-steps || value */,
				68		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				200		/* V01*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				68		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				127		/* '++' || soft-space value soft-space */,
				111		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 29/* PatternExpCS */,
					(74/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 4/* CollectionTypeCS */,
					(11/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionTypeCS-0(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] }
		serializationRules[22] = createSerializationRule("CollectionTypeCS-0", 11,
			createSerializationMatchSteps(
				98		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				314		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				315		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				120		/* assign V0 = |CollectionTypeCS::ownedType| */,
				6		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				158		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				14		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				124		/* '(' || no-space value no-space */,
				96		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				196		/* V01*1-steps || value */,
				26		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				125		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 53/* TypeExpWithoutMultiplicityCS */,
					(108/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS-0(essentialoclcs::CurlyBracketedClauseCS): { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" }
		serializationRules[23] = createSerializationRule("CurlyBracketedClauseCS-0", 13,
			createSerializationMatchSteps(
				64		/* assert |CurlyBracketedClauseCS::value| == 0 */,
				317		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : ShadowPartCS */,
				104		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				150		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				181		/* '{' || soft-space value push soft-new-line */,
				192		/* V00*4-steps || value */,
				69		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				200		/* V01*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				69		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 38/* ShadowPartCS */,
					(90/*ShadowPartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::ElseIfThenExpCS-0(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[24] = createSerializationRule("ElseIfThenExpCS-0", 20,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				323		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				324		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				16		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				15		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				151		/* 'elseif' || soft-new-line pop soft-space value push soft-space */,
				28		/* IfThenExpCS::ownedCondition=ExpCS || value */,
				174		/* 'then' || pop value push soft-space */,
				93		/* IfThenExpCS::ownedThenExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::ExpCS-18(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } }
		serializationRules[25] = createSerializationRule("ExpCS-18", 30,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				325		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				342		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				40		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				18		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				56		/* InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value */,
				264		/* NamedElementCS::name=BinaryOperatorName || soft-space value soft-space */,
				270		/* OperatorExpCS::ownedRight=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 35/* PrefixedPrimaryExpCS */,
					(78/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::IfExpCS-0(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[26] = createSerializationRule("IfExpCS-0", 36,
			createSerializationMatchSteps(
				69		/* assert |IfExpCS::isImplicit| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				319		/* check-rule essentialoclcs::IfExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				320		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				321		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS */,
				322		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				13		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				125		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				14		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				12		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				155		/* 'if' || soft-new-line value push soft-space */,
				27		/* IfExpCS::ownedCondition=ExpCS|PatternExpCS || value */,
				175		/* 'then' || pop soft-space value push soft-space */,
				92		/* IfExpCS::ownedThenExpression=ExpCS || value */,
				187		/* V00*1-steps || value */,
				45		/* IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value */,
				150		/* 'else' || soft-new-line pop value push soft-space */,
				35		/* IfExpCS::ownedElseExpression=ExpCS || value */,
				152		/* 'endif' || soft-new-line pop value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 30/* ExpCS,PatternExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/,
					(74/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 7/* ElseIfThenExpCS */,
					(20/*ElseIfThenExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::InvalidLiteralExpCS-0(essentialoclcs::InvalidLiteralExpCS): "invalid"
		serializationRules[27] = createSerializationRule("InvalidLiteralExpCS-0", 40,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				160		/* 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::LambdaLiteralExpCS-0(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[28] = createSerializationRule("LambdaLiteralExpCS-0", 44,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				326		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				19		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				139		/* 'Lambda' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				42		/* LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::LetExpCS-0(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[29] = createSerializationRule("LetExpCS-0", 45,
			createSerializationMatchSteps(
				70		/* assert |LetExpCS::isImplicit| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				327		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				328		/* check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS */,
				20		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				105		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				162		/* 'let' || soft-space value push */,
				103		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				190		/* V00*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				103		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				158		/* 'in' || soft-space pop value soft-new-line */,
				48		/* LetExpCS::ownedInExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 14/* LetVariableCS */,
					(46/*LetVariableCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::LetVariableCS-0(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[30] = createSerializationRule("LetVariableCS-0", 46,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				353		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				329		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				354		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				57		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				175		/* assign V1 = |VariableCS::ownedType| */,
				126		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				85		/* LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				100		/* VariableCS::ownedType=TypeExpCS || value */,
				136		/* '=' || soft-space value soft-space */,
				52		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 36/* RoundBracketedClauseCS */,
					(84/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::MapLiteralExpCS-0(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[31] = createSerializationRule("MapLiteralExpCS-0", 49,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				330		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS */,
				331		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS */,
				21		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				106		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				151		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				97		/* MapLiteralExpCS::ownedType=MapTypeCS || value */,
				181		/* '{' || soft-space value push soft-new-line */,
				192		/* V00*4-steps || value */,
				70		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				200		/* V01*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				70		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 15/* MapLiteralPartCS */,
					(50/*MapLiteralPartCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 16/* MapTypeCS */,
					(51/*MapTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapLiteralPartCS-0(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS {"with"|"<-"} ownedValue=ExpCS }
		serializationRules[32] = createSerializationRule("MapLiteralPartCS-0", 50,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				332		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				333		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				23		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				22		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				53		/* MapLiteralPartCS::ownedKey=ExpCS || value */,
				177		/* 'with' || value */,
				101		/* MapLiteralPartCS::ownedValue=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapTypeCS-0(essentialoclcs::MapTypeCS): { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] }
		serializationRules[33] = createSerializationRule("MapTypeCS-0", 51,
			createSerializationMatchSteps(
				98		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				334		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				335		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				127		/* assign V0 = |MapTypeCS::ownedValueType| */,
				25		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				24		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				15		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				124		/* '(' || no-space value no-space */,
				54		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				129		/* ',' || no-space value soft-space */,
				102		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				125		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(9/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::Model-0(essentialoclcs::ContextCS): ownedExpression=ExpCS
		serializationRules[34] = createSerializationRule("Model-0", 52,
			createSerializationMatchSteps(
				73		/* assert |NamedElementCS::name| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				87		/* assert |RootCS::ownedImports| == 0 */,
				316		/* check-rule essentialoclcs::ContextCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				10		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				39		/* ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NameExpCS-0(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre?="@" "pre" }[?] }
		serializationRules[35] = createSerializationRule("NameExpCS-0", 59,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				303		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				304		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS */,
				305		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				306		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS */,
				215		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				198		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				156		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				116		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				73		/* AbstractNameExpCS::ownedPathName=PathNameCS || value */,
				187		/* V00*1-steps || value */,
				88		/* AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value */,
				196		/* V01*1-steps || value */,
				84		/* AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				214		/* V02*1-steps || value */,
				31		/* AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				225		/* V03*2-steps || value */,
				8		/* AbstractNameExpCS::isPre?='@' || soft-space value soft-space */,
				169		/* 'pre' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, false,
					(8/*'@'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/* CurlyBracketedClauseCS */,
					(13/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 36/* RoundBracketedClauseCS */,
					(84/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 40/* SquareBracketedClauseCS */,
					(93/*SquareBracketedClauseCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-0(essentialoclcs::NavigatingArgCS): { ":" ownedType=TypeExpCS }
		serializationRules[36] = createSerializationRule("NavigatingArgCS-0", 60,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				75		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				76		/* assert |NavigatingArgCS::ownedNameExpression| == 0 */,
				78		/* assert |NavigatingArgCS::prefix| == 0 */,
				339		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				34		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				131		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-1(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[37] = createSerializationRule("NavigatingArgCS-1", 60,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				78		/* assert |NavigatingArgCS::prefix| == 0 */,
				336		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				337		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				339		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				164		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				130		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				34		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				131		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				189		/* V00*2-steps || value */,
				177		/* 'with' || value */,
				25		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 2/* CoIteratorVariableCS */,
					(7/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-2(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[38] = createSerializationRule("NavigatingArgCS-2", 60,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				78		/* assert |NavigatingArgCS::prefix| == 0 */,
				336		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				337		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				339		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				32		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				163		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				132		/* assign V0 = |NavigatingArgCS::ownedType| */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				199		/* V01*2-steps || value */,
				177		/* 'with' || value */,
				25		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				157		/* 'in' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 2/* CoIteratorVariableCS */,
					(7/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-3(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[39] = createSerializationRule("NavigatingArgCS-3", 60,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				77		/* assert |NavigatingArgCS::ownedType| == 0 */,
				78		/* assert |NavigatingArgCS::prefix| == 0 */,
				336		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				337		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				131		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				31		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				177		/* 'with' || value */,
				25		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				189		/* V00*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 2/* CoIteratorVariableCS */,
					(7/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-4(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
		serializationRules[40] = createSerializationRule("NavigatingArgCS-4", 60,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				75		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				77		/* assert |NavigatingArgCS::ownedType| == 0 */,
				78		/* assert |NavigatingArgCS::prefix| == 0 */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingBarArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[41] = createSerializationRule("NavigatingBarArgCS-0", 62,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				337		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				339		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				132		/* assign V0 = |NavigatingArgCS::ownedType| */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				37		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				164		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				105		/* NavigatingArgCS::prefix='|' || soft-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				193		/* V00*5-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(22/*'|'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[42] = createSerializationRule("NavigatingCommaArgCS-0", 63,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				336		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				337		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				339		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				164		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				130		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				34		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				35		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				107		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				131		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				189		/* V00*2-steps || value */,
				177		/* 'with' || value */,
				25		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(5/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 2/* CoIteratorVariableCS */,
					(7/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-1(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[43] = createSerializationRule("NavigatingCommaArgCS-1", 63,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				336		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				337		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				339		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				32		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				163		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				132		/* assign V0 = |NavigatingArgCS::ownedType| */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				35		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				107		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				199		/* V01*2-steps || value */,
				177		/* 'with' || value */,
				25		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				157		/* 'in' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(5/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 2/* CoIteratorVariableCS */,
					(7/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-2(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[44] = createSerializationRule("NavigatingCommaArgCS-2", 63,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				77		/* assert |NavigatingArgCS::ownedType| == 0 */,
				336		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				337		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				131		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				31		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				35		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				107		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				177		/* 'with' || value */,
				25		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				189		/* V00*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(5/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 2/* CoIteratorVariableCS */,
					(7/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-3(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS }
		serializationRules[45] = createSerializationRule("NavigatingCommaArgCS-3", 63,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				75		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				77		/* assert |NavigatingArgCS::ownedType| == 0 */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				35		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				107		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(5/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingSemiArgCS-0(essentialoclcs::NavigatingArgCS): { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[46] = createSerializationRule("NavigatingSemiArgCS-0", 64,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				337		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				338		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				339		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				132		/* assign V0 = |NavigatingArgCS::ownedType| */,
				33		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				36		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				164		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				106		/* NavigatingArgCS::prefix=';' || no-space value soft-new-line */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				193		/* V00*5-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(7/*';'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/* NavigatingArgExpCS */,
					(61/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NestedExpCS-0(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[47] = createSerializationRule("NestedExpCS-0", 66,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				340		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				38		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				123		/* '(' || value no-space */,
				41		/* NestedExpCS::ownedExpression=ExpCS || value */,
				125		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NullLiteralExpCS-0(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[48] = createSerializationRule("NullLiteralExpCS-0", 68,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				165		/* 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::NumberLiteralExpCS-0(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[49] = createSerializationRule("NumberLiteralExpCS-0", 69,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				39		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			),
			createSerializationSteps(
				115		/* NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::PatternExpCS-0(essentialoclcs::PatternExpCS): { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS }
		serializationRules[50] = createSerializationRule("PatternExpCS-0", 74,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				344		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : TypeExpCS */,
				43		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				135		/* assign V0 = |PatternExpCS::patternVariableName| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				104		/* PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				79		/* PatternExpCS::ownedPatternType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedLetExpCS-1(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		serializationRules[51] = createSerializationRule("PrefixedLetExpCS-1", 77,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				341		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : LetExpCS|PrefixedLetExpCS */,
				40		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				260		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				271		/* OperatorExpCS::ownedRight=PrefixedLetExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 33/* PrefixedLetExpCS */,
					(77/*PrefixedLetExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS-15(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[52] = createSerializationRule("PrefixedPrimaryExpCS-15", 78,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				343		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				40		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				260		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				272		/* OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 35/* PrefixedPrimaryExpCS */,
					(78/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrimitiveTypeCS-0(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		serializationRules[53] = createSerializationRule("PrimitiveTypeCS-0", 81,
			createSerializationMatchSteps(
				98		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				44		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				16		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::RoundBracketedClauseCS-0(essentialoclcs::RoundBracketedClauseCS): { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" }
		serializationRules[54] = createSerializationRule("RoundBracketedClauseCS-0", 84,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				124		/* '(' || no-space value no-space */,
				191		/* V00*3-steps || value */,
				265		/* RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value */,
				197		/* V01*1-steps || value */,
				266		/* RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value */,
				125		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 22/* NavigatingArgCS,NavigatingBarArgCS,NavigatingCommaArgCS,NavigatingSemiArgCS */,
					(60/*NavigatingArgCS*/ << 4) | 1 /*[?]*/,
					(62/*NavigatingBarArgCS*/ << 4) | 2 /*[*]*/,
					(63/*NavigatingCommaArgCS*/ << 4) | 2 /*[*]*/,
					(64/*NavigatingSemiArgCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::SelfExpCS-0(essentialoclcs::SelfExpCS): "self"
		serializationRules[55] = createSerializationRule("SelfExpCS-0", 89,
			createSerializationMatchSteps(
				88		/* assert |SelfExpCS::name| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				172		/* 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::ShadowPartCS-0(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[56] = createSerializationRule("ShadowPartCS-0", 90,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				345		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				45		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				46		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				110		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
				136		/* '=' || soft-space value soft-space */,
				50		/* ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 30/* ExpCS,PatternExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/,
					(74/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, -1
				)
			});
		// EssentialOCL::ShadowPartCS-1(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
		serializationRules[57] = createSerializationRule("ShadowPartCS-1", 90,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				89		/* assert |ShadowPartCS::referredProperty| == 0 */,
				346		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : StringLiteralExpCS */,
				45		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			),
			createSerializationSteps(
				51		/* ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 41/* StringLiteralExpCS */,
					(95/*StringLiteralExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SimplePathNameCS-0(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[58] = createSerializationRule("SimplePathNameCS-0", 91,
			createSerializationMatchSteps(
				282		/* check-rule basecs::PathNameCS.ownedPathElements : FirstPathElementCS */,
				42		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			),
			createSerializationSteps(
				268		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 10/* FirstPathElementCS */,
					(31/*FirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SquareBracketedClauseCS-0(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[59] = createSerializationRule("SquareBracketedClauseCS-0", 93,
			createSerializationMatchSteps(
				347		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				108		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			),
			createSerializationSteps(
				141		/* '[' || no-space value no-space */,
				91		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				190		/* V00*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				91		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				142		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::StringLiteralExpCS-0(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[60] = createSerializationRule("StringLiteralExpCS-0", 95,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				137		/* assign V0 = |StringLiteralExpCS::segments| */
			),
			createSerializationSteps(
				188		/* V00*1-steps || value */,
				112		/* StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, false, GrammarCardinality.ONE_OR_MORE)
			});
		// EssentialOCL::TupleLiteralExpCS-0(essentialoclcs::TupleLiteralExpCS): { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" }
		serializationRules[61] = createSerializationRule("TupleLiteralExpCS-0", 103,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				348		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS */,
				111		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				140		/* 'Tuple' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				71		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				190		/* V00*2-steps || value */,
				128		/* ',' || no-space value soft-new-line */,
				71		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 50/* TupleLiteralPartCS */,
					(104/*TupleLiteralPartCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::TupleLiteralPartCS-0(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[62] = createSerializationRule("TupleLiteralPartCS-0", 104,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				353		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				354		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				57		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				145		/* assign V0 = |VariableCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				100		/* VariableCS::ownedType=TypeExpCS || value */,
				136		/* '=' || soft-space value soft-space */,
				52		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TuplePartCS-0(basecs::TuplePartCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[63] = createSerializationRule("TuplePartCS-0", 105,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				97		/* assert |TypedElementCS::qualifiers| == 0 */,
				297		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				52		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				273		/* TypedElementCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
	}
	private void initSerializationRules1() {
		// EssentialOCL::TupleTypeCS-0(basecs::TupleTypeCS): { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] }
		serializationRules[64] = createSerializationRule("TupleTypeCS-0", 106,
			createSerializationMatchSteps(
				98		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				295		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				49		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				112		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				153		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				195		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				17		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				195		/* V00*7-steps || value */,
				124		/* '(' || no-space value no-space */,
				202		/* V01*4-steps || value */,
				72		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				72		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				125		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(10/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 51/* TuplePartCS */,
					(105/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeExpCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[65] = createSerializationRule("TypeExpCS-0", 107,
			createSerializationMatchSteps(
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				144		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				44		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				16		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[66] = createSerializationRule("TypeExpCS-1", 107,
			createSerializationMatchSteps(
				314		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				315		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				208		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				120		/* assign V0 = |CollectionTypeCS::ownedType| */,
				6		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				158		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				14		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				124		/* '(' || no-space value no-space */,
				96		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				196		/* V01*1-steps || value */,
				26		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				125		/* ')' || no-space value */,
				214		/* V02*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 53/* TypeExpWithoutMultiplicityCS */,
					(108/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[67] = createSerializationRule("TypeExpCS-2", 107,
			createSerializationMatchSteps(
				334		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				335		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				174		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				127		/* assign V0 = |MapTypeCS::ownedValueType| */,
				25		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				24		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				15		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				124		/* '(' || no-space value no-space */,
				54		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				129		/* ',' || no-space value soft-space */,
				102		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				125		/* ')' || no-space value */,
				196		/* V01*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(9/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-3(essentialoclcs::TypeNameExpCS): { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[68] = createSerializationRule("TypeExpCS-3", 107,
			createSerializationMatchSteps(
				350		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				351		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				352		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				208		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				140		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				51		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				171		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				76		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				193		/* V00*5-steps || value */,
				32		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				201		/* V01*3-steps || value */,
				181		/* '{' || soft-space value push soft-new-line */,
				78		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */,
				214		/* V02*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/* CurlyBracketedClauseCS */,
					(13/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-4(essentialoclcs::CollectionPatternCS): { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[69] = createSerializationRule("TypeExpCS-4", 107,
			createSerializationMatchSteps(
				62		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				312		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				313		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				208		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				119		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				149		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				5		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				95		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				181		/* '{' || soft-space value push soft-new-line */,
				194		/* V00*6-steps || value */,
				68		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				200		/* V01*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				68		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				127		/* '++' || soft-space value soft-space */,
				111		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				184		/* '}' || pop soft-new-line value soft-new-line */,
				214		/* V02*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 29/* PatternExpCS */,
					(74/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 4/* CollectionTypeCS */,
					(11/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeExpCS-5(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[70] = createSerializationRule("TypeExpCS-5", 107,
			createSerializationMatchSteps(
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				295		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				223		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				49		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				112		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				153		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				195		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				17		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				195		/* V00*7-steps || value */,
				124		/* '(' || no-space value no-space */,
				202		/* V01*4-steps || value */,
				72		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				72		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				125		/* ')' || no-space value */,
				222		/* V03*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(10/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 51/* TuplePartCS */,
					(105/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeLiteralExpCS-0(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[71] = createSerializationRule("TypeLiteralExpCS-0", 111,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				96		/* assert |TypeLiteralExpCS::ownedPathName| == 0 */,
				349		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS */,
				50		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				99		/* TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 55/* TypeLiteralWithMultiplicityCS */,
					(112/*TypeLiteralWithMultiplicityCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[72] = createSerializationRule("TypeLiteralWithMultiplicityCS-0", 112,
			createSerializationMatchSteps(
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				144		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				44		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				16		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[73] = createSerializationRule("TypeLiteralWithMultiplicityCS-1", 112,
			createSerializationMatchSteps(
				314		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				315		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				208		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				120		/* assign V0 = |CollectionTypeCS::ownedType| */,
				6		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				158		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				14		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				124		/* '(' || no-space value no-space */,
				96		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				196		/* V01*1-steps || value */,
				26		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				125		/* ')' || no-space value */,
				214		/* V02*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 53/* TypeExpWithoutMultiplicityCS */,
					(108/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[74] = createSerializationRule("TypeLiteralWithMultiplicityCS-2", 112,
			createSerializationMatchSteps(
				334		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				335		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				174		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				127		/* assign V0 = |MapTypeCS::ownedValueType| */,
				25		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				24		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				15		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				124		/* '(' || no-space value no-space */,
				54		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				129		/* ',' || no-space value soft-space */,
				102		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				125		/* ')' || no-space value */,
				196		/* V01*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(9/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-3(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[75] = createSerializationRule("TypeLiteralWithMultiplicityCS-3", 112,
			createSerializationMatchSteps(
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				295		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				223		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				49		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				112		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				153		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				195		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				17		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				195		/* V00*7-steps || value */,
				124		/* '(' || no-space value no-space */,
				202		/* V01*4-steps || value */,
				72		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				72		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				125		/* ')' || no-space value */,
				222		/* V03*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(10/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 51/* TuplePartCS */,
					(105/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeNameExpCS-0(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] }
		serializationRules[76] = createSerializationRule("TypeNameExpCS-0", 113,
			createSerializationMatchSteps(
				98		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				350		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				351		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				352		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				140		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				51		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				171		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				76		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				193		/* V00*5-steps || value */,
				32		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				201		/* V01*3-steps || value */,
				181		/* '{' || soft-space value push soft-new-line */,
				78		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/* CurlyBracketedClauseCS */,
					(13/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::URIFirstPathElementCS-0(basecs::PathElementWithURICS): referredElement=URI
		serializationRules[77] = createSerializationRule("URIFirstPathElementCS-0", 122,
			createSerializationMatchSteps(
				41		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				281		/* PathElementCS::referredElement=URI || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIFirstPathElementCS-1(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[78] = createSerializationRule("URIFirstPathElementCS-1", 122,
			createSerializationMatchSteps(
				41		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				283		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[79] = createSerializationRule("URIPathNameCS-0", 123,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				267		/* PathNameCS::ownedPathElements+=URIFirstPathElementCS || value */,
				190		/* V00*2-steps || value */,
				133		/* '::' || no-space value no-space */,
				269		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 62/* NextPathElementCS,URIFirstPathElementCS */,
					(67/*NextPathElementCS*/ << 4) | 2 /*[*]*/,
					(122/*URIFirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS-0(essentialoclcs::UnlimitedNaturalLiteralExpCS): "*"
		serializationRules[80] = createSerializationRule("UnlimitedNaturalLiteralExpCS-0", 125,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				126		/* '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// OCLinEcore::AnnotationCS-0(basecs::AnnotationCS): { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" }
		serializationRules[81] = createSerializationRule("AnnotationCS-0", 1,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				58		/* assert |AnnotationCS::ownedContents| == 0 */,
				59		/* assert |AnnotationCS::ownedReferences| == 0 */,
				267		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				129		/* assign V0 = |NamedElementCS::name| */,
				147		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				191		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				143		/* 'annotation' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				262		/* NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				204		/* V01*6-steps || value */,
				124		/* '(' || no-space value no-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				125		/* ')' || no-space value */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 6/* DetailCS */,
					(16/*DetailCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::AnnotationCS-1(basecs::AnnotationCS): { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] } "}" } }
		serializationRules[82] = createSerializationRule("AnnotationCS-1", 1,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				265		/* check-rule basecs::AnnotationCS.ownedContents : AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
				267		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				266		/* check-rule basecs::AnnotationCS.ownedReferences : ModelElementRefCS */,
				239		/* assign V5 = |AnnotationCS::ownedReferences| */,
				226		/* assign V4 = |AnnotationCS::ownedContents| */,
				218		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				129		/* assign V0 = |NamedElementCS::name| */,
				147		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				191		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				143		/* 'annotation' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				262		/* NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				204		/* V01*6-steps || value */,
				124		/* '(' || no-space value no-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				125		/* ')' || no-space value */,
				181		/* '{' || soft-space value push soft-new-line */,
				224		/* V03*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				229		/* V04*1-steps || value */,
				30		/* AnnotationCS::ownedContents+=ModelElementCS || value */,
				236		/* V05*1-steps || value */,
				83		/* AnnotationCS::ownedReferences+=ModelElementRefCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 3 /*[+]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, 17/* ModelElementCS */,
					(53/*ModelElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 6/* DetailCS */,
					(16/*DetailCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, 18/* ModelElementRefCS */,
					(54/*ModelElementRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::AnnotationCS-2(basecs::AnnotationCS): { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] } "}" } }
		serializationRules[83] = createSerializationRule("AnnotationCS-2", 1,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				265		/* check-rule basecs::AnnotationCS.ownedContents : AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
				267		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				266		/* check-rule basecs::AnnotationCS.ownedReferences : ModelElementRefCS */,
				239		/* assign V5 = |AnnotationCS::ownedReferences| */,
				226		/* assign V4 = |AnnotationCS::ownedContents| */,
				218		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				129		/* assign V0 = |NamedElementCS::name| */,
				147		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				191		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				143		/* 'annotation' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				262		/* NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				204		/* V01*6-steps || value */,
				124		/* '(' || no-space value no-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				125		/* ')' || no-space value */,
				181		/* '{' || soft-space value push soft-new-line */,
				223		/* V03*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				230		/* V04*1-steps || value */,
				30		/* AnnotationCS::ownedContents+=ModelElementCS || value */,
				236		/* V05*1-steps || value */,
				83		/* AnnotationCS::ownedReferences+=ModelElementRefCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, 17/* ModelElementCS */,
					(53/*ModelElementCS*/ << 4) | 3 /*[+]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 6/* DetailCS */,
					(16/*DetailCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, 18/* ModelElementRefCS */,
					(54/*ModelElementRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::AnnotationCS-3(basecs::AnnotationCS): { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] } "}" } }
		serializationRules[84] = createSerializationRule("AnnotationCS-3", 1,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				265		/* check-rule basecs::AnnotationCS.ownedContents : AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
				267		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				266		/* check-rule basecs::AnnotationCS.ownedReferences : ModelElementRefCS */,
				239		/* assign V5 = |AnnotationCS::ownedReferences| */,
				226		/* assign V4 = |AnnotationCS::ownedContents| */,
				218		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				129		/* assign V0 = |NamedElementCS::name| */,
				147		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				191		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				143		/* 'annotation' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				262		/* NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				204		/* V01*6-steps || value */,
				124		/* '(' || no-space value no-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				125		/* ')' || no-space value */,
				181		/* '{' || soft-space value push soft-new-line */,
				223		/* V03*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				229		/* V04*1-steps || value */,
				30		/* AnnotationCS::ownedContents+=ModelElementCS || value */,
				237		/* V05*1-steps || value */,
				83		/* AnnotationCS::ownedReferences+=ModelElementRefCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, 17/* ModelElementCS */,
					(53/*ModelElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 6/* DetailCS */,
					(16/*DetailCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, 18/* ModelElementRefCS */,
					(54/*ModelElementRefCS*/ << 4) | 3 /*[+]*/
				)
			});
		// OCLinEcore::AttributeCS-0(basecs::AttributeCS): { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[85] = createSerializationRule("AttributeCS-0", 3,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				91		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				205		/* assign V2 = |StructuralFeatureCS::default| */,
				172		/* assign V1 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				143		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				53		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				213		/* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				234		/* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				144		/* 'attribute' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				217		/* V02*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				228		/* V03*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				230		/* V04*1-steps || value */,
				276		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(1/*'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 0 /*[1]*/,
					(21/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::AttributeCS-1(basecs::AttributeCS): { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } }
		serializationRules[86] = createSerializationRule("AttributeCS-1", 3,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				286		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				262		/* assign V9 = |ModelElementCS::ownedAnnotations| */,
				205		/* assign V2 = |StructuralFeatureCS::default| */,
				172		/* assign V1 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				143		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				53		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				213		/* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				234		/* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
				241		/* assign V5 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				244		/* assign V6 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				251		/* assign V7 = 0 */,
				255		/* assign V8 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				144		/* 'attribute' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				217		/* V02*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				228		/* V03*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				230		/* V04*1-steps || value */,
				276		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				241		/* V05*5-steps || value */,
				148		/* 'derivation' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				242		/* V06*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				250		/* V07*5-steps || value */,
				159		/* 'initial' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				251		/* V08*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				256		/* V09*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(1/*'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 0 /*[1]*/,
					(21/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::AttributeCS-2(basecs::AttributeCS): { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[87] = createSerializationRule("AttributeCS-2", 3,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				91		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				205		/* assign V2 = |StructuralFeatureCS::default| */,
				172		/* assign V1 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				142		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				54		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				213		/* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				234		/* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				144		/* 'attribute' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				217		/* V02*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				228		/* V03*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				230		/* V04*1-steps || value */,
				276		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(1/*'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 1 /*[?]*/,
					(21/*'static'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::AttributeCS-3(basecs::AttributeCS): { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } }
		serializationRules[88] = createSerializationRule("AttributeCS-3", 3,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				286		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				262		/* assign V9 = |ModelElementCS::ownedAnnotations| */,
				205		/* assign V2 = |StructuralFeatureCS::default| */,
				172		/* assign V1 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				142		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				54		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				213		/* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				234		/* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
				241		/* assign V5 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				244		/* assign V6 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				251		/* assign V7 = 0 */,
				255		/* assign V8 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				144		/* 'attribute' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				217		/* V02*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				228		/* V03*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				230		/* V04*1-steps || value */,
				276		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				241		/* V05*5-steps || value */,
				148		/* 'derivation' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				242		/* V06*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				250		/* V07*5-steps || value */,
				159		/* 'initial' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				251		/* V08*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				256		/* V09*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(1/*'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 1 /*[?]*/,
					(21/*'static'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::AttributeCS-4(basecs::AttributeCS): { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[89] = createSerializationRule("AttributeCS-4", 3,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				91		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				168		/* assign V1 = |StructuralFeatureCS::default| */,
				141		/* assign V0 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				196		/* assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				222		/* assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				144		/* 'attribute' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				220		/* V02*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				224		/* V03*1-steps || value */,
				276		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(1/*'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::AttributeCS-5(basecs::AttributeCS): { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } }
		serializationRules[90] = createSerializationRule("AttributeCS-5", 3,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				286		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				256		/* assign V8 = |ModelElementCS::ownedAnnotations| */,
				168		/* assign V1 = |StructuralFeatureCS::default| */,
				141		/* assign V0 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				196		/* assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				222		/* assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
				231		/* assign V4 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				237		/* assign V5 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				246		/* assign V6 = 0 */,
				251		/* assign V7 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				144		/* 'attribute' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				220		/* V02*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				224		/* V03*1-steps || value */,
				276		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				234		/* V04*5-steps || value */,
				148		/* 'derivation' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				235		/* V05*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				245		/* V06*5-steps || value */,
				159		/* 'initial' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				246		/* V07*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				252		/* V08*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(1/*'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::DataTypeCS-0(basecs::DataTypeCS): { isPrimitive?="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" }
		serializationRules[91] = createSerializationRule("DataTypeCS-0", 15,
			createSerializationMatchSteps(
				65		/* assert |DataTypeCS::isSerializable| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				60		/* assert |ClassCS::ownedConstraints| == 0 */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				199		/* assign V2 = |ClassCS::instanceClassName| */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				214		/* assign V3 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				9		/* DataTypeCS::isPrimitive?='primitive' || soft-space value soft-space */,
				147		/* 'datatype' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				227		/* V03*3-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				120		/* '!serializable' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, false,
					(19/*'primitive'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::DataTypeCS-1(basecs::DataTypeCS): { isPrimitive?="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[92] = createSerializationRule("DataTypeCS-1", 15,
			createSerializationMatchSteps(
				65		/* assert |DataTypeCS::isSerializable| == 0 */,
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				268		/* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				240		/* assign V5 = |ClassCS::ownedConstraints| */,
				229		/* assign V4 = |ModelElementCS::ownedAnnotations| */,
				199		/* assign V2 = |ClassCS::instanceClassName| */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				214		/* assign V3 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				9		/* DataTypeCS::isPrimitive?='primitive' || soft-space value soft-space */,
				147		/* 'datatype' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				227		/* V03*3-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				120		/* '!serializable' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				180		/* '{' || soft-new-line value push soft-new-line */,
				229		/* V04*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				236		/* V05*1-steps || value */,
				29		/* ClassCS::ownedConstraints+=InvariantConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, false,
					(19/*'primitive'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/* InvariantConstraintCS */,
					(41/*InvariantConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::DataTypeCS-2(basecs::DataTypeCS): { isPrimitive?="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable?="serializable" "}" }[?] ";" }
		serializationRules[93] = createSerializationRule("DataTypeCS-2", 15,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				60		/* assert |ClassCS::ownedConstraints| == 0 */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				217		/* assign V3 = |DataTypeCS::isSerializable.'serializable'| */,
				199		/* assign V2 = |ClassCS::instanceClassName| */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				9		/* DataTypeCS::isPrimitive?='primitive' || soft-space value soft-space */,
				147		/* 'datatype' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				227		/* V03*3-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				10		/* DataTypeCS::isSerializable?='serializable' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, false,
					(19/*'primitive'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, false,
					(20/*'serializable'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::DataTypeCS-3(basecs::DataTypeCS): { isPrimitive?="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable?="serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[94] = createSerializationRule("DataTypeCS-3", 15,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				268		/* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				240		/* assign V5 = |ClassCS::ownedConstraints| */,
				229		/* assign V4 = |ModelElementCS::ownedAnnotations| */,
				217		/* assign V3 = |DataTypeCS::isSerializable.'serializable'| */,
				199		/* assign V2 = |ClassCS::instanceClassName| */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				9		/* DataTypeCS::isPrimitive?='primitive' || soft-space value soft-space */,
				147		/* 'datatype' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				227		/* V03*3-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				10		/* DataTypeCS::isSerializable?='serializable' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				180		/* '{' || soft-new-line value push soft-new-line */,
				229		/* V04*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				236		/* V05*1-steps || value */,
				29		/* ClassCS::ownedConstraints+=InvariantConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, false,
					(19/*'primitive'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, false,
					(20/*'serializable'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/* InvariantConstraintCS */,
					(41/*InvariantConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::DataTypeCS-4(basecs::DataTypeCS): { isPrimitive?="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" }
		serializationRules[95] = createSerializationRule("DataTypeCS-4", 15,
			createSerializationMatchSteps(
				65		/* assert |DataTypeCS::isSerializable| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				60		/* assert |ClassCS::ownedConstraints| == 0 */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				199		/* assign V2 = |ClassCS::instanceClassName| */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				214		/* assign V3 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				9		/* DataTypeCS::isPrimitive?='primitive' || soft-space value soft-space */,
				147		/* 'datatype' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				225		/* V03*2-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, false,
					(19/*'primitive'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::DataTypeCS-5(basecs::DataTypeCS): { isPrimitive?="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[96] = createSerializationRule("DataTypeCS-5", 15,
			createSerializationMatchSteps(
				65		/* assert |DataTypeCS::isSerializable| == 0 */,
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				268		/* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				240		/* assign V5 = |ClassCS::ownedConstraints| */,
				229		/* assign V4 = |ModelElementCS::ownedAnnotations| */,
				199		/* assign V2 = |ClassCS::instanceClassName| */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				214		/* assign V3 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				9		/* DataTypeCS::isPrimitive?='primitive' || soft-space value soft-space */,
				147		/* 'datatype' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				225		/* V03*2-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				180		/* '{' || soft-new-line value push soft-new-line */,
				229		/* V04*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				236		/* V05*1-steps || value */,
				29		/* ClassCS::ownedConstraints+=InvariantConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, false,
					(19/*'primitive'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/* InvariantConstraintCS */,
					(41/*InvariantConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::DetailCS-0(basecs::DetailCS): { name=(UnrestrictedName|SINGLE_QUOTED_STRING) "=" values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
		serializationRules[97] = createSerializationRule("DetailCS-0", 16,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				122		/* assign V0 = |DetailCS::values| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				262		/* NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				136		/* '=' || soft-space value soft-space */,
				187		/* V00*1-steps || value */,
				119		/* DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.DETAIL_CS__VALUES, false, GrammarCardinality.ZERO_OR_MORE)
			});
		// OCLinEcore::DocumentationCS-0(basecs::DocumentationCS): { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" }
		serializationRules[98] = createSerializationRule("DocumentationCS-0", 17,
			createSerializationMatchSteps(
				73		/* assert |NamedElementCS::name| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				267		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				123		/* assign V0 = |DocumentationCS::value| */,
				147		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				191		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				149		/* 'documentation' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				117		/* DocumentationCS::value=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				204		/* V01*6-steps || value */,
				124		/* '(' || no-space value no-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				125		/* ')' || no-space value */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 6/* DetailCS */,
					(16/*DetailCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::EnumerationCS-0(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" }
		serializationRules[99] = createSerializationRule("EnumerationCS-0", 21,
			createSerializationMatchSteps(
				66		/* assert |EnumerationCS::isSerializable| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				60		/* assert |ClassCS::ownedConstraints| == 0 */,
				67		/* assert |EnumerationCS::ownedLiterals| == 0 */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				157		/* assign V1 = |ClassCS::instanceClassName| */,
				139		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				197		/* assign V2 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				153		/* 'enum' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				219		/* V02*3-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				120		/* '!serializable' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::EnumerationCS-1(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[100] = createSerializationRule("EnumerationCS-1", 21,
			createSerializationMatchSteps(
				66		/* assert |EnumerationCS::isSerializable| == 0 */,
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				268		/* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
				271		/* check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				240		/* assign V5 = |ClassCS::ownedConstraints| */,
				228		/* assign V4 = |EnumerationCS::ownedLiterals| */,
				218		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				157		/* assign V1 = |ClassCS::instanceClassName| */,
				139		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				197		/* assign V2 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				153		/* 'enum' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				219		/* V02*3-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				120		/* '!serializable' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				180		/* '{' || soft-new-line value push soft-new-line */,
				223		/* V03*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				229		/* V04*1-steps || value */,
				57		/* EnumerationCS::ownedLiterals+=EnumerationLiteralCS || value */,
				236		/* V05*1-steps || value */,
				29		/* ClassCS::ownedConstraints+=InvariantConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/* InvariantConstraintCS */,
					(41/*InvariantConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 8/* EnumerationLiteralCS */,
					(22/*EnumerationLiteralCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::EnumerationCS-2(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable?="serializable" "}" }[?] ";" }
		serializationRules[101] = createSerializationRule("EnumerationCS-2", 21,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				60		/* assert |ClassCS::ownedConstraints| == 0 */,
				67		/* assert |EnumerationCS::ownedLiterals| == 0 */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				202		/* assign V2 = |EnumerationCS::isSerializable.'serializable'| */,
				157		/* assign V1 = |ClassCS::instanceClassName| */,
				139		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				153		/* 'enum' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				219		/* V02*3-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				11		/* EnumerationCS::isSerializable?='serializable' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, false,
					(20/*'serializable'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::EnumerationCS-3(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable?="serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[102] = createSerializationRule("EnumerationCS-3", 21,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				268		/* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
				271		/* check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				240		/* assign V5 = |ClassCS::ownedConstraints| */,
				228		/* assign V4 = |EnumerationCS::ownedLiterals| */,
				218		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				202		/* assign V2 = |EnumerationCS::isSerializable.'serializable'| */,
				157		/* assign V1 = |ClassCS::instanceClassName| */,
				139		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				153		/* 'enum' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				219		/* V02*3-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				11		/* EnumerationCS::isSerializable?='serializable' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				180		/* '{' || soft-new-line value push soft-new-line */,
				223		/* V03*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				229		/* V04*1-steps || value */,
				57		/* EnumerationCS::ownedLiterals+=EnumerationLiteralCS || value */,
				236		/* V05*1-steps || value */,
				29		/* ClassCS::ownedConstraints+=InvariantConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, false,
					(20/*'serializable'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/* InvariantConstraintCS */,
					(41/*InvariantConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 8/* EnumerationLiteralCS */,
					(22/*EnumerationLiteralCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::EnumerationCS-4(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" }
		serializationRules[103] = createSerializationRule("EnumerationCS-4", 21,
			createSerializationMatchSteps(
				66		/* assert |EnumerationCS::isSerializable| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				60		/* assert |ClassCS::ownedConstraints| == 0 */,
				67		/* assert |EnumerationCS::ownedLiterals| == 0 */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				157		/* assign V1 = |ClassCS::instanceClassName| */,
				139		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				197		/* assign V2 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				153		/* 'enum' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				217		/* V02*2-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::EnumerationCS-5(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[104] = createSerializationRule("EnumerationCS-5", 21,
			createSerializationMatchSteps(
				66		/* assert |EnumerationCS::isSerializable| == 0 */,
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				268		/* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
				271		/* check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				240		/* assign V5 = |ClassCS::ownedConstraints| */,
				228		/* assign V4 = |EnumerationCS::ownedLiterals| */,
				218		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				157		/* assign V1 = |ClassCS::instanceClassName| */,
				139		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				197		/* assign V2 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				153		/* 'enum' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				217		/* V02*2-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				180		/* '{' || soft-new-line value push soft-new-line */,
				223		/* V03*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				229		/* V04*1-steps || value */,
				57		/* EnumerationCS::ownedLiterals+=EnumerationLiteralCS || value */,
				236		/* V05*1-steps || value */,
				29		/* ClassCS::ownedConstraints+=InvariantConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/* InvariantConstraintCS */,
					(41/*InvariantConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 8/* EnumerationLiteralCS */,
					(22/*EnumerationLiteralCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::EnumerationLiteralCS-0(basecs::EnumerationLiteralCS): { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" }
		serializationRules[105] = createSerializationRule("EnumerationLiteralCS-0", 22,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				160		/* assign V1 = |EnumerationLiteralCS::value| */,
				124		/* assign V0 = |EnumerationLiteralCS::literal| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				163		/* 'literal' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				12		/* EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				118		/* EnumerationLiteralCS::value=SIGNED || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// OCLinEcore::EnumerationLiteralCS-1(basecs::EnumerationLiteralCS): { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } }
		serializationRules[106] = createSerializationRule("EnumerationLiteralCS-1", 22,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				203		/* assign V2 = |ModelElementCS::ownedAnnotations| */,
				160		/* assign V1 = |EnumerationLiteralCS::value| */,
				124		/* assign V0 = |EnumerationLiteralCS::literal| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				163		/* 'literal' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				12		/* EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				118		/* EnumerationLiteralCS::value=SIGNED || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				215		/* V02*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::EnumerationLiteralCS-2(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" }
		serializationRules[107] = createSerializationRule("EnumerationLiteralCS-2", 22,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				160		/* assign V1 = |EnumerationLiteralCS::value| */,
				124		/* assign V0 = |EnumerationLiteralCS::literal| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				263		/* NamedElementCS::name=EnumerationLiteralName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				12		/* EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				118		/* EnumerationLiteralCS::value=SIGNED || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// OCLinEcore::EnumerationLiteralCS-3(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } }
		serializationRules[108] = createSerializationRule("EnumerationLiteralCS-3", 22,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				203		/* assign V2 = |ModelElementCS::ownedAnnotations| */,
				160		/* assign V1 = |EnumerationLiteralCS::value| */,
				124		/* assign V0 = |EnumerationLiteralCS::literal| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				263		/* NamedElementCS::name=EnumerationLiteralName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				12		/* EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				118		/* EnumerationLiteralCS::value=SIGNED || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				215		/* V02*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::ImplicitOppositeCS-0(basecs::ImplicitOppositeCS): { "opposite" name=UnrestrictedName ":" ownedType=TypedMultiplicityRefCS { "{" { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] "}" }[?] }
		serializationRules[109] = createSerializationRule("ImplicitOppositeCS-0", 37,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				52		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				114		/* assign V0 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
				173		/* assign V1 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				167		/* 'opposite' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				192		/* V00*4-steps || value */,
				181		/* '{' || soft-space value push soft-new-line */,
				198		/* V01*1-steps || value */,
				278		/* TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique' || soft-space value soft-space */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(3/*'!ordered|!unique|ordered|unique'*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::ImportCS-0(basecs::ImportCS): { {"import"|"library"} { name=UnrestrictedName ":" }[?] ownedPathName=URIPathNameCS isAll?="::*"[?] ";" }
		serializationRules[110] = createSerializationRule("ImportCS-0", 38,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				272		/* check-rule basecs::ImportCS.ownedPathName : URIPathNameCS */,
				161		/* assign V1 = |ImportCS::isAll.'::*'| */,
				17		/* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				129		/* assign V0 = |NamedElementCS::name| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				156		/* 'import' || value */,
				189		/* V00*2-steps || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				74		/* ImportCS::ownedPathName=URIPathNameCS || value */,
				196		/* V01*1-steps || value */,
				4		/* ImportCS::isAll?='::*' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, false,
					(6/*'::*'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 64/* URIPathNameCS */,
					(123/*URIPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::InvariantConstraintCS-0(oclinecorecs::OCLinEcoreConstraintCS): { isCallable?="callable"[?] stereotype="invariant" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ";" }
		serializationRules[111] = createSerializationRule("InvariantConstraintCS-0", 41,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				63		/* assert |ConstraintCS::ownedSpecification| == 0 */,
				269		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				162		/* assign V1 = |NamedElementCS::name| */,
				7		/* assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0 */,
				133		/* assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'| */,
				200		/* assign V2 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				5		/* OCLinEcoreConstraintCS::isCallable?='callable' || soft-space value soft-space */,
				284		/* ConstraintCS::stereotype='invariant' || soft-space value soft-space */,
				203		/* V01*5-steps || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				219		/* V02*3-steps || value */,
				124		/* '(' || no-space value no-space */,
				58		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				125		/* ')' || no-space value */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, false,
					(12/*'callable'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, false,
					(16/*'invariant'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::InvariantConstraintCS-1(oclinecorecs::OCLinEcoreConstraintCS): { isCallable?="callable"[?] stereotype="invariant" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] { ":" ownedSpecification=SpecificationCS[?] ";" } }
		serializationRules[112] = createSerializationRule("InvariantConstraintCS-1", 41,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				269		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				270		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				216		/* assign V3 = |ConstraintCS::ownedSpecification| */,
				162		/* assign V1 = |NamedElementCS::name| */,
				7		/* assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0 */,
				133		/* assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'| */,
				200		/* assign V2 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				5		/* OCLinEcoreConstraintCS::isCallable?='callable' || soft-space value soft-space */,
				284		/* ConstraintCS::stereotype='invariant' || soft-space value soft-space */,
				203		/* V01*5-steps || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				219		/* V02*3-steps || value */,
				124		/* '(' || no-space value no-space */,
				58		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				125		/* ')' || no-space value */,
				132		/* ':' || no-space value soft-space */,
				222		/* V03*1-steps || value */,
				87		/* ConstraintCS::ownedSpecification=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, false,
					(12/*'callable'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, false,
					(16/*'invariant'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::ModelElementRefCS-0(basecs::ModelElementRefCS): { "reference" ownedPathName=PathNameCS ";" }
		serializationRules[113] = createSerializationRule("ModelElementRefCS-0", 54,
			createSerializationMatchSteps(
				274		/* check-rule basecs::ModelElementRefCS.ownedPathName : PathNameCS */,
				26		/* assert (|ModelElementRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				171		/* 'reference' || soft-space value soft-space */,
				75		/* ModelElementRefCS::ownedPathName=PathNameCS || value */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::OperationCS-0(basecs::OperationCS): { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" }
		serializationRules[114] = createSerializationRule("OperationCS-0", 70,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				80		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				81		/* assert |OperationCS::ownedPostconditions| == 0 */,
				82		/* assert |OperationCS::ownedPreconditions| == 0 */,
				276		/* check-rule basecs::OperationCS.ownedExceptions : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				277		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				232		/* assign V4 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				143		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				53		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				236		/* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
				243		/* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
				193		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				210		/* assign V3 = (|OperationCS::ownedParameters| - 1) */,
				250		/* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				258		/* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				166		/* 'operation' || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				124		/* '(' || no-space value no-space */,
				220		/* V02*4-steps || value */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				226		/* V03*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				125		/* ')' || no-space value */,
				231		/* V04*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				240		/* V05*5-steps || value */,
				176		/* 'throws' || soft-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				243		/* V06*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				249		/* V07*4-steps || value */,
				178		/* '{' || soft-space value soft-new-line */,
				253		/* V08*1-steps || value */,
				277		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(2/*'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 0 /*[1]*/,
					(21/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 27/* ParameterCS */,
					(72/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::OperationCS-1(basecs::OperationCS): { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } }
		serializationRules[115] = createSerializationRule("OperationCS-1", 70,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				275		/* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
				276		/* check-rule basecs::OperationCS.ownedExceptions : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				277		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				278		/* check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS */,
				279		/* check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				190		/* assign V13 = |OperationCS::ownedPostconditions| */,
				187		/* assign V12 = |OperationCS::ownedPreconditions| */,
				182		/* assign V11 = |ModelElementCS::ownedAnnotations| */,
				232		/* assign V4 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				143		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				53		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				259		/* assign V9 = (|OperationCS::ownedBodyExpressions| > 0) */,
				180		/* assign V10 = |OperationCS::ownedBodyExpressions| */,
				236		/* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
				243		/* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
				193		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				210		/* assign V3 = (|OperationCS::ownedParameters| - 1) */,
				250		/* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				258		/* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				166		/* 'operation' || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				124		/* '(' || no-space value no-space */,
				220		/* V02*4-steps || value */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				226		/* V03*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				125		/* ')' || no-space value */,
				231		/* V04*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				240		/* V05*5-steps || value */,
				176		/* 'throws' || soft-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				243		/* V06*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				249		/* V07*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				253		/* V08*1-steps || value */,
				277		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				257		/* V09*5-steps || value */,
				145		/* 'body' || soft-space value soft-space */,
				132		/* ':' || no-space value soft-space */,
				205		/* V10*1-steps || value */,
				23		/* OperationCS::ownedBodyExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				209		/* V11*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				211		/* V12*1-steps || value */,
				81		/* OperationCS::ownedPreconditions+=PreconditionConstraintCS || value */,
				213		/* V13*1-steps || value */,
				80		/* OperationCS::ownedPostconditions+=PostconditionConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(2/*'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 0 /*[1]*/,
					(21/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 27/* ParameterCS */,
					(72/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 31/* PostconditionConstraintCS */,
					(75/*PostconditionConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 32/* PreconditionConstraintCS */,
					(76/*PreconditionConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::OperationCS-2(basecs::OperationCS): { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" }
		serializationRules[116] = createSerializationRule("OperationCS-2", 70,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				80		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				81		/* assert |OperationCS::ownedPostconditions| == 0 */,
				82		/* assert |OperationCS::ownedPreconditions| == 0 */,
				276		/* check-rule basecs::OperationCS.ownedExceptions : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				277		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				232		/* assign V4 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				142		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				54		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				236		/* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
				243		/* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
				193		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				210		/* assign V3 = (|OperationCS::ownedParameters| - 1) */,
				250		/* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				258		/* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				166		/* 'operation' || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				124		/* '(' || no-space value no-space */,
				220		/* V02*4-steps || value */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				226		/* V03*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				125		/* ')' || no-space value */,
				231		/* V04*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				240		/* V05*5-steps || value */,
				176		/* 'throws' || soft-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				243		/* V06*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				249		/* V07*4-steps || value */,
				178		/* '{' || soft-space value soft-new-line */,
				253		/* V08*1-steps || value */,
				277		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(2/*'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 1 /*[?]*/,
					(21/*'static'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 27/* ParameterCS */,
					(72/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::OperationCS-3(basecs::OperationCS): { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } }
		serializationRules[117] = createSerializationRule("OperationCS-3", 70,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				275		/* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
				276		/* check-rule basecs::OperationCS.ownedExceptions : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				277		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				278		/* check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS */,
				279		/* check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				190		/* assign V13 = |OperationCS::ownedPostconditions| */,
				187		/* assign V12 = |OperationCS::ownedPreconditions| */,
				182		/* assign V11 = |ModelElementCS::ownedAnnotations| */,
				232		/* assign V4 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				142		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				54		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				259		/* assign V9 = (|OperationCS::ownedBodyExpressions| > 0) */,
				180		/* assign V10 = |OperationCS::ownedBodyExpressions| */,
				236		/* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
				243		/* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
				193		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				210		/* assign V3 = (|OperationCS::ownedParameters| - 1) */,
				250		/* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				258		/* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				166		/* 'operation' || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				124		/* '(' || no-space value no-space */,
				220		/* V02*4-steps || value */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				226		/* V03*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				125		/* ')' || no-space value */,
				231		/* V04*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				240		/* V05*5-steps || value */,
				176		/* 'throws' || soft-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				243		/* V06*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				249		/* V07*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				253		/* V08*1-steps || value */,
				277		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				257		/* V09*5-steps || value */,
				145		/* 'body' || soft-space value soft-space */,
				132		/* ':' || no-space value soft-space */,
				205		/* V10*1-steps || value */,
				23		/* OperationCS::ownedBodyExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				209		/* V11*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				211		/* V12*1-steps || value */,
				81		/* OperationCS::ownedPreconditions+=PreconditionConstraintCS || value */,
				213		/* V13*1-steps || value */,
				80		/* OperationCS::ownedPostconditions+=PostconditionConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(2/*'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 1 /*[?]*/,
					(21/*'static'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 27/* ParameterCS */,
					(72/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 31/* PostconditionConstraintCS */,
					(75/*PostconditionConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 32/* PreconditionConstraintCS */,
					(76/*PreconditionConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::OperationCS-4(basecs::OperationCS): { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" }
		serializationRules[118] = createSerializationRule("OperationCS-4", 70,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				80		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				81		/* assert |OperationCS::ownedPostconditions| == 0 */,
				82		/* assert |OperationCS::ownedPreconditions| == 0 */,
				276		/* check-rule basecs::OperationCS.ownedExceptions : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				277		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				221		/* assign V3 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				139		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				245		/* assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				253		/* assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
				224		/* assign V4 = (|OperationCS::ownedExceptions| > 0) */,
				235		/* assign V5 = (|OperationCS::ownedExceptions| - 1) */,
				152		/* assign V1 = (|OperationCS::ownedParameters| > 0) */,
				192		/* assign V2 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				166		/* 'operation' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				124		/* '(' || no-space value no-space */,
				202		/* V01*4-steps || value */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				125		/* ')' || no-space value */,
				225		/* V03*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				233		/* V04*5-steps || value */,
				176		/* 'throws' || soft-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				238		/* V05*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				244		/* V06*4-steps || value */,
				178		/* '{' || soft-space value soft-new-line */,
				248		/* V07*1-steps || value */,
				277		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(2/*'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 27/* ParameterCS */,
					(72/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::OperationCS-5(basecs::OperationCS): { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } }
		serializationRules[119] = createSerializationRule("OperationCS-5", 70,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				275		/* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
				276		/* check-rule basecs::OperationCS.ownedExceptions : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				277		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				278		/* check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS */,
				279		/* check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				186		/* assign V12 = |OperationCS::ownedPostconditions| */,
				183		/* assign V11 = |OperationCS::ownedPreconditions| */,
				179		/* assign V10 = |ModelElementCS::ownedAnnotations| */,
				221		/* assign V3 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				139		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				254		/* assign V8 = (|OperationCS::ownedBodyExpressions| > 0) */,
				263		/* assign V9 = |OperationCS::ownedBodyExpressions| */,
				245		/* assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				253		/* assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
				224		/* assign V4 = (|OperationCS::ownedExceptions| > 0) */,
				235		/* assign V5 = (|OperationCS::ownedExceptions| - 1) */,
				152		/* assign V1 = (|OperationCS::ownedParameters| > 0) */,
				192		/* assign V2 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				166		/* 'operation' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				124		/* '(' || no-space value no-space */,
				202		/* V01*4-steps || value */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				65		/* OperationCS::ownedParameters+=ParameterCS || value */,
				125		/* ')' || no-space value */,
				225		/* V03*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				233		/* V04*5-steps || value */,
				176		/* 'throws' || soft-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				238		/* V05*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				36		/* OperationCS::ownedExceptions+=TypedRefCS || value */,
				244		/* V06*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				248		/* V07*1-steps || value */,
				277		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				254		/* V08*5-steps || value */,
				145		/* 'body' || soft-space value soft-space */,
				132		/* ':' || no-space value soft-space */,
				255		/* V09*1-steps || value */,
				23		/* OperationCS::ownedBodyExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				206		/* V10*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				209		/* V11*1-steps || value */,
				81		/* OperationCS::ownedPreconditions+=PreconditionConstraintCS || value */,
				211		/* V12*1-steps || value */,
				80		/* OperationCS::ownedPostconditions+=PostconditionConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(2/*'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 27/* ParameterCS */,
					(72/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 31/* PostconditionConstraintCS */,
					(75/*PostconditionConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 32/* PreconditionConstraintCS */,
					(76/*PreconditionConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::PackageCS-0(basecs::PackageCS): { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] ";" }
		serializationRules[120] = createSerializationRule("PackageCS-0", 71,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				83		/* assert |PackageCS::ownedClasses| == 0 */,
				84		/* assert |PackageOwnerCS::ownedPackages| == 0 */,
				165		/* assign V1 = |PackageCS::nsURI| */,
				134		/* assign V0 = |PackageCS::nsPrefix| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				168		/* 'package' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				18		/* PackageCS::nsPrefix=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				19		/* PackageCS::nsURI=URI || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PACKAGE_CS__NS_URI, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// OCLinEcore::PackageCS-1(basecs::PackageCS): { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] } "}" } }
		serializationRules[121] = createSerializationRule("PackageCS-1", 71,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				280		/* check-rule basecs::PackageCS.ownedClasses : ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */,
				281		/* check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS */,
				230		/* assign V4 = |PackageCS::ownedClasses| */,
				219		/* assign V3 = |PackageOwnerCS::ownedPackages| */,
				203		/* assign V2 = |ModelElementCS::ownedAnnotations| */,
				165		/* assign V1 = |PackageCS::nsURI| */,
				134		/* assign V0 = |PackageCS::nsPrefix| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				168		/* 'package' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				18		/* PackageCS::nsPrefix=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				19		/* PackageCS::nsURI=URI || soft-space value soft-space */,
				180		/* '{' || soft-new-line value push soft-new-line */,
				215		/* V02*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				223		/* V03*1-steps || value */,
				64		/* PackageOwnerCS::ownedPackages+=PackageCS || half-new-line value half-new-line */,
				229		/* V04*1-steps || value */,
				24		/* PackageCS::ownedClasses+=ClassCS || half-new-line value half-new-line */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PACKAGE_CS__NS_URI, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 1/* ClassCS */,
					(6/*ClassCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 26/* PackageCS */,
					(71/*PackageCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::ParameterCS-0(basecs::ParameterCS): { name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "{" { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] "}" }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" }[?] }
		serializationRules[122] = createSerializationRule("ParameterCS-0", 72,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				141		/* assign V0 = |TypedElementCS::ownedType| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				209		/* assign V3 = (|ModelElementCS::ownedAnnotations| > 0) */,
				229		/* assign V4 = |ModelElementCS::ownedAnnotations| */,
				155		/* assign V1 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
				207		/* assign V2 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				202		/* V01*4-steps || value */,
				181		/* '{' || soft-space value push soft-new-line */,
				216		/* V02*1-steps || value */,
				278		/* TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique' || soft-space value soft-space */,
				184		/* '}' || pop soft-new-line value soft-new-line */,
				228		/* V03*4-steps || value */,
				181		/* '{' || soft-space value push soft-new-line */,
				229		/* V04*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(3/*'!ordered|!unique|ordered|unique'*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::PostconditionConstraintCS-0(oclinecorecs::OCLinEcoreConstraintCS): { stereotype="postcondition" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS[?] ";" }
		serializationRules[123] = createSerializationRule("PostconditionConstraintCS-0", 75,
			createSerializationMatchSteps(
				79		/* assert |OCLinEcoreConstraintCS::isCallable| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				269		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				270		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				201		/* assign V2 = |ConstraintCS::ownedSpecification| */,
				129		/* assign V0 = |NamedElementCS::name| */,
				8		/* assert (|ConstraintCS::stereotype.'postcondition'| - 1) == 0 */,
				159		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				285		/* ConstraintCS::stereotype='postcondition' || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				201		/* V01*3-steps || value */,
				124		/* '(' || no-space value no-space */,
				58		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				125		/* ')' || no-space value */,
				132		/* ':' || no-space value soft-space */,
				214		/* V02*1-steps || value */,
				87		/* ConstraintCS::ownedSpecification=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, false,
					(17/*'postcondition'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::PreconditionConstraintCS-0(oclinecorecs::OCLinEcoreConstraintCS): { stereotype="precondition" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS[?] ";" }
		serializationRules[124] = createSerializationRule("PreconditionConstraintCS-0", 76,
			createSerializationMatchSteps(
				79		/* assert |OCLinEcoreConstraintCS::isCallable| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				269		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				270		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				201		/* assign V2 = |ConstraintCS::ownedSpecification| */,
				129		/* assign V0 = |NamedElementCS::name| */,
				9		/* assert (|ConstraintCS::stereotype.'precondition'| - 1) == 0 */,
				159		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				286		/* ConstraintCS::stereotype='precondition' || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				201		/* V01*3-steps || value */,
				124		/* '(' || no-space value no-space */,
				58		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				125		/* ')' || no-space value */,
				132		/* ':' || no-space value soft-space */,
				214		/* V02*1-steps || value */,
				87		/* ConstraintCS::ownedSpecification=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, false,
					(18/*'precondition'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::ReferenceCS-0(basecs::ReferenceCS): { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[125] = createSerializationRule("ReferenceCS-0", 83,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				91		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				85		/* assert |ReferenceCS::ownedImplicitOpposites| == 0 */,
				86		/* assert |ReferenceCS::referredKeys| == 0 */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				220		/* assign V3 = |StructuralFeatureCS::default| */,
				206		/* assign V2 = |TypedElementCS::ownedType| */,
				166		/* assign V1 = |ReferenceCS::referredOpposite| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				143		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				53		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				225		/* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				242		/* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				170		/* 'property' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				121		/* '#' || no-space value no-space */,
				109		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				225		/* V03*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				232		/* V04*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				237		/* V05*1-steps || value */,
				275		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(0/*'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 0 /*[1]*/,
					(21/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, -1
				)
			});
		// OCLinEcore::ReferenceCS-1(basecs::ReferenceCS): { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } }
		serializationRules[126] = createSerializationRule("ReferenceCS-1", 83,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				286		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
				284		/* check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				189		/* assign V13 = |ModelElementCS::ownedAnnotations| */,
				188		/* assign V12 = |ReferenceCS::ownedImplicitOpposites| */,
				220		/* assign V3 = |StructuralFeatureCS::default| */,
				206		/* assign V2 = |TypedElementCS::ownedType| */,
				166		/* assign V1 = |ReferenceCS::referredOpposite| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				143		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				53		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				177		/* assign V10 = (|ReferenceCS::referredKeys| > 0) */,
				181		/* assign V11 = (|ReferenceCS::referredKeys| - 1) */,
				225		/* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				242		/* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				247		/* assign V6 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				249		/* assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				255		/* assign V8 = 0 */,
				261		/* assign V9 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				170		/* 'property' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				121		/* '#' || no-space value no-space */,
				109		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				225		/* V03*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				232		/* V04*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				237		/* V05*1-steps || value */,
				275		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				245		/* V06*5-steps || value */,
				148		/* 'derivation' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				246		/* V07*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				254		/* V08*5-steps || value */,
				159		/* 'initial' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				255		/* V09*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				208		/* V10*6-steps || value */,
				161		/* 'key' || soft-space value soft-space */,
				108		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
				210		/* V11*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				108		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */,
				212		/* V12*2-steps || value */,
				46		/* ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				213		/* V13*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(0/*'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 0 /*[1]*/,
					(21/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, 11/* ImplicitOppositeCS */,
					(37/*ImplicitOppositeCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, -1
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, -1
				)
			});
		// OCLinEcore::ReferenceCS-2(basecs::ReferenceCS): { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[127] = createSerializationRule("ReferenceCS-2", 83,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				91		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				85		/* assert |ReferenceCS::ownedImplicitOpposites| == 0 */,
				86		/* assert |ReferenceCS::referredKeys| == 0 */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				220		/* assign V3 = |StructuralFeatureCS::default| */,
				206		/* assign V2 = |TypedElementCS::ownedType| */,
				166		/* assign V1 = |ReferenceCS::referredOpposite| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				142		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				54		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				225		/* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				242		/* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				170		/* 'property' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				121		/* '#' || no-space value no-space */,
				109		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				225		/* V03*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				232		/* V04*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				237		/* V05*1-steps || value */,
				275		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(0/*'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 1 /*[?]*/,
					(21/*'static'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, -1
				)
			});
	}
	private void initSerializationRules2() {
		// OCLinEcore::ReferenceCS-3(basecs::ReferenceCS): { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } }
		serializationRules[128] = createSerializationRule("ReferenceCS-3", 83,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				286		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
				284		/* check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				189		/* assign V13 = |ModelElementCS::ownedAnnotations| */,
				188		/* assign V12 = |ReferenceCS::ownedImplicitOpposites| */,
				220		/* assign V3 = |StructuralFeatureCS::default| */,
				206		/* assign V2 = |TypedElementCS::ownedType| */,
				166		/* assign V1 = |ReferenceCS::referredOpposite| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				142		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				54		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				177		/* assign V10 = (|ReferenceCS::referredKeys| > 0) */,
				181		/* assign V11 = (|ReferenceCS::referredKeys| - 1) */,
				225		/* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				242		/* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				247		/* assign V6 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				249		/* assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				255		/* assign V8 = 0 */,
				261		/* assign V9 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				280		/* TypedElementCS::qualifiers+='static' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				279		/* TypedElementCS::qualifiers+='definition' || soft-space value soft-space */,
				170		/* 'property' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				121		/* '#' || no-space value no-space */,
				109		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
				217		/* V02*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				225		/* V03*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				232		/* V04*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				237		/* V05*1-steps || value */,
				275		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				245		/* V06*5-steps || value */,
				148		/* 'derivation' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				246		/* V07*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				254		/* V08*5-steps || value */,
				159		/* 'initial' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				255		/* V09*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				208		/* V10*6-steps || value */,
				161		/* 'key' || soft-space value soft-space */,
				108		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
				210		/* V11*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				108		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */,
				212		/* V12*2-steps || value */,
				46		/* ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				213		/* V13*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(0/*'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/,
					(13/*'definition'*/ << 4) | 1 /*[?]*/,
					(21/*'static'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, 11/* ImplicitOppositeCS */,
					(37/*ImplicitOppositeCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, -1
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, -1
				)
			});
		// OCLinEcore::ReferenceCS-4(basecs::ReferenceCS): { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[129] = createSerializationRule("ReferenceCS-4", 83,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				91		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				85		/* assert |ReferenceCS::ownedImplicitOpposites| == 0 */,
				86		/* assert |ReferenceCS::referredKeys| == 0 */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				205		/* assign V2 = |StructuralFeatureCS::default| */,
				172		/* assign V1 = |TypedElementCS::ownedType| */,
				136		/* assign V0 = |ReferenceCS::referredOpposite| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				212		/* assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				233		/* assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				170		/* 'property' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				121		/* '#' || no-space value no-space */,
				109		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				217		/* V02*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				228		/* V03*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				230		/* V04*1-steps || value */,
				275		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(0/*'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, -1
				)
			});
		// OCLinEcore::ReferenceCS-5(basecs::ReferenceCS): { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } }
		serializationRules[130] = createSerializationRule("ReferenceCS-5", 83,
			createSerializationMatchSteps(
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				286		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
				284		/* check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS */,
				298		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				185		/* assign V12 = |ModelElementCS::ownedAnnotations| */,
				184		/* assign V11 = |ReferenceCS::ownedImplicitOpposites| */,
				205		/* assign V2 = |StructuralFeatureCS::default| */,
				172		/* assign V1 = |TypedElementCS::ownedType| */,
				136		/* assign V0 = |ReferenceCS::referredOpposite| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				260		/* assign V9 = (|ReferenceCS::referredKeys| > 0) */,
				176		/* assign V10 = (|ReferenceCS::referredKeys| - 1) */,
				212		/* assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				233		/* assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				241		/* assign V5 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				244		/* assign V6 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				251		/* assign V7 = 0 */,
				255		/* assign V8 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				170		/* 'property' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				189		/* V00*2-steps || value */,
				121		/* '#' || no-space value no-space */,
				109		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
				199		/* V01*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				274		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				217		/* V02*2-steps || value */,
				136		/* '=' || soft-space value soft-space */,
				0		/* StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				228		/* V03*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				230		/* V04*1-steps || value */,
				275		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				241		/* V05*5-steps || value */,
				148		/* 'derivation' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				242		/* V06*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				250		/* V07*5-steps || value */,
				159		/* 'initial' || soft-space value soft-space */,
				131		/* ':' || soft-space value soft-space */,
				251		/* V08*1-steps || value */,
				33		/* StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				258		/* V09*6-steps || value */,
				161		/* 'key' || soft-space value soft-space */,
				108		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
				207		/* V10*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				108		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */,
				210		/* V11*2-steps || value */,
				46		/* ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				211		/* V12*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, false,
					(0/*'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 39/* SpecificationCS */,
					(92/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, 11/* ImplicitOppositeCS */,
					(37/*ImplicitOppositeCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 59/* TypedMultiplicityRefCS */,
					(116/*TypedMultiplicityRefCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, -1
				),
				createSerializationReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, -1
				)
			});
		// OCLinEcore::SpecificationCS-0(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS
		serializationRules[131] = createSerializationRule("SpecificationCS-0", 92,
			createSerializationMatchSteps(
				90		/* assert |SpecificationCS::exprString| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				318		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				11		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				40		/* ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(30/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::SpecificationCS-1(essentialoclcs::ExpSpecificationCS): exprString=UNQUOTED_STRING
		serializationRules[132] = createSerializationRule("SpecificationCS-1", 92,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				68		/* assert |ExpSpecificationCS::ownedExpression| == 0 */,
				47		/* assert (|SpecificationCS::exprString| - 1) == 0 */
			),
			createSerializationSteps(
				1		/* SpecificationCS::exprString=UNQUOTED_STRING || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, true, GrammarCardinality.ONE)
			});
		// OCLinEcore::StructuredClassCS-0(basecs::StructuredClassCS): { isAbstract?="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface?="interface"[?] "}" }[?] ";" }
		serializationRules[133] = createSerializationRule("StructuredClassCS-0", 97,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				60		/* assert |ClassCS::ownedConstraints| == 0 */,
				92		/* assert |StructuredClassCS::ownedMetaclass| == 0 */,
				93		/* assert |StructuredClassCS::ownedOperations| == 0 */,
				94		/* assert |StructuredClassCS::ownedProperties| == 0 */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				289		/* check-rule basecs::StructuredClassCS.ownedSuperTypes : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				227		/* assign V4 = |ClassCS::instanceClassName| */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				138		/* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
				238		/* assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0) */,
				248		/* assign V6 = |StructuredClassCS::isInterface.'interface'| */,
				194		/* assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
				211		/* assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				3		/* StructuredClassCS::isAbstract?='abstract' || soft-space value soft-space */,
				146		/* 'class' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				221		/* V02*5-steps || value */,
				154		/* 'extends' || soft-space value soft-space */,
				90		/* StructuredClassCS::ownedSuperTypes+=TypedRefCS || value */,
				226		/* V03*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				90		/* StructuredClassCS::ownedSuperTypes+=TypedRefCS || value */,
				231		/* V04*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				239		/* V05*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				242		/* V06*1-steps || value */,
				6		/* StructuredClassCS::isInterface?='interface' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, false,
					(11/*'abstract'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, false,
					(15/*'interface'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::StructuredClassCS-1(basecs::StructuredClassCS): { isAbstract?="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface?="interface"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[134] = createSerializationRule("StructuredClassCS-1", 97,
			createSerializationMatchSteps(
				92		/* assert |StructuredClassCS::ownedMetaclass| == 0 */,
				273		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				268		/* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
				287		/* check-rule basecs::StructuredClassCS.ownedOperations : OperationCS */,
				288		/* check-rule basecs::StructuredClassCS.ownedProperties : AttributeCS|ReferenceCS|StructuralFeatureCS */,
				294		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				289		/* check-rule basecs::StructuredClassCS.ownedSuperTypes : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				178		/* assign V10 = |ClassCS::ownedConstraints| */,
				264		/* assign V9 = |StructuredClassCS::ownedProperties| */,
				257		/* assign V8 = |StructuredClassCS::ownedOperations| */,
				252		/* assign V7 = |ModelElementCS::ownedAnnotations| */,
				227		/* assign V4 = |ClassCS::instanceClassName| */,
				170		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				30		/* assert (|NamedElementCS::name| - 1) == 0 */,
				138		/* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
				238		/* assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0) */,
				248		/* assign V6 = |StructuredClassCS::isInterface.'interface'| */,
				194		/* assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
				211		/* assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				3		/* StructuredClassCS::isAbstract?='abstract' || soft-space value soft-space */,
				146		/* 'class' || soft-space value soft-space */,
				261		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				196		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				221		/* V02*5-steps || value */,
				154		/* 'extends' || soft-space value soft-space */,
				90		/* StructuredClassCS::ownedSuperTypes+=TypedRefCS || value */,
				226		/* V03*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				90		/* StructuredClassCS::ownedSuperTypes+=TypedRefCS || value */,
				231		/* V04*2-steps || value */,
				131		/* ':' || soft-space value soft-space */,
				2		/* ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				239		/* V05*4-steps || value */,
				179		/* '{' || soft-space value soft-space */,
				242		/* V06*1-steps || value */,
				6		/* StructuredClassCS::isInterface?='interface' || soft-space value soft-space */,
				183		/* '}' || soft-space value soft-space */,
				180		/* '{' || soft-new-line value push soft-new-line */,
				247		/* V07*1-steps || value */,
				21		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				252		/* V08*1-steps || value */,
				63		/* StructuredClassCS::ownedOperations+=OperationCS || value */,
				256		/* V09*1-steps || value */,
				82		/* StructuredClassCS::ownedProperties+=StructuralFeatureCS || value */,
				206		/* V10*1-steps || value */,
				29		/* ClassCS::ownedConstraints+=InvariantConstraintCS || value */,
				185		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, false,
					(11/*'abstract'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, false,
					(15/*'interface'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/* AnnotationElementCS */,
					(2/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/* InvariantConstraintCS */,
					(41/*InvariantConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 25/* OperationCS */,
					(70/*OperationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 42/* StructuralFeatureCS */,
					(96/*StructuralFeatureCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 49/* TemplateSignatureCS */,
					(101/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 60/* TypedRefCS */,
					(117/*TypedRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::SysMLCS-0(oclinecorecs::SysMLCS): { "sysml" { "{" { ownedDetails+=DetailCS ";" }[*] "}" } }
		serializationRules[135] = createSerializationRule("SysMLCS-0", 98,
			createSerializationMatchSteps(
				73		/* assert |NamedElementCS::name| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				95		/* assert |SysMLCS::value| == 0 */,
				267		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				117		/* assign V0 = |AnnotationElementCS::ownedDetails| */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				173		/* 'sysml' || soft-space value soft-space */,
				181		/* '{' || soft-space value push soft-new-line */,
				190		/* V00*2-steps || value */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				134		/* ';' || no-space value soft-new-line */,
				184		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 6/* DetailCS */,
					(16/*DetailCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::SysMLCS-1(oclinecorecs::SysMLCS): { "sysml" { ownedDetails+=DetailCS ";" } }
		serializationRules[136] = createSerializationRule("SysMLCS-1", 98,
			createSerializationMatchSteps(
				73		/* assert |NamedElementCS::name| == 0 */,
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				95		/* assert |SysMLCS::value| == 0 */,
				267		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				1		/* assert (|AnnotationElementCS::ownedDetails| - 1) == 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				173		/* 'sysml' || soft-space value soft-space */,
				34		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				134		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 6/* DetailCS */,
					(16/*DetailCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::TemplateSignatureCS-0(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[137] = createSerializationRule("TemplateSignatureCS-0", 101,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				293		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				110		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				124		/* '(' || no-space value no-space */,
				66		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				190		/* V00*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				66		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				125		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 57/* TypeParameterCS */,
					(114/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// OCLinEcore::TemplateSignatureCS-1(basecs::TemplateSignatureCS): { "<" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ">" }
		serializationRules[138] = createSerializationRule("TemplateSignatureCS-1", 101,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				293		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				110		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				135		/* '<' || soft-space value soft-space */,
				66		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				190		/* V00*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				66		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				137		/* '>' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 57/* TypeParameterCS */,
					(114/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// OCLinEcore::TopLevelCS-0(oclinecorecs::TopLevelCS): { { "module" }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] }
		serializationRules[139] = createSerializationRule("TopLevelCS-0", 102,
			createSerializationMatchSteps(
				71		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				285		/* check-rule basecs::RootCS.ownedImports : ImportCS */,
				281		/* check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS */,
				204		/* assign V2 = |PackageOwnerCS::ownedPackages| */,
				167		/* assign V1 = |RootCS::ownedImports| */,
				115		/* assign V0 = 0 */
			),
			createSerializationSteps(
				259		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				186		/* V00*1-steps || value */,
				164		/* 'module' || soft-space value soft-space */,
				197		/* V01*1-steps || value */,
				47		/* RootCS::ownedImports+=ImportCS || value half-new-line */,
				215		/* V02*1-steps || value */,
				64		/* PackageOwnerCS::ownedPackages+=PackageCS || half-new-line value half-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/* ImportCS */,
					(38/*ImportCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 26/* PackageCS */,
					(71/*PackageCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[140] = createSerializationRule("TypedMultiplicityRefCS-0", 116,
			createSerializationMatchSteps(
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				144		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				44		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				16		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS-1(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[141] = createSerializationRule("TypedMultiplicityRefCS-1", 116,
			createSerializationMatchSteps(
				99		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				100		/* assert |TypedTypeRefCS::ownedBinding| == 0 */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				301		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				144		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				56		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				77		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */,
				186		/* V00*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS-2(basecs::TypedTypeRefCS): { { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[142] = createSerializationRule("TypedMultiplicityRefCS-2", 116,
			createSerializationMatchSteps(
				99		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				300		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				301		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				144		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				55		/* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				56		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				77		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */,
				124		/* '(' || no-space value no-space */,
				22		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				125		/* ')' || no-space value */,
				186		/* V00*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 47/* TemplateBindingCS */,
					(99/*TemplateBindingCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS-3(basecs::TypedTypeRefCS): { { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[143] = createSerializationRule("TypedMultiplicityRefCS-3", 116,
			createSerializationMatchSteps(
				99		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				300		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				301		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				144		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				55		/* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				56		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				77		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */,
				135		/* '<' || soft-space value soft-space */,
				22		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				137		/* '>' || soft-space value soft-space */,
				186		/* V00*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 47/* TemplateBindingCS */,
					(99/*TemplateBindingCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS-4(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[144] = createSerializationRule("TypedMultiplicityRefCS-4", 116,
			createSerializationMatchSteps(
				314		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				315		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				208		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				120		/* assign V0 = |CollectionTypeCS::ownedType| */,
				6		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				158		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				14		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				124		/* '(' || no-space value no-space */,
				96		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				196		/* V01*1-steps || value */,
				26		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				125		/* ')' || no-space value */,
				214		/* V02*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 53/* TypeExpWithoutMultiplicityCS */,
					(108/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS-5(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[145] = createSerializationRule("TypedMultiplicityRefCS-5", 116,
			createSerializationMatchSteps(
				334		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				335		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				174		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				127		/* assign V0 = |MapTypeCS::ownedValueType| */,
				25		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				24		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				15		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				193		/* V00*5-steps || value */,
				124		/* '(' || no-space value no-space */,
				54		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				129		/* ',' || no-space value soft-space */,
				102		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				125		/* ')' || no-space value */,
				196		/* V01*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(9/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 52/* TypeExpCS */,
					(107/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS-6(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[146] = createSerializationRule("TypedMultiplicityRefCS-6", 116,
			createSerializationMatchSteps(
				299		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				295		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				223		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				49		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				112		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				153		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				195		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				17		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				195		/* V00*7-steps || value */,
				124		/* '(' || no-space value no-space */,
				202		/* V01*4-steps || value */,
				72		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				218		/* V02*2-steps || value */,
				129		/* ',' || no-space value soft-space */,
				72		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				125		/* ')' || no-space value */,
				222		/* V03*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(10/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/* MultiplicityCS */,
					(56/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 51/* TuplePartCS */,
					(105/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLinEcore::TypedTypeRefCS-0(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } }
		serializationRules[147] = createSerializationRule("TypedTypeRefCS-0", 118,
			createSerializationMatchSteps(
				99		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				98		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				300		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				301		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				55		/* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				56		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				77		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */,
				124		/* '(' || no-space value no-space */,
				22		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				125		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 47/* TemplateBindingCS */,
					(99/*TemplateBindingCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::TypedTypeRefCS-1(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } }
		serializationRules[148] = createSerializationRule("TypedTypeRefCS-1", 118,
			createSerializationMatchSteps(
				99		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				98		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				300		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				301		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				55		/* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				56		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				77		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */,
				135		/* '<' || soft-space value soft-space */,
				22		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				137		/* '>' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 47/* TemplateBindingCS */,
					(99/*TemplateBindingCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLinEcore::TypedTypeRefCS-2(basecs::TypedTypeRefCS): ownedPathName=PathNameCS
		serializationRules[149] = createSerializationRule("TypedTypeRefCS-2", 118,
			createSerializationMatchSteps(
				99		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				100		/* assert |TypedTypeRefCS::ownedBinding| == 0 */,
				98		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				301		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				56		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				77		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 28/* PathNameCS */,
					(73/*PathNameCS*/ << 4) | 0 /*[1]*/
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
			SerializationSegment.VALUE /* value */,
			SerializationSegment.HALF_NEW_LINE /* half-new-line */
		};
		serializationSegments[4] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.VALUE /* value */,
			SerializationSegment.NO_SPACE /* no-space */
		};
		serializationSegments[5] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.HALF_NEW_LINE /* half-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.HALF_NEW_LINE /* half-new-line */
		};
		serializationSegments[6] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.NO_SPACE /* no-space */
		};
		serializationSegments[7] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[8] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[9] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */
		};
		serializationSegments[10] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[11] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[12] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[13] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[14] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[15] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[16] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[17] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[18] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[19] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[20] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[21] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[22] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSerializationSteps() {
		// 0: StructuralFeatureCS::default=SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[0] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, 87 /*SINGLE_QUOTED_STRING*/, 11);
		// 1: SpecificationCS::exprString=UNQUOTED_STRING || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[1] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, 119 /*UNQUOTED_STRING*/, 2);
		// 2: ClassCS::instanceClassName=SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[2] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, 87 /*SINGLE_QUOTED_STRING*/, 11);
		// 3: StructuredClassCS::isAbstract?='abstract' || soft-space value soft-space
		serializationSteps[3] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, 11 /* 'abstract' */, 11);
		// 4: ImportCS::isAll?='::*' || soft-space value soft-space
		serializationSteps[4] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, 6 /* '::*' */, 11);
		// 5: OCLinEcoreConstraintCS::isCallable?='callable' || soft-space value soft-space
		serializationSteps[5] = createSerializationStepAssignKeyword(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, 12 /* 'callable' */, 11);
		// 6: StructuredClassCS::isInterface?='interface' || soft-space value soft-space
		serializationSteps[6] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, 15 /* 'interface' */, 11);
		// 7: MultiplicityCS::isNullFree?='|1' || no-space value no-space
		serializationSteps[7] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 23 /* '|1' */, 6);
		// 8: AbstractNameExpCS::isPre?='@' || soft-space value soft-space
		serializationSteps[8] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 8 /* '@' */, 11);
		// 9: DataTypeCS::isPrimitive?='primitive' || soft-space value soft-space
		serializationSteps[9] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, 19 /* 'primitive' */, 11);
		// 10: DataTypeCS::isSerializable?='serializable' || soft-space value soft-space
		serializationSteps[10] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, 20 /* 'serializable' */, 11);
		// 11: EnumerationCS::isSerializable?='serializable' || soft-space value soft-space
		serializationSteps[11] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, 20 /* 'serializable' */, 11);
		// 12: EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[12] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, 87 /*SINGLE_QUOTED_STRING*/, 11);
		// 13: MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space
		serializationSteps[13] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 43 /*LOWER*/, 11);
		// 14: CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space
		serializationSteps[14] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 12 /*CollectionTypeIdentifier*/, 11);
		// 15: MapTypeCS::name='Map' || soft-space value soft-space
		serializationSteps[15] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 9 /* 'Map' */, 11);
		// 16: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space
		serializationSteps[16] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 82 /*PrimitiveTypeIdentifier*/, 11);
		// 17: TupleTypeCS::name='Tuple' || soft-space value soft-space
		serializationSteps[17] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 10 /* 'Tuple' */, 11);
		// 18: PackageCS::nsPrefix=UnrestrictedName || soft-space value soft-space
		serializationSteps[18] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, 128 /*UnrestrictedName*/, 11);
		// 19: PackageCS::nsURI=URI || soft-space value soft-space
		serializationSteps[19] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__NS_URI, 121 /*URI*/, 11);
		// 20: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[20] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 115 /*TypeRefCS*/, 2);
		// 21: ModelElementCS::ownedAnnotations+=AnnotationElementCS || value
		serializationSteps[21] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /*AnnotationElementCS*/, 0);
		// 22: TypedTypeRefCS::ownedBinding=TemplateBindingCS || value
		serializationSteps[22] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 99 /*TemplateBindingCS*/, 0);
		// 23: OperationCS::ownedBodyExpressions+=SpecificationCS || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 92 /*SpecificationCS*/, 0);
		// 24: PackageCS::ownedClasses+=ClassCS || half-new-line value half-new-line
		serializationSteps[24] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 6 /*ClassCS*/, 5);
		// 25: NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 7 /*CoIteratorVariableCS*/, 0);
		// 26: CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 56 /*MultiplicityCS*/, 0);
		// 27: IfExpCS::ownedCondition=ExpCS|PatternExpCS || value
		serializationSteps[27] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, -1, new int[] { 30/*ExpCS*/,74/*PatternExpCS*/}, 0);
		// 28: IfThenExpCS::ownedCondition=ExpCS || value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 30 /*ExpCS*/, 0);
		// 29: ClassCS::ownedConstraints+=InvariantConstraintCS || value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 41 /*InvariantConstraintCS*/, 0);
		// 30: AnnotationCS::ownedContents+=ModelElementCS || value
		serializationSteps[30] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, 53 /*ModelElementCS*/, 0);
		// 31: AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /*CurlyBracketedClauseCS*/, 0);
		// 32: TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /*CurlyBracketedClauseCS*/, 0);
		// 33: StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /*SpecificationCS*/, 0);
		// 34: AnnotationElementCS::ownedDetails+=DetailCS || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 16 /*DetailCS*/, 0);
		// 35: IfExpCS::ownedElseExpression=ExpCS || value
		serializationSteps[35] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 30 /*ExpCS*/, 0);
		// 36: OperationCS::ownedExceptions+=TypedRefCS || value
		serializationSteps[36] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 117 /*TypedRefCS*/, 0);
		// 37: CollectionLiteralPartCS::ownedExpression=ExpCS || value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 30 /*ExpCS*/, 0);
		// 38: CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[38] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 74 /*PatternExpCS*/, 2);
		// 39: ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[39] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 30 /*ExpCS*/, 2);
		// 40: ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 30 /*ExpCS*/, 2);
		// 41: NestedExpCS::ownedExpression=ExpCS || value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 30 /*ExpCS*/, 0);
		// 42: LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 30 /*ExpCS*/, 0);
		// 43: TypeParameterCS::ownedExtends+=TypedRefCS || value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 117 /*TypedRefCS*/, 0);
		// 44: WildcardTypeRefCS::ownedExtends=TypedRefCS || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 117 /*TypedRefCS*/, 0);
		// 45: IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 20 /*ElseIfThenExpCS*/, 0);
		// 46: ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, 37 /*ImplicitOppositeCS*/, 0);
		// 47: RootCS::ownedImports+=ImportCS || value half-new-line
		serializationSteps[47] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 38 /*ImportCS*/, 3);
		// 48: LetExpCS::ownedInExpression=ExpCS || value
		serializationSteps[48] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 30 /*ExpCS*/, 0);
		// 49: NavigatingArgCS::ownedInitExpression=ExpCS || value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 30 /*ExpCS*/, 0);
		// 50: ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value
		serializationSteps[50] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new int[] { 30/*ExpCS*/,74/*PatternExpCS*/}, 0);
		// 51: ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[51] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 95 /*StringLiteralExpCS*/, 2);
		// 52: VariableCS::ownedInitExpression=ExpCS || value
		serializationSteps[52] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 30 /*ExpCS*/, 0);
		// 53: MapLiteralPartCS::ownedKey=ExpCS || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 30 /*ExpCS*/, 0);
		// 54: MapTypeCS::ownedKeyType=TypeExpCS || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 107 /*TypeExpCS*/, 0);
		// 55: CollectionLiteralPartCS::ownedLastExpression=ExpCS || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 30 /*ExpCS*/, 0);
		// 56: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 78 /*PrefixedPrimaryExpCS*/, 0);
		// 57: EnumerationCS::ownedLiterals+=EnumerationLiteralCS || value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 22 /*EnumerationLiteralCS*/, 0);
		// 58: ConstraintCS::ownedMessageSpecification=SpecificationCS || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 92 /*SpecificationCS*/, 0);
		// 59: TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 56 /*MultiplicityCS*/, 0);
		// 60: TypedRefCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /*MultiplicityCS*/, 0);
		// 61: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 61 /*NavigatingArgExpCS*/, 0);
		// 62: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 61 /*NavigatingArgExpCS*/, 2);
		// 63: StructuredClassCS::ownedOperations+=OperationCS || value
		serializationSteps[63] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 70 /*OperationCS*/, 0);
		// 64: PackageOwnerCS::ownedPackages+=PackageCS || half-new-line value half-new-line
		serializationSteps[64] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 71 /*PackageCS*/, 5);
		// 65: OperationCS::ownedParameters+=ParameterCS || value
		serializationSteps[65] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 72 /*ParameterCS*/, 0);
		// 66: TemplateSignatureCS::ownedParameters+=TypeParameterCS || value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 114 /*TypeParameterCS*/, 0);
		// 67: CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value
		serializationSteps[67] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 9 /*CollectionLiteralPartCS*/, 0);
		// 68: CollectionPatternCS::ownedParts+=PatternExpCS || value
		serializationSteps[68] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 74 /*PatternExpCS*/, 0);
		// 69: CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value
		serializationSteps[69] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 90 /*ShadowPartCS*/, 0);
		// 70: MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value
		serializationSteps[70] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 50 /*MapLiteralPartCS*/, 0);
		// 71: TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value
		serializationSteps[71] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 104 /*TupleLiteralPartCS*/, 0);
		// 72: TupleTypeCS::ownedParts+=TuplePartCS || value
		serializationSteps[72] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 105 /*TuplePartCS*/, 0);
		// 73: AbstractNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[73] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 73 /*PathNameCS*/, 0);
		// 74: ImportCS::ownedPathName=URIPathNameCS || value
		serializationSteps[74] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 123 /*URIPathNameCS*/, 0);
		// 75: ModelElementRefCS::ownedPathName=PathNameCS || value
		serializationSteps[75] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, 73 /*PathNameCS*/, 0);
		// 76: TypeNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[76] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 73 /*PathNameCS*/, 0);
		// 77: TypedTypeRefCS::ownedPathName=PathNameCS || value
		serializationSteps[77] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 73 /*PathNameCS*/, 0);
		// 78: TypeNameExpCS::ownedPatternGuard=ExpCS || value
		serializationSteps[78] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 30 /*ExpCS*/, 0);
		// 79: PatternExpCS::ownedPatternType=TypeExpCS || value
		serializationSteps[79] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 107 /*TypeExpCS*/, 0);
		// 80: OperationCS::ownedPostconditions+=PostconditionConstraintCS || value
		serializationSteps[80] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 75 /*PostconditionConstraintCS*/, 0);
		// 81: OperationCS::ownedPreconditions+=PreconditionConstraintCS || value
		serializationSteps[81] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 76 /*PreconditionConstraintCS*/, 0);
		// 82: StructuredClassCS::ownedProperties+=StructuralFeatureCS || value
		serializationSteps[82] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 96 /*StructuralFeatureCS*/, 0);
		// 83: AnnotationCS::ownedReferences+=ModelElementRefCS || value
		serializationSteps[83] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, 54 /*ModelElementRefCS*/, 0);
		// 84: AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[84] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 84 /*RoundBracketedClauseCS*/, 0);
		// 85: LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[85] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 84 /*RoundBracketedClauseCS*/, 0);
		// 86: TemplateableElementCS::ownedSignature=TemplateSignatureCS || value
		serializationSteps[86] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 101 /*TemplateSignatureCS*/, 0);
		// 87: ConstraintCS::ownedSpecification=SpecificationCS || value
		serializationSteps[87] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 92 /*SpecificationCS*/, 0);
		// 88: AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value
		serializationSteps[88] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 93 /*SquareBracketedClauseCS*/, 0);
		// 89: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value
		serializationSteps[89] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 100 /*TemplateParameterSubstitutionCS*/, 0);
		// 90: StructuredClassCS::ownedSuperTypes+=TypedRefCS || value
		serializationSteps[90] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 117 /*TypedRefCS*/, 0);
		// 91: SquareBracketedClauseCS::ownedTerms+=ExpCS || value
		serializationSteps[91] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 30 /*ExpCS*/, 0);
		// 92: IfExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[92] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 30 /*ExpCS*/, 0);
		// 93: IfThenExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[93] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 30 /*ExpCS*/, 0);
		// 94: CollectionLiteralExpCS::ownedType=CollectionTypeCS || value
		serializationSteps[94] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 11 /*CollectionTypeCS*/, 0);
		// 95: CollectionPatternCS::ownedType=CollectionTypeCS || value
		serializationSteps[95] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 11 /*CollectionTypeCS*/, 0);
		// 96: CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value
		serializationSteps[96] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 108 /*TypeExpWithoutMultiplicityCS*/, 0);
		// 97: MapLiteralExpCS::ownedType=MapTypeCS || value
		serializationSteps[97] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 51 /*MapTypeCS*/, 0);
		// 98: NavigatingArgCS::ownedType=TypeExpCS || value
		serializationSteps[98] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 107 /*TypeExpCS*/, 0);
		// 99: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[99] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 112 /*TypeLiteralWithMultiplicityCS*/, 2);
		// 100: VariableCS::ownedType=TypeExpCS || value
		serializationSteps[100] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 107 /*TypeExpCS*/, 0);
		// 101: MapLiteralPartCS::ownedValue=ExpCS || value
		serializationSteps[101] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 30 /*ExpCS*/, 0);
		// 102: MapTypeCS::ownedValueType=TypeExpCS || value
		serializationSteps[102] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 107 /*TypeExpCS*/, 0);
		// 103: LetExpCS::ownedVariables+=LetVariableCS || value
		serializationSteps[103] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 46 /*LetVariableCS*/, 0);
		// 104: PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space
		serializationSteps[104] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 128 /*UnrestrictedName*/, 11);
		// 105: NavigatingArgCS::prefix='|' || soft-space value soft-space
		serializationSteps[105] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 22 /* '|' */, 11);
		// 106: NavigatingArgCS::prefix=';' || no-space value soft-new-line
		serializationSteps[106] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 7 /* ';' */, 7);
		// 107: NavigatingArgCS::prefix=',' || no-space value soft-space
		serializationSteps[107] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 5 /* ',' */, 8);
		// 108: ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space
		serializationSteps[108] = createSerializationStepCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "UnrestrictedName"), 128, 11);
		// 109: ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space
		serializationSteps[109] = createSerializationStepCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, "UnrestrictedName"), 128, 11);
		// 110: ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[110] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 128, 11);
		// 111: CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space
		serializationSteps[111] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 35 /*Identifier*/, 11);
		// 112: StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[112] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 94 /*StringLiteral*/, 2);
		// 113: MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[113] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 4 /* '*|+|?' */, 11);
		// 114: BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[114] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 14 /* 'false|true' */, 2);
		// 115: NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[115] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 58 /*NUMBER_LITERAL*/, 2);
		// 116: MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space
		serializationSteps[116] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 120 /*UPPER*/, 11);
		// 117: DocumentationCS::value=SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[117] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, 87 /*SINGLE_QUOTED_STRING*/, 11);
		// 118: EnumerationLiteralCS::value=SIGNED || soft-space value soft-space
		serializationSteps[118] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, 85 /*SIGNED*/, 11);
		// 119: DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[119] = createSerializationStepAssigns(BaseCSPackage.Literals.DETAIL_CS__VALUES, -1, new int[] { 87/*SINGLE_QUOTED_STRING*/,48/*ML_SINGLE_QUOTED_STRING*/}, 11);
		// 120: '!serializable' || soft-space value soft-space
		serializationSteps[120] = createSerializationStepKeyword("!serializable", 11);
		// 121: '#' || no-space value no-space
		serializationSteps[121] = createSerializationStepKeyword("#", 6);
		// 122: '&&' || soft-space value soft-space
		serializationSteps[122] = createSerializationStepKeyword("&&", 11);
		// 123: '(' || value no-space
		serializationSteps[123] = createSerializationStepKeyword("(", 4);
		// 124: '(' || no-space value no-space
		serializationSteps[124] = createSerializationStepKeyword("(", 6);
		// 125: ')' || no-space value
		serializationSteps[125] = createSerializationStepKeyword(")", 1);
		// 126: '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[126] = createSerializationStepKeyword("*", 2);
		// 127: '++' || soft-space value soft-space
		serializationSteps[127] = createSerializationStepKeyword("++", 11);
		// 128: ',' || no-space value soft-new-line
		serializationSteps[128] = createSerializationStepKeyword(",", 7);
		// 129: ',' || no-space value soft-space
		serializationSteps[129] = createSerializationStepKeyword(",", 8);
		// 130: '..' || no-space value no-space
		serializationSteps[130] = createSerializationStepKeyword("..", 6);
		// 131: ':' || soft-space value soft-space
		serializationSteps[131] = createSerializationStepKeyword(":", 11);
		// 132: ':' || no-space value soft-space
		serializationSteps[132] = createSerializationStepKeyword(":", 8);
		// 133: '::' || no-space value no-space
		serializationSteps[133] = createSerializationStepKeyword("::", 6);
		// 134: ';' || no-space value soft-new-line
		serializationSteps[134] = createSerializationStepKeyword(";", 7);
		// 135: '<' || soft-space value soft-space
		serializationSteps[135] = createSerializationStepKeyword("<", 11);
		// 136: '=' || soft-space value soft-space
		serializationSteps[136] = createSerializationStepKeyword("=", 11);
		// 137: '>' || soft-space value soft-space
		serializationSteps[137] = createSerializationStepKeyword(">", 11);
		// 138: '?' || soft-space value soft-space
		serializationSteps[138] = createSerializationStepKeyword("?", 11);
		// 139: 'Lambda' || soft-space value soft-space
		serializationSteps[139] = createSerializationStepKeyword("Lambda", 11);
		// 140: 'Tuple' || soft-space value soft-space
		serializationSteps[140] = createSerializationStepKeyword("Tuple", 11);
		// 141: '[' || no-space value no-space
		serializationSteps[141] = createSerializationStepKeyword("[", 6);
		// 142: ']' || no-space value
		serializationSteps[142] = createSerializationStepKeyword("]", 1);
		// 143: 'annotation' || soft-space value soft-space
		serializationSteps[143] = createSerializationStepKeyword("annotation", 11);
		// 144: 'attribute' || soft-space value soft-space
		serializationSteps[144] = createSerializationStepKeyword("attribute", 11);
		// 145: 'body' || soft-space value soft-space
		serializationSteps[145] = createSerializationStepKeyword("body", 11);
		// 146: 'class' || soft-space value soft-space
		serializationSteps[146] = createSerializationStepKeyword("class", 11);
		// 147: 'datatype' || soft-space value soft-space
		serializationSteps[147] = createSerializationStepKeyword("datatype", 11);
		// 148: 'derivation' || soft-space value soft-space
		serializationSteps[148] = createSerializationStepKeyword("derivation", 11);
		// 149: 'documentation' || soft-space value soft-space
		serializationSteps[149] = createSerializationStepKeyword("documentation", 11);
		// 150: 'else' || soft-new-line pop value push soft-space
		serializationSteps[150] = createSerializationStepKeyword("else", 21);
		// 151: 'elseif' || soft-new-line pop soft-space value push soft-space
		serializationSteps[151] = createSerializationStepKeyword("elseif", 22);
		// 152: 'endif' || soft-new-line pop value soft-space
		serializationSteps[152] = createSerializationStepKeyword("endif", 15);
		// 153: 'enum' || soft-space value soft-space
		serializationSteps[153] = createSerializationStepKeyword("enum", 11);
		// 154: 'extends' || soft-space value soft-space
		serializationSteps[154] = createSerializationStepKeyword("extends", 11);
		// 155: 'if' || soft-new-line value push soft-space
		serializationSteps[155] = createSerializationStepKeyword("if", 17);
		// 156: 'import' || value
		serializationSteps[156] = createSerializationStepKeyword("import", 0);
		// 157: 'in' || soft-space value soft-space
		serializationSteps[157] = createSerializationStepKeyword("in", 11);
		// 158: 'in' || soft-space pop value soft-new-line
		serializationSteps[158] = createSerializationStepKeyword("in", 18);
		// 159: 'initial' || soft-space value soft-space
		serializationSteps[159] = createSerializationStepKeyword("initial", 11);
		// 160: 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[160] = createSerializationStepKeyword("invalid", 2);
		// 161: 'key' || soft-space value soft-space
		serializationSteps[161] = createSerializationStepKeyword("key", 11);
		// 162: 'let' || soft-space value push
		serializationSteps[162] = createSerializationStepKeyword("let", 9);
		// 163: 'literal' || soft-space value soft-space
		serializationSteps[163] = createSerializationStepKeyword("literal", 11);
		// 164: 'module' || soft-space value soft-space
		serializationSteps[164] = createSerializationStepKeyword("module", 11);
		// 165: 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[165] = createSerializationStepKeyword("null", 2);
		// 166: 'operation' || soft-space value soft-space
		serializationSteps[166] = createSerializationStepKeyword("operation", 11);
		// 167: 'opposite' || soft-space value soft-space
		serializationSteps[167] = createSerializationStepKeyword("opposite", 11);
		// 168: 'package' || soft-space value soft-space
		serializationSteps[168] = createSerializationStepKeyword("package", 11);
		// 169: 'pre' || soft-space value soft-space
		serializationSteps[169] = createSerializationStepKeyword("pre", 11);
		// 170: 'property' || soft-space value soft-space
		serializationSteps[170] = createSerializationStepKeyword("property", 11);
		// 171: 'reference' || soft-space value soft-space
		serializationSteps[171] = createSerializationStepKeyword("reference", 11);
		// 172: 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[172] = createSerializationStepKeyword("self", 2);
		// 173: 'sysml' || soft-space value soft-space
		serializationSteps[173] = createSerializationStepKeyword("sysml", 11);
		// 174: 'then' || pop value push soft-space
		serializationSteps[174] = createSerializationStepKeyword("then", 14);
		// 175: 'then' || pop soft-space value push soft-space
		serializationSteps[175] = createSerializationStepKeyword("then", 20);
		// 176: 'throws' || soft-space value soft-space
		serializationSteps[176] = createSerializationStepKeyword("throws", 11);
		// 177: 'with' || value
		serializationSteps[177] = createSerializationStepKeyword("with", 0);
		// 178: '{' || soft-space value soft-new-line
		serializationSteps[178] = createSerializationStepKeyword("{", 10);
		// 179: '{' || soft-space value soft-space
		serializationSteps[179] = createSerializationStepKeyword("{", 11);
		// 180: '{' || soft-new-line value push soft-new-line
		serializationSteps[180] = createSerializationStepKeyword("{", 16);
		// 181: '{' || soft-space value push soft-new-line
		serializationSteps[181] = createSerializationStepKeyword("{", 19);
		// 182: '|?' || no-space value no-space
		serializationSteps[182] = createSerializationStepKeyword("|?", 6);
		// 183: '}' || soft-space value soft-space
		serializationSteps[183] = createSerializationStepKeyword("}", 11);
		// 184: '}' || pop soft-new-line value soft-new-line
		serializationSteps[184] = createSerializationStepKeyword("}", 12);
		// 185: '}' || pop soft-space value soft-new-line
		serializationSteps[185] = createSerializationStepKeyword("}", 13);
		// 186: V00*1-steps || value
		serializationSteps[186] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 1, 0);
		// 187: V00*1-steps || value
		serializationSteps[187] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 1, 0);
		// 188: V00*1-steps || value
		serializationSteps[188] = createSerializationStepSequence((0/*V0*/ << 4) | 3/*[+]*/, 1, 0);
		// 189: V00*2-steps || value
		serializationSteps[189] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 190: V00*2-steps || value
		serializationSteps[190] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 2, 0);
		// 191: V00*3-steps || value
		serializationSteps[191] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 3, 0);
		// 192: V00*4-steps || value
		serializationSteps[192] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 4, 0);
		// 193: V00*5-steps || value
		serializationSteps[193] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 5, 0);
		// 194: V00*6-steps || value
		serializationSteps[194] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 6, 0);
		// 195: V00*7-steps || value
		serializationSteps[195] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 7, 0);
		// 196: V01*1-steps || value
		serializationSteps[196] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 1, 0);
		// 197: V01*1-steps || value
		serializationSteps[197] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 1, 0);
		// 198: V01*1-steps || value
		serializationSteps[198] = createSerializationStepSequence((1/*V1*/ << 4) | 3/*[+]*/, 1, 0);
		// 199: V01*2-steps || value
		serializationSteps[199] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 2, 0);
		// 200: V01*2-steps || value
		serializationSteps[200] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 2, 0);
		// 201: V01*3-steps || value
		serializationSteps[201] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 3, 0);
		// 202: V01*4-steps || value
		serializationSteps[202] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 4, 0);
		// 203: V01*5-steps || value
		serializationSteps[203] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 5, 0);
		// 204: V01*6-steps || value
		serializationSteps[204] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 6, 0);
		// 205: V10*1-steps || value
		serializationSteps[205] = createSerializationStepSequence((10/*V10*/ << 4) | 1/*[?]*/, 1, 0);
		// 206: V10*1-steps || value
		serializationSteps[206] = createSerializationStepSequence((10/*V10*/ << 4) | 2/*[*]*/, 1, 0);
		// 207: V10*2-steps || value
		serializationSteps[207] = createSerializationStepSequence((10/*V10*/ << 4) | 2/*[*]*/, 2, 0);
		// 208: V10*6-steps || value
		serializationSteps[208] = createSerializationStepSequence((10/*V10*/ << 4) | 2/*[*]*/, 6, 0);
		// 209: V11*1-steps || value
		serializationSteps[209] = createSerializationStepSequence((11/*V11*/ << 4) | 2/*[*]*/, 1, 0);
		// 210: V11*2-steps || value
		serializationSteps[210] = createSerializationStepSequence((11/*V11*/ << 4) | 2/*[*]*/, 2, 0);
		// 211: V12*1-steps || value
		serializationSteps[211] = createSerializationStepSequence((12/*V12*/ << 4) | 2/*[*]*/, 1, 0);
		// 212: V12*2-steps || value
		serializationSteps[212] = createSerializationStepSequence((12/*V12*/ << 4) | 2/*[*]*/, 2, 0);
		// 213: V13*1-steps || value
		serializationSteps[213] = createSerializationStepSequence((13/*V13*/ << 4) | 2/*[*]*/, 1, 0);
		// 214: V02*1-steps || value
		serializationSteps[214] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 1, 0);
		// 215: V02*1-steps || value
		serializationSteps[215] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 1, 0);
		// 216: V02*1-steps || value
		serializationSteps[216] = createSerializationStepSequence((2/*V2*/ << 4) | 3/*[+]*/, 1, 0);
		// 217: V02*2-steps || value
		serializationSteps[217] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 2, 0);
		// 218: V02*2-steps || value
		serializationSteps[218] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 2, 0);
		// 219: V02*3-steps || value
		serializationSteps[219] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 3, 0);
		// 220: V02*4-steps || value
		serializationSteps[220] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 4, 0);
		// 221: V02*5-steps || value
		serializationSteps[221] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 5, 0);
		// 222: V03*1-steps || value
		serializationSteps[222] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 1, 0);
		// 223: V03*1-steps || value
		serializationSteps[223] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 1, 0);
		// 224: V03*1-steps || value
		serializationSteps[224] = createSerializationStepSequence((3/*V3*/ << 4) | 3/*[+]*/, 1, 0);
		// 225: V03*2-steps || value
		serializationSteps[225] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 2, 0);
		// 226: V03*2-steps || value
		serializationSteps[226] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 2, 0);
		// 227: V03*3-steps || value
		serializationSteps[227] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 3, 0);
		// 228: V03*4-steps || value
		serializationSteps[228] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 4, 0);
		// 229: V04*1-steps || value
		serializationSteps[229] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 1, 0);
		// 230: V04*1-steps || value
		serializationSteps[230] = createSerializationStepSequence((4/*V4*/ << 4) | 3/*[+]*/, 1, 0);
		// 231: V04*2-steps || value
		serializationSteps[231] = createSerializationStepSequence((4/*V4*/ << 4) | 1/*[?]*/, 2, 0);
		// 232: V04*4-steps || value
		serializationSteps[232] = createSerializationStepSequence((4/*V4*/ << 4) | 1/*[?]*/, 4, 0);
		// 233: V04*5-steps || value
		serializationSteps[233] = createSerializationStepSequence((4/*V4*/ << 4) | 1/*[?]*/, 5, 0);
		// 234: V04*5-steps || value
		serializationSteps[234] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 5, 0);
		// 235: V05*1-steps || value
		serializationSteps[235] = createSerializationStepSequence((5/*V5*/ << 4) | 1/*[?]*/, 1, 0);
		// 236: V05*1-steps || value
		serializationSteps[236] = createSerializationStepSequence((5/*V5*/ << 4) | 2/*[*]*/, 1, 0);
		// 237: V05*1-steps || value
		serializationSteps[237] = createSerializationStepSequence((5/*V5*/ << 4) | 3/*[+]*/, 1, 0);
		// 238: V05*2-steps || value
		serializationSteps[238] = createSerializationStepSequence((5/*V5*/ << 4) | 2/*[*]*/, 2, 0);
		// 239: V05*4-steps || value
		serializationSteps[239] = createSerializationStepSequence((5/*V5*/ << 4) | 1/*[?]*/, 4, 0);
		// 240: V05*5-steps || value
		serializationSteps[240] = createSerializationStepSequence((5/*V5*/ << 4) | 1/*[?]*/, 5, 0);
		// 241: V05*5-steps || value
		serializationSteps[241] = createSerializationStepSequence((5/*V5*/ << 4) | 2/*[*]*/, 5, 0);
		// 242: V06*1-steps || value
		serializationSteps[242] = createSerializationStepSequence((6/*V6*/ << 4) | 1/*[?]*/, 1, 0);
		// 243: V06*2-steps || value
		serializationSteps[243] = createSerializationStepSequence((6/*V6*/ << 4) | 2/*[*]*/, 2, 0);
		// 244: V06*4-steps || value
		serializationSteps[244] = createSerializationStepSequence((6/*V6*/ << 4) | 1/*[?]*/, 4, 0);
		// 245: V06*5-steps || value
		serializationSteps[245] = createSerializationStepSequence((6/*V6*/ << 4) | 2/*[*]*/, 5, 0);
		// 246: V07*1-steps || value
		serializationSteps[246] = createSerializationStepSequence((7/*V7*/ << 4) | 1/*[?]*/, 1, 0);
		// 247: V07*1-steps || value
		serializationSteps[247] = createSerializationStepSequence((7/*V7*/ << 4) | 2/*[*]*/, 1, 0);
		// 248: V07*1-steps || value
		serializationSteps[248] = createSerializationStepSequence((7/*V7*/ << 4) | 3/*[+]*/, 1, 0);
		// 249: V07*4-steps || value
		serializationSteps[249] = createSerializationStepSequence((7/*V7*/ << 4) | 1/*[?]*/, 4, 0);
		// 250: V07*5-steps || value
		serializationSteps[250] = createSerializationStepSequence((7/*V7*/ << 4) | 2/*[*]*/, 5, 0);
		// 251: V08*1-steps || value
		serializationSteps[251] = createSerializationStepSequence((8/*V8*/ << 4) | 1/*[?]*/, 1, 0);
		// 252: V08*1-steps || value
		serializationSteps[252] = createSerializationStepSequence((8/*V8*/ << 4) | 2/*[*]*/, 1, 0);
		// 253: V08*1-steps || value
		serializationSteps[253] = createSerializationStepSequence((8/*V8*/ << 4) | 3/*[+]*/, 1, 0);
		// 254: V08*5-steps || value
		serializationSteps[254] = createSerializationStepSequence((8/*V8*/ << 4) | 2/*[*]*/, 5, 0);
		// 255: V09*1-steps || value
		serializationSteps[255] = createSerializationStepSequence((9/*V9*/ << 4) | 1/*[?]*/, 1, 0);
		// 256: V09*1-steps || value
		serializationSteps[256] = createSerializationStepSequence((9/*V9*/ << 4) | 2/*[*]*/, 1, 0);
		// 257: V09*5-steps || value
		serializationSteps[257] = createSerializationStepSequence((9/*V9*/ << 4) | 2/*[*]*/, 5, 0);
		// 258: V09*6-steps || value
		serializationSteps[258] = createSerializationStepSequence((9/*V9*/ << 4) | 2/*[*]*/, 6, 0);
		// 259: wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[259] = createSerializationStepWrapper(2);
		// 260: NamedElementCS::name=UnaryOperatorName || soft-space value soft-space
		serializationSteps[260] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 124 /*UnaryOperatorName*/, 11);
		// 261: NamedElementCS::name=UnrestrictedName || soft-space value soft-space
		serializationSteps[261] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 128 /*UnrestrictedName*/, 11);
		// 262: NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[262] = createSerializationStepAssigns(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, -1, new int[] { 128/*UnrestrictedName*/,87/*SINGLE_QUOTED_STRING*/}, 11);
		// 263: NamedElementCS::name=EnumerationLiteralName || soft-space value soft-space
		serializationSteps[263] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 23 /*EnumerationLiteralName*/, 11);
		// 264: NamedElementCS::name=BinaryOperatorName || soft-space value soft-space
		serializationSteps[264] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 4 /*BinaryOperatorName*/, 11);
		// 265: RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value
		serializationSteps[265] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 60 /*NavigatingArgCS*/, 0);
		// 266: RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value
		serializationSteps[266] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new int[] { 63/*NavigatingCommaArgCS*/,64/*NavigatingSemiArgCS*/,62/*NavigatingBarArgCS*/}, 0);
		// 267: PathNameCS::ownedPathElements+=URIFirstPathElementCS || value
		serializationSteps[267] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 122 /*URIFirstPathElementCS*/, 0);
		// 268: PathNameCS::ownedPathElements+=FirstPathElementCS || value
		serializationSteps[268] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 31 /*FirstPathElementCS*/, 0);
		// 269: PathNameCS::ownedPathElements+=NextPathElementCS || value
		serializationSteps[269] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 67 /*NextPathElementCS*/, 0);
		// 270: OperatorExpCS::ownedRight=ExpCS || value
		serializationSteps[270] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 30 /*ExpCS*/, 0);
		// 271: OperatorExpCS::ownedRight=PrefixedLetExpCS || value
		serializationSteps[271] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 77 /*PrefixedLetExpCS*/, 0);
		// 272: OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value
		serializationSteps[272] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 78 /*PrefixedPrimaryExpCS*/, 0);
		// 273: TypedElementCS::ownedType=TypeExpCS || value
		serializationSteps[273] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 107 /*TypeExpCS*/, 0);
		// 274: TypedElementCS::ownedType=TypedMultiplicityRefCS || value
		serializationSteps[274] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 116 /*TypedMultiplicityRefCS*/, 0);
		// 275: TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space
		serializationSteps[275] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */, 11);
		// 276: TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space
		serializationSteps[276] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */, 11);
		// 277: TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space
		serializationSteps[277] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */, 11);
		// 278: TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique' || soft-space value soft-space
		serializationSteps[278] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 3 /* '!ordered|!unique|ordered|unique' */, 11);
		// 279: TypedElementCS::qualifiers+='definition' || soft-space value soft-space
		serializationSteps[279] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 13 /* 'definition' */, 11);
		// 280: TypedElementCS::qualifiers+='static' || soft-space value soft-space
		serializationSteps[280] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 21 /* 'static' */, 11);
		// 281: PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[281] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 121, 11);
		// 282: PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[282] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 126, 11);
		// 283: PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[283] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 128, 11);
		// 284: ConstraintCS::stereotype='invariant' || soft-space value soft-space
		serializationSteps[284] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 16 /* 'invariant' */, 11);
		// 285: ConstraintCS::stereotype='postcondition' || soft-space value soft-space
		serializationSteps[285] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 17 /* 'postcondition' */, 11);
		// 286: ConstraintCS::stereotype='precondition' || soft-space value soft-space
		serializationSteps[286] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 18 /* 'precondition' */, 11);
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSubstringSteps() {
		// 0: '->' : [no-space, value, no-space]
		substringSteps[0] = createSubstringStep("->", 6 /* no-space, value, no-space */);
		// 1: '.' : [no-space, value, no-space]
		substringSteps[1] = createSubstringStep(".", 6 /* no-space, value, no-space */);
		// 2: '?->' : [no-space, value, no-space]
		substringSteps[2] = createSubstringStep("?->", 6 /* no-space, value, no-space */);
		// 3: '?.' : [no-space, value, no-space]
		substringSteps[3] = createSubstringStep("?.", 6 /* no-space, value, no-space */);
		// 4: 'else' : [soft-new-line, pop, value, push, soft-space]
		substringSteps[4] = createSubstringStep("else", 21 /* soft-new-line, pop, value, push, soft-space */);
		// 5: 'endif' : [soft-new-line, pop, value, soft-space]
		substringSteps[5] = createSubstringStep("endif", 15 /* soft-new-line, pop, value, soft-space */);
		// 6: 'if' : [soft-new-line, value, push, soft-space]
		substringSteps[6] = createSubstringStep("if", 17 /* soft-new-line, value, push, soft-space */);
		// 7: 'in' : [soft-space, pop, value, soft-new-line]
		substringSteps[7] = createSubstringStep("in", 18 /* soft-space, pop, value, soft-new-line */);
		// 8: 'let' : [soft-space, value, push]
		substringSteps[8] = createSubstringStep("let", 9 /* soft-space, value, push */);
		// 9: 'then' : [pop, soft-space, value, push, soft-space]
		substringSteps[9] = createSubstringStep("then", 20 /* pop, soft-space, value, push, soft-space */);
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
//	import EssentialOCLCSPackage;
//	import OCLinEcoreCSPackage;
//	import Grammar;
//	import GrammarProvider;
