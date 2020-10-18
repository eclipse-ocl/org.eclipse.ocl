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
package org.eclipse.ocl.xtext.oclstdlib.serializer;

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
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSPackage;

public class OCLstdlibSerializationMetaData extends AbstractSerializationMetaData
{
	private boolean initialized = false;
	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[68];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[20];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[131];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[80];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[264];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[218];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[135];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [10] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[239];

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
		return 100;
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
		return 99;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 162;
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
			initSerializationRules2();
			initGrammarRuleValues();
			initEClassValues();
		}
	}

	/**
	 * Initialize configuration for each EClass that may be serialized.
	 */
	private void initEClassValues() {
		eClassValues[0] = new EClassValue(BaseCSPackage.Literals.ANNOTATION_CS,
			createSerializationRules(
				97 /* { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */,
				98 /* { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" ownedAnnotations+=AnnotationElementCS "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					7) /* DetailCS */
			}
		);
		eClassValues[1] = new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
			createSerializationRules(
				16 /* symbol={'false|true'} */,
				25 /* symbol={'false|true'} */
			), null
		);
		eClassValues[2] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
			createSerializationRules(
				18 /* { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				26 /* { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					4) /* CollectionLiteralPartCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					5) /* CollectionTypeCS */
			}
		);
		eClassValues[3] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
			createSerializationRules(
				19 /* ownedExpression=PatternExpCS */,
				20 /* { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					78) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[4] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
			createSerializationRules(
				21 /* { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */,
				82 /* { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					35) /* PatternExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					5) /* CollectionTypeCS */
			}
		);
		eClassValues[5] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
			createSerializationRules(
				22 /* { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				83 /* { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				89 /* { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					61) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[6] = new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
			createSerializationRules(
				50 /* ownedExpression=ExpCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[7] = new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				23 /* { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					47) /* ShadowPartCS */
			}
		);
		eClassValues[8] = new EClassValue(BaseCSPackage.Literals.DETAIL_CS,
			createSerializationRules(
				99 /* { name=(Name|SINGLE_QUOTED_STRING) "=" values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
			), null
		);
		eClassValues[9] = new EClassValue(BaseCSPackage.Literals.DOCUMENTATION_CS,
			createSerializationRules(
				100 /* { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					7) /* DetailCS */
			}
		);
		eClassValues[10] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
			createSerializationRules(
				27 /* "*" */,
				28 /* "invalid" */,
				29 /* "null" */,
				30 /* "self" */
			), null
		);
		eClassValues[11] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
			createSerializationRules(
				126 /* ownedExpression=ExpCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[12] = new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
			createSerializationRules(
				31 /* { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				42 /* { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					78) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					9) /* ElseIfThenExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[13] = new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
			createSerializationRules(
				24 /* { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[14] = new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
			createSerializationRules(
				101 /* { "import" { name=Identifier ":" }[?] ownedPathName=URIPathNameCS isAll="::*"[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					71) /* URIPathNameCS */
			}
		);
		eClassValues[15] = new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
			createSerializationRules(
				32 /* { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[16] = new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
			createSerializationRules(
				43 /* "invalid" */
			), null
		);
		eClassValues[17] = new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
			createSerializationRules(
				33 /* { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				44 /* { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[18] = new EClassValue(BaseCSPackage.Literals.LAMBDA_TYPE_CS,
			createSerializationRules(
				105 /* { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS } */,
				128 /* { { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
					15) /* LambdaContextTypeRefCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
					67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
					68) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					53) /* TemplateSignatureCS */
			}
		);
		eClassValues[19] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
			createSerializationRules(
				45 /* { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					16) /* LetVariableCS */
			}
		);
		eClassValues[20] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
			createSerializationRules(
				46 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					43) /* RoundBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[21] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_CLASS_CS,
			createSerializationRules(
				106 /* { isAbstract="abstract"[?] "type" name=AnyName ownedSignature=TemplateSignatureCS[?] { ":" metaclassName=AnyName }[?] { "conformsTo" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] "{" { ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					13) /* InvCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					31) /* LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					22) /* LibPropertyCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					53) /* TemplateSignatureCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					68) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[22] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_COERCION_CS,
			createSerializationRules(
				107 /* { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				108 /* { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					38) /* PreCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					37) /* PostCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[23] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_CONSTRAINT_CS,
			createSerializationRules(
				102 /* { stereotype="inv" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" } */,
				122 /* { stereotype="post" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" } */,
				123 /* { stereotype="pre" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					48) /* SpecificationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					48) /* SpecificationCS */
			}
		);
		eClassValues[24] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS,
			createSerializationRules(
				109 /* { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulators+=AccumulatorCS { "," ownedAccumulators+=AccumulatorCS }[*] }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating="invalidating"[?] isValidating="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				110 /* { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulators+=AccumulatorCS { "," ownedAccumulators+=AccumulatorCS }[*] }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating="invalidating"[?] isValidating="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
					0) /* AccumulatorCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
					14) /* IteratorCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					33) /* ParameterCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					38) /* PreCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					37) /* PostCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					53) /* TemplateSignatureCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[25] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS,
			createSerializationRules(
				111 /* { isStatic="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating="validating"[?] isInvalidating="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				112 /* { isStatic="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating="validating"[?] isInvalidating="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS ";" }[*] ownedPostconditions+=PostCS[*] ownedPreconditions+=PreCS[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					48) /* SpecificationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					33) /* ParameterCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					37) /* PostCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					38) /* PreCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					53) /* TemplateSignatureCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[26] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_OPPOSITE_CS,
			createSerializationRules(
				113 /* { "opposite" name=Name ":" ownedType=TypedMultiplicityRefCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[27] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS,
			createSerializationRules(
				114 /* { "library" name=Name { ":" nsPrefix=Identifier "=" nsURI=URI }[?] "{" { ownedPackages+=PackageCS[*] { "precedence" ownedPrecedences+=PrecedenceCS[+] ";" }[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					17) /* ClassCS|LibClassCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					32) /* PackageCS */,
				createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES,
					39) /* PrecedenceCS */
			}
		);
		eClassValues[28] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS,
			createSerializationRules(
				117 /* { isStatic="static"[?] "property" name=Name ":" ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				118 /* { isStatic="static"[?] "property" name=Name ":" ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
					18) /* LibOppositeCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[29] = new EClassValue(OCLstdlibCSPackage.Literals.LIB_ROOT_PACKAGE_CS,
			createSerializationRules(
				119 /* { { ownedImports+=ImportCS ";" }[*] ownedPackages+=LibPackageCS[*] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					12) /* ImportCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					19) /* LibPackageCS */
			}
		);
		eClassValues[30] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
			createSerializationRules(
				34 /* { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				47 /* { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					23) /* MapLiteralPartCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					24) /* MapTypeCS */
			}
		);
		eClassValues[31] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
			createSerializationRules(
				48 /* { ownedKey=ExpCS "<-" ownedValue=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[32] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
			createSerializationRules(
				49 /* { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				84 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				90 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				132 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[33] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */,
				2 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" } */,
				3 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" } */,
				4 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree="|1"[?] "]" } */
			), null
		);
		eClassValues[34] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				5 /* { "[" stringBounds={'*|+|?'} "]" } */,
				6 /* { "[" stringBounds={'*|+|?'} "|?" "]" } */,
				7 /* { "[" stringBounds={'*|+|?'} isNullFree="|1"[?] "]" } */,
				8 /* stringBounds={'*|+|?'} */
			), null
		);
		eClassValues[35] = new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
			createSerializationRules(
				35 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				51 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					6) /* CurlyBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					34) /* PathNameCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					43) /* RoundBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					49) /* SquareBracketedClauseCS */
			}
		);
		eClassValues[36] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
			createSerializationRules(
				52 /* ownedNameExpression=NavigatingArgExpCS */,
				53 /* { ":" ownedType=TypeExpCS } */,
				55 /* { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				54 /* { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				56 /* { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */,
				57 /* { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */,
				61 /* { prefix="," ownedNameExpression=NavigatingArgExpCS } */,
				59 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				58 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				60 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */,
				62 /* { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					3) /* CoIteratorVariableCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					77) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[37] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				36 /* { "(" ownedExpression=ExpCS ")" } */,
				63 /* { "(" ownedExpression=ExpCS ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[38] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				64 /* "null" */
			), null
		);
		eClassValues[39] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				37 /* symbol=NUMBER_LITERAL */,
				65 /* symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[40] = new EClassValue(BaseCSPackage.Literals.PACKAGE_CS,
			createSerializationRules(
				120 /* { "package" name=Name { ":" nsPrefix=Identifier "=" nsURI=URI }[?] "{" { ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					17) /* ClassCS|LibClassCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					32) /* PackageCS */
			}
		);
		eClassValues[41] = new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
			createSerializationRules(
				96 /* { name=Identifier ":" ownedType=TypedMultiplicityRefCS } */,
				103 /* { name=Identifier ":" ownedType=TypedMultiplicityRefCS } */,
				121 /* { name=Identifier ":" ownedType=TypedMultiplicityRefCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[42] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* referredElement=UnrestrictedName */,
				9 /* referredElement=UnreservedName */,
				92 /* referredElement=UnrestrictedName */,
				115 /* referredElement=Name */
			), null
		);
		eClassValues[43] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				93 /* referredElement=URI */
			), null
		);
		eClassValues[44] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */,
				74 /* ownedPathElements+=FirstPathElementCS */,
				94 /* { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */,
				116 /* { ownedPathElements+=LibPathElementCS { "::" ownedPathElements+=LibPathElementCS }[*] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					70) /* FirstPathElementCS|LibPathElementCS|NextPathElementCS|URIFirstPathElementCS */
			}
		);
		eClassValues[45] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				66 /* { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[46] = new EClassValue(OCLstdlibCSPackage.Literals.PRECEDENCE_CS,
			createSerializationRules(
				124 /* { "left" ":" name=Name } */,
				125 /* { isRightAssociative="right" ":" name=Name } */
			), null
		);
		eClassValues[47] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				38 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				67 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				68 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					75) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[48] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
			createSerializationRules(
				69 /* name=PrimitiveTypeIdentifier */,
				80 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				87 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */
			}
		);
		eClassValues[49] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				70 /* { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					28) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[50] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				71 /* "self" */
			), null
		);
		eClassValues[51] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				72 /* ownedInitExpression=StringLiteralExpCS */,
				73 /* { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					78) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[52] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				75 /* { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[53] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				39 /* segments+=StringLiteral[+] */,
				76 /* segments+=StringLiteral[+] */
			), null
		);
		eClassValues[54] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					52) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[55] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					79) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[56] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				13 /* { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					63) /* TypeParameterCS */
			}
		);
		eClassValues[57] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				40 /* { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				77 /* { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					54) /* TupleLiteralPartCS */
			}
		);
		eClassValues[58] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
			createSerializationRules(
				78 /* { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[59] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				127 /* { name=Identifier ":" ownedType=TypedMultiplicityRefCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[60] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
			createSerializationRules(
				79 /* { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				81 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				88 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				129 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					55) /* TuplePartCS */
			}
		);
		eClassValues[61] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				41 /* ownedType=TypeLiteralWithMultiplicityCS */,
				86 /* ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[62] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
			createSerializationRules(
				85 /* { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				91 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					6) /* CurlyBracketedClauseCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					34) /* PathNameCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[63] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				14 /* { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					68) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[64] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				133 /* { isTypeof="typeof" "(" ownedPathName=LibPathNameCS ")" } */,
				134 /* { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */,
				104 /* ownedPathName=LibPathNameCS */,
				130 /* { { isTypeof="typeof" "(" ownedPathName=LibPathNameCS ")" } ownedMultiplicity=MultiplicityCS[?] } */,
				131 /* { { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					51) /* TemplateBindingCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					25) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					21) /* LibPathNameCS */
			}
		);
		eClassValues[65] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				95 /* "*" */
			), null
		);
		eClassValues[66] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				17 /* { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[67] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				15 /* { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					68) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
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
		// '::*'
		enumerationValues[2] = new EnumerationValueSingle("::*");
		// ';'
		enumerationValues[3] = new EnumerationValueSingle(";");
		// '@'
		enumerationValues[4] = new EnumerationValueSingle("@");
		// 'Lambda'
		enumerationValues[5] = new EnumerationValueSingle("Lambda");
		// 'Map'
		enumerationValues[6] = new EnumerationValueSingle("Map");
		// 'Tuple'
		enumerationValues[7] = new EnumerationValueSingle("Tuple");
		// 'abstract'
		enumerationValues[8] = new EnumerationValueSingle("abstract");
		// 'false|true'
		enumerationValues[9] = new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		// 'inv'
		enumerationValues[10] = new EnumerationValueSingle("inv");
		// 'invalidating'
		enumerationValues[11] = new EnumerationValueSingle("invalidating");
		// 'post'
		enumerationValues[12] = new EnumerationValueSingle("post");
		// 'pre'
		enumerationValues[13] = new EnumerationValueSingle("pre");
		// 'right'
		enumerationValues[14] = new EnumerationValueSingle("right");
		// 'static'
		enumerationValues[15] = new EnumerationValueSingle("static");
		// 'typeof'
		enumerationValues[16] = new EnumerationValueSingle("typeof");
		// 'validating'
		enumerationValues[17] = new EnumerationValueSingle("validating");
		// '|'
		enumerationValues[18] = new EnumerationValueSingle("|");
		// '|1'
		enumerationValues[19] = new EnumerationValueSingle("|1");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = createParserRuleValue(1, "AccumulatorCS", -1,
			createSerializationRules(
				96	/* AccumulatorCS: { name=Identifier ":" ownedType=TypedMultiplicityRefCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[2] = createParserRuleValue(2, "AnnotationCS", -1,
			createSerializationRules(
				97	/* AnnotationCS: { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */,
				98	/* AnnotationCS: { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" ownedAnnotations+=AnnotationElementCS "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "annotation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=(Identifier|SINGLE_QUOTED_STRING) : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "AnnotationElementCS", 8 /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
			createSerializationRules(
				97	/* AnnotationCS: { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */,
				98	/* AnnotationCS: { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" ownedAnnotations+=AnnotationElementCS "}" } } */,
				100	/* DocumentationCS: { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* AnnotationCS : [value] | [value] */,
			(0 << 16) | 0	/* DocumentationCS : [value] | [value] */
		);
		grammarRuleValues[4] = new DataTypeRuleValue(4, "AnyName");
		grammarRuleValues[5] = new DataTypeRuleValue(5, "BinaryOperatorName");
		grammarRuleValues[6] = createParserRuleValue(6, "BooleanLiteralExpCS", -1,
			createSerializationRules(
				16	/* BooleanLiteralExpCS: symbol={'false|true'} */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* symbol="true" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* symbol="false" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "ClassCS", 17 /* ClassCS|LibClassCS */,
			createSerializationRules(
				106	/* LibClassCS: { isAbstract="abstract"[?] "type" name=AnyName ownedSignature=TemplateSignatureCS[?] { ":" metaclassName=AnyName }[?] { "conformsTo" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] "{" { ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } */
			),
			(0 << 16) | 2	/* LibClassCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[8] = createParserRuleValue(8, "CoIteratorVariableCS", -1,
			createSerializationRules(
				17	/* CoIteratorVariableCS: { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[9] = createParserRuleValue(9, "CollectionLiteralExpCS", -1,
			createSerializationRules(
				18	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[10] = createParserRuleValue(10, "CollectionLiteralPartCS", -1,
			createSerializationRules(
				19	/* CollectionLiteralPartCS: ownedExpression=PatternExpCS */,
				20	/* CollectionLiteralPartCS: { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedLastExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=PatternExpCS : [value] | [value] */
		);
		grammarRuleValues[11] = createParserRuleValue(11, "CollectionPatternCS", -1,
			createSerializationRules(
				21	/* CollectionPatternCS: { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=PatternExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "++" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* restVariableName=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[12] = createParserRuleValue(12, "CollectionTypeCS", -1,
			createSerializationRules(
				22	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* name=CollectionTypeIdentifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedCollectionMultiplicity=MultiplicityCS? : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[13] = new DataTypeRuleValue(13, "CollectionTypeIdentifier");
		grammarRuleValues[14] = createParserRuleValue(14, "CurlyBracketedClauseCS", -1,
			createSerializationRules(
				23	/* CurlyBracketedClauseCS: { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {CurlyBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[15] = new TerminalRuleValue(15, "DOUBLE_QUOTED_STRING");
		grammarRuleValues[16] = createParserRuleValue(16, "DetailCS", -1,
			createSerializationRules(
				99	/* DetailCS: { name=(Name|SINGLE_QUOTED_STRING) "=" values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=(Name|SINGLE_QUOTED_STRING) : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)* : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "DocumentationCS", -1,
			createSerializationRules(
				100	/* DocumentationCS: { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {DocumentationCS} : [value] | [value] */,
			(0 << 16) | 7	/* "documentation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* value=SINGLE_QUOTED_STRING? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[18] = new TerminalRuleValue(18, "ESCAPED_CHARACTER");
		grammarRuleValues[19] = new TerminalRuleValue(19, "ESCAPED_ID");
		grammarRuleValues[20] = createParserRuleValue(20, "ElseIfThenExpCS", -1,
			createSerializationRules(
				24	/* ElseIfThenExpCS: { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "elseif" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "then" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[21] = new DataTypeRuleValue(21, "EssentialOCLInfixOperatorName");
		grammarRuleValues[22] = new DataTypeRuleValue(22, "EssentialOCLNavigationOperatorName");
		grammarRuleValues[23] = new DataTypeRuleValue(23, "EssentialOCLReservedKeyword");
		grammarRuleValues[24] = new DataTypeRuleValue(24, "EssentialOCLUnaryOperatorName");
		grammarRuleValues[25] = new DataTypeRuleValue(25, "EssentialOCLUnreservedName");
		grammarRuleValues[26] = new DataTypeRuleValue(26, "EssentialOCLUnrestrictedName");
		grammarRuleValues[27] = createParserRuleValue(27, "ExpCS", 76 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				25	/* ExpCS: symbol={'false|true'} */,
				26	/* ExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				27	/* ExpCS: "*" */,
				28	/* ExpCS: "invalid" */,
				29	/* ExpCS: "null" */,
				30	/* ExpCS: "self" */,
				31	/* ExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				32	/* ExpCS: { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */,
				33	/* ExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				34	/* ExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				35	/* ExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				36	/* ExpCS: { "(" ownedExpression=ExpCS ")" } */,
				37	/* ExpCS: symbol=NUMBER_LITERAL */,
				38	/* ExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				39	/* ExpCS: segments+=StringLiteral[+] */,
				40	/* ExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				41	/* ExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				45	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				67	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* {InfixExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* name=BinaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedLetExpCS : [value] | [value] */
		);
		grammarRuleValues[28] = createParserRuleValue(28, "FirstPathElementCS", -1,
			createSerializationRules(
				0	/* FirstPathElementCS: referredElement=UnrestrictedName */
			),
			(0 << 16) | 7	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[29] = new DataTypeRuleValue(29, "ID");
		grammarRuleValues[30] = new TerminalRuleValue(30, "INT");
		grammarRuleValues[31] = new DataTypeRuleValue(31, "Identifier");
		grammarRuleValues[32] = createParserRuleValue(32, "IfExpCS", -1,
			createSerializationRules(
				42	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "if" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 7	/* "then" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedIfThenExpressions+=ElseIfThenExpCS* : [value] | [value] */,
			(0 << 16) | 7	/* "else" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedElseExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "endif" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[33] = createParserRuleValue(33, "ImportCS", -1,
			createSerializationRules(
				101	/* ImportCS: { "import" { name=Identifier ":" }[?] ownedPathName=URIPathNameCS isAll="::*"[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "import" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=URIPathNameCS : [value] | [value] */,
			(0 << 16) | 7	/* isAll?="::*"? : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[34] = new DataTypeRuleValue(34, "InfixOperatorName");
		grammarRuleValues[35] = createParserRuleValue(35, "InvCS", -1,
			createSerializationRules(
				102	/* InvCS: { stereotype="inv" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* stereotype="inv" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[36] = createParserRuleValue(36, "InvalidLiteralExpCS", -1,
			createSerializationRules(
				43	/* InvalidLiteralExpCS: "invalid" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {InvalidLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* "invalid" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[37] = createParserRuleValue(37, "IteratorCS", -1,
			createSerializationRules(
				103	/* IteratorCS: { name=Identifier ":" ownedType=TypedMultiplicityRefCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[38] = new TerminalRuleValue(38, "LETTER_CHARACTER");
		grammarRuleValues[39] = new DataTypeRuleValue(39, "LOWER");
		grammarRuleValues[40] = createParserRuleValue(40, "LambdaContextTypeRefCS", -1,
			createSerializationRules(
				104	/* LambdaContextTypeRefCS: ownedPathName=LibPathNameCS */
			),
			(0 << 16) | 0	/* ownedPathName=LibPathNameCS : [value] | [value] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				44	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[42] = createParserRuleValue(42, "LambdaTypeCS", -1,
			createSerializationRules(
				105	/* LambdaTypeCS: { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* name="Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedContextType=LambdaContextTypeRefCS : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameterTypes+=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameterTypes+=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedResultType=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[43] = createParserRuleValue(43, "LetExpCS", -1,
			createSerializationRules(
				45	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "let" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 7	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[44] = createParserRuleValue(44, "LetVariableCS", -1,
			createSerializationRules(
				46	/* LetVariableCS: { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[45] = createParserRuleValue(45, "LibClassCS", -1,
			createSerializationRules(
				106	/* LibClassCS: { isAbstract="abstract"[?] "type" name=AnyName ownedSignature=TemplateSignatureCS[?] { ":" metaclassName=AnyName }[?] { "conformsTo" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] "{" { ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* isAbstract?="abstract"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "type" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=AnyName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* metaclassName=AnyName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "conformsTo" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSuperTypes+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSuperTypes+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedOperations+=OperationCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedProperties+=LibPropertyCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedConstraints+=InvCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[46] = createParserRuleValue(46, "LibCoercionCS", -1,
			createSerializationRules(
				107	/* LibCoercionCS: { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				108	/* LibCoercionCS: { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "coercion" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=>" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* implementation=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPreconditions+=PostCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPostconditions+=PreCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[47] = createParserRuleValue(47, "LibIterationCS", -1,
			createSerializationRules(
				109	/* LibIterationCS: { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulators+=AccumulatorCS { "," ownedAccumulators+=AccumulatorCS }[*] }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating="invalidating"[?] isValidating="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				110	/* LibIterationCS: { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulators+=AccumulatorCS { "," ownedAccumulators+=AccumulatorCS }[*] }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating="invalidating"[?] isValidating="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "iteration" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedIterators+=IteratorCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedIterators+=IteratorCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedAccumulators+=AccumulatorCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedAccumulators+=AccumulatorCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "|" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 7	/* isInvalidating?="invalidating"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* isValidating?="validating"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=>" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* implementation=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPreconditions+=PostCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPostconditions+=PreCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[48] = createParserRuleValue(48, "LibOperationCS", -1,
			createSerializationRules(
				111	/* LibOperationCS: { isStatic="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating="validating"[?] isInvalidating="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				112	/* LibOperationCS: { isStatic="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating="validating"[?] isInvalidating="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS ";" }[*] ownedPostconditions+=PostCS[*] ownedPreconditions+=PreCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* isStatic?="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "operation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 7	/* isValidating?="validating"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* isInvalidating?="invalidating"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "precedence" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* precedence=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=>" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* implementation=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "body" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedBodyExpressions+=SpecificationCS : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedPostconditions+=PostCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPreconditions+=PreCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[49] = createParserRuleValue(49, "LibOppositeCS", -1,
			createSerializationRules(
				113	/* LibOppositeCS: { "opposite" name=Name ":" ownedType=TypedMultiplicityRefCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "opposite" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[50] = createParserRuleValue(50, "LibPackageCS", -1,
			createSerializationRules(
				114	/* LibPackageCS: { "library" name=Name { ":" nsPrefix=Identifier "=" nsURI=URI }[?] "{" { ownedPackages+=PackageCS[*] { "precedence" ownedPrecedences+=PrecedenceCS[+] ";" }[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "library" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* nsPrefix=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* nsURI=URI : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedPackages+=PackageCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "precedence" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPrecedences+=PrecedenceCS+ : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 3	/* ownedClasses+=ClassCS : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[51] = createParserRuleValue(51, "LibPathElementCS", -1,
			createSerializationRules(
				115	/* LibPathElementCS: referredElement=Name */
			),
			(0 << 16) | 7	/* referredElement=Name : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[52] = createParserRuleValue(52, "LibPathNameCS", -1,
			createSerializationRules(
				116	/* LibPathNameCS: { ownedPathElements+=LibPathElementCS { "::" ownedPathElements+=LibPathElementCS }[*] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=LibPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=LibPathElementCS : [value] | [value] */
		);
		grammarRuleValues[53] = createParserRuleValue(53, "LibPropertyCS", -1,
			createSerializationRules(
				117	/* LibPropertyCS: { isStatic="static"[?] "property" name=Name ":" ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				118	/* LibPropertyCS: { isStatic="static"[?] "property" name=Name ":" ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* isStatic?="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "property" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedOpposite=LibOppositeCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=>" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* implementation=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS* : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[54] = createParserRuleValue(54, "Library", -1,
			createSerializationRules(
				119	/* Library: { { ownedImports+=ImportCS ";" }[*] ownedPackages+=LibPackageCS[*] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 0	/* ownedImports+=ImportCS : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedPackages+=LibPackageCS* : [value] | [value] */
		);
		grammarRuleValues[55] = new TerminalRuleValue(55, "ML_COMMENT");
		grammarRuleValues[56] = new TerminalRuleValue(56, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[57] = createParserRuleValue(57, "MapLiteralExpCS", -1,
			createSerializationRules(
				47	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=MapTypeCS : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[58] = createParserRuleValue(58, "MapLiteralPartCS", -1,
			createSerializationRules(
				48	/* MapLiteralPartCS: { ownedKey=ExpCS "<-" ownedValue=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedKey=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValue=ExpCS : [value] | [value] */
		);
		grammarRuleValues[59] = createParserRuleValue(59, "MapTypeCS", -1,
			createSerializationRules(
				49	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* name="Map" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedKeyType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValueType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[60] = createParserRuleValue(60, "Model", -1,
			createSerializationRules(
				50	/* Model: ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[61] = createParserRuleValue(61, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS: { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 7	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[62] = createParserRuleValue(62, "MultiplicityCS", -1,
			createSerializationRules(
				2	/* MultiplicityCS: { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" } */,
				3	/* MultiplicityCS: { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" } */,
				4	/* MultiplicityCS: { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree="|1"[?] "]" } */,
				5	/* MultiplicityCS: { "[" stringBounds={'*|+|?'} "]" } */,
				6	/* MultiplicityCS: { "[" stringBounds={'*|+|?'} "|?" "]" } */,
				7	/* MultiplicityCS: { "[" stringBounds={'*|+|?'} isNullFree="|1"[?] "]" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityBoundsCS : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityStringCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 7	/* "|?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* isNullFree?="|1" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[63] = createParserRuleValue(63, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS: stringBounds={'*|+|?'} */
			),
			(0 << 16) | 7	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[64] = new DataTypeRuleValue(64, "NUMBER_LITERAL");
		grammarRuleValues[65] = new DataTypeRuleValue(65, "Name");
		grammarRuleValues[66] = createParserRuleValue(66, "NameExpCS", -1,
			createSerializationRules(
				51	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedSquareBracketedClauses+=SquareBracketedClauseCS* : [value] | [value] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* isPre?="@" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "pre" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[67] = createParserRuleValue(67, "NavigatingArgCS", -1,
			createSerializationRules(
				52	/* NavigatingArgCS: ownedNameExpression=NavigatingArgExpCS */,
				53	/* NavigatingArgCS: { ":" ownedType=TypeExpCS } */,
				54	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				55	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				56	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 7	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[68] = createParserRuleValue(68, "NavigatingArgExpCS", 77 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				25	/* ExpCS: symbol={'false|true'} */,
				26	/* ExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				27	/* ExpCS: "*" */,
				28	/* ExpCS: "invalid" */,
				29	/* ExpCS: "null" */,
				30	/* ExpCS: "self" */,
				31	/* ExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				32	/* ExpCS: { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */,
				33	/* ExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				34	/* ExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				35	/* ExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				36	/* ExpCS: { "(" ownedExpression=ExpCS ")" } */,
				37	/* ExpCS: symbol=NUMBER_LITERAL */,
				38	/* ExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				39	/* ExpCS: segments+=StringLiteral[+] */,
				40	/* ExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				41	/* ExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				45	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				67	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* ExpCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[69] = createParserRuleValue(69, "NavigatingBarArgCS", -1,
			createSerializationRules(
				57	/* NavigatingBarArgCS: { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* prefix="|" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[70] = createParserRuleValue(70, "NavigatingCommaArgCS", -1,
			createSerializationRules(
				61	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS } */,
				58	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				59	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				60	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* prefix="," : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 7	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[71] = createParserRuleValue(71, "NavigatingSemiArgCS", -1,
			createSerializationRules(
				62	/* NavigatingSemiArgCS: { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* prefix=";" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[72] = new DataTypeRuleValue(72, "NavigationOperatorName");
		grammarRuleValues[73] = createParserRuleValue(73, "NestedExpCS", -1,
			createSerializationRules(
				63	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[74] = createParserRuleValue(74, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS: referredElement=UnreservedName */
			),
			(0 << 16) | 7	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[75] = createParserRuleValue(75, "NullLiteralExpCS", -1,
			createSerializationRules(
				64	/* NullLiteralExpCS: "null" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {NullLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* "null" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[76] = createParserRuleValue(76, "NumberLiteralExpCS", -1,
			createSerializationRules(
				65	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */
			),
			(0 << 16) | 2	/* symbol=NUMBER_LITERAL : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[77] = createParserRuleValue(77, "OperationCS", 31 /* LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS */,
			createSerializationRules(
				107	/* LibCoercionCS: { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				108	/* LibCoercionCS: { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } } */,
				109	/* LibIterationCS: { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulators+=AccumulatorCS { "," ownedAccumulators+=AccumulatorCS }[*] }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating="invalidating"[?] isValidating="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				110	/* LibIterationCS: { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulators+=AccumulatorCS { "," ownedAccumulators+=AccumulatorCS }[*] }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating="invalidating"[?] isValidating="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } } */,
				111	/* LibOperationCS: { isStatic="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating="validating"[?] isInvalidating="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" } */,
				112	/* LibOperationCS: { isStatic="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating="validating"[?] isInvalidating="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS ";" }[*] ownedPostconditions+=PostCS[*] ownedPreconditions+=PreCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* LibCoercionCS : [value] | [value] */,
			(0 << 16) | 0	/* LibIterationCS : [value] | [value] */,
			(0 << 16) | 0	/* LibOperationCS : [value] | [value] */
		);
		grammarRuleValues[78] = createParserRuleValue(78, "PackageCS", -1,
			createSerializationRules(
				120	/* PackageCS: { "package" name=Name { ":" nsPrefix=Identifier "=" nsURI=URI }[?] "{" { ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "package" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=Name : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* nsPrefix=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* nsURI=URI : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedPackages+=PackageCS : [value] | [value] */,
			(0 << 16) | 3	/* ownedClasses+=ClassCS : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[79] = createParserRuleValue(79, "ParameterCS", -1,
			createSerializationRules(
				121	/* ParameterCS: { name=Identifier ":" ownedType=TypedMultiplicityRefCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[80] = createParserRuleValue(80, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS: { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[81] = createParserRuleValue(81, "PatternExpCS", -1,
			createSerializationRules(
				66	/* PatternExpCS: { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* patternVariableName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPatternType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[82] = createParserRuleValue(82, "PostCS", -1,
			createSerializationRules(
				122	/* PostCS: { stereotype="post" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* stereotype="post" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[83] = createParserRuleValue(83, "PreCS", -1,
			createSerializationRules(
				123	/* PreCS: { stereotype="pre" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* stereotype="pre" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[84] = createParserRuleValue(84, "PrecedenceCS", -1,
			createSerializationRules(
				124	/* PrecedenceCS: { "left" ":" name=Name } */,
				125	/* PrecedenceCS: { isRightAssociative="right" ":" name=Name } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 7	/* "left" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* isRightAssociative?="right" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=Name : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[85] = createParserRuleValue(85, "PrefixedLetExpCS", 41 /* LetExpCS|PrefixedLetExpCS */,
			createSerializationRules(
				45	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				67	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedLetExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LetExpCS : [value] | [value] */
		);
		grammarRuleValues[86] = createParserRuleValue(86, "PrefixedPrimaryExpCS", 74 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				16	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				18	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				42	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				43	/* InvalidLiteralExpCS: "invalid" */,
				44	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				47	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				51	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				63	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */,
				64	/* NullLiteralExpCS: "null" */,
				65	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				68	/* PrefixedPrimaryExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				71	/* SelfExpCS: "self" */,
				76	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				77	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				86	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				95	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrimaryExpCS : [value] | [value] */
		);
		grammarRuleValues[87] = createParserRuleValue(87, "PrimaryExpCS", 73 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				16	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				18	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				42	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				43	/* InvalidLiteralExpCS: "invalid" */,
				44	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				47	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				51	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				63	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */,
				64	/* NullLiteralExpCS: "null" */,
				65	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				71	/* SelfExpCS: "self" */,
				76	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				77	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				86	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				95	/* UnlimitedNaturalLiteralExpCS: "*" */
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
		grammarRuleValues[88] = createParserRuleValue(88, "PrimitiveLiteralExpCS", 72 /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				16	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				43	/* InvalidLiteralExpCS: "invalid" */,
				64	/* NullLiteralExpCS: "null" */,
				65	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				76	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				95	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* NumberLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* StringLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* BooleanLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* UnlimitedNaturalLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* InvalidLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* NullLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[89] = createParserRuleValue(89, "PrimitiveTypeCS", -1,
			createSerializationRules(
				69	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */
			),
			(0 << 16) | 7	/* name=PrimitiveTypeIdentifier : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[90] = new DataTypeRuleValue(90, "PrimitiveTypeIdentifier");
		grammarRuleValues[91] = new DataTypeRuleValue(91, "RestrictedKeywords");
		grammarRuleValues[92] = createParserRuleValue(92, "RoundBracketedClauseCS", -1,
			createSerializationRules(
				70	/* RoundBracketedClauseCS: { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {RoundBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
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
				71	/* SelfExpCS: "self" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SelfExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* "self" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[97] = createParserRuleValue(97, "ShadowPartCS", -1,
			createSerializationRules(
				72	/* ShadowPartCS: ownedInitExpression=StringLiteralExpCS */,
				73	/* ShadowPartCS: { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* referredProperty=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 0	/* ownedInitExpression=StringLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[98] = createParserRuleValue(98, "SimplePathNameCS", -1,
			createSerializationRules(
				74	/* SimplePathNameCS: ownedPathElements+=FirstPathElementCS */
			),
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */
		);
		grammarRuleValues[99] = createParserRuleValue(99, "SpecificationCS", -1,
			createSerializationRules(
				126	/* SpecificationCS: ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[100] = createParserRuleValue(100, "SquareBracketedClauseCS", -1,
			createSerializationRules(
				75	/* SquareBracketedClauseCS: { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[101] = new DataTypeRuleValue(101, "StringLiteral");
		grammarRuleValues[102] = createParserRuleValue(102, "StringLiteralExpCS", -1,
			createSerializationRules(
				76	/* StringLiteralExpCS: segments+=StringLiteral[+] */
			),
			(0 << 16) | 2	/* segments+=StringLiteral+ : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[103] = createParserRuleValue(103, "TemplateBindingCS", -1,
			createSerializationRules(
				11	/* TemplateBindingCS: { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[104] = createParserRuleValue(104, "TemplateParameterSubstitutionCS", -1,
			createSerializationRules(
				12	/* TemplateParameterSubstitutionCS: ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[105] = createParserRuleValue(105, "TemplateSignatureCS", -1,
			createSerializationRules(
				13	/* TemplateSignatureCS: { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[106] = createParserRuleValue(106, "TupleLiteralExpCS", -1,
			createSerializationRules(
				77	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[107] = createParserRuleValue(107, "TupleLiteralPartCS", -1,
			createSerializationRules(
				78	/* TupleLiteralPartCS: { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[108] = createParserRuleValue(108, "TuplePartCS", -1,
			createSerializationRules(
				127	/* TuplePartCS: { name=Identifier ":" ownedType=TypedMultiplicityRefCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */
		);
		grammarRuleValues[109] = createParserRuleValue(109, "TupleTypeCS", -1,
			createSerializationRules(
				79	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* name="Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[110] = createParserRuleValue(110, "TypeExpCS", 62 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				80	/* TypeExpCS: { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				81	/* TypeExpCS: { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				82	/* TypeExpCS: { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] } */,
				83	/* TypeExpCS: { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				84	/* TypeExpCS: { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				85	/* TypeExpCS: { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[111] = createParserRuleValue(111, "TypeExpWithoutMultiplicityCS", 61 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				21	/* CollectionPatternCS: { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */,
				22	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				49	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				69	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				79	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				91	/* TypeNameExpCS: { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeNameExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionPatternCS : [value] | [value] */
		);
		grammarRuleValues[112] = createParserRuleValue(112, "TypeLiteralCS", 58 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */,
			createSerializationRules(
				22	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				49	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				69	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				79	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */
		);
		grammarRuleValues[113] = createParserRuleValue(113, "TypeLiteralExpCS", -1,
			createSerializationRules(
				86	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */
			),
			(0 << 16) | 2	/* ownedType=TypeLiteralWithMultiplicityCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[114] = createParserRuleValue(114, "TypeLiteralWithMultiplicityCS", 60 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */,
			createSerializationRules(
				87	/* TypeLiteralWithMultiplicityCS: { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				88	/* TypeLiteralWithMultiplicityCS: { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				89	/* TypeLiteralWithMultiplicityCS: { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				90	/* TypeLiteralWithMultiplicityCS: { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[115] = createParserRuleValue(115, "TypeNameExpCS", -1,
			createSerializationRules(
				91	/* TypeNameExpCS: { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedPatternGuard=ExpCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[116] = createParserRuleValue(116, "TypeParameterCS", -1,
			createSerializationRules(
				14	/* TypeParameterCS: { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 7	/* "&&" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[117] = createParserRuleValue(117, "TypeRefCS", 79 /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				15	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */,
				49	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				79	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				105	/* LambdaTypeCS: { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS } */,
				133	/* TypedTypeRefCS: { isTypeof="typeof" "(" ownedPathName=LibPathNameCS ")" } */,
				134	/* TypedTypeRefCS: { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[118] = createParserRuleValue(118, "TypedMultiplicityRefCS", 67 /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */,
			createSerializationRules(
				128	/* TypedMultiplicityRefCS: { { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS } ownedMultiplicity=MultiplicityCS[?] } */,
				129	/* TypedMultiplicityRefCS: { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				130	/* TypedMultiplicityRefCS: { { isTypeof="typeof" "(" ownedPathName=LibPathNameCS ")" } ownedMultiplicity=MultiplicityCS[?] } */,
				131	/* TypedMultiplicityRefCS: { { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				132	/* TypedMultiplicityRefCS: { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */,
			(0 << 16) | 0	/* LambdaTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[119] = createParserRuleValue(119, "TypedRefCS", 68 /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				49	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				79	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				105	/* LambdaTypeCS: { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS } */,
				133	/* TypedTypeRefCS: { isTypeof="typeof" "(" ownedPathName=LibPathNameCS ")" } */,
				134	/* TypedTypeRefCS: { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */,
			(0 << 16) | 0	/* LambdaTypeCS : [value] | [value] */
		);
		grammarRuleValues[120] = createParserRuleValue(120, "TypedTypeRefCS", -1,
			createSerializationRules(
				133	/* TypedTypeRefCS: { isTypeof="typeof" "(" ownedPathName=LibPathNameCS ")" } */,
				134	/* TypedTypeRefCS: { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* isTypeof?="typeof" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathName=LibPathNameCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=LibPathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[121] = new DataTypeRuleValue(121, "UPPER");
		grammarRuleValues[122] = new DataTypeRuleValue(122, "URI");
		grammarRuleValues[123] = createParserRuleValue(123, "URIFirstPathElementCS", -1,
			createSerializationRules(
				92	/* URIFirstPathElementCS: referredElement=UnrestrictedName */,
				93	/* URIFirstPathElementCS: referredElement=URI */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 7	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PathElementWithURICS} : [value] | [value] */,
			(0 << 16) | 7	/* referredElement=URI : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[124] = createParserRuleValue(124, "URIPathNameCS", -1,
			createSerializationRules(
				94	/* URIPathNameCS: { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=URIFirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[125] = new DataTypeRuleValue(125, "UnaryOperatorName");
		grammarRuleValues[126] = createParserRuleValue(126, "UnlimitedNaturalLiteralExpCS", -1,
			createSerializationRules(
				95	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {UnlimitedNaturalLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* "*" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[127] = new DataTypeRuleValue(127, "UnreservedName");
		grammarRuleValues[128] = new DataTypeRuleValue(128, "UnrestrictedName");
		grammarRuleValues[129] = new TerminalRuleValue(129, "WS");
		grammarRuleValues[130] = createParserRuleValue(130, "WildcardTypeRefCS", -1,
			createSerializationRules(
				15	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WildcardTypeRefCS} : [value] | [value] */,
			(0 << 16) | 7	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends=TypedRefCS : [value] | [value] */
		);
	}

	/**
	 * Initialize bit vectors of useful grammar rule combinations.
	 */
	private void initGrammarRuleVectors() {
		// AccumulatorCS
		grammarRuleVectors[0] = new GrammarRuleVector(0x2L);
		// AnnotationElementCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x8L);
		// ClassCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x80L);
		// CoIteratorVariableCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x100L);
		// CollectionLiteralPartCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x400L);
		// CollectionTypeCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x1000L);
		// CurlyBracketedClauseCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x4000L);
		// DetailCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x10000L);
		// AnnotationCS|AnnotationElementCS|DocumentationCS
		grammarRuleVectors[8] = new GrammarRuleVector(0x2000cL);
		// ElseIfThenExpCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x100000L);
		// ExpCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x8000000L);
		// FirstPathElementCS
		grammarRuleVectors[11] = new GrammarRuleVector(0x10000000L);
		// ImportCS
		grammarRuleVectors[12] = new GrammarRuleVector(0x200000000L);
		// InvCS
		grammarRuleVectors[13] = new GrammarRuleVector(0x800000000L);
		// IteratorCS
		grammarRuleVectors[14] = new GrammarRuleVector(0x2000000000L);
		// LambdaContextTypeRefCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x10000000000L);
		// LetVariableCS
		grammarRuleVectors[16] = new GrammarRuleVector(0x100000000000L);
		// ClassCS|LibClassCS
		grammarRuleVectors[17] = new GrammarRuleVector(0x200000000080L);
		// LibOppositeCS
		grammarRuleVectors[18] = new GrammarRuleVector(0x2000000000000L);
		// LibPackageCS
		grammarRuleVectors[19] = new GrammarRuleVector(0x4000000000000L);
		// LibPathElementCS
		grammarRuleVectors[20] = new GrammarRuleVector(0x8000000000000L);
		// LibPathNameCS
		grammarRuleVectors[21] = new GrammarRuleVector(0x10000000000000L);
		// LibPropertyCS
		grammarRuleVectors[22] = new GrammarRuleVector(0x20000000000000L);
		// MapLiteralPartCS
		grammarRuleVectors[23] = new GrammarRuleVector(0x400000000000000L);
		// MapTypeCS
		grammarRuleVectors[24] = new GrammarRuleVector(0x800000000000000L);
		// MultiplicityCS
		grammarRuleVectors[25] = new GrammarRuleVector(0x4000000000000000L);
		// NavigatingArgExpCS
		grammarRuleVectors[26] = new GrammarRuleVector(0x0L,0x10L);
		// NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[27] = new GrammarRuleVector(0x0L,0xe0L);
		// NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[28] = new GrammarRuleVector(0x0L,0xe8L);
		// FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[29] = new GrammarRuleVector(0x10000000L,0x400L);
		// OperationCS
		grammarRuleVectors[30] = new GrammarRuleVector(0x0L,0x2000L);
		// LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS
		grammarRuleVectors[31] = new GrammarRuleVector(0x1c00000000000L,0x2000L);
		// PackageCS
		grammarRuleVectors[32] = new GrammarRuleVector(0x0L,0x4000L);
		// ParameterCS
		grammarRuleVectors[33] = new GrammarRuleVector(0x0L,0x8000L);
		// PathNameCS
		grammarRuleVectors[34] = new GrammarRuleVector(0x0L,0x10000L);
		// PatternExpCS
		grammarRuleVectors[35] = new GrammarRuleVector(0x0L,0x20000L);
		// ExpCS|PatternExpCS
		grammarRuleVectors[36] = new GrammarRuleVector(0x8000000L,0x20000L);
		// PostCS
		grammarRuleVectors[37] = new GrammarRuleVector(0x0L,0x40000L);
		// PreCS
		grammarRuleVectors[38] = new GrammarRuleVector(0x0L,0x80000L);
		// PrecedenceCS
		grammarRuleVectors[39] = new GrammarRuleVector(0x0L,0x100000L);
		// PrefixedLetExpCS
		grammarRuleVectors[40] = new GrammarRuleVector(0x0L,0x200000L);
		// LetExpCS|PrefixedLetExpCS
		grammarRuleVectors[41] = new GrammarRuleVector(0x80000000000L,0x200000L);
		// PrefixedPrimaryExpCS
		grammarRuleVectors[42] = new GrammarRuleVector(0x0L,0x400000L);
		// RoundBracketedClauseCS
		grammarRuleVectors[43] = new GrammarRuleVector(0x0L,0x10000000L);
		// Identifier|SINGLE_QUOTED_STRING
		grammarRuleVectors[44] = new GrammarRuleVector(0x80000000L,0x40000000L);
		// ML_SINGLE_QUOTED_STRING|SINGLE_QUOTED_STRING
		grammarRuleVectors[45] = new GrammarRuleVector(0x100000000000000L,0x40000000L);
		// Name|SINGLE_QUOTED_STRING
		grammarRuleVectors[46] = new GrammarRuleVector(0x0L,0x40000002L);
		// ShadowPartCS
		grammarRuleVectors[47] = new GrammarRuleVector(0x0L,0x200000000L);
		// SpecificationCS
		grammarRuleVectors[48] = new GrammarRuleVector(0x0L,0x800000000L);
		// SquareBracketedClauseCS
		grammarRuleVectors[49] = new GrammarRuleVector(0x0L,0x1000000000L);
		// StringLiteralExpCS
		grammarRuleVectors[50] = new GrammarRuleVector(0x0L,0x4000000000L);
		// TemplateBindingCS
		grammarRuleVectors[51] = new GrammarRuleVector(0x0L,0x8000000000L);
		// TemplateParameterSubstitutionCS
		grammarRuleVectors[52] = new GrammarRuleVector(0x0L,0x10000000000L);
		// TemplateSignatureCS
		grammarRuleVectors[53] = new GrammarRuleVector(0x0L,0x20000000000L);
		// TupleLiteralPartCS
		grammarRuleVectors[54] = new GrammarRuleVector(0x0L,0x80000000000L);
		// TuplePartCS
		grammarRuleVectors[55] = new GrammarRuleVector(0x0L,0x100000000000L);
		// TypeExpCS
		grammarRuleVectors[56] = new GrammarRuleVector(0x0L,0x400000000000L);
		// TypeExpWithoutMultiplicityCS
		grammarRuleVectors[57] = new GrammarRuleVector(0x0L,0x800000000000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
		grammarRuleVectors[58] = new GrammarRuleVector(0x800000000001000L,0x1200002000000L);
		// TypeLiteralWithMultiplicityCS
		grammarRuleVectors[59] = new GrammarRuleVector(0x0L,0x4000000000000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
		grammarRuleVectors[60] = new GrammarRuleVector(0x800000000001000L,0x5200002000000L);
		// CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[61] = new GrammarRuleVector(0x800000000001800L,0x9a00002000000L);
		// CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[62] = new GrammarRuleVector(0x800000000001800L,0x9e00002000000L);
		// TypeParameterCS
		grammarRuleVectors[63] = new GrammarRuleVector(0x0L,0x10000000000000L);
		// TypeRefCS
		grammarRuleVectors[64] = new GrammarRuleVector(0x0L,0x20000000000000L);
		// TypedMultiplicityRefCS
		grammarRuleVectors[65] = new GrammarRuleVector(0x0L,0x40000000000000L);
		// TypedRefCS
		grammarRuleVectors[66] = new GrammarRuleVector(0x0L,0x80000000000000L);
		// LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS
		grammarRuleVectors[67] = new GrammarRuleVector(0x800040000000000L,0x140200000000000L);
		// LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[68] = new GrammarRuleVector(0x800040000000000L,0x180200000000000L);
		// NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[69] = new GrammarRuleVector(0x0L,0x800000000000400L);
		// FirstPathElementCS|LibPathElementCS|NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[70] = new GrammarRuleVector(0x8000010000000L,0x800000000000400L);
		// URIPathNameCS
		grammarRuleVectors[71] = new GrammarRuleVector(0x0L,0x1000000000000000L);
		// BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[72] = new GrammarRuleVector(0x1000000040L,0x4000004001001800L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[73] = new GrammarRuleVector(0x200021100000240L,0x4002044101801a04L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[74] = new GrammarRuleVector(0x200021100000240L,0x4002044101c01a04L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[75] = new GrammarRuleVector(0x2000a1100000240L,0x4002044101e01a04L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[76] = new GrammarRuleVector(0x2000a1108000240L,0x4002044101e01a04L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[77] = new GrammarRuleVector(0x2000a1108000240L,0x4002044101e01a14L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[78] = new GrammarRuleVector(0x2000a1108000240L,0x4002044101e21a04L);
		// LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[79] = new GrammarRuleVector(0x800040000000000L,0x1a0200000000000L,0x4L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(129);
		// assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(132);
		// assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(135);
		// assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(136);
		// assert (|CollectionPatternCS::ownedType| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(138);
		// assert (|CollectionTypeCS::name| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(139);
		// assert (|ConstraintCS::ownedSpecification| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(140);
		// assert (|ConstraintCS::stereotype.'inv'| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(141);
		// assert (|ConstraintCS::stereotype.'post'| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(142);
		// assert (|ConstraintCS::stereotype.'pre'| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(143);
		// assert (|ContextCS::ownedExpression| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(144);
		// assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(147);
		// assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(148);
		// assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(149);
		// assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(150);
		// assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(151);
		// assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(152);
		// assert (|ImportCS::ownedPathName| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(153);
		// assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(154);
		// assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(155);
		// assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(156);
		// assert (|LambdaTypeCS::ownedContextType| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(157);
		// assert (|LambdaTypeCS::ownedResultType| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(160);
		// assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(161);
		// assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(169);
		// assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(170);
		// assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(171);
		// assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(172);
		// assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(173);
		// assert (|ModelElementCS::ownedAnnotations| - 1) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(174);
		// assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(175);
		// assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(176);
		// assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(177);
		// assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(178);
		// assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(179);
		// assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(180);
		// assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(181);
		// assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(182);
		// assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(183);
		// assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(184);
		// assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(185);
		// assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(186);
		// assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(189);
		// assert (|PackageCS::nsPrefix| - V0) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(190);
		// assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(191);
		// assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(192);
		// assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[46] = createMatchStep_Assert(193);
		// assert (|PrecedenceCS::isRightAssociative.'right'| - 1) == 0
		serializationMatchSteps[47] = createMatchStep_Assert(194);
		// assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[48] = createMatchStep_Assert(195);
		// assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[49] = createMatchStep_Assert(198);
		// assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[50] = createMatchStep_Assert(199);
		// assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[51] = createMatchStep_Assert(204);
		// assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[52] = createMatchStep_Assert(207);
		// assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[53] = createMatchStep_Assert(210);
		// assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[54] = createMatchStep_Assert(211);
		// assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[55] = createMatchStep_Assert(214);
		// assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0
		serializationMatchSteps[56] = createMatchStep_Assert(215);
		// assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[57] = createMatchStep_Assert(216);
		// assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[58] = createMatchStep_Assert(217);
		// assign V0 = (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchSteps[59] = createMatchStep_Assign(0, 131);
		// assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[60] = createMatchStep_Assign(0, 134);
		// assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchSteps[61] = createMatchStep_Assign(0, 146);
		// assign V0 = (|LetExpCS::ownedVariables| - 1)
		serializationMatchSteps[62] = createMatchStep_Assign(0, 162);
		// assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[63] = createMatchStep_Assign(0, 168);
		// assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[64] = createMatchStep_Assign(0, 192);
		// assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
		serializationMatchSteps[65] = createMatchStep_Assign(0, 197);
		// assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[66] = createMatchStep_Assign(0, 200);
		// assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[67] = createMatchStep_Assign(0, 203);
		// assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[68] = createMatchStep_Assign(0, 205);
		// assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[69] = createMatchStep_Assign(0, 206);
		// assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[70] = createMatchStep_Assign(0, 209);
		// assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[71] = createMatchStep_Assign(0, 213);
		// assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchSteps[72] = createMatchStep_Assign(0, 7);
		// assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchSteps[73] = createMatchStep_Assign(0, 14);
		// assign V0 = |CollectionPatternCS::restVariableName|
		serializationMatchSteps[74] = createMatchStep_Assign(0, 17);
		// assign V0 = |CollectionTypeCS::ownedType|
		serializationMatchSteps[75] = createMatchStep_Assign(0, 20);
		// assign V0 = |DetailCS::values|
		serializationMatchSteps[76] = createMatchStep_Assign(0, 28);
		// assign V0 = |DocumentationCS::value|
		serializationMatchSteps[77] = createMatchStep_Assign(0, 29);
		// assign V0 = |IfExpCS::ownedIfThenExpressions|
		serializationMatchSteps[78] = createMatchStep_Assign(0, 33);
		// assign V0 = |JavaImplementationCS::implementation|
		serializationMatchSteps[79] = createMatchStep_Assign(0, 40);
		// assign V0 = |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchSteps[80] = createMatchStep_Assign(0, 48);
		// assign V0 = |LibOperationCS::isStatic.'static'|
		serializationMatchSteps[81] = createMatchStep_Assign(0, 55);
		// assign V0 = |LibPropertyCS::isStatic.'static'|
		serializationMatchSteps[82] = createMatchStep_Assign(0, 59);
		// assign V0 = |MapTypeCS::ownedValueType|
		serializationMatchSteps[83] = createMatchStep_Assign(0, 67);
		// assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[84] = createMatchStep_Assign(0, 70);
		// assign V0 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[85] = createMatchStep_Assign(0, 71);
		// assign V0 = |NamedElementCS::name|
		serializationMatchSteps[86] = createMatchStep_Assign(0, 73);
		// assign V0 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[87] = createMatchStep_Assign(0, 74);
		// assign V0 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[88] = createMatchStep_Assign(0, 75);
		// assign V0 = |NavigatingArgCS::ownedType|
		serializationMatchSteps[89] = createMatchStep_Assign(0, 77);
		// assign V0 = |PackageCS::nsURI|
		serializationMatchSteps[90] = createMatchStep_Assign(0, 89);
		// assign V0 = |PatternExpCS::patternVariableName|
		serializationMatchSteps[91] = createMatchStep_Assign(0, 95);
		// assign V0 = |RootCS::ownedImports|
		serializationMatchSteps[92] = createMatchStep_Assign(0, 98);
		// assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[93] = createMatchStep_Assign(0, 103);
		// assign V0 = |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchSteps[94] = createMatchStep_Assign(0, 104);
		// assign V0 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[95] = createMatchStep_Assign(0, 112);
		// assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[96] = createMatchStep_Assign(0, 117);
		// assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[97] = createMatchStep_Assign(0, 122);
		// assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[98] = createMatchStep_Assign(0, 124);
		// assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[99] = createMatchStep_Assign(0, 127);
		// assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[100] = createMatchStep_Assign(0, 128);
		// assign V1 = (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchSteps[101] = createMatchStep_Assign(1, 130);
		// assign V1 = (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchSteps[102] = createMatchStep_Assign(1, 131);
		// assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[103] = createMatchStep_Assign(1, 133);
		// assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[104] = createMatchStep_Assign(1, 137);
		// assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[105] = createMatchStep_Assign(1, 145);
		// assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0)
		serializationMatchSteps[106] = createMatchStep_Assign(1, 159);
		// assign V1 = (|LibIterationCS::ownedIterators| - 1)
		serializationMatchSteps[107] = createMatchStep_Assign(1, 165);
		// assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[108] = createMatchStep_Assign(1, 167);
		// assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
		serializationMatchSteps[109] = createMatchStep_Assign(1, 196);
		// assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[110] = createMatchStep_Assign(1, 209);
		// assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[111] = createMatchStep_Assign(1, 212);
		// assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[112] = createMatchStep_Assign(1, 6);
		// assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[113] = createMatchStep_Assign(1, 19);
		// assign V1 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[114] = createMatchStep_Assign(1, 21);
		// assign V1 = |ImportCS::isAll.'::*'|
		serializationMatchSteps[115] = createMatchStep_Assign(1, 37);
		// assign V1 = |LibPropertyCS::ownedOpposite|
		serializationMatchSteps[116] = createMatchStep_Assign(1, 60);
		// assign V1 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[117] = createMatchStep_Assign(1, 68);
		// assign V1 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[118] = createMatchStep_Assign(1, 71);
		// assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[119] = createMatchStep_Assign(1, 74);
		// assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[120] = createMatchStep_Assign(1, 75);
		// assign V1 = |PackageOwnerCS::ownedPackages|
		serializationMatchSteps[121] = createMatchStep_Assign(1, 91);
		// assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[122] = createMatchStep_Assign(1, 108);
		// assign V1 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[123] = createMatchStep_Assign(1, 112);
		// assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[124] = createMatchStep_Assign(1, 119);
		// assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[125] = createMatchStep_Assign(1, 122);
		// assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[126] = createMatchStep_Assign(1, 127);
		// assign V10 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[127] = createMatchStep_Assign(10, 85);
		// assign V10 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[128] = createMatchStep_Assign(10, 86);
		// assign V11 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[129] = createMatchStep_Assign(11, 85);
		// assign V11 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[130] = createMatchStep_Assign(11, 86);
		// assign V2 = (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchSteps[131] = createMatchStep_Assign(2, 130);
		// assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1)
		serializationMatchSteps[132] = createMatchStep_Assign(2, 158);
		// assign V2 = (|LibIterationCS::ownedAccumulators| > 0)
		serializationMatchSteps[133] = createMatchStep_Assign(2, 164);
		// assign V2 = (|LibPackageCS::ownedPrecedences| > 0)
		serializationMatchSteps[134] = createMatchStep_Assign(2, 166);
		// assign V2 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[135] = createMatchStep_Assign(2, 188);
		// assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[136] = createMatchStep_Assign(2, 208);
		// assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[137] = createMatchStep_Assign(2, 4);
		// assign V2 = |JavaImplementationCS::implementation|
		serializationMatchSteps[138] = createMatchStep_Assign(2, 40);
		// assign V2 = |LibClassCS::metaclassName|
		serializationMatchSteps[139] = createMatchStep_Assign(2, 49);
		// assign V2 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[140] = createMatchStep_Assign(2, 86);
		// assign V2 = |PackageCS::ownedClasses|
		serializationMatchSteps[141] = createMatchStep_Assign(2, 90);
		// assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[142] = createMatchStep_Assign(2, 122);
		// assign V3 = (|LibIterationCS::ownedAccumulators| - 1)
		serializationMatchSteps[143] = createMatchStep_Assign(3, 163);
		// assign V3 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[144] = createMatchStep_Assign(3, 187);
		// assign V3 = (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchSteps[145] = createMatchStep_Assign(3, 202);
		// assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[146] = createMatchStep_Assign(3, 3);
		// assign V3 = |LibPackageCS::ownedPrecedences|
		serializationMatchSteps[147] = createMatchStep_Assign(3, 58);
		// assign V3 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[148] = createMatchStep_Assign(3, 68);
		// assign V3 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[149] = createMatchStep_Assign(3, 85);
		// assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[150] = createMatchStep_Assign(3, 122);
		// assign V4 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[151] = createMatchStep_Assign(4, 188);
		// assign V4 = (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchSteps[152] = createMatchStep_Assign(4, 201);
		// assign V4 = |LibOperationCS::isValidating.'validating'|
		serializationMatchSteps[153] = createMatchStep_Assign(4, 56);
		// assign V4 = |PackageCS::ownedClasses|
		serializationMatchSteps[154] = createMatchStep_Assign(4, 90);
		// assign V5 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[155] = createMatchStep_Assign(5, 187);
		// assign V5 = |LibOperationCS::isInvalidating.'invalidating'|
		serializationMatchSteps[156] = createMatchStep_Assign(5, 54);
		// assign V5 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[157] = createMatchStep_Assign(5, 68);
		// assign V5 = |StructuredClassCS::ownedOperations|
		serializationMatchSteps[158] = createMatchStep_Assign(5, 105);
		// assign V6 = |LibIterationCS::isInvalidating.'invalidating'|
		serializationMatchSteps[159] = createMatchStep_Assign(6, 50);
		// assign V6 = |LibOperationCS::precedence|
		serializationMatchSteps[160] = createMatchStep_Assign(6, 57);
		// assign V6 = |StructuredClassCS::ownedProperties|
		serializationMatchSteps[161] = createMatchStep_Assign(6, 106);
		// assign V7 = |ClassCS::ownedConstraints|
		serializationMatchSteps[162] = createMatchStep_Assign(7, 10);
		// assign V7 = |JavaImplementationCS::implementation|
		serializationMatchSteps[163] = createMatchStep_Assign(7, 40);
		// assign V7 = |LibIterationCS::isValidating.'validating'|
		serializationMatchSteps[164] = createMatchStep_Assign(7, 51);
		// assign V8 = |JavaImplementationCS::implementation|
		serializationMatchSteps[165] = createMatchStep_Assign(8, 40);
		// assign V8 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[166] = createMatchStep_Assign(8, 68);
		// assign V9 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[167] = createMatchStep_Assign(9, 68);
		// assign V9 = |OperationCS::ownedBodyExpressions|
		serializationMatchSteps[168] = createMatchStep_Assign(9, 83);
		// check-rule basecs::AnnotationElementCS.ownedDetails : 16
		serializationMatchSteps[169] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 7/*DetailCS*/);
		// check-rule basecs::ClassCS.ownedConstraints : 35
		serializationMatchSteps[170] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/*InvCS*/);
		// check-rule basecs::ConstraintCS.ownedMessageSpecification : 99
		serializationMatchSteps[171] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 48/*SpecificationCS*/);
		// check-rule basecs::ConstraintCS.ownedSpecification : 99
		serializationMatchSteps[172] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 48/*SpecificationCS*/);
		// check-rule basecs::ImportCS.ownedPathName : 124
		serializationMatchSteps[173] = createMatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 71/*URIPathNameCS*/);
		// check-rule basecs::LambdaTypeCS.ownedContextType : 40
		serializationMatchSteps[174] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 15/*LambdaContextTypeRefCS*/);
		// check-rule basecs::LambdaTypeCS.ownedParameterTypes : 118
		serializationMatchSteps[175] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 65/*TypedMultiplicityRefCS*/);
		// check-rule basecs::LambdaTypeCS.ownedResultType : 119
		serializationMatchSteps[176] = createMatchStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 66/*TypedRefCS*/);
		// check-rule basecs::ModelElementCS.ownedAnnotations : 3
		serializationMatchSteps[177] = createMatchStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 1/*AnnotationElementCS*/);
		// check-rule basecs::OperationCS.ownedBodyExpressions : 99
		serializationMatchSteps[178] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 48/*SpecificationCS*/);
		// check-rule basecs::OperationCS.ownedParameters : 79
		serializationMatchSteps[179] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 33/*ParameterCS*/);
		// check-rule basecs::OperationCS.ownedPostconditions : 82
		serializationMatchSteps[180] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 37/*PostCS*/);
		// check-rule basecs::OperationCS.ownedPostconditions : 83
		serializationMatchSteps[181] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 38/*PreCS*/);
		// check-rule basecs::OperationCS.ownedPreconditions : 82
		serializationMatchSteps[182] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 37/*PostCS*/);
		// check-rule basecs::OperationCS.ownedPreconditions : 83
		serializationMatchSteps[183] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 38/*PreCS*/);
		// check-rule basecs::PackageCS.ownedClasses : 7
		serializationMatchSteps[184] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 2/*ClassCS*/);
		// check-rule basecs::PackageOwnerCS.ownedPackages : 50
		serializationMatchSteps[185] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 19/*LibPackageCS*/);
		// check-rule basecs::PackageOwnerCS.ownedPackages : 78
		serializationMatchSteps[186] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 32/*PackageCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 28
		serializationMatchSteps[187] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 11/*FirstPathElementCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 28|74
		serializationMatchSteps[188] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 29/*FirstPathElementCS|NextPathElementCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 51
		serializationMatchSteps[189] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 20/*LibPathElementCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 74|123
		serializationMatchSteps[190] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 69/*NextPathElementCS|URIFirstPathElementCS*/);
		// check-rule basecs::RootCS.ownedImports : 33
		serializationMatchSteps[191] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/*ImportCS*/);
		// check-rule basecs::StructuredClassCS.ownedOperations : 77
		serializationMatchSteps[192] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 30/*OperationCS*/);
		// check-rule basecs::StructuredClassCS.ownedProperties : 53
		serializationMatchSteps[193] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 22/*LibPropertyCS*/);
		// check-rule basecs::StructuredClassCS.ownedSuperTypes : 119
		serializationMatchSteps[194] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 66/*TypedRefCS*/);
		// check-rule basecs::TemplateBindingCS.ownedMultiplicity : 62
		serializationMatchSteps[195] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 25/*MultiplicityCS*/);
		// check-rule basecs::TemplateBindingCS.ownedSubstitutions : 104
		serializationMatchSteps[196] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 52/*TemplateParameterSubstitutionCS*/);
		// check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 117
		serializationMatchSteps[197] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 64/*TypeRefCS*/);
		// check-rule basecs::TemplateSignatureCS.ownedParameters : 116
		serializationMatchSteps[198] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 63/*TypeParameterCS*/);
		// check-rule basecs::TemplateableElementCS.ownedSignature : 105
		serializationMatchSteps[199] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 53/*TemplateSignatureCS*/);
		// check-rule basecs::TupleTypeCS.ownedParts : 108
		serializationMatchSteps[200] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 55/*TuplePartCS*/);
		// check-rule basecs::TypeParameterCS.ownedExtends : 119
		serializationMatchSteps[201] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 66/*TypedRefCS*/);
		// check-rule basecs::TypedElementCS.ownedType : 118
		serializationMatchSteps[202] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 65/*TypedMultiplicityRefCS*/);
		// check-rule basecs::TypedRefCS.ownedMultiplicity : 62
		serializationMatchSteps[203] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 25/*MultiplicityCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedBinding : 103
		serializationMatchSteps[204] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 51/*TemplateBindingCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedPathName : 52
		serializationMatchSteps[205] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 21/*LibPathNameCS*/);
		// check-rule basecs::WildcardTypeRefCS.ownedExtends : 119
		serializationMatchSteps[206] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 66/*TypedRefCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[207] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 6/*CurlyBracketedClauseCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 80
		serializationMatchSteps[208] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 34/*PathNameCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 92
		serializationMatchSteps[209] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 43/*RoundBracketedClauseCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 100
		serializationMatchSteps[210] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 49/*SquareBracketedClauseCS*/);
		// check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 10
		serializationMatchSteps[211] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 4/*CollectionLiteralPartCS*/);
		// check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 12
		serializationMatchSteps[212] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 5/*CollectionTypeCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 27
		serializationMatchSteps[213] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 81
		serializationMatchSteps[214] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 35/*PatternExpCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 27
		serializationMatchSteps[215] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::CollectionPatternCS.ownedParts : 81
		serializationMatchSteps[216] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 35/*PatternExpCS*/);
		// check-rule essentialoclcs::CollectionPatternCS.ownedType : 12
		serializationMatchSteps[217] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 5/*CollectionTypeCS*/);
		// check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62
		serializationMatchSteps[218] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 25/*MultiplicityCS*/);
		// check-rule essentialoclcs::CollectionTypeCS.ownedType : 111
		serializationMatchSteps[219] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 57/*TypeExpWithoutMultiplicityCS*/);
		// check-rule essentialoclcs::ContextCS.ownedExpression : 27
		serializationMatchSteps[220] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 97
		serializationMatchSteps[221] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 47/*ShadowPartCS*/);
		// check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 27
		serializationMatchSteps[222] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedCondition : 27|81
		serializationMatchSteps[223] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 36/*ExpCS|PatternExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedElseExpression : 27
		serializationMatchSteps[224] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
		serializationMatchSteps[225] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 9/*ElseIfThenExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedThenExpression : 27
		serializationMatchSteps[226] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::IfThenExpCS.ownedCondition : 27
		serializationMatchSteps[227] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 10/*ExpCS*/);
		// check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 27
		serializationMatchSteps[228] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::InfixExpCS.ownedLeft : 86
		serializationMatchSteps[229] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 42/*PrefixedPrimaryExpCS*/);
		// check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 27
		serializationMatchSteps[230] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 10/*ExpCS*/);
		// check-rule essentialoclcs::LetExpCS.ownedInExpression : 27
		serializationMatchSteps[231] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::LetExpCS.ownedVariables : 44
		serializationMatchSteps[232] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 16/*LetVariableCS*/);
		// check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 92
		serializationMatchSteps[233] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 43/*RoundBracketedClauseCS*/);
		// check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 58
		serializationMatchSteps[234] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 23/*MapLiteralPartCS*/);
		// check-rule essentialoclcs::MapLiteralExpCS.ownedType : 59
		serializationMatchSteps[235] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 24/*MapTypeCS*/);
		// check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 27
		serializationMatchSteps[236] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 10/*ExpCS*/);
		// check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 27
		serializationMatchSteps[237] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 10/*ExpCS*/);
		// check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110
		serializationMatchSteps[238] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 56/*TypeExpCS*/);
		// check-rule essentialoclcs::MapTypeCS.ownedValueType : 110
		serializationMatchSteps[239] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 56/*TypeExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8
		serializationMatchSteps[240] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3/*CoIteratorVariableCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27
		serializationMatchSteps[241] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68
		serializationMatchSteps[242] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 26/*NavigatingArgExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedType : 110
		serializationMatchSteps[243] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 56/*TypeExpCS*/);
		// check-rule essentialoclcs::NestedExpCS.ownedExpression : 27
		serializationMatchSteps[244] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 27
		serializationMatchSteps[245] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 10/*ExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 85
		serializationMatchSteps[246] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 40/*PrefixedLetExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 86
		serializationMatchSteps[247] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 42/*PrefixedPrimaryExpCS*/);
		// check-rule essentialoclcs::PatternExpCS.ownedPatternType : 110
		serializationMatchSteps[248] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 56/*TypeExpCS*/);
		// check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 67|69|70|71
		serializationMatchSteps[249] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 28/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		// check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 102
		serializationMatchSteps[250] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 50/*StringLiteralExpCS*/);
		// check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 27|81
		serializationMatchSteps[251] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 36/*ExpCS|PatternExpCS*/);
		// check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 27
		serializationMatchSteps[252] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 10/*ExpCS*/);
		// check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 107
		serializationMatchSteps[253] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 54/*TupleLiteralPartCS*/);
		// check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 114
		serializationMatchSteps[254] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 59/*TypeLiteralWithMultiplicityCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[255] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 6/*CurlyBracketedClauseCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 80
		serializationMatchSteps[256] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 34/*PathNameCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 27
		serializationMatchSteps[257] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 10/*ExpCS*/);
		// check-rule essentialoclcs::VariableCS.ownedInitExpression : 27
		serializationMatchSteps[258] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::VariableCS.ownedType : 110
		serializationMatchSteps[259] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 56/*TypeExpCS*/);
		// check-rule oclstdlibcs::LibIterationCS.ownedAccumulators : 1
		serializationMatchSteps[260] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS, 0/*AccumulatorCS*/);
		// check-rule oclstdlibcs::LibIterationCS.ownedIterators : 37
		serializationMatchSteps[261] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, 14/*IteratorCS*/);
		// check-rule oclstdlibcs::LibPackageCS.ownedPrecedences : 84
		serializationMatchSteps[262] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES, 39/*PrecedenceCS*/);
		// check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : 49
		serializationMatchSteps[263] = createMatchStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, 18/*LibOppositeCS*/);
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
		serializationMatchTerms[3] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 4 /* '@' */);
		// |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[4] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// |AbstractNameExpCS::ownedPathName|
		serializationMatchTerms[5] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		// |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchTerms[6] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchTerms[7] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		// |AnnotationElementCS::ownedDetails|
		serializationMatchTerms[8] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		// |BooleanLiteralExpCS::symbol.'false|true'|
		serializationMatchTerms[9] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 9 /* 'false|true' */);
		// |ClassCS::ownedConstraints|
		serializationMatchTerms[10] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		// |CollectionLiteralExpCS::ownedParts|
		serializationMatchTerms[11] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		// |CollectionLiteralExpCS::ownedType|
		serializationMatchTerms[12] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		// |CollectionLiteralPartCS::ownedExpression|
		serializationMatchTerms[13] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		// |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchTerms[14] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		// |CollectionPatternCS::ownedParts|
		serializationMatchTerms[15] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		// |CollectionPatternCS::ownedType|
		serializationMatchTerms[16] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		// |CollectionPatternCS::restVariableName|
		serializationMatchTerms[17] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		// |CollectionTypeCS::name|
		serializationMatchTerms[18] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		// |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchTerms[19] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		// |CollectionTypeCS::ownedType|
		serializationMatchTerms[20] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		// |ConstraintCS::ownedMessageSpecification|
		serializationMatchTerms[21] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		// |ConstraintCS::ownedSpecification|
		serializationMatchTerms[22] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		// |ConstraintCS::stereotype.'inv'|
		serializationMatchTerms[23] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 10 /* 'inv' */);
		// |ConstraintCS::stereotype.'post'|
		serializationMatchTerms[24] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 12 /* 'post' */);
		// |ConstraintCS::stereotype.'pre'|
		serializationMatchTerms[25] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 13 /* 'pre' */);
		// |ContextCS::ownedExpression|
		serializationMatchTerms[26] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		// |CurlyBracketedClauseCS::ownedParts|
		serializationMatchTerms[27] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		// |DetailCS::values|
		serializationMatchTerms[28] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DETAIL_CS__VALUES);
		// |DocumentationCS::value|
		serializationMatchTerms[29] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		// |ExpSpecificationCS::ownedExpression|
		serializationMatchTerms[30] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		// |IfExpCS::ownedCondition|
		serializationMatchTerms[31] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		// |IfExpCS::ownedElseExpression|
		serializationMatchTerms[32] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		// |IfExpCS::ownedIfThenExpressions|
		serializationMatchTerms[33] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		// |IfExpCS::ownedThenExpression|
		serializationMatchTerms[34] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		// |IfThenExpCS::ownedCondition|
		serializationMatchTerms[35] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		// |IfThenExpCS::ownedThenExpression|
		serializationMatchTerms[36] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		// |ImportCS::isAll.'::*'|
		serializationMatchTerms[37] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, 2 /* '::*' */);
		// |ImportCS::ownedPathName|
		serializationMatchTerms[38] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		// |InfixExpCS::ownedLeft|
		serializationMatchTerms[39] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		// |JavaImplementationCS::implementation|
		serializationMatchTerms[40] = new SerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION);
		// |LambdaLiteralExpCS::ownedExpressionCS|
		serializationMatchTerms[41] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		// |LambdaTypeCS::name.'Lambda'|
		serializationMatchTerms[42] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, 5 /* 'Lambda' */);
		// |LambdaTypeCS::ownedContextType|
		serializationMatchTerms[43] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE);
		// |LambdaTypeCS::ownedParameterTypes|
		serializationMatchTerms[44] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES);
		// |LambdaTypeCS::ownedResultType|
		serializationMatchTerms[45] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE);
		// |LetExpCS::ownedInExpression|
		serializationMatchTerms[46] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		// |LetExpCS::ownedVariables|
		serializationMatchTerms[47] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		// |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchTerms[48] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// |LibClassCS::metaclassName|
		serializationMatchTerms[49] = new SerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME);
		// |LibIterationCS::isInvalidating.'invalidating'|
		serializationMatchTerms[50] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING, 11 /* 'invalidating' */);
		// |LibIterationCS::isValidating.'validating'|
		serializationMatchTerms[51] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING, 17 /* 'validating' */);
		// |LibIterationCS::ownedAccumulators|
		serializationMatchTerms[52] = new SerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS);
		// |LibIterationCS::ownedIterators|
		serializationMatchTerms[53] = new SerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS);
		// |LibOperationCS::isInvalidating.'invalidating'|
		serializationMatchTerms[54] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING, 11 /* 'invalidating' */);
		// |LibOperationCS::isStatic.'static'|
		serializationMatchTerms[55] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC, 15 /* 'static' */);
		// |LibOperationCS::isValidating.'validating'|
		serializationMatchTerms[56] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING, 17 /* 'validating' */);
		// |LibOperationCS::precedence|
		serializationMatchTerms[57] = new SerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE);
		// |LibPackageCS::ownedPrecedences|
		serializationMatchTerms[58] = new SerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES);
		// |LibPropertyCS::isStatic.'static'|
		serializationMatchTerms[59] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC, 15 /* 'static' */);
		// |LibPropertyCS::ownedOpposite|
		serializationMatchTerms[60] = new SerializationMatchTermEStructuralFeatureSize(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE);
		// |MapLiteralExpCS::ownedParts|
		serializationMatchTerms[61] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		// |MapLiteralExpCS::ownedType|
		serializationMatchTerms[62] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		// |MapLiteralPartCS::ownedKey|
		serializationMatchTerms[63] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		// |MapLiteralPartCS::ownedValue|
		serializationMatchTerms[64] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		// |MapTypeCS::name.'Map'|
		serializationMatchTerms[65] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 6 /* 'Map' */);
		// |MapTypeCS::ownedKeyType|
		serializationMatchTerms[66] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		// |MapTypeCS::ownedValueType|
		serializationMatchTerms[67] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		// |ModelElementCS::ownedAnnotations|
		serializationMatchTerms[68] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		// |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[69] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[70] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[71] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 19 /* '|1' */);
		// |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[72] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */);
		// |NamedElementCS::name|
		serializationMatchTerms[73] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// |NavigatingArgCS::ownedCoIterator|
		serializationMatchTerms[74] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		// |NavigatingArgCS::ownedInitExpression|
		serializationMatchTerms[75] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		// |NavigatingArgCS::ownedNameExpression|
		serializationMatchTerms[76] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		// |NavigatingArgCS::ownedType|
		serializationMatchTerms[77] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		// |NavigatingArgCS::prefix.','|
		serializationMatchTerms[78] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */);
		// |NavigatingArgCS::prefix.';'|
		serializationMatchTerms[79] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 3 /* ';' */);
		// |NavigatingArgCS::prefix.'|'|
		serializationMatchTerms[80] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 18 /* '|' */);
		// |NestedExpCS::ownedExpression|
		serializationMatchTerms[81] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		// |NumberLiteralExpCS::symbol|
		serializationMatchTerms[82] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		// |OperationCS::ownedBodyExpressions|
		serializationMatchTerms[83] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		// |OperationCS::ownedParameters|
		serializationMatchTerms[84] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		// |OperationCS::ownedPostconditions|
		serializationMatchTerms[85] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		// |OperationCS::ownedPreconditions|
		serializationMatchTerms[86] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		// |OperatorExpCS::ownedRight|
		serializationMatchTerms[87] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		// |PackageCS::nsPrefix|
		serializationMatchTerms[88] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		// |PackageCS::nsURI|
		serializationMatchTerms[89] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		// |PackageCS::ownedClasses|
		serializationMatchTerms[90] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		// |PackageOwnerCS::ownedPackages|
		serializationMatchTerms[91] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		// |PathElementCS::referredElement|
		serializationMatchTerms[92] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// |PathNameCS::ownedPathElements|
		serializationMatchTerms[93] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// |PatternExpCS::ownedPatternType|
		serializationMatchTerms[94] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		// |PatternExpCS::patternVariableName|
		serializationMatchTerms[95] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		// |PrecedenceCS::isRightAssociative.'right'|
		serializationMatchTerms[96] = createSerializationMatchTermEAttributeSize(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE, 14 /* 'right' */);
		// |PrimitiveTypeRefCS::name|
		serializationMatchTerms[97] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		// |RootCS::ownedImports|
		serializationMatchTerms[98] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		// |RoundBracketedClauseCS::ownedArguments|
		serializationMatchTerms[99] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		// |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[100] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// |ShadowPartCS::referredProperty|
		serializationMatchTerms[101] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[102] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// |StringLiteralExpCS::segments|
		serializationMatchTerms[103] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchTerms[104] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, 8 /* 'abstract' */);
		// |StructuredClassCS::ownedOperations|
		serializationMatchTerms[105] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		// |StructuredClassCS::ownedProperties|
		serializationMatchTerms[106] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		// |StructuredClassCS::ownedSuperTypes|
		serializationMatchTerms[107] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		// |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[108] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[109] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[110] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[111] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// |TemplateableElementCS::ownedSignature|
		serializationMatchTerms[112] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		// |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[113] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[114] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 7 /* 'Tuple' */);
		// |TupleTypeCS::ownedParts|
		serializationMatchTerms[115] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[116] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[117] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[118] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[119] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// |TypeParameterCS::ownedExtends|
		serializationMatchTerms[120] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// |TypedElementCS::ownedType|
		serializationMatchTerms[121] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		// |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[122] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// |TypedTypeRefCS::isTypeof.'typeof'|
		serializationMatchTerms[123] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF, 16 /* 'typeof' */);
		// |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[124] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[125] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// |VariableCS::ownedInitExpression|
		serializationMatchTerms[126] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		// |VariableCS::ownedType|
		serializationMatchTerms[127] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		// |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[128] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// (|AbstractNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[129] = createSerializationMatchTermSubtract(5, 1);
		// (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchTerms[130] = createSerializationMatchTermSubtract(8, 1);
		// (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchTerms[131] = createSerializationMatchTermGreaterThan(8, 0);
		// (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
		serializationMatchTerms[132] = createSerializationMatchTermSubtract(9, 1);
		// (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[133] = createSerializationMatchTermSubtract(11, 1);
		// (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[134] = createSerializationMatchTermGreaterThan(11, 0);
		// (|CollectionLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[135] = createSerializationMatchTermSubtract(12, 1);
		// (|CollectionLiteralPartCS::ownedExpression| - 1)
		serializationMatchTerms[136] = createSerializationMatchTermSubtract(13, 1);
		// (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchTerms[137] = createSerializationMatchTermSubtract(15, 1);
		// (|CollectionPatternCS::ownedType| - 1)
		serializationMatchTerms[138] = createSerializationMatchTermSubtract(16, 1);
		// (|CollectionTypeCS::name| - 1)
		serializationMatchTerms[139] = createSerializationMatchTermSubtract(18, 1);
		// (|ConstraintCS::ownedSpecification| - 1)
		serializationMatchTerms[140] = createSerializationMatchTermSubtract(22, 1);
		// (|ConstraintCS::stereotype.'inv'| - 1)
		serializationMatchTerms[141] = createSerializationMatchTermSubtract(23, 1);
		// (|ConstraintCS::stereotype.'post'| - 1)
		serializationMatchTerms[142] = createSerializationMatchTermSubtract(24, 1);
		// (|ConstraintCS::stereotype.'pre'| - 1)
		serializationMatchTerms[143] = createSerializationMatchTermSubtract(25, 1);
		// (|ContextCS::ownedExpression| - 1)
		serializationMatchTerms[144] = createSerializationMatchTermSubtract(26, 1);
		// (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchTerms[145] = createSerializationMatchTermSubtract(27, 1);
		// (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchTerms[146] = createSerializationMatchTermGreaterThan(27, 0);
		// (|ExpSpecificationCS::ownedExpression| - 1)
		serializationMatchTerms[147] = createSerializationMatchTermSubtract(30, 1);
		// (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[148] = createSerializationMatchTermSubtract(31, 1);
		// (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[149] = createSerializationMatchTermSubtract(32, 1);
		// (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[150] = createSerializationMatchTermSubtract(34, 1);
		// (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[151] = createSerializationMatchTermSubtract(35, 1);
		// (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[152] = createSerializationMatchTermSubtract(36, 1);
		// (|ImportCS::ownedPathName| - 1)
		serializationMatchTerms[153] = createSerializationMatchTermSubtract(38, 1);
		// (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[154] = createSerializationMatchTermSubtract(39, 1);
		// (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[155] = createSerializationMatchTermSubtract(41, 1);
		// (|LambdaTypeCS::name.'Lambda'| - 1)
		serializationMatchTerms[156] = createSerializationMatchTermSubtract(42, 1);
		// (|LambdaTypeCS::ownedContextType| - 1)
		serializationMatchTerms[157] = createSerializationMatchTermSubtract(43, 1);
		// (|LambdaTypeCS::ownedParameterTypes| - 1)
		serializationMatchTerms[158] = createSerializationMatchTermSubtract(44, 1);
		// (|LambdaTypeCS::ownedParameterTypes| > 0)
		serializationMatchTerms[159] = createSerializationMatchTermGreaterThan(44, 0);
		// (|LambdaTypeCS::ownedResultType| - 1)
		serializationMatchTerms[160] = createSerializationMatchTermSubtract(45, 1);
		// (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[161] = createSerializationMatchTermSubtract(46, 1);
		// (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[162] = createSerializationMatchTermSubtract(47, 1);
		// (|LibIterationCS::ownedAccumulators| - 1)
		serializationMatchTerms[163] = createSerializationMatchTermSubtract(52, 1);
		// (|LibIterationCS::ownedAccumulators| > 0)
		serializationMatchTerms[164] = createSerializationMatchTermGreaterThan(52, 0);
		// (|LibIterationCS::ownedIterators| - 1)
		serializationMatchTerms[165] = createSerializationMatchTermSubtract(53, 1);
		// (|LibPackageCS::ownedPrecedences| > 0)
		serializationMatchTerms[166] = createSerializationMatchTermGreaterThan(58, 0);
		// (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[167] = createSerializationMatchTermSubtract(61, 1);
		// (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[168] = createSerializationMatchTermGreaterThan(61, 0);
		// (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[169] = createSerializationMatchTermSubtract(62, 1);
		// (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[170] = createSerializationMatchTermSubtract(63, 1);
		// (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[171] = createSerializationMatchTermSubtract(64, 1);
		// (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[172] = createSerializationMatchTermSubtract(65, 1);
		// (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[173] = createSerializationMatchTermSubtract(66, 2);
		// (|ModelElementCS::ownedAnnotations| - 1)
		serializationMatchTerms[174] = createSerializationMatchTermSubtract(68, 1);
		// (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[175] = createSerializationMatchTermSubtract(69, 1);
		// (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[176] = createSerializationMatchTermSubtract(72, 1);
		// (|NamedElementCS::name| - 1)
		serializationMatchTerms[177] = createSerializationMatchTermSubtract(73, 1);
		// (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[178] = createSerializationMatchTermSubtract(74, 1);
		// (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[179] = createSerializationMatchTermSubtract(75, 1);
		// (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[180] = createSerializationMatchTermSubtract(76, 1);
		// (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[181] = createSerializationMatchTermSubtract(77, 1);
		// (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[182] = createSerializationMatchTermSubtract(78, 1);
		// (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[183] = createSerializationMatchTermSubtract(79, 1);
		// (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[184] = createSerializationMatchTermSubtract(80, 1);
		// (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[185] = createSerializationMatchTermSubtract(81, 1);
		// (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[186] = createSerializationMatchTermSubtract(82, 1);
		// (|OperationCS::ownedParameters| - 1)
		serializationMatchTerms[187] = createSerializationMatchTermSubtract(84, 1);
		// (|OperationCS::ownedParameters| > 0)
		serializationMatchTerms[188] = createSerializationMatchTermGreaterThan(84, 0);
		// (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[189] = createSerializationMatchTermSubtract(87, 1);
		// (|PackageCS::nsPrefix| - V0)
		serializationMatchTerms[190] = createSerializationMatchTermSubtract(88, 2);
		// (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[191] = createSerializationMatchTermSubtract(92, 1);
		// (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[192] = createSerializationMatchTermSubtract(93, 1);
		// (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[193] = createSerializationMatchTermSubtract(94, 1);
		// (|PrecedenceCS::isRightAssociative.'right'| - 1)
		serializationMatchTerms[194] = createSerializationMatchTermSubtract(96, 1);
		// (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[195] = createSerializationMatchTermSubtract(97, 1);
		// (|RoundBracketedClauseCS::ownedArguments| - 1)
		serializationMatchTerms[196] = createSerializationMatchTermSubtract(99, 1);
		// (|RoundBracketedClauseCS::ownedArguments| > 0)
		serializationMatchTerms[197] = createSerializationMatchTermGreaterThan(99, 0);
		// (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[198] = createSerializationMatchTermSubtract(100, 1);
		// (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[199] = createSerializationMatchTermSubtract(101, 1);
		// (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[200] = createSerializationMatchTermSubtract(102, 1);
		// (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchTerms[201] = createSerializationMatchTermSubtract(107, 1);
		// (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchTerms[202] = createSerializationMatchTermGreaterThan(107, 0);
		// (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[203] = createSerializationMatchTermSubtract(109, 1);
		// (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[204] = createSerializationMatchTermSubtract(110, 1);
		// (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[205] = createSerializationMatchTermSubtract(111, 1);
		// (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[206] = createSerializationMatchTermSubtract(113, 1);
		// (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[207] = createSerializationMatchTermSubtract(114, 1);
		// (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[208] = createSerializationMatchTermSubtract(115, 1);
		// (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[209] = createSerializationMatchTermGreaterThan(115, 0);
		// (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[210] = createSerializationMatchTermSubtract(116, 1);
		// (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[211] = createSerializationMatchTermSubtract(118, 1);
		// (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[212] = createSerializationMatchTermSubtract(120, 1);
		// (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[213] = createSerializationMatchTermGreaterThan(120, 0);
		// (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[214] = createSerializationMatchTermSubtract(121, 1);
		// (|TypedTypeRefCS::isTypeof.'typeof'| - 1)
		serializationMatchTerms[215] = createSerializationMatchTermSubtract(123, 1);
		// (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[216] = createSerializationMatchTermSubtract(125, 1);
		// (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[217] = createSerializationMatchTermSubtract(126, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] =
			new SerializationRule("FirstPathElementCS", 28,
				createSerializationMatchSteps(
					44		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					238		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
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
			new SerializationRule("MultiplicityBoundsCS", 61,
				createSerializationMatchSteps(
					84		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					30		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					1		/* MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space */,
					177		/* V00*4-steps || value */,
					163		/* 1*1-steps || value */,
					106		/* '..' || no-space value no-space */,
					163		/* 1*1-steps || value */,
					97		/* MultiplicityBoundsCS::upperBound=121 || soft-space value soft-space */
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
			new SerializationRule("MultiplicityCS", 62,
				createSerializationMatchSteps(
					84		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					30		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					119		/* '[' || no-space value no-space */,
					172		/* 1*7-steps || value */,
					163		/* 1*1-steps || value */,
					1		/* MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space */,
					177		/* V00*4-steps || value */,
					163		/* 1*1-steps || value */,
					106		/* '..' || no-space value no-space */,
					163		/* 1*1-steps || value */,
					97		/* MultiplicityBoundsCS::upperBound=121 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					120		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 62,
				createSerializationMatchSteps(
					84		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					30		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					119		/* '[' || no-space value no-space */,
					172		/* 1*7-steps || value */,
					163		/* 1*1-steps || value */,
					1		/* MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space */,
					177		/* V00*4-steps || value */,
					163		/* 1*1-steps || value */,
					106		/* '..' || no-space value no-space */,
					163		/* 1*1-steps || value */,
					97		/* MultiplicityBoundsCS::upperBound=121 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					160		/* '|?' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					120		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 62,
				createSerializationMatchSteps(
					118		/* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
					84		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					30		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					119		/* '[' || no-space value no-space */,
					172		/* 1*7-steps || value */,
					163		/* 1*1-steps || value */,
					1		/* MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space */,
					177		/* V00*4-steps || value */,
					163		/* 1*1-steps || value */,
					106		/* '..' || no-space value no-space */,
					163		/* 1*1-steps || value */,
					97		/* MultiplicityBoundsCS::upperBound=121 || soft-space value soft-space */,
					182		/* V01*1-steps || value */,
					159		/* '|1' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					120		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						19	/* '|1' */
					)
				},
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(19 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
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
			new SerializationRule("MultiplicityCS", 62,
				createSerializationMatchSteps(
					31		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					119		/* '[' || no-space value no-space */,
					163		/* 1*1-steps || value */,
					94		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					120		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 62,
				createSerializationMatchSteps(
					31		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					119		/* '[' || no-space value no-space */,
					163		/* 1*1-steps || value */,
					94		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					160		/* '|?' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					120		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 62,
				createSerializationMatchSteps(
					85		/* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
					31		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					119		/* '[' || no-space value no-space */,
					163		/* 1*1-steps || value */,
					94		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					159		/* '|1' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					120		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						19	/* '|1' */
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
							createEnumerationValue_GrammarCardinality(19 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
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
			new SerializationRule("MultiplicityStringCS", 63,
				createSerializationMatchSteps(
					31		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					94		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
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
			new SerializationRule("NextPathElementCS", 74,
				createSerializationMatchSteps(
					44		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					237		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
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
			new SerializationRule("PathNameCS", 80,
				createSerializationMatchSteps(
					188		/* check-rule basecs::PathNameCS.ownedPathElements : 28|74 */,
					64		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					225		/* PathNameCS::ownedPathElements+=28 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					109		/* '::' || no-space value no-space */,
					227		/* PathNameCS::ownedPathElements+=74 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						29) /* FirstPathElementCS|NextPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(28, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(74, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateBindingCS(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] =
			new SerializationRule("TemplateBindingCS", 103,
				createSerializationMatchSteps(
					195		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 62 */,
					196		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 104 */,
					122		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
					67		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
				),
				createSerializationSteps(
					73		/* TemplateBindingCS::ownedSubstitutions+=104 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					73		/* TemplateBindingCS::ownedSubstitutions+=104 || value */,
					182		/* V01*1-steps || value */,
					44		/* TemplateBindingCS::ownedMultiplicity=62 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						52) /* TemplateParameterSubstitutionCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(104, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateParameterSubstitutionCS(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] =
			new SerializationRule("TemplateParameterSubstitutionCS", 104,
				createSerializationMatchSteps(
					197		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 117 */,
					51		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					8		/* TemplateParameterSubstitutionCS::ownedActualParameter=117 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						64) /* TypeRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Base::TemplateSignatureCS(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[13] =
			new SerializationRule("TemplateSignatureCS", 105,
				createSerializationMatchSteps(
					198		/* check-rule basecs::TemplateSignatureCS.ownedParameters : 116 */,
					68		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					52		/* TemplateSignatureCS::ownedParameters+=116 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					52		/* TemplateSignatureCS::ownedParameters+=116 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						63) /* TypeParameterCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// Base::TypeParameterCS(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[14] =
			new SerializationRule("TypeParameterCS", 116,
				createSerializationMatchSteps(
					201		/* check-rule basecs::TypeParameterCS.ownedExtends : 119 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					71		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
					111		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					213		/* NamedElementCS::name=128 || soft-space value soft-space */,
					179		/* V00*7-steps || value */,
					163		/* 1*1-steps || value */,
					130		/* 'extends' || soft-space value soft-space */,
					29		/* TypeParameterCS::ownedExtends+=119 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					100		/* '&&' || soft-space value soft-space */,
					29		/* TypeParameterCS::ownedExtends+=119 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						66) /* TypedRefCS */
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
						new RuleIndex_GrammarCardinality(119, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[15] =
			new SerializationRule("WildcardTypeRefCS", 130,
				createSerializationMatchSteps(
					206		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : 119 */,
					100		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					114		/* '?' || soft-space value soft-space */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					130		/* 'extends' || soft-space value soft-space */,
					30		/* WildcardTypeRefCS::ownedExtends=119 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						66) /* TypedRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(119, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[16] =
			new SerializationRule("BooleanLiteralExpCS", 6,
				createSerializationMatchSteps(
					1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					95		/* BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						9	/* 'false|true' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(9 /* 'false|true' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::CoIteratorVariableCS(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[17] =
			new SerializationRule("CoIteratorVariableCS", 8,
				createSerializationMatchSteps(
					259		/* check-rule essentialoclcs::VariableCS.ownedType : 110 */,
					99		/* assign V0 = |VariableCS::ownedType| */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					213		/* NamedElementCS::name=128 || soft-space value soft-space */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					85		/* VariableCS::ownedType=110 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						56) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[18] =
			new SerializationRule("CollectionLiteralExpCS", 9,
				createSerializationMatchSteps(
					211		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 10 */,
					212		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 12 */,
					2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
					60		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
					103		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					78		/* CollectionLiteralExpCS::ownedType=12 || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					178		/* V00*5-steps || value */,
					53		/* CollectionLiteralExpCS::ownedParts+=10 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					53		/* CollectionLiteralExpCS::ownedParts+=10 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						4) /* CollectionLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						5) /* CollectionTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(10, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
		serializationRules[19] =
			new SerializationRule("CollectionLiteralPartCS", 10,
				createSerializationMatchSteps(
					214		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 81 */,
					3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					24		/* CollectionLiteralPartCS::ownedExpression=81 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						35) /* PatternExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(81, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] }
		serializationRules[20] =
			new SerializationRule("CollectionLiteralPartCS", 10,
				createSerializationMatchSteps(
					213		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 27 */,
					215		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 27 */,
					73		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
					3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					23		/* CollectionLiteralPartCS::ownedExpression=27 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					106		/* '..' || no-space value no-space */,
					41		/* CollectionLiteralPartCS::ownedLastExpression=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" }
		serializationRules[21] =
			new SerializationRule("CollectionPatternCS", 11,
				createSerializationMatchSteps(
					216		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 81 */,
					217		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : 12 */,
					74		/* assign V0 = |CollectionPatternCS::restVariableName| */,
					104		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
					4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					79		/* CollectionPatternCS::ownedType=12 || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					175		/* V00*10-steps || value */,
					54		/* CollectionPatternCS::ownedParts+=81 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					54		/* CollectionPatternCS::ownedParts+=81 || value */,
					169		/* 1*4-steps || value */,
					163		/* 1*1-steps || value */,
					104		/* '++' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					92		/* CollectionPatternCS::restVariableName=31 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						35) /* PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						5) /* CollectionTypeCS */
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
						new RuleIndex_GrammarCardinality(81, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] }
		serializationRules[22] =
			new SerializationRule("CollectionTypeCS", 12,
				createSerializationMatchSteps(
					218		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62 */,
					219		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 111 */,
					75		/* assign V0 = |CollectionTypeCS::ownedType| */,
					5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					113		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					3		/* CollectionTypeCS::name=13 || soft-space value soft-space */,
					179		/* V00*7-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					80		/* CollectionTypeCS::ownedType=111 || value */,
					182		/* V01*1-steps || value */,
					14		/* CollectionTypeCS::ownedCollectionMultiplicity=62 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						57) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(111, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CurlyBracketedClauseCS(essentialoclcs::CurlyBracketedClauseCS): { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" }
		serializationRules[23] =
			new SerializationRule("CurlyBracketedClauseCS", 14,
				createSerializationMatchSteps(
					221		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 97 */,
					61		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
					105		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					178		/* V00*5-steps || value */,
					55		/* CurlyBracketedClauseCS::ownedParts+=97 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					55		/* CurlyBracketedClauseCS::ownedParts+=97 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						47) /* ShadowPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(97, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ElseIfThenExpCS(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[24] =
			new SerializationRule("ElseIfThenExpCS", 20,
				createSerializationMatchSteps(
					227		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 27 */,
					228		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 27 */,
					16		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
					15		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					128		/* 'elseif' || soft-space value soft-space */,
					16		/* IfThenExpCS::ownedCondition=27 || value */,
					163		/* 1*1-steps || value */,
					152		/* 'then' || soft-space value soft-space */,
					77		/* IfThenExpCS::ownedThenExpression=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[25] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					95		/* BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						9	/* 'false|true' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(9 /* 'false|true' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[26] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					211		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 10 */,
					212		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 12 */,
					2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
					60		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
					103		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					78		/* CollectionLiteralExpCS::ownedType=12 || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					178		/* V00*5-steps || value */,
					53		/* CollectionLiteralExpCS::ownedParts+=10 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					53		/* CollectionLiteralExpCS::ownedParts+=10 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						4) /* CollectionLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						5) /* CollectionTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(10, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "*"
		serializationRules[27] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					103		/* '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "invalid"
		serializationRules[28] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					135		/* 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "null"
		serializationRules[29] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					141		/* 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "self"
		serializationRules[30] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					150		/* 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[31] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					223		/* check-rule essentialoclcs::IfExpCS.ownedCondition : 27|81 */,
					224		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 27 */,
					225		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
					226		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 27 */,
					13		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
					78		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
					14		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
					12		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					131		/* 'if' || soft-space value soft-space */,
					15		/* IfExpCS::ownedCondition=27|81 || value */,
					163		/* 1*1-steps || value */,
					152		/* 'then' || soft-space value soft-space */,
					76		/* IfExpCS::ownedThenExpression=27 || value */,
					174		/* V00*1-steps || value */,
					31		/* IfExpCS::ownedIfThenExpressions+=20 || value */,
					163		/* 1*1-steps || value */,
					127		/* 'else' || soft-space value soft-space */,
					22		/* IfExpCS::ownedElseExpression=27 || value */,
					163		/* 1*1-steps || value */,
					129		/* 'endif' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						36) /* ExpCS|PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						9) /* ElseIfThenExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(81, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } }
		serializationRules[32] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					229		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : 86 */,
					245		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 27 */,
					42		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					18		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					42		/* InfixExpCS::ownedLeft=86 || value */,
					168		/* 1*3-steps || value */,
					163		/* 1*1-steps || value */,
					217		/* NamedElementCS::name=5 || soft-space value soft-space */,
					232		/* OperatorExpCS::ownedRight=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						42) /* PrefixedPrimaryExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						10) /* ExpCS */
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
						new RuleIndex_GrammarCardinality(86, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[33] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					230		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 27 */,
					19		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					116		/* 'Lambda' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					28		/* LambdaLiteralExpCS::ownedExpressionCS=27 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[34] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					234		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 58 */,
					235		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 59 */,
					24		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
					63		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
					108		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					81		/* MapLiteralExpCS::ownedType=59 || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					178		/* V00*5-steps || value */,
					56		/* MapLiteralExpCS::ownedParts+=58 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					56		/* MapLiteralExpCS::ownedParts+=58 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						23) /* MapLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						24) /* MapTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(58, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(59, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] }
		serializationRules[35] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					207		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
					208		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 80 */,
					209		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 92 */,
					210		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 100 */,
					146		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
					137		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
					112		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
					72		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
					0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					59		/* AbstractNameExpCS::ownedPathName=80 || value */,
					174		/* V00*1-steps || value */,
					72		/* AbstractNameExpCS::ownedSquareBracketedClauses+=100 || value */,
					182		/* V01*1-steps || value */,
					68		/* AbstractNameExpCS::ownedRoundBracketedClause=92 || value */,
					188		/* V02*1-steps || value */,
					19		/* AbstractNameExpCS::ownedCurlyBracketedClause=14 || value */,
					196		/* V03*4-steps || value */,
					163		/* 1*1-steps || value */,
					115		/* '@' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					146		/* 'pre' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						4	/* '@' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						6) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						34) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						43) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						49) /* SquareBracketedClauseCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(4 /* '@' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(14, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(80, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(100, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[36] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					244		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : 27 */,
					40		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					27		/* NestedExpCS::ownedExpression=27 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[37] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					41		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					96		/* NumberLiteralExpCS::symbol=64 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[38] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					247		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 86 */,
					42		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					212		/* NamedElementCS::name=125 || soft-space value soft-space */,
					234		/* OperatorExpCS::ownedRight=86 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						42) /* PrefixedPrimaryExpCS */
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
						new RuleIndex_GrammarCardinality(86, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[39] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					93		/* assign V0 = |StringLiteralExpCS::segments| */
				),
				createSerializationSteps(
					174		/* V00*1-steps || value */,
					93		/* StringLiteralExpCS::segments+=101 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[40] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					253		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 107 */,
					69		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					118		/* 'Tuple' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					57		/* TupleLiteralExpCS::ownedParts+=107 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					57		/* TupleLiteralExpCS::ownedParts+=107 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						54) /* TupleLiteralPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[41] =
			new SerializationRule("ExpCS", 27,
				createSerializationMatchSteps(
					254		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 114 */,
					53		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					83		/* TypeLiteralExpCS::ownedType=114 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						59) /* TypeLiteralWithMultiplicityCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(114, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[42] =
			new SerializationRule("IfExpCS", 32,
				createSerializationMatchSteps(
					223		/* check-rule essentialoclcs::IfExpCS.ownedCondition : 27|81 */,
					224		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 27 */,
					225		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
					226		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 27 */,
					13		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
					78		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
					14		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
					12		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					131		/* 'if' || soft-space value soft-space */,
					15		/* IfExpCS::ownedCondition=27|81 || value */,
					163		/* 1*1-steps || value */,
					152		/* 'then' || soft-space value soft-space */,
					76		/* IfExpCS::ownedThenExpression=27 || value */,
					174		/* V00*1-steps || value */,
					31		/* IfExpCS::ownedIfThenExpressions+=20 || value */,
					163		/* 1*1-steps || value */,
					127		/* 'else' || soft-space value soft-space */,
					22		/* IfExpCS::ownedElseExpression=27 || value */,
					163		/* 1*1-steps || value */,
					129		/* 'endif' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						36) /* ExpCS|PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						9) /* ElseIfThenExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(81, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): "invalid"
		serializationRules[43] =
			new SerializationRule("InvalidLiteralExpCS", 36,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					135		/* 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[44] =
			new SerializationRule("LambdaLiteralExpCS", 41,
				createSerializationMatchSteps(
					230		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 27 */,
					19		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					116		/* 'Lambda' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					28		/* LambdaLiteralExpCS::ownedExpressionCS=27 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[45] =
			new SerializationRule("LetExpCS", 43,
				createSerializationMatchSteps(
					231		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : 27 */,
					232		/* check-rule essentialoclcs::LetExpCS.ownedVariables : 44 */,
					23		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
					62		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					139		/* 'let' || soft-space value soft-space */,
					88		/* LetExpCS::ownedVariables+=44 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					88		/* LetExpCS::ownedVariables+=44 || value */,
					163		/* 1*1-steps || value */,
					133		/* 'in' || soft-space value soft-space */,
					33		/* LetExpCS::ownedInExpression=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						16) /* LetVariableCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(44, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::LetVariableCS(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[46] =
			new SerializationRule("LetVariableCS", 44,
				createSerializationMatchSteps(
					258		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : 27 */,
					233		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 92 */,
					259		/* check-rule essentialoclcs::VariableCS.ownedType : 110 */,
					58		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
					126		/* assign V1 = |VariableCS::ownedType| */,
					80		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					213		/* NamedElementCS::name=128 || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					69		/* LetVariableCS::ownedRoundBracketedClause=92 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					85		/* VariableCS::ownedType=110 || value */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					37		/* VariableCS::ownedInitExpression=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						43) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						56) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[47] =
			new SerializationRule("MapLiteralExpCS", 57,
				createSerializationMatchSteps(
					234		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 58 */,
					235		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 59 */,
					24		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
					63		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
					108		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					81		/* MapLiteralExpCS::ownedType=59 || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					178		/* V00*5-steps || value */,
					56		/* MapLiteralExpCS::ownedParts+=58 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					56		/* MapLiteralExpCS::ownedParts+=58 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						23) /* MapLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						24) /* MapTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(58, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(59, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::MapLiteralPartCS(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS "<-" ownedValue=ExpCS }
		serializationRules[48] =
			new SerializationRule("MapLiteralPartCS", 58,
				createSerializationMatchSteps(
					236		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 27 */,
					237		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 27 */,
					26		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
					25		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					39		/* MapLiteralPartCS::ownedKey=27 || value */,
					163		/* 1*1-steps || value */,
					111		/* '<-' || soft-space value soft-space */,
					86		/* MapLiteralPartCS::ownedValue=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] }
		serializationRules[49] =
			new SerializationRule("MapTypeCS", 59,
				createSerializationMatchSteps(
					238		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110 */,
					239		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 110 */,
					83		/* assign V0 = |MapTypeCS::ownedValueType| */,
					28		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					27		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					117		/* 'Map' || soft-space value soft-space */,
					180		/* V00*8-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					40		/* MapTypeCS::ownedKeyType=110 || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					87		/* MapTypeCS::ownedValueType=110 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						6	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						56) /* TypeExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						56) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::Model(essentialoclcs::ContextCS): ownedExpression=ExpCS
		serializationRules[50] =
			new SerializationRule("Model", 60,
				createSerializationMatchSteps(
					220		/* check-rule essentialoclcs::ContextCS.ownedExpression : 27 */,
					10		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					25		/* ContextCS::ownedExpression=27 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] }
		serializationRules[51] =
			new SerializationRule("NameExpCS", 66,
				createSerializationMatchSteps(
					207		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
					208		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 80 */,
					209		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 92 */,
					210		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 100 */,
					146		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
					137		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
					112		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
					72		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
					0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					59		/* AbstractNameExpCS::ownedPathName=80 || value */,
					174		/* V00*1-steps || value */,
					72		/* AbstractNameExpCS::ownedSquareBracketedClauses+=100 || value */,
					182		/* V01*1-steps || value */,
					68		/* AbstractNameExpCS::ownedRoundBracketedClause=92 || value */,
					188		/* V02*1-steps || value */,
					19		/* AbstractNameExpCS::ownedCurlyBracketedClause=14 || value */,
					196		/* V03*4-steps || value */,
					163		/* 1*1-steps || value */,
					115		/* '@' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					146		/* 'pre' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						4	/* '@' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						6) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						34) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						43) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						49) /* SquareBracketedClauseCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(4 /* '@' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(14, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(80, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(100, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
		serializationRules[52] =
			new SerializationRule("NavigatingArgCS", 67,
				createSerializationMatchSteps(
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					47		/* NavigatingArgCS::ownedNameExpression=68 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ":" ownedType=TypeExpCS }
		serializationRules[53] =
			new SerializationRule("NavigatingArgCS", 67,
				createSerializationMatchSteps(
					243		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
					36		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					82		/* NavigatingArgCS::ownedType=110 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						56) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[54] =
			new SerializationRule("NavigatingArgCS", 67,
				createSerializationMatchSteps(
					240		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
					241		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					243		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
					120		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
					87		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
					36		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					46		/* NavigatingArgCS::ownedNameExpression=68 || value */,
					164		/* 1*11-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					82		/* NavigatingArgCS::ownedType=110 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					111		/* '<-' || soft-space value soft-space */,
					13		/* NavigatingArgCS::ownedCoIterator=8 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					34		/* NavigatingArgCS::ownedInitExpression=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						3) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						56) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[55] =
			new SerializationRule("NavigatingArgCS", 67,
				createSerializationMatchSteps(
					240		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
					241		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					88		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
					33		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					46		/* NavigatingArgCS::ownedNameExpression=68 || value */,
					172		/* 1*7-steps || value */,
					163		/* 1*1-steps || value */,
					111		/* '<-' || soft-space value soft-space */,
					13		/* NavigatingArgCS::ownedCoIterator=8 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					34		/* NavigatingArgCS::ownedInitExpression=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						3) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[56] =
			new SerializationRule("NavigatingArgCS", 67,
				createSerializationMatchSteps(
					240		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
					241		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					243		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
					34		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
					119		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
					89		/* assign V0 = |NavigatingArgCS::ownedType| */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					46		/* NavigatingArgCS::ownedNameExpression=68 || value */,
					164		/* 1*11-steps || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					82		/* NavigatingArgCS::ownedType=110 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					111		/* '<-' || soft-space value soft-space */,
					13		/* NavigatingArgCS::ownedCoIterator=8 || value */,
					163		/* 1*1-steps || value */,
					133		/* 'in' || soft-space value soft-space */,
					34		/* NavigatingArgCS::ownedInitExpression=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						3) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						56) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingBarArgCS(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[57] =
			new SerializationRule("NavigatingBarArgCS", 69,
				createSerializationMatchSteps(
					241		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					243		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
					89		/* assign V0 = |NavigatingArgCS::ownedType| */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					39		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
					120		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					158		/* '|' || soft-space value soft-space */,
					46		/* NavigatingArgCS::ownedNameExpression=68 || value */,
					179		/* V00*7-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					82		/* NavigatingArgCS::ownedType=110 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					34		/* NavigatingArgCS::ownedInitExpression=27 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						18	/* '|' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						56) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(18 /* '|' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[58] =
			new SerializationRule("NavigatingCommaArgCS", 70,
				createSerializationMatchSteps(
					240		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
					241		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					243		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
					120		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
					87		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
					36		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					37		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					46		/* NavigatingArgCS::ownedNameExpression=68 || value */,
					164		/* 1*11-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					82		/* NavigatingArgCS::ownedType=110 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					111		/* '<-' || soft-space value soft-space */,
					13		/* NavigatingArgCS::ownedCoIterator=8 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					34		/* NavigatingArgCS::ownedInitExpression=27 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						1	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						3) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						56) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[59] =
			new SerializationRule("NavigatingCommaArgCS", 70,
				createSerializationMatchSteps(
					240		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
					241		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					88		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
					33		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					37		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					46		/* NavigatingArgCS::ownedNameExpression=68 || value */,
					172		/* 1*7-steps || value */,
					163		/* 1*1-steps || value */,
					111		/* '<-' || soft-space value soft-space */,
					13		/* NavigatingArgCS::ownedCoIterator=8 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					34		/* NavigatingArgCS::ownedInitExpression=27 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						1	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						3) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */
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
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[60] =
			new SerializationRule("NavigatingCommaArgCS", 70,
				createSerializationMatchSteps(
					240		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
					241		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					243		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
					34		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
					119		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
					89		/* assign V0 = |NavigatingArgCS::ownedType| */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					37		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					46		/* NavigatingArgCS::ownedNameExpression=68 || value */,
					164		/* 1*11-steps || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					82		/* NavigatingArgCS::ownedType=110 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					111		/* '<-' || soft-space value soft-space */,
					13		/* NavigatingArgCS::ownedCoIterator=8 || value */,
					163		/* 1*1-steps || value */,
					133		/* 'in' || soft-space value soft-space */,
					34		/* NavigatingArgCS::ownedInitExpression=27 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						1	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						3) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						56) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS }
		serializationRules[61] =
			new SerializationRule("NavigatingCommaArgCS", 70,
				createSerializationMatchSteps(
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					37		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					46		/* NavigatingArgCS::ownedNameExpression=68 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						1	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */
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
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingSemiArgCS(essentialoclcs::NavigatingArgCS): { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[62] =
			new SerializationRule("NavigatingSemiArgCS", 71,
				createSerializationMatchSteps(
					241		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
					242		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
					243		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
					89		/* assign V0 = |NavigatingArgCS::ownedType| */,
					35		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					38		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
					120		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */,
					46		/* NavigatingArgCS::ownedNameExpression=68 || value */,
					179		/* V00*7-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					82		/* NavigatingArgCS::ownedType=110 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					34		/* NavigatingArgCS::ownedInitExpression=27 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						3	/* ';' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						26) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						56) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(3 /* ';' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[63] =
			new SerializationRule("NestedExpCS", 73,
				createSerializationMatchSteps(
					244		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : 27 */,
					40		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					27		/* NestedExpCS::ownedExpression=27 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
	}
	private void initSerializationRules1() {
		// EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[64] =
			new SerializationRule("NullLiteralExpCS", 75,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					141		/* 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[65] =
			new SerializationRule("NumberLiteralExpCS", 76,
				createSerializationMatchSteps(
					41		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					96		/* NumberLiteralExpCS::symbol=64 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[66] =
			new SerializationRule("PatternExpCS", 81,
				createSerializationMatchSteps(
					248		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 110 */,
					46		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
					91		/* assign V0 = |PatternExpCS::patternVariableName| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					174		/* V00*1-steps || value */,
					89		/* PatternExpCS::patternVariableName=128 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					64		/* PatternExpCS::ownedPatternType=110 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						56) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		serializationRules[67] =
			new SerializationRule("PrefixedLetExpCS", 85,
				createSerializationMatchSteps(
					246		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 85 */,
					42		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					212		/* NamedElementCS::name=125 || soft-space value soft-space */,
					233		/* OperatorExpCS::ownedRight=85 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						40) /* PrefixedLetExpCS */
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
						new RuleIndex_GrammarCardinality(85, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrefixedPrimaryExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[68] =
			new SerializationRule("PrefixedPrimaryExpCS", 86,
				createSerializationMatchSteps(
					247		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 86 */,
					42		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					212		/* NamedElementCS::name=125 || soft-space value soft-space */,
					234		/* OperatorExpCS::ownedRight=86 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						42) /* PrefixedPrimaryExpCS */
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
						new RuleIndex_GrammarCardinality(86, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		serializationRules[69] =
			new SerializationRule("PrimitiveTypeCS", 89,
				createSerializationMatchSteps(
					48		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					4		/* PrimitiveTypeRefCS::name=90 || soft-space value soft-space */
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
		serializationRules[70] =
			new SerializationRule("RoundBracketedClauseCS", 92,
				createSerializationMatchSteps(
					249		/* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 67|69|70|71 */,
					65		/* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
					109		/* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					176		/* V00*3-steps || value */,
					220		/* RoundBracketedClauseCS::ownedArguments+=67 || value */,
					182		/* V01*1-steps || value */,
					221		/* RoundBracketedClauseCS::ownedArguments+=70|71|69 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						28) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ZERO_OR_ONE),
						new RuleIndex_GrammarCardinality(69, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(70, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(71, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): "self"
		serializationRules[71] =
			new SerializationRule("SelfExpCS", 96,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					150		/* 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
		serializationRules[72] =
			new SerializationRule("ShadowPartCS", 97,
				createSerializationMatchSteps(
					250		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 102 */,
					49		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					36		/* ShadowPartCS::ownedInitExpression=102 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						50) /* StringLiteralExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(102, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[73] =
			new SerializationRule("ShadowPartCS", 97,
				createSerializationMatchSteps(
					251		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 27|81 */,
					49		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
					50		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					91		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					35		/* ShadowPartCS::ownedInitExpression=27|81 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						36) /* ExpCS|PatternExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(81, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// EssentialOCL::SimplePathNameCS(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[74] =
			new SerializationRule("SimplePathNameCS", 98,
				createSerializationMatchSteps(
					187		/* check-rule basecs::PathNameCS.ownedPathElements : 28 */,
					45		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
				),
				createSerializationSteps(
					225		/* PathNameCS::ownedPathElements+=28 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						11) /* FirstPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(28, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::SquareBracketedClauseCS(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[75] =
			new SerializationRule("SquareBracketedClauseCS", 100,
				createSerializationMatchSteps(
					252		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 27 */,
					66		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					119		/* '[' || no-space value no-space */,
					75		/* SquareBracketedClauseCS::ownedTerms+=27 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					75		/* SquareBracketedClauseCS::ownedTerms+=27 || value */,
					163		/* 1*1-steps || value */,
					120		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[76] =
			new SerializationRule("StringLiteralExpCS", 102,
				createSerializationMatchSteps(
					93		/* assign V0 = |StringLiteralExpCS::segments| */
				),
				createSerializationSteps(
					174		/* V00*1-steps || value */,
					93		/* StringLiteralExpCS::segments+=101 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[77] =
			new SerializationRule("TupleLiteralExpCS", 106,
				createSerializationMatchSteps(
					253		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 107 */,
					69		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					118		/* 'Tuple' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					57		/* TupleLiteralExpCS::ownedParts+=107 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					57		/* TupleLiteralExpCS::ownedParts+=107 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						54) /* TupleLiteralPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TupleLiteralPartCS(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[78] =
			new SerializationRule("TupleLiteralPartCS", 107,
				createSerializationMatchSteps(
					258		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : 27 */,
					259		/* check-rule essentialoclcs::VariableCS.ownedType : 110 */,
					58		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
					99		/* assign V0 = |VariableCS::ownedType| */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					213		/* NamedElementCS::name=128 || soft-space value soft-space */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					85		/* VariableCS::ownedType=110 || value */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					37		/* VariableCS::ownedInitExpression=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						56) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] }
		serializationRules[79] =
			new SerializationRule("TupleTypeCS", 109,
				createSerializationMatchSteps(
					200		/* check-rule basecs::TupleTypeCS.ownedParts : 108 */,
					52		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					70		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					110		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					136		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					118		/* 'Tuple' || soft-space value soft-space */,
					175		/* V00*10-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					184		/* V01*5-steps || value */,
					58		/* TupleTypeCS::ownedParts+=108 || value */,
					189		/* V02*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					58		/* TupleTypeCS::ownedParts+=108 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						7	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						55) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(7 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[80] =
			new SerializationRule("TypeExpCS", 110,
				createSerializationMatchSteps(
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					97		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					48		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					4		/* PrimitiveTypeRefCS::name=90 || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */
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
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[81] =
			new SerializationRule("TypeExpCS", 110,
				createSerializationMatchSteps(
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					200		/* check-rule basecs::TupleTypeCS.ownedParts : 108 */,
					150		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					52		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					70		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					110		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					136		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					118		/* 'Tuple' || soft-space value soft-space */,
					175		/* V00*10-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					184		/* V01*5-steps || value */,
					58		/* TupleTypeCS::ownedParts+=108 || value */,
					189		/* V02*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					58		/* TupleTypeCS::ownedParts+=108 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					194		/* V03*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						7	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						55) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(7 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::CollectionPatternCS): { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[82] =
			new SerializationRule("TypeExpCS", 110,
				createSerializationMatchSteps(
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					216		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 81 */,
					217		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : 12 */,
					142		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					74		/* assign V0 = |CollectionPatternCS::restVariableName| */,
					104		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
					4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					79		/* CollectionPatternCS::ownedType=12 || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					175		/* V00*10-steps || value */,
					54		/* CollectionPatternCS::ownedParts+=81 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					54		/* CollectionPatternCS::ownedParts+=81 || value */,
					169		/* 1*4-steps || value */,
					163		/* 1*1-steps || value */,
					104		/* '++' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					92		/* CollectionPatternCS::restVariableName=31 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */,
					188		/* V02*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						35) /* PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						5) /* CollectionTypeCS */
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
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(81, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[83] =
			new SerializationRule("TypeExpCS", 110,
				createSerializationMatchSteps(
					218		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62 */,
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					219		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 111 */,
					142		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					75		/* assign V0 = |CollectionTypeCS::ownedType| */,
					5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					113		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					3		/* CollectionTypeCS::name=13 || soft-space value soft-space */,
					179		/* V00*7-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					80		/* CollectionTypeCS::ownedType=111 || value */,
					182		/* V01*1-steps || value */,
					14		/* CollectionTypeCS::ownedCollectionMultiplicity=62 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					188		/* V02*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						57) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(111, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[84] =
			new SerializationRule("TypeExpCS", 110,
				createSerializationMatchSteps(
					238		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110 */,
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					239		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 110 */,
					125		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					83		/* assign V0 = |MapTypeCS::ownedValueType| */,
					28		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					27		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					117		/* 'Map' || soft-space value soft-space */,
					180		/* V00*8-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					40		/* MapTypeCS::ownedKeyType=110 || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					87		/* MapTypeCS::ownedValueType=110 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					182		/* V01*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						6	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						56) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						56) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::TypeNameExpCS): { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[85] =
			new SerializationRule("TypeExpCS", 110,
				createSerializationMatchSteps(
					255		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					256		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 80 */,
					257		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 27 */,
					142		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					96		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
					54		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
					124		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
				),
				createSerializationSteps(
					61		/* TypeNameExpCS::ownedPathName=80 || value */,
					179		/* V00*7-steps || value */,
					20		/* TypeNameExpCS::ownedCurlyBracketedClause=14 || value */,
					184		/* V01*5-steps || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					63		/* TypeNameExpCS::ownedPatternGuard=27 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */,
					188		/* V02*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						6) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						34) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(14, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(80, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[86] =
			new SerializationRule("TypeLiteralExpCS", 113,
				createSerializationMatchSteps(
					254		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 114 */,
					53		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					83		/* TypeLiteralExpCS::ownedType=114 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						59) /* TypeLiteralWithMultiplicityCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(114, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[87] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 114,
				createSerializationMatchSteps(
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					97		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					48		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					4		/* PrimitiveTypeRefCS::name=90 || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */
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
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[88] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 114,
				createSerializationMatchSteps(
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					200		/* check-rule basecs::TupleTypeCS.ownedParts : 108 */,
					150		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					52		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					70		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					110		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					136		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					118		/* 'Tuple' || soft-space value soft-space */,
					175		/* V00*10-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					184		/* V01*5-steps || value */,
					58		/* TupleTypeCS::ownedParts+=108 || value */,
					189		/* V02*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					58		/* TupleTypeCS::ownedParts+=108 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					194		/* V03*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						7	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						55) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(7 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[89] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 114,
				createSerializationMatchSteps(
					218		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62 */,
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					219		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 111 */,
					142		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					75		/* assign V0 = |CollectionTypeCS::ownedType| */,
					5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					113		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					3		/* CollectionTypeCS::name=13 || soft-space value soft-space */,
					179		/* V00*7-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					80		/* CollectionTypeCS::ownedType=111 || value */,
					182		/* V01*1-steps || value */,
					14		/* CollectionTypeCS::ownedCollectionMultiplicity=62 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					188		/* V02*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						57) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(111, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[90] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 114,
				createSerializationMatchSteps(
					238		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110 */,
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					239		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 110 */,
					125		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					83		/* assign V0 = |MapTypeCS::ownedValueType| */,
					28		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					27		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					117		/* 'Map' || soft-space value soft-space */,
					180		/* V00*8-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					40		/* MapTypeCS::ownedKeyType=110 || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					87		/* MapTypeCS::ownedValueType=110 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					182		/* V01*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						6	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						56) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						56) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] }
		serializationRules[91] =
			new SerializationRule("TypeNameExpCS", 115,
				createSerializationMatchSteps(
					255		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
					256		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 80 */,
					257		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 27 */,
					96		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
					54		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
					124		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
				),
				createSerializationSteps(
					61		/* TypeNameExpCS::ownedPathName=80 || value */,
					179		/* V00*7-steps || value */,
					20		/* TypeNameExpCS::ownedCurlyBracketedClause=14 || value */,
					184		/* V01*5-steps || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					63		/* TypeNameExpCS::ownedPatternGuard=27 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						6) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						34) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(14, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(80, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::URIFirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[92] =
			new SerializationRule("URIFirstPathElementCS", 123,
				createSerializationMatchSteps(
					44		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					238		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
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
		serializationRules[93] =
			new SerializationRule("URIFirstPathElementCS", 123,
				createSerializationMatchSteps(
					44		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					236		/* PathElementCS::referredElement=URI || soft-space value soft-space */
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
		serializationRules[94] =
			new SerializationRule("URIPathNameCS", 124,
				createSerializationMatchSteps(
					190		/* check-rule basecs::PathNameCS.ownedPathElements : 74|123 */,
					64		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					224		/* PathNameCS::ownedPathElements+=123 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					109		/* '::' || no-space value no-space */,
					227		/* PathNameCS::ownedPathElements+=74 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						69) /* NextPathElementCS|URIFirstPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(74, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(123, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): "*"
		serializationRules[95] =
			new SerializationRule("UnlimitedNaturalLiteralExpCS", 126,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					103		/* '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// OCLstdlib::AccumulatorCS(basecs::ParameterCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[96] =
			new SerializationRule("AccumulatorCS", 1,
				createSerializationMatchSteps(
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					214		/* NamedElementCS::name=31 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
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
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::AnnotationCS(basecs::AnnotationCS): { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" }
		serializationRules[97] =
			new SerializationRule("AnnotationCS", 2,
				createSerializationMatchSteps(
					169		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					59		/* assign V0 = (|AnnotationElementCS::ownedDetails| > 0) */,
					101		/* assign V1 = (|AnnotationElementCS::ownedDetails| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					122		/* 'annotation' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					215		/* NamedElementCS::name=31|94 || soft-space value soft-space */,
					181		/* V00*9-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					21		/* AnnotationElementCS::ownedDetails+=16 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					21		/* AnnotationElementCS::ownedDetails+=16 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						7) /* DetailCS */
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
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLstdlib::AnnotationCS(basecs::AnnotationCS): { "annotation" name=(Identifier|SINGLE_QUOTED_STRING) { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" ownedAnnotations+=AnnotationElementCS "}" } }
		serializationRules[98] =
			new SerializationRule("AnnotationCS", 2,
				createSerializationMatchSteps(
					177		/* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
					169		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					29		/* assert (|ModelElementCS::ownedAnnotations| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					59		/* assign V0 = (|AnnotationElementCS::ownedDetails| > 0) */,
					101		/* assign V1 = (|AnnotationElementCS::ownedDetails| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					122		/* 'annotation' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					215		/* NamedElementCS::name=31|94 || soft-space value soft-space */,
					181		/* V00*9-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					21		/* AnnotationElementCS::ownedDetails+=16 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					21		/* AnnotationElementCS::ownedDetails+=16 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					170		/* 1*5-steps || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					9		/* ModelElementCS::ownedAnnotations+=3 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						1) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						7) /* DetailCS */
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
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLstdlib::DetailCS(basecs::DetailCS): { name=(Name|SINGLE_QUOTED_STRING) "=" values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
		serializationRules[99] =
			new SerializationRule("DetailCS", 16,
				createSerializationMatchSteps(
					76		/* assign V0 = |DetailCS::values| */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					219		/* NamedElementCS::name=65|94 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					99		/* DetailCS::values+=94|56 || soft-space value soft-space */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DETAIL_CS__VALUES,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				},
				null);
			;
		// OCLstdlib::DocumentationCS(basecs::DocumentationCS): { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" }
		serializationRules[100] =
			new SerializationRule("DocumentationCS", 17,
				createSerializationMatchSteps(
					169		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					77		/* assign V0 = |DocumentationCS::value| */,
					102		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
					131		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					126		/* 'documentation' || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					98		/* DocumentationCS::value=94 || soft-space value soft-space */,
					185		/* V01*9-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					21		/* AnnotationElementCS::ownedDetails+=16 || value */,
					189		/* V02*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					21		/* AnnotationElementCS::ownedDetails+=16 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						7) /* DetailCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLstdlib::ImportCS(basecs::ImportCS): { "import" { name=Identifier ":" }[?] ownedPathName=URIPathNameCS isAll="::*"[?] }
		serializationRules[101] =
			new SerializationRule("ImportCS", 33,
				createSerializationMatchSteps(
					173		/* check-rule basecs::ImportCS.ownedPathName : 124 */,
					115		/* assign V1 = |ImportCS::isAll.'::*'| */,
					17		/* assert (|ImportCS::ownedPathName| - 1) == 0 */,
					86		/* assign V0 = |NamedElementCS::name| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					132		/* 'import' || soft-space value soft-space */,
					177		/* V00*4-steps || value */,
					163		/* 1*1-steps || value */,
					214		/* NamedElementCS::name=31 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					60		/* ImportCS::ownedPathName=124 || value */,
					182		/* V01*1-steps || value */,
					108		/* '::*' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
						2	/* '::*' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						71) /* URIPathNameCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(2 /* '::*' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(124, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::InvCS(oclstdlibcs::LibConstraintCS): { stereotype="inv" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" }
		serializationRules[102] =
			new SerializationRule("InvCS", 35,
				createSerializationMatchSteps(
					171		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : 99 */,
					172		/* check-rule basecs::ConstraintCS.ownedSpecification : 99 */,
					6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
					86		/* assign V0 = |NamedElementCS::name| */,
					7		/* assert (|ConstraintCS::stereotype.'inv'| - 1) == 0 */,
					114		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					134		/* 'inv' || soft-space value soft-space */,
					180		/* V00*8-steps || value */,
					163		/* 1*1-steps || value */,
					213		/* NamedElementCS::name=128 || soft-space value soft-space */,
					184		/* V01*5-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					43		/* ConstraintCS::ownedMessageSpecification=99 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					71		/* ConstraintCS::ownedSpecification=99 || value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						10	/* 'inv' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						48) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						48) /* SpecificationCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(10 /* 'inv' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::IteratorCS(basecs::ParameterCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[103] =
			new SerializationRule("IteratorCS", 37,
				createSerializationMatchSteps(
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					214		/* NamedElementCS::name=31 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
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
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::LambdaContextTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=LibPathNameCS
		serializationRules[104] =
			new SerializationRule("LambdaContextTypeRefCS", 40,
				createSerializationMatchSteps(
					205		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
					57		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					62		/* TypedTypeRefCS::ownedPathName=52 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						21) /* LibPathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(52, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::LambdaTypeCS(basecs::LambdaTypeCS): { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS }
		serializationRules[105] =
			new SerializationRule("LambdaTypeCS", 42,
				createSerializationMatchSteps(
					174		/* check-rule basecs::LambdaTypeCS.ownedContextType : 40 */,
					175		/* check-rule basecs::LambdaTypeCS.ownedParameterTypes : 118 */,
					176		/* check-rule basecs::LambdaTypeCS.ownedResultType : 119 */,
					199		/* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
					22		/* assert (|LambdaTypeCS::ownedResultType| - 1) == 0 */,
					21		/* assert (|LambdaTypeCS::ownedContextType| - 1) == 0 */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					20		/* assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0 */,
					106		/* assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0) */,
					132		/* assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1) */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					116		/* 'Lambda' || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					70		/* TemplateableElementCS::ownedSignature=105 || value */,
					18		/* LambdaTypeCS::ownedContextType=40 || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					184		/* V01*5-steps || value */,
					50		/* LambdaTypeCS::ownedParameterTypes+=118 || value */,
					189		/* V02*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					50		/* LambdaTypeCS::ownedParameterTypes+=118 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					67		/* LambdaTypeCS::ownedResultType=119 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME,
						5	/* 'Lambda' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
						15) /* LambdaContextTypeRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
						65) /* TypedMultiplicityRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
						66) /* TypedRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						53) /* TemplateSignatureCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* 'Lambda' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(119, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLstdlib::LibClassCS(oclstdlibcs::LibClassCS): { isAbstract="abstract"[?] "type" name=AnyName ownedSignature=TemplateSignatureCS[?] { ":" metaclassName=AnyName }[?] { "conformsTo" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] "{" { ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" }
		serializationRules[106] =
			new SerializationRule("LibClassCS", 45,
				createSerializationMatchSteps(
					177		/* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
					170		/* check-rule basecs::ClassCS.ownedConstraints : 35 */,
					192		/* check-rule basecs::StructuredClassCS.ownedOperations : 77 */,
					193		/* check-rule basecs::StructuredClassCS.ownedProperties : 53 */,
					199		/* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
					194		/* check-rule basecs::StructuredClassCS.ownedSuperTypes : 119 */,
					166		/* assign V8 = |ModelElementCS::ownedAnnotations| */,
					162		/* assign V7 = |ClassCS::ownedConstraints| */,
					161		/* assign V6 = |StructuredClassCS::ownedProperties| */,
					158		/* assign V5 = |StructuredClassCS::ownedOperations| */,
					139		/* assign V2 = |LibClassCS::metaclassName| */,
					123		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					94		/* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
					145		/* assign V3 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
					152		/* assign V4 = (|StructuredClassCS::ownedSuperTypes| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					174		/* V00*1-steps || value */,
					121		/* 'abstract' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					153		/* 'type' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					216		/* NamedElementCS::name=4 || soft-space value soft-space */,
					182		/* V01*1-steps || value */,
					70		/* TemplateableElementCS::ownedSignature=105 || value */,
					190		/* V02*4-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					2		/* LibClassCS::metaclassName=AnyName || soft-space value soft-space */,
					197		/* V03*7-steps || value */,
					163		/* 1*1-steps || value */,
					125		/* 'conformsTo' || soft-space value soft-space */,
					74		/* StructuredClassCS::ownedSuperTypes+=119 || value */,
					199		/* V04*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					74		/* StructuredClassCS::ownedSuperTypes+=119 || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					173		/* 1*8-steps || value */,
					201		/* V05*1-steps || value */,
					48		/* StructuredClassCS::ownedOperations+=77 || value */,
					203		/* V06*1-steps || value */,
					66		/* StructuredClassCS::ownedProperties+=53 || value */,
					205		/* V07*1-steps || value */,
					17		/* ClassCS::ownedConstraints+=35 || value */,
					207		/* V08*1-steps || value */,
					9		/* ModelElementCS::ownedAnnotations+=3 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
						8	/* 'abstract' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						1) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						13) /* InvCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
						30) /* OperationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
						22) /* LibPropertyCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						53) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						66) /* TypedRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(8 /* 'abstract' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(35, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(77, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(53, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(119, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLstdlib::LibCoercionCS(oclstdlibcs::LibCoercionCS): { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[107] =
			new SerializationRule("LibCoercionCS", 46,
				createSerializationMatchSteps(
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					79		/* assign V0 = |JavaImplementationCS::implementation| */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					124		/* 'coercion' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */,
					177		/* V00*4-steps || value */,
					163		/* 1*1-steps || value */,
					113		/* '=>' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
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
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::LibCoercionCS(oclstdlibcs::LibCoercionCS): { "coercion" name=Name "(" ")" ":" ownedType=TypedMultiplicityRefCS { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } }
		serializationRules[108] =
			new SerializationRule("LibCoercionCS", 46,
				createSerializationMatchSteps(
					177		/* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
					181		/* check-rule basecs::OperationCS.ownedPostconditions : 83 */,
					182		/* check-rule basecs::OperationCS.ownedPreconditions : 82 */,
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					149		/* assign V3 = |OperationCS::ownedPostconditions| */,
					140		/* assign V2 = |OperationCS::ownedPreconditions| */,
					117		/* assign V1 = |ModelElementCS::ownedAnnotations| */,
					79		/* assign V0 = |JavaImplementationCS::implementation| */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					124		/* 'coercion' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */,
					177		/* V00*4-steps || value */,
					163		/* 1*1-steps || value */,
					113		/* '=>' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
					164		/* 1*11-steps || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					171		/* 1*6-steps || value */,
					182		/* V01*1-steps || value */,
					9		/* ModelElementCS::ownedAnnotations+=3 || value */,
					188		/* V02*1-steps || value */,
					230		/* OperationCS::ownedPreconditions+=82 || value */,
					194		/* V03*1-steps || value */,
					229		/* OperationCS::ownedPostconditions+=83 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						1) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						38) /* PreCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						37) /* PostCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
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
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(82, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::LibIterationCS(oclstdlibcs::LibIterationCS): { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulators+=AccumulatorCS { "," ownedAccumulators+=AccumulatorCS }[*] }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating="invalidating"[?] isValidating="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[109] =
			new SerializationRule("LibIterationCS", 47,
				createSerializationMatchSteps(
					260		/* check-rule oclstdlibcs::LibIterationCS.ownedAccumulators : 1 */,
					261		/* check-rule oclstdlibcs::LibIterationCS.ownedIterators : 37 */,
					179		/* check-rule basecs::OperationCS.ownedParameters : 79 */,
					199		/* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					165		/* assign V8 = |JavaImplementationCS::implementation| */,
					164		/* assign V7 = |LibIterationCS::isValidating.'validating'| */,
					159		/* assign V6 = |LibIterationCS::isInvalidating.'invalidating'| */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					107		/* assign V1 = (|LibIterationCS::ownedIterators| - 1) */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					151		/* assign V4 = (|OperationCS::ownedParameters| > 0) */,
					155		/* assign V5 = (|OperationCS::ownedParameters| - 1) */,
					133		/* assign V2 = (|LibIterationCS::ownedAccumulators| > 0) */,
					143		/* assign V3 = (|LibIterationCS::ownedAccumulators| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					137		/* 'iteration' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					70		/* TemplateableElementCS::ownedSignature=105 || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					38		/* LibIterationCS::ownedIterators+=37 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					38		/* LibIterationCS::ownedIterators+=37 || value */,
					193		/* V02*7-steps || value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */,
					7		/* LibIterationCS::ownedAccumulators+=1 || value */,
					195		/* V03*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					7		/* LibIterationCS::ownedAccumulators+=1 || value */,
					200		/* V04*7-steps || value */,
					163		/* 1*1-steps || value */,
					158		/* '|' || soft-space value soft-space */,
					51		/* OperationCS::ownedParameters+=79 || value */,
					202		/* V05*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					51		/* OperationCS::ownedParameters+=79 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */,
					203		/* V06*1-steps || value */,
					136		/* 'invalidating' || soft-space value soft-space */,
					205		/* V07*1-steps || value */,
					155		/* 'validating' || soft-space value soft-space */,
					208		/* V08*4-steps || value */,
					163		/* 1*1-steps || value */,
					113		/* '=>' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING,
						11	/* 'invalidating' */
					),
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING,
						17	/* 'validating' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
						0) /* AccumulatorCS */,
					createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
						14) /* IteratorCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						33) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						53) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(11 /* 'invalidating' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(17 /* 'validating' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(1, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(37, GrammarCardinality.ONE_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::LibIterationCS(oclstdlibcs::LibIterationCS): { "iteration" name=Name ownedSignature=TemplateSignatureCS[?] "(" ownedIterators+=IteratorCS { "," ownedIterators+=IteratorCS }[*] { ";" ownedAccumulators+=AccumulatorCS { "," ownedAccumulators+=AccumulatorCS }[*] }[?] { "|" ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isInvalidating="invalidating"[?] isValidating="validating"[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] } "}" } }
		serializationRules[110] =
			new SerializationRule("LibIterationCS", 47,
				createSerializationMatchSteps(
					260		/* check-rule oclstdlibcs::LibIterationCS.ownedAccumulators : 1 */,
					177		/* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
					261		/* check-rule oclstdlibcs::LibIterationCS.ownedIterators : 37 */,
					179		/* check-rule basecs::OperationCS.ownedParameters : 79 */,
					181		/* check-rule basecs::OperationCS.ownedPostconditions : 83 */,
					182		/* check-rule basecs::OperationCS.ownedPreconditions : 82 */,
					199		/* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					129		/* assign V11 = |OperationCS::ownedPostconditions| */,
					128		/* assign V10 = |OperationCS::ownedPreconditions| */,
					167		/* assign V9 = |ModelElementCS::ownedAnnotations| */,
					165		/* assign V8 = |JavaImplementationCS::implementation| */,
					164		/* assign V7 = |LibIterationCS::isValidating.'validating'| */,
					159		/* assign V6 = |LibIterationCS::isInvalidating.'invalidating'| */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					107		/* assign V1 = (|LibIterationCS::ownedIterators| - 1) */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					151		/* assign V4 = (|OperationCS::ownedParameters| > 0) */,
					155		/* assign V5 = (|OperationCS::ownedParameters| - 1) */,
					133		/* assign V2 = (|LibIterationCS::ownedAccumulators| > 0) */,
					143		/* assign V3 = (|LibIterationCS::ownedAccumulators| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					137		/* 'iteration' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					70		/* TemplateableElementCS::ownedSignature=105 || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					38		/* LibIterationCS::ownedIterators+=37 || value */,
					183		/* V01*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					38		/* LibIterationCS::ownedIterators+=37 || value */,
					193		/* V02*7-steps || value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */,
					7		/* LibIterationCS::ownedAccumulators+=1 || value */,
					195		/* V03*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					7		/* LibIterationCS::ownedAccumulators+=1 || value */,
					200		/* V04*7-steps || value */,
					163		/* 1*1-steps || value */,
					158		/* '|' || soft-space value soft-space */,
					51		/* OperationCS::ownedParameters+=79 || value */,
					202		/* V05*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					51		/* OperationCS::ownedParameters+=79 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */,
					203		/* V06*1-steps || value */,
					136		/* 'invalidating' || soft-space value soft-space */,
					205		/* V07*1-steps || value */,
					155		/* 'validating' || soft-space value soft-space */,
					208		/* V08*4-steps || value */,
					163		/* 1*1-steps || value */,
					113		/* '=>' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
					164		/* 1*11-steps || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					171		/* 1*6-steps || value */,
					209		/* V09*1-steps || value */,
					9		/* ModelElementCS::ownedAnnotations+=3 || value */,
					186		/* V10*1-steps || value */,
					230		/* OperationCS::ownedPreconditions+=82 || value */,
					187		/* V11*1-steps || value */,
					229		/* OperationCS::ownedPostconditions+=83 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING,
						11	/* 'invalidating' */
					),
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING,
						17	/* 'validating' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
						0) /* AccumulatorCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						1) /* AnnotationElementCS */,
					createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
						14) /* IteratorCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						33) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						38) /* PreCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						37) /* PostCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						53) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(11 /* 'invalidating' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(17 /* 'validating' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(1, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(37, GrammarCardinality.ONE_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(82, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::LibOperationCS(oclstdlibcs::LibOperationCS): { isStatic="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating="validating"[?] isInvalidating="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[111] =
			new SerializationRule("LibOperationCS", 48,
				createSerializationMatchSteps(
					179		/* check-rule basecs::OperationCS.ownedParameters : 79 */,
					199		/* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					163		/* assign V7 = |JavaImplementationCS::implementation| */,
					160		/* assign V6 = |LibOperationCS::precedence| */,
					156		/* assign V5 = |LibOperationCS::isInvalidating.'invalidating'| */,
					153		/* assign V4 = |LibOperationCS::isValidating.'validating'| */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					123		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					81		/* assign V0 = |LibOperationCS::isStatic.'static'| */,
					135		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
					144		/* assign V3 = (|OperationCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					174		/* V00*1-steps || value */,
					151		/* 'static' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					142		/* 'operation' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					182		/* V01*1-steps || value */,
					70		/* TemplateableElementCS::ownedSignature=105 || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					191		/* V02*5-steps || value */,
					51		/* OperationCS::ownedParameters+=79 || value */,
					195		/* V03*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					51		/* OperationCS::ownedParameters+=79 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */,
					198		/* V04*1-steps || value */,
					155		/* 'validating' || soft-space value soft-space */,
					201		/* V05*1-steps || value */,
					136		/* 'invalidating' || soft-space value soft-space */,
					204		/* V06*6-steps || value */,
					163		/* 1*1-steps || value */,
					147		/* 'precedence' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					90		/* LibOperationCS::precedence=Name || soft-space value soft-space */,
					206		/* V07*4-steps || value */,
					163		/* 1*1-steps || value */,
					113		/* '=>' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING,
						11	/* 'invalidating' */
					),
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC,
						15	/* 'static' */
					),
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING,
						17	/* 'validating' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						33) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						53) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(11 /* 'invalidating' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(15 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(17 /* 'validating' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// OCLstdlib::LibOperationCS(oclstdlibcs::LibOperationCS): { isStatic="static"[?] "operation" name=Name ownedSignature=TemplateSignatureCS[?] "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypedMultiplicityRefCS isValidating="validating"[?] isInvalidating="invalidating"[?] { "precedence" "=" precedence=Name }[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS ";" }[*] ownedPostconditions+=PostCS[*] ownedPreconditions+=PreCS[*] } "}" } }
		serializationRules[112] =
			new SerializationRule("LibOperationCS", 48,
				createSerializationMatchSteps(
					177		/* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
					178		/* check-rule basecs::OperationCS.ownedBodyExpressions : 99 */,
					179		/* check-rule basecs::OperationCS.ownedParameters : 79 */,
					180		/* check-rule basecs::OperationCS.ownedPostconditions : 82 */,
					183		/* check-rule basecs::OperationCS.ownedPreconditions : 83 */,
					199		/* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					130		/* assign V11 = |OperationCS::ownedPreconditions| */,
					127		/* assign V10 = |OperationCS::ownedPostconditions| */,
					168		/* assign V9 = |OperationCS::ownedBodyExpressions| */,
					166		/* assign V8 = |ModelElementCS::ownedAnnotations| */,
					163		/* assign V7 = |JavaImplementationCS::implementation| */,
					160		/* assign V6 = |LibOperationCS::precedence| */,
					156		/* assign V5 = |LibOperationCS::isInvalidating.'invalidating'| */,
					153		/* assign V4 = |LibOperationCS::isValidating.'validating'| */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					123		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					81		/* assign V0 = |LibOperationCS::isStatic.'static'| */,
					135		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
					144		/* assign V3 = (|OperationCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					174		/* V00*1-steps || value */,
					151		/* 'static' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					142		/* 'operation' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					182		/* V01*1-steps || value */,
					70		/* TemplateableElementCS::ownedSignature=105 || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					191		/* V02*5-steps || value */,
					51		/* OperationCS::ownedParameters+=79 || value */,
					195		/* V03*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					51		/* OperationCS::ownedParameters+=79 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */,
					198		/* V04*1-steps || value */,
					155		/* 'validating' || soft-space value soft-space */,
					201		/* V05*1-steps || value */,
					136		/* 'invalidating' || soft-space value soft-space */,
					204		/* V06*6-steps || value */,
					163		/* 1*1-steps || value */,
					147		/* 'precedence' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					90		/* LibOperationCS::precedence=Name || soft-space value soft-space */,
					206		/* V07*4-steps || value */,
					163		/* 1*1-steps || value */,
					113		/* '=>' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
					167		/* 1*19-steps || value */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					166		/* 1*14-steps || value */,
					207		/* V08*1-steps || value */,
					9		/* ModelElementCS::ownedAnnotations+=3 || value */,
					210		/* V09*7-steps || value */,
					163		/* 1*1-steps || value */,
					123		/* 'body' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					11		/* OperationCS::ownedBodyExpressions+=99 || value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */,
					186		/* V10*1-steps || value */,
					228		/* OperationCS::ownedPostconditions+=82 || value */,
					187		/* V11*1-steps || value */,
					231		/* OperationCS::ownedPreconditions+=83 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING,
						11	/* 'invalidating' */
					),
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC,
						15	/* 'static' */
					),
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING,
						17	/* 'validating' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						1) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						48) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						33) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						37) /* PostCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						38) /* PreCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						53) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(11 /* 'invalidating' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(15 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(17 /* 'validating' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(79, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(82, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// OCLstdlib::LibOppositeCS(oclstdlibcs::LibOppositeCS): { "opposite" name=Name ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[113] =
			new SerializationRule("LibOppositeCS", 49,
				createSerializationMatchSteps(
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					143		/* 'opposite' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
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
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::LibPackageCS(oclstdlibcs::LibPackageCS): { "library" name=Name { ":" nsPrefix=Identifier "=" nsURI=URI }[?] "{" { ownedPackages+=PackageCS[*] { "precedence" ownedPrecedences+=PrecedenceCS[+] ";" }[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" }
		serializationRules[114] =
			new SerializationRule("LibPackageCS", 50,
				createSerializationMatchSteps(
					177		/* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
					184		/* check-rule basecs::PackageCS.ownedClasses : 7 */,
					186		/* check-rule basecs::PackageOwnerCS.ownedPackages : 78 */,
					262		/* check-rule oclstdlibcs::LibPackageCS.ownedPrecedences : 84 */,
					157		/* assign V5 = |ModelElementCS::ownedAnnotations| */,
					154		/* assign V4 = |PackageCS::ownedClasses| */,
					121		/* assign V1 = |PackageOwnerCS::ownedPackages| */,
					90		/* assign V0 = |PackageCS::nsURI| */,
					43		/* assert (|PackageCS::nsPrefix| - V0) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					134		/* assign V2 = (|LibPackageCS::ownedPrecedences| > 0) */,
					147		/* assign V3 = |LibPackageCS::ownedPrecedences| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					140		/* 'library' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					180		/* V00*8-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					5		/* PackageCS::nsPrefix=31 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					6		/* PackageCS::nsURI=122 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					165		/* 1*13-steps || value */,
					182		/* V01*1-steps || value */,
					223		/* PackageOwnerCS::ownedPackages+=78 || value */,
					192		/* V02*6-steps || value */,
					163		/* 1*1-steps || value */,
					147		/* 'precedence' || soft-space value soft-space */,
					194		/* V03*1-steps || value */,
					65		/* LibPackageCS::ownedPrecedences+=84 || value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */,
					198		/* V04*1-steps || value */,
					12		/* PackageCS::ownedClasses+=7 || half-new-line value half-new-line */,
					201		/* V05*1-steps || value */,
					9		/* ModelElementCS::ownedAnnotations+=3 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						1) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						2) /* ClassCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						32) /* PackageCS */,
					createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES,
						39) /* PrecedenceCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(78, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLstdlib::LibPathElementCS(basecs::PathElementCS): referredElement=Name
		serializationRules[115] =
			new SerializationRule("LibPathElementCS", 51,
				createSerializationMatchSteps(
					44		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					235		/* PathElementCS::referredElement=Name || soft-space value soft-space */
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
		// OCLstdlib::LibPathNameCS(basecs::PathNameCS): { ownedPathElements+=LibPathElementCS { "::" ownedPathElements+=LibPathElementCS }[*] }
		serializationRules[116] =
			new SerializationRule("LibPathNameCS", 52,
				createSerializationMatchSteps(
					189		/* check-rule basecs::PathNameCS.ownedPathElements : 51 */,
					64		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					226		/* PathNameCS::ownedPathElements+=51 || value */,
					176		/* V00*3-steps || value */,
					163		/* 1*1-steps || value */,
					109		/* '::' || no-space value no-space */,
					226		/* PathNameCS::ownedPathElements+=51 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						20) /* LibPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(51, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// OCLstdlib::LibPropertyCS(oclstdlibcs::LibPropertyCS): { isStatic="static"[?] "property" name=Name ":" ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] ";" }
		serializationRules[117] =
			new SerializationRule("LibPropertyCS", 53,
				createSerializationMatchSteps(
					263		/* check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : 49 */,
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					138		/* assign V2 = |JavaImplementationCS::implementation| */,
					116		/* assign V1 = |LibPropertyCS::ownedOpposite| */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					82		/* assign V0 = |LibPropertyCS::isStatic.'static'| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					174		/* V00*1-steps || value */,
					151		/* 'static' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					148		/* 'property' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */,
					182		/* V01*1-steps || value */,
					49		/* LibPropertyCS::ownedOpposite=49 || value */,
					190		/* V02*4-steps || value */,
					163		/* 1*1-steps || value */,
					113		/* '=>' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC,
						15	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
						18) /* LibOppositeCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(15 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(49, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::LibPropertyCS(oclstdlibcs::LibPropertyCS): { isStatic="static"[?] "property" name=Name ":" ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { "=>" implementation=SINGLE_QUOTED_STRING }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } }
		serializationRules[118] =
			new SerializationRule("LibPropertyCS", 53,
				createSerializationMatchSteps(
					177		/* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
					263		/* check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : 49 */,
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					148		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
					138		/* assign V2 = |JavaImplementationCS::implementation| */,
					116		/* assign V1 = |LibPropertyCS::ownedOpposite| */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					82		/* assign V0 = |LibPropertyCS::isStatic.'static'| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					174		/* V00*1-steps || value */,
					151		/* 'static' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					148		/* 'property' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */,
					182		/* V01*1-steps || value */,
					49		/* LibPropertyCS::ownedOpposite=49 || value */,
					190		/* V02*4-steps || value */,
					163		/* 1*1-steps || value */,
					113		/* '=>' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					0		/* JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space */,
					171		/* 1*6-steps || value */,
					163		/* 1*1-steps || value */,
					156		/* '{' || soft-space value soft-space */,
					194		/* V03*1-steps || value */,
					9		/* ModelElementCS::ownedAnnotations+=3 || value */,
					163		/* 1*1-steps || value */,
					161		/* '}' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC,
						15	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						1) /* AnnotationElementCS */,
					createEReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
						18) /* LibOppositeCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(15 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(49, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::Library(oclstdlibcs::LibRootPackageCS): { { ownedImports+=ImportCS ";" }[*] ownedPackages+=LibPackageCS[*] }
		serializationRules[119] =
			new SerializationRule("Library", 54,
				createSerializationMatchSteps(
					191		/* check-rule basecs::RootCS.ownedImports : 33 */,
					185		/* check-rule basecs::PackageOwnerCS.ownedPackages : 50 */,
					121		/* assign V1 = |PackageOwnerCS::ownedPackages| */,
					92		/* assign V0 = |RootCS::ownedImports| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					176		/* V00*3-steps || value */,
					32		/* RootCS::ownedImports+=33 || value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */,
					182		/* V01*1-steps || value */,
					222		/* PackageOwnerCS::ownedPackages+=50 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						12) /* ImportCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						19) /* LibPackageCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(33, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLstdlib::PackageCS(basecs::PackageCS): { "package" name=Name { ":" nsPrefix=Identifier "=" nsURI=URI }[?] "{" { ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] } "}" }
		serializationRules[120] =
			new SerializationRule("PackageCS", 78,
				createSerializationMatchSteps(
					177		/* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
					184		/* check-rule basecs::PackageCS.ownedClasses : 7 */,
					186		/* check-rule basecs::PackageOwnerCS.ownedPackages : 78 */,
					148		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
					141		/* assign V2 = |PackageCS::ownedClasses| */,
					121		/* assign V1 = |PackageOwnerCS::ownedPackages| */,
					90		/* assign V0 = |PackageCS::nsURI| */,
					43		/* assert (|PackageCS::nsPrefix| - V0) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					144		/* 'package' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */,
					180		/* V00*8-steps || value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					5		/* PackageCS::nsPrefix=31 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					112		/* '=' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					6		/* PackageCS::nsURI=122 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					157		/* '{' || soft-space value push soft-new-line */,
					171		/* 1*6-steps || value */,
					182		/* V01*1-steps || value */,
					223		/* PackageOwnerCS::ownedPackages+=78 || value */,
					188		/* V02*1-steps || value */,
					12		/* PackageCS::ownedClasses+=7 || half-new-line value half-new-line */,
					194		/* V03*1-steps || value */,
					9		/* ModelElementCS::ownedAnnotations+=3 || value */,
					163		/* 1*1-steps || value */,
					162		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						1) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						2) /* ClassCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						32) /* PackageCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(3, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(78, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLstdlib::ParameterCS(basecs::ParameterCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[121] =
			new SerializationRule("ParameterCS", 79,
				createSerializationMatchSteps(
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					214		/* NamedElementCS::name=31 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
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
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::PostCS(oclstdlibcs::LibConstraintCS): { stereotype="post" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" }
		serializationRules[122] =
			new SerializationRule("PostCS", 82,
				createSerializationMatchSteps(
					171		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : 99 */,
					172		/* check-rule basecs::ConstraintCS.ownedSpecification : 99 */,
					6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
					86		/* assign V0 = |NamedElementCS::name| */,
					8		/* assert (|ConstraintCS::stereotype.'post'| - 1) == 0 */,
					114		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					145		/* 'post' || soft-space value soft-space */,
					180		/* V00*8-steps || value */,
					163		/* 1*1-steps || value */,
					213		/* NamedElementCS::name=128 || soft-space value soft-space */,
					184		/* V01*5-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					43		/* ConstraintCS::ownedMessageSpecification=99 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					71		/* ConstraintCS::ownedSpecification=99 || value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						12	/* 'post' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						48) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						48) /* SpecificationCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(12 /* 'post' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::PreCS(oclstdlibcs::LibConstraintCS): { stereotype="pre" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS ";" }
		serializationRules[123] =
			new SerializationRule("PreCS", 83,
				createSerializationMatchSteps(
					171		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : 99 */,
					172		/* check-rule basecs::ConstraintCS.ownedSpecification : 99 */,
					6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
					86		/* assign V0 = |NamedElementCS::name| */,
					9		/* assert (|ConstraintCS::stereotype.'pre'| - 1) == 0 */,
					114		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					146		/* 'pre' || soft-space value soft-space */,
					180		/* V00*8-steps || value */,
					163		/* 1*1-steps || value */,
					213		/* NamedElementCS::name=128 || soft-space value soft-space */,
					184		/* V01*5-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					43		/* ConstraintCS::ownedMessageSpecification=99 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					71		/* ConstraintCS::ownedSpecification=99 || value */,
					163		/* 1*1-steps || value */,
					110		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						13	/* 'pre' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						48) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						48) /* SpecificationCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(13 /* 'pre' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::PrecedenceCS(oclstdlibcs::PrecedenceCS): { "left" ":" name=Name }
		serializationRules[124] =
			new SerializationRule("PrecedenceCS", 84,
				createSerializationMatchSteps(
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					138		/* 'left' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */
				),
				null,
				null,
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
				null);
			;
		// OCLstdlib::PrecedenceCS(oclstdlibcs::PrecedenceCS): { isRightAssociative="right" ":" name=Name }
		serializationRules[125] =
			new SerializationRule("PrecedenceCS", 84,
				createSerializationMatchSteps(
					32		/* assert (|NamedElementCS::name| - 1) == 0 */,
					47		/* assert (|PrecedenceCS::isRightAssociative.'right'| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					149		/* 'right' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					218		/* NamedElementCS::name=65 || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE,
						14	/* 'right' */
					)
				},
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(14 /* 'right' */, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// OCLstdlib::SpecificationCS(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS
		serializationRules[126] =
			new SerializationRule("SpecificationCS", 99,
				createSerializationMatchSteps(
					222		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 27 */,
					11		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					26		/* ExpSpecificationCS::ownedExpression=27 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
						10) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::TuplePartCS(basecs::TuplePartCS): { name=Identifier ":" ownedType=TypedMultiplicityRefCS }
		serializationRules[127] =
			new SerializationRule("TuplePartCS", 108,
				createSerializationMatchSteps(
					202		/* check-rule basecs::TypedElementCS.ownedType : 118 */,
					55		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					32		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					211		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					163		/* 1*1-steps || value */,
					214		/* NamedElementCS::name=31 || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					84		/* TypedElementCS::ownedType=118 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						65) /* TypedMultiplicityRefCS */
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
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ONE)
						}
					)
				});
			;
	}
	private void initSerializationRules2() {
		// OCLstdlib::TypedMultiplicityRefCS(basecs::LambdaTypeCS): { { name="Lambda" ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS "(" { ownedParameterTypes+=TypedMultiplicityRefCS { "," ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ")" ":" ownedResultType=TypedRefCS } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[128] =
			new SerializationRule("TypedMultiplicityRefCS", 118,
				createSerializationMatchSteps(
					174		/* check-rule basecs::LambdaTypeCS.ownedContextType : 40 */,
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					175		/* check-rule basecs::LambdaTypeCS.ownedParameterTypes : 118 */,
					176		/* check-rule basecs::LambdaTypeCS.ownedResultType : 119 */,
					199		/* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
					150		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					22		/* assert (|LambdaTypeCS::ownedResultType| - 1) == 0 */,
					21		/* assert (|LambdaTypeCS::ownedContextType| - 1) == 0 */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					20		/* assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0 */,
					106		/* assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0) */,
					132		/* assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1) */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					116		/* 'Lambda' || soft-space value soft-space */,
					174		/* V00*1-steps || value */,
					70		/* TemplateableElementCS::ownedSignature=105 || value */,
					18		/* LambdaTypeCS::ownedContextType=40 || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					184		/* V01*5-steps || value */,
					50		/* LambdaTypeCS::ownedParameterTypes+=118 || value */,
					189		/* V02*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					50		/* LambdaTypeCS::ownedParameterTypes+=118 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					163		/* 1*1-steps || value */,
					107		/* ':' || soft-space value soft-space */,
					67		/* LambdaTypeCS::ownedResultType=119 || value */,
					194		/* V03*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME,
						5	/* 'Lambda' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
						15) /* LambdaContextTypeRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
						65) /* TypedMultiplicityRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
						66) /* TypedRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						53) /* TemplateSignatureCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* 'Lambda' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(40, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(118, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(119, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLstdlib::TypedMultiplicityRefCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[129] =
			new SerializationRule("TypedMultiplicityRefCS", 118,
				createSerializationMatchSteps(
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					200		/* check-rule basecs::TupleTypeCS.ownedParts : 108 */,
					150		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					52		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					70		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					110		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					136		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					118		/* 'Tuple' || soft-space value soft-space */,
					175		/* V00*10-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					184		/* V01*5-steps || value */,
					58		/* TupleTypeCS::ownedParts+=108 || value */,
					189		/* V02*3-steps || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					58		/* TupleTypeCS::ownedParts+=108 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					194		/* V03*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						7	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						55) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(7 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLstdlib::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { { isTypeof="typeof" "(" ownedPathName=LibPathNameCS ")" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[130] =
			new SerializationRule("TypedMultiplicityRefCS", 118,
				createSerializationMatchSteps(
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					205		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
					97		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					57		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */,
					56		/* assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					154		/* 'typeof' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					62		/* TypedTypeRefCS::ownedPathName=52 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					174		/* V00*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF,
						16	/* 'typeof' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						21) /* LibPathNameCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(16 /* 'typeof' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(52, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[131] =
			new SerializationRule("TypedMultiplicityRefCS", 118,
				createSerializationMatchSteps(
					204		/* check-rule basecs::TypedTypeRefCS.ownedBinding : 103 */,
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					205		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
					125		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					98		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
					57		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					62		/* TypedTypeRefCS::ownedPathName=52 || value */,
					178		/* V00*5-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					10		/* TypedTypeRefCS::ownedBinding=103 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					182		/* V01*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						51) /* TemplateBindingCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						21) /* LibPathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(103, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(52, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::TypedMultiplicityRefCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[132] =
			new SerializationRule("TypedMultiplicityRefCS", 118,
				createSerializationMatchSteps(
					238		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110 */,
					203		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
					239		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 110 */,
					125		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					83		/* assign V0 = |MapTypeCS::ownedValueType| */,
					28		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					27		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					117		/* 'Map' || soft-space value soft-space */,
					180		/* V00*8-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					40		/* MapTypeCS::ownedKeyType=110 || value */,
					163		/* 1*1-steps || value */,
					105		/* ',' || no-space value soft-space */,
					87		/* MapTypeCS::ownedValueType=110 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */,
					182		/* V01*1-steps || value */,
					45		/* TypedRefCS::ownedMultiplicity=62 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						6	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						56) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						25) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						56) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(110, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLstdlib::TypedTypeRefCS(basecs::TypedTypeRefCS): { isTypeof="typeof" "(" ownedPathName=LibPathNameCS ")" }
		serializationRules[133] =
			new SerializationRule("TypedTypeRefCS", 120,
				createSerializationMatchSteps(
					205		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
					57		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */,
					56		/* assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0 */
				),
				createSerializationSteps(
					163		/* 1*1-steps || value */,
					154		/* 'typeof' || soft-space value soft-space */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					62		/* TypedTypeRefCS::ownedPathName=52 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF,
						16	/* 'typeof' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						21) /* LibPathNameCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(16 /* 'typeof' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(52, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLstdlib::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=LibPathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] }
		serializationRules[134] =
			new SerializationRule("TypedTypeRefCS", 120,
				createSerializationMatchSteps(
					204		/* check-rule basecs::TypedTypeRefCS.ownedBinding : 103 */,
					205		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
					98		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
					57		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					62		/* TypedTypeRefCS::ownedPathName=52 || value */,
					178		/* V00*5-steps || value */,
					163		/* 1*1-steps || value */,
					101		/* '(' || no-space value no-space */,
					10		/* TypedTypeRefCS::ownedBinding=103 || value */,
					163		/* 1*1-steps || value */,
					102		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						51) /* TemplateBindingCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						21) /* LibPathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(103, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(52, GrammarCardinality.ONE)
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
			SerializationSegment.HALF_NEW_LINE /* half-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.HALF_NEW_LINE /* half-new-line */
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
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[8] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[9] = new @NonNull SerializationSegment @NonNull [] {
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
		// JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || soft-space value soft-space
		serializationSteps[0] = createSerializationStepCrossReference(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, getCrossReference(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, "SINGLE_QUOTED_STRING"), 7);
		// MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 39 /*LOWER*/, 7);
		// LibClassCS::metaclassName=AnyName || soft-space value soft-space
		serializationSteps[2] = createSerializationStepCrossReference(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME, getCrossReference(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME, "AnyName"), 7);
		// CollectionTypeCS::name=13 || soft-space value soft-space
		serializationSteps[3] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 13 /*CollectionTypeIdentifier*/, 7);
		// PrimitiveTypeRefCS::name=90 || soft-space value soft-space
		serializationSteps[4] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 90 /*PrimitiveTypeIdentifier*/, 7);
		// PackageCS::nsPrefix=31 || soft-space value soft-space
		serializationSteps[5] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, 31 /*Identifier*/, 7);
		// PackageCS::nsURI=122 || soft-space value soft-space
		serializationSteps[6] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__NS_URI, 122 /*URI*/, 7);
		// LibIterationCS::ownedAccumulators+=1 || value
		serializationSteps[7] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS, 1 /*AccumulatorCS*/, 0);
		// TemplateParameterSubstitutionCS::ownedActualParameter=117 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[8] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 117 /*TypeRefCS*/, 2);
		// ModelElementCS::ownedAnnotations+=3 || value
		serializationSteps[9] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 3 /*AnnotationElementCS*/, 0);
		// TypedTypeRefCS::ownedBinding=103 || value
		serializationSteps[10] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 103 /*TemplateBindingCS*/, 0);
		// OperationCS::ownedBodyExpressions+=99 || value
		serializationSteps[11] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 99 /*SpecificationCS*/, 0);
		// PackageCS::ownedClasses+=7 || half-new-line value half-new-line
		serializationSteps[12] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 7 /*ClassCS*/, 3);
		// NavigatingArgCS::ownedCoIterator=8 || value
		serializationSteps[13] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 8 /*CoIteratorVariableCS*/, 0);
		// CollectionTypeCS::ownedCollectionMultiplicity=62 || value
		serializationSteps[14] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 62 /*MultiplicityCS*/, 0);
		// IfExpCS::ownedCondition=27|81 || value
		serializationSteps[15] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, -1, new @NonNull Integer [] { 27/*ExpCS*/,81/*PatternExpCS*/}, 0);
		// IfThenExpCS::ownedCondition=27 || value
		serializationSteps[16] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 27 /*ExpCS*/, 0);
		// ClassCS::ownedConstraints+=35 || value
		serializationSteps[17] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 35 /*InvCS*/, 0);
		// LambdaTypeCS::ownedContextType=40 || value
		serializationSteps[18] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 40 /*LambdaContextTypeRefCS*/, 0);
		// AbstractNameExpCS::ownedCurlyBracketedClause=14 || value
		serializationSteps[19] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// TypeNameExpCS::ownedCurlyBracketedClause=14 || value
		serializationSteps[20] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// AnnotationElementCS::ownedDetails+=16 || value
		serializationSteps[21] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 16 /*DetailCS*/, 0);
		// IfExpCS::ownedElseExpression=27 || value
		serializationSteps[22] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 27 /*ExpCS*/, 0);
		// CollectionLiteralPartCS::ownedExpression=27 || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 0);
		// CollectionLiteralPartCS::ownedExpression=81 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 81 /*PatternExpCS*/, 2);
		// ContextCS::ownedExpression=27 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 2);
		// ExpSpecificationCS::ownedExpression=27 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 2);
		// NestedExpCS::ownedExpression=27 || value
		serializationSteps[27] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 27 /*ExpCS*/, 0);
		// LambdaLiteralExpCS::ownedExpressionCS=27 || value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 27 /*ExpCS*/, 0);
		// TypeParameterCS::ownedExtends+=119 || value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 119 /*TypedRefCS*/, 0);
		// WildcardTypeRefCS::ownedExtends=119 || value
		serializationSteps[30] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 119 /*TypedRefCS*/, 0);
		// IfExpCS::ownedIfThenExpressions+=20 || value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 20 /*ElseIfThenExpCS*/, 0);
		// RootCS::ownedImports+=33 || value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 33 /*ImportCS*/, 0);
		// LetExpCS::ownedInExpression=27 || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 27 /*ExpCS*/, 0);
		// NavigatingArgCS::ownedInitExpression=27 || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 27 /*ExpCS*/, 0);
		// ShadowPartCS::ownedInitExpression=27|81 || value
		serializationSteps[35] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new @NonNull Integer [] { 27/*ExpCS*/,81/*PatternExpCS*/}, 0);
		// ShadowPartCS::ownedInitExpression=102 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[36] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 102 /*StringLiteralExpCS*/, 2);
		// VariableCS::ownedInitExpression=27 || value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 27 /*ExpCS*/, 0);
		// LibIterationCS::ownedIterators+=37 || value
		serializationSteps[38] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, 37 /*IteratorCS*/, 0);
		// MapLiteralPartCS::ownedKey=27 || value
		serializationSteps[39] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 27 /*ExpCS*/, 0);
		// MapTypeCS::ownedKeyType=110 || value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 110 /*TypeExpCS*/, 0);
		// CollectionLiteralPartCS::ownedLastExpression=27 || value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 27 /*ExpCS*/, 0);
		// InfixExpCS::ownedLeft=86 || value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 86 /*PrefixedPrimaryExpCS*/, 0);
		// ConstraintCS::ownedMessageSpecification=99 || value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 99 /*SpecificationCS*/, 0);
		// TemplateBindingCS::ownedMultiplicity=62 || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 62 /*MultiplicityCS*/, 0);
		// TypedRefCS::ownedMultiplicity=62 || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 62 /*MultiplicityCS*/, 0);
		// NavigatingArgCS::ownedNameExpression=68 || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 68 /*NavigatingArgExpCS*/, 0);
		// NavigatingArgCS::ownedNameExpression=68 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[47] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 68 /*NavigatingArgExpCS*/, 2);
		// StructuredClassCS::ownedOperations+=77 || value
		serializationSteps[48] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 77 /*OperationCS*/, 0);
		// LibPropertyCS::ownedOpposite=49 || value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, 49 /*LibOppositeCS*/, 0);
		// LambdaTypeCS::ownedParameterTypes+=118 || value
		serializationSteps[50] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 118 /*TypedMultiplicityRefCS*/, 0);
		// OperationCS::ownedParameters+=79 || value
		serializationSteps[51] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 79 /*ParameterCS*/, 0);
		// TemplateSignatureCS::ownedParameters+=116 || value
		serializationSteps[52] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 116 /*TypeParameterCS*/, 0);
		// CollectionLiteralExpCS::ownedParts+=10 || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 10 /*CollectionLiteralPartCS*/, 0);
		// CollectionPatternCS::ownedParts+=81 || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 81 /*PatternExpCS*/, 0);
		// CurlyBracketedClauseCS::ownedParts+=97 || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 97 /*ShadowPartCS*/, 0);
		// MapLiteralExpCS::ownedParts+=58 || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 58 /*MapLiteralPartCS*/, 0);
		// TupleLiteralExpCS::ownedParts+=107 || value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 107 /*TupleLiteralPartCS*/, 0);
		// TupleTypeCS::ownedParts+=108 || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 108 /*TuplePartCS*/, 0);
		// AbstractNameExpCS::ownedPathName=80 || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 80 /*PathNameCS*/, 0);
		// ImportCS::ownedPathName=124 || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 124 /*URIPathNameCS*/, 0);
		// TypeNameExpCS::ownedPathName=80 || value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 80 /*PathNameCS*/, 0);
		// TypedTypeRefCS::ownedPathName=52 || value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 52 /*LibPathNameCS*/, 0);
		// TypeNameExpCS::ownedPatternGuard=27 || value
		serializationSteps[63] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 27 /*ExpCS*/, 0);
		// PatternExpCS::ownedPatternType=110 || value
		serializationSteps[64] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 110 /*TypeExpCS*/, 0);
		// LibPackageCS::ownedPrecedences+=84 || value
		serializationSteps[65] = createSerializationStepAssignedRuleCall(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES, 84 /*PrecedenceCS*/, 0);
		// StructuredClassCS::ownedProperties+=53 || value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 53 /*LibPropertyCS*/, 0);
		// LambdaTypeCS::ownedResultType=119 || value
		serializationSteps[67] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 119 /*TypedRefCS*/, 0);
		// AbstractNameExpCS::ownedRoundBracketedClause=92 || value
		serializationSteps[68] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 92 /*RoundBracketedClauseCS*/, 0);
		// LetVariableCS::ownedRoundBracketedClause=92 || value
		serializationSteps[69] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 92 /*RoundBracketedClauseCS*/, 0);
		// TemplateableElementCS::ownedSignature=105 || value
		serializationSteps[70] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 105 /*TemplateSignatureCS*/, 0);
		// ConstraintCS::ownedSpecification=99 || value
		serializationSteps[71] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 99 /*SpecificationCS*/, 0);
		// AbstractNameExpCS::ownedSquareBracketedClauses+=100 || value
		serializationSteps[72] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 100 /*SquareBracketedClauseCS*/, 0);
		// TemplateBindingCS::ownedSubstitutions+=104 || value
		serializationSteps[73] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 104 /*TemplateParameterSubstitutionCS*/, 0);
		// StructuredClassCS::ownedSuperTypes+=119 || value
		serializationSteps[74] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 119 /*TypedRefCS*/, 0);
		// SquareBracketedClauseCS::ownedTerms+=27 || value
		serializationSteps[75] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 27 /*ExpCS*/, 0);
		// IfExpCS::ownedThenExpression=27 || value
		serializationSteps[76] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 27 /*ExpCS*/, 0);
		// IfThenExpCS::ownedThenExpression=27 || value
		serializationSteps[77] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 27 /*ExpCS*/, 0);
		// CollectionLiteralExpCS::ownedType=12 || value
		serializationSteps[78] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 12 /*CollectionTypeCS*/, 0);
		// CollectionPatternCS::ownedType=12 || value
		serializationSteps[79] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 12 /*CollectionTypeCS*/, 0);
		// CollectionTypeCS::ownedType=111 || value
		serializationSteps[80] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 111 /*TypeExpWithoutMultiplicityCS*/, 0);
		// MapLiteralExpCS::ownedType=59 || value
		serializationSteps[81] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 59 /*MapTypeCS*/, 0);
		// NavigatingArgCS::ownedType=110 || value
		serializationSteps[82] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 110 /*TypeExpCS*/, 0);
		// TypeLiteralExpCS::ownedType=114 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[83] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 114 /*TypeLiteralWithMultiplicityCS*/, 2);
		// TypedElementCS::ownedType=118 || value
		serializationSteps[84] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 118 /*TypedMultiplicityRefCS*/, 0);
		// VariableCS::ownedType=110 || value
		serializationSteps[85] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 110 /*TypeExpCS*/, 0);
		// MapLiteralPartCS::ownedValue=27 || value
		serializationSteps[86] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 27 /*ExpCS*/, 0);
		// MapTypeCS::ownedValueType=110 || value
		serializationSteps[87] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 110 /*TypeExpCS*/, 0);
		// LetExpCS::ownedVariables+=44 || value
		serializationSteps[88] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 44 /*LetVariableCS*/, 0);
		// PatternExpCS::patternVariableName=128 || soft-space value soft-space
		serializationSteps[89] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 128 /*UnrestrictedName*/, 7);
		// LibOperationCS::precedence=Name || soft-space value soft-space
		serializationSteps[90] = createSerializationStepCrossReference(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, getCrossReference(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, "Name"), 7);
		// ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[91] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 7);
		// CollectionPatternCS::restVariableName=31 || soft-space value soft-space
		serializationSteps[92] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 31 /*Identifier*/, 7);
		// StringLiteralExpCS::segments+=101 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[93] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 101 /*StringLiteral*/, 2);
		// MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[94] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 7);
		// BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[95] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 9 /* 'false|true' */, 2);
		// NumberLiteralExpCS::symbol=64 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[96] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 64 /*NUMBER_LITERAL*/, 2);
		// MultiplicityBoundsCS::upperBound=121 || soft-space value soft-space
		serializationSteps[97] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 121 /*UPPER*/, 7);
		// DocumentationCS::value=94 || soft-space value soft-space
		serializationSteps[98] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, 94 /*SINGLE_QUOTED_STRING*/, 7);
		// DetailCS::values+=94|56 || soft-space value soft-space
		serializationSteps[99] = createSerializationStepAssigns(BaseCSPackage.Literals.DETAIL_CS__VALUES, -1, new @NonNull Integer [] { 94/*SINGLE_QUOTED_STRING*/,56/*ML_SINGLE_QUOTED_STRING*/}, 7);
		// '&&' || soft-space value soft-space
		serializationSteps[100] = createSerializationStepKeyword("&&", 7);
		// '(' || no-space value no-space
		serializationSteps[101] = createSerializationStepKeyword("(", 4);
		// ')' || no-space value
		serializationSteps[102] = createSerializationStepKeyword(")", 1);
		// '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[103] = createSerializationStepKeyword("*", 2);
		// '++' || soft-space value soft-space
		serializationSteps[104] = createSerializationStepKeyword("++", 7);
		// ',' || no-space value soft-space
		serializationSteps[105] = createSerializationStepKeyword(",", 6);
		// '..' || no-space value no-space
		serializationSteps[106] = createSerializationStepKeyword("..", 4);
		// ':' || soft-space value soft-space
		serializationSteps[107] = createSerializationStepKeyword(":", 7);
		// '::*' || soft-space value soft-space
		serializationSteps[108] = createSerializationStepKeyword("::*", 7);
		// '::' || no-space value no-space
		serializationSteps[109] = createSerializationStepKeyword("::", 4);
		// ';' || no-space value soft-new-line
		serializationSteps[110] = createSerializationStepKeyword(";", 5);
		// '<-' || soft-space value soft-space
		serializationSteps[111] = createSerializationStepKeyword("<-", 7);
		// '=' || soft-space value soft-space
		serializationSteps[112] = createSerializationStepKeyword("=", 7);
		// '=>' || soft-space value soft-space
		serializationSteps[113] = createSerializationStepKeyword("=>", 7);
		// '?' || soft-space value soft-space
		serializationSteps[114] = createSerializationStepKeyword("?", 7);
		// '@' || soft-space value soft-space
		serializationSteps[115] = createSerializationStepKeyword("@", 7);
		// 'Lambda' || soft-space value soft-space
		serializationSteps[116] = createSerializationStepKeyword("Lambda", 7);
		// 'Map' || soft-space value soft-space
		serializationSteps[117] = createSerializationStepKeyword("Map", 7);
		// 'Tuple' || soft-space value soft-space
		serializationSteps[118] = createSerializationStepKeyword("Tuple", 7);
		// '[' || no-space value no-space
		serializationSteps[119] = createSerializationStepKeyword("[", 4);
		// ']' || no-space value
		serializationSteps[120] = createSerializationStepKeyword("]", 1);
		// 'abstract' || soft-space value soft-space
		serializationSteps[121] = createSerializationStepKeyword("abstract", 7);
		// 'annotation' || soft-space value soft-space
		serializationSteps[122] = createSerializationStepKeyword("annotation", 7);
		// 'body' || soft-space value soft-space
		serializationSteps[123] = createSerializationStepKeyword("body", 7);
		// 'coercion' || soft-space value soft-space
		serializationSteps[124] = createSerializationStepKeyword("coercion", 7);
		// 'conformsTo' || soft-space value soft-space
		serializationSteps[125] = createSerializationStepKeyword("conformsTo", 7);
		// 'documentation' || soft-space value soft-space
		serializationSteps[126] = createSerializationStepKeyword("documentation", 7);
		// 'else' || soft-space value soft-space
		serializationSteps[127] = createSerializationStepKeyword("else", 7);
		// 'elseif' || soft-space value soft-space
		serializationSteps[128] = createSerializationStepKeyword("elseif", 7);
		// 'endif' || soft-space value soft-space
		serializationSteps[129] = createSerializationStepKeyword("endif", 7);
		// 'extends' || soft-space value soft-space
		serializationSteps[130] = createSerializationStepKeyword("extends", 7);
		// 'if' || soft-space value soft-space
		serializationSteps[131] = createSerializationStepKeyword("if", 7);
		// 'import' || soft-space value soft-space
		serializationSteps[132] = createSerializationStepKeyword("import", 7);
		// 'in' || soft-space value soft-space
		serializationSteps[133] = createSerializationStepKeyword("in", 7);
		// 'inv' || soft-space value soft-space
		serializationSteps[134] = createSerializationStepKeyword("inv", 7);
		// 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[135] = createSerializationStepKeyword("invalid", 2);
		// 'invalidating' || soft-space value soft-space
		serializationSteps[136] = createSerializationStepKeyword("invalidating", 7);
		// 'iteration' || soft-space value soft-space
		serializationSteps[137] = createSerializationStepKeyword("iteration", 7);
		// 'left' || soft-space value soft-space
		serializationSteps[138] = createSerializationStepKeyword("left", 7);
		// 'let' || soft-space value soft-space
		serializationSteps[139] = createSerializationStepKeyword("let", 7);
		// 'library' || soft-space value soft-space
		serializationSteps[140] = createSerializationStepKeyword("library", 7);
		// 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[141] = createSerializationStepKeyword("null", 2);
		// 'operation' || soft-space value soft-space
		serializationSteps[142] = createSerializationStepKeyword("operation", 7);
		// 'opposite' || soft-space value soft-space
		serializationSteps[143] = createSerializationStepKeyword("opposite", 7);
		// 'package' || soft-space value soft-space
		serializationSteps[144] = createSerializationStepKeyword("package", 7);
		// 'post' || soft-space value soft-space
		serializationSteps[145] = createSerializationStepKeyword("post", 7);
		// 'pre' || soft-space value soft-space
		serializationSteps[146] = createSerializationStepKeyword("pre", 7);
		// 'precedence' || soft-space value soft-space
		serializationSteps[147] = createSerializationStepKeyword("precedence", 7);
		// 'property' || soft-space value soft-space
		serializationSteps[148] = createSerializationStepKeyword("property", 7);
		// 'right' || soft-space value soft-space
		serializationSteps[149] = createSerializationStepKeyword("right", 7);
		// 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[150] = createSerializationStepKeyword("self", 2);
		// 'static' || soft-space value soft-space
		serializationSteps[151] = createSerializationStepKeyword("static", 7);
		// 'then' || soft-space value soft-space
		serializationSteps[152] = createSerializationStepKeyword("then", 7);
		// 'type' || soft-space value soft-space
		serializationSteps[153] = createSerializationStepKeyword("type", 7);
		// 'typeof' || soft-space value soft-space
		serializationSteps[154] = createSerializationStepKeyword("typeof", 7);
		// 'validating' || soft-space value soft-space
		serializationSteps[155] = createSerializationStepKeyword("validating", 7);
		// '{' || soft-space value soft-space
		serializationSteps[156] = createSerializationStepKeyword("{", 7);
		// '{' || soft-space value push soft-new-line
		serializationSteps[157] = createSerializationStepKeyword("{", 9);
		// '|' || soft-space value soft-space
		serializationSteps[158] = createSerializationStepKeyword("|", 7);
		// '|1' || soft-space value soft-space
		serializationSteps[159] = createSerializationStepKeyword("|1", 7);
		// '|?' || soft-space value soft-space
		serializationSteps[160] = createSerializationStepKeyword("|?", 7);
		// '}' || soft-space value soft-space
		serializationSteps[161] = createSerializationStepKeyword("}", 7);
		// '}' || pop soft-space value soft-new-line
		serializationSteps[162] = createSerializationStepKeyword("}", 8);
		// 1*1-steps || value
		serializationSteps[163] = createSerializationStepSequence(-1, 1, 0);
		// 1*11-steps || value
		serializationSteps[164] = createSerializationStepSequence(-1, 11, 0);
		// 1*13-steps || value
		serializationSteps[165] = createSerializationStepSequence(-1, 13, 0);
		// 1*14-steps || value
		serializationSteps[166] = createSerializationStepSequence(-1, 14, 0);
		// 1*19-steps || value
		serializationSteps[167] = createSerializationStepSequence(-1, 19, 0);
		// 1*3-steps || value
		serializationSteps[168] = createSerializationStepSequence(-1, 3, 0);
		// 1*4-steps || value
		serializationSteps[169] = createSerializationStepSequence(-1, 4, 0);
		// 1*5-steps || value
		serializationSteps[170] = createSerializationStepSequence(-1, 5, 0);
		// 1*6-steps || value
		serializationSteps[171] = createSerializationStepSequence(-1, 6, 0);
		// 1*7-steps || value
		serializationSteps[172] = createSerializationStepSequence(-1, 7, 0);
		// 1*8-steps || value
		serializationSteps[173] = createSerializationStepSequence(-1, 8, 0);
		// V00*1-steps || value
		serializationSteps[174] = createSerializationStepSequence(0, 1, 0);
		// V00*10-steps || value
		serializationSteps[175] = createSerializationStepSequence(0, 10, 0);
		// V00*3-steps || value
		serializationSteps[176] = createSerializationStepSequence(0, 3, 0);
		// V00*4-steps || value
		serializationSteps[177] = createSerializationStepSequence(0, 4, 0);
		// V00*5-steps || value
		serializationSteps[178] = createSerializationStepSequence(0, 5, 0);
		// V00*7-steps || value
		serializationSteps[179] = createSerializationStepSequence(0, 7, 0);
		// V00*8-steps || value
		serializationSteps[180] = createSerializationStepSequence(0, 8, 0);
		// V00*9-steps || value
		serializationSteps[181] = createSerializationStepSequence(0, 9, 0);
		// V01*1-steps || value
		serializationSteps[182] = createSerializationStepSequence(1, 1, 0);
		// V01*3-steps || value
		serializationSteps[183] = createSerializationStepSequence(1, 3, 0);
		// V01*5-steps || value
		serializationSteps[184] = createSerializationStepSequence(1, 5, 0);
		// V01*9-steps || value
		serializationSteps[185] = createSerializationStepSequence(1, 9, 0);
		// V10*1-steps || value
		serializationSteps[186] = createSerializationStepSequence(10, 1, 0);
		// V11*1-steps || value
		serializationSteps[187] = createSerializationStepSequence(11, 1, 0);
		// V02*1-steps || value
		serializationSteps[188] = createSerializationStepSequence(2, 1, 0);
		// V02*3-steps || value
		serializationSteps[189] = createSerializationStepSequence(2, 3, 0);
		// V02*4-steps || value
		serializationSteps[190] = createSerializationStepSequence(2, 4, 0);
		// V02*5-steps || value
		serializationSteps[191] = createSerializationStepSequence(2, 5, 0);
		// V02*6-steps || value
		serializationSteps[192] = createSerializationStepSequence(2, 6, 0);
		// V02*7-steps || value
		serializationSteps[193] = createSerializationStepSequence(2, 7, 0);
		// V03*1-steps || value
		serializationSteps[194] = createSerializationStepSequence(3, 1, 0);
		// V03*3-steps || value
		serializationSteps[195] = createSerializationStepSequence(3, 3, 0);
		// V03*4-steps || value
		serializationSteps[196] = createSerializationStepSequence(3, 4, 0);
		// V03*7-steps || value
		serializationSteps[197] = createSerializationStepSequence(3, 7, 0);
		// V04*1-steps || value
		serializationSteps[198] = createSerializationStepSequence(4, 1, 0);
		// V04*3-steps || value
		serializationSteps[199] = createSerializationStepSequence(4, 3, 0);
		// V04*7-steps || value
		serializationSteps[200] = createSerializationStepSequence(4, 7, 0);
		// V05*1-steps || value
		serializationSteps[201] = createSerializationStepSequence(5, 1, 0);
		// V05*3-steps || value
		serializationSteps[202] = createSerializationStepSequence(5, 3, 0);
		// V06*1-steps || value
		serializationSteps[203] = createSerializationStepSequence(6, 1, 0);
		// V06*6-steps || value
		serializationSteps[204] = createSerializationStepSequence(6, 6, 0);
		// V07*1-steps || value
		serializationSteps[205] = createSerializationStepSequence(7, 1, 0);
		// V07*4-steps || value
		serializationSteps[206] = createSerializationStepSequence(7, 4, 0);
		// V08*1-steps || value
		serializationSteps[207] = createSerializationStepSequence(8, 1, 0);
		// V08*4-steps || value
		serializationSteps[208] = createSerializationStepSequence(8, 4, 0);
		// V09*1-steps || value
		serializationSteps[209] = createSerializationStepSequence(9, 1, 0);
		// V09*7-steps || value
		serializationSteps[210] = createSerializationStepSequence(9, 7, 0);
		// wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[211] = createSerializationStepWrapper(2);
		// NamedElementCS::name=125 || soft-space value soft-space
		serializationSteps[212] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 125 /*UnaryOperatorName*/, 7);
		// NamedElementCS::name=128 || soft-space value soft-space
		serializationSteps[213] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 128 /*UnrestrictedName*/, 7);
		// NamedElementCS::name=31 || soft-space value soft-space
		serializationSteps[214] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 31 /*Identifier*/, 7);
		// NamedElementCS::name=31|94 || soft-space value soft-space
		serializationSteps[215] = createSerializationStepAssigns(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, -1, new @NonNull Integer [] { 31/*Identifier*/,94/*SINGLE_QUOTED_STRING*/}, 7);
		// NamedElementCS::name=4 || soft-space value soft-space
		serializationSteps[216] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 4 /*AnyName*/, 7);
		// NamedElementCS::name=5 || soft-space value soft-space
		serializationSteps[217] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 5 /*BinaryOperatorName*/, 7);
		// NamedElementCS::name=65 || soft-space value soft-space
		serializationSteps[218] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 65 /*Name*/, 7);
		// NamedElementCS::name=65|94 || soft-space value soft-space
		serializationSteps[219] = createSerializationStepAssigns(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, -1, new @NonNull Integer [] { 65/*Name*/,94/*SINGLE_QUOTED_STRING*/}, 7);
		// RoundBracketedClauseCS::ownedArguments+=67 || value
		serializationSteps[220] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 67 /*NavigatingArgCS*/, 0);
		// RoundBracketedClauseCS::ownedArguments+=70|71|69 || value
		serializationSteps[221] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new @NonNull Integer [] { 70/*NavigatingCommaArgCS*/,71/*NavigatingSemiArgCS*/,69/*NavigatingBarArgCS*/}, 0);
		// PackageOwnerCS::ownedPackages+=50 || value
		serializationSteps[222] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 50 /*LibPackageCS*/, 0);
		// PackageOwnerCS::ownedPackages+=78 || value
		serializationSteps[223] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 78 /*PackageCS*/, 0);
		// PathNameCS::ownedPathElements+=123 || value
		serializationSteps[224] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 123 /*URIFirstPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=28 || value
		serializationSteps[225] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 28 /*FirstPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=51 || value
		serializationSteps[226] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 51 /*LibPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=74 || value
		serializationSteps[227] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 74 /*NextPathElementCS*/, 0);
		// OperationCS::ownedPostconditions+=82 || value
		serializationSteps[228] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 82 /*PostCS*/, 0);
		// OperationCS::ownedPostconditions+=83 || value
		serializationSteps[229] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 83 /*PreCS*/, 0);
		// OperationCS::ownedPreconditions+=82 || value
		serializationSteps[230] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 82 /*PostCS*/, 0);
		// OperationCS::ownedPreconditions+=83 || value
		serializationSteps[231] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 83 /*PreCS*/, 0);
		// OperatorExpCS::ownedRight=27 || value
		serializationSteps[232] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 27 /*ExpCS*/, 0);
		// OperatorExpCS::ownedRight=85 || value
		serializationSteps[233] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 85 /*PrefixedLetExpCS*/, 0);
		// OperatorExpCS::ownedRight=86 || value
		serializationSteps[234] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 86 /*PrefixedPrimaryExpCS*/, 0);
		// PathElementCS::referredElement=Name || soft-space value soft-space
		serializationSteps[235] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "Name"), 7);
		// PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[236] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 7);
		// PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[237] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 7);
		// PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[238] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 7);
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
//	import OCLstdlibCSPackage;
