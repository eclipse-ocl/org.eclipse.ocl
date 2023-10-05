/*******************************************************************************
 * Copyright (c) 2011, 2023 Willink Transformations and others.
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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.examples.xtext.serializer.DataTypeRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue.EReference_TargetGrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueMultiple;
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
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment.CustomSerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.TerminalRuleValue;
import org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;

/******* This file is 100% auto-generated - do not edit it *******/

/**
 * The CompleteOCLSerializationMetaData singleton provides the metadata to support a
 * model to text serialization of a parsed Xtext semantic model or to re-format an Xtext text node model.
 */
public class CompleteOCLSerializationMetaData extends AbstractSerializationMetaData
{
	/**
	 * The Provider supports injected creation of the CompleteOCLSerializationMetaData singleton.
	 */
	public static class Provider implements SerializationMetaData.Provider
	{
		private static @Nullable CompleteOCLSerializationMetaData INSTANCE = null;

		@Inject
		private GrammarProvider grammarProvider;

		@Override
		public synchronized @NonNull SerializationMetaData get() {
			// synchronized synchronizes the creation of this singleton.
			// It does not imply that the overall application is threadsafe.
			CompleteOCLSerializationMetaData instance = INSTANCE;
			if (instance == null) {
				assert grammarProvider != null;
				Grammar grammar = grammarProvider.getGrammar(Provider.class);
				assert grammar != null;
				INSTANCE = instance = new CompleteOCLSerializationMetaData(grammar);
			}
			return instance;
		}
	}

	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[61];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[11];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[116];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[62];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[234];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[193];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[99];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [9] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[195];
	private final @Nullable String @Nullable [] multipleLineCommentMidfixes = new @Nullable String[] {" *"};
	private final @NonNull String @Nullable [] multipleLineCommentPrefixes = new @NonNull String[] {"/*"};
	private final @NonNull String @Nullable [] multipleLineCommentSuffixes = new @NonNull String[] {"*/"};
	private final @NonNull String @Nullable [] singleLineCommentPrefixes = new @NonNull String[] {"--"};

	private CompleteOCLSerializationMetaData(@NonNull Grammar grammar) {
		super(grammar);
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
		return 103;
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
		return 102;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 146;
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
		eClassValues[0] = new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
			createSerializationRules(
				34 /* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			), null
		);
		eClassValues[1] = new EClassValue(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS,
			createSerializationRules(
				17 /* ClassifierContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS ('inv' ClassifierContextDeclCS::ownedInvariants+=ConstraintCS)[V2:+] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:*] */,
				18 /* ClassifierContextDeclCS-1: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS ('inv' ClassifierContextDeclCS::ownedInvariants+=ConstraintCS)[V2:*] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:+] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					8) /* DefCS|DefOperationCS|DefPropertyCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					3) /* ConstraintCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					60) /* UnreservedPathNameCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					38) /* TemplateSignatureCS */
			}
		);
		eClassValues[2] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
			createSerializationRules(
				36 /* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					1) /* CollectionLiteralPartCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					2) /* CollectionTypeCS */
			}
		);
		eClassValues[3] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
			createSerializationRules(
				38 /* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */,
				37 /* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					59) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[4] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
			createSerializationRules(
				39 /* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				87 /* TypeExpCS-4: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					25) /* PatternExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					2) /* CollectionTypeCS */
			}
		);
		eClassValues[5] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
			createSerializationRules(
				40 /* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				84 /* TypeExpCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				91 /* TypeLiteralWithMultiplicityCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[6] = new EClassValue(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS,
			createSerializationRules(
				19 /* CompleteOCLDocumentCS-0: (RootCS::ownedImports+=ImportCS)[V0:*] (CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS)[V1:*] (CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS)[V2:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
					30) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					12) /* ImportCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
					22) /* PackageDeclarationCS */
			}
		);
		eClassValues[7] = new EClassValue(BaseCSPackage.Literals.CONSTRAINT_CS,
			createSerializationRules(
				20 /* ConstraintCS-0: (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					33) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					33) /* SpecificationCS */
			}
		);
		eClassValues[8] = new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
			createSerializationRules(
				52 /* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[9] = new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				41 /* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					32) /* ShadowPartCS */
			}
		);
		eClassValues[10] = new EClassValue(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS,
			createSerializationRules(
				21 /* DefOperationCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (DefOperationCS::ownedParameters+=DefParameterCS (',' DefOperationCS::ownedParameters+=DefParameterCS)[V3:*])[V2:?] ')' ':' (TypedElementCS::ownedType=TypeExpCS)[V4:?] '=' DefCS::ownedSpecification=SpecificationCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS,
					7) /* DefParameterCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					38) /* TemplateSignatureCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					33) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[11] = new EClassValue(CompleteOCLCSPackage.Literals.DEF_PROPERTY_CS,
			createSerializationRules(
				23 /* DefPropertyCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS '=' DefCS::ownedSpecification=SpecificationCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					33) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[12] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
			createSerializationRules(
				30 /* SpecificationCS-0: ExpSpecificationCS::ownedExpression=ExpCS */,
				31 /* SpecificationCS-1: SpecificationCS::exprString=UNQUOTED_STRING */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[13] = new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
			createSerializationRules(
				44 /* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					59) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					9) /* ElseIfThenExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[14] = new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
			createSerializationRules(
				42 /* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[15] = new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
			createSerializationRules(
				24 /* ImportCS-0: 'import' (NamedElementCS::name=Identifier ':')[V0:?] ImportCS::ownedPathName=URIPathNameCS (ImportCS::isAll?='::*')[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					52) /* URIPathNameCS */
			}
		);
		eClassValues[16] = new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
			createSerializationRules(
				43 /* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					55) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[17] = new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
			createSerializationRules(
				45 /* InvalidLiteralExpCS-0: 'invalid' */
			), null
		);
		eClassValues[18] = new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
			createSerializationRules(
				46 /* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[19] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
			createSerializationRules(
				47 /* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					13) /* LetVariableCS */
			}
		);
		eClassValues[20] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
			createSerializationRules(
				48 /* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					31) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[21] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
			createSerializationRules(
				49 /* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					14) /* MapLiteralPartCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					15) /* MapTypeCS */
			}
		);
		eClassValues[22] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
			createSerializationRules(
				50 /* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[23] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
			createSerializationRules(
				51 /* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				85 /* TypeExpCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				92 /* TypeLiteralWithMultiplicityCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					41) /* TypeExpCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[24] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */,
				4 /* MultiplicityCS-2: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] ']' */,
				2 /* MultiplicityCS-0: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] '|?' ']' */,
				3 /* MultiplicityCS-1: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] MultiplicityCS::isNullFree?='|1' ']' */
			), null
		);
		eClassValues[25] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				7 /* MultiplicityCS-5: '[' MultiplicityStringCS::stringBounds='*|+|?' ']' */,
				5 /* MultiplicityCS-3: '[' MultiplicityStringCS::stringBounds='*|+|?' '|?' ']' */,
				6 /* MultiplicityCS-4: '[' MultiplicityStringCS::stringBounds='*|+|?' MultiplicityCS::isNullFree?='|1' ']' */,
				8 /* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			), null
		);
		eClassValues[26] = new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
			createSerializationRules(
				53 /* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					5) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					24) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					31) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					34) /* SquareBracketedClauseCS */
			}
		);
		eClassValues[27] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
			createSerializationRules(
				58 /* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				54 /* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				57 /* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				55 /* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				56 /* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				59 /* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */,
				63 /* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				62 /* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				60 /* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				61 /* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				64 /* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					0) /* CoIteratorVariableCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[28] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				65 /* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[29] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				66 /* NullLiteralExpCS-0: 'null' */
			), null
		);
		eClassValues[30] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				67 /* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[31] = new EClassValue(CompleteOCLCSPackage.Literals.OCL_MESSAGE_ARG_CS,
			createSerializationRules(
				25 /* NavigatingArgExpCS-0: '?' */
			), null
		);
		eClassValues[32] = new EClassValue(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS,
			createSerializationRules(
				26 /* OperationContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS '(' (OperationContextDeclCS::ownedParameters+=ParameterCS (',' OperationContextDeclCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' ':' (FeatureContextDeclCS::ownedType=TypeExpCS)[V3:?] ('body' ':' OperationContextDeclCS::ownedBodies+=SpecificationCS)[V4:*] ('post' OperationContextDeclCS::ownedPostconditions+=ConstraintCS)[V5:*] ('pre' OperationContextDeclCS::ownedPreconditions+=ConstraintCS)[V6:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
					33) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
					23) /* ParameterCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					60) /* UnreservedPathNameCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
					3) /* ConstraintCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
					3) /* ConstraintCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					38) /* TemplateSignatureCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[33] = new EClassValue(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS,
			createSerializationRules(
				27 /* PackageDeclarationCS-0: 'package' PathNameDeclCS::ownedPathName=UnreservedPathNameCS ('inv' PackageDeclarationCS::ownedInvariants+=ConstraintCS)[V0:*] (PackageDeclarationCS::ownedContexts+=ContextDeclCS)[V1:*] 'endpackage' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
					30) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
					3) /* ConstraintCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					60) /* UnreservedPathNameCS */
			}
		);
		eClassValues[34] = new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
			createSerializationRules(
				22 /* DefParameterCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */,
				28 /* ParameterCS-0: (NamedElementCS::name=UnrestrictedName ':')[V0:?] TypedElementCS::ownedType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[35] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */,
				9 /* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */,
				96 /* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */
			), null
		);
		eClassValues[36] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				95 /* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */
			), null
		);
		eClassValues[37] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				15 /* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				76 /* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */,
				97 /* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					51) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
			}
		);
		eClassValues[38] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				68 /* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[39] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				69 /* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				70 /* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[40] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
			createSerializationRules(
				71 /* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				83 /* TypeExpCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				90 /* TypeLiteralWithMultiplicityCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */
			}
		);
		eClassValues[41] = new EClassValue(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS,
			createSerializationRules(
				29 /* PropertyContextDeclCS-0: 'context' PathNameDeclCS::ownedPathName=UnreservedPathNameCS ':' FeatureContextDeclCS::ownedType=TypeExpCS ('derive' ':' PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS)[V0:*] ('init' ':' PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS)[V1:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
					33) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					60) /* UnreservedPathNameCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[42] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				72 /* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					19) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[43] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				73 /* SelfExpCS-0: 'self' */
			), null
		);
		eClassValues[44] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				75 /* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */,
				74 /* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					59) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[45] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				77 /* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[46] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				78 /* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			), null
		);
		eClassValues[47] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					37) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[48] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[49] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				32 /* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */,
				33 /* TemplateSignatureCS-1: '<' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] '>' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					46) /* TypeParameterCS */
			}
		);
		eClassValues[50] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				79 /* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					39) /* TupleLiteralPartCS */
			}
		);
		eClassValues[51] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
			createSerializationRules(
				80 /* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[52] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				81 /* TuplePartCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[53] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
			createSerializationRules(
				82 /* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				88 /* TypeExpCS-5: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
				93 /* TypeLiteralWithMultiplicityCS-3: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					40) /* TuplePartCS */
			}
		);
		eClassValues[54] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				89 /* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					44) /* TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[55] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
			createSerializationRules(
				86 /* TypeExpCS-3: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				94 /* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					5) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					16) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					24) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[56] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				13 /* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					49) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[57] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				14 /* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					36) /* TemplateBindingCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					24) /* PathNameCS */
			}
		);
		eClassValues[58] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				98 /* UnlimitedNaturalLiteralExpCS-0: '*' */
			), null
		);
		eClassValues[59] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				35 /* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					41) /* TypeExpCS */
			}
		);
		eClassValues[60] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				16 /* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					49) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
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
		// 5: 'Map'
		enumerationValues[5] = new EnumerationValueSingle("Map");
		// 6: 'Tuple'
		enumerationValues[6] = new EnumerationValueSingle("Tuple");
		// 7: 'false|true'
		enumerationValues[7] = new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		// 8: 'static'
		enumerationValues[8] = new EnumerationValueSingle("static");
		// 9: '|'
		enumerationValues[9] = new EnumerationValueSingle("|");
		// 10: '|1'
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
				34	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* symbol="true" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* symbol="false" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "ClassifierContextDeclCS", -1,
			createSerializationRules(
				17	/* ClassifierContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS ('inv' ClassifierContextDeclCS::ownedInvariants+=ConstraintCS)[V2:+] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:*] */,
				18	/* ClassifierContextDeclCS-1: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS ('inv' ClassifierContextDeclCS::ownedInvariants+=ConstraintCS)[V2:*] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:+] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "context" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 6	/* selfName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=UnreservedPathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives+ : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "inv" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInvariants+=ConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedDefinitions+=DefCS : [value] | [value] */
		);
		grammarRuleValues[4] = createParserRuleValue(4, "CoIteratorVariableCS", -1,
			createSerializationRules(
				35	/* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[5] = createParserRuleValue(5, "CollectionLiteralExpCS", -1,
			createSerializationRules(
				36	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				37	/* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */,
				38	/* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedLastExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=PatternExpCS : [value] | [value] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "CollectionPatternCS", -1,
			createSerializationRules(
				39	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */
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
				40	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */
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
				19	/* CompleteOCLDocumentCS-0: (RootCS::ownedImports+=ImportCS)[V0:*] (CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS)[V1:*] (CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS)[V2:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedImports+=ImportCS* : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedPackages+=PackageDeclarationCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedContexts+=ContextDeclCS : [value] | [value] */
		);
		grammarRuleValues[11] = new DataTypeRuleValue(11, "CompleteOCLNavigationOperatorName");
		grammarRuleValues[12] = createParserRuleValue(12, "ConstraintCS", -1,
			createSerializationRules(
				20	/* ConstraintCS-0: (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "ContextDeclCS", 30 /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
			createSerializationRules(
				17	/* ClassifierContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS ('inv' ClassifierContextDeclCS::ownedInvariants+=ConstraintCS)[V2:+] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:*] */,
				18	/* ClassifierContextDeclCS-1: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS ('inv' ClassifierContextDeclCS::ownedInvariants+=ConstraintCS)[V2:*] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:+] */,
				26	/* OperationContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS '(' (OperationContextDeclCS::ownedParameters+=ParameterCS (',' OperationContextDeclCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' ':' (FeatureContextDeclCS::ownedType=TypeExpCS)[V3:?] ('body' ':' OperationContextDeclCS::ownedBodies+=SpecificationCS)[V4:*] ('post' OperationContextDeclCS::ownedPostconditions+=ConstraintCS)[V5:*] ('pre' OperationContextDeclCS::ownedPreconditions+=ConstraintCS)[V6:*] */,
				29	/* PropertyContextDeclCS-0: 'context' PathNameDeclCS::ownedPathName=UnreservedPathNameCS ':' FeatureContextDeclCS::ownedType=TypeExpCS ('derive' ':' PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS)[V0:*] ('init' ':' PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS)[V1:*] */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* PropertyContextDeclCS : [value] | [value] */,
			(0 << 16) | 0	/* ClassifierContextDeclCS : [value] | [value] */,
			(0 << 16) | 0	/* OperationContextDeclCS : [value] | [value] */
		);
		grammarRuleValues[14] = createParserRuleValue(14, "CurlyBracketedClauseCS", -1,
			createSerializationRules(
				41	/* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
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
				21	/* DefOperationCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (DefOperationCS::ownedParameters+=DefParameterCS (',' DefOperationCS::ownedParameters+=DefParameterCS)[V3:*])[V2:?] ')' ':' (TypedElementCS::ownedType=TypeExpCS)[V4:?] '=' DefCS::ownedSpecification=SpecificationCS */,
				23	/* DefPropertyCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS '=' DefCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* DefOperationCS : [value] | [value] */,
			(0 << 16) | 0	/* DefPropertyCS : [value] | [value] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "DefOperationCS", -1,
			createSerializationRules(
				21	/* DefOperationCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (DefOperationCS::ownedParameters+=DefParameterCS (',' DefOperationCS::ownedParameters+=DefParameterCS)[V3:*])[V2:?] ')' ':' (TypedElementCS::ownedType=TypeExpCS)[V4:?] '=' DefCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				22	/* DefParameterCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[19] = createParserRuleValue(19, "DefPropertyCS", -1,
			createSerializationRules(
				23	/* DefPropertyCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS '=' DefCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				42	/* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
		grammarRuleValues[29] = createParserRuleValue(29, "ExpCS", 57 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				34	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				36	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				43	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				44	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				45	/* InvalidLiteralExpCS-0: 'invalid' */,
				46	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				47	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				49	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				53	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				65	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				66	/* NullLiteralExpCS-0: 'null' */,
				67	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				69	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				70	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				73	/* SelfExpCS-0: 'self' */,
				78	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				79	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				89	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				98	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				0	/* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 6	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[31] = new DataTypeRuleValue(31, "ID");
		grammarRuleValues[32] = new TerminalRuleValue(32, "INT");
		grammarRuleValues[33] = new DataTypeRuleValue(33, "Identifier");
		grammarRuleValues[34] = createParserRuleValue(34, "IfExpCS", -1,
			createSerializationRules(
				44	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				24	/* ImportCS-0: 'import' (NamedElementCS::name=Identifier ':')[V0:?] ImportCS::ownedPathName=URIPathNameCS (ImportCS::isAll?='::*')[V1:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				45	/* InvalidLiteralExpCS-0: 'invalid' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {InvalidLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "invalid" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[38] = new TerminalRuleValue(38, "LETTER_CHARACTER");
		grammarRuleValues[39] = new DataTypeRuleValue(39, "LOWER");
		grammarRuleValues[40] = createParserRuleValue(40, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				46	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "LetExpCS", -1,
			createSerializationRules(
				47	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				48	/* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				49	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				50	/* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedKey=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValue=ExpCS : [value] | [value] */
		);
		grammarRuleValues[47] = createParserRuleValue(47, "MapTypeCS", -1,
			createSerializationRules(
				51	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */
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
				52	/* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[49] = createParserRuleValue(49, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 6	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[50] = createParserRuleValue(50, "MultiplicityCS", -1,
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
			(0 << 16) | 6	/* isNullFree?="|1" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[51] = createParserRuleValue(51, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			),
			(0 << 16) | 6	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[52] = new DataTypeRuleValue(52, "NUMBER_LITERAL");
		grammarRuleValues[53] = createParserRuleValue(53, "NameExpCS", -1,
			createSerializationRules(
				53	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				54	/* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				55	/* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				56	/* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				57	/* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				58	/* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [soft-space, value, soft-space] */,
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
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 6	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[55] = createParserRuleValue(55, "NavigatingArgExpCS", 58 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				34	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				36	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				43	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				44	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				45	/* InvalidLiteralExpCS-0: 'invalid' */,
				46	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				47	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				49	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				53	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				25	/* NavigatingArgExpCS-0: '?' */,
				65	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				66	/* NullLiteralExpCS-0: 'null' */,
				67	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				69	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				70	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				73	/* SelfExpCS-0: 'self' */,
				78	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				79	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				89	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				98	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {OCLMessageArgCS} : [value] | [value] */,
			(0 << 16) | 6	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ExpCS : [value] | [value] */
		);
		grammarRuleValues[56] = createParserRuleValue(56, "NavigatingBarArgCS", -1,
			createSerializationRules(
				59	/* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				60	/* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				61	/* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				62	/* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				63	/* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* prefix="," : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [soft-space, value, soft-space] */,
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
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 6	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[58] = createParserRuleValue(58, "NavigatingSemiArgCS", -1,
			createSerializationRules(
				64	/* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				65	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[61] = createParserRuleValue(61, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */
			),
			(0 << 16) | 6	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[62] = createParserRuleValue(62, "NullLiteralExpCS", -1,
			createSerializationRules(
				66	/* NullLiteralExpCS-0: 'null' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {NullLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "null" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[63] = createParserRuleValue(63, "NumberLiteralExpCS", -1,
			createSerializationRules(
				67	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			),
			(0 << 16) | 2	/* symbol=NUMBER_LITERAL : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[64] = createParserRuleValue(64, "OperationContextDeclCS", -1,
			createSerializationRules(
				26	/* OperationContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS '(' (OperationContextDeclCS::ownedParameters+=ParameterCS (',' OperationContextDeclCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' ':' (FeatureContextDeclCS::ownedType=TypeExpCS)[V3:?] ('body' ':' OperationContextDeclCS::ownedBodies+=SpecificationCS)[V4:*] ('post' OperationContextDeclCS::ownedPostconditions+=ConstraintCS)[V5:*] ('pre' OperationContextDeclCS::ownedPreconditions+=ConstraintCS)[V6:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "context" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=UnreservedPathNameCS : [value] | [value] */,
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
				27	/* PackageDeclarationCS-0: 'package' PathNameDeclCS::ownedPathName=UnreservedPathNameCS ('inv' PackageDeclarationCS::ownedInvariants+=ConstraintCS)[V0:*] (PackageDeclarationCS::ownedContexts+=ContextDeclCS)[V1:*] 'endpackage' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "package" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=UnreservedPathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "inv" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInvariants+=ConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedContexts+=ContextDeclCS* : [value] | [value] */,
			(0 << 16) | 6	/* "endpackage" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[66] = createParserRuleValue(66, "ParameterCS", -1,
			createSerializationRules(
				28	/* ParameterCS-0: (NamedElementCS::name=UnrestrictedName ':')[V0:?] TypedElementCS::ownedType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[67] = createParserRuleValue(67, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 3	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[68] = createParserRuleValue(68, "PatternExpCS", -1,
			createSerializationRules(
				68	/* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* patternVariableName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPatternType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[69] = createParserRuleValue(69, "PrefixedLetExpCS", 28 /* LetExpCS|PrefixedLetExpCS */,
			createSerializationRules(
				47	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				69	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedLetExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LetExpCS : [value] | [value] */
		);
		grammarRuleValues[70] = createParserRuleValue(70, "PrefixedPrimaryExpCS", 55 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				34	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				36	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				44	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				45	/* InvalidLiteralExpCS-0: 'invalid' */,
				46	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				49	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				53	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				65	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				66	/* NullLiteralExpCS-0: 'null' */,
				67	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				70	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				73	/* SelfExpCS-0: 'self' */,
				78	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				79	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				89	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				98	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrimaryExpCS : [value] | [value] */
		);
		grammarRuleValues[71] = createParserRuleValue(71, "PrimaryExpCS", 54 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				34	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				36	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				44	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				45	/* InvalidLiteralExpCS-0: 'invalid' */,
				46	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				49	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				53	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				65	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				66	/* NullLiteralExpCS-0: 'null' */,
				67	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				73	/* SelfExpCS-0: 'self' */,
				78	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				79	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				89	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				98	/* UnlimitedNaturalLiteralExpCS-0: '*' */
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
		grammarRuleValues[72] = createParserRuleValue(72, "PrimitiveLiteralExpCS", 53 /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				34	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				45	/* InvalidLiteralExpCS-0: 'invalid' */,
				66	/* NullLiteralExpCS-0: 'null' */,
				67	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				78	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				98	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* NumberLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* StringLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* BooleanLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* UnlimitedNaturalLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* InvalidLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* NullLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[73] = createParserRuleValue(73, "PrimitiveTypeCS", -1,
			createSerializationRules(
				71	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */
			),
			(0 << 16) | 6	/* name=PrimitiveTypeIdentifier : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[74] = new DataTypeRuleValue(74, "PrimitiveTypeIdentifier");
		grammarRuleValues[75] = createParserRuleValue(75, "PropertyContextDeclCS", -1,
			createSerializationRules(
				29	/* PropertyContextDeclCS-0: 'context' PathNameDeclCS::ownedPathName=UnreservedPathNameCS ':' FeatureContextDeclCS::ownedType=TypeExpCS ('derive' ':' PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS)[V0:*] ('init' ':' PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS)[V1:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* "context" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=UnreservedPathNameCS : [value] | [value] */,
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
				72	/* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
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
				73	/* SelfExpCS-0: 'self' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SelfExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "self" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[81] = createParserRuleValue(81, "ShadowPartCS", -1,
			createSerializationRules(
				74	/* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */,
				75	/* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* referredProperty=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 0	/* ownedInitExpression=StringLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[82] = createParserRuleValue(82, "SimplePathNameCS", -1,
			createSerializationRules(
				76	/* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */
			),
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */
		);
		grammarRuleValues[83] = createParserRuleValue(83, "SpecificationCS", -1,
			createSerializationRules(
				30	/* SpecificationCS-0: ExpSpecificationCS::ownedExpression=ExpCS */,
				31	/* SpecificationCS-1: SpecificationCS::exprString=UNQUOTED_STRING */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 6	/* exprString=UNQUOTED_STRING : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[84] = createParserRuleValue(84, "SquareBracketedClauseCS", -1,
			createSerializationRules(
				77	/* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
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
				78	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			),
			(0 << 16) | 2	/* segments+=StringLiteral+ : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[87] = createParserRuleValue(87, "TemplateBindingCS", -1,
			createSerializationRules(
				11	/* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
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
				12	/* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[89] = createParserRuleValue(89, "TemplateSignatureCS", -1,
			createSerializationRules(
				32	/* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */,
				33	/* TemplateSignatureCS-1: '<' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] '>' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				79	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				80	/* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 6	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[92] = createParserRuleValue(92, "TuplePartCS", -1,
			createSerializationRules(
				81	/* TuplePartCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 6	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[93] = createParserRuleValue(93, "TupleTypeCS", -1,
			createSerializationRules(
				82	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
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
		grammarRuleValues[94] = createParserRuleValue(94, "TypeExpCS", -1,
			createSerializationRules(
				83	/* TypeExpCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				84	/* TypeExpCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				85	/* TypeExpCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				86	/* TypeExpCS-3: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				87	/* TypeExpCS-4: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				88	/* TypeExpCS-5: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[95] = createParserRuleValue(95, "TypeExpWithoutMultiplicityCS", 45 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				39	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				40	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				51	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				71	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				82	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				94	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeNameExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionPatternCS : [value] | [value] */
		);
		grammarRuleValues[96] = createParserRuleValue(96, "TypeLiteralCS", 43 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */,
			createSerializationRules(
				40	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				51	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				71	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				82	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */
		);
		grammarRuleValues[97] = createParserRuleValue(97, "TypeLiteralExpCS", -1,
			createSerializationRules(
				89	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			(0 << 16) | 2	/* ownedType=TypeLiteralWithMultiplicityCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[98] = createParserRuleValue(98, "TypeLiteralWithMultiplicityCS", -1,
			createSerializationRules(
				90	/* TypeLiteralWithMultiplicityCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				91	/* TypeLiteralWithMultiplicityCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				92	/* TypeLiteralWithMultiplicityCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				93	/* TypeLiteralWithMultiplicityCS-3: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[99] = createParserRuleValue(99, "TypeNameExpCS", -1,
			createSerializationRules(
				94	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
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
				13	/* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
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
				40	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				51	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				71	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				82	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				14	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */,
				16	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[102] = createParserRuleValue(102, "TypedRefCS", 49 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				40	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				51	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				71	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				82	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				14	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[103] = createParserRuleValue(103, "TypedTypeRefCS", -1,
			createSerializationRules(
				14	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
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
				95	/* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */,
				96	/* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 6	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PathElementWithURICS} : [value] | [value] */,
			(0 << 16) | 6	/* referredElement=URI : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[108] = createParserRuleValue(108, "URIPathNameCS", -1,
			createSerializationRules(
				97	/* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
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
				98	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {UnlimitedNaturalLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 6	/* "*" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[111] = new DataTypeRuleValue(111, "UnreservedName");
		grammarRuleValues[112] = createParserRuleValue(112, "UnreservedPathNameCS", -1,
			createSerializationRules(
				15	/* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 3	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[113] = new DataTypeRuleValue(113, "UnrestrictedName");
		grammarRuleValues[114] = new TerminalRuleValue(114, "WS");
		grammarRuleValues[115] = createParserRuleValue(115, "WildcardTypeRefCS", -1,
			createSerializationRules(
				16	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
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
		// 0: CoIteratorVariableCS
		grammarRuleVectors[0] = new GrammarRuleVector(0x10L);
		// 1: CollectionLiteralPartCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x40L);
		// 2: CollectionTypeCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x100L);
		// 3: ConstraintCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x1000L);
		// 4: ContextDeclCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x2000L);
		// 5: CurlyBracketedClauseCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x4000L);
		// 6: DefCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x10000L);
		// 7: DefParameterCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x40000L);
		// 8: DefCS|DefOperationCS|DefPropertyCS
		grammarRuleVectors[8] = new GrammarRuleVector(0xb0000L);
		// 9: ElseIfThenExpCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x400000L);
		// 10: ExpCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x20000000L);
		// 11: FirstPathElementCS
		grammarRuleVectors[11] = new GrammarRuleVector(0x40000000L);
		// 12: ImportCS
		grammarRuleVectors[12] = new GrammarRuleVector(0x800000000L);
		// 13: LetVariableCS
		grammarRuleVectors[13] = new GrammarRuleVector(0x40000000000L);
		// 14: MapLiteralPartCS
		grammarRuleVectors[14] = new GrammarRuleVector(0x400000000000L);
		// 15: MapTypeCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x800000000000L);
		// 16: MultiplicityCS
		grammarRuleVectors[16] = new GrammarRuleVector(0x4000000000000L);
		// 17: NavigatingArgExpCS
		grammarRuleVectors[17] = new GrammarRuleVector(0x80000000000000L);
		// 18: NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[18] = new GrammarRuleVector(0x700000000000000L);
		// 19: NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[19] = new GrammarRuleVector(0x740000000000000L);
		// 20: NextPathElementCS
		grammarRuleVectors[20] = new GrammarRuleVector(0x2000000000000000L);
		// 21: FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[21] = new GrammarRuleVector(0x2000000040000000L);
		// 22: PackageDeclarationCS
		grammarRuleVectors[22] = new GrammarRuleVector(0x0L,0x2L);
		// 23: ParameterCS
		grammarRuleVectors[23] = new GrammarRuleVector(0x0L,0x4L);
		// 24: PathNameCS
		grammarRuleVectors[24] = new GrammarRuleVector(0x0L,0x8L);
		// 25: PatternExpCS
		grammarRuleVectors[25] = new GrammarRuleVector(0x0L,0x10L);
		// 26: ExpCS|PatternExpCS
		grammarRuleVectors[26] = new GrammarRuleVector(0x20000000L,0x10L);
		// 27: PrefixedLetExpCS
		grammarRuleVectors[27] = new GrammarRuleVector(0x0L,0x20L);
		// 28: LetExpCS|PrefixedLetExpCS
		grammarRuleVectors[28] = new GrammarRuleVector(0x20000000000L,0x20L);
		// 29: PrefixedPrimaryExpCS
		grammarRuleVectors[29] = new GrammarRuleVector(0x0L,0x40L);
		// 30: ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS
		grammarRuleVectors[30] = new GrammarRuleVector(0x2008L,0x801L);
		// 31: RoundBracketedClauseCS
		grammarRuleVectors[31] = new GrammarRuleVector(0x0L,0x1000L);
		// 32: ShadowPartCS
		grammarRuleVectors[32] = new GrammarRuleVector(0x0L,0x20000L);
		// 33: SpecificationCS
		grammarRuleVectors[33] = new GrammarRuleVector(0x0L,0x80000L);
		// 34: SquareBracketedClauseCS
		grammarRuleVectors[34] = new GrammarRuleVector(0x0L,0x100000L);
		// 35: StringLiteralExpCS
		grammarRuleVectors[35] = new GrammarRuleVector(0x0L,0x400000L);
		// 36: TemplateBindingCS
		grammarRuleVectors[36] = new GrammarRuleVector(0x0L,0x800000L);
		// 37: TemplateParameterSubstitutionCS
		grammarRuleVectors[37] = new GrammarRuleVector(0x0L,0x1000000L);
		// 38: TemplateSignatureCS
		grammarRuleVectors[38] = new GrammarRuleVector(0x0L,0x2000000L);
		// 39: TupleLiteralPartCS
		grammarRuleVectors[39] = new GrammarRuleVector(0x0L,0x8000000L);
		// 40: TuplePartCS
		grammarRuleVectors[40] = new GrammarRuleVector(0x0L,0x10000000L);
		// 41: TypeExpCS
		grammarRuleVectors[41] = new GrammarRuleVector(0x0L,0x40000000L);
		// 42: TypeExpWithoutMultiplicityCS
		grammarRuleVectors[42] = new GrammarRuleVector(0x0L,0x80000000L);
		// 43: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
		grammarRuleVectors[43] = new GrammarRuleVector(0x800000000100L,0x120000200L);
		// 44: TypeLiteralWithMultiplicityCS
		grammarRuleVectors[44] = new GrammarRuleVector(0x0L,0x400000000L);
		// 45: CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[45] = new GrammarRuleVector(0x800000000180L,0x9a0000200L);
		// 46: TypeParameterCS
		grammarRuleVectors[46] = new GrammarRuleVector(0x0L,0x1000000000L);
		// 47: TypeRefCS
		grammarRuleVectors[47] = new GrammarRuleVector(0x0L,0x2000000000L);
		// 48: TypedRefCS
		grammarRuleVectors[48] = new GrammarRuleVector(0x0L,0x4000000000L);
		// 49: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[49] = new GrammarRuleVector(0x800000000100L,0xc120000200L);
		// 50: NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[50] = new GrammarRuleVector(0x2000000000000000L,0x80000000000L);
		// 51: FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[51] = new GrammarRuleVector(0x2000000040000000L,0x80000000000L);
		// 52: URIPathNameCS
		grammarRuleVectors[52] = new GrammarRuleVector(0x0L,0x100000000000L);
		// 53: BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[53] = new GrammarRuleVector(0xc000002000000004L,0x400000400100L);
		// 54: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[54] = new GrammarRuleVector(0xd020212400000024L,0x400204410180L);
		// 55: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[55] = new GrammarRuleVector(0xd020212400000024L,0x4002044101c0L);
		// 56: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[56] = new GrammarRuleVector(0xd020232400000024L,0x4002044101e0L);
		// 57: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[57] = new GrammarRuleVector(0xd020232420000024L,0x4002044101e0L);
		// 58: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[58] = new GrammarRuleVector(0xd0a0232420000024L,0x4002044101e0L);
		// 59: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[59] = new GrammarRuleVector(0xd020232420000024L,0x4002044101f0L);
		// 60: UnreservedPathNameCS
		grammarRuleVectors[60] = new GrammarRuleVector(0x0L,0x1000000000000L);
		// 61: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[61] = new GrammarRuleVector(0x800000000100L,0x800e120000200L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// 0: assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(120);
		// 1: assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(121);
		// 2: assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(124);
		// 3: assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(125);
		// 4: assert (|CollectionPatternCS::ownedType| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(127);
		// 5: assert (|CollectionTypeCS::name| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(128);
		// 6: assert (|ConstraintCS::ownedSpecification| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(129);
		// 7: assert (|ContextCS::ownedExpression| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(130);
		// 8: assert (|DefCS::ownedSpecification| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(133);
		// 9: assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(136);
		// 10: assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(137);
		// 11: assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(138);
		// 12: assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(139);
		// 13: assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(140);
		// 14: assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(141);
		// 15: assert (|ImportCS::ownedPathName| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(142);
		// 16: assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(143);
		// 17: assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(144);
		// 18: assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(145);
		// 19: assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(149);
		// 20: assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(150);
		// 21: assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(151);
		// 22: assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(152);
		// 23: assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(153);
		// 24: assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(154);
		// 25: assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(155);
		// 26: assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(156);
		// 27: assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(157);
		// 28: assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(158);
		// 29: assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(159);
		// 30: assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(160);
		// 31: assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(161);
		// 32: assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(162);
		// 33: assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(163);
		// 34: assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(164);
		// 35: assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(165);
		// 36: assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(166);
		// 37: assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(169);
		// 38: assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(170);
		// 39: assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(171);
		// 40: assert (|PathNameDeclCS::ownedPathName| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(172);
		// 41: assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(173);
		// 42: assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(174);
		// 43: assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(175);
		// 44: assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(176);
		// 45: assert (|SpecificationCS::exprString| - 1) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(177);
		// 46: assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[46] = createMatchStep_Assert(180);
		// 47: assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[47] = createMatchStep_Assert(183);
		// 48: assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[48] = createMatchStep_Assert(186);
		// 49: assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[49] = createMatchStep_Assert(187);
		// 50: assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[50] = createMatchStep_Assert(190);
		// 51: assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[51] = createMatchStep_Assert(191);
		// 52: assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[52] = createMatchStep_Assert(192);
		// 53: assert |CollectionLiteralPartCS::ownedLastExpression| == 0
		serializationMatchSteps[53] = createMatchStep_Assert(15);
		// 54: assert |CollectionPatternCS::ownedPatternGuard| == 0
		serializationMatchSteps[54] = createMatchStep_Assert(17);
		// 55: assert |ConstraintCS::stereotype| == 0
		serializationMatchSteps[55] = createMatchStep_Assert(27);
		// 56: assert |CurlyBracketedClauseCS::value| == 0
		serializationMatchSteps[56] = createMatchStep_Assert(30);
		// 57: assert |ExpSpecificationCS::ownedExpression| == 0
		serializationMatchSteps[57] = createMatchStep_Assert(34);
		// 58: assert |IfExpCS::isImplicit| == 0
		serializationMatchSteps[58] = createMatchStep_Assert(36);
		// 59: assert |LetExpCS::isImplicit| == 0
		serializationMatchSteps[59] = createMatchStep_Assert(47);
		// 60: assert |ModelElementCS::ownedAnnotations| == 0
		serializationMatchSteps[60] = createMatchStep_Assert(58);
		// 61: assert |MultiplicityCS::isNullFree| == 0
		serializationMatchSteps[61] = createMatchStep_Assert(62);
		// 62: assert |NamedElementCS::name| == 0
		serializationMatchSteps[62] = createMatchStep_Assert(64);
		// 63: assert |NavigatingArgCS::ownedCoIterator| == 0
		serializationMatchSteps[63] = createMatchStep_Assert(65);
		// 64: assert |NavigatingArgCS::ownedInitExpression| == 0
		serializationMatchSteps[64] = createMatchStep_Assert(66);
		// 65: assert |NavigatingArgCS::ownedNameExpression| == 0
		serializationMatchSteps[65] = createMatchStep_Assert(67);
		// 66: assert |NavigatingArgCS::ownedType| == 0
		serializationMatchSteps[66] = createMatchStep_Assert(68);
		// 67: assert |NavigatingArgCS::prefix| == 0
		serializationMatchSteps[67] = createMatchStep_Assert(72);
		// 68: assert |RootCS::ownedImports| == 0
		serializationMatchSteps[68] = createMatchStep_Assert(88);
		// 69: assert |SelfExpCS::name| == 0
		serializationMatchSteps[69] = createMatchStep_Assert(89);
		// 70: assert |ShadowPartCS::referredProperty| == 0
		serializationMatchSteps[70] = createMatchStep_Assert(91);
		// 71: assert |SpecificationCS::exprString| == 0
		serializationMatchSteps[71] = createMatchStep_Assert(92);
		// 72: assert |TypeLiteralExpCS::ownedPathName| == 0
		serializationMatchSteps[72] = createMatchStep_Assert(103);
		// 73: assert |TypedElementCS::isOptional| == 0
		serializationMatchSteps[73] = createMatchStep_Assert(109);
		// 74: assert |TypedElementCS::qualifiers| == 0
		serializationMatchSteps[74] = createMatchStep_Assert(111);
		// 75: assert |TypedRefCS::ownedMultiplicity| == 0
		serializationMatchSteps[75] = createMatchStep_Assert(112);
		// 76: assert |TypedTypeRefCS::isTypeof| == 0
		serializationMatchSteps[76] = createMatchStep_Assert(113);
		// 77: assert |VariableCS::ownedInitExpression| == 0
		serializationMatchSteps[77] = createMatchStep_Assert(116);
		// 78: assert |WildcardTypeRefCS::ownedSuper| == 0
		serializationMatchSteps[78] = createMatchStep_Assert(119);
		// 79: assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[79] = createMatchStep_Assign(0, 123);
		// 80: assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchSteps[80] = createMatchStep_Assign(0, 132);
		// 81: assign V0 = (|LetExpCS::ownedVariables| - 1)
		serializationMatchSteps[81] = createMatchStep_Assign(0, 146);
		// 82: assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[82] = createMatchStep_Assign(0, 148);
		// 83: assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[83] = createMatchStep_Assign(0, 171);
		// 84: assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[84] = createMatchStep_Assign(0, 178);
		// 85: assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[85] = createMatchStep_Assign(0, 179);
		// 86: assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[86] = createMatchStep_Assign(0, 181);
		// 87: assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[87] = createMatchStep_Assign(0, 182);
		// 88: assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[88] = createMatchStep_Assign(0, 185);
		// 89: assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[89] = createMatchStep_Assign(0, 189);
		// 90: assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchSteps[90] = createMatchStep_Assign(0, 7);
		// 91: assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchSteps[91] = createMatchStep_Assign(0, 15);
		// 92: assign V0 = |CollectionPatternCS::restVariableName|
		serializationMatchSteps[92] = createMatchStep_Assign(0, 19);
		// 93: assign V0 = |CollectionTypeCS::ownedType|
		serializationMatchSteps[93] = createMatchStep_Assign(0, 22);
		// 94: assign V0 = |DefCS::isStatic.'static'|
		serializationMatchSteps[94] = createMatchStep_Assign(0, 31);
		// 95: assign V0 = |IfExpCS::ownedIfThenExpressions|
		serializationMatchSteps[95] = createMatchStep_Assign(0, 39);
		// 96: assign V0 = |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchSteps[96] = createMatchStep_Assign(0, 50);
		// 97: assign V0 = |MapTypeCS::ownedValueType|
		serializationMatchSteps[97] = createMatchStep_Assign(0, 57);
		// 98: assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[98] = createMatchStep_Assign(0, 60);
		// 99: assign V0 = |NamedElementCS::name|
		serializationMatchSteps[99] = createMatchStep_Assign(0, 64);
		// 100: assign V0 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[100] = createMatchStep_Assign(0, 65);
		// 101: assign V0 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[101] = createMatchStep_Assign(0, 66);
		// 102: assign V0 = |NavigatingArgCS::ownedType|
		serializationMatchSteps[102] = createMatchStep_Assign(0, 68);
		// 103: assign V0 = |PackageDeclarationCS::ownedInvariants|
		serializationMatchSteps[103] = createMatchStep_Assign(0, 81);
		// 104: assign V0 = |PatternExpCS::patternVariableName|
		serializationMatchSteps[104] = createMatchStep_Assign(0, 86);
		// 105: assign V0 = |RootCS::ownedImports|
		serializationMatchSteps[105] = createMatchStep_Assign(0, 88);
		// 106: assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[106] = createMatchStep_Assign(0, 94);
		// 107: assign V0 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[107] = createMatchStep_Assign(0, 99);
		// 108: assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[108] = createMatchStep_Assign(0, 105);
		// 109: assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[109] = createMatchStep_Assign(0, 112);
		// 110: assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[110] = createMatchStep_Assign(0, 114);
		// 111: assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[111] = createMatchStep_Assign(0, 117);
		// 112: assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[112] = createMatchStep_Assign(0, 118);
		// 113: assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[113] = createMatchStep_Assign(1, 122);
		// 114: assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[114] = createMatchStep_Assign(1, 126);
		// 115: assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[115] = createMatchStep_Assign(1, 131);
		// 116: assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[116] = createMatchStep_Assign(1, 147);
		// 117: assign V1 = (|OperationContextDeclCS::ownedParameters| > 0)
		serializationMatchSteps[117] = createMatchStep_Assign(1, 168);
		// 118: assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[118] = createMatchStep_Assign(1, 185);
		// 119: assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[119] = createMatchStep_Assign(1, 188);
		// 120: assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[120] = createMatchStep_Assign(1, 6);
		// 121: assign V1 = |ClassifierContextDeclCS::selfName|
		serializationMatchSteps[121] = createMatchStep_Assign(1, 11);
		// 122: assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[122] = createMatchStep_Assign(1, 21);
		// 123: assign V1 = |CompleteOCLDocumentCS::ownedPackages|
		serializationMatchSteps[123] = createMatchStep_Assign(1, 24);
		// 124: assign V1 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[124] = createMatchStep_Assign(1, 25);
		// 125: assign V1 = |ImportCS::isAll.'::*'|
		serializationMatchSteps[125] = createMatchStep_Assign(1, 43);
		// 126: assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[126] = createMatchStep_Assign(1, 65);
		// 127: assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[127] = createMatchStep_Assign(1, 66);
		// 128: assign V1 = |PackageDeclarationCS::ownedContexts|
		serializationMatchSteps[128] = createMatchStep_Assign(1, 80);
		// 129: assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[129] = createMatchStep_Assign(1, 95);
		// 130: assign V1 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[130] = createMatchStep_Assign(1, 99);
		// 131: assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[131] = createMatchStep_Assign(1, 107);
		// 132: assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[132] = createMatchStep_Assign(1, 112);
		// 133: assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[133] = createMatchStep_Assign(1, 117);
		// 134: assign V2 = (|DefOperationCS::ownedParameters| > 0)
		serializationMatchSteps[134] = createMatchStep_Assign(2, 135);
		// 135: assign V2 = (|OperationContextDeclCS::ownedParameters| - 1)
		serializationMatchSteps[135] = createMatchStep_Assign(2, 167);
		// 136: assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[136] = createMatchStep_Assign(2, 184);
		// 137: assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[137] = createMatchStep_Assign(2, 4);
		// 138: assign V2 = |ClassifierContextDeclCS::ownedInvariants|
		serializationMatchSteps[138] = createMatchStep_Assign(2, 10);
		// 139: assign V2 = |CompleteOCLDocumentCS::ownedContexts|
		serializationMatchSteps[139] = createMatchStep_Assign(2, 23);
		// 140: assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[140] = createMatchStep_Assign(2, 112);
		// 141: assign V3 = (|DefOperationCS::ownedParameters| - 1)
		serializationMatchSteps[141] = createMatchStep_Assign(3, 134);
		// 142: assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[142] = createMatchStep_Assign(3, 3);
		// 143: assign V3 = |ClassifierContextDeclCS::ownedDefinitions|
		serializationMatchSteps[143] = createMatchStep_Assign(3, 9);
		// 144: assign V3 = |FeatureContextDeclCS::ownedType|
		serializationMatchSteps[144] = createMatchStep_Assign(3, 35);
		// 145: assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[145] = createMatchStep_Assign(3, 112);
		// 146: assign V4 = |OperationContextDeclCS::ownedBodies|
		serializationMatchSteps[146] = createMatchStep_Assign(4, 75);
		// 147: assign V4 = |TypedElementCS::ownedType|
		serializationMatchSteps[147] = createMatchStep_Assign(4, 110);
		// 148: assign V5 = |OperationContextDeclCS::ownedPostconditions|
		serializationMatchSteps[148] = createMatchStep_Assign(5, 77);
		// 149: assign V6 = |OperationContextDeclCS::ownedPreconditions|
		serializationMatchSteps[149] = createMatchStep_Assign(6, 78);
		// 150: check-rule basecs::ConstraintCS.ownedMessageSpecification : 83
		serializationMatchSteps[150] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 33/*SpecificationCS*/);
		// 151: check-rule basecs::ConstraintCS.ownedSpecification : 83
		serializationMatchSteps[151] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 33/*SpecificationCS*/);
		// 152: check-rule basecs::ImportCS.ownedPathName : 108
		serializationMatchSteps[152] = createMatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 52/*URIPathNameCS*/);
		// 153: check-rule basecs::PathNameCS.ownedPathElements : 30
		serializationMatchSteps[153] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 11/*FirstPathElementCS*/);
		// 154: check-rule basecs::PathNameCS.ownedPathElements : 61
		serializationMatchSteps[154] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 20/*NextPathElementCS*/);
		// 155: check-rule basecs::RootCS.ownedImports : 35
		serializationMatchSteps[155] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/*ImportCS*/);
		// 156: check-rule basecs::TemplateBindingCS.ownedMultiplicity : 50
		serializationMatchSteps[156] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 16/*MultiplicityCS*/);
		// 157: check-rule basecs::TemplateBindingCS.ownedSubstitutions : 88
		serializationMatchSteps[157] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 37/*TemplateParameterSubstitutionCS*/);
		// 158: check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 8|47|73|93|96|101|102|103|115
		serializationMatchSteps[158] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 61/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS*/);
		// 159: check-rule basecs::TemplateSignatureCS.ownedParameters : 100
		serializationMatchSteps[159] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 46/*TypeParameterCS*/);
		// 160: check-rule basecs::TemplateableElementCS.ownedSignature : 89
		serializationMatchSteps[160] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 38/*TemplateSignatureCS*/);
		// 161: check-rule basecs::TupleTypeCS.ownedParts : 92
		serializationMatchSteps[161] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 40/*TuplePartCS*/);
		// 162: check-rule basecs::TypeParameterCS.ownedExtends : 8|47|73|93|96|102|103
		serializationMatchSteps[162] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 49/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS*/);
		// 163: check-rule basecs::TypedElementCS.ownedType : 94
		serializationMatchSteps[163] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 41/*TypeExpCS*/);
		// 164: check-rule basecs::TypedRefCS.ownedMultiplicity : 50
		serializationMatchSteps[164] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/*MultiplicityCS*/);
		// 165: check-rule basecs::TypedTypeRefCS.ownedBinding : 87
		serializationMatchSteps[165] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 36/*TemplateBindingCS*/);
		// 166: check-rule basecs::TypedTypeRefCS.ownedPathName : 67
		serializationMatchSteps[166] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 24/*PathNameCS*/);
		// 167: check-rule basecs::WildcardTypeRefCS.ownedExtends : 8|47|73|93|96|102|103
		serializationMatchSteps[167] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 49/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS*/);
		// 168: check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16|17|19
		serializationMatchSteps[168] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 8/*DefCS|DefOperationCS|DefPropertyCS*/);
		// 169: check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12
		serializationMatchSteps[169] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 3/*ConstraintCS*/);
		// 170: check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : 3|13|64|75
		serializationMatchSteps[170] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, 30/*ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS*/);
		// 171: check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : 65
		serializationMatchSteps[171] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, 22/*PackageDeclarationCS*/);
		// 172: check-rule completeoclcs::DefCS.ownedSpecification : 83
		serializationMatchSteps[172] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 33/*SpecificationCS*/);
		// 173: check-rule completeoclcs::DefOperationCS.ownedParameters : 18
		serializationMatchSteps[173] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, 7/*DefParameterCS*/);
		// 174: check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94
		serializationMatchSteps[174] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 41/*TypeExpCS*/);
		// 175: check-rule completeoclcs::OperationContextDeclCS.ownedBodies : 83
		serializationMatchSteps[175] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, 33/*SpecificationCS*/);
		// 176: check-rule completeoclcs::OperationContextDeclCS.ownedParameters : 66
		serializationMatchSteps[176] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, 23/*ParameterCS*/);
		// 177: check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : 12
		serializationMatchSteps[177] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, 3/*ConstraintCS*/);
		// 178: check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : 12
		serializationMatchSteps[178] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, 3/*ConstraintCS*/);
		// 179: check-rule completeoclcs::PackageDeclarationCS.ownedContexts : 3|13|64|75
		serializationMatchSteps[179] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, 30/*ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS*/);
		// 180: check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : 12
		serializationMatchSteps[180] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, 3/*ConstraintCS*/);
		// 181: check-rule completeoclcs::PathNameDeclCS.ownedPathName : 112
		serializationMatchSteps[181] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 60/*UnreservedPathNameCS*/);
		// 182: check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[182] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/*CurlyBracketedClauseCS*/);
		// 183: check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67
		serializationMatchSteps[183] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 24/*PathNameCS*/);
		// 184: check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76
		serializationMatchSteps[184] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 31/*RoundBracketedClauseCS*/);
		// 185: check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84
		serializationMatchSteps[185] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 34/*SquareBracketedClauseCS*/);
		// 186: check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6
		serializationMatchSteps[186] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 1/*CollectionLiteralPartCS*/);
		// 187: check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8
		serializationMatchSteps[187] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// 188: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[188] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 189: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 68
		serializationMatchSteps[189] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 25/*PatternExpCS*/);
		// 190: check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[190] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 191: check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68
		serializationMatchSteps[191] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 25/*PatternExpCS*/);
		// 192: check-rule essentialoclcs::CollectionPatternCS.ownedType : 8
		serializationMatchSteps[192] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// 193: check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50
		serializationMatchSteps[193] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 16/*MultiplicityCS*/);
		// 194: check-rule essentialoclcs::CollectionTypeCS.ownedType : 7|8|47|73|93|95|96|99
		serializationMatchSteps[194] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 45/*CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS*/);
		// 195: check-rule essentialoclcs::ContextCS.ownedExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[195] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 196: check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 81
		serializationMatchSteps[196] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 32/*ShadowPartCS*/);
		// 197: check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[197] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 198: check-rule essentialoclcs::IfExpCS.ownedCondition : 2|5|29|34|37|40|41|45|53|60|62|63|68|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[198] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 59/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 199: check-rule essentialoclcs::IfExpCS.ownedElseExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[199] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 200: check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22
		serializationMatchSteps[200] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 9/*ElseIfThenExpCS*/);
		// 201: check-rule essentialoclcs::IfExpCS.ownedThenExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[201] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 202: check-rule essentialoclcs::IfThenExpCS.ownedCondition : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[202] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 203: check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[203] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 204: check-rule essentialoclcs::InfixExpCS.ownedLeft : 2|5|34|37|40|45|53|60|62|63|70|71|72|80|86|90|97|110
		serializationMatchSteps[204] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 55/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 205: check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[205] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 206: check-rule essentialoclcs::LetExpCS.ownedInExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[206] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 207: check-rule essentialoclcs::LetExpCS.ownedVariables : 42
		serializationMatchSteps[207] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 13/*LetVariableCS*/);
		// 208: check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 76
		serializationMatchSteps[208] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 31/*RoundBracketedClauseCS*/);
		// 209: check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46
		serializationMatchSteps[209] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 14/*MapLiteralPartCS*/);
		// 210: check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47
		serializationMatchSteps[210] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 15/*MapTypeCS*/);
		// 211: check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[211] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 212: check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[212] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 213: check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94
		serializationMatchSteps[213] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 41/*TypeExpCS*/);
		// 214: check-rule essentialoclcs::MapTypeCS.ownedValueType : 94
		serializationMatchSteps[214] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 41/*TypeExpCS*/);
		// 215: check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4
		serializationMatchSteps[215] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/*CoIteratorVariableCS*/);
		// 216: check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[216] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 217: check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 2|5|29|34|37|40|41|45|53|55|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[217] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 58/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 218: check-rule essentialoclcs::NavigatingArgCS.ownedType : 94
		serializationMatchSteps[218] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 41/*TypeExpCS*/);
		// 219: check-rule essentialoclcs::NestedExpCS.ownedExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[219] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 220: check-rule essentialoclcs::OperatorExpCS.ownedRight : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[220] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 221: check-rule essentialoclcs::OperatorExpCS.ownedRight : 2|5|34|37|40|45|53|60|62|63|70|71|72|80|86|90|97|110
		serializationMatchSteps[221] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 55/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 222: check-rule essentialoclcs::OperatorExpCS.ownedRight : 41|69
		serializationMatchSteps[222] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 28/*LetExpCS|PrefixedLetExpCS*/);
		// 223: check-rule essentialoclcs::PatternExpCS.ownedPatternType : 94
		serializationMatchSteps[223] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 41/*TypeExpCS*/);
		// 224: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 2|5|29|34|37|40|41|45|53|60|62|63|68|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[224] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 59/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 225: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 86
		serializationMatchSteps[225] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 35/*StringLiteralExpCS*/);
		// 226: check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[226] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 227: check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91
		serializationMatchSteps[227] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 39/*TupleLiteralPartCS*/);
		// 228: check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98
		serializationMatchSteps[228] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 44/*TypeLiteralWithMultiplicityCS*/);
		// 229: check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14
		serializationMatchSteps[229] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/*CurlyBracketedClauseCS*/);
		// 230: check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67
		serializationMatchSteps[230] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 24/*PathNameCS*/);
		// 231: check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[231] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 232: check-rule essentialoclcs::VariableCS.ownedInitExpression : 2|5|29|34|37|40|41|45|53|60|62|63|69|70|71|72|80|86|90|97|110
		serializationMatchSteps[232] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 233: check-rule essentialoclcs::VariableCS.ownedType : 94
		serializationMatchSteps[233] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 41/*TypeExpCS*/);
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
		// 8: |BooleanLiteralExpCS::symbol.'false|true'|
		serializationMatchTerms[8] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 7 /* 'false|true' */);
		// 9: |ClassifierContextDeclCS::ownedDefinitions|
		serializationMatchTerms[9] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS);
		// 10: |ClassifierContextDeclCS::ownedInvariants|
		serializationMatchTerms[10] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS);
		// 11: |ClassifierContextDeclCS::selfName|
		serializationMatchTerms[11] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME);
		// 12: |CollectionLiteralExpCS::ownedParts|
		serializationMatchTerms[12] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		// 13: |CollectionLiteralExpCS::ownedType|
		serializationMatchTerms[13] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		// 14: |CollectionLiteralPartCS::ownedExpression|
		serializationMatchTerms[14] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		// 15: |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchTerms[15] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		// 16: |CollectionPatternCS::ownedParts|
		serializationMatchTerms[16] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		// 17: |CollectionPatternCS::ownedPatternGuard|
		serializationMatchTerms[17] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD);
		// 18: |CollectionPatternCS::ownedType|
		serializationMatchTerms[18] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		// 19: |CollectionPatternCS::restVariableName|
		serializationMatchTerms[19] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		// 20: |CollectionTypeCS::name|
		serializationMatchTerms[20] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		// 21: |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchTerms[21] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		// 22: |CollectionTypeCS::ownedType|
		serializationMatchTerms[22] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		// 23: |CompleteOCLDocumentCS::ownedContexts|
		serializationMatchTerms[23] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS);
		// 24: |CompleteOCLDocumentCS::ownedPackages|
		serializationMatchTerms[24] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES);
		// 25: |ConstraintCS::ownedMessageSpecification|
		serializationMatchTerms[25] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		// 26: |ConstraintCS::ownedSpecification|
		serializationMatchTerms[26] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		// 27: |ConstraintCS::stereotype|
		serializationMatchTerms[27] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE);
		// 28: |ContextCS::ownedExpression|
		serializationMatchTerms[28] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		// 29: |CurlyBracketedClauseCS::ownedParts|
		serializationMatchTerms[29] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		// 30: |CurlyBracketedClauseCS::value|
		serializationMatchTerms[30] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__VALUE);
		// 31: |DefCS::isStatic.'static'|
		serializationMatchTerms[31] = createSerializationMatchTermEAttributeSize(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, 8 /* 'static' */);
		// 32: |DefCS::ownedSpecification|
		serializationMatchTerms[32] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION);
		// 33: |DefOperationCS::ownedParameters|
		serializationMatchTerms[33] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS);
		// 34: |ExpSpecificationCS::ownedExpression|
		serializationMatchTerms[34] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		// 35: |FeatureContextDeclCS::ownedType|
		serializationMatchTerms[35] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE);
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
		// 46: |LambdaLiteralExpCS::ownedExpressionCS|
		serializationMatchTerms[46] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		// 47: |LetExpCS::isImplicit|
		serializationMatchTerms[47] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__IS_IMPLICIT);
		// 48: |LetExpCS::ownedInExpression|
		serializationMatchTerms[48] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		// 49: |LetExpCS::ownedVariables|
		serializationMatchTerms[49] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		// 50: |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchTerms[50] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// 51: |MapLiteralExpCS::ownedParts|
		serializationMatchTerms[51] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		// 52: |MapLiteralExpCS::ownedType|
		serializationMatchTerms[52] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		// 53: |MapLiteralPartCS::ownedKey|
		serializationMatchTerms[53] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		// 54: |MapLiteralPartCS::ownedValue|
		serializationMatchTerms[54] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		// 55: |MapTypeCS::name.'Map'|
		serializationMatchTerms[55] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 5 /* 'Map' */);
		// 56: |MapTypeCS::ownedKeyType|
		serializationMatchTerms[56] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		// 57: |MapTypeCS::ownedValueType|
		serializationMatchTerms[57] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		// 58: |ModelElementCS::ownedAnnotations|
		serializationMatchTerms[58] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		// 59: |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[59] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// 60: |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[60] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// 61: |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[61] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 10 /* '|1' */);
		// 62: |MultiplicityCS::isNullFree|
		serializationMatchTerms[62] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE);
		// 63: |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[63] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */);
		// 64: |NamedElementCS::name|
		serializationMatchTerms[64] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// 65: |NavigatingArgCS::ownedCoIterator|
		serializationMatchTerms[65] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		// 66: |NavigatingArgCS::ownedInitExpression|
		serializationMatchTerms[66] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		// 67: |NavigatingArgCS::ownedNameExpression|
		serializationMatchTerms[67] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		// 68: |NavigatingArgCS::ownedType|
		serializationMatchTerms[68] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		// 69: |NavigatingArgCS::prefix.','|
		serializationMatchTerms[69] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */);
		// 70: |NavigatingArgCS::prefix.';'|
		serializationMatchTerms[70] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 3 /* ';' */);
		// 71: |NavigatingArgCS::prefix.'|'|
		serializationMatchTerms[71] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 9 /* '|' */);
		// 72: |NavigatingArgCS::prefix|
		serializationMatchTerms[72] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX);
		// 73: |NestedExpCS::ownedExpression|
		serializationMatchTerms[73] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		// 74: |NumberLiteralExpCS::symbol|
		serializationMatchTerms[74] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		// 75: |OperationContextDeclCS::ownedBodies|
		serializationMatchTerms[75] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES);
		// 76: |OperationContextDeclCS::ownedParameters|
		serializationMatchTerms[76] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS);
		// 77: |OperationContextDeclCS::ownedPostconditions|
		serializationMatchTerms[77] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS);
		// 78: |OperationContextDeclCS::ownedPreconditions|
		serializationMatchTerms[78] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS);
		// 79: |OperatorExpCS::ownedRight|
		serializationMatchTerms[79] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		// 80: |PackageDeclarationCS::ownedContexts|
		serializationMatchTerms[80] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS);
		// 81: |PackageDeclarationCS::ownedInvariants|
		serializationMatchTerms[81] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS);
		// 82: |PathElementCS::referredElement|
		serializationMatchTerms[82] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// 83: |PathNameCS::ownedPathElements|
		serializationMatchTerms[83] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// 84: |PathNameDeclCS::ownedPathName|
		serializationMatchTerms[84] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME);
		// 85: |PatternExpCS::ownedPatternType|
		serializationMatchTerms[85] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		// 86: |PatternExpCS::patternVariableName|
		serializationMatchTerms[86] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		// 87: |PrimitiveTypeRefCS::name|
		serializationMatchTerms[87] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		// 88: |RootCS::ownedImports|
		serializationMatchTerms[88] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		// 89: |SelfExpCS::name|
		serializationMatchTerms[89] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SELF_EXP_CS__NAME);
		// 90: |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[90] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// 91: |ShadowPartCS::referredProperty|
		serializationMatchTerms[91] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// 92: |SpecificationCS::exprString|
		serializationMatchTerms[92] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		// 93: |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[93] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// 94: |StringLiteralExpCS::segments|
		serializationMatchTerms[94] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// 95: |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[95] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// 96: |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[96] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// 97: |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[97] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// 98: |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[98] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// 99: |TemplateableElementCS::ownedSignature|
		serializationMatchTerms[99] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		// 100: |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[100] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// 101: |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[101] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 6 /* 'Tuple' */);
		// 102: |TupleTypeCS::ownedParts|
		serializationMatchTerms[102] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// 103: |TypeLiteralExpCS::ownedPathName|
		serializationMatchTerms[103] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_PATH_NAME);
		// 104: |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[104] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// 105: |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[105] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// 106: |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[106] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// 107: |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[107] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// 108: |TypeParameterCS::ownedExtends|
		serializationMatchTerms[108] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// 109: |TypedElementCS::isOptional|
		serializationMatchTerms[109] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__IS_OPTIONAL);
		// 110: |TypedElementCS::ownedType|
		serializationMatchTerms[110] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		// 111: |TypedElementCS::qualifiers|
		serializationMatchTerms[111] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		// 112: |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[112] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// 113: |TypedTypeRefCS::isTypeof|
		serializationMatchTerms[113] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF);
		// 114: |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[114] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// 115: |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[115] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// 116: |VariableCS::ownedInitExpression|
		serializationMatchTerms[116] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		// 117: |VariableCS::ownedType|
		serializationMatchTerms[117] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		// 118: |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[118] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// 119: |WildcardTypeRefCS::ownedSuper|
		serializationMatchTerms[119] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_SUPER);
		// 120: (|AbstractNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[120] = createSerializationMatchTermSubtract(5, 1);
		// 121: (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
		serializationMatchTerms[121] = createSerializationMatchTermSubtract(8, 1);
		// 122: (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[122] = createSerializationMatchTermSubtract(12, 1);
		// 123: (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[123] = createSerializationMatchTermGreaterThan(12, 0);
		// 124: (|CollectionLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[124] = createSerializationMatchTermSubtract(13, 1);
		// 125: (|CollectionLiteralPartCS::ownedExpression| - 1)
		serializationMatchTerms[125] = createSerializationMatchTermSubtract(14, 1);
		// 126: (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchTerms[126] = createSerializationMatchTermSubtract(16, 1);
		// 127: (|CollectionPatternCS::ownedType| - 1)
		serializationMatchTerms[127] = createSerializationMatchTermSubtract(18, 1);
		// 128: (|CollectionTypeCS::name| - 1)
		serializationMatchTerms[128] = createSerializationMatchTermSubtract(20, 1);
		// 129: (|ConstraintCS::ownedSpecification| - 1)
		serializationMatchTerms[129] = createSerializationMatchTermSubtract(26, 1);
		// 130: (|ContextCS::ownedExpression| - 1)
		serializationMatchTerms[130] = createSerializationMatchTermSubtract(28, 1);
		// 131: (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchTerms[131] = createSerializationMatchTermSubtract(29, 1);
		// 132: (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchTerms[132] = createSerializationMatchTermGreaterThan(29, 0);
		// 133: (|DefCS::ownedSpecification| - 1)
		serializationMatchTerms[133] = createSerializationMatchTermSubtract(32, 1);
		// 134: (|DefOperationCS::ownedParameters| - 1)
		serializationMatchTerms[134] = createSerializationMatchTermSubtract(33, 1);
		// 135: (|DefOperationCS::ownedParameters| > 0)
		serializationMatchTerms[135] = createSerializationMatchTermGreaterThan(33, 0);
		// 136: (|ExpSpecificationCS::ownedExpression| - 1)
		serializationMatchTerms[136] = createSerializationMatchTermSubtract(34, 1);
		// 137: (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[137] = createSerializationMatchTermSubtract(37, 1);
		// 138: (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[138] = createSerializationMatchTermSubtract(38, 1);
		// 139: (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[139] = createSerializationMatchTermSubtract(40, 1);
		// 140: (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[140] = createSerializationMatchTermSubtract(41, 1);
		// 141: (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[141] = createSerializationMatchTermSubtract(42, 1);
		// 142: (|ImportCS::ownedPathName| - 1)
		serializationMatchTerms[142] = createSerializationMatchTermSubtract(44, 1);
		// 143: (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[143] = createSerializationMatchTermSubtract(45, 1);
		// 144: (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[144] = createSerializationMatchTermSubtract(46, 1);
		// 145: (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[145] = createSerializationMatchTermSubtract(48, 1);
		// 146: (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[146] = createSerializationMatchTermSubtract(49, 1);
		// 147: (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[147] = createSerializationMatchTermSubtract(51, 1);
		// 148: (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[148] = createSerializationMatchTermGreaterThan(51, 0);
		// 149: (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[149] = createSerializationMatchTermSubtract(52, 1);
		// 150: (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[150] = createSerializationMatchTermSubtract(53, 1);
		// 151: (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[151] = createSerializationMatchTermSubtract(54, 1);
		// 152: (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[152] = createSerializationMatchTermSubtract(55, 1);
		// 153: (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[153] = createSerializationMatchTermSubtract(56, 2);
		// 154: (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[154] = createSerializationMatchTermSubtract(59, 1);
		// 155: (|MultiplicityCS::isNullFree.'|1'| - 1)
		serializationMatchTerms[155] = createSerializationMatchTermSubtract(61, 1);
		// 156: (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[156] = createSerializationMatchTermSubtract(63, 1);
		// 157: (|NamedElementCS::name| - 1)
		serializationMatchTerms[157] = createSerializationMatchTermSubtract(64, 1);
		// 158: (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[158] = createSerializationMatchTermSubtract(65, 1);
		// 159: (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[159] = createSerializationMatchTermSubtract(66, 1);
		// 160: (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[160] = createSerializationMatchTermSubtract(67, 1);
		// 161: (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[161] = createSerializationMatchTermSubtract(68, 1);
		// 162: (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[162] = createSerializationMatchTermSubtract(69, 1);
		// 163: (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[163] = createSerializationMatchTermSubtract(70, 1);
		// 164: (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[164] = createSerializationMatchTermSubtract(71, 1);
		// 165: (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[165] = createSerializationMatchTermSubtract(73, 1);
		// 166: (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[166] = createSerializationMatchTermSubtract(74, 1);
		// 167: (|OperationContextDeclCS::ownedParameters| - 1)
		serializationMatchTerms[167] = createSerializationMatchTermSubtract(76, 1);
		// 168: (|OperationContextDeclCS::ownedParameters| > 0)
		serializationMatchTerms[168] = createSerializationMatchTermGreaterThan(76, 0);
		// 169: (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[169] = createSerializationMatchTermSubtract(79, 1);
		// 170: (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[170] = createSerializationMatchTermSubtract(82, 1);
		// 171: (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[171] = createSerializationMatchTermSubtract(83, 1);
		// 172: (|PathNameDeclCS::ownedPathName| - 1)
		serializationMatchTerms[172] = createSerializationMatchTermSubtract(84, 1);
		// 173: (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[173] = createSerializationMatchTermSubtract(85, 1);
		// 174: (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[174] = createSerializationMatchTermSubtract(87, 1);
		// 175: (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[175] = createSerializationMatchTermSubtract(90, 1);
		// 176: (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[176] = createSerializationMatchTermSubtract(91, 1);
		// 177: (|SpecificationCS::exprString| - 1)
		serializationMatchTerms[177] = createSerializationMatchTermSubtract(92, 1);
		// 178: (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[178] = createSerializationMatchTermSubtract(93, 1);
		// 179: (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[179] = createSerializationMatchTermSubtract(96, 1);
		// 180: (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[180] = createSerializationMatchTermSubtract(97, 1);
		// 181: (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[181] = createSerializationMatchTermSubtract(98, 1);
		// 182: (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[182] = createSerializationMatchTermSubtract(100, 1);
		// 183: (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[183] = createSerializationMatchTermSubtract(101, 1);
		// 184: (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[184] = createSerializationMatchTermSubtract(102, 1);
		// 185: (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[185] = createSerializationMatchTermGreaterThan(102, 0);
		// 186: (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[186] = createSerializationMatchTermSubtract(104, 1);
		// 187: (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[187] = createSerializationMatchTermSubtract(106, 1);
		// 188: (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[188] = createSerializationMatchTermSubtract(108, 1);
		// 189: (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[189] = createSerializationMatchTermGreaterThan(108, 0);
		// 190: (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[190] = createSerializationMatchTermSubtract(110, 1);
		// 191: (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[191] = createSerializationMatchTermSubtract(115, 1);
		// 192: (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[192] = createSerializationMatchTermSubtract(116, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS-0(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] = createSerializationRule("FirstPathElementCS-0", 30,
			createSerializationMatchSteps(
				38		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				194		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::MultiplicityBoundsCS-0(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { ".." upperBound=UPPER }[?] }
		serializationRules[1] = createSerializationRule("MultiplicityBoundsCS-0", 49,
			createSerializationMatchSteps(
				61		/* assert |MultiplicityCS::isNullFree| == 0 */,
				98		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				24		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				5		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				150		/* V00*2-steps || value */,
				109		/* '..' || no-space value no-space */,
				102		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-0(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" }
		serializationRules[2] = createSerializationRule("MultiplicityCS-0", 50,
			createSerializationMatchSteps(
				61		/* assert |MultiplicityCS::isNullFree| == 0 */,
				98		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				24		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				119		/* '[' || no-space value no-space */,
				5		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				150		/* V00*2-steps || value */,
				109		/* '..' || no-space value no-space */,
				102		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				145		/* '|?' || no-space value no-space */,
				120		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-1(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree?="|1" "]" }
		serializationRules[3] = createSerializationRule("MultiplicityCS-1", 50,
			createSerializationMatchSteps(
				25		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				98		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				24		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				119		/* '[' || no-space value no-space */,
				5		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				150		/* V00*2-steps || value */,
				109		/* '..' || no-space value no-space */,
				102		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				2		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				120		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(10/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-2(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" }
		serializationRules[4] = createSerializationRule("MultiplicityCS-2", 50,
			createSerializationMatchSteps(
				61		/* assert |MultiplicityCS::isNullFree| == 0 */,
				98		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				24		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				119		/* '[' || no-space value no-space */,
				5		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				150		/* V00*2-steps || value */,
				109		/* '..' || no-space value no-space */,
				102		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				120		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-3(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "|?" "]" }
		serializationRules[5] = createSerializationRule("MultiplicityCS-3", 50,
			createSerializationMatchSteps(
				61		/* assert |MultiplicityCS::isNullFree| == 0 */,
				26		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				119		/* '[' || no-space value no-space */,
				99		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				145		/* '|?' || no-space value no-space */,
				120		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-4(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} isNullFree?="|1" "]" }
		serializationRules[6] = createSerializationRule("MultiplicityCS-4", 50,
			createSerializationMatchSteps(
				25		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				26		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				119		/* '[' || no-space value no-space */,
				99		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				2		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				120		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(10/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-5(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "]" }
		serializationRules[7] = createSerializationRule("MultiplicityCS-5", 50,
			createSerializationMatchSteps(
				61		/* assert |MultiplicityCS::isNullFree| == 0 */,
				26		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				119		/* '[' || no-space value no-space */,
				99		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				120		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityStringCS-0(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		serializationRules[8] = createSerializationRule("MultiplicityStringCS-0", 51,
			createSerializationMatchSteps(
				61		/* assert |MultiplicityCS::isNullFree| == 0 */,
				26		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				99		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::NextPathElementCS-0(basecs::PathElementCS): referredElement=UnreservedName
		serializationRules[9] = createSerializationRule("NextPathElementCS-0", 61,
			createSerializationMatchSteps(
				38		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				193		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::PathNameCS-0(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[10] = createSerializationRule("PathNameCS-0", 67,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				187		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */,
				151		/* V00*2-steps || value */,
				111		/* '::' || no-space value no-space */,
				188		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 21/* FirstPathElementCS,NextPathElementCS */,
					(30/*FirstPathElementCS*/ << 4) | 0 /*[1]*/,
					(61/*NextPathElementCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TemplateBindingCS-0(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] = createSerializationRule("TemplateBindingCS-0", 87,
			createSerializationMatchSteps(
				156		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : MultiplicityCS */,
				157		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : TemplateParameterSubstitutionCS */,
				129		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				85		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			),
			createSerializationSteps(
				75		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				151		/* V00*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				75		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				158		/* V01*1-steps || value */,
				46		/* TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 37/* TemplateParameterSubstitutionCS */,
					(88/*TemplateParameterSubstitutionCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TemplateParameterSubstitutionCS-0(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] = createSerializationRule("TemplateParameterSubstitutionCS-0", 88,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				158		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
				46		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			),
			createSerializationSteps(
				10		/* TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 47/* TypeRefCS */,
					(101/*TypeRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::TypeParameterCS-0(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[13] = createSerializationRule("TypeParameterCS-0", 100,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				162		/* check-rule basecs::TypeParameterCS.ownedExtends : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */,
				89		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				119		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				155		/* V00*5-steps || value */,
				129		/* 'extends' || soft-space value soft-space */,
				30		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */,
				161		/* V01*2-steps || value */,
				103		/* '&&' || soft-space value soft-space */,
				30		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 48/* TypedRefCS */,
					(102/*TypedRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TypedTypeRefCS-0(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] }
		serializationRules[14] = createSerializationRule("TypedTypeRefCS-0", 103,
			createSerializationMatchSteps(
				76		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				165		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				166		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				110		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				51		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				64		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */,
				152		/* V00*3-steps || value */,
				104		/* '(' || no-space value no-space */,
				11		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				105		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 36/* TemplateBindingCS */,
					(87/*TemplateBindingCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 24/* PathNameCS */,
					(67/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::UnreservedPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=NextPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[15] = createSerializationRule("UnreservedPathNameCS-0", 112,
			createSerializationMatchSteps(
				154		/* check-rule basecs::PathNameCS.ownedPathElements : NextPathElementCS */,
				83		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			),
			createSerializationSteps(
				188		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */,
				151		/* V00*2-steps || value */,
				111		/* '::' || no-space value no-space */,
				188		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 20/* NextPathElementCS */,
					(61/*NextPathElementCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::WildcardTypeRefCS-0(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[16] = createSerializationRule("WildcardTypeRefCS-0", 115,
			createSerializationMatchSteps(
				78		/* assert |WildcardTypeRefCS::ownedSuper| == 0 */,
				167		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				112		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			),
			createSerializationSteps(
				116		/* '?' || soft-space value soft-space */,
				150		/* V00*2-steps || value */,
				129		/* 'extends' || soft-space value soft-space */,
				31		/* WildcardTypeRefCS::ownedExtends=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 48/* TypedRefCS */,
					(102/*TypedRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::ClassifierContextDeclCS-0(completeoclcs::ClassifierContextDeclCS): { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=UnreservedPathNameCS { { "inv" ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } }
		serializationRules[17] = createSerializationRule("ClassifierContextDeclCS-0", 3,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				168		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : DefCS|DefOperationCS|DefPropertyCS */,
				169		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : ConstraintCS */,
				181		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : UnreservedPathNameCS */,
				160		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				143		/* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
				138		/* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
				40		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				121		/* assign V1 = |ClassifierContextDeclCS::selfName| */,
				107		/* assign V0 = |TemplateableElementCS::ownedSignature| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				122		/* 'context' || soft-space value soft-space */,
				147		/* V00*1-steps || value */,
				71		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				158		/* V01*1-steps || value */,
				98		/* ClassifierContextDeclCS::selfName=UnrestrictedName || soft-space value soft-space */,
				62		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				168		/* V02*2-steps || value */,
				134		/* 'inv' || soft-space value soft-space */,
				39		/* ClassifierContextDeclCS::ownedInvariants+=ConstraintCS || value */,
				171		/* V03*1-steps || value */,
				22		/* ClassifierContextDeclCS::ownedDefinitions+=DefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 6/* DefCS */,
					(16/*DefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 3/* ConstraintCS */,
					(12/*ConstraintCS*/ << 4) | 3 /*[+]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 60/* UnreservedPathNameCS */,
					(112/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 38/* TemplateSignatureCS */,
					(89/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::ClassifierContextDeclCS-1(completeoclcs::ClassifierContextDeclCS): { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=UnreservedPathNameCS { { "inv" ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } }
		serializationRules[18] = createSerializationRule("ClassifierContextDeclCS-1", 3,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				168		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : DefCS|DefOperationCS|DefPropertyCS */,
				169		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : ConstraintCS */,
				181		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : UnreservedPathNameCS */,
				160		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				143		/* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
				138		/* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
				40		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				121		/* assign V1 = |ClassifierContextDeclCS::selfName| */,
				107		/* assign V0 = |TemplateableElementCS::ownedSignature| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				122		/* 'context' || soft-space value soft-space */,
				147		/* V00*1-steps || value */,
				71		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				158		/* V01*1-steps || value */,
				98		/* ClassifierContextDeclCS::selfName=UnrestrictedName || soft-space value soft-space */,
				62		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				167		/* V02*2-steps || value */,
				134		/* 'inv' || soft-space value soft-space */,
				39		/* ClassifierContextDeclCS::ownedInvariants+=ConstraintCS || value */,
				172		/* V03*1-steps || value */,
				22		/* ClassifierContextDeclCS::ownedDefinitions+=DefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 6/* DefCS */,
					(16/*DefCS*/ << 4) | 3 /*[+]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 3/* ConstraintCS */,
					(12/*ConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 60/* UnreservedPathNameCS */,
					(112/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 38/* TemplateSignatureCS */,
					(89/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::CompleteOCLDocumentCS-0(completeoclcs::CompleteOCLDocumentCS): { ownedImports+=ImportCS[*] { ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] } }
		serializationRules[19] = createSerializationRule("CompleteOCLDocumentCS-0", 10,
			createSerializationMatchSteps(
				62		/* assert |NamedElementCS::name| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				170		/* check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				155		/* check-rule basecs::RootCS.ownedImports : ImportCS */,
				171		/* check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : PackageDeclarationCS */,
				139		/* assign V2 = |CompleteOCLDocumentCS::ownedContexts| */,
				123		/* assign V1 = |CompleteOCLDocumentCS::ownedPackages| */,
				105		/* assign V0 = |RootCS::ownedImports| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				148		/* V00*1-steps || value */,
				33		/* RootCS::ownedImports+=ImportCS || value */,
				159		/* V01*1-steps || value */,
				50		/* CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS || value */,
				166		/* V02*1-steps || value */,
				17		/* CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, 4/* ContextDeclCS */,
					(13/*ContextDeclCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/* ImportCS */,
					(35/*ImportCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, 22/* PackageDeclarationCS */,
					(65/*PackageDeclarationCS*/ << 4) | 2 /*[*]*/
				)
			});
		// CompleteOCL::ConstraintCS-0(basecs::ConstraintCS): { { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS }
		serializationRules[20] = createSerializationRule("ConstraintCS-0", 12,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				55		/* assert |ConstraintCS::stereotype| == 0 */,
				150		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				151		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				99		/* assign V0 = |NamedElementCS::name| */,
				124		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				155		/* V00*5-steps || value */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				162		/* V01*3-steps || value */,
				104		/* '(' || no-space value no-space */,
				45		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				105		/* ')' || no-space value */,
				110		/* ':' || soft-space value soft-space */,
				72		/* ConstraintCS::ownedSpecification=SpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 33/* SpecificationCS */,
					(83/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 33/* SpecificationCS */,
					(83/*SpecificationCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::DefOperationCS-0(completeoclcs::DefOperationCS): { isStatic?="static"[?] "def" ":" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=DefParameterCS { "," ownedParameters+=DefParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] "=" ownedSpecification=SpecificationCS }
		serializationRules[21] = createSerializationRule("DefOperationCS-0", 17,
			createSerializationMatchSteps(
				73		/* assert |TypedElementCS::isOptional| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				173		/* check-rule completeoclcs::DefOperationCS.ownedParameters : DefParameterCS */,
				160		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				172		/* check-rule completeoclcs::DefCS.ownedSpecification : SpecificationCS */,
				163		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				8		/* assert (|DefCS::ownedSpecification| - 1) == 0 */,
				147		/* assign V4 = |TypedElementCS::ownedType| */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */,
				130		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				94		/* assign V0 = |DefCS::isStatic.'static'| */,
				134		/* assign V2 = (|DefOperationCS::ownedParameters| > 0) */,
				141		/* assign V3 = (|DefOperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				147		/* V00*1-steps || value */,
				4		/* DefCS::isStatic?='static' || soft-space value soft-space */,
				123		/* 'def' || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				158		/* V01*1-steps || value */,
				71		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				104		/* '(' || no-space value no-space */,
				169		/* V02*4-steps || value */,
				51		/* DefOperationCS::ownedParameters+=DefParameterCS || value */,
				174		/* V03*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				51		/* DefOperationCS::ownedParameters+=DefParameterCS || value */,
				105		/* ')' || no-space value */,
				110		/* ':' || soft-space value soft-space */,
				175		/* V04*1-steps || value */,
				86		/* TypedElementCS::ownedType=TypeExpCS || value */,
				113		/* '=' || soft-space value soft-space */,
				73		/* DefCS::ownedSpecification=SpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, false,
					(8/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, 7/* DefParameterCS */,
					(18/*DefParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 38/* TemplateSignatureCS */,
					(89/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 33/* SpecificationCS */,
					(83/*SpecificationCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::DefParameterCS-0(basecs::ParameterCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[22] = createSerializationRule("DefParameterCS-0", 18,
			createSerializationMatchSteps(
				73		/* assert |TypedElementCS::isOptional| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				163		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				50		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				86		/* TypedElementCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::DefPropertyCS-0(completeoclcs::DefPropertyCS): { isStatic?="static"[?] "def" ":" name=UnrestrictedName ":" ownedType=TypeExpCS "=" ownedSpecification=SpecificationCS }
		serializationRules[23] = createSerializationRule("DefPropertyCS-0", 19,
			createSerializationMatchSteps(
				73		/* assert |TypedElementCS::isOptional| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				172		/* check-rule completeoclcs::DefCS.ownedSpecification : SpecificationCS */,
				163		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				8		/* assert (|DefCS::ownedSpecification| - 1) == 0 */,
				50		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */,
				94		/* assign V0 = |DefCS::isStatic.'static'| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				147		/* V00*1-steps || value */,
				4		/* DefCS::isStatic?='static' || soft-space value soft-space */,
				123		/* 'def' || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				86		/* TypedElementCS::ownedType=TypeExpCS || value */,
				113		/* '=' || soft-space value soft-space */,
				73		/* DefCS::ownedSpecification=SpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, false,
					(8/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 33/* SpecificationCS */,
					(83/*SpecificationCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::ImportCS-0(basecs::ImportCS): { {"import"|"include"|"library"} { name=Identifier ":" }[?] ownedPathName=URIPathNameCS isAll?="::*"[?] }
		serializationRules[24] = createSerializationRule("ImportCS-0", 35,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				152		/* check-rule basecs::ImportCS.ownedPathName : URIPathNameCS */,
				125		/* assign V1 = |ImportCS::isAll.'::*'| */,
				15		/* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				99		/* assign V0 = |NamedElementCS::name| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				131		/* 'import' || value */,
				150		/* V00*2-steps || value */,
				183		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				61		/* ImportCS::ownedPathName=URIPathNameCS || value */,
				158		/* V01*1-steps || value */,
				1		/* ImportCS::isAll?='::*' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, false,
					(2/*'::*'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 52/* URIPathNameCS */,
					(108/*URIPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::NavigatingArgExpCS-0(completeoclcs::OCLMessageArgCS): "?"
		serializationRules[25] = createSerializationRule("NavigatingArgExpCS-0", 55,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				115		/* '?' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// CompleteOCL::OperationContextDeclCS-0(completeoclcs::OperationContextDeclCS): { "context" ownedSignature=TemplateSignatureCS[?] ownedPathName=UnreservedPathNameCS "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] { { "body" ":" ownedBodies+=SpecificationCS }[*] { "post" ownedPostconditions+=ConstraintCS }[*] { "pre" ownedPreconditions+=ConstraintCS }[*] } }
		serializationRules[26] = createSerializationRule("OperationContextDeclCS-0", 64,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				175		/* check-rule completeoclcs::OperationContextDeclCS.ownedBodies : SpecificationCS */,
				176		/* check-rule completeoclcs::OperationContextDeclCS.ownedParameters : ParameterCS */,
				181		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : UnreservedPathNameCS */,
				177		/* check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : ConstraintCS */,
				178		/* check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : ConstraintCS */,
				160		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				174		/* check-rule completeoclcs::FeatureContextDeclCS.ownedType : TypeExpCS */,
				149		/* assign V6 = |OperationContextDeclCS::ownedPreconditions| */,
				148		/* assign V5 = |OperationContextDeclCS::ownedPostconditions| */,
				146		/* assign V4 = |OperationContextDeclCS::ownedBodies| */,
				144		/* assign V3 = |FeatureContextDeclCS::ownedType| */,
				40		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				107		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				117		/* assign V1 = (|OperationContextDeclCS::ownedParameters| > 0) */,
				135		/* assign V2 = (|OperationContextDeclCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				122		/* 'context' || soft-space value soft-space */,
				147		/* V00*1-steps || value */,
				71		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				62		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				104		/* '(' || no-space value no-space */,
				164		/* V01*4-steps || value */,
				52		/* OperationContextDeclCS::ownedParameters+=ParameterCS || value */,
				167		/* V02*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				52		/* OperationContextDeclCS::ownedParameters+=ParameterCS || value */,
				105		/* ')' || no-space value */,
				110		/* ':' || soft-space value soft-space */,
				170		/* V03*1-steps || value */,
				82		/* FeatureContextDeclCS::ownedType=TypeExpCS || value */,
				176		/* V04*3-steps || value */,
				121		/* 'body' || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				12		/* OperationContextDeclCS::ownedBodies+=SpecificationCS || value */,
				177		/* V05*2-steps || value */,
				139		/* 'post' || soft-space value soft-space */,
				67		/* OperationContextDeclCS::ownedPostconditions+=ConstraintCS || value */,
				178		/* V06*2-steps || value */,
				140		/* 'pre' || soft-space value soft-space */,
				68		/* OperationContextDeclCS::ownedPreconditions+=ConstraintCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, 33/* SpecificationCS */,
					(83/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, 23/* ParameterCS */,
					(66/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 60/* UnreservedPathNameCS */,
					(112/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, 3/* ConstraintCS */,
					(12/*ConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, 3/* ConstraintCS */,
					(12/*ConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 38/* TemplateSignatureCS */,
					(89/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::PackageDeclarationCS-0(completeoclcs::PackageDeclarationCS): { "package" ownedPathName=UnreservedPathNameCS { "inv" ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] "endpackage" }
		serializationRules[27] = createSerializationRule("PackageDeclarationCS-0", 65,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				179		/* check-rule completeoclcs::PackageDeclarationCS.ownedContexts : ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				180		/* check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : ConstraintCS */,
				181		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : UnreservedPathNameCS */,
				128		/* assign V1 = |PackageDeclarationCS::ownedContexts| */,
				103		/* assign V0 = |PackageDeclarationCS::ownedInvariants| */,
				40		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				138		/* 'package' || soft-space value soft-space */,
				62		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				151		/* V00*2-steps || value */,
				134		/* 'inv' || soft-space value soft-space */,
				40		/* PackageDeclarationCS::ownedInvariants+=ConstraintCS || value */,
				159		/* V01*1-steps || value */,
				18		/* PackageDeclarationCS::ownedContexts+=ContextDeclCS || value */,
				128		/* 'endpackage' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, 4/* ContextDeclCS */,
					(13/*ContextDeclCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, 3/* ConstraintCS */,
					(12/*ConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 60/* UnreservedPathNameCS */,
					(112/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::ParameterCS-0(basecs::ParameterCS): { { name=UnrestrictedName ":" }[?] ownedType=TypeExpCS }
		serializationRules[28] = createSerializationRule("ParameterCS-0", 66,
			createSerializationMatchSteps(
				73		/* assert |TypedElementCS::isOptional| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				163		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				50		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				99		/* assign V0 = |NamedElementCS::name| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				150		/* V00*2-steps || value */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				86		/* TypedElementCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::PropertyContextDeclCS-0(completeoclcs::PropertyContextDeclCS): { "context" ownedPathName=UnreservedPathNameCS ":" ownedType=TypeExpCS { { "derive" ":" ownedDefaultExpressions+=SpecificationCS }[*] { "init" ":" ownedDefaultExpressions+=SpecificationCS }[*] } }
		serializationRules[29] = createSerializationRule("PropertyContextDeclCS-0", 75,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				122		/* 'context' || soft-space value soft-space */,
				62		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				110		/* ':' || soft-space value soft-space */,
				82		/* FeatureContextDeclCS::ownedType=TypeExpCS || value */,
				153		/* V00*3-steps || value */,
				124		/* 'derive' || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				21		/* PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS || value */,
				163		/* V01*3-steps || value */,
				133		/* 'init' || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				21		/* PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, 33/* SpecificationCS */,
					(83/*SpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 60/* UnreservedPathNameCS */,
					(112/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::SpecificationCS-0(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS
		serializationRules[30] = createSerializationRule("SpecificationCS-0", 83,
			createSerializationMatchSteps(
				71		/* assert |SpecificationCS::exprString| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				197		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				9		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				27		/* ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::SpecificationCS-1(essentialoclcs::ExpSpecificationCS): exprString=UNQUOTED_STRING
		serializationRules[31] = createSerializationRule("SpecificationCS-1", 83,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				57		/* assert |ExpSpecificationCS::ownedExpression| == 0 */,
				45		/* assert (|SpecificationCS::exprString| - 1) == 0 */
			),
			createSerializationSteps(
				0		/* SpecificationCS::exprString=UNQUOTED_STRING || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, true, GrammarCardinality.ONE)
			});
		// CompleteOCL::TemplateSignatureCS-0(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[32] = createSerializationRule("TemplateSignatureCS-0", 89,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				159		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				86		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				104		/* '(' || no-space value no-space */,
				53		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				151		/* V00*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				53		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				105		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 46/* TypeParameterCS */,
					(100/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// CompleteOCL::TemplateSignatureCS-1(basecs::TemplateSignatureCS): { "<" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ">" }
		serializationRules[33] = createSerializationRule("TemplateSignatureCS-1", 89,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				159		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				86		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				112		/* '<' || soft-space value soft-space */,
				53		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				151		/* V00*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				53		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				114		/* '>' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 46/* TypeParameterCS */,
					(100/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::BooleanLiteralExpCS-0(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[34] = createSerializationRule("BooleanLiteralExpCS-0", 2,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			),
			createSerializationSteps(
				100		/* BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, false,
					(7/*'false|true'*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CoIteratorVariableCS-0(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[35] = createSerializationRule("CoIteratorVariableCS-0", 4,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				77		/* assert |VariableCS::ownedInitExpression| == 0 */,
				233		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				111		/* assign V0 = |VariableCS::ownedType| */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				150		/* V00*2-steps || value */,
				110		/* ':' || soft-space value soft-space */,
				87		/* VariableCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralExpCS-0(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[36] = createSerializationRule("CollectionLiteralExpCS-0", 5,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				186		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS */,
				187		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS */,
				2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				79		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				113		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				79		/* CollectionLiteralExpCS::ownedType=CollectionTypeCS || value */,
				144		/* '{' || soft-space value push soft-new-line */,
				154		/* V00*4-steps || value */,
				54		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				161		/* V01*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				54		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				146		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 1/* CollectionLiteralPartCS */,
					(6/*CollectionLiteralPartCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 2/* CollectionTypeCS */,
					(8/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionLiteralPartCS-0(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] }
		serializationRules[37] = createSerializationRule("CollectionLiteralPartCS-0", 6,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				188		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				190		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				91		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				24		/* CollectionLiteralPartCS::ownedExpression=ExpCS || value */,
				150		/* V00*2-steps || value */,
				109		/* '..' || no-space value no-space */,
				43		/* CollectionLiteralPartCS::ownedLastExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralPartCS-1(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
		serializationRules[38] = createSerializationRule("CollectionLiteralPartCS-1", 6,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				53		/* assert |CollectionLiteralPartCS::ownedLastExpression| == 0 */,
				189		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : PatternExpCS */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				25		/* CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 25/* PatternExpCS */,
					(68/*PatternExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionPatternCS-0(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" }
		serializationRules[39] = createSerializationRule("CollectionPatternCS-0", 7,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				54		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				191		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				192		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				92		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				114		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				80		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				144		/* '{' || soft-space value push soft-new-line */,
				156		/* V00*6-steps || value */,
				55		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				161		/* V01*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				55		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				107		/* '++' || soft-space value soft-space */,
				96		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				146		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 25/* PatternExpCS */,
					(68/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/* CollectionTypeCS */,
					(8/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionTypeCS-0(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] }
		serializationRules[40] = createSerializationRule("CollectionTypeCS-0", 8,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				193		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				194		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				93		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				122		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				6		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				155		/* V00*5-steps || value */,
				104		/* '(' || no-space value no-space */,
				81		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				158		/* V01*1-steps || value */,
				14		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				105		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 42/* TypeExpWithoutMultiplicityCS */,
					(95/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS-0(essentialoclcs::CurlyBracketedClauseCS): { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" }
		serializationRules[41] = createSerializationRule("CurlyBracketedClauseCS-0", 14,
			createSerializationMatchSteps(
				56		/* assert |CurlyBracketedClauseCS::value| == 0 */,
				196		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : ShadowPartCS */,
				80		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				115		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				144		/* '{' || soft-space value push soft-new-line */,
				154		/* V00*4-steps || value */,
				56		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				161		/* V01*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				56		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				146		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 32/* ShadowPartCS */,
					(81/*ShadowPartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::ElseIfThenExpCS-0(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[42] = createSerializationRule("ElseIfThenExpCS-0", 22,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				202		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				203		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				14		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				13		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				126		/* 'elseif' || soft-space value soft-space */,
				16		/* IfThenExpCS::ownedCondition=ExpCS || value */,
				142		/* 'then' || soft-space value soft-space */,
				78		/* IfThenExpCS::ownedThenExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::ExpCS-18(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } }
		serializationRules[43] = createSerializationRule("ExpCS-18", 29,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				204		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				220		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				37		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */,
				16		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				44		/* InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value */,
				180		/* NamedElementCS::name=BinaryOperatorName || soft-space value soft-space */,
				189		/* OperatorExpCS::ownedRight=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 29/* PrefixedPrimaryExpCS */,
					(70/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::IfExpCS-0(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[44] = createSerializationRule("IfExpCS-0", 34,
			createSerializationMatchSteps(
				58		/* assert |IfExpCS::isImplicit| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				198		/* check-rule essentialoclcs::IfExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				199		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				200		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS */,
				201		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				11		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				95		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				12		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				10		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				130		/* 'if' || soft-space value soft-space */,
				15		/* IfExpCS::ownedCondition=ExpCS|PatternExpCS || value */,
				142		/* 'then' || soft-space value soft-space */,
				77		/* IfExpCS::ownedThenExpression=ExpCS || value */,
				148		/* V00*1-steps || value */,
				32		/* IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value */,
				125		/* 'else' || soft-space value soft-space */,
				23		/* IfExpCS::ownedElseExpression=ExpCS || value */,
				127		/* 'endif' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 26/* ExpCS,PatternExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/,
					(68/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 9/* ElseIfThenExpCS */,
					(22/*ElseIfThenExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::InvalidLiteralExpCS-0(essentialoclcs::InvalidLiteralExpCS): "invalid"
		serializationRules[45] = createSerializationRule("InvalidLiteralExpCS-0", 37,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				135		/* 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::LambdaLiteralExpCS-0(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[46] = createSerializationRule("LambdaLiteralExpCS-0", 40,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				205		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				17		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				117		/* 'Lambda' || soft-space value soft-space */,
				144		/* '{' || soft-space value push soft-new-line */,
				29		/* LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value */,
				146		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::LetExpCS-0(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[47] = createSerializationRule("LetExpCS-0", 41,
			createSerializationMatchSteps(
				59		/* assert |LetExpCS::isImplicit| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				206		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				207		/* check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS */,
				18		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				81		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				136		/* 'let' || soft-space value soft-space */,
				90		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				151		/* V00*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				90		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				132		/* 'in' || soft-space value soft-space */,
				34		/* LetExpCS::ownedInExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 13/* LetVariableCS */,
					(42/*LetVariableCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::LetVariableCS-0(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[48] = createSerializationRule("LetVariableCS-0", 42,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				232		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				208		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				233		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				52		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				133		/* assign V1 = |VariableCS::ownedType| */,
				96		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				147		/* V00*1-steps || value */,
				70		/* LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				160		/* V01*2-steps || value */,
				110		/* ':' || soft-space value soft-space */,
				87		/* VariableCS::ownedType=TypeExpCS || value */,
				113		/* '=' || soft-space value soft-space */,
				38		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 31/* RoundBracketedClauseCS */,
					(76/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::MapLiteralExpCS-0(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[49] = createSerializationRule("MapLiteralExpCS-0", 45,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				209		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS */,
				210		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS */,
				19		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				82		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				116		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				83		/* MapLiteralExpCS::ownedType=MapTypeCS || value */,
				144		/* '{' || soft-space value push soft-new-line */,
				154		/* V00*4-steps || value */,
				57		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				161		/* V01*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				57		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				146		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 14/* MapLiteralPartCS */,
					(46/*MapLiteralPartCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 15/* MapTypeCS */,
					(47/*MapTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapLiteralPartCS-0(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS {"with"|"<-"} ownedValue=ExpCS }
		serializationRules[50] = createSerializationRule("MapLiteralPartCS-0", 46,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				211		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				212		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				21		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				20		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				41		/* MapLiteralPartCS::ownedKey=ExpCS || value */,
				143		/* 'with' || value */,
				88		/* MapLiteralPartCS::ownedValue=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapTypeCS-0(essentialoclcs::MapTypeCS): { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] }
		serializationRules[51] = createSerializationRule("MapTypeCS-0", 47,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				213		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				214		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				97		/* assign V0 = |MapTypeCS::ownedValueType| */,
				23		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				22		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				7		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				155		/* V00*5-steps || value */,
				104		/* '(' || no-space value no-space */,
				42		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				108		/* ',' || no-space value soft-space */,
				89		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				105		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(5/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::Model-0(essentialoclcs::ContextCS): ownedExpression=ExpCS
		serializationRules[52] = createSerializationRule("Model-0", 48,
			createSerializationMatchSteps(
				62		/* assert |NamedElementCS::name| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				68		/* assert |RootCS::ownedImports| == 0 */,
				195		/* check-rule essentialoclcs::ContextCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				7		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				26		/* ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NameExpCS-0(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre?="@" "pre" }[?] }
		serializationRules[53] = createSerializationRule("NameExpCS-0", 53,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				182		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				183		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS */,
				184		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				185		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS */,
				142		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				137		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				120		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				90		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				60		/* AbstractNameExpCS::ownedPathName=PathNameCS || value */,
				148		/* V00*1-steps || value */,
				74		/* AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value */,
				158		/* V01*1-steps || value */,
				69		/* AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				165		/* V02*1-steps || value */,
				19		/* AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				173		/* V03*2-steps || value */,
				3		/* AbstractNameExpCS::isPre?='@' || soft-space value soft-space */,
				140		/* 'pre' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, false,
					(4/*'@'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/* CurlyBracketedClauseCS */,
					(14/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 24/* PathNameCS */,
					(67/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 31/* RoundBracketedClauseCS */,
					(76/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 34/* SquareBracketedClauseCS */,
					(84/*SquareBracketedClauseCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-0(essentialoclcs::NavigatingArgCS): { ":" ownedType=TypeExpCS }
		serializationRules[54] = createSerializationRule("NavigatingArgCS-0", 54,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				63		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				64		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				65		/* assert |NavigatingArgCS::ownedNameExpression| == 0 */,
				67		/* assert |NavigatingArgCS::prefix| == 0 */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				31		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				110		/* ':' || soft-space value soft-space */,
				84		/* NavigatingArgCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-1(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[55] = createSerializationRule("NavigatingArgCS-1", 54,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				67		/* assert |NavigatingArgCS::prefix| == 0 */,
				215		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				216		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				127		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				100		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				31		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				48		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				110		/* ':' || soft-space value soft-space */,
				84		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				150		/* V00*2-steps || value */,
				143		/* 'with' || value */,
				13		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				160		/* V01*2-steps || value */,
				113		/* '=' || soft-space value soft-space */,
				35		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-2(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[56] = createSerializationRule("NavigatingArgCS-2", 54,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				67		/* assert |NavigatingArgCS::prefix| == 0 */,
				215		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				216		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				29		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				126		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				102		/* assign V0 = |NavigatingArgCS::ownedType| */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				48		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				150		/* V00*2-steps || value */,
				110		/* ':' || soft-space value soft-space */,
				84		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				160		/* V01*2-steps || value */,
				143		/* 'with' || value */,
				13		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				132		/* 'in' || soft-space value soft-space */,
				35		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-3(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[57] = createSerializationRule("NavigatingArgCS-3", 54,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				66		/* assert |NavigatingArgCS::ownedType| == 0 */,
				67		/* assert |NavigatingArgCS::prefix| == 0 */,
				215		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				216		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				101		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				28		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				48		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				143		/* 'with' || value */,
				13		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				150		/* V00*2-steps || value */,
				113		/* '=' || soft-space value soft-space */,
				35		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-4(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
		serializationRules[58] = createSerializationRule("NavigatingArgCS-4", 54,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				63		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				64		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				66		/* assert |NavigatingArgCS::ownedType| == 0 */,
				67		/* assert |NavigatingArgCS::prefix| == 0 */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				49		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingBarArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[59] = createSerializationRule("NavigatingBarArgCS-0", 56,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				63		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				216		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				102		/* assign V0 = |NavigatingArgCS::ownedType| */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				34		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				127		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				94		/* NavigatingArgCS::prefix='|' || soft-space value soft-space */,
				48		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				155		/* V00*5-steps || value */,
				110		/* ':' || soft-space value soft-space */,
				84		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				160		/* V01*2-steps || value */,
				113		/* '=' || soft-space value soft-space */,
				35		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(9/*'|'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[60] = createSerializationRule("NavigatingCommaArgCS-0", 57,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				215		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				216		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				127		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				100		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				31		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				32		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				93		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				48		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				110		/* ':' || soft-space value soft-space */,
				84		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				150		/* V00*2-steps || value */,
				143		/* 'with' || value */,
				13		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				160		/* V01*2-steps || value */,
				113		/* '=' || soft-space value soft-space */,
				35		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-1(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[61] = createSerializationRule("NavigatingCommaArgCS-1", 57,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				215		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				216		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				29		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				126		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				102		/* assign V0 = |NavigatingArgCS::ownedType| */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				32		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				93		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				48		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				150		/* V00*2-steps || value */,
				110		/* ':' || soft-space value soft-space */,
				84		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				160		/* V01*2-steps || value */,
				143		/* 'with' || value */,
				13		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				132		/* 'in' || soft-space value soft-space */,
				35		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-2(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[62] = createSerializationRule("NavigatingCommaArgCS-2", 57,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				66		/* assert |NavigatingArgCS::ownedType| == 0 */,
				215		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				216		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				101		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				28		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				32		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				93		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				48		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				143		/* 'with' || value */,
				13		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				150		/* V00*2-steps || value */,
				113		/* '=' || soft-space value soft-space */,
				35		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-3(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS }
		serializationRules[63] = createSerializationRule("NavigatingCommaArgCS-3", 57,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				63		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				64		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				66		/* assert |NavigatingArgCS::ownedType| == 0 */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				32		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				93		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				48		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
	}
	private void initSerializationRules1() {
		// EssentialOCL::NavigatingSemiArgCS-0(essentialoclcs::NavigatingArgCS): { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[64] = createSerializationRule("NavigatingSemiArgCS-0", 58,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				63		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				216		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				102		/* assign V0 = |NavigatingArgCS::ownedType| */,
				30		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				33		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				127		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				92		/* NavigatingArgCS::prefix=';' || no-space value soft-new-line */,
				48		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				155		/* V00*5-steps || value */,
				110		/* ':' || soft-space value soft-space */,
				84		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				160		/* V01*2-steps || value */,
				113		/* '=' || soft-space value soft-space */,
				35		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(3/*';'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 17/* NavigatingArgExpCS */,
					(55/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NestedExpCS-0(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[65] = createSerializationRule("NestedExpCS-0", 60,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				219		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				35		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				104		/* '(' || no-space value no-space */,
				28		/* NestedExpCS::ownedExpression=ExpCS || value */,
				105		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NullLiteralExpCS-0(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[66] = createSerializationRule("NullLiteralExpCS-0", 62,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				137		/* 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::NumberLiteralExpCS-0(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[67] = createSerializationRule("NumberLiteralExpCS-0", 63,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				36		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			),
			createSerializationSteps(
				101		/* NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::PatternExpCS-0(essentialoclcs::PatternExpCS): { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS }
		serializationRules[68] = createSerializationRule("PatternExpCS-0", 68,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				223		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : TypeExpCS */,
				41		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				104		/* assign V0 = |PatternExpCS::patternVariableName| */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				147		/* V00*1-steps || value */,
				91		/* PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				66		/* PatternExpCS::ownedPatternType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedLetExpCS-1(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		serializationRules[69] = createSerializationRule("PrefixedLetExpCS-1", 69,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				222		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : LetExpCS|PrefixedLetExpCS */,
				37		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				181		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				190		/* OperatorExpCS::ownedRight=PrefixedLetExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 27/* PrefixedLetExpCS */,
					(69/*PrefixedLetExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS-15(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[70] = createSerializationRule("PrefixedPrimaryExpCS-15", 70,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				221		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				37		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				181		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				191		/* OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 29/* PrefixedPrimaryExpCS */,
					(70/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrimitiveTypeCS-0(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		serializationRules[71] = createSerializationRule("PrimitiveTypeCS-0", 73,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				42		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				8		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::RoundBracketedClauseCS-0(essentialoclcs::RoundBracketedClauseCS): { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" }
		serializationRules[72] = createSerializationRule("RoundBracketedClauseCS-0", 76,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				104		/* '(' || no-space value no-space */,
				152		/* V00*3-steps || value */,
				184		/* RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value */,
				159		/* V01*1-steps || value */,
				185		/* RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value */,
				105		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 19/* NavigatingArgCS,NavigatingBarArgCS,NavigatingCommaArgCS,NavigatingSemiArgCS */,
					(54/*NavigatingArgCS*/ << 4) | 1 /*[?]*/,
					(56/*NavigatingBarArgCS*/ << 4) | 2 /*[*]*/,
					(57/*NavigatingCommaArgCS*/ << 4) | 2 /*[*]*/,
					(58/*NavigatingSemiArgCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::SelfExpCS-0(essentialoclcs::SelfExpCS): "self"
		serializationRules[73] = createSerializationRule("SelfExpCS-0", 80,
			createSerializationMatchSteps(
				69		/* assert |SelfExpCS::name| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				141		/* 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::ShadowPartCS-0(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[74] = createSerializationRule("ShadowPartCS-0", 81,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				224		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				43		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				44		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				95		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
				113		/* '=' || soft-space value soft-space */,
				36		/* ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 26/* ExpCS,PatternExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/,
					(68/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, -1
				)
			});
		// EssentialOCL::ShadowPartCS-1(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
		serializationRules[75] = createSerializationRule("ShadowPartCS-1", 81,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				70		/* assert |ShadowPartCS::referredProperty| == 0 */,
				225		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : StringLiteralExpCS */,
				43		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			),
			createSerializationSteps(
				37		/* ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 35/* StringLiteralExpCS */,
					(86/*StringLiteralExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SimplePathNameCS-0(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[76] = createSerializationRule("SimplePathNameCS-0", 82,
			createSerializationMatchSteps(
				153		/* check-rule basecs::PathNameCS.ownedPathElements : FirstPathElementCS */,
				39		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			),
			createSerializationSteps(
				187		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 11/* FirstPathElementCS */,
					(30/*FirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SquareBracketedClauseCS-0(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[77] = createSerializationRule("SquareBracketedClauseCS-0", 84,
			createSerializationMatchSteps(
				226		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				84		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			),
			createSerializationSteps(
				119		/* '[' || no-space value no-space */,
				76		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				151		/* V00*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				76		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				120		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::StringLiteralExpCS-0(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[78] = createSerializationRule("StringLiteralExpCS-0", 86,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				106		/* assign V0 = |StringLiteralExpCS::segments| */
			),
			createSerializationSteps(
				149		/* V00*1-steps || value */,
				97		/* StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, false, GrammarCardinality.ONE_OR_MORE)
			});
		// EssentialOCL::TupleLiteralExpCS-0(essentialoclcs::TupleLiteralExpCS): { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" }
		serializationRules[79] = createSerializationRule("TupleLiteralExpCS-0", 90,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				227		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS */,
				87		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				118		/* 'Tuple' || soft-space value soft-space */,
				144		/* '{' || soft-space value push soft-new-line */,
				58		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				151		/* V00*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				58		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				146		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 39/* TupleLiteralPartCS */,
					(91/*TupleLiteralPartCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::TupleLiteralPartCS-0(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[80] = createSerializationRule("TupleLiteralPartCS-0", 91,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				232		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				233		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				52		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				111		/* assign V0 = |VariableCS::ownedType| */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				150		/* V00*2-steps || value */,
				110		/* ':' || soft-space value soft-space */,
				87		/* VariableCS::ownedType=TypeExpCS || value */,
				113		/* '=' || soft-space value soft-space */,
				38		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TuplePartCS-0(basecs::TuplePartCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[81] = createSerializationRule("TuplePartCS-0", 92,
			createSerializationMatchSteps(
				73		/* assert |TypedElementCS::isOptional| == 0 */,
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				163		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				50		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				27		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				179		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				182		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				110		/* ':' || soft-space value soft-space */,
				86		/* TypedElementCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TupleTypeCS-0(basecs::TupleTypeCS): { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] }
		serializationRules[82] = createSerializationRule("TupleTypeCS-0", 93,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				161		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				47		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				88		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				118		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				136		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				9		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				157		/* V00*7-steps || value */,
				104		/* '(' || no-space value no-space */,
				164		/* V01*4-steps || value */,
				59		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				167		/* V02*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				59		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				105		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(6/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 40/* TuplePartCS */,
					(92/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeExpCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[83] = createSerializationRule("TypeExpCS-0", 94,
			createSerializationMatchSteps(
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				109		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				42		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				8		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				147		/* V00*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[84] = createSerializationRule("TypeExpCS-1", 94,
			createSerializationMatchSteps(
				193		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				194		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				140		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				93		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				122		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				6		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				155		/* V00*5-steps || value */,
				104		/* '(' || no-space value no-space */,
				81		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				158		/* V01*1-steps || value */,
				14		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				105		/* ')' || no-space value */,
				165		/* V02*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 42/* TypeExpWithoutMultiplicityCS */,
					(95/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[85] = createSerializationRule("TypeExpCS-2", 94,
			createSerializationMatchSteps(
				213		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				214		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				132		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				97		/* assign V0 = |MapTypeCS::ownedValueType| */,
				23		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				22		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				7		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				155		/* V00*5-steps || value */,
				104		/* '(' || no-space value no-space */,
				42		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				108		/* ',' || no-space value soft-space */,
				89		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				105		/* ')' || no-space value */,
				158		/* V01*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(5/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-3(essentialoclcs::TypeNameExpCS): { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[86] = createSerializationRule("TypeExpCS-3", 94,
			createSerializationMatchSteps(
				229		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				230		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				231		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				140		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				108		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				49		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				131		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				63		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				155		/* V00*5-steps || value */,
				20		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				162		/* V01*3-steps || value */,
				144		/* '{' || soft-space value push soft-new-line */,
				65		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				146		/* '}' || pop soft-space value soft-new-line */,
				165		/* V02*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/* CurlyBracketedClauseCS */,
					(14/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 24/* PathNameCS */,
					(67/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-4(essentialoclcs::CollectionPatternCS): { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[87] = createSerializationRule("TypeExpCS-4", 94,
			createSerializationMatchSteps(
				54		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				191		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				192		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				140		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				92		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				114		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				80		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				144		/* '{' || soft-space value push soft-new-line */,
				156		/* V00*6-steps || value */,
				55		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				161		/* V01*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				55		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				107		/* '++' || soft-space value soft-space */,
				96		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				146		/* '}' || pop soft-space value soft-new-line */,
				165		/* V02*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 25/* PatternExpCS */,
					(68/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/* CollectionTypeCS */,
					(8/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeExpCS-5(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[88] = createSerializationRule("TypeExpCS-5", 94,
			createSerializationMatchSteps(
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				161		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				145		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				47		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				88		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				118		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				136		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				9		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				157		/* V00*7-steps || value */,
				104		/* '(' || no-space value no-space */,
				164		/* V01*4-steps || value */,
				59		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				167		/* V02*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				59		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				105		/* ')' || no-space value */,
				170		/* V03*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(6/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 40/* TuplePartCS */,
					(92/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeLiteralExpCS-0(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[89] = createSerializationRule("TypeLiteralExpCS-0", 97,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				72		/* assert |TypeLiteralExpCS::ownedPathName| == 0 */,
				228		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS */,
				48		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				85		/* TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 44/* TypeLiteralWithMultiplicityCS */,
					(98/*TypeLiteralWithMultiplicityCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[90] = createSerializationRule("TypeLiteralWithMultiplicityCS-0", 98,
			createSerializationMatchSteps(
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				109		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				42		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				8		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				147		/* V00*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[91] = createSerializationRule("TypeLiteralWithMultiplicityCS-1", 98,
			createSerializationMatchSteps(
				193		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				194		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				140		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				93		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				122		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				6		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				155		/* V00*5-steps || value */,
				104		/* '(' || no-space value no-space */,
				81		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				158		/* V01*1-steps || value */,
				14		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				105		/* ')' || no-space value */,
				165		/* V02*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 42/* TypeExpWithoutMultiplicityCS */,
					(95/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[92] = createSerializationRule("TypeLiteralWithMultiplicityCS-2", 98,
			createSerializationMatchSteps(
				213		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				214		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				132		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				97		/* assign V0 = |MapTypeCS::ownedValueType| */,
				23		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				22		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				7		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				155		/* V00*5-steps || value */,
				104		/* '(' || no-space value no-space */,
				42		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				108		/* ',' || no-space value soft-space */,
				89		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				105		/* ')' || no-space value */,
				158		/* V01*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(5/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 41/* TypeExpCS */,
					(94/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-3(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[93] = createSerializationRule("TypeLiteralWithMultiplicityCS-3", 98,
			createSerializationMatchSteps(
				164		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				161		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				145		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				47		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				88		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				118		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				136		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				9		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				157		/* V00*7-steps || value */,
				104		/* '(' || no-space value no-space */,
				164		/* V01*4-steps || value */,
				59		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				167		/* V02*2-steps || value */,
				108		/* ',' || no-space value soft-space */,
				59		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				105		/* ')' || no-space value */,
				170		/* V03*1-steps || value */,
				47		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(6/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 16/* MultiplicityCS */,
					(50/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 40/* TuplePartCS */,
					(92/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeNameExpCS-0(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] }
		serializationRules[94] = createSerializationRule("TypeNameExpCS-0", 99,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				229		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				230		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				231		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				108		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				49		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				131		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				63		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				155		/* V00*5-steps || value */,
				20		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				162		/* V01*3-steps || value */,
				144		/* '{' || soft-space value push soft-new-line */,
				65		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				146		/* '}' || pop soft-space value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/* CurlyBracketedClauseCS */,
					(14/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 24/* PathNameCS */,
					(67/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 10/* ExpCS */,
					(29/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::URIFirstPathElementCS-0(basecs::PathElementWithURICS): referredElement=URI
		serializationRules[95] = createSerializationRule("URIFirstPathElementCS-0", 107,
			createSerializationMatchSteps(
				38		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				192		/* PathElementCS::referredElement=URI || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIFirstPathElementCS-1(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[96] = createSerializationRule("URIFirstPathElementCS-1", 107,
			createSerializationMatchSteps(
				38		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				194		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[97] = createSerializationRule("URIPathNameCS-0", 108,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				186		/* PathNameCS::ownedPathElements+=URIFirstPathElementCS || value */,
				151		/* V00*2-steps || value */,
				111		/* '::' || no-space value no-space */,
				188		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 50/* NextPathElementCS,URIFirstPathElementCS */,
					(61/*NextPathElementCS*/ << 4) | 2 /*[*]*/,
					(107/*URIFirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS-0(essentialoclcs::UnlimitedNaturalLiteralExpCS): "*"
		serializationRules[98] = createSerializationRule("UnlimitedNaturalLiteralExpCS-0", 110,
			createSerializationMatchSteps(
				60		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				106		/* '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		// 0: SpecificationCS::exprString=UNQUOTED_STRING || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[0] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, 104 /*UNQUOTED_STRING*/, 2);
		// 1: ImportCS::isAll?='::*' || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, 2 /* '::*' */, 6);
		// 2: MultiplicityCS::isNullFree?='|1' || no-space value no-space
		serializationSteps[2] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 10 /* '|1' */, 3);
		// 3: AbstractNameExpCS::isPre?='@' || soft-space value soft-space
		serializationSteps[3] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 4 /* '@' */, 6);
		// 4: DefCS::isStatic?='static' || soft-space value soft-space
		serializationSteps[4] = createSerializationStepAssignKeyword(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, 8 /* 'static' */, 6);
		// 5: MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space
		serializationSteps[5] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 39 /*LOWER*/, 6);
		// 6: CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space
		serializationSteps[6] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 9 /*CollectionTypeIdentifier*/, 6);
		// 7: MapTypeCS::name='Map' || soft-space value soft-space
		serializationSteps[7] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 5 /* 'Map' */, 6);
		// 8: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space
		serializationSteps[8] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 74 /*PrimitiveTypeIdentifier*/, 6);
		// 9: TupleTypeCS::name='Tuple' || soft-space value soft-space
		serializationSteps[9] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 6 /* 'Tuple' */, 6);
		// 10: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[10] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 101 /*TypeRefCS*/, 2);
		// 11: TypedTypeRefCS::ownedBinding=TemplateBindingCS || value
		serializationSteps[11] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 87 /*TemplateBindingCS*/, 0);
		// 12: OperationContextDeclCS::ownedBodies+=SpecificationCS || value
		serializationSteps[12] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, 83 /*SpecificationCS*/, 0);
		// 13: NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value
		serializationSteps[13] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 4 /*CoIteratorVariableCS*/, 0);
		// 14: CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value
		serializationSteps[14] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 50 /*MultiplicityCS*/, 0);
		// 15: IfExpCS::ownedCondition=ExpCS|PatternExpCS || value
		serializationSteps[15] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, -1, new int[] { 29/*ExpCS*/,68/*PatternExpCS*/}, 0);
		// 16: IfThenExpCS::ownedCondition=ExpCS || value
		serializationSteps[16] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 29 /*ExpCS*/, 0);
		// 17: CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS || value
		serializationSteps[17] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, 13 /*ContextDeclCS*/, 0);
		// 18: PackageDeclarationCS::ownedContexts+=ContextDeclCS || value
		serializationSteps[18] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, 13 /*ContextDeclCS*/, 0);
		// 19: AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[19] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// 20: TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[20] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /*CurlyBracketedClauseCS*/, 0);
		// 21: PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS || value
		serializationSteps[21] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, 83 /*SpecificationCS*/, 0);
		// 22: ClassifierContextDeclCS::ownedDefinitions+=DefCS || value
		serializationSteps[22] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 16 /*DefCS*/, 0);
		// 23: IfExpCS::ownedElseExpression=ExpCS || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 29 /*ExpCS*/, 0);
		// 24: CollectionLiteralPartCS::ownedExpression=ExpCS || value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 29 /*ExpCS*/, 0);
		// 25: CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 68 /*PatternExpCS*/, 2);
		// 26: ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 29 /*ExpCS*/, 2);
		// 27: ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[27] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 29 /*ExpCS*/, 2);
		// 28: NestedExpCS::ownedExpression=ExpCS || value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 29 /*ExpCS*/, 0);
		// 29: LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 29 /*ExpCS*/, 0);
		// 30: TypeParameterCS::ownedExtends+=TypedRefCS || value
		serializationSteps[30] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 102 /*TypedRefCS*/, 0);
		// 31: WildcardTypeRefCS::ownedExtends=TypedRefCS || value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 102 /*TypedRefCS*/, 0);
		// 32: IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 22 /*ElseIfThenExpCS*/, 0);
		// 33: RootCS::ownedImports+=ImportCS || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 35 /*ImportCS*/, 0);
		// 34: LetExpCS::ownedInExpression=ExpCS || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 29 /*ExpCS*/, 0);
		// 35: NavigatingArgCS::ownedInitExpression=ExpCS || value
		serializationSteps[35] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 29 /*ExpCS*/, 0);
		// 36: ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value
		serializationSteps[36] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new int[] { 29/*ExpCS*/,68/*PatternExpCS*/}, 0);
		// 37: ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 86 /*StringLiteralExpCS*/, 2);
		// 38: VariableCS::ownedInitExpression=ExpCS || value
		serializationSteps[38] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 29 /*ExpCS*/, 0);
		// 39: ClassifierContextDeclCS::ownedInvariants+=ConstraintCS || value
		serializationSteps[39] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 12 /*ConstraintCS*/, 0);
		// 40: PackageDeclarationCS::ownedInvariants+=ConstraintCS || value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, 12 /*ConstraintCS*/, 0);
		// 41: MapLiteralPartCS::ownedKey=ExpCS || value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 29 /*ExpCS*/, 0);
		// 42: MapTypeCS::ownedKeyType=TypeExpCS || value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 94 /*TypeExpCS*/, 0);
		// 43: CollectionLiteralPartCS::ownedLastExpression=ExpCS || value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 29 /*ExpCS*/, 0);
		// 44: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 70 /*PrefixedPrimaryExpCS*/, 0);
		// 45: ConstraintCS::ownedMessageSpecification=SpecificationCS || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 83 /*SpecificationCS*/, 0);
		// 46: TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 50 /*MultiplicityCS*/, 0);
		// 47: TypedRefCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[47] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 50 /*MultiplicityCS*/, 0);
		// 48: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value
		serializationSteps[48] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 55 /*NavigatingArgExpCS*/, 0);
		// 49: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 55 /*NavigatingArgExpCS*/, 2);
		// 50: CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS || value
		serializationSteps[50] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, 65 /*PackageDeclarationCS*/, 0);
		// 51: DefOperationCS::ownedParameters+=DefParameterCS || value
		serializationSteps[51] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, 18 /*DefParameterCS*/, 0);
		// 52: OperationContextDeclCS::ownedParameters+=ParameterCS || value
		serializationSteps[52] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, 66 /*ParameterCS*/, 0);
		// 53: TemplateSignatureCS::ownedParameters+=TypeParameterCS || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 100 /*TypeParameterCS*/, 0);
		// 54: CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 6 /*CollectionLiteralPartCS*/, 0);
		// 55: CollectionPatternCS::ownedParts+=PatternExpCS || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 68 /*PatternExpCS*/, 0);
		// 56: CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 81 /*ShadowPartCS*/, 0);
		// 57: MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 46 /*MapLiteralPartCS*/, 0);
		// 58: TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 91 /*TupleLiteralPartCS*/, 0);
		// 59: TupleTypeCS::ownedParts+=TuplePartCS || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 92 /*TuplePartCS*/, 0);
		// 60: AbstractNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 67 /*PathNameCS*/, 0);
		// 61: ImportCS::ownedPathName=URIPathNameCS || value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 108 /*URIPathNameCS*/, 0);
		// 62: PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 112 /*UnreservedPathNameCS*/, 0);
		// 63: TypeNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[63] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 67 /*PathNameCS*/, 0);
		// 64: TypedTypeRefCS::ownedPathName=PathNameCS || value
		serializationSteps[64] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 67 /*PathNameCS*/, 0);
		// 65: TypeNameExpCS::ownedPatternGuard=ExpCS || value
		serializationSteps[65] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 29 /*ExpCS*/, 0);
		// 66: PatternExpCS::ownedPatternType=TypeExpCS || value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 94 /*TypeExpCS*/, 0);
		// 67: OperationContextDeclCS::ownedPostconditions+=ConstraintCS || value
		serializationSteps[67] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, 12 /*ConstraintCS*/, 0);
		// 68: OperationContextDeclCS::ownedPreconditions+=ConstraintCS || value
		serializationSteps[68] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, 12 /*ConstraintCS*/, 0);
		// 69: AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[69] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 76 /*RoundBracketedClauseCS*/, 0);
		// 70: LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[70] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 76 /*RoundBracketedClauseCS*/, 0);
		// 71: TemplateableElementCS::ownedSignature=TemplateSignatureCS || value
		serializationSteps[71] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 89 /*TemplateSignatureCS*/, 0);
		// 72: ConstraintCS::ownedSpecification=SpecificationCS || value
		serializationSteps[72] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 83 /*SpecificationCS*/, 0);
		// 73: DefCS::ownedSpecification=SpecificationCS || value
		serializationSteps[73] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 83 /*SpecificationCS*/, 0);
		// 74: AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value
		serializationSteps[74] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 84 /*SquareBracketedClauseCS*/, 0);
		// 75: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value
		serializationSteps[75] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 88 /*TemplateParameterSubstitutionCS*/, 0);
		// 76: SquareBracketedClauseCS::ownedTerms+=ExpCS || value
		serializationSteps[76] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 29 /*ExpCS*/, 0);
		// 77: IfExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[77] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 29 /*ExpCS*/, 0);
		// 78: IfThenExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[78] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 29 /*ExpCS*/, 0);
		// 79: CollectionLiteralExpCS::ownedType=CollectionTypeCS || value
		serializationSteps[79] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 8 /*CollectionTypeCS*/, 0);
		// 80: CollectionPatternCS::ownedType=CollectionTypeCS || value
		serializationSteps[80] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 8 /*CollectionTypeCS*/, 0);
		// 81: CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value
		serializationSteps[81] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 95 /*TypeExpWithoutMultiplicityCS*/, 0);
		// 82: FeatureContextDeclCS::ownedType=TypeExpCS || value
		serializationSteps[82] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 94 /*TypeExpCS*/, 0);
		// 83: MapLiteralExpCS::ownedType=MapTypeCS || value
		serializationSteps[83] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 47 /*MapTypeCS*/, 0);
		// 84: NavigatingArgCS::ownedType=TypeExpCS || value
		serializationSteps[84] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 94 /*TypeExpCS*/, 0);
		// 85: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[85] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 98 /*TypeLiteralWithMultiplicityCS*/, 2);
		// 86: TypedElementCS::ownedType=TypeExpCS || value
		serializationSteps[86] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 94 /*TypeExpCS*/, 0);
		// 87: VariableCS::ownedType=TypeExpCS || value
		serializationSteps[87] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 94 /*TypeExpCS*/, 0);
		// 88: MapLiteralPartCS::ownedValue=ExpCS || value
		serializationSteps[88] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 29 /*ExpCS*/, 0);
		// 89: MapTypeCS::ownedValueType=TypeExpCS || value
		serializationSteps[89] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 94 /*TypeExpCS*/, 0);
		// 90: LetExpCS::ownedVariables+=LetVariableCS || value
		serializationSteps[90] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 42 /*LetVariableCS*/, 0);
		// 91: PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space
		serializationSteps[91] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 113 /*UnrestrictedName*/, 6);
		// 92: NavigatingArgCS::prefix=';' || no-space value soft-new-line
		serializationSteps[92] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 3 /* ';' */, 4);
		// 93: NavigatingArgCS::prefix=',' || no-space value soft-space
		serializationSteps[93] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */, 5);
		// 94: NavigatingArgCS::prefix='|' || soft-space value soft-space
		serializationSteps[94] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 9 /* '|' */, 6);
		// 95: ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[95] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 113, 6);
		// 96: CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space
		serializationSteps[96] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 33 /*Identifier*/, 6);
		// 97: StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[97] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 85 /*StringLiteral*/, 2);
		// 98: ClassifierContextDeclCS::selfName=UnrestrictedName || soft-space value soft-space
		serializationSteps[98] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME, 113 /*UnrestrictedName*/, 6);
		// 99: MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[99] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 6);
		// 100: BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[100] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 7 /* 'false|true' */, 2);
		// 101: NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[101] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 52 /*NUMBER_LITERAL*/, 2);
		// 102: MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space
		serializationSteps[102] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 105 /*UPPER*/, 6);
		// 103: '&&' || soft-space value soft-space
		serializationSteps[103] = createSerializationStepKeyword("&&", 6);
		// 104: '(' || no-space value no-space
		serializationSteps[104] = createSerializationStepKeyword("(", 3);
		// 105: ')' || no-space value
		serializationSteps[105] = createSerializationStepKeyword(")", 1);
		// 106: '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[106] = createSerializationStepKeyword("*", 2);
		// 107: '++' || soft-space value soft-space
		serializationSteps[107] = createSerializationStepKeyword("++", 6);
		// 108: ',' || no-space value soft-space
		serializationSteps[108] = createSerializationStepKeyword(",", 5);
		// 109: '..' || no-space value no-space
		serializationSteps[109] = createSerializationStepKeyword("..", 3);
		// 110: ':' || soft-space value soft-space
		serializationSteps[110] = createSerializationStepKeyword(":", 6);
		// 111: '::' || no-space value no-space
		serializationSteps[111] = createSerializationStepKeyword("::", 3);
		// 112: '<' || soft-space value soft-space
		serializationSteps[112] = createSerializationStepKeyword("<", 6);
		// 113: '=' || soft-space value soft-space
		serializationSteps[113] = createSerializationStepKeyword("=", 6);
		// 114: '>' || soft-space value soft-space
		serializationSteps[114] = createSerializationStepKeyword(">", 6);
		// 115: '?' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[115] = createSerializationStepKeyword("?", 2);
		// 116: '?' || soft-space value soft-space
		serializationSteps[116] = createSerializationStepKeyword("?", 6);
		// 117: 'Lambda' || soft-space value soft-space
		serializationSteps[117] = createSerializationStepKeyword("Lambda", 6);
		// 118: 'Tuple' || soft-space value soft-space
		serializationSteps[118] = createSerializationStepKeyword("Tuple", 6);
		// 119: '[' || no-space value no-space
		serializationSteps[119] = createSerializationStepKeyword("[", 3);
		// 120: ']' || no-space value
		serializationSteps[120] = createSerializationStepKeyword("]", 1);
		// 121: 'body' || soft-space value soft-space
		serializationSteps[121] = createSerializationStepKeyword("body", 6);
		// 122: 'context' || soft-space value soft-space
		serializationSteps[122] = createSerializationStepKeyword("context", 6);
		// 123: 'def' || soft-space value soft-space
		serializationSteps[123] = createSerializationStepKeyword("def", 6);
		// 124: 'derive' || soft-space value soft-space
		serializationSteps[124] = createSerializationStepKeyword("derive", 6);
		// 125: 'else' || soft-space value soft-space
		serializationSteps[125] = createSerializationStepKeyword("else", 6);
		// 126: 'elseif' || soft-space value soft-space
		serializationSteps[126] = createSerializationStepKeyword("elseif", 6);
		// 127: 'endif' || soft-space value soft-space
		serializationSteps[127] = createSerializationStepKeyword("endif", 6);
		// 128: 'endpackage' || soft-space value soft-space
		serializationSteps[128] = createSerializationStepKeyword("endpackage", 6);
		// 129: 'extends' || soft-space value soft-space
		serializationSteps[129] = createSerializationStepKeyword("extends", 6);
		// 130: 'if' || soft-space value soft-space
		serializationSteps[130] = createSerializationStepKeyword("if", 6);
		// 131: 'import' || value
		serializationSteps[131] = createSerializationStepKeyword("import", 0);
		// 132: 'in' || soft-space value soft-space
		serializationSteps[132] = createSerializationStepKeyword("in", 6);
		// 133: 'init' || soft-space value soft-space
		serializationSteps[133] = createSerializationStepKeyword("init", 6);
		// 134: 'inv' || soft-space value soft-space
		serializationSteps[134] = createSerializationStepKeyword("inv", 6);
		// 135: 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[135] = createSerializationStepKeyword("invalid", 2);
		// 136: 'let' || soft-space value soft-space
		serializationSteps[136] = createSerializationStepKeyword("let", 6);
		// 137: 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[137] = createSerializationStepKeyword("null", 2);
		// 138: 'package' || soft-space value soft-space
		serializationSteps[138] = createSerializationStepKeyword("package", 6);
		// 139: 'post' || soft-space value soft-space
		serializationSteps[139] = createSerializationStepKeyword("post", 6);
		// 140: 'pre' || soft-space value soft-space
		serializationSteps[140] = createSerializationStepKeyword("pre", 6);
		// 141: 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[141] = createSerializationStepKeyword("self", 2);
		// 142: 'then' || soft-space value soft-space
		serializationSteps[142] = createSerializationStepKeyword("then", 6);
		// 143: 'with' || value
		serializationSteps[143] = createSerializationStepKeyword("with", 0);
		// 144: '{' || soft-space value push soft-new-line
		serializationSteps[144] = createSerializationStepKeyword("{", 8);
		// 145: '|?' || no-space value no-space
		serializationSteps[145] = createSerializationStepKeyword("|?", 3);
		// 146: '}' || pop soft-space value soft-new-line
		serializationSteps[146] = createSerializationStepKeyword("}", 7);
		// 147: V00*1-steps || value
		serializationSteps[147] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 1, 0);
		// 148: V00*1-steps || value
		serializationSteps[148] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 1, 0);
		// 149: V00*1-steps || value
		serializationSteps[149] = createSerializationStepSequence((0/*V0*/ << 4) | 3/*[+]*/, 1, 0);
		// 150: V00*2-steps || value
		serializationSteps[150] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 151: V00*2-steps || value
		serializationSteps[151] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 2, 0);
		// 152: V00*3-steps || value
		serializationSteps[152] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 3, 0);
		// 153: V00*3-steps || value
		serializationSteps[153] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 3, 0);
		// 154: V00*4-steps || value
		serializationSteps[154] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 4, 0);
		// 155: V00*5-steps || value
		serializationSteps[155] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 5, 0);
		// 156: V00*6-steps || value
		serializationSteps[156] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 6, 0);
		// 157: V00*7-steps || value
		serializationSteps[157] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 7, 0);
		// 158: V01*1-steps || value
		serializationSteps[158] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 1, 0);
		// 159: V01*1-steps || value
		serializationSteps[159] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 1, 0);
		// 160: V01*2-steps || value
		serializationSteps[160] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 2, 0);
		// 161: V01*2-steps || value
		serializationSteps[161] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 2, 0);
		// 162: V01*3-steps || value
		serializationSteps[162] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 3, 0);
		// 163: V01*3-steps || value
		serializationSteps[163] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 3, 0);
		// 164: V01*4-steps || value
		serializationSteps[164] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 4, 0);
		// 165: V02*1-steps || value
		serializationSteps[165] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 1, 0);
		// 166: V02*1-steps || value
		serializationSteps[166] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 1, 0);
		// 167: V02*2-steps || value
		serializationSteps[167] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 2, 0);
		// 168: V02*2-steps || value
		serializationSteps[168] = createSerializationStepSequence((2/*V2*/ << 4) | 3/*[+]*/, 2, 0);
		// 169: V02*4-steps || value
		serializationSteps[169] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 4, 0);
		// 170: V03*1-steps || value
		serializationSteps[170] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 1, 0);
		// 171: V03*1-steps || value
		serializationSteps[171] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 1, 0);
		// 172: V03*1-steps || value
		serializationSteps[172] = createSerializationStepSequence((3/*V3*/ << 4) | 3/*[+]*/, 1, 0);
		// 173: V03*2-steps || value
		serializationSteps[173] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 2, 0);
		// 174: V03*2-steps || value
		serializationSteps[174] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 2, 0);
		// 175: V04*1-steps || value
		serializationSteps[175] = createSerializationStepSequence((4/*V4*/ << 4) | 1/*[?]*/, 1, 0);
		// 176: V04*3-steps || value
		serializationSteps[176] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 3, 0);
		// 177: V05*2-steps || value
		serializationSteps[177] = createSerializationStepSequence((5/*V5*/ << 4) | 2/*[*]*/, 2, 0);
		// 178: V06*2-steps || value
		serializationSteps[178] = createSerializationStepSequence((6/*V6*/ << 4) | 2/*[*]*/, 2, 0);
		// 179: wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[179] = createSerializationStepWrapper(2);
		// 180: NamedElementCS::name=BinaryOperatorName || soft-space value soft-space
		serializationSteps[180] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 1 /*BinaryOperatorName*/, 6);
		// 181: NamedElementCS::name=UnaryOperatorName || soft-space value soft-space
		serializationSteps[181] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 109 /*UnaryOperatorName*/, 6);
		// 182: NamedElementCS::name=UnrestrictedName || soft-space value soft-space
		serializationSteps[182] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 113 /*UnrestrictedName*/, 6);
		// 183: NamedElementCS::name=Identifier || soft-space value soft-space
		serializationSteps[183] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 33 /*Identifier*/, 6);
		// 184: RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value
		serializationSteps[184] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 54 /*NavigatingArgCS*/, 0);
		// 185: RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value
		serializationSteps[185] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new int[] { 57/*NavigatingCommaArgCS*/,58/*NavigatingSemiArgCS*/,56/*NavigatingBarArgCS*/}, 0);
		// 186: PathNameCS::ownedPathElements+=URIFirstPathElementCS || value
		serializationSteps[186] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 107 /*URIFirstPathElementCS*/, 0);
		// 187: PathNameCS::ownedPathElements+=FirstPathElementCS || value
		serializationSteps[187] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 30 /*FirstPathElementCS*/, 0);
		// 188: PathNameCS::ownedPathElements+=NextPathElementCS || value
		serializationSteps[188] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 61 /*NextPathElementCS*/, 0);
		// 189: OperatorExpCS::ownedRight=ExpCS || value
		serializationSteps[189] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 29 /*ExpCS*/, 0);
		// 190: OperatorExpCS::ownedRight=PrefixedLetExpCS || value
		serializationSteps[190] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 69 /*PrefixedLetExpCS*/, 0);
		// 191: OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value
		serializationSteps[191] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 70 /*PrefixedPrimaryExpCS*/, 0);
		// 192: PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[192] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 106, 6);
		// 193: PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[193] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 111, 6);
		// 194: PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[194] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 113, 6);
	}
}

//	Commented imports ensure Xtend provides a true import allowing unqualified annotated usage
//	import Inject;
//	import NonNull;
//	import Nullable;
//	import DataTypeRuleValue;
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
//	import TerminalRuleValue;
//	import BaseCommentSegmentSupport;
//	import BaseCSPackage;
//	import CompleteOCLCSPackage;
//	import EssentialOCLCSPackage;
//	import Grammar;
//	import GrammarProvider;
