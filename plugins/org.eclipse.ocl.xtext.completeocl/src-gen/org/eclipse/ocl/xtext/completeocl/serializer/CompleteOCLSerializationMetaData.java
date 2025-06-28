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
package org.eclipse.ocl.xtext.completeocl.serializer;

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
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[121];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[66];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[236];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[194];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[107];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [26] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[203];
	private final @NonNull SubstringStep @NonNull [] substringSteps = new @NonNull SubstringStep[11];
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
		return 105;
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
		return 104;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 155;
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
				42 /* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			), null
		);
		eClassValues[1] = new EClassValue(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS,
			createSerializationRules(
				17 /* ClassifierContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS (ClassifierContextDeclCS::ownedInvariants+=invConstraintCS)[V2:+] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:*] */,
				18 /* ClassifierContextDeclCS-1: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS (ClassifierContextDeclCS::ownedInvariants+=invConstraintCS)[V2:*] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:+] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					7) /* DefCS|DefOperationCS|DefPropertyCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					63) /* invConstraintCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					59) /* UnreservedPathNameCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					37) /* TemplateSignatureCS */
			}
		);
		eClassValues[2] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
			createSerializationRules(
				44 /* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
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
				46 /* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */,
				45 /* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[4] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
			createSerializationRules(
				47 /* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				95 /* TypeExpCS-4: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					15) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					24) /* PatternExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					2) /* CollectionTypeCS */
			}
		);
		eClassValues[5] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
			createSerializationRules(
				48 /* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				92 /* TypeExpCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				99 /* TypeLiteralWithMultiplicityCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					15) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					15) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					44) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[6] = new EClassValue(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS,
			createSerializationRules(
				19 /* CompleteOCLDocumentCS-0: (RootCS::ownedImports+=ImportCS)[V0:*] (CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS)[V1:*] (CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS)[V2:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
					29) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					11) /* ImportCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
					21) /* PackageDeclarationCS */
			}
		);
		eClassValues[7] = new EClassValue(BaseCSPackage.Literals.CONSTRAINT_CS,
			createSerializationRules(
				39 /* invConstraintCS-0: 'inv' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS */,
				40 /* postConstraintCS-0: 'post' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS */,
				41 /* preConstraintCS-0: 'pre' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					32) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					32) /* SpecificationCS */
			}
		);
		eClassValues[8] = new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
			createSerializationRules(
				60 /* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[9] = new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				49 /* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					31) /* ShadowPartCS */
			}
		);
		eClassValues[10] = new EClassValue(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS,
			createSerializationRules(
				20 /* DefOperationCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (DefOperationCS::ownedParameters+=DefParameterCS (',' DefOperationCS::ownedParameters+=DefParameterCS)[V3:*])[V2:?] ')' ':' (TypedElementCS::ownedType=TypeExpCS)[V4:?] '=' DefCS::ownedSpecification=SpecificationCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS,
					6) /* DefParameterCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					37) /* TemplateSignatureCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					32) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[11] = new EClassValue(CompleteOCLCSPackage.Literals.DEF_PROPERTY_CS,
			createSerializationRules(
				22 /* DefPropertyCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS '=' DefCS::ownedSpecification=SpecificationCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					32) /* SpecificationCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[12] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
			createSerializationRules(
				29 /* SpecificationCS-0: ExpSpecificationCS::ownedExpression=ExpCS */,
				30 /* SpecificationCS-1: SpecificationCS::exprString=UNQUOTED_STRING */,
				33 /* bodySpecificationCS-0: 'body' ':' ExpSpecificationCS::ownedExpression=ExpCS */,
				34 /* bodySpecificationCS-1: 'body' ':' SpecificationCS::exprString=UNQUOTED_STRING */,
				35 /* deriveSpecificationCS-0: 'derive' ':' ExpSpecificationCS::ownedExpression=ExpCS */,
				36 /* deriveSpecificationCS-1: 'derive' ':' SpecificationCS::exprString=UNQUOTED_STRING */,
				37 /* initSpecificationCS-0: 'init' ':' ExpSpecificationCS::ownedExpression=ExpCS */,
				38 /* initSpecificationCS-1: 'init' ':' SpecificationCS::exprString=UNQUOTED_STRING */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[13] = new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
			createSerializationRules(
				52 /* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					8) /* ElseIfThenExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[14] = new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
			createSerializationRules(
				50 /* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[15] = new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
			createSerializationRules(
				23 /* ImportCS-0: 'import' (NamedElementCS::name=Identifier ':')[V0:?] ImportCS::ownedPathName=URIPathNameCS (ImportCS::isAll?='::*')[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					51) /* URIPathNameCS */
			}
		);
		eClassValues[16] = new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
			createSerializationRules(
				51 /* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					54) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[17] = new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
			createSerializationRules(
				53 /* InvalidLiteralExpCS-0: 'invalid' */
			), null
		);
		eClassValues[18] = new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
			createSerializationRules(
				54 /* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[19] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
			createSerializationRules(
				55 /* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					12) /* LetVariableCS */
			}
		);
		eClassValues[20] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
			createSerializationRules(
				56 /* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					30) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[21] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
			createSerializationRules(
				57 /* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					13) /* MapLiteralPartCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					14) /* MapTypeCS */
			}
		);
		eClassValues[22] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
			createSerializationRules(
				58 /* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[23] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
			createSerializationRules(
				59 /* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				93 /* TypeExpCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				100 /* TypeLiteralWithMultiplicityCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					40) /* TypeExpCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					15) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					40) /* TypeExpCS */
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
				61 /* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					4) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					30) /* RoundBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					33) /* SquareBracketedClauseCS */
			}
		);
		eClassValues[27] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
			createSerializationRules(
				66 /* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				62 /* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				65 /* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				63 /* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				64 /* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				67 /* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */,
				71 /* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				70 /* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				68 /* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				69 /* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				72 /* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					0) /* CoIteratorVariableCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[28] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				73 /* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[29] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				74 /* NullLiteralExpCS-0: 'null' */
			), null
		);
		eClassValues[30] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				75 /* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[31] = new EClassValue(CompleteOCLCSPackage.Literals.OCL_MESSAGE_ARG_CS,
			createSerializationRules(
				24 /* NavigatingArgExpCS-0: '?' */
			), null
		);
		eClassValues[32] = new EClassValue(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS,
			createSerializationRules(
				25 /* OperationContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS '(' (OperationContextDeclCS::ownedParameters+=ParameterCS (',' OperationContextDeclCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' ':' (FeatureContextDeclCS::ownedType=TypeExpCS)[V3:?] (OperationContextDeclCS::ownedPreconditions+=preConstraintCS)[V4:*] (OperationContextDeclCS::ownedPostconditions+=postConstraintCS)[V5:*] (OperationContextDeclCS::ownedBodies+=bodySpecificationCS)[V6:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
					61) /* bodySpecificationCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
					22) /* ParameterCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					59) /* UnreservedPathNameCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
					64) /* postConstraintCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
					65) /* preConstraintCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					37) /* TemplateSignatureCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[33] = new EClassValue(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS,
			createSerializationRules(
				26 /* PackageDeclarationCS-0: 'package' PathNameDeclCS::ownedPathName=UnreservedPathNameCS (PackageDeclarationCS::ownedInvariants+=invConstraintCS)[V0:*] (PackageDeclarationCS::ownedContexts+=ContextDeclCS)[V1:*] 'endpackage' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
					29) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
					63) /* invConstraintCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					59) /* UnreservedPathNameCS */
			}
		);
		eClassValues[34] = new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
			createSerializationRules(
				21 /* DefParameterCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */,
				27 /* ParameterCS-0: (NamedElementCS::name=UnrestrictedName ':')[V0:?] TypedElementCS::ownedType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[35] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */,
				9 /* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */,
				104 /* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */
			), null
		);
		eClassValues[36] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				103 /* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */
			), null
		);
		eClassValues[37] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				15 /* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				84 /* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */,
				105 /* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					50) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
			}
		);
		eClassValues[38] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				76 /* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[39] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				77 /* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				78 /* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					55) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[40] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
			createSerializationRules(
				79 /* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				91 /* TypeExpCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				98 /* TypeLiteralWithMultiplicityCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					15) /* MultiplicityCS */
			}
		);
		eClassValues[41] = new EClassValue(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS,
			createSerializationRules(
				28 /* PropertyContextDeclCS-0: 'context' PathNameDeclCS::ownedPathName=UnreservedPathNameCS ':' FeatureContextDeclCS::ownedType=TypeExpCS (PropertyContextDeclCS::ownedDefaultExpressions+=deriveSpecificationCS|initSpecificationCS)[V0:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
					62) /* deriveSpecificationCS|initSpecificationCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					59) /* UnreservedPathNameCS */,
				createEReference_TargetGrammarRuleVector(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[42] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				80 /* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					18) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[43] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				81 /* SelfExpCS-0: 'self' */
			), null
		);
		eClassValues[44] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				83 /* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */,
				82 /* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[45] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				85 /* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[46] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				86 /* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			), null
		);
		eClassValues[47] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					15) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					36) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[48] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[49] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				31 /* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */,
				32 /* TemplateSignatureCS-1: '<' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] '>' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					45) /* TypeParameterCS */
			}
		);
		eClassValues[50] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				87 /* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					38) /* TupleLiteralPartCS */
			}
		);
		eClassValues[51] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
			createSerializationRules(
				88 /* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[52] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				89 /* TuplePartCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[53] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
			createSerializationRules(
				90 /* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				96 /* TypeExpCS-5: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */,
				101 /* TypeLiteralWithMultiplicityCS-3: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					15) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					39) /* TuplePartCS */
			}
		);
		eClassValues[54] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				97 /* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					43) /* TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[55] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
			createSerializationRules(
				94 /* TypeExpCS-3: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				102 /* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					4) /* CurlyBracketedClauseCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					15) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */,
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[56] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				13 /* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					48) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[57] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				14 /* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					35) /* TemplateBindingCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					23) /* PathNameCS */
			}
		);
		eClassValues[58] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				106 /* UnlimitedNaturalLiteralExpCS-0: '*' */
			), null
		);
		eClassValues[59] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				43 /* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					40) /* TypeExpCS */
			}
		);
		eClassValues[60] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				16 /* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					48) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
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
		grammarRuleValues[1] = createDataTypeRuleValue(1, "BinaryOperatorName", 12 /* [soft-space, value, soft-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			2	/* '=' : [soft-space, value, soft-new-line, push] */,
			3	/* '?->' : [no-space, value, no-space] */,
			4	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[2] = createParserRuleValue(2, "BooleanLiteralExpCS", -1,
			createSerializationRules(
				42	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* symbol="true" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* symbol="false" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "ClassifierContextDeclCS", -1,
			createSerializationRules(
				17	/* ClassifierContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS (ClassifierContextDeclCS::ownedInvariants+=invConstraintCS)[V2:+] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:*] */,
				18	/* ClassifierContextDeclCS-1: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS (ClassifierContextDeclCS::ownedInvariants+=invConstraintCS)[V2:*] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:+] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 9	/* "context" : [value] | [soft-new-line, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 12	/* selfName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=UnreservedPathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives+ : [value] | [value] */,
			(0 << 16) | 0	/* ownedInvariants+=invConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedDefinitions+=DefCS : [value] | [value] */
		);
		grammarRuleValues[4] = createParserRuleValue(4, "CoIteratorVariableCS", -1,
			createSerializationRules(
				43	/* CoIteratorVariableCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[5] = createParserRuleValue(5, "CollectionLiteralExpCS", -1,
			createSerializationRules(
				44	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=CollectionLiteralPartCS : [value] | [value] */,
			(0 << 16) | 14	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[6] = createParserRuleValue(6, "CollectionLiteralPartCS", -1,
			createSerializationRules(
				45	/* CollectionLiteralPartCS-0: CollectionLiteralPartCS::ownedExpression=ExpCS ('..' CollectionLiteralPartCS::ownedLastExpression=ExpCS)[V0:?] */,
				46	/* CollectionLiteralPartCS-1: CollectionLiteralPartCS::ownedExpression=PatternExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedLastExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=PatternExpCS : [value] | [value] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "CollectionPatternCS", -1,
			createSerializationRules(
				47	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */
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
			(0 << 16) | 12	/* "++" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* restVariableName=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 14	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[8] = createParserRuleValue(8, "CollectionTypeCS", -1,
			createSerializationRules(
				48	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 12	/* name=CollectionTypeIdentifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedCollectionMultiplicity=MultiplicityCS? : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[9] = createDataTypeRuleValue(9, "CollectionTypeIdentifier", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[10] = createParserRuleValue(10, "CompleteOCLDocumentCS", -1,
			createSerializationRules(
				19	/* CompleteOCLDocumentCS-0: (RootCS::ownedImports+=ImportCS)[V0:*] (CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS)[V1:*] (CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS)[V2:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 4	/* ownedImports+=ImportCS* : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 13	/* ownedPackages+=PackageDeclarationCS : [value] | [half-new-line, soft-new-line, value, half-new-line] */,
			(0 << 16) | 0	/* ownedContexts+=ContextDeclCS : [value] | [value] */
		);
		grammarRuleValues[11] = createDataTypeRuleValue(11, "CompleteOCLNavigationOperatorName", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[12] = createParserRuleValue(12, "ContextDeclCS", 29 /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
			createSerializationRules(
				17	/* ClassifierContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS (ClassifierContextDeclCS::ownedInvariants+=invConstraintCS)[V2:+] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:*] */,
				18	/* ClassifierContextDeclCS-1: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] (ClassifierContextDeclCS::selfName=UnrestrictedName)[V1:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS (ClassifierContextDeclCS::ownedInvariants+=invConstraintCS)[V2:*] (ClassifierContextDeclCS::ownedDefinitions+=DefCS)[V3:+] */,
				25	/* OperationContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS '(' (OperationContextDeclCS::ownedParameters+=ParameterCS (',' OperationContextDeclCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' ':' (FeatureContextDeclCS::ownedType=TypeExpCS)[V3:?] (OperationContextDeclCS::ownedPreconditions+=preConstraintCS)[V4:*] (OperationContextDeclCS::ownedPostconditions+=postConstraintCS)[V5:*] (OperationContextDeclCS::ownedBodies+=bodySpecificationCS)[V6:*] */,
				28	/* PropertyContextDeclCS-0: 'context' PathNameDeclCS::ownedPathName=UnreservedPathNameCS ':' FeatureContextDeclCS::ownedType=TypeExpCS (PropertyContextDeclCS::ownedDefaultExpressions+=deriveSpecificationCS|initSpecificationCS)[V0:*] */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* PropertyContextDeclCS : [value] | [value] */,
			(0 << 16) | 0	/* ClassifierContextDeclCS : [value] | [value] */,
			(0 << 16) | 0	/* OperationContextDeclCS : [value] | [value] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "CurlyBracketedClauseCS", -1,
			createSerializationRules(
				49	/* CurlyBracketedClauseCS-0: '{' (CurlyBracketedClauseCS::ownedParts+=ShadowPartCS (',' CurlyBracketedClauseCS::ownedParts+=ShadowPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {CurlyBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=ShadowPartCS : [value] | [value] */,
			(0 << 16) | 14	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[14] = new TerminalRuleValue(14, "DOUBLE_QUOTED_STRING");
		grammarRuleValues[15] = createParserRuleValue(15, "DefCS", 7 /* DefCS|DefOperationCS|DefPropertyCS */,
			createSerializationRules(
				20	/* DefOperationCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (DefOperationCS::ownedParameters+=DefParameterCS (',' DefOperationCS::ownedParameters+=DefParameterCS)[V3:*])[V2:?] ')' ':' (TypedElementCS::ownedType=TypeExpCS)[V4:?] '=' DefCS::ownedSpecification=SpecificationCS */,
				22	/* DefPropertyCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS '=' DefCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* DefOperationCS : [value] | [value] */,
			(0 << 16) | 0	/* DefPropertyCS : [value] | [value] */
		);
		grammarRuleValues[16] = createParserRuleValue(16, "DefOperationCS", -1,
			createSerializationRules(
				20	/* DefOperationCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V1:?] NamedElementCS::name=UnrestrictedName '(' (DefOperationCS::ownedParameters+=DefParameterCS (',' DefOperationCS::ownedParameters+=DefParameterCS)[V3:*])[V2:?] ')' ':' (TypedElementCS::ownedType=TypeExpCS)[V4:?] '=' DefCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 24	/* Group : [value] | [soft-new-line, push, value, pop, pop] */,
			(0 << 16) | 12	/* isStatic?="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "def" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameters+=DefParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=DefParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS? : [value] | [value] */,
			(0 << 16) | 20	/* "=" : [value] | [soft-space, value, soft-new-line, push] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "DefParameterCS", -1,
			createSerializationRules(
				21	/* DefParameterCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[18] = createParserRuleValue(18, "DefPropertyCS", -1,
			createSerializationRules(
				22	/* DefPropertyCS-0: (DefCS::isStatic?='static')[V0:?] 'def' ':' NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS '=' DefCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 24	/* Group : [value] | [soft-new-line, push, value, pop, pop] */,
			(0 << 16) | 12	/* isStatic?="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "def" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 20	/* "=" : [value] | [soft-space, value, soft-new-line, push] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[19] = new TerminalRuleValue(19, "ESCAPED_CHARACTER");
		grammarRuleValues[20] = new TerminalRuleValue(20, "ESCAPED_ID");
		grammarRuleValues[21] = createParserRuleValue(21, "ElseIfThenExpCS", -1,
			createSerializationRules(
				50	/* ElseIfThenExpCS-0: 'elseif' IfThenExpCS::ownedCondition=ExpCS 'then' IfThenExpCS::ownedThenExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 25	/* "elseif" : [value] | [soft-new-line, pop, soft-space, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=ExpCS : [value] | [value] */,
			(0 << 16) | 15	/* "then" : [value] | [pop, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[22] = createDataTypeRuleValue(22, "EssentialOCLInfixOperatorName", 12 /* [soft-space, value, soft-space] */,
			2	/* '=' : [soft-space, value, soft-new-line, push] */);
		grammarRuleValues[23] = createDataTypeRuleValue(23, "EssentialOCLNavigationOperatorName", 6 /* [no-space, value, no-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			3	/* '?->' : [no-space, value, no-space] */,
			4	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[24] = createDataTypeRuleValue(24, "EssentialOCLReservedKeyword", 12 /* [soft-space, value, soft-space] */,
			5	/* 'else' : [soft-new-line, pop, value, push, soft-space] */,
			6	/* 'endif' : [soft-new-line, pop, value, soft-space] */,
			7	/* 'if' : [soft-new-line, value, push, soft-space] */,
			8	/* 'in' : [soft-space, pop, value, soft-new-line] */,
			9	/* 'let' : [soft-space, value, push] */,
			10	/* 'then' : [pop, soft-space, value, push, soft-space] */);
		grammarRuleValues[25] = createDataTypeRuleValue(25, "EssentialOCLUnaryOperatorName", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[26] = createDataTypeRuleValue(26, "EssentialOCLUnreservedName", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[27] = createDataTypeRuleValue(27, "EssentialOCLUnrestrictedName", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[28] = createParserRuleValue(28, "ExpCS", 56 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				42	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				44	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				51	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				52	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				53	/* InvalidLiteralExpCS-0: 'invalid' */,
				54	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				55	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				57	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				61	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				73	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				74	/* NullLiteralExpCS-0: 'null' */,
				75	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				77	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				78	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				81	/* SelfExpCS-0: 'self' */,
				86	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				87	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				97	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				106	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* {InfixExpCS} : [value] | [value] */,
			(0 << 16) | 12	/* name=BinaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrefixedLetExpCS : [value] | [value] */
		);
		grammarRuleValues[29] = createParserRuleValue(29, "FirstPathElementCS", -1,
			createSerializationRules(
				0	/* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 12	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[30] = createDataTypeRuleValue(30, "ID", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[31] = new TerminalRuleValue(31, "INT");
		grammarRuleValues[32] = createDataTypeRuleValue(32, "Identifier", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[33] = createParserRuleValue(33, "IfExpCS", -1,
			createSerializationRules(
				52	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 17	/* "if" : [value] | [soft-new-line, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 22	/* "then" : [value] | [pop, soft-space, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedIfThenExpressions+=ElseIfThenExpCS* : [value] | [value] */,
			(0 << 16) | 23	/* "else" : [value] | [soft-new-line, pop, value, push, soft-space] */,
			(0 << 16) | 0	/* ownedElseExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 16	/* "endif" : [value] | [soft-new-line, pop, value, soft-space] */
		);
		grammarRuleValues[34] = createParserRuleValue(34, "ImportCS", -1,
			createSerializationRules(
				23	/* ImportCS-0: 'import' (NamedElementCS::name=Identifier ':')[V0:?] ImportCS::ownedPathName=URIPathNameCS (ImportCS::isAll?='::*')[V1:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 12	/* "import" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "include" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "library" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* name=Identifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=URIPathNameCS : [value] | [value] */,
			(0 << 16) | 12	/* isAll?="::*"? : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[35] = createDataTypeRuleValue(35, "InfixOperatorName", 12 /* [soft-space, value, soft-space] */,
			2	/* '=' : [soft-space, value, soft-new-line, push] */);
		grammarRuleValues[36] = createParserRuleValue(36, "InvalidLiteralExpCS", -1,
			createSerializationRules(
				53	/* InvalidLiteralExpCS-0: 'invalid' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {InvalidLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 12	/* "invalid" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[37] = new TerminalRuleValue(37, "LETTER_CHARACTER");
		grammarRuleValues[38] = createDataTypeRuleValue(38, "LOWER", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[39] = createParserRuleValue(39, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				54	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 14	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[40] = createParserRuleValue(40, "LetExpCS", -1,
			createSerializationRules(
				55	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 11	/* "let" : [value] | [soft-space, value, push] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedVariables+=LetVariableCS : [value] | [value] */,
			(0 << 16) | 18	/* "in" : [value] | [soft-space, pop, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedInExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "LetVariableCS", -1,
			createSerializationRules(
				56	/* LetVariableCS-0: NamedElementCS::name=UnrestrictedName (LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V0:?] (':' VariableCS::ownedType=TypeExpCS)[V1:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 12	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[42] = new TerminalRuleValue(42, "ML_COMMENT");
		grammarRuleValues[43] = new TerminalRuleValue(43, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[44] = createParserRuleValue(44, "MapLiteralExpCS", -1,
			createSerializationRules(
				57	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedType=MapTypeCS : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=MapLiteralPartCS : [value] | [value] */,
			(0 << 16) | 14	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[45] = createParserRuleValue(45, "MapLiteralPartCS", -1,
			createSerializationRules(
				58	/* MapLiteralPartCS-0: MapLiteralPartCS::ownedKey=ExpCS 'with' MapLiteralPartCS::ownedValue=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedKey=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 12	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValue=ExpCS : [value] | [value] */
		);
		grammarRuleValues[46] = createParserRuleValue(46, "MapTypeCS", -1,
			createSerializationRules(
				59	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 12	/* name="Map" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedKeyType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValueType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[47] = createParserRuleValue(47, "Model", -1,
			createSerializationRules(
				60	/* Model-0: ContextCS::ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[48] = createParserRuleValue(48, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 12	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 12	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[49] = createParserRuleValue(49, "MultiplicityCS", -1,
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
		grammarRuleValues[50] = createParserRuleValue(50, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			),
			(0 << 16) | 12	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[51] = createDataTypeRuleValue(51, "NUMBER_LITERAL", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[52] = createParserRuleValue(52, "NameExpCS", -1,
			createSerializationRules(
				61	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedSquareBracketedClauses+=SquareBracketedClauseCS* : [value] | [value] */,
			(0 << 16) | 0	/* ownedRoundBracketedClause=RoundBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 10	/* isPre?="@" : [value] | [soft-space, value, no-space] */,
			(0 << 16) | 8	/* "pre" : [value] | [no-space, value, soft-space] */
		);
		grammarRuleValues[53] = createParserRuleValue(53, "NavigatingArgCS", -1,
			createSerializationRules(
				62	/* NavigatingArgCS-0: ':' NavigatingArgCS::ownedType=TypeExpCS */,
				63	/* NavigatingArgCS-1: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				64	/* NavigatingArgCS-2: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				65	/* NavigatingArgCS-3: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				66	/* NavigatingArgCS-4: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 12	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 12	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 12	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 12	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[54] = createParserRuleValue(54, "NavigatingArgExpCS", 57 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				42	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				44	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				51	/* ExpCS-18: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS NamedElementCS::name=BinaryOperatorName OperatorExpCS::ownedRight=ExpCS */,
				52	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				53	/* InvalidLiteralExpCS-0: 'invalid' */,
				54	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				55	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				57	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				61	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				24	/* NavigatingArgExpCS-0: '?' */,
				73	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				74	/* NullLiteralExpCS-0: 'null' */,
				75	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				77	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */,
				78	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				81	/* SelfExpCS-0: 'self' */,
				86	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				87	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				97	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				106	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {OCLMessageArgCS} : [value] | [value] */,
			(0 << 16) | 12	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ExpCS : [value] | [value] */
		);
		grammarRuleValues[55] = createParserRuleValue(55, "NavigatingBarArgCS", -1,
			createSerializationRules(
				67	/* NavigatingBarArgCS-0: NavigatingArgCS::prefix='|' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* prefix="|" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[56] = createParserRuleValue(56, "NavigatingCommaArgCS", -1,
			createSerializationRules(
				68	/* NavigatingCommaArgCS-0: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS ':' NavigatingArgCS::ownedType=TypeExpCS ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V0:?] ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?] */,
				69	/* NavigatingCommaArgCS-1: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS)[V0:?] ('with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS)[V1:?] 'in' NavigatingArgCS::ownedInitExpression=ExpCS */,
				70	/* NavigatingCommaArgCS-2: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS 'with' NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V0:?] */,
				71	/* NavigatingCommaArgCS-3: NavigatingArgCS::prefix=',' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 8	/* prefix="," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 12	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 12	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 12	/* "with" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCoIterator=CoIteratorVariableCS : [value] | [value] */,
			(0 << 16) | 12	/* "in" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[57] = createParserRuleValue(57, "NavigatingSemiArgCS", -1,
			createSerializationRules(
				72	/* NavigatingSemiArgCS-0: NavigatingArgCS::prefix=';' NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS (':' NavigatingArgCS::ownedType=TypeExpCS ('=' NavigatingArgCS::ownedInitExpression=ExpCS)[V1:?])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* prefix=";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedNameExpression=NavigatingArgExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[58] = createDataTypeRuleValue(58, "NavigationOperatorName", 12 /* [soft-space, value, soft-space] */,
			0	/* '->' : [no-space, value, no-space] */,
			1	/* '.' : [no-space, value, no-space] */,
			3	/* '?->' : [no-space, value, no-space] */,
			4	/* '?.' : [no-space, value, no-space] */);
		grammarRuleValues[59] = createParserRuleValue(59, "NestedExpCS", -1,
			createSerializationRules(
				73	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 3	/* "(" : [value] | [value, no-space] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[60] = createParserRuleValue(60, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */
			),
			(0 << 16) | 12	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[61] = createParserRuleValue(61, "NullLiteralExpCS", -1,
			createSerializationRules(
				74	/* NullLiteralExpCS-0: 'null' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {NullLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 12	/* "null" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[62] = createParserRuleValue(62, "NumberLiteralExpCS", -1,
			createSerializationRules(
				75	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			),
			(0 << 16) | 2	/* symbol=NUMBER_LITERAL : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[63] = createParserRuleValue(63, "OperationContextDeclCS", -1,
			createSerializationRules(
				25	/* OperationContextDeclCS-0: 'context' (TemplateableElementCS::ownedSignature=TemplateSignatureCS)[V0:?] PathNameDeclCS::ownedPathName=UnreservedPathNameCS '(' (OperationContextDeclCS::ownedParameters+=ParameterCS (',' OperationContextDeclCS::ownedParameters+=ParameterCS)[V2:*])[V1:?] ')' ':' (FeatureContextDeclCS::ownedType=TypeExpCS)[V3:?] (OperationContextDeclCS::ownedPreconditions+=preConstraintCS)[V4:*] (OperationContextDeclCS::ownedPostconditions+=postConstraintCS)[V5:*] (OperationContextDeclCS::ownedBodies+=bodySpecificationCS)[V6:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 9	/* "context" : [value] | [soft-new-line, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=UnreservedPathNameCS : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS? : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedPreconditions+=preConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPostconditions+=postConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedBodies+=bodySpecificationCS : [value] | [value] */
		);
		grammarRuleValues[64] = createParserRuleValue(64, "PackageDeclarationCS", -1,
			createSerializationRules(
				26	/* PackageDeclarationCS-0: 'package' PathNameDeclCS::ownedPathName=UnreservedPathNameCS (PackageDeclarationCS::ownedInvariants+=invConstraintCS)[V0:*] (PackageDeclarationCS::ownedContexts+=ContextDeclCS)[V1:*] 'endpackage' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 5	/* "package" : [value] | [half-new-line, value, push] */,
			(0 << 16) | 0	/* ownedPathName=UnreservedPathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedInvariants+=invConstraintCS* : [value] | [value] */,
			(0 << 16) | 0	/* ownedContexts+=ContextDeclCS* : [value] | [value] */,
			(0 << 16) | 21	/* "endpackage" : [value] | [pop, soft-new-line, value, soft-new-line, half-new-line] */
		);
		grammarRuleValues[65] = createParserRuleValue(65, "ParameterCS", -1,
			createSerializationRules(
				27	/* ParameterCS-0: (NamedElementCS::name=UnrestrictedName ':')[V0:?] TypedElementCS::ownedType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[66] = createParserRuleValue(66, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[67] = createParserRuleValue(67, "PatternExpCS", -1,
			createSerializationRules(
				76	/* PatternExpCS-0: (PatternExpCS::patternVariableName=UnrestrictedName)[V0:?] ':' PatternExpCS::ownedPatternType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* patternVariableName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPatternType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[68] = createParserRuleValue(68, "PrefixedLetExpCS", 27 /* LetExpCS|PrefixedLetExpCS */,
			createSerializationRules(
				55	/* LetExpCS-0: 'let' LetExpCS::ownedVariables+=LetVariableCS (',' LetExpCS::ownedVariables+=LetVariableCS)[V0:*] 'in' LetExpCS::ownedInExpression=ExpCS */,
				77	/* PrefixedLetExpCS-1: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedLetExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 12	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedLetExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LetExpCS : [value] | [value] */
		);
		grammarRuleValues[69] = createParserRuleValue(69, "PrefixedPrimaryExpCS", 54 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				42	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				44	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				52	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				53	/* InvalidLiteralExpCS-0: 'invalid' */,
				54	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				57	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				61	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				73	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				74	/* NullLiteralExpCS-0: 'null' */,
				75	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				78	/* PrefixedPrimaryExpCS-15: NamedElementCS::name=UnaryOperatorName OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */,
				81	/* SelfExpCS-0: 'self' */,
				86	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				87	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				97	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				106	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 12	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedPrimaryExpCS : [value] | [value] */,
			(0 << 16) | 0	/* PrimaryExpCS : [value] | [value] */
		);
		grammarRuleValues[70] = createParserRuleValue(70, "PrimaryExpCS", 53 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				42	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				44	/* CollectionLiteralExpCS-0: CollectionLiteralExpCS::ownedType=CollectionTypeCS '{' (CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS (',' CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS)[V1:*])[V0:?] '}' */,
				52	/* IfExpCS-0: 'if' IfExpCS::ownedCondition=ExpCS|PatternExpCS 'then' IfExpCS::ownedThenExpression=ExpCS (IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS)[V0:*] 'else' IfExpCS::ownedElseExpression=ExpCS 'endif' */,
				53	/* InvalidLiteralExpCS-0: 'invalid' */,
				54	/* LambdaLiteralExpCS-0: 'Lambda' '{' LambdaLiteralExpCS::ownedExpressionCS=ExpCS '}' */,
				57	/* MapLiteralExpCS-0: MapLiteralExpCS::ownedType=MapTypeCS '{' (MapLiteralExpCS::ownedParts+=MapLiteralPartCS (',' MapLiteralExpCS::ownedParts+=MapLiteralPartCS)[V1:*])[V0:?] '}' */,
				61	/* NameExpCS-0: AbstractNameExpCS::ownedPathName=PathNameCS (AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS)[V0:*] (AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS)[V1:?] (AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS)[V2:?] (AbstractNameExpCS::isPre?='@' 'pre')[V3:?] */,
				73	/* NestedExpCS-0: '(' NestedExpCS::ownedExpression=ExpCS ')' */,
				74	/* NullLiteralExpCS-0: 'null' */,
				75	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				81	/* SelfExpCS-0: 'self' */,
				86	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				87	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */,
				97	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */,
				106	/* UnlimitedNaturalLiteralExpCS-0: '*' */
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
		grammarRuleValues[71] = createParserRuleValue(71, "PrimitiveLiteralExpCS", 52 /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				42	/* BooleanLiteralExpCS-0: BooleanLiteralExpCS::symbol='false|true' */,
				53	/* InvalidLiteralExpCS-0: 'invalid' */,
				74	/* NullLiteralExpCS-0: 'null' */,
				75	/* NumberLiteralExpCS-0: NumberLiteralExpCS::symbol=NUMBER_LITERAL */,
				86	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */,
				106	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* NumberLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* StringLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* BooleanLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* UnlimitedNaturalLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* InvalidLiteralExpCS : [value] | [value] */,
			(0 << 16) | 0	/* NullLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[72] = createParserRuleValue(72, "PrimitiveTypeCS", -1,
			createSerializationRules(
				79	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */
			),
			(0 << 16) | 12	/* name=PrimitiveTypeIdentifier : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[73] = createDataTypeRuleValue(73, "PrimitiveTypeIdentifier", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[74] = createParserRuleValue(74, "PropertyContextDeclCS", -1,
			createSerializationRules(
				28	/* PropertyContextDeclCS-0: 'context' PathNameDeclCS::ownedPathName=UnreservedPathNameCS ':' FeatureContextDeclCS::ownedType=TypeExpCS (PropertyContextDeclCS::ownedDefaultExpressions+=deriveSpecificationCS|initSpecificationCS)[V0:*] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 9	/* "context" : [value] | [soft-new-line, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=UnreservedPathNameCS : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=(deriveSpecificationCS|initSpecificationCS)* : [value] | [value] */
		);
		grammarRuleValues[75] = createParserRuleValue(75, "RoundBracketedClauseCS", -1,
			createSerializationRules(
				80	/* RoundBracketedClauseCS-0: '(' (RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS (RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[V1:*])[V0:?] ')' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {RoundBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=NavigatingArgCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)* : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[76] = new TerminalRuleValue(76, "SIMPLE_ID");
		grammarRuleValues[77] = new TerminalRuleValue(77, "SINGLE_QUOTED_STRING");
		grammarRuleValues[78] = new TerminalRuleValue(78, "SL_COMMENT");
		grammarRuleValues[79] = createParserRuleValue(79, "SelfExpCS", -1,
			createSerializationRules(
				81	/* SelfExpCS-0: 'self' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SelfExpCS} : [value] | [value] */,
			(0 << 16) | 12	/* "self" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[80] = createParserRuleValue(80, "ShadowPartCS", -1,
			createSerializationRules(
				82	/* ShadowPartCS-0: ShadowPartCS::referredProperty=UnrestrictedName '=' ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */,
				83	/* ShadowPartCS-1: ShadowPartCS::ownedInitExpression=StringLiteralExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 12	/* referredProperty=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 0	/* ownedInitExpression=StringLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[81] = createParserRuleValue(81, "SimplePathNameCS", -1,
			createSerializationRules(
				84	/* SimplePathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS */
			),
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */
		);
		grammarRuleValues[82] = createParserRuleValue(82, "SpecificationCS", -1,
			createSerializationRules(
				29	/* SpecificationCS-0: ExpSpecificationCS::ownedExpression=ExpCS */,
				30	/* SpecificationCS-1: SpecificationCS::exprString=UNQUOTED_STRING */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 12	/* exprString=UNQUOTED_STRING : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[83] = createParserRuleValue(83, "SquareBracketedClauseCS", -1,
			createSerializationRules(
				85	/* SquareBracketedClauseCS-0: '[' SquareBracketedClauseCS::ownedTerms+=ExpCS (',' SquareBracketedClauseCS::ownedTerms+=ExpCS)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[84] = createDataTypeRuleValue(84, "StringLiteral", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[85] = createParserRuleValue(85, "StringLiteralExpCS", -1,
			createSerializationRules(
				86	/* StringLiteralExpCS-0: (StringLiteralExpCS::segments+=StringLiteral)[V0:+] */
			),
			(0 << 16) | 2	/* segments+=StringLiteral+ : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[86] = createParserRuleValue(86, "TemplateBindingCS", -1,
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
		grammarRuleValues[87] = createParserRuleValue(87, "TemplateParameterSubstitutionCS", -1,
			createSerializationRules(
				12	/* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[88] = createParserRuleValue(88, "TemplateSignatureCS", -1,
			createSerializationRules(
				31	/* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */,
				32	/* TemplateSignatureCS-1: '<' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] '>' */
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
			(0 << 16) | 12	/* "<" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 12	/* ">" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[89] = createParserRuleValue(89, "TupleLiteralExpCS", -1,
			createSerializationRules(
				87	/* TupleLiteralExpCS-0: 'Tuple' '{' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS (',' TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS)[V0:*] '}' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* "Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 7	/* "," : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedParts+=TupleLiteralPartCS : [value] | [value] */,
			(0 << 16) | 14	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[90] = createParserRuleValue(90, "TupleLiteralPartCS", -1,
			createSerializationRules(
				88	/* TupleLiteralPartCS-0: NamedElementCS::name=UnrestrictedName (':' VariableCS::ownedType=TypeExpCS)[V0:?] '=' VariableCS::ownedInitExpression=ExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 12	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[91] = createParserRuleValue(91, "TuplePartCS", -1,
			createSerializationRules(
				89	/* TuplePartCS-0: NamedElementCS::name=UnrestrictedName ':' TypedElementCS::ownedType=TypeExpCS */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 12	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[92] = createParserRuleValue(92, "TupleTypeCS", -1,
			createSerializationRules(
				90	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 12	/* name="Tuple" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 8	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParts+=TuplePartCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[93] = createParserRuleValue(93, "TypeExpCS", -1,
			createSerializationRules(
				91	/* TypeExpCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				92	/* TypeExpCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				93	/* TypeExpCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				94	/* TypeExpCS-3: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				95	/* TypeExpCS-4: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				96	/* TypeExpCS-5: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[94] = createParserRuleValue(94, "TypeExpWithoutMultiplicityCS", 44 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				47	/* CollectionPatternCS-0: CollectionPatternCS::ownedType=CollectionTypeCS '{' (CollectionPatternCS::ownedParts+=PatternExpCS (',' CollectionPatternCS::ownedParts+=PatternExpCS)[V1:*] '++' CollectionPatternCS::restVariableName=Identifier)[V0:?] '}' */,
				48	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				59	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				79	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				90	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				102	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeNameExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionPatternCS : [value] | [value] */
		);
		grammarRuleValues[95] = createParserRuleValue(95, "TypeLiteralCS", 42 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */,
			createSerializationRules(
				48	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				59	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				79	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				90	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */
		);
		grammarRuleValues[96] = createParserRuleValue(96, "TypeLiteralExpCS", -1,
			createSerializationRules(
				97	/* TypeLiteralExpCS-0: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			),
			(0 << 16) | 2	/* ownedType=TypeLiteralWithMultiplicityCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[97] = createParserRuleValue(97, "TypeLiteralWithMultiplicityCS", -1,
			createSerializationRules(
				98	/* TypeLiteralWithMultiplicityCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V0:?] */,
				99	/* TypeLiteralWithMultiplicityCS-1: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V2:?] */,
				100	/* TypeLiteralWithMultiplicityCS-2: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V1:?] */,
				101	/* TypeLiteralWithMultiplicityCS-3: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] (TypedRefCS::ownedMultiplicity=MultiplicityCS)[V3:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[98] = createParserRuleValue(98, "TypeNameExpCS", -1,
			createSerializationRules(
				102	/* TypeNameExpCS-0: TypeNameExpCS::ownedPathName=PathNameCS (TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' TypeNameExpCS::ownedPatternGuard=ExpCS '}')[V1:?])[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedCurlyBracketedClause=CurlyBracketedClauseCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 19	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedPatternGuard=ExpCS : [value] | [value] */,
			(0 << 16) | 14	/* "}" : [value] | [pop, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[99] = createParserRuleValue(99, "TypeParameterCS", -1,
			createSerializationRules(
				13	/* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 12	/* "&&" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[100] = createParserRuleValue(100, "TypeRefCS", 60 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				48	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				59	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				79	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				90	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				14	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */,
				16	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[101] = createParserRuleValue(101, "TypedRefCS", 48 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				48	/* CollectionTypeCS-0: CollectionTypeCS::name=CollectionTypeIdentifier ('(' CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS (CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS)[V1:?] ')')[V0:?] */,
				59	/* MapTypeCS-0: MapTypeCS::name='Map' ('(' MapTypeCS::ownedKeyType=TypeExpCS ',' MapTypeCS::ownedValueType=TypeExpCS ')')[V0:?] */,
				79	/* PrimitiveTypeCS-0: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				90	/* TupleTypeCS-0: TupleTypeCS::name='Tuple' ('(' (TupleTypeCS::ownedParts+=TuplePartCS (',' TupleTypeCS::ownedParts+=TuplePartCS)[V2:*])[V1:?] ')')[V0:?] */,
				14	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[102] = createParserRuleValue(102, "TypedTypeRefCS", -1,
			createSerializationRules(
				14	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[103] = new TerminalRuleValue(103, "UNQUOTED_STRING");
		grammarRuleValues[104] = createDataTypeRuleValue(104, "UPPER", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[105] = createDataTypeRuleValue(105, "URI", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[106] = createParserRuleValue(106, "URIFirstPathElementCS", -1,
			createSerializationRules(
				103	/* URIFirstPathElementCS-0: PathElementCS::referredElement=URI */,
				104	/* URIFirstPathElementCS-1: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 12	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PathElementWithURICS} : [value] | [value] */,
			(0 << 16) | 12	/* referredElement=URI : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[107] = createParserRuleValue(107, "URIPathNameCS", -1,
			createSerializationRules(
				105	/* URIPathNameCS-0: PathNameCS::ownedPathElements+=URIFirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=URIFirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[108] = createDataTypeRuleValue(108, "UnaryOperatorName", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[109] = createParserRuleValue(109, "UnlimitedNaturalLiteralExpCS", -1,
			createSerializationRules(
				106	/* UnlimitedNaturalLiteralExpCS-0: '*' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {UnlimitedNaturalLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 12	/* "*" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[110] = createDataTypeRuleValue(110, "UnreservedName", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[111] = createParserRuleValue(111, "UnreservedPathNameCS", -1,
			createSerializationRules(
				15	/* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[112] = createDataTypeRuleValue(112, "UnrestrictedName", 12 /* [soft-space, value, soft-space] */);
		grammarRuleValues[113] = new TerminalRuleValue(113, "WS");
		grammarRuleValues[114] = createParserRuleValue(114, "WildcardTypeRefCS", -1,
			createSerializationRules(
				16	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WildcardTypeRefCS} : [value] | [value] */,
			(0 << 16) | 12	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[115] = createParserRuleValue(115, "bodySpecificationCS", -1,
			createSerializationRules(
				33	/* bodySpecificationCS-0: 'body' ':' ExpSpecificationCS::ownedExpression=ExpCS */,
				34	/* bodySpecificationCS-1: 'body' ':' SpecificationCS::exprString=UNQUOTED_STRING */
			),
			(0 << 16) | 24	/* Group : [value] | [soft-new-line, push, value, pop, pop] */,
			(0 << 16) | 12	/* "body" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 20	/* ":" : [value] | [soft-space, value, soft-new-line, push] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 12	/* exprString=UNQUOTED_STRING : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[116] = createParserRuleValue(116, "deriveSpecificationCS", -1,
			createSerializationRules(
				35	/* deriveSpecificationCS-0: 'derive' ':' ExpSpecificationCS::ownedExpression=ExpCS */,
				36	/* deriveSpecificationCS-1: 'derive' ':' SpecificationCS::exprString=UNQUOTED_STRING */
			),
			(0 << 16) | 24	/* Group : [value] | [soft-new-line, push, value, pop, pop] */,
			(0 << 16) | 12	/* "derive" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 20	/* ":" : [value] | [soft-space, value, soft-new-line, push] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 12	/* exprString=UNQUOTED_STRING : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[117] = createParserRuleValue(117, "initSpecificationCS", -1,
			createSerializationRules(
				37	/* initSpecificationCS-0: 'init' ':' ExpSpecificationCS::ownedExpression=ExpCS */,
				38	/* initSpecificationCS-1: 'init' ':' SpecificationCS::exprString=UNQUOTED_STRING */
			),
			(0 << 16) | 24	/* Group : [value] | [soft-new-line, push, value, pop, pop] */,
			(0 << 16) | 12	/* "init" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 20	/* ":" : [value] | [soft-space, value, soft-new-line, push] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 12	/* exprString=UNQUOTED_STRING : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[118] = createParserRuleValue(118, "invConstraintCS", -1,
			createSerializationRules(
				39	/* invConstraintCS-0: 'inv' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 24	/* Group : [value] | [soft-new-line, push, value, pop, pop] */,
			(0 << 16) | 12	/* "inv" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 20	/* ":" : [value] | [soft-space, value, soft-new-line, push] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[119] = createParserRuleValue(119, "postConstraintCS", -1,
			createSerializationRules(
				40	/* postConstraintCS-0: 'post' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 24	/* Group : [value] | [soft-new-line, push, value, pop, pop] */,
			(0 << 16) | 12	/* "post" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 20	/* ":" : [value] | [soft-space, value, soft-new-line, push] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */
		);
		grammarRuleValues[120] = createParserRuleValue(120, "preConstraintCS", -1,
			createSerializationRules(
				41	/* preConstraintCS-0: 'pre' (NamedElementCS::name=UnrestrictedName ('(' ConstraintCS::ownedMessageSpecification=SpecificationCS ')')[V1:?])[V0:?] ':' ConstraintCS::ownedSpecification=SpecificationCS */
			),
			(0 << 16) | 24	/* Group : [value] | [soft-new-line, push, value, pop, pop] */,
			(0 << 16) | 12	/* "pre" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 12	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 20	/* ":" : [value] | [soft-space, value, soft-new-line, push] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS : [value] | [value] */
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
		// 3: ContextDeclCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x1000L);
		// 4: CurlyBracketedClauseCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x2000L);
		// 5: DefCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x8000L);
		// 6: DefParameterCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x20000L);
		// 7: DefCS|DefOperationCS|DefPropertyCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x58000L);
		// 8: ElseIfThenExpCS
		grammarRuleVectors[8] = new GrammarRuleVector(0x200000L);
		// 9: ExpCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x10000000L);
		// 10: FirstPathElementCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x20000000L);
		// 11: ImportCS
		grammarRuleVectors[11] = new GrammarRuleVector(0x400000000L);
		// 12: LetVariableCS
		grammarRuleVectors[12] = new GrammarRuleVector(0x20000000000L);
		// 13: MapLiteralPartCS
		grammarRuleVectors[13] = new GrammarRuleVector(0x200000000000L);
		// 14: MapTypeCS
		grammarRuleVectors[14] = new GrammarRuleVector(0x400000000000L);
		// 15: MultiplicityCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x2000000000000L);
		// 16: NavigatingArgExpCS
		grammarRuleVectors[16] = new GrammarRuleVector(0x40000000000000L);
		// 17: NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[17] = new GrammarRuleVector(0x380000000000000L);
		// 18: NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[18] = new GrammarRuleVector(0x3a0000000000000L);
		// 19: NextPathElementCS
		grammarRuleVectors[19] = new GrammarRuleVector(0x1000000000000000L);
		// 20: FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[20] = new GrammarRuleVector(0x1000000020000000L);
		// 21: PackageDeclarationCS
		grammarRuleVectors[21] = new GrammarRuleVector(0x0L,0x1L);
		// 22: ParameterCS
		grammarRuleVectors[22] = new GrammarRuleVector(0x0L,0x2L);
		// 23: PathNameCS
		grammarRuleVectors[23] = new GrammarRuleVector(0x0L,0x4L);
		// 24: PatternExpCS
		grammarRuleVectors[24] = new GrammarRuleVector(0x0L,0x8L);
		// 25: ExpCS|PatternExpCS
		grammarRuleVectors[25] = new GrammarRuleVector(0x10000000L,0x8L);
		// 26: PrefixedLetExpCS
		grammarRuleVectors[26] = new GrammarRuleVector(0x0L,0x10L);
		// 27: LetExpCS|PrefixedLetExpCS
		grammarRuleVectors[27] = new GrammarRuleVector(0x10000000000L,0x10L);
		// 28: PrefixedPrimaryExpCS
		grammarRuleVectors[28] = new GrammarRuleVector(0x0L,0x20L);
		// 29: ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS
		grammarRuleVectors[29] = new GrammarRuleVector(0x8000000000001008L,0x400L);
		// 30: RoundBracketedClauseCS
		grammarRuleVectors[30] = new GrammarRuleVector(0x0L,0x800L);
		// 31: ShadowPartCS
		grammarRuleVectors[31] = new GrammarRuleVector(0x0L,0x10000L);
		// 32: SpecificationCS
		grammarRuleVectors[32] = new GrammarRuleVector(0x0L,0x40000L);
		// 33: SquareBracketedClauseCS
		grammarRuleVectors[33] = new GrammarRuleVector(0x0L,0x80000L);
		// 34: StringLiteralExpCS
		grammarRuleVectors[34] = new GrammarRuleVector(0x0L,0x200000L);
		// 35: TemplateBindingCS
		grammarRuleVectors[35] = new GrammarRuleVector(0x0L,0x400000L);
		// 36: TemplateParameterSubstitutionCS
		grammarRuleVectors[36] = new GrammarRuleVector(0x0L,0x800000L);
		// 37: TemplateSignatureCS
		grammarRuleVectors[37] = new GrammarRuleVector(0x0L,0x1000000L);
		// 38: TupleLiteralPartCS
		grammarRuleVectors[38] = new GrammarRuleVector(0x0L,0x4000000L);
		// 39: TuplePartCS
		grammarRuleVectors[39] = new GrammarRuleVector(0x0L,0x8000000L);
		// 40: TypeExpCS
		grammarRuleVectors[40] = new GrammarRuleVector(0x0L,0x20000000L);
		// 41: TypeExpWithoutMultiplicityCS
		grammarRuleVectors[41] = new GrammarRuleVector(0x0L,0x40000000L);
		// 42: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
		grammarRuleVectors[42] = new GrammarRuleVector(0x400000000100L,0x90000100L);
		// 43: TypeLiteralWithMultiplicityCS
		grammarRuleVectors[43] = new GrammarRuleVector(0x0L,0x200000000L);
		// 44: CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[44] = new GrammarRuleVector(0x400000000180L,0x4d0000100L);
		// 45: TypeParameterCS
		grammarRuleVectors[45] = new GrammarRuleVector(0x0L,0x800000000L);
		// 46: TypeRefCS
		grammarRuleVectors[46] = new GrammarRuleVector(0x0L,0x1000000000L);
		// 47: TypedRefCS
		grammarRuleVectors[47] = new GrammarRuleVector(0x0L,0x2000000000L);
		// 48: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[48] = new GrammarRuleVector(0x400000000100L,0x6090000100L);
		// 49: NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[49] = new GrammarRuleVector(0x1000000000000000L,0x40000000000L);
		// 50: FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[50] = new GrammarRuleVector(0x1000000020000000L,0x40000000000L);
		// 51: URIPathNameCS
		grammarRuleVectors[51] = new GrammarRuleVector(0x0L,0x80000000000L);
		// 52: BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[52] = new GrammarRuleVector(0x6000001000000004L,0x200000200080L);
		// 53: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[53] = new GrammarRuleVector(0x6810109200000024L,0x2001022080c0L);
		// 54: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[54] = new GrammarRuleVector(0x6810109200000024L,0x2001022080e0L);
		// 55: BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[55] = new GrammarRuleVector(0x6810119200000024L,0x2001022080f0L);
		// 56: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[56] = new GrammarRuleVector(0x6810119210000024L,0x2001022080f0L);
		// 57: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[57] = new GrammarRuleVector(0x6850119210000024L,0x2001022080f0L);
		// 58: BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[58] = new GrammarRuleVector(0x6810119210000024L,0x2001022080f8L);
		// 59: UnreservedPathNameCS
		grammarRuleVectors[59] = new GrammarRuleVector(0x0L,0x800000000000L);
		// 60: CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[60] = new GrammarRuleVector(0x400000000100L,0x4007090000100L);
		// 61: bodySpecificationCS
		grammarRuleVectors[61] = new GrammarRuleVector(0x0L,0x8000000000000L);
		// 62: deriveSpecificationCS|initSpecificationCS
		grammarRuleVectors[62] = new GrammarRuleVector(0x0L,0x30000000000000L);
		// 63: invConstraintCS
		grammarRuleVectors[63] = new GrammarRuleVector(0x0L,0x40000000000000L);
		// 64: postConstraintCS
		grammarRuleVectors[64] = new GrammarRuleVector(0x0L,0x80000000000000L);
		// 65: preConstraintCS
		grammarRuleVectors[65] = new GrammarRuleVector(0x0L,0x100000000000000L);
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
		// 10: assert (|FeatureContextDeclCS::ownedType| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(137);
		// 11: assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(138);
		// 12: assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(139);
		// 13: assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(140);
		// 14: assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(141);
		// 15: assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(142);
		// 16: assert (|ImportCS::ownedPathName| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(143);
		// 17: assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(144);
		// 18: assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(145);
		// 19: assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(146);
		// 20: assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(150);
		// 21: assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(151);
		// 22: assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(152);
		// 23: assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(153);
		// 24: assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(154);
		// 25: assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(155);
		// 26: assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(156);
		// 27: assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(157);
		// 28: assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(158);
		// 29: assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(159);
		// 30: assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(160);
		// 31: assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(161);
		// 32: assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(162);
		// 33: assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(163);
		// 34: assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(164);
		// 35: assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(165);
		// 36: assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(166);
		// 37: assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(167);
		// 38: assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(170);
		// 39: assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(171);
		// 40: assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(172);
		// 41: assert (|PathNameDeclCS::ownedPathName| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(173);
		// 42: assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(174);
		// 43: assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(175);
		// 44: assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(176);
		// 45: assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(177);
		// 46: assert (|SpecificationCS::exprString| - 1) == 0
		serializationMatchSteps[46] = createMatchStep_Assert(178);
		// 47: assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[47] = createMatchStep_Assert(181);
		// 48: assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[48] = createMatchStep_Assert(184);
		// 49: assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[49] = createMatchStep_Assert(187);
		// 50: assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[50] = createMatchStep_Assert(188);
		// 51: assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[51] = createMatchStep_Assert(191);
		// 52: assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[52] = createMatchStep_Assert(192);
		// 53: assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[53] = createMatchStep_Assert(193);
		// 54: assert |CollectionLiteralPartCS::ownedLastExpression| == 0
		serializationMatchSteps[54] = createMatchStep_Assert(15);
		// 55: assert |CollectionPatternCS::ownedPatternGuard| == 0
		serializationMatchSteps[55] = createMatchStep_Assert(17);
		// 56: assert |ConstraintCS::stereotype| == 0
		serializationMatchSteps[56] = createMatchStep_Assert(27);
		// 57: assert |CurlyBracketedClauseCS::value| == 0
		serializationMatchSteps[57] = createMatchStep_Assert(30);
		// 58: assert |ExpSpecificationCS::ownedExpression| == 0
		serializationMatchSteps[58] = createMatchStep_Assert(34);
		// 59: assert |IfExpCS::isImplicit| == 0
		serializationMatchSteps[59] = createMatchStep_Assert(36);
		// 60: assert |LetExpCS::isImplicit| == 0
		serializationMatchSteps[60] = createMatchStep_Assert(47);
		// 61: assert |ModelElementCS::ownedAnnotations| == 0
		serializationMatchSteps[61] = createMatchStep_Assert(58);
		// 62: assert |MultiplicityCS::isNullFree| == 0
		serializationMatchSteps[62] = createMatchStep_Assert(62);
		// 63: assert |NamedElementCS::name| == 0
		serializationMatchSteps[63] = createMatchStep_Assert(64);
		// 64: assert |NavigatingArgCS::ownedCoIterator| == 0
		serializationMatchSteps[64] = createMatchStep_Assert(65);
		// 65: assert |NavigatingArgCS::ownedInitExpression| == 0
		serializationMatchSteps[65] = createMatchStep_Assert(66);
		// 66: assert |NavigatingArgCS::ownedNameExpression| == 0
		serializationMatchSteps[66] = createMatchStep_Assert(67);
		// 67: assert |NavigatingArgCS::ownedType| == 0
		serializationMatchSteps[67] = createMatchStep_Assert(68);
		// 68: assert |NavigatingArgCS::prefix| == 0
		serializationMatchSteps[68] = createMatchStep_Assert(72);
		// 69: assert |RootCS::ownedImports| == 0
		serializationMatchSteps[69] = createMatchStep_Assert(89);
		// 70: assert |SelfExpCS::name| == 0
		serializationMatchSteps[70] = createMatchStep_Assert(90);
		// 71: assert |ShadowPartCS::referredProperty| == 0
		serializationMatchSteps[71] = createMatchStep_Assert(92);
		// 72: assert |SpecificationCS::exprString| == 0
		serializationMatchSteps[72] = createMatchStep_Assert(93);
		// 73: assert |TypeLiteralExpCS::ownedPathName| == 0
		serializationMatchSteps[73] = createMatchStep_Assert(104);
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
		serializationMatchSteps[81] = createMatchStep_Assign(0, 147);
		// 82: assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[82] = createMatchStep_Assign(0, 149);
		// 83: assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[83] = createMatchStep_Assign(0, 172);
		// 84: assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[84] = createMatchStep_Assign(0, 179);
		// 85: assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[85] = createMatchStep_Assign(0, 180);
		// 86: assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[86] = createMatchStep_Assign(0, 182);
		// 87: assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[87] = createMatchStep_Assign(0, 183);
		// 88: assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[88] = createMatchStep_Assign(0, 186);
		// 89: assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[89] = createMatchStep_Assign(0, 190);
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
		// 105: assign V0 = |PropertyContextDeclCS::ownedDefaultExpressions|
		serializationMatchSteps[105] = createMatchStep_Assign(0, 88);
		// 106: assign V0 = |RootCS::ownedImports|
		serializationMatchSteps[106] = createMatchStep_Assign(0, 89);
		// 107: assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[107] = createMatchStep_Assign(0, 95);
		// 108: assign V0 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[108] = createMatchStep_Assign(0, 100);
		// 109: assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[109] = createMatchStep_Assign(0, 106);
		// 110: assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[110] = createMatchStep_Assign(0, 112);
		// 111: assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[111] = createMatchStep_Assign(0, 114);
		// 112: assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[112] = createMatchStep_Assign(0, 117);
		// 113: assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[113] = createMatchStep_Assign(0, 118);
		// 114: assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[114] = createMatchStep_Assign(1, 122);
		// 115: assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[115] = createMatchStep_Assign(1, 126);
		// 116: assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[116] = createMatchStep_Assign(1, 131);
		// 117: assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[117] = createMatchStep_Assign(1, 148);
		// 118: assign V1 = (|OperationContextDeclCS::ownedParameters| > 0)
		serializationMatchSteps[118] = createMatchStep_Assign(1, 169);
		// 119: assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[119] = createMatchStep_Assign(1, 186);
		// 120: assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[120] = createMatchStep_Assign(1, 189);
		// 121: assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[121] = createMatchStep_Assign(1, 6);
		// 122: assign V1 = |ClassifierContextDeclCS::selfName|
		serializationMatchSteps[122] = createMatchStep_Assign(1, 11);
		// 123: assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[123] = createMatchStep_Assign(1, 21);
		// 124: assign V1 = |CompleteOCLDocumentCS::ownedPackages|
		serializationMatchSteps[124] = createMatchStep_Assign(1, 24);
		// 125: assign V1 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[125] = createMatchStep_Assign(1, 25);
		// 126: assign V1 = |ImportCS::isAll.'::*'|
		serializationMatchSteps[126] = createMatchStep_Assign(1, 43);
		// 127: assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[127] = createMatchStep_Assign(1, 65);
		// 128: assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[128] = createMatchStep_Assign(1, 66);
		// 129: assign V1 = |PackageDeclarationCS::ownedContexts|
		serializationMatchSteps[129] = createMatchStep_Assign(1, 80);
		// 130: assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[130] = createMatchStep_Assign(1, 96);
		// 131: assign V1 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[131] = createMatchStep_Assign(1, 100);
		// 132: assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[132] = createMatchStep_Assign(1, 108);
		// 133: assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[133] = createMatchStep_Assign(1, 112);
		// 134: assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[134] = createMatchStep_Assign(1, 117);
		// 135: assign V2 = (|DefOperationCS::ownedParameters| > 0)
		serializationMatchSteps[135] = createMatchStep_Assign(2, 135);
		// 136: assign V2 = (|OperationContextDeclCS::ownedParameters| - 1)
		serializationMatchSteps[136] = createMatchStep_Assign(2, 168);
		// 137: assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[137] = createMatchStep_Assign(2, 185);
		// 138: assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[138] = createMatchStep_Assign(2, 4);
		// 139: assign V2 = |ClassifierContextDeclCS::ownedInvariants|
		serializationMatchSteps[139] = createMatchStep_Assign(2, 10);
		// 140: assign V2 = |CompleteOCLDocumentCS::ownedContexts|
		serializationMatchSteps[140] = createMatchStep_Assign(2, 23);
		// 141: assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[141] = createMatchStep_Assign(2, 112);
		// 142: assign V3 = (|DefOperationCS::ownedParameters| - 1)
		serializationMatchSteps[142] = createMatchStep_Assign(3, 134);
		// 143: assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[143] = createMatchStep_Assign(3, 3);
		// 144: assign V3 = |ClassifierContextDeclCS::ownedDefinitions|
		serializationMatchSteps[144] = createMatchStep_Assign(3, 9);
		// 145: assign V3 = |FeatureContextDeclCS::ownedType|
		serializationMatchSteps[145] = createMatchStep_Assign(3, 35);
		// 146: assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[146] = createMatchStep_Assign(3, 112);
		// 147: assign V4 = |OperationContextDeclCS::ownedPreconditions|
		serializationMatchSteps[147] = createMatchStep_Assign(4, 78);
		// 148: assign V4 = |TypedElementCS::ownedType|
		serializationMatchSteps[148] = createMatchStep_Assign(4, 110);
		// 149: assign V5 = |OperationContextDeclCS::ownedPostconditions|
		serializationMatchSteps[149] = createMatchStep_Assign(5, 77);
		// 150: assign V6 = |OperationContextDeclCS::ownedBodies|
		serializationMatchSteps[150] = createMatchStep_Assign(6, 75);
		// 151: check-rule basecs::ConstraintCS.ownedMessageSpecification : 82
		serializationMatchSteps[151] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 32/*SpecificationCS*/);
		// 152: check-rule basecs::ConstraintCS.ownedSpecification : 82
		serializationMatchSteps[152] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 32/*SpecificationCS*/);
		// 153: check-rule basecs::ImportCS.ownedPathName : 107
		serializationMatchSteps[153] = createMatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 51/*URIPathNameCS*/);
		// 154: check-rule basecs::PathNameCS.ownedPathElements : 29
		serializationMatchSteps[154] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 10/*FirstPathElementCS*/);
		// 155: check-rule basecs::PathNameCS.ownedPathElements : 60
		serializationMatchSteps[155] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 19/*NextPathElementCS*/);
		// 156: check-rule basecs::RootCS.ownedImports : 34
		serializationMatchSteps[156] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 11/*ImportCS*/);
		// 157: check-rule basecs::TemplateBindingCS.ownedMultiplicity : 49
		serializationMatchSteps[157] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 15/*MultiplicityCS*/);
		// 158: check-rule basecs::TemplateBindingCS.ownedSubstitutions : 87
		serializationMatchSteps[158] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 36/*TemplateParameterSubstitutionCS*/);
		// 159: check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 8|46|72|92|95|100|101|102|114
		serializationMatchSteps[159] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 60/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS*/);
		// 160: check-rule basecs::TemplateSignatureCS.ownedParameters : 99
		serializationMatchSteps[160] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 45/*TypeParameterCS*/);
		// 161: check-rule basecs::TemplateableElementCS.ownedSignature : 88
		serializationMatchSteps[161] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 37/*TemplateSignatureCS*/);
		// 162: check-rule basecs::TupleTypeCS.ownedParts : 91
		serializationMatchSteps[162] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 39/*TuplePartCS*/);
		// 163: check-rule basecs::TypeParameterCS.ownedExtends : 8|46|72|92|95|101|102
		serializationMatchSteps[163] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 48/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS*/);
		// 164: check-rule basecs::TypedElementCS.ownedType : 93
		serializationMatchSteps[164] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 40/*TypeExpCS*/);
		// 165: check-rule basecs::TypedRefCS.ownedMultiplicity : 49
		serializationMatchSteps[165] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/*MultiplicityCS*/);
		// 166: check-rule basecs::TypedTypeRefCS.ownedBinding : 86
		serializationMatchSteps[166] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 35/*TemplateBindingCS*/);
		// 167: check-rule basecs::TypedTypeRefCS.ownedPathName : 66
		serializationMatchSteps[167] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 23/*PathNameCS*/);
		// 168: check-rule basecs::WildcardTypeRefCS.ownedExtends : 8|46|72|92|95|101|102
		serializationMatchSteps[168] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 48/*CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS*/);
		// 169: check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 15|16|18
		serializationMatchSteps[169] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 7/*DefCS|DefOperationCS|DefPropertyCS*/);
		// 170: check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 118
		serializationMatchSteps[170] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 63/*invConstraintCS*/);
		// 171: check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : 3|12|63|74
		serializationMatchSteps[171] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, 29/*ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS*/);
		// 172: check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : 64
		serializationMatchSteps[172] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, 21/*PackageDeclarationCS*/);
		// 173: check-rule completeoclcs::DefCS.ownedSpecification : 82
		serializationMatchSteps[173] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 32/*SpecificationCS*/);
		// 174: check-rule completeoclcs::DefOperationCS.ownedParameters : 17
		serializationMatchSteps[174] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, 6/*DefParameterCS*/);
		// 175: check-rule completeoclcs::FeatureContextDeclCS.ownedType : 93
		serializationMatchSteps[175] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 40/*TypeExpCS*/);
		// 176: check-rule completeoclcs::OperationContextDeclCS.ownedBodies : 115
		serializationMatchSteps[176] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, 61/*bodySpecificationCS*/);
		// 177: check-rule completeoclcs::OperationContextDeclCS.ownedParameters : 65
		serializationMatchSteps[177] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, 22/*ParameterCS*/);
		// 178: check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : 119
		serializationMatchSteps[178] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, 64/*postConstraintCS*/);
		// 179: check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : 120
		serializationMatchSteps[179] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, 65/*preConstraintCS*/);
		// 180: check-rule completeoclcs::PackageDeclarationCS.ownedContexts : 3|12|63|74
		serializationMatchSteps[180] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, 29/*ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS*/);
		// 181: check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : 118
		serializationMatchSteps[181] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, 63/*invConstraintCS*/);
		// 182: check-rule completeoclcs::PathNameDeclCS.ownedPathName : 111
		serializationMatchSteps[182] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 59/*UnreservedPathNameCS*/);
		// 183: check-rule completeoclcs::PropertyContextDeclCS.ownedDefaultExpressions : 116|117
		serializationMatchSteps[183] = createMatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, 62/*deriveSpecificationCS|initSpecificationCS*/);
		// 184: check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13
		serializationMatchSteps[184] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 4/*CurlyBracketedClauseCS*/);
		// 185: check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 66
		serializationMatchSteps[185] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 23/*PathNameCS*/);
		// 186: check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 75
		serializationMatchSteps[186] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 30/*RoundBracketedClauseCS*/);
		// 187: check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 83
		serializationMatchSteps[187] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 33/*SquareBracketedClauseCS*/);
		// 188: check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6
		serializationMatchSteps[188] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 1/*CollectionLiteralPartCS*/);
		// 189: check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8
		serializationMatchSteps[189] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// 190: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[190] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 191: check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 67
		serializationMatchSteps[191] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 24/*PatternExpCS*/);
		// 192: check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[192] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 193: check-rule essentialoclcs::CollectionPatternCS.ownedParts : 67
		serializationMatchSteps[193] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 24/*PatternExpCS*/);
		// 194: check-rule essentialoclcs::CollectionPatternCS.ownedType : 8
		serializationMatchSteps[194] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/*CollectionTypeCS*/);
		// 195: check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 49
		serializationMatchSteps[195] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 15/*MultiplicityCS*/);
		// 196: check-rule essentialoclcs::CollectionTypeCS.ownedType : 7|8|46|72|92|94|95|98
		serializationMatchSteps[196] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 44/*CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS*/);
		// 197: check-rule essentialoclcs::ContextCS.ownedExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[197] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 198: check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 80
		serializationMatchSteps[198] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 31/*ShadowPartCS*/);
		// 199: check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[199] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 200: check-rule essentialoclcs::IfExpCS.ownedCondition : 2|5|28|33|36|39|40|44|52|59|61|62|67|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[200] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 58/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 201: check-rule essentialoclcs::IfExpCS.ownedElseExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[201] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 202: check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 21
		serializationMatchSteps[202] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 8/*ElseIfThenExpCS*/);
		// 203: check-rule essentialoclcs::IfExpCS.ownedThenExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[203] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 204: check-rule essentialoclcs::IfThenExpCS.ownedCondition : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[204] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 205: check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[205] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 206: check-rule essentialoclcs::InfixExpCS.ownedLeft : 2|5|33|36|39|44|52|59|61|62|69|70|71|79|85|89|96|109
		serializationMatchSteps[206] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 54/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 207: check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[207] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 208: check-rule essentialoclcs::LetExpCS.ownedInExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[208] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 209: check-rule essentialoclcs::LetExpCS.ownedVariables : 41
		serializationMatchSteps[209] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 12/*LetVariableCS*/);
		// 210: check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 75
		serializationMatchSteps[210] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 30/*RoundBracketedClauseCS*/);
		// 211: check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 45
		serializationMatchSteps[211] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 13/*MapLiteralPartCS*/);
		// 212: check-rule essentialoclcs::MapLiteralExpCS.ownedType : 46
		serializationMatchSteps[212] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 14/*MapTypeCS*/);
		// 213: check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[213] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 214: check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[214] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 215: check-rule essentialoclcs::MapTypeCS.ownedKeyType : 93
		serializationMatchSteps[215] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 40/*TypeExpCS*/);
		// 216: check-rule essentialoclcs::MapTypeCS.ownedValueType : 93
		serializationMatchSteps[216] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 40/*TypeExpCS*/);
		// 217: check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4
		serializationMatchSteps[217] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/*CoIteratorVariableCS*/);
		// 218: check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[218] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 219: check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 2|5|28|33|36|39|40|44|52|54|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[219] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 57/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 220: check-rule essentialoclcs::NavigatingArgCS.ownedType : 93
		serializationMatchSteps[220] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 40/*TypeExpCS*/);
		// 221: check-rule essentialoclcs::NestedExpCS.ownedExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[221] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 222: check-rule essentialoclcs::OperatorExpCS.ownedRight : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[222] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 223: check-rule essentialoclcs::OperatorExpCS.ownedRight : 2|5|33|36|39|44|52|59|61|62|69|70|71|79|85|89|96|109
		serializationMatchSteps[223] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 54/*BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 224: check-rule essentialoclcs::OperatorExpCS.ownedRight : 40|68
		serializationMatchSteps[224] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 27/*LetExpCS|PrefixedLetExpCS*/);
		// 225: check-rule essentialoclcs::PatternExpCS.ownedPatternType : 93
		serializationMatchSteps[225] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 40/*TypeExpCS*/);
		// 226: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 2|5|28|33|36|39|40|44|52|59|61|62|67|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[226] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 58/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 227: check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 85
		serializationMatchSteps[227] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 34/*StringLiteralExpCS*/);
		// 228: check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[228] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 229: check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 90
		serializationMatchSteps[229] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 38/*TupleLiteralPartCS*/);
		// 230: check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 97
		serializationMatchSteps[230] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 43/*TypeLiteralWithMultiplicityCS*/);
		// 231: check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13
		serializationMatchSteps[231] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 4/*CurlyBracketedClauseCS*/);
		// 232: check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 66
		serializationMatchSteps[232] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 23/*PathNameCS*/);
		// 233: check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[233] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 234: check-rule essentialoclcs::VariableCS.ownedInitExpression : 2|5|28|33|36|39|40|44|52|59|61|62|68|69|70|71|79|85|89|96|109
		serializationMatchSteps[234] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 56/*BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS*/);
		// 235: check-rule essentialoclcs::VariableCS.ownedType : 93
		serializationMatchSteps[235] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 40/*TypeExpCS*/);
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
		// 88: |PropertyContextDeclCS::ownedDefaultExpressions|
		serializationMatchTerms[88] = createSerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS);
		// 89: |RootCS::ownedImports|
		serializationMatchTerms[89] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		// 90: |SelfExpCS::name|
		serializationMatchTerms[90] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SELF_EXP_CS__NAME);
		// 91: |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[91] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// 92: |ShadowPartCS::referredProperty|
		serializationMatchTerms[92] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// 93: |SpecificationCS::exprString|
		serializationMatchTerms[93] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		// 94: |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[94] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// 95: |StringLiteralExpCS::segments|
		serializationMatchTerms[95] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// 96: |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[96] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// 97: |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[97] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// 98: |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[98] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// 99: |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[99] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// 100: |TemplateableElementCS::ownedSignature|
		serializationMatchTerms[100] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		// 101: |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[101] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// 102: |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[102] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 6 /* 'Tuple' */);
		// 103: |TupleTypeCS::ownedParts|
		serializationMatchTerms[103] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// 104: |TypeLiteralExpCS::ownedPathName|
		serializationMatchTerms[104] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_PATH_NAME);
		// 105: |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[105] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// 106: |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[106] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// 107: |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[107] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// 108: |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[108] = createSerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// 109: |TypeParameterCS::ownedExtends|
		serializationMatchTerms[109] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
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
		// 137: (|FeatureContextDeclCS::ownedType| - 1)
		serializationMatchTerms[137] = createSerializationMatchTermSubtract(35, 1);
		// 138: (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[138] = createSerializationMatchTermSubtract(37, 1);
		// 139: (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[139] = createSerializationMatchTermSubtract(38, 1);
		// 140: (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[140] = createSerializationMatchTermSubtract(40, 1);
		// 141: (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[141] = createSerializationMatchTermSubtract(41, 1);
		// 142: (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[142] = createSerializationMatchTermSubtract(42, 1);
		// 143: (|ImportCS::ownedPathName| - 1)
		serializationMatchTerms[143] = createSerializationMatchTermSubtract(44, 1);
		// 144: (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[144] = createSerializationMatchTermSubtract(45, 1);
		// 145: (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[145] = createSerializationMatchTermSubtract(46, 1);
		// 146: (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[146] = createSerializationMatchTermSubtract(48, 1);
		// 147: (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[147] = createSerializationMatchTermSubtract(49, 1);
		// 148: (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[148] = createSerializationMatchTermSubtract(51, 1);
		// 149: (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[149] = createSerializationMatchTermGreaterThan(51, 0);
		// 150: (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[150] = createSerializationMatchTermSubtract(52, 1);
		// 151: (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[151] = createSerializationMatchTermSubtract(53, 1);
		// 152: (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[152] = createSerializationMatchTermSubtract(54, 1);
		// 153: (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[153] = createSerializationMatchTermSubtract(55, 1);
		// 154: (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[154] = createSerializationMatchTermSubtract(56, 2);
		// 155: (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[155] = createSerializationMatchTermSubtract(59, 1);
		// 156: (|MultiplicityCS::isNullFree.'|1'| - 1)
		serializationMatchTerms[156] = createSerializationMatchTermSubtract(61, 1);
		// 157: (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[157] = createSerializationMatchTermSubtract(63, 1);
		// 158: (|NamedElementCS::name| - 1)
		serializationMatchTerms[158] = createSerializationMatchTermSubtract(64, 1);
		// 159: (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[159] = createSerializationMatchTermSubtract(65, 1);
		// 160: (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[160] = createSerializationMatchTermSubtract(66, 1);
		// 161: (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[161] = createSerializationMatchTermSubtract(67, 1);
		// 162: (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[162] = createSerializationMatchTermSubtract(68, 1);
		// 163: (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[163] = createSerializationMatchTermSubtract(69, 1);
		// 164: (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[164] = createSerializationMatchTermSubtract(70, 1);
		// 165: (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[165] = createSerializationMatchTermSubtract(71, 1);
		// 166: (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[166] = createSerializationMatchTermSubtract(73, 1);
		// 167: (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[167] = createSerializationMatchTermSubtract(74, 1);
		// 168: (|OperationContextDeclCS::ownedParameters| - 1)
		serializationMatchTerms[168] = createSerializationMatchTermSubtract(76, 1);
		// 169: (|OperationContextDeclCS::ownedParameters| > 0)
		serializationMatchTerms[169] = createSerializationMatchTermGreaterThan(76, 0);
		// 170: (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[170] = createSerializationMatchTermSubtract(79, 1);
		// 171: (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[171] = createSerializationMatchTermSubtract(82, 1);
		// 172: (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[172] = createSerializationMatchTermSubtract(83, 1);
		// 173: (|PathNameDeclCS::ownedPathName| - 1)
		serializationMatchTerms[173] = createSerializationMatchTermSubtract(84, 1);
		// 174: (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[174] = createSerializationMatchTermSubtract(85, 1);
		// 175: (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[175] = createSerializationMatchTermSubtract(87, 1);
		// 176: (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[176] = createSerializationMatchTermSubtract(91, 1);
		// 177: (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[177] = createSerializationMatchTermSubtract(92, 1);
		// 178: (|SpecificationCS::exprString| - 1)
		serializationMatchTerms[178] = createSerializationMatchTermSubtract(93, 1);
		// 179: (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[179] = createSerializationMatchTermSubtract(94, 1);
		// 180: (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[180] = createSerializationMatchTermSubtract(97, 1);
		// 181: (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[181] = createSerializationMatchTermSubtract(98, 1);
		// 182: (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[182] = createSerializationMatchTermSubtract(99, 1);
		// 183: (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[183] = createSerializationMatchTermSubtract(101, 1);
		// 184: (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[184] = createSerializationMatchTermSubtract(102, 1);
		// 185: (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[185] = createSerializationMatchTermSubtract(103, 1);
		// 186: (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[186] = createSerializationMatchTermGreaterThan(103, 0);
		// 187: (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[187] = createSerializationMatchTermSubtract(105, 1);
		// 188: (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[188] = createSerializationMatchTermSubtract(107, 1);
		// 189: (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[189] = createSerializationMatchTermSubtract(109, 1);
		// 190: (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[190] = createSerializationMatchTermGreaterThan(109, 0);
		// 191: (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[191] = createSerializationMatchTermSubtract(110, 1);
		// 192: (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[192] = createSerializationMatchTermSubtract(115, 1);
		// 193: (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[193] = createSerializationMatchTermSubtract(116, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS-0(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] = createSerializationRule("FirstPathElementCS-0", 29,
			createSerializationMatchSteps(
				39		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				202		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::MultiplicityBoundsCS-0(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { ".." upperBound=UPPER }[?] }
		serializationRules[1] = createSerializationRule("MultiplicityBoundsCS-0", 48,
			createSerializationMatchSteps(
				62		/* assert |MultiplicityCS::isNullFree| == 0 */,
				98		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				25		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				6		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				159		/* V00*2-steps || value */,
				113		/* '..' || no-space value no-space */,
				104		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-0(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" }
		serializationRules[2] = createSerializationRule("MultiplicityCS-0", 49,
			createSerializationMatchSteps(
				62		/* assert |MultiplicityCS::isNullFree| == 0 */,
				98		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				25		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				125		/* '[' || no-space value no-space */,
				6		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				159		/* V00*2-steps || value */,
				113		/* '..' || no-space value no-space */,
				104		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				154		/* '|?' || no-space value no-space */,
				126		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-1(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree?="|1" "]" }
		serializationRules[3] = createSerializationRule("MultiplicityCS-1", 49,
			createSerializationMatchSteps(
				26		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				98		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				25		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				125		/* '[' || no-space value no-space */,
				6		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				159		/* V00*2-steps || value */,
				113		/* '..' || no-space value no-space */,
				104		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				3		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				126		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(10/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-2(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" }
		serializationRules[4] = createSerializationRule("MultiplicityCS-2", 49,
			createSerializationMatchSteps(
				62		/* assert |MultiplicityCS::isNullFree| == 0 */,
				98		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				25		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				125		/* '[' || no-space value no-space */,
				6		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				159		/* V00*2-steps || value */,
				113		/* '..' || no-space value no-space */,
				104		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				126		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-3(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "|?" "]" }
		serializationRules[5] = createSerializationRule("MultiplicityCS-3", 49,
			createSerializationMatchSteps(
				62		/* assert |MultiplicityCS::isNullFree| == 0 */,
				27		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				125		/* '[' || no-space value no-space */,
				101		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				154		/* '|?' || no-space value no-space */,
				126		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-4(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} isNullFree?="|1" "]" }
		serializationRules[6] = createSerializationRule("MultiplicityCS-4", 49,
			createSerializationMatchSteps(
				26		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				27		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				125		/* '[' || no-space value no-space */,
				101		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				3		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				126		/* ']' || no-space value */
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
		serializationRules[7] = createSerializationRule("MultiplicityCS-5", 49,
			createSerializationMatchSteps(
				62		/* assert |MultiplicityCS::isNullFree| == 0 */,
				27		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				125		/* '[' || no-space value no-space */,
				101		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				126		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityStringCS-0(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		serializationRules[8] = createSerializationRule("MultiplicityStringCS-0", 50,
			createSerializationMatchSteps(
				62		/* assert |MultiplicityCS::isNullFree| == 0 */,
				27		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				101		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::NextPathElementCS-0(basecs::PathElementCS): referredElement=UnreservedName
		serializationRules[9] = createSerializationRule("NextPathElementCS-0", 60,
			createSerializationMatchSteps(
				39		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				201		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::PathNameCS-0(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[10] = createSerializationRule("PathNameCS-0", 66,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				195		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */,
				160		/* V00*2-steps || value */,
				116		/* '::' || no-space value no-space */,
				196		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 20/* FirstPathElementCS,NextPathElementCS */,
					(29/*FirstPathElementCS*/ << 4) | 0 /*[1]*/,
					(60/*NextPathElementCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TemplateBindingCS-0(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] = createSerializationRule("TemplateBindingCS-0", 86,
			createSerializationMatchSteps(
				157		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : MultiplicityCS */,
				158		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : TemplateParameterSubstitutionCS */,
				130		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				85		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			),
			createSerializationSteps(
				77		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				160		/* V00*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				77		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				166		/* V01*1-steps || value */,
				48		/* TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 36/* TemplateParameterSubstitutionCS */,
					(87/*TemplateParameterSubstitutionCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TemplateParameterSubstitutionCS-0(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] = createSerializationRule("TemplateParameterSubstitutionCS-0", 87,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				159		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
				47		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			),
			createSerializationSteps(
				11		/* TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 46/* TypeRefCS */,
					(100/*TypeRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::TypeParameterCS-0(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[13] = createSerializationRule("TypeParameterCS-0", 99,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				163		/* check-rule basecs::TypeParameterCS.ownedExtends : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */,
				89		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				120		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				135		/* 'extends' || soft-space value soft-space */,
				32		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */,
				169		/* V01*2-steps || value */,
				105		/* '&&' || soft-space value soft-space */,
				32		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 47/* TypedRefCS */,
					(101/*TypedRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TypedTypeRefCS-0(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] }
		serializationRules[14] = createSerializationRule("TypedTypeRefCS-0", 102,
			createSerializationMatchSteps(
				76		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				166		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				167		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				111		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				52		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				66		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */,
				161		/* V00*3-steps || value */,
				107		/* '(' || no-space value no-space */,
				12		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				108		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 35/* TemplateBindingCS */,
					(86/*TemplateBindingCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 23/* PathNameCS */,
					(66/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::UnreservedPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=NextPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[15] = createSerializationRule("UnreservedPathNameCS-0", 111,
			createSerializationMatchSteps(
				155		/* check-rule basecs::PathNameCS.ownedPathElements : NextPathElementCS */,
				83		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			),
			createSerializationSteps(
				196		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */,
				160		/* V00*2-steps || value */,
				116		/* '::' || no-space value no-space */,
				196		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 19/* NextPathElementCS */,
					(60/*NextPathElementCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::WildcardTypeRefCS-0(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[16] = createSerializationRule("WildcardTypeRefCS-0", 114,
			createSerializationMatchSteps(
				78		/* assert |WildcardTypeRefCS::ownedSuper| == 0 */,
				168		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				113		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			),
			createSerializationSteps(
				121		/* '?' || soft-space value soft-space */,
				159		/* V00*2-steps || value */,
				135		/* 'extends' || soft-space value soft-space */,
				33		/* WildcardTypeRefCS::ownedExtends=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 47/* TypedRefCS */,
					(101/*TypedRefCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::ClassifierContextDeclCS-0(completeoclcs::ClassifierContextDeclCS): { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=UnreservedPathNameCS { ownedInvariants+=invConstraintCS[+] ownedDefinitions+=DefCS[*] } }
		serializationRules[17] = createSerializationRule("ClassifierContextDeclCS-0", 3,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				169		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : DefCS|DefOperationCS|DefPropertyCS */,
				170		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : invConstraintCS */,
				182		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : UnreservedPathNameCS */,
				161		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				144		/* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
				139		/* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
				41		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				122		/* assign V1 = |ClassifierContextDeclCS::selfName| */,
				108		/* assign V0 = |TemplateableElementCS::ownedSignature| */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				128		/* 'context' || soft-new-line value soft-space */,
				156		/* V00*1-steps || value */,
				73		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				166		/* V01*1-steps || value */,
				100		/* ClassifierContextDeclCS::selfName=UnrestrictedName || soft-space value soft-space */,
				64		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				174		/* V02*1-steps || value */,
				41		/* ClassifierContextDeclCS::ownedInvariants+=invConstraintCS || value */,
				178		/* V03*1-steps || value */,
				23		/* ClassifierContextDeclCS::ownedDefinitions+=DefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 5/* DefCS */,
					(15/*DefCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 63/* invConstraintCS */,
					(118/*invConstraintCS*/ << 4) | 3 /*[+]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 59/* UnreservedPathNameCS */,
					(111/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 37/* TemplateSignatureCS */,
					(88/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::ClassifierContextDeclCS-1(completeoclcs::ClassifierContextDeclCS): { "context" ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=UnreservedPathNameCS { ownedInvariants+=invConstraintCS[*] ownedDefinitions+=DefCS[+] } }
		serializationRules[18] = createSerializationRule("ClassifierContextDeclCS-1", 3,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				169		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : DefCS|DefOperationCS|DefPropertyCS */,
				170		/* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : invConstraintCS */,
				182		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : UnreservedPathNameCS */,
				161		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				144		/* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
				139		/* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
				41		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				122		/* assign V1 = |ClassifierContextDeclCS::selfName| */,
				108		/* assign V0 = |TemplateableElementCS::ownedSignature| */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				128		/* 'context' || soft-new-line value soft-space */,
				156		/* V00*1-steps || value */,
				73		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				166		/* V01*1-steps || value */,
				100		/* ClassifierContextDeclCS::selfName=UnrestrictedName || soft-space value soft-space */,
				64		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				173		/* V02*1-steps || value */,
				41		/* ClassifierContextDeclCS::ownedInvariants+=invConstraintCS || value */,
				179		/* V03*1-steps || value */,
				23		/* ClassifierContextDeclCS::ownedDefinitions+=DefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 5/* DefCS */,
					(15/*DefCS*/ << 4) | 3 /*[+]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 63/* invConstraintCS */,
					(118/*invConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 59/* UnreservedPathNameCS */,
					(111/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 37/* TemplateSignatureCS */,
					(88/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::CompleteOCLDocumentCS-0(completeoclcs::CompleteOCLDocumentCS): { ownedImports+=ImportCS[*] { ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] } }
		serializationRules[19] = createSerializationRule("CompleteOCLDocumentCS-0", 10,
			createSerializationMatchSteps(
				63		/* assert |NamedElementCS::name| == 0 */,
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				171		/* check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				156		/* check-rule basecs::RootCS.ownedImports : ImportCS */,
				172		/* check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : PackageDeclarationCS */,
				140		/* assign V2 = |CompleteOCLDocumentCS::ownedContexts| */,
				124		/* assign V1 = |CompleteOCLDocumentCS::ownedPackages| */,
				106		/* assign V0 = |RootCS::ownedImports| */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				157		/* V00*1-steps || value */,
				35		/* RootCS::ownedImports+=ImportCS || half-new-line value half-new-line */,
				167		/* V01*1-steps || value */,
				52		/* CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS || half-new-line soft-new-line value half-new-line */,
				173		/* V02*1-steps || value */,
				18		/* CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, 3/* ContextDeclCS */,
					(12/*ContextDeclCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 11/* ImportCS */,
					(34/*ImportCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, 21/* PackageDeclarationCS */,
					(64/*PackageDeclarationCS*/ << 4) | 2 /*[*]*/
				)
			});
		// CompleteOCL::DefOperationCS-0(completeoclcs::DefOperationCS): { isStatic?="static"[?] "def" ":" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=DefParameterCS { "," ownedParameters+=DefParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] "=" ownedSpecification=SpecificationCS }
		serializationRules[20] = createSerializationRule("DefOperationCS-0", 16,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				174		/* check-rule completeoclcs::DefOperationCS.ownedParameters : DefParameterCS */,
				161		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				173		/* check-rule completeoclcs::DefCS.ownedSpecification : SpecificationCS */,
				164		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				8		/* assert (|DefCS::ownedSpecification| - 1) == 0 */,
				148		/* assign V4 = |TypedElementCS::ownedType| */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */,
				131		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
				94		/* assign V0 = |DefCS::isStatic.'static'| */,
				135		/* assign V2 = (|DefOperationCS::ownedParameters| > 0) */,
				142		/* assign V3 = (|DefOperationCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				156		/* V00*1-steps || value */,
				5		/* DefCS::isStatic?='static' || soft-space value soft-space */,
				129		/* 'def' || soft-space value soft-space */,
				114		/* ':' || soft-space value soft-space */,
				166		/* V01*1-steps || value */,
				73		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				107		/* '(' || no-space value no-space */,
				176		/* V02*4-steps || value */,
				53		/* DefOperationCS::ownedParameters+=DefParameterCS || value */,
				181		/* V03*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				53		/* DefOperationCS::ownedParameters+=DefParameterCS || value */,
				108		/* ')' || no-space value */,
				114		/* ':' || soft-space value soft-space */,
				182		/* V04*1-steps || value */,
				88		/* TypedElementCS::ownedType=TypeExpCS || value */,
				119		/* '=' || soft-space value soft-new-line push */,
				75		/* DefCS::ownedSpecification=SpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, false,
					(8/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, 6/* DefParameterCS */,
					(17/*DefParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 37/* TemplateSignatureCS */,
					(88/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 32/* SpecificationCS */,
					(82/*SpecificationCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::DefParameterCS-0(basecs::ParameterCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[21] = createSerializationRule("DefParameterCS-0", 17,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				164		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				51		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				114		/* ':' || soft-space value soft-space */,
				88		/* TypedElementCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::DefPropertyCS-0(completeoclcs::DefPropertyCS): { isStatic?="static"[?] "def" ":" name=UnrestrictedName ":" ownedType=TypeExpCS "=" ownedSpecification=SpecificationCS }
		serializationRules[22] = createSerializationRule("DefPropertyCS-0", 18,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				173		/* check-rule completeoclcs::DefCS.ownedSpecification : SpecificationCS */,
				164		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				8		/* assert (|DefCS::ownedSpecification| - 1) == 0 */,
				51		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */,
				94		/* assign V0 = |DefCS::isStatic.'static'| */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				156		/* V00*1-steps || value */,
				5		/* DefCS::isStatic?='static' || soft-space value soft-space */,
				129		/* 'def' || soft-space value soft-space */,
				114		/* ':' || soft-space value soft-space */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				114		/* ':' || soft-space value soft-space */,
				88		/* TypedElementCS::ownedType=TypeExpCS || value */,
				119		/* '=' || soft-space value soft-new-line push */,
				75		/* DefCS::ownedSpecification=SpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, false,
					(8/*'static'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 32/* SpecificationCS */,
					(82/*SpecificationCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::ImportCS-0(basecs::ImportCS): { {"import"|"include"|"library"} { name=Identifier ":" }[?] ownedPathName=URIPathNameCS isAll?="::*"[?] }
		serializationRules[23] = createSerializationRule("ImportCS-0", 34,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				153		/* check-rule basecs::ImportCS.ownedPathName : URIPathNameCS */,
				126		/* assign V1 = |ImportCS::isAll.'::*'| */,
				16		/* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				99		/* assign V0 = |NamedElementCS::name| */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				137		/* 'import' || value */,
				159		/* V00*2-steps || value */,
				191		/* NamedElementCS::name=Identifier || soft-space value soft-space */,
				114		/* ':' || soft-space value soft-space */,
				63		/* ImportCS::ownedPathName=URIPathNameCS || value */,
				166		/* V01*1-steps || value */,
				2		/* ImportCS::isAll?='::*' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, false,
					(2/*'::*'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 51/* URIPathNameCS */,
					(107/*URIPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::NavigatingArgExpCS-0(completeoclcs::OCLMessageArgCS): "?"
		serializationRules[24] = createSerializationRule("NavigatingArgExpCS-0", 54,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				122		/* '?' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// CompleteOCL::OperationContextDeclCS-0(completeoclcs::OperationContextDeclCS): { "context" ownedSignature=TemplateSignatureCS[?] ownedPathName=UnreservedPathNameCS "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" ":" ownedType=TypeExpCS[?] { ownedPreconditions+=preConstraintCS[*] ownedPostconditions+=postConstraintCS[*] ownedBodies+=bodySpecificationCS[*] } }
		serializationRules[25] = createSerializationRule("OperationContextDeclCS-0", 63,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				176		/* check-rule completeoclcs::OperationContextDeclCS.ownedBodies : bodySpecificationCS */,
				177		/* check-rule completeoclcs::OperationContextDeclCS.ownedParameters : ParameterCS */,
				182		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : UnreservedPathNameCS */,
				178		/* check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : postConstraintCS */,
				179		/* check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : preConstraintCS */,
				161		/* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
				175		/* check-rule completeoclcs::FeatureContextDeclCS.ownedType : TypeExpCS */,
				150		/* assign V6 = |OperationContextDeclCS::ownedBodies| */,
				149		/* assign V5 = |OperationContextDeclCS::ownedPostconditions| */,
				147		/* assign V4 = |OperationContextDeclCS::ownedPreconditions| */,
				145		/* assign V3 = |FeatureContextDeclCS::ownedType| */,
				41		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				108		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
				118		/* assign V1 = (|OperationContextDeclCS::ownedParameters| > 0) */,
				136		/* assign V2 = (|OperationContextDeclCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				128		/* 'context' || soft-new-line value soft-space */,
				156		/* V00*1-steps || value */,
				73		/* TemplateableElementCS::ownedSignature=TemplateSignatureCS || value */,
				64		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				107		/* '(' || no-space value no-space */,
				171		/* V01*4-steps || value */,
				54		/* OperationContextDeclCS::ownedParameters+=ParameterCS || value */,
				175		/* V02*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				54		/* OperationContextDeclCS::ownedParameters+=ParameterCS || value */,
				108		/* ')' || no-space value */,
				114		/* ':' || soft-space value soft-space */,
				177		/* V03*1-steps || value */,
				84		/* FeatureContextDeclCS::ownedType=TypeExpCS || value */,
				183		/* V04*1-steps || value */,
				70		/* OperationContextDeclCS::ownedPreconditions+=preConstraintCS || value */,
				184		/* V05*1-steps || value */,
				69		/* OperationContextDeclCS::ownedPostconditions+=postConstraintCS || value */,
				185		/* V06*1-steps || value */,
				13		/* OperationContextDeclCS::ownedBodies+=bodySpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, 61/* bodySpecificationCS */,
					(115/*bodySpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, 22/* ParameterCS */,
					(65/*ParameterCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 59/* UnreservedPathNameCS */,
					(111/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, 64/* postConstraintCS */,
					(119/*postConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, 65/* preConstraintCS */,
					(120/*preConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 37/* TemplateSignatureCS */,
					(88/*TemplateSignatureCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// CompleteOCL::PackageDeclarationCS-0(completeoclcs::PackageDeclarationCS): { "package" ownedPathName=UnreservedPathNameCS ownedInvariants+=invConstraintCS[*] ownedContexts+=ContextDeclCS[*] "endpackage" }
		serializationRules[26] = createSerializationRule("PackageDeclarationCS-0", 64,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				180		/* check-rule completeoclcs::PackageDeclarationCS.ownedContexts : ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
				181		/* check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : invConstraintCS */,
				182		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : UnreservedPathNameCS */,
				129		/* assign V1 = |PackageDeclarationCS::ownedContexts| */,
				103		/* assign V0 = |PackageDeclarationCS::ownedInvariants| */,
				41		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				145		/* 'package' || half-new-line value push */,
				64		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				157		/* V00*1-steps || value */,
				42		/* PackageDeclarationCS::ownedInvariants+=invConstraintCS || value */,
				167		/* V01*1-steps || value */,
				19		/* PackageDeclarationCS::ownedContexts+=ContextDeclCS || value */,
				134		/* 'endpackage' || pop soft-new-line value soft-new-line half-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, 3/* ContextDeclCS */,
					(12/*ContextDeclCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, 63/* invConstraintCS */,
					(118/*invConstraintCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 59/* UnreservedPathNameCS */,
					(111/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::ParameterCS-0(basecs::ParameterCS): { { name=UnrestrictedName ":" }[?] ownedType=TypeExpCS }
		serializationRules[27] = createSerializationRule("ParameterCS-0", 65,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				164		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				51		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				99		/* assign V0 = |NamedElementCS::name| */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				159		/* V00*2-steps || value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				114		/* ':' || soft-space value soft-space */,
				88		/* TypedElementCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::PropertyContextDeclCS-0(completeoclcs::PropertyContextDeclCS): { "context" ownedPathName=UnreservedPathNameCS ":" ownedType=TypeExpCS ownedDefaultExpressions+=(deriveSpecificationCS|initSpecificationCS)[*] }
		serializationRules[28] = createSerializationRule("PropertyContextDeclCS-0", 74,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				183		/* check-rule completeoclcs::PropertyContextDeclCS.ownedDefaultExpressions : deriveSpecificationCS|initSpecificationCS */,
				182		/* check-rule completeoclcs::PathNameDeclCS.ownedPathName : UnreservedPathNameCS */,
				175		/* check-rule completeoclcs::FeatureContextDeclCS.ownedType : TypeExpCS */,
				105		/* assign V0 = |PropertyContextDeclCS::ownedDefaultExpressions| */,
				10		/* assert (|FeatureContextDeclCS::ownedType| - 1) == 0 */,
				41		/* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				128		/* 'context' || soft-new-line value soft-space */,
				64		/* PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value */,
				114		/* ':' || soft-space value soft-space */,
				84		/* FeatureContextDeclCS::ownedType=TypeExpCS || value */,
				157		/* V00*1-steps || value */,
				22		/* PropertyContextDeclCS::ownedDefaultExpressions+=deriveSpecificationCS|initSpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, 62/* deriveSpecificationCS,initSpecificationCS */,
					(116/*deriveSpecificationCS*/ << 4) | 2 /*[*]*/,
					(117/*initSpecificationCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 59/* UnreservedPathNameCS */,
					(111/*UnreservedPathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::SpecificationCS-0(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS
		serializationRules[29] = createSerializationRule("SpecificationCS-0", 82,
			createSerializationMatchSteps(
				72		/* assert |SpecificationCS::exprString| == 0 */,
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				199		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				9		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				29		/* ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::SpecificationCS-1(essentialoclcs::ExpSpecificationCS): exprString=UNQUOTED_STRING
		serializationRules[30] = createSerializationRule("SpecificationCS-1", 82,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				58		/* assert |ExpSpecificationCS::ownedExpression| == 0 */,
				46		/* assert (|SpecificationCS::exprString| - 1) == 0 */
			),
			createSerializationSteps(
				1		/* SpecificationCS::exprString=UNQUOTED_STRING || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, true, GrammarCardinality.ONE)
			});
		// CompleteOCL::TemplateSignatureCS-0(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[31] = createSerializationRule("TemplateSignatureCS-0", 88,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				160		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				86		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				107		/* '(' || no-space value no-space */,
				55		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				160		/* V00*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				55		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				108		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 45/* TypeParameterCS */,
					(99/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// CompleteOCL::TemplateSignatureCS-1(basecs::TemplateSignatureCS): { "<" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ">" }
		serializationRules[32] = createSerializationRule("TemplateSignatureCS-1", 88,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				160		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				86		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				117		/* '<' || soft-space value soft-space */,
				55		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				160		/* V00*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				55		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				120		/* '>' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 45/* TypeParameterCS */,
					(99/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// CompleteOCL::bodySpecificationCS-0(essentialoclcs::ExpSpecificationCS): { "body" ":" ownedExpression=ExpCS }
		serializationRules[33] = createSerializationRule("bodySpecificationCS-0", 115,
			createSerializationMatchSteps(
				72		/* assert |SpecificationCS::exprString| == 0 */,
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				199		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				9		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				127		/* 'body' || soft-space value soft-space */,
				115		/* ':' || soft-space value soft-new-line push */,
				28		/* ExpSpecificationCS::ownedExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::bodySpecificationCS-1(essentialoclcs::ExpSpecificationCS): { "body" ":" exprString=UNQUOTED_STRING }
		serializationRules[34] = createSerializationRule("bodySpecificationCS-1", 115,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				58		/* assert |ExpSpecificationCS::ownedExpression| == 0 */,
				46		/* assert (|SpecificationCS::exprString| - 1) == 0 */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				127		/* 'body' || soft-space value soft-space */,
				115		/* ':' || soft-space value soft-new-line push */,
				0		/* SpecificationCS::exprString=UNQUOTED_STRING || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, true, GrammarCardinality.ONE)
			});
		// CompleteOCL::deriveSpecificationCS-0(essentialoclcs::ExpSpecificationCS): { "derive" ":" ownedExpression=ExpCS }
		serializationRules[35] = createSerializationRule("deriveSpecificationCS-0", 116,
			createSerializationMatchSteps(
				72		/* assert |SpecificationCS::exprString| == 0 */,
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				199		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				9		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				130		/* 'derive' || soft-space value soft-space */,
				115		/* ':' || soft-space value soft-new-line push */,
				28		/* ExpSpecificationCS::ownedExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::deriveSpecificationCS-1(essentialoclcs::ExpSpecificationCS): { "derive" ":" exprString=UNQUOTED_STRING }
		serializationRules[36] = createSerializationRule("deriveSpecificationCS-1", 116,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				58		/* assert |ExpSpecificationCS::ownedExpression| == 0 */,
				46		/* assert (|SpecificationCS::exprString| - 1) == 0 */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				130		/* 'derive' || soft-space value soft-space */,
				115		/* ':' || soft-space value soft-new-line push */,
				0		/* SpecificationCS::exprString=UNQUOTED_STRING || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, true, GrammarCardinality.ONE)
			});
		// CompleteOCL::initSpecificationCS-0(essentialoclcs::ExpSpecificationCS): { "init" ":" ownedExpression=ExpCS }
		serializationRules[37] = createSerializationRule("initSpecificationCS-0", 117,
			createSerializationMatchSteps(
				72		/* assert |SpecificationCS::exprString| == 0 */,
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				199		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				9		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				140		/* 'init' || soft-space value soft-space */,
				115		/* ':' || soft-space value soft-new-line push */,
				28		/* ExpSpecificationCS::ownedExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::initSpecificationCS-1(essentialoclcs::ExpSpecificationCS): { "init" ":" exprString=UNQUOTED_STRING }
		serializationRules[38] = createSerializationRule("initSpecificationCS-1", 117,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				58		/* assert |ExpSpecificationCS::ownedExpression| == 0 */,
				46		/* assert (|SpecificationCS::exprString| - 1) == 0 */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				140		/* 'init' || soft-space value soft-space */,
				115		/* ':' || soft-space value soft-new-line push */,
				0		/* SpecificationCS::exprString=UNQUOTED_STRING || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, true, GrammarCardinality.ONE)
			});
		// CompleteOCL::invConstraintCS-0(basecs::ConstraintCS): { "inv" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS }
		serializationRules[39] = createSerializationRule("invConstraintCS-0", 118,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				56		/* assert |ConstraintCS::stereotype| == 0 */,
				151		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				152		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				99		/* assign V0 = |NamedElementCS::name| */,
				125		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				141		/* 'inv' || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				170		/* V01*3-steps || value */,
				107		/* '(' || no-space value no-space */,
				47		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				108		/* ')' || no-space value */,
				115		/* ':' || soft-space value soft-new-line push */,
				74		/* ConstraintCS::ownedSpecification=SpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 32/* SpecificationCS */,
					(82/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 32/* SpecificationCS */,
					(82/*SpecificationCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::postConstraintCS-0(basecs::ConstraintCS): { "post" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS }
		serializationRules[40] = createSerializationRule("postConstraintCS-0", 119,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				56		/* assert |ConstraintCS::stereotype| == 0 */,
				151		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				152		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				99		/* assign V0 = |NamedElementCS::name| */,
				125		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				146		/* 'post' || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				170		/* V01*3-steps || value */,
				107		/* '(' || no-space value no-space */,
				47		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				108		/* ')' || no-space value */,
				115		/* ':' || soft-space value soft-new-line push */,
				74		/* ConstraintCS::ownedSpecification=SpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 32/* SpecificationCS */,
					(82/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 32/* SpecificationCS */,
					(82/*SpecificationCS*/ << 4) | 0 /*[1]*/
				)
			});
		// CompleteOCL::preConstraintCS-0(basecs::ConstraintCS): { "pre" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS }
		serializationRules[41] = createSerializationRule("preConstraintCS-0", 120,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				56		/* assert |ConstraintCS::stereotype| == 0 */,
				151		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
				152		/* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
				6		/* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				99		/* assign V0 = |NamedElementCS::name| */,
				125		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			),
			createSerializationSteps(
				187		/* wrapper || soft-new-line push value pop pop */,
				147		/* 'pre' || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				170		/* V01*3-steps || value */,
				107		/* '(' || no-space value no-space */,
				47		/* ConstraintCS::ownedMessageSpecification=SpecificationCS || value */,
				108		/* ')' || no-space value */,
				115		/* ':' || soft-space value soft-new-line push */,
				74		/* ConstraintCS::ownedSpecification=SpecificationCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 32/* SpecificationCS */,
					(82/*SpecificationCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 32/* SpecificationCS */,
					(82/*SpecificationCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::BooleanLiteralExpCS-0(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[42] = createSerializationRule("BooleanLiteralExpCS-0", 2,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				1		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			),
			createSerializationSteps(
				102		/* BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, false,
					(7/*'false|true'*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CoIteratorVariableCS-0(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[43] = createSerializationRule("CoIteratorVariableCS-0", 4,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				77		/* assert |VariableCS::ownedInitExpression| == 0 */,
				235		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				112		/* assign V0 = |VariableCS::ownedType| */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				159		/* V00*2-steps || value */,
				114		/* ':' || soft-space value soft-space */,
				89		/* VariableCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralExpCS-0(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[44] = createSerializationRule("CollectionLiteralExpCS-0", 5,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				188		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS */,
				189		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS */,
				2		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				79		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				114		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				81		/* CollectionLiteralExpCS::ownedType=CollectionTypeCS || value */,
				153		/* '{' || soft-space value push soft-new-line */,
				162		/* V00*4-steps || value */,
				56		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				169		/* V01*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				56		/* CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value */,
				155		/* '}' || pop soft-new-line value soft-new-line */
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
		serializationRules[45] = createSerializationRule("CollectionLiteralPartCS-0", 6,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				190		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				192		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				91		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				25		/* CollectionLiteralPartCS::ownedExpression=ExpCS || value */,
				159		/* V00*2-steps || value */,
				113		/* '..' || no-space value no-space */,
				45		/* CollectionLiteralPartCS::ownedLastExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CollectionLiteralPartCS-1(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
		serializationRules[46] = createSerializationRule("CollectionLiteralPartCS-1", 6,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				54		/* assert |CollectionLiteralPartCS::ownedLastExpression| == 0 */,
				191		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : PatternExpCS */,
				3		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				26		/* CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 24/* PatternExpCS */,
					(67/*PatternExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionPatternCS-0(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" }
		serializationRules[47] = createSerializationRule("CollectionPatternCS-0", 7,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				55		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				193		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				194		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				92		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				115		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				82		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				153		/* '{' || soft-space value push soft-new-line */,
				164		/* V00*6-steps || value */,
				57		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				169		/* V01*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				57		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				110		/* '++' || soft-space value soft-space */,
				98		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				155		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 24/* PatternExpCS */,
					(67/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/* CollectionTypeCS */,
					(8/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::CollectionTypeCS-0(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] }
		serializationRules[48] = createSerializationRule("CollectionTypeCS-0", 8,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				195		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				196		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				93		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				123		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				7		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				107		/* '(' || no-space value no-space */,
				83		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				166		/* V01*1-steps || value */,
				15		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				108		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 41/* TypeExpWithoutMultiplicityCS */,
					(94/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS-0(essentialoclcs::CurlyBracketedClauseCS): { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" }
		serializationRules[49] = createSerializationRule("CurlyBracketedClauseCS-0", 13,
			createSerializationMatchSteps(
				57		/* assert |CurlyBracketedClauseCS::value| == 0 */,
				198		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : ShadowPartCS */,
				80		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				116		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				153		/* '{' || soft-space value push soft-new-line */,
				162		/* V00*4-steps || value */,
				58		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				169		/* V01*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				58		/* CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value */,
				155		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 31/* ShadowPartCS */,
					(80/*ShadowPartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::ElseIfThenExpCS-0(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[50] = createSerializationRule("ElseIfThenExpCS-0", 21,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				204		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				205		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				15		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				14		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				132		/* 'elseif' || soft-new-line pop soft-space value push soft-space */,
				17		/* IfThenExpCS::ownedCondition=ExpCS || value */,
				150		/* 'then' || pop value push soft-space */,
				80		/* IfThenExpCS::ownedThenExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::ExpCS-18(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } }
		serializationRules[51] = createSerializationRule("ExpCS-18", 28,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				206		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				222		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				38		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */,
				17		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				46		/* InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value */,
				188		/* NamedElementCS::name=BinaryOperatorName || soft-space value soft-space */,
				197		/* OperatorExpCS::ownedRight=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 28/* PrefixedPrimaryExpCS */,
					(69/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::IfExpCS-0(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[52] = createSerializationRule("IfExpCS-0", 33,
			createSerializationMatchSteps(
				59		/* assert |IfExpCS::isImplicit| == 0 */,
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				200		/* check-rule essentialoclcs::IfExpCS.ownedCondition : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				201		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				202		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS */,
				203		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				12		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				95		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				13		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				11		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				136		/* 'if' || soft-new-line value push soft-space */,
				16		/* IfExpCS::ownedCondition=ExpCS|PatternExpCS || value */,
				151		/* 'then' || pop soft-space value push soft-space */,
				79		/* IfExpCS::ownedThenExpression=ExpCS || value */,
				157		/* V00*1-steps || value */,
				34		/* IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value */,
				131		/* 'else' || soft-new-line pop value push soft-space */,
				24		/* IfExpCS::ownedElseExpression=ExpCS || value */,
				133		/* 'endif' || soft-new-line pop value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 25/* ExpCS,PatternExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/,
					(67/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 8/* ElseIfThenExpCS */,
					(21/*ElseIfThenExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::InvalidLiteralExpCS-0(essentialoclcs::InvalidLiteralExpCS): "invalid"
		serializationRules[53] = createSerializationRule("InvalidLiteralExpCS-0", 36,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				142		/* 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::LambdaLiteralExpCS-0(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[54] = createSerializationRule("LambdaLiteralExpCS-0", 39,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				207		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				18		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				123		/* 'Lambda' || soft-space value soft-space */,
				153		/* '{' || soft-space value push soft-new-line */,
				31		/* LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value */,
				155		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::LetExpCS-0(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[55] = createSerializationRule("LetExpCS-0", 40,
			createSerializationMatchSteps(
				60		/* assert |LetExpCS::isImplicit| == 0 */,
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				208		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				209		/* check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS */,
				19		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				81		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				143		/* 'let' || soft-space value push */,
				92		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				160		/* V00*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				92		/* LetExpCS::ownedVariables+=LetVariableCS || value */,
				139		/* 'in' || soft-space pop value soft-new-line */,
				36		/* LetExpCS::ownedInExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 12/* LetVariableCS */,
					(41/*LetVariableCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::LetVariableCS-0(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[56] = createSerializationRule("LetVariableCS-0", 41,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				234		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				210		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				235		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				53		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				134		/* assign V1 = |VariableCS::ownedType| */,
				96		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				156		/* V00*1-steps || value */,
				72		/* LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				168		/* V01*2-steps || value */,
				114		/* ':' || soft-space value soft-space */,
				89		/* VariableCS::ownedType=TypeExpCS || value */,
				118		/* '=' || soft-space value soft-space */,
				40		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 30/* RoundBracketedClauseCS */,
					(75/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::MapLiteralExpCS-0(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[57] = createSerializationRule("MapLiteralExpCS-0", 44,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				211		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS */,
				212		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS */,
				20		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				82		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				117		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				85		/* MapLiteralExpCS::ownedType=MapTypeCS || value */,
				153		/* '{' || soft-space value push soft-new-line */,
				162		/* V00*4-steps || value */,
				59		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				169		/* V01*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				59		/* MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value */,
				155		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 13/* MapLiteralPartCS */,
					(45/*MapLiteralPartCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 14/* MapTypeCS */,
					(46/*MapTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapLiteralPartCS-0(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS {"with"|"<-"} ownedValue=ExpCS }
		serializationRules[58] = createSerializationRule("MapLiteralPartCS-0", 45,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				213		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				214		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				22		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				21		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				43		/* MapLiteralPartCS::ownedKey=ExpCS || value */,
				152		/* 'with' || value */,
				90		/* MapLiteralPartCS::ownedValue=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::MapTypeCS-0(essentialoclcs::MapTypeCS): { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] }
		serializationRules[59] = createSerializationRule("MapTypeCS-0", 46,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				215		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				216		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				97		/* assign V0 = |MapTypeCS::ownedValueType| */,
				24		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				23		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				8		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				107		/* '(' || no-space value no-space */,
				44		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				112		/* ',' || no-space value soft-space */,
				91		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				108		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(5/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::Model-0(essentialoclcs::ContextCS): ownedExpression=ExpCS
		serializationRules[60] = createSerializationRule("Model-0", 47,
			createSerializationMatchSteps(
				63		/* assert |NamedElementCS::name| == 0 */,
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				69		/* assert |RootCS::ownedImports| == 0 */,
				197		/* check-rule essentialoclcs::ContextCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				7		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				27		/* ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NameExpCS-0(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre?="@" "pre" }[?] }
		serializationRules[61] = createSerializationRule("NameExpCS-0", 52,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				184		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				185		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS */,
				186		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
				187		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS */,
				143		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				138		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				121		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				90		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				62		/* AbstractNameExpCS::ownedPathName=PathNameCS || value */,
				157		/* V00*1-steps || value */,
				76		/* AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value */,
				166		/* V01*1-steps || value */,
				71		/* AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value */,
				172		/* V02*1-steps || value */,
				20		/* AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				180		/* V03*2-steps || value */,
				4		/* AbstractNameExpCS::isPre?='@' || soft-space value no-space */,
				148		/* 'pre' || no-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, false,
					(4/*'@'*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 4/* CurlyBracketedClauseCS */,
					(13/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 23/* PathNameCS */,
					(66/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 30/* RoundBracketedClauseCS */,
					(75/*RoundBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 33/* SquareBracketedClauseCS */,
					(83/*SquareBracketedClauseCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-0(essentialoclcs::NavigatingArgCS): { ":" ownedType=TypeExpCS }
		serializationRules[62] = createSerializationRule("NavigatingArgCS-0", 53,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				64		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				65		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				66		/* assert |NavigatingArgCS::ownedNameExpression| == 0 */,
				68		/* assert |NavigatingArgCS::prefix| == 0 */,
				220		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				32		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				114		/* ':' || soft-space value soft-space */,
				86		/* NavigatingArgCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-1(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[63] = createSerializationRule("NavigatingArgCS-1", 53,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				68		/* assert |NavigatingArgCS::prefix| == 0 */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				220		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				128		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				100		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				32		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				50		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				114		/* ':' || soft-space value soft-space */,
				86		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				159		/* V00*2-steps || value */,
				152		/* 'with' || value */,
				14		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				168		/* V01*2-steps || value */,
				118		/* '=' || soft-space value soft-space */,
				37		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
	}
	private void initSerializationRules1() {
		// EssentialOCL::NavigatingArgCS-2(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[64] = createSerializationRule("NavigatingArgCS-2", 53,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				68		/* assert |NavigatingArgCS::prefix| == 0 */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				220		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				30		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				127		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				102		/* assign V0 = |NavigatingArgCS::ownedType| */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				50		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				159		/* V00*2-steps || value */,
				114		/* ':' || soft-space value soft-space */,
				86		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				168		/* V01*2-steps || value */,
				152		/* 'with' || value */,
				14		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				138		/* 'in' || soft-space value soft-space */,
				37		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-3(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[65] = createSerializationRule("NavigatingArgCS-3", 53,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				67		/* assert |NavigatingArgCS::ownedType| == 0 */,
				68		/* assert |NavigatingArgCS::prefix| == 0 */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				101		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				29		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				50		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				152		/* 'with' || value */,
				14		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				159		/* V00*2-steps || value */,
				118		/* '=' || soft-space value soft-space */,
				37		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingArgCS-4(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
		serializationRules[66] = createSerializationRule("NavigatingArgCS-4", 53,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				64		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				65		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				67		/* assert |NavigatingArgCS::ownedType| == 0 */,
				68		/* assert |NavigatingArgCS::prefix| == 0 */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			),
			createSerializationSteps(
				51		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingBarArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[67] = createSerializationRule("NavigatingBarArgCS-0", 55,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				64		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				220		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				102		/* assign V0 = |NavigatingArgCS::ownedType| */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				35		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				128		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				94		/* NavigatingArgCS::prefix='|' || soft-space value soft-space */,
				50		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				163		/* V00*5-steps || value */,
				114		/* ':' || soft-space value soft-space */,
				86		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				168		/* V01*2-steps || value */,
				118		/* '=' || soft-space value soft-space */,
				37		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(9/*'|'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-0(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[68] = createSerializationRule("NavigatingCommaArgCS-0", 56,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				220		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				128		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				100		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				32		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				33		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				96		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				50		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				114		/* ':' || soft-space value soft-space */,
				86		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				159		/* V00*2-steps || value */,
				152		/* 'with' || value */,
				14		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				168		/* V01*2-steps || value */,
				118		/* '=' || soft-space value soft-space */,
				37		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-1(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[69] = createSerializationRule("NavigatingCommaArgCS-1", 56,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				220		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				30		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				127		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				102		/* assign V0 = |NavigatingArgCS::ownedType| */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				33		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				96		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				50		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				159		/* V00*2-steps || value */,
				114		/* ':' || soft-space value soft-space */,
				86		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				168		/* V01*2-steps || value */,
				152		/* 'with' || value */,
				14		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				138		/* 'in' || soft-space value soft-space */,
				37		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-2(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { {"with"|"<-"} ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[70] = createSerializationRule("NavigatingCommaArgCS-2", 56,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				67		/* assert |NavigatingArgCS::ownedType| == 0 */,
				217		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				101		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				29		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				33		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				96		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				50		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				152		/* 'with' || value */,
				14		/* NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value */,
				159		/* V00*2-steps || value */,
				118		/* '=' || soft-space value soft-space */,
				37		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 0/* CoIteratorVariableCS */,
					(4/*CoIteratorVariableCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingCommaArgCS-3(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS }
		serializationRules[71] = createSerializationRule("NavigatingCommaArgCS-3", 56,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				64		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				65		/* assert |NavigatingArgCS::ownedInitExpression| == 0 */,
				67		/* assert |NavigatingArgCS::ownedType| == 0 */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				33		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				96		/* NavigatingArgCS::prefix=',' || no-space value soft-space */,
				50		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(1/*','*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NavigatingSemiArgCS-0(essentialoclcs::NavigatingArgCS): { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[72] = createSerializationRule("NavigatingSemiArgCS-0", 57,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				64		/* assert |NavigatingArgCS::ownedCoIterator| == 0 */,
				218		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				219		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				220		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
				102		/* assign V0 = |NavigatingArgCS::ownedType| */,
				31		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				34		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				128		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				95		/* NavigatingArgCS::prefix=';' || no-space value soft-new-line */,
				50		/* NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value */,
				163		/* V00*5-steps || value */,
				114		/* ':' || soft-space value soft-space */,
				86		/* NavigatingArgCS::ownedType=TypeExpCS || value */,
				168		/* V01*2-steps || value */,
				118		/* '=' || soft-space value soft-space */,
				37		/* NavigatingArgCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, false,
					(3/*';'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 16/* NavigatingArgExpCS */,
					(54/*NavigatingArgExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::NestedExpCS-0(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[73] = createSerializationRule("NestedExpCS-0", 59,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				221		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				36		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				106		/* '(' || value no-space */,
				30		/* NestedExpCS::ownedExpression=ExpCS || value */,
				108		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::NullLiteralExpCS-0(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[74] = createSerializationRule("NullLiteralExpCS-0", 61,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				144		/* 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::NumberLiteralExpCS-0(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[75] = createSerializationRule("NumberLiteralExpCS-0", 62,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				37		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			),
			createSerializationSteps(
				103		/* NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::PatternExpCS-0(essentialoclcs::PatternExpCS): { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS }
		serializationRules[76] = createSerializationRule("PatternExpCS-0", 67,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				225		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : TypeExpCS */,
				42		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				104		/* assign V0 = |PatternExpCS::patternVariableName| */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				156		/* V00*1-steps || value */,
				93		/* PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space */,
				114		/* ':' || soft-space value soft-space */,
				68		/* PatternExpCS::ownedPatternType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedLetExpCS-1(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		serializationRules[77] = createSerializationRule("PrefixedLetExpCS-1", 68,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				224		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : LetExpCS|PrefixedLetExpCS */,
				38		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				189		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				198		/* OperatorExpCS::ownedRight=PrefixedLetExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 26/* PrefixedLetExpCS */,
					(68/*PrefixedLetExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS-15(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[78] = createSerializationRule("PrefixedPrimaryExpCS-15", 69,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				223		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				38		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				189		/* NamedElementCS::name=UnaryOperatorName || soft-space value soft-space */,
				199		/* OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 28/* PrefixedPrimaryExpCS */,
					(69/*PrefixedPrimaryExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::PrimitiveTypeCS-0(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		serializationRules[79] = createSerializationRule("PrimitiveTypeCS-0", 72,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				43		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				9		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE)
			});
		// EssentialOCL::RoundBracketedClauseCS-0(essentialoclcs::RoundBracketedClauseCS): { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" }
		serializationRules[80] = createSerializationRule("RoundBracketedClauseCS-0", 75,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				107		/* '(' || no-space value no-space */,
				161		/* V00*3-steps || value */,
				192		/* RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value */,
				167		/* V01*1-steps || value */,
				193		/* RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value */,
				108		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 18/* NavigatingArgCS,NavigatingBarArgCS,NavigatingCommaArgCS,NavigatingSemiArgCS */,
					(53/*NavigatingArgCS*/ << 4) | 1 /*[?]*/,
					(55/*NavigatingBarArgCS*/ << 4) | 2 /*[*]*/,
					(56/*NavigatingCommaArgCS*/ << 4) | 2 /*[*]*/,
					(57/*NavigatingSemiArgCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::SelfExpCS-0(essentialoclcs::SelfExpCS): "self"
		serializationRules[81] = createSerializationRule("SelfExpCS-0", 79,
			createSerializationMatchSteps(
				70		/* assert |SelfExpCS::name| == 0 */,
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				149		/* 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			null);
		// EssentialOCL::ShadowPartCS-0(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[82] = createSerializationRule("ShadowPartCS-0", 80,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				226		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				44		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				45		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				97		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
				118		/* '=' || soft-space value soft-space */,
				38		/* ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 25/* ExpCS,PatternExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/,
					(67/*PatternExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, -1
				)
			});
		// EssentialOCL::ShadowPartCS-1(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
		serializationRules[83] = createSerializationRule("ShadowPartCS-1", 80,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				71		/* assert |ShadowPartCS::referredProperty| == 0 */,
				227		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : StringLiteralExpCS */,
				44		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			),
			createSerializationSteps(
				39		/* ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 34/* StringLiteralExpCS */,
					(85/*StringLiteralExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SimplePathNameCS-0(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[84] = createSerializationRule("SimplePathNameCS-0", 81,
			createSerializationMatchSteps(
				154		/* check-rule basecs::PathNameCS.ownedPathElements : FirstPathElementCS */,
				40		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			),
			createSerializationSteps(
				195		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 10/* FirstPathElementCS */,
					(29/*FirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::SquareBracketedClauseCS-0(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[85] = createSerializationRule("SquareBracketedClauseCS-0", 83,
			createSerializationMatchSteps(
				228		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				84		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			),
			createSerializationSteps(
				125		/* '[' || no-space value no-space */,
				78		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				160		/* V00*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				78		/* SquareBracketedClauseCS::ownedTerms+=ExpCS || value */,
				126		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::StringLiteralExpCS-0(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[86] = createSerializationRule("StringLiteralExpCS-0", 85,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				107		/* assign V0 = |StringLiteralExpCS::segments| */
			),
			createSerializationSteps(
				158		/* V00*1-steps || value */,
				99		/* StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, false, GrammarCardinality.ONE_OR_MORE)
			});
		// EssentialOCL::TupleLiteralExpCS-0(essentialoclcs::TupleLiteralExpCS): { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" }
		serializationRules[87] = createSerializationRule("TupleLiteralExpCS-0", 89,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				229		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS */,
				87		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				124		/* 'Tuple' || soft-space value soft-space */,
				153		/* '{' || soft-space value push soft-new-line */,
				60		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				160		/* V00*2-steps || value */,
				111		/* ',' || no-space value soft-new-line */,
				60		/* TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value */,
				155		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 38/* TupleLiteralPartCS */,
					(90/*TupleLiteralPartCS*/ << 4) | 3 /*[+]*/
				)
			});
		// EssentialOCL::TupleLiteralPartCS-0(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[88] = createSerializationRule("TupleLiteralPartCS-0", 90,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				234		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				235		/* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
				53		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				112		/* assign V0 = |VariableCS::ownedType| */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				159		/* V00*2-steps || value */,
				114		/* ':' || soft-space value soft-space */,
				89		/* VariableCS::ownedType=TypeExpCS || value */,
				118		/* '=' || soft-space value soft-space */,
				40		/* VariableCS::ownedInitExpression=ExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TuplePartCS-0(basecs::TuplePartCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[89] = createSerializationRule("TuplePartCS-0", 91,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				74		/* assert |TypedElementCS::qualifiers| == 0 */,
				164		/* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
				51		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				28		/* assert (|NamedElementCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				186		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				190		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				114		/* ':' || soft-space value soft-space */,
				88		/* TypedElementCS::ownedType=TypeExpCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TupleTypeCS-0(basecs::TupleTypeCS): { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] }
		serializationRules[90] = createSerializationRule("TupleTypeCS-0", 92,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				162		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				48		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				88		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				119		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				137		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				10		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				165		/* V00*7-steps || value */,
				107		/* '(' || no-space value no-space */,
				171		/* V01*4-steps || value */,
				61		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				175		/* V02*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				61		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				108		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(6/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 39/* TuplePartCS */,
					(91/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeExpCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[91] = createSerializationRule("TypeExpCS-0", 93,
			createSerializationMatchSteps(
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				110		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				43		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				9		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				156		/* V00*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[92] = createSerializationRule("TypeExpCS-1", 93,
			createSerializationMatchSteps(
				195		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				196		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				141		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				93		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				123		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				7		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				107		/* '(' || no-space value no-space */,
				83		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				166		/* V01*1-steps || value */,
				15		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				108		/* ')' || no-space value */,
				172		/* V02*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 41/* TypeExpWithoutMultiplicityCS */,
					(94/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[93] = createSerializationRule("TypeExpCS-2", 93,
			createSerializationMatchSteps(
				215		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				216		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				133		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				97		/* assign V0 = |MapTypeCS::ownedValueType| */,
				24		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				23		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				8		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				107		/* '(' || no-space value no-space */,
				44		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				112		/* ',' || no-space value soft-space */,
				91		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				108		/* ')' || no-space value */,
				166		/* V01*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(5/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-3(essentialoclcs::TypeNameExpCS): { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[94] = createSerializationRule("TypeExpCS-3", 93,
			createSerializationMatchSteps(
				231		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				232		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				233		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				141		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				109		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				50		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				132		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				65		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				163		/* V00*5-steps || value */,
				21		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				170		/* V01*3-steps || value */,
				153		/* '{' || soft-space value push soft-new-line */,
				67		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				155		/* '}' || pop soft-new-line value soft-new-line */,
				172		/* V02*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 4/* CurlyBracketedClauseCS */,
					(13/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 23/* PathNameCS */,
					(66/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeExpCS-4(essentialoclcs::CollectionPatternCS): { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[95] = createSerializationRule("TypeExpCS-4", 93,
			createSerializationMatchSteps(
				55		/* assert |CollectionPatternCS::ownedPatternGuard| == 0 */,
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				193		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
				194		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
				141		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				92		/* assign V0 = |CollectionPatternCS::restVariableName| */,
				115		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				4		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				82		/* CollectionPatternCS::ownedType=CollectionTypeCS || value */,
				153		/* '{' || soft-space value push soft-new-line */,
				164		/* V00*6-steps || value */,
				57		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				169		/* V01*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				57		/* CollectionPatternCS::ownedParts+=PatternExpCS || value */,
				110		/* '++' || soft-space value soft-space */,
				98		/* CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space */,
				155		/* '}' || pop soft-new-line value soft-new-line */,
				172		/* V02*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 24/* PatternExpCS */,
					(67/*PatternExpCS*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 2/* CollectionTypeCS */,
					(8/*CollectionTypeCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeExpCS-5(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[96] = createSerializationRule("TypeExpCS-5", 93,
			createSerializationMatchSteps(
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				162		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				146		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				48		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				88		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				119		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				137		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				10		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				165		/* V00*7-steps || value */,
				107		/* '(' || no-space value no-space */,
				171		/* V01*4-steps || value */,
				61		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				175		/* V02*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				61		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				108		/* ')' || no-space value */,
				177		/* V03*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(6/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 39/* TuplePartCS */,
					(91/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeLiteralExpCS-0(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[97] = createSerializationRule("TypeLiteralExpCS-0", 96,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				73		/* assert |TypeLiteralExpCS::ownedPathName| == 0 */,
				230		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS */,
				49		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			),
			createSerializationSteps(
				87		/* TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 43/* TypeLiteralWithMultiplicityCS */,
					(97/*TypeLiteralWithMultiplicityCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-0(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[98] = createSerializationRule("TypeLiteralWithMultiplicityCS-0", 97,
			createSerializationMatchSteps(
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				110		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				43		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			),
			createSerializationSteps(
				9		/* PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space */,
				156		/* V00*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-1(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[99] = createSerializationRule("TypeLiteralWithMultiplicityCS-1", 97,
			createSerializationMatchSteps(
				195		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				196		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				141		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				93		/* assign V0 = |CollectionTypeCS::ownedType| */,
				5		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
				123		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			),
			createSerializationSteps(
				7		/* CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				107		/* '(' || no-space value no-space */,
				83		/* CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value */,
				166		/* V01*1-steps || value */,
				15		/* CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value */,
				108		/* ')' || no-space value */,
				172		/* V02*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 41/* TypeExpWithoutMultiplicityCS */,
					(94/*TypeExpWithoutMultiplicityCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-2(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[100] = createSerializationRule("TypeLiteralWithMultiplicityCS-2", 97,
			createSerializationMatchSteps(
				215		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				216		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
				133		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				97		/* assign V0 = |MapTypeCS::ownedValueType| */,
				24		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				23		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			),
			createSerializationSteps(
				8		/* MapTypeCS::name='Map' || soft-space value soft-space */,
				163		/* V00*5-steps || value */,
				107		/* '(' || no-space value no-space */,
				44		/* MapTypeCS::ownedKeyType=TypeExpCS || value */,
				112		/* ',' || no-space value soft-space */,
				91		/* MapTypeCS::ownedValueType=TypeExpCS || value */,
				108		/* ')' || no-space value */,
				166		/* V01*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, false,
					(5/*'Map'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 40/* TypeExpCS */,
					(93/*TypeExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS-3(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[101] = createSerializationRule("TypeLiteralWithMultiplicityCS-3", 97,
			createSerializationMatchSteps(
				165		/* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
				162		/* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
				146		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				48		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				88		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				119		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				137		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			),
			createSerializationSteps(
				10		/* TupleTypeCS::name='Tuple' || soft-space value soft-space */,
				165		/* V00*7-steps || value */,
				107		/* '(' || no-space value no-space */,
				171		/* V01*4-steps || value */,
				61		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				175		/* V02*2-steps || value */,
				112		/* ',' || no-space value soft-space */,
				61		/* TupleTypeCS::ownedParts+=TuplePartCS || value */,
				108		/* ')' || no-space value */,
				177		/* V03*1-steps || value */,
				49		/* TypedRefCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, false,
					(6/*'Tuple'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 15/* MultiplicityCS */,
					(49/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 39/* TuplePartCS */,
					(91/*TuplePartCS*/ << 4) | 2 /*[*]*/
				)
			});
		// EssentialOCL::TypeNameExpCS-0(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] }
		serializationRules[102] = createSerializationRule("TypeNameExpCS-0", 98,
			createSerializationMatchSteps(
				75		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				231		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
				232		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
				233		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				109		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				50		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				132		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			),
			createSerializationSteps(
				65		/* TypeNameExpCS::ownedPathName=PathNameCS || value */,
				163		/* V00*5-steps || value */,
				21		/* TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value */,
				170		/* V01*3-steps || value */,
				153		/* '{' || soft-space value push soft-new-line */,
				67		/* TypeNameExpCS::ownedPatternGuard=ExpCS || value */,
				155		/* '}' || pop soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 4/* CurlyBracketedClauseCS */,
					(13/*CurlyBracketedClauseCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 23/* PathNameCS */,
					(66/*PathNameCS*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 9/* ExpCS */,
					(28/*ExpCS*/ << 4) | 1 /*[?]*/
				)
			});
		// EssentialOCL::URIFirstPathElementCS-0(basecs::PathElementWithURICS): referredElement=URI
		serializationRules[103] = createSerializationRule("URIFirstPathElementCS-0", 106,
			createSerializationMatchSteps(
				39		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				200		/* PathElementCS::referredElement=URI || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIFirstPathElementCS-1(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[104] = createSerializationRule("URIFirstPathElementCS-1", 106,
			createSerializationMatchSteps(
				39		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				202		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// EssentialOCL::URIPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[105] = createSerializationRule("URIPathNameCS-0", 107,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				194		/* PathNameCS::ownedPathElements+=URIFirstPathElementCS || value */,
				160		/* V00*2-steps || value */,
				116		/* '::' || no-space value no-space */,
				196		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 49/* NextPathElementCS,URIFirstPathElementCS */,
					(60/*NextPathElementCS*/ << 4) | 2 /*[*]*/,
					(106/*URIFirstPathElementCS*/ << 4) | 0 /*[1]*/
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS-0(essentialoclcs::UnlimitedNaturalLiteralExpCS): "*"
		serializationRules[106] = createSerializationRule("UnlimitedNaturalLiteralExpCS-0", 109,
			createSerializationMatchSteps(
				61		/* assert |ModelElementCS::ownedAnnotations| == 0 */
			),
			createSerializationSteps(
				109		/* '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
			SerializationSegment.HALF_NEW_LINE /* half-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.HALF_NEW_LINE /* half-new-line */
		};
		serializationSegments[5] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.HALF_NEW_LINE /* half-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */
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
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[10] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.NO_SPACE /* no-space */
		};
		serializationSegments[11] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */
		};
		serializationSegments[12] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[13] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.HALF_NEW_LINE /* half-new-line */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.HALF_NEW_LINE /* half-new-line */
		};
		serializationSegments[14] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[15] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[16] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
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
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.PUSH /* push */
		};
		serializationSegments[21] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.HALF_NEW_LINE /* half-new-line */
		};
		serializationSegments[22] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[23] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[24] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.POP /* pop */
		};
		serializationSegments[25] = new @NonNull SerializationSegment @NonNull [] {
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
		// 0: SpecificationCS::exprString=UNQUOTED_STRING || soft-space value soft-space
		serializationSteps[0] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, 103 /*UNQUOTED_STRING*/, 12);
		// 1: SpecificationCS::exprString=UNQUOTED_STRING || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[1] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, 103 /*UNQUOTED_STRING*/, 2);
		// 2: ImportCS::isAll?='::*' || soft-space value soft-space
		serializationSteps[2] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, 2 /* '::*' */, 12);
		// 3: MultiplicityCS::isNullFree?='|1' || no-space value no-space
		serializationSteps[3] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 10 /* '|1' */, 6);
		// 4: AbstractNameExpCS::isPre?='@' || soft-space value no-space
		serializationSteps[4] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 4 /* '@' */, 10);
		// 5: DefCS::isStatic?='static' || soft-space value soft-space
		serializationSteps[5] = createSerializationStepAssignKeyword(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, 8 /* 'static' */, 12);
		// 6: MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space
		serializationSteps[6] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 38 /*LOWER*/, 12);
		// 7: CollectionTypeCS::name=CollectionTypeIdentifier || soft-space value soft-space
		serializationSteps[7] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 9 /*CollectionTypeIdentifier*/, 12);
		// 8: MapTypeCS::name='Map' || soft-space value soft-space
		serializationSteps[8] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 5 /* 'Map' */, 12);
		// 9: PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || soft-space value soft-space
		serializationSteps[9] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 73 /*PrimitiveTypeIdentifier*/, 12);
		// 10: TupleTypeCS::name='Tuple' || soft-space value soft-space
		serializationSteps[10] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 6 /* 'Tuple' */, 12);
		// 11: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[11] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 100 /*TypeRefCS*/, 2);
		// 12: TypedTypeRefCS::ownedBinding=TemplateBindingCS || value
		serializationSteps[12] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 86 /*TemplateBindingCS*/, 0);
		// 13: OperationContextDeclCS::ownedBodies+=bodySpecificationCS || value
		serializationSteps[13] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, 115 /*bodySpecificationCS*/, 0);
		// 14: NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || value
		serializationSteps[14] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 4 /*CoIteratorVariableCS*/, 0);
		// 15: CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || value
		serializationSteps[15] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 49 /*MultiplicityCS*/, 0);
		// 16: IfExpCS::ownedCondition=ExpCS|PatternExpCS || value
		serializationSteps[16] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, -1, new int[] { 28/*ExpCS*/,67/*PatternExpCS*/}, 0);
		// 17: IfThenExpCS::ownedCondition=ExpCS || value
		serializationSteps[17] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 28 /*ExpCS*/, 0);
		// 18: CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS || value
		serializationSteps[18] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, 12 /*ContextDeclCS*/, 0);
		// 19: PackageDeclarationCS::ownedContexts+=ContextDeclCS || value
		serializationSteps[19] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, 12 /*ContextDeclCS*/, 0);
		// 20: AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[20] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /*CurlyBracketedClauseCS*/, 0);
		// 21: TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || value
		serializationSteps[21] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /*CurlyBracketedClauseCS*/, 0);
		// 22: PropertyContextDeclCS::ownedDefaultExpressions+=deriveSpecificationCS|initSpecificationCS || value
		serializationSteps[22] = createSerializationStepAssigns(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, -1, new int[] { 116/*deriveSpecificationCS*/,117/*initSpecificationCS*/}, 0);
		// 23: ClassifierContextDeclCS::ownedDefinitions+=DefCS || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 15 /*DefCS*/, 0);
		// 24: IfExpCS::ownedElseExpression=ExpCS || value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 28 /*ExpCS*/, 0);
		// 25: CollectionLiteralPartCS::ownedExpression=ExpCS || value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 28 /*ExpCS*/, 0);
		// 26: CollectionLiteralPartCS::ownedExpression=PatternExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 67 /*PatternExpCS*/, 2);
		// 27: ContextCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[27] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 28 /*ExpCS*/, 2);
		// 28: ExpSpecificationCS::ownedExpression=ExpCS || value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 28 /*ExpCS*/, 0);
		// 29: ExpSpecificationCS::ownedExpression=ExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 28 /*ExpCS*/, 2);
		// 30: NestedExpCS::ownedExpression=ExpCS || value
		serializationSteps[30] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 28 /*ExpCS*/, 0);
		// 31: LambdaLiteralExpCS::ownedExpressionCS=ExpCS || value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 28 /*ExpCS*/, 0);
		// 32: TypeParameterCS::ownedExtends+=TypedRefCS || value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 101 /*TypedRefCS*/, 0);
		// 33: WildcardTypeRefCS::ownedExtends=TypedRefCS || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 101 /*TypedRefCS*/, 0);
		// 34: IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 21 /*ElseIfThenExpCS*/, 0);
		// 35: RootCS::ownedImports+=ImportCS || half-new-line value half-new-line
		serializationSteps[35] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 34 /*ImportCS*/, 4);
		// 36: LetExpCS::ownedInExpression=ExpCS || value
		serializationSteps[36] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 28 /*ExpCS*/, 0);
		// 37: NavigatingArgCS::ownedInitExpression=ExpCS || value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 28 /*ExpCS*/, 0);
		// 38: ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || value
		serializationSteps[38] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new int[] { 28/*ExpCS*/,67/*PatternExpCS*/}, 0);
		// 39: ShadowPartCS::ownedInitExpression=StringLiteralExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[39] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 85 /*StringLiteralExpCS*/, 2);
		// 40: VariableCS::ownedInitExpression=ExpCS || value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 28 /*ExpCS*/, 0);
		// 41: ClassifierContextDeclCS::ownedInvariants+=invConstraintCS || value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 118 /*invConstraintCS*/, 0);
		// 42: PackageDeclarationCS::ownedInvariants+=invConstraintCS || value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, 118 /*invConstraintCS*/, 0);
		// 43: MapLiteralPartCS::ownedKey=ExpCS || value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 28 /*ExpCS*/, 0);
		// 44: MapTypeCS::ownedKeyType=TypeExpCS || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 93 /*TypeExpCS*/, 0);
		// 45: CollectionLiteralPartCS::ownedLastExpression=ExpCS || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 28 /*ExpCS*/, 0);
		// 46: InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 69 /*PrefixedPrimaryExpCS*/, 0);
		// 47: ConstraintCS::ownedMessageSpecification=SpecificationCS || value
		serializationSteps[47] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 82 /*SpecificationCS*/, 0);
		// 48: TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[48] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 49 /*MultiplicityCS*/, 0);
		// 49: TypedRefCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 49 /*MultiplicityCS*/, 0);
		// 50: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || value
		serializationSteps[50] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 54 /*NavigatingArgExpCS*/, 0);
		// 51: NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[51] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 54 /*NavigatingArgExpCS*/, 2);
		// 52: CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS || half-new-line soft-new-line value half-new-line
		serializationSteps[52] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, 64 /*PackageDeclarationCS*/, 13);
		// 53: DefOperationCS::ownedParameters+=DefParameterCS || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, 17 /*DefParameterCS*/, 0);
		// 54: OperationContextDeclCS::ownedParameters+=ParameterCS || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, 65 /*ParameterCS*/, 0);
		// 55: TemplateSignatureCS::ownedParameters+=TypeParameterCS || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 99 /*TypeParameterCS*/, 0);
		// 56: CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 6 /*CollectionLiteralPartCS*/, 0);
		// 57: CollectionPatternCS::ownedParts+=PatternExpCS || value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 67 /*PatternExpCS*/, 0);
		// 58: CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 80 /*ShadowPartCS*/, 0);
		// 59: MapLiteralExpCS::ownedParts+=MapLiteralPartCS || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 45 /*MapLiteralPartCS*/, 0);
		// 60: TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 90 /*TupleLiteralPartCS*/, 0);
		// 61: TupleTypeCS::ownedParts+=TuplePartCS || value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 91 /*TuplePartCS*/, 0);
		// 62: AbstractNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 66 /*PathNameCS*/, 0);
		// 63: ImportCS::ownedPathName=URIPathNameCS || value
		serializationSteps[63] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 107 /*URIPathNameCS*/, 0);
		// 64: PathNameDeclCS::ownedPathName=UnreservedPathNameCS || value
		serializationSteps[64] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 111 /*UnreservedPathNameCS*/, 0);
		// 65: TypeNameExpCS::ownedPathName=PathNameCS || value
		serializationSteps[65] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 66 /*PathNameCS*/, 0);
		// 66: TypedTypeRefCS::ownedPathName=PathNameCS || value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 66 /*PathNameCS*/, 0);
		// 67: TypeNameExpCS::ownedPatternGuard=ExpCS || value
		serializationSteps[67] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 28 /*ExpCS*/, 0);
		// 68: PatternExpCS::ownedPatternType=TypeExpCS || value
		serializationSteps[68] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 93 /*TypeExpCS*/, 0);
		// 69: OperationContextDeclCS::ownedPostconditions+=postConstraintCS || value
		serializationSteps[69] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, 119 /*postConstraintCS*/, 0);
		// 70: OperationContextDeclCS::ownedPreconditions+=preConstraintCS || value
		serializationSteps[70] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, 120 /*preConstraintCS*/, 0);
		// 71: AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[71] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 75 /*RoundBracketedClauseCS*/, 0);
		// 72: LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || value
		serializationSteps[72] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 75 /*RoundBracketedClauseCS*/, 0);
		// 73: TemplateableElementCS::ownedSignature=TemplateSignatureCS || value
		serializationSteps[73] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 88 /*TemplateSignatureCS*/, 0);
		// 74: ConstraintCS::ownedSpecification=SpecificationCS || value
		serializationSteps[74] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 82 /*SpecificationCS*/, 0);
		// 75: DefCS::ownedSpecification=SpecificationCS || value
		serializationSteps[75] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 82 /*SpecificationCS*/, 0);
		// 76: AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || value
		serializationSteps[76] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 83 /*SquareBracketedClauseCS*/, 0);
		// 77: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value
		serializationSteps[77] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 87 /*TemplateParameterSubstitutionCS*/, 0);
		// 78: SquareBracketedClauseCS::ownedTerms+=ExpCS || value
		serializationSteps[78] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 28 /*ExpCS*/, 0);
		// 79: IfExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[79] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 28 /*ExpCS*/, 0);
		// 80: IfThenExpCS::ownedThenExpression=ExpCS || value
		serializationSteps[80] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 28 /*ExpCS*/, 0);
		// 81: CollectionLiteralExpCS::ownedType=CollectionTypeCS || value
		serializationSteps[81] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 8 /*CollectionTypeCS*/, 0);
		// 82: CollectionPatternCS::ownedType=CollectionTypeCS || value
		serializationSteps[82] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 8 /*CollectionTypeCS*/, 0);
		// 83: CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || value
		serializationSteps[83] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 94 /*TypeExpWithoutMultiplicityCS*/, 0);
		// 84: FeatureContextDeclCS::ownedType=TypeExpCS || value
		serializationSteps[84] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 93 /*TypeExpCS*/, 0);
		// 85: MapLiteralExpCS::ownedType=MapTypeCS || value
		serializationSteps[85] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 46 /*MapTypeCS*/, 0);
		// 86: NavigatingArgCS::ownedType=TypeExpCS || value
		serializationSteps[86] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 93 /*TypeExpCS*/, 0);
		// 87: TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[87] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 97 /*TypeLiteralWithMultiplicityCS*/, 2);
		// 88: TypedElementCS::ownedType=TypeExpCS || value
		serializationSteps[88] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 93 /*TypeExpCS*/, 0);
		// 89: VariableCS::ownedType=TypeExpCS || value
		serializationSteps[89] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 93 /*TypeExpCS*/, 0);
		// 90: MapLiteralPartCS::ownedValue=ExpCS || value
		serializationSteps[90] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 28 /*ExpCS*/, 0);
		// 91: MapTypeCS::ownedValueType=TypeExpCS || value
		serializationSteps[91] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 93 /*TypeExpCS*/, 0);
		// 92: LetExpCS::ownedVariables+=LetVariableCS || value
		serializationSteps[92] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 41 /*LetVariableCS*/, 0);
		// 93: PatternExpCS::patternVariableName=UnrestrictedName || soft-space value soft-space
		serializationSteps[93] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 112 /*UnrestrictedName*/, 12);
		// 94: NavigatingArgCS::prefix='|' || soft-space value soft-space
		serializationSteps[94] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 9 /* '|' */, 12);
		// 95: NavigatingArgCS::prefix=';' || no-space value soft-new-line
		serializationSteps[95] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 3 /* ';' */, 7);
		// 96: NavigatingArgCS::prefix=',' || no-space value soft-space
		serializationSteps[96] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 1 /* ',' */, 8);
		// 97: ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[97] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 112, 12);
		// 98: CollectionPatternCS::restVariableName=Identifier || soft-space value soft-space
		serializationSteps[98] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 32 /*Identifier*/, 12);
		// 99: StringLiteralExpCS::segments+=StringLiteral || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[99] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 84 /*StringLiteral*/, 2);
		// 100: ClassifierContextDeclCS::selfName=UnrestrictedName || soft-space value soft-space
		serializationSteps[100] = createSerializationStepAssignedRuleCall(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME, 112 /*UnrestrictedName*/, 12);
		// 101: MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[101] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 12);
		// 102: BooleanLiteralExpCS::symbol='false|true' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[102] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 7 /* 'false|true' */, 2);
		// 103: NumberLiteralExpCS::symbol=NUMBER_LITERAL || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[103] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 51 /*NUMBER_LITERAL*/, 2);
		// 104: MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space
		serializationSteps[104] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 104 /*UPPER*/, 12);
		// 105: '&&' || soft-space value soft-space
		serializationSteps[105] = createSerializationStepKeyword("&&", 12);
		// 106: '(' || value no-space
		serializationSteps[106] = createSerializationStepKeyword("(", 3);
		// 107: '(' || no-space value no-space
		serializationSteps[107] = createSerializationStepKeyword("(", 6);
		// 108: ')' || no-space value
		serializationSteps[108] = createSerializationStepKeyword(")", 1);
		// 109: '*' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[109] = createSerializationStepKeyword("*", 2);
		// 110: '++' || soft-space value soft-space
		serializationSteps[110] = createSerializationStepKeyword("++", 12);
		// 111: ',' || no-space value soft-new-line
		serializationSteps[111] = createSerializationStepKeyword(",", 7);
		// 112: ',' || no-space value soft-space
		serializationSteps[112] = createSerializationStepKeyword(",", 8);
		// 113: '..' || no-space value no-space
		serializationSteps[113] = createSerializationStepKeyword("..", 6);
		// 114: ':' || soft-space value soft-space
		serializationSteps[114] = createSerializationStepKeyword(":", 12);
		// 115: ':' || soft-space value soft-new-line push
		serializationSteps[115] = createSerializationStepKeyword(":", 20);
		// 116: '::' || no-space value no-space
		serializationSteps[116] = createSerializationStepKeyword("::", 6);
		// 117: '<' || soft-space value soft-space
		serializationSteps[117] = createSerializationStepKeyword("<", 12);
		// 118: '=' || soft-space value soft-space
		serializationSteps[118] = createSerializationStepKeyword("=", 12);
		// 119: '=' || soft-space value soft-new-line push
		serializationSteps[119] = createSerializationStepKeyword("=", 20);
		// 120: '>' || soft-space value soft-space
		serializationSteps[120] = createSerializationStepKeyword(">", 12);
		// 121: '?' || soft-space value soft-space
		serializationSteps[121] = createSerializationStepKeyword("?", 12);
		// 122: '?' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[122] = createSerializationStepKeyword("?", 2);
		// 123: 'Lambda' || soft-space value soft-space
		serializationSteps[123] = createSerializationStepKeyword("Lambda", 12);
		// 124: 'Tuple' || soft-space value soft-space
		serializationSteps[124] = createSerializationStepKeyword("Tuple", 12);
		// 125: '[' || no-space value no-space
		serializationSteps[125] = createSerializationStepKeyword("[", 6);
		// 126: ']' || no-space value
		serializationSteps[126] = createSerializationStepKeyword("]", 1);
		// 127: 'body' || soft-space value soft-space
		serializationSteps[127] = createSerializationStepKeyword("body", 12);
		// 128: 'context' || soft-new-line value soft-space
		serializationSteps[128] = createSerializationStepKeyword("context", 9);
		// 129: 'def' || soft-space value soft-space
		serializationSteps[129] = createSerializationStepKeyword("def", 12);
		// 130: 'derive' || soft-space value soft-space
		serializationSteps[130] = createSerializationStepKeyword("derive", 12);
		// 131: 'else' || soft-new-line pop value push soft-space
		serializationSteps[131] = createSerializationStepKeyword("else", 23);
		// 132: 'elseif' || soft-new-line pop soft-space value push soft-space
		serializationSteps[132] = createSerializationStepKeyword("elseif", 25);
		// 133: 'endif' || soft-new-line pop value soft-space
		serializationSteps[133] = createSerializationStepKeyword("endif", 16);
		// 134: 'endpackage' || pop soft-new-line value soft-new-line half-new-line
		serializationSteps[134] = createSerializationStepKeyword("endpackage", 21);
		// 135: 'extends' || soft-space value soft-space
		serializationSteps[135] = createSerializationStepKeyword("extends", 12);
		// 136: 'if' || soft-new-line value push soft-space
		serializationSteps[136] = createSerializationStepKeyword("if", 17);
		// 137: 'import' || value
		serializationSteps[137] = createSerializationStepKeyword("import", 0);
		// 138: 'in' || soft-space value soft-space
		serializationSteps[138] = createSerializationStepKeyword("in", 12);
		// 139: 'in' || soft-space pop value soft-new-line
		serializationSteps[139] = createSerializationStepKeyword("in", 18);
		// 140: 'init' || soft-space value soft-space
		serializationSteps[140] = createSerializationStepKeyword("init", 12);
		// 141: 'inv' || soft-space value soft-space
		serializationSteps[141] = createSerializationStepKeyword("inv", 12);
		// 142: 'invalid' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[142] = createSerializationStepKeyword("invalid", 2);
		// 143: 'let' || soft-space value push
		serializationSteps[143] = createSerializationStepKeyword("let", 11);
		// 144: 'null' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[144] = createSerializationStepKeyword("null", 2);
		// 145: 'package' || half-new-line value push
		serializationSteps[145] = createSerializationStepKeyword("package", 5);
		// 146: 'post' || soft-space value soft-space
		serializationSteps[146] = createSerializationStepKeyword("post", 12);
		// 147: 'pre' || soft-space value soft-space
		serializationSteps[147] = createSerializationStepKeyword("pre", 12);
		// 148: 'pre' || no-space value soft-space
		serializationSteps[148] = createSerializationStepKeyword("pre", 8);
		// 149: 'self' || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[149] = createSerializationStepKeyword("self", 2);
		// 150: 'then' || pop value push soft-space
		serializationSteps[150] = createSerializationStepKeyword("then", 15);
		// 151: 'then' || pop soft-space value push soft-space
		serializationSteps[151] = createSerializationStepKeyword("then", 22);
		// 152: 'with' || value
		serializationSteps[152] = createSerializationStepKeyword("with", 0);
		// 153: '{' || soft-space value push soft-new-line
		serializationSteps[153] = createSerializationStepKeyword("{", 19);
		// 154: '|?' || no-space value no-space
		serializationSteps[154] = createSerializationStepKeyword("|?", 6);
		// 155: '}' || pop soft-new-line value soft-new-line
		serializationSteps[155] = createSerializationStepKeyword("}", 14);
		// 156: V00*1-steps || value
		serializationSteps[156] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 1, 0);
		// 157: V00*1-steps || value
		serializationSteps[157] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 1, 0);
		// 158: V00*1-steps || value
		serializationSteps[158] = createSerializationStepSequence((0/*V0*/ << 4) | 3/*[+]*/, 1, 0);
		// 159: V00*2-steps || value
		serializationSteps[159] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 160: V00*2-steps || value
		serializationSteps[160] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 2, 0);
		// 161: V00*3-steps || value
		serializationSteps[161] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 3, 0);
		// 162: V00*4-steps || value
		serializationSteps[162] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 4, 0);
		// 163: V00*5-steps || value
		serializationSteps[163] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 5, 0);
		// 164: V00*6-steps || value
		serializationSteps[164] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 6, 0);
		// 165: V00*7-steps || value
		serializationSteps[165] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 7, 0);
		// 166: V01*1-steps || value
		serializationSteps[166] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 1, 0);
		// 167: V01*1-steps || value
		serializationSteps[167] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 1, 0);
		// 168: V01*2-steps || value
		serializationSteps[168] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 2, 0);
		// 169: V01*2-steps || value
		serializationSteps[169] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 2, 0);
		// 170: V01*3-steps || value
		serializationSteps[170] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 3, 0);
		// 171: V01*4-steps || value
		serializationSteps[171] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 4, 0);
		// 172: V02*1-steps || value
		serializationSteps[172] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 1, 0);
		// 173: V02*1-steps || value
		serializationSteps[173] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 1, 0);
		// 174: V02*1-steps || value
		serializationSteps[174] = createSerializationStepSequence((2/*V2*/ << 4) | 3/*[+]*/, 1, 0);
		// 175: V02*2-steps || value
		serializationSteps[175] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 2, 0);
		// 176: V02*4-steps || value
		serializationSteps[176] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 4, 0);
		// 177: V03*1-steps || value
		serializationSteps[177] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 1, 0);
		// 178: V03*1-steps || value
		serializationSteps[178] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 1, 0);
		// 179: V03*1-steps || value
		serializationSteps[179] = createSerializationStepSequence((3/*V3*/ << 4) | 3/*[+]*/, 1, 0);
		// 180: V03*2-steps || value
		serializationSteps[180] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 2, 0);
		// 181: V03*2-steps || value
		serializationSteps[181] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 2, 0);
		// 182: V04*1-steps || value
		serializationSteps[182] = createSerializationStepSequence((4/*V4*/ << 4) | 1/*[?]*/, 1, 0);
		// 183: V04*1-steps || value
		serializationSteps[183] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 1, 0);
		// 184: V05*1-steps || value
		serializationSteps[184] = createSerializationStepSequence((5/*V5*/ << 4) | 2/*[*]*/, 1, 0);
		// 185: V06*1-steps || value
		serializationSteps[185] = createSerializationStepSequence((6/*V6*/ << 4) | 2/*[*]*/, 1, 0);
		// 186: wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[186] = createSerializationStepWrapper(2);
		// 187: wrapper || soft-new-line push value pop pop
		serializationSteps[187] = createSerializationStepWrapper(24);
		// 188: NamedElementCS::name=BinaryOperatorName || soft-space value soft-space
		serializationSteps[188] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 1 /*BinaryOperatorName*/, 12);
		// 189: NamedElementCS::name=UnaryOperatorName || soft-space value soft-space
		serializationSteps[189] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 108 /*UnaryOperatorName*/, 12);
		// 190: NamedElementCS::name=UnrestrictedName || soft-space value soft-space
		serializationSteps[190] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 112 /*UnrestrictedName*/, 12);
		// 191: NamedElementCS::name=Identifier || soft-space value soft-space
		serializationSteps[191] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 32 /*Identifier*/, 12);
		// 192: RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || value
		serializationSteps[192] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 53 /*NavigatingArgCS*/, 0);
		// 193: RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || value
		serializationSteps[193] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new int[] { 56/*NavigatingCommaArgCS*/,57/*NavigatingSemiArgCS*/,55/*NavigatingBarArgCS*/}, 0);
		// 194: PathNameCS::ownedPathElements+=URIFirstPathElementCS || value
		serializationSteps[194] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 106 /*URIFirstPathElementCS*/, 0);
		// 195: PathNameCS::ownedPathElements+=FirstPathElementCS || value
		serializationSteps[195] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 29 /*FirstPathElementCS*/, 0);
		// 196: PathNameCS::ownedPathElements+=NextPathElementCS || value
		serializationSteps[196] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 60 /*NextPathElementCS*/, 0);
		// 197: OperatorExpCS::ownedRight=ExpCS || value
		serializationSteps[197] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 28 /*ExpCS*/, 0);
		// 198: OperatorExpCS::ownedRight=PrefixedLetExpCS || value
		serializationSteps[198] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 68 /*PrefixedLetExpCS*/, 0);
		// 199: OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || value
		serializationSteps[199] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 69 /*PrefixedPrimaryExpCS*/, 0);
		// 200: PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[200] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 105, 12);
		// 201: PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[201] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 110, 12);
		// 202: PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[202] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 112, 12);
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSubstringSteps() {
		// 0: '->' : [no-space, value, no-space]
		substringSteps[0] = createSubstringStep("->", 6 /* no-space, value, no-space */);
		// 1: '.' : [no-space, value, no-space]
		substringSteps[1] = createSubstringStep(".", 6 /* no-space, value, no-space */);
		// 2: '=' : [soft-space, value, soft-new-line, push]
		substringSteps[2] = createSubstringStep("=", 20 /* soft-space, value, soft-new-line, push */);
		// 3: '?->' : [no-space, value, no-space]
		substringSteps[3] = createSubstringStep("?->", 6 /* no-space, value, no-space */);
		// 4: '?.' : [no-space, value, no-space]
		substringSteps[4] = createSubstringStep("?.", 6 /* no-space, value, no-space */);
		// 5: 'else' : [soft-new-line, pop, value, push, soft-space]
		substringSteps[5] = createSubstringStep("else", 23 /* soft-new-line, pop, value, push, soft-space */);
		// 6: 'endif' : [soft-new-line, pop, value, soft-space]
		substringSteps[6] = createSubstringStep("endif", 16 /* soft-new-line, pop, value, soft-space */);
		// 7: 'if' : [soft-new-line, value, push, soft-space]
		substringSteps[7] = createSubstringStep("if", 17 /* soft-new-line, value, push, soft-space */);
		// 8: 'in' : [soft-space, pop, value, soft-new-line]
		substringSteps[8] = createSubstringStep("in", 18 /* soft-space, pop, value, soft-new-line */);
		// 9: 'let' : [soft-space, value, push]
		substringSteps[9] = createSubstringStep("let", 11 /* soft-space, value, push */);
		// 10: 'then' : [pop, soft-space, value, push, soft-space]
		substringSteps[10] = createSubstringStep("then", 22 /* pop, soft-space, value, push, soft-space */);
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
//	import CompleteOCLCSPackage;
//	import EssentialOCLCSPackage;
//	import Grammar;
//	import GrammarProvider;
