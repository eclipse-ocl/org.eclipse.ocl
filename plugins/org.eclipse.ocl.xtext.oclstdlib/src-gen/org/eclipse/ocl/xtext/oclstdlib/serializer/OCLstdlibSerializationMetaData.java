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

	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[67];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[20];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[132];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[78];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[297];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[236];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[120];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [20] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[247];
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
		return 118;
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
		return 117;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 170;
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
		eClassValues[17] = new EClassValue(BaseCSPackage.Literals.LAMBDA_TYPE_CS,
			createSerializationRules(
				90 /* LambdaTypeCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=LambdaContextTypeRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedRefCS */,
				113 /* TypedMultiplicityRefCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=LambdaContextTypeRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedRefCS (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
					15) /* LambdaContextTypeRefCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
					64) /* TypedMultiplicityRefCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
					66) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					54) /* TemplateSignatureCS */
			}
		);
		eClassValues[18] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
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
		eClassValues[19] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
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
		eClassValues[20] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_CLASS_CS,
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
		eClassValues[21] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_COERCION_CS,
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
		eClassValues[22] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_CONSTRAINT_CS,
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
		eClassValues[23] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS,
			createSerializationRules(
				94 /* LibIterationCS-0: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] ';' */,
				95 /* LibIterationCS-1: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] (OperationCS::ownedPreconditions+=PostCS)[V9:*] (OperationCS::ownedPostconditions+=PreCS)[V10:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATOR,
					0) /* AccumulatorCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_TargetGrammarRuleVector(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
					14) /* IteratorCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					34) /* ParameterCS */,
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
		eClassValues[24] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS,
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
		eClassValues[25] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_OPPOSITE_CS,
			createSerializationRules(
				98 /* LibOppositeCS-0: 'opposite' NamedElementCS::name=Name ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[26] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS,
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
		eClassValues[27] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS,
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
		eClassValues[28] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_ROOT_PACKAGE_CS,
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
		eClassValues[29] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
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
		eClassValues[30] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
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
		eClassValues[31] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
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
		eClassValues[32] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */,
				4 /* MultiplicityCS-2: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] ']' */,
				2 /* MultiplicityCS-0: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] '|?' ']' */,
				3 /* MultiplicityCS-1: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] MultiplicityCS::isNullFree?='|1' ']' */
			), null
		);
		eClassValues[33] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				7 /* MultiplicityCS-5: '[' MultiplicityStringCS::stringBounds='*|+|?' ']' */,
				5 /* MultiplicityCS-3: '[' MultiplicityStringCS::stringBounds='*|+|?' '|?' ']' */,
				6 /* MultiplicityCS-4: '[' MultiplicityStringCS::stringBounds='*|+|?' MultiplicityCS::isNullFree?='|1' ']' */,
				8 /* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			), null
		);
		eClassValues[34] = new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
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
		eClassValues[35] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
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
		eClassValues[36] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				48 /* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[37] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				49 /* NullLiteralExpCS-0: 'null' */
			), null
		);
		eClassValues[38] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				50 /* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[39] = new EClassValue(BaseCSPackage.Literals.PACKAGE_CS,
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
		eClassValues[40] = new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
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
		eClassValues[41] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */,
				9 /* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */,
				78 /* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */,
				100 /* LibPathElementCS-0: PathElementCS::referredElement=Name */
			), null
		);
		eClassValues[42] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				77 /* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */
			), null
		);
		eClassValues[43] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
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
		eClassValues[44] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				51 /* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					57) /* TypeExpCS */
			}
		);
		eClassValues[45] = new EClassValue(OCLstdlibCSPackage.Literals.PRECEDENCE_CS,
			createSerializationRules(
				109 /* PrecedenceCS-0: 'left' ':' NamedElementCS::name=Name */,
				110 /* PrecedenceCS-1: PrecedenceCS::isRightAssociative?='right' ':' NamedElementCS::name=Name */
			), null
		);
		eClassValues[46] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				52 /* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				53 /* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					73) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[47] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
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
		eClassValues[48] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				55 /* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					28) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[49] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				56 /* SelfExpCS-0: 'self' */
			), null
		);
		eClassValues[50] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				58 /* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */,
				57 /* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[51] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				60 /* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[52] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				61 /* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			), null
		);
		eClassValues[53] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
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
		eClassValues[54] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					77) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[55] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				13 /* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					62) /* TypeParameterCS */
			}
		);
		eClassValues[56] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				62 /* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					55) /* TupleLiteralPartCS */
			}
		);
		eClassValues[57] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
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
		eClassValues[58] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				112 /* TuplePartCS-0: NamedElementCS::name=Identifier ':' TypedElementCS::ownedType=TypedMultiplicityRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					64) /* TypedMultiplicityRefCS */
			}
		);
		eClassValues[59] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
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
		eClassValues[60] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				71 /* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					60) /* TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[61] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
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
		eClassValues[62] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				14 /* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					66) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[63] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				118 /* TypedTypeRefCS-0: TypedTypeRefCS::isTypeof?='typeof' '(' TypedTypeRefCS::ownedPathName=LibPathNameCS ')' */,
				119 /* TypedTypeRefCS-1: TypedTypeRefCS::ownedPathName=LibPathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */,
				89 /* LambdaContextTypeRefCS-0: TypedTypeRefCS::ownedPathName=LibPathNameCS */,
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
		eClassValues[64] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				80 /* UnlimitedNaturalLiteralExpCS-0: '*' */
			), null
		);
		eClassValues[65] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				18 /* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					57) /* TypeExpCS */
			}
		);
		eClassValues[66] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
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
		grammarRuleValues[40] = createParserRuleValue(40, "LambdaContextTypeRefCS", -1,
			createSerializationRules(
				89	/* LambdaContextTypeRefCS-0: TypedTypeRefCS::ownedPathName=LibPathNameCS */
			),
			(0 << 16) | 0	/* ownedPathName=LibPathNameCS : [value] | [value] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				29	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 10	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 16	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 11	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[42] = createParserRuleValue(42, "LambdaTypeCS", -1,
			createSerializationRules(
				90	/* LambdaTypeCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=LambdaContextTypeRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedRefCS */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 10	/* name="Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedContextType=LambdaContextTypeRefCS : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameterTypes+=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameterTypes+=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 10	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedResultType=TypedRefCS : [value] | [value] */
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
				94	/* LibIterationCS-0: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] ';' */,
				95	/* LibIterationCS-1: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] (OperationCS::ownedPreconditions+=PostCS)[V9:*] (OperationCS::ownedPostconditions+=PreCS)[V10:*] '}' */
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
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
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
				94	/* LibIterationCS-0: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] ';' */,
				95	/* LibIterationCS-1: 'iteration' NamedElementCS::name=Name (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] '(' LibIterationCS::ownedIterators+=IteratorCS (',' LibIterationCS::ownedIterators+=IteratorCS)[V1:*] (';' LibIterationCS::ownedAccumulator=AccumulatorCS)[V2:?] ('|' OperationCS::ownedParameters+=ParameterCS (',' OperationCS::ownedParameters+=ParameterCS)[V4:*])[V3:?] ')' ':' TypedElementCS::ownedType=TypedMultiplicityRefCS (LibIterationCS::isInvalidating?='invalidating')[V5:?] (LibIterationCS::isValidating?='validating')[V6:?] ('=>' JavaImplementationCS::implementation=SINGLE_QUOTED_STRING)[V7:?] '{' (ModelElementCS::ownedAnnotations+=AnnotationElementCS)[V8:*] (OperationCS::ownedPreconditions+=PostCS)[V9:*] (OperationCS::ownedPostconditions+=PreCS)[V10:*] '}' */,
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
				90	/* LambdaTypeCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=LambdaContextTypeRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedRefCS */,
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
				113	/* TypedMultiplicityRefCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=LambdaContextTypeRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedRefCS (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
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
				90	/* LambdaTypeCS-0: LambdaTypeCS::name='Lambda' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] LambdaTypeCS::ownedContextType=LambdaContextTypeRefCS '(' (LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS (',' LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS)[V2:*])[V1:?] ')' ':' LambdaTypeCS::ownedResultType=TypedRefCS */,
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
		// 15: LambdaContextTypeRefCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x10000000000L);
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
		grammarRuleVectors[71] = new GrammarRuleVector(0x200021100000240L,0x4002044101801a04L);
		// 72: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[72] = new GrammarRuleVector(0x200021100000240L,0x4002044101c01a04L);
		// 73: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[73] = new GrammarRuleVector(0x2000a1100000240L,0x4002044101e01a04L);
		// 74: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[74] = new GrammarRuleVector(0x2000a1108000240L,0x4002044101e01a04L);
		// 75: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[75] = new GrammarRuleVector(0x2000a1108000240L,0x4002044101e01a14L);
		// 76: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[76] = new GrammarRuleVector(0x2000a1108000240L,0x4002044101e21a04L);
		// 77: LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[77] = new GrammarRuleVector(0x800040000000000L,0x1a0200000000000L,0x8L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// 0: assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(150);
		// 1: assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(153);
		// 2: assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(156);
		// 3: assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(157);
		// 4: assert (|CollectionPatternCS::ownedType| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(159);
		// 5: assert (|CollectionTypeCS::name| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(160);
		// 6: assert (|ConstraintCS::ownedSpecification| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(161);
		// 7: assert (|ConstraintCS::stereotype.'inv'| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(162);
		// 8: assert (|ConstraintCS::stereotype.'post'| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(163);
		// 9: assert (|ConstraintCS::stereotype.'pre'| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(164);
		// 10: assert (|ContextCS::ownedExpression| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(165);
		// 11: assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(168);
		// 12: assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(169);
		// 13: assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(170);
		// 14: assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(171);
		// 15: assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(172);
		// 16: assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(173);
		// 17: assert (|ImportCS::ownedPathName| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(174);
		// 18: assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(175);
		// 19: assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(176);
		// 20: assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(177);
		// 21: assert (|LambdaTypeCS::ownedContextType| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(178);
		// 22: assert (|LambdaTypeCS::ownedResultType| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(181);
		// 23: assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(182);
		// 24: assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(188);
		// 25: assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(189);
		// 26: assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(190);
		// 27: assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(191);
		// 28: assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(192);
		// 29: assert (|ModelElementCS::ownedAnnotations| - 1) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(193);
		// 30: assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(194);
		// 31: assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(195);
		// 32: assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(196);
		// 33: assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(197);
		// 34: assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(198);
		// 35: assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(199);
		// 36: assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(200);
		// 37: assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(201);
		// 38: assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(202);
		// 39: assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(203);
		// 40: assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(204);
		// 41: assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(205);
		// 42: assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(206);
		// 43: assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(209);
		// 44: assert (|PackageCS::nsPrefix| - V0) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(210);
		// 45: assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(211);
		// 46: assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[46] = createMatchStep_Assert(212);
		// 47: assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[47] = createMatchStep_Assert(213);
		// 48: assert (|PrecedenceCS::isRightAssociative.'right'| - 1) == 0
		serializationMatchSteps[48] = createMatchStep_Assert(214);
		// 49: assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[49] = createMatchStep_Assert(215);
		// 50: assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[50] = createMatchStep_Assert(216);
		// 51: assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[51] = createMatchStep_Assert(217);
		// 52: assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[52] = createMatchStep_Assert(222);
		// 53: assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[53] = createMatchStep_Assert(225);
		// 54: assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[54] = createMatchStep_Assert(228);
		// 55: assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[55] = createMatchStep_Assert(229);
		// 56: assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[56] = createMatchStep_Assert(232);
		// 57: assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0
		serializationMatchSteps[57] = createMatchStep_Assert(233);
		// 58: assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[58] = createMatchStep_Assert(234);
		// 59: assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[59] = createMatchStep_Assert(235);
		// 60: assert |AnnotationCS::ownedContents| == 0
		serializationMatchSteps[60] = createMatchStep_Assert(8);
		// 61: assert |AnnotationCS::ownedReferences| == 0
		serializationMatchSteps[61] = createMatchStep_Assert(9);
		// 62: assert |ClassCS::instanceClassName| == 0
		serializationMatchSteps[62] = createMatchStep_Assert(12);
		// 63: assert |CollectionLiteralPartCS::ownedLastExpression| == 0
		serializationMatchSteps[63] = createMatchStep_Assert(17);
		// 64: assert |CollectionPatternCS::ownedPatternGuard| == 0
		serializationMatchSteps[64] = createMatchStep_Assert(19);
		// 65: assert |CurlyBracketedClauseCS::value| == 0
		serializationMatchSteps[65] = createMatchStep_Assert(32);
		// 66: assert |IfExpCS::isImplicit| == 0
		serializationMatchSteps[66] = createMatchStep_Assert(36);
		// 67: assert |LetExpCS::isImplicit| == 0
		serializationMatchSteps[67] = createMatchStep_Assert(52);
		// 68: assert |ModelElementCS::ownedAnnotations| == 0
		serializationMatchSteps[68] = createMatchStep_Assert(75);
		// 69: assert |MultiplicityCS::isNullFree| == 0
		serializationMatchSteps[69] = createMatchStep_Assert(79);
		// 70: assert |NamedElementCS::name| == 0
		serializationMatchSteps[70] = createMatchStep_Assert(81);
		// 71: assert |NavigatingArgCS::ownedCoIterator| == 0
		serializationMatchSteps[71] = createMatchStep_Assert(82);
		// 72: assert |NavigatingArgCS::ownedInitExpression| == 0
		serializationMatchSteps[72] = createMatchStep_Assert(83);
		// 73: assert |NavigatingArgCS::ownedNameExpression| == 0
		serializationMatchSteps[73] = createMatchStep_Assert(84);
		// 74: assert |NavigatingArgCS::ownedType| == 0
		serializationMatchSteps[74] = createMatchStep_Assert(85);
		// 75: assert |NavigatingArgCS::prefix| == 0
		serializationMatchSteps[75] = createMatchStep_Assert(89);
		// 76: assert |OperationCS::ownedBodyExpressions| == 0
		serializationMatchSteps[76] = createMatchStep_Assert(92);
		// 77: assert |OperationCS::ownedExceptions| == 0
		serializationMatchSteps[77] = createMatchStep_Assert(93);
		// 78: assert |OperationCS::ownedParameters| == 0
		serializationMatchSteps[78] = createMatchStep_Assert(94);
		// 79: assert |OperationCS::ownedPostconditions| == 0
		serializationMatchSteps[79] = createMatchStep_Assert(95);
		// 80: assert |OperationCS::ownedPreconditions| == 0
		serializationMatchSteps[80] = createMatchStep_Assert(96);
		// 81: assert |PrecedenceCS::isRightAssociative| == 0
		serializationMatchSteps[81] = createMatchStep_Assert(107);
		// 82: assert |RootCS::ownedImports| == 0
		serializationMatchSteps[82] = createMatchStep_Assert(109);
		// 83: assert |SelfExpCS::name| == 0
		serializationMatchSteps[83] = createMatchStep_Assert(110);
		// 84: assert |ShadowPartCS::referredProperty| == 0
		serializationMatchSteps[84] = createMatchStep_Assert(112);
		// 85: assert |SpecificationCS::exprString| == 0
		serializationMatchSteps[85] = createMatchStep_Assert(113);
		// 86: assert |StructuralFeatureCS::default| == 0
		serializationMatchSteps[86] = createMatchStep_Assert(116);
		// 87: assert |StructuralFeatureCS::ownedDefaultExpressions| == 0
		serializationMatchSteps[87] = createMatchStep_Assert(117);
		// 88: assert |StructuredClassCS::isInterface| == 0
		serializationMatchSteps[88] = createMatchStep_Assert(119);
		// 89: assert |StructuredClassCS::ownedMetaclass| == 0
		serializationMatchSteps[89] = createMatchStep_Assert(120);
		// 90: assert |TemplateableElementCS::ownedSignature| == 0
		serializationMatchSteps[90] = createMatchStep_Assert(128);
		// 91: assert |TypeLiteralExpCS::ownedPathName| == 0
		serializationMatchSteps[91] = createMatchStep_Assert(132);
		// 92: assert |TypedElementCS::isOptional| == 0
		serializationMatchSteps[92] = createMatchStep_Assert(138);
		// 93: assert |TypedElementCS::qualifiers| == 0
		serializationMatchSteps[93] = createMatchStep_Assert(140);
		// 94: assert |TypedRefCS::ownedMultiplicity| == 0
		serializationMatchSteps[94] = createMatchStep_Assert(141);
		// 95: assert |TypedTypeRefCS::isTypeof| == 0
		serializationMatchSteps[95] = createMatchStep_Assert(143);
		// 96: assert |TypedTypeRefCS::ownedBinding| == 0
		serializationMatchSteps[96] = createMatchStep_Assert(144);
		// 97: assert |VariableCS::ownedInitExpression| == 0
		serializationMatchSteps[97] = createMatchStep_Assert(146);
		// 98: assert |WildcardTypeRefCS::ownedSuper| == 0
		serializationMatchSteps[98] = createMatchStep_Assert(149);
		// 99: assign V0 = (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchSteps[99] = createMatchStep_Assign(0, 152);
		// 100: assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[100] = createMatchStep_Assign(0, 155);
		// 101: assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchSteps[101] = createMatchStep_Assign(0, 167);
		// 102: assign V0 = (|LetExpCS::ownedVariables| - 1)
		serializationMatchSteps[102] = createMatchStep_Assign(0, 183);
		// 103: assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[103] = createMatchStep_Assign(0, 187);
		// 104: assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[104] = createMatchStep_Assign(0, 212);
		// 105: assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[105] = createMatchStep_Assign(0, 218);
		// 106: assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[106] = createMatchStep_Assign(0, 221);
		// 107: assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[107] = createMatchStep_Assign(0, 223);
		// 108: assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[108] = createMatchStep_Assign(0, 224);
		// 109: assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[109] = createMatchStep_Assign(0, 227);
		// 110: assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[110] = createMatchStep_Assign(0, 231);
		// 111: assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchSteps[111] = createMatchStep_Assign(0, 7);
		// 112: assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchSteps[112] = createMatchStep_Assign(0, 17);
		// 113: assign V0 = |CollectionPatternCS::restVariableName|
		serializationMatchSteps[113] = createMatchStep_Assign(0, 21);
		// 114: assign V0 = |CollectionTypeCS::ownedType|
		serializationMatchSteps[114] = createMatchStep_Assign(0, 24);
		// 115: assign V0 = |DetailCS::values|
		serializationMatchSteps[115] = createMatchStep_Assign(0, 33);
		// 116: assign V0 = |DocumentationCS::value|
		serializationMatchSteps[116] = createMatchStep_Assign(0, 34);
		// 117: assign V0 = |IfExpCS::ownedIfThenExpressions|
		serializationMatchSteps[117] = createMatchStep_Assign(0, 39);
		// 118: assign V0 = |JavaImplementationCS::implementation|
		serializationMatchSteps[118] = createMatchStep_Assign(0, 46);
		// 119: assign V0 = |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchSteps[119] = createMatchStep_Assign(0, 55);
		// 120: assign V0 = |LibOperationCS::isStatic.'static'|
		serializationMatchSteps[120] = createMatchStep_Assign(0, 62);
		// 121: assign V0 = |LibPropertyCS::isStatic.'static'|
		serializationMatchSteps[121] = createMatchStep_Assign(0, 66);
		// 122: assign V0 = |MapTypeCS::ownedValueType|
		serializationMatchSteps[122] = createMatchStep_Assign(0, 74);
		// 123: assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[123] = createMatchStep_Assign(0, 77);
		// 124: assign V0 = |NamedElementCS::name|
		serializationMatchSteps[124] = createMatchStep_Assign(0, 81);
		// 125: assign V0 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[125] = createMatchStep_Assign(0, 82);
		// 126: assign V0 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[126] = createMatchStep_Assign(0, 83);
		// 127: assign V0 = |NavigatingArgCS::ownedType|
		serializationMatchSteps[127] = createMatchStep_Assign(0, 85);
		// 128: assign V0 = |PackageCS::nsURI|
		serializationMatchSteps[128] = createMatchStep_Assign(0, 99);
		// 129: assign V0 = |PatternExpCS::patternVariableName|
		serializationMatchSteps[129] = createMatchStep_Assign(0, 105);
		// 130: assign V0 = |RootCS::ownedImports|
		serializationMatchSteps[130] = createMatchStep_Assign(0, 109);
		// 131: assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[131] = createMatchStep_Assign(0, 115);
		// 132: assign V0 = |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchSteps[132] = createMatchStep_Assign(0, 118);
		// 133: assign V0 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[133] = createMatchStep_Assign(0, 128);
		// 134: assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[134] = createMatchStep_Assign(0, 134);
		// 135: assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[135] = createMatchStep_Assign(0, 141);
		// 136: assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[136] = createMatchStep_Assign(0, 144);
		// 137: assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[137] = createMatchStep_Assign(0, 147);
		// 138: assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[138] = createMatchStep_Assign(0, 148);
		// 139: assign V1 = (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchSteps[139] = createMatchStep_Assign(1, 151);
		// 140: assign V1 = (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchSteps[140] = createMatchStep_Assign(1, 152);
		// 141: assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[141] = createMatchStep_Assign(1, 154);
		// 142: assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[142] = createMatchStep_Assign(1, 158);
		// 143: assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[143] = createMatchStep_Assign(1, 166);
		// 144: assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0)
		serializationMatchSteps[144] = createMatchStep_Assign(1, 180);
		// 145: assign V1 = (|LibIterationCS::ownedIterators| - 1)
		serializationMatchSteps[145] = createMatchStep_Assign(1, 184);
		// 146: assign V1 = (|LibPackageCS::ownedPrecedences| > 0)
		serializationMatchSteps[146] = createMatchStep_Assign(1, 185);
		// 147: assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[147] = createMatchStep_Assign(1, 186);
		// 148: assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[148] = createMatchStep_Assign(1, 227);
		// 149: assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[149] = createMatchStep_Assign(1, 230);
		// 150: assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[150] = createMatchStep_Assign(1, 6);
		// 151: assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[151] = createMatchStep_Assign(1, 23);
		// 152: assign V1 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[152] = createMatchStep_Assign(1, 25);
		// 153: assign V1 = |ImportCS::isAll.'::*'|
		serializationMatchSteps[153] = createMatchStep_Assign(1, 43);
		// 154: assign V1 = |LibPropertyCS::ownedOpposite|
		serializationMatchSteps[154] = createMatchStep_Assign(1, 67);
		// 155: assign V1 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[155] = createMatchStep_Assign(1, 75);
		// 156: assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[156] = createMatchStep_Assign(1, 82);
		// 157: assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[157] = createMatchStep_Assign(1, 83);
		// 158: assign V1 = |PackageOwnerCS::ownedPackages|
		serializationMatchSteps[158] = createMatchStep_Assign(1, 101);
		// 159: assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[159] = createMatchStep_Assign(1, 124);
		// 160: assign V1 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[160] = createMatchStep_Assign(1, 128);
		// 161: assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[161] = createMatchStep_Assign(1, 136);
		// 162: assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[162] = createMatchStep_Assign(1, 141);
		// 163: assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[163] = createMatchStep_Assign(1, 147);
		// 164: assign V10 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[164] = createMatchStep_Assign(10, 95);
		// 165: assign V11 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[165] = createMatchStep_Assign(11, 96);
		// 166: assign V2 = (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchSteps[166] = createMatchStep_Assign(2, 151);
		// 167: assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1)
		serializationMatchSteps[167] = createMatchStep_Assign(2, 179);
		// 168: assign V2 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[168] = createMatchStep_Assign(2, 208);
		// 169: assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[169] = createMatchStep_Assign(2, 226);
		// 170: assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[170] = createMatchStep_Assign(2, 4);
		// 171: assign V2 = |JavaImplementationCS::implementation|
		serializationMatchSteps[171] = createMatchStep_Assign(2, 46);
		// 172: assign V2 = |LibClassCS::metaclassName|
		serializationMatchSteps[172] = createMatchStep_Assign(2, 56);
		// 173: assign V2 = |LibIterationCS::ownedAccumulator|
		serializationMatchSteps[173] = createMatchStep_Assign(2, 59);
		// 174: assign V2 = |LibPackageCS::ownedPrecedences|
		serializationMatchSteps[174] = createMatchStep_Assign(2, 65);
		// 175: assign V2 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[175] = createMatchStep_Assign(2, 96);
		// 176: assign V2 = |PackageCS::ownedClasses|
		serializationMatchSteps[176] = createMatchStep_Assign(2, 100);
		// 177: assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[177] = createMatchStep_Assign(2, 141);
		// 178: assign V3 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[178] = createMatchStep_Assign(3, 207);
		// 179: assign V3 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[179] = createMatchStep_Assign(3, 208);
		// 180: assign V3 = (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchSteps[180] = createMatchStep_Assign(3, 220);
		// 181: assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[181] = createMatchStep_Assign(3, 3);
		// 182: assign V3 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[182] = createMatchStep_Assign(3, 75);
		// 183: assign V3 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[183] = createMatchStep_Assign(3, 95);
		// 184: assign V3 = |PackageOwnerCS::ownedPackages|
		serializationMatchSteps[184] = createMatchStep_Assign(3, 101);
		// 185: assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[185] = createMatchStep_Assign(3, 141);
		// 186: assign V4 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[186] = createMatchStep_Assign(4, 207);
		// 187: assign V4 = (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchSteps[187] = createMatchStep_Assign(4, 219);
		// 188: assign V4 = |LibOperationCS::isValidating.'validating'|
		serializationMatchSteps[188] = createMatchStep_Assign(4, 63);
		// 189: assign V4 = |PackageCS::ownedClasses|
		serializationMatchSteps[189] = createMatchStep_Assign(4, 100);
		// 190: assign V5 = |JavaImplementationCS::implementation|
		serializationMatchSteps[190] = createMatchStep_Assign(5, 46);
		// 191: assign V5 = |LibIterationCS::isInvalidating.'invalidating'|
		serializationMatchSteps[191] = createMatchStep_Assign(5, 57);
		// 192: assign V5 = |LibOperationCS::isInvalidating.'invalidating'|
		serializationMatchSteps[192] = createMatchStep_Assign(5, 61);
		// 193: assign V5 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[193] = createMatchStep_Assign(5, 75);
		// 194: assign V6 = |LibIterationCS::isValidating.'validating'|
		serializationMatchSteps[194] = createMatchStep_Assign(6, 58);
		// 195: assign V6 = |LibOperationCS::precedence|
		serializationMatchSteps[195] = createMatchStep_Assign(6, 64);
		// 196: assign V6 = |StructuredClassCS::ownedOperations|
		serializationMatchSteps[196] = createMatchStep_Assign(6, 121);
		// 197: assign V7 = |JavaImplementationCS::implementation|
		serializationMatchSteps[197] = createMatchStep_Assign(7, 46);
		// 198: assign V7 = |StructuredClassCS::ownedProperties|
		serializationMatchSteps[198] = createMatchStep_Assign(7, 122);
		// 199: assign V8 = |ClassCS::ownedConstraints|
		serializationMatchSteps[199] = createMatchStep_Assign(8, 13);
		// 200: assign V8 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[200] = createMatchStep_Assign(8, 75);
		// 201: assign V8 = |OperationCS::ownedBodyExpressions|
		serializationMatchSteps[201] = createMatchStep_Assign(8, 92);
		// 202: assign V9 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[202] = createMatchStep_Assign(9, 75);
		// 203: assign V9 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[203] = createMatchStep_Assign(9, 96);
		// 204: check-rule basecs::AnnotationElementCS.ownedDetails : 16
		serializationMatchSteps[204] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 7/*DetailCS*/);
		// 205: check-rule basecs::ClassCS.ownedConstraints : 35
		serializationMatchSteps[205] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/*InvCS*/);
		// 206: check-rule basecs::ConstraintCS.ownedMessageSpecification : 99
		serializationMatchSteps[206] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 49/*SpecificationCS*/);
		// 207: check-rule basecs::ConstraintCS.ownedSpecification : 99
		serializationMatchSteps[207] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 49/*SpecificationCS*/);
		// 208: check-rule basecs::ImportCS.ownedPathName : 124
		serializationMatchSteps[208] = createMatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 69/*URIPathNameCS*/);
		// 209: check-rule basecs::LambdaTypeCS.ownedContextType : 40
		serializationMatchSteps[209] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 15/*LambdaContextTypeRefCS*/);
		// 210: check-rule basecs::LambdaTypeCS.ownedParameterTypes : 118
		serializationMatchSteps[210] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 64/*TypedMultiplicityRefCS*/);
		// 211: check-rule basecs::LambdaTypeCS.ownedResultType : 42|59|109|119|120
		serializationMatchSteps[211] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 66/*LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS*/);
		// 212: check-rule basecs::ModelElementCS.ownedAnnotations : 2|3|17
		serializationMatchSteps[212] = createMatchStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 8/*AnnotationCS|AnnotationElementCS|DocumentationCS*/);
		// 213: check-rule basecs::OperationCS.ownedBodyExpressions : 99
		serializationMatchSteps[213] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 49/*SpecificationCS*/);
		// 214: check-rule basecs::OperationCS.ownedParameters : 79
		serializationMatchSteps[214] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 34/*ParameterCS*/);
		// 215: check-rule basecs::OperationCS.ownedPostconditions : 82
		serializationMatchSteps[215] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 38/*PostCS*/);
		// 216: check-rule basecs::OperationCS.ownedPostconditions : 83
		serializationMatchSteps[216] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 39/*PreCS*/);
		// 217: check-rule basecs::OperationCS.ownedPreconditions : 82
		serializationMatchSteps[217] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 38/*PostCS*/);
		// 218: check-rule basecs::OperationCS.ownedPreconditions : 83
		serializationMatchSteps[218] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 39/*PreCS*/);
		// 219: check-rule basecs::PackageCS.ownedClasses : 7|45
		serializationMatchSteps[219] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 17/*ClassCS|LibClassCS*/);
		// 220: check-rule basecs::PackageOwnerCS.ownedPackages : 50
		serializationMatchSteps[220] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 19/*LibPackageCS*/);
		// 221: check-rule basecs::PackageOwnerCS.ownedPackages : 78
		serializationMatchSteps[221] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 33/*PackageCS*/);
		// 222: check-rule basecs::PathNameCS.ownedPathElements : 28
		serializationMatchSteps[222] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 11/*FirstPathElementCS*/);
		// 223: check-rule basecs::PathNameCS.ownedPathElements : 51
		serializationMatchSteps[223] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 20/*LibPathElementCS*/);
		// 224: check-rule basecs::PathNameCS.ownedPathElements : 74
		serializationMatchSteps[224] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 29/*NextPathElementCS*/);
		// 225: check-rule basecs::RootCS.ownedImports : 33
		serializationMatchSteps[225] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/*ImportCS*/);
		// 226: check-rule basecs::StructuredClassCS.ownedOperations : 46|47|48|77
		serializationMatchSteps[226] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 32/*LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS*/);
		// 227: check-rule basecs::StructuredClassCS.ownedProperties : 53
		serializationMatchSteps[227] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 22/*LibPropertyCS*/);
		// 228: check-rule basecs::StructuredClassCS.ownedSuperTypes : 42|59|109|119|120
		serializationMatchSteps[228] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 66/*LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS*/);
		// 229: check-rule basecs::TemplateBindingCS.ownedMultiplicity : 62
		serializationMatchSteps[229] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 25/*MultiplicityCS*/);
		// 230: check-rule basecs::TemplateBindingCS.ownedSubstitutions : 104
		serializationMatchSteps[230] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 53/*TemplateParameterSubstitutionCS*/);
		// 231: check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 42|59|109|117|119|120|131
		serializationMatchSteps[231] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 77/*LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS*/);
		// 232: check-rule basecs::TemplateSignatureCS.ownedParameters : 116
		serializationMatchSteps[232] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 62/*TypeParameterCS*/);
		// 233: check-rule basecs::TemplateableElementCS.ownedSignature : 105
		serializationMatchSteps[233] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/*TemplateSignatureCS*/);
		// 234: check-rule basecs::TupleTypeCS.ownedParts : 108
		serializationMatchSteps[234] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 56/*TuplePartCS*/);
		// 235: check-rule basecs::TypeParameterCS.ownedExtends : 42|59|109|119|120
		serializationMatchSteps[235] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 66/*LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS*/);
		// 236: check-rule basecs::TypedElementCS.ownedType : 118
		serializationMatchSteps[236] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/*TypedMultiplicityRefCS*/);
		// 237: check-rule basecs::TypedRefCS.ownedMultiplicity : 62
		serializationMatchSteps[237] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/*MultiplicityCS*/);
		// 238: check-rule basecs::TypedTypeRefCS.ownedBinding : 103
		serializationMatchSteps[238] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 52/*TemplateBindingCS*/);
		// 239: check-rule basecs::TypedTypeRefCS.ownedPathName : 52
		serializationMatchSteps[239] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 21/*LibPathNameCS*/);
		// 240: check-rule basecs::WildcardTypeRefCS.ownedExtends : 42|59|109|119|120
		serializationMatchSteps[240] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 66/*LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS*/);
		// 241: check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[241] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 6/*CurlyBracketedClauseCS*/);
		// 242: check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 80
		serializationMatchSteps[242] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 35/*PathNameCS*/);
		// 243: check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 92
		serializationMatchSteps[243] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 44/*RoundBracketedClauseCS*/);
		// 244: check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 100
		serializationMatchSteps[244] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 50/*SquareBracketedClauseCS*/);
		// 245: check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 10
		serializationMatchSteps[245] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 4/*CollectionLiteralPartCS*/);
		// 246: check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 12
		serializationMatchSteps[246] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 5/*CollectionTypeCS*/);
		// 247: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[247] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 248: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 81
		serializationMatchSteps[248] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 36/*PatternExpCS*/);
		// 249: check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[249] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 250: check-rule essentialoclcs::CollectionPatternCS.ownedParts : 81
		serializationMatchSteps[250] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 36/*PatternExpCS*/);
		// 251: check-rule essentialoclcs::CollectionPatternCS.ownedType : 12
		serializationMatchSteps[251] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 5/*CollectionTypeCS*/);
		// 252: check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62
		serializationMatchSteps[252] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 25/*MultiplicityCS*/);
		// 253: check-rule essentialoclcs::CollectionTypeCS.ownedType : 11|12|59|89|109|111|112|115
		serializationMatchSteps[253] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 61/*CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS*/);
		// 254: check-rule essentialoclcs::ContextCS.ownedExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[254] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 255: check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 97
		serializationMatchSteps[255] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 48/*ShadowPartCS*/);
		// 256: check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[256] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 257: check-rule essentialoclcs::IfExpCS.ownedCondition : 6|9|27|32|36|41|43|57|66|73|75|76|81|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[257] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 76/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 258: check-rule essentialoclcs::IfExpCS.ownedElseExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[258] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 259: check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
		serializationMatchSteps[259] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 9/*ElseIfThenExpCS*/);
		// 260: check-rule essentialoclcs::IfExpCS.ownedThenExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[260] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 261: check-rule essentialoclcs::IfThenExpCS.ownedCondition : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[261] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 262: check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[262] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 263: check-rule essentialoclcs::InfixExpCS.ownedLeft : 6|9|32|36|41|57|66|73|75|76|86|87|88|96|102|106|113|126
		serializationMatchSteps[263] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 72/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 264: check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[264] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 265: check-rule essentialoclcs::LetExpCS.ownedInExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[265] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 266: check-rule essentialoclcs::LetExpCS.ownedVariables : 44
		serializationMatchSteps[266] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 16/*LetVariableCS*/);
		// 267: check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 92
		serializationMatchSteps[267] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 44/*RoundBracketedClauseCS*/);
		// 268: check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 58
		serializationMatchSteps[268] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 23/*MapLiteralPartCS*/);
		// 269: check-rule essentialoclcs::MapLiteralExpCS.ownedType : 59
		serializationMatchSteps[269] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 24/*MapTypeCS*/);
		// 270: check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[270] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 271: check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[271] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 272: check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110
		serializationMatchSteps[272] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 57/*TypeExpCS*/);
		// 273: check-rule essentialoclcs::MapTypeCS.ownedValueType : 110
		serializationMatchSteps[273] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 57/*TypeExpCS*/);
		// 274: check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8
		serializationMatchSteps[274] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3/*CoIteratorVariableCS*/);
		// 275: check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[275] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 276: check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 6|9|27|32|36|41|43|57|66|68|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[276] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 75/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 277: check-rule essentialoclcs::NavigatingArgCS.ownedType : 110
		serializationMatchSteps[277] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/*TypeExpCS*/);
		// 278: check-rule essentialoclcs::NestedExpCS.ownedExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[278] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 279: check-rule essentialoclcs::OperatorExpCS.ownedRight : 43|85
		serializationMatchSteps[279] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 42/*LetExpCS|PrefixedLetExpCS*/);
		// 280: check-rule essentialoclcs::OperatorExpCS.ownedRight : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[280] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 281: check-rule essentialoclcs::OperatorExpCS.ownedRight : 6|9|32|36|41|57|66|73|75|76|86|87|88|96|102|106|113|126
		serializationMatchSteps[281] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 72/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 282: check-rule essentialoclcs::PatternExpCS.ownedPatternType : 110
		serializationMatchSteps[282] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 57/*TypeExpCS*/);
		// 283: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 102
		serializationMatchSteps[283] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 51/*StringLiteralExpCS*/);
		// 284: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 6|9|27|32|36|41|43|57|66|73|75|76|81|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[284] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 76/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 285: check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[285] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 286: check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 107
		serializationMatchSteps[286] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 55/*TupleLiteralPartCS*/);
		// 287: check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 114
		serializationMatchSteps[287] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 60/*TypeLiteralWithMultiplicityCS*/);
		// 288: check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[288] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 6/*CurlyBracketedClauseCS*/);
		// 289: check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 80
		serializationMatchSteps[289] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 35/*PathNameCS*/);
		// 290: check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[290] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 291: check-rule essentialoclcs::VariableCS.ownedInitExpression : 6|9|27|32|36|41|43|57|66|73|75|76|85|86|87|88|96|102|106|113|126
		serializationMatchSteps[291] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 74/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 292: check-rule essentialoclcs::VariableCS.ownedType : 110
		serializationMatchSteps[292] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 57/*TypeExpCS*/);
		// 293: check-rule oclstdlibcs::LibIterationCS.ownedAccumulator : 1
		serializationMatchSteps[293] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATOR, 0/*AccumulatorCS*/);
		// 294: check-rule oclstdlibcs::LibIterationCS.ownedIterators : 37
		serializationMatchSteps[294] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, 14/*IteratorCS*/);
		// 295: check-rule oclstdlibcs::LibPackageCS.ownedPrecedences : 84
		serializationMatchSteps[295] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES, 40/*PrecedenceCS*/);
		// 296: check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : 49
		serializationMatchSteps[296] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, 18/*LibOppositeCS*/);
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
		// 48: |LambdaTypeCS::name.'Lambda'|
		serializationMatchTerms[48] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, 5 /* 'Lambda' */);
		// 49: |LambdaTypeCS::ownedContextType|
		serializationMatchTerms[49] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE);
		// 50: |LambdaTypeCS::ownedParameterTypes|
		serializationMatchTerms[50] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES);
		// 51: |LambdaTypeCS::ownedResultType|
		serializationMatchTerms[51] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE);
		// 52: |LetExpCS::isImplicit|
		serializationMatchTerms[52] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__IS_IMPLICIT);
		// 53: |LetExpCS::ownedInExpression|
		serializationMatchTerms[53] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		// 54: |LetExpCS::ownedVariables|
		serializationMatchTerms[54] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		// 55: |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchTerms[55] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// 56: |LibClassCS::metaclassName|
		serializationMatchTerms[56] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME);
		// 57: |LibIterationCS::isInvalidating.'invalidating'|
		serializationMatchTerms[57] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING, 11 /* 'invalidating' */);
		// 58: |LibIterationCS::isValidating.'validating'|
		serializationMatchTerms[58] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING, 17 /* 'validating' */);
		// 59: |LibIterationCS::ownedAccumulator|
		serializationMatchTerms[59] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATOR);
		// 60: |LibIterationCS::ownedIterators|
		serializationMatchTerms[60] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS);
		// 61: |LibOperationCS::isInvalidating.'invalidating'|
		serializationMatchTerms[61] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING, 11 /* 'invalidating' */);
		// 62: |LibOperationCS::isStatic.'static'|
		serializationMatchTerms[62] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC, 15 /* 'static' */);
		// 63: |LibOperationCS::isValidating.'validating'|
		serializationMatchTerms[63] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING, 17 /* 'validating' */);
		// 64: |LibOperationCS::precedence|
		serializationMatchTerms[64] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE);
		// 65: |LibPackageCS::ownedPrecedences|
		serializationMatchTerms[65] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES);
		// 66: |LibPropertyCS::isStatic.'static'|
		serializationMatchTerms[66] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC, 15 /* 'static' */);
		// 67: |LibPropertyCS::ownedOpposite|
		serializationMatchTerms[67] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE);
		// 68: |MapLiteralExpCS::ownedParts|
		serializationMatchTerms[68] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		// 69: |MapLiteralExpCS::ownedType|
		serializationMatchTerms[69] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		// 70: |MapLiteralPartCS::ownedKey|
		serializationMatchTerms[70] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		// 71: |MapLiteralPartCS::ownedValue|
		serializationMatchTerms[71] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		// 72: |MapTypeCS::name.'Map'|
		serializationMatchTerms[72] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 6 /* 'Map' */);
		// 73: |MapTypeCS::ownedKeyType|
		serializationMatchTerms[73] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		// 74: |MapTypeCS::ownedValueType|
		serializationMatchTerms[74] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		// 75: |ModelElementCS::ownedAnnotations|
		serializationMatchTerms[75] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		// 76: |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[76] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// 77: |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[77] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// 78: |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[78] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 19 /* '|1' */);
		// 79: |MultiplicityCS::isNullFree|
		serializationMatchTerms[79] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE);
		// 80: |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[80] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */);
		// 81: |NamedElementCS::name|
		serializationMatchTerms[81] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// 82: |NavigatingArgCS::ownedCoIterator|
		serializationMatchTerms[82] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		// 83: |NavigatingArgCS::ownedInitExpression|
		serializationMatchTerms[83] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		// 84: |NavigatingArgCS::ownedNameExpression|
		serializationMatchTerms[84] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		// 85: |NavigatingArgCS::ownedType|
		serializationMatchTerms[85] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		// 86: |NavigatingArgCS::prefix.','|
		serializationMatchTerms[86] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */);
		// 87: |NavigatingArgCS::prefix.';'|
		serializationMatchTerms[87] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 3 /* ';' */);
		// 88: |NavigatingArgCS::prefix.'|'|
		serializationMatchTerms[88] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 18 /* '|' */);
		// 89: |NavigatingArgCS::prefix|
		serializationMatchTerms[89] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX);
		// 90: |NestedExpCS::ownedExpression|
		serializationMatchTerms[90] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		// 91: |NumberLiteralExpCS::symbol|
		serializationMatchTerms[91] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		// 92: |OperationCS::ownedBodyExpressions|
		serializationMatchTerms[92] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		// 93: |OperationCS::ownedExceptions|
		serializationMatchTerms[93] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		// 94: |OperationCS::ownedParameters|
		serializationMatchTerms[94] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		// 95: |OperationCS::ownedPostconditions|
		serializationMatchTerms[95] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		// 96: |OperationCS::ownedPreconditions|
		serializationMatchTerms[96] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		// 97: |OperatorExpCS::ownedRight|
		serializationMatchTerms[97] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		// 98: |PackageCS::nsPrefix|
		serializationMatchTerms[98] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		// 99: |PackageCS::nsURI|
		serializationMatchTerms[99] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		// 100: |PackageCS::ownedClasses|
		serializationMatchTerms[100] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		// 101: |PackageOwnerCS::ownedPackages|
		serializationMatchTerms[101] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		// 102: |PathElementCS::referredElement|
		serializationMatchTerms[102] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// 103: |PathNameCS::ownedPathElements|
		serializationMatchTerms[103] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// 104: |PatternExpCS::ownedPatternType|
		serializationMatchTerms[104] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		// 105: |PatternExpCS::patternVariableName|
		serializationMatchTerms[105] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		// 106: |PrecedenceCS::isRightAssociative.'right'|
		serializationMatchTerms[106] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE, 14 /* 'right' */);
		// 107: |PrecedenceCS::isRightAssociative|
		serializationMatchTerms[107] = createSerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE);
		// 108: |PrimitiveTypeRefCS::name|
		serializationMatchTerms[108] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		// 109: |RootCS::ownedImports|
		serializationMatchTerms[109] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		// 110: |SelfExpCS::name|
		serializationMatchTerms[110] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SELF_EXP_CS__NAME);
		// 111: |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[111] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// 112: |ShadowPartCS::referredProperty|
		serializationMatchTerms[112] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// 113: |SpecificationCS::exprString|
		serializationMatchTerms[113] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		// 114: |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[114] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// 115: |StringLiteralExpCS::segments|
		serializationMatchTerms[115] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// 116: |StructuralFeatureCS::default|
		serializationMatchTerms[116] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		// 117: |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchTerms[117] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		// 118: |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchTerms[118] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, 8 /* 'abstract' */);
		// 119: |StructuredClassCS::isInterface|
		serializationMatchTerms[119] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE);
		// 120: |StructuredClassCS::ownedMetaclass|
		serializationMatchTerms[120] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_METACLASS);
		// 121: |StructuredClassCS::ownedOperations|
		serializationMatchTerms[121] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		// 122: |StructuredClassCS::ownedProperties|
		serializationMatchTerms[122] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		// 123: |StructuredClassCS::ownedSuperTypes|
		serializationMatchTerms[123] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		// 124: |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[124] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// 125: |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[125] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// 126: |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[126] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// 127: |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[127] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// 128: |TemplateableElementCS::ownedSignature|
		serializationMatchTerms[128] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		// 129: |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[129] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// 130: |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[130] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 7 /* 'Tuple' */);
		// 131: |TupleTypeCS::ownedParts|
		serializationMatchTerms[131] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// 132: |TypeLiteralExpCS::ownedPathName|
		serializationMatchTerms[132] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_PATH_NAME);
		// 133: |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[133] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// 134: |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[134] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// 135: |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[135] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// 136: |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[136] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// 137: |TypeParameterCS::ownedExtends|
		serializationMatchTerms[137] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// 138: |TypedElementCS::isOptional|
		serializationMatchTerms[138] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__IS_OPTIONAL);
		// 139: |TypedElementCS::ownedType|
		serializationMatchTerms[139] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		// 140: |TypedElementCS::qualifiers|
		serializationMatchTerms[140] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		// 141: |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[141] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// 142: |TypedTypeRefCS::isTypeof.'typeof'|
		serializationMatchTerms[142] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF, 16 /* 'typeof' */);
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
		// 161: (|ConstraintCS::ownedSpecification| - 1)
		serializationMatchTerms[161] = createSerializationMatchTermSubtract(26, 1);
		// 162: (|ConstraintCS::stereotype.'inv'| - 1)
		serializationMatchTerms[162] = createSerializationMatchTermSubtract(27, 1);
		// 163: (|ConstraintCS::stereotype.'post'| - 1)
		serializationMatchTerms[163] = createSerializationMatchTermSubtract(28, 1);
		// 164: (|ConstraintCS::stereotype.'pre'| - 1)
		serializationMatchTerms[164] = createSerializationMatchTermSubtract(29, 1);
		// 165: (|ContextCS::ownedExpression| - 1)
		serializationMatchTerms[165] = createSerializationMatchTermSubtract(30, 1);
		// 166: (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchTerms[166] = createSerializationMatchTermSubtract(31, 1);
		// 167: (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchTerms[167] = createSerializationMatchTermGreaterThan(31, 0);
		// 168: (|ExpSpecificationCS::ownedExpression| - 1)
		serializationMatchTerms[168] = createSerializationMatchTermSubtract(35, 1);
		// 169: (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[169] = createSerializationMatchTermSubtract(37, 1);
		// 170: (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[170] = createSerializationMatchTermSubtract(38, 1);
		// 171: (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[171] = createSerializationMatchTermSubtract(40, 1);
		// 172: (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[172] = createSerializationMatchTermSubtract(41, 1);
		// 173: (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[173] = createSerializationMatchTermSubtract(42, 1);
		// 174: (|ImportCS::ownedPathName| - 1)
		serializationMatchTerms[174] = createSerializationMatchTermSubtract(44, 1);
		// 175: (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[175] = createSerializationMatchTermSubtract(45, 1);
		// 176: (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[176] = createSerializationMatchTermSubtract(47, 1);
		// 177: (|LambdaTypeCS::name.'Lambda'| - 1)
		serializationMatchTerms[177] = createSerializationMatchTermSubtract(48, 1);
		// 178: (|LambdaTypeCS::ownedContextType| - 1)
		serializationMatchTerms[178] = createSerializationMatchTermSubtract(49, 1);
		// 179: (|LambdaTypeCS::ownedParameterTypes| - 1)
		serializationMatchTerms[179] = createSerializationMatchTermSubtract(50, 1);
		// 180: (|LambdaTypeCS::ownedParameterTypes| > 0)
		serializationMatchTerms[180] = createSerializationMatchTermGreaterThan(50, 0);
		// 181: (|LambdaTypeCS::ownedResultType| - 1)
		serializationMatchTerms[181] = createSerializationMatchTermSubtract(51, 1);
		// 182: (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[182] = createSerializationMatchTermSubtract(53, 1);
		// 183: (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[183] = createSerializationMatchTermSubtract(54, 1);
		// 184: (|LibIterationCS::ownedIterators| - 1)
		serializationMatchTerms[184] = createSerializationMatchTermSubtract(60, 1);
		// 185: (|LibPackageCS::ownedPrecedences| > 0)
		serializationMatchTerms[185] = createSerializationMatchTermGreaterThan(65, 0);
		// 186: (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[186] = createSerializationMatchTermSubtract(68, 1);
		// 187: (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[187] = createSerializationMatchTermGreaterThan(68, 0);
		// 188: (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[188] = createSerializationMatchTermSubtract(69, 1);
		// 189: (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[189] = createSerializationMatchTermSubtract(70, 1);
		// 190: (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[190] = createSerializationMatchTermSubtract(71, 1);
		// 191: (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[191] = createSerializationMatchTermSubtract(72, 1);
		// 192: (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[192] = createSerializationMatchTermSubtract(73, 2);
		// 193: (|ModelElementCS::ownedAnnotations| - 1)
		serializationMatchTerms[193] = createSerializationMatchTermSubtract(75, 1);
		// 194: (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[194] = createSerializationMatchTermSubtract(76, 1);
		// 195: (|MultiplicityCS::isNullFree.'|1'| - 1)
		serializationMatchTerms[195] = createSerializationMatchTermSubtract(78, 1);
		// 196: (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[196] = createSerializationMatchTermSubtract(80, 1);
		// 197: (|NamedElementCS::name| - 1)
		serializationMatchTerms[197] = createSerializationMatchTermSubtract(81, 1);
		// 198: (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[198] = createSerializationMatchTermSubtract(82, 1);
		// 199: (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[199] = createSerializationMatchTermSubtract(83, 1);
		// 200: (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[200] = createSerializationMatchTermSubtract(84, 1);
		// 201: (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[201] = createSerializationMatchTermSubtract(85, 1);
		// 202: (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[202] = createSerializationMatchTermSubtract(86, 1);
		// 203: (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[203] = createSerializationMatchTermSubtract(87, 1);
		// 204: (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[204] = createSerializationMatchTermSubtract(88, 1);
		// 205: (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[205] = createSerializationMatchTermSubtract(90, 1);
		// 206: (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[206] = createSerializationMatchTermSubtract(91, 1);
		// 207: (|OperationCS::ownedParameters| - 1)
		serializationMatchTerms[207] = createSerializationMatchTermSubtract(94, 1);
		// 208: (|OperationCS::ownedParameters| > 0)
		serializationMatchTerms[208] = createSerializationMatchTermGreaterThan(94, 0);
		// 209: (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[209] = createSerializationMatchTermSubtract(97, 1);
		// 210: (|PackageCS::nsPrefix| - V0)
		serializationMatchTerms[210] = createSerializationMatchTermSubtract(98, 2);
		// 211: (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[211] = createSerializationMatchTermSubtract(102, 1);
		// 212: (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[212] = createSerializationMatchTermSubtract(103, 1);
		// 213: (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[213] = createSerializationMatchTermSubtract(104, 1);
		// 214: (|PrecedenceCS::isRightAssociative.'right'| - 1)
		serializationMatchTerms[214] = createSerializationMatchTermSubtract(106, 1);
		// 215: (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[215] = createSerializationMatchTermSubtract(108, 1);
		// 216: (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[216] = createSerializationMatchTermSubtract(111, 1);
		// 217: (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[217] = createSerializationMatchTermSubtract(112, 1);
		// 218: (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[218] = createSerializationMatchTermSubtract(114, 1);
		// 219: (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchTerms[219] = createSerializationMatchTermSubtract(123, 1);
		// 220: (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchTerms[220] = createSerializationMatchTermGreaterThan(123, 0);
		// 221: (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[221] = createSerializationMatchTermSubtract(125, 1);
		// 222: (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[222] = createSerializationMatchTermSubtract(126, 1);
		// 223: (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[223] = createSerializationMatchTermSubtract(127, 1);
		// 224: (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[224] = createSerializationMatchTermSubtract(129, 1);
		// 225: (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[225] = createSerializationMatchTermSubtract(130, 1);
		// 226: (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[226] = createSerializationMatchTermSubtract(131, 1);
		// 227: (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[227] = createSerializationMatchTermGreaterThan(131, 0);
		// 228: (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[228] = createSerializationMatchTermSubtract(133, 1);
		// 229: (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[229] = createSerializationMatchTermSubtract(135, 1);
		// 230: (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[230] = createSerializationMatchTermSubtract(137, 1);
		// 231: (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[231] = createSerializationMatchTermGreaterThan(137, 0);
		// 232: (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[232] = createSerializationMatchTermSubtract(139, 1);
		// 233: (|TypedTypeRefCS::isTypeof.'typeof'| - 1)
		serializationMatchTerms[233] = createSerializationMatchTermSubtract(142, 1);
		// 234: (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[234] = createSerializationMatchTermSubtract(145, 1);
		// 235: (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[235] = createSerializationMatchTermSubtract(146, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS-0(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] = createSerializationRule("FirstPathElementCS-0", 28,
			createSerializationMatchSteps(
				45		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				243		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::MultiplicityBoundsCS-0(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { ".." upperBound=UPPER }[?] }
		serializationRules[1] = createSerializationRule("MultiplicityBoundsCS-0", 61,
			createSerializationMatchSteps(
				69		/* assert |MultiplicityCS::isNullFree| == 0 */,
				123		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				30		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				174		/* V00*2-steps || value */,
				126		/* '..' || no-space value no-space */,
				115		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-0(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" }
		serializationRules[2] = createSerializationRule("MultiplicityCS-0", 62,
			createSerializationMatchSteps(
				69		/* assert |MultiplicityCS::isNullFree| == 0 */,
				123		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				30		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				135		/* '[' || no-space value no-space */,
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				174		/* V00*2-steps || value */,
				126		/* '..' || no-space value no-space */,
				115		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				169		/* '|?' || no-space value no-space */,
				136		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-1(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree?="|1" "]" }
		serializationRules[3] = createSerializationRule("MultiplicityCS-1", 62,
			createSerializationMatchSteps(
				31		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				123		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				30		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				135		/* '[' || no-space value no-space */,
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				174		/* V00*2-steps || value */,
				126		/* '..' || no-space value no-space */,
				115		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				5		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				136		/* ']' || no-space value */
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
				69		/* assert |MultiplicityCS::isNullFree| == 0 */,
				123		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				30		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				135		/* '[' || no-space value no-space */,
				13		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				174		/* V00*2-steps || value */,
				126		/* '..' || no-space value no-space */,
				115		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				136		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-3(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "|?" "]" }
		serializationRules[5] = createSerializationRule("MultiplicityCS-3", 62,
			createSerializationMatchSteps(
				69		/* assert |MultiplicityCS::isNullFree| == 0 */,
				32		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				135		/* '[' || no-space value no-space */,
				112		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				169		/* '|?' || no-space value no-space */,
				136		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-4(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} isNullFree?="|1" "]" }
		serializationRules[6] = createSerializationRule("MultiplicityCS-4", 62,
			createSerializationMatchSteps(
				31		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				32		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				135		/* '[' || no-space value no-space */,
				112		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				5		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				136		/* ']' || no-space value */
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
				69		/* assert |MultiplicityCS::isNullFree| == 0 */,
				32		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				135		/* '[' || no-space value no-space */,
				112		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				136		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityStringCS-0(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		serializationRules[8] = createSerializationRule("MultiplicityStringCS-0", 63,
			createSerializationMatchSteps(
				69		/* assert |MultiplicityCS::isNullFree| == 0 */,
				32		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				112		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::NextPathElementCS-0(basecs::PathElementCS): referredElement=UnreservedName
		serializationRules[9] = createSerializationRule("NextPathElementCS-0", 74,
			createSerializationMatchSteps(
				45		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				242		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::PathNameCS-0(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[10] = createSerializationRule("PathNameCS-0", 80,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				230		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */,
				175		/* V00*2-steps || value */,
				128		/* '::' || no-space value no-space */,
				232		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
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
				229		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : MultiplicityCS */,
				230		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : TemplateParameterSubstitutionCS */,
				159		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				106		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			),
			createSerializationSteps(
				88		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				175		/* V00*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				88		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				181		/* V01*1-steps || value */,
				59		/* TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				231		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
				52		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				232		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				107		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				120		/* '(' || no-space value no-space */,
				67		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				175		/* V00*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				67		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				121		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 62/* TypeParameterCS */,
					(116/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TypeParameterCS-0(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[14] = createSerializationRule("TypeParameterCS-0", 116,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				235		/* check-rule basecs::TypeParameterCS.ownedExtends : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				110		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				149		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				218		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				145		/* 'extends' || soft-space value soft-space */,
				44		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */,
				184		/* V01*2-steps || value */,
				118		/* '&&' || soft-space value soft-space */,
				44		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */
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
				224		/* check-rule basecs::PathNameCS.ownedPathElements : NextPathElementCS */,
				104		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			),
			createSerializationSteps(
				232		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */,
				175		/* V00*2-steps || value */,
				128		/* '::' || no-space value no-space */,
				232		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
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
				240		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				138		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			),
			createSerializationSteps(
				132		/* '?' || soft-space value soft-space */,
				174		/* V00*2-steps || value */,
				145		/* 'extends' || soft-space value soft-space */,
				45		/* WildcardTypeRefCS::ownedExtends=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 65/* TypedRefCS */,
					(119/*TypedRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::BooleanLiteralExpCS-0(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[17] = createSerializationRule("BooleanLiteralExpCS-0", 6,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			),
			createSerializationSteps(
				113		/* BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, false,
					(9/*'false|true'*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CoIteratorVariableCS-0(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[18] = createSerializationRule("CoIteratorVariableCS-0", 8,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				97		/* assert |VariableCS::ownedInitExpression| == 0 */,
				292		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				137		/* assign V0 = |VariableCS::ownedType| */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				218		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				174		/* V00*2-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				100		/* VariableCS::ownedType=TypeExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				245		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS */,
				246		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS */,
				2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				100		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				141		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				93		/* CollectionLiteralExpCS::ownedType=CollectionTypeCS || value */,
				167		/* '{' || soft-space value push soft-new-line */,
				177		/* V00*4-steps || value */,
				68		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				184		/* V01*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				68		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				247		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				249		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				112		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				38		/* CollectionLiteralPartCS::ownedExpression=ExpCS || value */,
				174		/* V00*2-steps || value */,
				126		/* '..' || no-space value no-space */,
				56		/* CollectionLiteralPartCS::ownedLastExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				63		/* assert |CollectionLiteralPartCS::ownedLastExpression| == 0 */,
				248		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : PatternExpCS */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				39		/* CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
				64		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				250		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				251		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				113		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				142		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				94		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				167		/* '{' || soft-space value push soft-new-line */,
				179		/* V00*6-steps || value */,
				69		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				184		/* V01*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				69		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				123		/* '++' || soft-space value soft-space */,
				110		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				252		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				253		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				114		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				151		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				15		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				120		/* '(' || no-space value no-space */,
				95		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				181		/* V01*1-steps || value */,
				29		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				121		/* ')' || no-space value */
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
				65		/* assert |CurlyBracketedClauseCS::value| == 0 */,
				255		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : ShadowPartCS */,
				101		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				143		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				167		/* '{' || soft-space value push soft-new-line */,
				177		/* V00*4-steps || value */,
				70		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				184		/* V01*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				70		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 48/* ShadowPartCS */,
					(97/*ShadowPartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::ElseIfThenExpCS-0(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[25] = createSerializationRule("ElseIfThenExpCS-0", 20,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				261		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				262		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				16		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				15		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				143		/* 'elseif' || soft-new-line pop soft-space value push soft-space */,
				31		/* IfThenExpCS::ownedCondition=ExpCS || value */,
				163		/* 'then' || pop value push soft-space */,
				92		/* IfThenExpCS::ownedThenExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				263		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				280		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				43		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				18		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				57		/* InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value */,
				222		/* NamedElementCS::name=BinaryOperatorName || soft-space value soft-space */,
				237		/* OperatorExpCS::ownedRight=ExpCS || value */
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
				66		/* assert |IfExpCS::isImplicit| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				257		/* check-rule essentialoclcs::IfExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				258		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				259		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS */,
				260		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				13		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				117		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				14		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				12		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				146		/* 'if' || soft-new-line value push soft-space */,
				30		/* IfExpCS::ownedCondition=ExpCS|PatternExpCS || value */,
				164		/* 'then' || pop soft-space value push soft-space */,
				91		/* IfExpCS::ownedThenExpression=ExpCS || value */,
				172		/* V00*1-steps || value */,
				46		/* IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value */,
				142		/* 'else' || soft-new-line pop value push soft-space */,
				37		/* IfExpCS::ownedElseExpression=ExpCS || value */,
				144		/* 'endif' || soft-new-line pop value soft-space */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				150		/* 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::LambdaLiteralExpCS-0(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[29] = createSerializationRule("LambdaLiteralExpCS-0", 41,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				264		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				19		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				133		/* 'Lambda' || soft-space value soft-space */,
				167		/* '{' || soft-space value push soft-new-line */,
				43		/* LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::LetExpCS-0(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[30] = createSerializationRule("LetExpCS-0", 43,
			createSerializationMatchSteps(
				67		/* assert |LetExpCS::isImplicit| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				265		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				266		/* check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS */,
				23		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				102		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				153		/* 'let' || soft-space value push */,
				103		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				175		/* V00*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				103		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				149		/* 'in' || soft-space pop value soft-new-line */,
				48		/* LetExpCS::ownedInExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				291		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				267		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				292		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				59		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				163		/* assign V1 = |VariableCS::ownedType| */,
				119		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				218		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				171		/* V00*1-steps || value */,
				84		/* LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				183		/* V01*2-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				100		/* VariableCS::ownedType=TypeExpCS || value */,
				130		/* '=' || soft-space value soft-space */,
				52		/* VariableCS::ownedInitExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				268		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS */,
				269		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS */,
				24		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				103		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				147		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				96		/* MapLiteralExpCS::ownedType=MapTypeCS || value */,
				167		/* '{' || soft-space value push soft-new-line */,
				177		/* V00*4-steps || value */,
				71		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				184		/* V01*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				71		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				270		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				271		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				26		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				25		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				54		/* MapLiteralPartCS::ownedKey=ExpCS || value */,
				166		/* 'with' || value */,
				101		/* MapLiteralPartCS::ownedValue=ExpCS || value */
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
				272		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				273		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				122		/* assign V0 = |MapTypeCS::ownedValueType| */,
				28		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				27		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				17		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				120		/* '(' || no-space value no-space */,
				55		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				125		/* ',' || no-space value soft-space */,
				102		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				121		/* ')' || no-space value */
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
				70		/* assert |NamedElementCS::name| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				82		/* assert |RootCS::ownedImports| == 0 */,
				254		/* check-rule essentialoclcs::ContextCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				10		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				40		/* ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NameExpCS-0(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre?="@" "pre" }[?] }
		serializationRules[36] = createSerializationRule("NameExpCS-0", 66,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				241		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				242		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS */,
				243		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				244		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS */,
				181		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				170		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				150		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				111		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				74		/* AbstractNameExpCS::ownedPathName=PathNameCS || value */,
				172		/* V00*1-steps || value */,
				87		/* AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value */,
				181		/* V01*1-steps || value */,
				83		/* AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				191		/* V02*1-steps || value */,
				34		/* AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				199		/* V03*2-steps || value */,
				6		/* AbstractNameExpCS::isPre?='@' || soft-space value soft-space */,
				159		/* 'pre' || soft-space value soft-space */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				71		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				72		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				73		/* assert |NavigatingArgCS::ownedNameExpression| == 0 */,
				75		/* assert |NavigatingArgCS::prefix| == 0 */,
				277		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				37		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				127		/* ':' || soft-space value soft-space */,
				97		/* NavigatingArgCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 57/* TypeExpCS */,
					(110/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-1(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[38] = createSerializationRule("NavigatingArgCS-1", 67,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				75		/* assert |NavigatingArgCS::prefix| == 0 */,
				274		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				275		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				277		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				157		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				125		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				37		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				127		/* ':' || soft-space value soft-space */,
				97		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				174		/* V00*2-steps || value */,
				166		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				183		/* V01*2-steps || value */,
				130		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				75		/* assert |NavigatingArgCS::prefix| == 0 */,
				274		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				275		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				277		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				35		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				156		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				127		/* assign V0 = |NavigatingArgCS::ownedType| */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				174		/* V00*2-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				97		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				183		/* V01*2-steps || value */,
				166		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				148		/* 'in' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |NavigatingArgCS::ownedType| == 0 */,
				75		/* assert |NavigatingArgCS::prefix| == 0 */,
				274		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				275		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				126		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				34		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				166		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				174		/* V00*2-steps || value */,
				130		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				71		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				72		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				74		/* assert |NavigatingArgCS::ownedType| == 0 */,
				75		/* assert |NavigatingArgCS::prefix| == 0 */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				62		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/* NavigatingArgExpCS */,
					(68/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingBarArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[42] = createSerializationRule("NavigatingBarArgCS-0", 69,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				71		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				275		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				277		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				127		/* assign V0 = |NavigatingArgCS::ownedType| */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				40		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				157		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				106		/* NavigatingArgCS::prefix='|' || soft-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				178		/* V00*5-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				97		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				183		/* V01*2-steps || value */,
				130		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				274		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				275		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				277		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				157		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				125		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				37		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				38		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				108		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				127		/* ':' || soft-space value soft-space */,
				97		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				174		/* V00*2-steps || value */,
				166		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				183		/* V01*2-steps || value */,
				130		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				274		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				275		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				277		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				35		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				156		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				127		/* assign V0 = |NavigatingArgCS::ownedType| */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				38		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				108		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				174		/* V00*2-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				97		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				183		/* V01*2-steps || value */,
				166		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				148		/* 'in' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |NavigatingArgCS::ownedType| == 0 */,
				274		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				275		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				126		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				34		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				38		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				108		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				166		/* 'with' || value */,
				28		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				174		/* V00*2-steps || value */,
				130		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				71		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				72		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				74		/* assert |NavigatingArgCS::ownedType| == 0 */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				38		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				108		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				71		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				275		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				276		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				277		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				127		/* assign V0 = |NavigatingArgCS::ownedType| */,
				36		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				39		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				157		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				107		/* NavigatingArgCS::prefix=';' || no-space value soft-new-line */,
				61		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				178		/* V00*5-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				97		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				183		/* V01*2-steps || value */,
				130		/* '=' || soft-space value soft-space */,
				49		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				278		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				41		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				119		/* '(' || value no-space */,
				42		/* NestedExpCS::ownedExpression=ExpCS || value */,
				121		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NullLiteralExpCS-0(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[49] = createSerializationRule("NullLiteralExpCS-0", 75,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				155		/* 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::NumberLiteralExpCS-0(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[50] = createSerializationRule("NumberLiteralExpCS-0", 76,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				42		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			),
			createSerializationSteps(
				114		/* NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::PatternExpCS-0(essentialoclcs::PatternExpCS): { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS }
		serializationRules[51] = createSerializationRule("PatternExpCS-0", 81,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				282		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : TypeExpCS */,
				47		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				129		/* assign V0 = |PatternExpCS::patternVariableName| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				171		/* V00*1-steps || value */,
				104		/* PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				79		/* PatternExpCS::ownedPatternType=TypeExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				279		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : LetExpCS|PrefixedLetExpCS */,
				43		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				217		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				238		/* OperatorExpCS::ownedRight=PrefixedLetExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				281		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				43		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				217		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				239		/* OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value */
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
				49		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
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
				120		/* '(' || no-space value no-space */,
				176		/* V00*3-steps || value */,
				225		/* RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value */,
				182		/* V01*1-steps || value */,
				226		/* RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value */,
				121		/* ')' || no-space value */
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
				83		/* assert |SelfExpCS::name| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				162		/* 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::ShadowPartCS-0(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[57] = createSerializationRule("ShadowPartCS-0", 97,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				284		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				50		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				51		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				109		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
				130		/* '=' || soft-space value soft-space */,
				50		/* ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				84		/* assert |ShadowPartCS::referredProperty| == 0 */,
				283		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : StringLiteralExpCS */,
				50		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			),
			createSerializationSteps(
				51		/* ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 51/* StringLiteralExpCS */,
					(102/*StringLiteralExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SimplePathNameCS-0(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[59] = createSerializationRule("SimplePathNameCS-0", 98,
			createSerializationMatchSteps(
				222		/* check-rule basecs::PathNameCS.ownedPathElements : FirstPathElementCS */,
				46		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			),
			createSerializationSteps(
				230		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 11/* FirstPathElementCS */,
					(28/*FirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SquareBracketedClauseCS-0(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[60] = createSerializationRule("SquareBracketedClauseCS-0", 100,
			createSerializationMatchSteps(
				285		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				105		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			),
			createSerializationSteps(
				135		/* '[' || no-space value no-space */,
				90		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				175		/* V00*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				90		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				136		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::StringLiteralExpCS-0(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[61] = createSerializationRule("StringLiteralExpCS-0", 102,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				131		/* assign V0 = |StringLiteralExpCS::segments| */
			),
			createSerializationSteps(
				173		/* V00*1-steps || value */,
				111		/* StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, false, GrammarCardinality.ONE_OR_MORE)
			});
		// EssentialOCL::TupleLiteralExpCS-0(essentialoclcs::TupleLiteralExpCS): { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" }
		serializationRules[62] = createSerializationRule("TupleLiteralExpCS-0", 106,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				286		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS */,
				108		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				134		/* 'Tuple' || soft-space value soft-space */,
				167		/* '{' || soft-space value push soft-new-line */,
				72		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				175		/* V00*2-steps || value */,
				124		/* ',' || no-space value soft-new-line */,
				72		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 55/* TupleLiteralPartCS */,
					(107/*TupleLiteralPartCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::TupleLiteralPartCS-0(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[63] = createSerializationRule("TupleLiteralPartCS-0", 107,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				291		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				292		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				59		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				137		/* assign V0 = |VariableCS::ownedType| */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				218		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				174		/* V00*2-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				100		/* VariableCS::ownedType=TypeExpCS || value */,
				130		/* '=' || soft-space value soft-space */,
				52		/* VariableCS::ownedInitExpression=ExpCS || value */
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
				234		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				53		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				109		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				148		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				169		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				19		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				180		/* V00*7-steps || value */,
				120		/* '(' || no-space value no-space */,
				186		/* V01*4-steps || value */,
				73		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				195		/* V02*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				73		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				121		/* ')' || no-space value */
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
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				135		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				49		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				18		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				171		/* V00*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				252		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				253		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				177		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				114		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				151		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				15		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				120		/* '(' || no-space value no-space */,
				95		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				181		/* V01*1-steps || value */,
				29		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				121		/* ')' || no-space value */,
				191		/* V02*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				272		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				273		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				162		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				122		/* assign V0 = |MapTypeCS::ownedValueType| */,
				28		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				27		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				17		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				120		/* '(' || no-space value no-space */,
				55		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				125		/* ',' || no-space value soft-space */,
				102		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				121		/* ')' || no-space value */,
				181		/* V01*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				288		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				289		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				290		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				177		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				134		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				55		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				161		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				76		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				178		/* V00*5-steps || value */,
				35		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				185		/* V01*3-steps || value */,
				167		/* '{' || soft-space value push soft-new-line */,
				78		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */,
				191		/* V02*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				64		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				250		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				251		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				177		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				113		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				142		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				94		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				167		/* '{' || soft-space value push soft-new-line */,
				179		/* V00*6-steps || value */,
				69		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				184		/* V01*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				69		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				123		/* '++' || soft-space value soft-space */,
				110		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				170		/* '}' || pop soft-new-line value soft-new-line */,
				191		/* V02*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				234		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				185		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				53		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				109		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				148		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				169		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				19		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				180		/* V00*7-steps || value */,
				120		/* '(' || no-space value no-space */,
				186		/* V01*4-steps || value */,
				73		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				195		/* V02*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				73		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				121		/* ')' || no-space value */,
				197		/* V03*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				91		/* assert |TypeLiteralExpCS::ownedPathName| == 0 */,
				287		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS */,
				54		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				98		/* TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 60/* TypeLiteralWithMultiplicityCS */,
					(114/*TypeLiteralWithMultiplicityCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[72] = createSerializationRule("TypeLiteralWithMultiplicityCS-0", 114,
			createSerializationMatchSteps(
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				135		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				49		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				18		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				171		/* V00*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				252		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				253		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				177		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				114		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				151		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				15		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				120		/* '(' || no-space value no-space */,
				95		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				181		/* V01*1-steps || value */,
				29		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				121		/* ')' || no-space value */,
				191		/* V02*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				272		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				273		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				162		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				122		/* assign V0 = |MapTypeCS::ownedValueType| */,
				28		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				27		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				17		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				120		/* '(' || no-space value no-space */,
				55		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				125		/* ',' || no-space value soft-space */,
				102		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				121		/* ')' || no-space value */,
				181		/* V01*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				234		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				185		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				53		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				109		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				148		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				169		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				19		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				180		/* V00*7-steps || value */,
				120		/* '(' || no-space value no-space */,
				186		/* V01*4-steps || value */,
				73		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				195		/* V02*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				73		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				121		/* ')' || no-space value */,
				197		/* V03*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				288		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				289		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				290		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				134		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				55		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				161		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				76		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				178		/* V00*5-steps || value */,
				35		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				185		/* V01*3-steps || value */,
				167		/* '{' || soft-space value push soft-new-line */,
				78		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				45		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				241		/* PathElementCS::referredElement=URI || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIFirstPathElementCS-1(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[78] = createSerializationRule("URIFirstPathElementCS-1", 123,
			createSerializationMatchSteps(
				45		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				243		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[79] = createSerializationRule("URIPathNameCS-0", 124,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				229		/* PathNameCS::ownedPathElements+=URIFirstPathElementCS || value */,
				175		/* V00*2-steps || value */,
				128		/* '::' || no-space value no-space */,
				232		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				122		/* '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// OCLstdlib::AccumulatorCS-0(basecs::ParameterCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[81] = createSerializationRule("AccumulatorCS-0", 1,
			createSerializationMatchSteps(
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				219		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				60		/* assert |AnnotationCS::ownedContents| == 0 */,
				61		/* assert |AnnotationCS::ownedReferences| == 0 */,
				204		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				99		/* assign V0 = (|AnnotationElementCS::ownedDetails| > 0) */,
				139		/* assign V1 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				137		/* 'annotation' || soft-space value soft-space */,
				220		/* NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				179		/* V00*6-steps || value */,
				120		/* '(' || no-space value no-space */,
				36		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				184		/* V01*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				36		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				121		/* ')' || no-space value */,
				129		/* ';' || no-space value soft-new-line */
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
				60		/* assert |AnnotationCS::ownedContents| == 0 */,
				61		/* assert |AnnotationCS::ownedReferences| == 0 */,
				212		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				204		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				29		/* assert (|ModelElementCS::ownedAnnotations| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				99		/* assign V0 = (|AnnotationElementCS::ownedDetails| > 0) */,
				139		/* assign V1 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				137		/* 'annotation' || soft-space value soft-space */,
				220		/* NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				179		/* V00*6-steps || value */,
				120		/* '(' || no-space value no-space */,
				36		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				184		/* V01*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				36		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				121		/* ')' || no-space value */,
				167		/* '{' || soft-space value push soft-new-line */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				115		/* assign V0 = |DetailCS::values| */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				224		/* NamedElementCS::name=Name|SINGLE_QUOTED_STRING || soft-space value soft-space */,
				130		/* '=' || soft-space value soft-space */,
				172		/* V00*1-steps || value */,
				117		/* DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.DETAIL_CS__VALUES, false, GrammarCardinality.ZERO_OR_MORE)
			});
		// OCLstdlib::DocumentationCS-0(basecs::DocumentationCS): { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" }
		serializationRules[85] = createSerializationRule("DocumentationCS-0", 17,
			createSerializationMatchSteps(
				70		/* assert |NamedElementCS::name| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				204		/* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
				116		/* assign V0 = |DocumentationCS::value| */,
				140		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				166		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				141		/* 'documentation' || soft-space value soft-space */,
				171		/* V00*1-steps || value */,
				116		/* DocumentationCS::value=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				188		/* V01*6-steps || value */,
				120		/* '(' || no-space value no-space */,
				36		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				195		/* V02*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				36		/* AnnotationElementCS::ownedDetails+=DetailCS || value */,
				121		/* ')' || no-space value */,
				129		/* ';' || no-space value soft-new-line */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				208		/* check-rule basecs::ImportCS.ownedPathName : URIPathNameCS */,
				153		/* assign V1 = |ImportCS::isAll.'::*'| */,
				17		/* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				124		/* assign V0 = |NamedElementCS::name| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				147		/* 'import' || soft-space value soft-space */,
				174		/* V00*2-steps || value */,
				219		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				75		/* ImportCS::ownedPathName=URIPathNameCS || value */,
				181		/* V01*1-steps || value */,
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				206		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				207		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				124		/* assign V0 = |NamedElementCS::name| */,
				7		/* assert (|ConstraintCS::stereotype.'inv'| - 1) == 0 */,
				152		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				244		/* ConstraintCS::stereotype='inv' || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				218		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				185		/* V01*3-steps || value */,
				120		/* '(' || no-space value no-space */,
				58		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				86		/* ConstraintCS::ownedSpecification=SpecificationCS || value */,
				129		/* ';' || no-space value soft-new-line */
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
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				219		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LambdaContextTypeRefCS-0(basecs::TypedTypeRefCS): ownedPathName=LibPathNameCS
		serializationRules[89] = createSerializationRule("LambdaContextTypeRefCS-0", 40,
			createSerializationMatchSteps(
				95		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				96		/* assert |TypedTypeRefCS::ownedBinding| == 0 */,
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				239		/* check-rule basecs::TypedTypeRefCS.ownedPathName : LibPathNameCS */,
				58		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				77		/* TypedTypeRefCS::ownedPathName=LibPathNameCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 21/* LibPathNameCS */,
					(52/*LibPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LambdaTypeCS-0(basecs::LambdaTypeCS): { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS }
		serializationRules[90] = createSerializationRule("LambdaTypeCS-0", 42,
			createSerializationMatchSteps(
				94		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				209		/* check-rule basecs::LambdaTypeCS.ownedContextType : LambdaContextTypeRefCS */,
				210		/* check-rule basecs::LambdaTypeCS.ownedParameterTypes : TypedMultiplicityRefCS */,
				211		/* check-rule basecs::LambdaTypeCS.ownedResultType : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				233		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				22		/* assert (|LambdaTypeCS::ownedResultType| - 1) == 0 */,
				21		/* assert (|LambdaTypeCS::ownedContextType| - 1) == 0 */,
				133		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				20		/* assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0 */,
				144		/* assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0) */,
				167		/* assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1) */
			),
			createSerializationSteps(
				16		/* LambdaTypeCS::name='Lambda' || soft-space value soft-space */,
				171		/* V00*1-steps || value */,
				85		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				33		/* LambdaTypeCS::ownedContextType=LambdaContextTypeRefCS || value */,
				120		/* '(' || no-space value no-space */,
				186		/* V01*4-steps || value */,
				65		/* LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value */,
				195		/* V02*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				65		/* LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				82		/* LambdaTypeCS::ownedResultType=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, false,
					(5/*'Lambda'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 15/* LambdaContextTypeRefCS */,
					(40/*LambdaContextTypeRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 65/* TypedRefCS */,
					(119/*TypedRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLstdlib::LibClassCS-0(oclstdlibcs::LibClassCS): { isAbstract?="abstract"[?] "type" name=AnyName ownedSignature=TemplateSignatureCS[?] { ":" metaclassName=AnyName }[?] { "conformsTo" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] "{" { ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" }
		serializationRules[91] = createSerializationRule("LibClassCS-0", 45,
			createSerializationMatchSteps(
				62		/* assert |ClassCS::instanceClassName| == 0 */,
				88		/* assert |StructuredClassCS::isInterface| == 0 */,
				89		/* assert |StructuredClassCS::ownedMetaclass| == 0 */,
				212		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				205		/* check-rule basecs::ClassCS.ownedConstraints : InvCS */,
				226		/* check-rule basecs::StructuredClassCS.ownedOperations : LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS */,
				227		/* check-rule basecs::StructuredClassCS.ownedProperties : LibPropertyCS */,
				233		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				228		/* check-rule basecs::StructuredClassCS.ownedSuperTypes : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				202		/* assign V9 = |ModelElementCS::ownedAnnotations| */,
				199		/* assign V8 = |ClassCS::ownedConstraints| */,
				198		/* assign V7 = |StructuredClassCS::ownedProperties| */,
				196		/* assign V6 = |StructuredClassCS::ownedOperations| */,
				190		/* assign V5 = |JavaImplementationCS::implementation| */,
				172		/* assign V2 = |LibClassCS::metaclassName| */,
				160		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				132		/* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
				180		/* assign V3 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
				187		/* assign V4 = (|StructuredClassCS::ownedSuperTypes| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				171		/* V00*1-steps || value */,
				1		/* StructuredClassCS::isAbstract?='abstract' || soft-space value soft-space */,
				165		/* 'type' || soft-space value soft-space */,
				221		/* NamedElementCS::name=AnyName || soft-space value soft-space */,
				181		/* V01*1-steps || value */,
				85		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				194		/* V02*2-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				14		/* LibClassCS::metaclassName=AnyName || soft-space value soft-space */,
				201		/* V03*5-steps || value */,
				140		/* 'conformsTo' || soft-space value soft-space */,
				89		/* StructuredClassCS::ownedSuperTypes+=TypedRefCS || value */,
				204		/* V04*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				89		/* StructuredClassCS::ownedSuperTypes+=TypedRefCS || value */,
				207		/* V05*2-steps || value */,
				131		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				167		/* '{' || soft-space value push soft-new-line */,
				209		/* V06*1-steps || value */,
				63		/* StructuredClassCS::ownedOperations+=OperationCS || value */,
				211		/* V07*1-steps || value */,
				81		/* StructuredClassCS::ownedProperties+=LibPropertyCS || value */,
				213		/* V08*1-steps || value */,
				32		/* ClassCS::ownedConstraints+=InvCS || value */,
				215		/* V09*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				76		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				77		/* assert |OperationCS::ownedExceptions| == 0 */,
				78		/* assert |OperationCS::ownedParameters| == 0 */,
				79		/* assert |OperationCS::ownedPostconditions| == 0 */,
				80		/* assert |OperationCS::ownedPreconditions| == 0 */,
				90		/* assert |TemplateableElementCS::ownedSignature| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				118		/* assign V0 = |JavaImplementationCS::implementation| */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				139		/* 'coercion' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				120		/* '(' || no-space value no-space */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				174		/* V00*2-steps || value */,
				131		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				129		/* ';' || no-space value soft-new-line */
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
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				76		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				77		/* assert |OperationCS::ownedExceptions| == 0 */,
				78		/* assert |OperationCS::ownedParameters| == 0 */,
				90		/* assert |TemplateableElementCS::ownedSignature| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				212		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				216		/* check-rule basecs::OperationCS.ownedPostconditions : PreCS */,
				217		/* check-rule basecs::OperationCS.ownedPreconditions : PostCS */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				183		/* assign V3 = |OperationCS::ownedPostconditions| */,
				175		/* assign V2 = |OperationCS::ownedPreconditions| */,
				155		/* assign V1 = |ModelElementCS::ownedAnnotations| */,
				118		/* assign V0 = |JavaImplementationCS::implementation| */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				139		/* 'coercion' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				120		/* '(' || no-space value no-space */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				174		/* V00*2-steps || value */,
				131		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				167		/* '{' || soft-space value push soft-new-line */,
				182		/* V01*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				192		/* V02*1-steps || value */,
				235		/* OperationCS::ownedPreconditions+=PostCS || value */,
				198		/* V03*1-steps || value */,
				234		/* OperationCS::ownedPostconditions+=PreCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
		// OCLstdlib::LibIterationCS-0(oclstdlibcs::LibIterationCS): { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulator=AccumulatorCS }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating?="invalidating"[?] isValidating?="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[94] = createSerializationRule("LibIterationCS-0", 47,
			createSerializationMatchSteps(
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				76		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				77		/* assert |OperationCS::ownedExceptions| == 0 */,
				79		/* assert |OperationCS::ownedPostconditions| == 0 */,
				80		/* assert |OperationCS::ownedPreconditions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				293		/* check-rule oclstdlibcs::LibIterationCS.ownedAccumulator : AccumulatorCS */,
				294		/* check-rule oclstdlibcs::LibIterationCS.ownedIterators : IteratorCS */,
				214		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				233		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				197		/* assign V7 = |JavaImplementationCS::implementation| */,
				194		/* assign V6 = |LibIterationCS::isValidating.'validating'| */,
				191		/* assign V5 = |LibIterationCS::isInvalidating.'invalidating'| */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				173		/* assign V2 = |LibIterationCS::ownedAccumulator| */,
				145		/* assign V1 = (|LibIterationCS::ownedIterators| - 1) */,
				133		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				179		/* assign V3 = (|OperationCS::ownedParameters| > 0) */,
				186		/* assign V4 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				151		/* 'iteration' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				171		/* V00*1-steps || value */,
				85		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				120		/* '(' || no-space value no-space */,
				53		/* LibIterationCS::ownedIterators+=IteratorCS || value */,
				184		/* V01*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				53		/* LibIterationCS::ownedIterators+=IteratorCS || value */,
				194		/* V02*2-steps || value */,
				129		/* ';' || no-space value soft-new-line */,
				22		/* LibIterationCS::ownedAccumulator=AccumulatorCS || value */,
				201		/* V03*5-steps || value */,
				168		/* '|' || soft-space value soft-space */,
				66		/* OperationCS::ownedParameters+=ParameterCS || value */,
				204		/* V04*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				66		/* OperationCS::ownedParameters+=ParameterCS || value */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				205		/* V05*1-steps || value */,
				3		/* LibIterationCS::isInvalidating?='invalidating' || soft-space value soft-space */,
				208		/* V06*1-steps || value */,
				11		/* LibIterationCS::isValidating?='validating' || soft-space value soft-space */,
				212		/* V07*2-steps || value */,
				131		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				129		/* ';' || no-space value soft-new-line */
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
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 34/* ParameterCS */,
					(79/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::LibIterationCS-1(oclstdlibcs::LibIterationCS): { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulator=AccumulatorCS }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating?="invalidating"[?] isValidating?="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } }
		serializationRules[95] = createSerializationRule("LibIterationCS-1", 47,
			createSerializationMatchSteps(
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				76		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				77		/* assert |OperationCS::ownedExceptions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				293		/* check-rule oclstdlibcs::LibIterationCS.ownedAccumulator : AccumulatorCS */,
				212		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				294		/* check-rule oclstdlibcs::LibIterationCS.ownedIterators : IteratorCS */,
				214		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				216		/* check-rule basecs::OperationCS.ownedPostconditions : PreCS */,
				217		/* check-rule basecs::OperationCS.ownedPreconditions : PostCS */,
				233		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				164		/* assign V10 = |OperationCS::ownedPostconditions| */,
				203		/* assign V9 = |OperationCS::ownedPreconditions| */,
				200		/* assign V8 = |ModelElementCS::ownedAnnotations| */,
				197		/* assign V7 = |JavaImplementationCS::implementation| */,
				194		/* assign V6 = |LibIterationCS::isValidating.'validating'| */,
				191		/* assign V5 = |LibIterationCS::isInvalidating.'invalidating'| */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				173		/* assign V2 = |LibIterationCS::ownedAccumulator| */,
				145		/* assign V1 = (|LibIterationCS::ownedIterators| - 1) */,
				133		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				179		/* assign V3 = (|OperationCS::ownedParameters| > 0) */,
				186		/* assign V4 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				151		/* 'iteration' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				171		/* V00*1-steps || value */,
				85		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				120		/* '(' || no-space value no-space */,
				53		/* LibIterationCS::ownedIterators+=IteratorCS || value */,
				184		/* V01*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				53		/* LibIterationCS::ownedIterators+=IteratorCS || value */,
				194		/* V02*2-steps || value */,
				129		/* ';' || no-space value soft-new-line */,
				22		/* LibIterationCS::ownedAccumulator=AccumulatorCS || value */,
				201		/* V03*5-steps || value */,
				168		/* '|' || soft-space value soft-space */,
				66		/* OperationCS::ownedParameters+=ParameterCS || value */,
				204		/* V04*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				66		/* OperationCS::ownedParameters+=ParameterCS || value */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				205		/* V05*1-steps || value */,
				3		/* LibIterationCS::isInvalidating?='invalidating' || soft-space value soft-space */,
				208		/* V06*1-steps || value */,
				11		/* LibIterationCS::isValidating?='validating' || soft-space value soft-space */,
				212		/* V07*2-steps || value */,
				131		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				167		/* '{' || soft-space value push soft-new-line */,
				213		/* V08*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				215		/* V09*1-steps || value */,
				235		/* OperationCS::ownedPreconditions+=PostCS || value */,
				189		/* V10*1-steps || value */,
				234		/* OperationCS::ownedPostconditions+=PreCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				createSerializationReference(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 34/* ParameterCS */,
					(79/*ParameterCS*/ << 4) | 2 /*[*]*/
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
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				76		/* assert |OperationCS::ownedBodyExpressions| == 0 */,
				77		/* assert |OperationCS::ownedExceptions| == 0 */,
				79		/* assert |OperationCS::ownedPostconditions| == 0 */,
				80		/* assert |OperationCS::ownedPreconditions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				214		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				233		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				197		/* assign V7 = |JavaImplementationCS::implementation| */,
				195		/* assign V6 = |LibOperationCS::precedence| */,
				192		/* assign V5 = |LibOperationCS::isInvalidating.'invalidating'| */,
				188		/* assign V4 = |LibOperationCS::isValidating.'validating'| */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				160		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				120		/* assign V0 = |LibOperationCS::isStatic.'static'| */,
				168		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				178		/* assign V3 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				171		/* V00*1-steps || value */,
				8		/* LibOperationCS::isStatic?='static' || soft-space value soft-space */,
				156		/* 'operation' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				181		/* V01*1-steps || value */,
				85		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				120		/* '(' || no-space value no-space */,
				196		/* V02*4-steps || value */,
				66		/* OperationCS::ownedParameters+=ParameterCS || value */,
				200		/* V03*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				66		/* OperationCS::ownedParameters+=ParameterCS || value */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				202		/* V04*1-steps || value */,
				12		/* LibOperationCS::isValidating?='validating' || soft-space value soft-space */,
				205		/* V05*1-steps || value */,
				4		/* LibOperationCS::isInvalidating?='invalidating' || soft-space value soft-space */,
				210		/* V06*3-steps || value */,
				160		/* 'precedence' || soft-space value soft-space */,
				130		/* '=' || soft-space value soft-space */,
				105		/* LibOperationCS::precedence=Name || soft-space value soft-space */,
				212		/* V07*2-steps || value */,
				131		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				129		/* ';' || no-space value soft-new-line */
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
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				77		/* assert |OperationCS::ownedExceptions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				212		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				213		/* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
				214		/* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
				215		/* check-rule basecs::OperationCS.ownedPostconditions : PostCS */,
				218		/* check-rule basecs::OperationCS.ownedPreconditions : PreCS */,
				233		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				165		/* assign V11 = |OperationCS::ownedPreconditions| */,
				164		/* assign V10 = |OperationCS::ownedPostconditions| */,
				202		/* assign V9 = |ModelElementCS::ownedAnnotations| */,
				201		/* assign V8 = |OperationCS::ownedBodyExpressions| */,
				197		/* assign V7 = |JavaImplementationCS::implementation| */,
				195		/* assign V6 = |LibOperationCS::precedence| */,
				192		/* assign V5 = |LibOperationCS::isInvalidating.'invalidating'| */,
				188		/* assign V4 = |LibOperationCS::isValidating.'validating'| */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				160		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				120		/* assign V0 = |LibOperationCS::isStatic.'static'| */,
				168		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				178		/* assign V3 = (|OperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				171		/* V00*1-steps || value */,
				8		/* LibOperationCS::isStatic?='static' || soft-space value soft-space */,
				156		/* 'operation' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				181		/* V01*1-steps || value */,
				85		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				120		/* '(' || no-space value no-space */,
				196		/* V02*4-steps || value */,
				66		/* OperationCS::ownedParameters+=ParameterCS || value */,
				200		/* V03*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				66		/* OperationCS::ownedParameters+=ParameterCS || value */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				202		/* V04*1-steps || value */,
				12		/* LibOperationCS::isValidating?='validating' || soft-space value soft-space */,
				205		/* V05*1-steps || value */,
				4		/* LibOperationCS::isInvalidating?='invalidating' || soft-space value soft-space */,
				210		/* V06*3-steps || value */,
				160		/* 'precedence' || soft-space value soft-space */,
				130		/* '=' || soft-space value soft-space */,
				105		/* LibOperationCS::precedence=Name || soft-space value soft-space */,
				212		/* V07*2-steps || value */,
				131		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				167		/* '{' || soft-space value push soft-new-line */,
				214		/* V08*4-steps || value */,
				138		/* 'body' || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				26		/* OperationCS::ownedBodyExpressions+=SpecificationCS || value */,
				129		/* ';' || no-space value soft-new-line */,
				215		/* V09*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				189		/* V10*1-steps || value */,
				233		/* OperationCS::ownedPostconditions+=PostCS || value */,
				190		/* V11*1-steps || value */,
				236		/* OperationCS::ownedPreconditions+=PreCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				157		/* 'opposite' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
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
				212		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				219		/* check-rule basecs::PackageCS.ownedClasses : ClassCS|LibClassCS */,
				221		/* check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS */,
				295		/* check-rule oclstdlibcs::LibPackageCS.ownedPrecedences : PrecedenceCS */,
				193		/* assign V5 = |ModelElementCS::ownedAnnotations| */,
				189		/* assign V4 = |PackageCS::ownedClasses| */,
				184		/* assign V3 = |PackageOwnerCS::ownedPackages| */,
				128		/* assign V0 = |PackageCS::nsURI| */,
				44		/* assert (|PackageCS::nsPrefix| - V0) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				146		/* assign V1 = (|LibPackageCS::ownedPrecedences| > 0) */,
				174		/* assign V2 = |LibPackageCS::ownedPrecedences| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				154		/* 'library' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				177		/* V00*4-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				20		/* PackageCS::nsPrefix=Identifier || soft-space value soft-space */,
				130		/* '=' || soft-space value soft-space */,
				21		/* PackageCS::nsURI=URI || soft-space value soft-space */,
				167		/* '{' || soft-space value push soft-new-line */,
				187		/* V01*4-steps || value */,
				160		/* 'precedence' || soft-space value soft-space */,
				193		/* V02*1-steps || value */,
				80		/* LibPackageCS::ownedPrecedences+=PrecedenceCS || value */,
				129		/* ';' || no-space value soft-new-line */,
				198		/* V03*1-steps || value */,
				228		/* PackageOwnerCS::ownedPackages+=PackageCS || half-new-line value half-new-line */,
				203		/* V04*1-steps || value */,
				27		/* PackageCS::ownedClasses+=ClassCS || half-new-line value half-new-line */,
				206		/* V05*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				45		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				240		/* PathElementCS::referredElement=Name || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// OCLstdlib::LibPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=LibPathElementCS { "::" ownedPathElements+=LibPathElementCS }[*] }
		serializationRules[101] = createSerializationRule("LibPathNameCS-0", 52,
			createSerializationMatchSteps(
				223		/* check-rule basecs::PathNameCS.ownedPathElements : LibPathElementCS */,
				104		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			),
			createSerializationSteps(
				231		/* PathNameCS::ownedPathElements+=LibPathElementCS || value */,
				175		/* V00*2-steps || value */,
				128		/* '::' || no-space value no-space */,
				231		/* PathNameCS::ownedPathElements+=LibPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 20/* LibPathElementCS */,
					(51/*LibPathElementCS*/ << 4) | 3 /*[+]*/
				)
			});
		// OCLstdlib::LibPropertyCS-0(oclstdlibcs::LibPropertyCS): { isStatic?="static"[?] "property" name=Name ":" ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[102] = createSerializationRule("LibPropertyCS-0", 53,
			createSerializationMatchSteps(
				86		/* assert |StructuralFeatureCS::default| == 0 */,
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				87		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				296		/* check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : LibOppositeCS */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				171		/* assign V2 = |JavaImplementationCS::implementation| */,
				154		/* assign V1 = |LibPropertyCS::ownedOpposite| */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |LibPropertyCS::isStatic.'static'| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				171		/* V00*1-steps || value */,
				9		/* LibPropertyCS::isStatic?='static' || soft-space value soft-space */,
				161		/* 'property' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				181		/* V01*1-steps || value */,
				64		/* LibPropertyCS::ownedOpposite=LibOppositeCS || value */,
				194		/* V02*2-steps || value */,
				131		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				129		/* ';' || no-space value soft-new-line */
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
				86		/* assert |StructuralFeatureCS::default| == 0 */,
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				87		/* assert |StructuralFeatureCS::ownedDefaultExpressions| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				212		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				296		/* check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : LibOppositeCS */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				182		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				171		/* assign V2 = |JavaImplementationCS::implementation| */,
				154		/* assign V1 = |LibPropertyCS::ownedOpposite| */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				121		/* assign V0 = |LibPropertyCS::isStatic.'static'| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				171		/* V00*1-steps || value */,
				9		/* LibPropertyCS::isStatic?='static' || soft-space value soft-space */,
				161		/* 'property' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */,
				181		/* V01*1-steps || value */,
				64		/* LibPropertyCS::ownedOpposite=LibOppositeCS || value */,
				194		/* V02*2-steps || value */,
				131		/* '=>' || soft-space value soft-space */,
				0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
				167		/* '{' || soft-space value push soft-new-line */,
				198		/* V03*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				225		/* check-rule basecs::RootCS.ownedImports : ImportCS */,
				220		/* check-rule basecs::PackageOwnerCS.ownedPackages : LibPackageCS */,
				158		/* assign V1 = |PackageOwnerCS::ownedPackages| */,
				130		/* assign V0 = |RootCS::ownedImports| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				175		/* V00*2-steps || value */,
				47		/* RootCS::ownedImports+=ImportCS || value half-new-line */,
				129		/* ';' || no-space value soft-new-line */,
				182		/* V01*1-steps || value */,
				227		/* PackageOwnerCS::ownedPackages+=LibPackageCS || half-new-line value half-new-line */
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
				212		/* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationCS|AnnotationElementCS|DocumentationCS */,
				219		/* check-rule basecs::PackageCS.ownedClasses : ClassCS|LibClassCS */,
				221		/* check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS */,
				182		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
				176		/* assign V2 = |PackageCS::ownedClasses| */,
				158		/* assign V1 = |PackageOwnerCS::ownedPackages| */,
				128		/* assign V0 = |PackageCS::nsURI| */,
				44		/* assert (|PackageCS::nsPrefix| - V0) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				158		/* 'package' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */,
				177		/* V00*4-steps || value */,
				127		/* ':' || soft-space value soft-space */,
				20		/* PackageCS::nsPrefix=Identifier || soft-space value soft-space */,
				130		/* '=' || soft-space value soft-space */,
				21		/* PackageCS::nsURI=URI || soft-space value soft-space */,
				167		/* '{' || soft-space value push soft-new-line */,
				182		/* V01*1-steps || value */,
				228		/* PackageOwnerCS::ownedPackages+=PackageCS || half-new-line value half-new-line */,
				192		/* V02*1-steps || value */,
				27		/* PackageCS::ownedClasses+=ClassCS || half-new-line value half-new-line */,
				198		/* V03*1-steps || value */,
				24		/* ModelElementCS::ownedAnnotations+=AnnotationElementCS || value */,
				170		/* '}' || pop soft-new-line value soft-new-line */
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
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				219		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				206		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				207		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				124		/* assign V0 = |NamedElementCS::name| */,
				8		/* assert (|ConstraintCS::stereotype.'post'| - 1) == 0 */,
				152		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				245		/* ConstraintCS::stereotype='post' || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				218		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				185		/* V01*3-steps || value */,
				120		/* '(' || no-space value no-space */,
				58		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				86		/* ConstraintCS::ownedSpecification=SpecificationCS || value */,
				129		/* ';' || no-space value soft-new-line */
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
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				206		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				207		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				124		/* assign V0 = |NamedElementCS::name| */,
				9		/* assert (|ConstraintCS::stereotype.'pre'| - 1) == 0 */,
				152		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				246		/* ConstraintCS::stereotype='pre' || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				218		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				185		/* V01*3-steps || value */,
				120		/* '(' || no-space value no-space */,
				58		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				86		/* ConstraintCS::ownedSpecification=SpecificationCS || value */,
				129		/* ';' || no-space value soft-new-line */
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
				81		/* assert |PrecedenceCS::isRightAssociative| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				152		/* 'left' || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE)
			});
		// OCLstdlib::PrecedenceCS-1(oclstdlibcs::PrecedenceCS): { isRightAssociative?="right" ":" name=Name }
		serializationRules[110] = createSerializationRule("PrecedenceCS-1", 84,
			createSerializationMatchSteps(
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */,
				48		/* assert (|PrecedenceCS::isRightAssociative.'right'| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				7		/* PrecedenceCS::isRightAssociative?='right' || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				223		/* NamedElementCS::name=Name || soft-space value soft-space */
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
				85		/* assert |SpecificationCS::exprString| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				256		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				11		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				41		/* ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(27/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::TuplePartCS-0(basecs::TuplePartCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[112] = createSerializationRule("TuplePartCS-0", 108,
			createSerializationMatchSteps(
				92		/* assert |TypedElementCS::isOptional| == 0 */,
				68		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assert |TypedElementCS::qualifiers| == 0 */,
				236		/* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
				56		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				33		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				216		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				219		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				127		/* ':' || soft-space value soft-space */,
				99		/* TypedElementCS::ownedType=TypedMultiplicityRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS-0(basecs::LambdaTypeCS): { { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[113] = createSerializationRule("TypedMultiplicityRefCS-0", 118,
			createSerializationMatchSteps(
				209		/* check-rule basecs::LambdaTypeCS.ownedContextType : LambdaContextTypeRefCS */,
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				210		/* check-rule basecs::LambdaTypeCS.ownedParameterTypes : TypedMultiplicityRefCS */,
				211		/* check-rule basecs::LambdaTypeCS.ownedResultType : LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				233		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				185		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				22		/* assert (|LambdaTypeCS::ownedResultType| - 1) == 0 */,
				21		/* assert (|LambdaTypeCS::ownedContextType| - 1) == 0 */,
				133		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				20		/* assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0 */,
				144		/* assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0) */,
				167		/* assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1) */
			),
			createSerializationSteps(
				16		/* LambdaTypeCS::name='Lambda' || soft-space value soft-space */,
				171		/* V00*1-steps || value */,
				85		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				33		/* LambdaTypeCS::ownedContextType=LambdaContextTypeRefCS || value */,
				120		/* '(' || no-space value no-space */,
				186		/* V01*4-steps || value */,
				65		/* LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value */,
				195		/* V02*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				65		/* LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value */,
				121		/* ')' || no-space value */,
				127		/* ':' || soft-space value soft-space */,
				82		/* LambdaTypeCS::ownedResultType=TypedRefCS || value */,
				197		/* V03*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, false,
					(5/*'Lambda'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 15/* LambdaContextTypeRefCS */,
					(40/*LambdaContextTypeRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/* MultiplicityCS */,
					(62/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 64/* TypedMultiplicityRefCS */,
					(118/*TypedMultiplicityRefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 65/* TypedRefCS */,
					(119/*TypedRefCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 54/* TemplateSignatureCS */,
					(105/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS-1(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[114] = createSerializationRule("TypedMultiplicityRefCS-1", 118,
			createSerializationMatchSteps(
				272		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				273		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				162		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				122		/* assign V0 = |MapTypeCS::ownedValueType| */,
				28		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				27		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				17		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				178		/* V00*5-steps || value */,
				120		/* '(' || no-space value no-space */,
				55		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				125		/* ',' || no-space value soft-space */,
				102		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				121		/* ')' || no-space value */,
				181		/* V01*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				234		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				185		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				53		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				109		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				148		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				169		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				19		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				180		/* V00*7-steps || value */,
				120		/* '(' || no-space value no-space */,
				186		/* V01*4-steps || value */,
				73		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				195		/* V02*2-steps || value */,
				125		/* ',' || no-space value soft-space */,
				73		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				121		/* ')' || no-space value */,
				197		/* V03*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				239		/* check-rule basecs::TypedTypeRefCS.ownedPathName : LibPathNameCS */,
				135		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				58		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */,
				57		/* assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0 */
			),
			createSerializationSteps(
				10		/* TypedTypeRefCS::isTypeof?='typeof' || soft-space value soft-space */,
				120		/* '(' || no-space value no-space */,
				77		/* TypedTypeRefCS::ownedPathName=LibPathNameCS || value */,
				121		/* ')' || no-space value */,
				171		/* V00*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				238		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				237		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				239		/* check-rule basecs::TypedTypeRefCS.ownedPathName : LibPathNameCS */,
				162		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				136		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				58		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				77		/* TypedTypeRefCS::ownedPathName=LibPathNameCS || value */,
				176		/* V00*3-steps || value */,
				120		/* '(' || no-space value no-space */,
				25		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				121		/* ')' || no-space value */,
				181		/* V01*1-steps || value */,
				60		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
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
				239		/* check-rule basecs::TypedTypeRefCS.ownedPathName : LibPathNameCS */,
				58		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */,
				57		/* assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0 */
			),
			createSerializationSteps(
				10		/* TypedTypeRefCS::isTypeof?='typeof' || soft-space value soft-space */,
				120		/* '(' || no-space value no-space */,
				77		/* TypedTypeRefCS::ownedPathName=LibPathNameCS || value */,
				121		/* ')' || no-space value */
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
				238		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				239		/* check-rule basecs::TypedTypeRefCS.ownedPathName : LibPathNameCS */,
				136		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				58		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				77		/* TypedTypeRefCS::ownedPathName=LibPathNameCS || value */,
				176		/* V00*3-steps || value */,
				120		/* '(' || no-space value no-space */,
				25		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				121		/* ')' || no-space value */
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
		// 33: LambdaTypeCS::ownedContextType=LambdaContextTypeRefCS || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 40 /*LambdaContextTypeRefCS*/, 0);
		// 34: AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// 35: TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[35] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// 36: AnnotationElementCS::ownedDetails+=DetailCS || value
		serializationSteps[36] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 16 /*DetailCS*/, 0);
		// 37: IfExpCS::ownedElseExpression=ExpCS || value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 27 /*ExpCS*/, 0);
		// 38: CollectionLiteralPartCS::ownedExpression=ExpCS || value
		serializationSteps[38] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 0);
		// 39: CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[39] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 81 /*PatternExpCS*/, 2);
		// 40: ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 2);
		// 41: ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 2);
		// 42: NestedExpCS::ownedExpression=ExpCS || value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 0);
		// 43: LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 27 /*ExpCS*/, 0);
		// 44: TypeParameterCS::ownedExtends+=TypedRefCS || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 119 /*TypedRefCS*/, 0);
		// 45: WildcardTypeRefCS::ownedExtends=TypedRefCS || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 119 /*TypedRefCS*/, 0);
		// 46: IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 20 /*ElseIfThenExpCS*/, 0);
		// 47: RootCS::ownedImports+=ImportCS || value half-new-line
		serializationSteps[47] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 33 /*ImportCS*/, 3);
		// 48: LetExpCS::ownedInExpression=ExpCS || value
		serializationSteps[48] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 27 /*ExpCS*/, 0);
		// 49: NavigatingArgCS::ownedInitExpression=ExpCS || value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 27 /*ExpCS*/, 0);
		// 50: ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value
		serializationSteps[50] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new int[] { 27/*ExpCS*/,81/*PatternExpCS*/}, 0);
		// 51: ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[51] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 102 /*StringLiteralExpCS*/, 2);
		// 52: VariableCS::ownedInitExpression=ExpCS || value
		serializationSteps[52] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 27 /*ExpCS*/, 0);
		// 53: LibIterationCS::ownedIterators+=IteratorCS || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, 37 /*IteratorCS*/, 0);
		// 54: MapLiteralPartCS::ownedKey=ExpCS || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 27 /*ExpCS*/, 0);
		// 55: MapTypeCS::ownedKeyType=TypeExpCS || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 110 /*TypeExpCS*/, 0);
		// 56: CollectionLiteralPartCS::ownedLastExpression=ExpCS || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 27 /*ExpCS*/, 0);
		// 57: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 86 /*PrefixedPrimaryExpCS*/, 0);
		// 58: ConstraintCS::ownedMessageSpecification=SpecificationCS || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 99 /*SpecificationCS*/, 0);
		// 59: TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 62 /*MultiplicityCS*/, 0);
		// 60: TypedRefCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 62 /*MultiplicityCS*/, 0);
		// 61: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 68 /*NavigatingArgExpCS*/, 0);
		// 62: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 68 /*NavigatingArgExpCS*/, 2);
		// 63: StructuredClassCS::ownedOperations+=OperationCS || value
		serializationSteps[63] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 77 /*OperationCS*/, 0);
		// 64: LibPropertyCS::ownedOpposite=LibOppositeCS || value
		serializationSteps[64] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, 49 /*LibOppositeCS*/, 0);
		// 65: LambdaTypeCS::ownedParameterTypes+=TypedMultiplicityRefCS || value
		serializationSteps[65] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 118 /*TypedMultiplicityRefCS*/, 0);
		// 66: OperationCS::ownedParameters+=ParameterCS || value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 79 /*ParameterCS*/, 0);
		// 67: TemplateSignatureCS::ownedParameters+=TypeParameterCS || value
		serializationSteps[67] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 116 /*TypeParameterCS*/, 0);
		// 68: CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value
		serializationSteps[68] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 10 /*CollectionLiteralPartCS*/, 0);
		// 69: CollectionPatternCS::ownedParts+=PatternExpCS || value
		serializationSteps[69] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 81 /*PatternExpCS*/, 0);
		// 70: CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value
		serializationSteps[70] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 97 /*ShadowPartCS*/, 0);
		// 71: MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value
		serializationSteps[71] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 58 /*MapLiteralPartCS*/, 0);
		// 72: TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value
		serializationSteps[72] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 107 /*TupleLiteralPartCS*/, 0);
		// 73: TupleTypeCS::ownedParts+=TuplePartCS || value
		serializationSteps[73] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 108 /*TuplePartCS*/, 0);
		// 74: AbstractNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[74] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 80 /*PathNameCS*/, 0);
		// 75: ImportCS::ownedPathName=URIPathNameCS || value
		serializationSteps[75] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 124 /*URIPathNameCS*/, 0);
		// 76: TypeNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[76] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 80 /*PathNameCS*/, 0);
		// 77: TypedTypeRefCS::ownedPathName=LibPathNameCS || value
		serializationSteps[77] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 52 /*LibPathNameCS*/, 0);
		// 78: TypeNameExpCS::ownedPatternGuard=ExpCS || value
		serializationSteps[78] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 27 /*ExpCS*/, 0);
		// 79: PatternExpCS::ownedPatternType=TypeExpCS || value
		serializationSteps[79] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 110 /*TypeExpCS*/, 0);
		// 80: LibPackageCS::ownedPrecedences+=PrecedenceCS || value
		serializationSteps[80] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES, 84 /*PrecedenceCS*/, 0);
		// 81: StructuredClassCS::ownedProperties+=LibPropertyCS || value
		serializationSteps[81] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 53 /*LibPropertyCS*/, 0);
		// 82: LambdaTypeCS::ownedResultType=TypedRefCS || value
		serializationSteps[82] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 119 /*TypedRefCS*/, 0);
		// 83: AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[83] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 92 /*RoundBracketedClauseCS*/, 0);
		// 84: LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[84] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 92 /*RoundBracketedClauseCS*/, 0);
		// 85: TemplateableElementCS::ownedSignature=TemplateSignatureCS || value
		serializationSteps[85] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 105 /*TemplateSignatureCS*/, 0);
		// 86: ConstraintCS::ownedSpecification=SpecificationCS || value
		serializationSteps[86] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 99 /*SpecificationCS*/, 0);
		// 87: AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value
		serializationSteps[87] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 100 /*SquareBracketedClauseCS*/, 0);
		// 88: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value
		serializationSteps[88] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 104 /*TemplateParameterSubstitutionCS*/, 0);
		// 89: StructuredClassCS::ownedSuperTypes+=TypedRefCS || value
		serializationSteps[89] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 119 /*TypedRefCS*/, 0);
		// 90: SquareBracketedClauseCS::ownedTerms+=ExpCS || value
		serializationSteps[90] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 27 /*ExpCS*/, 0);
		// 91: IfExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[91] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 27 /*ExpCS*/, 0);
		// 92: IfThenExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[92] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 27 /*ExpCS*/, 0);
		// 93: CollectionLiteralExpCS::ownedType=CollectionTypeCS || value
		serializationSteps[93] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 12 /*CollectionTypeCS*/, 0);
		// 94: CollectionPatternCS::ownedType=CollectionTypeCS || value
		serializationSteps[94] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 12 /*CollectionTypeCS*/, 0);
		// 95: CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value
		serializationSteps[95] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 111 /*TypeExpWithoutMultiplicityCS*/, 0);
		// 96: MapLiteralExpCS::ownedType=MapTypeCS || value
		serializationSteps[96] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 59 /*MapTypeCS*/, 0);
		// 97: NavigatingArgCS::ownedType=TypeExpCS || value
		serializationSteps[97] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 110 /*TypeExpCS*/, 0);
		// 98: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[98] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 114 /*TypeLiteralWithMultiplicityCS*/, 2);
		// 99: TypedElementCS::ownedType=TypedMultiplicityRefCS || value
		serializationSteps[99] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 118 /*TypedMultiplicityRefCS*/, 0);
		// 100: VariableCS::ownedType=TypeExpCS || value
		serializationSteps[100] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 110 /*TypeExpCS*/, 0);
		// 101: MapLiteralPartCS::ownedValue=ExpCS || value
		serializationSteps[101] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 27 /*ExpCS*/, 0);
		// 102: MapTypeCS::ownedValueType=TypeExpCS || value
		serializationSteps[102] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 110 /*TypeExpCS*/, 0);
		// 103: LetExpCS::ownedVariables+=LetVariableCS || value
		serializationSteps[103] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 44 /*LetVariableCS*/, 0);
		// 104: PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space
		serializationSteps[104] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 129 /*UnrestrictedName*/, 10);
		// 105: LibOperationCS::precedence=Name || soft-space value soft-space
		serializationSteps[105] = createSerializationStepCrossReference(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, getCrossReference(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, "Name"), 65, 10);
		// 106: NavigatingArgCS::prefix='|' || soft-space value soft-space
		serializationSteps[106] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 18 /* '|' */, 10);
		// 107: NavigatingArgCS::prefix=';' || no-space value soft-new-line
		serializationSteps[107] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 3 /* ';' */, 7);
		// 108: NavigatingArgCS::prefix=',' || no-space value soft-space
		serializationSteps[108] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */, 8);
		// 109: ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[109] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 129, 10);
		// 110: CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space
		serializationSteps[110] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 31 /*Identifier*/, 10);
		// 111: StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[111] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 101 /*StringLiteral*/, 2);
		// 112: MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[112] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 10);
		// 113: BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[113] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 9 /* 'false|true' */, 2);
		// 114: NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[114] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 64 /*NUMBER_LITERAL*/, 2);
		// 115: MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space
		serializationSteps[115] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 121 /*UPPER*/, 10);
		// 116: DocumentationCS::value=SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[116] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, 94 /*SINGLE_QUOTED_STRING*/, 10);
		// 117: DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[117] = createSerializationStepAssigns(BaseCSPackage.Literals.DETAIL_CS__VALUES, -1, new int[] { 94/*SINGLE_QUOTED_STRING*/,56/*ML_SINGLE_QUOTED_STRING*/}, 10);
		// 118: '&&' || soft-space value soft-space
		serializationSteps[118] = createSerializationStepKeyword("&&", 10);
		// 119: '(' || value no-space
		serializationSteps[119] = createSerializationStepKeyword("(", 4);
		// 120: '(' || no-space value no-space
		serializationSteps[120] = createSerializationStepKeyword("(", 6);
		// 121: ')' || no-space value
		serializationSteps[121] = createSerializationStepKeyword(")", 1);
		// 122: '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[122] = createSerializationStepKeyword("*", 2);
		// 123: '++' || soft-space value soft-space
		serializationSteps[123] = createSerializationStepKeyword("++", 10);
		// 124: ',' || no-space value soft-new-line
		serializationSteps[124] = createSerializationStepKeyword(",", 7);
		// 125: ',' || no-space value soft-space
		serializationSteps[125] = createSerializationStepKeyword(",", 8);
		// 126: '..' || no-space value no-space
		serializationSteps[126] = createSerializationStepKeyword("..", 6);
		// 127: ':' || soft-space value soft-space
		serializationSteps[127] = createSerializationStepKeyword(":", 10);
		// 128: '::' || no-space value no-space
		serializationSteps[128] = createSerializationStepKeyword("::", 6);
		// 129: ';' || no-space value soft-new-line
		serializationSteps[129] = createSerializationStepKeyword(";", 7);
		// 130: '=' || soft-space value soft-space
		serializationSteps[130] = createSerializationStepKeyword("=", 10);
		// 131: '=>' || soft-space value soft-space
		serializationSteps[131] = createSerializationStepKeyword("=>", 10);
		// 132: '?' || soft-space value soft-space
		serializationSteps[132] = createSerializationStepKeyword("?", 10);
		// 133: 'Lambda' || soft-space value soft-space
		serializationSteps[133] = createSerializationStepKeyword("Lambda", 10);
		// 134: 'Tuple' || soft-space value soft-space
		serializationSteps[134] = createSerializationStepKeyword("Tuple", 10);
		// 135: '[' || no-space value no-space
		serializationSteps[135] = createSerializationStepKeyword("[", 6);
		// 136: ']' || no-space value
		serializationSteps[136] = createSerializationStepKeyword("]", 1);
		// 137: 'annotation' || soft-space value soft-space
		serializationSteps[137] = createSerializationStepKeyword("annotation", 10);
		// 138: 'body' || soft-space value soft-space
		serializationSteps[138] = createSerializationStepKeyword("body", 10);
		// 139: 'coercion' || soft-space value soft-space
		serializationSteps[139] = createSerializationStepKeyword("coercion", 10);
		// 140: 'conformsTo' || soft-space value soft-space
		serializationSteps[140] = createSerializationStepKeyword("conformsTo", 10);
		// 141: 'documentation' || soft-space value soft-space
		serializationSteps[141] = createSerializationStepKeyword("documentation", 10);
		// 142: 'else' || soft-new-line pop value push soft-space
		serializationSteps[142] = createSerializationStepKeyword("else", 18);
		// 143: 'elseif' || soft-new-line pop soft-space value push soft-space
		serializationSteps[143] = createSerializationStepKeyword("elseif", 19);
		// 144: 'endif' || soft-new-line pop value soft-space
		serializationSteps[144] = createSerializationStepKeyword("endif", 13);
		// 145: 'extends' || soft-space value soft-space
		serializationSteps[145] = createSerializationStepKeyword("extends", 10);
		// 146: 'if' || soft-new-line value push soft-space
		serializationSteps[146] = createSerializationStepKeyword("if", 14);
		// 147: 'import' || soft-space value soft-space
		serializationSteps[147] = createSerializationStepKeyword("import", 10);
		// 148: 'in' || soft-space value soft-space
		serializationSteps[148] = createSerializationStepKeyword("in", 10);
		// 149: 'in' || soft-space pop value soft-new-line
		serializationSteps[149] = createSerializationStepKeyword("in", 15);
		// 150: 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[150] = createSerializationStepKeyword("invalid", 2);
		// 151: 'iteration' || soft-space value soft-space
		serializationSteps[151] = createSerializationStepKeyword("iteration", 10);
		// 152: 'left' || soft-space value soft-space
		serializationSteps[152] = createSerializationStepKeyword("left", 10);
		// 153: 'let' || soft-space value push
		serializationSteps[153] = createSerializationStepKeyword("let", 9);
		// 154: 'library' || soft-space value soft-space
		serializationSteps[154] = createSerializationStepKeyword("library", 10);
		// 155: 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[155] = createSerializationStepKeyword("null", 2);
		// 156: 'operation' || soft-space value soft-space
		serializationSteps[156] = createSerializationStepKeyword("operation", 10);
		// 157: 'opposite' || soft-space value soft-space
		serializationSteps[157] = createSerializationStepKeyword("opposite", 10);
		// 158: 'package' || soft-space value soft-space
		serializationSteps[158] = createSerializationStepKeyword("package", 10);
		// 159: 'pre' || soft-space value soft-space
		serializationSteps[159] = createSerializationStepKeyword("pre", 10);
		// 160: 'precedence' || soft-space value soft-space
		serializationSteps[160] = createSerializationStepKeyword("precedence", 10);
		// 161: 'property' || soft-space value soft-space
		serializationSteps[161] = createSerializationStepKeyword("property", 10);
		// 162: 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[162] = createSerializationStepKeyword("self", 2);
		// 163: 'then' || pop value push soft-space
		serializationSteps[163] = createSerializationStepKeyword("then", 12);
		// 164: 'then' || pop soft-space value push soft-space
		serializationSteps[164] = createSerializationStepKeyword("then", 17);
		// 165: 'type' || soft-space value soft-space
		serializationSteps[165] = createSerializationStepKeyword("type", 10);
		// 166: 'with' || value
		serializationSteps[166] = createSerializationStepKeyword("with", 0);
		// 167: '{' || soft-space value push soft-new-line
		serializationSteps[167] = createSerializationStepKeyword("{", 16);
		// 168: '|' || soft-space value soft-space
		serializationSteps[168] = createSerializationStepKeyword("|", 10);
		// 169: '|?' || no-space value no-space
		serializationSteps[169] = createSerializationStepKeyword("|?", 6);
		// 170: '}' || pop soft-new-line value soft-new-line
		serializationSteps[170] = createSerializationStepKeyword("}", 11);
		// 171: V00*1-steps || value
		serializationSteps[171] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 1, 0);
		// 172: V00*1-steps || value
		serializationSteps[172] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 1, 0);
		// 173: V00*1-steps || value
		serializationSteps[173] = createSerializationStepSequence((0/*V0*/ << 4) | 3/*[+]*/, 1, 0);
		// 174: V00*2-steps || value
		serializationSteps[174] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 175: V00*2-steps || value
		serializationSteps[175] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 2, 0);
		// 176: V00*3-steps || value
		serializationSteps[176] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 3, 0);
		// 177: V00*4-steps || value
		serializationSteps[177] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 4, 0);
		// 178: V00*5-steps || value
		serializationSteps[178] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 5, 0);
		// 179: V00*6-steps || value
		serializationSteps[179] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 6, 0);
		// 180: V00*7-steps || value
		serializationSteps[180] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 7, 0);
		// 181: V01*1-steps || value
		serializationSteps[181] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 1, 0);
		// 182: V01*1-steps || value
		serializationSteps[182] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 1, 0);
		// 183: V01*2-steps || value
		serializationSteps[183] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 2, 0);
		// 184: V01*2-steps || value
		serializationSteps[184] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 2, 0);
		// 185: V01*3-steps || value
		serializationSteps[185] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 3, 0);
		// 186: V01*4-steps || value
		serializationSteps[186] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 4, 0);
		// 187: V01*4-steps || value
		serializationSteps[187] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 4, 0);
		// 188: V01*6-steps || value
		serializationSteps[188] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 6, 0);
		// 189: V10*1-steps || value
		serializationSteps[189] = createSerializationStepSequence((10/*V10*/ << 4) | 2/*[*]*/, 1, 0);
		// 190: V11*1-steps || value
		serializationSteps[190] = createSerializationStepSequence((11/*V11*/ << 4) | 2/*[*]*/, 1, 0);
		// 191: V02*1-steps || value
		serializationSteps[191] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 1, 0);
		// 192: V02*1-steps || value
		serializationSteps[192] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 1, 0);
		// 193: V02*1-steps || value
		serializationSteps[193] = createSerializationStepSequence((2/*V2*/ << 4) | 3/*[+]*/, 1, 0);
		// 194: V02*2-steps || value
		serializationSteps[194] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 2, 0);
		// 195: V02*2-steps || value
		serializationSteps[195] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 2, 0);
		// 196: V02*4-steps || value
		serializationSteps[196] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 4, 0);
		// 197: V03*1-steps || value
		serializationSteps[197] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 1, 0);
		// 198: V03*1-steps || value
		serializationSteps[198] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 1, 0);
		// 199: V03*2-steps || value
		serializationSteps[199] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 2, 0);
		// 200: V03*2-steps || value
		serializationSteps[200] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 2, 0);
		// 201: V03*5-steps || value
		serializationSteps[201] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 5, 0);
		// 202: V04*1-steps || value
		serializationSteps[202] = createSerializationStepSequence((4/*V4*/ << 4) | 1/*[?]*/, 1, 0);
		// 203: V04*1-steps || value
		serializationSteps[203] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 1, 0);
		// 204: V04*2-steps || value
		serializationSteps[204] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 2, 0);
		// 205: V05*1-steps || value
		serializationSteps[205] = createSerializationStepSequence((5/*V5*/ << 4) | 1/*[?]*/, 1, 0);
		// 206: V05*1-steps || value
		serializationSteps[206] = createSerializationStepSequence((5/*V5*/ << 4) | 2/*[*]*/, 1, 0);
		// 207: V05*2-steps || value
		serializationSteps[207] = createSerializationStepSequence((5/*V5*/ << 4) | 1/*[?]*/, 2, 0);
		// 208: V06*1-steps || value
		serializationSteps[208] = createSerializationStepSequence((6/*V6*/ << 4) | 1/*[?]*/, 1, 0);
		// 209: V06*1-steps || value
		serializationSteps[209] = createSerializationStepSequence((6/*V6*/ << 4) | 2/*[*]*/, 1, 0);
		// 210: V06*3-steps || value
		serializationSteps[210] = createSerializationStepSequence((6/*V6*/ << 4) | 1/*[?]*/, 3, 0);
		// 211: V07*1-steps || value
		serializationSteps[211] = createSerializationStepSequence((7/*V7*/ << 4) | 2/*[*]*/, 1, 0);
		// 212: V07*2-steps || value
		serializationSteps[212] = createSerializationStepSequence((7/*V7*/ << 4) | 1/*[?]*/, 2, 0);
		// 213: V08*1-steps || value
		serializationSteps[213] = createSerializationStepSequence((8/*V8*/ << 4) | 2/*[*]*/, 1, 0);
		// 214: V08*4-steps || value
		serializationSteps[214] = createSerializationStepSequence((8/*V8*/ << 4) | 2/*[*]*/, 4, 0);
		// 215: V09*1-steps || value
		serializationSteps[215] = createSerializationStepSequence((9/*V9*/ << 4) | 2/*[*]*/, 1, 0);
		// 216: wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[216] = createSerializationStepWrapper(2);
		// 217: NamedElementCS::name=UnaryOperatorName || soft-space value soft-space
		serializationSteps[217] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 125 /*UnaryOperatorName*/, 10);
		// 218: NamedElementCS::name=UnrestrictedName || soft-space value soft-space
		serializationSteps[218] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 129 /*UnrestrictedName*/, 10);
		// 219: NamedElementCS::name=Identifier || soft-space value soft-space
		serializationSteps[219] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 31 /*Identifier*/, 10);
		// 220: NamedElementCS::name=Identifier|SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[220] = createSerializationStepAssigns(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, -1, new int[] { 31/*Identifier*/,94/*SINGLE_QUOTED_STRING*/}, 10);
		// 221: NamedElementCS::name=AnyName || soft-space value soft-space
		serializationSteps[221] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 4 /*AnyName*/, 10);
		// 222: NamedElementCS::name=BinaryOperatorName || soft-space value soft-space
		serializationSteps[222] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 5 /*BinaryOperatorName*/, 10);
		// 223: NamedElementCS::name=Name || soft-space value soft-space
		serializationSteps[223] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 65 /*Name*/, 10);
		// 224: NamedElementCS::name=Name|SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[224] = createSerializationStepAssigns(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, -1, new int[] { 65/*Name*/,94/*SINGLE_QUOTED_STRING*/}, 10);
		// 225: RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value
		serializationSteps[225] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 67 /*NavigatingArgCS*/, 0);
		// 226: RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value
		serializationSteps[226] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new int[] { 70/*NavigatingCommaArgCS*/,71/*NavigatingSemiArgCS*/,69/*NavigatingBarArgCS*/}, 0);
		// 227: PackageOwnerCS::ownedPackages+=LibPackageCS || half-new-line value half-new-line
		serializationSteps[227] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 50 /*LibPackageCS*/, 5);
		// 228: PackageOwnerCS::ownedPackages+=PackageCS || half-new-line value half-new-line
		serializationSteps[228] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 78 /*PackageCS*/, 5);
		// 229: PathNameCS::ownedPathElements+=URIFirstPathElementCS || value
		serializationSteps[229] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 123 /*URIFirstPathElementCS*/, 0);
		// 230: PathNameCS::ownedPathElements+=FirstPathElementCS || value
		serializationSteps[230] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 28 /*FirstPathElementCS*/, 0);
		// 231: PathNameCS::ownedPathElements+=LibPathElementCS || value
		serializationSteps[231] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 51 /*LibPathElementCS*/, 0);
		// 232: PathNameCS::ownedPathElements+=NextPathElementCS || value
		serializationSteps[232] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 74 /*NextPathElementCS*/, 0);
		// 233: OperationCS::ownedPostconditions+=PostCS || value
		serializationSteps[233] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 82 /*PostCS*/, 0);
		// 234: OperationCS::ownedPostconditions+=PreCS || value
		serializationSteps[234] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 83 /*PreCS*/, 0);
		// 235: OperationCS::ownedPreconditions+=PostCS || value
		serializationSteps[235] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 82 /*PostCS*/, 0);
		// 236: OperationCS::ownedPreconditions+=PreCS || value
		serializationSteps[236] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 83 /*PreCS*/, 0);
		// 237: OperatorExpCS::ownedRight=ExpCS || value
		serializationSteps[237] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 27 /*ExpCS*/, 0);
		// 238: OperatorExpCS::ownedRight=PrefixedLetExpCS || value
		serializationSteps[238] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 85 /*PrefixedLetExpCS*/, 0);
		// 239: OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value
		serializationSteps[239] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 86 /*PrefixedPrimaryExpCS*/, 0);
		// 240: PathElementCS::referredElement=Name || soft-space value soft-space
		serializationSteps[240] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "Name"), 65, 10);
		// 241: PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[241] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 122, 10);
		// 242: PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[242] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 127, 10);
		// 243: PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[243] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 129, 10);
		// 244: ConstraintCS::stereotype='inv' || soft-space value soft-space
		serializationSteps[244] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 10 /* 'inv' */, 10);
		// 245: ConstraintCS::stereotype='post' || soft-space value soft-space
		serializationSteps[245] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 12 /* 'post' */, 10);
		// 246: ConstraintCS::stereotype='pre' || soft-space value soft-space
		serializationSteps[246] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 13 /* 'pre' */, 10);
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
