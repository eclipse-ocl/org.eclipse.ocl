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
package org.eclipse.ocl.xtext.essentialocl.serializer;

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
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;

/******* This file is 100% auto-generated - do not edit it *******/

/**
 * The EssentialOCLSerializationMetaData singleton provides the metadata to support a
 * model to text serialization of a parsed Xtext semantic model or to re-format an Xtext text node model.
 */
public class EssentialOCLSerializationMetaData extends AbstractSerializationMetaData
{
	/**
	 * The Provider supports injected creation of the EssentialOCLSerializationMetaData singleton.
	 */
	public static class Provider implements SerializationMetaData.Provider
	{
		private static @Nullable EssentialOCLSerializationMetaData INSTANCE = null;

		@Inject
		private GrammarProvider grammarProvider;

		@Override
		public synchronized @NonNull SerializationMetaData get() {
			// synchronized synchronizes the creation of this singleton.
			// It does not imply that the overall application is threadsafe.
			EssentialOCLSerializationMetaData instance = INSTANCE;
			if (instance == null) {
				assert grammarProvider != null;
				Grammar grammar = grammarProvider.getGrammar(Provider.class);
				assert grammar != null;
				INSTANCE = instance = new EssentialOCLSerializationMetaData(grammar);
			}
			return instance;
		}
	}

	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[49];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[9];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[100];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[49];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[182];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[159];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[83];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [18] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[148];
	private final @NonNull SubstringStep @NonNull [] substringSteps = new @NonNull SubstringStep[10];
	private final @Nullable String @Nullable [] multipleLineCommentMidfixes = new @Nullable String[] {" *"};
	private final @NonNull String @Nullable [] multipleLineCommentPrefixes = new @NonNull String[] {"/*"};
	private final @NonNull String @Nullable [] multipleLineCommentSuffixes = new @NonNull String[] {"*/"};
	private final @NonNull String @Nullable [] singleLineCommentPrefixes = new @NonNull String[] {"--"};

	private EssentialOCLSerializationMetaData(@NonNull Grammar grammar) {
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
		return 78;
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
		return 77;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 112;
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
		eClassValues[0] = new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
			createSerializationRules(
				18 /* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			), null
		);
		eClassValues[1] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
			createSerializationRules(
				20 /* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					1) /* CollectionLiteralPartCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					2) /* CollectionTypeCS */
			}
		);
		eClassValues[2] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
			createSerializationRules(
				22 /* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */,
				21 /* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					47) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[3] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
			createSerializationRules(
				23 /* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				71 /* TypeExpCS-4: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					17) /* PatternExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					2) /* CollectionTypeCS */
			}
		);
		eClassValues[4] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
			createSerializationRules(
				24 /* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				68 /* TypeExpCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				75 /* TypeLiteralWithMultiplicityCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[5] = new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
			createSerializationRules(
				36 /* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[6] = new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				25 /* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					23) /* ShadowPartCS */
			}
		);
		eClassValues[7] = new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
			createSerializationRules(
				28 /* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					47) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					4) /* ElseIfThenExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[8] = new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
			createSerializationRules(
				26 /* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[9] = new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
			createSerializationRules(
				27 /* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					43) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[10] = new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
			createSerializationRules(
				29 /* InvalidLiteralExpCS-0: 'invalid' */
			), null
		);
		eClassValues[11] = new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
			createSerializationRules(
				30 /* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[12] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
			createSerializationRules(
				31 /* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					7) /* LetVariableCS */
			}
		);
		eClassValues[13] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
			createSerializationRules(
				32 /* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					22) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					30) /* TypeExpCS */
			}
		);
		eClassValues[14] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
			createSerializationRules(
				33 /* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					8) /* MapLiteralPartCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					9) /* MapTypeCS */
			}
		);
		eClassValues[15] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
			createSerializationRules(
				34 /* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[16] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
			createSerializationRules(
				35 /* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				69 /* TypeExpCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				76 /* TypeLiteralWithMultiplicityCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					30) /* TypeExpCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					30) /* TypeExpCS */
			}
		);
		eClassValues[17] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */,
				4 /* MultiplicityCS-2: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] ']' */,
				2 /* MultiplicityCS-0: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] '|?' ']' */,
				3 /* MultiplicityCS-1: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] MultiplicityCS::isNullFree?='|1' ']' */
			), null
		);
		eClassValues[18] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				7 /* MultiplicityCS-5: '[' MultiplicityStringCS::stringBounds='*|+|?' ']' */,
				5 /* MultiplicityCS-3: '[' MultiplicityStringCS::stringBounds='*|+|?' '|?' ']' */,
				6 /* MultiplicityCS-4: '[' MultiplicityStringCS::stringBounds='*|+|?' MultiplicityCS::isNullFree?='|1' ']' */,
				8 /* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			), null
		);
		eClassValues[19] = new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
			createSerializationRules(
				37 /* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					3) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					16) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					22) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					24) /* SquareBracketedClauseCS */
			}
		);
		eClassValues[20] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
			createSerializationRules(
				42 /* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				38 /* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				41 /* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				39 /* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				40 /* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				43 /* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */,
				47 /* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				46 /* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				44 /* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				45 /* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				48 /* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					0) /* CoIteratorVariableCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					30) /* TypeExpCS */
			}
		);
		eClassValues[21] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				49 /* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[22] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				50 /* NullLiteralExpCS-0: 'null' */
			), null
		);
		eClassValues[23] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				51 /* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[24] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */,
				9 /* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */,
				80 /* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */
			), null
		);
		eClassValues[25] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				79 /* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */
			), null
		);
		eClassValues[26] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				16 /* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				60 /* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */,
				81 /* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					40) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
			}
		);
		eClassValues[27] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				52 /* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					30) /* TypeExpCS */
			}
		);
		eClassValues[28] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				53 /* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				54 /* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					44) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[29] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
			createSerializationRules(
				55 /* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				67 /* TypeExpCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				74 /* TypeLiteralWithMultiplicityCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */
			}
		);
		eClassValues[30] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				56 /* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					13) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[31] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				57 /* SelfExpCS-0: 'self' */
			), null
		);
		eClassValues[32] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				59 /* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */,
				58 /* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					47) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[33] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				61 /* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[34] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				62 /* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			), null
		);
		eClassValues[35] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					27) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[36] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					48) /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[37] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				13 /* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					35) /* TypeParameterCS */
			}
		);
		eClassValues[38] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				63 /* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					28) /* TupleLiteralPartCS */
			}
		);
		eClassValues[39] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
			createSerializationRules(
				64 /* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					30) /* TypeExpCS */
			}
		);
		eClassValues[40] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				65 /* TuplePartCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					30) /* TypeExpCS */
			}
		);
		eClassValues[41] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
			createSerializationRules(
				66 /* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				72 /* TypeExpCS-5: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
				77 /* TypeLiteralWithMultiplicityCS-3: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					29) /* TuplePartCS */
			}
		);
		eClassValues[42] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				73 /* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					33) /* TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[43] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
			createSerializationRules(
				70 /* TypeExpCS-3: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				78 /* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					3) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					16) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[44] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				14 /* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					38) /* TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[45] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				15 /* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					26) /* TemplateBindingCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					16) /* PathNameCS */
			}
		);
		eClassValues[46] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				82 /* UnlimitedNaturalLiteralExpCS-0: '*' */
			), null
		);
		eClassValues[47] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				19 /* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					30) /* TypeExpCS */
			}
		);
		eClassValues[48] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				17 /* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					38) /* TypedRefCS|TypedTypeRefCS */
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
		// 2: ';'
		enumerationValues[2] = new EnumerationValueSingle(";");
		// 3: '@'
		enumerationValues[3] = new EnumerationValueSingle("@");
		// 4: 'Map'
		enumerationValues[4] = new EnumerationValueSingle("Map");
		// 5: 'Tuple'
		enumerationValues[5] = new EnumerationValueSingle("Tuple");
		// 6: 'false|true'
		enumerationValues[6] = new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		// 7: '|'
		enumerationValues[7] = new EnumerationValueSingle("|");
		// 8: '|1'
		enumerationValues[8] = new EnumerationValueSingle("|1");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = createDataTypeRuleValue(1, "BinaryOperatorName", 8 /* [soft-space, value, soft-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '?->' : [no-space, value, no-space] */,
			3	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[2] = createParserRuleValue(2, "BooleanLiteralExpCS", -1,
			createSerializationRules(
				18	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* symbol="true" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* symbol="false" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "CoIteratorVariableCS", -1,
			createSerializationRules(
				19	/* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[4] = createParserRuleValue(4, "CollectionLiteralExpCS", -1,
			createSerializationRules(
				20	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 14	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 9	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[5] = createParserRuleValue(5, "CollectionLiteralPartCS", -1,
			createSerializationRules(
				21	/* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */,
				22	/* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedLastExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=PatternExpCS : [value] | [value] */
		);
		grammarRuleValues[6] = createParserRuleValue(6, "CollectionPatternCS", -1,
			createSerializationRules(
				23	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 14	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* "++" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* restVariableName=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 9	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "CollectionTypeCS", -1,
			createSerializationRules(
				24	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* name=CollectionTypeIdentifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedCollectionMultiplicity=MultiplicityCS? : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[8] = createDataTypeRuleValue(8, "CollectionTypeIdentifier", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[9] = createParserRuleValue(9, "CurlyBracketedClauseCS", -1,
			createSerializationRules(
				25	/* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {CurlyBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 14	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 9	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[10] = new TerminalRuleValue(10, "DOUBLE_QUOTED_STRING");
		grammarRuleValues[11] = new TerminalRuleValue(11, "ESCAPED_CHARACTER");
		grammarRuleValues[12] = new TerminalRuleValue(12, "ESCAPED_ID");
		grammarRuleValues[13] = createParserRuleValue(13, "ElseIfThenExpCS", -1,
			createSerializationRules(
				26	/* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 17	/* "elseif" : [value] | [soft-new-line, pop, soft-space, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=ExpCS : [value] | [value] */,
			(0 << 16) | 10	/* "then" : [value] | [pop, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[14] = createDataTypeRuleValue(14, "EssentialOCLInfixOperatorName", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[15] = createDataTypeRuleValue(15, "EssentialOCLNavigationOperatorName", 4 /* [no-space, value, no-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '?->' : [no-space, value, no-space] */,
			3	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[16] = createDataTypeRuleValue(16, "EssentialOCLReservedKeyword", 8 /* [soft-space, value, soft-space] */,
			4	/* 'else' : [soft-new-line, pop, value, push, soft-space] */,
			5	/* 'endif' : [soft-new-line, pop, value, soft-space] */,
			6	/* 'if' : [soft-new-line, value, push, soft-space] */,
			7	/* 'in' : [soft-space, pop, value, soft-new-line] */,
			8	/* 'let' : [soft-space, value, push] */,
			9	/* 'then' : [pop, soft-space, value, push, soft-space] */);
		grammarRuleValues[17] = createDataTypeRuleValue(17, "EssentialOCLUnaryOperatorName", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[18] = createDataTypeRuleValue(18, "EssentialOCLUnreservedName", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[19] = createDataTypeRuleValue(19, "EssentialOCLUnrestrictedName", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[20] = createParserRuleValue(20, "ExpCS", 45 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				18	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				20	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				27	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				28	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				29	/* InvalidLiteralExpCS-0: 'invalid' */,
				30	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				31	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				33	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				37	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				49	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				50	/* NullLiteralExpCS-0: 'null' */,
				51	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				53	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				54	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				57	/* SelfExpCS-0: 'self' */,
				62	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				63	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				73	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				82	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* {InfixExpCS} : [value] | [value] */,
			(0 << 16) | 8	/* name=BinaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedLetExpCS : [value] | [value] */
		);
		grammarRuleValues[21] = createParserRuleValue(21, "FirstPathElementCS", -1,
			createSerializationRules(
				0	/* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 8	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[22] = createDataTypeRuleValue(22, "ID", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[23] = new TerminalRuleValue(23, "INT");
		grammarRuleValues[24] = createDataTypeRuleValue(24, "Identifier", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[25] = createParserRuleValue(25, "IfExpCS", -1,
			createSerializationRules(
				28	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* "if" : [value] | [soft-new-line, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 15	/* "then" : [value] | [pop, soft-space, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedIfThenExpressions+=ElseIfThenExpCS* : [value] | [value] */,
			(0 << 16) | 16	/* "else" : [value] | [soft-new-line, pop, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedElseExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 11	/* "endif" : [value] | [soft-new-line, pop, value, soft-space] */
		);
		grammarRuleValues[26] = createDataTypeRuleValue(26, "InfixOperatorName", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[27] = createParserRuleValue(27, "InvalidLiteralExpCS", -1,
			createSerializationRules(
				29	/* InvalidLiteralExpCS-0: 'invalid' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {InvalidLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 8	/* "invalid" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[28] = new TerminalRuleValue(28, "LETTER_CHARACTER");
		grammarRuleValues[29] = createDataTypeRuleValue(29, "LOWER", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[30] = createParserRuleValue(30, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				30	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 14	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 9	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[31] = createParserRuleValue(31, "LetExpCS", -1,
			createSerializationRules(
				31	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "let" : [value] | [soft-space, value, push] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 13	/* "in" : [value] | [soft-space, pop, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedInExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[32] = createParserRuleValue(32, "LetVariableCS", -1,
			createSerializationRules(
				32	/* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 8	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[33] = new TerminalRuleValue(33, "ML_COMMENT");
		grammarRuleValues[34] = new TerminalRuleValue(34, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[35] = createParserRuleValue(35, "MapLiteralExpCS", -1,
			createSerializationRules(
				33	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=MapTypeCS : [value] | [value] */,
			(0 << 16) | 14	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 9	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[36] = createParserRuleValue(36, "MapLiteralPartCS", -1,
			createSerializationRules(
				34	/* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedKey=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 8	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValue=ExpCS : [value] | [value] */
		);
		grammarRuleValues[37] = createParserRuleValue(37, "MapTypeCS", -1,
			createSerializationRules(
				35	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* name="Map" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedKeyType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValueType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[38] = createParserRuleValue(38, "Model", -1,
			createSerializationRules(
				36	/* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[39] = createParserRuleValue(39, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 8	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[40] = createParserRuleValue(40, "MultiplicityCS", -1,
			createSerializationRules(
				2	/* MultiplicityCS-0: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] '|?' ']' */,
				3	/* MultiplicityCS-1: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] MultiplicityCS::isNullFree?='|1' ']' */,
				4	/* MultiplicityCS-2: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] ']' */,
				5	/* MultiplicityCS-3: '[' MultiplicityStringCS::stringBounds='*|+|?' '|?' ']' */,
				6	/* MultiplicityCS-4: '[' MultiplicityStringCS::stringBounds='*|+|?' MultiplicityCS::isNullFree?='|1' ']' */,
				7	/* MultiplicityCS-5: '[' MultiplicityStringCS::stringBounds='*|+|?' ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityBoundsCS : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityStringCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 4	/* "|?" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 4	/* isNullFree?="|1" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			),
			(0 << 16) | 8	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[42] = createDataTypeRuleValue(42, "NUMBER_LITERAL", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[43] = createParserRuleValue(43, "NameExpCS", -1,
			createSerializationRules(
				37	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedSquareBracketedClauses+=SquareBracketedClauseCS* : [value] | [value] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* isPre?="@" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "pre" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[44] = createParserRuleValue(44, "NavigatingArgCS", -1,
			createSerializationRules(
				38	/* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				39	/* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				40	/* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				41	/* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				42	/* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 8	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 8	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 8	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 8	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[45] = createParserRuleValue(45, "NavigatingArgExpCS", 46 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				18	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				20	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				27	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				28	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				29	/* InvalidLiteralExpCS-0: 'invalid' */,
				30	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				31	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				33	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				37	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				49	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				50	/* NullLiteralExpCS-0: 'null' */,
				51	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				53	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				54	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				57	/* SelfExpCS-0: 'self' */,
				62	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				63	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				73	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				82	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* ExpCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[46] = createParserRuleValue(46, "NavigatingBarArgCS", -1,
			createSerializationRules(
				43	/* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* prefix="|" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[47] = createParserRuleValue(47, "NavigatingCommaArgCS", -1,
			createSerializationRules(
				44	/* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				45	/* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				46	/* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				47	/* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* prefix="," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 8	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 8	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 8	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 8	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[48] = createParserRuleValue(48, "NavigatingSemiArgCS", -1,
			createSerializationRules(
				48	/* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 5	/* prefix=";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[49] = createDataTypeRuleValue(49, "NavigationOperatorName", 8 /* [soft-space, value, soft-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '?->' : [no-space, value, no-space] */,
			3	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[50] = createParserRuleValue(50, "NestedExpCS", -1,
			createSerializationRules(
				49	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 3	/* "(" : [value] | [value, no-space] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[51] = createParserRuleValue(51, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */
			),
			(0 << 16) | 8	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[52] = createParserRuleValue(52, "NullLiteralExpCS", -1,
			createSerializationRules(
				50	/* NullLiteralExpCS-0: 'null' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {NullLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 8	/* "null" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[53] = createParserRuleValue(53, "NumberLiteralExpCS", -1,
			createSerializationRules(
				51	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			),
			(0 << 16) | 2	/* symbol=NUMBER_LITERAL : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[54] = createParserRuleValue(54, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[55] = createParserRuleValue(55, "PatternExpCS", -1,
			createSerializationRules(
				52	/* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* patternVariableName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPatternType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[56] = createParserRuleValue(56, "PrefixedLetExpCS", 20 /* LetExpCS|PrefixedLetExpCS */,
			createSerializationRules(
				31	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				53	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 8	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedLetExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LetExpCS : [value] | [value] */
		);
		grammarRuleValues[57] = createParserRuleValue(57, "PrefixedPrimaryExpCS", 43 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				18	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				20	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				28	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				29	/* InvalidLiteralExpCS-0: 'invalid' */,
				30	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				33	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				37	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				49	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				50	/* NullLiteralExpCS-0: 'null' */,
				51	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				54	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				57	/* SelfExpCS-0: 'self' */,
				62	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				63	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				73	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				82	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 8	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrimaryExpCS : [value] | [value] */
		);
		grammarRuleValues[58] = createParserRuleValue(58, "PrimaryExpCS", 42 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				18	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				20	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				28	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				29	/* InvalidLiteralExpCS-0: 'invalid' */,
				30	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				33	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				37	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				49	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				50	/* NullLiteralExpCS-0: 'null' */,
				51	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				57	/* SelfExpCS-0: 'self' */,
				62	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				63	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				73	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				82	/* UnlimitedNaturalLiteralExpCS-0: '*' */
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
		grammarRuleValues[59] = createParserRuleValue(59, "PrimitiveLiteralExpCS", 41 /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				18	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				29	/* InvalidLiteralExpCS-0: 'invalid' */,
				50	/* NullLiteralExpCS-0: 'null' */,
				51	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				62	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				82	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* NumberLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* StringLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* BooleanLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* UnlimitedNaturalLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* InvalidLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* NullLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[60] = createParserRuleValue(60, "PrimitiveTypeCS", -1,
			createSerializationRules(
				55	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */
			),
			(0 << 16) | 8	/* name=PrimitiveTypeIdentifier : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[61] = createDataTypeRuleValue(61, "PrimitiveTypeIdentifier", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[62] = createParserRuleValue(62, "RoundBracketedClauseCS", -1,
			createSerializationRules(
				56	/* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {RoundBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=NavigatingArgCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)* : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[63] = new TerminalRuleValue(63, "SIMPLE_ID");
		grammarRuleValues[64] = new TerminalRuleValue(64, "SINGLE_QUOTED_STRING");
		grammarRuleValues[65] = new TerminalRuleValue(65, "SL_COMMENT");
		grammarRuleValues[66] = createParserRuleValue(66, "SelfExpCS", -1,
			createSerializationRules(
				57	/* SelfExpCS-0: 'self' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SelfExpCS} : [value] | [value] */,
			(0 << 16) | 8	/* "self" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[67] = createParserRuleValue(67, "ShadowPartCS", -1,
			createSerializationRules(
				58	/* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */,
				59	/* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* referredProperty=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 0	/* ownedInitExpression=StringLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[68] = createParserRuleValue(68, "SimplePathNameCS", -1,
			createSerializationRules(
				60	/* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */
			),
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */
		);
		grammarRuleValues[69] = createParserRuleValue(69, "SquareBracketedClauseCS", -1,
			createSerializationRules(
				61	/* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[70] = createDataTypeRuleValue(70, "StringLiteral", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[71] = createParserRuleValue(71, "StringLiteralExpCS", -1,
			createSerializationRules(
				62	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			),
			(0 << 16) | 2	/* segments+=StringLiteral+ : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[72] = createParserRuleValue(72, "TemplateBindingCS", -1,
			createSerializationRules(
				11	/* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[73] = createParserRuleValue(73, "TemplateParameterSubstitutionCS", -1,
			createSerializationRules(
				12	/* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[74] = createParserRuleValue(74, "TemplateSignatureCS", -1,
			createSerializationRules(
				13	/* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[75] = createParserRuleValue(75, "TupleLiteralExpCS", -1,
			createSerializationRules(
				63	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* "Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 14	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 9	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[76] = createParserRuleValue(76, "TupleLiteralPartCS", -1,
			createSerializationRules(
				64	/* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 8	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[77] = createParserRuleValue(77, "TuplePartCS", -1,
			createSerializationRules(
				65	/* TuplePartCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[78] = createParserRuleValue(78, "TupleTypeCS", -1,
			createSerializationRules(
				66	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* name="Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[79] = createParserRuleValue(79, "TypeExpCS", -1,
			createSerializationRules(
				67	/* TypeExpCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				68	/* TypeExpCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				69	/* TypeExpCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				70	/* TypeExpCS-3: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				71	/* TypeExpCS-4: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				72	/* TypeExpCS-5: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[80] = createParserRuleValue(80, "TypeExpWithoutMultiplicityCS", 34 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				23	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				24	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				35	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				55	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				66	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				78	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeNameExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionPatternCS : [value] | [value] */
		);
		grammarRuleValues[81] = createParserRuleValue(81, "TypeLiteralCS", 32 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */,
			createSerializationRules(
				24	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				35	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				55	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				66	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */
		);
		grammarRuleValues[82] = createParserRuleValue(82, "TypeLiteralExpCS", -1,
			createSerializationRules(
				73	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			(0 << 16) | 2	/* ownedType=TypeLiteralWithMultiplicityCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[83] = createParserRuleValue(83, "TypeLiteralWithMultiplicityCS", -1,
			createSerializationRules(
				74	/* TypeLiteralWithMultiplicityCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				75	/* TypeLiteralWithMultiplicityCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				76	/* TypeLiteralWithMultiplicityCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				77	/* TypeLiteralWithMultiplicityCS-3: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[84] = createParserRuleValue(84, "TypeNameExpCS", -1,
			createSerializationRules(
				78	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 14	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedPatternGuard=ExpCS : [value] | [value] */,
			(0 << 16) | 9	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[85] = createParserRuleValue(85, "TypeParameterCS", -1,
			createSerializationRules(
				14	/* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "&&" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[86] = createParserRuleValue(86, "TypeRefCS", 48 /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				15	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */,
				17	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[87] = createParserRuleValue(87, "TypedRefCS", 38 /* TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				15	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[88] = createParserRuleValue(88, "TypedTypeRefCS", -1,
			createSerializationRules(
				15	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[89] = createDataTypeRuleValue(89, "UPPER", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[90] = createDataTypeRuleValue(90, "URI", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[91] = createParserRuleValue(91, "URIFirstPathElementCS", -1,
			createSerializationRules(
				79	/* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */,
				80	/* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 8	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PathElementWithURICS} : [value] | [value] */,
			(0 << 16) | 8	/* referredElement=URI : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[92] = createParserRuleValue(92, "URIPathNameCS", -1,
			createSerializationRules(
				81	/* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=URIFirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[93] = createDataTypeRuleValue(93, "UnaryOperatorName", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[94] = createParserRuleValue(94, "UnlimitedNaturalLiteralExpCS", -1,
			createSerializationRules(
				82	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {UnlimitedNaturalLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 8	/* "*" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[95] = createDataTypeRuleValue(95, "UnreservedName", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[96] = createParserRuleValue(96, "UnreservedPathNameCS", -1,
			createSerializationRules(
				16	/* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[97] = createDataTypeRuleValue(97, "UnrestrictedName", 8 /* [soft-space, value, soft-space] */);
		grammarRuleValues[98] = new TerminalRuleValue(98, "WS");
		grammarRuleValues[99] = createParserRuleValue(99, "WildcardTypeRefCS", -1,
			createSerializationRules(
				17	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WildcardTypeRefCS} : [value] | [value] */,
			(0 << 16) | 8	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends=TypedRefCS : [value] | [value] */
		);
	}

	/**
	 * Initialize bit vectors of useful grammar rule combinations.
	 */
	private void initGrammarRuleVectors() {
		// 0: CoIteratorVariableCS
		grammarRuleVectors[0] = new GrammarRuleVector(0x8L);
		// 1: CollectionLiteralPartCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x20L);
		// 2: CollectionTypeCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x80L);
		// 3: CurlyBracketedClauseCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x200L);
		// 4: ElseIfThenExpCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x2000L);
		// 5: ExpCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x100000L);
		// 6: FirstPathElementCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x200000L);
		// 7: LetVariableCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x100000000L);
		// 8: MapLiteralPartCS
		grammarRuleVectors[8] = new GrammarRuleVector(0x1000000000L);
		// 9: MapTypeCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x2000000000L);
		// 10: MultiplicityCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x10000000000L);
		// 11: NavigatingArgExpCS
		grammarRuleVectors[11] = new GrammarRuleVector(0x200000000000L);
		// 12: NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[12] = new GrammarRuleVector(0x1c00000000000L);
		// 13: NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[13] = new GrammarRuleVector(0x1d00000000000L);
		// 14: NextPathElementCS
		grammarRuleVectors[14] = new GrammarRuleVector(0x8000000000000L);
		// 15: FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x8000000200000L);
		// 16: PathNameCS
		grammarRuleVectors[16] = new GrammarRuleVector(0x40000000000000L);
		// 17: PatternExpCS
		grammarRuleVectors[17] = new GrammarRuleVector(0x80000000000000L);
		// 18: ExpCS|PatternExpCS
		grammarRuleVectors[18] = new GrammarRuleVector(0x80000000100000L);
		// 19: PrefixedLetExpCS
		grammarRuleVectors[19] = new GrammarRuleVector(0x100000000000000L);
		// 20: LetExpCS|PrefixedLetExpCS
		grammarRuleVectors[20] = new GrammarRuleVector(0x100000080000000L);
		// 21: PrefixedPrimaryExpCS
		grammarRuleVectors[21] = new GrammarRuleVector(0x200000000000000L);
		// 22: RoundBracketedClauseCS
		grammarRuleVectors[22] = new GrammarRuleVector(0x4000000000000000L);
		// 23: ShadowPartCS
		grammarRuleVectors[23] = new GrammarRuleVector(0x0L,0x8L);
		// 24: SquareBracketedClauseCS
		grammarRuleVectors[24] = new GrammarRuleVector(0x0L,0x20L);
		// 25: StringLiteralExpCS
		grammarRuleVectors[25] = new GrammarRuleVector(0x0L,0x80L);
		// 26: TemplateBindingCS
		grammarRuleVectors[26] = new GrammarRuleVector(0x0L,0x100L);
		// 27: TemplateParameterSubstitutionCS
		grammarRuleVectors[27] = new GrammarRuleVector(0x0L,0x200L);
		// 28: TupleLiteralPartCS
		grammarRuleVectors[28] = new GrammarRuleVector(0x0L,0x1000L);
		// 29: TuplePartCS
		grammarRuleVectors[29] = new GrammarRuleVector(0x0L,0x2000L);
		// 30: TypeExpCS
		grammarRuleVectors[30] = new GrammarRuleVector(0x0L,0x8000L);
		// 31: TypeExpWithoutMultiplicityCS
		grammarRuleVectors[31] = new GrammarRuleVector(0x0L,0x10000L);
		// 32: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
		grammarRuleVectors[32] = new GrammarRuleVector(0x1000002000000080L,0x24000L);
		// 33: TypeLiteralWithMultiplicityCS
		grammarRuleVectors[33] = new GrammarRuleVector(0x0L,0x80000L);
		// 34: CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[34] = new GrammarRuleVector(0x10000020000000c0L,0x134000L);
		// 35: TypeParameterCS
		grammarRuleVectors[35] = new GrammarRuleVector(0x0L,0x200000L);
		// 36: TypeRefCS
		grammarRuleVectors[36] = new GrammarRuleVector(0x0L,0x400000L);
		// 37: TypedRefCS
		grammarRuleVectors[37] = new GrammarRuleVector(0x0L,0x800000L);
		// 38: TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[38] = new GrammarRuleVector(0x0L,0x1800000L);
		// 39: NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[39] = new GrammarRuleVector(0x8000000000000L,0x8000000L);
		// 40: FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[40] = new GrammarRuleVector(0x8000000200000L,0x8000000L);
		// 41: BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[41] = new GrammarRuleVector(0x830000008000004L,0x40000080L);
		// 42: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[42] = new GrammarRuleVector(0xc3408084a000014L,0x40040884L);
		// 43: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[43] = new GrammarRuleVector(0xe3408084a000014L,0x40040884L);
		// 44: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[44] = new GrammarRuleVector(0xf340808ca000014L,0x40040884L);
		// 45: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[45] = new GrammarRuleVector(0xf340808ca100014L,0x40040884L);
		// 46: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[46] = new GrammarRuleVector(0xf342808ca100014L,0x40040884L);
		// 47: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[47] = new GrammarRuleVector(0xfb40808ca100014L,0x40040884L);
		// 48: TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[48] = new GrammarRuleVector(0x0L,0x801c00000L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// 0: assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(96);
		// 1: assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(97);
		// 2: assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(100);
		// 3: assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(101);
		// 4: assert (|CollectionPatternCS::ownedType| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(103);
		// 5: assert (|CollectionTypeCS::name| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(104);
		// 6: assert (|ContextCS::ownedExpression| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(105);
		// 7: assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(108);
		// 8: assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(109);
		// 9: assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(110);
		// 10: assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(111);
		// 11: assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(112);
		// 12: assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(113);
		// 13: assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(114);
		// 14: assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(115);
		// 15: assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(119);
		// 16: assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(120);
		// 17: assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(121);
		// 18: assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(122);
		// 19: assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(123);
		// 20: assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(124);
		// 21: assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(125);
		// 22: assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(126);
		// 23: assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(127);
		// 24: assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(128);
		// 25: assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(129);
		// 26: assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(130);
		// 27: assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(131);
		// 28: assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(132);
		// 29: assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(133);
		// 30: assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(134);
		// 31: assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(135);
		// 32: assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(136);
		// 33: assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(137);
		// 34: assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(138);
		// 35: assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(139);
		// 36: assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(140);
		// 37: assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(141);
		// 38: assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(142);
		// 39: assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(143);
		// 40: assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(146);
		// 41: assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(149);
		// 42: assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(152);
		// 43: assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(153);
		// 44: assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(156);
		// 45: assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(157);
		// 46: assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[46] = createMatchStep_Assert(158);
		// 47: assert |CollectionLiteralPartCS::ownedLastExpression| == 0
		serializationMatchSteps[47] = createMatchStep_Assert(12);
		// 48: assert |CollectionPatternCS::ownedPatternGuard| == 0
		serializationMatchSteps[48] = createMatchStep_Assert(14);
		// 49: assert |CurlyBracketedClauseCS::value| == 0
		serializationMatchSteps[49] = createMatchStep_Assert(22);
		// 50: assert |IfExpCS::isImplicit| == 0
		serializationMatchSteps[50] = createMatchStep_Assert(23);
		// 51: assert |LetExpCS::isImplicit| == 0
		serializationMatchSteps[51] = createMatchStep_Assert(32);
		// 52: assert |ModelElementCS::ownedAnnotations| == 0
		serializationMatchSteps[52] = createMatchStep_Assert(43);
		// 53: assert |MultiplicityCS::isNullFree| == 0
		serializationMatchSteps[53] = createMatchStep_Assert(47);
		// 54: assert |NamedElementCS::name| == 0
		serializationMatchSteps[54] = createMatchStep_Assert(49);
		// 55: assert |NavigatingArgCS::ownedCoIterator| == 0
		serializationMatchSteps[55] = createMatchStep_Assert(50);
		// 56: assert |NavigatingArgCS::ownedInitExpression| == 0
		serializationMatchSteps[56] = createMatchStep_Assert(51);
		// 57: assert |NavigatingArgCS::ownedNameExpression| == 0
		serializationMatchSteps[57] = createMatchStep_Assert(52);
		// 58: assert |NavigatingArgCS::ownedType| == 0
		serializationMatchSteps[58] = createMatchStep_Assert(53);
		// 59: assert |NavigatingArgCS::prefix| == 0
		serializationMatchSteps[59] = createMatchStep_Assert(57);
		// 60: assert |RootCS::ownedImports| == 0
		serializationMatchSteps[60] = createMatchStep_Assert(66);
		// 61: assert |SelfExpCS::name| == 0
		serializationMatchSteps[61] = createMatchStep_Assert(67);
		// 62: assert |ShadowPartCS::referredProperty| == 0
		serializationMatchSteps[62] = createMatchStep_Assert(69);
		// 63: assert |TypeLiteralExpCS::ownedPathName| == 0
		serializationMatchSteps[63] = createMatchStep_Assert(79);
		// 64: assert |TypedElementCS::isOptional| == 0
		serializationMatchSteps[64] = createMatchStep_Assert(85);
		// 65: assert |TypedElementCS::qualifiers| == 0
		serializationMatchSteps[65] = createMatchStep_Assert(87);
		// 66: assert |TypedRefCS::ownedMultiplicity| == 0
		serializationMatchSteps[66] = createMatchStep_Assert(88);
		// 67: assert |TypedTypeRefCS::isTypeof| == 0
		serializationMatchSteps[67] = createMatchStep_Assert(89);
		// 68: assert |VariableCS::ownedInitExpression| == 0
		serializationMatchSteps[68] = createMatchStep_Assert(92);
		// 69: assert |WildcardTypeRefCS::ownedSuper| == 0
		serializationMatchSteps[69] = createMatchStep_Assert(95);
		// 70: assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[70] = createMatchStep_Assign(0, 99);
		// 71: assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchSteps[71] = createMatchStep_Assign(0, 107);
		// 72: assign V0 = (|LetExpCS::ownedVariables| - 1)
		serializationMatchSteps[72] = createMatchStep_Assign(0, 116);
		// 73: assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[73] = createMatchStep_Assign(0, 118);
		// 74: assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[74] = createMatchStep_Assign(0, 139);
		// 75: assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[75] = createMatchStep_Assign(0, 144);
		// 76: assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[76] = createMatchStep_Assign(0, 145);
		// 77: assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[77] = createMatchStep_Assign(0, 147);
		// 78: assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[78] = createMatchStep_Assign(0, 148);
		// 79: assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[79] = createMatchStep_Assign(0, 151);
		// 80: assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[80] = createMatchStep_Assign(0, 155);
		// 81: assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchSteps[81] = createMatchStep_Assign(0, 7);
		// 82: assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchSteps[82] = createMatchStep_Assign(0, 12);
		// 83: assign V0 = |CollectionPatternCS::restVariableName|
		serializationMatchSteps[83] = createMatchStep_Assign(0, 16);
		// 84: assign V0 = |CollectionTypeCS::ownedType|
		serializationMatchSteps[84] = createMatchStep_Assign(0, 19);
		// 85: assign V0 = |IfExpCS::ownedIfThenExpressions|
		serializationMatchSteps[85] = createMatchStep_Assign(0, 26);
		// 86: assign V0 = |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchSteps[86] = createMatchStep_Assign(0, 35);
		// 87: assign V0 = |MapTypeCS::ownedValueType|
		serializationMatchSteps[87] = createMatchStep_Assign(0, 42);
		// 88: assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[88] = createMatchStep_Assign(0, 45);
		// 89: assign V0 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[89] = createMatchStep_Assign(0, 50);
		// 90: assign V0 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[90] = createMatchStep_Assign(0, 51);
		// 91: assign V0 = |NavigatingArgCS::ownedType|
		serializationMatchSteps[91] = createMatchStep_Assign(0, 53);
		// 92: assign V0 = |PatternExpCS::patternVariableName|
		serializationMatchSteps[92] = createMatchStep_Assign(0, 64);
		// 93: assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[93] = createMatchStep_Assign(0, 71);
		// 94: assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[94] = createMatchStep_Assign(0, 81);
		// 95: assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[95] = createMatchStep_Assign(0, 88);
		// 96: assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[96] = createMatchStep_Assign(0, 90);
		// 97: assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[97] = createMatchStep_Assign(0, 93);
		// 98: assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[98] = createMatchStep_Assign(0, 94);
		// 99: assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[99] = createMatchStep_Assign(1, 98);
		// 100: assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[100] = createMatchStep_Assign(1, 102);
		// 101: assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[101] = createMatchStep_Assign(1, 106);
		// 102: assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[102] = createMatchStep_Assign(1, 117);
		// 103: assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[103] = createMatchStep_Assign(1, 151);
		// 104: assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[104] = createMatchStep_Assign(1, 154);
		// 105: assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[105] = createMatchStep_Assign(1, 6);
		// 106: assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[106] = createMatchStep_Assign(1, 18);
		// 107: assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[107] = createMatchStep_Assign(1, 50);
		// 108: assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[108] = createMatchStep_Assign(1, 51);
		// 109: assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[109] = createMatchStep_Assign(1, 72);
		// 110: assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[110] = createMatchStep_Assign(1, 83);
		// 111: assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[111] = createMatchStep_Assign(1, 88);
		// 112: assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[112] = createMatchStep_Assign(1, 93);
		// 113: assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[113] = createMatchStep_Assign(2, 150);
		// 114: assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[114] = createMatchStep_Assign(2, 4);
		// 115: assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[115] = createMatchStep_Assign(2, 88);
		// 116: assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[116] = createMatchStep_Assign(3, 3);
		// 117: assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[117] = createMatchStep_Assign(3, 88);
		// 118: check-rule basecs::PathNameCS.ownedPathElements : 21
		serializationMatchSteps[118] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 6/*FirstPathElementCS*/);
		// 119: check-rule basecs::PathNameCS.ownedPathElements : 51
		serializationMatchSteps[119] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 14/*NextPathElementCS*/);
		// 120: check-rule basecs::TemplateBindingCS.ownedMultiplicity : 40
		serializationMatchSteps[120] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 10/*MultiplicityCS*/);
		// 121: check-rule basecs::TemplateBindingCS.ownedSubstitutions : 73
		serializationMatchSteps[121] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 27/*TemplateParameterSubstitutionCS*/);
		// 122: check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 86|87|88|99
		serializationMatchSteps[122] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 48/*TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS*/);
		// 123: check-rule basecs::TemplateSignatureCS.ownedParameters : 85
		serializationMatchSteps[123] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 35/*TypeParameterCS*/);
		// 124: check-rule basecs::TupleTypeCS.ownedParts : 77
		serializationMatchSteps[124] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 29/*TuplePartCS*/);
		// 125: check-rule basecs::TypeParameterCS.ownedExtends : 87|88
		serializationMatchSteps[125] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 38/*TypedRefCS|TypedTypeRefCS*/);
		// 126: check-rule basecs::TypedElementCS.ownedType : 79
		serializationMatchSteps[126] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 30/*TypeExpCS*/);
		// 127: check-rule basecs::TypedRefCS.ownedMultiplicity : 40
		serializationMatchSteps[127] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/*MultiplicityCS*/);
		// 128: check-rule basecs::TypedTypeRefCS.ownedBinding : 72
		serializationMatchSteps[128] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 26/*TemplateBindingCS*/);
		// 129: check-rule basecs::TypedTypeRefCS.ownedPathName : 54
		serializationMatchSteps[129] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 16/*PathNameCS*/);
		// 130: check-rule basecs::WildcardTypeRefCS.ownedExtends : 87|88
		serializationMatchSteps[130] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 38/*TypedRefCS|TypedTypeRefCS*/);
		// 131: check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 9
		serializationMatchSteps[131] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 3/*CurlyBracketedClauseCS*/);
		// 132: check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 54
		serializationMatchSteps[132] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 16/*PathNameCS*/);
		// 133: check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 62
		serializationMatchSteps[133] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 22/*RoundBracketedClauseCS*/);
		// 134: check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 69
		serializationMatchSteps[134] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 24/*SquareBracketedClauseCS*/);
		// 135: check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5
		serializationMatchSteps[135] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 1/*CollectionLiteralPartCS*/);
		// 136: check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7
		serializationMatchSteps[136] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// 137: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[137] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 138: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 55
		serializationMatchSteps[138] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 17/*PatternExpCS*/);
		// 139: check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[139] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 140: check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55
		serializationMatchSteps[140] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 17/*PatternExpCS*/);
		// 141: check-rule essentialoclcs::CollectionPatternCS.ownedType : 7
		serializationMatchSteps[141] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// 142: check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40
		serializationMatchSteps[142] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 10/*MultiplicityCS*/);
		// 143: check-rule essentialoclcs::CollectionTypeCS.ownedType : 6|7|37|60|78|80|81|84
		serializationMatchSteps[143] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 34/*CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS*/);
		// 144: check-rule essentialoclcs::ContextCS.ownedExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[144] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 145: check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 67
		serializationMatchSteps[145] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 23/*ShadowPartCS*/);
		// 146: check-rule essentialoclcs::IfExpCS.ownedCondition : 2|4|20|25|27|30|31|35|43|50|52|53|55|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[146] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 47/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 147: check-rule essentialoclcs::IfExpCS.ownedElseExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[147] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 148: check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13
		serializationMatchSteps[148] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 4/*ElseIfThenExpCS*/);
		// 149: check-rule essentialoclcs::IfExpCS.ownedThenExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[149] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 150: check-rule essentialoclcs::IfThenExpCS.ownedCondition : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[150] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 151: check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[151] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 152: check-rule essentialoclcs::InfixExpCS.ownedLeft : 2|4|25|27|30|35|43|50|52|53|57|58|59|66|71|75|82|94
		serializationMatchSteps[152] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 43/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 153: check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[153] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 154: check-rule essentialoclcs::LetExpCS.ownedInExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[154] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 155: check-rule essentialoclcs::LetExpCS.ownedVariables : 32
		serializationMatchSteps[155] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 7/*LetVariableCS*/);
		// 156: check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 62
		serializationMatchSteps[156] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 22/*RoundBracketedClauseCS*/);
		// 157: check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36
		serializationMatchSteps[157] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 8/*MapLiteralPartCS*/);
		// 158: check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37
		serializationMatchSteps[158] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 9/*MapTypeCS*/);
		// 159: check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[159] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 160: check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[160] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 161: check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79
		serializationMatchSteps[161] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 30/*TypeExpCS*/);
		// 162: check-rule essentialoclcs::MapTypeCS.ownedValueType : 79
		serializationMatchSteps[162] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 30/*TypeExpCS*/);
		// 163: check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3
		serializationMatchSteps[163] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/*CoIteratorVariableCS*/);
		// 164: check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[164] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 165: check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 2|4|20|25|27|30|31|35|43|45|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[165] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 46/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 166: check-rule essentialoclcs::NavigatingArgCS.ownedType : 79
		serializationMatchSteps[166] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 30/*TypeExpCS*/);
		// 167: check-rule essentialoclcs::NestedExpCS.ownedExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[167] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 168: check-rule essentialoclcs::OperatorExpCS.ownedRight : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[168] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 169: check-rule essentialoclcs::OperatorExpCS.ownedRight : 2|4|25|27|30|35|43|50|52|53|57|58|59|66|71|75|82|94
		serializationMatchSteps[169] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 43/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 170: check-rule essentialoclcs::OperatorExpCS.ownedRight : 31|56
		serializationMatchSteps[170] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 20/*LetExpCS|PrefixedLetExpCS*/);
		// 171: check-rule essentialoclcs::PatternExpCS.ownedPatternType : 79
		serializationMatchSteps[171] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 30/*TypeExpCS*/);
		// 172: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 2|4|20|25|27|30|31|35|43|50|52|53|55|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[172] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 47/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 173: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 71
		serializationMatchSteps[173] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 25/*StringLiteralExpCS*/);
		// 174: check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[174] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 175: check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76
		serializationMatchSteps[175] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 28/*TupleLiteralPartCS*/);
		// 176: check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83
		serializationMatchSteps[176] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 33/*TypeLiteralWithMultiplicityCS*/);
		// 177: check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9
		serializationMatchSteps[177] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 3/*CurlyBracketedClauseCS*/);
		// 178: check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54
		serializationMatchSteps[178] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 16/*PathNameCS*/);
		// 179: check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[179] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 180: check-rule essentialoclcs::VariableCS.ownedInitExpression : 2|4|20|25|27|30|31|35|43|50|52|53|56|57|58|59|66|71|75|82|94
		serializationMatchSteps[180] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 45/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 181: check-rule essentialoclcs::VariableCS.ownedType : 79
		serializationMatchSteps[181] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 30/*TypeExpCS*/);
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
		serializationMatchTerms[3] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 3 /* '@' */);
		// 4: |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[4] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// 5: |AbstractNameExpCS::ownedPathName|
		serializationMatchTerms[5] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		// 6: |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchTerms[6] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// 7: |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchTerms[7] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		// 8: |BooleanLiteralExpCS::symbol.'false|true'|
		serializationMatchTerms[8] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 6 /* 'false|true' */);
		// 9: |CollectionLiteralExpCS::ownedParts|
		serializationMatchTerms[9] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		// 10: |CollectionLiteralExpCS::ownedType|
		serializationMatchTerms[10] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		// 11: |CollectionLiteralPartCS::ownedExpression|
		serializationMatchTerms[11] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		// 12: |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchTerms[12] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		// 13: |CollectionPatternCS::ownedParts|
		serializationMatchTerms[13] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		// 14: |CollectionPatternCS::ownedPatternGuard|
		serializationMatchTerms[14] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD);
		// 15: |CollectionPatternCS::ownedType|
		serializationMatchTerms[15] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		// 16: |CollectionPatternCS::restVariableName|
		serializationMatchTerms[16] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		// 17: |CollectionTypeCS::name|
		serializationMatchTerms[17] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		// 18: |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchTerms[18] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		// 19: |CollectionTypeCS::ownedType|
		serializationMatchTerms[19] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		// 20: |ContextCS::ownedExpression|
		serializationMatchTerms[20] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		// 21: |CurlyBracketedClauseCS::ownedParts|
		serializationMatchTerms[21] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		// 22: |CurlyBracketedClauseCS::value|
		serializationMatchTerms[22] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__VALUE);
		// 23: |IfExpCS::isImplicit|
		serializationMatchTerms[23] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__IS_IMPLICIT);
		// 24: |IfExpCS::ownedCondition|
		serializationMatchTerms[24] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		// 25: |IfExpCS::ownedElseExpression|
		serializationMatchTerms[25] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		// 26: |IfExpCS::ownedIfThenExpressions|
		serializationMatchTerms[26] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		// 27: |IfExpCS::ownedThenExpression|
		serializationMatchTerms[27] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		// 28: |IfThenExpCS::ownedCondition|
		serializationMatchTerms[28] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		// 29: |IfThenExpCS::ownedThenExpression|
		serializationMatchTerms[29] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		// 30: |InfixExpCS::ownedLeft|
		serializationMatchTerms[30] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		// 31: |LambdaLiteralExpCS::ownedExpressionCS|
		serializationMatchTerms[31] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		// 32: |LetExpCS::isImplicit|
		serializationMatchTerms[32] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__IS_IMPLICIT);
		// 33: |LetExpCS::ownedInExpression|
		serializationMatchTerms[33] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		// 34: |LetExpCS::ownedVariables|
		serializationMatchTerms[34] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		// 35: |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchTerms[35] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// 36: |MapLiteralExpCS::ownedParts|
		serializationMatchTerms[36] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		// 37: |MapLiteralExpCS::ownedType|
		serializationMatchTerms[37] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		// 38: |MapLiteralPartCS::ownedKey|
		serializationMatchTerms[38] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		// 39: |MapLiteralPartCS::ownedValue|
		serializationMatchTerms[39] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		// 40: |MapTypeCS::name.'Map'|
		serializationMatchTerms[40] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 4 /* 'Map' */);
		// 41: |MapTypeCS::ownedKeyType|
		serializationMatchTerms[41] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		// 42: |MapTypeCS::ownedValueType|
		serializationMatchTerms[42] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		// 43: |ModelElementCS::ownedAnnotations|
		serializationMatchTerms[43] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		// 44: |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[44] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// 45: |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[45] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// 46: |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[46] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 8 /* '|1' */);
		// 47: |MultiplicityCS::isNullFree|
		serializationMatchTerms[47] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE);
		// 48: |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[48] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */);
		// 49: |NamedElementCS::name|
		serializationMatchTerms[49] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// 50: |NavigatingArgCS::ownedCoIterator|
		serializationMatchTerms[50] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		// 51: |NavigatingArgCS::ownedInitExpression|
		serializationMatchTerms[51] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		// 52: |NavigatingArgCS::ownedNameExpression|
		serializationMatchTerms[52] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		// 53: |NavigatingArgCS::ownedType|
		serializationMatchTerms[53] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		// 54: |NavigatingArgCS::prefix.','|
		serializationMatchTerms[54] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */);
		// 55: |NavigatingArgCS::prefix.';'|
		serializationMatchTerms[55] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 2 /* ';' */);
		// 56: |NavigatingArgCS::prefix.'|'|
		serializationMatchTerms[56] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 7 /* '|' */);
		// 57: |NavigatingArgCS::prefix|
		serializationMatchTerms[57] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX);
		// 58: |NestedExpCS::ownedExpression|
		serializationMatchTerms[58] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		// 59: |NumberLiteralExpCS::symbol|
		serializationMatchTerms[59] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		// 60: |OperatorExpCS::ownedRight|
		serializationMatchTerms[60] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		// 61: |PathElementCS::referredElement|
		serializationMatchTerms[61] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// 62: |PathNameCS::ownedPathElements|
		serializationMatchTerms[62] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// 63: |PatternExpCS::ownedPatternType|
		serializationMatchTerms[63] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		// 64: |PatternExpCS::patternVariableName|
		serializationMatchTerms[64] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		// 65: |PrimitiveTypeRefCS::name|
		serializationMatchTerms[65] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		// 66: |RootCS::ownedImports|
		serializationMatchTerms[66] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		// 67: |SelfExpCS::name|
		serializationMatchTerms[67] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SELF_EXP_CS__NAME);
		// 68: |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[68] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// 69: |ShadowPartCS::referredProperty|
		serializationMatchTerms[69] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// 70: |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[70] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// 71: |StringLiteralExpCS::segments|
		serializationMatchTerms[71] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// 72: |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[72] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// 73: |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[73] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// 74: |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[74] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// 75: |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[75] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// 76: |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[76] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// 77: |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[77] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 5 /* 'Tuple' */);
		// 78: |TupleTypeCS::ownedParts|
		serializationMatchTerms[78] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// 79: |TypeLiteralExpCS::ownedPathName|
		serializationMatchTerms[79] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_PATH_NAME);
		// 80: |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[80] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// 81: |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[81] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// 82: |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[82] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// 83: |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[83] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// 84: |TypeParameterCS::ownedExtends|
		serializationMatchTerms[84] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// 85: |TypedElementCS::isOptional|
		serializationMatchTerms[85] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__IS_OPTIONAL);
		// 86: |TypedElementCS::ownedType|
		serializationMatchTerms[86] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		// 87: |TypedElementCS::qualifiers|
		serializationMatchTerms[87] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		// 88: |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[88] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// 89: |TypedTypeRefCS::isTypeof|
		serializationMatchTerms[89] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF);
		// 90: |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[90] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// 91: |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[91] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// 92: |VariableCS::ownedInitExpression|
		serializationMatchTerms[92] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		// 93: |VariableCS::ownedType|
		serializationMatchTerms[93] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		// 94: |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[94] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// 95: |WildcardTypeRefCS::ownedSuper|
		serializationMatchTerms[95] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_SUPER);
		// 96: (|AbstractNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[96] = createSerializationMatchTermSubtract(5, 1);
		// 97: (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
		serializationMatchTerms[97] = createSerializationMatchTermSubtract(8, 1);
		// 98: (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[98] = createSerializationMatchTermSubtract(9, 1);
		// 99: (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[99] = createSerializationMatchTermGreaterThan(9, 0);
		// 100: (|CollectionLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[100] = createSerializationMatchTermSubtract(10, 1);
		// 101: (|CollectionLiteralPartCS::ownedExpression| - 1)
		serializationMatchTerms[101] = createSerializationMatchTermSubtract(11, 1);
		// 102: (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchTerms[102] = createSerializationMatchTermSubtract(13, 1);
		// 103: (|CollectionPatternCS::ownedType| - 1)
		serializationMatchTerms[103] = createSerializationMatchTermSubtract(15, 1);
		// 104: (|CollectionTypeCS::name| - 1)
		serializationMatchTerms[104] = createSerializationMatchTermSubtract(17, 1);
		// 105: (|ContextCS::ownedExpression| - 1)
		serializationMatchTerms[105] = createSerializationMatchTermSubtract(20, 1);
		// 106: (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchTerms[106] = createSerializationMatchTermSubtract(21, 1);
		// 107: (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchTerms[107] = createSerializationMatchTermGreaterThan(21, 0);
		// 108: (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[108] = createSerializationMatchTermSubtract(24, 1);
		// 109: (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[109] = createSerializationMatchTermSubtract(25, 1);
		// 110: (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[110] = createSerializationMatchTermSubtract(27, 1);
		// 111: (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[111] = createSerializationMatchTermSubtract(28, 1);
		// 112: (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[112] = createSerializationMatchTermSubtract(29, 1);
		// 113: (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[113] = createSerializationMatchTermSubtract(30, 1);
		// 114: (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[114] = createSerializationMatchTermSubtract(31, 1);
		// 115: (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[115] = createSerializationMatchTermSubtract(33, 1);
		// 116: (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[116] = createSerializationMatchTermSubtract(34, 1);
		// 117: (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[117] = createSerializationMatchTermSubtract(36, 1);
		// 118: (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[118] = createSerializationMatchTermGreaterThan(36, 0);
		// 119: (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[119] = createSerializationMatchTermSubtract(37, 1);
		// 120: (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[120] = createSerializationMatchTermSubtract(38, 1);
		// 121: (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[121] = createSerializationMatchTermSubtract(39, 1);
		// 122: (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[122] = createSerializationMatchTermSubtract(40, 1);
		// 123: (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[123] = createSerializationMatchTermSubtract(41, 2);
		// 124: (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[124] = createSerializationMatchTermSubtract(44, 1);
		// 125: (|MultiplicityCS::isNullFree.'|1'| - 1)
		serializationMatchTerms[125] = createSerializationMatchTermSubtract(46, 1);
		// 126: (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[126] = createSerializationMatchTermSubtract(48, 1);
		// 127: (|NamedElementCS::name| - 1)
		serializationMatchTerms[127] = createSerializationMatchTermSubtract(49, 1);
		// 128: (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[128] = createSerializationMatchTermSubtract(50, 1);
		// 129: (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[129] = createSerializationMatchTermSubtract(51, 1);
		// 130: (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[130] = createSerializationMatchTermSubtract(52, 1);
		// 131: (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[131] = createSerializationMatchTermSubtract(53, 1);
		// 132: (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[132] = createSerializationMatchTermSubtract(54, 1);
		// 133: (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[133] = createSerializationMatchTermSubtract(55, 1);
		// 134: (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[134] = createSerializationMatchTermSubtract(56, 1);
		// 135: (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[135] = createSerializationMatchTermSubtract(58, 1);
		// 136: (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[136] = createSerializationMatchTermSubtract(59, 1);
		// 137: (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[137] = createSerializationMatchTermSubtract(60, 1);
		// 138: (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[138] = createSerializationMatchTermSubtract(61, 1);
		// 139: (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[139] = createSerializationMatchTermSubtract(62, 1);
		// 140: (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[140] = createSerializationMatchTermSubtract(63, 1);
		// 141: (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[141] = createSerializationMatchTermSubtract(65, 1);
		// 142: (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[142] = createSerializationMatchTermSubtract(68, 1);
		// 143: (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[143] = createSerializationMatchTermSubtract(69, 1);
		// 144: (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[144] = createSerializationMatchTermSubtract(70, 1);
		// 145: (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[145] = createSerializationMatchTermSubtract(73, 1);
		// 146: (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[146] = createSerializationMatchTermSubtract(74, 1);
		// 147: (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[147] = createSerializationMatchTermSubtract(75, 1);
		// 148: (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[148] = createSerializationMatchTermSubtract(76, 1);
		// 149: (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[149] = createSerializationMatchTermSubtract(77, 1);
		// 150: (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[150] = createSerializationMatchTermSubtract(78, 1);
		// 151: (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[151] = createSerializationMatchTermGreaterThan(78, 0);
		// 152: (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[152] = createSerializationMatchTermSubtract(80, 1);
		// 153: (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[153] = createSerializationMatchTermSubtract(82, 1);
		// 154: (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[154] = createSerializationMatchTermSubtract(84, 1);
		// 155: (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[155] = createSerializationMatchTermGreaterThan(84, 0);
		// 156: (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[156] = createSerializationMatchTermSubtract(86, 1);
		// 157: (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[157] = createSerializationMatchTermSubtract(91, 1);
		// 158: (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[158] = createSerializationMatchTermSubtract(92, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS-0(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] = createSerializationRule("FirstPathElementCS-0", 21,
			createSerializationMatchSteps(
				34		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				147		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::MultiplicityBoundsCS-0(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { ".." upperBound=UPPER }[?] }
		serializationRules[1] = createSerializationRule("MultiplicityBoundsCS-0", 39,
			createSerializationMatchSteps(
				53		/* assert |MultiplicityCS::isNullFree| == 0 */,
				88		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				20		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				2		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				116		/* V00*2-steps || value */,
				86		/* '..' || no-space value no-space */,
				77		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-0(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" }
		serializationRules[2] = createSerializationRule("MultiplicityCS-0", 40,
			createSerializationMatchSteps(
				53		/* assert |MultiplicityCS::isNullFree| == 0 */,
				88		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				20		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				93		/* '[' || no-space value no-space */,
				2		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				116		/* V00*2-steps || value */,
				86		/* '..' || no-space value no-space */,
				77		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				111		/* '|?' || no-space value no-space */,
				94		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-1(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree?="|1" "]" }
		serializationRules[3] = createSerializationRule("MultiplicityCS-1", 40,
			createSerializationMatchSteps(
				21		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				88		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				20		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				93		/* '[' || no-space value no-space */,
				2		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				116		/* V00*2-steps || value */,
				86		/* '..' || no-space value no-space */,
				77		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				0		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				94		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(8/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-2(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" }
		serializationRules[4] = createSerializationRule("MultiplicityCS-2", 40,
			createSerializationMatchSteps(
				53		/* assert |MultiplicityCS::isNullFree| == 0 */,
				88		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				20		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				93		/* '[' || no-space value no-space */,
				2		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				116		/* V00*2-steps || value */,
				86		/* '..' || no-space value no-space */,
				77		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				94		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-3(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "|?" "]" }
		serializationRules[5] = createSerializationRule("MultiplicityCS-3", 40,
			createSerializationMatchSteps(
				53		/* assert |MultiplicityCS::isNullFree| == 0 */,
				22		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				93		/* '[' || no-space value no-space */,
				74		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				111		/* '|?' || no-space value no-space */,
				94		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-4(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} isNullFree?="|1" "]" }
		serializationRules[6] = createSerializationRule("MultiplicityCS-4", 40,
			createSerializationMatchSteps(
				21		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				22		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				93		/* '[' || no-space value no-space */,
				74		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				0		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				94		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(8/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-5(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "]" }
		serializationRules[7] = createSerializationRule("MultiplicityCS-5", 40,
			createSerializationMatchSteps(
				53		/* assert |MultiplicityCS::isNullFree| == 0 */,
				22		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				93		/* '[' || no-space value no-space */,
				74		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				94		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityStringCS-0(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		serializationRules[8] = createSerializationRule("MultiplicityStringCS-0", 41,
			createSerializationMatchSteps(
				53		/* assert |MultiplicityCS::isNullFree| == 0 */,
				22		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				74		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::NextPathElementCS-0(basecs::PathElementCS): referredElement=UnreservedName
		serializationRules[9] = createSerializationRule("NextPathElementCS-0", 51,
			createSerializationMatchSteps(
				34		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				146		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::PathNameCS-0(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[10] = createSerializationRule("PathNameCS-0", 54,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				139		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */,
				117		/* V00*2-steps || value */,
				88		/* '::' || no-space value no-space */,
				140		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 15/* FirstPathElementCS,NextPathElementCS */,
					(21/*FirstPathElementCS*/ << 4) | 0 /*[1]*/,
					(51/*NextPathElementCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TemplateBindingCS-0(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] = createSerializationRule("TemplateBindingCS-0", 72,
			createSerializationMatchSteps(
				120		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : MultiplicityCS */,
				121		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : TemplateParameterSubstitutionCS */,
				109		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				76		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			),
			createSerializationSteps(
				52		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				117		/* V00*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				52		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				123		/* V01*1-steps || value */,
				33		/* TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 27/* TemplateParameterSubstitutionCS */,
					(73/*TemplateParameterSubstitutionCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TemplateParameterSubstitutionCS-0(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] = createSerializationRule("TemplateParameterSubstitutionCS-0", 73,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				122		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
				40		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			),
			createSerializationSteps(
				7		/* TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 36/* TypeRefCS */,
					(86/*TypeRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::TemplateSignatureCS-0(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[13] = createSerializationRule("TemplateSignatureCS-0", 74,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				123		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				77		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				80		/* '(' || no-space value no-space */,
				37		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				117		/* V00*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				37		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				81		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 35/* TypeParameterCS */,
					(85/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TypeParameterCS-0(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[14] = createSerializationRule("TypeParameterCS-0", 85,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				125		/* check-rule basecs::TypeParameterCS.ownedExtends : TypedRefCS|TypedTypeRefCS */,
				23		/* assert (|NamedElementCS::name| - 1) == 0 */,
				80		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				104		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				136		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				120		/* V00*5-steps || value */,
				98		/* 'extends' || soft-space value soft-space */,
				21		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */,
				126		/* V01*2-steps || value */,
				78		/* '&&' || soft-space value soft-space */,
				21		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 37/* TypedRefCS */,
					(87/*TypedRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TypedTypeRefCS-0(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] }
		serializationRules[15] = createSerializationRule("TypedTypeRefCS-0", 88,
			createSerializationMatchSteps(
				67		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				66		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				128		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				129		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				96		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				45		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				46		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */,
				118		/* V00*3-steps || value */,
				80		/* '(' || no-space value no-space */,
				8		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				81		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 26/* TemplateBindingCS */,
					(72/*TemplateBindingCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 16/* PathNameCS */,
					(54/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::UnreservedPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=NextPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[16] = createSerializationRule("UnreservedPathNameCS-0", 96,
			createSerializationMatchSteps(
				119		/* check-rule basecs::PathNameCS.ownedPathElements : NextPathElementCS */,
				74		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			),
			createSerializationSteps(
				140		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */,
				117		/* V00*2-steps || value */,
				88		/* '::' || no-space value no-space */,
				140		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 14/* NextPathElementCS */,
					(51/*NextPathElementCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::WildcardTypeRefCS-0(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[17] = createSerializationRule("WildcardTypeRefCS-0", 99,
			createSerializationMatchSteps(
				69		/* assert |WildcardTypeRefCS::ownedSuper| == 0 */,
				130		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : TypedRefCS|TypedTypeRefCS */,
				98		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			),
			createSerializationSteps(
				90		/* '?' || soft-space value soft-space */,
				116		/* V00*2-steps || value */,
				98		/* 'extends' || soft-space value soft-space */,
				22		/* WildcardTypeRefCS::ownedExtends=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 37/* TypedRefCS */,
					(87/*TypedRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::BooleanLiteralExpCS-0(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[18] = createSerializationRule("BooleanLiteralExpCS-0", 2,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			),
			createSerializationSteps(
				75		/* BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, false,
					(6/*'false|true'*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CoIteratorVariableCS-0(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[19] = createSerializationRule("CoIteratorVariableCS-0", 3,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				68		/* assert |VariableCS::ownedInitExpression| == 0 */,
				181		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				97		/* assign V0 = |VariableCS::ownedType| */,
				23		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				136		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				116		/* V00*2-steps || value */,
				87		/* ':' || soft-space value soft-space */,
				63		/* VariableCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralExpCS-0(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[20] = createSerializationRule("CollectionLiteralExpCS-0", 4,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				135		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS */,
				136		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS */,
				2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				70		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				99		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				56		/* CollectionLiteralExpCS::ownedType=CollectionTypeCS || value */,
				110		/* '{' || soft-space value push soft-new-line */,
				119		/* V00*4-steps || value */,
				38		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				126		/* V01*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				38		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				112		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 1/* CollectionLiteralPartCS */,
					(5/*CollectionLiteralPartCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 2/* CollectionTypeCS */,
					(7/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionLiteralPartCS-0(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] }
		serializationRules[21] = createSerializationRule("CollectionLiteralPartCS-0", 5,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				137		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				139		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				82		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				16		/* CollectionLiteralPartCS::ownedExpression=ExpCS || value */,
				116		/* V00*2-steps || value */,
				86		/* '..' || no-space value no-space */,
				31		/* CollectionLiteralPartCS::ownedLastExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralPartCS-1(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
		serializationRules[22] = createSerializationRule("CollectionLiteralPartCS-1", 5,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				47		/* assert |CollectionLiteralPartCS::ownedLastExpression| == 0 */,
				138		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : PatternExpCS */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				17		/* CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 17/* PatternExpCS */,
					(55/*PatternExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionPatternCS-0(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" }
		serializationRules[23] = createSerializationRule("CollectionPatternCS-0", 6,
			createSerializationMatchSteps(
				66		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				48		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				140		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				141		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				83		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				100		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				57		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				110		/* '{' || soft-space value push soft-new-line */,
				121		/* V00*6-steps || value */,
				39		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				126		/* V01*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				39		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				83		/* '++' || soft-space value soft-space */,
				72		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				112		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 17/* PatternExpCS */,
					(55/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/* CollectionTypeCS */,
					(7/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionTypeCS-0(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] }
		serializationRules[24] = createSerializationRule("CollectionTypeCS-0", 7,
			createSerializationMatchSteps(
				66		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				142		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				143		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				84		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				106		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				3		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				120		/* V00*5-steps || value */,
				80		/* '(' || no-space value no-space */,
				58		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				123		/* V01*1-steps || value */,
				10		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				81		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 31/* TypeExpWithoutMultiplicityCS */,
					(80/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS-0(essentialoclcs::CurlyBracketedClauseCS): { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" }
		serializationRules[25] = createSerializationRule("CurlyBracketedClauseCS-0", 9,
			createSerializationMatchSteps(
				49		/* assert |CurlyBracketedClauseCS::value| == 0 */,
				145		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : ShadowPartCS */,
				71		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				101		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				110		/* '{' || soft-space value push soft-new-line */,
				119		/* V00*4-steps || value */,
				40		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				126		/* V01*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				40		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				112		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 23/* ShadowPartCS */,
					(67/*ShadowPartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::ElseIfThenExpCS-0(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[26] = createSerializationRule("ElseIfThenExpCS-0", 13,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				150		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				151		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				11		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				10		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				96		/* 'elseif' || soft-new-line pop soft-space value push soft-space */,
				12		/* IfThenExpCS::ownedCondition=ExpCS || value */,
				107		/* 'then' || pop value push soft-space */,
				55		/* IfThenExpCS::ownedThenExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::ExpCS-18(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } }
		serializationRules[27] = createSerializationRule("ExpCS-18", 20,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				152		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				168		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				33		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				23		/* assert (|NamedElementCS::name| - 1) == 0 */,
				12		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				32		/* InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value */,
				134		/* NamedElementCS::name=BinaryOperatorName || soft-space value soft-space */,
				142		/* OperatorExpCS::ownedRight=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 21/* PrefixedPrimaryExpCS */,
					(57/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::IfExpCS-0(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[28] = createSerializationRule("IfExpCS-0", 25,
			createSerializationMatchSteps(
				50		/* assert |IfExpCS::isImplicit| == 0 */,
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				146		/* check-rule essentialoclcs::IfExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				147		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				148		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS */,
				149		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				8		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				85		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				9		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				7		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				99		/* 'if' || soft-new-line value push soft-space */,
				11		/* IfExpCS::ownedCondition=ExpCS|PatternExpCS || value */,
				108		/* 'then' || pop soft-space value push soft-space */,
				54		/* IfExpCS::ownedThenExpression=ExpCS || value */,
				114		/* V00*1-steps || value */,
				23		/* IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value */,
				95		/* 'else' || soft-new-line pop value push soft-space */,
				15		/* IfExpCS::ownedElseExpression=ExpCS || value */,
				97		/* 'endif' || soft-new-line pop value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 18/* ExpCS,PatternExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/,
					(55/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 4/* ElseIfThenExpCS */,
					(13/*ElseIfThenExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::InvalidLiteralExpCS-0(essentialoclcs::InvalidLiteralExpCS): "invalid"
		serializationRules[29] = createSerializationRule("InvalidLiteralExpCS-0", 27,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				102		/* 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::LambdaLiteralExpCS-0(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[30] = createSerializationRule("LambdaLiteralExpCS-0", 30,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				153		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				13		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				91		/* 'Lambda' || soft-space value soft-space */,
				110		/* '{' || soft-space value push soft-new-line */,
				20		/* LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value */,
				112		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::LetExpCS-0(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[31] = createSerializationRule("LetExpCS-0", 31,
			createSerializationMatchSteps(
				51		/* assert |LetExpCS::isImplicit| == 0 */,
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				154		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				155		/* check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS */,
				14		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				72		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				103		/* 'let' || soft-space value push */,
				66		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				117		/* V00*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				66		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				100		/* 'in' || soft-space pop value soft-new-line */,
				24		/* LetExpCS::ownedInExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 7/* LetVariableCS */,
					(32/*LetVariableCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::LetVariableCS-0(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[32] = createSerializationRule("LetVariableCS-0", 32,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				180		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				156		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				181		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				46		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				112		/* assign V1 = |VariableCS::ownedType| */,
				86		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				23		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				136		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				113		/* V00*1-steps || value */,
				50		/* LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				125		/* V01*2-steps || value */,
				87		/* ':' || soft-space value soft-space */,
				63		/* VariableCS::ownedType=TypeExpCS || value */,
				89		/* '=' || soft-space value soft-space */,
				28		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 22/* RoundBracketedClauseCS */,
					(62/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::MapLiteralExpCS-0(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[33] = createSerializationRule("MapLiteralExpCS-0", 35,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				157		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS */,
				158		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS */,
				15		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				73		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				102		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				59		/* MapLiteralExpCS::ownedType=MapTypeCS || value */,
				110		/* '{' || soft-space value push soft-new-line */,
				119		/* V00*4-steps || value */,
				41		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				126		/* V01*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				41		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				112		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 8/* MapLiteralPartCS */,
					(36/*MapLiteralPartCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 9/* MapTypeCS */,
					(37/*MapTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapLiteralPartCS-0(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS {"with"|"<-"} ownedValue=ExpCS }
		serializationRules[34] = createSerializationRule("MapLiteralPartCS-0", 36,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				159		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				160		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				17		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				16		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				29		/* MapLiteralPartCS::ownedKey=ExpCS || value */,
				109		/* 'with' || value */,
				64		/* MapLiteralPartCS::ownedValue=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapTypeCS-0(essentialoclcs::MapTypeCS): { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] }
		serializationRules[35] = createSerializationRule("MapTypeCS-0", 37,
			createSerializationMatchSteps(
				66		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				161		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				162		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				87		/* assign V0 = |MapTypeCS::ownedValueType| */,
				19		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				18		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				4		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				120		/* V00*5-steps || value */,
				80		/* '(' || no-space value no-space */,
				30		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				85		/* ',' || no-space value soft-space */,
				65		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				81		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(4/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::Model-0(essentialoclcs::ContextCS): ownedExpression=ExpCS
		serializationRules[36] = createSerializationRule("Model-0", 38,
			createSerializationMatchSteps(
				54		/* assert |NamedElementCS::name| == 0 */,
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				60		/* assert |RootCS::ownedImports| == 0 */,
				144		/* check-rule essentialoclcs::ContextCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				6		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				18		/* ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NameExpCS-0(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre?="@" "pre" }[?] }
		serializationRules[37] = createSerializationRule("NameExpCS-0", 43,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				131		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				132		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS */,
				133		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				134		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS */,
				116		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				114		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				105		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				81		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				44		/* AbstractNameExpCS::ownedPathName=PathNameCS || value */,
				114		/* V00*1-steps || value */,
				51		/* AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value */,
				123		/* V01*1-steps || value */,
				49		/* AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				129		/* V02*1-steps || value */,
				13		/* AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				132		/* V03*2-steps || value */,
				1		/* AbstractNameExpCS::isPre?='@' || soft-space value soft-space */,
				105		/* 'pre' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, false,
					(3/*'@'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 3/* CurlyBracketedClauseCS */,
					(9/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 16/* PathNameCS */,
					(54/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 22/* RoundBracketedClauseCS */,
					(62/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 24/* SquareBracketedClauseCS */,
					(69/*SquareBracketedClauseCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-0(essentialoclcs::NavigatingArgCS): { ":" ownedType=TypeExpCS }
		serializationRules[38] = createSerializationRule("NavigatingArgCS-0", 44,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				55		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				56		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				57		/* assert |NavigatingArgCS::ownedNameExpression| == 0 */,
				59		/* assert |NavigatingArgCS::prefix| == 0 */,
				166		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				27		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				87		/* ':' || soft-space value soft-space */,
				60		/* NavigatingArgCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-1(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[39] = createSerializationRule("NavigatingArgCS-1", 44,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				59		/* assert |NavigatingArgCS::prefix| == 0 */,
				163		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				164		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				166		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				108		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				89		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				27		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				35		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				87		/* ':' || soft-space value soft-space */,
				60		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				116		/* V00*2-steps || value */,
				109		/* 'with' || value */,
				9		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				125		/* V01*2-steps || value */,
				89		/* '=' || soft-space value soft-space */,
				25		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(3/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-2(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[40] = createSerializationRule("NavigatingArgCS-2", 44,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				59		/* assert |NavigatingArgCS::prefix| == 0 */,
				163		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				164		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				166		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				25		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				107		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				91		/* assign V0 = |NavigatingArgCS::ownedType| */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				35		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				116		/* V00*2-steps || value */,
				87		/* ':' || soft-space value soft-space */,
				60		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				125		/* V01*2-steps || value */,
				109		/* 'with' || value */,
				9		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				101		/* 'in' || soft-space value soft-space */,
				25		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(3/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-3(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[41] = createSerializationRule("NavigatingArgCS-3", 44,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				58		/* assert |NavigatingArgCS::ownedType| == 0 */,
				59		/* assert |NavigatingArgCS::prefix| == 0 */,
				163		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				164		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				90		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				24		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				35		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				109		/* 'with' || value */,
				9		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				116		/* V00*2-steps || value */,
				89		/* '=' || soft-space value soft-space */,
				25		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(3/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-4(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
		serializationRules[42] = createSerializationRule("NavigatingArgCS-4", 44,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				55		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				56		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				58		/* assert |NavigatingArgCS::ownedType| == 0 */,
				59		/* assert |NavigatingArgCS::prefix| == 0 */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				36		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingBarArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[43] = createSerializationRule("NavigatingBarArgCS-0", 46,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				55		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				164		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				166		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				91		/* assign V0 = |NavigatingArgCS::ownedType| */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				30		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				108		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				70		/* NavigatingArgCS::prefix='|' || soft-space value soft-space */,
				35		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				120		/* V00*5-steps || value */,
				87		/* ':' || soft-space value soft-space */,
				60		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				125		/* V01*2-steps || value */,
				89		/* '=' || soft-space value soft-space */,
				25		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(7/*'|'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[44] = createSerializationRule("NavigatingCommaArgCS-0", 47,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				163		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				164		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				166		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				108		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				89		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				27		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				28		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				69		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				35		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				87		/* ':' || soft-space value soft-space */,
				60		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				116		/* V00*2-steps || value */,
				109		/* 'with' || value */,
				9		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				125		/* V01*2-steps || value */,
				89		/* '=' || soft-space value soft-space */,
				25		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(3/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-1(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[45] = createSerializationRule("NavigatingCommaArgCS-1", 47,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				163		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				164		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				166		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				25		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				107		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				91		/* assign V0 = |NavigatingArgCS::ownedType| */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				28		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				69		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				35		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				116		/* V00*2-steps || value */,
				87		/* ':' || soft-space value soft-space */,
				60		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				125		/* V01*2-steps || value */,
				109		/* 'with' || value */,
				9		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				101		/* 'in' || soft-space value soft-space */,
				25		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(3/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-2(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[46] = createSerializationRule("NavigatingCommaArgCS-2", 47,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				58		/* assert |NavigatingArgCS::ownedType| == 0 */,
				163		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				164		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				90		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				24		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				28		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				69		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				35		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				109		/* 'with' || value */,
				9		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				116		/* V00*2-steps || value */,
				89		/* '=' || soft-space value soft-space */,
				25		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(3/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-3(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS }
		serializationRules[47] = createSerializationRule("NavigatingCommaArgCS-3", 47,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				55		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				56		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				58		/* assert |NavigatingArgCS::ownedType| == 0 */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				28		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				69		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				35		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingSemiArgCS-0(essentialoclcs::NavigatingArgCS): { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[48] = createSerializationRule("NavigatingSemiArgCS-0", 48,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				55		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				164		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				165		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				166		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				91		/* assign V0 = |NavigatingArgCS::ownedType| */,
				26		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				29		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				108		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				68		/* NavigatingArgCS::prefix=';' || no-space value soft-new-line */,
				35		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				120		/* V00*5-steps || value */,
				87		/* ':' || soft-space value soft-space */,
				60		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				125		/* V01*2-steps || value */,
				89		/* '=' || soft-space value soft-space */,
				25		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(2/*';'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/* NavigatingArgExpCS */,
					(45/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NestedExpCS-0(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[49] = createSerializationRule("NestedExpCS-0", 50,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				167		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				31		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				79		/* '(' || value no-space */,
				19		/* NestedExpCS::ownedExpression=ExpCS || value */,
				81		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NullLiteralExpCS-0(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[50] = createSerializationRule("NullLiteralExpCS-0", 52,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				104		/* 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::NumberLiteralExpCS-0(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[51] = createSerializationRule("NumberLiteralExpCS-0", 53,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				32		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			),
			createSerializationSteps(
				76		/* NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::PatternExpCS-0(essentialoclcs::PatternExpCS): { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS }
		serializationRules[52] = createSerializationRule("PatternExpCS-0", 55,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				171		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : TypeExpCS */,
				36		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				92		/* assign V0 = |PatternExpCS::patternVariableName| */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				113		/* V00*1-steps || value */,
				67		/* PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space */,
				87		/* ':' || soft-space value soft-space */,
				48		/* PatternExpCS::ownedPatternType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedLetExpCS-1(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		serializationRules[53] = createSerializationRule("PrefixedLetExpCS-1", 56,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				170		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : LetExpCS|PrefixedLetExpCS */,
				33		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				23		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				135		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				143		/* OperatorExpCS::ownedRight=PrefixedLetExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 19/* PrefixedLetExpCS */,
					(56/*PrefixedLetExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS-15(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[54] = createSerializationRule("PrefixedPrimaryExpCS-15", 57,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				169		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				33		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				23		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				135		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				144		/* OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 21/* PrefixedPrimaryExpCS */,
					(57/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrimitiveTypeCS-0(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		serializationRules[55] = createSerializationRule("PrimitiveTypeCS-0", 60,
			createSerializationMatchSteps(
				66		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				37		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				5		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::RoundBracketedClauseCS-0(essentialoclcs::RoundBracketedClauseCS): { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" }
		serializationRules[56] = createSerializationRule("RoundBracketedClauseCS-0", 62,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				80		/* '(' || no-space value no-space */,
				118		/* V00*3-steps || value */,
				137		/* RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value */,
				124		/* V01*1-steps || value */,
				138		/* RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value */,
				81		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 13/* NavigatingArgCS,NavigatingBarArgCS,NavigatingCommaArgCS,NavigatingSemiArgCS */,
					(44/*NavigatingArgCS*/ << 4) | 1 /*[?]*/,
					(46/*NavigatingBarArgCS*/ << 4) | 2 /*[*]*/,
					(47/*NavigatingCommaArgCS*/ << 4) | 2 /*[*]*/,
					(48/*NavigatingSemiArgCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::SelfExpCS-0(essentialoclcs::SelfExpCS): "self"
		serializationRules[57] = createSerializationRule("SelfExpCS-0", 66,
			createSerializationMatchSteps(
				61		/* assert |SelfExpCS::name| == 0 */,
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				106		/* 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::ShadowPartCS-0(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[58] = createSerializationRule("ShadowPartCS-0", 67,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				172		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				38		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				39		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				71		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
				89		/* '=' || soft-space value soft-space */,
				26		/* ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 18/* ExpCS,PatternExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/,
					(55/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, -1
				)
			});
		// EssentialOCL::ShadowPartCS-1(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
		serializationRules[59] = createSerializationRule("ShadowPartCS-1", 67,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				62		/* assert |ShadowPartCS::referredProperty| == 0 */,
				173		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : StringLiteralExpCS */,
				38		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			),
			createSerializationSteps(
				27		/* ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 25/* StringLiteralExpCS */,
					(71/*StringLiteralExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SimplePathNameCS-0(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[60] = createSerializationRule("SimplePathNameCS-0", 68,
			createSerializationMatchSteps(
				118		/* check-rule basecs::PathNameCS.ownedPathElements : FirstPathElementCS */,
				35		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			),
			createSerializationSteps(
				139		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 6/* FirstPathElementCS */,
					(21/*FirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SquareBracketedClauseCS-0(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[61] = createSerializationRule("SquareBracketedClauseCS-0", 69,
			createSerializationMatchSteps(
				174		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				75		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			),
			createSerializationSteps(
				93		/* '[' || no-space value no-space */,
				53		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				117		/* V00*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				53		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				94		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::StringLiteralExpCS-0(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[62] = createSerializationRule("StringLiteralExpCS-0", 71,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				93		/* assign V0 = |StringLiteralExpCS::segments| */
			),
			createSerializationSteps(
				115		/* V00*1-steps || value */,
				73		/* StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, false, GrammarCardinality.ONE_OR_MORE)
			});
		// EssentialOCL::TupleLiteralExpCS-0(essentialoclcs::TupleLiteralExpCS): { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" }
		serializationRules[63] = createSerializationRule("TupleLiteralExpCS-0", 75,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				175		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS */,
				78		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				92		/* 'Tuple' || soft-space value soft-space */,
				110		/* '{' || soft-space value push soft-new-line */,
				42		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				117		/* V00*2-steps || value */,
				84		/* ',' || no-space value soft-new-line */,
				42		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				112		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 28/* TupleLiteralPartCS */,
					(76/*TupleLiteralPartCS*/ << 4) | 3 /*[+]*/
				)
			});
	}
	private void initSerializationRules1() {
		// EssentialOCL::TupleLiteralPartCS-0(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[64] = createSerializationRule("TupleLiteralPartCS-0", 76,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				180		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				181		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				46		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				97		/* assign V0 = |VariableCS::ownedType| */,
				23		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				136		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				116		/* V00*2-steps || value */,
				87		/* ':' || soft-space value soft-space */,
				63		/* VariableCS::ownedType=TypeExpCS || value */,
				89		/* '=' || soft-space value soft-space */,
				28		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TuplePartCS-0(basecs::TuplePartCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[65] = createSerializationRule("TuplePartCS-0", 77,
			createSerializationMatchSteps(
				64		/* assert |TypedElementCS::isOptional| == 0 */,
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				65		/* assert |TypedElementCS::qualifiers| == 0 */,
				126		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				44		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				23		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				133		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				136		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				87		/* ':' || soft-space value soft-space */,
				62		/* TypedElementCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TupleTypeCS-0(basecs::TupleTypeCS): { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] }
		serializationRules[66] = createSerializationRule("TupleTypeCS-0", 78,
			createSerializationMatchSteps(
				66		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				124		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				41		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				79		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				103		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				113		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				6		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				122		/* V00*7-steps || value */,
				80		/* '(' || no-space value no-space */,
				128		/* V01*4-steps || value */,
				43		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				130		/* V02*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				43		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				81		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(5/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 29/* TuplePartCS */,
					(77/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeExpCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[67] = createSerializationRule("TypeExpCS-0", 79,
			createSerializationMatchSteps(
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				95		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				37		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				5		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				113		/* V00*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[68] = createSerializationRule("TypeExpCS-1", 79,
			createSerializationMatchSteps(
				142		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				143		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				115		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				84		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				106		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				3		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				120		/* V00*5-steps || value */,
				80		/* '(' || no-space value no-space */,
				58		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				123		/* V01*1-steps || value */,
				10		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				81		/* ')' || no-space value */,
				129		/* V02*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 31/* TypeExpWithoutMultiplicityCS */,
					(80/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[69] = createSerializationRule("TypeExpCS-2", 79,
			createSerializationMatchSteps(
				161		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				162		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				111		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				87		/* assign V0 = |MapTypeCS::ownedValueType| */,
				19		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				18		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				4		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				120		/* V00*5-steps || value */,
				80		/* '(' || no-space value no-space */,
				30		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				85		/* ',' || no-space value soft-space */,
				65		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				81		/* ')' || no-space value */,
				123		/* V01*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(4/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-3(essentialoclcs::TypeNameExpCS): { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[70] = createSerializationRule("TypeExpCS-3", 79,
			createSerializationMatchSteps(
				177		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				178		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				179		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				115		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				94		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				43		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				110		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				45		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				120		/* V00*5-steps || value */,
				14		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				127		/* V01*3-steps || value */,
				110		/* '{' || soft-space value push soft-new-line */,
				47		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				112		/* '}' || pop soft-new-line value soft-new-line */,
				129		/* V02*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 3/* CurlyBracketedClauseCS */,
					(9/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 16/* PathNameCS */,
					(54/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-4(essentialoclcs::CollectionPatternCS): { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[71] = createSerializationRule("TypeExpCS-4", 79,
			createSerializationMatchSteps(
				48		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				140		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				141		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				115		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				83		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				100		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				57		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				110		/* '{' || soft-space value push soft-new-line */,
				121		/* V00*6-steps || value */,
				39		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				126		/* V01*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				39		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				83		/* '++' || soft-space value soft-space */,
				72		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				112		/* '}' || pop soft-new-line value soft-new-line */,
				129		/* V02*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 17/* PatternExpCS */,
					(55/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/* CollectionTypeCS */,
					(7/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeExpCS-5(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[72] = createSerializationRule("TypeExpCS-5", 79,
			createSerializationMatchSteps(
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				124		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				117		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				41		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				79		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				103		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				113		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				6		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				122		/* V00*7-steps || value */,
				80		/* '(' || no-space value no-space */,
				128		/* V01*4-steps || value */,
				43		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				130		/* V02*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				43		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				81		/* ')' || no-space value */,
				131		/* V03*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(5/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 29/* TuplePartCS */,
					(77/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeLiteralExpCS-0(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[73] = createSerializationRule("TypeLiteralExpCS-0", 82,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				63		/* assert |TypeLiteralExpCS::ownedPathName| == 0 */,
				176		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS */,
				42		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				61		/* TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 33/* TypeLiteralWithMultiplicityCS */,
					(83/*TypeLiteralWithMultiplicityCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[74] = createSerializationRule("TypeLiteralWithMultiplicityCS-0", 83,
			createSerializationMatchSteps(
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				95		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				37		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				5		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				113		/* V00*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[75] = createSerializationRule("TypeLiteralWithMultiplicityCS-1", 83,
			createSerializationMatchSteps(
				142		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				143		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				115		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				84		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				106		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				3		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				120		/* V00*5-steps || value */,
				80		/* '(' || no-space value no-space */,
				58		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				123		/* V01*1-steps || value */,
				10		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				81		/* ')' || no-space value */,
				129		/* V02*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 31/* TypeExpWithoutMultiplicityCS */,
					(80/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[76] = createSerializationRule("TypeLiteralWithMultiplicityCS-2", 83,
			createSerializationMatchSteps(
				161		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				162		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				111		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				87		/* assign V0 = |MapTypeCS::ownedValueType| */,
				19		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				18		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				4		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				120		/* V00*5-steps || value */,
				80		/* '(' || no-space value no-space */,
				30		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				85		/* ',' || no-space value soft-space */,
				65		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				81		/* ')' || no-space value */,
				123		/* V01*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(4/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 30/* TypeExpCS */,
					(79/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-3(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[77] = createSerializationRule("TypeLiteralWithMultiplicityCS-3", 83,
			createSerializationMatchSteps(
				127		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				124		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				117		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				41		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				79		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				103		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				113		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				6		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				122		/* V00*7-steps || value */,
				80		/* '(' || no-space value no-space */,
				128		/* V01*4-steps || value */,
				43		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				130		/* V02*2-steps || value */,
				85		/* ',' || no-space value soft-space */,
				43		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				81		/* ')' || no-space value */,
				131		/* V03*1-steps || value */,
				34		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(5/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/* MultiplicityCS */,
					(40/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 29/* TuplePartCS */,
					(77/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeNameExpCS-0(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] }
		serializationRules[78] = createSerializationRule("TypeNameExpCS-0", 84,
			createSerializationMatchSteps(
				66		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				177		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				178		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				179		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				94		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				43		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				110		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				45		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				120		/* V00*5-steps || value */,
				14		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				127		/* V01*3-steps || value */,
				110		/* '{' || soft-space value push soft-new-line */,
				47		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				112		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 3/* CurlyBracketedClauseCS */,
					(9/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 16/* PathNameCS */,
					(54/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 5/* ExpCS */,
					(20/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::URIFirstPathElementCS-0(basecs::PathElementWithURICS): referredElement=URI
		serializationRules[79] = createSerializationRule("URIFirstPathElementCS-0", 91,
			createSerializationMatchSteps(
				34		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				145		/* PathElementCS::referredElement=URI || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIFirstPathElementCS-1(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[80] = createSerializationRule("URIFirstPathElementCS-1", 91,
			createSerializationMatchSteps(
				34		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				147		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[81] = createSerializationRule("URIPathNameCS-0", 92,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				141		/* PathNameCS::ownedPathElements+=URIFirstPathElementCS || value */,
				117		/* V00*2-steps || value */,
				88		/* '::' || no-space value no-space */,
				140		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 39/* NextPathElementCS,URIFirstPathElementCS */,
					(51/*NextPathElementCS*/ << 4) | 2 /*[*]*/,
					(91/*URIFirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS-0(essentialoclcs::UnlimitedNaturalLiteralExpCS): "*"
		serializationRules[82] = createSerializationRule("UnlimitedNaturalLiteralExpCS-0", 94,
			createSerializationMatchSteps(
				52		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				82		/* '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */
		};
		serializationSegments[2] = new @NonNull SerializationSegment @NonNull [] {
			new CustomSerializationSegment(BaseCommentSegmentSupport.class) /* org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport */,
			SerializationSegment.VALUE /* value */
		};
		serializationSegments[3] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.VALUE /* value */,
			SerializationSegment.NO_SPACE /* no-space */
		};
		serializationSegments[4] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.NO_SPACE /* no-space */
		};
		serializationSegments[5] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[6] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[7] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */
		};
		serializationSegments[8] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[9] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[10] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[11] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[12] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[13] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[14] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[15] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[16] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[17] = new @NonNull SerializationSegment @NonNull [] {
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
		// 0: MultiplicityCS::isNullFree?='|1' || no-space value no-space
		serializationSteps[0] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 8 /* '|1' */, 4);
		// 1: AbstractNameExpCS::isPre?='@' || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 3 /* '@' */, 8);
		// 2: MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space
		serializationSteps[2] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 29 /*LOWER*/, 8);
		// 3: CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space
		serializationSteps[3] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 8 /*CollectionTypeIdentifier*/, 8);
		// 4: MapTypeCS::name='Map' || soft-space value soft-space
		serializationSteps[4] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 4 /* 'Map' */, 8);
		// 5: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space
		serializationSteps[5] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 61 /*PrimitiveTypeIdentifier*/, 8);
		// 6: TupleTypeCS::name='Tuple' || soft-space value soft-space
		serializationSteps[6] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 5 /* 'Tuple' */, 8);
		// 7: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[7] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 86 /*TypeRefCS*/, 2);
		// 8: TypedTypeRefCS::ownedBinding=TemplateBindingCS || value
		serializationSteps[8] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 72 /*TemplateBindingCS*/, 0);
		// 9: NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value
		serializationSteps[9] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3 /*CoIteratorVariableCS*/, 0);
		// 10: CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value
		serializationSteps[10] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 40 /*MultiplicityCS*/, 0);
		// 11: IfExpCS::ownedCondition=ExpCS|PatternExpCS || value
		serializationSteps[11] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, -1, new int[] { 20/*ExpCS*/,55/*PatternExpCS*/}, 0);
		// 12: IfThenExpCS::ownedCondition=ExpCS || value
		serializationSteps[12] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 20 /*ExpCS*/, 0);
		// 13: AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[13] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 9 /*CurlyBracketedClauseCS*/, 0);
		// 14: TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[14] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 9 /*CurlyBracketedClauseCS*/, 0);
		// 15: IfExpCS::ownedElseExpression=ExpCS || value
		serializationSteps[15] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 20 /*ExpCS*/, 0);
		// 16: CollectionLiteralPartCS::ownedExpression=ExpCS || value
		serializationSteps[16] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 20 /*ExpCS*/, 0);
		// 17: CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[17] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 55 /*PatternExpCS*/, 2);
		// 18: ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[18] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 20 /*ExpCS*/, 2);
		// 19: NestedExpCS::ownedExpression=ExpCS || value
		serializationSteps[19] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 20 /*ExpCS*/, 0);
		// 20: LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value
		serializationSteps[20] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 20 /*ExpCS*/, 0);
		// 21: TypeParameterCS::ownedExtends+=TypedRefCS || value
		serializationSteps[21] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 87 /*TypedRefCS*/, 0);
		// 22: WildcardTypeRefCS::ownedExtends=TypedRefCS || value
		serializationSteps[22] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 87 /*TypedRefCS*/, 0);
		// 23: IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 13 /*ElseIfThenExpCS*/, 0);
		// 24: LetExpCS::ownedInExpression=ExpCS || value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 20 /*ExpCS*/, 0);
		// 25: NavigatingArgCS::ownedInitExpression=ExpCS || value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 20 /*ExpCS*/, 0);
		// 26: ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value
		serializationSteps[26] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new int[] { 20/*ExpCS*/,55/*PatternExpCS*/}, 0);
		// 27: ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[27] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 71 /*StringLiteralExpCS*/, 2);
		// 28: VariableCS::ownedInitExpression=ExpCS || value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 20 /*ExpCS*/, 0);
		// 29: MapLiteralPartCS::ownedKey=ExpCS || value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 20 /*ExpCS*/, 0);
		// 30: MapTypeCS::ownedKeyType=TypeExpCS || value
		serializationSteps[30] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 79 /*TypeExpCS*/, 0);
		// 31: CollectionLiteralPartCS::ownedLastExpression=ExpCS || value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 20 /*ExpCS*/, 0);
		// 32: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 57 /*PrefixedPrimaryExpCS*/, 0);
		// 33: TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 40 /*MultiplicityCS*/, 0);
		// 34: TypedRefCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 40 /*MultiplicityCS*/, 0);
		// 35: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value
		serializationSteps[35] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 45 /*NavigatingArgExpCS*/, 0);
		// 36: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[36] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 45 /*NavigatingArgExpCS*/, 2);
		// 37: TemplateSignatureCS::ownedParameters+=TypeParameterCS || value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 85 /*TypeParameterCS*/, 0);
		// 38: CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value
		serializationSteps[38] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 5 /*CollectionLiteralPartCS*/, 0);
		// 39: CollectionPatternCS::ownedParts+=PatternExpCS || value
		serializationSteps[39] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 55 /*PatternExpCS*/, 0);
		// 40: CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 67 /*ShadowPartCS*/, 0);
		// 41: MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 36 /*MapLiteralPartCS*/, 0);
		// 42: TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 76 /*TupleLiteralPartCS*/, 0);
		// 43: TupleTypeCS::ownedParts+=TuplePartCS || value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 77 /*TuplePartCS*/, 0);
		// 44: AbstractNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 54 /*PathNameCS*/, 0);
		// 45: TypeNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 54 /*PathNameCS*/, 0);
		// 46: TypedTypeRefCS::ownedPathName=PathNameCS || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 54 /*PathNameCS*/, 0);
		// 47: TypeNameExpCS::ownedPatternGuard=ExpCS || value
		serializationSteps[47] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 20 /*ExpCS*/, 0);
		// 48: PatternExpCS::ownedPatternType=TypeExpCS || value
		serializationSteps[48] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 79 /*TypeExpCS*/, 0);
		// 49: AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 62 /*RoundBracketedClauseCS*/, 0);
		// 50: LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[50] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 62 /*RoundBracketedClauseCS*/, 0);
		// 51: AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value
		serializationSteps[51] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 69 /*SquareBracketedClauseCS*/, 0);
		// 52: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value
		serializationSteps[52] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 73 /*TemplateParameterSubstitutionCS*/, 0);
		// 53: SquareBracketedClauseCS::ownedTerms+=ExpCS || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 20 /*ExpCS*/, 0);
		// 54: IfExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 20 /*ExpCS*/, 0);
		// 55: IfThenExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 20 /*ExpCS*/, 0);
		// 56: CollectionLiteralExpCS::ownedType=CollectionTypeCS || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 7 /*CollectionTypeCS*/, 0);
		// 57: CollectionPatternCS::ownedType=CollectionTypeCS || value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 7 /*CollectionTypeCS*/, 0);
		// 58: CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 80 /*TypeExpWithoutMultiplicityCS*/, 0);
		// 59: MapLiteralExpCS::ownedType=MapTypeCS || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 37 /*MapTypeCS*/, 0);
		// 60: NavigatingArgCS::ownedType=TypeExpCS || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 79 /*TypeExpCS*/, 0);
		// 61: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 83 /*TypeLiteralWithMultiplicityCS*/, 2);
		// 62: TypedElementCS::ownedType=TypeExpCS || value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 79 /*TypeExpCS*/, 0);
		// 63: VariableCS::ownedType=TypeExpCS || value
		serializationSteps[63] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 79 /*TypeExpCS*/, 0);
		// 64: MapLiteralPartCS::ownedValue=ExpCS || value
		serializationSteps[64] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 20 /*ExpCS*/, 0);
		// 65: MapTypeCS::ownedValueType=TypeExpCS || value
		serializationSteps[65] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 79 /*TypeExpCS*/, 0);
		// 66: LetExpCS::ownedVariables+=LetVariableCS || value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 32 /*LetVariableCS*/, 0);
		// 67: PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space
		serializationSteps[67] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 97 /*UnrestrictedName*/, 8);
		// 68: NavigatingArgCS::prefix=';' || no-space value soft-new-line
		serializationSteps[68] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 2 /* ';' */, 5);
		// 69: NavigatingArgCS::prefix=',' || no-space value soft-space
		serializationSteps[69] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */, 6);
		// 70: NavigatingArgCS::prefix='|' || soft-space value soft-space
		serializationSteps[70] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 7 /* '|' */, 8);
		// 71: ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[71] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 97, 8);
		// 72: CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space
		serializationSteps[72] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 24 /*Identifier*/, 8);
		// 73: StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[73] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 70 /*StringLiteral*/, 2);
		// 74: MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[74] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 8);
		// 75: BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[75] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 6 /* 'false|true' */, 2);
		// 76: NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[76] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 42 /*NUMBER_LITERAL*/, 2);
		// 77: MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space
		serializationSteps[77] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 89 /*UPPER*/, 8);
		// 78: '&&' || soft-space value soft-space
		serializationSteps[78] = createSerializationStepKeyword("&&", 8);
		// 79: '(' || value no-space
		serializationSteps[79] = createSerializationStepKeyword("(", 3);
		// 80: '(' || no-space value no-space
		serializationSteps[80] = createSerializationStepKeyword("(", 4);
		// 81: ')' || no-space value
		serializationSteps[81] = createSerializationStepKeyword(")", 1);
		// 82: '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[82] = createSerializationStepKeyword("*", 2);
		// 83: '++' || soft-space value soft-space
		serializationSteps[83] = createSerializationStepKeyword("++", 8);
		// 84: ',' || no-space value soft-new-line
		serializationSteps[84] = createSerializationStepKeyword(",", 5);
		// 85: ',' || no-space value soft-space
		serializationSteps[85] = createSerializationStepKeyword(",", 6);
		// 86: '..' || no-space value no-space
		serializationSteps[86] = createSerializationStepKeyword("..", 4);
		// 87: ':' || soft-space value soft-space
		serializationSteps[87] = createSerializationStepKeyword(":", 8);
		// 88: '::' || no-space value no-space
		serializationSteps[88] = createSerializationStepKeyword("::", 4);
		// 89: '=' || soft-space value soft-space
		serializationSteps[89] = createSerializationStepKeyword("=", 8);
		// 90: '?' || soft-space value soft-space
		serializationSteps[90] = createSerializationStepKeyword("?", 8);
		// 91: 'Lambda' || soft-space value soft-space
		serializationSteps[91] = createSerializationStepKeyword("Lambda", 8);
		// 92: 'Tuple' || soft-space value soft-space
		serializationSteps[92] = createSerializationStepKeyword("Tuple", 8);
		// 93: '[' || no-space value no-space
		serializationSteps[93] = createSerializationStepKeyword("[", 4);
		// 94: ']' || no-space value
		serializationSteps[94] = createSerializationStepKeyword("]", 1);
		// 95: 'else' || soft-new-line pop value push soft-space
		serializationSteps[95] = createSerializationStepKeyword("else", 16);
		// 96: 'elseif' || soft-new-line pop soft-space value push soft-space
		serializationSteps[96] = createSerializationStepKeyword("elseif", 17);
		// 97: 'endif' || soft-new-line pop value soft-space
		serializationSteps[97] = createSerializationStepKeyword("endif", 11);
		// 98: 'extends' || soft-space value soft-space
		serializationSteps[98] = createSerializationStepKeyword("extends", 8);
		// 99: 'if' || soft-new-line value push soft-space
		serializationSteps[99] = createSerializationStepKeyword("if", 12);
		// 100: 'in' || soft-space pop value soft-new-line
		serializationSteps[100] = createSerializationStepKeyword("in", 13);
		// 101: 'in' || soft-space value soft-space
		serializationSteps[101] = createSerializationStepKeyword("in", 8);
		// 102: 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[102] = createSerializationStepKeyword("invalid", 2);
		// 103: 'let' || soft-space value push
		serializationSteps[103] = createSerializationStepKeyword("let", 7);
		// 104: 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[104] = createSerializationStepKeyword("null", 2);
		// 105: 'pre' || soft-space value soft-space
		serializationSteps[105] = createSerializationStepKeyword("pre", 8);
		// 106: 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[106] = createSerializationStepKeyword("self", 2);
		// 107: 'then' || pop value push soft-space
		serializationSteps[107] = createSerializationStepKeyword("then", 10);
		// 108: 'then' || pop soft-space value push soft-space
		serializationSteps[108] = createSerializationStepKeyword("then", 15);
		// 109: 'with' || value
		serializationSteps[109] = createSerializationStepKeyword("with", 0);
		// 110: '{' || soft-space value push soft-new-line
		serializationSteps[110] = createSerializationStepKeyword("{", 14);
		// 111: '|?' || no-space value no-space
		serializationSteps[111] = createSerializationStepKeyword("|?", 4);
		// 112: '}' || pop soft-new-line value soft-new-line
		serializationSteps[112] = createSerializationStepKeyword("}", 9);
		// 113: V00*1-steps || value
		serializationSteps[113] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 1, 0);
		// 114: V00*1-steps || value
		serializationSteps[114] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 1, 0);
		// 115: V00*1-steps || value
		serializationSteps[115] = createSerializationStepSequence((0/*V0*/ << 4) | 3/*[+]*/, 1, 0);
		// 116: V00*2-steps || value
		serializationSteps[116] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 117: V00*2-steps || value
		serializationSteps[117] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 2, 0);
		// 118: V00*3-steps || value
		serializationSteps[118] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 3, 0);
		// 119: V00*4-steps || value
		serializationSteps[119] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 4, 0);
		// 120: V00*5-steps || value
		serializationSteps[120] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 5, 0);
		// 121: V00*6-steps || value
		serializationSteps[121] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 6, 0);
		// 122: V00*7-steps || value
		serializationSteps[122] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 7, 0);
		// 123: V01*1-steps || value
		serializationSteps[123] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 1, 0);
		// 124: V01*1-steps || value
		serializationSteps[124] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 1, 0);
		// 125: V01*2-steps || value
		serializationSteps[125] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 2, 0);
		// 126: V01*2-steps || value
		serializationSteps[126] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 2, 0);
		// 127: V01*3-steps || value
		serializationSteps[127] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 3, 0);
		// 128: V01*4-steps || value
		serializationSteps[128] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 4, 0);
		// 129: V02*1-steps || value
		serializationSteps[129] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 1, 0);
		// 130: V02*2-steps || value
		serializationSteps[130] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 2, 0);
		// 131: V03*1-steps || value
		serializationSteps[131] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 1, 0);
		// 132: V03*2-steps || value
		serializationSteps[132] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 2, 0);
		// 133: wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[133] = createSerializationStepWrapper(2);
		// 134: NamedElementCS::name=BinaryOperatorName || soft-space value soft-space
		serializationSteps[134] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 1 /*BinaryOperatorName*/, 8);
		// 135: NamedElementCS::name=UnaryOperatorName || soft-space value soft-space
		serializationSteps[135] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 93 /*UnaryOperatorName*/, 8);
		// 136: NamedElementCS::name=UnrestrictedName || soft-space value soft-space
		serializationSteps[136] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 97 /*UnrestrictedName*/, 8);
		// 137: RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value
		serializationSteps[137] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 44 /*NavigatingArgCS*/, 0);
		// 138: RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value
		serializationSteps[138] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new int[] { 47/*NavigatingCommaArgCS*/,48/*NavigatingSemiArgCS*/,46/*NavigatingBarArgCS*/}, 0);
		// 139: PathNameCS::ownedPathElements+=FirstPathElementCS || value
		serializationSteps[139] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 21 /*FirstPathElementCS*/, 0);
		// 140: PathNameCS::ownedPathElements+=NextPathElementCS || value
		serializationSteps[140] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 51 /*NextPathElementCS*/, 0);
		// 141: PathNameCS::ownedPathElements+=URIFirstPathElementCS || value
		serializationSteps[141] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 91 /*URIFirstPathElementCS*/, 0);
		// 142: OperatorExpCS::ownedRight=ExpCS || value
		serializationSteps[142] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 20 /*ExpCS*/, 0);
		// 143: OperatorExpCS::ownedRight=PrefixedLetExpCS || value
		serializationSteps[143] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 56 /*PrefixedLetExpCS*/, 0);
		// 144: OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value
		serializationSteps[144] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 57 /*PrefixedPrimaryExpCS*/, 0);
		// 145: PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[145] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 90, 8);
		// 146: PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[146] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 95, 8);
		// 147: PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[147] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 97, 8);
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSubstringSteps() {
		// 0: '->' : [no-space, value, no-space]
		substringSteps[0] = createSubstringStep("->", 4 /* no-space, value, no-space */);
		// 1: '.' : [no-space, value, no-space]
		substringSteps[1] = createSubstringStep(".", 4 /* no-space, value, no-space */);
		// 2: '?->' : [no-space, value, no-space]
		substringSteps[2] = createSubstringStep("?->", 4 /* no-space, value, no-space */);
		// 3: '?.' : [no-space, value, no-space]
		substringSteps[3] = createSubstringStep("?.", 4 /* no-space, value, no-space */);
		// 4: 'else' : [soft-new-line, pop, value, push, soft-space]
		substringSteps[4] = createSubstringStep("else", 16 /* soft-new-line, pop, value, push, soft-space */);
		// 5: 'endif' : [soft-new-line, pop, value, soft-space]
		substringSteps[5] = createSubstringStep("endif", 11 /* soft-new-line, pop, value, soft-space */);
		// 6: 'if' : [soft-new-line, value, push, soft-space]
		substringSteps[6] = createSubstringStep("if", 12 /* soft-new-line, value, push, soft-space */);
		// 7: 'in' : [soft-space, pop, value, soft-new-line]
		substringSteps[7] = createSubstringStep("in", 13 /* soft-space, pop, value, soft-new-line */);
		// 8: 'let' : [soft-space, value, push]
		substringSteps[8] = createSubstringStep("let", 7 /* soft-space, value, push */);
		// 9: 'then' : [pop, soft-space, value, push, soft-space]
		substringSteps[9] = createSubstringStep("then", 15 /* pop, soft-space, value, push, soft-space */);
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
//	import Grammar;
//	import GrammarProvider;
