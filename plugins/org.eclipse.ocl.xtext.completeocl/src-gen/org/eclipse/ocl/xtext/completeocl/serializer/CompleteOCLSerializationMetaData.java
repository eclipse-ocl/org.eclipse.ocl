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
package org.eclipse.ocl.xtext.completeocl.serializer;

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
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;

public class CompleteOCLSerializationMetaData extends AbstractSerializationMetaData
{
	private boolean initialized = false;
	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[61];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[11];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[115];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[62];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[217];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[183];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[114];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [9] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[189];

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
		return 94;
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
		return 93;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 144;
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
				42 /* symbol={'false|true'} */,
				33 /* symbol={'false|true'} */
			), null
		);
		eClassValues[1] = new EClassValue(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS,
			createSerializationRules(
				16 /* { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { { "inv" ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } } */,
				17 /* { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { { "inv" ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					8) /* DefCS|DefOperationCS|DefPropertyCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					3) /* ConstraintCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					37) /* TemplateSignatureCS */
			}
		);
		eClassValues[2] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
			createSerializationRules(
				43 /* { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				35 /* { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					1) /* CollectionLiteralPartCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					2) /* CollectionTypeCS */
			}
		);
		eClassValues[3] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
			createSerializationRules(
				36 /* ownedExpression=PatternExpCS */,
				37 /* { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					60) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[4] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
			createSerializationRules(
				38 /* { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */,
				100 /* { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					24) /* PatternExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					2) /* CollectionTypeCS */
			}
		);
		eClassValues[5] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
			createSerializationRules(
				39 /* { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				101 /* { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				107 /* { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[6] = new EClassValue(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS,
			createSerializationRules(
				18 /* { ownedImports+=ImportCS[*] { ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
					29) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					12) /* ImportCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
					21) /* PackageDeclarationCS */
			}
		);
		eClassValues[7] = new EClassValue(BaseCSPackage.Literals.CONSTRAINT_CS,
			createSerializationRules(
				19 /* { { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					32) /* SpecificationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					32) /* SpecificationCS */
			}
		);
		eClassValues[8] = new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
			createSerializationRules(
				67 /* ownedExpression=ExpCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[9] = new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				40 /* { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					31) /* ShadowPartCS */
			}
		);
		eClassValues[10] = new EClassValue(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS,
			createSerializationRules(
				20 /* { isStatic="static"[?] "def" ":" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=DefParameterCS { "," ownedParameters+=DefParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] "=" ownedSpecification=SpecificationCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS,
					7) /* DefParameterCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					37) /* TemplateSignatureCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					32) /* SpecificationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[11] = new EClassValue(CompleteOCLCSPackage.Literals.DEF_PROPERTY_CS,
			createSerializationRules(
				22 /* { isStatic="static"[?] "def" ":" name=UnrestrictedName ":" ownedType=TypeExpCS "=" ownedSpecification=SpecificationCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					32) /* SpecificationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[12] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
			createSerializationRules(
				24 /* "?" */,
				44 /* "*" */,
				45 /* "invalid" */,
				46 /* "null" */,
				47 /* "self" */
			), null
		);
		eClassValues[13] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
			createSerializationRules(
				29 /* exprString=UNQUOTED_STRING */,
				30 /* ownedExpression=ExpCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[14] = new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
			createSerializationRules(
				48 /* { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				59 /* { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					60) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					9) /* ElseIfThenExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[15] = new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
			createSerializationRules(
				41 /* { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[16] = new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
			createSerializationRules(
				23 /* { {"import"|"include"|"library"} { name=Identifier ":" }[?] ownedPathName=URIPathNameCS isAll="::*"[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					53) /* URIPathNameCS */
			}
		);
		eClassValues[17] = new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
			createSerializationRules(
				49 /* { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[18] = new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
			createSerializationRules(
				60 /* "invalid" */
			), null
		);
		eClassValues[19] = new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
			createSerializationRules(
				50 /* { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				61 /* { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[20] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
			createSerializationRules(
				62 /* { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					13) /* LetVariableCS */
			}
		);
		eClassValues[21] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
			createSerializationRules(
				63 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					30) /* RoundBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[22] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
			createSerializationRules(
				51 /* { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				64 /* { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					14) /* MapLiteralPartCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					15) /* MapTypeCS */
			}
		);
		eClassValues[23] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
			createSerializationRules(
				65 /* { ownedKey=ExpCS "<-" ownedValue=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[24] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
			createSerializationRules(
				66 /* { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				102 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				108 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[25] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */,
				2 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" } */,
				3 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" } */,
				4 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree="|1"[?] "]" } */
			), null
		);
		eClassValues[26] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				5 /* { "[" stringBounds={'*|+|?'} "]" } */,
				6 /* { "[" stringBounds={'*|+|?'} "|?" "]" } */,
				7 /* { "[" stringBounds={'*|+|?'} isNullFree="|1"[?] "]" } */,
				8 /* stringBounds={'*|+|?'} */
			), null
		);
		eClassValues[27] = new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
			createSerializationRules(
				52 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				68 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					5) /* CurlyBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					30) /* RoundBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					33) /* SquareBracketedClauseCS */
			}
		);
		eClassValues[28] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
			createSerializationRules(
				69 /* ownedNameExpression=NavigatingArgExpCS */,
				70 /* { ":" ownedType=TypeExpCS } */,
				72 /* { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				71 /* { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				73 /* { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */,
				74 /* { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */,
				78 /* { prefix="," ownedNameExpression=NavigatingArgExpCS } */,
				76 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				75 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				77 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */,
				79 /* { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					0) /* CoIteratorVariableCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					59) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[29] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				53 /* { "(" ownedExpression=ExpCS ")" } */,
				80 /* { "(" ownedExpression=ExpCS ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[30] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				81 /* "null" */
			), null
		);
		eClassValues[31] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				54 /* symbol=NUMBER_LITERAL */,
				82 /* symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[32] = new EClassValue(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS,
			createSerializationRules(
				25 /* { "context" ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] { { "pre" ownedPreconditions+=ConstraintCS }[*] { "post" ownedPostconditions+=ConstraintCS }[*] { "body" ":" ownedBodies+=SpecificationCS }[*] } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
					32) /* SpecificationCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
					22) /* ParameterCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
					3) /* ConstraintCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
					3) /* ConstraintCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					37) /* TemplateSignatureCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[33] = new EClassValue(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS,
			createSerializationRules(
				26 /* { "package" ownedPathName=PathNameCS { "inv" ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] "endpackage" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
					29) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
					3) /* ConstraintCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */
			}
		);
		eClassValues[34] = new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
			createSerializationRules(
				21 /* { name=UnrestrictedName ":" ownedType=TypeExpCS } */,
				27 /* { { name=UnrestrictedName ":" }[?] ownedType=TypeExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[35] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* referredElement=UnrestrictedName */,
				9 /* referredElement=UnreservedName */,
				110 /* referredElement=UnrestrictedName */
			), null
		);
		eClassValues[36] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				111 /* referredElement=URI */
			), null
		);
		eClassValues[37] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */,
				91 /* ownedPathElements+=FirstPathElementCS */,
				112 /* { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					52) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
			}
		);
		eClassValues[38] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				83 /* { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[39] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				55 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				84 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				85 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[40] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
			createSerializationRules(
				86 /* name=PrimitiveTypeIdentifier */,
				98 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				105 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */
			}
		);
		eClassValues[41] = new EClassValue(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS,
			createSerializationRules(
				28 /* { "context" ownedPathName=PathNameCS ":" ownedType=TypeExpCS { { "derive" ":" ownedDefaultExpressions+=SpecificationCS }[*] { "init" ":" ownedDefaultExpressions+=SpecificationCS }[*] } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
					32) /* SpecificationCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */,
				createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[42] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				87 /* { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					19) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[43] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				88 /* "self" */
			), null
		);
		eClassValues[44] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				89 /* ownedInitExpression=StringLiteralExpCS */,
				90 /* { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					60) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[45] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				92 /* { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[46] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				56 /* segments+=StringLiteral[+] */,
				93 /* segments+=StringLiteral[+] */
			), null
		);
		eClassValues[47] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					36) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[48] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[49] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				31 /* { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */,
				32 /* { "<" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ">" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					47) /* TypeParameterCS */
			}
		);
		eClassValues[50] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				57 /* { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				94 /* { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					38) /* TupleLiteralPartCS */
			}
		);
		eClassValues[51] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
			createSerializationRules(
				95 /* { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[52] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				96 /* { name=UnrestrictedName ":" ownedType=TypeExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[53] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
			createSerializationRules(
				97 /* { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				99 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				106 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					39) /* TuplePartCS */
			}
		);
		eClassValues[54] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				58 /* ownedType=TypeLiteralWithMultiplicityCS */,
				104 /* ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					44) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[55] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
			createSerializationRules(
				103 /* { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				109 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					5) /* CurlyBracketedClauseCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[56] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				13 /* { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					50) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[57] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				14 /* { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					35) /* TemplateBindingCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */
			}
		);
		eClassValues[58] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				113 /* "*" */
			), null
		);
		eClassValues[59] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				34 /* { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[60] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				15 /* { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					50) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
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
		// 'Map'
		enumerationValues[5] = new EnumerationValueSingle("Map");
		// 'Tuple'
		enumerationValues[6] = new EnumerationValueSingle("Tuple");
		// 'false|true'
		enumerationValues[7] = new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		// 'static'
		enumerationValues[8] = new EnumerationValueSingle("static");
		// '|'
		enumerationValues[9] = new EnumerationValueSingle("|");
		// '|1'
		enumerationValues[10] = new EnumerationValueSingle("|1");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = new DataTypeRuleValue(1, "BinaryOperatorName");
		grammarRuleValues[2] = createParserRuleValue(2, "BooleanLiteralExpCS", -1,
			createSerializationRules(
				33	/* BooleanLiteralExpCS: symbol={'false|true'} */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* symbol="true" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* symbol="false" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "ClassifierContextDeclCS", -1,
			createSerializationRules(
				16	/* ClassifierContextDeclCS: { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { { "inv" ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } } */,
				17	/* ClassifierContextDeclCS: { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { { "inv" ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "context" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 6	/* selfName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives+ : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "inv" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInvariants+=ConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedDefinitions+=DefCS : [value] | [value] */
		);
		grammarRuleValues[4] = createParserRuleValue(4, "CoIteratorVariableCS", -1,
			createSerializationRules(
				34	/* CoIteratorVariableCS: { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[5] = createParserRuleValue(5, "CollectionLiteralExpCS", -1,
			createSerializationRules(
				35	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */
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
		grammarRuleValues[6] = createParserRuleValue(6, "CollectionLiteralPartCS", -1,
			createSerializationRules(
				36	/* CollectionLiteralPartCS: ownedExpression=PatternExpCS */,
				37	/* CollectionLiteralPartCS: { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedLastExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=PatternExpCS : [value] | [value] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "CollectionPatternCS", -1,
			createSerializationRules(
				38	/* CollectionPatternCS: { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */
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
		grammarRuleValues[8] = createParserRuleValue(8, "CollectionTypeCS", -1,
			createSerializationRules(
				39	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* name=CollectionTypeIdentifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedCollectionMultiplicity=MultiplicityCS? : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[9] = new DataTypeRuleValue(9, "CollectionTypeIdentifier");
		grammarRuleValues[10] = createParserRuleValue(10, "CompleteOCLDocumentCS", -1,
			createSerializationRules(
				18	/* CompleteOCLDocumentCS: { ownedImports+=ImportCS[*] { ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedImports+=ImportCS* : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedPackages+=PackageDeclarationCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedContexts+=ContextDeclCS : [value] | [value] */
		);
		grammarRuleValues[11] = new DataTypeRuleValue(11, "CompleteOCLNavigationOperatorName");
		grammarRuleValues[12] = createParserRuleValue(12, "ConstraintCS", -1,
			createSerializationRules(
				19	/* ConstraintCS: { { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "ContextDeclCS", 29 /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
			createSerializationRules(
				16	/* ClassifierContextDeclCS: { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { { "inv" ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } } */,
				17	/* ClassifierContextDeclCS: { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { { "inv" ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } } */,
				25	/* OperationContextDeclCS: { "context" ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] { { "pre" ownedPreconditions+=ConstraintCS }[*] { "post" ownedPostconditions+=ConstraintCS }[*] { "body" ":" ownedBodies+=SpecificationCS }[*] } } */,
				28	/* PropertyContextDeclCS: { "context" ownedPathName=PathNameCS ":" ownedType=TypeExpCS { { "derive" ":" ownedDefaultExpressions+=SpecificationCS }[*] { "init" ":" ownedDefaultExpressions+=SpecificationCS }[*] } } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* PropertyContextDeclCS : [value] | [value] */,
			(0 << 16) | 0	/* ClassifierContextDeclCS : [value] | [value] */,
			(0 << 16) | 0	/* OperationContextDeclCS : [value] | [value] */
		);
		grammarRuleValues[14] = createParserRuleValue(14, "CurlyBracketedClauseCS", -1,
			createSerializationRules(
				40	/* CurlyBracketedClauseCS: { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" } */
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
		grammarRuleValues[15] = new TerminalRuleValue(15, "DOUBLE_QUOTED_STRING");
		grammarRuleValues[16] = createParserRuleValue(16, "DefCS", 8 /* DefCS|DefOperationCS|DefPropertyCS */,
			createSerializationRules(
				20	/* DefOperationCS: { isStatic="static"[?] "def" ":" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=DefParameterCS { "," ownedParameters+=DefParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] "=" ownedSpecification=SpecificationCS } */,
				22	/* DefPropertyCS: { isStatic="static"[?] "def" ":" name=UnrestrictedName ":" ownedType=TypeExpCS "=" ownedSpecification=SpecificationCS } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* DefOperationCS : [value] | [value] */,
			(0 << 16) | 0	/* DefPropertyCS : [value] | [value] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "DefOperationCS", -1,
			createSerializationRules(
				20	/* DefOperationCS: { isStatic="static"[?] "def" ":" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=DefParameterCS { "," ownedParameters+=DefParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] "=" ownedSpecification=SpecificationCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* isStatic?="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "def" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameters+=DefParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=DefParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS? : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[18] = createParserRuleValue(18, "DefParameterCS", -1,
			createSerializationRules(
				21	/* DefParameterCS: { name=UnrestrictedName ":" ownedType=TypeExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[19] = createParserRuleValue(19, "DefPropertyCS", -1,
			createSerializationRules(
				22	/* DefPropertyCS: { isStatic="static"[?] "def" ":" name=UnrestrictedName ":" ownedType=TypeExpCS "=" ownedSpecification=SpecificationCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* isStatic?="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "def" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[20] = new TerminalRuleValue(20, "ESCAPED_CHARACTER");
		grammarRuleValues[21] = new TerminalRuleValue(21, "ESCAPED_ID");
		grammarRuleValues[22] = createParserRuleValue(22, "ElseIfThenExpCS", -1,
			createSerializationRules(
				41	/* ElseIfThenExpCS: { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "elseif" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=ExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "then" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[23] = new DataTypeRuleValue(23, "EssentialOCLInfixOperatorName");
		grammarRuleValues[24] = new DataTypeRuleValue(24, "EssentialOCLNavigationOperatorName");
		grammarRuleValues[25] = new DataTypeRuleValue(25, "EssentialOCLReservedKeyword");
		grammarRuleValues[26] = new DataTypeRuleValue(26, "EssentialOCLUnaryOperatorName");
		grammarRuleValues[27] = new DataTypeRuleValue(27, "EssentialOCLUnreservedName");
		grammarRuleValues[28] = new DataTypeRuleValue(28, "EssentialOCLUnrestrictedName");
		grammarRuleValues[29] = createParserRuleValue(29, "ExpCS", 58 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				42	/* ExpCS: symbol={'false|true'} */,
				43	/* ExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				44	/* ExpCS: "*" */,
				45	/* ExpCS: "invalid" */,
				46	/* ExpCS: "null" */,
				47	/* ExpCS: "self" */,
				48	/* ExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				49	/* ExpCS: { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */,
				50	/* ExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				51	/* ExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				52	/* ExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				53	/* ExpCS: { "(" ownedExpression=ExpCS ")" } */,
				54	/* ExpCS: symbol=NUMBER_LITERAL */,
				55	/* ExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				56	/* ExpCS: segments+=StringLiteral[+] */,
				57	/* ExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				58	/* ExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				62	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				84	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
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
		grammarRuleValues[30] = createParserRuleValue(30, "FirstPathElementCS", -1,
			createSerializationRules(
				0	/* FirstPathElementCS: referredElement=UnrestrictedName */
			),
			(0 << 16) | 6	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[31] = new DataTypeRuleValue(31, "ID");
		grammarRuleValues[32] = new TerminalRuleValue(32, "INT");
		grammarRuleValues[33] = new DataTypeRuleValue(33, "Identifier");
		grammarRuleValues[34] = createParserRuleValue(34, "IfExpCS", -1,
			createSerializationRules(
				59	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */
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
		grammarRuleValues[35] = createParserRuleValue(35, "ImportCS", -1,
			createSerializationRules(
				23	/* ImportCS: { {"import"|"include"|"library"} { name=Identifier ":" }[?] ownedPathName=URIPathNameCS isAll="::*"[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* "import" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "include" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "library" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=URIPathNameCS : [value] | [value] */,
			(0 << 16) | 6	/* isAll?="::*"? : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[36] = new DataTypeRuleValue(36, "InfixOperatorName");
		grammarRuleValues[37] = createParserRuleValue(37, "InvalidLiteralExpCS", -1,
			createSerializationRules(
				60	/* InvalidLiteralExpCS: "invalid" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {InvalidLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "invalid" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[38] = new TerminalRuleValue(38, "LETTER_CHARACTER");
		grammarRuleValues[39] = new DataTypeRuleValue(39, "LOWER");
		grammarRuleValues[40] = createParserRuleValue(40, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				61	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "LetExpCS", -1,
			createSerializationRules(
				62	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */
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
		grammarRuleValues[42] = createParserRuleValue(42, "LetVariableCS", -1,
			createSerializationRules(
				63	/* LetVariableCS: { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
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
		grammarRuleValues[43] = new TerminalRuleValue(43, "ML_COMMENT");
		grammarRuleValues[44] = new TerminalRuleValue(44, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[45] = createParserRuleValue(45, "MapLiteralExpCS", -1,
			createSerializationRules(
				64	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */
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
		grammarRuleValues[46] = createParserRuleValue(46, "MapLiteralPartCS", -1,
			createSerializationRules(
				65	/* MapLiteralPartCS: { ownedKey=ExpCS "<-" ownedValue=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedKey=ExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValue=ExpCS : [value] | [value] */
		);
		grammarRuleValues[47] = createParserRuleValue(47, "MapTypeCS", -1,
			createSerializationRules(
				66	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */
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
		grammarRuleValues[48] = createParserRuleValue(48, "Model", -1,
			createSerializationRules(
				67	/* Model: ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[49] = createParserRuleValue(49, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS: { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 6	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[50] = createParserRuleValue(50, "MultiplicityCS", -1,
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
		grammarRuleValues[51] = createParserRuleValue(51, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS: stringBounds={'*|+|?'} */
			),
			(0 << 16) | 6	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[52] = new DataTypeRuleValue(52, "NUMBER_LITERAL");
		grammarRuleValues[53] = createParserRuleValue(53, "NameExpCS", -1,
			createSerializationRules(
				68	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */
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
		grammarRuleValues[54] = createParserRuleValue(54, "NavigatingArgCS", -1,
			createSerializationRules(
				69	/* NavigatingArgCS: ownedNameExpression=NavigatingArgExpCS */,
				70	/* NavigatingArgCS: { ":" ownedType=TypeExpCS } */,
				71	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				72	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				73	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */
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
		grammarRuleValues[55] = createParserRuleValue(55, "NavigatingArgExpCS", 59 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				24	/* NavigatingArgExpCS: "?" */,
				42	/* ExpCS: symbol={'false|true'} */,
				43	/* ExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				44	/* ExpCS: "*" */,
				45	/* ExpCS: "invalid" */,
				46	/* ExpCS: "null" */,
				47	/* ExpCS: "self" */,
				48	/* ExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				49	/* ExpCS: { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */,
				50	/* ExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				51	/* ExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				52	/* ExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				53	/* ExpCS: { "(" ownedExpression=ExpCS ")" } */,
				54	/* ExpCS: symbol=NUMBER_LITERAL */,
				55	/* ExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				56	/* ExpCS: segments+=StringLiteral[+] */,
				57	/* ExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				58	/* ExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				62	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				84	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {OCLMessageArgCS} : [value] | [value] */,
			(0 << 16) | 6	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ExpCS : [value] | [value] */
		);
		grammarRuleValues[56] = createParserRuleValue(56, "NavigatingBarArgCS", -1,
			createSerializationRules(
				74	/* NavigatingBarArgCS: { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
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
		grammarRuleValues[57] = createParserRuleValue(57, "NavigatingCommaArgCS", -1,
			createSerializationRules(
				78	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS } */,
				75	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				76	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				77	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */
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
		grammarRuleValues[58] = createParserRuleValue(58, "NavigatingSemiArgCS", -1,
			createSerializationRules(
				79	/* NavigatingSemiArgCS: { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
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
		grammarRuleValues[59] = new DataTypeRuleValue(59, "NavigationOperatorName");
		grammarRuleValues[60] = createParserRuleValue(60, "NestedExpCS", -1,
			createSerializationRules(
				80	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[61] = createParserRuleValue(61, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS: referredElement=UnreservedName */
			),
			(0 << 16) | 6	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[62] = createParserRuleValue(62, "NullLiteralExpCS", -1,
			createSerializationRules(
				81	/* NullLiteralExpCS: "null" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {NullLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "null" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[63] = createParserRuleValue(63, "NumberLiteralExpCS", -1,
			createSerializationRules(
				82	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */
			),
			(0 << 16) | 2	/* symbol=NUMBER_LITERAL : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[64] = createParserRuleValue(64, "OperationContextDeclCS", -1,
			createSerializationRules(
				25	/* OperationContextDeclCS: { "context" ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] { { "pre" ownedPreconditions+=ConstraintCS }[*] { "post" ownedPostconditions+=ConstraintCS }[*] { "body" ":" ownedBodies+=SpecificationCS }[*] } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "context" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "pre" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPreconditions+=ConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "post" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPostconditions+=ConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "body" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedBodies+=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[65] = createParserRuleValue(65, "PackageDeclarationCS", -1,
			createSerializationRules(
				26	/* PackageDeclarationCS: { "package" ownedPathName=PathNameCS { "inv" ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] "endpackage" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "package" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "inv" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInvariants+=ConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedContexts+=ContextDeclCS* : [value] | [value] */,
			(0 << 16) | 6	/* "endpackage" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[66] = createParserRuleValue(66, "ParameterCS", -1,
			createSerializationRules(
				27	/* ParameterCS: { { name=UnrestrictedName ":" }[?] ownedType=TypeExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[67] = createParserRuleValue(67, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS: { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 3	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[68] = createParserRuleValue(68, "PatternExpCS", -1,
			createSerializationRules(
				83	/* PatternExpCS: { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* patternVariableName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPatternType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[69] = createParserRuleValue(69, "PrefixedLetExpCS", 27 /* LetExpCS|PrefixedLetExpCS */,
			createSerializationRules(
				62	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				84	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedLetExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LetExpCS : [value] | [value] */
		);
		grammarRuleValues[70] = createParserRuleValue(70, "PrefixedPrimaryExpCS", 56 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				33	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				35	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				59	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				60	/* InvalidLiteralExpCS: "invalid" */,
				61	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				64	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				68	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				80	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */,
				81	/* NullLiteralExpCS: "null" */,
				82	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				85	/* PrefixedPrimaryExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				88	/* SelfExpCS: "self" */,
				93	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				94	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				104	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				113	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrimaryExpCS : [value] | [value] */
		);
		grammarRuleValues[71] = createParserRuleValue(71, "PrimaryExpCS", 55 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				33	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				35	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				59	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				60	/* InvalidLiteralExpCS: "invalid" */,
				61	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				64	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				68	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				80	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */,
				81	/* NullLiteralExpCS: "null" */,
				82	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				88	/* SelfExpCS: "self" */,
				93	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				94	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				104	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				113	/* UnlimitedNaturalLiteralExpCS: "*" */
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
		grammarRuleValues[72] = createParserRuleValue(72, "PrimitiveLiteralExpCS", 54 /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				33	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				60	/* InvalidLiteralExpCS: "invalid" */,
				81	/* NullLiteralExpCS: "null" */,
				82	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				93	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				113	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* NumberLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* StringLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* BooleanLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* UnlimitedNaturalLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* InvalidLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* NullLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[73] = createParserRuleValue(73, "PrimitiveTypeCS", -1,
			createSerializationRules(
				86	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */
			),
			(0 << 16) | 6	/* name=PrimitiveTypeIdentifier : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[74] = new DataTypeRuleValue(74, "PrimitiveTypeIdentifier");
		grammarRuleValues[75] = createParserRuleValue(75, "PropertyContextDeclCS", -1,
			createSerializationRules(
				28	/* PropertyContextDeclCS: { "context" ownedPathName=PathNameCS ":" ownedType=TypeExpCS { { "derive" ":" ownedDefaultExpressions+=SpecificationCS }[*] { "init" ":" ownedDefaultExpressions+=SpecificationCS }[*] } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "context" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "derive" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "init" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[76] = createParserRuleValue(76, "RoundBracketedClauseCS", -1,
			createSerializationRules(
				87	/* RoundBracketedClauseCS: { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {RoundBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=NavigatingArgCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)* : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[77] = new TerminalRuleValue(77, "SIMPLE_ID");
		grammarRuleValues[78] = new TerminalRuleValue(78, "SINGLE_QUOTED_STRING");
		grammarRuleValues[79] = new TerminalRuleValue(79, "SL_COMMENT");
		grammarRuleValues[80] = createParserRuleValue(80, "SelfExpCS", -1,
			createSerializationRules(
				88	/* SelfExpCS: "self" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SelfExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "self" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[81] = createParserRuleValue(81, "ShadowPartCS", -1,
			createSerializationRules(
				89	/* ShadowPartCS: ownedInitExpression=StringLiteralExpCS */,
				90	/* ShadowPartCS: { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* referredProperty=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 0	/* ownedInitExpression=StringLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[82] = createParserRuleValue(82, "SimplePathNameCS", -1,
			createSerializationRules(
				91	/* SimplePathNameCS: ownedPathElements+=FirstPathElementCS */
			),
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */
		);
		grammarRuleValues[83] = createParserRuleValue(83, "SpecificationCS", -1,
			createSerializationRules(
				29	/* SpecificationCS: exprString=UNQUOTED_STRING */,
				30	/* SpecificationCS: ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 6	/* exprString=UNQUOTED_STRING : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[84] = createParserRuleValue(84, "SquareBracketedClauseCS", -1,
			createSerializationRules(
				92	/* SquareBracketedClauseCS: { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 3	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[85] = new DataTypeRuleValue(85, "StringLiteral");
		grammarRuleValues[86] = createParserRuleValue(86, "StringLiteralExpCS", -1,
			createSerializationRules(
				93	/* StringLiteralExpCS: segments+=StringLiteral[+] */
			),
			(0 << 16) | 2	/* segments+=StringLiteral+ : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[87] = createParserRuleValue(87, "TemplateBindingCS", -1,
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
		grammarRuleValues[88] = createParserRuleValue(88, "TemplateParameterSubstitutionCS", -1,
			createSerializationRules(
				12	/* TemplateParameterSubstitutionCS: ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[89] = createParserRuleValue(89, "TemplateSignatureCS", -1,
			createSerializationRules(
				31	/* TemplateSignatureCS: { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */,
				32	/* TemplateSignatureCS: { "<" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ">" } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "<" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 6	/* ">" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[90] = createParserRuleValue(90, "TupleLiteralExpCS", -1,
			createSerializationRules(
				94	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */
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
		grammarRuleValues[91] = createParserRuleValue(91, "TupleLiteralPartCS", -1,
			createSerializationRules(
				95	/* TupleLiteralPartCS: { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[92] = createParserRuleValue(92, "TuplePartCS", -1,
			createSerializationRules(
				96	/* TuplePartCS: { name=UnrestrictedName ":" ownedType=TypeExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[93] = createParserRuleValue(93, "TupleTypeCS", -1,
			createSerializationRules(
				97	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */
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
		grammarRuleValues[94] = createParserRuleValue(94, "TypeExpCS", 46 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				98	/* TypeExpCS: { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				99	/* TypeExpCS: { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				100	/* TypeExpCS: { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] } */,
				101	/* TypeExpCS: { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				102	/* TypeExpCS: { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				103	/* TypeExpCS: { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[95] = createParserRuleValue(95, "TypeExpWithoutMultiplicityCS", 45 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				38	/* CollectionPatternCS: { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */,
				39	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				66	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				86	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				97	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				109	/* TypeNameExpCS: { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeNameExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionPatternCS : [value] | [value] */
		);
		grammarRuleValues[96] = createParserRuleValue(96, "TypeLiteralCS", 42 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */,
			createSerializationRules(
				39	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				66	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				86	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				97	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */
		);
		grammarRuleValues[97] = createParserRuleValue(97, "TypeLiteralExpCS", -1,
			createSerializationRules(
				104	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */
			),
			(0 << 16) | 2	/* ownedType=TypeLiteralWithMultiplicityCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[98] = createParserRuleValue(98, "TypeLiteralWithMultiplicityCS", 44 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */,
			createSerializationRules(
				105	/* TypeLiteralWithMultiplicityCS: { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				106	/* TypeLiteralWithMultiplicityCS: { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				107	/* TypeLiteralWithMultiplicityCS: { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				108	/* TypeLiteralWithMultiplicityCS: { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[99] = createParserRuleValue(99, "TypeNameExpCS", -1,
			createSerializationRules(
				109	/* TypeNameExpCS: { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
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
		grammarRuleValues[100] = createParserRuleValue(100, "TypeParameterCS", -1,
			createSerializationRules(
				13	/* TypeParameterCS: { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
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
		grammarRuleValues[101] = createParserRuleValue(101, "TypeRefCS", 61 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				14	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */,
				15	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */,
				39	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				66	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				86	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				97	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[102] = createParserRuleValue(102, "TypedRefCS", 50 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				14	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */,
				39	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				66	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				86	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				97	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[103] = createParserRuleValue(103, "TypedTypeRefCS", -1,
			createSerializationRules(
				14	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[104] = new TerminalRuleValue(104, "UNQUOTED_STRING");
		grammarRuleValues[105] = new DataTypeRuleValue(105, "UPPER");
		grammarRuleValues[106] = new DataTypeRuleValue(106, "URI");
		grammarRuleValues[107] = createParserRuleValue(107, "URIFirstPathElementCS", -1,
			createSerializationRules(
				110	/* URIFirstPathElementCS: referredElement=UnrestrictedName */,
				111	/* URIFirstPathElementCS: referredElement=URI */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PathElementWithURICS} : [value] | [value] */,
			(0 << 16) | 6	/* referredElement=URI : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[108] = createParserRuleValue(108, "URIPathNameCS", -1,
			createSerializationRules(
				112	/* URIPathNameCS: { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=URIFirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 3	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[109] = new DataTypeRuleValue(109, "UnaryOperatorName");
		grammarRuleValues[110] = createParserRuleValue(110, "UnlimitedNaturalLiteralExpCS", -1,
			createSerializationRules(
				113	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {UnlimitedNaturalLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "*" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[111] = new DataTypeRuleValue(111, "UnreservedName");
		grammarRuleValues[112] = new DataTypeRuleValue(112, "UnrestrictedName");
		grammarRuleValues[113] = new TerminalRuleValue(113, "WS");
		grammarRuleValues[114] = createParserRuleValue(114, "WildcardTypeRefCS", -1,
			createSerializationRules(
				15	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
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
		grammarRuleVectors[0] = new GrammarRuleVector(0x10L);
		// CollectionLiteralPartCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x40L);
		// CollectionTypeCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x100L);
		// ConstraintCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x1000L);
		// ContextDeclCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x2000L);
		// CurlyBracketedClauseCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x4000L);
		// DefCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x10000L);
		// DefParameterCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x40000L);
		// DefCS|DefOperationCS|DefPropertyCS
		grammarRuleVectors[8] = new GrammarRuleVector(0xb0000L);
		// ElseIfThenExpCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x400000L);
		// ExpCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x20000000L);
		// FirstPathElementCS
		grammarRuleVectors[11] = new GrammarRuleVector(0x40000000L);
		// ImportCS
		grammarRuleVectors[12] = new GrammarRuleVector(0x800000000L);
		// LetVariableCS
		grammarRuleVectors[13] = new GrammarRuleVector(0x40000000000L);
		// MapLiteralPartCS
		grammarRuleVectors[14] = new GrammarRuleVector(0x400000000000L);
		// MapTypeCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x800000000000L);
		// MultiplicityCS
		grammarRuleVectors[16] = new GrammarRuleVector(0x4000000000000L);
		// NavigatingArgExpCS
		grammarRuleVectors[17] = new GrammarRuleVector(0x80000000000000L);
		// NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[18] = new GrammarRuleVector(0x700000000000000L);
		// NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[19] = new GrammarRuleVector(0x740000000000000L);
		// FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[20] = new GrammarRuleVector(0x2000000040000000L);
		// PackageDeclarationCS
		grammarRuleVectors[21] = new GrammarRuleVector(0x0L,0x2L);
		// ParameterCS
		grammarRuleVectors[22] = new GrammarRuleVector(0x0L,0x4L);
		// PathNameCS
		grammarRuleVectors[23] = new GrammarRuleVector(0x0L,0x8L);
		// PatternExpCS
		grammarRuleVectors[24] = new GrammarRuleVector(0x0L,0x10L);
		// ExpCS|PatternExpCS
		grammarRuleVectors[25] = new GrammarRuleVector(0x20000000L,0x10L);
		// PrefixedLetExpCS
		grammarRuleVectors[26] = new GrammarRuleVector(0x0L,0x20L);
		// LetExpCS|PrefixedLetExpCS
		grammarRuleVectors[27] = new GrammarRuleVector(0x20000000000L,0x20L);
		// PrefixedPrimaryExpCS
		grammarRuleVectors[28] = new GrammarRuleVector(0x0L,0x40L);
		// ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS
		grammarRuleVectors[29] = new GrammarRuleVector(0x2008L,0x801L);
		// RoundBracketedClauseCS
		grammarRuleVectors[30] = new GrammarRuleVector(0x0L,0x1000L);
		// ShadowPartCS
		grammarRuleVectors[31] = new GrammarRuleVector(0x0L,0x20000L);
		// SpecificationCS
		grammarRuleVectors[32] = new GrammarRuleVector(0x0L,0x80000L);
		// SquareBracketedClauseCS
		grammarRuleVectors[33] = new GrammarRuleVector(0x0L,0x100000L);
		// StringLiteralExpCS
		grammarRuleVectors[34] = new GrammarRuleVector(0x0L,0x400000L);
		// TemplateBindingCS
		grammarRuleVectors[35] = new GrammarRuleVector(0x0L,0x800000L);
		// TemplateParameterSubstitutionCS
		grammarRuleVectors[36] = new GrammarRuleVector(0x0L,0x1000000L);
		// TemplateSignatureCS
		grammarRuleVectors[37] = new GrammarRuleVector(0x0L,0x2000000L);
		// TupleLiteralPartCS
		grammarRuleVectors[38] = new GrammarRuleVector(0x0L,0x8000000L);
		// TuplePartCS
		grammarRuleVectors[39] = new GrammarRuleVector(0x0L,0x10000000L);
		// TypeExpCS
		grammarRuleVectors[40] = new GrammarRuleVector(0x0L,0x40000000L);
		// TypeExpWithoutMultiplicityCS
		grammarRuleVectors[41] = new GrammarRuleVector(0x0L,0x80000000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
		grammarRuleVectors[42] = new GrammarRuleVector(0x800000000100L,0x120000200L);
		// TypeLiteralWithMultiplicityCS
		grammarRuleVectors[43] = new GrammarRuleVector(0x0L,0x400000000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
		grammarRuleVectors[44] = new GrammarRuleVector(0x800000000100L,0x520000200L);
		// CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[45] = new GrammarRuleVector(0x800000000180L,0x9a0000200L);
		// CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[46] = new GrammarRuleVector(0x800000000180L,0x9e0000200L);
		// TypeParameterCS
		grammarRuleVectors[47] = new GrammarRuleVector(0x0L,0x1000000000L);
		// TypeRefCS
		grammarRuleVectors[48] = new GrammarRuleVector(0x0L,0x2000000000L);
		// TypedRefCS
		grammarRuleVectors[49] = new GrammarRuleVector(0x0L,0x4000000000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[50] = new GrammarRuleVector(0x800000000100L,0xc120000200L);
		// NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[51] = new GrammarRuleVector(0x2000000000000000L,0x80000000000L);
		// FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[52] = new GrammarRuleVector(0x2000000040000000L,0x80000000000L);
		// URIPathNameCS
		grammarRuleVectors[53] = new GrammarRuleVector(0x0L,0x100000000000L);
		// BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[54] = new GrammarRuleVector(0xc000002000000004L,0x400000400100L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[55] = new GrammarRuleVector(0xd020212400000024L,0x400204410180L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[56] = new GrammarRuleVector(0xd020212400000024L,0x4002044101c0L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[57] = new GrammarRuleVector(0xd020232400000024L,0x4002044101e0L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[58] = new GrammarRuleVector(0xd020232420000024L,0x4002044101e0L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[59] = new GrammarRuleVector(0xd0a0232420000024L,0x4002044101e0L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[60] = new GrammarRuleVector(0xd020232420000024L,0x4002044101f0L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[61] = new GrammarRuleVector(0x800000000100L,0x400e120000200L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(108);
		// assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(109);
		// assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(112);
		// assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(113);
		// assert (|CollectionPatternCS::ownedType| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(115);
		// assert (|CollectionTypeCS::name| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(116);
		// assert (|ConstraintCS::ownedSpecification| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(117);
		// assert (|ContextCS::ownedExpression| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(118);
		// assert (|DefCS::ownedSpecification| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(121);
		// assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(124);
		// assert (|FeatureContextDeclCS::ownedType| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(125);
		// assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(126);
		// assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(127);
		// assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(128);
		// assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(129);
		// assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(130);
		// assert (|ImportCS::ownedPathName| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(131);
		// assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(132);
		// assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(133);
		// assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(134);
		// assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(138);
		// assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(139);
		// assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(140);
		// assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(141);
		// assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(142);
		// assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(143);
		// assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(144);
		// assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(145);
		// assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(146);
		// assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(147);
		// assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(148);
		// assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(149);
		// assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(150);
		// assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(151);
		// assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(152);
		// assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(153);
		// assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(154);
		// assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(157);
		// assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(158);
		// assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(159);
		// assert (|PathNameDeclCS::ownedPathName| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(160);
		// assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(161);
		// assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(162);
		// assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(165);
		// assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(166);
		// assert (|SpecificationCS::exprString| - 1) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(167);
		// assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[46] = createMatchStep_Assert(170);
		// assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[47] = createMatchStep_Assert(173);
		// assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[48] = createMatchStep_Assert(176);
		// assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[49] = createMatchStep_Assert(177);
		// assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[50] = createMatchStep_Assert(180);
		// assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[51] = createMatchStep_Assert(181);
		// assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[52] = createMatchStep_Assert(182);
		// assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[53] = createMatchStep_Assign(0, 111);
		// assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchSteps[54] = createMatchStep_Assign(0, 120);
		// assign V0 = (|LetExpCS::ownedVariables| - 1)
		serializationMatchSteps[55] = createMatchStep_Assign(0, 135);
		// assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[56] = createMatchStep_Assign(0, 137);
		// assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[57] = createMatchStep_Assign(0, 159);
		// assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
		serializationMatchSteps[58] = createMatchStep_Assign(0, 164);
		// assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[59] = createMatchStep_Assign(0, 168);
		// assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[60] = createMatchStep_Assign(0, 169);
		// assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[61] = createMatchStep_Assign(0, 171);
		// assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[62] = createMatchStep_Assign(0, 172);
		// assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[63] = createMatchStep_Assign(0, 175);
		// assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[64] = createMatchStep_Assign(0, 179);
		// assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchSteps[65] = createMatchStep_Assign(0, 7);
		// assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchSteps[66] = createMatchStep_Assign(0, 15);
		// assign V0 = |CollectionPatternCS::restVariableName|
		serializationMatchSteps[67] = createMatchStep_Assign(0, 18);
		// assign V0 = |CollectionTypeCS::ownedType|
		serializationMatchSteps[68] = createMatchStep_Assign(0, 21);
		// assign V0 = |DefCS::isStatic.'static'|
		serializationMatchSteps[69] = createMatchStep_Assign(0, 28);
		// assign V0 = |IfExpCS::ownedIfThenExpressions|
		serializationMatchSteps[70] = createMatchStep_Assign(0, 35);
		// assign V0 = |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchSteps[71] = createMatchStep_Assign(0, 45);
		// assign V0 = |MapTypeCS::ownedValueType|
		serializationMatchSteps[72] = createMatchStep_Assign(0, 52);
		// assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[73] = createMatchStep_Assign(0, 54);
		// assign V0 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[74] = createMatchStep_Assign(0, 55);
		// assign V0 = |NamedElementCS::name|
		serializationMatchSteps[75] = createMatchStep_Assign(0, 57);
		// assign V0 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[76] = createMatchStep_Assign(0, 58);
		// assign V0 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[77] = createMatchStep_Assign(0, 59);
		// assign V0 = |NavigatingArgCS::ownedType|
		serializationMatchSteps[78] = createMatchStep_Assign(0, 61);
		// assign V0 = |PackageDeclarationCS::ownedInvariants|
		serializationMatchSteps[79] = createMatchStep_Assign(0, 73);
		// assign V0 = |PatternExpCS::patternVariableName|
		serializationMatchSteps[80] = createMatchStep_Assign(0, 78);
		// assign V0 = |PropertyContextDeclCS::ownedDefaultExpressions|
		serializationMatchSteps[81] = createMatchStep_Assign(0, 80);
		// assign V0 = |RootCS::ownedImports|
		serializationMatchSteps[82] = createMatchStep_Assign(0, 81);
		// assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[83] = createMatchStep_Assign(0, 87);
		// assign V0 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[84] = createMatchStep_Assign(0, 92);
		// assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[85] = createMatchStep_Assign(0, 97);
		// assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[86] = createMatchStep_Assign(0, 102);
		// assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[87] = createMatchStep_Assign(0, 103);
		// assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[88] = createMatchStep_Assign(0, 106);
		// assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[89] = createMatchStep_Assign(0, 107);
		// assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[90] = createMatchStep_Assign(1, 110);
		// assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[91] = createMatchStep_Assign(1, 114);
		// assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[92] = createMatchStep_Assign(1, 119);
		// assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[93] = createMatchStep_Assign(1, 136);
		// assign V1 = (|OperationContextDeclCS::ownedParameters| > 0)
		serializationMatchSteps[94] = createMatchStep_Assign(1, 156);
		// assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
		serializationMatchSteps[95] = createMatchStep_Assign(1, 163);
		// assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[96] = createMatchStep_Assign(1, 175);
		// assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[97] = createMatchStep_Assign(1, 178);
		// assign V1 = 0
		serializationMatchSteps[98] = createMatchStep_Assign(1, 0);
		// assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[99] = createMatchStep_Assign(1, 6);
		// assign V1 = |ClassifierContextDeclCS::selfName|
		serializationMatchSteps[100] = createMatchStep_Assign(1, 11);
		// assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[101] = createMatchStep_Assign(1, 20);
		// assign V1 = |CompleteOCLDocumentCS::ownedPackages|
		serializationMatchSteps[102] = createMatchStep_Assign(1, 23);
		// assign V1 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[103] = createMatchStep_Assign(1, 24);
		// assign V1 = |ImportCS::isAll.'::*'|
		serializationMatchSteps[104] = createMatchStep_Assign(1, 39);
		// assign V1 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[105] = createMatchStep_Assign(1, 55);
		// assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[106] = createMatchStep_Assign(1, 58);
		// assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[107] = createMatchStep_Assign(1, 59);
		// assign V1 = |PackageDeclarationCS::ownedContexts|
		serializationMatchSteps[108] = createMatchStep_Assign(1, 72);
		// assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[109] = createMatchStep_Assign(1, 88);
		// assign V1 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[110] = createMatchStep_Assign(1, 92);
		// assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[111] = createMatchStep_Assign(1, 99);
		// assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[112] = createMatchStep_Assign(1, 102);
		// assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[113] = createMatchStep_Assign(1, 106);
		// assign V2 = (|DefOperationCS::ownedParameters| > 0)
		serializationMatchSteps[114] = createMatchStep_Assign(2, 123);
		// assign V2 = (|OperationContextDeclCS::ownedParameters| - 1)
		serializationMatchSteps[115] = createMatchStep_Assign(2, 155);
		// assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[116] = createMatchStep_Assign(2, 174);
		// assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[117] = createMatchStep_Assign(2, 4);
		// assign V2 = |ClassifierContextDeclCS::ownedInvariants|
		serializationMatchSteps[118] = createMatchStep_Assign(2, 10);
		// assign V2 = |CompleteOCLDocumentCS::ownedContexts|
		serializationMatchSteps[119] = createMatchStep_Assign(2, 22);
		// assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[120] = createMatchStep_Assign(2, 102);
		// assign V3 = (|DefOperationCS::ownedParameters| - 1)
		serializationMatchSteps[121] = createMatchStep_Assign(3, 122);
		// assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[122] = createMatchStep_Assign(3, 3);
		// assign V3 = |ClassifierContextDeclCS::ownedDefinitions|
		serializationMatchSteps[123] = createMatchStep_Assign(3, 9);
		// assign V3 = |FeatureContextDeclCS::ownedType|
		serializationMatchSteps[124] = createMatchStep_Assign(3, 32);
		// assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[125] = createMatchStep_Assign(3, 102);
		// assign V4 = |OperationContextDeclCS::ownedPreconditions|
		serializationMatchSteps[126] = createMatchStep_Assign(4, 70);
		// assign V4 = |TypedElementCS::ownedType|
		serializationMatchSteps[127] = createMatchStep_Assign(4, 101);
		// assign V5 = |OperationContextDeclCS::ownedPostconditions|
		serializationMatchSteps[128] = createMatchStep_Assign(5, 69);
		// assign V6 = |OperationContextDeclCS::ownedBodies|
		serializationMatchSteps[129] = createMatchStep_Assign(6, 67);
		// check-rule basecs::ConstraintCS.ownedMessageSpecification : 83
		serializationMatchSteps[130] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 32/*SpecificationCS*/);
		// check-rule basecs::ConstraintCS.ownedSpecification : 83
		serializationMatchSteps[131] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 32/*SpecificationCS*/);
		// check-rule basecs::ImportCS.ownedPathName : 108
		serializationMatchSteps[132] = createMatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 53/*URIPathNameCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 30
		serializationMatchSteps[133] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 11/*FirstPathElementCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 30|61
		serializationMatchSteps[134] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 20/*FirstPathElementCS|NextPathElementCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 61|107
		serializationMatchSteps[135] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 51/*NextPathElementCS|URIFirstPathElementCS*/);
		// check-rule basecs::RootCS.ownedImports : 35
		serializationMatchSteps[136] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/*ImportCS*/);
		// check-rule basecs::TemplateBindingCS.ownedMultiplicity : 50
		serializationMatchSteps[137] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 16/*MultiplicityCS*/);
		// check-rule basecs::TemplateBindingCS.ownedSubstitutions : 88
		serializationMatchSteps[138] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 36/*TemplateParameterSubstitutionCS*/);
		// check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 101
		serializationMatchSteps[139] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 48/*TypeRefCS*/);
		// check-rule basecs::TemplateSignatureCS.ownedParameters : 100
		serializationMatchSteps[140] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 47/*TypeParameterCS*/);
		// check-rule basecs::TemplateableElementCS.ownedSignature : 89
		serializationMatchSteps[141] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 37/*TemplateSignatureCS*/);
		// check-rule basecs::TupleTypeCS.ownedParts : 92
		serializationMatchSteps[142] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 39/*TuplePartCS*/);
		// check-rule basecs::TypeParameterCS.ownedExtends : 102
		serializationMatchSteps[143] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 49/*TypedRefCS*/);
		// check-rule basecs::TypedElementCS.ownedType : 94
		serializationMatchSteps[144] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 40/*TypeExpCS*/);
		// check-rule basecs::TypedRefCS.ownedMultiplicity : 50
		serializationMatchSteps[145] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/*MultiplicityCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedBinding : 87
		serializationMatchSteps[146] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 35/*TemplateBindingCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedPathName : 67
		serializationMatchSteps[147] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 23/*PathNameCS*/);
		// check-rule basecs::WildcardTypeRefCS.ownedExtends : 102
		serializationMatchSteps[148] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 49/*TypedRefCS*/);
		// check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16
		serializationMatchSteps[149] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 6/*DefCS*/);
		// check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12
		serializationMatchSteps[150] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 3/*ConstraintCS*/);
		// check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : 13
		serializationMatchSteps[151] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, 4/*ContextDeclCS*/);
		// check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : 65
		serializationMatchSteps[152] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, 21/*PackageDeclarationCS*/);
		// check-rule completeoclcs::DefCS.ownedSpecification : 83
		serializationMatchSteps[153] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 32/*SpecificationCS*/);
		// check-rule completeoclcs::DefOperationCS.ownedParameters : 18
		serializationMatchSteps[154] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, 7/*DefParameterCS*/);
		// check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94
		serializationMatchSteps[155] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 40/*TypeExpCS*/);
		// check-rule completeoclcs::OperationContextDeclCS.ownedBodies : 83
		serializationMatchSteps[156] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, 32/*SpecificationCS*/);
		// check-rule completeoclcs::OperationContextDeclCS.ownedParameters : 66
		serializationMatchSteps[157] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, 22/*ParameterCS*/);
		// check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : 12
		serializationMatchSteps[158] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, 3/*ConstraintCS*/);
		// check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : 12
		serializationMatchSteps[159] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, 3/*ConstraintCS*/);
		// check-rule completeoclcs::PackageDeclarationCS.ownedContexts : 13
		serializationMatchSteps[160] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, 4/*ContextDeclCS*/);
		// check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : 12
		serializationMatchSteps[161] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, 3/*ConstraintCS*/);
		// check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67
		serializationMatchSteps[162] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 23/*PathNameCS*/);
		// check-rule completeoclcs::PropertyContextDeclCS.ownedDefaultExpressions : 83
		serializationMatchSteps[163] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, 32/*SpecificationCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[164] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/*CurlyBracketedClauseCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67
		serializationMatchSteps[165] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 23/*PathNameCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76
		serializationMatchSteps[166] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 30/*RoundBracketedClauseCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84
		serializationMatchSteps[167] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 33/*SquareBracketedClauseCS*/);
		// check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6
		serializationMatchSteps[168] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 1/*CollectionLiteralPartCS*/);
		// check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8
		serializationMatchSteps[169] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 29
		serializationMatchSteps[170] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 68
		serializationMatchSteps[171] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 24/*PatternExpCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 29
		serializationMatchSteps[172] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68
		serializationMatchSteps[173] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 24/*PatternExpCS*/);
		// check-rule essentialoclcs::CollectionPatternCS.ownedType : 8
		serializationMatchSteps[174] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50
		serializationMatchSteps[175] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 16/*MultiplicityCS*/);
		// check-rule essentialoclcs::CollectionTypeCS.ownedType : 95
		serializationMatchSteps[176] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 41/*TypeExpWithoutMultiplicityCS*/);
		// check-rule essentialoclcs::ContextCS.ownedExpression : 29
		serializationMatchSteps[177] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 81
		serializationMatchSteps[178] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 31/*ShadowPartCS*/);
		// check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 29
		serializationMatchSteps[179] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedCondition : 29|68
		serializationMatchSteps[180] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 25/*ExpCS|PatternExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedElseExpression : 29
		serializationMatchSteps[181] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22
		serializationMatchSteps[182] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 9/*ElseIfThenExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedThenExpression : 29
		serializationMatchSteps[183] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::IfThenExpCS.ownedCondition : 29
		serializationMatchSteps[184] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 10/*ExpCS*/);
		// check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 29
		serializationMatchSteps[185] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::InfixExpCS.ownedLeft : 70
		serializationMatchSteps[186] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 28/*PrefixedPrimaryExpCS*/);
		// check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 29
		serializationMatchSteps[187] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 10/*ExpCS*/);
		// check-rule essentialoclcs::LetExpCS.ownedInExpression : 29
		serializationMatchSteps[188] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::LetExpCS.ownedVariables : 42
		serializationMatchSteps[189] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 13/*LetVariableCS*/);
		// check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 76
		serializationMatchSteps[190] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 30/*RoundBracketedClauseCS*/);
		// check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46
		serializationMatchSteps[191] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 14/*MapLiteralPartCS*/);
		// check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47
		serializationMatchSteps[192] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 15/*MapTypeCS*/);
		// check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 29
		serializationMatchSteps[193] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 10/*ExpCS*/);
		// check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 29
		serializationMatchSteps[194] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 10/*ExpCS*/);
		// check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94
		serializationMatchSteps[195] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 40/*TypeExpCS*/);
		// check-rule essentialoclcs::MapTypeCS.ownedValueType : 94
		serializationMatchSteps[196] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 40/*TypeExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4
		serializationMatchSteps[197] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/*CoIteratorVariableCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29
		serializationMatchSteps[198] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55
		serializationMatchSteps[199] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/*NavigatingArgExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedType : 94
		serializationMatchSteps[200] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 40/*TypeExpCS*/);
		// check-rule essentialoclcs::NestedExpCS.ownedExpression : 29
		serializationMatchSteps[201] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 29
		serializationMatchSteps[202] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 10/*ExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 69
		serializationMatchSteps[203] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 26/*PrefixedLetExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 70
		serializationMatchSteps[204] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 28/*PrefixedPrimaryExpCS*/);
		// check-rule essentialoclcs::PatternExpCS.ownedPatternType : 94
		serializationMatchSteps[205] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 40/*TypeExpCS*/);
		// check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 54|56|57|58
		serializationMatchSteps[206] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 19/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		// check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 29|68
		serializationMatchSteps[207] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 25/*ExpCS|PatternExpCS*/);
		// check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 86
		serializationMatchSteps[208] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 34/*StringLiteralExpCS*/);
		// check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 29
		serializationMatchSteps[209] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 10/*ExpCS*/);
		// check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91
		serializationMatchSteps[210] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 38/*TupleLiteralPartCS*/);
		// check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98
		serializationMatchSteps[211] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 43/*TypeLiteralWithMultiplicityCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[212] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/*CurlyBracketedClauseCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67
		serializationMatchSteps[213] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 23/*PathNameCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 29
		serializationMatchSteps[214] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 10/*ExpCS*/);
		// check-rule essentialoclcs::VariableCS.ownedInitExpression : 29
		serializationMatchSteps[215] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 10/*ExpCS*/);
		// check-rule essentialoclcs::VariableCS.ownedType : 94
		serializationMatchSteps[216] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 40/*TypeExpCS*/);
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
		// |BooleanLiteralExpCS::symbol.'false|true'|
		serializationMatchTerms[8] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 7 /* 'false|true' */);
		// |ClassifierContextDeclCS::ownedDefinitions|
		serializationMatchTerms[9] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS);
		// |ClassifierContextDeclCS::ownedInvariants|
		serializationMatchTerms[10] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS);
		// |ClassifierContextDeclCS::selfName|
		serializationMatchTerms[11] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME);
		// |CollectionLiteralExpCS::ownedParts|
		serializationMatchTerms[12] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		// |CollectionLiteralExpCS::ownedType|
		serializationMatchTerms[13] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		// |CollectionLiteralPartCS::ownedExpression|
		serializationMatchTerms[14] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		// |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchTerms[15] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		// |CollectionPatternCS::ownedParts|
		serializationMatchTerms[16] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		// |CollectionPatternCS::ownedType|
		serializationMatchTerms[17] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		// |CollectionPatternCS::restVariableName|
		serializationMatchTerms[18] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		// |CollectionTypeCS::name|
		serializationMatchTerms[19] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		// |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchTerms[20] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		// |CollectionTypeCS::ownedType|
		serializationMatchTerms[21] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		// |CompleteOCLDocumentCS::ownedContexts|
		serializationMatchTerms[22] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS);
		// |CompleteOCLDocumentCS::ownedPackages|
		serializationMatchTerms[23] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES);
		// |ConstraintCS::ownedMessageSpecification|
		serializationMatchTerms[24] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		// |ConstraintCS::ownedSpecification|
		serializationMatchTerms[25] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		// |ContextCS::ownedExpression|
		serializationMatchTerms[26] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		// |CurlyBracketedClauseCS::ownedParts|
		serializationMatchTerms[27] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		// |DefCS::isStatic.'static'|
		serializationMatchTerms[28] = createSerializationMatchTermEAttributeSize(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, 8 /* 'static' */);
		// |DefCS::ownedSpecification|
		serializationMatchTerms[29] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION);
		// |DefOperationCS::ownedParameters|
		serializationMatchTerms[30] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS);
		// |ExpSpecificationCS::ownedExpression|
		serializationMatchTerms[31] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		// |FeatureContextDeclCS::ownedType|
		serializationMatchTerms[32] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE);
		// |IfExpCS::ownedCondition|
		serializationMatchTerms[33] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		// |IfExpCS::ownedElseExpression|
		serializationMatchTerms[34] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		// |IfExpCS::ownedIfThenExpressions|
		serializationMatchTerms[35] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		// |IfExpCS::ownedThenExpression|
		serializationMatchTerms[36] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		// |IfThenExpCS::ownedCondition|
		serializationMatchTerms[37] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		// |IfThenExpCS::ownedThenExpression|
		serializationMatchTerms[38] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		// |ImportCS::isAll.'::*'|
		serializationMatchTerms[39] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, 2 /* '::*' */);
		// |ImportCS::ownedPathName|
		serializationMatchTerms[40] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		// |InfixExpCS::ownedLeft|
		serializationMatchTerms[41] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		// |LambdaLiteralExpCS::ownedExpressionCS|
		serializationMatchTerms[42] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		// |LetExpCS::ownedInExpression|
		serializationMatchTerms[43] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		// |LetExpCS::ownedVariables|
		serializationMatchTerms[44] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		// |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchTerms[45] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// |MapLiteralExpCS::ownedParts|
		serializationMatchTerms[46] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		// |MapLiteralExpCS::ownedType|
		serializationMatchTerms[47] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		// |MapLiteralPartCS::ownedKey|
		serializationMatchTerms[48] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		// |MapLiteralPartCS::ownedValue|
		serializationMatchTerms[49] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		// |MapTypeCS::name.'Map'|
		serializationMatchTerms[50] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 5 /* 'Map' */);
		// |MapTypeCS::ownedKeyType|
		serializationMatchTerms[51] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		// |MapTypeCS::ownedValueType|
		serializationMatchTerms[52] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		// |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[53] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[54] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[55] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 10 /* '|1' */);
		// |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[56] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */);
		// |NamedElementCS::name|
		serializationMatchTerms[57] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// |NavigatingArgCS::ownedCoIterator|
		serializationMatchTerms[58] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		// |NavigatingArgCS::ownedInitExpression|
		serializationMatchTerms[59] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		// |NavigatingArgCS::ownedNameExpression|
		serializationMatchTerms[60] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		// |NavigatingArgCS::ownedType|
		serializationMatchTerms[61] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		// |NavigatingArgCS::prefix.','|
		serializationMatchTerms[62] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */);
		// |NavigatingArgCS::prefix.';'|
		serializationMatchTerms[63] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 3 /* ';' */);
		// |NavigatingArgCS::prefix.'|'|
		serializationMatchTerms[64] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 9 /* '|' */);
		// |NestedExpCS::ownedExpression|
		serializationMatchTerms[65] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		// |NumberLiteralExpCS::symbol|
		serializationMatchTerms[66] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		// |OperationContextDeclCS::ownedBodies|
		serializationMatchTerms[67] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES);
		// |OperationContextDeclCS::ownedParameters|
		serializationMatchTerms[68] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS);
		// |OperationContextDeclCS::ownedPostconditions|
		serializationMatchTerms[69] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS);
		// |OperationContextDeclCS::ownedPreconditions|
		serializationMatchTerms[70] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS);
		// |OperatorExpCS::ownedRight|
		serializationMatchTerms[71] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		// |PackageDeclarationCS::ownedContexts|
		serializationMatchTerms[72] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS);
		// |PackageDeclarationCS::ownedInvariants|
		serializationMatchTerms[73] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS);
		// |PathElementCS::referredElement|
		serializationMatchTerms[74] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// |PathNameCS::ownedPathElements|
		serializationMatchTerms[75] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// |PathNameDeclCS::ownedPathName|
		serializationMatchTerms[76] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME);
		// |PatternExpCS::ownedPatternType|
		serializationMatchTerms[77] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		// |PatternExpCS::patternVariableName|
		serializationMatchTerms[78] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		// |PrimitiveTypeRefCS::name|
		serializationMatchTerms[79] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		// |PropertyContextDeclCS::ownedDefaultExpressions|
		serializationMatchTerms[80] = new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS);
		// |RootCS::ownedImports|
		serializationMatchTerms[81] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		// |RoundBracketedClauseCS::ownedArguments|
		serializationMatchTerms[82] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		// |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[83] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// |ShadowPartCS::referredProperty|
		serializationMatchTerms[84] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// |SpecificationCS::exprString|
		serializationMatchTerms[85] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		// |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[86] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// |StringLiteralExpCS::segments|
		serializationMatchTerms[87] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[88] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[89] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[90] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[91] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// |TemplateableElementCS::ownedSignature|
		serializationMatchTerms[92] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		// |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[93] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[94] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 6 /* 'Tuple' */);
		// |TupleTypeCS::ownedParts|
		serializationMatchTerms[95] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[96] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[97] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[98] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[99] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// |TypeParameterCS::ownedExtends|
		serializationMatchTerms[100] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// |TypedElementCS::ownedType|
		serializationMatchTerms[101] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		// |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[102] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[103] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[104] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// |VariableCS::ownedInitExpression|
		serializationMatchTerms[105] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		// |VariableCS::ownedType|
		serializationMatchTerms[106] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		// |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[107] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// (|AbstractNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[108] = createSerializationMatchTermSubtract(5, 1);
		// (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
		serializationMatchTerms[109] = createSerializationMatchTermSubtract(8, 1);
		// (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[110] = createSerializationMatchTermSubtract(12, 1);
		// (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[111] = createSerializationMatchTermGreaterThan(12, 0);
		// (|CollectionLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[112] = createSerializationMatchTermSubtract(13, 1);
		// (|CollectionLiteralPartCS::ownedExpression| - 1)
		serializationMatchTerms[113] = createSerializationMatchTermSubtract(14, 1);
		// (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchTerms[114] = createSerializationMatchTermSubtract(16, 1);
		// (|CollectionPatternCS::ownedType| - 1)
		serializationMatchTerms[115] = createSerializationMatchTermSubtract(17, 1);
		// (|CollectionTypeCS::name| - 1)
		serializationMatchTerms[116] = createSerializationMatchTermSubtract(19, 1);
		// (|ConstraintCS::ownedSpecification| - 1)
		serializationMatchTerms[117] = createSerializationMatchTermSubtract(25, 1);
		// (|ContextCS::ownedExpression| - 1)
		serializationMatchTerms[118] = createSerializationMatchTermSubtract(26, 1);
		// (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchTerms[119] = createSerializationMatchTermSubtract(27, 1);
		// (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchTerms[120] = createSerializationMatchTermGreaterThan(27, 0);
		// (|DefCS::ownedSpecification| - 1)
		serializationMatchTerms[121] = createSerializationMatchTermSubtract(29, 1);
		// (|DefOperationCS::ownedParameters| - 1)
		serializationMatchTerms[122] = createSerializationMatchTermSubtract(30, 1);
		// (|DefOperationCS::ownedParameters| > 0)
		serializationMatchTerms[123] = createSerializationMatchTermGreaterThan(30, 0);
		// (|ExpSpecificationCS::ownedExpression| - 1)
		serializationMatchTerms[124] = createSerializationMatchTermSubtract(31, 1);
		// (|FeatureContextDeclCS::ownedType| - 1)
		serializationMatchTerms[125] = createSerializationMatchTermSubtract(32, 1);
		// (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[126] = createSerializationMatchTermSubtract(33, 1);
		// (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[127] = createSerializationMatchTermSubtract(34, 1);
		// (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[128] = createSerializationMatchTermSubtract(36, 1);
		// (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[129] = createSerializationMatchTermSubtract(37, 1);
		// (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[130] = createSerializationMatchTermSubtract(38, 1);
		// (|ImportCS::ownedPathName| - 1)
		serializationMatchTerms[131] = createSerializationMatchTermSubtract(40, 1);
		// (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[132] = createSerializationMatchTermSubtract(41, 1);
		// (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[133] = createSerializationMatchTermSubtract(42, 1);
		// (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[134] = createSerializationMatchTermSubtract(43, 1);
		// (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[135] = createSerializationMatchTermSubtract(44, 1);
		// (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[136] = createSerializationMatchTermSubtract(46, 1);
		// (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[137] = createSerializationMatchTermGreaterThan(46, 0);
		// (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[138] = createSerializationMatchTermSubtract(47, 1);
		// (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[139] = createSerializationMatchTermSubtract(48, 1);
		// (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[140] = createSerializationMatchTermSubtract(49, 1);
		// (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[141] = createSerializationMatchTermSubtract(50, 1);
		// (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[142] = createSerializationMatchTermSubtract(51, 2);
		// (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[143] = createSerializationMatchTermSubtract(53, 1);
		// (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[144] = createSerializationMatchTermSubtract(56, 1);
		// (|NamedElementCS::name| - 1)
		serializationMatchTerms[145] = createSerializationMatchTermSubtract(57, 1);
		// (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[146] = createSerializationMatchTermSubtract(58, 1);
		// (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[147] = createSerializationMatchTermSubtract(59, 1);
		// (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[148] = createSerializationMatchTermSubtract(60, 1);
		// (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[149] = createSerializationMatchTermSubtract(61, 1);
		// (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[150] = createSerializationMatchTermSubtract(62, 1);
		// (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[151] = createSerializationMatchTermSubtract(63, 1);
		// (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[152] = createSerializationMatchTermSubtract(64, 1);
		// (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[153] = createSerializationMatchTermSubtract(65, 1);
		// (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[154] = createSerializationMatchTermSubtract(66, 1);
		// (|OperationContextDeclCS::ownedParameters| - 1)
		serializationMatchTerms[155] = createSerializationMatchTermSubtract(68, 1);
		// (|OperationContextDeclCS::ownedParameters| > 0)
		serializationMatchTerms[156] = createSerializationMatchTermGreaterThan(68, 0);
		// (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[157] = createSerializationMatchTermSubtract(71, 1);
		// (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[158] = createSerializationMatchTermSubtract(74, 1);
		// (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[159] = createSerializationMatchTermSubtract(75, 1);
		// (|PathNameDeclCS::ownedPathName| - 1)
		serializationMatchTerms[160] = createSerializationMatchTermSubtract(76, 1);
		// (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[161] = createSerializationMatchTermSubtract(77, 1);
		// (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[162] = createSerializationMatchTermSubtract(79, 1);
		// (|RoundBracketedClauseCS::ownedArguments| - 1)
		serializationMatchTerms[163] = createSerializationMatchTermSubtract(82, 1);
		// (|RoundBracketedClauseCS::ownedArguments| > 0)
		serializationMatchTerms[164] = createSerializationMatchTermGreaterThan(82, 0);
		// (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[165] = createSerializationMatchTermSubtract(83, 1);
		// (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[166] = createSerializationMatchTermSubtract(84, 1);
		// (|SpecificationCS::exprString| - 1)
		serializationMatchTerms[167] = createSerializationMatchTermSubtract(85, 1);
		// (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[168] = createSerializationMatchTermSubtract(86, 1);
		// (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[169] = createSerializationMatchTermSubtract(89, 1);
		// (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[170] = createSerializationMatchTermSubtract(90, 1);
		// (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[171] = createSerializationMatchTermSubtract(91, 1);
		// (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[172] = createSerializationMatchTermSubtract(93, 1);
		// (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[173] = createSerializationMatchTermSubtract(94, 1);
		// (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[174] = createSerializationMatchTermSubtract(95, 1);
		// (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[175] = createSerializationMatchTermGreaterThan(95, 0);
		// (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[176] = createSerializationMatchTermSubtract(96, 1);
		// (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[177] = createSerializationMatchTermSubtract(98, 1);
		// (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[178] = createSerializationMatchTermSubtract(100, 1);
		// (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[179] = createSerializationMatchTermGreaterThan(100, 0);
		// (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[180] = createSerializationMatchTermSubtract(101, 1);
		// (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[181] = createSerializationMatchTermSubtract(104, 1);
		// (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[182] = createSerializationMatchTermSubtract(105, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] =
			new SerializationRule("FirstPathElementCS", 30,
				createSerializationMatchSteps(
					38		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					188		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
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
			new SerializationRule("MultiplicityBoundsCS", 49,
				createSerializationMatchSteps(
					73		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					25		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					1		/* MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space */,
					156		/* V00*4-steps || value */,
					145		/* 1*1-steps || value */,
					100		/* '..' || no-space value no-space */,
					145		/* 1*1-steps || value */,
					93		/* MultiplicityBoundsCS::upperBound=105 || soft-space value soft-space */
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
			new SerializationRule("MultiplicityCS", 50,
				createSerializationMatchSteps(
					73		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					25		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					115		/* '[' || no-space value no-space */,
					152		/* 1*7-steps || value */,
					145		/* 1*1-steps || value */,
					1		/* MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space */,
					156		/* V00*4-steps || value */,
					145		/* 1*1-steps || value */,
					100		/* '..' || no-space value no-space */,
					145		/* 1*1-steps || value */,
					93		/* MultiplicityBoundsCS::upperBound=105 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					116		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 50,
				createSerializationMatchSteps(
					73		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					25		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					115		/* '[' || no-space value no-space */,
					152		/* 1*7-steps || value */,
					145		/* 1*1-steps || value */,
					1		/* MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space */,
					156		/* V00*4-steps || value */,
					145		/* 1*1-steps || value */,
					100		/* '..' || no-space value no-space */,
					145		/* 1*1-steps || value */,
					93		/* MultiplicityBoundsCS::upperBound=105 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					143		/* '|?' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					116		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 50,
				createSerializationMatchSteps(
					105		/* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
					73		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					25		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					115		/* '[' || no-space value no-space */,
					152		/* 1*7-steps || value */,
					145		/* 1*1-steps || value */,
					1		/* MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space */,
					156		/* V00*4-steps || value */,
					145		/* 1*1-steps || value */,
					100		/* '..' || no-space value no-space */,
					145		/* 1*1-steps || value */,
					93		/* MultiplicityBoundsCS::upperBound=105 || soft-space value soft-space */,
					160		/* V01*1-steps || value */,
					142		/* '|1' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					116		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						10	/* '|1' */
					)
				},
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(10 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
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
			new SerializationRule("MultiplicityCS", 50,
				createSerializationMatchSteps(
					26		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					115		/* '[' || no-space value no-space */,
					145		/* 1*1-steps || value */,
					90		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					116		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 50,
				createSerializationMatchSteps(
					26		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					115		/* '[' || no-space value no-space */,
					145		/* 1*1-steps || value */,
					90		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					143		/* '|?' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					116		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 50,
				createSerializationMatchSteps(
					74		/* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
					26		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					115		/* '[' || no-space value no-space */,
					145		/* 1*1-steps || value */,
					90		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					153		/* V00*1-steps || value */,
					142		/* '|1' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					116		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						10	/* '|1' */
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
							createEnumerationValue_GrammarCardinality(10 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
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
			new SerializationRule("MultiplicityStringCS", 51,
				createSerializationMatchSteps(
					26		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					90		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
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
			new SerializationRule("NextPathElementCS", 61,
				createSerializationMatchSteps(
					38		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					187		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
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
			new SerializationRule("PathNameCS", 67,
				createSerializationMatchSteps(
					134		/* check-rule basecs::PathNameCS.ownedPathElements : 30|61 */,
					57		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					181		/* PathNameCS::ownedPathElements+=30 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					103		/* '::' || no-space value no-space */,
					182		/* PathNameCS::ownedPathElements+=61 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						20) /* FirstPathElementCS|NextPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateBindingCS(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] =
			new SerializationRule("TemplateBindingCS", 87,
				createSerializationMatchSteps(
					137		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 50 */,
					138		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 88 */,
					109		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
					60		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
				),
				createSerializationSteps(
					69		/* TemplateBindingCS::ownedSubstitutions+=88 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					69		/* TemplateBindingCS::ownedSubstitutions+=88 || value */,
					160		/* V01*1-steps || value */,
					40		/* TemplateBindingCS::ownedMultiplicity=50 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						36) /* TemplateParameterSubstitutionCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(88, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateParameterSubstitutionCS(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] =
			new SerializationRule("TemplateParameterSubstitutionCS", 88,
				createSerializationMatchSteps(
					139		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 101 */,
					46		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					4		/* TemplateParameterSubstitutionCS::ownedActualParameter=101 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						48) /* TypeRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Base::TypeParameterCS(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[13] =
			new SerializationRule("TypeParameterCS", 100,
				createSerializationMatchSteps(
					143		/* check-rule basecs::TypeParameterCS.ownedExtends : 102 */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */,
					64		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
					97		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					158		/* V00*7-steps || value */,
					145		/* 1*1-steps || value */,
					125		/* 'extends' || soft-space value soft-space */,
					24		/* TypeParameterCS::ownedExtends+=102 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					94		/* '&&' || soft-space value soft-space */,
					24		/* TypeParameterCS::ownedExtends+=102 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						49) /* TypedRefCS */
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
						new RuleIndex_GrammarCardinality(102, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] }
		serializationRules[14] =
			new SerializationRule("TypedTypeRefCS", 103,
				createSerializationMatchSteps(
					146		/* check-rule basecs::TypedTypeRefCS.ownedBinding : 87 */,
					147		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 67 */,
					87		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
					51		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					58		/* TypedTypeRefCS::ownedPathName=67 || value */,
					157		/* V00*5-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					5		/* TypedTypeRefCS::ownedBinding=87 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						35) /* TemplateBindingCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(87, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[15] =
			new SerializationRule("WildcardTypeRefCS", 114,
				createSerializationMatchSteps(
					148		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : 102 */,
					89		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					110		/* '?' || soft-space value soft-space */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					125		/* 'extends' || soft-space value soft-space */,
					25		/* WildcardTypeRefCS::ownedExtends=102 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						49) /* TypedRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(102, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// CompleteOCL::ClassifierContextDeclCS(completeoclcs::ClassifierContextDeclCS): { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { { "inv" ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } }
		serializationRules[16] =
			new SerializationRule("ClassifierContextDeclCS", 3,
				createSerializationMatchSteps(
					149		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16 */,
					150		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12 */,
					162		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
					141		/* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
					123		/* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
					118		/* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
					40		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
					100		/* assign V1 = |ClassifierContextDeclCS::selfName| */,
					84		/* assign V0 = |TemplateableElementCS::ownedSignature| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					118		/* 'context' || soft-space value soft-space */,
					153		/* V00*1-steps || value */,
					65		/* TemplateableElementCS::ownedSignature=89 || value */,
					160		/* V01*1-steps || value */,
					89		/* ClassifierContextDeclCS::selfName=112 || soft-space value soft-space */,
					56		/* PathNameDeclCS::ownedPathName=67 || value */,
					151		/* 1*6-steps || value */,
					164		/* V02*3-steps || value */,
					145		/* 1*1-steps || value */,
					130		/* 'inv' || soft-space value soft-space */,
					33		/* ClassifierContextDeclCS::ownedInvariants+=12 || value */,
					166		/* V03*1-steps || value */,
					16		/* ClassifierContextDeclCS::ownedDefinitions+=16 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
						6) /* DefCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
						3) /* ConstraintCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						37) /* TemplateSignatureCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ONE_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(89, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// CompleteOCL::ClassifierContextDeclCS(completeoclcs::ClassifierContextDeclCS): { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { { "inv" ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } }
		serializationRules[17] =
			new SerializationRule("ClassifierContextDeclCS", 3,
				createSerializationMatchSteps(
					149		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16 */,
					150		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12 */,
					162		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
					141		/* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
					123		/* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
					118		/* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
					40		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
					100		/* assign V1 = |ClassifierContextDeclCS::selfName| */,
					84		/* assign V0 = |TemplateableElementCS::ownedSignature| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					118		/* 'context' || soft-space value soft-space */,
					153		/* V00*1-steps || value */,
					65		/* TemplateableElementCS::ownedSignature=89 || value */,
					160		/* V01*1-steps || value */,
					89		/* ClassifierContextDeclCS::selfName=112 || soft-space value soft-space */,
					56		/* PathNameDeclCS::ownedPathName=67 || value */,
					151		/* 1*6-steps || value */,
					164		/* V02*3-steps || value */,
					145		/* 1*1-steps || value */,
					130		/* 'inv' || soft-space value soft-space */,
					33		/* ClassifierContextDeclCS::ownedInvariants+=12 || value */,
					166		/* V03*1-steps || value */,
					16		/* ClassifierContextDeclCS::ownedDefinitions+=16 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
						6) /* DefCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
						3) /* ConstraintCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						37) /* TemplateSignatureCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ONE_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(89, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// CompleteOCL::CompleteOCLDocumentCS(completeoclcs::CompleteOCLDocumentCS): { ownedImports+=ImportCS[*] { ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] } }
		serializationRules[18] =
			new SerializationRule("CompleteOCLDocumentCS", 10,
				createSerializationMatchSteps(
					151		/* check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : 13 */,
					136		/* check-rule basecs::RootCS.ownedImports : 35 */,
					152		/* check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : 65 */,
					119		/* assign V2 = |CompleteOCLDocumentCS::ownedContexts| */,
					102		/* assign V1 = |CompleteOCLDocumentCS::ownedPackages| */,
					82		/* assign V0 = |RootCS::ownedImports| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					153		/* V00*1-steps || value */,
					27		/* RootCS::ownedImports+=35 || value */,
					150		/* 1*4-steps || value */,
					160		/* V01*1-steps || value */,
					44		/* CompleteOCLDocumentCS::ownedPackages+=65 || value */,
					163		/* V02*1-steps || value */,
					11		/* CompleteOCLDocumentCS::ownedContexts+=13 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
						4) /* ContextDeclCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						12) /* ImportCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
						21) /* PackageDeclarationCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(35, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(65, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// CompleteOCL::ConstraintCS(basecs::ConstraintCS): { { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS }
		serializationRules[19] =
			new SerializationRule("ConstraintCS", 12,
				createSerializationMatchSteps(
					130		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : 83 */,
					131		/* check-rule basecs::ConstraintCS.ownedSpecification : 83 */,
					6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
					75		/* assign V0 = |NamedElementCS::name| */,
					103		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					159		/* V00*8-steps || value */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					162		/* V01*5-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					39		/* ConstraintCS::ownedMessageSpecification=83 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					66		/* ConstraintCS::ownedSpecification=83 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						32) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						32) /* SpecificationCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ONE)
						}
					)
				});
			;
		// CompleteOCL::DefOperationCS(completeoclcs::DefOperationCS): { isStatic="static"[?] "def" ":" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=DefParameterCS { "," ownedParameters+=DefParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] "=" ownedSpecification=SpecificationCS }
		serializationRules[20] =
			new SerializationRule("DefOperationCS", 17,
				createSerializationMatchSteps(
					154		/* check-rule completeoclcs::DefOperationCS.ownedParameters : 18 */,
					141		/* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
					153		/* check-rule completeoclcs::DefCS.ownedSpecification : 83 */,
					144		/* check-rule basecs::TypedElementCS.ownedType : 94 */,
					8		/* assert (|DefCS::ownedSpecification| - 1) == 0 */,
					127		/* assign V4 = |TypedElementCS::ownedType| */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */,
					110		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					69		/* assign V0 = |DefCS::isStatic.'static'| */,
					114		/* assign V2 = (|DefOperationCS::ownedParameters| > 0) */,
					121		/* assign V3 = (|DefOperationCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					153		/* V00*1-steps || value */,
					138		/* 'static' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					119		/* 'def' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					160		/* V01*1-steps || value */,
					65		/* TemplateableElementCS::ownedSignature=89 || value */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					165		/* V02*5-steps || value */,
					45		/* DefOperationCS::ownedParameters+=18 || value */,
					167		/* V03*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					45		/* DefOperationCS::ownedParameters+=18 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					169		/* V04*1-steps || value */,
					80		/* TypedElementCS::ownedType=94 || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					67		/* DefCS::ownedSpecification=83 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
						8	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS,
						7) /* DefParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						37) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
						32) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						40) /* TypeExpCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(8 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(18, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(89, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// CompleteOCL::DefParameterCS(basecs::ParameterCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[21] =
			new SerializationRule("DefParameterCS", 18,
				createSerializationMatchSteps(
					144		/* check-rule basecs::TypedElementCS.ownedType : 94 */,
					50		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					80		/* TypedElementCS::ownedType=94 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						40) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
						}
					)
				});
			;
		// CompleteOCL::DefPropertyCS(completeoclcs::DefPropertyCS): { isStatic="static"[?] "def" ":" name=UnrestrictedName ":" ownedType=TypeExpCS "=" ownedSpecification=SpecificationCS }
		serializationRules[22] =
			new SerializationRule("DefPropertyCS", 19,
				createSerializationMatchSteps(
					153		/* check-rule completeoclcs::DefCS.ownedSpecification : 83 */,
					144		/* check-rule basecs::TypedElementCS.ownedType : 94 */,
					8		/* assert (|DefCS::ownedSpecification| - 1) == 0 */,
					50		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */,
					69		/* assign V0 = |DefCS::isStatic.'static'| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					153		/* V00*1-steps || value */,
					138		/* 'static' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					119		/* 'def' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					80		/* TypedElementCS::ownedType=94 || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					67		/* DefCS::ownedSpecification=83 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
						8	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
						32) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						40) /* TypeExpCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(8 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
						}
					)
				});
			;
		// CompleteOCL::ImportCS(basecs::ImportCS): { {"import"|"include"|"library"} { name=Identifier ":" }[?] ownedPathName=URIPathNameCS isAll="::*"[?] }
		serializationRules[23] =
			new SerializationRule("ImportCS", 35,
				createSerializationMatchSteps(
					132		/* check-rule basecs::ImportCS.ownedPathName : 108 */,
					104		/* assign V1 = |ImportCS::isAll.'::*'| */,
					16		/* assert (|ImportCS::ownedPathName| - 1) == 0 */,
					75		/* assign V0 = |NamedElementCS::name| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					127		/* 'import' || value */,
					156		/* V00*4-steps || value */,
					145		/* 1*1-steps || value */,
					177		/* NamedElementCS::name=33 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					55		/* ImportCS::ownedPathName=108 || value */,
					160		/* V01*1-steps || value */,
					102		/* '::*' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
						2	/* '::*' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						53) /* URIPathNameCS */
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
						new RuleIndex_GrammarCardinality(108, GrammarCardinality.ONE)
						}
					)
				});
			;
		// CompleteOCL::NavigatingArgExpCS(essentialoclcs::ExpCS): "?"
		serializationRules[24] =
			new SerializationRule("NavigatingArgExpCS", 55,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					109		/* '?' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// CompleteOCL::OperationContextDeclCS(completeoclcs::OperationContextDeclCS): { "context" ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] { { "pre" ownedPreconditions+=ConstraintCS }[*] { "post" ownedPostconditions+=ConstraintCS }[*] { "body" ":" ownedBodies+=SpecificationCS }[*] } }
		serializationRules[25] =
			new SerializationRule("OperationContextDeclCS", 64,
				createSerializationMatchSteps(
					156		/* check-rule completeoclcs::OperationContextDeclCS.ownedBodies : 83 */,
					157		/* check-rule completeoclcs::OperationContextDeclCS.ownedParameters : 66 */,
					162		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
					158		/* check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : 12 */,
					159		/* check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : 12 */,
					141		/* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
					155		/* check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94 */,
					129		/* assign V6 = |OperationContextDeclCS::ownedBodies| */,
					128		/* assign V5 = |OperationContextDeclCS::ownedPostconditions| */,
					126		/* assign V4 = |OperationContextDeclCS::ownedPreconditions| */,
					124		/* assign V3 = |FeatureContextDeclCS::ownedType| */,
					40		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
					84		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					94		/* assign V1 = (|OperationContextDeclCS::ownedParameters| > 0) */,
					115		/* assign V2 = (|OperationContextDeclCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					118		/* 'context' || soft-space value soft-space */,
					153		/* V00*1-steps || value */,
					65		/* TemplateableElementCS::ownedSignature=89 || value */,
					56		/* PathNameDeclCS::ownedPathName=67 || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					162		/* V01*5-steps || value */,
					46		/* OperationContextDeclCS::ownedParameters+=66 || value */,
					164		/* V02*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					46		/* OperationContextDeclCS::ownedParameters+=66 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					166		/* V03*1-steps || value */,
					76		/* FeatureContextDeclCS::ownedType=94 || value */,
					148		/* 1*14-steps || value */,
					170		/* V04*3-steps || value */,
					145		/* 1*1-steps || value */,
					136		/* 'pre' || soft-space value soft-space */,
					62		/* OperationContextDeclCS::ownedPreconditions+=12 || value */,
					171		/* V05*3-steps || value */,
					145		/* 1*1-steps || value */,
					135		/* 'post' || soft-space value soft-space */,
					61		/* OperationContextDeclCS::ownedPostconditions+=12 || value */,
					172		/* V06*5-steps || value */,
					145		/* 1*1-steps || value */,
					117		/* 'body' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					6		/* OperationContextDeclCS::ownedBodies+=83 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
						32) /* SpecificationCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
						22) /* ParameterCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
						3) /* ConstraintCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
						3) /* ConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						37) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
						40) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(66, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(89, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// CompleteOCL::PackageDeclarationCS(completeoclcs::PackageDeclarationCS): { "package" ownedPathName=PathNameCS { "inv" ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] "endpackage" }
		serializationRules[26] =
			new SerializationRule("PackageDeclarationCS", 65,
				createSerializationMatchSteps(
					160		/* check-rule completeoclcs::PackageDeclarationCS.ownedContexts : 13 */,
					161		/* check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : 12 */,
					162		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
					108		/* assign V1 = |PackageDeclarationCS::ownedContexts| */,
					79		/* assign V0 = |PackageDeclarationCS::ownedInvariants| */,
					40		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					134		/* 'package' || soft-space value soft-space */,
					56		/* PathNameDeclCS::ownedPathName=67 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					130		/* 'inv' || soft-space value soft-space */,
					34		/* PackageDeclarationCS::ownedInvariants+=12 || value */,
					160		/* V01*1-steps || value */,
					12		/* PackageDeclarationCS::ownedContexts+=13 || value */,
					145		/* 1*1-steps || value */,
					124		/* 'endpackage' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
						4) /* ContextDeclCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
						3) /* ConstraintCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					)
				});
			;
		// CompleteOCL::ParameterCS(basecs::ParameterCS): { { name=UnrestrictedName ":" }[?] ownedType=TypeExpCS }
		serializationRules[27] =
			new SerializationRule("ParameterCS", 66,
				createSerializationMatchSteps(
					144		/* check-rule basecs::TypedElementCS.ownedType : 94 */,
					50		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					75		/* assign V0 = |NamedElementCS::name| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					156		/* V00*4-steps || value */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					80		/* TypedElementCS::ownedType=94 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						40) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
						}
					)
				});
			;
		// CompleteOCL::PropertyContextDeclCS(completeoclcs::PropertyContextDeclCS): { "context" ownedPathName=PathNameCS ":" ownedType=TypeExpCS { { "derive" ":" ownedDefaultExpressions+=SpecificationCS }[*] { "init" ":" ownedDefaultExpressions+=SpecificationCS }[*] } }
		serializationRules[28] =
			new SerializationRule("PropertyContextDeclCS", 75,
				createSerializationMatchSteps(
					163		/* check-rule completeoclcs::PropertyContextDeclCS.ownedDefaultExpressions : 83 */,
					162		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
					155		/* check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94 */,
					10		/* assert (|FeatureContextDeclCS::ownedType| - 1) == 0 */,
					40		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
					81		/* assign V0 = |PropertyContextDeclCS::ownedDefaultExpressions| */,
					98		/* assign V1 = 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					118		/* 'context' || soft-space value soft-space */,
					56		/* PathNameDeclCS::ownedPathName=67 || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					76		/* FeatureContextDeclCS::ownedType=94 || value */,
					147		/* 1*12-steps || value */,
					157		/* V00*5-steps || value */,
					145		/* 1*1-steps || value */,
					120		/* 'derive' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					15		/* PropertyContextDeclCS::ownedDefaultExpressions+=83 || value */,
					162		/* V01*5-steps || value */,
					145		/* 1*1-steps || value */,
					129		/* 'init' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					15		/* PropertyContextDeclCS::ownedDefaultExpressions+=83 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
						32) /* SpecificationCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */,
					createEReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
						40) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(83, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
						}
					)
				});
			;
		// CompleteOCL::SpecificationCS(essentialoclcs::ExpSpecificationCS): exprString=UNQUOTED_STRING
		serializationRules[29] =
			new SerializationRule("SpecificationCS", 83,
				createSerializationMatchSteps(
					45		/* assert (|SpecificationCS::exprString| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					0		/* SpecificationCS::exprString=104 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// CompleteOCL::SpecificationCS(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS
		serializationRules[30] =
			new SerializationRule("SpecificationCS", 83,
				createSerializationMatchSteps(
					179		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 29 */,
					9		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					21		/* ExpSpecificationCS::ownedExpression=29 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// CompleteOCL::TemplateSignatureCS(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[31] =
			new SerializationRule("TemplateSignatureCS", 89,
				createSerializationMatchSteps(
					140		/* check-rule basecs::TemplateSignatureCS.ownedParameters : 100 */,
					61		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					47		/* TemplateSignatureCS::ownedParameters+=100 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					47		/* TemplateSignatureCS::ownedParameters+=100 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						47) /* TypeParameterCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(100, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// CompleteOCL::TemplateSignatureCS(basecs::TemplateSignatureCS): { "<" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ">" }
		serializationRules[32] =
			new SerializationRule("TemplateSignatureCS", 89,
				createSerializationMatchSteps(
					140		/* check-rule basecs::TemplateSignatureCS.ownedParameters : 100 */,
					61		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					106		/* '<' || soft-space value soft-space */,
					47		/* TemplateSignatureCS::ownedParameters+=100 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					47		/* TemplateSignatureCS::ownedParameters+=100 || value */,
					145		/* 1*1-steps || value */,
					108		/* '>' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						47) /* TypeParameterCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(100, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[33] =
			new SerializationRule("BooleanLiteralExpCS", 2,
				createSerializationMatchSteps(
					1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					91		/* BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						7	/* 'false|true' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(7 /* 'false|true' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::CoIteratorVariableCS(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[34] =
			new SerializationRule("CoIteratorVariableCS", 4,
				createSerializationMatchSteps(
					216		/* check-rule essentialoclcs::VariableCS.ownedType : 94 */,
					88		/* assign V0 = |VariableCS::ownedType| */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					81		/* VariableCS::ownedType=94 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						40) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[35] =
			new SerializationRule("CollectionLiteralExpCS", 5,
				createSerializationMatchSteps(
					168		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6 */,
					169		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8 */,
					2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
					53		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
					90		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					73		/* CollectionLiteralExpCS::ownedType=8 || value */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					157		/* V00*5-steps || value */,
					48		/* CollectionLiteralExpCS::ownedParts+=6 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					48		/* CollectionLiteralExpCS::ownedParts+=6 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
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
						new RuleIndex_GrammarCardinality(6, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
		serializationRules[36] =
			new SerializationRule("CollectionLiteralPartCS", 6,
				createSerializationMatchSteps(
					171		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 68 */,
					3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					19		/* CollectionLiteralPartCS::ownedExpression=68 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						24) /* PatternExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] }
		serializationRules[37] =
			new SerializationRule("CollectionLiteralPartCS", 6,
				createSerializationMatchSteps(
					170		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 29 */,
					172		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 29 */,
					66		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
					3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					18		/* CollectionLiteralPartCS::ownedExpression=29 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					100		/* '..' || no-space value no-space */,
					37		/* CollectionLiteralPartCS::ownedLastExpression=29 || value */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" }
		serializationRules[38] =
			new SerializationRule("CollectionPatternCS", 7,
				createSerializationMatchSteps(
					173		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68 */,
					174		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : 8 */,
					67		/* assign V0 = |CollectionPatternCS::restVariableName| */,
					91		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
					4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					74		/* CollectionPatternCS::ownedType=8 || value */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					154		/* V00*10-steps || value */,
					49		/* CollectionPatternCS::ownedParts+=68 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					49		/* CollectionPatternCS::ownedParts+=68 || value */,
					150		/* 1*4-steps || value */,
					145		/* 1*1-steps || value */,
					98		/* '++' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					87		/* CollectionPatternCS::restVariableName=33 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						24) /* PatternExpCS */,
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
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] }
		serializationRules[39] =
			new SerializationRule("CollectionTypeCS", 8,
				createSerializationMatchSteps(
					175		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50 */,
					176		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 95 */,
					68		/* assign V0 = |CollectionTypeCS::ownedType| */,
					5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					101		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					2		/* CollectionTypeCS::name=9 || soft-space value soft-space */,
					158		/* V00*7-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					75		/* CollectionTypeCS::ownedType=95 || value */,
					160		/* V01*1-steps || value */,
					8		/* CollectionTypeCS::ownedCollectionMultiplicity=50 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						41) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(95, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CurlyBracketedClauseCS(essentialoclcs::CurlyBracketedClauseCS): { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" }
		serializationRules[40] =
			new SerializationRule("CurlyBracketedClauseCS", 14,
				createSerializationMatchSteps(
					178		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 81 */,
					54		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
					92		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					157		/* V00*5-steps || value */,
					50		/* CurlyBracketedClauseCS::ownedParts+=81 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					50		/* CurlyBracketedClauseCS::ownedParts+=81 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						31) /* ShadowPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(81, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ElseIfThenExpCS(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[41] =
			new SerializationRule("ElseIfThenExpCS", 22,
				createSerializationMatchSteps(
					184		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 29 */,
					185		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 29 */,
					15		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
					14		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					122		/* 'elseif' || soft-space value soft-space */,
					10		/* IfThenExpCS::ownedCondition=29 || value */,
					145		/* 1*1-steps || value */,
					139		/* 'then' || soft-space value soft-space */,
					72		/* IfThenExpCS::ownedThenExpression=29 || value */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[42] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					91		/* BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						7	/* 'false|true' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(7 /* 'false|true' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[43] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					168		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6 */,
					169		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8 */,
					2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
					53		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
					90		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					73		/* CollectionLiteralExpCS::ownedType=8 || value */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					157		/* V00*5-steps || value */,
					48		/* CollectionLiteralExpCS::ownedParts+=6 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					48		/* CollectionLiteralExpCS::ownedParts+=6 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
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
						new RuleIndex_GrammarCardinality(6, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "*"
		serializationRules[44] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					97		/* '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "invalid"
		serializationRules[45] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					131		/* 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "null"
		serializationRules[46] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					133		/* 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "self"
		serializationRules[47] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					137		/* 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[48] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					180		/* check-rule essentialoclcs::IfExpCS.ownedCondition : 29|68 */,
					181		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 29 */,
					182		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22 */,
					183		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 29 */,
					12		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
					70		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
					13		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
					11		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					126		/* 'if' || soft-space value soft-space */,
					9		/* IfExpCS::ownedCondition=29|68 || value */,
					145		/* 1*1-steps || value */,
					139		/* 'then' || soft-space value soft-space */,
					71		/* IfExpCS::ownedThenExpression=29 || value */,
					153		/* V00*1-steps || value */,
					26		/* IfExpCS::ownedIfThenExpressions+=22 || value */,
					145		/* 1*1-steps || value */,
					121		/* 'else' || soft-space value soft-space */,
					17		/* IfExpCS::ownedElseExpression=29 || value */,
					145		/* 1*1-steps || value */,
					123		/* 'endif' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						25) /* ExpCS|PatternExpCS */,
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } }
		serializationRules[49] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					186		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : 70 */,
					202		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 29 */,
					37		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */,
					17		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					38		/* InfixExpCS::ownedLeft=70 || value */,
					149		/* 1*3-steps || value */,
					145		/* 1*1-steps || value */,
					174		/* NamedElementCS::name=1 || soft-space value soft-space */,
					183		/* OperatorExpCS::ownedRight=29 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						28) /* PrefixedPrimaryExpCS */,
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
						new RuleIndex_GrammarCardinality(70, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[50] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					187		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 29 */,
					18		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					112		/* 'Lambda' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					23		/* LambdaLiteralExpCS::ownedExpressionCS=29 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[51] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					191		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46 */,
					192		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47 */,
					20		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
					56		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
					93		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					77		/* MapLiteralExpCS::ownedType=47 || value */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					157		/* V00*5-steps || value */,
					51		/* MapLiteralExpCS::ownedParts+=46 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					51		/* MapLiteralExpCS::ownedParts+=46 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						14) /* MapLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						15) /* MapTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(46, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(47, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] }
		serializationRules[52] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					164		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
					165		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67 */,
					166		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76 */,
					167		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84 */,
					122		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
					117		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
					99		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
					65		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
					0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					54		/* AbstractNameExpCS::ownedPathName=67 || value */,
					153		/* V00*1-steps || value */,
					68		/* AbstractNameExpCS::ownedSquareBracketedClauses+=84 || value */,
					160		/* V01*1-steps || value */,
					63		/* AbstractNameExpCS::ownedRoundBracketedClause=76 || value */,
					163		/* V02*1-steps || value */,
					13		/* AbstractNameExpCS::ownedCurlyBracketedClause=14 || value */,
					168		/* V03*4-steps || value */,
					145		/* 1*1-steps || value */,
					111		/* '@' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					136		/* 'pre' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						4	/* '@' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						5) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						30) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						33) /* SquareBracketedClauseCS */
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
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[53] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					201		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : 29 */,
					35		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					22		/* NestedExpCS::ownedExpression=29 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[54] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					36		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					92		/* NumberLiteralExpCS::symbol=52 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[55] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					204		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 70 */,
					37		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					175		/* NamedElementCS::name=109 || soft-space value soft-space */,
					185		/* OperatorExpCS::ownedRight=70 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						28) /* PrefixedPrimaryExpCS */
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
						new RuleIndex_GrammarCardinality(70, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[56] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					83		/* assign V0 = |StringLiteralExpCS::segments| */
				),
				createSerializationSteps(
					153		/* V00*1-steps || value */,
					88		/* StringLiteralExpCS::segments+=85 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[57] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					210		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91 */,
					62		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					114		/* 'Tuple' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					52		/* TupleLiteralExpCS::ownedParts+=91 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					52		/* TupleLiteralExpCS::ownedParts+=91 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						38) /* TupleLiteralPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(91, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[58] =
			new SerializationRule("ExpCS", 29,
				createSerializationMatchSteps(
					211		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98 */,
					48		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					79		/* TypeLiteralExpCS::ownedType=98 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						43) /* TypeLiteralWithMultiplicityCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(98, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[59] =
			new SerializationRule("IfExpCS", 34,
				createSerializationMatchSteps(
					180		/* check-rule essentialoclcs::IfExpCS.ownedCondition : 29|68 */,
					181		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 29 */,
					182		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22 */,
					183		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 29 */,
					12		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
					70		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
					13		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
					11		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					126		/* 'if' || soft-space value soft-space */,
					9		/* IfExpCS::ownedCondition=29|68 || value */,
					145		/* 1*1-steps || value */,
					139		/* 'then' || soft-space value soft-space */,
					71		/* IfExpCS::ownedThenExpression=29 || value */,
					153		/* V00*1-steps || value */,
					26		/* IfExpCS::ownedIfThenExpressions+=22 || value */,
					145		/* 1*1-steps || value */,
					121		/* 'else' || soft-space value soft-space */,
					17		/* IfExpCS::ownedElseExpression=29 || value */,
					145		/* 1*1-steps || value */,
					123		/* 'endif' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						25) /* ExpCS|PatternExpCS */,
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): "invalid"
		serializationRules[60] =
			new SerializationRule("InvalidLiteralExpCS", 37,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					131		/* 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[61] =
			new SerializationRule("LambdaLiteralExpCS", 40,
				createSerializationMatchSteps(
					187		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 29 */,
					18		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					112		/* 'Lambda' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					23		/* LambdaLiteralExpCS::ownedExpressionCS=29 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[62] =
			new SerializationRule("LetExpCS", 41,
				createSerializationMatchSteps(
					188		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : 29 */,
					189		/* check-rule essentialoclcs::LetExpCS.ownedVariables : 42 */,
					19		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
					55		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					132		/* 'let' || soft-space value soft-space */,
					84		/* LetExpCS::ownedVariables+=42 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					84		/* LetExpCS::ownedVariables+=42 || value */,
					145		/* 1*1-steps || value */,
					128		/* 'in' || soft-space value soft-space */,
					28		/* LetExpCS::ownedInExpression=29 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						13) /* LetVariableCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(42, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::LetVariableCS(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[63] =
			new SerializationRule("LetVariableCS", 42,
				createSerializationMatchSteps(
					215		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : 29 */,
					190		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 76 */,
					216		/* check-rule essentialoclcs::VariableCS.ownedType : 94 */,
					52		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
					113		/* assign V1 = |VariableCS::ownedType| */,
					71		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					153		/* V00*1-steps || value */,
					64		/* LetVariableCS::ownedRoundBracketedClause=76 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					81		/* VariableCS::ownedType=94 || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					32		/* VariableCS::ownedInitExpression=29 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						30) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						40) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
	}
	private void initSerializationRules1() {
		// EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[64] =
			new SerializationRule("MapLiteralExpCS", 45,
				createSerializationMatchSteps(
					191		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46 */,
					192		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47 */,
					20		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
					56		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
					93		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					77		/* MapLiteralExpCS::ownedType=47 || value */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					157		/* V00*5-steps || value */,
					51		/* MapLiteralExpCS::ownedParts+=46 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					51		/* MapLiteralExpCS::ownedParts+=46 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						14) /* MapLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						15) /* MapTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(46, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(47, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::MapLiteralPartCS(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS "<-" ownedValue=ExpCS }
		serializationRules[65] =
			new SerializationRule("MapLiteralPartCS", 46,
				createSerializationMatchSteps(
					193		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 29 */,
					194		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 29 */,
					22		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
					21		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					35		/* MapLiteralPartCS::ownedKey=29 || value */,
					145		/* 1*1-steps || value */,
					105		/* '<-' || soft-space value soft-space */,
					82		/* MapLiteralPartCS::ownedValue=29 || value */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] }
		serializationRules[66] =
			new SerializationRule("MapTypeCS", 47,
				createSerializationMatchSteps(
					195		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94 */,
					196		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 94 */,
					72		/* assign V0 = |MapTypeCS::ownedValueType| */,
					24		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					23		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					113		/* 'Map' || soft-space value soft-space */,
					159		/* V00*8-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					36		/* MapTypeCS::ownedKeyType=94 || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					83		/* MapTypeCS::ownedValueType=94 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						5	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						40) /* TypeExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						40) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::Model(essentialoclcs::ContextCS): ownedExpression=ExpCS
		serializationRules[67] =
			new SerializationRule("Model", 48,
				createSerializationMatchSteps(
					177		/* check-rule essentialoclcs::ContextCS.ownedExpression : 29 */,
					7		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					20		/* ContextCS::ownedExpression=29 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] }
		serializationRules[68] =
			new SerializationRule("NameExpCS", 53,
				createSerializationMatchSteps(
					164		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
					165		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67 */,
					166		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76 */,
					167		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84 */,
					122		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
					117		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
					99		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
					65		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
					0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					54		/* AbstractNameExpCS::ownedPathName=67 || value */,
					153		/* V00*1-steps || value */,
					68		/* AbstractNameExpCS::ownedSquareBracketedClauses+=84 || value */,
					160		/* V01*1-steps || value */,
					63		/* AbstractNameExpCS::ownedRoundBracketedClause=76 || value */,
					163		/* V02*1-steps || value */,
					13		/* AbstractNameExpCS::ownedCurlyBracketedClause=14 || value */,
					168		/* V03*4-steps || value */,
					145		/* 1*1-steps || value */,
					111		/* '@' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					136		/* 'pre' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						4	/* '@' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						5) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						30) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						33) /* SquareBracketedClauseCS */
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
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
		serializationRules[69] =
			new SerializationRule("NavigatingArgCS", 54,
				createSerializationMatchSteps(
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					43		/* NavigatingArgCS::ownedNameExpression=55 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						17) /* NavigatingArgExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ":" ownedType=TypeExpCS }
		serializationRules[70] =
			new SerializationRule("NavigatingArgCS", 54,
				createSerializationMatchSteps(
					200		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
					31		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					78		/* NavigatingArgCS::ownedType=94 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						40) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[71] =
			new SerializationRule("NavigatingArgCS", 54,
				createSerializationMatchSteps(
					197		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
					198		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					200		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
					107		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
					76		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
					31		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					42		/* NavigatingArgCS::ownedNameExpression=55 || value */,
					146		/* 1*11-steps || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					78		/* NavigatingArgCS::ownedType=94 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					105		/* '<-' || soft-space value soft-space */,
					7		/* NavigatingArgCS::ownedCoIterator=4 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					29		/* NavigatingArgCS::ownedInitExpression=29 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						0) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						17) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						40) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(4, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[72] =
			new SerializationRule("NavigatingArgCS", 54,
				createSerializationMatchSteps(
					197		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
					198		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					77		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
					28		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					42		/* NavigatingArgCS::ownedNameExpression=55 || value */,
					152		/* 1*7-steps || value */,
					145		/* 1*1-steps || value */,
					105		/* '<-' || soft-space value soft-space */,
					7		/* NavigatingArgCS::ownedCoIterator=4 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					29		/* NavigatingArgCS::ownedInitExpression=29 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						0) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						17) /* NavigatingArgExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(4, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[73] =
			new SerializationRule("NavigatingArgCS", 54,
				createSerializationMatchSteps(
					197		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
					198		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					200		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
					29		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
					106		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
					78		/* assign V0 = |NavigatingArgCS::ownedType| */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					42		/* NavigatingArgCS::ownedNameExpression=55 || value */,
					146		/* 1*11-steps || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					78		/* NavigatingArgCS::ownedType=94 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					105		/* '<-' || soft-space value soft-space */,
					7		/* NavigatingArgCS::ownedCoIterator=4 || value */,
					145		/* 1*1-steps || value */,
					128		/* 'in' || soft-space value soft-space */,
					29		/* NavigatingArgCS::ownedInitExpression=29 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						0) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						17) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						40) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(4, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingBarArgCS(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[74] =
			new SerializationRule("NavigatingBarArgCS", 56,
				createSerializationMatchSteps(
					198		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					200		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
					78		/* assign V0 = |NavigatingArgCS::ownedType| */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					34		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
					107		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					141		/* '|' || soft-space value soft-space */,
					42		/* NavigatingArgCS::ownedNameExpression=55 || value */,
					158		/* V00*7-steps || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					78		/* NavigatingArgCS::ownedType=94 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					29		/* NavigatingArgCS::ownedInitExpression=29 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						9	/* '|' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						17) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						40) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(9 /* '|' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[75] =
			new SerializationRule("NavigatingCommaArgCS", 57,
				createSerializationMatchSteps(
					197		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
					198		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					200		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
					107		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
					76		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
					31		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					32		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					42		/* NavigatingArgCS::ownedNameExpression=55 || value */,
					146		/* 1*11-steps || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					78		/* NavigatingArgCS::ownedType=94 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					105		/* '<-' || soft-space value soft-space */,
					7		/* NavigatingArgCS::ownedCoIterator=4 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					29		/* NavigatingArgCS::ownedInitExpression=29 || value */
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
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						17) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						40) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(4, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[76] =
			new SerializationRule("NavigatingCommaArgCS", 57,
				createSerializationMatchSteps(
					197		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
					198		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					77		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
					28		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					32		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					42		/* NavigatingArgCS::ownedNameExpression=55 || value */,
					152		/* 1*7-steps || value */,
					145		/* 1*1-steps || value */,
					105		/* '<-' || soft-space value soft-space */,
					7		/* NavigatingArgCS::ownedCoIterator=4 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					29		/* NavigatingArgCS::ownedInitExpression=29 || value */
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
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						17) /* NavigatingArgExpCS */
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
						new RuleIndex_GrammarCardinality(4, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[77] =
			new SerializationRule("NavigatingCommaArgCS", 57,
				createSerializationMatchSteps(
					197		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
					198		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					200		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
					29		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
					106		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
					78		/* assign V0 = |NavigatingArgCS::ownedType| */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					32		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					42		/* NavigatingArgCS::ownedNameExpression=55 || value */,
					146		/* 1*11-steps || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					78		/* NavigatingArgCS::ownedType=94 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					105		/* '<-' || soft-space value soft-space */,
					7		/* NavigatingArgCS::ownedCoIterator=4 || value */,
					145		/* 1*1-steps || value */,
					128		/* 'in' || soft-space value soft-space */,
					29		/* NavigatingArgCS::ownedInitExpression=29 || value */
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
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						17) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						40) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(4, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS }
		serializationRules[78] =
			new SerializationRule("NavigatingCommaArgCS", 57,
				createSerializationMatchSteps(
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					32		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					42		/* NavigatingArgCS::ownedNameExpression=55 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						1	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						17) /* NavigatingArgExpCS */
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
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingSemiArgCS(essentialoclcs::NavigatingArgCS): { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[79] =
			new SerializationRule("NavigatingSemiArgCS", 58,
				createSerializationMatchSteps(
					198		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
					199		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
					200		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
					78		/* assign V0 = |NavigatingArgCS::ownedType| */,
					30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					33		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
					107		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					104		/* ';' || no-space value soft-new-line */,
					42		/* NavigatingArgCS::ownedNameExpression=55 || value */,
					158		/* V00*7-steps || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					78		/* NavigatingArgCS::ownedType=94 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					29		/* NavigatingArgCS::ownedInitExpression=29 || value */
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
						17) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						40) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[80] =
			new SerializationRule("NestedExpCS", 60,
				createSerializationMatchSteps(
					201		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : 29 */,
					35		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					22		/* NestedExpCS::ownedExpression=29 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[81] =
			new SerializationRule("NullLiteralExpCS", 62,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					133		/* 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[82] =
			new SerializationRule("NumberLiteralExpCS", 63,
				createSerializationMatchSteps(
					36		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					92		/* NumberLiteralExpCS::symbol=52 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[83] =
			new SerializationRule("PatternExpCS", 68,
				createSerializationMatchSteps(
					205		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 94 */,
					41		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
					80		/* assign V0 = |PatternExpCS::patternVariableName| */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					153		/* V00*1-steps || value */,
					85		/* PatternExpCS::patternVariableName=112 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					60		/* PatternExpCS::ownedPatternType=94 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						40) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		serializationRules[84] =
			new SerializationRule("PrefixedLetExpCS", 69,
				createSerializationMatchSteps(
					203		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 69 */,
					37		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					175		/* NamedElementCS::name=109 || soft-space value soft-space */,
					184		/* OperatorExpCS::ownedRight=69 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						26) /* PrefixedLetExpCS */
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
						new RuleIndex_GrammarCardinality(69, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrefixedPrimaryExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[85] =
			new SerializationRule("PrefixedPrimaryExpCS", 70,
				createSerializationMatchSteps(
					204		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 70 */,
					37		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					175		/* NamedElementCS::name=109 || soft-space value soft-space */,
					185		/* OperatorExpCS::ownedRight=70 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						28) /* PrefixedPrimaryExpCS */
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
						new RuleIndex_GrammarCardinality(70, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		serializationRules[86] =
			new SerializationRule("PrimitiveTypeCS", 73,
				createSerializationMatchSteps(
					42		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					3		/* PrimitiveTypeRefCS::name=74 || soft-space value soft-space */
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
		serializationRules[87] =
			new SerializationRule("RoundBracketedClauseCS", 76,
				createSerializationMatchSteps(
					206		/* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 54|56|57|58 */,
					58		/* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
					95		/* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					155		/* V00*3-steps || value */,
					178		/* RoundBracketedClauseCS::ownedArguments+=54 || value */,
					160		/* V01*1-steps || value */,
					179		/* RoundBracketedClauseCS::ownedArguments+=57|58|56 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						19) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(54, GrammarCardinality.ZERO_OR_ONE),
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(57, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(58, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): "self"
		serializationRules[88] =
			new SerializationRule("SelfExpCS", 80,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					137		/* 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
		serializationRules[89] =
			new SerializationRule("ShadowPartCS", 81,
				createSerializationMatchSteps(
					208		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 86 */,
					43		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					31		/* ShadowPartCS::ownedInitExpression=86 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						34) /* StringLiteralExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(86, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[90] =
			new SerializationRule("ShadowPartCS", 81,
				createSerializationMatchSteps(
					207		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 29|68 */,
					43		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
					44		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					86		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					30		/* ShadowPartCS::ownedInitExpression=29|68 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						25) /* ExpCS|PatternExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// EssentialOCL::SimplePathNameCS(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[91] =
			new SerializationRule("SimplePathNameCS", 82,
				createSerializationMatchSteps(
					133		/* check-rule basecs::PathNameCS.ownedPathElements : 30 */,
					39		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
				),
				createSerializationSteps(
					181		/* PathNameCS::ownedPathElements+=30 || value */
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
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::SquareBracketedClauseCS(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[92] =
			new SerializationRule("SquareBracketedClauseCS", 84,
				createSerializationMatchSteps(
					209		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 29 */,
					59		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					115		/* '[' || no-space value no-space */,
					70		/* SquareBracketedClauseCS::ownedTerms+=29 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					70		/* SquareBracketedClauseCS::ownedTerms+=29 || value */,
					145		/* 1*1-steps || value */,
					116		/* ']' || no-space value */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[93] =
			new SerializationRule("StringLiteralExpCS", 86,
				createSerializationMatchSteps(
					83		/* assign V0 = |StringLiteralExpCS::segments| */
				),
				createSerializationSteps(
					153		/* V00*1-steps || value */,
					88		/* StringLiteralExpCS::segments+=85 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[94] =
			new SerializationRule("TupleLiteralExpCS", 90,
				createSerializationMatchSteps(
					210		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91 */,
					62		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					114		/* 'Tuple' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					52		/* TupleLiteralExpCS::ownedParts+=91 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					52		/* TupleLiteralExpCS::ownedParts+=91 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						38) /* TupleLiteralPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(91, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TupleLiteralPartCS(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[95] =
			new SerializationRule("TupleLiteralPartCS", 91,
				createSerializationMatchSteps(
					215		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : 29 */,
					216		/* check-rule essentialoclcs::VariableCS.ownedType : 94 */,
					52		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
					88		/* assign V0 = |VariableCS::ownedType| */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					81		/* VariableCS::ownedType=94 || value */,
					145		/* 1*1-steps || value */,
					107		/* '=' || soft-space value soft-space */,
					32		/* VariableCS::ownedInitExpression=29 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						10) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						40) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TuplePartCS(basecs::TuplePartCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[96] =
			new SerializationRule("TuplePartCS", 92,
				createSerializationMatchSteps(
					144		/* check-rule basecs::TypedElementCS.ownedType : 94 */,
					50		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					27		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					173		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					145		/* 1*1-steps || value */,
					176		/* NamedElementCS::name=112 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					101		/* ':' || soft-space value soft-space */,
					80		/* TypedElementCS::ownedType=94 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						40) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] }
		serializationRules[97] =
			new SerializationRule("TupleTypeCS", 93,
				createSerializationMatchSteps(
					142		/* check-rule basecs::TupleTypeCS.ownedParts : 92 */,
					47		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					63		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					96		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					116		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					114		/* 'Tuple' || soft-space value soft-space */,
					154		/* V00*10-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					162		/* V01*5-steps || value */,
					53		/* TupleTypeCS::ownedParts+=92 || value */,
					164		/* V02*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					53		/* TupleTypeCS::ownedParts+=92 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						6	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						39) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[98] =
			new SerializationRule("TypeExpCS", 94,
				createSerializationMatchSteps(
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					86		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					42		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					3		/* PrimitiveTypeRefCS::name=74 || soft-space value soft-space */,
					153		/* V00*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */
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
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[99] =
			new SerializationRule("TypeExpCS", 94,
				createSerializationMatchSteps(
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					142		/* check-rule basecs::TupleTypeCS.ownedParts : 92 */,
					125		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					47		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					63		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					96		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					116		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					114		/* 'Tuple' || soft-space value soft-space */,
					154		/* V00*10-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					162		/* V01*5-steps || value */,
					53		/* TupleTypeCS::ownedParts+=92 || value */,
					164		/* V02*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					53		/* TupleTypeCS::ownedParts+=92 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */,
					166		/* V03*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						6	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						39) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::CollectionPatternCS): { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[100] =
			new SerializationRule("TypeExpCS", 94,
				createSerializationMatchSteps(
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					173		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68 */,
					174		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : 8 */,
					120		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					67		/* assign V0 = |CollectionPatternCS::restVariableName| */,
					91		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
					4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					74		/* CollectionPatternCS::ownedType=8 || value */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					154		/* V00*10-steps || value */,
					49		/* CollectionPatternCS::ownedParts+=68 || value */,
					161		/* V01*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					49		/* CollectionPatternCS::ownedParts+=68 || value */,
					150		/* 1*4-steps || value */,
					145		/* 1*1-steps || value */,
					98		/* '++' || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					87		/* CollectionPatternCS::restVariableName=33 || soft-space value soft-space */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */,
					163		/* V02*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						24) /* PatternExpCS */,
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
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(68, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[101] =
			new SerializationRule("TypeExpCS", 94,
				createSerializationMatchSteps(
					175		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50 */,
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					176		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 95 */,
					120		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					68		/* assign V0 = |CollectionTypeCS::ownedType| */,
					5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					101		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					2		/* CollectionTypeCS::name=9 || soft-space value soft-space */,
					158		/* V00*7-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					75		/* CollectionTypeCS::ownedType=95 || value */,
					160		/* V01*1-steps || value */,
					8		/* CollectionTypeCS::ownedCollectionMultiplicity=50 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */,
					163		/* V02*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						41) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(95, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[102] =
			new SerializationRule("TypeExpCS", 94,
				createSerializationMatchSteps(
					195		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94 */,
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					196		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 94 */,
					112		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					72		/* assign V0 = |MapTypeCS::ownedValueType| */,
					24		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					23		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					113		/* 'Map' || soft-space value soft-space */,
					159		/* V00*8-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					36		/* MapTypeCS::ownedKeyType=94 || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					83		/* MapTypeCS::ownedValueType=94 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */,
					160		/* V01*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						5	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						40) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						40) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::TypeNameExpCS): { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[103] =
			new SerializationRule("TypeExpCS", 94,
				createSerializationMatchSteps(
					212		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					213		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67 */,
					214		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 29 */,
					120		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					85		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
					49		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
					111		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
				),
				createSerializationSteps(
					57		/* TypeNameExpCS::ownedPathName=67 || value */,
					158		/* V00*7-steps || value */,
					14		/* TypeNameExpCS::ownedCurlyBracketedClause=14 || value */,
					162		/* V01*5-steps || value */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					59		/* TypeNameExpCS::ownedPatternGuard=29 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */,
					163		/* V02*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						5) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */,
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
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[104] =
			new SerializationRule("TypeLiteralExpCS", 97,
				createSerializationMatchSteps(
					211		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98 */,
					48		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					79		/* TypeLiteralExpCS::ownedType=98 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						43) /* TypeLiteralWithMultiplicityCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(98, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[105] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 98,
				createSerializationMatchSteps(
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					86		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					42		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					3		/* PrimitiveTypeRefCS::name=74 || soft-space value soft-space */,
					153		/* V00*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */
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
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[106] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 98,
				createSerializationMatchSteps(
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					142		/* check-rule basecs::TupleTypeCS.ownedParts : 92 */,
					125		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					47		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					63		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					96		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					116		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					114		/* 'Tuple' || soft-space value soft-space */,
					154		/* V00*10-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					162		/* V01*5-steps || value */,
					53		/* TupleTypeCS::ownedParts+=92 || value */,
					164		/* V02*3-steps || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					53		/* TupleTypeCS::ownedParts+=92 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */,
					166		/* V03*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						6	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						39) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[107] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 98,
				createSerializationMatchSteps(
					175		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50 */,
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					176		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 95 */,
					120		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					68		/* assign V0 = |CollectionTypeCS::ownedType| */,
					5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					101		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					2		/* CollectionTypeCS::name=9 || soft-space value soft-space */,
					158		/* V00*7-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					75		/* CollectionTypeCS::ownedType=95 || value */,
					160		/* V01*1-steps || value */,
					8		/* CollectionTypeCS::ownedCollectionMultiplicity=50 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */,
					163		/* V02*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						41) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(95, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[108] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 98,
				createSerializationMatchSteps(
					195		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94 */,
					145		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
					196		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 94 */,
					112		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					72		/* assign V0 = |MapTypeCS::ownedValueType| */,
					24		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					23		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					113		/* 'Map' || soft-space value soft-space */,
					159		/* V00*8-steps || value */,
					145		/* 1*1-steps || value */,
					95		/* '(' || no-space value no-space */,
					36		/* MapTypeCS::ownedKeyType=94 || value */,
					145		/* 1*1-steps || value */,
					99		/* ',' || no-space value soft-space */,
					83		/* MapTypeCS::ownedValueType=94 || value */,
					145		/* 1*1-steps || value */,
					96		/* ')' || no-space value */,
					160		/* V01*1-steps || value */,
					41		/* TypedRefCS::ownedMultiplicity=50 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						5	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						40) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						16) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						40) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] }
		serializationRules[109] =
			new SerializationRule("TypeNameExpCS", 99,
				createSerializationMatchSteps(
					212		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
					213		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67 */,
					214		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 29 */,
					85		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
					49		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
					111		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
				),
				createSerializationSteps(
					57		/* TypeNameExpCS::ownedPathName=67 || value */,
					158		/* V00*7-steps || value */,
					14		/* TypeNameExpCS::ownedCurlyBracketedClause=14 || value */,
					162		/* V01*5-steps || value */,
					145		/* 1*1-steps || value */,
					140		/* '{' || soft-space value push soft-new-line */,
					59		/* TypeNameExpCS::ownedPatternGuard=29 || value */,
					145		/* 1*1-steps || value */,
					144		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						5) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						23) /* PathNameCS */,
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
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::URIFirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[110] =
			new SerializationRule("URIFirstPathElementCS", 107,
				createSerializationMatchSteps(
					38		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					188		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
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
		serializationRules[111] =
			new SerializationRule("URIFirstPathElementCS", 107,
				createSerializationMatchSteps(
					38		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					186		/* PathElementCS::referredElement=URI || soft-space value soft-space */
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
		serializationRules[112] =
			new SerializationRule("URIPathNameCS", 108,
				createSerializationMatchSteps(
					135		/* check-rule basecs::PathNameCS.ownedPathElements : 61|107 */,
					57		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					180		/* PathNameCS::ownedPathElements+=107 || value */,
					155		/* V00*3-steps || value */,
					145		/* 1*1-steps || value */,
					103		/* '::' || no-space value no-space */,
					182		/* PathNameCS::ownedPathElements+=61 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						51) /* NextPathElementCS|URIFirstPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): "*"
		serializationRules[113] =
			new SerializationRule("UnlimitedNaturalLiteralExpCS", 110,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					145		/* 1*1-steps || value */,
					97		/* '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		// SpecificationCS::exprString=104 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[0] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, 104 /*UNQUOTED_STRING*/, 2);
		// MultiplicityBoundsCS::lowerBound=39 || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 39 /*LOWER*/, 6);
		// CollectionTypeCS::name=9 || soft-space value soft-space
		serializationSteps[2] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 9 /*CollectionTypeIdentifier*/, 6);
		// PrimitiveTypeRefCS::name=74 || soft-space value soft-space
		serializationSteps[3] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 74 /*PrimitiveTypeIdentifier*/, 6);
		// TemplateParameterSubstitutionCS::ownedActualParameter=101 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[4] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 101 /*TypeRefCS*/, 2);
		// TypedTypeRefCS::ownedBinding=87 || value
		serializationSteps[5] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 87 /*TemplateBindingCS*/, 0);
		// OperationContextDeclCS::ownedBodies+=83 || value
		serializationSteps[6] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, 83 /*SpecificationCS*/, 0);
		// NavigatingArgCS::ownedCoIterator=4 || value
		serializationSteps[7] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 4 /*CoIteratorVariableCS*/, 0);
		// CollectionTypeCS::ownedCollectionMultiplicity=50 || value
		serializationSteps[8] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 50 /*MultiplicityCS*/, 0);
		// IfExpCS::ownedCondition=29|68 || value
		serializationSteps[9] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, -1, new @NonNull Integer [] { 29/*ExpCS*/,68/*PatternExpCS*/}, 0);
		// IfThenExpCS::ownedCondition=29 || value
		serializationSteps[10] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 29 /*ExpCS*/, 0);
		// CompleteOCLDocumentCS::ownedContexts+=13 || value
		serializationSteps[11] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, 13 /*ContextDeclCS*/, 0);
		// PackageDeclarationCS::ownedContexts+=13 || value
		serializationSteps[12] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, 13 /*ContextDeclCS*/, 0);
		// AbstractNameExpCS::ownedCurlyBracketedClause=14 || value
		serializationSteps[13] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// TypeNameExpCS::ownedCurlyBracketedClause=14 || value
		serializationSteps[14] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// PropertyContextDeclCS::ownedDefaultExpressions+=83 || value
		serializationSteps[15] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, 83 /*SpecificationCS*/, 0);
		// ClassifierContextDeclCS::ownedDefinitions+=16 || value
		serializationSteps[16] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 16 /*DefCS*/, 0);
		// IfExpCS::ownedElseExpression=29 || value
		serializationSteps[17] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 29 /*ExpCS*/, 0);
		// CollectionLiteralPartCS::ownedExpression=29 || value
		serializationSteps[18] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 29 /*ExpCS*/, 0);
		// CollectionLiteralPartCS::ownedExpression=68 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[19] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 68 /*PatternExpCS*/, 2);
		// ContextCS::ownedExpression=29 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[20] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 29 /*ExpCS*/, 2);
		// ExpSpecificationCS::ownedExpression=29 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[21] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 29 /*ExpCS*/, 2);
		// NestedExpCS::ownedExpression=29 || value
		serializationSteps[22] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 29 /*ExpCS*/, 0);
		// LambdaLiteralExpCS::ownedExpressionCS=29 || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 29 /*ExpCS*/, 0);
		// TypeParameterCS::ownedExtends+=102 || value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 102 /*TypedRefCS*/, 0);
		// WildcardTypeRefCS::ownedExtends=102 || value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 102 /*TypedRefCS*/, 0);
		// IfExpCS::ownedIfThenExpressions+=22 || value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 22 /*ElseIfThenExpCS*/, 0);
		// RootCS::ownedImports+=35 || value
		serializationSteps[27] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 35 /*ImportCS*/, 0);
		// LetExpCS::ownedInExpression=29 || value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 29 /*ExpCS*/, 0);
		// NavigatingArgCS::ownedInitExpression=29 || value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 29 /*ExpCS*/, 0);
		// ShadowPartCS::ownedInitExpression=29|68 || value
		serializationSteps[30] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new @NonNull Integer [] { 29/*ExpCS*/,68/*PatternExpCS*/}, 0);
		// ShadowPartCS::ownedInitExpression=86 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 86 /*StringLiteralExpCS*/, 2);
		// VariableCS::ownedInitExpression=29 || value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 29 /*ExpCS*/, 0);
		// ClassifierContextDeclCS::ownedInvariants+=12 || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 12 /*ConstraintCS*/, 0);
		// PackageDeclarationCS::ownedInvariants+=12 || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, 12 /*ConstraintCS*/, 0);
		// MapLiteralPartCS::ownedKey=29 || value
		serializationSteps[35] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 29 /*ExpCS*/, 0);
		// MapTypeCS::ownedKeyType=94 || value
		serializationSteps[36] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 94 /*TypeExpCS*/, 0);
		// CollectionLiteralPartCS::ownedLastExpression=29 || value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 29 /*ExpCS*/, 0);
		// InfixExpCS::ownedLeft=70 || value
		serializationSteps[38] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 70 /*PrefixedPrimaryExpCS*/, 0);
		// ConstraintCS::ownedMessageSpecification=83 || value
		serializationSteps[39] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 83 /*SpecificationCS*/, 0);
		// TemplateBindingCS::ownedMultiplicity=50 || value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 50 /*MultiplicityCS*/, 0);
		// TypedRefCS::ownedMultiplicity=50 || value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 50 /*MultiplicityCS*/, 0);
		// NavigatingArgCS::ownedNameExpression=55 || value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 55 /*NavigatingArgExpCS*/, 0);
		// NavigatingArgCS::ownedNameExpression=55 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 55 /*NavigatingArgExpCS*/, 2);
		// CompleteOCLDocumentCS::ownedPackages+=65 || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, 65 /*PackageDeclarationCS*/, 0);
		// DefOperationCS::ownedParameters+=18 || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, 18 /*DefParameterCS*/, 0);
		// OperationContextDeclCS::ownedParameters+=66 || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, 66 /*ParameterCS*/, 0);
		// TemplateSignatureCS::ownedParameters+=100 || value
		serializationSteps[47] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 100 /*TypeParameterCS*/, 0);
		// CollectionLiteralExpCS::ownedParts+=6 || value
		serializationSteps[48] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 6 /*CollectionLiteralPartCS*/, 0);
		// CollectionPatternCS::ownedParts+=68 || value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 68 /*PatternExpCS*/, 0);
		// CurlyBracketedClauseCS::ownedParts+=81 || value
		serializationSteps[50] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 81 /*ShadowPartCS*/, 0);
		// MapLiteralExpCS::ownedParts+=46 || value
		serializationSteps[51] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 46 /*MapLiteralPartCS*/, 0);
		// TupleLiteralExpCS::ownedParts+=91 || value
		serializationSteps[52] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 91 /*TupleLiteralPartCS*/, 0);
		// TupleTypeCS::ownedParts+=92 || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 92 /*TuplePartCS*/, 0);
		// AbstractNameExpCS::ownedPathName=67 || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 67 /*PathNameCS*/, 0);
		// ImportCS::ownedPathName=108 || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 108 /*URIPathNameCS*/, 0);
		// PathNameDeclCS::ownedPathName=67 || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 67 /*PathNameCS*/, 0);
		// TypeNameExpCS::ownedPathName=67 || value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 67 /*PathNameCS*/, 0);
		// TypedTypeRefCS::ownedPathName=67 || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 67 /*PathNameCS*/, 0);
		// TypeNameExpCS::ownedPatternGuard=29 || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 29 /*ExpCS*/, 0);
		// PatternExpCS::ownedPatternType=94 || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 94 /*TypeExpCS*/, 0);
		// OperationContextDeclCS::ownedPostconditions+=12 || value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, 12 /*ConstraintCS*/, 0);
		// OperationContextDeclCS::ownedPreconditions+=12 || value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, 12 /*ConstraintCS*/, 0);
		// AbstractNameExpCS::ownedRoundBracketedClause=76 || value
		serializationSteps[63] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 76 /*RoundBracketedClauseCS*/, 0);
		// LetVariableCS::ownedRoundBracketedClause=76 || value
		serializationSteps[64] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 76 /*RoundBracketedClauseCS*/, 0);
		// TemplateableElementCS::ownedSignature=89 || value
		serializationSteps[65] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 89 /*TemplateSignatureCS*/, 0);
		// ConstraintCS::ownedSpecification=83 || value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 83 /*SpecificationCS*/, 0);
		// DefCS::ownedSpecification=83 || value
		serializationSteps[67] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 83 /*SpecificationCS*/, 0);
		// AbstractNameExpCS::ownedSquareBracketedClauses+=84 || value
		serializationSteps[68] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 84 /*SquareBracketedClauseCS*/, 0);
		// TemplateBindingCS::ownedSubstitutions+=88 || value
		serializationSteps[69] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 88 /*TemplateParameterSubstitutionCS*/, 0);
		// SquareBracketedClauseCS::ownedTerms+=29 || value
		serializationSteps[70] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 29 /*ExpCS*/, 0);
		// IfExpCS::ownedThenExpression=29 || value
		serializationSteps[71] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 29 /*ExpCS*/, 0);
		// IfThenExpCS::ownedThenExpression=29 || value
		serializationSteps[72] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 29 /*ExpCS*/, 0);
		// CollectionLiteralExpCS::ownedType=8 || value
		serializationSteps[73] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 8 /*CollectionTypeCS*/, 0);
		// CollectionPatternCS::ownedType=8 || value
		serializationSteps[74] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 8 /*CollectionTypeCS*/, 0);
		// CollectionTypeCS::ownedType=95 || value
		serializationSteps[75] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 95 /*TypeExpWithoutMultiplicityCS*/, 0);
		// FeatureContextDeclCS::ownedType=94 || value
		serializationSteps[76] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 94 /*TypeExpCS*/, 0);
		// MapLiteralExpCS::ownedType=47 || value
		serializationSteps[77] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 47 /*MapTypeCS*/, 0);
		// NavigatingArgCS::ownedType=94 || value
		serializationSteps[78] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 94 /*TypeExpCS*/, 0);
		// TypeLiteralExpCS::ownedType=98 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[79] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 98 /*TypeLiteralWithMultiplicityCS*/, 2);
		// TypedElementCS::ownedType=94 || value
		serializationSteps[80] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 94 /*TypeExpCS*/, 0);
		// VariableCS::ownedType=94 || value
		serializationSteps[81] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 94 /*TypeExpCS*/, 0);
		// MapLiteralPartCS::ownedValue=29 || value
		serializationSteps[82] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 29 /*ExpCS*/, 0);
		// MapTypeCS::ownedValueType=94 || value
		serializationSteps[83] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 94 /*TypeExpCS*/, 0);
		// LetExpCS::ownedVariables+=42 || value
		serializationSteps[84] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 42 /*LetVariableCS*/, 0);
		// PatternExpCS::patternVariableName=112 || soft-space value soft-space
		serializationSteps[85] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 112 /*UnrestrictedName*/, 6);
		// ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[86] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 6);
		// CollectionPatternCS::restVariableName=33 || soft-space value soft-space
		serializationSteps[87] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 33 /*Identifier*/, 6);
		// StringLiteralExpCS::segments+=85 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[88] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 85 /*StringLiteral*/, 2);
		// ClassifierContextDeclCS::selfName=112 || soft-space value soft-space
		serializationSteps[89] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME, 112 /*UnrestrictedName*/, 6);
		// MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[90] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 6);
		// BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[91] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 7 /* 'false|true' */, 2);
		// NumberLiteralExpCS::symbol=52 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[92] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 52 /*NUMBER_LITERAL*/, 2);
		// MultiplicityBoundsCS::upperBound=105 || soft-space value soft-space
		serializationSteps[93] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 105 /*UPPER*/, 6);
		// '&&' || soft-space value soft-space
		serializationSteps[94] = createSerializationStepKeyword("&&", 6);
		// '(' || no-space value no-space
		serializationSteps[95] = createSerializationStepKeyword("(", 3);
		// ')' || no-space value
		serializationSteps[96] = createSerializationStepKeyword(")", 1);
		// '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[97] = createSerializationStepKeyword("*", 2);
		// '++' || soft-space value soft-space
		serializationSteps[98] = createSerializationStepKeyword("++", 6);
		// ',' || no-space value soft-space
		serializationSteps[99] = createSerializationStepKeyword(",", 5);
		// '..' || no-space value no-space
		serializationSteps[100] = createSerializationStepKeyword("..", 3);
		// ':' || soft-space value soft-space
		serializationSteps[101] = createSerializationStepKeyword(":", 6);
		// '::*' || soft-space value soft-space
		serializationSteps[102] = createSerializationStepKeyword("::*", 6);
		// '::' || no-space value no-space
		serializationSteps[103] = createSerializationStepKeyword("::", 3);
		// ';' || no-space value soft-new-line
		serializationSteps[104] = createSerializationStepKeyword(";", 4);
		// '<-' || soft-space value soft-space
		serializationSteps[105] = createSerializationStepKeyword("<-", 6);
		// '<' || soft-space value soft-space
		serializationSteps[106] = createSerializationStepKeyword("<", 6);
		// '=' || soft-space value soft-space
		serializationSteps[107] = createSerializationStepKeyword("=", 6);
		// '>' || soft-space value soft-space
		serializationSteps[108] = createSerializationStepKeyword(">", 6);
		// '?' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[109] = createSerializationStepKeyword("?", 2);
		// '?' || soft-space value soft-space
		serializationSteps[110] = createSerializationStepKeyword("?", 6);
		// '@' || soft-space value soft-space
		serializationSteps[111] = createSerializationStepKeyword("@", 6);
		// 'Lambda' || soft-space value soft-space
		serializationSteps[112] = createSerializationStepKeyword("Lambda", 6);
		// 'Map' || soft-space value soft-space
		serializationSteps[113] = createSerializationStepKeyword("Map", 6);
		// 'Tuple' || soft-space value soft-space
		serializationSteps[114] = createSerializationStepKeyword("Tuple", 6);
		// '[' || no-space value no-space
		serializationSteps[115] = createSerializationStepKeyword("[", 3);
		// ']' || no-space value
		serializationSteps[116] = createSerializationStepKeyword("]", 1);
		// 'body' || soft-space value soft-space
		serializationSteps[117] = createSerializationStepKeyword("body", 6);
		// 'context' || soft-space value soft-space
		serializationSteps[118] = createSerializationStepKeyword("context", 6);
		// 'def' || soft-space value soft-space
		serializationSteps[119] = createSerializationStepKeyword("def", 6);
		// 'derive' || soft-space value soft-space
		serializationSteps[120] = createSerializationStepKeyword("derive", 6);
		// 'else' || soft-space value soft-space
		serializationSteps[121] = createSerializationStepKeyword("else", 6);
		// 'elseif' || soft-space value soft-space
		serializationSteps[122] = createSerializationStepKeyword("elseif", 6);
		// 'endif' || soft-space value soft-space
		serializationSteps[123] = createSerializationStepKeyword("endif", 6);
		// 'endpackage' || soft-space value soft-space
		serializationSteps[124] = createSerializationStepKeyword("endpackage", 6);
		// 'extends' || soft-space value soft-space
		serializationSteps[125] = createSerializationStepKeyword("extends", 6);
		// 'if' || soft-space value soft-space
		serializationSteps[126] = createSerializationStepKeyword("if", 6);
		// 'import' || value
		serializationSteps[127] = createSerializationStepKeyword("import", 0);
		// 'in' || soft-space value soft-space
		serializationSteps[128] = createSerializationStepKeyword("in", 6);
		// 'init' || soft-space value soft-space
		serializationSteps[129] = createSerializationStepKeyword("init", 6);
		// 'inv' || soft-space value soft-space
		serializationSteps[130] = createSerializationStepKeyword("inv", 6);
		// 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[131] = createSerializationStepKeyword("invalid", 2);
		// 'let' || soft-space value soft-space
		serializationSteps[132] = createSerializationStepKeyword("let", 6);
		// 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[133] = createSerializationStepKeyword("null", 2);
		// 'package' || soft-space value soft-space
		serializationSteps[134] = createSerializationStepKeyword("package", 6);
		// 'post' || soft-space value soft-space
		serializationSteps[135] = createSerializationStepKeyword("post", 6);
		// 'pre' || soft-space value soft-space
		serializationSteps[136] = createSerializationStepKeyword("pre", 6);
		// 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[137] = createSerializationStepKeyword("self", 2);
		// 'static' || soft-space value soft-space
		serializationSteps[138] = createSerializationStepKeyword("static", 6);
		// 'then' || soft-space value soft-space
		serializationSteps[139] = createSerializationStepKeyword("then", 6);
		// '{' || soft-space value push soft-new-line
		serializationSteps[140] = createSerializationStepKeyword("{", 8);
		// '|' || soft-space value soft-space
		serializationSteps[141] = createSerializationStepKeyword("|", 6);
		// '|1' || soft-space value soft-space
		serializationSteps[142] = createSerializationStepKeyword("|1", 6);
		// '|?' || soft-space value soft-space
		serializationSteps[143] = createSerializationStepKeyword("|?", 6);
		// '}' || pop soft-space value soft-new-line
		serializationSteps[144] = createSerializationStepKeyword("}", 7);
		// 1*1-steps || value
		serializationSteps[145] = createSerializationStepSequence(-1, 1, 0);
		// 1*11-steps || value
		serializationSteps[146] = createSerializationStepSequence(-1, 11, 0);
		// 1*12-steps || value
		serializationSteps[147] = createSerializationStepSequence(-1, 12, 0);
		// 1*14-steps || value
		serializationSteps[148] = createSerializationStepSequence(-1, 14, 0);
		// 1*3-steps || value
		serializationSteps[149] = createSerializationStepSequence(-1, 3, 0);
		// 1*4-steps || value
		serializationSteps[150] = createSerializationStepSequence(-1, 4, 0);
		// 1*6-steps || value
		serializationSteps[151] = createSerializationStepSequence(-1, 6, 0);
		// 1*7-steps || value
		serializationSteps[152] = createSerializationStepSequence(-1, 7, 0);
		// V00*1-steps || value
		serializationSteps[153] = createSerializationStepSequence(0, 1, 0);
		// V00*10-steps || value
		serializationSteps[154] = createSerializationStepSequence(0, 10, 0);
		// V00*3-steps || value
		serializationSteps[155] = createSerializationStepSequence(0, 3, 0);
		// V00*4-steps || value
		serializationSteps[156] = createSerializationStepSequence(0, 4, 0);
		// V00*5-steps || value
		serializationSteps[157] = createSerializationStepSequence(0, 5, 0);
		// V00*7-steps || value
		serializationSteps[158] = createSerializationStepSequence(0, 7, 0);
		// V00*8-steps || value
		serializationSteps[159] = createSerializationStepSequence(0, 8, 0);
		// V01*1-steps || value
		serializationSteps[160] = createSerializationStepSequence(1, 1, 0);
		// V01*3-steps || value
		serializationSteps[161] = createSerializationStepSequence(1, 3, 0);
		// V01*5-steps || value
		serializationSteps[162] = createSerializationStepSequence(1, 5, 0);
		// V02*1-steps || value
		serializationSteps[163] = createSerializationStepSequence(2, 1, 0);
		// V02*3-steps || value
		serializationSteps[164] = createSerializationStepSequence(2, 3, 0);
		// V02*5-steps || value
		serializationSteps[165] = createSerializationStepSequence(2, 5, 0);
		// V03*1-steps || value
		serializationSteps[166] = createSerializationStepSequence(3, 1, 0);
		// V03*3-steps || value
		serializationSteps[167] = createSerializationStepSequence(3, 3, 0);
		// V03*4-steps || value
		serializationSteps[168] = createSerializationStepSequence(3, 4, 0);
		// V04*1-steps || value
		serializationSteps[169] = createSerializationStepSequence(4, 1, 0);
		// V04*3-steps || value
		serializationSteps[170] = createSerializationStepSequence(4, 3, 0);
		// V05*3-steps || value
		serializationSteps[171] = createSerializationStepSequence(5, 3, 0);
		// V06*5-steps || value
		serializationSteps[172] = createSerializationStepSequence(6, 5, 0);
		// wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[173] = createSerializationStepWrapper(2);
		// NamedElementCS::name=1 || soft-space value soft-space
		serializationSteps[174] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 1 /*BinaryOperatorName*/, 6);
		// NamedElementCS::name=109 || soft-space value soft-space
		serializationSteps[175] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 109 /*UnaryOperatorName*/, 6);
		// NamedElementCS::name=112 || soft-space value soft-space
		serializationSteps[176] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 112 /*UnrestrictedName*/, 6);
		// NamedElementCS::name=33 || soft-space value soft-space
		serializationSteps[177] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 33 /*Identifier*/, 6);
		// RoundBracketedClauseCS::ownedArguments+=54 || value
		serializationSteps[178] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 54 /*NavigatingArgCS*/, 0);
		// RoundBracketedClauseCS::ownedArguments+=57|58|56 || value
		serializationSteps[179] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new @NonNull Integer [] { 57/*NavigatingCommaArgCS*/,58/*NavigatingSemiArgCS*/,56/*NavigatingBarArgCS*/}, 0);
		// PathNameCS::ownedPathElements+=107 || value
		serializationSteps[180] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 107 /*URIFirstPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=30 || value
		serializationSteps[181] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 30 /*FirstPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=61 || value
		serializationSteps[182] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 61 /*NextPathElementCS*/, 0);
		// OperatorExpCS::ownedRight=29 || value
		serializationSteps[183] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 29 /*ExpCS*/, 0);
		// OperatorExpCS::ownedRight=69 || value
		serializationSteps[184] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 69 /*PrefixedLetExpCS*/, 0);
		// OperatorExpCS::ownedRight=70 || value
		serializationSteps[185] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 70 /*PrefixedPrimaryExpCS*/, 0);
		// PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[186] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 6);
		// PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[187] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 6);
		// PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[188] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 6);
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
//	import CompleteOCLCSPackage;
//	import EssentialOCLCSPackage;
