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
package org.eclipse.ocl.xtext.oclstdlib.serializer;

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
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSPackage;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;

/******* This file is 100% auto-generated - do not edit it *******/

/**
 * The OCLstdlibSerializationMetaData singleton provides the metadata to support a
 * model to text serialization of a parsed Xtext semantic model or to re-format an Xtext text node model.
 */
public class OCLstdlibSerializationMetaData extends AbstractSerializationMetaData
{
	/**
	 * The Provider supports injected creation of the OCLstdlibSerializationMetaData singleton.
	 */
	public static class Provider implements SerializationMetaData.Provider
	{
		private static @Nullable OCLstdlibSerializationMetaData INSTANCE = null;

		@Inject
		private GrammarProvider grammarProvider;

		@Override
		public synchronized @NonNull SerializationMetaData get() {
			// synchronized synchronizes the creation of this singleton.
			// It does not imply that the overall application is threadsafe.
			OCLstdlibSerializationMetaData instance = INSTANCE;
			if (instance == null) {
				assert grammarProvider != null;
				Grammar grammar = grammarProvider.getGrammar(Provider.class);
				assert grammar != null;
				INSTANCE = instance = new OCLstdlibSerializationMetaData(grammar);
			}
			return instance;
		}
	}

	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[68];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[20];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[132];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[78];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[302];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[240];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[120];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [20] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[250];
	private final @NonNull SubstringStep @NonNull [] substringSteps = new @NonNull SubstringStep[10];
	private final @Nullable String @Nullable [] multipleLineCommentMidfixes = new @Nullable String[] {" *"};
	private final @NonNull String @Nullable [] multipleLineCommentPrefixes = new @NonNull String[] {"/*"};
	private final @NonNull String @Nullable [] multipleLineCommentSuffixes = new @NonNull String[] {"*/"};
	private final @NonNull String @Nullable [] singleLineCommentPrefixes = new @NonNull String[] {"--"};

	private OCLstdlibSerializationMetaData(@NonNull Grammar grammar) {
		super(grammar);
		initGrammarRuleVectors();
		initEnumerationValues();
		initMatchTerms();
		initMatchSteps();
		initSerializationSegments();
		initSerializationSteps();
		initSerializationRules0();
		initSerializationRules1();
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
		return 119;
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
		return 118;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 171;
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
				82 /* AnnotationCS-0: 'annotation' NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V1:*] ')')[V0:?] ';' */,
				83 /* AnnotationCS-1: 'annotation' NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V1:*] ')')[V0:?] '{' ModelElementCS::ownedAnnotations+=AnnotationElementCS '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					7) /* DetailCS */
			}
		);
		eClassValues[1] = new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
			createSerializationRules(
				17 /* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			), null
		);
		eClassValues[2] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
			createSerializationRules(
				19 /* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					4) /* CollectionLiteralPartCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					5) /* CollectionTypeCS */
			}
		);
		eClassValues[3] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
			createSerializationRules(
				21 /* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */,
				20 /* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[4] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
			createSerializationRules(
				22 /* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				69 /* TypeExpCS-4: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					36) /* PatternExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					5) /* CollectionTypeCS */
			}
		);
		eClassValues[5] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
			createSerializationRules(
				23 /* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				66 /* TypeExpCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				73 /* TypeLiteralWithMultiplicityCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					61) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[6] = new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
			createSerializationRules(
				35 /* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[7] = new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				24 /* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					48) /* ShadowPartCS */
			}
		);
		eClassValues[8] = new EClassValue(BaseCSPackage.Literals.DETAIL_CS,
			createSerializationRules(
				84 /* DetailCS-0: NamedElementCS::name=Name|SINGLE_QUOTED_STRING '=' (DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[V0:*] */
			), null
		);
		eClassValues[9] = new EClassValue(BaseCSPackage.Literals.DOCUMENTATION_CS,
			createSerializationRules(
				85 /* DocumentationCS-0: 'documentation' (DocumentationCS::value=SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					7) /* DetailCS */
			}
		);
		eClassValues[10] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
			createSerializationRules(
				111 /* SpecificationCS-0: ExpSpecificationCS::ownedExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[11] = new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
			createSerializationRules(
				27 /* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					9) /* ElseIfThenExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[12] = new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
			createSerializationRules(
				25 /* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[13] = new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
			createSerializationRules(
				86 /* ImportCS-0: 'import' (NamedElementCS::name=Identifier ':')[V0:?] ImportCS::ownedPathName=URIPathNameCS (ImportCS::isAll?='::*')[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					69) /* URIPathNameCS */
			}
		);
		eClassValues[14] = new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
			createSerializationRules(
				26 /* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					72) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[15] = new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
			createSerializationRules(
				28 /* InvalidLiteralExpCS-0: 'invalid' */
			), null
		);
		eClassValues[16] = new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
			createSerializationRules(
				29 /* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[17] = new EClassValue(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS,
			createSerializationRules(
				89 /* LambdaParameterCS-0: NamedElementCS::name=Identifier ':' 'Lambda' LambdaParameterCS::ownedContextType=TypedMultiplicityRefCS '(' (LambdaParameterCS::ownedParameters+=ParameterCS (',' LambdaParameterCS::ownedParameters+=ParameterCS)[V1:*])[V0:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_CONTEXT_TYPE,
					64) /* TypedMultiplicityRefCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_PARAMETERS,
					34) /* ParameterCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[18] = new EClassValue(BaseCSPackage.Literals.LAMBDA_TYPE_CS,
			createSerializationRules(
				90 /* LambdaTypeCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=TypedMultiplicityRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedMultiplicityRefCS */,
				113 /* TypedMultiplicityRefCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=TypedMultiplicityRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedMultiplicityRefCS (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
					64) /* TypedMultiplicityRefCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
					64) /* TypedMultiplicityRefCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
					64) /* TypedMultiplicityRefCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					54) /* TemplateSignatureCS */
			}
		);
		eClassValues[19] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
			createSerializationRules(
				30 /* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					16) /* LetVariableCS */
			}
		);
		eClassValues[20] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
			createSerializationRules(
				31 /* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					44) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					57) /* TypeExpCS */
			}
		);
		eClassValues[21] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_CLASS_CS,
			createSerializationRules(
				91 /* LibClassCS-0: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'type' NamedElementCS::name=AnyName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' LibClassCS::metaclassName=AnyName)[V2:?] ('conformsTo' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V4:*])[V3:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V5:?] '{' (StructuredClassCS::ownedOperations+=OperationCS)[V6:*] (StructuredClassCS::ownedProperties+=LibPropertyCS)[V7:*] (ClassCS::ownedConstraints+=InvCS)[V8:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					13) /* InvCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					32) /* LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					22) /* LibPropertyCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					54) /* TemplateSignatureCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					66) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[22] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_COERCION_CS,
			createSerializationRules(
				92 /* LibCoercionCS-0: 'coercion' NamedElementCS::name=Name '(' ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V0:?] ';' */,
				93 /* LibCoercionCS-1: 'coercion' NamedElementCS::name=Name '(' ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V0:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V1:*] (OperationCS::ownedPreconditions+=PostCS)[V2:*] (OperationCS::ownedPostconditions+=PreCS)[V3:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					39) /* PreCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					38) /* PostCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[23] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_CONSTRAINT_CS,
			createSerializationRules(
				87 /* InvCS-0: ConstraintCS::stereotype='inv' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS ';' */,
				107 /* PostCS-0: ConstraintCS::stereotype='post' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS ';' */,
				108 /* PreCS-0: ConstraintCS::stereotype='pre' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					49) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					49) /* SpecificationCS */
			}
		);
		eClassValues[24] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS,
			createSerializationRules(
				94 /* LibIterationCS-0: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=LambdaParameterCS (',' OperationCS::ownedParameters+=LambdaParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] ';' */,
				95 /* LibIterationCS-1: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=LambdaParameterCS (',' OperationCS::ownedParameters+=LambdaParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] (OperationCS::ownedPreconditions+=PostCS)[V9:*] (OperationCS::ownedPostconditions+=PreCS)[V10:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATOR,
					0) /* AccumulatorCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_TargetGrammarRuleVector(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
					14) /* IteratorCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					15) /* LambdaParameterCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					39) /* PreCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					38) /* PostCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					54) /* TemplateSignatureCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[25] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS,
			createSerializationRules(
				96 /* LibOperationCS-0: (LibOperationCS::isStatic?='static')[V0:?] 'operation' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibOperationCS::isValidating?='validating')[V4:?] (LibOperationCS::isInvalidating?='invalidating')[V5:?] ('precedence' '=' LibOperationCS::precedence=Name)[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] ';' */,
				97 /* LibOperationCS-1: (LibOperationCS::isStatic?='static')[V0:?] 'operation' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibOperationCS::isValidating?='validating')[V4:?] (LibOperationCS::isInvalidating?='invalidating')[V5:?] ('precedence' '=' LibOperationCS::precedence=Name)[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] '{' ('body' ':' OperationCS::ownedBodyExpressions+=SpecificationCS ';')[V8:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] (OperationCS::ownedPostconditions+=PostCS)[V10:*] (OperationCS::ownedPreconditions+=PreCS)[V11:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					49) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					34) /* ParameterCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					38) /* PostCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					39) /* PreCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					54) /* TemplateSignatureCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[26] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_OPPOSITE_CS,
			createSerializationRules(
				98 /* LibOppositeCS-0: 'opposite' NamedElementCS::name=Name ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[27] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS,
			createSerializationRules(
				99 /* LibPackageCS-0: 'library' NamedElementCS::name=Name (':' PackageCS::nsPrefix=Identifier '=' PackageCS::nsURI=URI)[V0:?] '{' ('precedence' (LibPackageCS::ownedPrecedences+=PrecedenceCS)[V2:+] ';')[V1:*] (PackageOwnerCS::ownedPackages+=PackageCS)[V3:*] (PackageCS::ownedClasses+=ClassCS)[V4:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V5:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					17) /* ClassCS|LibClassCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					33) /* PackageCS */,
				createEReference_TargetGrammarRuleVector(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES,
					40) /* PrecedenceCS */
			}
		);
		eClassValues[28] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS,
			createSerializationRules(
				102 /* LibPropertyCS-0: (LibPropertyCS::isStatic?='static')[V0:?] 'property' NamedElementCS::name=Name ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibPropertyCS::ownedOpposite=LibOppositeCS)[V1:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V2:?] ';' */,
				103 /* LibPropertyCS-1: (LibPropertyCS::isStatic?='static')[V0:?] 'property' NamedElementCS::name=Name ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibPropertyCS::ownedOpposite=LibOppositeCS)[V1:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_TargetGrammarRuleVector(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
					18) /* LibOppositeCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[29] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_ROOT_PACKAGE_CS,
			createSerializationRules(
				104 /* Library-0: (RootCS::ownedImports+=ImportCS ';')[V0:*] (PackageOwnerCS::ownedPackages+=LibPackageCS)[V1:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					12) /* ImportCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					19) /* LibPackageCS */
			}
		);
		eClassValues[30] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
			createSerializationRules(
				32 /* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					23) /* MapLiteralPartCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					24) /* MapTypeCS */
			}
		);
		eClassValues[31] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
			createSerializationRules(
				33 /* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[32] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
			createSerializationRules(
				34 /* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				67 /* TypeExpCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				74 /* TypeLiteralWithMultiplicityCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				114 /* TypedMultiplicityRefCS-1: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					57) /* TypeExpCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					57) /* TypeExpCS */
			}
		);
		eClassValues[33] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */,
				4 /* MultiplicityCS-2: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] ']' */,
				2 /* MultiplicityCS-0: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] '|?' ']' */,
				3 /* MultiplicityCS-1: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] MultiplicityCS::isNullFree?='|1' ']' */
			), null
		);
		eClassValues[34] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				7 /* MultiplicityCS-5: '[' MultiplicityStringCS::stringBounds='*|+|?' ']' */,
				5 /* MultiplicityCS-3: '[' MultiplicityStringCS::stringBounds='*|+|?' '|?' ']' */,
				6 /* MultiplicityCS-4: '[' MultiplicityStringCS::stringBounds='*|+|?' MultiplicityCS::isNullFree?='|1' ']' */,
				8 /* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			), null
		);
		eClassValues[35] = new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
			createSerializationRules(
				36 /* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					6) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					35) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					44) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					50) /* SquareBracketedClauseCS */
			}
		);
		eClassValues[36] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
			createSerializationRules(
				41 /* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				37 /* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				40 /* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				38 /* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				39 /* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				42 /* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */,
				46 /* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				45 /* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				43 /* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				44 /* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				47 /* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					3) /* CoIteratorVariableCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					75) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					57) /* TypeExpCS */
			}
		);
		eClassValues[37] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				48 /* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[38] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				49 /* NullLiteralExpCS-0: 'null' */
			), null
		);
		eClassValues[39] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				50 /* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[40] = new EClassValue(BaseCSPackage.Literals.PACKAGE_CS,
			createSerializationRules(
				105 /* PackageCS-0: 'package' NamedElementCS::name=Name (':' PackageCS::nsPrefix=Identifier '=' PackageCS::nsURI=URI)[V0:?] '{' (PackageOwnerCS::ownedPackages+=PackageCS)[V1:*] (PackageCS::ownedClasses+=ClassCS)[V2:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					17) /* ClassCS|LibClassCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					33) /* PackageCS */
			}
		);
		eClassValues[41] = new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
			createSerializationRules(
				81 /* AccumulatorCS-0: NamedElementCS::name=Identifier ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				88 /* IteratorCS-0: NamedElementCS::name=Identifier ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				106 /* ParameterCS-0: NamedElementCS::name=Identifier ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[42] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */,
				9 /* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */,
				78 /* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */,
				100 /* LibPathElementCS-0: PathElementCS::referredElement=Name */
			), null
		);
		eClassValues[43] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				77 /* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */
			), null
		);
		eClassValues[44] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				15 /* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				59 /* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */,
				79 /* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				101 /* LibPathNameCS-0: PathNameCS::ownedPathElements+=LibPathElementCS ('::' PathNameCS::ownedPathElements+=LibPathElementCS)[V0:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					68) /* FirstPathElementCS|LibPathElementCS|NextPathElementCS|URIFirstPathElementCS */
			}
		);
		eClassValues[45] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				51 /* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					57) /* TypeExpCS */
			}
		);
		eClassValues[46] = new EClassValue(OCLstdlibCSPackage.Literals.PRECEDENCE_CS,
			createSerializationRules(
				109 /* PrecedenceCS-0: 'left' ':' NamedElementCS::name=Name */,
				110 /* PrecedenceCS-1: PrecedenceCS::isRightAssociative?='right' ':' NamedElementCS::name=Name */
			), null
		);
		eClassValues[47] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				52 /* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				53 /* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					73) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[48] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
			createSerializationRules(
				54 /* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				65 /* TypeExpCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				72 /* TypeLiteralWithMultiplicityCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */
			}
		);
		eClassValues[49] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				55 /* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					28) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[50] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				56 /* SelfExpCS-0: 'self' */
			), null
		);
		eClassValues[51] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				58 /* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */,
				57 /* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[52] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				60 /* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[53] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				61 /* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			), null
		);
		eClassValues[54] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					53) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[55] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					77) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[56] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				13 /* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					62) /* TypeParameterCS */
			}
		);
		eClassValues[57] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				62 /* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					55) /* TupleLiteralPartCS */
			}
		);
		eClassValues[58] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
			createSerializationRules(
				63 /* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					57) /* TypeExpCS */
			}
		);
		eClassValues[59] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				112 /* TuplePartCS-0: NamedElementCS::name=Identifier ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[60] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
			createSerializationRules(
				64 /* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				70 /* TypeExpCS-5: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
				75 /* TypeLiteralWithMultiplicityCS-3: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
				115 /* TypedMultiplicityRefCS-2: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					56) /* TuplePartCS */
			}
		);
		eClassValues[61] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				71 /* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					60) /* TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[62] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
			createSerializationRules(
				68 /* TypeExpCS-3: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				76 /* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					6) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					35) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[63] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				14 /* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					66) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[64] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				118 /* TypedTypeRefCS-0: TypedTypeRefCS::isTypeof?='typeof' '(' TypedTypeRefCS::ownedPathName=LibPathNameCS ')' */,
				119 /* TypedTypeRefCS-1: TypedTypeRefCS::ownedPathName=LibPathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */,
				116 /* TypedMultiplicityRefCS-3: TypedTypeRefCS::isTypeof?='typeof' '(' TypedTypeRefCS::ownedPathName=LibPathNameCS ')' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				117 /* TypedMultiplicityRefCS-4: TypedTypeRefCS::ownedPathName=LibPathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					52) /* TemplateBindingCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					21) /* LibPathNameCS */
			}
		);
		eClassValues[65] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				80 /* UnlimitedNaturalLiteralExpCS-0: '*' */
			), null
		);
		eClassValues[66] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				18 /* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					57) /* TypeExpCS */
			}
		);
		eClassValues[67] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				16 /* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					66) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
			}
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// 0: '*|+|?'
		enumerationValues[0] = new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		// 1: ','
		enumerationValues[1] = new EnumerationValueSingle(",");
		// 2: '::*'
		enumerationValues[2] = new EnumerationValueSingle("::*");
		// 3: ';'
		enumerationValues[3] = new EnumerationValueSingle(";");
		// 4: '@'
		enumerationValues[4] = new EnumerationValueSingle("@");
		// 5: 'Lambda'
		enumerationValues[5] = new EnumerationValueSingle("Lambda");
		// 6: 'Map'
		enumerationValues[6] = new EnumerationValueSingle("Map");
		// 7: 'Tuple'
		enumerationValues[7] = new EnumerationValueSingle("Tuple");
		// 8: 'abstract'
		enumerationValues[8] = new EnumerationValueSingle("abstract");
		// 9: 'false|true'
		enumerationValues[9] = new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		// 10: 'inv'
		enumerationValues[10] = new EnumerationValueSingle("inv");
		// 11: 'invalidating'
		enumerationValues[11] = new EnumerationValueSingle("invalidating");
		// 12: 'post'
		enumerationValues[12] = new EnumerationValueSingle("post");
		// 13: 'pre'
		enumerationValues[13] = new EnumerationValueSingle("pre");
		// 14: 'right'
		enumerationValues[14] = new EnumerationValueSingle("right");
		// 15: 'static'
		enumerationValues[15] = new EnumerationValueSingle("static");
		// 16: 'typeof'
		enumerationValues[16] = new EnumerationValueSingle("typeof");
		// 17: 'validating'
		enumerationValues[17] = new EnumerationValueSingle("validating");
		// 18: '|'
		enumerationValues[18] = new EnumerationValueSingle("|");
		// 19: '|1'
		enumerationValues[19] = new EnumerationValueSingle("|1");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = createParserRuleValue(1, "AccumulatorCS", -1,
			createSerializationRules(
				81	/* AccumulatorCS-0: NamedElementCS::name=Identifier ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[2] = createParserRuleValue(2, "AnnotationCS", -1,
			createSerializationRules(
				82	/* AnnotationCS-0: 'annotation' NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V1:*] ')')[V0:?] ';' */,
				83	/* AnnotationCS-1: 'annotation' NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V1:*] ')')[V0:?] '{' ModelElementCS::ownedAnnotations+=AnnotationElementCS '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "annotation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=(Identifier|SINGLE_QUOTED_STRING) : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "AnnotationElementCS", 8 /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
			createSerializationRules(
				82	/* AnnotationCS-0: 'annotation' NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V1:*] ')')[V0:?] ';' */,
				83	/* AnnotationCS-1: 'annotation' NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V1:*] ')')[V0:?] '{' ModelElementCS::ownedAnnotations+=AnnotationElementCS '}' */,
				85	/* DocumentationCS-0: 'documentation' (DocumentationCS::value=SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] ';' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* AnnotationCS : [value] | [value] */,
			(0 << 16) | 0	/* DocumentationCS : [value] | [value] */
		);
		grammarRuleValues[4] = createDataTypeRuleValue(4, "AnyName", 10 /* [soft-space, value, soft-space] */,
			4	/* 'else' : [soft-new-line, pop, value, push, soft-space] */,
			5	/* 'endif' : [soft-new-line, pop, value, soft-space] */,
			6	/* 'if' : [soft-new-line, value, push, soft-space] */,
			7	/* 'in' : [soft-space, pop, value, soft-new-line] */,
			8	/* 'let' : [soft-space, value, push] */,
			9	/* 'then' : [pop, soft-space, value, push, soft-space] */);
		grammarRuleValues[5] = createDataTypeRuleValue(5, "BinaryOperatorName", 10 /* [soft-space, value, soft-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '?->' : [no-space, value, no-space] */,
			3	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[6] = createParserRuleValue(6, "BooleanLiteralExpCS", -1,
			createSerializationRules(
				17	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* symbol="true" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* symbol="false" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "ClassCS", 17 /* ClassCS|LibClassCS */,
			createSerializationRules(
				91	/* LibClassCS-0: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'type' NamedElementCS::name=AnyName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' LibClassCS::metaclassName=AnyName)[V2:?] ('conformsTo' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V4:*])[V3:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V5:?] '{' (StructuredClassCS::ownedOperations+=OperationCS)[V6:*] (StructuredClassCS::ownedProperties+=LibPropertyCS)[V7:*] (ClassCS::ownedConstraints+=InvCS)[V8:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */
			),
			(0 << 16) | 2	/* LibClassCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[8] = createParserRuleValue(8, "CoIteratorVariableCS", -1,
			createSerializationRules(
				18	/* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[9] = createParserRuleValue(9, "CollectionLiteralExpCS", -1,
			createSerializationRules(
				19	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[10] = createParserRuleValue(10, "CollectionLiteralPartCS", -1,
			createSerializationRules(
				20	/* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */,
				21	/* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedLastExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=PatternExpCS : [value] | [value] */
		);
		grammarRuleValues[11] = createParserRuleValue(11, "CollectionPatternCS", -1,
			createSerializationRules(
				22	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* "++" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* restVariableName=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[12] = createParserRuleValue(12, "CollectionTypeCS", -1,
			createSerializationRules(
				23	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* name=CollectionTypeIdentifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedCollectionMultiplicity=MultiplicityCS? : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[13] = createDataTypeRuleValue(13, "CollectionTypeIdentifier", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[14] = createParserRuleValue(14, "CurlyBracketedClauseCS", -1,
			createSerializationRules(
				24	/* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {CurlyBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[15] = new TerminalRuleValue(15, "DOUBLE_QUOTED_STRING");
		grammarRuleValues[16] = createParserRuleValue(16, "DetailCS", -1,
			createSerializationRules(
				84	/* DetailCS-0: NamedElementCS::name=Name|SINGLE_QUOTED_STRING '=' (DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[V0:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=(Name|SINGLE_QUOTED_STRING) : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)* : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "DocumentationCS", -1,
			createSerializationRules(
				85	/* DocumentationCS-0: 'documentation' (DocumentationCS::value=SINGLE_QUOTED_STRING)[V0:?] ('(' AnnotationElementCS::ownedDetails+=DetailCS (',' AnnotationElementCS::ownedDetails+=DetailCS)[V2:*] ')')[V1:?] ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {DocumentationCS} : [value] | [value] */,
			(0 << 16) | 10	/* "documentation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* value=SINGLE_QUOTED_STRING? : [value] | [soft-space, value, soft-space] */,
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
				25	/* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 19	/* "elseif" : [value] | [soft-new-line, pop, soft-space, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=ExpCS : [value] | [value] */,
			(0 << 16) | 12	/* "then" : [value] | [pop, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[21] = createDataTypeRuleValue(21, "EssentialOCLInfixOperatorName", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[22] = createDataTypeRuleValue(22, "EssentialOCLNavigationOperatorName", 6 /* [no-space, value, no-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '?->' : [no-space, value, no-space] */,
			3	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[23] = createDataTypeRuleValue(23, "EssentialOCLReservedKeyword", 10 /* [soft-space, value, soft-space] */,
			4	/* 'else' : [soft-new-line, pop, value, push, soft-space] */,
			5	/* 'endif' : [soft-new-line, pop, value, soft-space] */,
			6	/* 'if' : [soft-new-line, value, push, soft-space] */,
			7	/* 'in' : [soft-space, pop, value, soft-new-line] */,
			8	/* 'let' : [soft-space, value, push] */,
			9	/* 'then' : [pop, soft-space, value, push, soft-space] */);
		grammarRuleValues[24] = createDataTypeRuleValue(24, "EssentialOCLUnaryOperatorName", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[25] = createDataTypeRuleValue(25, "EssentialOCLUnreservedName", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[26] = createDataTypeRuleValue(26, "EssentialOCLUnrestrictedName", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[27] = createParserRuleValue(27, "ExpCS", 74 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				17	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				19	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				26	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				27	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				28	/* InvalidLiteralExpCS-0: 'invalid' */,
				29	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				30	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				32	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				36	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				48	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				49	/* NullLiteralExpCS-0: 'null' */,
				50	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				52	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				53	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				56	/* SelfExpCS-0: 'self' */,
				61	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				62	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				71	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* {InfixExpCS} : [value] | [value] */,
			(0 << 16) | 10	/* name=BinaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedLetExpCS : [value] | [value] */
		);
		grammarRuleValues[28] = createParserRuleValue(28, "FirstPathElementCS", -1,
			createSerializationRules(
				0	/* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 10	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[29] = createDataTypeRuleValue(29, "ID", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[30] = new TerminalRuleValue(30, "INT");
		grammarRuleValues[31] = createDataTypeRuleValue(31, "Identifier", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[32] = createParserRuleValue(32, "IfExpCS", -1,
			createSerializationRules(
				27	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 14	/* "if" : [value] | [soft-new-line, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 17	/* "then" : [value] | [pop, soft-space, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedIfThenExpressions+=ElseIfThenExpCS* : [value] | [value] */,
			(0 << 16) | 18	/* "else" : [value] | [soft-new-line, pop, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedElseExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 13	/* "endif" : [value] | [soft-new-line, pop, value, soft-space] */
		);
		grammarRuleValues[33] = createParserRuleValue(33, "ImportCS", -1,
			createSerializationRules(
				86	/* ImportCS-0: 'import' (NamedElementCS::name=Identifier ':')[V0:?] ImportCS::ownedPathName=URIPathNameCS (ImportCS::isAll?='::*')[V1:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "import" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=URIPathNameCS : [value] | [value] */,
			(0 << 16) | 10	/* isAll?="::*"? : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[34] = createDataTypeRuleValue(34, "InfixOperatorName", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[35] = createParserRuleValue(35, "InvCS", -1,
			createSerializationRules(
				87	/* InvCS-0: ConstraintCS::stereotype='inv' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* stereotype="inv" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[36] = createParserRuleValue(36, "InvalidLiteralExpCS", -1,
			createSerializationRules(
				28	/* InvalidLiteralExpCS-0: 'invalid' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {InvalidLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 10	/* "invalid" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[37] = createParserRuleValue(37, "IteratorCS", -1,
			createSerializationRules(
				88	/* IteratorCS-0: NamedElementCS::name=Identifier ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[38] = new TerminalRuleValue(38, "LETTER_CHARACTER");
		grammarRuleValues[39] = createDataTypeRuleValue(39, "LOWER", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[40] = createParserRuleValue(40, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				29	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "LambdaParameterCS", -1,
			createSerializationRules(
				89	/* LambdaParameterCS-0: NamedElementCS::name=Identifier ':' 'Lambda' LambdaParameterCS::ownedContextType=TypedMultiplicityRefCS '(' (LambdaParameterCS::ownedParameters+=ParameterCS (',' LambdaParameterCS::ownedParameters+=ParameterCS)[V1:*])[V0:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedContextType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[42] = createParserRuleValue(42, "LambdaTypeCS", -1,
			createSerializationRules(
				90	/* LambdaTypeCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=TypedMultiplicityRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedMultiplicityRefCS */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* name="Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedContextType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameterTypes+=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameterTypes+=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedResultType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[43] = createParserRuleValue(43, "LetExpCS", -1,
			createSerializationRules(
				30	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 9	/* "let" : [value] | [soft-space, value, push] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 15	/* "in" : [value] | [soft-space, pop, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedInExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[44] = createParserRuleValue(44, "LetVariableCS", -1,
			createSerializationRules(
				31	/* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[45] = createParserRuleValue(45, "LibClassCS", -1,
			createSerializationRules(
				91	/* LibClassCS-0: (StructuredClassCS::isAbstract?='abstract')[V0:?] 'type' NamedElementCS::name=AnyName (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] (':' LibClassCS::metaclassName=AnyName)[V2:?] ('conformsTo' StructuredClassCS::ownedSuperTypes+=TypedRefCS (',' StructuredClassCS::ownedSuperTypes+=TypedRefCS)[V4:*])[V3:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V5:?] '{' (StructuredClassCS::ownedOperations+=OperationCS)[V6:*] (StructuredClassCS::ownedProperties+=LibPropertyCS)[V7:*] (ClassCS::ownedConstraints+=InvCS)[V8:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* isAbstract?="abstract"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "type" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=AnyName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* metaclassName=AnyName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "conformsTo" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSuperTypes+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSuperTypes+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=>" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* implementation=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedOperations+=OperationCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedProperties+=LibPropertyCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedConstraints+=InvCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[46] = createParserRuleValue(46, "LibCoercionCS", -1,
			createSerializationRules(
				92	/* LibCoercionCS-0: 'coercion' NamedElementCS::name=Name '(' ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V0:?] ';' */,
				93	/* LibCoercionCS-1: 'coercion' NamedElementCS::name=Name '(' ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V0:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V1:*] (OperationCS::ownedPreconditions+=PostCS)[V2:*] (OperationCS::ownedPostconditions+=PreCS)[V3:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "coercion" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=>" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* implementation=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPreconditions+=PostCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPostconditions+=PreCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[47] = createParserRuleValue(47, "LibIterationCS", -1,
			createSerializationRules(
				94	/* LibIterationCS-0: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=LambdaParameterCS (',' OperationCS::ownedParameters+=LambdaParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] ';' */,
				95	/* LibIterationCS-1: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=LambdaParameterCS (',' OperationCS::ownedParameters+=LambdaParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] (OperationCS::ownedPreconditions+=PostCS)[V9:*] (OperationCS::ownedPostconditions+=PreCS)[V10:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "iteration" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedIterators+=IteratorCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedIterators+=IteratorCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedAccumulator=AccumulatorCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "|" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=LambdaParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=LambdaParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 10	/* isInvalidating?="invalidating"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* isValidating?="validating"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=>" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* implementation=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPreconditions+=PostCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPostconditions+=PreCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[48] = createParserRuleValue(48, "LibOperationCS", -1,
			createSerializationRules(
				96	/* LibOperationCS-0: (LibOperationCS::isStatic?='static')[V0:?] 'operation' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibOperationCS::isValidating?='validating')[V4:?] (LibOperationCS::isInvalidating?='invalidating')[V5:?] ('precedence' '=' LibOperationCS::precedence=Name)[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] ';' */,
				97	/* LibOperationCS-1: (LibOperationCS::isStatic?='static')[V0:?] 'operation' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibOperationCS::isValidating?='validating')[V4:?] (LibOperationCS::isInvalidating?='invalidating')[V5:?] ('precedence' '=' LibOperationCS::precedence=Name)[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] '{' ('body' ':' OperationCS::ownedBodyExpressions+=SpecificationCS ';')[V8:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] (OperationCS::ownedPostconditions+=PostCS)[V10:*] (OperationCS::ownedPreconditions+=PreCS)[V11:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* isStatic?="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "operation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 10	/* isValidating?="validating"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* isInvalidating?="invalidating"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "precedence" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* precedence=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=>" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* implementation=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* "body" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedBodyExpressions+=SpecificationCS : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedPostconditions+=PostCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPreconditions+=PreCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[49] = createParserRuleValue(49, "LibOppositeCS", -1,
			createSerializationRules(
				98	/* LibOppositeCS-0: 'opposite' NamedElementCS::name=Name ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "opposite" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[50] = createParserRuleValue(50, "LibPackageCS", -1,
			createSerializationRules(
				99	/* LibPackageCS-0: 'library' NamedElementCS::name=Name (':' PackageCS::nsPrefix=Identifier '=' PackageCS::nsURI=URI)[V0:?] '{' ('precedence' (LibPackageCS::ownedPrecedences+=PrecedenceCS)[V2:+] ';')[V1:*] (PackageOwnerCS::ownedPackages+=PackageCS)[V3:*] (PackageCS::ownedClasses+=ClassCS)[V4:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V5:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "library" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* nsPrefix=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* nsURI=URI : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 5	/* ownedPackages+=PackageCS : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* "precedence" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPrecedences+=PrecedenceCS+ : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ownedClasses+=ClassCS : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[51] = createParserRuleValue(51, "LibPathElementCS", -1,
			createSerializationRules(
				100	/* LibPathElementCS-0: PathElementCS::referredElement=Name */
			),
			(0 << 16) | 10	/* referredElement=Name : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[52] = createParserRuleValue(52, "LibPathNameCS", -1,
			createSerializationRules(
				101	/* LibPathNameCS-0: PathNameCS::ownedPathElements+=LibPathElementCS ('::' PathNameCS::ownedPathElements+=LibPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=LibPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=LibPathElementCS : [value] | [value] */
		);
		grammarRuleValues[53] = createParserRuleValue(53, "LibPropertyCS", -1,
			createSerializationRules(
				102	/* LibPropertyCS-0: (LibPropertyCS::isStatic?='static')[V0:?] 'property' NamedElementCS::name=Name ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibPropertyCS::ownedOpposite=LibOppositeCS)[V1:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V2:?] ';' */,
				103	/* LibPropertyCS-1: (LibPropertyCS::isStatic?='static')[V0:?] 'property' NamedElementCS::name=Name ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibPropertyCS::ownedOpposite=LibOppositeCS)[V1:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V2:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* isStatic?="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "property" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedOpposite=LibOppositeCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=>" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* implementation=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS* : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[54] = createParserRuleValue(54, "Library", -1,
			createSerializationRules(
				104	/* Library-0: (RootCS::ownedImports+=ImportCS ';')[V0:*] (PackageOwnerCS::ownedPackages+=LibPackageCS)[V1:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 3	/* ownedImports+=ImportCS : [value] | [value, half-new-line] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ownedPackages+=LibPackageCS* : [value] | [half-new-line, value, half-new-line] */
		);
		grammarRuleValues[55] = new TerminalRuleValue(55, "ML_COMMENT");
		grammarRuleValues[56] = new TerminalRuleValue(56, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[57] = createParserRuleValue(57, "MapLiteralExpCS", -1,
			createSerializationRules(
				32	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=MapTypeCS : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[58] = createParserRuleValue(58, "MapLiteralPartCS", -1,
			createSerializationRules(
				33	/* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedKey=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 10	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValue=ExpCS : [value] | [value] */
		);
		grammarRuleValues[59] = createParserRuleValue(59, "MapTypeCS", -1,
			createSerializationRules(
				34	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* name="Map" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedKeyType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValueType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[60] = createParserRuleValue(60, "Model", -1,
			createSerializationRules(
				35	/* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[61] = createParserRuleValue(61, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 10	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[62] = createParserRuleValue(62, "MultiplicityCS", -1,
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
		grammarRuleValues[63] = createParserRuleValue(63, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			),
			(0 << 16) | 10	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[64] = createDataTypeRuleValue(64, "NUMBER_LITERAL", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[65] = createDataTypeRuleValue(65, "Name", 10 /* [soft-space, value, soft-space] */,
			4	/* 'else' : [soft-new-line, pop, value, push, soft-space] */,
			5	/* 'endif' : [soft-new-line, pop, value, soft-space] */,
			6	/* 'if' : [soft-new-line, value, push, soft-space] */,
			7	/* 'in' : [soft-space, pop, value, soft-new-line] */,
			8	/* 'let' : [soft-space, value, push] */,
			9	/* 'then' : [pop, soft-space, value, push, soft-space] */);
		grammarRuleValues[66] = createParserRuleValue(66, "NameExpCS", -1,
			createSerializationRules(
				36	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedSquareBracketedClauses+=SquareBracketedClauseCS* : [value] | [value] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* isPre?="@" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "pre" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[67] = createParserRuleValue(67, "NavigatingArgCS", -1,
			createSerializationRules(
				37	/* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				38	/* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				39	/* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				40	/* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				41	/* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 10	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 10	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 10	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 10	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[68] = createParserRuleValue(68, "NavigatingArgExpCS", 75 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				17	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				19	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				26	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				27	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				28	/* InvalidLiteralExpCS-0: 'invalid' */,
				29	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				30	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				32	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				36	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				48	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				49	/* NullLiteralExpCS-0: 'null' */,
				50	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				52	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				53	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				56	/* SelfExpCS-0: 'self' */,
				61	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				62	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				71	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* ExpCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[69] = createParserRuleValue(69, "NavigatingBarArgCS", -1,
			createSerializationRules(
				42	/* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* prefix="|" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[70] = createParserRuleValue(70, "NavigatingCommaArgCS", -1,
			createSerializationRules(
				43	/* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				44	/* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				45	/* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				46	/* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* prefix="," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 10	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 10	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 10	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 10	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[71] = createParserRuleValue(71, "NavigatingSemiArgCS", -1,
			createSerializationRules(
				47	/* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* prefix=";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[72] = createDataTypeRuleValue(72, "NavigationOperatorName", 10 /* [soft-space, value, soft-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '?->' : [no-space, value, no-space] */,
			3	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[73] = createParserRuleValue(73, "NestedExpCS", -1,
			createSerializationRules(
				48	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 4	/* "(" : [value] | [value, no-space] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[74] = createParserRuleValue(74, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */
			),
			(0 << 16) | 10	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[75] = createParserRuleValue(75, "NullLiteralExpCS", -1,
			createSerializationRules(
				49	/* NullLiteralExpCS-0: 'null' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {NullLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 10	/* "null" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[76] = createParserRuleValue(76, "NumberLiteralExpCS", -1,
			createSerializationRules(
				50	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			),
			(0 << 16) | 2	/* symbol=NUMBER_LITERAL : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[77] = createParserRuleValue(77, "OperationCS", 32 /* LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS */,
			createSerializationRules(
				92	/* LibCoercionCS-0: 'coercion' NamedElementCS::name=Name '(' ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V0:?] ';' */,
				93	/* LibCoercionCS-1: 'coercion' NamedElementCS::name=Name '(' ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V0:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V1:*] (OperationCS::ownedPreconditions+=PostCS)[V2:*] (OperationCS::ownedPostconditions+=PreCS)[V3:*] '}' */,
				94	/* LibIterationCS-0: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=LambdaParameterCS (',' OperationCS::ownedParameters+=LambdaParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] ';' */,
				95	/* LibIterationCS-1: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=LambdaParameterCS (',' OperationCS::ownedParameters+=LambdaParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] (OperationCS::ownedPreconditions+=PostCS)[V9:*] (OperationCS::ownedPostconditions+=PreCS)[V10:*] '}' */,
				96	/* LibOperationCS-0: (LibOperationCS::isStatic?='static')[V0:?] 'operation' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibOperationCS::isValidating?='validating')[V4:?] (LibOperationCS::isInvalidating?='invalidating')[V5:?] ('precedence' '=' LibOperationCS::precedence=Name)[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] ';' */,
				97	/* LibOperationCS-1: (LibOperationCS::isStatic?='static')[V0:?] 'operation' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] '(' (OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V3:*])[V2:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibOperationCS::isValidating?='validating')[V4:?] (LibOperationCS::isInvalidating?='invalidating')[V5:?] ('precedence' '=' LibOperationCS::precedence=Name)[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] '{' ('body' ':' OperationCS::ownedBodyExpressions+=SpecificationCS ';')[V8:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V9:*] (OperationCS::ownedPostconditions+=PostCS)[V10:*] (OperationCS::ownedPreconditions+=PreCS)[V11:*] '}' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* LibCoercionCS : [value] | [value] */,
			(0 << 16) | 0	/* LibIterationCS : [value] | [value] */,
			(0 << 16) | 0	/* LibOperationCS : [value] | [value] */
		);
		grammarRuleValues[78] = createParserRuleValue(78, "PackageCS", -1,
			createSerializationRules(
				105	/* PackageCS-0: 'package' NamedElementCS::name=Name (':' PackageCS::nsPrefix=Identifier '=' PackageCS::nsURI=URI)[V0:?] '{' (PackageOwnerCS::ownedPackages+=PackageCS)[V1:*] (PackageCS::ownedClasses+=ClassCS)[V2:*] (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V3:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "package" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* nsPrefix=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* nsURI=URI : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 5	/* ownedPackages+=PackageCS : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 5	/* ownedClasses+=ClassCS : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[79] = createParserRuleValue(79, "ParameterCS", -1,
			createSerializationRules(
				106	/* ParameterCS-0: NamedElementCS::name=Identifier ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[80] = createParserRuleValue(80, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[81] = createParserRuleValue(81, "PatternExpCS", -1,
			createSerializationRules(
				51	/* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* patternVariableName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPatternType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[82] = createParserRuleValue(82, "PostCS", -1,
			createSerializationRules(
				107	/* PostCS-0: ConstraintCS::stereotype='post' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* stereotype="post" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[83] = createParserRuleValue(83, "PreCS", -1,
			createSerializationRules(
				108	/* PreCS-0: ConstraintCS::stereotype='pre' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS ';' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* stereotype="pre" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 7	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[84] = createParserRuleValue(84, "PrecedenceCS", -1,
			createSerializationRules(
				109	/* PrecedenceCS-0: 'left' ':' NamedElementCS::name=Name */,
				110	/* PrecedenceCS-1: PrecedenceCS::isRightAssociative?='right' ':' NamedElementCS::name=Name */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 10	/* "left" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* isRightAssociative?="right" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* name=Name : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[85] = createParserRuleValue(85, "PrefixedLetExpCS", 42 /* LetExpCS|PrefixedLetExpCS */,
			createSerializationRules(
				30	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				52	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 10	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedLetExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LetExpCS : [value] | [value] */
		);
		grammarRuleValues[86] = createParserRuleValue(86, "PrefixedPrimaryExpCS", 72 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				17	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				19	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				27	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				28	/* InvalidLiteralExpCS-0: 'invalid' */,
				29	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				32	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				36	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				48	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				49	/* NullLiteralExpCS-0: 'null' */,
				50	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				53	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				56	/* SelfExpCS-0: 'self' */,
				61	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				62	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				71	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 10	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrimaryExpCS : [value] | [value] */
		);
		grammarRuleValues[87] = createParserRuleValue(87, "PrimaryExpCS", 71 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				17	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				19	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				27	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				28	/* InvalidLiteralExpCS-0: 'invalid' */,
				29	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				32	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				36	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				48	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				49	/* NullLiteralExpCS-0: 'null' */,
				50	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				56	/* SelfExpCS-0: 'self' */,
				61	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				62	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
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
		grammarRuleValues[88] = createParserRuleValue(88, "PrimitiveLiteralExpCS", 70 /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				17	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				28	/* InvalidLiteralExpCS-0: 'invalid' */,
				49	/* NullLiteralExpCS-0: 'null' */,
				50	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				61	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
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
		grammarRuleValues[89] = createParserRuleValue(89, "PrimitiveTypeCS", -1,
			createSerializationRules(
				54	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */
			),
			(0 << 16) | 10	/* name=PrimitiveTypeIdentifier : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[90] = createDataTypeRuleValue(90, "PrimitiveTypeIdentifier", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[91] = createDataTypeRuleValue(91, "RestrictedKeywords", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[92] = createParserRuleValue(92, "RoundBracketedClauseCS", -1,
			createSerializationRules(
				55	/* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {RoundBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=NavigatingArgCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)* : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[93] = new TerminalRuleValue(93, "SIMPLE_ID");
		grammarRuleValues[94] = new TerminalRuleValue(94, "SINGLE_QUOTED_STRING");
		grammarRuleValues[95] = new TerminalRuleValue(95, "SL_COMMENT");
		grammarRuleValues[96] = createParserRuleValue(96, "SelfExpCS", -1,
			createSerializationRules(
				56	/* SelfExpCS-0: 'self' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SelfExpCS} : [value] | [value] */,
			(0 << 16) | 10	/* "self" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[97] = createParserRuleValue(97, "ShadowPartCS", -1,
			createSerializationRules(
				57	/* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */,
				58	/* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* referredProperty=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 0	/* ownedInitExpression=StringLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[98] = createParserRuleValue(98, "SimplePathNameCS", -1,
			createSerializationRules(
				59	/* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */
			),
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */
		);
		grammarRuleValues[99] = createParserRuleValue(99, "SpecificationCS", -1,
			createSerializationRules(
				111	/* SpecificationCS-0: ExpSpecificationCS::ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[100] = createParserRuleValue(100, "SquareBracketedClauseCS", -1,
			createSerializationRules(
				60	/* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[101] = createDataTypeRuleValue(101, "StringLiteral", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[102] = createParserRuleValue(102, "StringLiteralExpCS", -1,
			createSerializationRules(
				61	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			),
			(0 << 16) | 2	/* segments+=StringLiteral+ : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[103] = createParserRuleValue(103, "TemplateBindingCS", -1,
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
		grammarRuleValues[104] = createParserRuleValue(104, "TemplateParameterSubstitutionCS", -1,
			createSerializationRules(
				12	/* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[105] = createParserRuleValue(105, "TemplateSignatureCS", -1,
			createSerializationRules(
				13	/* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[106] = createParserRuleValue(106, "TupleLiteralExpCS", -1,
			createSerializationRules(
				62	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 7	/* "," : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[107] = createParserRuleValue(107, "TupleLiteralPartCS", -1,
			createSerializationRules(
				63	/* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 10	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[108] = createParserRuleValue(108, "TuplePartCS", -1,
			createSerializationRules(
				112	/* TuplePartCS-0: NamedElementCS::name=Identifier ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[109] = createParserRuleValue(109, "TupleTypeCS", -1,
			createSerializationRules(
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* name="Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[110] = createParserRuleValue(110, "TypeExpCS", -1,
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
		grammarRuleValues[111] = createParserRuleValue(111, "TypeExpWithoutMultiplicityCS", 61 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				22	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				23	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				34	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				54	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				76	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeNameExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionPatternCS : [value] | [value] */
		);
		grammarRuleValues[112] = createParserRuleValue(112, "TypeLiteralCS", 59 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */,
			createSerializationRules(
				23	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				34	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				54	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */
		);
		grammarRuleValues[113] = createParserRuleValue(113, "TypeLiteralExpCS", -1,
			createSerializationRules(
				71	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			(0 << 16) | 2	/* ownedType=TypeLiteralWithMultiplicityCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[114] = createParserRuleValue(114, "TypeLiteralWithMultiplicityCS", -1,
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
		grammarRuleValues[115] = createParserRuleValue(115, "TypeNameExpCS", -1,
			createSerializationRules(
				76	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedPatternGuard=ExpCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[116] = createParserRuleValue(116, "TypeParameterCS", -1,
			createSerializationRules(
				14	/* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 10	/* "&&" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[117] = createParserRuleValue(117, "TypeRefCS", 77 /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				90	/* LambdaTypeCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=TypedMultiplicityRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedMultiplicityRefCS */,
				34	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				118	/* TypedTypeRefCS-0: TypedTypeRefCS::isTypeof?='typeof' '(' TypedTypeRefCS::ownedPathName=LibPathNameCS ')' */,
				119	/* TypedTypeRefCS-1: TypedTypeRefCS::ownedPathName=LibPathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */,
				16	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[118] = createParserRuleValue(118, "TypedMultiplicityRefCS", -1,
			createSerializationRules(
				113	/* TypedMultiplicityRefCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=TypedMultiplicityRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedMultiplicityRefCS (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
				114	/* TypedMultiplicityRefCS-1: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				115	/* TypedMultiplicityRefCS-2: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
				116	/* TypedMultiplicityRefCS-3: TypedTypeRefCS::isTypeof?='typeof' '(' TypedTypeRefCS::ownedPathName=LibPathNameCS ')' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				117	/* TypedMultiplicityRefCS-4: TypedTypeRefCS::ownedPathName=LibPathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */,
			(0 << 16) | 0	/* LambdaTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[119] = createParserRuleValue(119, "TypedRefCS", 66 /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				90	/* LambdaTypeCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=TypedMultiplicityRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedMultiplicityRefCS */,
				34	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				64	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				118	/* TypedTypeRefCS-0: TypedTypeRefCS::isTypeof?='typeof' '(' TypedTypeRefCS::ownedPathName=LibPathNameCS ')' */,
				119	/* TypedTypeRefCS-1: TypedTypeRefCS::ownedPathName=LibPathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */,
			(0 << 16) | 0	/* LambdaTypeCS : [value] | [value] */
		);
		grammarRuleValues[120] = createParserRuleValue(120, "TypedTypeRefCS", -1,
			createSerializationRules(
				118	/* TypedTypeRefCS-0: TypedTypeRefCS::isTypeof?='typeof' '(' TypedTypeRefCS::ownedPathName=LibPathNameCS ')' */,
				119	/* TypedTypeRefCS-1: TypedTypeRefCS::ownedPathName=LibPathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* isTypeof?="typeof" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathName=LibPathNameCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=LibPathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[121] = createDataTypeRuleValue(121, "UPPER", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[122] = createDataTypeRuleValue(122, "URI", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[123] = createParserRuleValue(123, "URIFirstPathElementCS", -1,
			createSerializationRules(
				77	/* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */,
				78	/* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 10	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PathElementWithURICS} : [value] | [value] */,
			(0 << 16) | 10	/* referredElement=URI : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[124] = createParserRuleValue(124, "URIPathNameCS", -1,
			createSerializationRules(
				79	/* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=URIFirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[125] = createDataTypeRuleValue(125, "UnaryOperatorName", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[126] = createParserRuleValue(126, "UnlimitedNaturalLiteralExpCS", -1,
			createSerializationRules(
				80	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {UnlimitedNaturalLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 10	/* "*" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[127] = createDataTypeRuleValue(127, "UnreservedName", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[128] = createParserRuleValue(128, "UnreservedPathNameCS", -1,
			createSerializationRules(
				15	/* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[129] = createDataTypeRuleValue(129, "UnrestrictedName", 10 /* [soft-space, value, soft-space] */);
		grammarRuleValues[130] = new TerminalRuleValue(130, "WS");
		grammarRuleValues[131] = createParserRuleValue(131, "WildcardTypeRefCS", -1,
			createSerializationRules(
				16	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WildcardTypeRefCS} : [value] | [value] */,
			(0 << 16) | 10	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends=TypedRefCS : [value] | [value] */
		);
	}

	/**
	 * Initialize bit vectors of useful grammar rule combinations.
	 */
	private void initGrammarRuleVectors() {
		// 0: AccumulatorCS
		grammarRuleVectors[0] = new GrammarRuleVector(0x2L);
		// 1: AnnotationElementCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x8L);
		// 2: ClassCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x80L);
		// 3: CoIteratorVariableCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x100L);
		// 4: CollectionLiteralPartCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x400L);
		// 5: CollectionTypeCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x1000L);
		// 6: CurlyBracketedClauseCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x4000L);
		// 7: DetailCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x10000L);
		// 8: AnnotationCS|AnnotationElementCS|DocumentationCS
		grammarRuleVectors[8] = new GrammarRuleVector(0x2000cL);
		// 9: ElseIfThenExpCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x100000L);
		// 10: ExpCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x8000000L);
		// 11: FirstPathElementCS
		grammarRuleVectors[11] = new GrammarRuleVector(0x10000000L);
		// 12: ImportCS
		grammarRuleVectors[12] = new GrammarRuleVector(0x200000000L);
		// 13: InvCS
		grammarRuleVectors[13] = new GrammarRuleVector(0x800000000L);
		// 14: IteratorCS
		grammarRuleVectors[14] = new GrammarRuleVector(0x2000000000L);
		// 15: LambdaParameterCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x20000000000L);
		// 16: LetVariableCS
		grammarRuleVectors[16] = new GrammarRuleVector(0x100000000000L);
		// 17: ClassCS|LibClassCS
		grammarRuleVectors[17] = new GrammarRuleVector(0x200000000080L);
		// 18: LibOppositeCS
		grammarRuleVectors[18] = new GrammarRuleVector(0x2000000000000L);
		// 19: LibPackageCS
		grammarRuleVectors[19] = new GrammarRuleVector(0x4000000000000L);
		// 20: LibPathElementCS
		grammarRuleVectors[20] = new GrammarRuleVector(0x8000000000000L);
		// 21: LibPathNameCS
		grammarRuleVectors[21] = new GrammarRuleVector(0x10000000000000L);
		// 22: LibPropertyCS
		grammarRuleVectors[22] = new GrammarRuleVector(0x20000000000000L);
		// 23: MapLiteralPartCS
		grammarRuleVectors[23] = new GrammarRuleVector(0x400000000000000L);
		// 24: MapTypeCS
		grammarRuleVectors[24] = new GrammarRuleVector(0x800000000000000L);
		// 25: MultiplicityCS
		grammarRuleVectors[25] = new GrammarRuleVector(0x4000000000000000L);
		// 26: NavigatingArgExpCS
		grammarRuleVectors[26] = new GrammarRuleVector(0x0L,0x10L);
		// 27: NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[27] = new GrammarRuleVector(0x0L,0xe0L);
		// 28: NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[28] = new GrammarRuleVector(0x0L,0xe8L);
		// 29: NextPathElementCS
		grammarRuleVectors[29] = new GrammarRuleVector(0x0L,0x400L);
		// 30: FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[30] = new GrammarRuleVector(0x10000000L,0x400L);
		// 31: OperationCS
		grammarRuleVectors[31] = new GrammarRuleVector(0x0L,0x2000L);
		// 32: LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS
		grammarRuleVectors[32] = new GrammarRuleVector(0x1c00000000000L,0x2000L);
		// 33: PackageCS
		grammarRuleVectors[33] = new GrammarRuleVector(0x0L,0x4000L);
		// 34: ParameterCS
		grammarRuleVectors[34] = new GrammarRuleVector(0x0L,0x8000L);
		// 35: PathNameCS
		grammarRuleVectors[35] = new GrammarRuleVector(0x0L,0x10000L);
		// 36: PatternExpCS
		grammarRuleVectors[36] = new GrammarRuleVector(0x0L,0x20000L);
		// 37: ExpCS|PatternExpCS
		grammarRuleVectors[37] = new GrammarRuleVector(0x8000000L,0x20000L);
		// 38: PostCS
		grammarRuleVectors[38] = new GrammarRuleVector(0x0L,0x40000L);
		// 39: PreCS
		grammarRuleVectors[39] = new GrammarRuleVector(0x0L,0x80000L);
		// 40: PrecedenceCS
		grammarRuleVectors[40] = new GrammarRuleVector(0x0L,0x100000L);
		// 41: PrefixedLetExpCS
		grammarRuleVectors[41] = new GrammarRuleVector(0x0L,0x200000L);
		// 42: LetExpCS|PrefixedLetExpCS
		grammarRuleVectors[42] = new GrammarRuleVector(0x80000000000L,0x200000L);
		// 43: PrefixedPrimaryExpCS
		grammarRuleVectors[43] = new GrammarRuleVector(0x0L,0x400000L);
		// 44: RoundBracketedClauseCS
		grammarRuleVectors[44] = new GrammarRuleVector(0x0L,0x10000000L);
		// 45: Identifier|SINGLE_QUOTED_STRING
		grammarRuleVectors[45] = new GrammarRuleVector(0x80000000L,0x40000000L);
		// 46: ML_SINGLE_QUOTED_STRING|SINGLE_QUOTED_STRING
		grammarRuleVectors[46] = new GrammarRuleVector(0x100000000000000L,0x40000000L);
		// 47: Name|SINGLE_QUOTED_STRING
		grammarRuleVectors[47] = new GrammarRuleVector(0x0L,0x40000002L);
		// 48: ShadowPartCS
		grammarRuleVectors[48] = new GrammarRuleVector(0x0L,0x200000000L);
		// 49: SpecificationCS
		grammarRuleVectors[49] = new GrammarRuleVector(0x0L,0x800000000L);
		// 50: SquareBracketedClauseCS
		grammarRuleVectors[50] = new GrammarRuleVector(0x0L,0x1000000000L);
		// 51: StringLiteralExpCS
		grammarRuleVectors[51] = new GrammarRuleVector(0x0L,0x4000000000L);
		// 52: TemplateBindingCS
		grammarRuleVectors[52] = new GrammarRuleVector(0x0L,0x8000000000L);
		// 53: TemplateParameterSubstitutionCS
		grammarRuleVectors[53] = new GrammarRuleVector(0x0L,0x10000000000L);
		// 54: TemplateSignatureCS
		grammarRuleVectors[54] = new GrammarRuleVector(0x0L,0x20000000000L);
		// 55: TupleLiteralPartCS
		grammarRuleVectors[55] = new GrammarRuleVector(0x0L,0x80000000000L);
		// 56: TuplePartCS
		grammarRuleVectors[56] = new GrammarRuleVector(0x0L,0x100000000000L);
		// 57: TypeExpCS
		grammarRuleVectors[57] = new GrammarRuleVector(0x0L,0x400000000000L);
		// 58: TypeExpWithoutMultiplicityCS
		grammarRuleVectors[58] = new GrammarRuleVector(0x0L,0x800000000000L);
		// 59: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
		grammarRuleVectors[59] = new GrammarRuleVector(0x800000000001000L,0x1200002000000L);
		// 60: TypeLiteralWithMultiplicityCS
		grammarRuleVectors[60] = new GrammarRuleVector(0x0L,0x4000000000000L);
		// 61: CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[61] = new GrammarRuleVector(0x800000000001800L,0x9a00002000000L);
		// 62: TypeParameterCS
		grammarRuleVectors[62] = new GrammarRuleVector(0x0L,0x10000000000000L);
		// 63: TypeRefCS
		grammarRuleVectors[63] = new GrammarRuleVector(0x0L,0x20000000000000L);
		// 64: TypedMultiplicityRefCS
		grammarRuleVectors[64] = new GrammarRuleVector(0x0L,0x40000000000000L);
		// 65: TypedRefCS
		grammarRuleVectors[65] = new GrammarRuleVector(0x0L,0x80000000000000L);
		// 66: LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[66] = new GrammarRuleVector(0x800040000000000L,0x180200000000000L);
		// 67: NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[67] = new GrammarRuleVector(0x0L,0x800000000000400L);
		// 68: FirstPathElementCS|LibPathElementCS|NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[68] = new GrammarRuleVector(0x8000010000000L,0x800000000000400L);
		// 69: URIPathNameCS
		grammarRuleVectors[69] = new GrammarRuleVector(0x0L,0x1000000000000000L);
		// 70: BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[70] = new GrammarRuleVector(0x1000000040L,0x4000004001001800L);
		// 71: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[71] = new GrammarRuleVector(0x200011100000240L,0x4002044101801a04L);
		// 72: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[72] = new GrammarRuleVector(0x200011100000240L,0x4002044101c01a04L);
		// 73: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[73] = new GrammarRuleVector(0x200091100000240L,0x4002044101e01a04L);
		// 74: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[74] = new GrammarRuleVector(0x200091108000240L,0x4002044101e01a04L);
		// 75: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[75] = new GrammarRuleVector(0x200091108000240L,0x4002044101e01a14L);
		// 76: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[76] = new GrammarRuleVector(0x200091108000240L,0x4002044101e21a04L);
		// 77: LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[77] = new GrammarRuleVector(0x800040000000000L,0x1a0200000000000L,0x8L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// 0: assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(151);
		// 1: assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(154);
		// 2: assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(157);
		// 3: assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(158);
		// 4: assert (|CollectionPatternCS::ownedType| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(160);
		// 5: assert (|CollectionTypeCS::name| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(161);
		// 6: assert (|ConstraintCS::ownedSpecification| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(162);
		// 7: assert (|ConstraintCS::stereotype.'inv'| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(163);
		// 8: assert (|ConstraintCS::stereotype.'post'| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(164);
		// 9: assert (|ConstraintCS::stereotype.'pre'| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(165);
		// 10: assert (|ContextCS::ownedExpression| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(166);
		// 11: assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(169);
		// 12: assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(170);
		// 13: assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(171);
		// 14: assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(172);
		// 15: assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(173);
		// 16: assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(174);
		// 17: assert (|ImportCS::ownedPathName| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(175);
		// 18: assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(176);
		// 19: assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(177);
		// 20: assert (|LambdaParameterCS::ownedContextType| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(178);
		// 21: assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(181);
		// 22: assert (|LambdaTypeCS::ownedContextType| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(182);
		// 23: assert (|LambdaTypeCS::ownedResultType| - 1) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(185);
		// 24: assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(186);
		// 25: assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(192);
		// 26: assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(193);
		// 27: assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(194);
		// 28: assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(195);
		// 29: assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(196);
		// 30: assert (|ModelElementCS::ownedAnnotations| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(197);
		// 31: assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(198);
		// 32: assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(199);
		// 33: assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(200);
		// 34: assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(201);
		// 35: assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(202);
		// 36: assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(203);
		// 37: assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(204);
		// 38: assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(205);
		// 39: assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(206);
		// 40: assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(207);
		// 41: assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(208);
		// 42: assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(209);
		// 43: assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(210);
		// 44: assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(213);
		// 45: assert (|PackageCS::nsPrefix| - V0) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(214);
		// 46: assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[46] = createMatchStep_Assert(215);
		// 47: assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[47] = createMatchStep_Assert(216);
		// 48: assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[48] = createMatchStep_Assert(217);
		// 49: assert (|PrecedenceCS::isRightAssociative.'right'| - 1) == 0
		serializationMatchSteps[49] = createMatchStep_Assert(218);
		// 50: assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[50] = createMatchStep_Assert(219);
		// 51: assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[51] = createMatchStep_Assert(220);
		// 52: assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[52] = createMatchStep_Assert(221);
		// 53: assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[53] = createMatchStep_Assert(226);
		// 54: assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[54] = createMatchStep_Assert(229);
		// 55: assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[55] = createMatchStep_Assert(232);
		// 56: assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[56] = createMatchStep_Assert(233);
		// 57: assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[57] = createMatchStep_Assert(236);
		// 58: assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0
		serializationMatchSteps[58] = createMatchStep_Assert(237);
		// 59: assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[59] = createMatchStep_Assert(238);
		// 60: assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[60] = createMatchStep_Assert(239);
		// 61: assert |AnnotationCS::ownedContents| == 0
		serializationMatchSteps[61] = createMatchStep_Assert(8);
		// 62: assert |AnnotationCS::ownedReferences| == 0
		serializationMatchSteps[62] = createMatchStep_Assert(9);
		// 63: assert |ClassCS::instanceClassName| == 0
		serializationMatchSteps[63] = createMatchStep_Assert(12);
		// 64: assert |CollectionLiteralPartCS::ownedLastExpression| == 0
		serializationMatchSteps[64] = createMatchStep_Assert(17);
		// 65: assert |CollectionPatternCS::ownedPatternGuard| == 0
		serializationMatchSteps[65] = createMatchStep_Assert(19);
		// 66: assert |CurlyBracketedClauseCS::value| == 0
		serializationMatchSteps[66] = createMatchStep_Assert(32);
		// 67: assert |IfExpCS::isImplicit| == 0
		serializationMatchSteps[67] = createMatchStep_Assert(36);
		// 68: assert |LetExpCS::isImplicit| == 0
		serializationMatchSteps[68] = createMatchStep_Assert(54);
		// 69: assert |ModelElementCS::ownedAnnotations| == 0
		serializationMatchSteps[69] = createMatchStep_Assert(77);
		// 70: assert |MultiplicityCS::isNullFree| == 0
		serializationMatchSteps[70] = createMatchStep_Assert(81);
		// 71: assert |NamedElementCS::name| == 0
		serializationMatchSteps[71] = createMatchStep_Assert(83);
		// 72: assert |NavigatingArgCS::ownedCoIterator| == 0
		serializationMatchSteps[72] = createMatchStep_Assert(84);
		// 73: assert |NavigatingArgCS::ownedInitExpression| == 0
		serializationMatchSteps[73] = createMatchStep_Assert(85);
		// 74: assert |NavigatingArgCS::ownedNameExpression| == 0
		serializationMatchSteps[74] = createMatchStep_Assert(86);
		// 75: assert |NavigatingArgCS::ownedType| == 0
		serializationMatchSteps[75] = createMatchStep_Assert(87);
		// 76: assert |NavigatingArgCS::prefix| == 0
		serializationMatchSteps[76] = createMatchStep_Assert(91);
		// 77: assert |OperationCS::ownedBodyExpressions| == 0
		serializationMatchSteps[77] = createMatchStep_Assert(94);
		// 78: assert |OperationCS::ownedExceptions| == 0
		serializationMatchSteps[78] = createMatchStep_Assert(95);
		// 79: assert |OperationCS::ownedParameters| == 0
		serializationMatchSteps[79] = createMatchStep_Assert(96);
		// 80: assert |OperationCS::ownedPostconditions| == 0
		serializationMatchSteps[80] = createMatchStep_Assert(97);
		// 81: assert |OperationCS::ownedPreconditions| == 0
		serializationMatchSteps[81] = createMatchStep_Assert(98);
		// 82: assert |PrecedenceCS::isRightAssociative| == 0
		serializationMatchSteps[82] = createMatchStep_Assert(109);
		// 83: assert |RootCS::ownedImports| == 0
		serializationMatchSteps[83] = createMatchStep_Assert(111);
		// 84: assert |SelfExpCS::name| == 0
		serializationMatchSteps[84] = createMatchStep_Assert(112);
		// 85: assert |ShadowPartCS::referredProperty| == 0
		serializationMatchSteps[85] = createMatchStep_Assert(114);
		// 86: assert |SpecificationCS::exprString| == 0
		serializationMatchSteps[86] = createMatchStep_Assert(115);
		// 87: assert |StructuralFeatureCS::default| == 0
		serializationMatchSteps[87] = createMatchStep_Assert(118);
		// 88: assert |StructuralFeatureCS::ownedDefaultExpressions| == 0
		serializationMatchSteps[88] = createMatchStep_Assert(119);
		// 89: assert |StructuredClassCS::isInterface| == 0
		serializationMatchSteps[89] = createMatchStep_Assert(121);
		// 90: assert |StructuredClassCS::ownedMetaclass| == 0
		serializationMatchSteps[90] = createMatchStep_Assert(122);
		// 91: assert |TemplateableElementCS::ownedSignature| == 0
		serializationMatchSteps[91] = createMatchStep_Assert(130);
		// 92: assert |TypeLiteralExpCS::ownedPathName| == 0
		serializationMatchSteps[92] = createMatchStep_Assert(134);
		// 93: assert |TypedElementCS::qualifiers| == 0
		serializationMatchSteps[93] = createMatchStep_Assert(141);
		// 94: assert |TypedRefCS::ownedMultiplicity| == 0
		serializationMatchSteps[94] = createMatchStep_Assert(142);
		// 95: assert |TypedTypeRefCS::isTypeof| == 0
		serializationMatchSteps[95] = createMatchStep_Assert(144);
		// 96: assert |TypedTypeRefCS::ownedBinding| == 0
		serializationMatchSteps[96] = createMatchStep_Assert(145);
		// 97: assert |VariableCS::ownedInitExpression| == 0
		serializationMatchSteps[97] = createMatchStep_Assert(147);
		// 98: assert |WildcardTypeRefCS::ownedSuper| == 0
		serializationMatchSteps[98] = createMatchStep_Assert(150);
		// 99: assign V0 = (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchSteps[99] = createMatchStep_Assign(0, 153);
		// 100: assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[100] = createMatchStep_Assign(0, 156);
		// 101: assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchSteps[101] = createMatchStep_Assign(0, 168);
		// 102: assign V0 = (|LambdaParameterCS::ownedParameters| > 0)
		serializationMatchSteps[102] = createMatchStep_Assign(0, 180);
		// 103: assign V0 = (|LetExpCS::ownedVariables| - 1)
		serializationMatchSteps[103] = createMatchStep_Assign(0, 187);
		// 104: assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[104] = createMatchStep_Assign(0, 191);
		// 105: assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[105] = createMatchStep_Assign(0, 216);
		// 106: assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[106] = createMatchStep_Assign(0, 222);
		// 107: assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[107] = createMatchStep_Assign(0, 225);
		// 108: assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[108] = createMatchStep_Assign(0, 227);
		// 109: assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[109] = createMatchStep_Assign(0, 228);
		// 110: assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[110] = createMatchStep_Assign(0, 231);
		// 111: assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[111] = createMatchStep_Assign(0, 235);
		// 112: assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchSteps[112] = createMatchStep_Assign(0, 7);
		// 113: assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchSteps[113] = createMatchStep_Assign(0, 17);
		// 114: assign V0 = |CollectionPatternCS::restVariableName|
		serializationMatchSteps[114] = createMatchStep_Assign(0, 21);
		// 115: assign V0 = |CollectionTypeCS::ownedType|
		serializationMatchSteps[115] = createMatchStep_Assign(0, 24);
		// 116: assign V0 = |DetailCS::values|
		serializationMatchSteps[116] = createMatchStep_Assign(0, 33);
		// 117: assign V0 = |DocumentationCS::value|
		serializationMatchSteps[117] = createMatchStep_Assign(0, 34);
		// 118: assign V0 = |IfExpCS::ownedIfThenExpressions|
		serializationMatchSteps[118] = createMatchStep_Assign(0, 39);
		// 119: assign V0 = |JavaImplementationCS::implementation|
		serializationMatchSteps[119] = createMatchStep_Assign(0, 46);
		// 120: assign V0 = |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchSteps[120] = createMatchStep_Assign(0, 57);
		// 121: assign V0 = |LibOperationCS::isStatic.'static'|
		serializationMatchSteps[121] = createMatchStep_Assign(0, 64);
		// 122: assign V0 = |LibPropertyCS::isStatic.'static'|
		serializationMatchSteps[122] = createMatchStep_Assign(0, 68);
		// 123: assign V0 = |MapTypeCS::ownedValueType|
		serializationMatchSteps[123] = createMatchStep_Assign(0, 76);
		// 124: assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[124] = createMatchStep_Assign(0, 79);
		// 125: assign V0 = |NamedElementCS::name|
		serializationMatchSteps[125] = createMatchStep_Assign(0, 83);
		// 126: assign V0 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[126] = createMatchStep_Assign(0, 84);
		// 127: assign V0 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[127] = createMatchStep_Assign(0, 85);
		// 128: assign V0 = |NavigatingArgCS::ownedType|
		serializationMatchSteps[128] = createMatchStep_Assign(0, 87);
		// 129: assign V0 = |PackageCS::nsURI|
		serializationMatchSteps[129] = createMatchStep_Assign(0, 101);
		// 130: assign V0 = |PatternExpCS::patternVariableName|
		serializationMatchSteps[130] = createMatchStep_Assign(0, 107);
		// 131: assign V0 = |RootCS::ownedImports|
		serializationMatchSteps[131] = createMatchStep_Assign(0, 111);
		// 132: assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[132] = createMatchStep_Assign(0, 117);
		// 133: assign V0 = |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchSteps[133] = createMatchStep_Assign(0, 120);
		// 134: assign V0 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[134] = createMatchStep_Assign(0, 130);
		// 135: assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[135] = createMatchStep_Assign(0, 136);
		// 136: assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[136] = createMatchStep_Assign(0, 142);
		// 137: assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[137] = createMatchStep_Assign(0, 145);
		// 138: assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[138] = createMatchStep_Assign(0, 148);
		// 139: assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[139] = createMatchStep_Assign(0, 149);
		// 140: assign V1 = (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchSteps[140] = createMatchStep_Assign(1, 152);
		// 141: assign V1 = (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchSteps[141] = createMatchStep_Assign(1, 153);
		// 142: assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[142] = createMatchStep_Assign(1, 155);
		// 143: assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[143] = createMatchStep_Assign(1, 159);
		// 144: assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[144] = createMatchStep_Assign(1, 167);
		// 145: assign V1 = (|LambdaParameterCS::ownedParameters| - 1)
		serializationMatchSteps[145] = createMatchStep_Assign(1, 179);
		// 146: assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0)
		serializationMatchSteps[146] = createMatchStep_Assign(1, 184);
		// 147: assign V1 = (|LibIterationCS::ownedIterators| - 1)
		serializationMatchSteps[147] = createMatchStep_Assign(1, 188);
		// 148: assign V1 = (|LibPackageCS::ownedPrecedences| > 0)
		serializationMatchSteps[148] = createMatchStep_Assign(1, 189);
		// 149: assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[149] = createMatchStep_Assign(1, 190);
		// 150: assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[150] = createMatchStep_Assign(1, 231);
		// 151: assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[151] = createMatchStep_Assign(1, 234);
		// 152: assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[152] = createMatchStep_Assign(1, 6);
		// 153: assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[153] = createMatchStep_Assign(1, 23);
		// 154: assign V1 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[154] = createMatchStep_Assign(1, 25);
		// 155: assign V1 = |ImportCS::isAll.'::*'|
		serializationMatchSteps[155] = createMatchStep_Assign(1, 43);
		// 156: assign V1 = |LibPropertyCS::ownedOpposite|
		serializationMatchSteps[156] = createMatchStep_Assign(1, 69);
		// 157: assign V1 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[157] = createMatchStep_Assign(1, 77);
		// 158: assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[158] = createMatchStep_Assign(1, 84);
		// 159: assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[159] = createMatchStep_Assign(1, 85);
		// 160: assign V1 = |PackageOwnerCS::ownedPackages|
		serializationMatchSteps[160] = createMatchStep_Assign(1, 103);
		// 161: assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[161] = createMatchStep_Assign(1, 126);
		// 162: assign V1 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[162] = createMatchStep_Assign(1, 130);
		// 163: assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[163] = createMatchStep_Assign(1, 138);
		// 164: assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[164] = createMatchStep_Assign(1, 142);
		// 165: assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[165] = createMatchStep_Assign(1, 148);
		// 166: assign V10 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[166] = createMatchStep_Assign(10, 97);
		// 167: assign V11 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[167] = createMatchStep_Assign(11, 98);
		// 168: assign V2 = (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchSteps[168] = createMatchStep_Assign(2, 152);
		// 169: assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1)
		serializationMatchSteps[169] = createMatchStep_Assign(2, 183);
		// 170: assign V2 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[170] = createMatchStep_Assign(2, 212);
		// 171: assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[171] = createMatchStep_Assign(2, 230);
		// 172: assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[172] = createMatchStep_Assign(2, 4);
		// 173: assign V2 = |JavaImplementationCS::implementation|
		serializationMatchSteps[173] = createMatchStep_Assign(2, 46);
		// 174: assign V2 = |LibClassCS::metaclassName|
		serializationMatchSteps[174] = createMatchStep_Assign(2, 58);
		// 175: assign V2 = |LibIterationCS::ownedAccumulator|
		serializationMatchSteps[175] = createMatchStep_Assign(2, 61);
		// 176: assign V2 = |LibPackageCS::ownedPrecedences|
		serializationMatchSteps[176] = createMatchStep_Assign(2, 67);
		// 177: assign V2 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[177] = createMatchStep_Assign(2, 98);
		// 178: assign V2 = |PackageCS::ownedClasses|
		serializationMatchSteps[178] = createMatchStep_Assign(2, 102);
		// 179: assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[179] = createMatchStep_Assign(2, 142);
		// 180: assign V3 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[180] = createMatchStep_Assign(3, 211);
		// 181: assign V3 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[181] = createMatchStep_Assign(3, 212);
		// 182: assign V3 = (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchSteps[182] = createMatchStep_Assign(3, 224);
		// 183: assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[183] = createMatchStep_Assign(3, 3);
		// 184: assign V3 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[184] = createMatchStep_Assign(3, 77);
		// 185: assign V3 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[185] = createMatchStep_Assign(3, 97);
		// 186: assign V3 = |PackageOwnerCS::ownedPackages|
		serializationMatchSteps[186] = createMatchStep_Assign(3, 103);
		// 187: assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[187] = createMatchStep_Assign(3, 142);
		// 188: assign V4 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[188] = createMatchStep_Assign(4, 211);
		// 189: assign V4 = (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchSteps[189] = createMatchStep_Assign(4, 223);
		// 190: assign V4 = |LibOperationCS::isValidating.'validating'|
		serializationMatchSteps[190] = createMatchStep_Assign(4, 65);
		// 191: assign V4 = |PackageCS::ownedClasses|
		serializationMatchSteps[191] = createMatchStep_Assign(4, 102);
		// 192: assign V5 = |JavaImplementationCS::implementation|
		serializationMatchSteps[192] = createMatchStep_Assign(5, 46);
		// 193: assign V5 = |LibIterationCS::isInvalidating.'invalidating'|
		serializationMatchSteps[193] = createMatchStep_Assign(5, 59);
		// 194: assign V5 = |LibOperationCS::isInvalidating.'invalidating'|
		serializationMatchSteps[194] = createMatchStep_Assign(5, 63);
		// 195: assign V5 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[195] = createMatchStep_Assign(5, 77);
		// 196: assign V6 = |LibIterationCS::isValidating.'validating'|
		serializationMatchSteps[196] = createMatchStep_Assign(6, 60);
		// 197: assign V6 = |LibOperationCS::precedence|
		serializationMatchSteps[197] = createMatchStep_Assign(6, 66);
		// 198: assign V6 = |StructuredClassCS::ownedOperations|
		serializationMatchSteps[198] = createMatchStep_Assign(6, 123);
		// 199: assign V7 = |JavaImplementationCS::implementation|
		serializationMatchSteps[199] = createMatchStep_Assign(7, 46);
		// 200: assign V7 = |StructuredClassCS::ownedProperties|
		serializationMatchSteps[200] = createMatchStep_Assign(7, 124);
		// 201: assign V8 = |ClassCS::ownedConstraints|
		serializationMatchSteps[201] = createMatchStep_Assign(8, 13);
		// 202: assign V8 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[202] = createMatchStep_Assign(8, 77);
		// 203: assign V8 = |OperationCS::ownedBodyExpressions|
		serializationMatchSteps[203] = createMatchStep_Assign(8, 94);
		// 204: assign V9 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[204] = createMatchStep_Assign(9, 77);
		// 205: assign V9 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[205] = createMatchStep_Assign(9, 98);
		// 206: check-rule basecs::AnnotationElementCS.ownedDetails : 16
		serializationMatchSteps[206] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 7/*DetailCS*/);
		// 207: check-rule basecs::ClassCS.ownedConstraints : 35
		serializationMatchSteps[207] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/*InvCS*/);
		// 208: check-rule basecs::ConstraintCS.ownedMessageSpecification : 99
		serializationMatchSteps[208] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 49/*SpecificationCS*/);
		// 209: check-rule basecs::ConstraintCS.ownedSpecification : 99
		serializationMatchSteps[209] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 49/*SpecificationCS*/);
		// 210: check-rule basecs::ImportCS.ownedPathName : 124
		serializationMatchSteps[210] = createMatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 69/*URIPathNameCS*/);
		// 211: check-rule basecs::LambdaParameterCS.ownedContextType : 118
		serializationMatchSteps[211] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_CONTEXT_TYPE, 64/*TypedMultiplicityRefCS*/);
		// 212: check-rule basecs::LambdaParameterCS.ownedParameters : 79
		serializationMatchSteps[212] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_PARAMETERS, 34/*ParameterCS*/);
		// 213: check-rule basecs::LambdaTypeCS.ownedContextType : 118
		serializationMatchSteps[213] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 64/*TypedMultiplicityRefCS*/);
		// 214: check-rule basecs::LambdaTypeCS.ownedParameterTypes : 118
		serializationMatchSteps[214] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 64/*TypedMultiplicityRefCS*/);
		// 215: check-rule basecs::LambdaTypeCS.ownedResultType : 118
		serializationMatchSteps[215] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 64/*TypedMultiplicityRefCS*/);
		// 216: check-rule basecs::ModelElementCS.ownedAnnotations : 2|3|17
		serializationMatchSteps[216] = createMatchStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 8/*AnnotationCS|AnnotationElementCS|DocumentationCS*/);
		// 217: check-rule basecs::OperationCS.ownedBodyExpressions : 99
		serializationMatchSteps[217] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 49/*SpecificationCS*/);
		// 218: check-rule basecs::OperationCS.ownedParameters : 41
		serializationMatchSteps[218] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 15/*LambdaParameterCS*/);
		// 219: check-rule basecs::OperationCS.ownedParameters : 79
		serializationMatchSteps[219] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 34/*ParameterCS*/);
		// 220: check-rule basecs::OperationCS.ownedPostconditions : 82
		serializationMatchSteps[220] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 38/*PostCS*/);
		// 221: check-rule basecs::OperationCS.ownedPostconditions : 83
		serializationMatchSteps[221] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 39/*PreCS*/);
		// 222: check-rule basecs::OperationCS.ownedPreconditions : 82
		serializationMatchSteps[222] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 38/*PostCS*/);
		// 223: check-rule basecs::OperationCS.ownedPreconditions : 83
		serializationMatchSteps[223] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 39/*PreCS*/);
		// 224: check-rule basecs::PackageCS.ownedClasses : 7|45
		serializationMatchSteps[224] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 17/*ClassCS|LibClassCS*/);
		// 225: check-rule basecs::PackageOwnerCS.ownedPackages : 50
		serializationMatchSteps[225] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 19/*LibPackageCS*/);
		// 226: check-rule basecs::PackageOwnerCS.ownedPackages : 78
		serializationMatchSteps[226] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 33/*PackageCS*/);
		// 227: check-rule basecs::PathNameCS.ownedPathElements : 28
		serializationMatchSteps[227] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 11/*FirstPathElementCS*/);
		// 228: check-rule basecs::PathNameCS.ownedPathElements : 51
		serializationMatchSteps[228] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 20/*LibPathElementCS*/);
		// 229: check-rule basecs::PathNameCS.ownedPathElements : 74
		serializationMatchSteps[229] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 29/*NextPathElementCS*/);
		// 230: check-rule basecs::RootCS.ownedImports : 33
		serializationMatchSteps[230] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/*ImportCS*/);
		// 231: check-rule basecs::StructuredClassCS.ownedOperations : 46|47|48|77
		serializationMatchSteps[231] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 32/*LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS*/);
		// 232: check-rule basecs::StructuredClassCS.ownedProperties : 53
		serializationMatchSteps[232] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 22/*LibPropertyCS*/);
		// 233: check-rule basecs::StructuredClassCS.ownedSuperTypes : 42|59|109|119|120
		serializationMatchSteps[233] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 66/*LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS*/);
		// 234: check-rule basecs::TemplateBindingCS.ownedMultiplicity : 62
		serializationMatchSteps[234] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 25/*MultiplicityCS*/);
		// 235: check-rule basecs::TemplateBindingCS.ownedSubstitutions : 104
		serializationMatchSteps[235] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 53/*TemplateParameterSubstitutionCS*/);
		// 236: check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 42|59|109|117|119|120|131
		serializationMatchSteps[236] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 77/*LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS*/);
		// 237: check-rule basecs::TemplateSignatureCS.ownedParameters : 116
		serializationMatchSteps[237] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 62/*TypeParameterCS*/);
		// 238: check-rule basecs::TemplateableElementCS.ownedSignature : 105
		serializationMatchSteps[238] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/*TemplateSignatureCS*/);
		// 239: check-rule basecs::TupleTypeCS.ownedParts : 108
		serializationMatchSteps[239] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 56/*TuplePartCS*/);
		// 240: check-rule basecs::TypeParameterCS.ownedExtends : 42|59|109|119|120
		serializationMatchSteps[240] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 66/*LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS*/);
		// 241: check-rule basecs::TypedElementCS.ownedType : 118
		serializationMatchSteps[241] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/*TypedMultiplicityRefCS*/);
		// 242: check-rule basecs::TypedRefCS.ownedMultiplicity : 62
		serializationMatchSteps[242] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/*MultiplicityCS*/);
		// 243: check-rule basecs::TypedTypeRefCS.ownedBinding : 103
		serializationMatchSteps[243] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 52/*TemplateBindingCS*/);
		// 244: check-rule basecs::TypedTypeRefCS.ownedPathName : 52
		serializationMatchSteps[244] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 21/*LibPathNameCS*/);
		// 245: check-rule basecs::WildcardTypeRefCS.ownedExtends : 42|59|109|119|120
		serializationMatchSteps[245] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 66/*LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS*/);
		// 246: check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[246] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 6/*CurlyBracketedClauseCS*/);
		// 247: check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 80
		serializationMatchSteps[247] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 35/*PathNameCS*/);
		// 248: check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 92
		serializationMatchSteps[248] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 44/*RoundBracketedClauseCS*/);
		// 249: check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 100
		serializationMatchSteps[249] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 50/*SquareBracketedClauseCS*/);
		// 250: check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 10
		serializationMatchSteps[250] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 4/*CollectionLiteralPartCS*/);
		// 251: check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 12
		serializationMatchSteps[251] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 5/*CollectionTypeCS*/);
		// 252: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[252] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 253: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 81
		serializationMatchSteps[253] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 36/*PatternExpCS*/);
		// 254: check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[254] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 255: check-rule essentialoclcs::CollectionPatternCS.ownedParts : 81
		serializationMatchSteps[255] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 36/*PatternExpCS*/);
		// 256: check-rule essentialoclcs::CollectionPatternCS.ownedType : 12
		serializationMatchSteps[256] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 5/*CollectionTypeCS*/);
		// 257: check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62
		serializationMatchSteps[257] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 25/*MultiplicityCS*/);
		// 258: check-rule essentialoclcs::CollectionTypeCS.ownedType : 11|12|59|89|109|111|112|115
		serializationMatchSteps[258] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 61/*CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS*/);
		// 259: check-rule essentialoclcs::ContextCS.ownedExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[259] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 260: check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 97
		serializationMatchSteps[260] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 48/*ShadowPartCS*/);
		// 261: check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[261] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 262: check-rule essentialoclcs::IfExpCS.ownedCondition : 6|9|27|32|36|40|43|57|66|73|75|76|81|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[262] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 76/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 263: check-rule essentialoclcs::IfExpCS.ownedElseExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[263] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 264: check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
		serializationMatchSteps[264] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 9/*ElseIfThenExpCS*/);
		// 265: check-rule essentialoclcs::IfExpCS.ownedThenExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[265] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 266: check-rule essentialoclcs::IfThenExpCS.ownedCondition : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[266] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 267: check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[267] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 268: check-rule essentialoclcs::InfixExpCS.ownedLeft : 6|9|32|36|40|57|66|73|75|76|86|87|88|96|102|106|113|126
		serializationMatchSteps[268] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 72/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 269: check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[269] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 270: check-rule essentialoclcs::LetExpCS.ownedInExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[270] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 271: check-rule essentialoclcs::LetExpCS.ownedVariables : 44
		serializationMatchSteps[271] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 16/*LetVariableCS*/);
		// 272: check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 92
		serializationMatchSteps[272] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 44/*RoundBracketedClauseCS*/);
		// 273: check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 58
		serializationMatchSteps[273] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 23/*MapLiteralPartCS*/);
		// 274: check-rule essentialoclcs::MapLiteralExpCS.ownedType : 59
		serializationMatchSteps[274] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 24/*MapTypeCS*/);
		// 275: check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[275] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 276: check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[276] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 277: check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110
		serializationMatchSteps[277] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 57/*TypeExpCS*/);
		// 278: check-rule essentialoclcs::MapTypeCS.ownedValueType : 110
		serializationMatchSteps[278] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 57/*TypeExpCS*/);
		// 279: check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8
		serializationMatchSteps[279] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3/*CoIteratorVariableCS*/);
		// 280: check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[280] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 281: check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 6|9|27|32|36|40|43|57|66|68|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[281] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 75/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 282: check-rule essentialoclcs::NavigatingArgCS.ownedType : 110
		serializationMatchSteps[282] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/*TypeExpCS*/);
		// 283: check-rule essentialoclcs::NestedExpCS.ownedExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[283] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 284: check-rule essentialoclcs::OperatorExpCS.ownedRight : 43|85
		serializationMatchSteps[284] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 42/*LetExpCS|PrefixedLetExpCS*/);
		// 285: check-rule essentialoclcs::OperatorExpCS.ownedRight : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[285] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 286: check-rule essentialoclcs::OperatorExpCS.ownedRight : 6|9|32|36|40|57|66|73|75|76|86|87|88|96|102|106|113|126
		serializationMatchSteps[286] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 72/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 287: check-rule essentialoclcs::PatternExpCS.ownedPatternType : 110
		serializationMatchSteps[287] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 57/*TypeExpCS*/);
		// 288: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 102
		serializationMatchSteps[288] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 51/*StringLiteralExpCS*/);
		// 289: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 6|9|27|32|36|40|43|57|66|73|75|76|81|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[289] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 76/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 290: check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[290] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 291: check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 107
		serializationMatchSteps[291] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 55/*TupleLiteralPartCS*/);
		// 292: check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 114
		serializationMatchSteps[292] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 60/*TypeLiteralWithMultiplicityCS*/);
		// 293: check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[293] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 6/*CurlyBracketedClauseCS*/);
		// 294: check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 80
		serializationMatchSteps[294] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 35/*PathNameCS*/);
		// 295: check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[295] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 296: check-rule essentialoclcs::VariableCS.ownedInitExpression : 6|9|27|32|36|40|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[296] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 297: check-rule essentialoclcs::VariableCS.ownedType : 110
		serializationMatchSteps[297] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 57/*TypeExpCS*/);
		// 298: check-rule oclstdlibcs::LibIterationCS.ownedAccumulator : 1
		serializationMatchSteps[298] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATOR, 0/*AccumulatorCS*/);
		// 299: check-rule oclstdlibcs::LibIterationCS.ownedIterators : 37
		serializationMatchSteps[299] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, 14/*IteratorCS*/);
		// 300: check-rule oclstdlibcs::LibPackageCS.ownedPrecedences : 84
		serializationMatchSteps[300] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES, 40/*PrecedenceCS*/);
		// 301: check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : 49
		serializationMatchSteps[301] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, 18/*LibOppositeCS*/);
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
		serializationMatchTerms[3] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 4 /* '@' */);
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
		serializationMatchTerms[11] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 9 /* 'false|true' */);
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
		// 27: |ConstraintCS::stereotype.'inv'|
		serializationMatchTerms[27] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 10 /* 'inv' */);
		// 28: |ConstraintCS::stereotype.'post'|
		serializationMatchTerms[28] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 12 /* 'post' */);
		// 29: |ConstraintCS::stereotype.'pre'|
		serializationMatchTerms[29] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 13 /* 'pre' */);
		// 30: |ContextCS::ownedExpression|
		serializationMatchTerms[30] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		// 31: |CurlyBracketedClauseCS::ownedParts|
		serializationMatchTerms[31] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		// 32: |CurlyBracketedClauseCS::value|
		serializationMatchTerms[32] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__VALUE);
		// 33: |DetailCS::values|
		serializationMatchTerms[33] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DETAIL_CS__VALUES);
		// 34: |DocumentationCS::value|
		serializationMatchTerms[34] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		// 35: |ExpSpecificationCS::ownedExpression|
		serializationMatchTerms[35] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		// 36: |IfExpCS::isImplicit|
		serializationMatchTerms[36] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__IS_IMPLICIT);
		// 37: |IfExpCS::ownedCondition|
		serializationMatchTerms[37] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		// 38: |IfExpCS::ownedElseExpression|
		serializationMatchTerms[38] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		// 39: |IfExpCS::ownedIfThenExpressions|
		serializationMatchTerms[39] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		// 40: |IfExpCS::ownedThenExpression|
		serializationMatchTerms[40] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		// 41: |IfThenExpCS::ownedCondition|
		serializationMatchTerms[41] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		// 42: |IfThenExpCS::ownedThenExpression|
		serializationMatchTerms[42] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		// 43: |ImportCS::isAll.'::*'|
		serializationMatchTerms[43] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, 2 /* '::*' */);
		// 44: |ImportCS::ownedPathName|
		serializationMatchTerms[44] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		// 45: |InfixExpCS::ownedLeft|
		serializationMatchTerms[45] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		// 46: |JavaImplementationCS::implementation|
		serializationMatchTerms[46] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION);
		// 47: |LambdaLiteralExpCS::ownedExpressionCS|
		serializationMatchTerms[47] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		// 48: |LambdaParameterCS::ownedContextType|
		serializationMatchTerms[48] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_CONTEXT_TYPE);
		// 49: |LambdaParameterCS::ownedParameters|
		serializationMatchTerms[49] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_PARAMETERS);
		// 50: |LambdaTypeCS::name.'Lambda'|
		serializationMatchTerms[50] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, 5 /* 'Lambda' */);
		// 51: |LambdaTypeCS::ownedContextType|
		serializationMatchTerms[51] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE);
		// 52: |LambdaTypeCS::ownedParameterTypes|
		serializationMatchTerms[52] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES);
		// 53: |LambdaTypeCS::ownedResultType|
		serializationMatchTerms[53] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE);
		// 54: |LetExpCS::isImplicit|
		serializationMatchTerms[54] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__IS_IMPLICIT);
		// 55: |LetExpCS::ownedInExpression|
		serializationMatchTerms[55] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		// 56: |LetExpCS::ownedVariables|
		serializationMatchTerms[56] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		// 57: |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchTerms[57] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// 58: |LibClassCS::metaclassName|
		serializationMatchTerms[58] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME);
		// 59: |LibIterationCS::isInvalidating.'invalidating'|
		serializationMatchTerms[59] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING, 11 /* 'invalidating' */);
		// 60: |LibIterationCS::isValidating.'validating'|
		serializationMatchTerms[60] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING, 17 /* 'validating' */);
		// 61: |LibIterationCS::ownedAccumulator|
		serializationMatchTerms[61] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATOR);
		// 62: |LibIterationCS::ownedIterators|
		serializationMatchTerms[62] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS);
		// 63: |LibOperationCS::isInvalidating.'invalidating'|
		serializationMatchTerms[63] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING, 11 /* 'invalidating' */);
		// 64: |LibOperationCS::isStatic.'static'|
		serializationMatchTerms[64] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC, 15 /* 'static' */);
		// 65: |LibOperationCS::isValidating.'validating'|
		serializationMatchTerms[65] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING, 17 /* 'validating' */);
		// 66: |LibOperationCS::precedence|
		serializationMatchTerms[66] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE);
		// 67: |LibPackageCS::ownedPrecedences|
		serializationMatchTerms[67] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES);
		// 68: |LibPropertyCS::isStatic.'static'|
		serializationMatchTerms[68] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC, 15 /* 'static' */);
		// 69: |LibPropertyCS::ownedOpposite|
		serializationMatchTerms[69] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE);
		// 70: |MapLiteralExpCS::ownedParts|
		serializationMatchTerms[70] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		// 71: |MapLiteralExpCS::ownedType|
		serializationMatchTerms[71] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		// 72: |MapLiteralPartCS::ownedKey|
		serializationMatchTerms[72] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		// 73: |MapLiteralPartCS::ownedValue|
		serializationMatchTerms[73] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		// 74: |MapTypeCS::name.'Map'|
		serializationMatchTerms[74] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 6 /* 'Map' */);
		// 75: |MapTypeCS::ownedKeyType|
		serializationMatchTerms[75] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		// 76: |MapTypeCS::ownedValueType|
		serializationMatchTerms[76] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		// 77: |ModelElementCS::ownedAnnotations|
		serializationMatchTerms[77] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		// 78: |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[78] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// 79: |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[79] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// 80: |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[80] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 19 /* '|1' */);
		// 81: |MultiplicityCS::isNullFree|
		serializationMatchTerms[81] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE);
		// 82: |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[82] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */);
		// 83: |NamedElementCS::name|
		serializationMatchTerms[83] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// 84: |NavigatingArgCS::ownedCoIterator|
		serializationMatchTerms[84] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		// 85: |NavigatingArgCS::ownedInitExpression|
		serializationMatchTerms[85] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		// 86: |NavigatingArgCS::ownedNameExpression|
		serializationMatchTerms[86] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		// 87: |NavigatingArgCS::ownedType|
		serializationMatchTerms[87] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		// 88: |NavigatingArgCS::prefix.','|
		serializationMatchTerms[88] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */);
		// 89: |NavigatingArgCS::prefix.';'|
		serializationMatchTerms[89] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 3 /* ';' */);
		// 90: |NavigatingArgCS::prefix.'|'|
		serializationMatchTerms[90] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 18 /* '|' */);
		// 91: |NavigatingArgCS::prefix|
		serializationMatchTerms[91] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX);
		// 92: |NestedExpCS::ownedExpression|
		serializationMatchTerms[92] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		// 93: |NumberLiteralExpCS::symbol|
		serializationMatchTerms[93] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		// 94: |OperationCS::ownedBodyExpressions|
		serializationMatchTerms[94] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		// 95: |OperationCS::ownedExceptions|
		serializationMatchTerms[95] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		// 96: |OperationCS::ownedParameters|
		serializationMatchTerms[96] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		// 97: |OperationCS::ownedPostconditions|
		serializationMatchTerms[97] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		// 98: |OperationCS::ownedPreconditions|
		serializationMatchTerms[98] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		// 99: |OperatorExpCS::ownedRight|
		serializationMatchTerms[99] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		// 100: |PackageCS::nsPrefix|
		serializationMatchTerms[100] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		// 101: |PackageCS::nsURI|
		serializationMatchTerms[101] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		// 102: |PackageCS::ownedClasses|
		serializationMatchTerms[102] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		// 103: |PackageOwnerCS::ownedPackages|
		serializationMatchTerms[103] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		// 104: |PathElementCS::referredElement|
		serializationMatchTerms[104] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// 105: |PathNameCS::ownedPathElements|
		serializationMatchTerms[105] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// 106: |PatternExpCS::ownedPatternType|
		serializationMatchTerms[106] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		// 107: |PatternExpCS::patternVariableName|
		serializationMatchTerms[107] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		// 108: |PrecedenceCS::isRightAssociative.'right'|
		serializationMatchTerms[108] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE, 14 /* 'right' */);
		// 109: |PrecedenceCS::isRightAssociative|
		serializationMatchTerms[109] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE);
		// 110: |PrimitiveTypeRefCS::name|
		serializationMatchTerms[110] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		// 111: |RootCS::ownedImports|
		serializationMatchTerms[111] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		// 112: |SelfExpCS::name|
		serializationMatchTerms[112] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SELF_EXP_CS__NAME);
		// 113: |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[113] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// 114: |ShadowPartCS::referredProperty|
		serializationMatchTerms[114] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// 115: |SpecificationCS::exprString|
		serializationMatchTerms[115] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		// 116: |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[116] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// 117: |StringLiteralExpCS::segments|
		serializationMatchTerms[117] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// 118: |StructuralFeatureCS::default|
		serializationMatchTerms[118] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		// 119: |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchTerms[119] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		// 120: |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchTerms[120] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, 8 /* 'abstract' */);
		// 121: |StructuredClassCS::isInterface|
		serializationMatchTerms[121] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE);
		// 122: |StructuredClassCS::ownedMetaclass|
		serializationMatchTerms[122] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_METACLASS);
		// 123: |StructuredClassCS::ownedOperations|
		serializationMatchTerms[123] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		// 124: |StructuredClassCS::ownedProperties|
		serializationMatchTerms[124] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		// 125: |StructuredClassCS::ownedSuperTypes|
		serializationMatchTerms[125] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		// 126: |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[126] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// 127: |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[127] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// 128: |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[128] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// 129: |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[129] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// 130: |TemplateableElementCS::ownedSignature|
		serializationMatchTerms[130] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		// 131: |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[131] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// 132: |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[132] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 7 /* 'Tuple' */);
		// 133: |TupleTypeCS::ownedParts|
		serializationMatchTerms[133] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// 134: |TypeLiteralExpCS::ownedPathName|
		serializationMatchTerms[134] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_PATH_NAME);
		// 135: |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[135] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// 136: |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[136] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// 137: |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[137] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// 138: |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[138] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// 139: |TypeParameterCS::ownedExtends|
		serializationMatchTerms[139] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// 140: |TypedElementCS::ownedType|
		serializationMatchTerms[140] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		// 141: |TypedElementCS::qualifiers|
		serializationMatchTerms[141] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		// 142: |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[142] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// 143: |TypedTypeRefCS::isTypeof.'typeof'|
		serializationMatchTerms[143] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF, 16 /* 'typeof' */);
		// 144: |TypedTypeRefCS::isTypeof|
		serializationMatchTerms[144] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF);
		// 145: |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[145] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// 146: |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[146] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// 147: |VariableCS::ownedInitExpression|
		serializationMatchTerms[147] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		// 148: |VariableCS::ownedType|
		serializationMatchTerms[148] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		// 149: |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[149] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// 150: |WildcardTypeRefCS::ownedSuper|
		serializationMatchTerms[150] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_SUPER);
		// 151: (|AbstractNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[151] = createSerializationMatchTermSubtract(5, 1);
		// 152: (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchTerms[152] = createSerializationMatchTermSubtract(10, 1);
		// 153: (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchTerms[153] = createSerializationMatchTermGreaterThan(10, 0);
		// 154: (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
		serializationMatchTerms[154] = createSerializationMatchTermSubtract(11, 1);
		// 155: (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[155] = createSerializationMatchTermSubtract(14, 1);
		// 156: (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[156] = createSerializationMatchTermGreaterThan(14, 0);
		// 157: (|CollectionLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[157] = createSerializationMatchTermSubtract(15, 1);
		// 158: (|CollectionLiteralPartCS::ownedExpression| - 1)
		serializationMatchTerms[158] = createSerializationMatchTermSubtract(16, 1);
		// 159: (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchTerms[159] = createSerializationMatchTermSubtract(18, 1);
		// 160: (|CollectionPatternCS::ownedType| - 1)
		serializationMatchTerms[160] = createSerializationMatchTermSubtract(20, 1);
		// 161: (|CollectionTypeCS::name| - 1)
		serializationMatchTerms[161] = createSerializationMatchTermSubtract(22, 1);
		// 162: (|ConstraintCS::ownedSpecification| - 1)
		serializationMatchTerms[162] = createSerializationMatchTermSubtract(26, 1);
		// 163: (|ConstraintCS::stereotype.'inv'| - 1)
		serializationMatchTerms[163] = createSerializationMatchTermSubtract(27, 1);
		// 164: (|ConstraintCS::stereotype.'post'| - 1)
		serializationMatchTerms[164] = createSerializationMatchTermSubtract(28, 1);
		// 165: (|ConstraintCS::stereotype.'pre'| - 1)
		serializationMatchTerms[165] = createSerializationMatchTermSubtract(29, 1);
		// 166: (|ContextCS::ownedExpression| - 1)
		serializationMatchTerms[166] = createSerializationMatchTermSubtract(30, 1);
		// 167: (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchTerms[167] = createSerializationMatchTermSubtract(31, 1);
		// 168: (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchTerms[168] = createSerializationMatchTermGreaterThan(31, 0);
		// 169: (|ExpSpecificationCS::ownedExpression| - 1)
		serializationMatchTerms[169] = createSerializationMatchTermSubtract(35, 1);
		// 170: (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[170] = createSerializationMatchTermSubtract(37, 1);
		// 171: (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[171] = createSerializationMatchTermSubtract(38, 1);
		// 172: (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[172] = createSerializationMatchTermSubtract(40, 1);
		// 173: (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[173] = createSerializationMatchTermSubtract(41, 1);
		// 174: (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[174] = createSerializationMatchTermSubtract(42, 1);
		// 175: (|ImportCS::ownedPathName| - 1)
		serializationMatchTerms[175] = createSerializationMatchTermSubtract(44, 1);
		// 176: (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[176] = createSerializationMatchTermSubtract(45, 1);
		// 177: (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[177] = createSerializationMatchTermSubtract(47, 1);
		// 178: (|LambdaParameterCS::ownedContextType| - 1)
		serializationMatchTerms[178] = createSerializationMatchTermSubtract(48, 1);
		// 179: (|LambdaParameterCS::ownedParameters| - 1)
		serializationMatchTerms[179] = createSerializationMatchTermSubtract(49, 1);
		// 180: (|LambdaParameterCS::ownedParameters| > 0)
		serializationMatchTerms[180] = createSerializationMatchTermGreaterThan(49, 0);
		// 181: (|LambdaTypeCS::name.'Lambda'| - 1)
		serializationMatchTerms[181] = createSerializationMatchTermSubtract(50, 1);
		// 182: (|LambdaTypeCS::ownedContextType| - 1)
		serializationMatchTerms[182] = createSerializationMatchTermSubtract(51, 1);
		// 183: (|LambdaTypeCS::ownedParameterTypes| - 1)
		serializationMatchTerms[183] = createSerializationMatchTermSubtract(52, 1);
		// 184: (|LambdaTypeCS::ownedParameterTypes| > 0)
		serializationMatchTerms[184] = createSerializationMatchTermGreaterThan(52, 0);
		// 185: (|LambdaTypeCS::ownedResultType| - 1)
		serializationMatchTerms[185] = createSerializationMatchTermSubtract(53, 1);
		// 186: (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[186] = createSerializationMatchTermSubtract(55, 1);
		// 187: (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[187] = createSerializationMatchTermSubtract(56, 1);
		// 188: (|LibIterationCS::ownedIterators| - 1)
		serializationMatchTerms[188] = createSerializationMatchTermSubtract(62, 1);
		// 189: (|LibPackageCS::ownedPrecedences| > 0)
		serializationMatchTerms[189] = createSerializationMatchTermGreaterThan(67, 0);
		// 190: (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[190] = createSerializationMatchTermSubtract(70, 1);
		// 191: (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[191] = createSerializationMatchTermGreaterThan(70, 0);
		// 192: (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[192] = createSerializationMatchTermSubtract(71, 1);
		// 193: (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[193] = createSerializationMatchTermSubtract(72, 1);
		// 194: (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[194] = createSerializationMatchTermSubtract(73, 1);
		// 195: (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[195] = createSerializationMatchTermSubtract(74, 1);
		// 196: (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[196] = createSerializationMatchTermSubtract(75, 2);
		// 197: (|ModelElementCS::ownedAnnotations| - 1)
		serializationMatchTerms[197] = createSerializationMatchTermSubtract(77, 1);
		// 198: (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[198] = createSerializationMatchTermSubtract(78, 1);
		// 199: (|MultiplicityCS::isNullFree.'|1'| - 1)
		serializationMatchTerms[199] = createSerializationMatchTermSubtract(80, 1);
		// 200: (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[200] = createSerializationMatchTermSubtract(82, 1);
		// 201: (|NamedElementCS::name| - 1)
		serializationMatchTerms[201] = createSerializationMatchTermSubtract(83, 1);
		// 202: (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[202] = createSerializationMatchTermSubtract(84, 1);
		// 203: (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[203] = createSerializationMatchTermSubtract(85, 1);
		// 204: (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[204] = createSerializationMatchTermSubtract(86, 1);
		// 205: (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[205] = createSerializationMatchTermSubtract(87, 1);
		// 206: (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[206] = createSerializationMatchTermSubtract(88, 1);
		// 207: (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[207] = createSerializationMatchTermSubtract(89, 1);
		// 208: (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[208] = createSerializationMatchTermSubtract(90, 1);
		// 209: (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[209] = createSerializationMatchTermSubtract(92, 1);
		// 210: (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[210] = createSerializationMatchTermSubtract(93, 1);
		// 211: (|OperationCS::ownedParameters| - 1)
		serializationMatchTerms[211] = createSerializationMatchTermSubtract(96, 1);
		// 212: (|OperationCS::ownedParameters| > 0)
		serializationMatchTerms[212] = createSerializationMatchTermGreaterThan(96, 0);
		// 213: (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[213] = createSerializationMatchTermSubtract(99, 1);
		// 214: (|PackageCS::nsPrefix| - V0)
		serializationMatchTerms[214] = createSerializationMatchTermSubtract(100, 2);
		// 215: (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[215] = createSerializationMatchTermSubtract(104, 1);
		// 216: (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[216] = createSerializationMatchTermSubtract(105, 1);
		// 217: (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[217] = createSerializationMatchTermSubtract(106, 1);
		// 218: (|PrecedenceCS::isRightAssociative.'right'| - 1)
		serializationMatchTerms[218] = createSerializationMatchTermSubtract(108, 1);
		// 219: (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[219] = createSerializationMatchTermSubtract(110, 1);
		// 220: (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[220] = createSerializationMatchTermSubtract(113, 1);
		// 221: (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[221] = createSerializationMatchTermSubtract(114, 1);
		// 222: (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[222] = createSerializationMatchTermSubtract(116, 1);
		// 223: (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchTerms[223] = createSerializationMatchTermSubtract(125, 1);
		// 224: (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchTerms[224] = createSerializationMatchTermGreaterThan(125, 0);
		// 225: (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[225] = createSerializationMatchTermSubtract(127, 1);
		// 226: (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[226] = createSerializationMatchTermSubtract(128, 1);
		// 227: (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[227] = createSerializationMatchTermSubtract(129, 1);
		// 228: (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[228] = createSerializationMatchTermSubtract(131, 1);
		// 229: (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[229] = createSerializationMatchTermSubtract(132, 1);
		// 230: (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[230] = createSerializationMatchTermSubtract(133, 1);
		// 231: (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[231] = createSerializationMatchTermGreaterThan(133, 0);
		// 232: (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[232] = createSerializationMatchTermSubtract(135, 1);
		// 233: (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[233] = createSerializationMatchTermSubtract(137, 1);
		// 234: (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[234] = createSerializationMatchTermSubtract(139, 1);
		// 235: (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[235] = createSerializationMatchTermGreaterThan(139, 0);
		// 236: (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[236] = createSerializationMatchTermSubtract(140, 1);
		// 237: (|TypedTypeRefCS::isTypeof.'typeof'| - 1)
		serializationMatchTerms[237] = createSerializationMatchTermSubtract(143, 1);
		// 238: (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[238] = createSerializationMatchTermSubtract(146, 1);
		// 239: (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[239] = createSerializationMatchTermSubtract(147, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS-0(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] = createSerializationRule("FirstPathElementCS-0", 28,
			createSerializationMatchSteps(
				46		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				246		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::MultiplicityBoundsCS-0(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { ".." upperBound=UPPER }[?] }
		serializationRules[1] = createSerializationRule("MultiplicityBoundsCS-0", 61,
			createSerializationMatchSteps(
				70		/* assert |MultiplicityCS::isNullFree| == 0 */,
				124		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				31		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				175		/* V00*2-steps || value */,
				127		/* '..' || no-space value no-space */,
				116		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-0(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" }
		serializationRules[2] = createSerializationRule("MultiplicityCS-0", 62,
			createSerializationMatchSteps(
				70		/* assert |MultiplicityCS::isNullFree| == 0 */,
				124		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				31		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				136		/* '[' || no-space value no-space */,
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				175		/* V00*2-steps || value */,
				127		/* '..' || no-space value no-space */,
				116		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				170		/* '|?' || no-space value no-space */,
				137		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-1(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree?="|1" "]" }
		serializationRules[3] = createSerializationRule("MultiplicityCS-1", 62,
			createSerializationMatchSteps(
				32		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				124		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				31		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				136		/* '[' || no-space value no-space */,
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				175		/* V00*2-steps || value */,
				127		/* '..' || no-space value no-space */,
				116		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				5		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				137		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(19/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-2(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" }
		serializationRules[4] = createSerializationRule("MultiplicityCS-2", 62,
			createSerializationMatchSteps(
				70		/* assert |MultiplicityCS::isNullFree| == 0 */,
				124		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				31		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				136		/* '[' || no-space value no-space */,
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				175		/* V00*2-steps || value */,
				127		/* '..' || no-space value no-space */,
				116		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				137		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-3(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "|?" "]" }
		serializationRules[5] = createSerializationRule("MultiplicityCS-3", 62,
			createSerializationMatchSteps(
				70		/* assert |MultiplicityCS::isNullFree| == 0 */,
				33		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				136		/* '[' || no-space value no-space */,
				113		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				170		/* '|?' || no-space value no-space */,
				137		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-4(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} isNullFree?="|1" "]" }
		serializationRules[6] = createSerializationRule("MultiplicityCS-4", 62,
			createSerializationMatchSteps(
				32		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				33		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				136		/* '[' || no-space value no-space */,
				113		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				5		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				137		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(19/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-5(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "]" }
		serializationRules[7] = createSerializationRule("MultiplicityCS-5", 62,
			createSerializationMatchSteps(
				70		/* assert |MultiplicityCS::isNullFree| == 0 */,
				33		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				136		/* '[' || no-space value no-space */,
				113		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				137		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityStringCS-0(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		serializationRules[8] = createSerializationRule("MultiplicityStringCS-0", 63,
			createSerializationMatchSteps(
				70		/* assert |MultiplicityCS::isNullFree| == 0 */,
				33		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				113		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::NextPathElementCS-0(basecs::PathElementCS): referredElement=UnreservedName
		serializationRules[9] = createSerializationRule("NextPathElementCS-0", 74,
			createSerializationMatchSteps(
				46		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				245		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::PathNameCS-0(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[10] = createSerializationRule("PathNameCS-0", 80,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				233		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */,
				176		/* V00*2-steps || value */,
				129		/* '::' || no-space value no-space */,
				235		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 30/* FirstPathElementCS,NextPathElementCS */,
					(28/*FirstPathElementCS*/ << 4) | 0 /*[1]*/,
					(74/*NextPathElementCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TemplateBindingCS-0(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] = createSerializationRule("TemplateBindingCS-0", 103,
			createSerializationMatchSteps(
				234		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : MultiplicityCS */,
				235		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : TemplateParameterSubstitutionCS */,
				161		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				107		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			),
			createSerializationSteps(
				89		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				176		/* V00*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				89		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				182		/* V01*1-steps || value */,
				60		/* TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 53/* TemplateParameterSubstitutionCS */,
					(104/*TemplateParameterSubstitutionCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TemplateParameterSubstitutionCS-0(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] = createSerializationRule("TemplateParameterSubstitutionCS-0", 104,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				236		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
				53		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			),
			createSerializationSteps(
				23		/* TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 63/* TypeRefCS */,
					(117/*TypeRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::TemplateSignatureCS-0(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[13] = createSerializationRule("TemplateSignatureCS-0", 105,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				237		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				108		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				121		/* '(' || no-space value no-space */,
				68		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				176		/* V00*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				68		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				122		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 62/* TypeParameterCS */,
					(116/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TypeParameterCS-0(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[14] = createSerializationRule("TypeParameterCS-0", 116,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				240		/* check-rule basecs::TypeParameterCS.ownedExtends : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				111		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				151		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				219		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				146		/* 'extends' || soft-space value soft-space */,
				45		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */,
				185		/* V01*2-steps || value */,
				119		/* '&&' || soft-space value soft-space */,
				45		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 65/* TypedRefCS */,
					(119/*TypedRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::UnreservedPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=NextPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[15] = createSerializationRule("UnreservedPathNameCS-0", 128,
			createSerializationMatchSteps(
				229		/* check-rule basecs::PathNameCS.ownedPathElements : NextPathElementCS */,
				105		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			),
			createSerializationSteps(
				235		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */,
				176		/* V00*2-steps || value */,
				129		/* '::' || no-space value no-space */,
				235		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 29/* NextPathElementCS */,
					(74/*NextPathElementCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::WildcardTypeRefCS-0(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[16] = createSerializationRule("WildcardTypeRefCS-0", 131,
			createSerializationMatchSteps(
				98		/* assert |WildcardTypeRefCS::ownedSuper| == 0 */,
				245		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				139		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			),
			createSerializationSteps(
				133		/* '?' || soft-space value soft-space */,
				175		/* V00*2-steps || value */,
				146		/* 'extends' || soft-space value soft-space */,
				46		/* WildcardTypeRefCS::ownedExtends=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 65/* TypedRefCS */,
					(119/*TypedRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::BooleanLiteralExpCS-0(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[17] = createSerializationRule("BooleanLiteralExpCS-0", 6,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			),
			createSerializationSteps(
				114		/* BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, false,
					(9/*'false|true'*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CoIteratorVariableCS-0(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[18] = createSerializationRule("CoIteratorVariableCS-0", 8,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				97		/* assert |VariableCS::ownedInitExpression| == 0 */,
				297		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				138		/* assign V0 = |VariableCS::ownedType| */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				219		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				175		/* V00*2-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				101		/* VariableCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralExpCS-0(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[19] = createSerializationRule("CollectionLiteralExpCS-0", 9,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				250		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS */,
				251		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS */,
				2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				100		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				142		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				94		/* CollectionLiteralExpCS::ownedType=CollectionTypeCS || value */,
				168		/* '{' || soft-space value push soft-new-line */,
				178		/* V00*4-steps || value */,
				69		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				69		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 4/* CollectionLiteralPartCS */,
					(10/*CollectionLiteralPartCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 5/* CollectionTypeCS */,
					(12/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionLiteralPartCS-0(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] }
		serializationRules[20] = createSerializationRule("CollectionLiteralPartCS-0", 10,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				252		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				254		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				113		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				39		/* CollectionLiteralPartCS::ownedExpression=ExpCS || value */,
				175		/* V00*2-steps || value */,
				127		/* '..' || no-space value no-space */,
				57		/* CollectionLiteralPartCS::ownedLastExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralPartCS-1(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
		serializationRules[21] = createSerializationRule("CollectionLiteralPartCS-1", 10,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				64		/* assert |CollectionLiteralPartCS::ownedLastExpression| == 0 */,
				253		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : PatternExpCS */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				40		/* CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 36/* PatternExpCS */,
					(81/*PatternExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionPatternCS-0(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" }
		serializationRules[22] = createSerializationRule("CollectionPatternCS-0", 11,
			createSerializationMatchSteps(
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				65		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				255		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				256		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				114		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				143		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				95		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				168		/* '{' || soft-space value push soft-new-line */,
				180		/* V00*6-steps || value */,
				70		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				70		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				124		/* '++' || soft-space value soft-space */,
				111		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 36/* PatternExpCS */,
					(81/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 5/* CollectionTypeCS */,
					(12/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionTypeCS-0(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] }
		serializationRules[23] = createSerializationRule("CollectionTypeCS-0", 12,
			createSerializationMatchSteps(
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				257		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				258		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				115		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				153		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				15		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				121		/* '(' || no-space value no-space */,
				96		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				182		/* V01*1-steps || value */,
				29		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				122		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 58/* TypeExpWithoutMultiplicityCS */,
					(111/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS-0(essentialoclcs::CurlyBracketedClauseCS): { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" }
		serializationRules[24] = createSerializationRule("CurlyBracketedClauseCS-0", 14,
			createSerializationMatchSteps(
				66		/* assert |CurlyBracketedClauseCS::value| == 0 */,
				260		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : ShadowPartCS */,
				101		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				144		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				168		/* '{' || soft-space value push soft-new-line */,
				178		/* V00*4-steps || value */,
				71		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				71		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 48/* ShadowPartCS */,
					(97/*ShadowPartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::ElseIfThenExpCS-0(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[25] = createSerializationRule("ElseIfThenExpCS-0", 20,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				266		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				267		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				16		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				15		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				144		/* 'elseif' || soft-new-line pop soft-space value push soft-space */,
				31		/* IfThenExpCS::ownedCondition=ExpCS || value */,
				164		/* 'then' || pop value push soft-space */,
				93		/* IfThenExpCS::ownedThenExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::ExpCS-18(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } }
		serializationRules[26] = createSerializationRule("ExpCS-18", 27,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				268		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				285		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				44		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				18		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				58		/* InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value */,
				223		/* NamedElementCS::name=BinaryOperatorName || soft-space value soft-space */,
				240		/* OperatorExpCS::ownedRight=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 43/* PrefixedPrimaryExpCS */,
					(86/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::IfExpCS-0(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[27] = createSerializationRule("IfExpCS-0", 32,
			createSerializationMatchSteps(
				67		/* assert |IfExpCS::isImplicit| == 0 */,
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				262		/* check-rule essentialoclcs::IfExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				263		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				264		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS */,
				265		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				13		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				118		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				14		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				12		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				147		/* 'if' || soft-new-line value push soft-space */,
				30		/* IfExpCS::ownedCondition=ExpCS|PatternExpCS || value */,
				165		/* 'then' || pop soft-space value push soft-space */,
				92		/* IfExpCS::ownedThenExpression=ExpCS || value */,
				173		/* V00*1-steps || value */,
				47		/* IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value */,
				143		/* 'else' || soft-new-line pop value push soft-space */,
				38		/* IfExpCS::ownedElseExpression=ExpCS || value */,
				145		/* 'endif' || soft-new-line pop value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 37/* ExpCS,PatternExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/,
					(81/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 9/* ElseIfThenExpCS */,
					(20/*ElseIfThenExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::InvalidLiteralExpCS-0(essentialoclcs::InvalidLiteralExpCS): "invalid"
		serializationRules[28] = createSerializationRule("InvalidLiteralExpCS-0", 36,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				151		/* 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::LambdaLiteralExpCS-0(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[29] = createSerializationRule("LambdaLiteralExpCS-0", 40,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				269		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				19		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				134		/* 'Lambda' || soft-space value soft-space */,
				168		/* '{' || soft-space value push soft-new-line */,
				44		/* LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::LetExpCS-0(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[30] = createSerializationRule("LetExpCS-0", 43,
			createSerializationMatchSteps(
				68		/* assert |LetExpCS::isImplicit| == 0 */,
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				270		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				271		/* check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS */,
				24		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				103		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				154		/* 'let' || soft-space value push */,
				104		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				176		/* V00*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				104		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				150		/* 'in' || soft-space pop value soft-new-line */,
				49		/* LetExpCS::ownedInExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 16/* LetVariableCS */,
					(44/*LetVariableCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::LetVariableCS-0(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[31] = createSerializationRule("LetVariableCS-0", 44,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				296		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				272		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				297		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				60		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				165		/* assign V1 = |VariableCS::ownedType| */,
				120		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				219		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				172		/* V00*1-steps || value */,
				85		/* LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				184		/* V01*2-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				101		/* VariableCS::ownedType=TypeExpCS || value */,
				131		/* '=' || soft-space value soft-space */,
				53		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 44/* RoundBracketedClauseCS */,
					(92/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::MapLiteralExpCS-0(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[32] = createSerializationRule("MapLiteralExpCS-0", 57,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				273		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS */,
				274		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS */,
				25		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				104		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				149		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				97		/* MapLiteralExpCS::ownedType=MapTypeCS || value */,
				168		/* '{' || soft-space value push soft-new-line */,
				178		/* V00*4-steps || value */,
				72		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				72		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 23/* MapLiteralPartCS */,
					(58/*MapLiteralPartCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 24/* MapTypeCS */,
					(59/*MapTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapLiteralPartCS-0(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS {"with"|"<-"} ownedValue=ExpCS }
		serializationRules[33] = createSerializationRule("MapLiteralPartCS-0", 58,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				275		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				276		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				27		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				26		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				55		/* MapLiteralPartCS::ownedKey=ExpCS || value */,
				167		/* 'with' || value */,
				102		/* MapLiteralPartCS::ownedValue=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapTypeCS-0(essentialoclcs::MapTypeCS): { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] }
		serializationRules[34] = createSerializationRule("MapTypeCS-0", 59,
			createSerializationMatchSteps(
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				277		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				278		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				123		/* assign V0 = |MapTypeCS::ownedValueType| */,
				29		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				28		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				17		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				121		/* '(' || no-space value no-space */,
				56		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				126		/* ',' || no-space value soft-space */,
				103		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				122		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(6/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::Model-0(essentialoclcs::ContextCS): ownedExpression=ExpCS
		serializationRules[35] = createSerializationRule("Model-0", 60,
			createSerializationMatchSteps(
				71		/* assert |NamedElementCS::name| == 0 */,
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				83		/* assert |RootCS::ownedImports| == 0 */,
				259		/* check-rule essentialoclcs::ContextCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				10		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				41		/* ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NameExpCS-0(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre?="@" "pre" }[?] }
		serializationRules[36] = createSerializationRule("NameExpCS-0", 66,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				246		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				247		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS */,
				248		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				249		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS */,
				183		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				172		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				152		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				112		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				75		/* AbstractNameExpCS::ownedPathName=PathNameCS || value */,
				173		/* V00*1-steps || value */,
				88		/* AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value */,
				182		/* V01*1-steps || value */,
				84		/* AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				192		/* V02*1-steps || value */,
				35		/* AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				200		/* V03*2-steps || value */,
				6		/* AbstractNameExpCS::isPre?='@' || soft-space value soft-space */,
				160		/* 'pre' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, false,
					(4/*'@'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 6/* CurlyBracketedClauseCS */,
					(14/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 35/* PathNameCS */,
					(80/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 44/* RoundBracketedClauseCS */,
					(92/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 50/* SquareBracketedClauseCS */,
					(100/*SquareBracketedClauseCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-0(essentialoclcs::NavigatingArgCS): { ":" ownedType=TypeExpCS }
		serializationRules[37] = createSerializationRule("NavigatingArgCS-0", 67,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				72		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				73		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				74		/* assert |NavigatingArgCS::ownedNameExpression| == 0 */,
				76		/* assert |NavigatingArgCS::prefix| == 0 */,
				282		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				38		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				128		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-1(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[38] = createSerializationRule("NavigatingArgCS-1", 67,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				76		/* assert |NavigatingArgCS::prefix| == 0 */,
				279		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				280		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				282		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				159		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				126		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				38		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				128		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				175		/* V00*2-steps || value */,
				167		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				184		/* V01*2-steps || value */,
				131		/* '=' || soft-space value soft-space */,
				50		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3/* CoIteratorVariableCS */,
					(8/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-2(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[39] = createSerializationRule("NavigatingArgCS-2", 67,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				76		/* assert |NavigatingArgCS::prefix| == 0 */,
				279		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				280		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				282		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				36		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				158		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				128		/* assign V0 = |NavigatingArgCS::ownedType| */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				175		/* V00*2-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				184		/* V01*2-steps || value */,
				167		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				149		/* 'in' || soft-space value soft-space */,
				50		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3/* CoIteratorVariableCS */,
					(8/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-3(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[40] = createSerializationRule("NavigatingArgCS-3", 67,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				75		/* assert |NavigatingArgCS::ownedType| == 0 */,
				76		/* assert |NavigatingArgCS::prefix| == 0 */,
				279		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				280		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				127		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				35		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				167		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				175		/* V00*2-steps || value */,
				131		/* '=' || soft-space value soft-space */,
				50		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3/* CoIteratorVariableCS */,
					(8/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-4(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
		serializationRules[41] = createSerializationRule("NavigatingArgCS-4", 67,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				72		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				73		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				75		/* assert |NavigatingArgCS::ownedType| == 0 */,
				76		/* assert |NavigatingArgCS::prefix| == 0 */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				63		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingBarArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[42] = createSerializationRule("NavigatingBarArgCS-0", 69,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				72		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				280		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				282		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				128		/* assign V0 = |NavigatingArgCS::ownedType| */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				41		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				159		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				107		/* NavigatingArgCS::prefix='|' || soft-space value soft-space */,
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				179		/* V00*5-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				184		/* V01*2-steps || value */,
				131		/* '=' || soft-space value soft-space */,
				50		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(18/*'|'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[43] = createSerializationRule("NavigatingCommaArgCS-0", 70,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				279		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				280		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				282		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				159		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				126		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				38		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				39		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				109		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				128		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				175		/* V00*2-steps || value */,
				167		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				184		/* V01*2-steps || value */,
				131		/* '=' || soft-space value soft-space */,
				50		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3/* CoIteratorVariableCS */,
					(8/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-1(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[44] = createSerializationRule("NavigatingCommaArgCS-1", 70,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				279		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				280		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				282		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				36		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				158		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				128		/* assign V0 = |NavigatingArgCS::ownedType| */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				39		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				109		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				175		/* V00*2-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				184		/* V01*2-steps || value */,
				167		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				149		/* 'in' || soft-space value soft-space */,
				50		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3/* CoIteratorVariableCS */,
					(8/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-2(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[45] = createSerializationRule("NavigatingCommaArgCS-2", 70,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				75		/* assert |NavigatingArgCS::ownedType| == 0 */,
				279		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				280		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				127		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				35		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				39		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				109		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				167		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				175		/* V00*2-steps || value */,
				131		/* '=' || soft-space value soft-space */,
				50		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3/* CoIteratorVariableCS */,
					(8/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-3(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS }
		serializationRules[46] = createSerializationRule("NavigatingCommaArgCS-3", 70,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				72		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				73		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				75		/* assert |NavigatingArgCS::ownedType| == 0 */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				39		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				109		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingSemiArgCS-0(essentialoclcs::NavigatingArgCS): { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[47] = createSerializationRule("NavigatingSemiArgCS-0", 71,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				72		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				280		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				281		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				282		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				128		/* assign V0 = |NavigatingArgCS::ownedType| */,
				37		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				40		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				159		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				108		/* NavigatingArgCS::prefix=';' || no-space value soft-new-line */,
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				179		/* V00*5-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				98		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				184		/* V01*2-steps || value */,
				131		/* '=' || soft-space value soft-space */,
				50		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(3/*';'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NestedExpCS-0(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[48] = createSerializationRule("NestedExpCS-0", 73,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				283		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				42		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				120		/* '(' || value no-space */,
				43		/* NestedExpCS::ownedExpression=ExpCS || value */,
				122		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NullLiteralExpCS-0(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[49] = createSerializationRule("NullLiteralExpCS-0", 75,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				156		/* 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::NumberLiteralExpCS-0(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[50] = createSerializationRule("NumberLiteralExpCS-0", 76,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				43		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			),
			createSerializationSteps(
				115		/* NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::PatternExpCS-0(essentialoclcs::PatternExpCS): { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS }
		serializationRules[51] = createSerializationRule("PatternExpCS-0", 81,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				287		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : TypeExpCS */,
				48		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				130		/* assign V0 = |PatternExpCS::patternVariableName| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				172		/* V00*1-steps || value */,
				105		/* PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				80		/* PatternExpCS::ownedPatternType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedLetExpCS-1(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		serializationRules[52] = createSerializationRule("PrefixedLetExpCS-1", 85,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				284		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : LetExpCS|PrefixedLetExpCS */,
				44		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				218		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				241		/* OperatorExpCS::ownedRight=PrefixedLetExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 41/* PrefixedLetExpCS */,
					(85/*PrefixedLetExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS-15(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[53] = createSerializationRule("PrefixedPrimaryExpCS-15", 86,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				286		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				44		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				218		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				242		/* OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 43/* PrefixedPrimaryExpCS */,
					(86/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrimitiveTypeCS-0(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		serializationRules[54] = createSerializationRule("PrimitiveTypeCS-0", 89,
			createSerializationMatchSteps(
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				50		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				18		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::RoundBracketedClauseCS-0(essentialoclcs::RoundBracketedClauseCS): { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" }
		serializationRules[55] = createSerializationRule("RoundBracketedClauseCS-0", 92,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				121		/* '(' || no-space value no-space */,
				177		/* V00*3-steps || value */,
				226		/* RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value */,
				183		/* V01*1-steps || value */,
				227		/* RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value */,
				122		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 28/* NavigatingArgCS,NavigatingBarArgCS,NavigatingCommaArgCS,NavigatingSemiArgCS */,
					(67/*NavigatingArgCS*/ << 4) | 1 /*[?]*/,
					(69/*NavigatingBarArgCS*/ << 4) | 2 /*[*]*/,
					(70/*NavigatingCommaArgCS*/ << 4) | 2 /*[*]*/,
					(71/*NavigatingSemiArgCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::SelfExpCS-0(essentialoclcs::SelfExpCS): "self"
		serializationRules[56] = createSerializationRule("SelfExpCS-0", 96,
			createSerializationMatchSteps(
				84		/* assert |SelfExpCS::name| == 0 */,
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				163		/* 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::ShadowPartCS-0(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[57] = createSerializationRule("ShadowPartCS-0", 97,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				289		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				51		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				52		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				110		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
				131		/* '=' || soft-space value soft-space */,
				51		/* ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 37/* ExpCS,PatternExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/,
					(81/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, -1
				)
			});
		// EssentialOCL::ShadowPartCS-1(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
		serializationRules[58] = createSerializationRule("ShadowPartCS-1", 97,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				85		/* assert |ShadowPartCS::referredProperty| == 0 */,
				288		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : StringLiteralExpCS */,
				51		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			),
			createSerializationSteps(
				52		/* ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 51/* StringLiteralExpCS */,
					(102/*StringLiteralExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SimplePathNameCS-0(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[59] = createSerializationRule("SimplePathNameCS-0", 98,
			createSerializationMatchSteps(
				227		/* check-rule basecs::PathNameCS.ownedPathElements : FirstPathElementCS */,
				47		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			),
			createSerializationSteps(
				233		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 11/* FirstPathElementCS */,
					(28/*FirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SquareBracketedClauseCS-0(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[60] = createSerializationRule("SquareBracketedClauseCS-0", 100,
			createSerializationMatchSteps(
				290		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				106		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			),
			createSerializationSteps(
				136		/* '[' || no-space value no-space */,
				91		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				176		/* V00*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				91		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				137		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::StringLiteralExpCS-0(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[61] = createSerializationRule("StringLiteralExpCS-0", 102,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				132		/* assign V0 = |StringLiteralExpCS::segments| */
			),
			createSerializationSteps(
				174		/* V00*1-steps || value */,
				112		/* StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, false, GrammarCardinality.ONE_OR_MORE)
			});
		// EssentialOCL::TupleLiteralExpCS-0(essentialoclcs::TupleLiteralExpCS): { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" }
		serializationRules[62] = createSerializationRule("TupleLiteralExpCS-0", 106,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				291		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS */,
				109		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				135		/* 'Tuple' || soft-space value soft-space */,
				168		/* '{' || soft-space value push soft-new-line */,
				73		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				176		/* V00*2-steps || value */,
				125		/* ',' || no-space value soft-new-line */,
				73		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 55/* TupleLiteralPartCS */,
					(107/*TupleLiteralPartCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::TupleLiteralPartCS-0(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[63] = createSerializationRule("TupleLiteralPartCS-0", 107,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				296		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				297		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				60		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				138		/* assign V0 = |VariableCS::ownedType| */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				219		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				175		/* V00*2-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				101		/* VariableCS::ownedType=TypeExpCS || value */,
				131		/* '=' || soft-space value soft-space */,
				53		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
	}
	private void initSerializationRules1() {
		// EssentialOCL::TupleTypeCS-0(basecs::TupleTypeCS): { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] }
		serializationRules[64] = createSerializationRule("TupleTypeCS-0", 109,
			createSerializationMatchSteps(
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				239		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				54		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				110		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				150		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				171		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				19		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				181		/* V00*7-steps || value */,
				121		/* '(' || no-space value no-space */,
				187		/* V01*4-steps || value */,
				74		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				196		/* V02*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				74		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				122		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(7/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 56/* TuplePartCS */,
					(108/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeExpCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[65] = createSerializationRule("TypeExpCS-0", 110,
			createSerializationMatchSteps(
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				136		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				50		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				18		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				172		/* V00*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[66] = createSerializationRule("TypeExpCS-1", 110,
			createSerializationMatchSteps(
				257		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				258		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				179		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				115		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				153		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				15		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				121		/* '(' || no-space value no-space */,
				96		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				182		/* V01*1-steps || value */,
				29		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				122		/* ')' || no-space value */,
				192		/* V02*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 58/* TypeExpWithoutMultiplicityCS */,
					(111/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[67] = createSerializationRule("TypeExpCS-2", 110,
			createSerializationMatchSteps(
				277		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				278		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				164		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				123		/* assign V0 = |MapTypeCS::ownedValueType| */,
				29		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				28		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				17		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				121		/* '(' || no-space value no-space */,
				56		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				126		/* ',' || no-space value soft-space */,
				103		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				122		/* ')' || no-space value */,
				182		/* V01*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(6/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-3(essentialoclcs::TypeNameExpCS): { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[68] = createSerializationRule("TypeExpCS-3", 110,
			createSerializationMatchSteps(
				293		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				294		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				295		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				179		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				135		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				56		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				163		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				77		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				179		/* V00*5-steps || value */,
				36		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				186		/* V01*3-steps || value */,
				168		/* '{' || soft-space value push soft-new-line */,
				79		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */,
				192		/* V02*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 6/* CurlyBracketedClauseCS */,
					(14/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 35/* PathNameCS */,
					(80/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-4(essentialoclcs::CollectionPatternCS): { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[69] = createSerializationRule("TypeExpCS-4", 110,
			createSerializationMatchSteps(
				65		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				255		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				256		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				179		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				114		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				143		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				95		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				168		/* '{' || soft-space value push soft-new-line */,
				180		/* V00*6-steps || value */,
				70		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				70		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				124		/* '++' || soft-space value soft-space */,
				111		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				171		/* '}' || pop soft-new-line value soft-new-line */,
				192		/* V02*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 36/* PatternExpCS */,
					(81/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 5/* CollectionTypeCS */,
					(12/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeExpCS-5(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[70] = createSerializationRule("TypeExpCS-5", 110,
			createSerializationMatchSteps(
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				239		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				187		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				54		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				110		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				150		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				171		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				19		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				181		/* V00*7-steps || value */,
				121		/* '(' || no-space value no-space */,
				187		/* V01*4-steps || value */,
				74		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				196		/* V02*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				74		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				122		/* ')' || no-space value */,
				198		/* V03*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(7/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 56/* TuplePartCS */,
					(108/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeLiteralExpCS-0(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[71] = createSerializationRule("TypeLiteralExpCS-0", 113,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				92		/* assert |TypeLiteralExpCS::ownedPathName| == 0 */,
				292		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS */,
				55		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				99		/* TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 60/* TypeLiteralWithMultiplicityCS */,
					(114/*TypeLiteralWithMultiplicityCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[72] = createSerializationRule("TypeLiteralWithMultiplicityCS-0", 114,
			createSerializationMatchSteps(
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				136		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				50		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				18		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				172		/* V00*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[73] = createSerializationRule("TypeLiteralWithMultiplicityCS-1", 114,
			createSerializationMatchSteps(
				257		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				258		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				179		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				115		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				153		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				15		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				121		/* '(' || no-space value no-space */,
				96		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				182		/* V01*1-steps || value */,
				29		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				122		/* ')' || no-space value */,
				192		/* V02*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 58/* TypeExpWithoutMultiplicityCS */,
					(111/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[74] = createSerializationRule("TypeLiteralWithMultiplicityCS-2", 114,
			createSerializationMatchSteps(
				277		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				278		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				164		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				123		/* assign V0 = |MapTypeCS::ownedValueType| */,
				29		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				28		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				17		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				121		/* '(' || no-space value no-space */,
				56		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				126		/* ',' || no-space value soft-space */,
				103		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				122		/* ')' || no-space value */,
				182		/* V01*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(6/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-3(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[75] = createSerializationRule("TypeLiteralWithMultiplicityCS-3", 114,
			createSerializationMatchSteps(
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				239		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				187		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				54		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				110		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				150		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				171		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				19		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				181		/* V00*7-steps || value */,
				121		/* '(' || no-space value no-space */,
				187		/* V01*4-steps || value */,
				74		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				196		/* V02*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				74		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				122		/* ')' || no-space value */,
				198		/* V03*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(7/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 56/* TuplePartCS */,
					(108/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeNameExpCS-0(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] }
		serializationRules[76] = createSerializationRule("TypeNameExpCS-0", 115,
			createSerializationMatchSteps(
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				293		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				294		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				295		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				135		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				56		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				163		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				77		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				179		/* V00*5-steps || value */,
				36		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				186		/* V01*3-steps || value */,
				168		/* '{' || soft-space value push soft-new-line */,
				79		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 6/* CurlyBracketedClauseCS */,
					(14/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 35/* PathNameCS */,
					(80/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::URIFirstPathElementCS-0(basecs::PathElementWithURICS): referredElement=URI
		serializationRules[77] = createSerializationRule("URIFirstPathElementCS-0", 123,
			createSerializationMatchSteps(
				46		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				244		/* PathElementCS::referredElement=URI || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIFirstPathElementCS-1(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[78] = createSerializationRule("URIFirstPathElementCS-1", 123,
			createSerializationMatchSteps(
				46		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				246		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[79] = createSerializationRule("URIPathNameCS-0", 124,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				232		/* PathNameCS::ownedPathElements+=URIFirstPathElementCS || value */,
				176		/* V00*2-steps || value */,
				129		/* '::' || no-space value no-space */,
				235		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 67/* NextPathElementCS,URIFirstPathElementCS */,
					(74/*NextPathElementCS*/ << 4) | 2 /*[*]*/,
					(123/*URIFirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS-0(essentialoclcs::UnlimitedNaturalLiteralExpCS): "*"
		serializationRules[80] = createSerializationRule("UnlimitedNaturalLiteralExpCS-0", 126,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				123		/* '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// OCLstdlib::AccumulatorCS-0(basecs::ParameterCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[81] = createSerializationRule("AccumulatorCS-0", 1,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				220		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::AnnotationCS-0(basecs::AnnotationCS): { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" }
		serializationRules[82] = createSerializationRule("AnnotationCS-0", 2,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				61		/* assert |AnnotationCS::ownedContents| == 0 */,
				62		/* assert |AnnotationCS::ownedReferences| == 0 */,
				206		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				99		/* assign V0 = (|AnnotationElementCS::ownedDetails| > 0) */,
				140		/* assign V1 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				138		/* 'annotation' || soft-space value soft-space */,
				221		/* NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				180		/* V00*6-steps || value */,
				121		/* '(' || no-space value no-space */,
				37		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				37		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				122		/* ')' || no-space value */,
				130		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 7/* DetailCS */,
					(16/*DetailCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLstdlib::AnnotationCS-1(basecs::AnnotationCS): { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" ownedAnnotations+=AnnotationElementCS "}" } }
		serializationRules[83] = createSerializationRule("AnnotationCS-1", 2,
			createSerializationMatchSteps(
				61		/* assert |AnnotationCS::ownedContents| == 0 */,
				62		/* assert |AnnotationCS::ownedReferences| == 0 */,
				216		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				206		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				30		/* assert (|ModelElementCS::ownedAnnotations| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				99		/* assign V0 = (|AnnotationElementCS::ownedDetails| > 0) */,
				140		/* assign V1 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				138		/* 'annotation' || soft-space value soft-space */,
				221		/* NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				180		/* V00*6-steps || value */,
				121		/* '(' || no-space value no-space */,
				37		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				37		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				122		/* ')' || no-space value */,
				168		/* '{' || soft-space value push soft-new-line */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 1/* AnnotationElementCS */,
					(3/*AnnotationElementCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 7/* DetailCS */,
					(16/*DetailCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLstdlib::DetailCS-0(basecs::DetailCS): { name=(Name|SINGLE_QUOTED_STRING) "=" values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
		serializationRules[84] = createSerializationRule("DetailCS-0", 16,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				116		/* assign V0 = |DetailCS::values| */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				225		/* NamedElementCS::name=Name|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				131		/* '=' || soft-space value soft-space */,
				173		/* V00*1-steps || value */,
				118		/* DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.DETAIL_CS__VALUES, false, GrammarCardinality.ZERO_OR_MORE)
			});
		// OCLstdlib::DocumentationCS-0(basecs::DocumentationCS): { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" }
		serializationRules[85] = createSerializationRule("DocumentationCS-0", 17,
			createSerializationMatchSteps(
				71		/* assert |NamedElementCS::name| == 0 */,
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				206		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				117		/* assign V0 = |DocumentationCS::value| */,
				141		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				168		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				142		/* 'documentation' || soft-space value soft-space */,
				172		/* V00*1-steps || value */,
				117		/* DocumentationCS::value=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				189		/* V01*6-steps || value */,
				121		/* '(' || no-space value no-space */,
				37		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				196		/* V02*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				37		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				122		/* ')' || no-space value */,
				130		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 7/* DetailCS */,
					(16/*DetailCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLstdlib::ImportCS-0(basecs::ImportCS): { "import" { name=Identifier ":" }[?] ownedPathName=URIPathNameCS isAll?="::*"[?] }
		serializationRules[86] = createSerializationRule("ImportCS-0", 33,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				210		/* check-rule basecs::ImportCS.ownedPathName : URIPathNameCS */,
				155		/* assign V1 = |ImportCS::isAll.'::*'| */,
				17		/* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				125		/* assign V0 = |NamedElementCS::name| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				148		/* 'import' || soft-space value soft-space */,
				175		/* V00*2-steps || value */,
				220		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				76		/* ImportCS::ownedPathName=URIPathNameCS || value */,
				182		/* V01*1-steps || value */,
				2		/* ImportCS::isAll?='::*' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, false,
					(2/*'::*'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 69/* URIPathNameCS */,
					(124/*URIPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::InvCS-0(oclstdlibcs::LibConstraintCS): { stereotype="inv" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" }
		serializationRules[87] = createSerializationRule("InvCS-0", 35,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				208		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				209		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				125		/* assign V0 = |NamedElementCS::name| */,
				7		/* assert (|ConstraintCS::stereotype.'inv'| - 1) == 0 */,
				154		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				247		/* ConstraintCS::stereotype='inv' || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				219		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V01*3-steps || value */,
				121		/* '(' || no-space value no-space */,
				59		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				87		/* ConstraintCS::ownedSpecification=SpecificationCS || value */,
				130		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, false,
					(10/*'inv'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 49/* SpecificationCS */,
					(99/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 49/* SpecificationCS */,
					(99/*SpecificationCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::IteratorCS-0(basecs::ParameterCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[88] = createSerializationRule("IteratorCS-0", 37,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				220		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LambdaParameterCS-0(basecs::LambdaParameterCS): { name=Identifier ":" "Lambda" ownedContextType=TypedMultiplicityRefCS "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[89] = createSerializationRule("LambdaParameterCS-0", 41,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				211		/* check-rule basecs::LambdaParameterCS.ownedContextType : TypedMultiplicityRefCS */,
				212		/* check-rule basecs::LambdaParameterCS.ownedParameters : ParameterCS */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				20		/* assert (|LambdaParameterCS::ownedContextType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				102		/* assign V0 = (|LambdaParameterCS::ownedParameters| > 0) */,
				145		/* assign V1 = (|LambdaParameterCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				220		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				134		/* 'Lambda' || soft-space value soft-space */,
				33		/* LambdaParameterCS::ownedContextType=TypedMultiplicityRefCS || value */,
				121		/* '(' || no-space value no-space */,
				178		/* V00*4-steps || value */,
				67		/* LambdaParameterCS::ownedParameters+=ParameterCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				67		/* LambdaParameterCS::ownedParameters+=ParameterCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_CONTEXT_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_PARAMETERS, 34/* ParameterCS */,
					(79/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LambdaTypeCS-0(basecs::LambdaTypeCS): { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=TypedMultiplicityRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedMultiplicityRefCS }
		serializationRules[90] = createSerializationRule("LambdaTypeCS-0", 42,
			createSerializationMatchSteps(
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				213		/* check-rule basecs::LambdaTypeCS.ownedContextType : TypedMultiplicityRefCS */,
				214		/* check-rule basecs::LambdaTypeCS.ownedParameterTypes : TypedMultiplicityRefCS */,
				215		/* check-rule basecs::LambdaTypeCS.ownedResultType : TypedMultiplicityRefCS */,
				238		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				23		/* assert (|LambdaTypeCS::ownedResultType| - 1) == 0 */,
				22		/* assert (|LambdaTypeCS::ownedContextType| - 1) == 0 */,
				134		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				21		/* assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0 */,
				146		/* assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0) */,
				169		/* assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1) */
			),
			createSerializationSteps(
				16		/* LambdaTypeCS::name='Lambda' || soft-space value soft-space */,
				172		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				34		/* LambdaTypeCS::ownedContextType=TypedMultiplicityRefCS || value */,
				121		/* '(' || no-space value no-space */,
				187		/* V01*4-steps || value */,
				66		/* LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value */,
				196		/* V02*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				66		/* LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				83		/* LambdaTypeCS::ownedResultType=TypedMultiplicityRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, false,
					(5/*'Lambda'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLstdlib::LibClassCS-0(oclstdlibcs::LibClassCS): { isAbstract?="abstract"[?] "type" name=AnyName ownedSignature=TemplateSignatureCS[?] { ":" metaclassName=AnyName }[?] { "conformsTo" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] "{" { ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" }
		serializationRules[91] = createSerializationRule("LibClassCS-0", 45,
			createSerializationMatchSteps(
				63		/* assert |ClassCS::instanceClassName| == 0 */,
				89		/* assert |StructuredClassCS::isInterface| == 0 */,
				90		/* assert |StructuredClassCS::ownedMetaclass| == 0 */,
				216		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				207		/* check-rule basecs::ClassCS.ownedConstraints : InvCS */,
				231		/* check-rule basecs::StructuredClassCS.ownedOperations : LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS */,
				232		/* check-rule basecs::StructuredClassCS.ownedProperties : LibPropertyCS */,
				238		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				233		/* check-rule basecs::StructuredClassCS.ownedSuperTypes : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				204		/* assign V9 = |ModelElementCS::ownedAnnotations| */,
				201		/* assign V8 = |ClassCS::ownedConstraints| */,
				200		/* assign V7 = |StructuredClassCS::ownedProperties| */,
				198		/* assign V6 = |StructuredClassCS::ownedOperations| */,
				192		/* assign V5 = |JavaImplementationCS::implementation| */,
				174		/* assign V2 = |LibClassCS::metaclassName| */,
				162		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				133		/* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
				182		/* assign V3 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
				189		/* assign V4 = (|StructuredClassCS::ownedSuperTypes| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				172		/* V00*1-steps || value */,
				1		/* StructuredClassCS::isAbstract?='abstract' || soft-space value soft-space */,
				166		/* 'type' || soft-space value soft-space */,
				222		/* NamedElementCS::name=AnyName || soft-space value soft-space */,
				182		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				195		/* V02*2-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				14		/* LibClassCS::metaclassName=AnyName || soft-space value soft-space */,
				202		/* V03*5-steps || value */,
				141		/* 'conformsTo' || soft-space value soft-space */,
				90		/* StructuredClassCS::ownedSuperTypes+=TypedRefCS || value */,
				205		/* V04*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				90		/* StructuredClassCS::ownedSuperTypes+=TypedRefCS || value */,
				208		/* V05*2-steps || value */,
				132		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				168		/* '{' || soft-space value push soft-new-line */,
				210		/* V06*1-steps || value */,
				64		/* StructuredClassCS::ownedOperations+=OperationCS || value */,
				212		/* V07*1-steps || value */,
				82		/* StructuredClassCS::ownedProperties+=LibPropertyCS || value */,
				214		/* V08*1-steps || value */,
				32		/* ClassCS::ownedConstraints+=InvCS || value */,
				216		/* V09*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, false,
					(8/*'abstract'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, -1
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME, -1
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 1/* AnnotationElementCS */,
					(3/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/* InvCS */,
					(35/*InvCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 31/* OperationCS */,
					(77/*OperationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 22/* LibPropertyCS */,
					(53/*LibPropertyCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 65/* TypedRefCS */,
					(119/*TypedRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLstdlib::LibCoercionCS-0(oclstdlibcs::LibCoercionCS): { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[92] = createSerializationRule("LibCoercionCS-0", 46,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				77		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				78		/* assert |OperationCS::ownedExceptions| == 0 */,
				79		/* assert |OperationCS::ownedParameters| == 0 */,
				80		/* assert |OperationCS::ownedPostconditions| == 0 */,
				81		/* assert |OperationCS::ownedPreconditions| == 0 */,
				91		/* assert |TemplateableElementCS::ownedSignature| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				119		/* assign V0 = |JavaImplementationCS::implementation| */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				140		/* 'coercion' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				121		/* '(' || no-space value no-space */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				175		/* V00*2-steps || value */,
				132		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				130		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, -1
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LibCoercionCS-1(oclstdlibcs::LibCoercionCS): { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } }
		serializationRules[93] = createSerializationRule("LibCoercionCS-1", 46,
			createSerializationMatchSteps(
				77		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				78		/* assert |OperationCS::ownedExceptions| == 0 */,
				79		/* assert |OperationCS::ownedParameters| == 0 */,
				91		/* assert |TemplateableElementCS::ownedSignature| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				216		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				221		/* check-rule basecs::OperationCS.ownedPostconditions : PreCS */,
				222		/* check-rule basecs::OperationCS.ownedPreconditions : PostCS */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				185		/* assign V3 = |OperationCS::ownedPostconditions| */,
				177		/* assign V2 = |OperationCS::ownedPreconditions| */,
				157		/* assign V1 = |ModelElementCS::ownedAnnotations| */,
				119		/* assign V0 = |JavaImplementationCS::implementation| */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				140		/* 'coercion' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				121		/* '(' || no-space value no-space */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				175		/* V00*2-steps || value */,
				132		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				168		/* '{' || soft-space value push soft-new-line */,
				183		/* V01*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				193		/* V02*1-steps || value */,
				238		/* OperationCS::ownedPreconditions+=PostCS || value */,
				199		/* V03*1-steps || value */,
				237		/* OperationCS::ownedPostconditions+=PreCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, -1
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 1/* AnnotationElementCS */,
					(3/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 39/* PreCS */,
					(83/*PreCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 38/* PostCS */,
					(82/*PostCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LibIterationCS-0(oclstdlibcs::LibIterationCS): { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulator=AccumulatorCS }[?] { "|" ownedParameters+=LambdaParameterCS { "," ownedParameters+=LambdaParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating?="invalidating"[?] isValidating?="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[94] = createSerializationRule("LibIterationCS-0", 47,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				77		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				78		/* assert |OperationCS::ownedExceptions| == 0 */,
				80		/* assert |OperationCS::ownedPostconditions| == 0 */,
				81		/* assert |OperationCS::ownedPreconditions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				298		/* check-rule oclstdlibcs::LibIterationCS.ownedAccumulator : AccumulatorCS */,
				299		/* check-rule oclstdlibcs::LibIterationCS.ownedIterators : IteratorCS */,
				218		/* check-rule basecs::OperationCS.ownedParameters : LambdaParameterCS */,
				238		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				199		/* assign V7 = |JavaImplementationCS::implementation| */,
				196		/* assign V6 = |LibIterationCS::isValidating.'validating'| */,
				193		/* assign V5 = |LibIterationCS::isInvalidating.'invalidating'| */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				175		/* assign V2 = |LibIterationCS::ownedAccumulator| */,
				147		/* assign V1 = (|LibIterationCS::ownedIterators| - 1) */,
				134		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				181		/* assign V3 = (|OperationCS::ownedParameters| > 0) */,
				188		/* assign V4 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				152		/* 'iteration' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				172		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				121		/* '(' || no-space value no-space */,
				54		/* LibIterationCS::ownedIterators+=IteratorCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				54		/* LibIterationCS::ownedIterators+=IteratorCS || value */,
				195		/* V02*2-steps || value */,
				130		/* ';' || no-space value soft-new-line */,
				22		/* LibIterationCS::ownedAccumulator=AccumulatorCS || value */,
				202		/* V03*5-steps || value */,
				169		/* '|' || soft-space value soft-space */,
				230		/* OperationCS::ownedParameters+=LambdaParameterCS || value */,
				205		/* V04*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				230		/* OperationCS::ownedParameters+=LambdaParameterCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				206		/* V05*1-steps || value */,
				3		/* LibIterationCS::isInvalidating?='invalidating' || soft-space value soft-space */,
				209		/* V06*1-steps || value */,
				11		/* LibIterationCS::isValidating?='validating' || soft-space value soft-space */,
				213		/* V07*2-steps || value */,
				132		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				130		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING, false,
					(11/*'invalidating'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING, false,
					(17/*'validating'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, -1
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATOR, 0/* AccumulatorCS */,
					(1/*AccumulatorCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, 14/* IteratorCS */,
					(37/*IteratorCS*/ << 4) | 3 /*[+]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 15/* LambdaParameterCS */,
					(41/*LambdaParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LibIterationCS-1(oclstdlibcs::LibIterationCS): { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulator=AccumulatorCS }[?] { "|" ownedParameters+=LambdaParameterCS { "," ownedParameters+=LambdaParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating?="invalidating"[?] isValidating?="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } }
		serializationRules[95] = createSerializationRule("LibIterationCS-1", 47,
			createSerializationMatchSteps(
				77		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				78		/* assert |OperationCS::ownedExceptions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				298		/* check-rule oclstdlibcs::LibIterationCS.ownedAccumulator : AccumulatorCS */,
				216		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				299		/* check-rule oclstdlibcs::LibIterationCS.ownedIterators : IteratorCS */,
				218		/* check-rule basecs::OperationCS.ownedParameters : LambdaParameterCS */,
				221		/* check-rule basecs::OperationCS.ownedPostconditions : PreCS */,
				222		/* check-rule basecs::OperationCS.ownedPreconditions : PostCS */,
				238		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				166		/* assign V10 = |OperationCS::ownedPostconditions| */,
				205		/* assign V9 = |OperationCS::ownedPreconditions| */,
				202		/* assign V8 = |ModelElementCS::ownedAnnotations| */,
				199		/* assign V7 = |JavaImplementationCS::implementation| */,
				196		/* assign V6 = |LibIterationCS::isValidating.'validating'| */,
				193		/* assign V5 = |LibIterationCS::isInvalidating.'invalidating'| */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				175		/* assign V2 = |LibIterationCS::ownedAccumulator| */,
				147		/* assign V1 = (|LibIterationCS::ownedIterators| - 1) */,
				134		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				181		/* assign V3 = (|OperationCS::ownedParameters| > 0) */,
				188		/* assign V4 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				152		/* 'iteration' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				172		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				121		/* '(' || no-space value no-space */,
				54		/* LibIterationCS::ownedIterators+=IteratorCS || value */,
				185		/* V01*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				54		/* LibIterationCS::ownedIterators+=IteratorCS || value */,
				195		/* V02*2-steps || value */,
				130		/* ';' || no-space value soft-new-line */,
				22		/* LibIterationCS::ownedAccumulator=AccumulatorCS || value */,
				202		/* V03*5-steps || value */,
				169		/* '|' || soft-space value soft-space */,
				230		/* OperationCS::ownedParameters+=LambdaParameterCS || value */,
				205		/* V04*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				230		/* OperationCS::ownedParameters+=LambdaParameterCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				206		/* V05*1-steps || value */,
				3		/* LibIterationCS::isInvalidating?='invalidating' || soft-space value soft-space */,
				209		/* V06*1-steps || value */,
				11		/* LibIterationCS::isValidating?='validating' || soft-space value soft-space */,
				213		/* V07*2-steps || value */,
				132		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				168		/* '{' || soft-space value push soft-new-line */,
				214		/* V08*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				216		/* V09*1-steps || value */,
				238		/* OperationCS::ownedPreconditions+=PostCS || value */,
				190		/* V10*1-steps || value */,
				237		/* OperationCS::ownedPostconditions+=PreCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING, false,
					(11/*'invalidating'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING, false,
					(17/*'validating'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, -1
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATOR, 0/* AccumulatorCS */,
					(1/*AccumulatorCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 1/* AnnotationElementCS */,
					(3/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, 14/* IteratorCS */,
					(37/*IteratorCS*/ << 4) | 3 /*[+]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 15/* LambdaParameterCS */,
					(41/*LambdaParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 39/* PreCS */,
					(83/*PreCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 38/* PostCS */,
					(82/*PostCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LibOperationCS-0(oclstdlibcs::LibOperationCS): { isStatic?="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating?="validating"[?] isInvalidating?="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[96] = createSerializationRule("LibOperationCS-0", 48,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				77		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				78		/* assert |OperationCS::ownedExceptions| == 0 */,
				80		/* assert |OperationCS::ownedPostconditions| == 0 */,
				81		/* assert |OperationCS::ownedPreconditions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				219		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				238		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				199		/* assign V7 = |JavaImplementationCS::implementation| */,
				197		/* assign V6 = |LibOperationCS::precedence| */,
				194		/* assign V5 = |LibOperationCS::isInvalidating.'invalidating'| */,
				190		/* assign V4 = |LibOperationCS::isValidating.'validating'| */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				162		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |LibOperationCS::isStatic.'static'| */,
				170		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				180		/* assign V3 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				172		/* V00*1-steps || value */,
				8		/* LibOperationCS::isStatic?='static' || soft-space value soft-space */,
				157		/* 'operation' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				182		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				121		/* '(' || no-space value no-space */,
				197		/* V02*4-steps || value */,
				231		/* OperationCS::ownedParameters+=ParameterCS || value */,
				201		/* V03*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				231		/* OperationCS::ownedParameters+=ParameterCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				203		/* V04*1-steps || value */,
				12		/* LibOperationCS::isValidating?='validating' || soft-space value soft-space */,
				206		/* V05*1-steps || value */,
				4		/* LibOperationCS::isInvalidating?='invalidating' || soft-space value soft-space */,
				211		/* V06*3-steps || value */,
				161		/* 'precedence' || soft-space value soft-space */,
				131		/* '=' || soft-space value soft-space */,
				106		/* LibOperationCS::precedence=Name || soft-space value soft-space */,
				213		/* V07*2-steps || value */,
				132		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				130		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING, false,
					(11/*'invalidating'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC, false,
					(15/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING, false,
					(17/*'validating'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, -1
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 34/* ParameterCS */,
					(79/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, -1
				)
			});
		// OCLstdlib::LibOperationCS-1(oclstdlibcs::LibOperationCS): { isStatic?="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating?="validating"[?] isInvalidating?="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { { "body" ":" ownedBodyExpressions+=SpecificationCS ";" }[*] ownedAnnotations+=AnnotationElementCS[*] ownedPostconditions+=PostCS[*] ownedPreconditions+=PreCS[*] } "}" } }
		serializationRules[97] = createSerializationRule("LibOperationCS-1", 48,
			createSerializationMatchSteps(
				78		/* assert |OperationCS::ownedExceptions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				216		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				217		/* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
				219		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				220		/* check-rule basecs::OperationCS.ownedPostconditions : PostCS */,
				223		/* check-rule basecs::OperationCS.ownedPreconditions : PreCS */,
				238		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				167		/* assign V11 = |OperationCS::ownedPreconditions| */,
				166		/* assign V10 = |OperationCS::ownedPostconditions| */,
				204		/* assign V9 = |ModelElementCS::ownedAnnotations| */,
				203		/* assign V8 = |OperationCS::ownedBodyExpressions| */,
				199		/* assign V7 = |JavaImplementationCS::implementation| */,
				197		/* assign V6 = |LibOperationCS::precedence| */,
				194		/* assign V5 = |LibOperationCS::isInvalidating.'invalidating'| */,
				190		/* assign V4 = |LibOperationCS::isValidating.'validating'| */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				162		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |LibOperationCS::isStatic.'static'| */,
				170		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				180		/* assign V3 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				172		/* V00*1-steps || value */,
				8		/* LibOperationCS::isStatic?='static' || soft-space value soft-space */,
				157		/* 'operation' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				182		/* V01*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				121		/* '(' || no-space value no-space */,
				197		/* V02*4-steps || value */,
				231		/* OperationCS::ownedParameters+=ParameterCS || value */,
				201		/* V03*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				231		/* OperationCS::ownedParameters+=ParameterCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				203		/* V04*1-steps || value */,
				12		/* LibOperationCS::isValidating?='validating' || soft-space value soft-space */,
				206		/* V05*1-steps || value */,
				4		/* LibOperationCS::isInvalidating?='invalidating' || soft-space value soft-space */,
				211		/* V06*3-steps || value */,
				161		/* 'precedence' || soft-space value soft-space */,
				131		/* '=' || soft-space value soft-space */,
				106		/* LibOperationCS::precedence=Name || soft-space value soft-space */,
				213		/* V07*2-steps || value */,
				132		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				168		/* '{' || soft-space value push soft-new-line */,
				215		/* V08*4-steps || value */,
				139		/* 'body' || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				26		/* OperationCS::ownedBodyExpressions+=SpecificationCS || value */,
				130		/* ';' || no-space value soft-new-line */,
				216		/* V09*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				190		/* V10*1-steps || value */,
				236		/* OperationCS::ownedPostconditions+=PostCS || value */,
				191		/* V11*1-steps || value */,
				239		/* OperationCS::ownedPreconditions+=PreCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING, false,
					(11/*'invalidating'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC, false,
					(15/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING, false,
					(17/*'validating'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, -1
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 1/* AnnotationElementCS */,
					(3/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 49/* SpecificationCS */,
					(99/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 34/* ParameterCS */,
					(79/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 38/* PostCS */,
					(82/*PostCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 39/* PreCS */,
					(83/*PreCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, -1
				)
			});
		// OCLstdlib::LibOppositeCS-0(oclstdlibcs::LibOppositeCS): { "opposite" name=Name ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[98] = createSerializationRule("LibOppositeCS-0", 49,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				158		/* 'opposite' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LibPackageCS-0(oclstdlibcs::LibPackageCS): { "library" name=Name { ":" nsPrefix=Identifier "=" nsURI=URI }[?] "{" { { "precedence" ownedPrecedences+=PrecedenceCS[+] ";" }[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" }
		serializationRules[99] = createSerializationRule("LibPackageCS-0", 50,
			createSerializationMatchSteps(
				216		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				224		/* check-rule basecs::PackageCS.ownedClasses : ClassCS|LibClassCS */,
				226		/* check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS */,
				300		/* check-rule oclstdlibcs::LibPackageCS.ownedPrecedences : PrecedenceCS */,
				195		/* assign V5 = |ModelElementCS::ownedAnnotations| */,
				191		/* assign V4 = |PackageCS::ownedClasses| */,
				186		/* assign V3 = |PackageOwnerCS::ownedPackages| */,
				129		/* assign V0 = |PackageCS::nsURI| */,
				45		/* assert (|PackageCS::nsPrefix| - V0) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				148		/* assign V1 = (|LibPackageCS::ownedPrecedences| > 0) */,
				176		/* assign V2 = |LibPackageCS::ownedPrecedences| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				155		/* 'library' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				178		/* V00*4-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				20		/* PackageCS::nsPrefix=Identifier || soft-space value soft-space */,
				131		/* '=' || soft-space value soft-space */,
				21		/* PackageCS::nsURI=URI || soft-space value soft-space */,
				168		/* '{' || soft-space value push soft-new-line */,
				188		/* V01*4-steps || value */,
				161		/* 'precedence' || soft-space value soft-space */,
				194		/* V02*1-steps || value */,
				81		/* LibPackageCS::ownedPrecedences+=PrecedenceCS || value */,
				130		/* ';' || no-space value soft-new-line */,
				199		/* V03*1-steps || value */,
				229		/* PackageOwnerCS::ownedPackages+=PackageCS || half-new-line value half-new-line */,
				204		/* V04*1-steps || value */,
				27		/* PackageCS::ownedClasses+=ClassCS || half-new-line value half-new-line */,
				207		/* V05*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PACKAGE_CS__NS_URI, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 1/* AnnotationElementCS */,
					(3/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 2/* ClassCS */,
					(7/*ClassCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 33/* PackageCS */,
					(78/*PackageCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES, 40/* PrecedenceCS */,
					(84/*PrecedenceCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLstdlib::LibPathElementCS-0(basecs::PathElementCS): referredElement=Name
		serializationRules[100] = createSerializationRule("LibPathElementCS-0", 51,
			createSerializationMatchSteps(
				46		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				243		/* PathElementCS::referredElement=Name || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// OCLstdlib::LibPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=LibPathElementCS { "::" ownedPathElements+=LibPathElementCS }[*] }
		serializationRules[101] = createSerializationRule("LibPathNameCS-0", 52,
			createSerializationMatchSteps(
				228		/* check-rule basecs::PathNameCS.ownedPathElements : LibPathElementCS */,
				105		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			),
			createSerializationSteps(
				234		/* PathNameCS::ownedPathElements+=LibPathElementCS || value */,
				176		/* V00*2-steps || value */,
				129		/* '::' || no-space value no-space */,
				234		/* PathNameCS::ownedPathElements+=LibPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 20/* LibPathElementCS */,
					(51/*LibPathElementCS*/ << 4) | 3 /*[+]*/
				)
			});
		// OCLstdlib::LibPropertyCS-0(oclstdlibcs::LibPropertyCS): { isStatic?="static"[?] "property" name=Name ":" ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[102] = createSerializationRule("LibPropertyCS-0", 53,
			createSerializationMatchSteps(
				87		/* assert |StructuralFeatureCS::default| == 0 */,
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				88		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				301		/* check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : LibOppositeCS */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				173		/* assign V2 = |JavaImplementationCS::implementation| */,
				156		/* assign V1 = |LibPropertyCS::ownedOpposite| */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				122		/* assign V0 = |LibPropertyCS::isStatic.'static'| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				172		/* V00*1-steps || value */,
				9		/* LibPropertyCS::isStatic?='static' || soft-space value soft-space */,
				162		/* 'property' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				182		/* V01*1-steps || value */,
				65		/* LibPropertyCS::ownedOpposite=LibOppositeCS || value */,
				195		/* V02*2-steps || value */,
				132		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				130		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC, false,
					(15/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, -1
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, 18/* LibOppositeCS */,
					(49/*LibOppositeCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LibPropertyCS-1(oclstdlibcs::LibPropertyCS): { isStatic?="static"[?] "property" name=Name ":" ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } }
		serializationRules[103] = createSerializationRule("LibPropertyCS-1", 53,
			createSerializationMatchSteps(
				87		/* assert |StructuralFeatureCS::default| == 0 */,
				88		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				216		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				301		/* check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : LibOppositeCS */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				184		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				173		/* assign V2 = |JavaImplementationCS::implementation| */,
				156		/* assign V1 = |LibPropertyCS::ownedOpposite| */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				122		/* assign V0 = |LibPropertyCS::isStatic.'static'| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				172		/* V00*1-steps || value */,
				9		/* LibPropertyCS::isStatic?='static' || soft-space value soft-space */,
				162		/* 'property' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				182		/* V01*1-steps || value */,
				65		/* LibPropertyCS::ownedOpposite=LibOppositeCS || value */,
				195		/* V02*2-steps || value */,
				132		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				168		/* '{' || soft-space value push soft-new-line */,
				199		/* V03*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC, false,
					(15/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, -1
				),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 1/* AnnotationElementCS */,
					(3/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, 18/* LibOppositeCS */,
					(49/*LibOppositeCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::Library-0(oclstdlibcs::LibRootPackageCS): { { ownedImports+=ImportCS ";" }[*] ownedPackages+=LibPackageCS[*] }
		serializationRules[104] = createSerializationRule("Library-0", 54,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				230		/* check-rule basecs::RootCS.ownedImports : ImportCS */,
				225		/* check-rule basecs::PackageOwnerCS.ownedPackages : LibPackageCS */,
				160		/* assign V1 = |PackageOwnerCS::ownedPackages| */,
				131		/* assign V0 = |RootCS::ownedImports| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				176		/* V00*2-steps || value */,
				48		/* RootCS::ownedImports+=ImportCS || value half-new-line */,
				130		/* ';' || no-space value soft-new-line */,
				183		/* V01*1-steps || value */,
				228		/* PackageOwnerCS::ownedPackages+=LibPackageCS || half-new-line value half-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/* ImportCS */,
					(33/*ImportCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 19/* LibPackageCS */,
					(50/*LibPackageCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLstdlib::PackageCS-0(basecs::PackageCS): { "package" name=Name { ":" nsPrefix=Identifier "=" nsURI=URI }[?] "{" { ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" }
		serializationRules[105] = createSerializationRule("PackageCS-0", 78,
			createSerializationMatchSteps(
				216		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				224		/* check-rule basecs::PackageCS.ownedClasses : ClassCS|LibClassCS */,
				226		/* check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS */,
				184		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				178		/* assign V2 = |PackageCS::ownedClasses| */,
				160		/* assign V1 = |PackageOwnerCS::ownedPackages| */,
				129		/* assign V0 = |PackageCS::nsURI| */,
				45		/* assert (|PackageCS::nsPrefix| - V0) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				159		/* 'package' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */,
				178		/* V00*4-steps || value */,
				128		/* ':' || soft-space value soft-space */,
				20		/* PackageCS::nsPrefix=Identifier || soft-space value soft-space */,
				131		/* '=' || soft-space value soft-space */,
				21		/* PackageCS::nsURI=URI || soft-space value soft-space */,
				168		/* '{' || soft-space value push soft-new-line */,
				183		/* V01*1-steps || value */,
				229		/* PackageOwnerCS::ownedPackages+=PackageCS || half-new-line value half-new-line */,
				193		/* V02*1-steps || value */,
				27		/* PackageCS::ownedClasses+=ClassCS || half-new-line value half-new-line */,
				199		/* V03*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				171		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PACKAGE_CS__NS_URI, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 1/* AnnotationElementCS */,
					(3/*AnnotationElementCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 2/* ClassCS */,
					(7/*ClassCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 33/* PackageCS */,
					(78/*PackageCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLstdlib::ParameterCS-0(basecs::ParameterCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[106] = createSerializationRule("ParameterCS-0", 79,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				220		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::PostCS-0(oclstdlibcs::LibConstraintCS): { stereotype="post" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" }
		serializationRules[107] = createSerializationRule("PostCS-0", 82,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				208		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				209		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				125		/* assign V0 = |NamedElementCS::name| */,
				8		/* assert (|ConstraintCS::stereotype.'post'| - 1) == 0 */,
				154		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				248		/* ConstraintCS::stereotype='post' || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				219		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V01*3-steps || value */,
				121		/* '(' || no-space value no-space */,
				59		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				87		/* ConstraintCS::ownedSpecification=SpecificationCS || value */,
				130		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, false,
					(12/*'post'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 49/* SpecificationCS */,
					(99/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 49/* SpecificationCS */,
					(99/*SpecificationCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::PreCS-0(oclstdlibcs::LibConstraintCS): { stereotype="pre" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" }
		serializationRules[108] = createSerializationRule("PreCS-0", 83,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				208		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				209		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				125		/* assign V0 = |NamedElementCS::name| */,
				9		/* assert (|ConstraintCS::stereotype.'pre'| - 1) == 0 */,
				154		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				249		/* ConstraintCS::stereotype='pre' || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				219		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				186		/* V01*3-steps || value */,
				121		/* '(' || no-space value no-space */,
				59		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				87		/* ConstraintCS::ownedSpecification=SpecificationCS || value */,
				130		/* ';' || no-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, false,
					(13/*'pre'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 49/* SpecificationCS */,
					(99/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 49/* SpecificationCS */,
					(99/*SpecificationCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::PrecedenceCS-0(oclstdlibcs::PrecedenceCS): { "left" ":" name=Name }
		serializationRules[109] = createSerializationRule("PrecedenceCS-0", 84,
			createSerializationMatchSteps(
				82		/* assert |PrecedenceCS::isRightAssociative| == 0 */,
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				153		/* 'left' || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE)
			});
		// OCLstdlib::PrecedenceCS-1(oclstdlibcs::PrecedenceCS): { isRightAssociative?="right" ":" name=Name }
		serializationRules[110] = createSerializationRule("PrecedenceCS-1", 84,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */,
				49		/* assert (|PrecedenceCS::isRightAssociative.'right'| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				7		/* PrecedenceCS::isRightAssociative?='right' || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				224		/* NamedElementCS::name=Name || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE, false,
					(14/*'right'*/ << 4) | 0 /*[1]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE)
			});
		// OCLstdlib::SpecificationCS-0(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS
		serializationRules[111] = createSerializationRule("SpecificationCS-0", 99,
			createSerializationMatchSteps(
				86		/* assert |SpecificationCS::exprString| == 0 */,
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				261		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				11		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				42		/* ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::TuplePartCS-0(basecs::TuplePartCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[112] = createSerializationRule("TuplePartCS-0", 108,
			createSerializationMatchSteps(
				69		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				241		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				57		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				34		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				217		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				220		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				128		/* ':' || soft-space value soft-space */,
				100		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS-0(basecs::LambdaTypeCS): { { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=TypedMultiplicityRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedMultiplicityRefCS } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[113] = createSerializationRule("TypedMultiplicityRefCS-0", 118,
			createSerializationMatchSteps(
				213		/* check-rule basecs::LambdaTypeCS.ownedContextType : TypedMultiplicityRefCS */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				214		/* check-rule basecs::LambdaTypeCS.ownedParameterTypes : TypedMultiplicityRefCS */,
				215		/* check-rule basecs::LambdaTypeCS.ownedResultType : TypedMultiplicityRefCS */,
				238		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				187		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				23		/* assert (|LambdaTypeCS::ownedResultType| - 1) == 0 */,
				22		/* assert (|LambdaTypeCS::ownedContextType| - 1) == 0 */,
				134		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				21		/* assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0 */,
				146		/* assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0) */,
				169		/* assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1) */
			),
			createSerializationSteps(
				16		/* LambdaTypeCS::name='Lambda' || soft-space value soft-space */,
				172		/* V00*1-steps || value */,
				86		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				34		/* LambdaTypeCS::ownedContextType=TypedMultiplicityRefCS || value */,
				121		/* '(' || no-space value no-space */,
				187		/* V01*4-steps || value */,
				66		/* LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value */,
				196		/* V02*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				66		/* LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value */,
				122		/* ')' || no-space value */,
				128		/* ':' || soft-space value soft-space */,
				83		/* LambdaTypeCS::ownedResultType=TypedMultiplicityRefCS || value */,
				198		/* V03*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, false,
					(5/*'Lambda'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS-1(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[114] = createSerializationRule("TypedMultiplicityRefCS-1", 118,
			createSerializationMatchSteps(
				277		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				278		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				164		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				123		/* assign V0 = |MapTypeCS::ownedValueType| */,
				29		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				28		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				17		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				179		/* V00*5-steps || value */,
				121		/* '(' || no-space value no-space */,
				56		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				126		/* ',' || no-space value soft-space */,
				103		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				122		/* ')' || no-space value */,
				182		/* V01*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(6/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS-2(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[115] = createSerializationRule("TypedMultiplicityRefCS-2", 118,
			createSerializationMatchSteps(
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				239		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				187		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				54		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				110		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				150		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				171		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				19		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				181		/* V00*7-steps || value */,
				121		/* '(' || no-space value no-space */,
				187		/* V01*4-steps || value */,
				74		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				196		/* V02*2-steps || value */,
				126		/* ',' || no-space value soft-space */,
				74		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				122		/* ')' || no-space value */,
				198		/* V03*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(7/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 56/* TuplePartCS */,
					(108/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS-3(basecs::TypedTypeRefCS): { { isTypeof?="typeof" "(" ownedPathName=LibPathNameCS ")" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[116] = createSerializationRule("TypedMultiplicityRefCS-3", 118,
			createSerializationMatchSteps(
				96		/* assert |TypedTypeRefCS::ownedBinding| == 0 */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				244		/* check-rule basecs::TypedTypeRefCS.ownedPathName : LibPathNameCS */,
				136		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				59		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */,
				58		/* assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0 */
			),
			createSerializationSteps(
				10		/* TypedTypeRefCS::isTypeof?='typeof' || soft-space value soft-space */,
				121		/* '(' || no-space value no-space */,
				78		/* TypedTypeRefCS::ownedPathName=LibPathNameCS || value */,
				122		/* ')' || no-space value */,
				172		/* V00*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF, false,
					(16/*'typeof'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 21/* LibPathNameCS */,
					(52/*LibPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS-4(basecs::TypedTypeRefCS): { { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[117] = createSerializationRule("TypedMultiplicityRefCS-4", 118,
			createSerializationMatchSteps(
				95		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				243		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				242		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				244		/* check-rule basecs::TypedTypeRefCS.ownedPathName : LibPathNameCS */,
				164		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				137		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				59		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				78		/* TypedTypeRefCS::ownedPathName=LibPathNameCS || value */,
				177		/* V00*3-steps || value */,
				121		/* '(' || no-space value no-space */,
				25		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				122		/* ')' || no-space value */,
				182		/* V01*1-steps || value */,
				61		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 52/* TemplateBindingCS */,
					(103/*TemplateBindingCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 21/* LibPathNameCS */,
					(52/*LibPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::TypedTypeRefCS-0(basecs::TypedTypeRefCS): { isTypeof?="typeof" "(" ownedPathName=LibPathNameCS ")" }
		serializationRules[118] = createSerializationRule("TypedTypeRefCS-0", 120,
			createSerializationMatchSteps(
				96		/* assert |TypedTypeRefCS::ownedBinding| == 0 */,
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				244		/* check-rule basecs::TypedTypeRefCS.ownedPathName : LibPathNameCS */,
				59		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */,
				58		/* assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0 */
			),
			createSerializationSteps(
				10		/* TypedTypeRefCS::isTypeof?='typeof' || soft-space value soft-space */,
				121		/* '(' || no-space value no-space */,
				78		/* TypedTypeRefCS::ownedPathName=LibPathNameCS || value */,
				122		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF, false,
					(16/*'typeof'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 21/* LibPathNameCS */,
					(52/*LibPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::TypedTypeRefCS-1(basecs::TypedTypeRefCS): { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] }
		serializationRules[119] = createSerializationRule("TypedTypeRefCS-1", 120,
			createSerializationMatchSteps(
				95		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				243		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				244		/* check-rule basecs::TypedTypeRefCS.ownedPathName : LibPathNameCS */,
				137		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				59		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				78		/* TypedTypeRefCS::ownedPathName=LibPathNameCS || value */,
				177		/* V00*3-steps || value */,
				121		/* '(' || no-space value no-space */,
				25		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				122		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 52/* TemplateBindingCS */,
					(103/*TemplateBindingCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 21/* LibPathNameCS */,
					(52/*LibPathNameCS*/ << 4) | 0 /*[1]*/
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
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[11] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[12] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[13] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[14] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[15] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[16] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[17] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[18] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[19] = new @NonNull SerializationSegment @NonNull [] {
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
		// 0: JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[0] = createSerializationStepCrossReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, getCrossReference(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, "SINGLE_QUOTED_STRING"), 94, 10);
		// 1: StructuredClassCS::isAbstract?='abstract' || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, 8 /* 'abstract' */, 10);
		// 2: ImportCS::isAll?='::*' || soft-space value soft-space
		serializationSteps[2] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, 2 /* '::*' */, 10);
		// 3: LibIterationCS::isInvalidating?='invalidating' || soft-space value soft-space
		serializationSteps[3] = createSerializationStepAssignKeyword(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING, 11 /* 'invalidating' */, 10);
		// 4: LibOperationCS::isInvalidating?='invalidating' || soft-space value soft-space
		serializationSteps[4] = createSerializationStepAssignKeyword(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING, 11 /* 'invalidating' */, 10);
		// 5: MultiplicityCS::isNullFree?='|1' || no-space value no-space
		serializationSteps[5] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 19 /* '|1' */, 6);
		// 6: AbstractNameExpCS::isPre?='@' || soft-space value soft-space
		serializationSteps[6] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 4 /* '@' */, 10);
		// 7: PrecedenceCS::isRightAssociative?='right' || soft-space value soft-space
		serializationSteps[7] = createSerializationStepAssignKeyword(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE, 14 /* 'right' */, 10);
		// 8: LibOperationCS::isStatic?='static' || soft-space value soft-space
		serializationSteps[8] = createSerializationStepAssignKeyword(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC, 15 /* 'static' */, 10);
		// 9: LibPropertyCS::isStatic?='static' || soft-space value soft-space
		serializationSteps[9] = createSerializationStepAssignKeyword(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC, 15 /* 'static' */, 10);
		// 10: TypedTypeRefCS::isTypeof?='typeof' || soft-space value soft-space
		serializationSteps[10] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF, 16 /* 'typeof' */, 10);
		// 11: LibIterationCS::isValidating?='validating' || soft-space value soft-space
		serializationSteps[11] = createSerializationStepAssignKeyword(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING, 17 /* 'validating' */, 10);
		// 12: LibOperationCS::isValidating?='validating' || soft-space value soft-space
		serializationSteps[12] = createSerializationStepAssignKeyword(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING, 17 /* 'validating' */, 10);
		// 13: MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space
		serializationSteps[13] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 39 /*LOWER*/, 10);
		// 14: LibClassCS::metaclassName=AnyName || soft-space value soft-space
		serializationSteps[14] = createSerializationStepCrossReference(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME, getCrossReference(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME, "AnyName"), 4, 10);
		// 15: CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space
		serializationSteps[15] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 13 /*CollectionTypeIdentifier*/, 10);
		// 16: LambdaTypeCS::name='Lambda' || soft-space value soft-space
		serializationSteps[16] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, 5 /* 'Lambda' */, 10);
		// 17: MapTypeCS::name='Map' || soft-space value soft-space
		serializationSteps[17] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 6 /* 'Map' */, 10);
		// 18: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space
		serializationSteps[18] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 90 /*PrimitiveTypeIdentifier*/, 10);
		// 19: TupleTypeCS::name='Tuple' || soft-space value soft-space
		serializationSteps[19] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 7 /* 'Tuple' */, 10);
		// 20: PackageCS::nsPrefix=Identifier || soft-space value soft-space
		serializationSteps[20] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, 31 /*Identifier*/, 10);
		// 21: PackageCS::nsURI=URI || soft-space value soft-space
		serializationSteps[21] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__NS_URI, 122 /*URI*/, 10);
		// 22: LibIterationCS::ownedAccumulator=AccumulatorCS || value
		serializationSteps[22] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATOR, 1 /*AccumulatorCS*/, 0);
		// 23: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 117 /*TypeRefCS*/, 2);
		// 24: ModelElementCS::ownedAnnotations+=AnnotationElementCS || value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 3 /*AnnotationElementCS*/, 0);
		// 25: TypedTypeRefCS::ownedBinding=TemplateBindingCS || value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 103 /*TemplateBindingCS*/, 0);
		// 26: OperationCS::ownedBodyExpressions+=SpecificationCS || value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 99 /*SpecificationCS*/, 0);
		// 27: PackageCS::ownedClasses+=ClassCS || half-new-line value half-new-line
		serializationSteps[27] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 7 /*ClassCS*/, 5);
		// 28: NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 8 /*CoIteratorVariableCS*/, 0);
		// 29: CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 62 /*MultiplicityCS*/, 0);
		// 30: IfExpCS::ownedCondition=ExpCS|PatternExpCS || value
		serializationSteps[30] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, -1, new int[] { 27/*ExpCS*/,81/*PatternExpCS*/}, 0);
		// 31: IfThenExpCS::ownedCondition=ExpCS || value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 27 /*ExpCS*/, 0);
		// 32: ClassCS::ownedConstraints+=InvCS || value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 35 /*InvCS*/, 0);
		// 33: LambdaParameterCS::ownedContextType=TypedMultiplicityRefCS || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_CONTEXT_TYPE, 118 /*TypedMultiplicityRefCS*/, 0);
		// 34: LambdaTypeCS::ownedContextType=TypedMultiplicityRefCS || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 118 /*TypedMultiplicityRefCS*/, 0);
		// 35: AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[35] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// 36: TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[36] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// 37: AnnotationElementCS::ownedDetails+=DetailCS || value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 16 /*DetailCS*/, 0);
		// 38: IfExpCS::ownedElseExpression=ExpCS || value
		serializationSteps[38] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 27 /*ExpCS*/, 0);
		// 39: CollectionLiteralPartCS::ownedExpression=ExpCS || value
		serializationSteps[39] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 0);
		// 40: CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 81 /*PatternExpCS*/, 2);
		// 41: ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 2);
		// 42: ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 2);
		// 43: NestedExpCS::ownedExpression=ExpCS || value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 0);
		// 44: LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 27 /*ExpCS*/, 0);
		// 45: TypeParameterCS::ownedExtends+=TypedRefCS || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 119 /*TypedRefCS*/, 0);
		// 46: WildcardTypeRefCS::ownedExtends=TypedRefCS || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 119 /*TypedRefCS*/, 0);
		// 47: IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value
		serializationSteps[47] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 20 /*ElseIfThenExpCS*/, 0);
		// 48: RootCS::ownedImports+=ImportCS || value half-new-line
		serializationSteps[48] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 33 /*ImportCS*/, 3);
		// 49: LetExpCS::ownedInExpression=ExpCS || value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 27 /*ExpCS*/, 0);
		// 50: NavigatingArgCS::ownedInitExpression=ExpCS || value
		serializationSteps[50] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 27 /*ExpCS*/, 0);
		// 51: ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value
		serializationSteps[51] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new int[] { 27/*ExpCS*/,81/*PatternExpCS*/}, 0);
		// 52: ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[52] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 102 /*StringLiteralExpCS*/, 2);
		// 53: VariableCS::ownedInitExpression=ExpCS || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 27 /*ExpCS*/, 0);
		// 54: LibIterationCS::ownedIterators+=IteratorCS || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, 37 /*IteratorCS*/, 0);
		// 55: MapLiteralPartCS::ownedKey=ExpCS || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 27 /*ExpCS*/, 0);
		// 56: MapTypeCS::ownedKeyType=TypeExpCS || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 110 /*TypeExpCS*/, 0);
		// 57: CollectionLiteralPartCS::ownedLastExpression=ExpCS || value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 27 /*ExpCS*/, 0);
		// 58: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 86 /*PrefixedPrimaryExpCS*/, 0);
		// 59: ConstraintCS::ownedMessageSpecification=SpecificationCS || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 99 /*SpecificationCS*/, 0);
		// 60: TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 62 /*MultiplicityCS*/, 0);
		// 61: TypedRefCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 62 /*MultiplicityCS*/, 0);
		// 62: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 68 /*NavigatingArgExpCS*/, 0);
		// 63: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[63] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 68 /*NavigatingArgExpCS*/, 2);
		// 64: StructuredClassCS::ownedOperations+=OperationCS || value
		serializationSteps[64] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 77 /*OperationCS*/, 0);
		// 65: LibPropertyCS::ownedOpposite=LibOppositeCS || value
		serializationSteps[65] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, 49 /*LibOppositeCS*/, 0);
		// 66: LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 118 /*TypedMultiplicityRefCS*/, 0);
		// 67: LambdaParameterCS::ownedParameters+=ParameterCS || value
		serializationSteps[67] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_PARAMETER_CS__OWNED_PARAMETERS, 79 /*ParameterCS*/, 0);
		// 68: TemplateSignatureCS::ownedParameters+=TypeParameterCS || value
		serializationSteps[68] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 116 /*TypeParameterCS*/, 0);
		// 69: CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value
		serializationSteps[69] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 10 /*CollectionLiteralPartCS*/, 0);
		// 70: CollectionPatternCS::ownedParts+=PatternExpCS || value
		serializationSteps[70] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 81 /*PatternExpCS*/, 0);
		// 71: CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value
		serializationSteps[71] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 97 /*ShadowPartCS*/, 0);
		// 72: MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value
		serializationSteps[72] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 58 /*MapLiteralPartCS*/, 0);
		// 73: TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value
		serializationSteps[73] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 107 /*TupleLiteralPartCS*/, 0);
		// 74: TupleTypeCS::ownedParts+=TuplePartCS || value
		serializationSteps[74] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 108 /*TuplePartCS*/, 0);
		// 75: AbstractNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[75] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 80 /*PathNameCS*/, 0);
		// 76: ImportCS::ownedPathName=URIPathNameCS || value
		serializationSteps[76] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 124 /*URIPathNameCS*/, 0);
		// 77: TypeNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[77] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 80 /*PathNameCS*/, 0);
		// 78: TypedTypeRefCS::ownedPathName=LibPathNameCS || value
		serializationSteps[78] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 52 /*LibPathNameCS*/, 0);
		// 79: TypeNameExpCS::ownedPatternGuard=ExpCS || value
		serializationSteps[79] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 27 /*ExpCS*/, 0);
		// 80: PatternExpCS::ownedPatternType=TypeExpCS || value
		serializationSteps[80] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 110 /*TypeExpCS*/, 0);
		// 81: LibPackageCS::ownedPrecedences+=PrecedenceCS || value
		serializationSteps[81] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES, 84 /*PrecedenceCS*/, 0);
		// 82: StructuredClassCS::ownedProperties+=LibPropertyCS || value
		serializationSteps[82] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 53 /*LibPropertyCS*/, 0);
		// 83: LambdaTypeCS::ownedResultType=TypedMultiplicityRefCS || value
		serializationSteps[83] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 118 /*TypedMultiplicityRefCS*/, 0);
		// 84: AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[84] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 92 /*RoundBracketedClauseCS*/, 0);
		// 85: LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[85] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 92 /*RoundBracketedClauseCS*/, 0);
		// 86: TemplateableElementCS::ownedSignature=TemplateSignatureCS || value
		serializationSteps[86] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 105 /*TemplateSignatureCS*/, 0);
		// 87: ConstraintCS::ownedSpecification=SpecificationCS || value
		serializationSteps[87] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 99 /*SpecificationCS*/, 0);
		// 88: AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value
		serializationSteps[88] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 100 /*SquareBracketedClauseCS*/, 0);
		// 89: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value
		serializationSteps[89] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 104 /*TemplateParameterSubstitutionCS*/, 0);
		// 90: StructuredClassCS::ownedSuperTypes+=TypedRefCS || value
		serializationSteps[90] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 119 /*TypedRefCS*/, 0);
		// 91: SquareBracketedClauseCS::ownedTerms+=ExpCS || value
		serializationSteps[91] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 27 /*ExpCS*/, 0);
		// 92: IfExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[92] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 27 /*ExpCS*/, 0);
		// 93: IfThenExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[93] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 27 /*ExpCS*/, 0);
		// 94: CollectionLiteralExpCS::ownedType=CollectionTypeCS || value
		serializationSteps[94] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 12 /*CollectionTypeCS*/, 0);
		// 95: CollectionPatternCS::ownedType=CollectionTypeCS || value
		serializationSteps[95] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 12 /*CollectionTypeCS*/, 0);
		// 96: CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value
		serializationSteps[96] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 111 /*TypeExpWithoutMultiplicityCS*/, 0);
		// 97: MapLiteralExpCS::ownedType=MapTypeCS || value
		serializationSteps[97] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 59 /*MapTypeCS*/, 0);
		// 98: NavigatingArgCS::ownedType=TypeExpCS || value
		serializationSteps[98] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 110 /*TypeExpCS*/, 0);
		// 99: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[99] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 114 /*TypeLiteralWithMultiplicityCS*/, 2);
		// 100: TypedElementCS::ownedType=TypedMultiplicityRefCS || value
		serializationSteps[100] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 118 /*TypedMultiplicityRefCS*/, 0);
		// 101: VariableCS::ownedType=TypeExpCS || value
		serializationSteps[101] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 110 /*TypeExpCS*/, 0);
		// 102: MapLiteralPartCS::ownedValue=ExpCS || value
		serializationSteps[102] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 27 /*ExpCS*/, 0);
		// 103: MapTypeCS::ownedValueType=TypeExpCS || value
		serializationSteps[103] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 110 /*TypeExpCS*/, 0);
		// 104: LetExpCS::ownedVariables+=LetVariableCS || value
		serializationSteps[104] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 44 /*LetVariableCS*/, 0);
		// 105: PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space
		serializationSteps[105] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 129 /*UnrestrictedName*/, 10);
		// 106: LibOperationCS::precedence=Name || soft-space value soft-space
		serializationSteps[106] = createSerializationStepCrossReference(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, getCrossReference(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, "Name"), 65, 10);
		// 107: NavigatingArgCS::prefix='|' || soft-space value soft-space
		serializationSteps[107] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 18 /* '|' */, 10);
		// 108: NavigatingArgCS::prefix=';' || no-space value soft-new-line
		serializationSteps[108] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 3 /* ';' */, 7);
		// 109: NavigatingArgCS::prefix=',' || no-space value soft-space
		serializationSteps[109] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */, 8);
		// 110: ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[110] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 129, 10);
		// 111: CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space
		serializationSteps[111] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 31 /*Identifier*/, 10);
		// 112: StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[112] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 101 /*StringLiteral*/, 2);
		// 113: MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[113] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 10);
		// 114: BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[114] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 9 /* 'false|true' */, 2);
		// 115: NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[115] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 64 /*NUMBER_LITERAL*/, 2);
		// 116: MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space
		serializationSteps[116] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 121 /*UPPER*/, 10);
		// 117: DocumentationCS::value=SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[117] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, 94 /*SINGLE_QUOTED_STRING*/, 10);
		// 118: DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[118] = createSerializationStepAssigns(BaseCSPackage.Literals.DETAIL_CS__VALUES, -1, new int[] { 94/*SINGLE_QUOTED_STRING*/,56/*ML_SINGLE_QUOTED_STRING*/}, 10);
		// 119: '&&' || soft-space value soft-space
		serializationSteps[119] = createSerializationStepKeyword("&&", 10);
		// 120: '(' || value no-space
		serializationSteps[120] = createSerializationStepKeyword("(", 4);
		// 121: '(' || no-space value no-space
		serializationSteps[121] = createSerializationStepKeyword("(", 6);
		// 122: ')' || no-space value
		serializationSteps[122] = createSerializationStepKeyword(")", 1);
		// 123: '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[123] = createSerializationStepKeyword("*", 2);
		// 124: '++' || soft-space value soft-space
		serializationSteps[124] = createSerializationStepKeyword("++", 10);
		// 125: ',' || no-space value soft-new-line
		serializationSteps[125] = createSerializationStepKeyword(",", 7);
		// 126: ',' || no-space value soft-space
		serializationSteps[126] = createSerializationStepKeyword(",", 8);
		// 127: '..' || no-space value no-space
		serializationSteps[127] = createSerializationStepKeyword("..", 6);
		// 128: ':' || soft-space value soft-space
		serializationSteps[128] = createSerializationStepKeyword(":", 10);
		// 129: '::' || no-space value no-space
		serializationSteps[129] = createSerializationStepKeyword("::", 6);
		// 130: ';' || no-space value soft-new-line
		serializationSteps[130] = createSerializationStepKeyword(";", 7);
		// 131: '=' || soft-space value soft-space
		serializationSteps[131] = createSerializationStepKeyword("=", 10);
		// 132: '=>' || soft-space value soft-space
		serializationSteps[132] = createSerializationStepKeyword("=>", 10);
		// 133: '?' || soft-space value soft-space
		serializationSteps[133] = createSerializationStepKeyword("?", 10);
		// 134: 'Lambda' || soft-space value soft-space
		serializationSteps[134] = createSerializationStepKeyword("Lambda", 10);
		// 135: 'Tuple' || soft-space value soft-space
		serializationSteps[135] = createSerializationStepKeyword("Tuple", 10);
		// 136: '[' || no-space value no-space
		serializationSteps[136] = createSerializationStepKeyword("[", 6);
		// 137: ']' || no-space value
		serializationSteps[137] = createSerializationStepKeyword("]", 1);
		// 138: 'annotation' || soft-space value soft-space
		serializationSteps[138] = createSerializationStepKeyword("annotation", 10);
		// 139: 'body' || soft-space value soft-space
		serializationSteps[139] = createSerializationStepKeyword("body", 10);
		// 140: 'coercion' || soft-space value soft-space
		serializationSteps[140] = createSerializationStepKeyword("coercion", 10);
		// 141: 'conformsTo' || soft-space value soft-space
		serializationSteps[141] = createSerializationStepKeyword("conformsTo", 10);
		// 142: 'documentation' || soft-space value soft-space
		serializationSteps[142] = createSerializationStepKeyword("documentation", 10);
		// 143: 'else' || soft-new-line pop value push soft-space
		serializationSteps[143] = createSerializationStepKeyword("else", 18);
		// 144: 'elseif' || soft-new-line pop soft-space value push soft-space
		serializationSteps[144] = createSerializationStepKeyword("elseif", 19);
		// 145: 'endif' || soft-new-line pop value soft-space
		serializationSteps[145] = createSerializationStepKeyword("endif", 13);
		// 146: 'extends' || soft-space value soft-space
		serializationSteps[146] = createSerializationStepKeyword("extends", 10);
		// 147: 'if' || soft-new-line value push soft-space
		serializationSteps[147] = createSerializationStepKeyword("if", 14);
		// 148: 'import' || soft-space value soft-space
		serializationSteps[148] = createSerializationStepKeyword("import", 10);
		// 149: 'in' || soft-space value soft-space
		serializationSteps[149] = createSerializationStepKeyword("in", 10);
		// 150: 'in' || soft-space pop value soft-new-line
		serializationSteps[150] = createSerializationStepKeyword("in", 15);
		// 151: 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[151] = createSerializationStepKeyword("invalid", 2);
		// 152: 'iteration' || soft-space value soft-space
		serializationSteps[152] = createSerializationStepKeyword("iteration", 10);
		// 153: 'left' || soft-space value soft-space
		serializationSteps[153] = createSerializationStepKeyword("left", 10);
		// 154: 'let' || soft-space value push
		serializationSteps[154] = createSerializationStepKeyword("let", 9);
		// 155: 'library' || soft-space value soft-space
		serializationSteps[155] = createSerializationStepKeyword("library", 10);
		// 156: 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[156] = createSerializationStepKeyword("null", 2);
		// 157: 'operation' || soft-space value soft-space
		serializationSteps[157] = createSerializationStepKeyword("operation", 10);
		// 158: 'opposite' || soft-space value soft-space
		serializationSteps[158] = createSerializationStepKeyword("opposite", 10);
		// 159: 'package' || soft-space value soft-space
		serializationSteps[159] = createSerializationStepKeyword("package", 10);
		// 160: 'pre' || soft-space value soft-space
		serializationSteps[160] = createSerializationStepKeyword("pre", 10);
		// 161: 'precedence' || soft-space value soft-space
		serializationSteps[161] = createSerializationStepKeyword("precedence", 10);
		// 162: 'property' || soft-space value soft-space
		serializationSteps[162] = createSerializationStepKeyword("property", 10);
		// 163: 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[163] = createSerializationStepKeyword("self", 2);
		// 164: 'then' || pop value push soft-space
		serializationSteps[164] = createSerializationStepKeyword("then", 12);
		// 165: 'then' || pop soft-space value push soft-space
		serializationSteps[165] = createSerializationStepKeyword("then", 17);
		// 166: 'type' || soft-space value soft-space
		serializationSteps[166] = createSerializationStepKeyword("type", 10);
		// 167: 'with' || value
		serializationSteps[167] = createSerializationStepKeyword("with", 0);
		// 168: '{' || soft-space value push soft-new-line
		serializationSteps[168] = createSerializationStepKeyword("{", 16);
		// 169: '|' || soft-space value soft-space
		serializationSteps[169] = createSerializationStepKeyword("|", 10);
		// 170: '|?' || no-space value no-space
		serializationSteps[170] = createSerializationStepKeyword("|?", 6);
		// 171: '}' || pop soft-new-line value soft-new-line
		serializationSteps[171] = createSerializationStepKeyword("}", 11);
		// 172: V00*1-steps || value
		serializationSteps[172] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 1, 0);
		// 173: V00*1-steps || value
		serializationSteps[173] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 1, 0);
		// 174: V00*1-steps || value
		serializationSteps[174] = createSerializationStepSequence((0/*V0*/ << 4) | 3/*[+]*/, 1, 0);
		// 175: V00*2-steps || value
		serializationSteps[175] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 176: V00*2-steps || value
		serializationSteps[176] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 2, 0);
		// 177: V00*3-steps || value
		serializationSteps[177] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 3, 0);
		// 178: V00*4-steps || value
		serializationSteps[178] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 4, 0);
		// 179: V00*5-steps || value
		serializationSteps[179] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 5, 0);
		// 180: V00*6-steps || value
		serializationSteps[180] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 6, 0);
		// 181: V00*7-steps || value
		serializationSteps[181] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 7, 0);
		// 182: V01*1-steps || value
		serializationSteps[182] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 1, 0);
		// 183: V01*1-steps || value
		serializationSteps[183] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 1, 0);
		// 184: V01*2-steps || value
		serializationSteps[184] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 2, 0);
		// 185: V01*2-steps || value
		serializationSteps[185] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 2, 0);
		// 186: V01*3-steps || value
		serializationSteps[186] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 3, 0);
		// 187: V01*4-steps || value
		serializationSteps[187] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 4, 0);
		// 188: V01*4-steps || value
		serializationSteps[188] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 4, 0);
		// 189: V01*6-steps || value
		serializationSteps[189] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 6, 0);
		// 190: V10*1-steps || value
		serializationSteps[190] = createSerializationStepSequence((10/*V10*/ << 4) | 2/*[*]*/, 1, 0);
		// 191: V11*1-steps || value
		serializationSteps[191] = createSerializationStepSequence((11/*V11*/ << 4) | 2/*[*]*/, 1, 0);
		// 192: V02*1-steps || value
		serializationSteps[192] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 1, 0);
		// 193: V02*1-steps || value
		serializationSteps[193] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 1, 0);
		// 194: V02*1-steps || value
		serializationSteps[194] = createSerializationStepSequence((2/*V2*/ << 4) | 3/*[+]*/, 1, 0);
		// 195: V02*2-steps || value
		serializationSteps[195] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 2, 0);
		// 196: V02*2-steps || value
		serializationSteps[196] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 2, 0);
		// 197: V02*4-steps || value
		serializationSteps[197] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 4, 0);
		// 198: V03*1-steps || value
		serializationSteps[198] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 1, 0);
		// 199: V03*1-steps || value
		serializationSteps[199] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 1, 0);
		// 200: V03*2-steps || value
		serializationSteps[200] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 2, 0);
		// 201: V03*2-steps || value
		serializationSteps[201] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 2, 0);
		// 202: V03*5-steps || value
		serializationSteps[202] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 5, 0);
		// 203: V04*1-steps || value
		serializationSteps[203] = createSerializationStepSequence((4/*V4*/ << 4) | 1/*[?]*/, 1, 0);
		// 204: V04*1-steps || value
		serializationSteps[204] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 1, 0);
		// 205: V04*2-steps || value
		serializationSteps[205] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 2, 0);
		// 206: V05*1-steps || value
		serializationSteps[206] = createSerializationStepSequence((5/*V5*/ << 4) | 1/*[?]*/, 1, 0);
		// 207: V05*1-steps || value
		serializationSteps[207] = createSerializationStepSequence((5/*V5*/ << 4) | 2/*[*]*/, 1, 0);
		// 208: V05*2-steps || value
		serializationSteps[208] = createSerializationStepSequence((5/*V5*/ << 4) | 1/*[?]*/, 2, 0);
		// 209: V06*1-steps || value
		serializationSteps[209] = createSerializationStepSequence((6/*V6*/ << 4) | 1/*[?]*/, 1, 0);
		// 210: V06*1-steps || value
		serializationSteps[210] = createSerializationStepSequence((6/*V6*/ << 4) | 2/*[*]*/, 1, 0);
		// 211: V06*3-steps || value
		serializationSteps[211] = createSerializationStepSequence((6/*V6*/ << 4) | 1/*[?]*/, 3, 0);
		// 212: V07*1-steps || value
		serializationSteps[212] = createSerializationStepSequence((7/*V7*/ << 4) | 2/*[*]*/, 1, 0);
		// 213: V07*2-steps || value
		serializationSteps[213] = createSerializationStepSequence((7/*V7*/ << 4) | 1/*[?]*/, 2, 0);
		// 214: V08*1-steps || value
		serializationSteps[214] = createSerializationStepSequence((8/*V8*/ << 4) | 2/*[*]*/, 1, 0);
		// 215: V08*4-steps || value
		serializationSteps[215] = createSerializationStepSequence((8/*V8*/ << 4) | 2/*[*]*/, 4, 0);
		// 216: V09*1-steps || value
		serializationSteps[216] = createSerializationStepSequence((9/*V9*/ << 4) | 2/*[*]*/, 1, 0);
		// 217: wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[217] = createSerializationStepWrapper(2);
		// 218: NamedElementCS::name=UnaryOperatorName || soft-space value soft-space
		serializationSteps[218] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 125 /*UnaryOperatorName*/, 10);
		// 219: NamedElementCS::name=UnrestrictedName || soft-space value soft-space
		serializationSteps[219] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 129 /*UnrestrictedName*/, 10);
		// 220: NamedElementCS::name=Identifier || soft-space value soft-space
		serializationSteps[220] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 31 /*Identifier*/, 10);
		// 221: NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[221] = createSerializationStepAssigns(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, -1, new int[] { 31/*Identifier*/,94/*SINGLE_QUOTED_STRING*/}, 10);
		// 222: NamedElementCS::name=AnyName || soft-space value soft-space
		serializationSteps[222] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 4 /*AnyName*/, 10);
		// 223: NamedElementCS::name=BinaryOperatorName || soft-space value soft-space
		serializationSteps[223] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 5 /*BinaryOperatorName*/, 10);
		// 224: NamedElementCS::name=Name || soft-space value soft-space
		serializationSteps[224] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 65 /*Name*/, 10);
		// 225: NamedElementCS::name=Name|SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[225] = createSerializationStepAssigns(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, -1, new int[] { 65/*Name*/,94/*SINGLE_QUOTED_STRING*/}, 10);
		// 226: RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value
		serializationSteps[226] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 67 /*NavigatingArgCS*/, 0);
		// 227: RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value
		serializationSteps[227] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new int[] { 70/*NavigatingCommaArgCS*/,71/*NavigatingSemiArgCS*/,69/*NavigatingBarArgCS*/}, 0);
		// 228: PackageOwnerCS::ownedPackages+=LibPackageCS || half-new-line value half-new-line
		serializationSteps[228] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 50 /*LibPackageCS*/, 5);
		// 229: PackageOwnerCS::ownedPackages+=PackageCS || half-new-line value half-new-line
		serializationSteps[229] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 78 /*PackageCS*/, 5);
		// 230: OperationCS::ownedParameters+=LambdaParameterCS || value
		serializationSteps[230] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 41 /*LambdaParameterCS*/, 0);
		// 231: OperationCS::ownedParameters+=ParameterCS || value
		serializationSteps[231] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 79 /*ParameterCS*/, 0);
		// 232: PathNameCS::ownedPathElements+=URIFirstPathElementCS || value
		serializationSteps[232] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 123 /*URIFirstPathElementCS*/, 0);
		// 233: PathNameCS::ownedPathElements+=FirstPathElementCS || value
		serializationSteps[233] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 28 /*FirstPathElementCS*/, 0);
		// 234: PathNameCS::ownedPathElements+=LibPathElementCS || value
		serializationSteps[234] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 51 /*LibPathElementCS*/, 0);
		// 235: PathNameCS::ownedPathElements+=NextPathElementCS || value
		serializationSteps[235] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 74 /*NextPathElementCS*/, 0);
		// 236: OperationCS::ownedPostconditions+=PostCS || value
		serializationSteps[236] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 82 /*PostCS*/, 0);
		// 237: OperationCS::ownedPostconditions+=PreCS || value
		serializationSteps[237] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 83 /*PreCS*/, 0);
		// 238: OperationCS::ownedPreconditions+=PostCS || value
		serializationSteps[238] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 82 /*PostCS*/, 0);
		// 239: OperationCS::ownedPreconditions+=PreCS || value
		serializationSteps[239] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 83 /*PreCS*/, 0);
		// 240: OperatorExpCS::ownedRight=ExpCS || value
		serializationSteps[240] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 27 /*ExpCS*/, 0);
		// 241: OperatorExpCS::ownedRight=PrefixedLetExpCS || value
		serializationSteps[241] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 85 /*PrefixedLetExpCS*/, 0);
		// 242: OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value
		serializationSteps[242] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 86 /*PrefixedPrimaryExpCS*/, 0);
		// 243: PathElementCS::referredElement=Name || soft-space value soft-space
		serializationSteps[243] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "Name"), 65, 10);
		// 244: PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[244] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 122, 10);
		// 245: PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[245] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 127, 10);
		// 246: PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[246] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 129, 10);
		// 247: ConstraintCS::stereotype='inv' || soft-space value soft-space
		serializationSteps[247] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 10 /* 'inv' */, 10);
		// 248: ConstraintCS::stereotype='post' || soft-space value soft-space
		serializationSteps[248] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 12 /* 'post' */, 10);
		// 249: ConstraintCS::stereotype='pre' || soft-space value soft-space
		serializationSteps[249] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 13 /* 'pre' */, 10);
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
		substringSteps[4] = createSubstringStep("else", 18 /* soft-new-line, pop, value, push, soft-space */);
		// 5: 'endif' : [soft-new-line, pop, value, soft-space]
		substringSteps[5] = createSubstringStep("endif", 13 /* soft-new-line, pop, value, soft-space */);
		// 6: 'if' : [soft-new-line, value, push, soft-space]
		substringSteps[6] = createSubstringStep("if", 14 /* soft-new-line, value, push, soft-space */);
		// 7: 'in' : [soft-space, pop, value, soft-new-line]
		substringSteps[7] = createSubstringStep("in", 15 /* soft-space, pop, value, soft-new-line */);
		// 8: 'let' : [soft-space, value, push]
		substringSteps[8] = createSubstringStep("let", 9 /* soft-space, value, push */);
		// 9: 'then' : [pop, soft-space, value, push, soft-space]
		substringSteps[9] = createSubstringStep("then", 17 /* pop, soft-space, value, push, soft-space */);
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
//	import OCLstdlibCSPackage;
//	import Grammar;
//	import GrammarProvider;
