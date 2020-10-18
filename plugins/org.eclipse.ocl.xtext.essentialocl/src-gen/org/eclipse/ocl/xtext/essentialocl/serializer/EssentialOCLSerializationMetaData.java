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
package org.eclipse.ocl.xtext.essentialocl.serializer;

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
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermVariable;
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
import org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;

public class EssentialOCLSerializationMetaData extends AbstractSerializationMetaData
{
	private boolean initialized = false;
	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[50];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[9];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[99];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[50];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[164];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[147];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[98];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [9] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[141];

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
		return 71;
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
		return 70;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 106;
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
			initSerializationRules0();
			initSerializationRules1();
			initGrammarRuleValues();
			initEClassValues();
		}
	}

	/**
	 * Initialize configuration for each EClass that may be serialized.
	 */
	private void initEClassValues() {
		eClassValues[0] = new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
			createSerializationRules(
				17 /* symbol={'false|true'} */,
				26 /* symbol={'false|true'} */
			), null
		);
		eClassValues[1] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
			createSerializationRules(
				19 /* { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				27 /* { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					1) /* CollectionLiteralPartCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					2) /* CollectionTypeCS */
			}
		);
		eClassValues[2] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
			createSerializationRules(
				20 /* ownedExpression=PatternExpCS */,
				21 /* { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					48) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[3] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
			createSerializationRules(
				22 /* { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */,
				84 /* { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					16) /* PatternExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					2) /* CollectionTypeCS */
			}
		);
		eClassValues[4] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
			createSerializationRules(
				23 /* { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				85 /* { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				91 /* { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[5] = new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
			createSerializationRules(
				51 /* ownedExpression=ExpCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[6] = new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				24 /* { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					22) /* ShadowPartCS */
			}
		);
		eClassValues[7] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
			createSerializationRules(
				28 /* "*" */,
				29 /* "invalid" */,
				30 /* "null" */,
				31 /* "self" */
			), null
		);
		eClassValues[8] = new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
			createSerializationRules(
				32 /* { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				43 /* { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					48) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					4) /* ElseIfThenExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[9] = new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
			createSerializationRules(
				25 /* { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[10] = new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
			createSerializationRules(
				33 /* { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					44) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[11] = new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
			createSerializationRules(
				44 /* "invalid" */
			), null
		);
		eClassValues[12] = new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
			createSerializationRules(
				34 /* { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				45 /* { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[13] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
			createSerializationRules(
				46 /* { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					7) /* LetVariableCS */
			}
		);
		eClassValues[14] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
			createSerializationRules(
				47 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					21) /* RoundBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[15] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
			createSerializationRules(
				35 /* { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				48 /* { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					8) /* MapLiteralPartCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					9) /* MapTypeCS */
			}
		);
		eClassValues[16] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
			createSerializationRules(
				49 /* { ownedKey=ExpCS "<-" ownedValue=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[17] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
			createSerializationRules(
				50 /* { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				86 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				92 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[18] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */,
				2 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" } */,
				3 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" } */,
				4 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree="|1"[?] "]" } */
			), null
		);
		eClassValues[19] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				5 /* { "[" stringBounds={'*|+|?'} "]" } */,
				6 /* { "[" stringBounds={'*|+|?'} "|?" "]" } */,
				7 /* { "[" stringBounds={'*|+|?'} isNullFree="|1"[?] "]" } */,
				8 /* stringBounds={'*|+|?'} */
			), null
		);
		eClassValues[20] = new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
			createSerializationRules(
				36 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				52 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					3) /* CurlyBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					15) /* PathNameCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					21) /* RoundBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					23) /* SquareBracketedClauseCS */
			}
		);
		eClassValues[21] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
			createSerializationRules(
				53 /* ownedNameExpression=NavigatingArgExpCS */,
				54 /* { ":" ownedType=TypeExpCS } */,
				56 /* { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				55 /* { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				57 /* { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */,
				58 /* { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */,
				62 /* { prefix="," ownedNameExpression=NavigatingArgExpCS } */,
				60 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				59 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				61 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */,
				63 /* { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					0) /* CoIteratorVariableCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					47) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[22] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				37 /* { "(" ownedExpression=ExpCS ")" } */,
				64 /* { "(" ownedExpression=ExpCS ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[23] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				65 /* "null" */
			), null
		);
		eClassValues[24] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				38 /* symbol=NUMBER_LITERAL */,
				66 /* symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[25] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* referredElement=UnrestrictedName */,
				9 /* referredElement=UnreservedName */,
				94 /* referredElement=UnrestrictedName */
			), null
		);
		eClassValues[26] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				95 /* referredElement=URI */
			), null
		);
		eClassValues[27] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */,
				75 /* ownedPathElements+=FirstPathElementCS */,
				96 /* { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					41) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
			}
		);
		eClassValues[28] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				67 /* { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[29] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				39 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				68 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				69 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[30] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
			createSerializationRules(
				70 /* name=PrimitiveTypeIdentifier */,
				82 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				89 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */
			}
		);
		eClassValues[31] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				71 /* { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					13) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[32] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				72 /* "self" */
			), null
		);
		eClassValues[33] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				73 /* ownedInitExpression=StringLiteralExpCS */,
				74 /* { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					48) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[34] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				76 /* { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[35] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				40 /* segments+=StringLiteral[+] */,
				77 /* segments+=StringLiteral[+] */
			), null
		);
		eClassValues[36] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					26) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[37] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					49) /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[38] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				13 /* { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					36) /* TypeParameterCS */
			}
		);
		eClassValues[39] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				41 /* { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				78 /* { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					27) /* TupleLiteralPartCS */
			}
		);
		eClassValues[40] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
			createSerializationRules(
				79 /* { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[41] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				80 /* { name=UnrestrictedName ":" ownedType=TypeExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[42] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
			createSerializationRules(
				81 /* { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				83 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				90 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					28) /* TuplePartCS */
			}
		);
		eClassValues[43] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				42 /* ownedType=TypeLiteralWithMultiplicityCS */,
				88 /* ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					33) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[44] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
			createSerializationRules(
				87 /* { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				93 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					3) /* CurlyBracketedClauseCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					10) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					15) /* PathNameCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[45] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				14 /* { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					39) /* TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[46] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				15 /* { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					25) /* TemplateBindingCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					15) /* PathNameCS */
			}
		);
		eClassValues[47] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				97 /* "*" */
			), null
		);
		eClassValues[48] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				18 /* { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[49] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				16 /* { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					39) /* TypedRefCS|TypedTypeRefCS */
			}
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// '*|+|?'
		enumerationValues[0] = new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		// ','
		enumerationValues[1] = new EnumerationValueSingle(",");
		// ';'
		enumerationValues[2] = new EnumerationValueSingle(";");
		// '@'
		enumerationValues[3] = new EnumerationValueSingle("@");
		// 'Map'
		enumerationValues[4] = new EnumerationValueSingle("Map");
		// 'Tuple'
		enumerationValues[5] = new EnumerationValueSingle("Tuple");
		// 'false|true'
		enumerationValues[6] = new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		// '|'
		enumerationValues[7] = new EnumerationValueSingle("|");
		// '|1'
		enumerationValues[8] = new EnumerationValueSingle("|1");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = new DataTypeRuleValue(1, "BinaryOperatorName");
		grammarRuleValues[2] = createParserRuleValue(2, "BooleanLiteralExpCS", -1,
			createSerializationRules(
				17	/* BooleanLiteralExpCS: symbol={'false|true'} */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* symbol="true" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* symbol="false" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "CoIteratorVariableCS", -1,
			createSerializationRules(
				18	/* CoIteratorVariableCS: { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[4] = createParserRuleValue(4, "CollectionLiteralExpCS", -1,
			createSerializationRules(
				19	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 8	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[5] = createParserRuleValue(5, "CollectionLiteralPartCS", -1,
			createSerializationRules(
				20	/* CollectionLiteralPartCS: ownedExpression=PatternExpCS */,
				21	/* CollectionLiteralPartCS: { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedLastExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=PatternExpCS : [value] | [value] */
		);
		grammarRuleValues[6] = createParserRuleValue(6, "CollectionPatternCS", -1,
			createSerializationRules(
				22	/* CollectionPatternCS: { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 8	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "++" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* restVariableName=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "CollectionTypeCS", -1,
			createSerializationRules(
				23	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* name=CollectionTypeIdentifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedCollectionMultiplicity=MultiplicityCS? : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[8] = new DataTypeRuleValue(8, "CollectionTypeIdentifier");
		grammarRuleValues[9] = createParserRuleValue(9, "CurlyBracketedClauseCS", -1,
			createSerializationRules(
				24	/* CurlyBracketedClauseCS: { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {CurlyBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 8	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[10] = new TerminalRuleValue(10, "DOUBLE_QUOTED_STRING");
		grammarRuleValues[11] = new TerminalRuleValue(11, "ESCAPED_CHARACTER");
		grammarRuleValues[12] = new TerminalRuleValue(12, "ESCAPED_ID");
		grammarRuleValues[13] = createParserRuleValue(13, "ElseIfThenExpCS", -1,
			createSerializationRules(
				25	/* ElseIfThenExpCS: { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "elseif" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=ExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "then" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[14] = new DataTypeRuleValue(14, "EssentialOCLInfixOperatorName");
		grammarRuleValues[15] = new DataTypeRuleValue(15, "EssentialOCLNavigationOperatorName");
		grammarRuleValues[16] = new DataTypeRuleValue(16, "EssentialOCLReservedKeyword");
		grammarRuleValues[17] = new DataTypeRuleValue(17, "EssentialOCLUnaryOperatorName");
		grammarRuleValues[18] = new DataTypeRuleValue(18, "EssentialOCLUnreservedName");
		grammarRuleValues[19] = new DataTypeRuleValue(19, "EssentialOCLUnrestrictedName");
		grammarRuleValues[20] = createParserRuleValue(20, "ExpCS", 46 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				26	/* ExpCS: symbol={'false|true'} */,
				27	/* ExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				28	/* ExpCS: "*" */,
				29	/* ExpCS: "invalid" */,
				30	/* ExpCS: "null" */,
				31	/* ExpCS: "self" */,
				32	/* ExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				33	/* ExpCS: { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */,
				34	/* ExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				35	/* ExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				36	/* ExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				37	/* ExpCS: { "(" ownedExpression=ExpCS ")" } */,
				38	/* ExpCS: symbol=NUMBER_LITERAL */,
				39	/* ExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				40	/* ExpCS: segments+=StringLiteral[+] */,
				41	/* ExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				42	/* ExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				46	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				68	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* {InfixExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* name=BinaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedLetExpCS : [value] | [value] */
		);
		grammarRuleValues[21] = createParserRuleValue(21, "FirstPathElementCS", -1,
			createSerializationRules(
				0	/* FirstPathElementCS: referredElement=UnrestrictedName */
			),
			(0 << 16) | 6	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[22] = new DataTypeRuleValue(22, "ID");
		grammarRuleValues[23] = new TerminalRuleValue(23, "INT");
		grammarRuleValues[24] = new DataTypeRuleValue(24, "Identifier");
		grammarRuleValues[25] = createParserRuleValue(25, "IfExpCS", -1,
			createSerializationRules(
				43	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "if" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 6	/* "then" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedIfThenExpressions+=ElseIfThenExpCS* : [value] | [value] */,
			(0 << 16) | 6	/* "else" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedElseExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "endif" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[26] = new DataTypeRuleValue(26, "InfixOperatorName");
		grammarRuleValues[27] = createParserRuleValue(27, "InvalidLiteralExpCS", -1,
			createSerializationRules(
				44	/* InvalidLiteralExpCS: "invalid" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {InvalidLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "invalid" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[28] = new TerminalRuleValue(28, "LETTER_CHARACTER");
		grammarRuleValues[29] = new DataTypeRuleValue(29, "LOWER");
		grammarRuleValues[30] = createParserRuleValue(30, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				45	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[31] = createParserRuleValue(31, "LetExpCS", -1,
			createSerializationRules(
				46	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "let" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 6	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[32] = createParserRuleValue(32, "LetVariableCS", -1,
			createSerializationRules(
				47	/* LetVariableCS: { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[33] = new TerminalRuleValue(33, "ML_COMMENT");
		grammarRuleValues[34] = new TerminalRuleValue(34, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[35] = createParserRuleValue(35, "MapLiteralExpCS", -1,
			createSerializationRules(
				48	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=MapTypeCS : [value] | [value] */,
			(0 << 16) | 8	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[36] = createParserRuleValue(36, "MapLiteralPartCS", -1,
			createSerializationRules(
				49	/* MapLiteralPartCS: { ownedKey=ExpCS "<-" ownedValue=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedKey=ExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValue=ExpCS : [value] | [value] */
		);
		grammarRuleValues[37] = createParserRuleValue(37, "MapTypeCS", -1,
			createSerializationRules(
				50	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* name="Map" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedKeyType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValueType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[38] = createParserRuleValue(38, "Model", -1,
			createSerializationRules(
				51	/* Model: ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[39] = createParserRuleValue(39, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS: { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 6	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[40] = createParserRuleValue(40, "MultiplicityCS", -1,
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
			(0 << 16) | 6	/* "|?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* isNullFree?="|1" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS: stringBounds={'*|+|?'} */
			),
			(0 << 16) | 6	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[42] = new DataTypeRuleValue(42, "NUMBER_LITERAL");
		grammarRuleValues[43] = createParserRuleValue(43, "NameExpCS", -1,
			createSerializationRules(
				52	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedSquareBracketedClauses+=SquareBracketedClauseCS* : [value] | [value] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* isPre?="@" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "pre" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[44] = createParserRuleValue(44, "NavigatingArgCS", -1,
			createSerializationRules(
				53	/* NavigatingArgCS: ownedNameExpression=NavigatingArgExpCS */,
				54	/* NavigatingArgCS: { ":" ownedType=TypeExpCS } */,
				55	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				56	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				57	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 6	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[45] = createParserRuleValue(45, "NavigatingArgExpCS", 47 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				26	/* ExpCS: symbol={'false|true'} */,
				27	/* ExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				28	/* ExpCS: "*" */,
				29	/* ExpCS: "invalid" */,
				30	/* ExpCS: "null" */,
				31	/* ExpCS: "self" */,
				32	/* ExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				33	/* ExpCS: { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */,
				34	/* ExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				35	/* ExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				36	/* ExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				37	/* ExpCS: { "(" ownedExpression=ExpCS ")" } */,
				38	/* ExpCS: symbol=NUMBER_LITERAL */,
				39	/* ExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				40	/* ExpCS: segments+=StringLiteral[+] */,
				41	/* ExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				42	/* ExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				46	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				68	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* ExpCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[46] = createParserRuleValue(46, "NavigatingBarArgCS", -1,
			createSerializationRules(
				58	/* NavigatingBarArgCS: { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* prefix="|" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[47] = createParserRuleValue(47, "NavigatingCommaArgCS", -1,
			createSerializationRules(
				62	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS } */,
				59	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				60	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				61	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* prefix="," : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 6	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[48] = createParserRuleValue(48, "NavigatingSemiArgCS", -1,
			createSerializationRules(
				63	/* NavigatingSemiArgCS: { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* prefix=";" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[49] = new DataTypeRuleValue(49, "NavigationOperatorName");
		grammarRuleValues[50] = createParserRuleValue(50, "NestedExpCS", -1,
			createSerializationRules(
				64	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[51] = createParserRuleValue(51, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS: referredElement=UnreservedName */
			),
			(0 << 16) | 6	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[52] = createParserRuleValue(52, "NullLiteralExpCS", -1,
			createSerializationRules(
				65	/* NullLiteralExpCS: "null" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {NullLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "null" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[53] = createParserRuleValue(53, "NumberLiteralExpCS", -1,
			createSerializationRules(
				66	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */
			),
			(0 << 16) | 2	/* symbol=NUMBER_LITERAL : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[54] = createParserRuleValue(54, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS: { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 3	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[55] = createParserRuleValue(55, "PatternExpCS", -1,
			createSerializationRules(
				67	/* PatternExpCS: { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* patternVariableName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPatternType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[56] = createParserRuleValue(56, "PrefixedLetExpCS", 19 /* LetExpCS|PrefixedLetExpCS */,
			createSerializationRules(
				46	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				68	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedLetExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LetExpCS : [value] | [value] */
		);
		grammarRuleValues[57] = createParserRuleValue(57, "PrefixedPrimaryExpCS", 44 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				17	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				19	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				43	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				44	/* InvalidLiteralExpCS: "invalid" */,
				45	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				48	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				52	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				64	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */,
				65	/* NullLiteralExpCS: "null" */,
				66	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				69	/* PrefixedPrimaryExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				72	/* SelfExpCS: "self" */,
				77	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				78	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				88	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				97	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrimaryExpCS : [value] | [value] */
		);
		grammarRuleValues[58] = createParserRuleValue(58, "PrimaryExpCS", 43 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				17	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				19	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				43	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				44	/* InvalidLiteralExpCS: "invalid" */,
				45	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				48	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				52	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				64	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */,
				65	/* NullLiteralExpCS: "null" */,
				66	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				72	/* SelfExpCS: "self" */,
				77	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				78	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				88	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				97	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
		grammarRuleValues[59] = createParserRuleValue(59, "PrimitiveLiteralExpCS", 42 /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				17	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				44	/* InvalidLiteralExpCS: "invalid" */,
				65	/* NullLiteralExpCS: "null" */,
				66	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				77	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				97	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* NumberLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* StringLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* BooleanLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* UnlimitedNaturalLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* InvalidLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* NullLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[60] = createParserRuleValue(60, "PrimitiveTypeCS", -1,
			createSerializationRules(
				70	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */
			),
			(0 << 16) | 6	/* name=PrimitiveTypeIdentifier : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[61] = new DataTypeRuleValue(61, "PrimitiveTypeIdentifier");
		grammarRuleValues[62] = createParserRuleValue(62, "RoundBracketedClauseCS", -1,
			createSerializationRules(
				71	/* RoundBracketedClauseCS: { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {RoundBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
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
				72	/* SelfExpCS: "self" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SelfExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "self" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[67] = createParserRuleValue(67, "ShadowPartCS", -1,
			createSerializationRules(
				73	/* ShadowPartCS: ownedInitExpression=StringLiteralExpCS */,
				74	/* ShadowPartCS: { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* referredProperty=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 0	/* ownedInitExpression=StringLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[68] = createParserRuleValue(68, "SimplePathNameCS", -1,
			createSerializationRules(
				75	/* SimplePathNameCS: ownedPathElements+=FirstPathElementCS */
			),
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */
		);
		grammarRuleValues[69] = createParserRuleValue(69, "SquareBracketedClauseCS", -1,
			createSerializationRules(
				76	/* SquareBracketedClauseCS: { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 3	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[70] = new DataTypeRuleValue(70, "StringLiteral");
		grammarRuleValues[71] = createParserRuleValue(71, "StringLiteralExpCS", -1,
			createSerializationRules(
				77	/* StringLiteralExpCS: segments+=StringLiteral[+] */
			),
			(0 << 16) | 2	/* segments+=StringLiteral+ : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[72] = createParserRuleValue(72, "TemplateBindingCS", -1,
			createSerializationRules(
				11	/* TemplateBindingCS: { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[73] = createParserRuleValue(73, "TemplateParameterSubstitutionCS", -1,
			createSerializationRules(
				12	/* TemplateParameterSubstitutionCS: ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[74] = createParserRuleValue(74, "TemplateSignatureCS", -1,
			createSerializationRules(
				13	/* TemplateSignatureCS: { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[75] = createParserRuleValue(75, "TupleLiteralExpCS", -1,
			createSerializationRules(
				78	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[76] = createParserRuleValue(76, "TupleLiteralPartCS", -1,
			createSerializationRules(
				79	/* TupleLiteralPartCS: { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[77] = createParserRuleValue(77, "TuplePartCS", -1,
			createSerializationRules(
				80	/* TuplePartCS: { name=UnrestrictedName ":" ownedType=TypeExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[78] = createParserRuleValue(78, "TupleTypeCS", -1,
			createSerializationRules(
				81	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* name="Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[79] = createParserRuleValue(79, "TypeExpCS", 35 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				82	/* TypeExpCS: { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				83	/* TypeExpCS: { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				84	/* TypeExpCS: { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] } */,
				85	/* TypeExpCS: { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				86	/* TypeExpCS: { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				87	/* TypeExpCS: { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[80] = createParserRuleValue(80, "TypeExpWithoutMultiplicityCS", 34 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				22	/* CollectionPatternCS: { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */,
				23	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				50	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				70	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				81	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				93	/* TypeNameExpCS: { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeNameExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionPatternCS : [value] | [value] */
		);
		grammarRuleValues[81] = createParserRuleValue(81, "TypeLiteralCS", 31 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */,
			createSerializationRules(
				23	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				50	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				70	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				81	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */
		);
		grammarRuleValues[82] = createParserRuleValue(82, "TypeLiteralExpCS", -1,
			createSerializationRules(
				88	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */
			),
			(0 << 16) | 2	/* ownedType=TypeLiteralWithMultiplicityCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[83] = createParserRuleValue(83, "TypeLiteralWithMultiplicityCS", 33 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */,
			createSerializationRules(
				89	/* TypeLiteralWithMultiplicityCS: { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				90	/* TypeLiteralWithMultiplicityCS: { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				91	/* TypeLiteralWithMultiplicityCS: { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				92	/* TypeLiteralWithMultiplicityCS: { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[84] = createParserRuleValue(84, "TypeNameExpCS", -1,
			createSerializationRules(
				93	/* TypeNameExpCS: { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 8	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedPatternGuard=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[85] = createParserRuleValue(85, "TypeParameterCS", -1,
			createSerializationRules(
				14	/* TypeParameterCS: { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "&&" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[86] = createParserRuleValue(86, "TypeRefCS", 49 /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				15	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */,
				16	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[87] = createParserRuleValue(87, "TypedRefCS", 39 /* TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				15	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[88] = createParserRuleValue(88, "TypedTypeRefCS", -1,
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
		grammarRuleValues[89] = new DataTypeRuleValue(89, "UPPER");
		grammarRuleValues[90] = new DataTypeRuleValue(90, "URI");
		grammarRuleValues[91] = createParserRuleValue(91, "URIFirstPathElementCS", -1,
			createSerializationRules(
				94	/* URIFirstPathElementCS: referredElement=UnrestrictedName */,
				95	/* URIFirstPathElementCS: referredElement=URI */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PathElementWithURICS} : [value] | [value] */,
			(0 << 16) | 6	/* referredElement=URI : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[92] = createParserRuleValue(92, "URIPathNameCS", -1,
			createSerializationRules(
				96	/* URIPathNameCS: { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=URIFirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 3	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[93] = new DataTypeRuleValue(93, "UnaryOperatorName");
		grammarRuleValues[94] = createParserRuleValue(94, "UnlimitedNaturalLiteralExpCS", -1,
			createSerializationRules(
				97	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {UnlimitedNaturalLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "*" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[95] = new DataTypeRuleValue(95, "UnreservedName");
		grammarRuleValues[96] = new DataTypeRuleValue(96, "UnrestrictedName");
		grammarRuleValues[97] = new TerminalRuleValue(97, "WS");
		grammarRuleValues[98] = createParserRuleValue(98, "WildcardTypeRefCS", -1,
			createSerializationRules(
				16	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WildcardTypeRefCS} : [value] | [value] */,
			(0 << 16) | 6	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends=TypedRefCS : [value] | [value] */
		);
	}

	/**
	 * Initialize bit vectors of useful grammar rule combinations.
	 */
	private void initGrammarRuleVectors() {
		// CoIteratorVariableCS
		grammarRuleVectors[0] = new GrammarRuleVector(0x8L);
		// CollectionLiteralPartCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x20L);
		// CollectionTypeCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x80L);
		// CurlyBracketedClauseCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x200L);
		// ElseIfThenExpCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x2000L);
		// ExpCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x100000L);
		// FirstPathElementCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x200000L);
		// LetVariableCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x100000000L);
		// MapLiteralPartCS
		grammarRuleVectors[8] = new GrammarRuleVector(0x1000000000L);
		// MapTypeCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x2000000000L);
		// MultiplicityCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x10000000000L);
		// NavigatingArgExpCS
		grammarRuleVectors[11] = new GrammarRuleVector(0x200000000000L);
		// NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[12] = new GrammarRuleVector(0x1c00000000000L);
		// NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[13] = new GrammarRuleVector(0x1d00000000000L);
		// FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[14] = new GrammarRuleVector(0x8000000200000L);
		// PathNameCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x40000000000000L);
		// PatternExpCS
		grammarRuleVectors[16] = new GrammarRuleVector(0x80000000000000L);
		// ExpCS|PatternExpCS
		grammarRuleVectors[17] = new GrammarRuleVector(0x80000000100000L);
		// PrefixedLetExpCS
		grammarRuleVectors[18] = new GrammarRuleVector(0x100000000000000L);
		// LetExpCS|PrefixedLetExpCS
		grammarRuleVectors[19] = new GrammarRuleVector(0x100000080000000L);
		// PrefixedPrimaryExpCS
		grammarRuleVectors[20] = new GrammarRuleVector(0x200000000000000L);
		// RoundBracketedClauseCS
		grammarRuleVectors[21] = new GrammarRuleVector(0x4000000000000000L);
		// ShadowPartCS
		grammarRuleVectors[22] = new GrammarRuleVector(0x0L,0x8L);
		// SquareBracketedClauseCS
		grammarRuleVectors[23] = new GrammarRuleVector(0x0L,0x20L);
		// StringLiteralExpCS
		grammarRuleVectors[24] = new GrammarRuleVector(0x0L,0x80L);
		// TemplateBindingCS
		grammarRuleVectors[25] = new GrammarRuleVector(0x0L,0x100L);
		// TemplateParameterSubstitutionCS
		grammarRuleVectors[26] = new GrammarRuleVector(0x0L,0x200L);
		// TupleLiteralPartCS
		grammarRuleVectors[27] = new GrammarRuleVector(0x0L,0x1000L);
		// TuplePartCS
		grammarRuleVectors[28] = new GrammarRuleVector(0x0L,0x2000L);
		// TypeExpCS
		grammarRuleVectors[29] = new GrammarRuleVector(0x0L,0x8000L);
		// TypeExpWithoutMultiplicityCS
		grammarRuleVectors[30] = new GrammarRuleVector(0x0L,0x10000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
		grammarRuleVectors[31] = new GrammarRuleVector(0x1000002000000080L,0x24000L);
		// TypeLiteralWithMultiplicityCS
		grammarRuleVectors[32] = new GrammarRuleVector(0x0L,0x80000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
		grammarRuleVectors[33] = new GrammarRuleVector(0x1000002000000080L,0xa4000L);
		// CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[34] = new GrammarRuleVector(0x10000020000000c0L,0x134000L);
		// CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[35] = new GrammarRuleVector(0x10000020000000c0L,0x13c000L);
		// TypeParameterCS
		grammarRuleVectors[36] = new GrammarRuleVector(0x0L,0x200000L);
		// TypeRefCS
		grammarRuleVectors[37] = new GrammarRuleVector(0x0L,0x400000L);
		// TypedRefCS
		grammarRuleVectors[38] = new GrammarRuleVector(0x0L,0x800000L);
		// TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[39] = new GrammarRuleVector(0x0L,0x1800000L);
		// NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[40] = new GrammarRuleVector(0x8000000000000L,0x8000000L);
		// FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[41] = new GrammarRuleVector(0x8000000200000L,0x8000000L);
		// BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[42] = new GrammarRuleVector(0x830000008000004L,0x40000080L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[43] = new GrammarRuleVector(0xc3408084a000014L,0x40040884L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[44] = new GrammarRuleVector(0xe3408084a000014L,0x40040884L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[45] = new GrammarRuleVector(0xf340808ca000014L,0x40040884L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[46] = new GrammarRuleVector(0xf340808ca100014L,0x40040884L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[47] = new GrammarRuleVector(0xf342808ca100014L,0x40040884L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[48] = new GrammarRuleVector(0xfb40808ca100014L,0x40040884L);
		// TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[49] = new GrammarRuleVector(0x0L,0x401c00000L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(83);
		// assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(84);
		// assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(87);
		// assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(88);
		// assert (|CollectionPatternCS::ownedType| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(90);
		// assert (|CollectionTypeCS::name| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(91);
		// assert (|ContextCS::ownedExpression| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(92);
		// assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(95);
		// assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(96);
		// assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(97);
		// assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(98);
		// assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(99);
		// assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(100);
		// assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(101);
		// assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(102);
		// assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(106);
		// assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(107);
		// assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(108);
		// assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(109);
		// assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(110);
		// assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(111);
		// assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(112);
		// assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(113);
		// assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(114);
		// assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(115);
		// assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(116);
		// assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(117);
		// assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(118);
		// assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(119);
		// assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(120);
		// assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(121);
		// assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(122);
		// assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(123);
		// assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(124);
		// assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(125);
		// assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(126);
		// assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(127);
		// assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(130);
		// assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(131);
		// assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(134);
		// assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(137);
		// assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(140);
		// assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(141);
		// assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(144);
		// assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(145);
		// assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(146);
		// assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[46] = createMatchStep_Assign(0, 86);
		// assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchSteps[47] = createMatchStep_Assign(0, 94);
		// assign V0 = (|LetExpCS::ownedVariables| - 1)
		serializationMatchSteps[48] = createMatchStep_Assign(0, 103);
		// assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[49] = createMatchStep_Assign(0, 105);
		// assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[50] = createMatchStep_Assign(0, 125);
		// assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
		serializationMatchSteps[51] = createMatchStep_Assign(0, 129);
		// assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[52] = createMatchStep_Assign(0, 132);
		// assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[53] = createMatchStep_Assign(0, 133);
		// assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[54] = createMatchStep_Assign(0, 135);
		// assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[55] = createMatchStep_Assign(0, 136);
		// assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[56] = createMatchStep_Assign(0, 139);
		// assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[57] = createMatchStep_Assign(0, 143);
		// assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchSteps[58] = createMatchStep_Assign(0, 7);
		// assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchSteps[59] = createMatchStep_Assign(0, 12);
		// assign V0 = |CollectionPatternCS::restVariableName|
		serializationMatchSteps[60] = createMatchStep_Assign(0, 15);
		// assign V0 = |CollectionTypeCS::ownedType|
		serializationMatchSteps[61] = createMatchStep_Assign(0, 18);
		// assign V0 = |IfExpCS::ownedIfThenExpressions|
		serializationMatchSteps[62] = createMatchStep_Assign(0, 23);
		// assign V0 = |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchSteps[63] = createMatchStep_Assign(0, 31);
		// assign V0 = |MapTypeCS::ownedValueType|
		serializationMatchSteps[64] = createMatchStep_Assign(0, 38);
		// assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[65] = createMatchStep_Assign(0, 40);
		// assign V0 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[66] = createMatchStep_Assign(0, 41);
		// assign V0 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[67] = createMatchStep_Assign(0, 44);
		// assign V0 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[68] = createMatchStep_Assign(0, 45);
		// assign V0 = |NavigatingArgCS::ownedType|
		serializationMatchSteps[69] = createMatchStep_Assign(0, 47);
		// assign V0 = |PatternExpCS::patternVariableName|
		serializationMatchSteps[70] = createMatchStep_Assign(0, 57);
		// assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[71] = createMatchStep_Assign(0, 63);
		// assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[72] = createMatchStep_Assign(0, 72);
		// assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[73] = createMatchStep_Assign(0, 77);
		// assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[74] = createMatchStep_Assign(0, 78);
		// assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[75] = createMatchStep_Assign(0, 81);
		// assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[76] = createMatchStep_Assign(0, 82);
		// assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[77] = createMatchStep_Assign(1, 85);
		// assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[78] = createMatchStep_Assign(1, 89);
		// assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[79] = createMatchStep_Assign(1, 93);
		// assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[80] = createMatchStep_Assign(1, 104);
		// assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
		serializationMatchSteps[81] = createMatchStep_Assign(1, 128);
		// assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[82] = createMatchStep_Assign(1, 139);
		// assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[83] = createMatchStep_Assign(1, 142);
		// assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[84] = createMatchStep_Assign(1, 6);
		// assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[85] = createMatchStep_Assign(1, 17);
		// assign V1 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[86] = createMatchStep_Assign(1, 41);
		// assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[87] = createMatchStep_Assign(1, 44);
		// assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[88] = createMatchStep_Assign(1, 45);
		// assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[89] = createMatchStep_Assign(1, 64);
		// assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[90] = createMatchStep_Assign(1, 74);
		// assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[91] = createMatchStep_Assign(1, 77);
		// assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[92] = createMatchStep_Assign(1, 81);
		// assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[93] = createMatchStep_Assign(2, 138);
		// assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[94] = createMatchStep_Assign(2, 4);
		// assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[95] = createMatchStep_Assign(2, 77);
		// assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[96] = createMatchStep_Assign(3, 3);
		// assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[97] = createMatchStep_Assign(3, 77);
		// check-rule basecs::PathNameCS.ownedPathElements : 21
		serializationMatchSteps[98] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 6/*FirstPathElementCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 21|51
		serializationMatchSteps[99] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 14/*FirstPathElementCS|NextPathElementCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 51|91
		serializationMatchSteps[100] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 40/*NextPathElementCS|URIFirstPathElementCS*/);
		// check-rule basecs::TemplateBindingCS.ownedMultiplicity : 40
		serializationMatchSteps[101] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 10/*MultiplicityCS*/);
		// check-rule basecs::TemplateBindingCS.ownedSubstitutions : 73
		serializationMatchSteps[102] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 26/*TemplateParameterSubstitutionCS*/);
		// check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 86
		serializationMatchSteps[103] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 37/*TypeRefCS*/);
		// check-rule basecs::TemplateSignatureCS.ownedParameters : 85
		serializationMatchSteps[104] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 36/*TypeParameterCS*/);
		// check-rule basecs::TupleTypeCS.ownedParts : 77
		serializationMatchSteps[105] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 28/*TuplePartCS*/);
		// check-rule basecs::TypeParameterCS.ownedExtends : 87
		serializationMatchSteps[106] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 38/*TypedRefCS*/);
		// check-rule basecs::TypedElementCS.ownedType : 79
		serializationMatchSteps[107] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 29/*TypeExpCS*/);
		// check-rule basecs::TypedRefCS.ownedMultiplicity : 40
		serializationMatchSteps[108] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 10/*MultiplicityCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedBinding : 72
		serializationMatchSteps[109] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 25/*TemplateBindingCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedPathName : 54
		serializationMatchSteps[110] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 15/*PathNameCS*/);
		// check-rule basecs::WildcardTypeRefCS.ownedExtends : 87
		serializationMatchSteps[111] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 38/*TypedRefCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 9
		serializationMatchSteps[112] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 3/*CurlyBracketedClauseCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 54
		serializationMatchSteps[113] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 15/*PathNameCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 62
		serializationMatchSteps[114] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 21/*RoundBracketedClauseCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 69
		serializationMatchSteps[115] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 23/*SquareBracketedClauseCS*/);
		// check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5
		serializationMatchSteps[116] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 1/*CollectionLiteralPartCS*/);
		// check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7
		serializationMatchSteps[117] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 20
		serializationMatchSteps[118] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 55
		serializationMatchSteps[119] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 16/*PatternExpCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 20
		serializationMatchSteps[120] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55
		serializationMatchSteps[121] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 16/*PatternExpCS*/);
		// check-rule essentialoclcs::CollectionPatternCS.ownedType : 7
		serializationMatchSteps[122] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40
		serializationMatchSteps[123] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 10/*MultiplicityCS*/);
		// check-rule essentialoclcs::CollectionTypeCS.ownedType : 80
		serializationMatchSteps[124] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 30/*TypeExpWithoutMultiplicityCS*/);
		// check-rule essentialoclcs::ContextCS.ownedExpression : 20
		serializationMatchSteps[125] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 67
		serializationMatchSteps[126] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 22/*ShadowPartCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedCondition : 20|55
		serializationMatchSteps[127] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 17/*ExpCS|PatternExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedElseExpression : 20
		serializationMatchSteps[128] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13
		serializationMatchSteps[129] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 4/*ElseIfThenExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedThenExpression : 20
		serializationMatchSteps[130] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::IfThenExpCS.ownedCondition : 20
		serializationMatchSteps[131] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 5/*ExpCS*/);
		// check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 20
		serializationMatchSteps[132] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::InfixExpCS.ownedLeft : 57
		serializationMatchSteps[133] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 20/*PrefixedPrimaryExpCS*/);
		// check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 20
		serializationMatchSteps[134] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 5/*ExpCS*/);
		// check-rule essentialoclcs::LetExpCS.ownedInExpression : 20
		serializationMatchSteps[135] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::LetExpCS.ownedVariables : 32
		serializationMatchSteps[136] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 7/*LetVariableCS*/);
		// check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 62
		serializationMatchSteps[137] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 21/*RoundBracketedClauseCS*/);
		// check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36
		serializationMatchSteps[138] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 8/*MapLiteralPartCS*/);
		// check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37
		serializationMatchSteps[139] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 9/*MapTypeCS*/);
		// check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 20
		serializationMatchSteps[140] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 5/*ExpCS*/);
		// check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 20
		serializationMatchSteps[141] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 5/*ExpCS*/);
		// check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79
		serializationMatchSteps[142] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 29/*TypeExpCS*/);
		// check-rule essentialoclcs::MapTypeCS.ownedValueType : 79
		serializationMatchSteps[143] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 29/*TypeExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3
		serializationMatchSteps[144] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/*CoIteratorVariableCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20
		serializationMatchSteps[145] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45
		serializationMatchSteps[146] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 11/*NavigatingArgExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedType : 79
		serializationMatchSteps[147] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 29/*TypeExpCS*/);
		// check-rule essentialoclcs::NestedExpCS.ownedExpression : 20
		serializationMatchSteps[148] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 20
		serializationMatchSteps[149] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 5/*ExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 56
		serializationMatchSteps[150] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 18/*PrefixedLetExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 57
		serializationMatchSteps[151] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 20/*PrefixedPrimaryExpCS*/);
		// check-rule essentialoclcs::PatternExpCS.ownedPatternType : 79
		serializationMatchSteps[152] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 29/*TypeExpCS*/);
		// check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 44|46|47|48
		serializationMatchSteps[153] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 13/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		// check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 20|55
		serializationMatchSteps[154] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 17/*ExpCS|PatternExpCS*/);
		// check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 71
		serializationMatchSteps[155] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 24/*StringLiteralExpCS*/);
		// check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 20
		serializationMatchSteps[156] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 5/*ExpCS*/);
		// check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76
		serializationMatchSteps[157] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 27/*TupleLiteralPartCS*/);
		// check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83
		serializationMatchSteps[158] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 32/*TypeLiteralWithMultiplicityCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9
		serializationMatchSteps[159] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 3/*CurlyBracketedClauseCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54
		serializationMatchSteps[160] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 15/*PathNameCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 20
		serializationMatchSteps[161] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 5/*ExpCS*/);
		// check-rule essentialoclcs::VariableCS.ownedInitExpression : 20
		serializationMatchSteps[162] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 5/*ExpCS*/);
		// check-rule essentialoclcs::VariableCS.ownedType : 79
		serializationMatchSteps[163] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 29/*TypeExpCS*/);
	}

	/**
	 * Initialize expression terms used during the matching process.
	 */
	private void initMatchTerms() {
		// 0
		serializationMatchTerms[0] = new SerializationMatchTermInteger(0);
		// 1
		serializationMatchTerms[1] = new SerializationMatchTermInteger(1);
		// V0
		serializationMatchTerms[2] = new SerializationMatchTermVariable(0);
		// |AbstractNameExpCS::isPre.'@'|
		serializationMatchTerms[3] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 3 /* '@' */);
		// |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[4] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// |AbstractNameExpCS::ownedPathName|
		serializationMatchTerms[5] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		// |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchTerms[6] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchTerms[7] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		// |BooleanLiteralExpCS::symbol.'false|true'|
		serializationMatchTerms[8] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 6 /* 'false|true' */);
		// |CollectionLiteralExpCS::ownedParts|
		serializationMatchTerms[9] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		// |CollectionLiteralExpCS::ownedType|
		serializationMatchTerms[10] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		// |CollectionLiteralPartCS::ownedExpression|
		serializationMatchTerms[11] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		// |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchTerms[12] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		// |CollectionPatternCS::ownedParts|
		serializationMatchTerms[13] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		// |CollectionPatternCS::ownedType|
		serializationMatchTerms[14] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		// |CollectionPatternCS::restVariableName|
		serializationMatchTerms[15] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		// |CollectionTypeCS::name|
		serializationMatchTerms[16] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		// |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchTerms[17] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		// |CollectionTypeCS::ownedType|
		serializationMatchTerms[18] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		// |ContextCS::ownedExpression|
		serializationMatchTerms[19] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		// |CurlyBracketedClauseCS::ownedParts|
		serializationMatchTerms[20] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		// |IfExpCS::ownedCondition|
		serializationMatchTerms[21] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		// |IfExpCS::ownedElseExpression|
		serializationMatchTerms[22] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		// |IfExpCS::ownedIfThenExpressions|
		serializationMatchTerms[23] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		// |IfExpCS::ownedThenExpression|
		serializationMatchTerms[24] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		// |IfThenExpCS::ownedCondition|
		serializationMatchTerms[25] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		// |IfThenExpCS::ownedThenExpression|
		serializationMatchTerms[26] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		// |InfixExpCS::ownedLeft|
		serializationMatchTerms[27] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		// |LambdaLiteralExpCS::ownedExpressionCS|
		serializationMatchTerms[28] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		// |LetExpCS::ownedInExpression|
		serializationMatchTerms[29] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		// |LetExpCS::ownedVariables|
		serializationMatchTerms[30] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		// |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchTerms[31] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// |MapLiteralExpCS::ownedParts|
		serializationMatchTerms[32] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		// |MapLiteralExpCS::ownedType|
		serializationMatchTerms[33] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		// |MapLiteralPartCS::ownedKey|
		serializationMatchTerms[34] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		// |MapLiteralPartCS::ownedValue|
		serializationMatchTerms[35] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		// |MapTypeCS::name.'Map'|
		serializationMatchTerms[36] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 4 /* 'Map' */);
		// |MapTypeCS::ownedKeyType|
		serializationMatchTerms[37] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		// |MapTypeCS::ownedValueType|
		serializationMatchTerms[38] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		// |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[39] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[40] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[41] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 8 /* '|1' */);
		// |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[42] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */);
		// |NamedElementCS::name|
		serializationMatchTerms[43] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// |NavigatingArgCS::ownedCoIterator|
		serializationMatchTerms[44] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		// |NavigatingArgCS::ownedInitExpression|
		serializationMatchTerms[45] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		// |NavigatingArgCS::ownedNameExpression|
		serializationMatchTerms[46] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		// |NavigatingArgCS::ownedType|
		serializationMatchTerms[47] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		// |NavigatingArgCS::prefix.','|
		serializationMatchTerms[48] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */);
		// |NavigatingArgCS::prefix.';'|
		serializationMatchTerms[49] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 2 /* ';' */);
		// |NavigatingArgCS::prefix.'|'|
		serializationMatchTerms[50] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 7 /* '|' */);
		// |NestedExpCS::ownedExpression|
		serializationMatchTerms[51] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		// |NumberLiteralExpCS::symbol|
		serializationMatchTerms[52] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		// |OperatorExpCS::ownedRight|
		serializationMatchTerms[53] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		// |PathElementCS::referredElement|
		serializationMatchTerms[54] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// |PathNameCS::ownedPathElements|
		serializationMatchTerms[55] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// |PatternExpCS::ownedPatternType|
		serializationMatchTerms[56] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		// |PatternExpCS::patternVariableName|
		serializationMatchTerms[57] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		// |PrimitiveTypeRefCS::name|
		serializationMatchTerms[58] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		// |RoundBracketedClauseCS::ownedArguments|
		serializationMatchTerms[59] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		// |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[60] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// |ShadowPartCS::referredProperty|
		serializationMatchTerms[61] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[62] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// |StringLiteralExpCS::segments|
		serializationMatchTerms[63] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[64] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[65] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[66] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[67] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[68] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[69] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 5 /* 'Tuple' */);
		// |TupleTypeCS::ownedParts|
		serializationMatchTerms[70] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[71] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[72] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[73] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[74] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// |TypeParameterCS::ownedExtends|
		serializationMatchTerms[75] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// |TypedElementCS::ownedType|
		serializationMatchTerms[76] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		// |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[77] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[78] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[79] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// |VariableCS::ownedInitExpression|
		serializationMatchTerms[80] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		// |VariableCS::ownedType|
		serializationMatchTerms[81] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		// |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[82] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// (|AbstractNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[83] = createSerializationMatchTermSubtract(5, 1);
		// (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
		serializationMatchTerms[84] = createSerializationMatchTermSubtract(8, 1);
		// (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[85] = createSerializationMatchTermSubtract(9, 1);
		// (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[86] = createSerializationMatchTermGreaterThan(9, 0);
		// (|CollectionLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[87] = createSerializationMatchTermSubtract(10, 1);
		// (|CollectionLiteralPartCS::ownedExpression| - 1)
		serializationMatchTerms[88] = createSerializationMatchTermSubtract(11, 1);
		// (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchTerms[89] = createSerializationMatchTermSubtract(13, 1);
		// (|CollectionPatternCS::ownedType| - 1)
		serializationMatchTerms[90] = createSerializationMatchTermSubtract(14, 1);
		// (|CollectionTypeCS::name| - 1)
		serializationMatchTerms[91] = createSerializationMatchTermSubtract(16, 1);
		// (|ContextCS::ownedExpression| - 1)
		serializationMatchTerms[92] = createSerializationMatchTermSubtract(19, 1);
		// (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchTerms[93] = createSerializationMatchTermSubtract(20, 1);
		// (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchTerms[94] = createSerializationMatchTermGreaterThan(20, 0);
		// (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[95] = createSerializationMatchTermSubtract(21, 1);
		// (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[96] = createSerializationMatchTermSubtract(22, 1);
		// (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[97] = createSerializationMatchTermSubtract(24, 1);
		// (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[98] = createSerializationMatchTermSubtract(25, 1);
		// (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[99] = createSerializationMatchTermSubtract(26, 1);
		// (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[100] = createSerializationMatchTermSubtract(27, 1);
		// (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[101] = createSerializationMatchTermSubtract(28, 1);
		// (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[102] = createSerializationMatchTermSubtract(29, 1);
		// (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[103] = createSerializationMatchTermSubtract(30, 1);
		// (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[104] = createSerializationMatchTermSubtract(32, 1);
		// (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[105] = createSerializationMatchTermGreaterThan(32, 0);
		// (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[106] = createSerializationMatchTermSubtract(33, 1);
		// (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[107] = createSerializationMatchTermSubtract(34, 1);
		// (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[108] = createSerializationMatchTermSubtract(35, 1);
		// (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[109] = createSerializationMatchTermSubtract(36, 1);
		// (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[110] = createSerializationMatchTermSubtract(37, 2);
		// (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[111] = createSerializationMatchTermSubtract(39, 1);
		// (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[112] = createSerializationMatchTermSubtract(42, 1);
		// (|NamedElementCS::name| - 1)
		serializationMatchTerms[113] = createSerializationMatchTermSubtract(43, 1);
		// (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[114] = createSerializationMatchTermSubtract(44, 1);
		// (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[115] = createSerializationMatchTermSubtract(45, 1);
		// (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[116] = createSerializationMatchTermSubtract(46, 1);
		// (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[117] = createSerializationMatchTermSubtract(47, 1);
		// (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[118] = createSerializationMatchTermSubtract(48, 1);
		// (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[119] = createSerializationMatchTermSubtract(49, 1);
		// (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[120] = createSerializationMatchTermSubtract(50, 1);
		// (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[121] = createSerializationMatchTermSubtract(51, 1);
		// (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[122] = createSerializationMatchTermSubtract(52, 1);
		// (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[123] = createSerializationMatchTermSubtract(53, 1);
		// (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[124] = createSerializationMatchTermSubtract(54, 1);
		// (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[125] = createSerializationMatchTermSubtract(55, 1);
		// (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[126] = createSerializationMatchTermSubtract(56, 1);
		// (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[127] = createSerializationMatchTermSubtract(58, 1);
		// (|RoundBracketedClauseCS::ownedArguments| - 1)
		serializationMatchTerms[128] = createSerializationMatchTermSubtract(59, 1);
		// (|RoundBracketedClauseCS::ownedArguments| > 0)
		serializationMatchTerms[129] = createSerializationMatchTermGreaterThan(59, 0);
		// (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[130] = createSerializationMatchTermSubtract(60, 1);
		// (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[131] = createSerializationMatchTermSubtract(61, 1);
		// (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[132] = createSerializationMatchTermSubtract(62, 1);
		// (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[133] = createSerializationMatchTermSubtract(65, 1);
		// (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[134] = createSerializationMatchTermSubtract(66, 1);
		// (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[135] = createSerializationMatchTermSubtract(67, 1);
		// (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[136] = createSerializationMatchTermSubtract(68, 1);
		// (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[137] = createSerializationMatchTermSubtract(69, 1);
		// (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[138] = createSerializationMatchTermSubtract(70, 1);
		// (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[139] = createSerializationMatchTermGreaterThan(70, 0);
		// (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[140] = createSerializationMatchTermSubtract(71, 1);
		// (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[141] = createSerializationMatchTermSubtract(73, 1);
		// (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[142] = createSerializationMatchTermSubtract(75, 1);
		// (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[143] = createSerializationMatchTermGreaterThan(75, 0);
		// (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[144] = createSerializationMatchTermSubtract(76, 1);
		// (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[145] = createSerializationMatchTermSubtract(79, 1);
		// (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[146] = createSerializationMatchTermSubtract(80, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] =
			new SerializationRule("FirstPathElementCS", 21,
				createSerializationMatchSteps(
					33		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					140		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
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
			new SerializationRule("MultiplicityBoundsCS", 39,
				createSerializationMatchSteps(
					65		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					20		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					0		/* MultiplicityBoundsCS::lowerBound=29 || soft-space value soft-space */,
					115		/* V00*4-steps || value */,
					107		/* 1*1-steps || value */,
					77		/* '..' || no-space value no-space */,
					107		/* 1*1-steps || value */,
					70		/* MultiplicityBoundsCS::upperBound=89 || soft-space value soft-space */
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
			new SerializationRule("MultiplicityCS", 40,
				createSerializationMatchSteps(
					65		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					20		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					88		/* '[' || no-space value no-space */,
					111		/* 1*7-steps || value */,
					107		/* 1*1-steps || value */,
					0		/* MultiplicityBoundsCS::lowerBound=29 || soft-space value soft-space */,
					115		/* V00*4-steps || value */,
					107		/* 1*1-steps || value */,
					77		/* '..' || no-space value no-space */,
					107		/* 1*1-steps || value */,
					70		/* MultiplicityBoundsCS::upperBound=89 || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					89		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 40,
				createSerializationMatchSteps(
					65		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					20		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					88		/* '[' || no-space value no-space */,
					111		/* 1*7-steps || value */,
					107		/* 1*1-steps || value */,
					0		/* MultiplicityBoundsCS::lowerBound=29 || soft-space value soft-space */,
					115		/* V00*4-steps || value */,
					107		/* 1*1-steps || value */,
					77		/* '..' || no-space value no-space */,
					107		/* 1*1-steps || value */,
					70		/* MultiplicityBoundsCS::upperBound=89 || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					105		/* '|?' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					89		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 40,
				createSerializationMatchSteps(
					86		/* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
					65		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					20		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					88		/* '[' || no-space value no-space */,
					111		/* 1*7-steps || value */,
					107		/* 1*1-steps || value */,
					0		/* MultiplicityBoundsCS::lowerBound=29 || soft-space value soft-space */,
					115		/* V00*4-steps || value */,
					107		/* 1*1-steps || value */,
					77		/* '..' || no-space value no-space */,
					107		/* 1*1-steps || value */,
					70		/* MultiplicityBoundsCS::upperBound=89 || soft-space value soft-space */,
					119		/* V01*1-steps || value */,
					104		/* '|1' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					89		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						8	/* '|1' */
					)
				},
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(8 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
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
			new SerializationRule("MultiplicityCS", 40,
				createSerializationMatchSteps(
					21		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					88		/* '[' || no-space value no-space */,
					107		/* 1*1-steps || value */,
					67		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					89		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 40,
				createSerializationMatchSteps(
					21		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					88		/* '[' || no-space value no-space */,
					107		/* 1*1-steps || value */,
					67		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					105		/* '|?' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					89		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 40,
				createSerializationMatchSteps(
					66		/* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
					21		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					88		/* '[' || no-space value no-space */,
					107		/* 1*1-steps || value */,
					67		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					112		/* V00*1-steps || value */,
					104		/* '|1' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					89		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						8	/* '|1' */
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
							createEnumerationValue_GrammarCardinality(8 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
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
			new SerializationRule("MultiplicityStringCS", 41,
				createSerializationMatchSteps(
					21		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					67		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
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
			new SerializationRule("NextPathElementCS", 51,
				createSerializationMatchSteps(
					33		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					139		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
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
			new SerializationRule("PathNameCS", 54,
				createSerializationMatchSteps(
					99		/* check-rule basecs::PathNameCS.ownedPathElements : 21|51 */,
					50		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					132		/* PathNameCS::ownedPathElements+=21 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					79		/* '::' || no-space value no-space */,
					133		/* PathNameCS::ownedPathElements+=51 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						14) /* FirstPathElementCS|NextPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(21, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(51, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateBindingCS(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] =
			new SerializationRule("TemplateBindingCS", 72,
				createSerializationMatchSteps(
					101		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 40 */,
					102		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 73 */,
					89		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
					53		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
				),
				createSerializationSteps(
					48		/* TemplateBindingCS::ownedSubstitutions+=73 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					48		/* TemplateBindingCS::ownedSubstitutions+=73 || value */,
					119		/* V01*1-steps || value */,
					29		/* TemplateBindingCS::ownedMultiplicity=40 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						26) /* TemplateParameterSubstitutionCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateParameterSubstitutionCS(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] =
			new SerializationRule("TemplateParameterSubstitutionCS", 73,
				createSerializationMatchSteps(
					103		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 86 */,
					39		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					3		/* TemplateParameterSubstitutionCS::ownedActualParameter=86 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						37) /* TypeRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(86, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Base::TemplateSignatureCS(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[13] =
			new SerializationRule("TemplateSignatureCS", 74,
				createSerializationMatchSteps(
					104		/* check-rule basecs::TemplateSignatureCS.ownedParameters : 85 */,
					54		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					33		/* TemplateSignatureCS::ownedParameters+=85 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					33		/* TemplateSignatureCS::ownedParameters+=85 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						36) /* TypeParameterCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(85, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// Base::TypeParameterCS(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[14] =
			new SerializationRule("TypeParameterCS", 85,
				createSerializationMatchSteps(
					106		/* check-rule basecs::TypeParameterCS.ownedExtends : 87 */,
					22		/* assert (|NamedElementCS::name| - 1) == 0 */,
					57		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
					83		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					129		/* NamedElementCS::name=96 || soft-space value soft-space */,
					117		/* V00*7-steps || value */,
					107		/* 1*1-steps || value */,
					93		/* 'extends' || soft-space value soft-space */,
					17		/* TypeParameterCS::ownedExtends+=87 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					71		/* '&&' || soft-space value soft-space */,
					17		/* TypeParameterCS::ownedExtends+=87 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						38) /* TypedRefCS */
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
						new RuleIndex_GrammarCardinality(87, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] }
		serializationRules[15] =
			new SerializationRule("TypedTypeRefCS", 88,
				createSerializationMatchSteps(
					109		/* check-rule basecs::TypedTypeRefCS.ownedBinding : 72 */,
					110		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 54 */,
					74		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
					44		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					42		/* TypedTypeRefCS::ownedPathName=54 || value */,
					116		/* V00*5-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					4		/* TypedTypeRefCS::ownedBinding=72 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						25) /* TemplateBindingCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						15) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(54, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[16] =
			new SerializationRule("WildcardTypeRefCS", 98,
				createSerializationMatchSteps(
					111		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : 87 */,
					76		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					83		/* '?' || soft-space value soft-space */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					93		/* 'extends' || soft-space value soft-space */,
					18		/* WildcardTypeRefCS::ownedExtends=87 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						38) /* TypedRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(87, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[17] =
			new SerializationRule("BooleanLiteralExpCS", 2,
				createSerializationMatchSteps(
					1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					68		/* BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						6	/* 'false|true' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* 'false|true' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::CoIteratorVariableCS(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[18] =
			new SerializationRule("CoIteratorVariableCS", 3,
				createSerializationMatchSteps(
					163		/* check-rule essentialoclcs::VariableCS.ownedType : 79 */,
					75		/* assign V0 = |VariableCS::ownedType| */,
					22		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					129		/* NamedElementCS::name=96 || soft-space value soft-space */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					59		/* VariableCS::ownedType=79 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						29) /* TypeExpCS */
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
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[19] =
			new SerializationRule("CollectionLiteralExpCS", 4,
				createSerializationMatchSteps(
					116		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5 */,
					117		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7 */,
					2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
					46		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
					77		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					52		/* CollectionLiteralExpCS::ownedType=7 || value */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					116		/* V00*5-steps || value */,
					34		/* CollectionLiteralExpCS::ownedParts+=5 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					34		/* CollectionLiteralExpCS::ownedParts+=5 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						1) /* CollectionLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						2) /* CollectionTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(5, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
		serializationRules[20] =
			new SerializationRule("CollectionLiteralPartCS", 5,
				createSerializationMatchSteps(
					119		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 55 */,
					3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					13		/* CollectionLiteralPartCS::ownedExpression=55 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						16) /* PatternExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] }
		serializationRules[21] =
			new SerializationRule("CollectionLiteralPartCS", 5,
				createSerializationMatchSteps(
					118		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 20 */,
					120		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 20 */,
					59		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
					3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					12		/* CollectionLiteralPartCS::ownedExpression=20 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					77		/* '..' || no-space value no-space */,
					27		/* CollectionLiteralPartCS::ownedLastExpression=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" }
		serializationRules[22] =
			new SerializationRule("CollectionPatternCS", 6,
				createSerializationMatchSteps(
					121		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55 */,
					122		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : 7 */,
					60		/* assign V0 = |CollectionPatternCS::restVariableName| */,
					78		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
					4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					53		/* CollectionPatternCS::ownedType=7 || value */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					113		/* V00*10-steps || value */,
					35		/* CollectionPatternCS::ownedParts+=55 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					35		/* CollectionPatternCS::ownedParts+=55 || value */,
					110		/* 1*4-steps || value */,
					107		/* 1*1-steps || value */,
					75		/* '++' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					65		/* CollectionPatternCS::restVariableName=24 || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						16) /* PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						2) /* CollectionTypeCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] }
		serializationRules[23] =
			new SerializationRule("CollectionTypeCS", 7,
				createSerializationMatchSteps(
					123		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40 */,
					124		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 80 */,
					61		/* assign V0 = |CollectionTypeCS::ownedType| */,
					5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					85		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					1		/* CollectionTypeCS::name=8 || soft-space value soft-space */,
					117		/* V00*7-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					54		/* CollectionTypeCS::ownedType=80 || value */,
					119		/* V01*1-steps || value */,
					6		/* CollectionTypeCS::ownedCollectionMultiplicity=40 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						30) /* TypeExpWithoutMultiplicityCS */
				},
				new /*@NonNull*/ EAttribute [] {
					EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(80, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CurlyBracketedClauseCS(essentialoclcs::CurlyBracketedClauseCS): { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" }
		serializationRules[24] =
			new SerializationRule("CurlyBracketedClauseCS", 9,
				createSerializationMatchSteps(
					126		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 67 */,
					47		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
					79		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					116		/* V00*5-steps || value */,
					36		/* CurlyBracketedClauseCS::ownedParts+=67 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					36		/* CurlyBracketedClauseCS::ownedParts+=67 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						22) /* ShadowPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ElseIfThenExpCS(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[25] =
			new SerializationRule("ElseIfThenExpCS", 13,
				createSerializationMatchSteps(
					131		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 20 */,
					132		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 20 */,
					11		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
					10		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					91		/* 'elseif' || soft-space value soft-space */,
					8		/* IfThenExpCS::ownedCondition=20 || value */,
					107		/* 1*1-steps || value */,
					101		/* 'then' || soft-space value soft-space */,
					51		/* IfThenExpCS::ownedThenExpression=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[26] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					68		/* BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						6	/* 'false|true' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* 'false|true' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[27] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					116		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5 */,
					117		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7 */,
					2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
					46		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
					77		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					52		/* CollectionLiteralExpCS::ownedType=7 || value */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					116		/* V00*5-steps || value */,
					34		/* CollectionLiteralExpCS::ownedParts+=5 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					34		/* CollectionLiteralExpCS::ownedParts+=5 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						1) /* CollectionLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						2) /* CollectionTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(5, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "*"
		serializationRules[28] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					74		/* '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "invalid"
		serializationRules[29] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					96		/* 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "null"
		serializationRules[30] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					98		/* 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "self"
		serializationRules[31] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					100		/* 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[32] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					127		/* check-rule essentialoclcs::IfExpCS.ownedCondition : 20|55 */,
					128		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 20 */,
					129		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13 */,
					130		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 20 */,
					8		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
					62		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
					9		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
					7		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					94		/* 'if' || soft-space value soft-space */,
					7		/* IfExpCS::ownedCondition=20|55 || value */,
					107		/* 1*1-steps || value */,
					101		/* 'then' || soft-space value soft-space */,
					50		/* IfExpCS::ownedThenExpression=20 || value */,
					112		/* V00*1-steps || value */,
					19		/* IfExpCS::ownedIfThenExpressions+=13 || value */,
					107		/* 1*1-steps || value */,
					90		/* 'else' || soft-space value soft-space */,
					11		/* IfExpCS::ownedElseExpression=20 || value */,
					107		/* 1*1-steps || value */,
					92		/* 'endif' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						17) /* ExpCS|PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						4) /* ElseIfThenExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } }
		serializationRules[33] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					133		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : 57 */,
					149		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 20 */,
					32		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					22		/* assert (|NamedElementCS::name| - 1) == 0 */,
					12		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					28		/* InfixExpCS::ownedLeft=57 || value */,
					109		/* 1*3-steps || value */,
					107		/* 1*1-steps || value */,
					127		/* NamedElementCS::name=1 || soft-space value soft-space */,
					135		/* OperatorExpCS::ownedRight=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						20) /* PrefixedPrimaryExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						5) /* ExpCS */
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
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(57, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[34] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					134		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 20 */,
					13		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					85		/* 'Lambda' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					16		/* LambdaLiteralExpCS::ownedExpressionCS=20 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[35] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					138		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36 */,
					139		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37 */,
					15		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
					49		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
					80		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					55		/* MapLiteralExpCS::ownedType=37 || value */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					116		/* V00*5-steps || value */,
					37		/* MapLiteralExpCS::ownedParts+=36 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					37		/* MapLiteralExpCS::ownedParts+=36 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						8) /* MapLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						9) /* MapTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(36, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(37, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] }
		serializationRules[36] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					112		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 9 */,
					113		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 54 */,
					114		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 62 */,
					115		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 69 */,
					96		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
					94		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
					84		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
					58		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
					0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					40		/* AbstractNameExpCS::ownedPathName=54 || value */,
					112		/* V00*1-steps || value */,
					47		/* AbstractNameExpCS::ownedSquareBracketedClauses+=69 || value */,
					119		/* V01*1-steps || value */,
					45		/* AbstractNameExpCS::ownedRoundBracketedClause=62 || value */,
					122		/* V02*1-steps || value */,
					9		/* AbstractNameExpCS::ownedCurlyBracketedClause=9 || value */,
					125		/* V03*4-steps || value */,
					107		/* 1*1-steps || value */,
					84		/* '@' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					99		/* 'pre' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						3	/* '@' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						3) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						15) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						21) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						23) /* SquareBracketedClauseCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(3 /* '@' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(9, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(54, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(69, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[37] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					148		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : 20 */,
					30		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					15		/* NestedExpCS::ownedExpression=20 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[38] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					31		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					69		/* NumberLiteralExpCS::symbol=42 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[39] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					151		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 57 */,
					32		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					22		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					128		/* NamedElementCS::name=93 || soft-space value soft-space */,
					137		/* OperatorExpCS::ownedRight=57 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						20) /* PrefixedPrimaryExpCS */
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
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(57, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[40] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					71		/* assign V0 = |StringLiteralExpCS::segments| */
				),
				createSerializationSteps(
					112		/* V00*1-steps || value */,
					66		/* StringLiteralExpCS::segments+=70 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE_OR_MORE)
						}
					)
				},
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::TupleLiteralExpCS): { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" }
		serializationRules[41] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					157		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76 */,
					55		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					87		/* 'Tuple' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					38		/* TupleLiteralExpCS::ownedParts+=76 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					38		/* TupleLiteralExpCS::ownedParts+=76 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						27) /* TupleLiteralPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(76, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[42] =
			new SerializationRule("ExpCS", 20,
				createSerializationMatchSteps(
					158		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83 */,
					41		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					57		/* TypeLiteralExpCS::ownedType=83 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						32) /* TypeLiteralWithMultiplicityCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[43] =
			new SerializationRule("IfExpCS", 25,
				createSerializationMatchSteps(
					127		/* check-rule essentialoclcs::IfExpCS.ownedCondition : 20|55 */,
					128		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 20 */,
					129		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13 */,
					130		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 20 */,
					8		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
					62		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
					9		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
					7		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					94		/* 'if' || soft-space value soft-space */,
					7		/* IfExpCS::ownedCondition=20|55 || value */,
					107		/* 1*1-steps || value */,
					101		/* 'then' || soft-space value soft-space */,
					50		/* IfExpCS::ownedThenExpression=20 || value */,
					112		/* V00*1-steps || value */,
					19		/* IfExpCS::ownedIfThenExpressions+=13 || value */,
					107		/* 1*1-steps || value */,
					90		/* 'else' || soft-space value soft-space */,
					11		/* IfExpCS::ownedElseExpression=20 || value */,
					107		/* 1*1-steps || value */,
					92		/* 'endif' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						17) /* ExpCS|PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						4) /* ElseIfThenExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): "invalid"
		serializationRules[44] =
			new SerializationRule("InvalidLiteralExpCS", 27,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					96		/* 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[45] =
			new SerializationRule("LambdaLiteralExpCS", 30,
				createSerializationMatchSteps(
					134		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 20 */,
					13		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					85		/* 'Lambda' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					16		/* LambdaLiteralExpCS::ownedExpressionCS=20 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[46] =
			new SerializationRule("LetExpCS", 31,
				createSerializationMatchSteps(
					135		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : 20 */,
					136		/* check-rule essentialoclcs::LetExpCS.ownedVariables : 32 */,
					14		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
					48		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					97		/* 'let' || soft-space value soft-space */,
					62		/* LetExpCS::ownedVariables+=32 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					62		/* LetExpCS::ownedVariables+=32 || value */,
					107		/* 1*1-steps || value */,
					95		/* 'in' || soft-space value soft-space */,
					20		/* LetExpCS::ownedInExpression=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						7) /* LetVariableCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(32, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::LetVariableCS(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[47] =
			new SerializationRule("LetVariableCS", 32,
				createSerializationMatchSteps(
					162		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : 20 */,
					137		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 62 */,
					163		/* check-rule essentialoclcs::VariableCS.ownedType : 79 */,
					45		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
					92		/* assign V1 = |VariableCS::ownedType| */,
					63		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
					22		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					129		/* NamedElementCS::name=96 || soft-space value soft-space */,
					112		/* V00*1-steps || value */,
					46		/* LetVariableCS::ownedRoundBracketedClause=62 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					59		/* VariableCS::ownedType=79 || value */,
					107		/* 1*1-steps || value */,
					82		/* '=' || soft-space value soft-space */,
					24		/* VariableCS::ownedInitExpression=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						21) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						29) /* TypeExpCS */
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
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[48] =
			new SerializationRule("MapLiteralExpCS", 35,
				createSerializationMatchSteps(
					138		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36 */,
					139		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37 */,
					15		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
					49		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
					80		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					55		/* MapLiteralExpCS::ownedType=37 || value */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					116		/* V00*5-steps || value */,
					37		/* MapLiteralExpCS::ownedParts+=36 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					37		/* MapLiteralExpCS::ownedParts+=36 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						8) /* MapLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						9) /* MapTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(36, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(37, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::MapLiteralPartCS(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS "<-" ownedValue=ExpCS }
		serializationRules[49] =
			new SerializationRule("MapLiteralPartCS", 36,
				createSerializationMatchSteps(
					140		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 20 */,
					141		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 20 */,
					17		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
					16		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					25		/* MapLiteralPartCS::ownedKey=20 || value */,
					107		/* 1*1-steps || value */,
					81		/* '<-' || soft-space value soft-space */,
					60		/* MapLiteralPartCS::ownedValue=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] }
		serializationRules[50] =
			new SerializationRule("MapTypeCS", 37,
				createSerializationMatchSteps(
					142		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79 */,
					143		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 79 */,
					64		/* assign V0 = |MapTypeCS::ownedValueType| */,
					19		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					18		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					86		/* 'Map' || soft-space value soft-space */,
					118		/* V00*8-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					26		/* MapTypeCS::ownedKeyType=79 || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					61		/* MapTypeCS::ownedValueType=79 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						4	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						29) /* TypeExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						29) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(4 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::Model(essentialoclcs::ContextCS): ownedExpression=ExpCS
		serializationRules[51] =
			new SerializationRule("Model", 38,
				createSerializationMatchSteps(
					125		/* check-rule essentialoclcs::ContextCS.ownedExpression : 20 */,
					6		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					14		/* ContextCS::ownedExpression=20 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] }
		serializationRules[52] =
			new SerializationRule("NameExpCS", 43,
				createSerializationMatchSteps(
					112		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 9 */,
					113		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 54 */,
					114		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 62 */,
					115		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 69 */,
					96		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
					94		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
					84		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
					58		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
					0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					40		/* AbstractNameExpCS::ownedPathName=54 || value */,
					112		/* V00*1-steps || value */,
					47		/* AbstractNameExpCS::ownedSquareBracketedClauses+=69 || value */,
					119		/* V01*1-steps || value */,
					45		/* AbstractNameExpCS::ownedRoundBracketedClause=62 || value */,
					122		/* V02*1-steps || value */,
					9		/* AbstractNameExpCS::ownedCurlyBracketedClause=9 || value */,
					125		/* V03*4-steps || value */,
					107		/* 1*1-steps || value */,
					84		/* '@' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					99		/* 'pre' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						3	/* '@' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						3) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						15) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						21) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						23) /* SquareBracketedClauseCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(3 /* '@' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(9, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(54, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(69, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
		serializationRules[53] =
			new SerializationRule("NavigatingArgCS", 44,
				createSerializationMatchSteps(
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					32		/* NavigatingArgCS::ownedNameExpression=45 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ":" ownedType=TypeExpCS }
		serializationRules[54] =
			new SerializationRule("NavigatingArgCS", 44,
				createSerializationMatchSteps(
					147		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
					26		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					56		/* NavigatingArgCS::ownedType=79 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						29) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[55] =
			new SerializationRule("NavigatingArgCS", 44,
				createSerializationMatchSteps(
					144		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
					145		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					147		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
					88		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
					67		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
					26		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					31		/* NavigatingArgCS::ownedNameExpression=45 || value */,
					108		/* 1*11-steps || value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					56		/* NavigatingArgCS::ownedType=79 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					81		/* '<-' || soft-space value soft-space */,
					5		/* NavigatingArgCS::ownedCoIterator=3 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					82		/* '=' || soft-space value soft-space */,
					21		/* NavigatingArgCS::ownedInitExpression=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						0) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						29) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[56] =
			new SerializationRule("NavigatingArgCS", 44,
				createSerializationMatchSteps(
					144		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
					145		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					68		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
					23		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					31		/* NavigatingArgCS::ownedNameExpression=45 || value */,
					111		/* 1*7-steps || value */,
					107		/* 1*1-steps || value */,
					81		/* '<-' || soft-space value soft-space */,
					5		/* NavigatingArgCS::ownedCoIterator=3 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					82		/* '=' || soft-space value soft-space */,
					21		/* NavigatingArgCS::ownedInitExpression=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						0) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[57] =
			new SerializationRule("NavigatingArgCS", 44,
				createSerializationMatchSteps(
					144		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
					145		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					147		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
					24		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
					87		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
					69		/* assign V0 = |NavigatingArgCS::ownedType| */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					31		/* NavigatingArgCS::ownedNameExpression=45 || value */,
					108		/* 1*11-steps || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					56		/* NavigatingArgCS::ownedType=79 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					81		/* '<-' || soft-space value soft-space */,
					5		/* NavigatingArgCS::ownedCoIterator=3 || value */,
					107		/* 1*1-steps || value */,
					95		/* 'in' || soft-space value soft-space */,
					21		/* NavigatingArgCS::ownedInitExpression=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						0) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						29) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingBarArgCS(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[58] =
			new SerializationRule("NavigatingBarArgCS", 46,
				createSerializationMatchSteps(
					145		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					147		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
					69		/* assign V0 = |NavigatingArgCS::ownedType| */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					29		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
					88		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					103		/* '|' || soft-space value soft-space */,
					31		/* NavigatingArgCS::ownedNameExpression=45 || value */,
					117		/* V00*7-steps || value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					56		/* NavigatingArgCS::ownedType=79 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					82		/* '=' || soft-space value soft-space */,
					21		/* NavigatingArgCS::ownedInitExpression=20 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						7	/* '|' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						29) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(7 /* '|' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[59] =
			new SerializationRule("NavigatingCommaArgCS", 47,
				createSerializationMatchSteps(
					144		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
					145		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					147		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
					88		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
					67		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
					26		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					27		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					31		/* NavigatingArgCS::ownedNameExpression=45 || value */,
					108		/* 1*11-steps || value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					56		/* NavigatingArgCS::ownedType=79 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					81		/* '<-' || soft-space value soft-space */,
					5		/* NavigatingArgCS::ownedCoIterator=3 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					82		/* '=' || soft-space value soft-space */,
					21		/* NavigatingArgCS::ownedInitExpression=20 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						1	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						0) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						29) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* ',' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[60] =
			new SerializationRule("NavigatingCommaArgCS", 47,
				createSerializationMatchSteps(
					144		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
					145		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					68		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
					23		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					27		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					31		/* NavigatingArgCS::ownedNameExpression=45 || value */,
					111		/* 1*7-steps || value */,
					107		/* 1*1-steps || value */,
					81		/* '<-' || soft-space value soft-space */,
					5		/* NavigatingArgCS::ownedCoIterator=3 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					82		/* '=' || soft-space value soft-space */,
					21		/* NavigatingArgCS::ownedInitExpression=20 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						1	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						0) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* ',' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[61] =
			new SerializationRule("NavigatingCommaArgCS", 47,
				createSerializationMatchSteps(
					144		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
					145		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					147		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
					24		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
					87		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
					69		/* assign V0 = |NavigatingArgCS::ownedType| */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					27		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					31		/* NavigatingArgCS::ownedNameExpression=45 || value */,
					108		/* 1*11-steps || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					56		/* NavigatingArgCS::ownedType=79 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					81		/* '<-' || soft-space value soft-space */,
					5		/* NavigatingArgCS::ownedCoIterator=3 || value */,
					107		/* 1*1-steps || value */,
					95		/* 'in' || soft-space value soft-space */,
					21		/* NavigatingArgCS::ownedInitExpression=20 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						1	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						0) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						29) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* ',' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS }
		serializationRules[62] =
			new SerializationRule("NavigatingCommaArgCS", 47,
				createSerializationMatchSteps(
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					27		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					31		/* NavigatingArgCS::ownedNameExpression=45 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						1	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* ',' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingSemiArgCS(essentialoclcs::NavigatingArgCS): { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[63] =
			new SerializationRule("NavigatingSemiArgCS", 48,
				createSerializationMatchSteps(
					145		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
					146		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
					147		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
					69		/* assign V0 = |NavigatingArgCS::ownedType| */,
					25		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					28		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
					88		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					80		/* ';' || no-space value soft-new-line */,
					31		/* NavigatingArgCS::ownedNameExpression=45 || value */,
					117		/* V00*7-steps || value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					56		/* NavigatingArgCS::ownedType=79 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					82		/* '=' || soft-space value soft-space */,
					21		/* NavigatingArgCS::ownedInitExpression=20 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						2	/* ';' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						11) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						29) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(2 /* ';' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(45, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
	}
	private void initSerializationRules1() {
		// EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[64] =
			new SerializationRule("NestedExpCS", 50,
				createSerializationMatchSteps(
					148		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : 20 */,
					30		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					15		/* NestedExpCS::ownedExpression=20 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[65] =
			new SerializationRule("NullLiteralExpCS", 52,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					98		/* 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[66] =
			new SerializationRule("NumberLiteralExpCS", 53,
				createSerializationMatchSteps(
					31		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					69		/* NumberLiteralExpCS::symbol=42 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::PatternExpCS(essentialoclcs::PatternExpCS): { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS }
		serializationRules[67] =
			new SerializationRule("PatternExpCS", 55,
				createSerializationMatchSteps(
					152		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 79 */,
					35		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
					70		/* assign V0 = |PatternExpCS::patternVariableName| */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					112		/* V00*1-steps || value */,
					63		/* PatternExpCS::patternVariableName=96 || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					44		/* PatternExpCS::ownedPatternType=79 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						29) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		serializationRules[68] =
			new SerializationRule("PrefixedLetExpCS", 56,
				createSerializationMatchSteps(
					150		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 56 */,
					32		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					22		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					128		/* NamedElementCS::name=93 || soft-space value soft-space */,
					136		/* OperatorExpCS::ownedRight=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						18) /* PrefixedLetExpCS */
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
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrefixedPrimaryExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[69] =
			new SerializationRule("PrefixedPrimaryExpCS", 57,
				createSerializationMatchSteps(
					151		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 57 */,
					32		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					22		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					128		/* NamedElementCS::name=93 || soft-space value soft-space */,
					137		/* OperatorExpCS::ownedRight=57 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						20) /* PrefixedPrimaryExpCS */
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
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(57, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		serializationRules[70] =
			new SerializationRule("PrimitiveTypeCS", 60,
				createSerializationMatchSteps(
					36		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					2		/* PrimitiveTypeRefCS::name=61 || soft-space value soft-space */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::RoundBracketedClauseCS(essentialoclcs::RoundBracketedClauseCS): { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" }
		serializationRules[71] =
			new SerializationRule("RoundBracketedClauseCS", 62,
				createSerializationMatchSteps(
					153		/* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 44|46|47|48 */,
					51		/* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
					81		/* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					114		/* V00*3-steps || value */,
					130		/* RoundBracketedClauseCS::ownedArguments+=44 || value */,
					119		/* V01*1-steps || value */,
					131		/* RoundBracketedClauseCS::ownedArguments+=47|48|46 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						13) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(44, GrammarCardinality.ZERO_OR_ONE),
						new RuleIndex_GrammarCardinality(46, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(47, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(48, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): "self"
		serializationRules[72] =
			new SerializationRule("SelfExpCS", 66,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					100		/* 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
		serializationRules[73] =
			new SerializationRule("ShadowPartCS", 67,
				createSerializationMatchSteps(
					155		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 71 */,
					37		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					23		/* ShadowPartCS::ownedInitExpression=71 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						24) /* StringLiteralExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(71, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[74] =
			new SerializationRule("ShadowPartCS", 67,
				createSerializationMatchSteps(
					154		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 20|55 */,
					37		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
					38		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					64		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					82		/* '=' || soft-space value soft-space */,
					22		/* ShadowPartCS::ownedInitExpression=20|55 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						17) /* ExpCS|PatternExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// EssentialOCL::SimplePathNameCS(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[75] =
			new SerializationRule("SimplePathNameCS", 68,
				createSerializationMatchSteps(
					98		/* check-rule basecs::PathNameCS.ownedPathElements : 21 */,
					34		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
				),
				createSerializationSteps(
					132		/* PathNameCS::ownedPathElements+=21 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						6) /* FirstPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(21, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::SquareBracketedClauseCS(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[76] =
			new SerializationRule("SquareBracketedClauseCS", 69,
				createSerializationMatchSteps(
					156		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 20 */,
					52		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					88		/* '[' || no-space value no-space */,
					49		/* SquareBracketedClauseCS::ownedTerms+=20 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					49		/* SquareBracketedClauseCS::ownedTerms+=20 || value */,
					107		/* 1*1-steps || value */,
					89		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[77] =
			new SerializationRule("StringLiteralExpCS", 71,
				createSerializationMatchSteps(
					71		/* assign V0 = |StringLiteralExpCS::segments| */
				),
				createSerializationSteps(
					112		/* V00*1-steps || value */,
					66		/* StringLiteralExpCS::segments+=70 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE_OR_MORE)
						}
					)
				},
				null);
			;
		// EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" }
		serializationRules[78] =
			new SerializationRule("TupleLiteralExpCS", 75,
				createSerializationMatchSteps(
					157		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76 */,
					55		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					87		/* 'Tuple' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					38		/* TupleLiteralExpCS::ownedParts+=76 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					38		/* TupleLiteralExpCS::ownedParts+=76 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						27) /* TupleLiteralPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(76, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TupleLiteralPartCS(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[79] =
			new SerializationRule("TupleLiteralPartCS", 76,
				createSerializationMatchSteps(
					162		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : 20 */,
					163		/* check-rule essentialoclcs::VariableCS.ownedType : 79 */,
					45		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
					75		/* assign V0 = |VariableCS::ownedType| */,
					22		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					129		/* NamedElementCS::name=96 || soft-space value soft-space */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					59		/* VariableCS::ownedType=79 || value */,
					107		/* 1*1-steps || value */,
					82		/* '=' || soft-space value soft-space */,
					24		/* VariableCS::ownedInitExpression=20 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						5) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						29) /* TypeExpCS */
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
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TuplePartCS(basecs::TuplePartCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[80] =
			new SerializationRule("TuplePartCS", 77,
				createSerializationMatchSteps(
					107		/* check-rule basecs::TypedElementCS.ownedType : 79 */,
					43		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					22		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					126		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					107		/* 1*1-steps || value */,
					129		/* NamedElementCS::name=96 || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					78		/* ':' || soft-space value soft-space */,
					58		/* TypedElementCS::ownedType=79 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						29) /* TypeExpCS */
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
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] }
		serializationRules[81] =
			new SerializationRule("TupleTypeCS", 78,
				createSerializationMatchSteps(
					105		/* check-rule basecs::TupleTypeCS.ownedParts : 77 */,
					40		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					56		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					82		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					93		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					87		/* 'Tuple' || soft-space value soft-space */,
					113		/* V00*10-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					121		/* V01*5-steps || value */,
					39		/* TupleTypeCS::ownedParts+=77 || value */,
					123		/* V02*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					39		/* TupleTypeCS::ownedParts+=77 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						5	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						28) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(77, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[82] =
			new SerializationRule("TypeExpCS", 79,
				createSerializationMatchSteps(
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					73		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					36		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					2		/* PrimitiveTypeRefCS::name=61 || soft-space value soft-space */,
					112		/* V00*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[83] =
			new SerializationRule("TypeExpCS", 79,
				createSerializationMatchSteps(
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					105		/* check-rule basecs::TupleTypeCS.ownedParts : 77 */,
					97		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					40		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					56		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					82		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					93		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					87		/* 'Tuple' || soft-space value soft-space */,
					113		/* V00*10-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					121		/* V01*5-steps || value */,
					39		/* TupleTypeCS::ownedParts+=77 || value */,
					123		/* V02*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					39		/* TupleTypeCS::ownedParts+=77 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */,
					124		/* V03*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						5	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						28) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(77, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::CollectionPatternCS): { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[84] =
			new SerializationRule("TypeExpCS", 79,
				createSerializationMatchSteps(
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					121		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55 */,
					122		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : 7 */,
					95		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					60		/* assign V0 = |CollectionPatternCS::restVariableName| */,
					78		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
					4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					53		/* CollectionPatternCS::ownedType=7 || value */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					113		/* V00*10-steps || value */,
					35		/* CollectionPatternCS::ownedParts+=55 || value */,
					120		/* V01*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					35		/* CollectionPatternCS::ownedParts+=55 || value */,
					110		/* 1*4-steps || value */,
					107		/* 1*1-steps || value */,
					75		/* '++' || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					65		/* CollectionPatternCS::restVariableName=24 || soft-space value soft-space */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */,
					122		/* V02*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						16) /* PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						2) /* CollectionTypeCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[85] =
			new SerializationRule("TypeExpCS", 79,
				createSerializationMatchSteps(
					123		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40 */,
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					124		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 80 */,
					95		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					61		/* assign V0 = |CollectionTypeCS::ownedType| */,
					5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					85		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					1		/* CollectionTypeCS::name=8 || soft-space value soft-space */,
					117		/* V00*7-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					54		/* CollectionTypeCS::ownedType=80 || value */,
					119		/* V01*1-steps || value */,
					6		/* CollectionTypeCS::ownedCollectionMultiplicity=40 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */,
					122		/* V02*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						30) /* TypeExpWithoutMultiplicityCS */
				},
				new /*@NonNull*/ EAttribute [] {
					EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(80, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[86] =
			new SerializationRule("TypeExpCS", 79,
				createSerializationMatchSteps(
					142		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79 */,
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					143		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 79 */,
					91		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					64		/* assign V0 = |MapTypeCS::ownedValueType| */,
					19		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					18		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					86		/* 'Map' || soft-space value soft-space */,
					118		/* V00*8-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					26		/* MapTypeCS::ownedKeyType=79 || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					61		/* MapTypeCS::ownedValueType=79 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */,
					119		/* V01*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						4	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						29) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						29) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(4 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::TypeNameExpCS): { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[87] =
			new SerializationRule("TypeExpCS", 79,
				createSerializationMatchSteps(
					159		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9 */,
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					160		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54 */,
					161		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 20 */,
					95		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					72		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
					42		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
					90		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
				),
				createSerializationSteps(
					41		/* TypeNameExpCS::ownedPathName=54 || value */,
					117		/* V00*7-steps || value */,
					10		/* TypeNameExpCS::ownedCurlyBracketedClause=9 || value */,
					121		/* V01*5-steps || value */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					43		/* TypeNameExpCS::ownedPatternGuard=20 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */,
					122		/* V02*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						3) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						15) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(9, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(54, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[88] =
			new SerializationRule("TypeLiteralExpCS", 82,
				createSerializationMatchSteps(
					158		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83 */,
					41		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					57		/* TypeLiteralExpCS::ownedType=83 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						32) /* TypeLiteralWithMultiplicityCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[89] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 83,
				createSerializationMatchSteps(
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					73		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					36		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					2		/* PrimitiveTypeRefCS::name=61 || soft-space value soft-space */,
					112		/* V00*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[90] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 83,
				createSerializationMatchSteps(
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					105		/* check-rule basecs::TupleTypeCS.ownedParts : 77 */,
					97		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					40		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					56		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					82		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					93		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					87		/* 'Tuple' || soft-space value soft-space */,
					113		/* V00*10-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					121		/* V01*5-steps || value */,
					39		/* TupleTypeCS::ownedParts+=77 || value */,
					123		/* V02*3-steps || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					39		/* TupleTypeCS::ownedParts+=77 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */,
					124		/* V03*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						5	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						28) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(77, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[91] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 83,
				createSerializationMatchSteps(
					123		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40 */,
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					124		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 80 */,
					95		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					61		/* assign V0 = |CollectionTypeCS::ownedType| */,
					5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					85		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					1		/* CollectionTypeCS::name=8 || soft-space value soft-space */,
					117		/* V00*7-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					54		/* CollectionTypeCS::ownedType=80 || value */,
					119		/* V01*1-steps || value */,
					6		/* CollectionTypeCS::ownedCollectionMultiplicity=40 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */,
					122		/* V02*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						30) /* TypeExpWithoutMultiplicityCS */
				},
				new /*@NonNull*/ EAttribute [] {
					EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(80, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[92] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 83,
				createSerializationMatchSteps(
					142		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79 */,
					108		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
					143		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 79 */,
					91		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					64		/* assign V0 = |MapTypeCS::ownedValueType| */,
					19		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					18		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					86		/* 'Map' || soft-space value soft-space */,
					118		/* V00*8-steps || value */,
					107		/* 1*1-steps || value */,
					72		/* '(' || no-space value no-space */,
					26		/* MapTypeCS::ownedKeyType=79 || value */,
					107		/* 1*1-steps || value */,
					76		/* ',' || no-space value soft-space */,
					61		/* MapTypeCS::ownedValueType=79 || value */,
					107		/* 1*1-steps || value */,
					73		/* ')' || no-space value */,
					119		/* V01*1-steps || value */,
					30		/* TypedRefCS::ownedMultiplicity=40 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						4	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						29) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						10) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						29) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(4 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] }
		serializationRules[93] =
			new SerializationRule("TypeNameExpCS", 84,
				createSerializationMatchSteps(
					159		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9 */,
					160		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54 */,
					161		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 20 */,
					72		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
					42		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
					90		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
				),
				createSerializationSteps(
					41		/* TypeNameExpCS::ownedPathName=54 || value */,
					117		/* V00*7-steps || value */,
					10		/* TypeNameExpCS::ownedCurlyBracketedClause=9 || value */,
					121		/* V01*5-steps || value */,
					107		/* 1*1-steps || value */,
					102		/* '{' || soft-space value push soft-new-line */,
					43		/* TypeNameExpCS::ownedPatternGuard=20 || value */,
					107		/* 1*1-steps || value */,
					106		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						3) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						15) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						5) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(9, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(54, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::URIFirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[94] =
			new SerializationRule("URIFirstPathElementCS", 91,
				createSerializationMatchSteps(
					33		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					140		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
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
		// EssentialOCL::URIFirstPathElementCS(basecs::PathElementWithURICS): referredElement=URI
		serializationRules[95] =
			new SerializationRule("URIFirstPathElementCS", 91,
				createSerializationMatchSteps(
					33		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					138		/* PathElementCS::referredElement=URI || soft-space value soft-space */
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
		// EssentialOCL::URIPathNameCS(basecs::PathNameCS): { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[96] =
			new SerializationRule("URIPathNameCS", 92,
				createSerializationMatchSteps(
					100		/* check-rule basecs::PathNameCS.ownedPathElements : 51|91 */,
					50		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					134		/* PathNameCS::ownedPathElements+=91 || value */,
					114		/* V00*3-steps || value */,
					107		/* 1*1-steps || value */,
					79		/* '::' || no-space value no-space */,
					133		/* PathNameCS::ownedPathElements+=51 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						40) /* NextPathElementCS|URIFirstPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(51, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(91, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): "*"
		serializationRules[97] =
			new SerializationRule("UnlimitedNaturalLiteralExpCS", 94,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					107		/* 1*1-steps || value */,
					74		/* '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[5] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[6] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[7] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[8] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSerializationSteps() {
		// MultiplicityBoundsCS::lowerBound=29 || soft-space value soft-space
		serializationSteps[0] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 29 /*LOWER*/, 6);
		// CollectionTypeCS::name=8 || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 8 /*CollectionTypeIdentifier*/, 6);
		// PrimitiveTypeRefCS::name=61 || soft-space value soft-space
		serializationSteps[2] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 61 /*PrimitiveTypeIdentifier*/, 6);
		// TemplateParameterSubstitutionCS::ownedActualParameter=86 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[3] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 86 /*TypeRefCS*/, 2);
		// TypedTypeRefCS::ownedBinding=72 || value
		serializationSteps[4] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 72 /*TemplateBindingCS*/, 0);
		// NavigatingArgCS::ownedCoIterator=3 || value
		serializationSteps[5] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3 /*CoIteratorVariableCS*/, 0);
		// CollectionTypeCS::ownedCollectionMultiplicity=40 || value
		serializationSteps[6] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 40 /*MultiplicityCS*/, 0);
		// IfExpCS::ownedCondition=20|55 || value
		serializationSteps[7] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, -1, new @NonNull Integer [] { 20/*ExpCS*/,55/*PatternExpCS*/}, 0);
		// IfThenExpCS::ownedCondition=20 || value
		serializationSteps[8] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 20 /*ExpCS*/, 0);
		// AbstractNameExpCS::ownedCurlyBracketedClause=9 || value
		serializationSteps[9] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 9 /*CurlyBracketedClauseCS*/, 0);
		// TypeNameExpCS::ownedCurlyBracketedClause=9 || value
		serializationSteps[10] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 9 /*CurlyBracketedClauseCS*/, 0);
		// IfExpCS::ownedElseExpression=20 || value
		serializationSteps[11] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 20 /*ExpCS*/, 0);
		// CollectionLiteralPartCS::ownedExpression=20 || value
		serializationSteps[12] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 20 /*ExpCS*/, 0);
		// CollectionLiteralPartCS::ownedExpression=55 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[13] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 55 /*PatternExpCS*/, 2);
		// ContextCS::ownedExpression=20 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[14] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 20 /*ExpCS*/, 2);
		// NestedExpCS::ownedExpression=20 || value
		serializationSteps[15] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 20 /*ExpCS*/, 0);
		// LambdaLiteralExpCS::ownedExpressionCS=20 || value
		serializationSteps[16] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 20 /*ExpCS*/, 0);
		// TypeParameterCS::ownedExtends+=87 || value
		serializationSteps[17] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 87 /*TypedRefCS*/, 0);
		// WildcardTypeRefCS::ownedExtends=87 || value
		serializationSteps[18] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 87 /*TypedRefCS*/, 0);
		// IfExpCS::ownedIfThenExpressions+=13 || value
		serializationSteps[19] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 13 /*ElseIfThenExpCS*/, 0);
		// LetExpCS::ownedInExpression=20 || value
		serializationSteps[20] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 20 /*ExpCS*/, 0);
		// NavigatingArgCS::ownedInitExpression=20 || value
		serializationSteps[21] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 20 /*ExpCS*/, 0);
		// ShadowPartCS::ownedInitExpression=20|55 || value
		serializationSteps[22] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new @NonNull Integer [] { 20/*ExpCS*/,55/*PatternExpCS*/}, 0);
		// ShadowPartCS::ownedInitExpression=71 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 71 /*StringLiteralExpCS*/, 2);
		// VariableCS::ownedInitExpression=20 || value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 20 /*ExpCS*/, 0);
		// MapLiteralPartCS::ownedKey=20 || value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 20 /*ExpCS*/, 0);
		// MapTypeCS::ownedKeyType=79 || value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 79 /*TypeExpCS*/, 0);
		// CollectionLiteralPartCS::ownedLastExpression=20 || value
		serializationSteps[27] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 20 /*ExpCS*/, 0);
		// InfixExpCS::ownedLeft=57 || value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 57 /*PrefixedPrimaryExpCS*/, 0);
		// TemplateBindingCS::ownedMultiplicity=40 || value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 40 /*MultiplicityCS*/, 0);
		// TypedRefCS::ownedMultiplicity=40 || value
		serializationSteps[30] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 40 /*MultiplicityCS*/, 0);
		// NavigatingArgCS::ownedNameExpression=45 || value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 45 /*NavigatingArgExpCS*/, 0);
		// NavigatingArgCS::ownedNameExpression=45 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 45 /*NavigatingArgExpCS*/, 2);
		// TemplateSignatureCS::ownedParameters+=85 || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 85 /*TypeParameterCS*/, 0);
		// CollectionLiteralExpCS::ownedParts+=5 || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 5 /*CollectionLiteralPartCS*/, 0);
		// CollectionPatternCS::ownedParts+=55 || value
		serializationSteps[35] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 55 /*PatternExpCS*/, 0);
		// CurlyBracketedClauseCS::ownedParts+=67 || value
		serializationSteps[36] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 67 /*ShadowPartCS*/, 0);
		// MapLiteralExpCS::ownedParts+=36 || value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 36 /*MapLiteralPartCS*/, 0);
		// TupleLiteralExpCS::ownedParts+=76 || value
		serializationSteps[38] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 76 /*TupleLiteralPartCS*/, 0);
		// TupleTypeCS::ownedParts+=77 || value
		serializationSteps[39] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 77 /*TuplePartCS*/, 0);
		// AbstractNameExpCS::ownedPathName=54 || value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 54 /*PathNameCS*/, 0);
		// TypeNameExpCS::ownedPathName=54 || value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 54 /*PathNameCS*/, 0);
		// TypedTypeRefCS::ownedPathName=54 || value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 54 /*PathNameCS*/, 0);
		// TypeNameExpCS::ownedPatternGuard=20 || value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 20 /*ExpCS*/, 0);
		// PatternExpCS::ownedPatternType=79 || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 79 /*TypeExpCS*/, 0);
		// AbstractNameExpCS::ownedRoundBracketedClause=62 || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 62 /*RoundBracketedClauseCS*/, 0);
		// LetVariableCS::ownedRoundBracketedClause=62 || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 62 /*RoundBracketedClauseCS*/, 0);
		// AbstractNameExpCS::ownedSquareBracketedClauses+=69 || value
		serializationSteps[47] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 69 /*SquareBracketedClauseCS*/, 0);
		// TemplateBindingCS::ownedSubstitutions+=73 || value
		serializationSteps[48] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 73 /*TemplateParameterSubstitutionCS*/, 0);
		// SquareBracketedClauseCS::ownedTerms+=20 || value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 20 /*ExpCS*/, 0);
		// IfExpCS::ownedThenExpression=20 || value
		serializationSteps[50] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 20 /*ExpCS*/, 0);
		// IfThenExpCS::ownedThenExpression=20 || value
		serializationSteps[51] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 20 /*ExpCS*/, 0);
		// CollectionLiteralExpCS::ownedType=7 || value
		serializationSteps[52] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 7 /*CollectionTypeCS*/, 0);
		// CollectionPatternCS::ownedType=7 || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 7 /*CollectionTypeCS*/, 0);
		// CollectionTypeCS::ownedType=80 || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 80 /*TypeExpWithoutMultiplicityCS*/, 0);
		// MapLiteralExpCS::ownedType=37 || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 37 /*MapTypeCS*/, 0);
		// NavigatingArgCS::ownedType=79 || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 79 /*TypeExpCS*/, 0);
		// TypeLiteralExpCS::ownedType=83 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 83 /*TypeLiteralWithMultiplicityCS*/, 2);
		// TypedElementCS::ownedType=79 || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 79 /*TypeExpCS*/, 0);
		// VariableCS::ownedType=79 || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 79 /*TypeExpCS*/, 0);
		// MapLiteralPartCS::ownedValue=20 || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 20 /*ExpCS*/, 0);
		// MapTypeCS::ownedValueType=79 || value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 79 /*TypeExpCS*/, 0);
		// LetExpCS::ownedVariables+=32 || value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 32 /*LetVariableCS*/, 0);
		// PatternExpCS::patternVariableName=96 || soft-space value soft-space
		serializationSteps[63] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 96 /*UnrestrictedName*/, 6);
		// ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[64] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 6);
		// CollectionPatternCS::restVariableName=24 || soft-space value soft-space
		serializationSteps[65] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 24 /*Identifier*/, 6);
		// StringLiteralExpCS::segments+=70 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 70 /*StringLiteral*/, 2);
		// MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[67] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 6);
		// BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[68] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 6 /* 'false|true' */, 2);
		// NumberLiteralExpCS::symbol=42 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[69] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 42 /*NUMBER_LITERAL*/, 2);
		// MultiplicityBoundsCS::upperBound=89 || soft-space value soft-space
		serializationSteps[70] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 89 /*UPPER*/, 6);
		// '&&' || soft-space value soft-space
		serializationSteps[71] = createSerializationStepKeyword("&&", 6);
		// '(' || no-space value no-space
		serializationSteps[72] = createSerializationStepKeyword("(", 3);
		// ')' || no-space value
		serializationSteps[73] = createSerializationStepKeyword(")", 1);
		// '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[74] = createSerializationStepKeyword("*", 2);
		// '++' || soft-space value soft-space
		serializationSteps[75] = createSerializationStepKeyword("++", 6);
		// ',' || no-space value soft-space
		serializationSteps[76] = createSerializationStepKeyword(",", 5);
		// '..' || no-space value no-space
		serializationSteps[77] = createSerializationStepKeyword("..", 3);
		// ':' || soft-space value soft-space
		serializationSteps[78] = createSerializationStepKeyword(":", 6);
		// '::' || no-space value no-space
		serializationSteps[79] = createSerializationStepKeyword("::", 3);
		// ';' || no-space value soft-new-line
		serializationSteps[80] = createSerializationStepKeyword(";", 4);
		// '<-' || soft-space value soft-space
		serializationSteps[81] = createSerializationStepKeyword("<-", 6);
		// '=' || soft-space value soft-space
		serializationSteps[82] = createSerializationStepKeyword("=", 6);
		// '?' || soft-space value soft-space
		serializationSteps[83] = createSerializationStepKeyword("?", 6);
		// '@' || soft-space value soft-space
		serializationSteps[84] = createSerializationStepKeyword("@", 6);
		// 'Lambda' || soft-space value soft-space
		serializationSteps[85] = createSerializationStepKeyword("Lambda", 6);
		// 'Map' || soft-space value soft-space
		serializationSteps[86] = createSerializationStepKeyword("Map", 6);
		// 'Tuple' || soft-space value soft-space
		serializationSteps[87] = createSerializationStepKeyword("Tuple", 6);
		// '[' || no-space value no-space
		serializationSteps[88] = createSerializationStepKeyword("[", 3);
		// ']' || no-space value
		serializationSteps[89] = createSerializationStepKeyword("]", 1);
		// 'else' || soft-space value soft-space
		serializationSteps[90] = createSerializationStepKeyword("else", 6);
		// 'elseif' || soft-space value soft-space
		serializationSteps[91] = createSerializationStepKeyword("elseif", 6);
		// 'endif' || soft-space value soft-space
		serializationSteps[92] = createSerializationStepKeyword("endif", 6);
		// 'extends' || soft-space value soft-space
		serializationSteps[93] = createSerializationStepKeyword("extends", 6);
		// 'if' || soft-space value soft-space
		serializationSteps[94] = createSerializationStepKeyword("if", 6);
		// 'in' || soft-space value soft-space
		serializationSteps[95] = createSerializationStepKeyword("in", 6);
		// 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[96] = createSerializationStepKeyword("invalid", 2);
		// 'let' || soft-space value soft-space
		serializationSteps[97] = createSerializationStepKeyword("let", 6);
		// 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[98] = createSerializationStepKeyword("null", 2);
		// 'pre' || soft-space value soft-space
		serializationSteps[99] = createSerializationStepKeyword("pre", 6);
		// 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[100] = createSerializationStepKeyword("self", 2);
		// 'then' || soft-space value soft-space
		serializationSteps[101] = createSerializationStepKeyword("then", 6);
		// '{' || soft-space value push soft-new-line
		serializationSteps[102] = createSerializationStepKeyword("{", 8);
		// '|' || soft-space value soft-space
		serializationSteps[103] = createSerializationStepKeyword("|", 6);
		// '|1' || soft-space value soft-space
		serializationSteps[104] = createSerializationStepKeyword("|1", 6);
		// '|?' || soft-space value soft-space
		serializationSteps[105] = createSerializationStepKeyword("|?", 6);
		// '}' || pop soft-space value soft-new-line
		serializationSteps[106] = createSerializationStepKeyword("}", 7);
		// 1*1-steps || value
		serializationSteps[107] = createSerializationStepSequence(-1, 1, 0);
		// 1*11-steps || value
		serializationSteps[108] = createSerializationStepSequence(-1, 11, 0);
		// 1*3-steps || value
		serializationSteps[109] = createSerializationStepSequence(-1, 3, 0);
		// 1*4-steps || value
		serializationSteps[110] = createSerializationStepSequence(-1, 4, 0);
		// 1*7-steps || value
		serializationSteps[111] = createSerializationStepSequence(-1, 7, 0);
		// V00*1-steps || value
		serializationSteps[112] = createSerializationStepSequence(0, 1, 0);
		// V00*10-steps || value
		serializationSteps[113] = createSerializationStepSequence(0, 10, 0);
		// V00*3-steps || value
		serializationSteps[114] = createSerializationStepSequence(0, 3, 0);
		// V00*4-steps || value
		serializationSteps[115] = createSerializationStepSequence(0, 4, 0);
		// V00*5-steps || value
		serializationSteps[116] = createSerializationStepSequence(0, 5, 0);
		// V00*7-steps || value
		serializationSteps[117] = createSerializationStepSequence(0, 7, 0);
		// V00*8-steps || value
		serializationSteps[118] = createSerializationStepSequence(0, 8, 0);
		// V01*1-steps || value
		serializationSteps[119] = createSerializationStepSequence(1, 1, 0);
		// V01*3-steps || value
		serializationSteps[120] = createSerializationStepSequence(1, 3, 0);
		// V01*5-steps || value
		serializationSteps[121] = createSerializationStepSequence(1, 5, 0);
		// V02*1-steps || value
		serializationSteps[122] = createSerializationStepSequence(2, 1, 0);
		// V02*3-steps || value
		serializationSteps[123] = createSerializationStepSequence(2, 3, 0);
		// V03*1-steps || value
		serializationSteps[124] = createSerializationStepSequence(3, 1, 0);
		// V03*4-steps || value
		serializationSteps[125] = createSerializationStepSequence(3, 4, 0);
		// wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[126] = createSerializationStepWrapper(2);
		// NamedElementCS::name=1 || soft-space value soft-space
		serializationSteps[127] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 1 /*BinaryOperatorName*/, 6);
		// NamedElementCS::name=93 || soft-space value soft-space
		serializationSteps[128] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 93 /*UnaryOperatorName*/, 6);
		// NamedElementCS::name=96 || soft-space value soft-space
		serializationSteps[129] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 96 /*UnrestrictedName*/, 6);
		// RoundBracketedClauseCS::ownedArguments+=44 || value
		serializationSteps[130] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 44 /*NavigatingArgCS*/, 0);
		// RoundBracketedClauseCS::ownedArguments+=47|48|46 || value
		serializationSteps[131] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new @NonNull Integer [] { 47/*NavigatingCommaArgCS*/,48/*NavigatingSemiArgCS*/,46/*NavigatingBarArgCS*/}, 0);
		// PathNameCS::ownedPathElements+=21 || value
		serializationSteps[132] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 21 /*FirstPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=51 || value
		serializationSteps[133] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 51 /*NextPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=91 || value
		serializationSteps[134] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 91 /*URIFirstPathElementCS*/, 0);
		// OperatorExpCS::ownedRight=20 || value
		serializationSteps[135] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 20 /*ExpCS*/, 0);
		// OperatorExpCS::ownedRight=56 || value
		serializationSteps[136] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 56 /*PrefixedLetExpCS*/, 0);
		// OperatorExpCS::ownedRight=57 || value
		serializationSteps[137] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 57 /*PrefixedPrimaryExpCS*/, 0);
		// PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[138] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 6);
		// PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[139] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 6);
		// PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[140] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 6);
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
//	import SerializationMatchTermVariable;
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
//	import EssentialOCLCSPackage;
