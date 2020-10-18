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
package org.eclipse.ocl.xtext.oclinecore.serializer;

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
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;

public class OCLinEcoreSerializationMetaData extends AbstractSerializationMetaData
{
	private boolean initialized = false;
	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[69];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[24];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[130];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[76];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[321];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[229];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[165];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [10] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[284];

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
		return 106;
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
		return 105;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 179;
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
				96 /* { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */,
				97 /* { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] } "}" } } */,
				98 /* { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] } "}" } } */,
				99 /* { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					44) /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					6) /* DetailCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					18) /* ModelElementRefCS */
			}
		);
		eClassValues[1] = new EClassValue(BaseCSPackage.Literals.ATTRIBUTE_CS,
			createSerializationRules(
				100 /* { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				102 /* { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				104 /* { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				101 /* { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				103 /* { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				105 /* { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					38) /* SpecificationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[2] = new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
			createSerializationRules(
				15 /* symbol={'false|true'} */,
				24 /* symbol={'false|true'} */
			), null
		);
		eClassValues[3] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
			createSerializationRules(
				17 /* { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				25 /* { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					3) /* CollectionLiteralPartCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					4) /* CollectionTypeCS */
			}
		);
		eClassValues[4] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
			createSerializationRules(
				18 /* ownedExpression=PatternExpCS */,
				19 /* { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					73) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[5] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
			createSerializationRules(
				20 /* { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */,
				82 /* { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					28) /* PatternExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					4) /* CollectionTypeCS */
			}
		);
		eClassValues[6] = new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
			createSerializationRules(
				21 /* { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				83 /* { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				89 /* { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				160 /* { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					56) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[7] = new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
			createSerializationRules(
				49 /* ownedExpression=ExpCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[8] = new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				22 /* { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					37) /* ShadowPartCS */
			}
		);
		eClassValues[9] = new EClassValue(BaseCSPackage.Literals.DATA_TYPE_CS,
			createSerializationRules(
				108 /* { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" } */,
				106 /* { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" } */,
				110 /* { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" } */,
				109 /* { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				107 /* { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				111 /* { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					13) /* InvariantConstraintCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					48) /* TemplateSignatureCS */
			}
		);
		eClassValues[10] = new EClassValue(BaseCSPackage.Literals.DETAIL_CS,
			createSerializationRules(
				112 /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) "=" values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
			), null
		);
		eClassValues[11] = new EClassValue(BaseCSPackage.Literals.DOCUMENTATION_CS,
			createSerializationRules(
				113 /* { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					6) /* DetailCS */
			}
		);
		eClassValues[12] = new EClassValue(BaseCSPackage.Literals.ENUMERATION_CS,
			createSerializationRules(
				116 /* { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" } */,
				114 /* { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" } */,
				118 /* { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" } */,
				117 /* { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				115 /* { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				119 /* { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					13) /* InvariantConstraintCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					8) /* EnumerationLiteralCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					48) /* TemplateSignatureCS */
			}
		);
		eClassValues[13] = new EClassValue(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS,
			createSerializationRules(
				120 /* { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" } */,
				122 /* { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" } */,
				121 /* { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } } */,
				123 /* { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
			}
		);
		eClassValues[14] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
			createSerializationRules(
				26 /* "*" */,
				27 /* "invalid" */,
				28 /* "null" */,
				29 /* "self" */
			), null
		);
		eClassValues[15] = new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
			createSerializationRules(
				146 /* exprString=UNQUOTED_STRING */,
				147 /* ownedExpression=ExpCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[16] = new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
			createSerializationRules(
				30 /* { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				41 /* { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					73) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					7) /* ElseIfThenExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[17] = new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
			createSerializationRules(
				23 /* { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[18] = new EClassValue(BaseCSPackage.Literals.IMPLICIT_OPPOSITE_CS,
			createSerializationRules(
				124 /* { "opposite" name=UnrestrictedName ":" ownedType=TypedMultiplicityRefCS { "{" { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] "}" }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[19] = new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
			createSerializationRules(
				125 /* { {"import"|"library"} { name=UnrestrictedName ":" }[?] ownedPathName=URIPathNameCS isAll="::*"[?] ";" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					66) /* URIPathNameCS */
			}
		);
		eClassValues[20] = new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
			createSerializationRules(
				31 /* { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[21] = new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
			createSerializationRules(
				42 /* "invalid" */
			), null
		);
		eClassValues[22] = new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
			createSerializationRules(
				32 /* { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				43 /* { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[23] = new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
			createSerializationRules(
				44 /* { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					14) /* LetVariableCS */
			}
		);
		eClassValues[24] = new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
			createSerializationRules(
				45 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					35) /* RoundBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[25] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
			createSerializationRules(
				33 /* { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				46 /* { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					15) /* MapLiteralPartCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					16) /* MapTypeCS */
			}
		);
		eClassValues[26] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
			createSerializationRules(
				47 /* { ownedKey=ExpCS "<-" ownedValue=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[27] = new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
			createSerializationRules(
				48 /* { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				84 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				90 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				161 /* { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[28] = new EClassValue(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS,
			createSerializationRules(
				128 /* { "reference" ownedPathName=PathNameCS ";" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
					27) /* PathNameCS */
			}
		);
		eClassValues[29] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */,
				2 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" } */,
				3 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" } */,
				4 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree="|1"[?] "]" } */
			), null
		);
		eClassValues[30] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				5 /* { "[" stringBounds={'*|+|?'} "]" } */,
				6 /* { "[" stringBounds={'*|+|?'} "|?" "]" } */,
				7 /* { "[" stringBounds={'*|+|?'} isNullFree="|1"[?] "]" } */,
				8 /* stringBounds={'*|+|?'} */
			), null
		);
		eClassValues[31] = new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
			createSerializationRules(
				34 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				50 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					5) /* CurlyBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					27) /* PathNameCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					35) /* RoundBracketedClauseCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					39) /* SquareBracketedClauseCS */
			}
		);
		eClassValues[32] = new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
			createSerializationRules(
				51 /* ownedNameExpression=NavigatingArgExpCS */,
				52 /* { ":" ownedType=TypeExpCS } */,
				54 /* { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				53 /* { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				55 /* { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */,
				56 /* { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */,
				60 /* { prefix="," ownedNameExpression=NavigatingArgExpCS } */,
				58 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				57 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				59 /* { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */,
				61 /* { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					2) /* CoIteratorVariableCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					72) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[33] = new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
			createSerializationRules(
				35 /* { "(" ownedExpression=ExpCS ")" } */,
				62 /* { "(" ownedExpression=ExpCS ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[34] = new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
			createSerializationRules(
				63 /* "null" */
			), null
		);
		eClassValues[35] = new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
			createSerializationRules(
				36 /* symbol=NUMBER_LITERAL */,
				64 /* symbol=NUMBER_LITERAL */
			), null
		);
		eClassValues[36] = new EClassValue(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS,
			createSerializationRules(
				126 /* { isCallable="callable"[?] stereotype="invariant" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ";" } */,
				127 /* { isCallable="callable"[?] stereotype="invariant" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] { ":" ownedSpecification=SpecificationCS[?] ";" } } */,
				138 /* { stereotype="postcondition" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS[?] ";" } */,
				139 /* { stereotype="precondition" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS[?] ";" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					38) /* SpecificationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					38) /* SpecificationCS */
			}
		);
		eClassValues[37] = new EClassValue(BaseCSPackage.Literals.OPERATION_CS,
			createSerializationRules(
				129 /* { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" } */,
				131 /* { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" } */,
				133 /* { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" } */,
				130 /* { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } } */,
				132 /* { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } } */,
				134 /* { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					38) /* SpecificationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					62) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					26) /* ParameterCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					30) /* PostconditionConstraintCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					31) /* PreconditionConstraintCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					48) /* TemplateSignatureCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[38] = new EClassValue(BaseCSPackage.Literals.PACKAGE_CS,
			createSerializationRules(
				135 /* { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] ";" } */,
				136 /* { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					43) /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					25) /* PackageCS */
			}
		);
		eClassValues[39] = new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
			createSerializationRules(
				137 /* { name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "{" { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] "}" }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[40] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* referredElement=UnrestrictedName */,
				9 /* referredElement=UnreservedName */,
				92 /* referredElement=UnrestrictedName */
			), null
		);
		eClassValues[41] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
			createSerializationRules(
				93 /* referredElement=URI */
			), null
		);
		eClassValues[42] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */,
				73 /* ownedPathElements+=FirstPathElementCS */,
				94 /* { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					65) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
			}
		);
		eClassValues[43] = new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
			createSerializationRules(
				65 /* { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[44] = new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
			createSerializationRules(
				37 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				66 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				67 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					70) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[45] = new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
			createSerializationRules(
				68 /* name=PrimitiveTypeIdentifier */,
				80 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				87 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				155 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */
			}
		);
		eClassValues[46] = new EClassValue(BaseCSPackage.Literals.REFERENCE_CS,
			createSerializationRules(
				140 /* { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				142 /* { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				144 /* { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				141 /* { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */,
				143 /* { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */,
				145 /* { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					38) /* SpecificationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					11) /* ImplicitOppositeCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[47] = new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				69 /* { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					22) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			}
		);
		eClassValues[48] = new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
			createSerializationRules(
				70 /* "self" */
			), null
		);
		eClassValues[49] = new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
			createSerializationRules(
				71 /* ownedInitExpression=StringLiteralExpCS */,
				72 /* { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					73) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[50] = new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
			createSerializationRules(
				74 /* { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[51] = new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
			createSerializationRules(
				38 /* segments+=StringLiteral[+] */,
				75 /* segments+=StringLiteral[+] */
			), null
		);
		eClassValues[52] = new EClassValue(BaseCSPackage.Literals.STRUCTURED_CLASS_CS,
			createSerializationRules(
				148 /* { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] ";" } */,
				149 /* { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					13) /* InvariantConstraintCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					24) /* OperationCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					42) /* AttributeCS|ReferenceCS|StructuralFeatureCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					48) /* TemplateSignatureCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					62) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[53] = new EClassValue(OCLinEcoreCSPackage.Literals.SYS_MLCS,
			createSerializationRules(
				151 /* { "sysml" { ownedDetails+=DetailCS ";" } } */,
				150 /* { "sysml" { "{" { ownedDetails+=DetailCS ";" }[*] "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					6) /* DetailCS */
			}
		);
		eClassValues[54] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					47) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[55] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					75) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[56] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				152 /* { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */,
				153 /* { "<" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ">" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					58) /* TypeParameterCS */
			}
		);
		eClassValues[57] = new EClassValue(OCLinEcoreCSPackage.Literals.TOP_LEVEL_CS,
			createSerializationRules(
				154 /* { { "module" }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					12) /* ImportCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					25) /* PackageCS */
			}
		);
		eClassValues[58] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
			createSerializationRules(
				39 /* { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				76 /* { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					49) /* TupleLiteralPartCS */
			}
		);
		eClassValues[59] = new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
			createSerializationRules(
				77 /* { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[60] = new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
			createSerializationRules(
				78 /* { name=UnrestrictedName ":" ownedType=TypeExpCS } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[61] = new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
			createSerializationRules(
				79 /* { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				81 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				88 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				156 /* { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					50) /* TuplePartCS */
			}
		);
		eClassValues[62] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
			createSerializationRules(
				40 /* ownedType=TypeLiteralWithMultiplicityCS */,
				86 /* ownedType=TypeLiteralWithMultiplicityCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					55) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
			}
		);
		eClassValues[63] = new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
			createSerializationRules(
				85 /* { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				91 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					5) /* CurlyBracketedClauseCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					27) /* PathNameCS */,
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
			}
		);
		eClassValues[64] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				13 /* { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					62) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[65] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				162 /* ownedPathName=PathNameCS */,
				163 /* { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } } */,
				164 /* { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } } */,
				157 /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
				158 /* { { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } } ownedMultiplicity=MultiplicityCS[?] } */,
				159 /* { { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } } ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					46) /* TemplateBindingCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					19) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					27) /* PathNameCS */
			}
		);
		eClassValues[66] = new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
			createSerializationRules(
				95 /* "*" */
			), null
		);
		eClassValues[67] = new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
			createSerializationRules(
				16 /* { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
			}
		);
		eClassValues[68] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				14 /* { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					62) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
			}
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'
		enumerationValues[0] = new EnumerationValueMultiple(new @NonNull String[]{"!composes", "!derived", "!ordered", "!readonly", "!resolve", "!transient", "!unique", "!unsettable", "!volatile", "composes", "derived", "ordered", "readonly", "resolve", "transient", "unique", "unsettable", "volatile"});
		// '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'
		enumerationValues[1] = new EnumerationValueMultiple(new @NonNull String[]{"!derived", "!id", "!ordered", "!readonly", "!transient", "!unique", "!unsettable", "!volatile", "derived", "id", "ordered", "readonly", "transient", "unique", "unsettable", "volatile"});
		// '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'
		enumerationValues[2] = new EnumerationValueMultiple(new @NonNull String[]{"!derived", "!ordered", "!transient", "!unique", "derived", "ordered", "transient", "unique"});
		// '!ordered|!unique|ordered|unique'
		enumerationValues[3] = new EnumerationValueMultiple(new @NonNull String[]{"!ordered", "!unique", "ordered", "unique"});
		// '*|+|?'
		enumerationValues[4] = new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		// ','
		enumerationValues[5] = new EnumerationValueSingle(",");
		// '::*'
		enumerationValues[6] = new EnumerationValueSingle("::*");
		// ';'
		enumerationValues[7] = new EnumerationValueSingle(";");
		// '@'
		enumerationValues[8] = new EnumerationValueSingle("@");
		// 'Map'
		enumerationValues[9] = new EnumerationValueSingle("Map");
		// 'Tuple'
		enumerationValues[10] = new EnumerationValueSingle("Tuple");
		// 'abstract'
		enumerationValues[11] = new EnumerationValueSingle("abstract");
		// 'callable'
		enumerationValues[12] = new EnumerationValueSingle("callable");
		// 'definition'
		enumerationValues[13] = new EnumerationValueSingle("definition");
		// 'false|true'
		enumerationValues[14] = new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		// 'interface'
		enumerationValues[15] = new EnumerationValueSingle("interface");
		// 'invariant'
		enumerationValues[16] = new EnumerationValueSingle("invariant");
		// 'postcondition'
		enumerationValues[17] = new EnumerationValueSingle("postcondition");
		// 'precondition'
		enumerationValues[18] = new EnumerationValueSingle("precondition");
		// 'primitive'
		enumerationValues[19] = new EnumerationValueSingle("primitive");
		// 'serializable'
		enumerationValues[20] = new EnumerationValueSingle("serializable");
		// 'static'
		enumerationValues[21] = new EnumerationValueSingle("static");
		// '|'
		enumerationValues[22] = new EnumerationValueSingle("|");
		// '|1'
		enumerationValues[23] = new EnumerationValueSingle("|1");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = createParserRuleValue(1, "AnnotationCS", -1,
			createSerializationRules(
				96	/* AnnotationCS: { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */,
				97	/* AnnotationCS: { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] } "}" } } */,
				98	/* AnnotationCS: { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] } "}" } } */,
				99	/* AnnotationCS: { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {AnnotationCS} : [value] | [value] */,
			(0 << 16) | 7	/* "annotation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=(UnrestrictedName|SINGLE_QUOTED_STRING)? : [value] | [soft-space, value, soft-space] */,
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
			(0 << 16) | 0	/* Alternatives+ : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedContents+=ModelElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedReferences+=ModelElementRefCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[2] = createParserRuleValue(2, "AnnotationElementCS", 45 /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
			createSerializationRules(
				96	/* AnnotationCS: { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */,
				97	/* AnnotationCS: { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] } "}" } } */,
				98	/* AnnotationCS: { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] } "}" } } */,
				99	/* AnnotationCS: { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] } "}" } } */,
				113	/* DocumentationCS: { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */,
				150	/* SysMLCS: { "sysml" { "{" { ownedDetails+=DetailCS ";" }[*] "}" } } */,
				151	/* SysMLCS: { "sysml" { ownedDetails+=DetailCS ";" } } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* AnnotationCS : [value] | [value] */,
			(0 << 16) | 0	/* DocumentationCS : [value] | [value] */,
			(0 << 16) | 0	/* SysMLCS : [value] | [value] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "AttributeCS", -1,
			createSerializationRules(
				100	/* AttributeCS: { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				101	/* AttributeCS: { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				102	/* AttributeCS: { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				103	/* AttributeCS: { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				104	/* AttributeCS: { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				105	/* AttributeCS: { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="static" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="definition"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="definition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "attribute" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* default=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="id" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!id" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="readonly" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!readonly" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="unsettable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!unsettable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="volatile" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!volatile" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 7	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "initial" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "derivation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 7	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[4] = new DataTypeRuleValue(4, "BinaryOperatorName");
		grammarRuleValues[5] = createParserRuleValue(5, "BooleanLiteralExpCS", -1,
			createSerializationRules(
				15	/* BooleanLiteralExpCS: symbol={'false|true'} */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* symbol="true" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* symbol="false" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[6] = createParserRuleValue(6, "ClassCS", 43 /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */,
			createSerializationRules(
				106	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" } */,
				107	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				108	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" } */,
				109	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				110	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" } */,
				111	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				114	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" } */,
				115	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				116	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" } */,
				117	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				118	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" } */,
				119	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				148	/* StructuredClassCS: { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] ";" } */,
				149	/* StructuredClassCS: { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* StructuredClassCS : [value] | [value] */,
			(0 << 16) | 0	/* DataTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* EnumerationCS : [value] | [value] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "CoIteratorVariableCS", -1,
			createSerializationRules(
				16	/* CoIteratorVariableCS: { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[8] = createParserRuleValue(8, "CollectionLiteralExpCS", -1,
			createSerializationRules(
				17	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */
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
		grammarRuleValues[9] = createParserRuleValue(9, "CollectionLiteralPartCS", -1,
			createSerializationRules(
				18	/* CollectionLiteralPartCS: ownedExpression=PatternExpCS */,
				19	/* CollectionLiteralPartCS: { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedLastExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedExpression=PatternExpCS : [value] | [value] */
		);
		grammarRuleValues[10] = createParserRuleValue(10, "CollectionPatternCS", -1,
			createSerializationRules(
				20	/* CollectionPatternCS: { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */
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
		grammarRuleValues[11] = createParserRuleValue(11, "CollectionTypeCS", -1,
			createSerializationRules(
				21	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* name=CollectionTypeIdentifier : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpWithoutMultiplicityCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedCollectionMultiplicity=MultiplicityCS? : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[12] = new DataTypeRuleValue(12, "CollectionTypeIdentifier");
		grammarRuleValues[13] = createParserRuleValue(13, "CurlyBracketedClauseCS", -1,
			createSerializationRules(
				22	/* CurlyBracketedClauseCS: { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" } */
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
		grammarRuleValues[14] = new TerminalRuleValue(14, "DOUBLE_QUOTED_STRING");
		grammarRuleValues[15] = createParserRuleValue(15, "DataTypeCS", -1,
			createSerializationRules(
				106	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" } */,
				107	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				108	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" } */,
				109	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				110	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" } */,
				111	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* isPrimitive?="primitive"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "datatype" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* instanceClassName=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 7	/* isSerializable?="serializable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "!serializable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedConstraints+=InvariantConstraintCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[16] = createParserRuleValue(16, "DetailCS", -1,
			createSerializationRules(
				112	/* DetailCS: { name=(UnrestrictedName|SINGLE_QUOTED_STRING) "=" values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=(UnrestrictedName|SINGLE_QUOTED_STRING) : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)* : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "DocumentationCS", -1,
			createSerializationRules(
				113	/* DocumentationCS: { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" } */
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
				23	/* ElseIfThenExpCS: { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "elseif" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedCondition=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "then" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedThenExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[21] = createParserRuleValue(21, "EnumerationCS", -1,
			createSerializationRules(
				114	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" } */,
				115	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				116	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" } */,
				117	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				118	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" } */,
				119	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "enum" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* instanceClassName=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 7	/* isSerializable?="serializable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "!serializable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedLiterals+=EnumerationLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedConstraints+=InvariantConstraintCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[22] = createParserRuleValue(22, "EnumerationLiteralCS", -1,
			createSerializationRules(
				120	/* EnumerationLiteralCS: { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" } */,
				121	/* EnumerationLiteralCS: { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } } */,
				122	/* EnumerationLiteralCS: { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" } */,
				123	/* EnumerationLiteralCS: { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "literal" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=EnumerationLiteralName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* literal=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* value=SIGNED : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS* : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[23] = new DataTypeRuleValue(23, "EnumerationLiteralName");
		grammarRuleValues[24] = new DataTypeRuleValue(24, "EssentialOCLInfixOperatorName");
		grammarRuleValues[25] = new DataTypeRuleValue(25, "EssentialOCLNavigationOperatorName");
		grammarRuleValues[26] = new DataTypeRuleValue(26, "EssentialOCLReservedKeyword");
		grammarRuleValues[27] = new DataTypeRuleValue(27, "EssentialOCLUnaryOperatorName");
		grammarRuleValues[28] = new DataTypeRuleValue(28, "EssentialOCLUnreservedName");
		grammarRuleValues[29] = new DataTypeRuleValue(29, "EssentialOCLUnrestrictedName");
		grammarRuleValues[30] = createParserRuleValue(30, "ExpCS", 71 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				24	/* ExpCS: symbol={'false|true'} */,
				25	/* ExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				26	/* ExpCS: "*" */,
				27	/* ExpCS: "invalid" */,
				28	/* ExpCS: "null" */,
				29	/* ExpCS: "self" */,
				30	/* ExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				31	/* ExpCS: { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */,
				32	/* ExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				33	/* ExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				34	/* ExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				35	/* ExpCS: { "(" ownedExpression=ExpCS ")" } */,
				36	/* ExpCS: symbol=NUMBER_LITERAL */,
				37	/* ExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				38	/* ExpCS: segments+=StringLiteral[+] */,
				39	/* ExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				40	/* ExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				44	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				66	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
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
		grammarRuleValues[31] = createParserRuleValue(31, "FirstPathElementCS", -1,
			createSerializationRules(
				0	/* FirstPathElementCS: referredElement=UnrestrictedName */
			),
			(0 << 16) | 7	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[32] = new DataTypeRuleValue(32, "ID");
		grammarRuleValues[33] = new TerminalRuleValue(33, "INT");
		grammarRuleValues[34] = new DataTypeRuleValue(34, "INTEGER");
		grammarRuleValues[35] = new DataTypeRuleValue(35, "Identifier");
		grammarRuleValues[36] = createParserRuleValue(36, "IfExpCS", -1,
			createSerializationRules(
				41	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */
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
		grammarRuleValues[37] = createParserRuleValue(37, "ImplicitOppositeCS", -1,
			createSerializationRules(
				124	/* ImplicitOppositeCS: { "opposite" name=UnrestrictedName ":" ownedType=TypedMultiplicityRefCS { "{" { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] "}" }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "opposite" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[38] = createParserRuleValue(38, "ImportCS", -1,
			createSerializationRules(
				125	/* ImportCS: { {"import"|"library"} { name=UnrestrictedName ":" }[?] ownedPathName=URIPathNameCS isAll="::*"[?] ";" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 7	/* "import" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "library" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=URIPathNameCS : [value] | [value] */,
			(0 << 16) | 7	/* isAll?="::*"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[39] = new DataTypeRuleValue(39, "InfixOperatorName");
		grammarRuleValues[40] = createParserRuleValue(40, "InvalidLiteralExpCS", -1,
			createSerializationRules(
				42	/* InvalidLiteralExpCS: "invalid" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {InvalidLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* "invalid" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "InvariantConstraintCS", -1,
			createSerializationRules(
				126	/* InvariantConstraintCS: { isCallable="callable"[?] stereotype="invariant" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ";" } */,
				127	/* InvariantConstraintCS: { isCallable="callable"[?] stereotype="invariant" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] { ":" ownedSpecification=SpecificationCS[?] ";" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* isCallable?="callable"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* stereotype="invariant" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[42] = new TerminalRuleValue(42, "LETTER_CHARACTER");
		grammarRuleValues[43] = new DataTypeRuleValue(43, "LOWER");
		grammarRuleValues[44] = createParserRuleValue(44, "LambdaLiteralExpCS", -1,
			createSerializationRules(
				43	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "Lambda" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedExpressionCS=ExpCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[45] = createParserRuleValue(45, "LetExpCS", -1,
			createSerializationRules(
				44	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */
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
		grammarRuleValues[46] = createParserRuleValue(46, "LetVariableCS", -1,
			createSerializationRules(
				45	/* LetVariableCS: { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
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
		grammarRuleValues[47] = new TerminalRuleValue(47, "ML_COMMENT");
		grammarRuleValues[48] = new TerminalRuleValue(48, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[49] = createParserRuleValue(49, "MapLiteralExpCS", -1,
			createSerializationRules(
				46	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */
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
		grammarRuleValues[50] = createParserRuleValue(50, "MapLiteralPartCS", -1,
			createSerializationRules(
				47	/* MapLiteralPartCS: { ownedKey=ExpCS "<-" ownedValue=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedKey=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "<-" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedValue=ExpCS : [value] | [value] */
		);
		grammarRuleValues[51] = createParserRuleValue(51, "MapTypeCS", -1,
			createSerializationRules(
				48	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */
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
		grammarRuleValues[52] = createParserRuleValue(52, "Model", -1,
			createSerializationRules(
				49	/* Model: ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* ownedExpression=ExpCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[53] = createParserRuleValue(53, "ModelElementCS", 44 /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
			createSerializationRules(
				100	/* AttributeCS: { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				101	/* AttributeCS: { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				102	/* AttributeCS: { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				103	/* AttributeCS: { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				104	/* AttributeCS: { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				105	/* AttributeCS: { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				106	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" } */,
				107	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				108	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" } */,
				109	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				110	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" } */,
				111	/* DataTypeCS: { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				114	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" } */,
				115	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				116	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" } */,
				117	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				118	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" } */,
				119	/* EnumerationCS: { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */,
				120	/* EnumerationLiteralCS: { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" } */,
				121	/* EnumerationLiteralCS: { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } } */,
				122	/* EnumerationLiteralCS: { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" } */,
				123	/* EnumerationLiteralCS: { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } } */,
				129	/* OperationCS: { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" } */,
				130	/* OperationCS: { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } } */,
				131	/* OperationCS: { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" } */,
				132	/* OperationCS: { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } } */,
				133	/* OperationCS: { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" } */,
				134	/* OperationCS: { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } } */,
				135	/* PackageCS: { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] ";" } */,
				136	/* PackageCS: { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] } "}" } } */,
				140	/* ReferenceCS: { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				141	/* ReferenceCS: { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */,
				142	/* ReferenceCS: { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				143	/* ReferenceCS: { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */,
				144	/* ReferenceCS: { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				145	/* ReferenceCS: { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */,
				148	/* StructuredClassCS: { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] ";" } */,
				149	/* StructuredClassCS: { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ClassCS : [value] | [value] */,
			(0 << 16) | 0	/* EnumerationLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* OperationCS : [value] | [value] */,
			(0 << 16) | 0	/* PackageCS : [value] | [value] */,
			(0 << 16) | 0	/* StructuralFeatureCS : [value] | [value] */
		);
		grammarRuleValues[54] = createParserRuleValue(54, "ModelElementRefCS", -1,
			createSerializationRules(
				128	/* ModelElementRefCS: { "reference" ownedPathName=PathNameCS ";" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "reference" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[55] = createParserRuleValue(55, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS: { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 7	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[56] = createParserRuleValue(56, "MultiplicityCS", -1,
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
		grammarRuleValues[57] = createParserRuleValue(57, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS: stringBounds={'*|+|?'} */
			),
			(0 << 16) | 7	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[58] = new DataTypeRuleValue(58, "NUMBER_LITERAL");
		grammarRuleValues[59] = createParserRuleValue(59, "NameExpCS", -1,
			createSerializationRules(
				50	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */
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
		grammarRuleValues[60] = createParserRuleValue(60, "NavigatingArgCS", -1,
			createSerializationRules(
				51	/* NavigatingArgCS: ownedNameExpression=NavigatingArgExpCS */,
				52	/* NavigatingArgCS: { ":" ownedType=TypeExpCS } */,
				53	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				54	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				55	/* NavigatingArgCS: { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */
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
		grammarRuleValues[61] = createParserRuleValue(61, "NavigatingArgExpCS", 72 /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				24	/* ExpCS: symbol={'false|true'} */,
				25	/* ExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				26	/* ExpCS: "*" */,
				27	/* ExpCS: "invalid" */,
				28	/* ExpCS: "null" */,
				29	/* ExpCS: "self" */,
				30	/* ExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				31	/* ExpCS: { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } } */,
				32	/* ExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				33	/* ExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				34	/* ExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				35	/* ExpCS: { "(" ownedExpression=ExpCS ")" } */,
				36	/* ExpCS: symbol=NUMBER_LITERAL */,
				37	/* ExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				38	/* ExpCS: segments+=StringLiteral[+] */,
				39	/* ExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
				40	/* ExpCS: ownedType=TypeLiteralWithMultiplicityCS */,
				44	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				66	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* ExpCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[62] = createParserRuleValue(62, "NavigatingBarArgCS", -1,
			createSerializationRules(
				56	/* NavigatingBarArgCS: { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
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
		grammarRuleValues[63] = createParserRuleValue(63, "NavigatingCommaArgCS", -1,
			createSerializationRules(
				60	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS } */,
				57	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } } */,
				58	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } } */,
				59	/* NavigatingCommaArgCS: { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } } */
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
		grammarRuleValues[64] = createParserRuleValue(64, "NavigatingSemiArgCS", -1,
			createSerializationRules(
				61	/* NavigatingSemiArgCS: { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] } */
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
		grammarRuleValues[65] = new DataTypeRuleValue(65, "NavigationOperatorName");
		grammarRuleValues[66] = createParserRuleValue(66, "NestedExpCS", -1,
			createSerializationRules(
				62	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[67] = createParserRuleValue(67, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS: referredElement=UnreservedName */
			),
			(0 << 16) | 7	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[68] = createParserRuleValue(68, "NullLiteralExpCS", -1,
			createSerializationRules(
				63	/* NullLiteralExpCS: "null" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {NullLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* "null" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[69] = createParserRuleValue(69, "NumberLiteralExpCS", -1,
			createSerializationRules(
				64	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */
			),
			(0 << 16) | 2	/* symbol=NUMBER_LITERAL : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[70] = createParserRuleValue(70, "OperationCS", -1,
			createSerializationRules(
				129	/* OperationCS: { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" } */,
				130	/* OperationCS: { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } } */,
				131	/* OperationCS: { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" } */,
				132	/* OperationCS: { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } } */,
				133	/* OperationCS: { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" } */,
				134	/* OperationCS: { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="static" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="definition"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="definition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "operation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=ParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "throws" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExceptions+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExceptions+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPreconditions+=PreconditionConstraintCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "body" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 6	/* ":" : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedBodyExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedPostconditions+=PostconditionConstraintCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[71] = createParserRuleValue(71, "PackageCS", -1,
			createSerializationRules(
				135	/* PackageCS: { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] ";" } */,
				136	/* PackageCS: { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* "package" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* nsPrefix=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* nsURI=URI : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedPackages+=PackageCS : [value] | [value] */,
			(0 << 16) | 3	/* ownedClasses+=ClassCS : [value] | [half-new-line, value, half-new-line] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[72] = createParserRuleValue(72, "ParameterCS", -1,
			createSerializationRules(
				137	/* ParameterCS: { name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "{" { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] "}" }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS* : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[73] = createParserRuleValue(73, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS: { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[74] = createParserRuleValue(74, "PatternExpCS", -1,
			createSerializationRules(
				65	/* PatternExpCS: { patternVariableName=UnrestrictedName[?] ":" ownedPatternType=TypeExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* patternVariableName=UnrestrictedName? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedPatternType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[75] = createParserRuleValue(75, "PostconditionConstraintCS", -1,
			createSerializationRules(
				138	/* PostconditionConstraintCS: { stereotype="postcondition" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS[?] ";" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* stereotype="postcondition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 6	/* ":" : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[76] = createParserRuleValue(76, "PreconditionConstraintCS", -1,
			createSerializationRules(
				139	/* PreconditionConstraintCS: { stereotype="precondition" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS[?] ";" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* stereotype="precondition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedMessageSpecification=SpecificationCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 6	/* ":" : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSpecification=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[77] = createParserRuleValue(77, "PrefixedLetExpCS", 33 /* LetExpCS|PrefixedLetExpCS */,
			createSerializationRules(
				44	/* LetExpCS: { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS } */,
				66	/* PrefixedLetExpCS: { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PrefixExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* name=UnaryOperatorName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedRight=PrefixedLetExpCS : [value] | [value] */,
			(0 << 16) | 0	/* LetExpCS : [value] | [value] */
		);
		grammarRuleValues[78] = createParserRuleValue(78, "PrefixedPrimaryExpCS", 69 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				15	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				17	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				41	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				42	/* InvalidLiteralExpCS: "invalid" */,
				43	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				46	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				50	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				62	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */,
				63	/* NullLiteralExpCS: "null" */,
				64	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				67	/* PrefixedPrimaryExpCS: { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				70	/* SelfExpCS: "self" */,
				75	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				76	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
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
		grammarRuleValues[79] = createParserRuleValue(79, "PrimaryExpCS", 68 /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				15	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				17	/* CollectionLiteralExpCS: { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" } */,
				41	/* IfExpCS: { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" } */,
				42	/* InvalidLiteralExpCS: "invalid" */,
				43	/* LambdaLiteralExpCS: { "Lambda" "{" ownedExpressionCS=ExpCS "}" } */,
				46	/* MapLiteralExpCS: { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" } */,
				50	/* NameExpCS: { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] } */,
				62	/* NestedExpCS: { "(" ownedExpression=ExpCS ")" } */,
				63	/* NullLiteralExpCS: "null" */,
				64	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				70	/* SelfExpCS: "self" */,
				75	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
				76	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */,
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
		grammarRuleValues[80] = createParserRuleValue(80, "PrimitiveLiteralExpCS", 67 /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
			createSerializationRules(
				15	/* BooleanLiteralExpCS: symbol={'false|true'} */,
				42	/* InvalidLiteralExpCS: "invalid" */,
				63	/* NullLiteralExpCS: "null" */,
				64	/* NumberLiteralExpCS: symbol=NUMBER_LITERAL */,
				75	/* StringLiteralExpCS: segments+=StringLiteral[+] */,
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
		grammarRuleValues[81] = createParserRuleValue(81, "PrimitiveTypeCS", -1,
			createSerializationRules(
				68	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */
			),
			(0 << 16) | 7	/* name=PrimitiveTypeIdentifier : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[82] = new DataTypeRuleValue(82, "PrimitiveTypeIdentifier");
		grammarRuleValues[83] = createParserRuleValue(83, "ReferenceCS", -1,
			createSerializationRules(
				140	/* ReferenceCS: { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				141	/* ReferenceCS: { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */,
				142	/* ReferenceCS: { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				143	/* ReferenceCS: { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */,
				144	/* ReferenceCS: { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				145	/* ReferenceCS: { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="static" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="definition"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="definition" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="static"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "property" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "#" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 7	/* referredOpposite=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypedMultiplicityRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* default=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group+ : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 7	/* qualifiers+="composes" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!composes" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!derived" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!ordered" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="readonly" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!readonly" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="resolve" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!resolve" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!transient" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!unique" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="unsettable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!unsettable" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="volatile" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* qualifiers+="!volatile" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 6	/* ","? : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 7	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "{" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "key" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* referredKeys+=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 7	/* referredKeys+=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "initial" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "derivation" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedDefaultExpressions+=SpecificationCS? : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedImplicitOpposites+=ImplicitOppositeCS : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 7	/* "}" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[84] = createParserRuleValue(84, "RoundBracketedClauseCS", -1,
			createSerializationRules(
				69	/* RoundBracketedClauseCS: { "(" { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ")" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {RoundBracketedClauseCS} : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=NavigatingArgCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)* : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[85] = new DataTypeRuleValue(85, "SIGNED");
		grammarRuleValues[86] = new TerminalRuleValue(86, "SIMPLE_ID");
		grammarRuleValues[87] = new TerminalRuleValue(87, "SINGLE_QUOTED_STRING");
		grammarRuleValues[88] = new TerminalRuleValue(88, "SL_COMMENT");
		grammarRuleValues[89] = createParserRuleValue(89, "SelfExpCS", -1,
			createSerializationRules(
				70	/* SelfExpCS: "self" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SelfExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* "self" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[90] = createParserRuleValue(90, "ShadowPartCS", -1,
			createSerializationRules(
				71	/* ShadowPartCS: ownedInitExpression=StringLiteralExpCS */,
				72	/* ShadowPartCS: { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* referredProperty=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=(ExpCS|PatternExpCS) : [value] | [value] */,
			(0 << 16) | 0	/* ownedInitExpression=StringLiteralExpCS : [value] | [value] */
		);
		grammarRuleValues[91] = createParserRuleValue(91, "SimplePathNameCS", -1,
			createSerializationRules(
				73	/* SimplePathNameCS: ownedPathElements+=FirstPathElementCS */
			),
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */
		);
		grammarRuleValues[92] = createParserRuleValue(92, "SpecificationCS", -1,
			createSerializationRules(
				146	/* SpecificationCS: exprString=UNQUOTED_STRING */,
				147	/* SpecificationCS: ownedExpression=ExpCS */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* ownedExpression=ExpCS : [value] | [value] */,
			(0 << 16) | 7	/* exprString=UNQUOTED_STRING : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[93] = createParserRuleValue(93, "SquareBracketedClauseCS", -1,
			createSerializationRules(
				74	/* SquareBracketedClauseCS: { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedTerms+=ExpCS : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[94] = new DataTypeRuleValue(94, "StringLiteral");
		grammarRuleValues[95] = createParserRuleValue(95, "StringLiteralExpCS", -1,
			createSerializationRules(
				75	/* StringLiteralExpCS: segments+=StringLiteral[+] */
			),
			(0 << 16) | 2	/* segments+=StringLiteral+ : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[96] = createParserRuleValue(96, "StructuralFeatureCS", 42 /* AttributeCS|ReferenceCS|StructuralFeatureCS */,
			createSerializationRules(
				100	/* AttributeCS: { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				101	/* AttributeCS: { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				102	/* AttributeCS: { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				103	/* AttributeCS: { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				104	/* AttributeCS: { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				105	/* AttributeCS: { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } } */,
				140	/* ReferenceCS: { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				141	/* ReferenceCS: { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */,
				142	/* ReferenceCS: { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				143	/* ReferenceCS: { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */,
				144	/* ReferenceCS: { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" } */,
				145	/* ReferenceCS: { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* AttributeCS : [value] | [value] */,
			(0 << 16) | 0	/* ReferenceCS : [value] | [value] */
		);
		grammarRuleValues[97] = createParserRuleValue(97, "StructuredClassCS", -1,
			createSerializationRules(
				148	/* StructuredClassCS: { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] ";" } */,
				149	/* StructuredClassCS: { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* isAbstract?="abstract"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* "class" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSignature=TemplateSignatureCS? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSuperTypes+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSuperTypes+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* instanceClassName=SINGLE_QUOTED_STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 7	/* isInterface?="interface"? : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 0	/* ownedAnnotations+=AnnotationElementCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedOperations+=OperationCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedProperties+=StructuralFeatureCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedConstraints+=InvariantConstraintCS : [value] | [value] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */
		);
		grammarRuleValues[98] = createParserRuleValue(98, "SysMLCS", -1,
			createSerializationRules(
				150	/* SysMLCS: { "sysml" { "{" { ownedDetails+=DetailCS ";" }[*] "}" } } */,
				151	/* SysMLCS: { "sysml" { ownedDetails+=DetailCS ";" } } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {SysMLCS} : [value] | [value] */,
			(0 << 16) | 7	/* "sysml" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 9	/* "{" : [value] | [soft-space, value, push, soft-new-line] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 0	/* ownedDetails+=DetailCS : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [no-space, value, soft-new-line] */,
			(0 << 16) | 8	/* "}" : [value] | [pop, soft-space, value, soft-new-line] */
		);
		grammarRuleValues[99] = createParserRuleValue(99, "TemplateBindingCS", -1,
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
		grammarRuleValues[100] = createParserRuleValue(100, "TemplateParameterSubstitutionCS", -1,
			createSerializationRules(
				12	/* TemplateParameterSubstitutionCS: ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[101] = createParserRuleValue(101, "TemplateSignatureCS", -1,
			createSerializationRules(
				152	/* TemplateSignatureCS: { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */,
				153	/* TemplateSignatureCS: { "<" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ">" } */
			),
			(0 << 16) | 2	/* Alternatives : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "<" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 7	/* ">" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[102] = createParserRuleValue(102, "TopLevelCS", -1,
			createSerializationRules(
				154	/* TopLevelCS: { { "module" }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {TopLevelCS} : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* "module" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* UnrestrictedName : [value] | [value] */,
			(0 << 16) | 0	/* ownedImports+=ImportCS* : [value] | [value] */,
			(0 << 16) | 0	/* ownedPackages+=PackageCS* : [value] | [value] */
		);
		grammarRuleValues[103] = createParserRuleValue(103, "TupleLiteralExpCS", -1,
			createSerializationRules(
				76	/* TupleLiteralExpCS: { "Tuple" "{" ownedParts+=TupleLiteralPartCS { "," ownedParts+=TupleLiteralPartCS }[*] "}" } */
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
		grammarRuleValues[104] = createParserRuleValue(104, "TupleLiteralPartCS", -1,
			createSerializationRules(
				77	/* TupleLiteralPartCS: { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */,
			(0 << 16) | 7	/* "=" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedInitExpression=ExpCS : [value] | [value] */
		);
		grammarRuleValues[105] = createParserRuleValue(105, "TuplePartCS", -1,
			createSerializationRules(
				78	/* TuplePartCS: { name=UnrestrictedName ":" ownedType=TypeExpCS } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 7	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 7	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedType=TypeExpCS : [value] | [value] */
		);
		grammarRuleValues[106] = createParserRuleValue(106, "TupleTypeCS", -1,
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
		grammarRuleValues[107] = createParserRuleValue(107, "TypeExpCS", 57 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
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
		grammarRuleValues[108] = createParserRuleValue(108, "TypeExpWithoutMultiplicityCS", 56 /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
			createSerializationRules(
				20	/* CollectionPatternCS: { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } */,
				21	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				48	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				68	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				79	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				91	/* TypeNameExpCS: { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeNameExpCS : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionPatternCS : [value] | [value] */
		);
		grammarRuleValues[109] = new DataTypeRuleValue(109, "TypeIdentifier");
		grammarRuleValues[110] = createParserRuleValue(110, "TypeLiteralCS", 53 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */,
			createSerializationRules(
				21	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				48	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				68	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				79	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* PrimitiveTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* CollectionTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* MapTypeCS : [value] | [value] */,
			(0 << 16) | 0	/* TupleTypeCS : [value] | [value] */
		);
		grammarRuleValues[111] = createParserRuleValue(111, "TypeLiteralExpCS", -1,
			createSerializationRules(
				86	/* TypeLiteralExpCS: ownedType=TypeLiteralWithMultiplicityCS */
			),
			(0 << 16) | 2	/* ownedType=TypeLiteralWithMultiplicityCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[112] = createParserRuleValue(112, "TypeLiteralWithMultiplicityCS", 55 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */,
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
		grammarRuleValues[113] = createParserRuleValue(113, "TypeNameExpCS", -1,
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
		grammarRuleValues[114] = createParserRuleValue(114, "TypeParameterCS", -1,
			createSerializationRules(
				13	/* TypeParameterCS: { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
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
		grammarRuleValues[115] = createParserRuleValue(115, "TypeRefCS", 75 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				14	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */,
				21	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				48	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				68	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				79	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				162	/* TypedTypeRefCS: ownedPathName=PathNameCS */,
				163	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } } */,
				164	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[116] = createParserRuleValue(116, "TypedMultiplicityRefCS", 63 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				155	/* TypedMultiplicityRefCS: { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				156	/* TypedMultiplicityRefCS: { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				157	/* TypedMultiplicityRefCS: { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
				158	/* TypedMultiplicityRefCS: { { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } } ownedMultiplicity=MultiplicityCS[?] } */,
				159	/* TypedMultiplicityRefCS: { { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } } ownedMultiplicity=MultiplicityCS[?] } */,
				160	/* TypedMultiplicityRefCS: { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */,
				161	/* TypedMultiplicityRefCS: { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[117] = createParserRuleValue(117, "TypedRefCS", 62 /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				21	/* CollectionTypeCS: { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } */,
				48	/* MapTypeCS: { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } */,
				68	/* PrimitiveTypeCS: name=PrimitiveTypeIdentifier */,
				79	/* TupleTypeCS: { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } */,
				162	/* TypedTypeRefCS: ownedPathName=PathNameCS */,
				163	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } } */,
				164	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypeLiteralCS : [value] | [value] */,
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[118] = createParserRuleValue(118, "TypedTypeRefCS", -1,
			createSerializationRules(
				162	/* TypedTypeRefCS: ownedPathName=PathNameCS */,
				163	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } } */,
				164	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 7	/* "<" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 7	/* ">" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[119] = new TerminalRuleValue(119, "UNQUOTED_STRING");
		grammarRuleValues[120] = new DataTypeRuleValue(120, "UPPER");
		grammarRuleValues[121] = new DataTypeRuleValue(121, "URI");
		grammarRuleValues[122] = createParserRuleValue(122, "URIFirstPathElementCS", -1,
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
		grammarRuleValues[123] = createParserRuleValue(123, "URIPathNameCS", -1,
			createSerializationRules(
				94	/* URIPathNameCS: { ownedPathElements+=URIFirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=URIFirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[124] = new DataTypeRuleValue(124, "UnaryOperatorName");
		grammarRuleValues[125] = createParserRuleValue(125, "UnlimitedNaturalLiteralExpCS", -1,
			createSerializationRules(
				95	/* UnlimitedNaturalLiteralExpCS: "*" */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 0	/* {UnlimitedNaturalLiteralExpCS} : [value] | [value] */,
			(0 << 16) | 7	/* "*" : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[126] = new DataTypeRuleValue(126, "UnreservedName");
		grammarRuleValues[127] = new DataTypeRuleValue(127, "UnrestrictedName");
		grammarRuleValues[128] = new TerminalRuleValue(128, "WS");
		grammarRuleValues[129] = createParserRuleValue(129, "WildcardTypeRefCS", -1,
			createSerializationRules(
				14	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
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
		// AnnotationElementCS
		grammarRuleVectors[0] = new GrammarRuleVector(0x4L);
		// ClassCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x40L);
		// CoIteratorVariableCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x80L);
		// CollectionLiteralPartCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x200L);
		// CollectionTypeCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x800L);
		// CurlyBracketedClauseCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x2000L);
		// DetailCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x10000L);
		// ElseIfThenExpCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x100000L);
		// EnumerationLiteralCS
		grammarRuleVectors[8] = new GrammarRuleVector(0x400000L);
		// ExpCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x40000000L);
		// FirstPathElementCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x80000000L);
		// ImplicitOppositeCS
		grammarRuleVectors[11] = new GrammarRuleVector(0x2000000000L);
		// ImportCS
		grammarRuleVectors[12] = new GrammarRuleVector(0x4000000000L);
		// InvariantConstraintCS
		grammarRuleVectors[13] = new GrammarRuleVector(0x20000000000L);
		// LetVariableCS
		grammarRuleVectors[14] = new GrammarRuleVector(0x400000000000L);
		// MapLiteralPartCS
		grammarRuleVectors[15] = new GrammarRuleVector(0x4000000000000L);
		// MapTypeCS
		grammarRuleVectors[16] = new GrammarRuleVector(0x8000000000000L);
		// ModelElementCS
		grammarRuleVectors[17] = new GrammarRuleVector(0x20000000000000L);
		// ModelElementRefCS
		grammarRuleVectors[18] = new GrammarRuleVector(0x40000000000000L);
		// MultiplicityCS
		grammarRuleVectors[19] = new GrammarRuleVector(0x100000000000000L);
		// NavigatingArgExpCS
		grammarRuleVectors[20] = new GrammarRuleVector(0x2000000000000000L);
		// NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[21] = new GrammarRuleVector(0xc000000000000000L,0x1L);
		// NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
		grammarRuleVectors[22] = new GrammarRuleVector(0xd000000000000000L,0x1L);
		// FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[23] = new GrammarRuleVector(0x80000000L,0x8L);
		// OperationCS
		grammarRuleVectors[24] = new GrammarRuleVector(0x0L,0x40L);
		// PackageCS
		grammarRuleVectors[25] = new GrammarRuleVector(0x0L,0x80L);
		// ParameterCS
		grammarRuleVectors[26] = new GrammarRuleVector(0x0L,0x100L);
		// PathNameCS
		grammarRuleVectors[27] = new GrammarRuleVector(0x0L,0x200L);
		// PatternExpCS
		grammarRuleVectors[28] = new GrammarRuleVector(0x0L,0x400L);
		// ExpCS|PatternExpCS
		grammarRuleVectors[29] = new GrammarRuleVector(0x40000000L,0x400L);
		// PostconditionConstraintCS
		grammarRuleVectors[30] = new GrammarRuleVector(0x0L,0x800L);
		// PreconditionConstraintCS
		grammarRuleVectors[31] = new GrammarRuleVector(0x0L,0x1000L);
		// PrefixedLetExpCS
		grammarRuleVectors[32] = new GrammarRuleVector(0x0L,0x2000L);
		// LetExpCS|PrefixedLetExpCS
		grammarRuleVectors[33] = new GrammarRuleVector(0x200000000000L,0x2000L);
		// PrefixedPrimaryExpCS
		grammarRuleVectors[34] = new GrammarRuleVector(0x0L,0x4000L);
		// RoundBracketedClauseCS
		grammarRuleVectors[35] = new GrammarRuleVector(0x0L,0x100000L);
		// ML_SINGLE_QUOTED_STRING|SINGLE_QUOTED_STRING
		grammarRuleVectors[36] = new GrammarRuleVector(0x1000000000000L,0x800000L);
		// ShadowPartCS
		grammarRuleVectors[37] = new GrammarRuleVector(0x0L,0x4000000L);
		// SpecificationCS
		grammarRuleVectors[38] = new GrammarRuleVector(0x0L,0x10000000L);
		// SquareBracketedClauseCS
		grammarRuleVectors[39] = new GrammarRuleVector(0x0L,0x20000000L);
		// StringLiteralExpCS
		grammarRuleVectors[40] = new GrammarRuleVector(0x0L,0x80000000L);
		// StructuralFeatureCS
		grammarRuleVectors[41] = new GrammarRuleVector(0x0L,0x100000000L);
		// AttributeCS|ReferenceCS|StructuralFeatureCS
		grammarRuleVectors[42] = new GrammarRuleVector(0x8L,0x100080000L);
		// ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS
		grammarRuleVectors[43] = new GrammarRuleVector(0x208040L,0x200000000L);
		// AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS
		grammarRuleVectors[44] = new GrammarRuleVector(0x20000000608048L,0x3000800c0L);
		// AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS
		grammarRuleVectors[45] = new GrammarRuleVector(0x20006L,0x400000000L);
		// TemplateBindingCS
		grammarRuleVectors[46] = new GrammarRuleVector(0x0L,0x800000000L);
		// TemplateParameterSubstitutionCS
		grammarRuleVectors[47] = new GrammarRuleVector(0x0L,0x1000000000L);
		// TemplateSignatureCS
		grammarRuleVectors[48] = new GrammarRuleVector(0x0L,0x2000000000L);
		// TupleLiteralPartCS
		grammarRuleVectors[49] = new GrammarRuleVector(0x0L,0x10000000000L);
		// TuplePartCS
		grammarRuleVectors[50] = new GrammarRuleVector(0x0L,0x20000000000L);
		// TypeExpCS
		grammarRuleVectors[51] = new GrammarRuleVector(0x0L,0x80000000000L);
		// TypeExpWithoutMultiplicityCS
		grammarRuleVectors[52] = new GrammarRuleVector(0x0L,0x100000000000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
		grammarRuleVectors[53] = new GrammarRuleVector(0x8000000000800L,0x440000020000L);
		// TypeLiteralWithMultiplicityCS
		grammarRuleVectors[54] = new GrammarRuleVector(0x0L,0x1000000000000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
		grammarRuleVectors[55] = new GrammarRuleVector(0x8000000000800L,0x1440000020000L);
		// CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[56] = new GrammarRuleVector(0x8000000000c00L,0x2540000020000L);
		// CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
		grammarRuleVectors[57] = new GrammarRuleVector(0x8000000000c00L,0x25c0000020000L);
		// TypeParameterCS
		grammarRuleVectors[58] = new GrammarRuleVector(0x0L,0x4000000000000L);
		// TypeRefCS
		grammarRuleVectors[59] = new GrammarRuleVector(0x0L,0x8000000000000L);
		// TypedMultiplicityRefCS
		grammarRuleVectors[60] = new GrammarRuleVector(0x0L,0x10000000000000L);
		// TypedRefCS
		grammarRuleVectors[61] = new GrammarRuleVector(0x0L,0x20000000000000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[62] = new GrammarRuleVector(0x8000000000800L,0x60440000020000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[63] = new GrammarRuleVector(0x8000000000800L,0x70440000020000L);
		// NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[64] = new GrammarRuleVector(0x0L,0x400000000000008L);
		// FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
		grammarRuleVectors[65] = new GrammarRuleVector(0x80000000L,0x400000000000008L);
		// URIPathNameCS
		grammarRuleVectors[66] = new GrammarRuleVector(0x0L,0x800000000000000L);
		// BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[67] = new GrammarRuleVector(0x10000000020L,0x2000000080010030L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[68] = new GrammarRuleVector(0x802111000000120L,0x2000808082018034L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[69] = new GrammarRuleVector(0x802111000000120L,0x200080808201c034L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[70] = new GrammarRuleVector(0x802311000000120L,0x200080808201e034L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[71] = new GrammarRuleVector(0x802311040000120L,0x200080808201e034L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[72] = new GrammarRuleVector(0x2802311040000120L,0x200080808201e034L);
		// BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
		grammarRuleVectors[73] = new GrammarRuleVector(0x802311040000120L,0x200080808201e434L);
		// SINGLE_QUOTED_STRING|UnrestrictedName
		grammarRuleVectors[74] = new GrammarRuleVector(0x0L,0x8000000000800000L);
		// CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[75] = new GrammarRuleVector(0x8000000000800L,0x68440000020000L,0x2L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(135);
		// assert (|AnnotationElementCS::ownedDetails| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(136);
		// assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(138);
		// assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(141);
		// assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(142);
		// assert (|CollectionPatternCS::ownedType| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(144);
		// assert (|CollectionTypeCS::name| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(145);
		// assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(146);
		// assert (|ConstraintCS::stereotype.'postcondition'| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(147);
		// assert (|ConstraintCS::stereotype.'precondition'| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(148);
		// assert (|ContextCS::ownedExpression| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(149);
		// assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(154);
		// assert (|IfExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(155);
		// assert (|IfExpCS::ownedElseExpression| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(156);
		// assert (|IfExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(157);
		// assert (|IfThenExpCS::ownedCondition| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(158);
		// assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(159);
		// assert (|ImportCS::ownedPathName| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(160);
		// assert (|InfixExpCS::ownedLeft| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(161);
		// assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
		serializationMatchSteps[19] = createMatchStep_Assert(162);
		// assert (|LetExpCS::ownedInExpression| - 1) == 0
		serializationMatchSteps[20] = createMatchStep_Assert(163);
		// assert (|MapLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[21] = createMatchStep_Assert(167);
		// assert (|MapLiteralPartCS::ownedKey| - 1) == 0
		serializationMatchSteps[22] = createMatchStep_Assert(168);
		// assert (|MapLiteralPartCS::ownedValue| - 1) == 0
		serializationMatchSteps[23] = createMatchStep_Assert(169);
		// assert (|MapTypeCS::name.'Map'| - 1) == 0
		serializationMatchSteps[24] = createMatchStep_Assert(170);
		// assert (|MapTypeCS::ownedKeyType| - V0) == 0
		serializationMatchSteps[25] = createMatchStep_Assert(171);
		// assert (|ModelElementRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[26] = createMatchStep_Assert(173);
		// assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[27] = createMatchStep_Assert(174);
		// assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[28] = createMatchStep_Assert(175);
		// assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[29] = createMatchStep_Assert(176);
		// assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
		serializationMatchSteps[30] = createMatchStep_Assert(177);
		// assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[31] = createMatchStep_Assert(178);
		// assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
		serializationMatchSteps[32] = createMatchStep_Assert(179);
		// assert (|NavigatingArgCS::ownedType| - 1) == 0
		serializationMatchSteps[33] = createMatchStep_Assert(180);
		// assert (|NavigatingArgCS::prefix.','| - 1) == 0
		serializationMatchSteps[34] = createMatchStep_Assert(181);
		// assert (|NavigatingArgCS::prefix.';'| - 1) == 0
		serializationMatchSteps[35] = createMatchStep_Assert(182);
		// assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
		serializationMatchSteps[36] = createMatchStep_Assert(183);
		// assert (|NestedExpCS::ownedExpression| - 1) == 0
		serializationMatchSteps[37] = createMatchStep_Assert(184);
		// assert (|NumberLiteralExpCS::symbol| - 1) == 0
		serializationMatchSteps[38] = createMatchStep_Assert(185);
		// assert (|OperatorExpCS::ownedRight| - 1) == 0
		serializationMatchSteps[39] = createMatchStep_Assert(191);
		// assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[40] = createMatchStep_Assert(192);
		// assert (|PathNameCS::ownedPathElements| - 1) == 0
		serializationMatchSteps[41] = createMatchStep_Assert(193);
		// assert (|PatternExpCS::ownedPatternType| - 1) == 0
		serializationMatchSteps[42] = createMatchStep_Assert(194);
		// assert (|PrimitiveTypeRefCS::name| - 1) == 0
		serializationMatchSteps[43] = createMatchStep_Assert(195);
		// assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[44] = createMatchStep_Assert(200);
		// assert (|ShadowPartCS::referredProperty| - 1) == 0
		serializationMatchSteps[45] = createMatchStep_Assert(201);
		// assert (|SpecificationCS::exprString| - 1) == 0
		serializationMatchSteps[46] = createMatchStep_Assert(202);
		// assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[47] = createMatchStep_Assert(209);
		// assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
		serializationMatchSteps[48] = createMatchStep_Assert(212);
		// assert (|TypeLiteralExpCS::ownedType| - 1) == 0
		serializationMatchSteps[49] = createMatchStep_Assert(215);
		// assert (|TypeNameExpCS::ownedPathName| - 1) == 0
		serializationMatchSteps[50] = createMatchStep_Assert(216);
		// assert (|TypedElementCS::ownedType| - 1) == 0
		serializationMatchSteps[51] = createMatchStep_Assert(219);
		// assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0
		serializationMatchSteps[52] = createMatchStep_Assert(224);
		// assert (|TypedElementCS::qualifiers.'static'| - 1) == 0
		serializationMatchSteps[53] = createMatchStep_Assert(225);
		// assert (|TypedTypeRefCS::ownedBinding| - 1) == 0
		serializationMatchSteps[54] = createMatchStep_Assert(226);
		// assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[55] = createMatchStep_Assert(227);
		// assert (|VariableCS::ownedInitExpression| - 1) == 0
		serializationMatchSteps[56] = createMatchStep_Assert(228);
		// assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[57] = createMatchStep_Assign(0, 140);
		// assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchSteps[58] = createMatchStep_Assign(0, 151);
		// assign V0 = (|LetExpCS::ownedVariables| - 1)
		serializationMatchSteps[59] = createMatchStep_Assign(0, 164);
		// assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchSteps[60] = createMatchStep_Assign(0, 166);
		// assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[61] = createMatchStep_Assign(0, 193);
		// assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
		serializationMatchSteps[62] = createMatchStep_Assign(0, 199);
		// assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchSteps[63] = createMatchStep_Assign(0, 203);
		// assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[64] = createMatchStep_Assign(0, 208);
		// assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[65] = createMatchStep_Assign(0, 210);
		// assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[66] = createMatchStep_Assign(0, 211);
		// assign V0 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[67] = createMatchStep_Assign(0, 214);
		// assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[68] = createMatchStep_Assign(0, 218);
		// assign V0 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
		serializationMatchSteps[69] = createMatchStep_Assign(0, 223);
		// assign V0 = 0
		serializationMatchSteps[70] = createMatchStep_Assign(0, 0);
		// assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchSteps[71] = createMatchStep_Assign(0, 7);
		// assign V0 = |AnnotationElementCS::ownedDetails|
		serializationMatchSteps[72] = createMatchStep_Assign(0, 10);
		// assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchSteps[73] = createMatchStep_Assign(0, 17);
		// assign V0 = |CollectionPatternCS::restVariableName|
		serializationMatchSteps[74] = createMatchStep_Assign(0, 20);
		// assign V0 = |CollectionTypeCS::ownedType|
		serializationMatchSteps[75] = createMatchStep_Assign(0, 23);
		// assign V0 = |DataTypeCS::isPrimitive.'primitive'|
		serializationMatchSteps[76] = createMatchStep_Assign(0, 31);
		// assign V0 = |DetailCS::values|
		serializationMatchSteps[77] = createMatchStep_Assign(0, 33);
		// assign V0 = |DocumentationCS::value|
		serializationMatchSteps[78] = createMatchStep_Assign(0, 34);
		// assign V0 = |EnumerationLiteralCS::literal|
		serializationMatchSteps[79] = createMatchStep_Assign(0, 37);
		// assign V0 = |IfExpCS::ownedIfThenExpressions|
		serializationMatchSteps[80] = createMatchStep_Assign(0, 42);
		// assign V0 = |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchSteps[81] = createMatchStep_Assign(0, 52);
		// assign V0 = |MapTypeCS::ownedValueType|
		serializationMatchSteps[82] = createMatchStep_Assign(0, 59);
		// assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[83] = createMatchStep_Assign(0, 63);
		// assign V0 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[84] = createMatchStep_Assign(0, 64);
		// assign V0 = |NamedElementCS::name|
		serializationMatchSteps[85] = createMatchStep_Assign(0, 66);
		// assign V0 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[86] = createMatchStep_Assign(0, 67);
		// assign V0 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[87] = createMatchStep_Assign(0, 68);
		// assign V0 = |NavigatingArgCS::ownedType|
		serializationMatchSteps[88] = createMatchStep_Assign(0, 70);
		// assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'|
		serializationMatchSteps[89] = createMatchStep_Assign(0, 76);
		// assign V0 = |PackageCS::nsPrefix|
		serializationMatchSteps[90] = createMatchStep_Assign(0, 83);
		// assign V0 = |PatternExpCS::patternVariableName|
		serializationMatchSteps[91] = createMatchStep_Assign(0, 90);
		// assign V0 = |ReferenceCS::referredOpposite|
		serializationMatchSteps[92] = createMatchStep_Assign(0, 94);
		// assign V0 = |StringLiteralExpCS::segments|
		serializationMatchSteps[93] = createMatchStep_Assign(0, 101);
		// assign V0 = |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchSteps[94] = createMatchStep_Assign(0, 104);
		// assign V0 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[95] = createMatchStep_Assign(0, 113);
		// assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[96] = createMatchStep_Assign(0, 118);
		// assign V0 = |TypedElementCS::ownedType|
		serializationMatchSteps[97] = createMatchStep_Assign(0, 122);
		// assign V0 = |TypedElementCS::qualifiers.'definition'|
		serializationMatchSteps[98] = createMatchStep_Assign(0, 127);
		// assign V0 = |TypedElementCS::qualifiers.'static'|
		serializationMatchSteps[99] = createMatchStep_Assign(0, 128);
		// assign V0 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[100] = createMatchStep_Assign(0, 129);
		// assign V0 = |VariableCS::ownedType|
		serializationMatchSteps[101] = createMatchStep_Assign(0, 133);
		// assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[102] = createMatchStep_Assign(0, 134);
		// assign V1 = (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchSteps[103] = createMatchStep_Assign(1, 137);
		// assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[104] = createMatchStep_Assign(1, 139);
		// assign V1 = (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchSteps[105] = createMatchStep_Assign(1, 143);
		// assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchSteps[106] = createMatchStep_Assign(1, 150);
		// assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchSteps[107] = createMatchStep_Assign(1, 165);
		// assign V1 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[108] = createMatchStep_Assign(1, 190);
		// assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
		serializationMatchSteps[109] = createMatchStep_Assign(1, 198);
		// assign V1 = (|TupleTypeCS::ownedParts| > 0)
		serializationMatchSteps[110] = createMatchStep_Assign(1, 214);
		// assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[111] = createMatchStep_Assign(1, 217);
		// assign V1 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
		serializationMatchSteps[112] = createMatchStep_Assign(1, 223);
		// assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchSteps[113] = createMatchStep_Assign(1, 6);
		// assign V1 = |ClassCS::instanceClassName|
		serializationMatchSteps[114] = createMatchStep_Assign(1, 12);
		// assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchSteps[115] = createMatchStep_Assign(1, 22);
		// assign V1 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[116] = createMatchStep_Assign(1, 24);
		// assign V1 = |EnumerationLiteralCS::value|
		serializationMatchSteps[117] = createMatchStep_Assign(1, 38);
		// assign V1 = |ImportCS::isAll.'::*'|
		serializationMatchSteps[118] = createMatchStep_Assign(1, 46);
		// assign V1 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[119] = createMatchStep_Assign(1, 64);
		// assign V1 = |NamedElementCS::name|
		serializationMatchSteps[120] = createMatchStep_Assign(1, 66);
		// assign V1 = |NavigatingArgCS::ownedCoIterator|
		serializationMatchSteps[121] = createMatchStep_Assign(1, 67);
		// assign V1 = |NavigatingArgCS::ownedInitExpression|
		serializationMatchSteps[122] = createMatchStep_Assign(1, 68);
		// assign V1 = |PackageCS::nsURI|
		serializationMatchSteps[123] = createMatchStep_Assign(1, 84);
		// assign V1 = |ReferenceCS::referredOpposite|
		serializationMatchSteps[124] = createMatchStep_Assign(1, 94);
		// assign V1 = |RootCS::ownedImports|
		serializationMatchSteps[125] = createMatchStep_Assign(1, 95);
		// assign V1 = |StructuralFeatureCS::default|
		serializationMatchSteps[126] = createMatchStep_Assign(1, 102);
		// assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[127] = createMatchStep_Assign(1, 109);
		// assign V1 = |TemplateableElementCS::ownedSignature|
		serializationMatchSteps[128] = createMatchStep_Assign(1, 113);
		// assign V1 = |TypeNameExpCS::ownedPatternGuard|
		serializationMatchSteps[129] = createMatchStep_Assign(1, 120);
		// assign V1 = |TypedElementCS::ownedType|
		serializationMatchSteps[130] = createMatchStep_Assign(1, 122);
		// assign V1 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
		serializationMatchSteps[131] = createMatchStep_Assign(1, 126);
		// assign V1 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[132] = createMatchStep_Assign(1, 129);
		// assign V1 = |VariableCS::ownedType|
		serializationMatchSteps[133] = createMatchStep_Assign(1, 133);
		// assign V10 = (|OperationCS::ownedBodyExpressions| > 0)
		serializationMatchSteps[134] = createMatchStep_Assign(10, 186);
		// assign V10 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
		serializationMatchSteps[135] = createMatchStep_Assign(10, 204);
		// assign V10 = 0
		serializationMatchSteps[136] = createMatchStep_Assign(10, 0);
		// assign V10 = |ClassCS::ownedConstraints|
		serializationMatchSteps[137] = createMatchStep_Assign(10, 13);
		// assign V10 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[138] = createMatchStep_Assign(10, 81);
		// assign V11 = (|OperationCS::ownedBodyExpressions| > 0)
		serializationMatchSteps[139] = createMatchStep_Assign(11, 186);
		// assign V11 = 0
		serializationMatchSteps[140] = createMatchStep_Assign(11, 0);
		// assign V11 = |OperationCS::ownedBodyExpressions|
		serializationMatchSteps[141] = createMatchStep_Assign(11, 77);
		// assign V12 = 0
		serializationMatchSteps[142] = createMatchStep_Assign(12, 0);
		// assign V12 = |OperationCS::ownedBodyExpressions|
		serializationMatchSteps[143] = createMatchStep_Assign(12, 77);
		// assign V12 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[144] = createMatchStep_Assign(12, 80);
		// assign V12 = |ReferenceCS::ownedImplicitOpposites|
		serializationMatchSteps[145] = createMatchStep_Assign(12, 92);
		// assign V13 = |OperationCS::ownedPostconditions|
		serializationMatchSteps[146] = createMatchStep_Assign(13, 80);
		// assign V13 = |ReferenceCS::ownedImplicitOpposites|
		serializationMatchSteps[147] = createMatchStep_Assign(13, 92);
		// assign V2 = (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchSteps[148] = createMatchStep_Assign(2, 136);
		// assign V2 = (|EnumerationCS::isSerializable.'serializable'| > 0)
		serializationMatchSteps[149] = createMatchStep_Assign(2, 153);
		// assign V2 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[150] = createMatchStep_Assign(2, 189);
		// assign V2 = (|OperationCS::ownedParameters| > 0)
		serializationMatchSteps[151] = createMatchStep_Assign(2, 190);
		// assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchSteps[152] = createMatchStep_Assign(2, 207);
		// assign V2 = (|TupleTypeCS::ownedParts| - 1)
		serializationMatchSteps[153] = createMatchStep_Assign(2, 213);
		// assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
		serializationMatchSteps[154] = createMatchStep_Assign(2, 221);
		// assign V2 = 0
		serializationMatchSteps[155] = createMatchStep_Assign(2, 0);
		// assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchSteps[156] = createMatchStep_Assign(2, 4);
		// assign V2 = |ClassCS::instanceClassName|
		serializationMatchSteps[157] = createMatchStep_Assign(2, 12);
		// assign V2 = |ConstraintCS::ownedMessageSpecification|
		serializationMatchSteps[158] = createMatchStep_Assign(2, 24);
		// assign V2 = |ConstraintCS::ownedSpecification|
		serializationMatchSteps[159] = createMatchStep_Assign(2, 25);
		// assign V2 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[160] = createMatchStep_Assign(2, 60);
		// assign V2 = |PackageOwnerCS::ownedPackages|
		serializationMatchSteps[161] = createMatchStep_Assign(2, 86);
		// assign V2 = |StructuralFeatureCS::default|
		serializationMatchSteps[162] = createMatchStep_Assign(2, 102);
		// assign V2 = |TypedElementCS::ownedType|
		serializationMatchSteps[163] = createMatchStep_Assign(2, 122);
		// assign V2 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
		serializationMatchSteps[164] = createMatchStep_Assign(2, 126);
		// assign V2 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[165] = createMatchStep_Assign(2, 129);
		// assign V3 = (|DataTypeCS::isSerializable.'serializable'| > 0)
		serializationMatchSteps[166] = createMatchStep_Assign(3, 152);
		// assign V3 = (|ModelElementCS::ownedAnnotations| > 0)
		serializationMatchSteps[167] = createMatchStep_Assign(3, 172);
		// assign V3 = (|OperationCS::ownedParameters| - 1)
		serializationMatchSteps[168] = createMatchStep_Assign(3, 189);
		// assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchSteps[169] = createMatchStep_Assign(3, 206);
		// assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
		serializationMatchSteps[170] = createMatchStep_Assign(3, 220);
		// assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
		serializationMatchSteps[171] = createMatchStep_Assign(3, 221);
		// assign V3 = 0
		serializationMatchSteps[172] = createMatchStep_Assign(3, 0);
		// assign V3 = |AbstractNameExpCS::isPre.'@'|
		serializationMatchSteps[173] = createMatchStep_Assign(3, 3);
		// assign V3 = |ConstraintCS::ownedSpecification|
		serializationMatchSteps[174] = createMatchStep_Assign(3, 25);
		// assign V3 = |EnumerationCS::isSerializable.'serializable'|
		serializationMatchSteps[175] = createMatchStep_Assign(3, 35);
		// assign V3 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[176] = createMatchStep_Assign(3, 60);
		// assign V3 = |PackageOwnerCS::ownedPackages|
		serializationMatchSteps[177] = createMatchStep_Assign(3, 86);
		// assign V3 = |StructuralFeatureCS::default|
		serializationMatchSteps[178] = createMatchStep_Assign(3, 102);
		// assign V3 = |TypedElementCS::ownedType|
		serializationMatchSteps[179] = createMatchStep_Assign(3, 122);
		// assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
		serializationMatchSteps[180] = createMatchStep_Assign(3, 124);
		// assign V3 = |TypedRefCS::ownedMultiplicity|
		serializationMatchSteps[181] = createMatchStep_Assign(3, 129);
		// assign V4 = (|OperationCS::ownedExceptions| > 0)
		serializationMatchSteps[182] = createMatchStep_Assign(4, 188);
		// assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
		serializationMatchSteps[183] = createMatchStep_Assign(4, 220);
		// assign V4 = |AnnotationCS::ownedContents|
		serializationMatchSteps[184] = createMatchStep_Assign(4, 8);
		// assign V4 = |ClassCS::instanceClassName|
		serializationMatchSteps[185] = createMatchStep_Assign(4, 12);
		// assign V4 = |DataTypeCS::isSerializable.'serializable'|
		serializationMatchSteps[186] = createMatchStep_Assign(4, 32);
		// assign V4 = |EnumerationCS::ownedLiterals|
		serializationMatchSteps[187] = createMatchStep_Assign(4, 36);
		// assign V4 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[188] = createMatchStep_Assign(4, 60);
		// assign V4 = |PackageCS::ownedClasses|
		serializationMatchSteps[189] = createMatchStep_Assign(4, 85);
		// assign V4 = |TypedElementCS::ownedType|
		serializationMatchSteps[190] = createMatchStep_Assign(4, 122);
		// assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
		serializationMatchSteps[191] = createMatchStep_Assign(4, 123);
		// assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
		serializationMatchSteps[192] = createMatchStep_Assign(4, 124);
		// assign V5 = (|OperationCS::ownedExceptions| - 1)
		serializationMatchSteps[193] = createMatchStep_Assign(5, 187);
		// assign V5 = (|OperationCS::ownedExceptions| > 0)
		serializationMatchSteps[194] = createMatchStep_Assign(5, 188);
		// assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0)
		serializationMatchSteps[195] = createMatchStep_Assign(5, 205);
		// assign V5 = |AnnotationCS::ownedReferences|
		serializationMatchSteps[196] = createMatchStep_Assign(5, 9);
		// assign V5 = |ClassCS::ownedConstraints|
		serializationMatchSteps[197] = createMatchStep_Assign(5, 13);
		// assign V5 = |EnumerationCS::ownedLiterals|
		serializationMatchSteps[198] = createMatchStep_Assign(5, 36);
		// assign V5 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[199] = createMatchStep_Assign(5, 60);
		// assign V5 = |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchSteps[200] = createMatchStep_Assign(5, 103);
		// assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
		serializationMatchSteps[201] = createMatchStep_Assign(5, 123);
		// assign V6 = (|OperationCS::ownedExceptions| - 1)
		serializationMatchSteps[202] = createMatchStep_Assign(6, 187);
		// assign V6 = (|ReferenceCS::referredKeys| > 0)
		serializationMatchSteps[203] = createMatchStep_Assign(6, 197);
		// assign V6 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
		serializationMatchSteps[204] = createMatchStep_Assign(6, 204);
		// assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
		serializationMatchSteps[205] = createMatchStep_Assign(6, 222);
		// assign V6 = |ClassCS::ownedConstraints|
		serializationMatchSteps[206] = createMatchStep_Assign(6, 13);
		// assign V6 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[207] = createMatchStep_Assign(6, 60);
		// assign V6 = |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchSteps[208] = createMatchStep_Assign(6, 103);
		// assign V6 = |StructuredClassCS::isInterface.'interface'|
		serializationMatchSteps[209] = createMatchStep_Assign(6, 105);
		// assign V7 = (|ReferenceCS::referredKeys| - 1)
		serializationMatchSteps[210] = createMatchStep_Assign(7, 196);
		// assign V7 = (|ReferenceCS::referredKeys| > 0)
		serializationMatchSteps[211] = createMatchStep_Assign(7, 197);
		// assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
		serializationMatchSteps[212] = createMatchStep_Assign(7, 204);
		// assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
		serializationMatchSteps[213] = createMatchStep_Assign(7, 222);
		// assign V7 = 0
		serializationMatchSteps[214] = createMatchStep_Assign(7, 0);
		// assign V7 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[215] = createMatchStep_Assign(7, 60);
		// assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
		serializationMatchSteps[216] = createMatchStep_Assign(7, 125);
		// assign V8 = (|ReferenceCS::referredKeys| - 1)
		serializationMatchSteps[217] = createMatchStep_Assign(8, 196);
		// assign V8 = 0
		serializationMatchSteps[218] = createMatchStep_Assign(8, 0);
		// assign V8 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[219] = createMatchStep_Assign(8, 60);
		// assign V8 = |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchSteps[220] = createMatchStep_Assign(8, 103);
		// assign V8 = |StructuredClassCS::ownedOperations|
		serializationMatchSteps[221] = createMatchStep_Assign(8, 106);
		// assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
		serializationMatchSteps[222] = createMatchStep_Assign(8, 125);
		// assign V9 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
		serializationMatchSteps[223] = createMatchStep_Assign(9, 204);
		// assign V9 = 0
		serializationMatchSteps[224] = createMatchStep_Assign(9, 0);
		// assign V9 = |ModelElementCS::ownedAnnotations|
		serializationMatchSteps[225] = createMatchStep_Assign(9, 60);
		// assign V9 = |OperationCS::ownedPreconditions|
		serializationMatchSteps[226] = createMatchStep_Assign(9, 81);
		// assign V9 = |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchSteps[227] = createMatchStep_Assign(9, 103);
		// assign V9 = |StructuredClassCS::ownedProperties|
		serializationMatchSteps[228] = createMatchStep_Assign(9, 107);
		// check-rule basecs::AnnotationCS.ownedContents : 53
		serializationMatchSteps[229] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, 17/*ModelElementCS*/);
		// check-rule basecs::AnnotationCS.ownedReferences : 54
		serializationMatchSteps[230] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, 18/*ModelElementRefCS*/);
		// check-rule basecs::AnnotationElementCS.ownedDetails : 16
		serializationMatchSteps[231] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 6/*DetailCS*/);
		// check-rule basecs::ClassCS.ownedConstraints : 41
		serializationMatchSteps[232] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 13/*InvariantConstraintCS*/);
		// check-rule basecs::ConstraintCS.ownedMessageSpecification : 92
		serializationMatchSteps[233] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 38/*SpecificationCS*/);
		// check-rule basecs::ConstraintCS.ownedSpecification : 92
		serializationMatchSteps[234] = createMatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 38/*SpecificationCS*/);
		// check-rule basecs::EnumerationCS.ownedLiterals : 22
		serializationMatchSteps[235] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 8/*EnumerationLiteralCS*/);
		// check-rule basecs::ImportCS.ownedPathName : 123
		serializationMatchSteps[236] = createMatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 66/*URIPathNameCS*/);
		// check-rule basecs::ModelElementCS.ownedAnnotations : 2
		serializationMatchSteps[237] = createMatchStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 0/*AnnotationElementCS*/);
		// check-rule basecs::ModelElementRefCS.ownedPathName : 73
		serializationMatchSteps[238] = createMatchStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, 27/*PathNameCS*/);
		// check-rule basecs::OperationCS.ownedBodyExpressions : 92
		serializationMatchSteps[239] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 38/*SpecificationCS*/);
		// check-rule basecs::OperationCS.ownedExceptions : 117
		serializationMatchSteps[240] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 61/*TypedRefCS*/);
		// check-rule basecs::OperationCS.ownedParameters : 72
		serializationMatchSteps[241] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 26/*ParameterCS*/);
		// check-rule basecs::OperationCS.ownedPostconditions : 75
		serializationMatchSteps[242] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 30/*PostconditionConstraintCS*/);
		// check-rule basecs::OperationCS.ownedPreconditions : 76
		serializationMatchSteps[243] = createMatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 31/*PreconditionConstraintCS*/);
		// check-rule basecs::PackageCS.ownedClasses : 6
		serializationMatchSteps[244] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 1/*ClassCS*/);
		// check-rule basecs::PackageOwnerCS.ownedPackages : 71
		serializationMatchSteps[245] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 25/*PackageCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 31
		serializationMatchSteps[246] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 10/*FirstPathElementCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 31|67
		serializationMatchSteps[247] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 23/*FirstPathElementCS|NextPathElementCS*/);
		// check-rule basecs::PathNameCS.ownedPathElements : 67|122
		serializationMatchSteps[248] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 64/*NextPathElementCS|URIFirstPathElementCS*/);
		// check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37
		serializationMatchSteps[249] = createMatchStep_RuleCheck(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, 11/*ImplicitOppositeCS*/);
		// check-rule basecs::RootCS.ownedImports : 38
		serializationMatchSteps[250] = createMatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 12/*ImportCS*/);
		// check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92
		serializationMatchSteps[251] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 38/*SpecificationCS*/);
		// check-rule basecs::StructuredClassCS.ownedOperations : 70
		serializationMatchSteps[252] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 24/*OperationCS*/);
		// check-rule basecs::StructuredClassCS.ownedProperties : 96
		serializationMatchSteps[253] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 41/*StructuralFeatureCS*/);
		// check-rule basecs::StructuredClassCS.ownedSuperTypes : 117
		serializationMatchSteps[254] = createMatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 61/*TypedRefCS*/);
		// check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56
		serializationMatchSteps[255] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 19/*MultiplicityCS*/);
		// check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100
		serializationMatchSteps[256] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 47/*TemplateParameterSubstitutionCS*/);
		// check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115
		serializationMatchSteps[257] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 59/*TypeRefCS*/);
		// check-rule basecs::TemplateSignatureCS.ownedParameters : 114
		serializationMatchSteps[258] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 58/*TypeParameterCS*/);
		// check-rule basecs::TemplateableElementCS.ownedSignature : 101
		serializationMatchSteps[259] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 48/*TemplateSignatureCS*/);
		// check-rule basecs::TupleTypeCS.ownedParts : 105
		serializationMatchSteps[260] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 50/*TuplePartCS*/);
		// check-rule basecs::TypeParameterCS.ownedExtends : 117
		serializationMatchSteps[261] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 61/*TypedRefCS*/);
		// check-rule basecs::TypedElementCS.ownedType : 107
		serializationMatchSteps[262] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 51/*TypeExpCS*/);
		// check-rule basecs::TypedElementCS.ownedType : 116
		serializationMatchSteps[263] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 60/*TypedMultiplicityRefCS*/);
		// check-rule basecs::TypedRefCS.ownedMultiplicity : 56
		serializationMatchSteps[264] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 19/*MultiplicityCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedBinding : 99
		serializationMatchSteps[265] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 46/*TemplateBindingCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedPathName : 73
		serializationMatchSteps[266] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 27/*PathNameCS*/);
		// check-rule basecs::WildcardTypeRefCS.ownedExtends : 117
		serializationMatchSteps[267] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 61/*TypedRefCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13
		serializationMatchSteps[268] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/*CurlyBracketedClauseCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73
		serializationMatchSteps[269] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 27/*PathNameCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84
		serializationMatchSteps[270] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 35/*RoundBracketedClauseCS*/);
		// check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93
		serializationMatchSteps[271] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 39/*SquareBracketedClauseCS*/);
		// check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9
		serializationMatchSteps[272] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 3/*CollectionLiteralPartCS*/);
		// check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11
		serializationMatchSteps[273] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 4/*CollectionTypeCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30
		serializationMatchSteps[274] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74
		serializationMatchSteps[275] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 28/*PatternExpCS*/);
		// check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30
		serializationMatchSteps[276] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74
		serializationMatchSteps[277] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 28/*PatternExpCS*/);
		// check-rule essentialoclcs::CollectionPatternCS.ownedType : 11
		serializationMatchSteps[278] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 4/*CollectionTypeCS*/);
		// check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56
		serializationMatchSteps[279] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 19/*MultiplicityCS*/);
		// check-rule essentialoclcs::CollectionTypeCS.ownedType : 108
		serializationMatchSteps[280] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 52/*TypeExpWithoutMultiplicityCS*/);
		// check-rule essentialoclcs::ContextCS.ownedExpression : 30
		serializationMatchSteps[281] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90
		serializationMatchSteps[282] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 37/*ShadowPartCS*/);
		// check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30
		serializationMatchSteps[283] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74
		serializationMatchSteps[284] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, 29/*ExpCS|PatternExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30
		serializationMatchSteps[285] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
		serializationMatchSteps[286] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 7/*ElseIfThenExpCS*/);
		// check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30
		serializationMatchSteps[287] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30
		serializationMatchSteps[288] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 9/*ExpCS*/);
		// check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30
		serializationMatchSteps[289] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::InfixExpCS.ownedLeft : 78
		serializationMatchSteps[290] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 34/*PrefixedPrimaryExpCS*/);
		// check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30
		serializationMatchSteps[291] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 9/*ExpCS*/);
		// check-rule essentialoclcs::LetExpCS.ownedInExpression : 30
		serializationMatchSteps[292] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::LetExpCS.ownedVariables : 46
		serializationMatchSteps[293] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 14/*LetVariableCS*/);
		// check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84
		serializationMatchSteps[294] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 35/*RoundBracketedClauseCS*/);
		// check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50
		serializationMatchSteps[295] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 15/*MapLiteralPartCS*/);
		// check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51
		serializationMatchSteps[296] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 16/*MapTypeCS*/);
		// check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30
		serializationMatchSteps[297] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 9/*ExpCS*/);
		// check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30
		serializationMatchSteps[298] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 9/*ExpCS*/);
		// check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107
		serializationMatchSteps[299] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 51/*TypeExpCS*/);
		// check-rule essentialoclcs::MapTypeCS.ownedValueType : 107
		serializationMatchSteps[300] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 51/*TypeExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7
		serializationMatchSteps[301] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 2/*CoIteratorVariableCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30
		serializationMatchSteps[302] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61
		serializationMatchSteps[303] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 20/*NavigatingArgExpCS*/);
		// check-rule essentialoclcs::NavigatingArgCS.ownedType : 107
		serializationMatchSteps[304] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 51/*TypeExpCS*/);
		// check-rule essentialoclcs::NestedExpCS.ownedExpression : 30
		serializationMatchSteps[305] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 30
		serializationMatchSteps[306] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 9/*ExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 77
		serializationMatchSteps[307] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 32/*PrefixedLetExpCS*/);
		// check-rule essentialoclcs::OperatorExpCS.ownedRight : 78
		serializationMatchSteps[308] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 34/*PrefixedPrimaryExpCS*/);
		// check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107
		serializationMatchSteps[309] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 51/*TypeExpCS*/);
		// check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64
		serializationMatchSteps[310] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 22/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		// check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74
		serializationMatchSteps[311] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 29/*ExpCS|PatternExpCS*/);
		// check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95
		serializationMatchSteps[312] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 40/*StringLiteralExpCS*/);
		// check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30
		serializationMatchSteps[313] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 9/*ExpCS*/);
		// check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104
		serializationMatchSteps[314] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 49/*TupleLiteralPartCS*/);
		// check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112
		serializationMatchSteps[315] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 54/*TypeLiteralWithMultiplicityCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13
		serializationMatchSteps[316] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 5/*CurlyBracketedClauseCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73
		serializationMatchSteps[317] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 27/*PathNameCS*/);
		// check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30
		serializationMatchSteps[318] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 9/*ExpCS*/);
		// check-rule essentialoclcs::VariableCS.ownedInitExpression : 30
		serializationMatchSteps[319] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 9/*ExpCS*/);
		// check-rule essentialoclcs::VariableCS.ownedType : 107
		serializationMatchSteps[320] = createMatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 51/*TypeExpCS*/);
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
		serializationMatchTerms[3] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, 8 /* '@' */);
		// |AbstractNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[4] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// |AbstractNameExpCS::ownedPathName|
		serializationMatchTerms[5] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		// |AbstractNameExpCS::ownedRoundBracketedClause|
		serializationMatchTerms[6] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// |AbstractNameExpCS::ownedSquareBracketedClauses|
		serializationMatchTerms[7] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		// |AnnotationCS::ownedContents|
		serializationMatchTerms[8] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
		// |AnnotationCS::ownedReferences|
		serializationMatchTerms[9] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
		// |AnnotationElementCS::ownedDetails|
		serializationMatchTerms[10] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		// |BooleanLiteralExpCS::symbol.'false|true'|
		serializationMatchTerms[11] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 14 /* 'false|true' */);
		// |ClassCS::instanceClassName|
		serializationMatchTerms[12] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
		// |ClassCS::ownedConstraints|
		serializationMatchTerms[13] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		// |CollectionLiteralExpCS::ownedParts|
		serializationMatchTerms[14] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		// |CollectionLiteralExpCS::ownedType|
		serializationMatchTerms[15] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		// |CollectionLiteralPartCS::ownedExpression|
		serializationMatchTerms[16] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		// |CollectionLiteralPartCS::ownedLastExpression|
		serializationMatchTerms[17] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		// |CollectionPatternCS::ownedParts|
		serializationMatchTerms[18] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		// |CollectionPatternCS::ownedType|
		serializationMatchTerms[19] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		// |CollectionPatternCS::restVariableName|
		serializationMatchTerms[20] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		// |CollectionTypeCS::name|
		serializationMatchTerms[21] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		// |CollectionTypeCS::ownedCollectionMultiplicity|
		serializationMatchTerms[22] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		// |CollectionTypeCS::ownedType|
		serializationMatchTerms[23] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		// |ConstraintCS::ownedMessageSpecification|
		serializationMatchTerms[24] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		// |ConstraintCS::ownedSpecification|
		serializationMatchTerms[25] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		// |ConstraintCS::stereotype.'invariant'|
		serializationMatchTerms[26] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 16 /* 'invariant' */);
		// |ConstraintCS::stereotype.'postcondition'|
		serializationMatchTerms[27] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 17 /* 'postcondition' */);
		// |ConstraintCS::stereotype.'precondition'|
		serializationMatchTerms[28] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 18 /* 'precondition' */);
		// |ContextCS::ownedExpression|
		serializationMatchTerms[29] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		// |CurlyBracketedClauseCS::ownedParts|
		serializationMatchTerms[30] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		// |DataTypeCS::isPrimitive.'primitive'|
		serializationMatchTerms[31] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, 19 /* 'primitive' */);
		// |DataTypeCS::isSerializable.'serializable'|
		serializationMatchTerms[32] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, 20 /* 'serializable' */);
		// |DetailCS::values|
		serializationMatchTerms[33] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DETAIL_CS__VALUES);
		// |DocumentationCS::value|
		serializationMatchTerms[34] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		// |EnumerationCS::isSerializable.'serializable'|
		serializationMatchTerms[35] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, 20 /* 'serializable' */);
		// |EnumerationCS::ownedLiterals|
		serializationMatchTerms[36] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		// |EnumerationLiteralCS::literal|
		serializationMatchTerms[37] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
		// |EnumerationLiteralCS::value|
		serializationMatchTerms[38] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
		// |ExpSpecificationCS::ownedExpression|
		serializationMatchTerms[39] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		// |IfExpCS::ownedCondition|
		serializationMatchTerms[40] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		// |IfExpCS::ownedElseExpression|
		serializationMatchTerms[41] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		// |IfExpCS::ownedIfThenExpressions|
		serializationMatchTerms[42] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		// |IfExpCS::ownedThenExpression|
		serializationMatchTerms[43] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		// |IfThenExpCS::ownedCondition|
		serializationMatchTerms[44] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		// |IfThenExpCS::ownedThenExpression|
		serializationMatchTerms[45] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		// |ImportCS::isAll.'::*'|
		serializationMatchTerms[46] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, 6 /* '::*' */);
		// |ImportCS::ownedPathName|
		serializationMatchTerms[47] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		// |InfixExpCS::ownedLeft|
		serializationMatchTerms[48] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		// |LambdaLiteralExpCS::ownedExpressionCS|
		serializationMatchTerms[49] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		// |LetExpCS::ownedInExpression|
		serializationMatchTerms[50] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		// |LetExpCS::ownedVariables|
		serializationMatchTerms[51] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		// |LetVariableCS::ownedRoundBracketedClause|
		serializationMatchTerms[52] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		// |MapLiteralExpCS::ownedParts|
		serializationMatchTerms[53] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		// |MapLiteralExpCS::ownedType|
		serializationMatchTerms[54] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		// |MapLiteralPartCS::ownedKey|
		serializationMatchTerms[55] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		// |MapLiteralPartCS::ownedValue|
		serializationMatchTerms[56] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		// |MapTypeCS::name.'Map'|
		serializationMatchTerms[57] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 9 /* 'Map' */);
		// |MapTypeCS::ownedKeyType|
		serializationMatchTerms[58] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		// |MapTypeCS::ownedValueType|
		serializationMatchTerms[59] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		// |ModelElementCS::ownedAnnotations|
		serializationMatchTerms[60] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		// |ModelElementRefCS::ownedPathName|
		serializationMatchTerms[61] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
		// |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[62] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[63] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[64] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 23 /* '|1' */);
		// |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[65] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 4 /* '*|+|?' */);
		// |NamedElementCS::name|
		serializationMatchTerms[66] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// |NavigatingArgCS::ownedCoIterator|
		serializationMatchTerms[67] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		// |NavigatingArgCS::ownedInitExpression|
		serializationMatchTerms[68] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		// |NavigatingArgCS::ownedNameExpression|
		serializationMatchTerms[69] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		// |NavigatingArgCS::ownedType|
		serializationMatchTerms[70] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		// |NavigatingArgCS::prefix.','|
		serializationMatchTerms[71] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 5 /* ',' */);
		// |NavigatingArgCS::prefix.';'|
		serializationMatchTerms[72] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 7 /* ';' */);
		// |NavigatingArgCS::prefix.'|'|
		serializationMatchTerms[73] = createSerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, 22 /* '|' */);
		// |NestedExpCS::ownedExpression|
		serializationMatchTerms[74] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		// |NumberLiteralExpCS::symbol|
		serializationMatchTerms[75] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		// |OCLinEcoreConstraintCS::isCallable.'callable'|
		serializationMatchTerms[76] = createSerializationMatchTermEAttributeSize(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, 12 /* 'callable' */);
		// |OperationCS::ownedBodyExpressions|
		serializationMatchTerms[77] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		// |OperationCS::ownedExceptions|
		serializationMatchTerms[78] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		// |OperationCS::ownedParameters|
		serializationMatchTerms[79] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		// |OperationCS::ownedPostconditions|
		serializationMatchTerms[80] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		// |OperationCS::ownedPreconditions|
		serializationMatchTerms[81] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		// |OperatorExpCS::ownedRight|
		serializationMatchTerms[82] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		// |PackageCS::nsPrefix|
		serializationMatchTerms[83] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		// |PackageCS::nsURI|
		serializationMatchTerms[84] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		// |PackageCS::ownedClasses|
		serializationMatchTerms[85] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		// |PackageOwnerCS::ownedPackages|
		serializationMatchTerms[86] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		// |PathElementCS::referredElement|
		serializationMatchTerms[87] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// |PathNameCS::ownedPathElements|
		serializationMatchTerms[88] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// |PatternExpCS::ownedPatternType|
		serializationMatchTerms[89] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		// |PatternExpCS::patternVariableName|
		serializationMatchTerms[90] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		// |PrimitiveTypeRefCS::name|
		serializationMatchTerms[91] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		// |ReferenceCS::ownedImplicitOpposites|
		serializationMatchTerms[92] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
		// |ReferenceCS::referredKeys|
		serializationMatchTerms[93] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		// |ReferenceCS::referredOpposite|
		serializationMatchTerms[94] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
		// |RootCS::ownedImports|
		serializationMatchTerms[95] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		// |RoundBracketedClauseCS::ownedArguments|
		serializationMatchTerms[96] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		// |ShadowPartCS::ownedInitExpression|
		serializationMatchTerms[97] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		// |ShadowPartCS::referredProperty|
		serializationMatchTerms[98] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		// |SpecificationCS::exprString|
		serializationMatchTerms[99] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		// |SquareBracketedClauseCS::ownedTerms|
		serializationMatchTerms[100] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		// |StringLiteralExpCS::segments|
		serializationMatchTerms[101] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		// |StructuralFeatureCS::default|
		serializationMatchTerms[102] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		// |StructuralFeatureCS::ownedDefaultExpressions|
		serializationMatchTerms[103] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		// |StructuredClassCS::isAbstract.'abstract'|
		serializationMatchTerms[104] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, 11 /* 'abstract' */);
		// |StructuredClassCS::isInterface.'interface'|
		serializationMatchTerms[105] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, 15 /* 'interface' */);
		// |StructuredClassCS::ownedOperations|
		serializationMatchTerms[106] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		// |StructuredClassCS::ownedProperties|
		serializationMatchTerms[107] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		// |StructuredClassCS::ownedSuperTypes|
		serializationMatchTerms[108] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		// |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[109] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[110] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[111] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[112] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// |TemplateableElementCS::ownedSignature|
		serializationMatchTerms[113] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		// |TupleLiteralExpCS::ownedParts|
		serializationMatchTerms[114] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		// |TupleTypeCS::name.'Tuple'|
		serializationMatchTerms[115] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 10 /* 'Tuple' */);
		// |TupleTypeCS::ownedParts|
		serializationMatchTerms[116] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		// |TypeLiteralExpCS::ownedType|
		serializationMatchTerms[117] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		// |TypeNameExpCS::ownedCurlyBracketedClause|
		serializationMatchTerms[118] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		// |TypeNameExpCS::ownedPathName|
		serializationMatchTerms[119] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		// |TypeNameExpCS::ownedPatternGuard|
		serializationMatchTerms[120] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		// |TypeParameterCS::ownedExtends|
		serializationMatchTerms[121] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// |TypedElementCS::ownedType|
		serializationMatchTerms[122] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		// |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
		serializationMatchTerms[123] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */);
		// |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
		serializationMatchTerms[124] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */);
		// |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
		serializationMatchTerms[125] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */);
		// |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
		serializationMatchTerms[126] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 3 /* '!ordered|!unique|ordered|unique' */);
		// |TypedElementCS::qualifiers.'definition'|
		serializationMatchTerms[127] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 13 /* 'definition' */);
		// |TypedElementCS::qualifiers.'static'|
		serializationMatchTerms[128] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 21 /* 'static' */);
		// |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[129] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[130] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[131] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// |VariableCS::ownedInitExpression|
		serializationMatchTerms[132] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		// |VariableCS::ownedType|
		serializationMatchTerms[133] = new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		// |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[134] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// (|AbstractNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[135] = createSerializationMatchTermSubtract(5, 1);
		// (|AnnotationElementCS::ownedDetails| - 1)
		serializationMatchTerms[136] = createSerializationMatchTermSubtract(10, 1);
		// (|AnnotationElementCS::ownedDetails| > 0)
		serializationMatchTerms[137] = createSerializationMatchTermGreaterThan(10, 0);
		// (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
		serializationMatchTerms[138] = createSerializationMatchTermSubtract(11, 1);
		// (|CollectionLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[139] = createSerializationMatchTermSubtract(14, 1);
		// (|CollectionLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[140] = createSerializationMatchTermGreaterThan(14, 0);
		// (|CollectionLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[141] = createSerializationMatchTermSubtract(15, 1);
		// (|CollectionLiteralPartCS::ownedExpression| - 1)
		serializationMatchTerms[142] = createSerializationMatchTermSubtract(16, 1);
		// (|CollectionPatternCS::ownedParts| - 1)
		serializationMatchTerms[143] = createSerializationMatchTermSubtract(18, 1);
		// (|CollectionPatternCS::ownedType| - 1)
		serializationMatchTerms[144] = createSerializationMatchTermSubtract(19, 1);
		// (|CollectionTypeCS::name| - 1)
		serializationMatchTerms[145] = createSerializationMatchTermSubtract(21, 1);
		// (|ConstraintCS::stereotype.'invariant'| - 1)
		serializationMatchTerms[146] = createSerializationMatchTermSubtract(26, 1);
		// (|ConstraintCS::stereotype.'postcondition'| - 1)
		serializationMatchTerms[147] = createSerializationMatchTermSubtract(27, 1);
		// (|ConstraintCS::stereotype.'precondition'| - 1)
		serializationMatchTerms[148] = createSerializationMatchTermSubtract(28, 1);
		// (|ContextCS::ownedExpression| - 1)
		serializationMatchTerms[149] = createSerializationMatchTermSubtract(29, 1);
		// (|CurlyBracketedClauseCS::ownedParts| - 1)
		serializationMatchTerms[150] = createSerializationMatchTermSubtract(30, 1);
		// (|CurlyBracketedClauseCS::ownedParts| > 0)
		serializationMatchTerms[151] = createSerializationMatchTermGreaterThan(30, 0);
		// (|DataTypeCS::isSerializable.'serializable'| > 0)
		serializationMatchTerms[152] = createSerializationMatchTermGreaterThan(32, 0);
		// (|EnumerationCS::isSerializable.'serializable'| > 0)
		serializationMatchTerms[153] = createSerializationMatchTermGreaterThan(35, 0);
		// (|ExpSpecificationCS::ownedExpression| - 1)
		serializationMatchTerms[154] = createSerializationMatchTermSubtract(39, 1);
		// (|IfExpCS::ownedCondition| - 1)
		serializationMatchTerms[155] = createSerializationMatchTermSubtract(40, 1);
		// (|IfExpCS::ownedElseExpression| - 1)
		serializationMatchTerms[156] = createSerializationMatchTermSubtract(41, 1);
		// (|IfExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[157] = createSerializationMatchTermSubtract(43, 1);
		// (|IfThenExpCS::ownedCondition| - 1)
		serializationMatchTerms[158] = createSerializationMatchTermSubtract(44, 1);
		// (|IfThenExpCS::ownedThenExpression| - 1)
		serializationMatchTerms[159] = createSerializationMatchTermSubtract(45, 1);
		// (|ImportCS::ownedPathName| - 1)
		serializationMatchTerms[160] = createSerializationMatchTermSubtract(47, 1);
		// (|InfixExpCS::ownedLeft| - 1)
		serializationMatchTerms[161] = createSerializationMatchTermSubtract(48, 1);
		// (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
		serializationMatchTerms[162] = createSerializationMatchTermSubtract(49, 1);
		// (|LetExpCS::ownedInExpression| - 1)
		serializationMatchTerms[163] = createSerializationMatchTermSubtract(50, 1);
		// (|LetExpCS::ownedVariables| - 1)
		serializationMatchTerms[164] = createSerializationMatchTermSubtract(51, 1);
		// (|MapLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[165] = createSerializationMatchTermSubtract(53, 1);
		// (|MapLiteralExpCS::ownedParts| > 0)
		serializationMatchTerms[166] = createSerializationMatchTermGreaterThan(53, 0);
		// (|MapLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[167] = createSerializationMatchTermSubtract(54, 1);
		// (|MapLiteralPartCS::ownedKey| - 1)
		serializationMatchTerms[168] = createSerializationMatchTermSubtract(55, 1);
		// (|MapLiteralPartCS::ownedValue| - 1)
		serializationMatchTerms[169] = createSerializationMatchTermSubtract(56, 1);
		// (|MapTypeCS::name.'Map'| - 1)
		serializationMatchTerms[170] = createSerializationMatchTermSubtract(57, 1);
		// (|MapTypeCS::ownedKeyType| - V0)
		serializationMatchTerms[171] = createSerializationMatchTermSubtract(58, 2);
		// (|ModelElementCS::ownedAnnotations| > 0)
		serializationMatchTerms[172] = createSerializationMatchTermGreaterThan(60, 0);
		// (|ModelElementRefCS::ownedPathName| - 1)
		serializationMatchTerms[173] = createSerializationMatchTermSubtract(61, 1);
		// (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[174] = createSerializationMatchTermSubtract(62, 1);
		// (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[175] = createSerializationMatchTermSubtract(65, 1);
		// (|NamedElementCS::name| - 1)
		serializationMatchTerms[176] = createSerializationMatchTermSubtract(66, 1);
		// (|NavigatingArgCS::ownedCoIterator| - 1)
		serializationMatchTerms[177] = createSerializationMatchTermSubtract(67, 1);
		// (|NavigatingArgCS::ownedInitExpression| - 1)
		serializationMatchTerms[178] = createSerializationMatchTermSubtract(68, 1);
		// (|NavigatingArgCS::ownedNameExpression| - 1)
		serializationMatchTerms[179] = createSerializationMatchTermSubtract(69, 1);
		// (|NavigatingArgCS::ownedType| - 1)
		serializationMatchTerms[180] = createSerializationMatchTermSubtract(70, 1);
		// (|NavigatingArgCS::prefix.','| - 1)
		serializationMatchTerms[181] = createSerializationMatchTermSubtract(71, 1);
		// (|NavigatingArgCS::prefix.';'| - 1)
		serializationMatchTerms[182] = createSerializationMatchTermSubtract(72, 1);
		// (|NavigatingArgCS::prefix.'|'| - 1)
		serializationMatchTerms[183] = createSerializationMatchTermSubtract(73, 1);
		// (|NestedExpCS::ownedExpression| - 1)
		serializationMatchTerms[184] = createSerializationMatchTermSubtract(74, 1);
		// (|NumberLiteralExpCS::symbol| - 1)
		serializationMatchTerms[185] = createSerializationMatchTermSubtract(75, 1);
		// (|OperationCS::ownedBodyExpressions| > 0)
		serializationMatchTerms[186] = createSerializationMatchTermGreaterThan(77, 0);
		// (|OperationCS::ownedExceptions| - 1)
		serializationMatchTerms[187] = createSerializationMatchTermSubtract(78, 1);
		// (|OperationCS::ownedExceptions| > 0)
		serializationMatchTerms[188] = createSerializationMatchTermGreaterThan(78, 0);
		// (|OperationCS::ownedParameters| - 1)
		serializationMatchTerms[189] = createSerializationMatchTermSubtract(79, 1);
		// (|OperationCS::ownedParameters| > 0)
		serializationMatchTerms[190] = createSerializationMatchTermGreaterThan(79, 0);
		// (|OperatorExpCS::ownedRight| - 1)
		serializationMatchTerms[191] = createSerializationMatchTermSubtract(82, 1);
		// (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[192] = createSerializationMatchTermSubtract(87, 1);
		// (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[193] = createSerializationMatchTermSubtract(88, 1);
		// (|PatternExpCS::ownedPatternType| - 1)
		serializationMatchTerms[194] = createSerializationMatchTermSubtract(89, 1);
		// (|PrimitiveTypeRefCS::name| - 1)
		serializationMatchTerms[195] = createSerializationMatchTermSubtract(91, 1);
		// (|ReferenceCS::referredKeys| - 1)
		serializationMatchTerms[196] = createSerializationMatchTermSubtract(93, 1);
		// (|ReferenceCS::referredKeys| > 0)
		serializationMatchTerms[197] = createSerializationMatchTermGreaterThan(93, 0);
		// (|RoundBracketedClauseCS::ownedArguments| - 1)
		serializationMatchTerms[198] = createSerializationMatchTermSubtract(96, 1);
		// (|RoundBracketedClauseCS::ownedArguments| > 0)
		serializationMatchTerms[199] = createSerializationMatchTermGreaterThan(96, 0);
		// (|ShadowPartCS::ownedInitExpression| - 1)
		serializationMatchTerms[200] = createSerializationMatchTermSubtract(97, 1);
		// (|ShadowPartCS::referredProperty| - 1)
		serializationMatchTerms[201] = createSerializationMatchTermSubtract(98, 1);
		// (|SpecificationCS::exprString| - 1)
		serializationMatchTerms[202] = createSerializationMatchTermSubtract(99, 1);
		// (|SquareBracketedClauseCS::ownedTerms| - 1)
		serializationMatchTerms[203] = createSerializationMatchTermSubtract(100, 1);
		// (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
		serializationMatchTerms[204] = createSerializationMatchTermGreaterThan(103, 0);
		// (|StructuredClassCS::isInterface.'interface'| > 0)
		serializationMatchTerms[205] = createSerializationMatchTermGreaterThan(105, 0);
		// (|StructuredClassCS::ownedSuperTypes| - 1)
		serializationMatchTerms[206] = createSerializationMatchTermSubtract(108, 1);
		// (|StructuredClassCS::ownedSuperTypes| > 0)
		serializationMatchTerms[207] = createSerializationMatchTermGreaterThan(108, 0);
		// (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[208] = createSerializationMatchTermSubtract(110, 1);
		// (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[209] = createSerializationMatchTermSubtract(111, 1);
		// (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[210] = createSerializationMatchTermSubtract(112, 1);
		// (|TupleLiteralExpCS::ownedParts| - 1)
		serializationMatchTerms[211] = createSerializationMatchTermSubtract(114, 1);
		// (|TupleTypeCS::name.'Tuple'| - 1)
		serializationMatchTerms[212] = createSerializationMatchTermSubtract(115, 1);
		// (|TupleTypeCS::ownedParts| - 1)
		serializationMatchTerms[213] = createSerializationMatchTermSubtract(116, 1);
		// (|TupleTypeCS::ownedParts| > 0)
		serializationMatchTerms[214] = createSerializationMatchTermGreaterThan(116, 0);
		// (|TypeLiteralExpCS::ownedType| - 1)
		serializationMatchTerms[215] = createSerializationMatchTermSubtract(117, 1);
		// (|TypeNameExpCS::ownedPathName| - 1)
		serializationMatchTerms[216] = createSerializationMatchTermSubtract(119, 1);
		// (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[217] = createSerializationMatchTermSubtract(121, 1);
		// (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[218] = createSerializationMatchTermGreaterThan(121, 0);
		// (|TypedElementCS::ownedType| - 1)
		serializationMatchTerms[219] = createSerializationMatchTermSubtract(122, 1);
		// (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
		serializationMatchTerms[220] = createSerializationMatchTermGreaterThan(123, 0);
		// (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
		serializationMatchTerms[221] = createSerializationMatchTermGreaterThan(124, 0);
		// (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
		serializationMatchTerms[222] = createSerializationMatchTermGreaterThan(125, 0);
		// (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
		serializationMatchTerms[223] = createSerializationMatchTermGreaterThan(126, 0);
		// (|TypedElementCS::qualifiers.'definition'| - 1)
		serializationMatchTerms[224] = createSerializationMatchTermSubtract(127, 1);
		// (|TypedElementCS::qualifiers.'static'| - 1)
		serializationMatchTerms[225] = createSerializationMatchTermSubtract(128, 1);
		// (|TypedTypeRefCS::ownedBinding| - 1)
		serializationMatchTerms[226] = createSerializationMatchTermSubtract(130, 1);
		// (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[227] = createSerializationMatchTermSubtract(131, 1);
		// (|VariableCS::ownedInitExpression| - 1)
		serializationMatchTerms[228] = createSerializationMatchTermSubtract(132, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules0() {
		// Base::FirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] =
			new SerializationRule("FirstPathElementCS", 31,
				createSerializationMatchSteps(
					40		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					283		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
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
			new SerializationRule("MultiplicityBoundsCS", 55,
				createSerializationMatchSteps(
					83		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					27		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					4		/* MultiplicityBoundsCS::lowerBound=43 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					114		/* '..' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					102		/* MultiplicityBoundsCS::upperBound=120 || soft-space value soft-space */
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
			new SerializationRule("MultiplicityCS", 56,
				createSerializationMatchSteps(
					83		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					27		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					129		/* '[' || no-space value no-space */,
					192		/* 1*7-steps || value */,
					180		/* 1*1-steps || value */,
					4		/* MultiplicityBoundsCS::lowerBound=43 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					114		/* '..' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					102		/* MultiplicityBoundsCS::upperBound=120 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					130		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 56,
				createSerializationMatchSteps(
					83		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					27		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					129		/* '[' || no-space value no-space */,
					192		/* 1*7-steps || value */,
					180		/* 1*1-steps || value */,
					4		/* MultiplicityBoundsCS::lowerBound=43 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					114		/* '..' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					102		/* MultiplicityBoundsCS::upperBound=120 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					177		/* '|?' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					130		/* ']' || no-space value */
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
			new SerializationRule("MultiplicityCS", 56,
				createSerializationMatchSteps(
					119		/* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
					83		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					27		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					129		/* '[' || no-space value no-space */,
					192		/* 1*7-steps || value */,
					180		/* 1*1-steps || value */,
					4		/* MultiplicityBoundsCS::lowerBound=43 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					114		/* '..' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					102		/* MultiplicityBoundsCS::upperBound=120 || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					176		/* '|1' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					130		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						23	/* '|1' */
					)
				},
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(23 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
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
			new SerializationRule("MultiplicityCS", 56,
				createSerializationMatchSteps(
					28		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					129		/* '[' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					99		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					130		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						4	/* '*|+|?' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(4 /* '*|+|?' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityCS(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "|?" "]" }
		serializationRules[6] =
			new SerializationRule("MultiplicityCS", 56,
				createSerializationMatchSteps(
					28		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					129		/* '[' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					99		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					177		/* '|?' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					130		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						4	/* '*|+|?' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(4 /* '*|+|?' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityCS(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} isNullFree="|1"[?] "]" }
		serializationRules[7] =
			new SerializationRule("MultiplicityCS", 56,
				createSerializationMatchSteps(
					84		/* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
					28		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					129		/* '[' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					99		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					176		/* '|1' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					130		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						23	/* '|1' */
					),
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						4	/* '*|+|?' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(23 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(4 /* '*|+|?' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityStringCS(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		serializationRules[8] =
			new SerializationRule("MultiplicityStringCS", 57,
				createSerializationMatchSteps(
					28		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					99		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						4	/* '*|+|?' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(4 /* '*|+|?' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Base::NextPathElementCS(basecs::PathElementCS): referredElement=UnreservedName
		serializationRules[9] =
			new SerializationRule("NextPathElementCS", 67,
				createSerializationMatchSteps(
					40		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					282		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
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
			new SerializationRule("PathNameCS", 73,
				createSerializationMatchSteps(
					247		/* check-rule basecs::PathNameCS.ownedPathElements : 31|67 */,
					61		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					270		/* PathNameCS::ownedPathElements+=31 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					118		/* '::' || no-space value no-space */,
					271		/* PathNameCS::ownedPathElements+=67 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						23) /* FirstPathElementCS|NextPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(31, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateBindingCS(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] =
			new SerializationRule("TemplateBindingCS", 99,
				createSerializationMatchSteps(
					255		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56 */,
					256		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100 */,
					127		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
					64		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
				),
				createSerializationSteps(
					78		/* TemplateBindingCS::ownedSubstitutions+=100 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					78		/* TemplateBindingCS::ownedSubstitutions+=100 || value */,
					203		/* V01*1-steps || value */,
					48		/* TemplateBindingCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						47) /* TemplateParameterSubstitutionCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(100, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateParameterSubstitutionCS(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] =
			new SerializationRule("TemplateParameterSubstitutionCS", 100,
				createSerializationMatchSteps(
					257		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115 */,
					47		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					9		/* TemplateParameterSubstitutionCS::ownedActualParameter=115 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						59) /* TypeRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(115, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Base::TypeParameterCS(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[13] =
			new SerializationRule("TypeParameterCS", 114,
				createSerializationMatchSteps(
					261		/* check-rule basecs::TypeParameterCS.ownedExtends : 117 */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					68		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
					111		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					201		/* V00*7-steps || value */,
					180		/* 1*1-steps || value */,
					145		/* 'extends' || soft-space value soft-space */,
					32		/* TypeParameterCS::ownedExtends+=117 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					108		/* '&&' || soft-space value soft-space */,
					32		/* TypeParameterCS::ownedExtends+=117 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						61) /* TypedRefCS */
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
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[14] =
			new SerializationRule("WildcardTypeRefCS", 129,
				createSerializationMatchSteps(
					267		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : 117 */,
					102		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					124		/* '?' || soft-space value soft-space */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					145		/* 'extends' || soft-space value soft-space */,
					33		/* WildcardTypeRefCS::ownedExtends=117 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						61) /* TypedRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[15] =
			new SerializationRule("BooleanLiteralExpCS", 5,
				createSerializationMatchSteps(
					2		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					100		/* BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						14	/* 'false|true' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(14 /* 'false|true' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::CoIteratorVariableCS(essentialoclcs::VariableCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] }
		serializationRules[16] =
			new SerializationRule("CoIteratorVariableCS", 7,
				createSerializationMatchSteps(
					320		/* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
					101		/* assign V0 = |VariableCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					89		/* VariableCS::ownedType=107 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						51) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[17] =
			new SerializationRule("CollectionLiteralExpCS", 8,
				createSerializationMatchSteps(
					272		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
					273		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
					3		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
					57		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
					104		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					83		/* CollectionLiteralExpCS::ownedType=11 || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					200		/* V00*5-steps || value */,
					56		/* CollectionLiteralExpCS::ownedParts+=9 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					56		/* CollectionLiteralExpCS::ownedParts+=9 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						3) /* CollectionLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						4) /* CollectionTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(9, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(11, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
		serializationRules[18] =
			new SerializationRule("CollectionLiteralPartCS", 9,
				createSerializationMatchSteps(
					275		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74 */,
					4		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					27		/* CollectionLiteralPartCS::ownedExpression=74 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						28) /* PatternExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(74, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { ".." ownedLastExpression=ExpCS }[?] }
		serializationRules[19] =
			new SerializationRule("CollectionLiteralPartCS", 9,
				createSerializationMatchSteps(
					274		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30 */,
					276		/* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30 */,
					73		/* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
					4		/* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					26		/* CollectionLiteralPartCS::ownedExpression=30 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					114		/* '..' || no-space value no-space */,
					44		/* CollectionLiteralPartCS::ownedLastExpression=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" }
		serializationRules[20] =
			new SerializationRule("CollectionPatternCS", 10,
				createSerializationMatchSteps(
					277		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
					278		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
					74		/* assign V0 = |CollectionPatternCS::restVariableName| */,
					105		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
					5		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					84		/* CollectionPatternCS::ownedType=11 || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					196		/* V00*10-steps || value */,
					57		/* CollectionPatternCS::ownedParts+=74 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					57		/* CollectionPatternCS::ownedParts+=74 || value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					112		/* '++' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					97		/* CollectionPatternCS::restVariableName=35 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						28) /* PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						4) /* CollectionTypeCS */
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
						new RuleIndex_GrammarCardinality(74, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(11, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] }
		serializationRules[21] =
			new SerializationRule("CollectionTypeCS", 11,
				createSerializationMatchSteps(
					279		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
					280		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
					75		/* assign V0 = |CollectionTypeCS::ownedType| */,
					6		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					115		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					5		/* CollectionTypeCS::name=12 || soft-space value soft-space */,
					201		/* V00*7-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					85		/* CollectionTypeCS::ownedType=108 || value */,
					203		/* V01*1-steps || value */,
					15		/* CollectionTypeCS::ownedCollectionMultiplicity=56 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						52) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::CurlyBracketedClauseCS(essentialoclcs::CurlyBracketedClauseCS): { "{" { ownedParts+=ShadowPartCS { "," ownedParts+=ShadowPartCS }[*] }[?] "}" }
		serializationRules[22] =
			new SerializationRule("CurlyBracketedClauseCS", 13,
				createSerializationMatchSteps(
					282		/* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90 */,
					58		/* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
					106		/* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					200		/* V00*5-steps || value */,
					58		/* CurlyBracketedClauseCS::ownedParts+=90 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					58		/* CurlyBracketedClauseCS::ownedParts+=90 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						37) /* ShadowPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(90, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ElseIfThenExpCS(essentialoclcs::IfThenExpCS): { "elseif" ownedCondition=ExpCS "then" ownedThenExpression=ExpCS }
		serializationRules[23] =
			new SerializationRule("ElseIfThenExpCS", 20,
				createSerializationMatchSteps(
					288		/* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30 */,
					289		/* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30 */,
					16		/* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
					15		/* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					142		/* 'elseif' || soft-space value soft-space */,
					17		/* IfThenExpCS::ownedCondition=30 || value */,
					180		/* 1*1-steps || value */,
					171		/* 'then' || soft-space value soft-space */,
					82		/* IfThenExpCS::ownedThenExpression=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		serializationRules[24] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					2		/* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					100		/* BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						14	/* 'false|true' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(14 /* 'false|true' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS "{" { ownedParts+=CollectionLiteralPartCS { "," ownedParts+=CollectionLiteralPartCS }[*] }[?] "}" }
		serializationRules[25] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					272		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
					273		/* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
					3		/* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
					57		/* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
					104		/* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					83		/* CollectionLiteralExpCS::ownedType=11 || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					200		/* V00*5-steps || value */,
					56		/* CollectionLiteralExpCS::ownedParts+=9 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					56		/* CollectionLiteralExpCS::ownedParts+=9 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						3) /* CollectionLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						4) /* CollectionTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(9, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(11, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "*"
		serializationRules[26] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					111		/* '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "invalid"
		serializationRules[27] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					151		/* 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "null"
		serializationRules[28] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					157		/* 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::ExpCS): "self"
		serializationRules[29] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					167		/* 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ExpCS(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[30] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					284		/* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
					285		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
					286		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
					287		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
					13		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
					80		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
					14		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
					12		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					146		/* 'if' || soft-space value soft-space */,
					16		/* IfExpCS::ownedCondition=30|74 || value */,
					180		/* 1*1-steps || value */,
					171		/* 'then' || soft-space value soft-space */,
					81		/* IfExpCS::ownedThenExpression=30 || value */,
					195		/* V00*1-steps || value */,
					34		/* IfExpCS::ownedIfThenExpressions+=20 || value */,
					180		/* 1*1-steps || value */,
					141		/* 'else' || soft-space value soft-space */,
					24		/* IfExpCS::ownedElseExpression=30 || value */,
					180		/* 1*1-steps || value */,
					143		/* 'endif' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						29) /* ExpCS|PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						7) /* ElseIfThenExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(74, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS { name=BinaryOperatorName ownedRight=ExpCS } }
		serializationRules[31] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					290		/* check-rule essentialoclcs::InfixExpCS.ownedLeft : 78 */,
					306		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 30 */,
					39		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					18		/* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					45		/* InfixExpCS::ownedLeft=78 || value */,
					186		/* 1*3-steps || value */,
					180		/* 1*1-steps || value */,
					266		/* NamedElementCS::name=4 || soft-space value soft-space */,
					272		/* OperatorExpCS::ownedRight=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						34) /* PrefixedPrimaryExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						9) /* ExpCS */
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
						new RuleIndex_GrammarCardinality(78, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[32] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					291		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
					19		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					126		/* 'Lambda' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					31		/* LambdaLiteralExpCS::ownedExpressionCS=30 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[33] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					295		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
					296		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
					21		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
					60		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
					107		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					86		/* MapLiteralExpCS::ownedType=51 || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					200		/* V00*5-steps || value */,
					59		/* MapLiteralExpCS::ownedParts+=50 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					59		/* MapLiteralExpCS::ownedParts+=50 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						15) /* MapLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						16) /* MapTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(51, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] }
		serializationRules[34] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					268		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
					269		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
					270		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
					271		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
					173		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
					156		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
					113		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
					71		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
					0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					62		/* AbstractNameExpCS::ownedPathName=73 || value */,
					195		/* V00*1-steps || value */,
					77		/* AbstractNameExpCS::ownedSquareBracketedClauses+=93 || value */,
					203		/* V01*1-steps || value */,
					73		/* AbstractNameExpCS::ownedRoundBracketedClause=84 || value */,
					219		/* V02*1-steps || value */,
					20		/* AbstractNameExpCS::ownedCurlyBracketedClause=13 || value */,
					229		/* V03*4-steps || value */,
					180		/* 1*1-steps || value */,
					125		/* '@' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					162		/* 'pre' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						8	/* '@' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						5) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						35) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						39) /* SquareBracketedClauseCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(8 /* '@' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(93, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[35] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					305		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
					37		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					30		/* NestedExpCS::ownedExpression=30 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[36] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					38		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					101		/* NumberLiteralExpCS::symbol=58 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[37] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					308		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
					39		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					261		/* NamedElementCS::name=124 || soft-space value soft-space */,
					274		/* OperatorExpCS::ownedRight=78 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						34) /* PrefixedPrimaryExpCS */
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
						new RuleIndex_GrammarCardinality(78, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[38] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					93		/* assign V0 = |StringLiteralExpCS::segments| */
				),
				createSerializationSteps(
					195		/* V00*1-steps || value */,
					98		/* StringLiteralExpCS::segments+=94 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[39] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					314		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
					66		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					128		/* 'Tuple' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					60		/* TupleLiteralExpCS::ownedParts+=104 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					60		/* TupleLiteralExpCS::ownedParts+=104 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						49) /* TupleLiteralPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(104, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[40] =
			new SerializationRule("ExpCS", 30,
				createSerializationMatchSteps(
					315		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
					49		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					88		/* TypeLiteralExpCS::ownedType=112 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						54) /* TypeLiteralWithMultiplicityCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(112, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { "if" ownedCondition=(ExpCS|PatternExpCS) "then" ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] "else" ownedElseExpression=ExpCS "endif" }
		serializationRules[41] =
			new SerializationRule("IfExpCS", 36,
				createSerializationMatchSteps(
					284		/* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
					285		/* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
					286		/* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
					287		/* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
					13		/* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
					80		/* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
					14		/* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
					12		/* assert (|IfExpCS::ownedCondition| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					146		/* 'if' || soft-space value soft-space */,
					16		/* IfExpCS::ownedCondition=30|74 || value */,
					180		/* 1*1-steps || value */,
					171		/* 'then' || soft-space value soft-space */,
					81		/* IfExpCS::ownedThenExpression=30 || value */,
					195		/* V00*1-steps || value */,
					34		/* IfExpCS::ownedIfThenExpressions+=20 || value */,
					180		/* 1*1-steps || value */,
					141		/* 'else' || soft-space value soft-space */,
					24		/* IfExpCS::ownedElseExpression=30 || value */,
					180		/* 1*1-steps || value */,
					143		/* 'endif' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						29) /* ExpCS|PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						7) /* ElseIfThenExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(74, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): "invalid"
		serializationRules[42] =
			new SerializationRule("InvalidLiteralExpCS", 40,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					151		/* 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { "Lambda" "{" ownedExpressionCS=ExpCS "}" }
		serializationRules[43] =
			new SerializationRule("LambdaLiteralExpCS", 44,
				createSerializationMatchSteps(
					291		/* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
					19		/* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					126		/* 'Lambda' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					31		/* LambdaLiteralExpCS::ownedExpressionCS=30 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { "let" ownedVariables+=LetVariableCS { "," ownedVariables+=LetVariableCS }[*] "in" ownedInExpression=ExpCS }
		serializationRules[44] =
			new SerializationRule("LetExpCS", 45,
				createSerializationMatchSteps(
					292		/* check-rule essentialoclcs::LetExpCS.ownedInExpression : 30 */,
					293		/* check-rule essentialoclcs::LetExpCS.ownedVariables : 46 */,
					20		/* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
					59		/* assign V0 = (|LetExpCS::ownedVariables| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					154		/* 'let' || soft-space value soft-space */,
					92		/* LetExpCS::ownedVariables+=46 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					92		/* LetExpCS::ownedVariables+=46 || value */,
					180		/* 1*1-steps || value */,
					148		/* 'in' || soft-space value soft-space */,
					37		/* LetExpCS::ownedInExpression=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						14) /* LetVariableCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(46, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::LetVariableCS(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[45] =
			new SerializationRule("LetVariableCS", 46,
				createSerializationMatchSteps(
					319		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
					294		/* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84 */,
					320		/* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
					56		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
					133		/* assign V1 = |VariableCS::ownedType| */,
					81		/* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					74		/* LetVariableCS::ownedRoundBracketedClause=84 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					89		/* VariableCS::ownedType=107 || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					41		/* VariableCS::ownedInitExpression=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						35) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						51) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS "{" { ownedParts+=MapLiteralPartCS { "," ownedParts+=MapLiteralPartCS }[*] }[?] "}" }
		serializationRules[46] =
			new SerializationRule("MapLiteralExpCS", 49,
				createSerializationMatchSteps(
					295		/* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
					296		/* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
					21		/* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
					60		/* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
					107		/* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					86		/* MapLiteralExpCS::ownedType=51 || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					200		/* V00*5-steps || value */,
					59		/* MapLiteralExpCS::ownedParts+=50 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					59		/* MapLiteralExpCS::ownedParts+=50 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						15) /* MapLiteralPartCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						16) /* MapTypeCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(51, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::MapLiteralPartCS(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS "<-" ownedValue=ExpCS }
		serializationRules[47] =
			new SerializationRule("MapLiteralPartCS", 50,
				createSerializationMatchSteps(
					297		/* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30 */,
					298		/* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30 */,
					23		/* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
					22		/* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					42		/* MapLiteralPartCS::ownedKey=30 || value */,
					180		/* 1*1-steps || value */,
					120		/* '<-' || soft-space value soft-space */,
					90		/* MapLiteralPartCS::ownedValue=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] }
		serializationRules[48] =
			new SerializationRule("MapTypeCS", 51,
				createSerializationMatchSteps(
					299		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
					300		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
					82		/* assign V0 = |MapTypeCS::ownedValueType| */,
					25		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					24		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					127		/* 'Map' || soft-space value soft-space */,
					202		/* V00*8-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					43		/* MapTypeCS::ownedKeyType=107 || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					91		/* MapTypeCS::ownedValueType=107 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						9	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						51) /* TypeExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						51) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(9 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::Model(essentialoclcs::ContextCS): ownedExpression=ExpCS
		serializationRules[49] =
			new SerializationRule("Model", 52,
				createSerializationMatchSteps(
					281		/* check-rule essentialoclcs::ContextCS.ownedExpression : 30 */,
					10		/* assert (|ContextCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					28		/* ContextCS::ownedExpression=30 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre="@" "pre" }[?] }
		serializationRules[50] =
			new SerializationRule("NameExpCS", 59,
				createSerializationMatchSteps(
					268		/* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
					269		/* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
					270		/* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
					271		/* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
					173		/* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
					156		/* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
					113		/* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
					71		/* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
					0		/* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					62		/* AbstractNameExpCS::ownedPathName=73 || value */,
					195		/* V00*1-steps || value */,
					77		/* AbstractNameExpCS::ownedSquareBracketedClauses+=93 || value */,
					203		/* V01*1-steps || value */,
					73		/* AbstractNameExpCS::ownedRoundBracketedClause=84 || value */,
					219		/* V02*1-steps || value */,
					20		/* AbstractNameExpCS::ownedCurlyBracketedClause=13 || value */,
					229		/* V03*4-steps || value */,
					180		/* 1*1-steps || value */,
					125		/* '@' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					162		/* 'pre' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						8	/* '@' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						5) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						35) /* RoundBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						39) /* SquareBracketedClauseCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(8 /* '@' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(93, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
		serializationRules[51] =
			new SerializationRule("NavigatingArgCS", 60,
				createSerializationMatchSteps(
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					51		/* NavigatingArgCS::ownedNameExpression=61 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ":" ownedType=TypeExpCS }
		serializationRules[52] =
			new SerializationRule("NavigatingArgCS", 60,
				createSerializationMatchSteps(
					304		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
					33		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					87		/* NavigatingArgCS::ownedType=107 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						51) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[53] =
			new SerializationRule("NavigatingArgCS", 60,
				createSerializationMatchSteps(
					301		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
					302		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					304		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
					122		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
					86		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
					33		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					50		/* NavigatingArgCS::ownedNameExpression=61 || value */,
					181		/* 1*11-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					87		/* NavigatingArgCS::ownedType=107 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					120		/* '<-' || soft-space value soft-space */,
					14		/* NavigatingArgCS::ownedCoIterator=7 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					38		/* NavigatingArgCS::ownedInitExpression=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						2) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						51) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[54] =
			new SerializationRule("NavigatingArgCS", 60,
				createSerializationMatchSteps(
					301		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
					302		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					87		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
					30		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					50		/* NavigatingArgCS::ownedNameExpression=61 || value */,
					192		/* 1*7-steps || value */,
					180		/* 1*1-steps || value */,
					120		/* '<-' || soft-space value soft-space */,
					14		/* NavigatingArgCS::ownedCoIterator=7 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					38		/* NavigatingArgCS::ownedInitExpression=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						2) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[55] =
			new SerializationRule("NavigatingArgCS", 60,
				createSerializationMatchSteps(
					301		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
					302		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					304		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
					31		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
					121		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
					88		/* assign V0 = |NavigatingArgCS::ownedType| */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					50		/* NavigatingArgCS::ownedNameExpression=61 || value */,
					181		/* 1*11-steps || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					87		/* NavigatingArgCS::ownedType=107 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					120		/* '<-' || soft-space value soft-space */,
					14		/* NavigatingArgCS::ownedCoIterator=7 || value */,
					180		/* 1*1-steps || value */,
					148		/* 'in' || soft-space value soft-space */,
					38		/* NavigatingArgCS::ownedInitExpression=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						2) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						51) /* TypeExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingBarArgCS(essentialoclcs::NavigatingArgCS): { prefix="|" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[56] =
			new SerializationRule("NavigatingBarArgCS", 62,
				createSerializationMatchSteps(
					302		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					304		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
					88		/* assign V0 = |NavigatingArgCS::ownedType| */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					36		/* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
					122		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					175		/* '|' || soft-space value soft-space */,
					50		/* NavigatingArgCS::ownedNameExpression=61 || value */,
					201		/* V00*7-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					87		/* NavigatingArgCS::ownedType=107 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					38		/* NavigatingArgCS::ownedInitExpression=30 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						22	/* '|' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						51) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(22 /* '|' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "<-" ownedCoIterator=CoIteratorVariableCS }[?] { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[57] =
			new SerializationRule("NavigatingCommaArgCS", 63,
				createSerializationMatchSteps(
					301		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
					302		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					304		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
					122		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
					86		/* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
					33		/* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					34		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					50		/* NavigatingArgCS::ownedNameExpression=61 || value */,
					181		/* 1*11-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					87		/* NavigatingArgCS::ownedType=107 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					120		/* '<-' || soft-space value soft-space */,
					14		/* NavigatingArgCS::ownedCoIterator=7 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					38		/* NavigatingArgCS::ownedInitExpression=30 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						5	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						2) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						51) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* ',' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { "<-" ownedCoIterator=CoIteratorVariableCS { "=" ownedInitExpression=ExpCS }[?] } }
		serializationRules[58] =
			new SerializationRule("NavigatingCommaArgCS", 63,
				createSerializationMatchSteps(
					301		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
					302		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					87		/* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
					30		/* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					34		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					50		/* NavigatingArgCS::ownedNameExpression=61 || value */,
					192		/* 1*7-steps || value */,
					180		/* 1*1-steps || value */,
					120		/* '<-' || soft-space value soft-space */,
					14		/* NavigatingArgCS::ownedCoIterator=7 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					38		/* NavigatingArgCS::ownedInitExpression=30 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						5	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						2) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* ',' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS { { ":" ownedType=TypeExpCS }[?] { "<-" ownedCoIterator=CoIteratorVariableCS }[?] "in" ownedInitExpression=ExpCS } }
		serializationRules[59] =
			new SerializationRule("NavigatingCommaArgCS", 63,
				createSerializationMatchSteps(
					301		/* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
					302		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					304		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
					31		/* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
					121		/* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
					88		/* assign V0 = |NavigatingArgCS::ownedType| */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					34		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					50		/* NavigatingArgCS::ownedNameExpression=61 || value */,
					181		/* 1*11-steps || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					87		/* NavigatingArgCS::ownedType=107 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					120		/* '<-' || soft-space value soft-space */,
					14		/* NavigatingArgCS::ownedCoIterator=7 || value */,
					180		/* 1*1-steps || value */,
					148		/* 'in' || soft-space value soft-space */,
					38		/* NavigatingArgCS::ownedInitExpression=30 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						5	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						2) /* CoIteratorVariableCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						51) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* ',' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix="," ownedNameExpression=NavigatingArgExpCS }
		serializationRules[60] =
			new SerializationRule("NavigatingCommaArgCS", 63,
				createSerializationMatchSteps(
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					34		/* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					50		/* NavigatingArgCS::ownedNameExpression=61 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						5	/* ',' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(5 /* ',' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NavigatingSemiArgCS(essentialoclcs::NavigatingArgCS): { prefix=";" ownedNameExpression=NavigatingArgExpCS { ":" ownedType=TypeExpCS { "=" ownedInitExpression=ExpCS }[?] }[?] }
		serializationRules[61] =
			new SerializationRule("NavigatingSemiArgCS", 64,
				createSerializationMatchSteps(
					302		/* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
					303		/* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
					304		/* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
					88		/* assign V0 = |NavigatingArgCS::ownedType| */,
					32		/* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
					35		/* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
					122		/* assign V1 = |NavigatingArgCS::ownedInitExpression| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					50		/* NavigatingArgCS::ownedNameExpression=61 || value */,
					201		/* V00*7-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					87		/* NavigatingArgCS::ownedType=107 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					38		/* NavigatingArgCS::ownedInitExpression=30 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						7	/* ';' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						20) /* NavigatingArgExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						51) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(7 /* ';' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { "(" ownedExpression=ExpCS ")" }
		serializationRules[62] =
			new SerializationRule("NestedExpCS", 66,
				createSerializationMatchSteps(
					305		/* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
					37		/* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					30		/* NestedExpCS::ownedExpression=30 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): "null"
		serializationRules[63] =
			new SerializationRule("NullLiteralExpCS", 68,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					157		/* 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
	}
	private void initSerializationRules1() {
		// EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		serializationRules[64] =
			new SerializationRule("NumberLiteralExpCS", 69,
				createSerializationMatchSteps(
					38		/* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					101		/* NumberLiteralExpCS::symbol=58 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[65] =
			new SerializationRule("PatternExpCS", 74,
				createSerializationMatchSteps(
					309		/* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107 */,
					42		/* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
					91		/* assign V0 = |PatternExpCS::patternVariableName| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					93		/* PatternExpCS::patternVariableName=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					68		/* PatternExpCS::ownedPatternType=107 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						51) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		serializationRules[66] =
			new SerializationRule("PrefixedLetExpCS", 77,
				createSerializationMatchSteps(
					307		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 77 */,
					39		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					261		/* NamedElementCS::name=124 || soft-space value soft-space */,
					273		/* OperatorExpCS::ownedRight=77 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						32) /* PrefixedLetExpCS */
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
						new RuleIndex_GrammarCardinality(77, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrefixedPrimaryExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		serializationRules[67] =
			new SerializationRule("PrefixedPrimaryExpCS", 78,
				createSerializationMatchSteps(
					308		/* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
					39		/* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					261		/* NamedElementCS::name=124 || soft-space value soft-space */,
					274		/* OperatorExpCS::ownedRight=78 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						34) /* PrefixedPrimaryExpCS */
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
						new RuleIndex_GrammarCardinality(78, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		serializationRules[68] =
			new SerializationRule("PrimitiveTypeCS", 81,
				createSerializationMatchSteps(
					43		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					6		/* PrimitiveTypeRefCS::name=82 || soft-space value soft-space */
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
		serializationRules[69] =
			new SerializationRule("RoundBracketedClauseCS", 84,
				createSerializationMatchSteps(
					310		/* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64 */,
					62		/* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
					109		/* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					198		/* V00*3-steps || value */,
					267		/* RoundBracketedClauseCS::ownedArguments+=60 || value */,
					203		/* V01*1-steps || value */,
					268		/* RoundBracketedClauseCS::ownedArguments+=63|64|62 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						22) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(60, GrammarCardinality.ZERO_OR_ONE),
						new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(63, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(64, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): "self"
		serializationRules[70] =
			new SerializationRule("SelfExpCS", 89,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					167		/* 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
		serializationRules[71] =
			new SerializationRule("ShadowPartCS", 90,
				createSerializationMatchSteps(
					312		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95 */,
					44		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					40		/* ShadowPartCS::ownedInitExpression=95 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						40) /* StringLiteralExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(95, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName "=" ownedInitExpression=(ExpCS|PatternExpCS) }
		serializationRules[72] =
			new SerializationRule("ShadowPartCS", 90,
				createSerializationMatchSteps(
					311		/* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74 */,
					44		/* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
					45		/* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					96		/* ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					39		/* ShadowPartCS::ownedInitExpression=30|74 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						29) /* ExpCS|PatternExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(74, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// EssentialOCL::SimplePathNameCS(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
		serializationRules[73] =
			new SerializationRule("SimplePathNameCS", 91,
				createSerializationMatchSteps(
					246		/* check-rule basecs::PathNameCS.ownedPathElements : 31 */,
					41		/* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
				),
				createSerializationSteps(
					270		/* PathNameCS::ownedPathElements+=31 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						10) /* FirstPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(31, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::SquareBracketedClauseCS(essentialoclcs::SquareBracketedClauseCS): { "[" ownedTerms+=ExpCS { "," ownedTerms+=ExpCS }[*] "]" }
		serializationRules[74] =
			new SerializationRule("SquareBracketedClauseCS", 93,
				createSerializationMatchSteps(
					313		/* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30 */,
					63		/* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					129		/* '[' || no-space value no-space */,
					80		/* SquareBracketedClauseCS::ownedTerms+=30 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					80		/* SquareBracketedClauseCS::ownedTerms+=30 || value */,
					180		/* 1*1-steps || value */,
					130		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		serializationRules[75] =
			new SerializationRule("StringLiteralExpCS", 95,
				createSerializationMatchSteps(
					93		/* assign V0 = |StringLiteralExpCS::segments| */
				),
				createSerializationSteps(
					195		/* V00*1-steps || value */,
					98		/* StringLiteralExpCS::segments+=94 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		serializationRules[76] =
			new SerializationRule("TupleLiteralExpCS", 103,
				createSerializationMatchSteps(
					314		/* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
					66		/* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					128		/* 'Tuple' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					60		/* TupleLiteralExpCS::ownedParts+=104 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					60		/* TupleLiteralExpCS::ownedParts+=104 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						49) /* TupleLiteralPartCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(104, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TupleLiteralPartCS(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ":" ownedType=TypeExpCS }[?] "=" ownedInitExpression=ExpCS }
		serializationRules[77] =
			new SerializationRule("TupleLiteralPartCS", 104,
				createSerializationMatchSteps(
					319		/* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
					320		/* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
					56		/* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
					101		/* assign V0 = |VariableCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					89		/* VariableCS::ownedType=107 || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					41		/* VariableCS::ownedInitExpression=30 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						9) /* ExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						51) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TuplePartCS(basecs::TuplePartCS): { name=UnrestrictedName ":" ownedType=TypeExpCS }
		serializationRules[78] =
			new SerializationRule("TuplePartCS", 105,
				createSerializationMatchSteps(
					262		/* check-rule basecs::TypedElementCS.ownedType : 107 */,
					51		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					275		/* TypedElementCS::ownedType=107 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						51) /* TypeExpCS */
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
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] }
		serializationRules[79] =
			new SerializationRule("TupleTypeCS", 106,
				createSerializationMatchSteps(
					260		/* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
					48		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					67		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					110		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					153		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					128		/* 'Tuple' || soft-space value soft-space */,
					196		/* V00*10-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					207		/* V01*5-steps || value */,
					61		/* TupleTypeCS::ownedParts+=105 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					61		/* TupleTypeCS::ownedParts+=105 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						10	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						50) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(10 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[80] =
			new SerializationRule("TypeExpCS", 107,
				createSerializationMatchSteps(
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					100		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					43		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					6		/* PrimitiveTypeRefCS::name=82 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */
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
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[81] =
			new SerializationRule("TypeExpCS", 107,
				createSerializationMatchSteps(
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					260		/* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
					181		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					48		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					67		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					110		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					153		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					128		/* 'Tuple' || soft-space value soft-space */,
					196		/* V00*10-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					207		/* V01*5-steps || value */,
					61		/* TupleTypeCS::ownedParts+=105 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					61		/* TupleTypeCS::ownedParts+=105 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					226		/* V03*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						10	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						50) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(10 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::CollectionPatternCS): { { ownedType=CollectionTypeCS "{" { ownedParts+=PatternExpCS { "," ownedParts+=PatternExpCS }[*] { "++" restVariableName=Identifier } }[?] "}" } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[82] =
			new SerializationRule("TypeExpCS", 107,
				createSerializationMatchSteps(
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					277		/* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
					278		/* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
					165		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					74		/* assign V0 = |CollectionPatternCS::restVariableName| */,
					105		/* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
					5		/* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					84		/* CollectionPatternCS::ownedType=11 || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					196		/* V00*10-steps || value */,
					57		/* CollectionPatternCS::ownedParts+=74 || value */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					57		/* CollectionPatternCS::ownedParts+=74 || value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					112		/* '++' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					97		/* CollectionPatternCS::restVariableName=35 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					219		/* V02*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						28) /* PatternExpCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						4) /* CollectionTypeCS */
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
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(74, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(11, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[83] =
			new SerializationRule("TypeExpCS", 107,
				createSerializationMatchSteps(
					279		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					280		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
					165		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					75		/* assign V0 = |CollectionTypeCS::ownedType| */,
					6		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					115		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					5		/* CollectionTypeCS::name=12 || soft-space value soft-space */,
					201		/* V00*7-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					85		/* CollectionTypeCS::ownedType=108 || value */,
					203		/* V01*1-steps || value */,
					15		/* CollectionTypeCS::ownedCollectionMultiplicity=56 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					219		/* V02*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						52) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[84] =
			new SerializationRule("TypeExpCS", 107,
				createSerializationMatchSteps(
					299		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					300		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
					132		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					82		/* assign V0 = |MapTypeCS::ownedValueType| */,
					25		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					24		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					127		/* 'Map' || soft-space value soft-space */,
					202		/* V00*8-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					43		/* MapTypeCS::ownedKeyType=107 || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					91		/* MapTypeCS::ownedValueType=107 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					203		/* V01*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						9	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						51) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						51) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(9 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeExpCS(essentialoclcs::TypeNameExpCS): { { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[85] =
			new SerializationRule("TypeExpCS", 107,
				createSerializationMatchSteps(
					316		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					317		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
					318		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
					165		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					96		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
					50		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
					129		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
				),
				createSerializationSteps(
					65		/* TypeNameExpCS::ownedPathName=73 || value */,
					201		/* V00*7-steps || value */,
					21		/* TypeNameExpCS::ownedCurlyBracketedClause=13 || value */,
					207		/* V01*5-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					67		/* TypeNameExpCS::ownedPatternGuard=30 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					219		/* V02*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						5) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		serializationRules[86] =
			new SerializationRule("TypeLiteralExpCS", 111,
				createSerializationMatchSteps(
					315		/* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
					49		/* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					88		/* TypeLiteralExpCS::ownedType=112 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						54) /* TypeLiteralWithMultiplicityCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(112, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[87] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 112,
				createSerializationMatchSteps(
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					100		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					43		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					6		/* PrimitiveTypeRefCS::name=82 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */
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
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[88] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 112,
				createSerializationMatchSteps(
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					260		/* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
					181		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					48		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					67		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					110		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					153		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					128		/* 'Tuple' || soft-space value soft-space */,
					196		/* V00*10-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					207		/* V01*5-steps || value */,
					61		/* TupleTypeCS::ownedParts+=105 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					61		/* TupleTypeCS::ownedParts+=105 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					226		/* V03*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						10	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						50) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(10 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[89] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 112,
				createSerializationMatchSteps(
					279		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					280		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
					165		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					75		/* assign V0 = |CollectionTypeCS::ownedType| */,
					6		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					115		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					5		/* CollectionTypeCS::name=12 || soft-space value soft-space */,
					201		/* V00*7-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					85		/* CollectionTypeCS::ownedType=108 || value */,
					203		/* V01*1-steps || value */,
					15		/* CollectionTypeCS::ownedCollectionMultiplicity=56 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					219		/* V02*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						52) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[90] =
			new SerializationRule("TypeLiteralWithMultiplicityCS", 112,
				createSerializationMatchSteps(
					299		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					300		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
					132		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					82		/* assign V0 = |MapTypeCS::ownedValueType| */,
					25		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					24		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					127		/* 'Map' || soft-space value soft-space */,
					202		/* V00*8-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					43		/* MapTypeCS::ownedKeyType=107 || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					91		/* MapTypeCS::ownedValueType=107 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					203		/* V01*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						9	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						51) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						51) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(9 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { "{" ownedPatternGuard=ExpCS "}" }[?] }[?] }
		serializationRules[91] =
			new SerializationRule("TypeNameExpCS", 113,
				createSerializationMatchSteps(
					316		/* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
					317		/* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
					318		/* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
					96		/* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
					50		/* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
					129		/* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
				),
				createSerializationSteps(
					65		/* TypeNameExpCS::ownedPathName=73 || value */,
					201		/* V00*7-steps || value */,
					21		/* TypeNameExpCS::ownedCurlyBracketedClause=13 || value */,
					207		/* V01*5-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					67		/* TypeNameExpCS::ownedPatternGuard=30 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						5) /* CurlyBracketedClauseCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// EssentialOCL::URIFirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[92] =
			new SerializationRule("URIFirstPathElementCS", 122,
				createSerializationMatchSteps(
					40		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					283		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
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
			new SerializationRule("URIFirstPathElementCS", 122,
				createSerializationMatchSteps(
					40		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					281		/* PathElementCS::referredElement=URI || soft-space value soft-space */
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
			new SerializationRule("URIPathNameCS", 123,
				createSerializationMatchSteps(
					248		/* check-rule basecs::PathNameCS.ownedPathElements : 67|122 */,
					61		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					269		/* PathNameCS::ownedPathElements+=122 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					118		/* '::' || no-space value no-space */,
					271		/* PathNameCS::ownedPathElements+=67 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						64) /* NextPathElementCS|URIFirstPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(67, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(122, GrammarCardinality.ONE)
						}
					)
				});
			;
		// EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): "*"
		serializationRules[95] =
			new SerializationRule("UnlimitedNaturalLiteralExpCS", 125,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					111		/* '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// OCLinEcore::AnnotationCS(basecs::AnnotationCS): { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" }
		serializationRules[96] =
			new SerializationRule("AnnotationCS", 1,
				createSerializationMatchSteps(
					231		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					85		/* assign V0 = |NamedElementCS::name| */,
					103		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
					148		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					132		/* 'annotation' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					263		/* NamedElementCS::name=127|87 || soft-space value soft-space */,
					210		/* V01*9-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						6) /* DetailCS */
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
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::AnnotationCS(basecs::AnnotationCS): { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] } "}" } }
		serializationRules[97] =
			new SerializationRule("AnnotationCS", 1,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					229		/* check-rule basecs::AnnotationCS.ownedContents : 53 */,
					231		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					230		/* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
					196		/* assign V5 = |AnnotationCS::ownedReferences| */,
					184		/* assign V4 = |AnnotationCS::ownedContents| */,
					176		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
					85		/* assign V0 = |NamedElementCS::name| */,
					103		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
					148		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					132		/* 'annotation' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					263		/* NamedElementCS::name=127|87 || soft-space value soft-space */,
					210		/* V01*9-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					181		/* 1*11-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					191		/* 1*6-steps || value */,
					226		/* V03*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					232		/* V04*1-steps || value */,
					19		/* AnnotationCS::ownedContents+=53 || value */,
					237		/* V05*1-steps || value */,
					72		/* AnnotationCS::ownedReferences+=54 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
						17) /* ModelElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						6) /* DetailCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
						18) /* ModelElementRefCS */
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
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(53, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(54, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::AnnotationCS(basecs::AnnotationCS): { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] } "}" } }
		serializationRules[98] =
			new SerializationRule("AnnotationCS", 1,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					229		/* check-rule basecs::AnnotationCS.ownedContents : 53 */,
					231		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					230		/* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
					196		/* assign V5 = |AnnotationCS::ownedReferences| */,
					184		/* assign V4 = |AnnotationCS::ownedContents| */,
					176		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
					85		/* assign V0 = |NamedElementCS::name| */,
					103		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
					148		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					132		/* 'annotation' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					263		/* NamedElementCS::name=127|87 || soft-space value soft-space */,
					210		/* V01*9-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					181		/* 1*11-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					191		/* 1*6-steps || value */,
					226		/* V03*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					232		/* V04*1-steps || value */,
					19		/* AnnotationCS::ownedContents+=53 || value */,
					237		/* V05*1-steps || value */,
					72		/* AnnotationCS::ownedReferences+=54 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
						17) /* ModelElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						6) /* DetailCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
						18) /* ModelElementRefCS */
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
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(53, GrammarCardinality.ONE_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(54, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::AnnotationCS(basecs::AnnotationCS): { "annotation" name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] } "}" } }
		serializationRules[99] =
			new SerializationRule("AnnotationCS", 1,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					229		/* check-rule basecs::AnnotationCS.ownedContents : 53 */,
					231		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					230		/* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
					196		/* assign V5 = |AnnotationCS::ownedReferences| */,
					184		/* assign V4 = |AnnotationCS::ownedContents| */,
					176		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
					85		/* assign V0 = |NamedElementCS::name| */,
					103		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
					148		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					132		/* 'annotation' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					263		/* NamedElementCS::name=127|87 || soft-space value soft-space */,
					210		/* V01*9-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					181		/* 1*11-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					191		/* 1*6-steps || value */,
					226		/* V03*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					232		/* V04*1-steps || value */,
					19		/* AnnotationCS::ownedContents+=53 || value */,
					237		/* V05*1-steps || value */,
					72		/* AnnotationCS::ownedReferences+=54 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
						17) /* ModelElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						6) /* DetailCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
						18) /* ModelElementRefCS */
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
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ONE_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(53, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(54, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::AttributeCS(basecs::AttributeCS): { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[100] =
			new SerializationRule("AttributeCS", 3,
				createSerializationMatchSteps(
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					126		/* assign V1 = |StructuralFeatureCS::default| */,
					97		/* assign V0 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					154		/* assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
					180		/* assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					133		/* 'attribute' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					225		/* V02*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					227		/* V03*2-steps || value */,
					180		/* 1*1-steps || value */,
					278		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						1	/* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::AttributeCS(basecs::AttributeCS): { "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } }
		serializationRules[101] =
			new SerializationRule("AttributeCS", 3,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					251		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					188		/* assign V4 = |ModelElementCS::ownedAnnotations| */,
					126		/* assign V1 = |StructuralFeatureCS::default| */,
					97		/* assign V0 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					154		/* assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
					180		/* assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
					200		/* assign V5 = |StructuralFeatureCS::ownedDefaultExpressions| */,
					204		/* assign V6 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
					214		/* assign V7 = 0 */,
					218		/* assign V8 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					133		/* 'attribute' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					225		/* V02*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					227		/* V03*2-steps || value */,
					180		/* 1*1-steps || value */,
					278		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					185		/* 1*25-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					184		/* 1*20-steps || value */,
					232		/* V04*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					242		/* V05*8-steps || value */,
					180		/* 1*1-steps || value */,
					149		/* 'initial' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					243		/* V06*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					253		/* V07*8-steps || value */,
					180		/* 1*1-steps || value */,
					139		/* 'derivation' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					254		/* V08*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						1	/* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::AttributeCS(basecs::AttributeCS): { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[102] =
			new SerializationRule("AttributeCS", 3,
				createSerializationMatchSteps(
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					162		/* assign V2 = |StructuralFeatureCS::default| */,
					130		/* assign V1 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					99		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
					52		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
					171		/* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
					192		/* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					133		/* 'attribute' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					231		/* V03*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					233		/* V04*2-steps || value */,
					180		/* 1*1-steps || value */,
					278		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						1	/* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::AttributeCS(basecs::AttributeCS): { { qualifiers+="definition" qualifiers+="static"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } }
		serializationRules[103] =
			new SerializationRule("AttributeCS", 3,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					251		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					199		/* assign V5 = |ModelElementCS::ownedAnnotations| */,
					162		/* assign V2 = |StructuralFeatureCS::default| */,
					130		/* assign V1 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					99		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
					52		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
					171		/* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
					192		/* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
					208		/* assign V6 = |StructuralFeatureCS::ownedDefaultExpressions| */,
					212		/* assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
					218		/* assign V8 = 0 */,
					224		/* assign V9 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					133		/* 'attribute' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					231		/* V03*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					233		/* V04*2-steps || value */,
					180		/* 1*1-steps || value */,
					278		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					185		/* 1*25-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					184		/* 1*20-steps || value */,
					237		/* V05*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					247		/* V06*8-steps || value */,
					180		/* 1*1-steps || value */,
					149		/* 'initial' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					248		/* V07*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					257		/* V08*8-steps || value */,
					180		/* 1*1-steps || value */,
					139		/* 'derivation' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					258		/* V09*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						1	/* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::AttributeCS(basecs::AttributeCS): { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[104] =
			new SerializationRule("AttributeCS", 3,
				createSerializationMatchSteps(
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					162		/* assign V2 = |StructuralFeatureCS::default| */,
					130		/* assign V1 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					98		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
					53		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
					171		/* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
					192		/* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					133		/* 'attribute' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					231		/* V03*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					233		/* V04*2-steps || value */,
					180		/* 1*1-steps || value */,
					278		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						1	/* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ZERO_OR_ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::AttributeCS(basecs::AttributeCS): { { qualifiers+="static" qualifiers+="definition"[?] } "attribute" name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] } "}" } }
		serializationRules[105] =
			new SerializationRule("AttributeCS", 3,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					251		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					199		/* assign V5 = |ModelElementCS::ownedAnnotations| */,
					162		/* assign V2 = |StructuralFeatureCS::default| */,
					130		/* assign V1 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					98		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
					53		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
					171		/* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
					192		/* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
					208		/* assign V6 = |StructuralFeatureCS::ownedDefaultExpressions| */,
					212		/* assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
					218		/* assign V8 = 0 */,
					224		/* assign V9 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					133		/* 'attribute' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					231		/* V03*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					233		/* V04*2-steps || value */,
					180		/* 1*1-steps || value */,
					278		/* TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					185		/* 1*25-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					184		/* 1*20-steps || value */,
					237		/* V05*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					247		/* V06*8-steps || value */,
					180		/* 1*1-steps || value */,
					149		/* 'initial' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					248		/* V07*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					257		/* V08*8-steps || value */,
					180		/* 1*1-steps || value */,
					139		/* 'derivation' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					258		/* V09*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						1	/* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ZERO_OR_ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" }
		serializationRules[106] =
			new SerializationRule("DataTypeCS", 15,
				createSerializationMatchSteps(
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					157		/* assign V2 = |ClassCS::instanceClassName| */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					76		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
					172		/* assign V3 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					164		/* 'primitive' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					137		/* 'datatype' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					230		/* V03*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					180		/* 1*1-steps || value */,
					106		/* '!serializable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						19	/* 'primitive' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(19 /* 'primitive' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[107] =
			new SerializationRule("DataTypeCS", 15,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					232		/* check-rule basecs::ClassCS.ownedConstraints : 41 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					197		/* assign V5 = |ClassCS::ownedConstraints| */,
					188		/* assign V4 = |ModelElementCS::ownedAnnotations| */,
					157		/* assign V2 = |ClassCS::instanceClassName| */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					76		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
					172		/* assign V3 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					164		/* 'primitive' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					137		/* 'datatype' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					230		/* V03*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					180		/* 1*1-steps || value */,
					106		/* '!serializable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					194		/* 1*9-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					188		/* 1*4-steps || value */,
					232		/* V04*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					237		/* V05*1-steps || value */,
					18		/* ClassCS::ownedConstraints+=41 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						19	/* 'primitive' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						13) /* InvariantConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(19 /* 'primitive' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" }
		serializationRules[108] =
			new SerializationRule("DataTypeCS", 15,
				createSerializationMatchSteps(
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					157		/* assign V2 = |ClassCS::instanceClassName| */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					76		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
					172		/* assign V3 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					164		/* 'primitive' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					137		/* 'datatype' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					229		/* V03*4-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						19	/* 'primitive' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(19 /* 'primitive' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[109] =
			new SerializationRule("DataTypeCS", 15,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					232		/* check-rule basecs::ClassCS.ownedConstraints : 41 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					197		/* assign V5 = |ClassCS::ownedConstraints| */,
					188		/* assign V4 = |ModelElementCS::ownedAnnotations| */,
					157		/* assign V2 = |ClassCS::instanceClassName| */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					76		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
					172		/* assign V3 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					164		/* 'primitive' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					137		/* 'datatype' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					229		/* V03*4-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					194		/* 1*9-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					188		/* 1*4-steps || value */,
					232		/* V04*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					237		/* V05*1-steps || value */,
					18		/* ClassCS::ownedConstraints+=41 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						19	/* 'primitive' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						13) /* InvariantConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(19 /* 'primitive' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" }
		serializationRules[110] =
			new SerializationRule("DataTypeCS", 15,
				createSerializationMatchSteps(
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					157		/* assign V2 = |ClassCS::instanceClassName| */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					76		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
					166		/* assign V3 = (|DataTypeCS::isSerializable.'serializable'| > 0) */,
					186		/* assign V4 = |DataTypeCS::isSerializable.'serializable'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					164		/* 'primitive' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					137		/* 'datatype' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					230		/* V03*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					232		/* V04*1-steps || value */,
					168		/* 'serializable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						19	/* 'primitive' */
					),
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
						20	/* 'serializable' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(19 /* 'primitive' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(20 /* 'serializable' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive="primitive"[?] "datatype" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[111] =
			new SerializationRule("DataTypeCS", 15,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					232		/* check-rule basecs::ClassCS.ownedConstraints : 41 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					206		/* assign V6 = |ClassCS::ownedConstraints| */,
					199		/* assign V5 = |ModelElementCS::ownedAnnotations| */,
					157		/* assign V2 = |ClassCS::instanceClassName| */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					76		/* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
					166		/* assign V3 = (|DataTypeCS::isSerializable.'serializable'| > 0) */,
					186		/* assign V4 = |DataTypeCS::isSerializable.'serializable'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					164		/* 'primitive' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					137		/* 'datatype' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					230		/* V03*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					232		/* V04*1-steps || value */,
					168		/* 'serializable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					194		/* 1*9-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					188		/* 1*4-steps || value */,
					237		/* V05*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					243		/* V06*1-steps || value */,
					18		/* ClassCS::ownedConstraints+=41 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						19	/* 'primitive' */
					),
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
						20	/* 'serializable' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						13) /* InvariantConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(19 /* 'primitive' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(20 /* 'serializable' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::DetailCS(basecs::DetailCS): { name=(UnrestrictedName|SINGLE_QUOTED_STRING) "=" values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
		serializationRules[112] =
			new SerializationRule("DetailCS", 16,
				createSerializationMatchSteps(
					77		/* assign V0 = |DetailCS::values| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					264		/* NamedElementCS::name=127|87 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					105		/* DetailCS::values+=87|48 || soft-space value soft-space */
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
		// OCLinEcore::DocumentationCS(basecs::DocumentationCS): { "documentation" value=SINGLE_QUOTED_STRING[?] { "(" ownedDetails+=DetailCS { "," ownedDetails+=DetailCS }[*] ")" }[?] ";" }
		serializationRules[113] =
			new SerializationRule("DocumentationCS", 17,
				createSerializationMatchSteps(
					231		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					78		/* assign V0 = |DocumentationCS::value| */,
					103		/* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
					148		/* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					140		/* 'documentation' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					103		/* DocumentationCS::value=87 || soft-space value soft-space */,
					210		/* V01*9-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						6) /* DetailCS */
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
		// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] ";" }
		serializationRules[114] =
			new SerializationRule("EnumerationCS", 21,
				createSerializationMatchSteps(
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					114		/* assign V1 = |ClassCS::instanceClassName| */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					155		/* assign V2 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					144		/* 'enum' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					224		/* V02*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					180		/* 1*1-steps || value */,
					106		/* '!serializable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "!serializable" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[115] =
			new SerializationRule("EnumerationCS", 21,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					232		/* check-rule basecs::ClassCS.ownedConstraints : 41 */,
					235		/* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					197		/* assign V5 = |ClassCS::ownedConstraints| */,
					187		/* assign V4 = |EnumerationCS::ownedLiterals| */,
					176		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
					114		/* assign V1 = |ClassCS::instanceClassName| */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					155		/* assign V2 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					144		/* 'enum' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					224		/* V02*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					180		/* 1*1-steps || value */,
					106		/* '!serializable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					181		/* 1*11-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					191		/* 1*6-steps || value */,
					226		/* V03*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					232		/* V04*1-steps || value */,
					46		/* EnumerationCS::ownedLiterals+=22 || value */,
					237		/* V05*1-steps || value */,
					18		/* ClassCS::ownedConstraints+=41 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						13) /* InvariantConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
						8) /* EnumerationLiteralCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] ";" }
		serializationRules[116] =
			new SerializationRule("EnumerationCS", 21,
				createSerializationMatchSteps(
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					114		/* assign V1 = |ClassCS::instanceClassName| */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					155		/* assign V2 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					144		/* 'enum' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[117] =
			new SerializationRule("EnumerationCS", 21,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					232		/* check-rule basecs::ClassCS.ownedConstraints : 41 */,
					235		/* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					197		/* assign V5 = |ClassCS::ownedConstraints| */,
					187		/* assign V4 = |EnumerationCS::ownedLiterals| */,
					176		/* assign V3 = |ModelElementCS::ownedAnnotations| */,
					114		/* assign V1 = |ClassCS::instanceClassName| */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					155		/* assign V2 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					144		/* 'enum' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					181		/* 1*11-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					191		/* 1*6-steps || value */,
					226		/* V03*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					232		/* V04*1-steps || value */,
					46		/* EnumerationCS::ownedLiterals+=22 || value */,
					237		/* V05*1-steps || value */,
					18		/* ClassCS::ownedConstraints+=41 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						13) /* InvariantConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
						8) /* EnumerationLiteralCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] ";" }
		serializationRules[118] =
			new SerializationRule("EnumerationCS", 21,
				createSerializationMatchSteps(
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					114		/* assign V1 = |ClassCS::instanceClassName| */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					149		/* assign V2 = (|EnumerationCS::isSerializable.'serializable'| > 0) */,
					175		/* assign V3 = |EnumerationCS::isSerializable.'serializable'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					144		/* 'enum' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					224		/* V02*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					226		/* V03*1-steps || value */,
					168		/* 'serializable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
						20	/* 'serializable' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(20 /* 'serializable' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { "enum" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isSerializable="serializable"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[119] =
			new SerializationRule("EnumerationCS", 21,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					232		/* check-rule basecs::ClassCS.ownedConstraints : 41 */,
					235		/* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					206		/* assign V6 = |ClassCS::ownedConstraints| */,
					198		/* assign V5 = |EnumerationCS::ownedLiterals| */,
					188		/* assign V4 = |ModelElementCS::ownedAnnotations| */,
					114		/* assign V1 = |ClassCS::instanceClassName| */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					149		/* assign V2 = (|EnumerationCS::isSerializable.'serializable'| > 0) */,
					175		/* assign V3 = |EnumerationCS::isSerializable.'serializable'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					144		/* 'enum' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					224		/* V02*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					226		/* V03*1-steps || value */,
					168		/* 'serializable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					181		/* 1*11-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					191		/* 1*6-steps || value */,
					232		/* V04*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					237		/* V05*1-steps || value */,
					46		/* EnumerationCS::ownedLiterals+=22 || value */,
					243		/* V06*1-steps || value */,
					18		/* ClassCS::ownedConstraints+=41 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
						20	/* 'serializable' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						13) /* InvariantConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
						8) /* EnumerationLiteralCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(20 /* 'serializable' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" }
		serializationRules[120] =
			new SerializationRule("EnumerationLiteralCS", 22,
				createSerializationMatchSteps(
					117		/* assign V1 = |EnumerationLiteralCS::value| */,
					79		/* assign V0 = |EnumerationLiteralCS::literal| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					265		/* NamedElementCS::name=23 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					3		/* EnumerationLiteralCS::literal=87 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					104		/* EnumerationLiteralCS::value=85 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				null);
			;
		// OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } }
		serializationRules[121] =
			new SerializationRule("EnumerationLiteralCS", 22,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					160		/* assign V2 = |ModelElementCS::ownedAnnotations| */,
					117		/* assign V1 = |EnumerationLiteralCS::value| */,
					79		/* assign V0 = |EnumerationLiteralCS::literal| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					265		/* NamedElementCS::name=23 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					3		/* EnumerationLiteralCS::literal=87 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					104		/* EnumerationLiteralCS::value=85 || soft-space value soft-space */,
					191		/* 1*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					219		/* V02*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] ";" }
		serializationRules[122] =
			new SerializationRule("EnumerationLiteralCS", 22,
				createSerializationMatchSteps(
					117		/* assign V1 = |EnumerationLiteralCS::value| */,
					79		/* assign V0 = |EnumerationLiteralCS::literal| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					155		/* 'literal' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					3		/* EnumerationLiteralCS::literal=87 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					104		/* EnumerationLiteralCS::value=85 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				null);
			;
		// OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { { "literal" name=UnrestrictedName } { ":" literal=SINGLE_QUOTED_STRING }[?] { "=" value=SIGNED }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" } }
		serializationRules[123] =
			new SerializationRule("EnumerationLiteralCS", 22,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					160		/* assign V2 = |ModelElementCS::ownedAnnotations| */,
					117		/* assign V1 = |EnumerationLiteralCS::value| */,
					79		/* assign V0 = |EnumerationLiteralCS::literal| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					155		/* 'literal' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					3		/* EnumerationLiteralCS::literal=87 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					104		/* EnumerationLiteralCS::value=85 || soft-space value soft-space */,
					191		/* 1*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					219		/* V02*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::ImplicitOppositeCS(basecs::ImplicitOppositeCS): { "opposite" name=UnrestrictedName ":" ownedType=TypedMultiplicityRefCS { "{" { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] "}" }[?] }
		serializationRules[124] =
			new SerializationRule("ImplicitOppositeCS", 37,
				createSerializationMatchSteps(
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					51		/* assert (|TypedElementCS::ownedType| - 1) == 0 */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					69		/* assign V0 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
					131		/* assign V1 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					159		/* 'opposite' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					201		/* V00*7-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					204		/* V01*2-steps || value */,
					180		/* 1*1-steps || value */,
					280		/* TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						3	/* '!ordered|!unique|ordered|unique' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
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
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(3 /* '!ordered|!unique|ordered|unique' */, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::ImportCS(basecs::ImportCS): { {"import"|"library"} { name=UnrestrictedName ":" }[?] ownedPathName=URIPathNameCS isAll="::*"[?] ";" }
		serializationRules[125] =
			new SerializationRule("ImportCS", 38,
				createSerializationMatchSteps(
					236		/* check-rule basecs::ImportCS.ownedPathName : 123 */,
					118		/* assign V1 = |ImportCS::isAll.'::*'| */,
					17		/* assert (|ImportCS::ownedPathName| - 1) == 0 */,
					85		/* assign V0 = |NamedElementCS::name| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					147		/* 'import' || value */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					63		/* ImportCS::ownedPathName=123 || value */,
					203		/* V01*1-steps || value */,
					117		/* '::*' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
						6	/* '::*' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						66) /* URIPathNameCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(6 /* '::*' */, GrammarCardinality.ZERO_OR_ONE)
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
						new RuleIndex_GrammarCardinality(123, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::InvariantConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { isCallable="callable"[?] stereotype="invariant" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ";" }
		serializationRules[126] =
			new SerializationRule("InvariantConstraintCS", 41,
				createSerializationMatchSteps(
					233		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
					120		/* assign V1 = |NamedElementCS::name| */,
					7		/* assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0 */,
					89		/* assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'| */,
					158		/* assign V2 = |ConstraintCS::ownedMessageSpecification| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					135		/* 'callable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					152		/* 'invariant' || soft-space value soft-space */,
					209		/* V01*8-steps || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					223		/* V02*5-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					47		/* ConstraintCS::ownedMessageSpecification=92 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
						12	/* 'callable' */
					),
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						16	/* 'invariant' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						38) /* SpecificationCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(12 /* 'callable' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(16 /* 'invariant' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::InvariantConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { isCallable="callable"[?] stereotype="invariant" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] { ":" ownedSpecification=SpecificationCS[?] ";" } }
		serializationRules[127] =
			new SerializationRule("InvariantConstraintCS", 41,
				createSerializationMatchSteps(
					233		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
					234		/* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
					174		/* assign V3 = |ConstraintCS::ownedSpecification| */,
					120		/* assign V1 = |NamedElementCS::name| */,
					7		/* assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0 */,
					89		/* assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'| */,
					158		/* assign V2 = |ConstraintCS::ownedMessageSpecification| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					135		/* 'callable' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					152		/* 'invariant' || soft-space value soft-space */,
					209		/* V01*8-steps || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					223		/* V02*5-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					47		/* ConstraintCS::ownedMessageSpecification=92 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					191		/* 1*6-steps || value */,
					180		/* 1*1-steps || value */,
					115		/* ':' || no-space value soft-space */,
					226		/* V03*1-steps || value */,
					76		/* ConstraintCS::ownedSpecification=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
						12	/* 'callable' */
					),
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						16	/* 'invariant' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						38) /* SpecificationCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(12 /* 'callable' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(16 /* 'invariant' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
	}
	private void initSerializationRules2() {
		// OCLinEcore::ModelElementRefCS(basecs::ModelElementRefCS): { "reference" ownedPathName=PathNameCS ";" }
		serializationRules[128] =
			new SerializationRule("ModelElementRefCS", 54,
				createSerializationMatchSteps(
					238		/* check-rule basecs::ModelElementRefCS.ownedPathName : 73 */,
					26		/* assert (|ModelElementRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					166		/* 'reference' || soft-space value soft-space */,
					64		/* ModelElementRefCS::ownedPathName=73 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::OperationCS(basecs::OperationCS): { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" }
		serializationRules[129] =
			new SerializationRule("OperationCS", 70,
				createSerializationMatchSteps(
					240		/* check-rule basecs::OperationCS.ownedExceptions : 117 */,
					241		/* check-rule basecs::OperationCS.ownedParameters : 72 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					179		/* assign V3 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					205		/* assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
					216		/* assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
					182		/* assign V4 = (|OperationCS::ownedExceptions| > 0) */,
					193		/* assign V5 = (|OperationCS::ownedExceptions| - 1) */,
					108		/* assign V1 = (|OperationCS::ownedParameters| > 0) */,
					150		/* assign V2 = (|OperationCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					158		/* 'operation' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					207		/* V01*5-steps || value */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					228		/* V03*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					236		/* V04*7-steps || value */,
					180		/* 1*1-steps || value */,
					172		/* 'throws' || soft-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					239		/* V05*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					246		/* V06*7-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					250		/* V07*2-steps || value */,
					180		/* 1*1-steps || value */,
					279		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						2	/* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						61) /* TypedRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						26) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
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
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::OperationCS(basecs::OperationCS): { "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } }
		serializationRules[130] =
			new SerializationRule("OperationCS", 70,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					239		/* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
					240		/* check-rule basecs::OperationCS.ownedExceptions : 117 */,
					241		/* check-rule basecs::OperationCS.ownedParameters : 72 */,
					242		/* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
					243		/* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					144		/* assign V12 = |OperationCS::ownedPostconditions| */,
					226		/* assign V9 = |OperationCS::ownedPreconditions| */,
					219		/* assign V8 = |ModelElementCS::ownedAnnotations| */,
					179		/* assign V3 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					95		/* assign V0 = |TemplateableElementCS::ownedSignature| */,
					134		/* assign V10 = (|OperationCS::ownedBodyExpressions| > 0) */,
					141		/* assign V11 = |OperationCS::ownedBodyExpressions| */,
					205		/* assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
					216		/* assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
					182		/* assign V4 = (|OperationCS::ownedExceptions| > 0) */,
					193		/* assign V5 = (|OperationCS::ownedExceptions| - 1) */,
					108		/* assign V1 = (|OperationCS::ownedParameters| > 0) */,
					150		/* assign V2 = (|OperationCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					158		/* 'operation' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					207		/* V01*5-steps || value */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					228		/* V03*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					236		/* V04*7-steps || value */,
					180		/* 1*1-steps || value */,
					172		/* 'throws' || soft-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					239		/* V05*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					246		/* V06*7-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					250		/* V07*2-steps || value */,
					180		/* 1*1-steps || value */,
					279		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					184		/* 1*20-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					183		/* 1*15-steps || value */,
					254		/* V08*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					258		/* V09*1-steps || value */,
					70		/* OperationCS::ownedPreconditions+=76 || value */,
					212		/* V10*8-steps || value */,
					180		/* 1*1-steps || value */,
					134		/* 'body' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					115		/* ':' || no-space value soft-space */,
					213		/* V11*1-steps || value */,
					12		/* OperationCS::ownedBodyExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					215		/* V12*1-steps || value */,
					69		/* OperationCS::ownedPostconditions+=75 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						2	/* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						61) /* TypedRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						26) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						30) /* PostconditionConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						31) /* PreconditionConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
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
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(75, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::OperationCS(basecs::OperationCS): { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" }
		serializationRules[131] =
			new SerializationRule("OperationCS", 70,
				createSerializationMatchSteps(
					240		/* check-rule basecs::OperationCS.ownedExceptions : 117 */,
					241		/* check-rule basecs::OperationCS.ownedParameters : 72 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					190		/* assign V4 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					99		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
					52		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
					194		/* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
					202		/* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
					151		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
					168		/* assign V3 = (|OperationCS::ownedParameters| - 1) */,
					213		/* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
					222		/* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					158		/* 'operation' || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					223		/* V02*5-steps || value */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					228		/* V03*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					234		/* V04*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					241		/* V05*7-steps || value */,
					180		/* 1*1-steps || value */,
					172		/* 'throws' || soft-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					245		/* V06*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					252		/* V07*7-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					255		/* V08*2-steps || value */,
					180		/* 1*1-steps || value */,
					279		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						2	/* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						61) /* TypedRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						26) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
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
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::OperationCS(basecs::OperationCS): { { qualifiers+="definition" qualifiers+="static"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } }
		serializationRules[132] =
			new SerializationRule("OperationCS", 70,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					239		/* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
					240		/* check-rule basecs::OperationCS.ownedExceptions : 117 */,
					241		/* check-rule basecs::OperationCS.ownedParameters : 72 */,
					242		/* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
					243		/* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					146		/* assign V13 = |OperationCS::ownedPostconditions| */,
					138		/* assign V10 = |OperationCS::ownedPreconditions| */,
					225		/* assign V9 = |ModelElementCS::ownedAnnotations| */,
					190		/* assign V4 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					99		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
					52		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
					139		/* assign V11 = (|OperationCS::ownedBodyExpressions| > 0) */,
					143		/* assign V12 = |OperationCS::ownedBodyExpressions| */,
					194		/* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
					202		/* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
					151		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
					168		/* assign V3 = (|OperationCS::ownedParameters| - 1) */,
					213		/* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
					222		/* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					158		/* 'operation' || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					223		/* V02*5-steps || value */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					228		/* V03*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					234		/* V04*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					241		/* V05*7-steps || value */,
					180		/* 1*1-steps || value */,
					172		/* 'throws' || soft-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					245		/* V06*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					252		/* V07*7-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					255		/* V08*2-steps || value */,
					180		/* 1*1-steps || value */,
					279		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					184		/* 1*20-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					183		/* 1*15-steps || value */,
					258		/* V09*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					211		/* V10*1-steps || value */,
					70		/* OperationCS::ownedPreconditions+=76 || value */,
					214		/* V11*8-steps || value */,
					180		/* 1*1-steps || value */,
					134		/* 'body' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					115		/* ':' || no-space value soft-space */,
					215		/* V12*1-steps || value */,
					12		/* OperationCS::ownedBodyExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					217		/* V13*1-steps || value */,
					69		/* OperationCS::ownedPostconditions+=75 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						2	/* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						61) /* TypedRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						26) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						30) /* PostconditionConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						31) /* PreconditionConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
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
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(75, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::OperationCS(basecs::OperationCS): { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] ";" }
		serializationRules[133] =
			new SerializationRule("OperationCS", 70,
				createSerializationMatchSteps(
					240		/* check-rule basecs::OperationCS.ownedExceptions : 117 */,
					241		/* check-rule basecs::OperationCS.ownedParameters : 72 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					190		/* assign V4 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					98		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
					53		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
					194		/* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
					202		/* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
					151		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
					168		/* assign V3 = (|OperationCS::ownedParameters| - 1) */,
					213		/* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
					222		/* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					158		/* 'operation' || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					223		/* V02*5-steps || value */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					228		/* V03*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					234		/* V04*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					241		/* V05*7-steps || value */,
					180		/* 1*1-steps || value */,
					172		/* 'throws' || soft-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					245		/* V06*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					252		/* V07*7-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					255		/* V08*2-steps || value */,
					180		/* 1*1-steps || value */,
					279		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						2	/* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						61) /* TypedRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						26) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
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
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ZERO_OR_ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::OperationCS(basecs::OperationCS): { { qualifiers+="static" qualifiers+="definition"[?] } "operation" ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName "(" { ownedParameters+=ParameterCS { "," ownedParameters+=ParameterCS }[*] }[?] ")" { ":" ownedType=TypedMultiplicityRefCS }[?] { "throws" ownedExceptions+=TypedRefCS { "," ownedExceptions+=TypedRefCS }[*] }[?] { "{" { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { "body" ":" ownedBodyExpressions+=SpecificationCS[?] ";" }[*] ownedPostconditions+=PostconditionConstraintCS[*] } "}" } }
		serializationRules[134] =
			new SerializationRule("OperationCS", 70,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					239		/* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
					240		/* check-rule basecs::OperationCS.ownedExceptions : 117 */,
					241		/* check-rule basecs::OperationCS.ownedParameters : 72 */,
					242		/* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
					243		/* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					146		/* assign V13 = |OperationCS::ownedPostconditions| */,
					138		/* assign V10 = |OperationCS::ownedPreconditions| */,
					225		/* assign V9 = |ModelElementCS::ownedAnnotations| */,
					190		/* assign V4 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					98		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
					53		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
					139		/* assign V11 = (|OperationCS::ownedBodyExpressions| > 0) */,
					143		/* assign V12 = |OperationCS::ownedBodyExpressions| */,
					194		/* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
					202		/* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
					151		/* assign V2 = (|OperationCS::ownedParameters| > 0) */,
					168		/* assign V3 = (|OperationCS::ownedParameters| - 1) */,
					213		/* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
					222		/* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					158		/* 'operation' || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					223		/* V02*5-steps || value */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					228		/* V03*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					54		/* OperationCS::ownedParameters+=72 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					234		/* V04*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					241		/* V05*7-steps || value */,
					180		/* 1*1-steps || value */,
					172		/* 'throws' || soft-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					245		/* V06*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					25		/* OperationCS::ownedExceptions+=117 || value */,
					252		/* V07*7-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					255		/* V08*2-steps || value */,
					180		/* 1*1-steps || value */,
					279		/* TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					184		/* 1*20-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					183		/* 1*15-steps || value */,
					258		/* V09*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					211		/* V10*1-steps || value */,
					70		/* OperationCS::ownedPreconditions+=76 || value */,
					214		/* V11*8-steps || value */,
					180		/* 1*1-steps || value */,
					134		/* 'body' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					115		/* ':' || no-space value soft-space */,
					215		/* V12*1-steps || value */,
					12		/* OperationCS::ownedBodyExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					217		/* V13*1-steps || value */,
					69		/* OperationCS::ownedPostconditions+=75 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						2	/* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						61) /* TypedRefCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						26) /* ParameterCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						30) /* PostconditionConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						31) /* PreconditionConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
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
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ZERO_OR_ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(75, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::PackageCS(basecs::PackageCS): { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] ";" }
		serializationRules[135] =
			new SerializationRule("PackageCS", 71,
				createSerializationMatchSteps(
					123		/* assign V1 = |PackageCS::nsURI| */,
					90		/* assign V0 = |PackageCS::nsPrefix| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					160		/* 'package' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					7		/* PackageCS::nsPrefix=127 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					8		/* PackageCS::nsURI=121 || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
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
				null);
			;
		// OCLinEcore::PackageCS(basecs::PackageCS): { "package" name=UnrestrictedName { ":" nsPrefix=UnrestrictedName }[?] { "=" nsURI=URI }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] } "}" } }
		serializationRules[136] =
			new SerializationRule("PackageCS", 71,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					244		/* check-rule basecs::PackageCS.ownedClasses : 6 */,
					245		/* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
					189		/* assign V4 = |PackageCS::ownedClasses| */,
					177		/* assign V3 = |PackageOwnerCS::ownedPackages| */,
					160		/* assign V2 = |ModelElementCS::ownedAnnotations| */,
					123		/* assign V1 = |PackageCS::nsURI| */,
					90		/* assign V0 = |PackageCS::nsPrefix| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					160		/* 'package' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					7		/* PackageCS::nsPrefix=127 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					8		/* PackageCS::nsURI=121 || soft-space value soft-space */,
					181		/* 1*11-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					191		/* 1*6-steps || value */,
					219		/* V02*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					226		/* V03*1-steps || value */,
					53		/* PackageOwnerCS::ownedPackages+=71 || value */,
					232		/* V04*1-steps || value */,
					13		/* PackageCS::ownedClasses+=6 || half-new-line value half-new-line */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						1) /* ClassCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						25) /* PackageCS */
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
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(6, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(71, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::ParameterCS(basecs::ParameterCS): { name=UnrestrictedName { ":" ownedType=TypedMultiplicityRefCS }[?] { "{" { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] "}" }[?] { "{" ownedAnnotations+=AnnotationElementCS[*] "}" }[?] }
		serializationRules[137] =
			new SerializationRule("ParameterCS", 72,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					97		/* assign V0 = |TypedElementCS::ownedType| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					167		/* assign V3 = (|ModelElementCS::ownedAnnotations| > 0) */,
					188		/* assign V4 = |ModelElementCS::ownedAnnotations| */,
					112		/* assign V1 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
					164		/* assign V2 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					208		/* V01*7-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					220		/* V02*2-steps || value */,
					180		/* 1*1-steps || value */,
					280		/* TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					230		/* V03*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					232		/* V04*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						3	/* '!ordered|!unique|ordered|unique' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
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
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(3 /* '!ordered|!unique|ordered|unique' */, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::PostconditionConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { stereotype="postcondition" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS[?] ";" }
		serializationRules[138] =
			new SerializationRule("PostconditionConstraintCS", 75,
				createSerializationMatchSteps(
					233		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
					234		/* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
					159		/* assign V2 = |ConstraintCS::ownedSpecification| */,
					85		/* assign V0 = |NamedElementCS::name| */,
					8		/* assert (|ConstraintCS::stereotype.'postcondition'| - 1) == 0 */,
					116		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					161		/* 'postcondition' || soft-space value soft-space */,
					202		/* V00*8-steps || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					207		/* V01*5-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					47		/* ConstraintCS::ownedMessageSpecification=92 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					180		/* 1*1-steps || value */,
					115		/* ':' || no-space value soft-space */,
					219		/* V02*1-steps || value */,
					76		/* ConstraintCS::ownedSpecification=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						17	/* 'postcondition' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						38) /* SpecificationCS */
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
							createEnumerationValue_GrammarCardinality(17 /* 'postcondition' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::PreconditionConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { stereotype="precondition" { name=UnrestrictedName { "(" ownedMessageSpecification=SpecificationCS ")" }[?] }[?] ":" ownedSpecification=SpecificationCS[?] ";" }
		serializationRules[139] =
			new SerializationRule("PreconditionConstraintCS", 76,
				createSerializationMatchSteps(
					233		/* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
					234		/* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
					159		/* assign V2 = |ConstraintCS::ownedSpecification| */,
					85		/* assign V0 = |NamedElementCS::name| */,
					9		/* assert (|ConstraintCS::stereotype.'precondition'| - 1) == 0 */,
					116		/* assign V1 = |ConstraintCS::ownedMessageSpecification| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					163		/* 'precondition' || soft-space value soft-space */,
					202		/* V00*8-steps || value */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					207		/* V01*5-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					47		/* ConstraintCS::ownedMessageSpecification=92 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					180		/* 1*1-steps || value */,
					115		/* ':' || no-space value soft-space */,
					219		/* V02*1-steps || value */,
					76		/* ConstraintCS::ownedSpecification=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
						18	/* 'precondition' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						38) /* SpecificationCS */
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
							createEnumerationValue_GrammarCardinality(18 /* 'precondition' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[140] =
			new SerializationRule("ReferenceCS", 83,
				createSerializationMatchSteps(
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					162		/* assign V2 = |StructuralFeatureCS::default| */,
					130		/* assign V1 = |TypedElementCS::ownedType| */,
					92		/* assign V0 = |ReferenceCS::referredOpposite| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					170		/* assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
					191		/* assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					165		/* 'property' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					107		/* '#' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					95		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					231		/* V03*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					233		/* V04*2-steps || value */,
					180		/* 1*1-steps || value */,
					277		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						0	/* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } }
		serializationRules[141] =
			new SerializationRule("ReferenceCS", 83,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					251		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
					249		/* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					145		/* assign V12 = |ReferenceCS::ownedImplicitOpposites| */,
					199		/* assign V5 = |ModelElementCS::ownedAnnotations| */,
					162		/* assign V2 = |StructuralFeatureCS::default| */,
					130		/* assign V1 = |TypedElementCS::ownedType| */,
					92		/* assign V0 = |ReferenceCS::referredOpposite| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					203		/* assign V6 = (|ReferenceCS::referredKeys| > 0) */,
					210		/* assign V7 = (|ReferenceCS::referredKeys| - 1) */,
					170		/* assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
					191		/* assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
					220		/* assign V8 = |StructuralFeatureCS::ownedDefaultExpressions| */,
					223		/* assign V9 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
					136		/* assign V10 = 0 */,
					140		/* assign V11 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					165		/* 'property' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					199		/* V00*4-steps || value */,
					180		/* 1*1-steps || value */,
					107		/* '#' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					95		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
					205		/* V01*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					222		/* V02*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					231		/* V03*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					233		/* V04*2-steps || value */,
					180		/* 1*1-steps || value */,
					277		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					189		/* 1*41-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					187		/* 1*36-steps || value */,
					237		/* V05*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					244		/* V06*11-steps || value */,
					180		/* 1*1-steps || value */,
					153		/* 'key' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					94		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
					251		/* V07*4-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					180		/* 1*1-steps || value */,
					94		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					257		/* V08*8-steps || value */,
					180		/* 1*1-steps || value */,
					149		/* 'initial' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					258		/* V09*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					212		/* V10*8-steps || value */,
					180		/* 1*1-steps || value */,
					139		/* 'derivation' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					213		/* V11*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					216		/* V12*3-steps || value */,
					35		/* ReferenceCS::ownedImplicitOpposites+=37 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						0	/* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
						11) /* ImplicitOppositeCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(37, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[142] =
			new SerializationRule("ReferenceCS", 83,
				createSerializationMatchSteps(
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					178		/* assign V3 = |StructuralFeatureCS::default| */,
					163		/* assign V2 = |TypedElementCS::ownedType| */,
					124		/* assign V1 = |ReferenceCS::referredOpposite| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					99		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
					52		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
					183		/* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
					201		/* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					165		/* 'property' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					107		/* '#' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					95		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					229		/* V03*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					236		/* V04*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					238		/* V05*2-steps || value */,
					180		/* 1*1-steps || value */,
					277		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						0	/* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { { qualifiers+="definition" qualifiers+="static"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } }
		serializationRules[143] =
			new SerializationRule("ReferenceCS", 83,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					251		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
					249		/* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					147		/* assign V13 = |ReferenceCS::ownedImplicitOpposites| */,
					207		/* assign V6 = |ModelElementCS::ownedAnnotations| */,
					178		/* assign V3 = |StructuralFeatureCS::default| */,
					163		/* assign V2 = |TypedElementCS::ownedType| */,
					124		/* assign V1 = |ReferenceCS::referredOpposite| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					99		/* assign V0 = |TypedElementCS::qualifiers.'static'| */,
					52		/* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
					211		/* assign V7 = (|ReferenceCS::referredKeys| > 0) */,
					217		/* assign V8 = (|ReferenceCS::referredKeys| - 1) */,
					183		/* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
					201		/* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
					227		/* assign V9 = |StructuralFeatureCS::ownedDefaultExpressions| */,
					135		/* assign V10 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
					140		/* assign V11 = 0 */,
					142		/* assign V12 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					165		/* 'property' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					107		/* '#' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					95		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					229		/* V03*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					236		/* V04*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					238		/* V05*2-steps || value */,
					180		/* 1*1-steps || value */,
					277		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					189		/* 1*41-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					187		/* 1*36-steps || value */,
					243		/* V06*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					249		/* V07*11-steps || value */,
					180		/* 1*1-steps || value */,
					153		/* 'key' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					94		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
					256		/* V08*4-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					180		/* 1*1-steps || value */,
					94		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					259		/* V09*8-steps || value */,
					180		/* 1*1-steps || value */,
					149		/* 'initial' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					211		/* V10*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					214		/* V11*8-steps || value */,
					180		/* 1*1-steps || value */,
					139		/* 'derivation' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					215		/* V12*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					218		/* V13*3-steps || value */,
					35		/* ReferenceCS::ownedImplicitOpposites+=37 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						0	/* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
						11) /* ImplicitOppositeCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(37, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] ";" }
		serializationRules[144] =
			new SerializationRule("ReferenceCS", 83,
				createSerializationMatchSteps(
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					178		/* assign V3 = |StructuralFeatureCS::default| */,
					163		/* assign V2 = |TypedElementCS::ownedType| */,
					124		/* assign V1 = |ReferenceCS::referredOpposite| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					98		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
					53		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
					183		/* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
					201		/* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					165		/* 'property' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					107		/* '#' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					95		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					229		/* V03*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					236		/* V04*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					238		/* V05*2-steps || value */,
					180		/* 1*1-steps || value */,
					277		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						0	/* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ZERO_OR_ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { { qualifiers+="static" qualifiers+="definition"[?] } "property" name=UnrestrictedName { "#" referredOpposite=UnrestrictedName }[?] { ":" ownedType=TypedMultiplicityRefCS }[?] { "=" default=SINGLE_QUOTED_STRING }[?] { "{" { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] { "key" referredKeys+=UnrestrictedName { "," referredKeys+=UnrestrictedName }[*] ";" }[*] { "initial" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { "derivation" ":" ownedDefaultExpressions+=SpecificationCS[?] ";" }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ";" }[*] } "}" } }
		serializationRules[145] =
			new SerializationRule("ReferenceCS", 83,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					251		/* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
					249		/* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
					263		/* check-rule basecs::TypedElementCS.ownedType : 116 */,
					147		/* assign V13 = |ReferenceCS::ownedImplicitOpposites| */,
					207		/* assign V6 = |ModelElementCS::ownedAnnotations| */,
					178		/* assign V3 = |StructuralFeatureCS::default| */,
					163		/* assign V2 = |TypedElementCS::ownedType| */,
					124		/* assign V1 = |ReferenceCS::referredOpposite| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					98		/* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
					53		/* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
					211		/* assign V7 = (|ReferenceCS::referredKeys| > 0) */,
					217		/* assign V8 = (|ReferenceCS::referredKeys| - 1) */,
					183		/* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
					201		/* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
					227		/* assign V9 = |StructuralFeatureCS::ownedDefaultExpressions| */,
					135		/* assign V10 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
					140		/* assign V11 = 0 */,
					142		/* assign V12 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					188		/* 1*4-steps || value */,
					180		/* 1*1-steps || value */,
					169		/* 'static' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					138		/* 'definition' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					165		/* 'property' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					206		/* V01*4-steps || value */,
					180		/* 1*1-steps || value */,
					107		/* '#' || no-space value no-space */,
					180		/* 1*1-steps || value */,
					95		/* ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					276		/* TypedElementCS::ownedType=116 || value */,
					229		/* V03*4-steps || value */,
					180		/* 1*1-steps || value */,
					122		/* '=' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					0		/* StructuralFeatureCS::default=87 || soft-space value soft-space */,
					236		/* V04*7-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					238		/* V05*2-steps || value */,
					180		/* 1*1-steps || value */,
					277		/* TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */,
					189		/* 1*41-steps || value */,
					180		/* 1*1-steps || value */,
					173		/* '{' || soft-space value soft-space */,
					187		/* 1*36-steps || value */,
					243		/* V06*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					249		/* V07*11-steps || value */,
					180		/* 1*1-steps || value */,
					153		/* 'key' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					94		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
					256		/* V08*4-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					180		/* 1*1-steps || value */,
					94		/* ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					259		/* V09*8-steps || value */,
					180		/* 1*1-steps || value */,
					149		/* 'initial' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					211		/* V10*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					214		/* V11*8-steps || value */,
					180		/* 1*1-steps || value */,
					139		/* 'derivation' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					215		/* V12*1-steps || value */,
					22		/* StructuralFeatureCS::ownedDefaultExpressions+=92 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					218		/* V13*3-steps || value */,
					35		/* ReferenceCS::ownedImplicitOpposites+=37 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					178		/* '}' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						0	/* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */,
						13	/* 'definition' */,
						21	/* 'static' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						38) /* SpecificationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
						11) /* ImplicitOppositeCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						60) /* TypedMultiplicityRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */, GrammarCardinality.ZERO_OR_MORE),
							createEnumerationValue_GrammarCardinality(13 /* 'definition' */, GrammarCardinality.ZERO_OR_ONE),
							createEnumerationValue_GrammarCardinality(21 /* 'static' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(37, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// OCLinEcore::SpecificationCS(essentialoclcs::ExpSpecificationCS): exprString=UNQUOTED_STRING
		serializationRules[146] =
			new SerializationRule("SpecificationCS", 92,
				createSerializationMatchSteps(
					46		/* assert (|SpecificationCS::exprString| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					1		/* SpecificationCS::exprString=119 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
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
		// OCLinEcore::SpecificationCS(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS
		serializationRules[147] =
			new SerializationRule("SpecificationCS", 92,
				createSerializationMatchSteps(
					283		/* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30 */,
					11		/* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					29		/* ExpSpecificationCS::ownedExpression=30 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
						9) /* ExpCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] ";" }
		serializationRules[148] =
			new SerializationRule("StructuredClassCS", 97,
				createSerializationMatchSteps(
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					254		/* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
					185		/* assign V4 = |ClassCS::instanceClassName| */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					94		/* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
					195		/* assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0) */,
					209		/* assign V6 = |StructuredClassCS::isInterface.'interface'| */,
					152		/* assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
					169		/* assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					131		/* 'abstract' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					136		/* 'class' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					225		/* V02*7-steps || value */,
					180		/* 1*1-steps || value */,
					145		/* 'extends' || soft-space value soft-space */,
					79		/* StructuredClassCS::ownedSuperTypes+=117 || value */,
					228		/* V03*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					79		/* StructuredClassCS::ownedSuperTypes+=117 || value */,
					235		/* V04*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					240		/* V05*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					243		/* V06*1-steps || value */,
					150		/* 'interface' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
						11	/* 'abstract' */
					),
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
						15	/* 'interface' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						61) /* TypedRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(11 /* 'abstract' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(15 /* 'interface' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract="abstract"[?] "class" name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { "extends" ownedSuperTypes+=TypedRefCS { "," ownedSuperTypes+=TypedRefCS }[*] }[?] { ":" instanceClassName=SINGLE_QUOTED_STRING }[?] { "{" isInterface="interface"[?] "}" }[?] { "{" { ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] } "}" } }
		serializationRules[149] =
			new SerializationRule("StructuredClassCS", 97,
				createSerializationMatchSteps(
					237		/* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
					232		/* check-rule basecs::ClassCS.ownedConstraints : 41 */,
					252		/* check-rule basecs::StructuredClassCS.ownedOperations : 70 */,
					253		/* check-rule basecs::StructuredClassCS.ownedProperties : 96 */,
					259		/* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
					254		/* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
					137		/* assign V10 = |ClassCS::ownedConstraints| */,
					228		/* assign V9 = |StructuredClassCS::ownedProperties| */,
					221		/* assign V8 = |StructuredClassCS::ownedOperations| */,
					215		/* assign V7 = |ModelElementCS::ownedAnnotations| */,
					185		/* assign V4 = |ClassCS::instanceClassName| */,
					128		/* assign V1 = |TemplateableElementCS::ownedSignature| */,
					29		/* assert (|NamedElementCS::name| - 1) == 0 */,
					94		/* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
					195		/* assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0) */,
					209		/* assign V6 = |StructuredClassCS::isInterface.'interface'| */,
					152		/* assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
					169		/* assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					195		/* V00*1-steps || value */,
					131		/* 'abstract' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					136		/* 'class' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					262		/* NamedElementCS::name=127 || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					75		/* TemplateableElementCS::ownedSignature=101 || value */,
					225		/* V02*7-steps || value */,
					180		/* 1*1-steps || value */,
					145		/* 'extends' || soft-space value soft-space */,
					79		/* StructuredClassCS::ownedSuperTypes+=117 || value */,
					228		/* V03*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					79		/* StructuredClassCS::ownedSuperTypes+=117 || value */,
					235		/* V04*4-steps || value */,
					180		/* 1*1-steps || value */,
					116		/* ':' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					2		/* ClassCS::instanceClassName=87 || soft-space value soft-space */,
					240		/* V05*6-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					243		/* V06*1-steps || value */,
					150		/* 'interface' || soft-space value soft-space */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */,
					182		/* 1*13-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					193		/* 1*8-steps || value */,
					248		/* V07*1-steps || value */,
					10		/* ModelElementCS::ownedAnnotations+=2 || value */,
					254		/* V08*1-steps || value */,
					52		/* StructuredClassCS::ownedOperations+=70 || value */,
					258		/* V09*1-steps || value */,
					71		/* StructuredClassCS::ownedProperties+=96 || value */,
					211		/* V10*1-steps || value */,
					18		/* ClassCS::ownedConstraints+=41 || value */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
						11	/* 'abstract' */
					),
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
						15	/* 'interface' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						0) /* AnnotationElementCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						13) /* InvariantConstraintCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
						24) /* OperationCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
						41) /* StructuralFeatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						48) /* TemplateSignatureCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						61) /* TypedRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(11 /* 'abstract' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(15 /* 'interface' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(70, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(96, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { "sysml" { "{" { ownedDetails+=DetailCS ";" }[*] "}" } }
		serializationRules[150] =
			new SerializationRule("SysMLCS", 98,
				createSerializationMatchSteps(
					231		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					72		/* assign V0 = |AnnotationElementCS::ownedDetails| */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					170		/* 'sysml' || soft-space value soft-space */,
					193		/* 1*8-steps || value */,
					180		/* 1*1-steps || value */,
					174		/* '{' || soft-space value push soft-new-line */,
					198		/* V00*3-steps || value */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */,
					180		/* 1*1-steps || value */,
					179		/* '}' || pop soft-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						6) /* DetailCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { "sysml" { ownedDetails+=DetailCS ";" } }
		serializationRules[151] =
			new SerializationRule("SysMLCS", 98,
				createSerializationMatchSteps(
					231		/* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
					1		/* assert (|AnnotationElementCS::ownedDetails| - 1) == 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					170		/* 'sysml' || soft-space value soft-space */,
					186		/* 1*3-steps || value */,
					23		/* AnnotationElementCS::ownedDetails+=16 || value */,
					180		/* 1*1-steps || value */,
					119		/* ';' || no-space value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						6) /* DetailCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::TemplateSignatureCS(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[152] =
			new SerializationRule("TemplateSignatureCS", 101,
				createSerializationMatchSteps(
					258		/* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
					65		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					55		/* TemplateSignatureCS::ownedParameters+=114 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					55		/* TemplateSignatureCS::ownedParameters+=114 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						58) /* TypeParameterCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(114, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::TemplateSignatureCS(basecs::TemplateSignatureCS): { "<" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ">" }
		serializationRules[153] =
			new SerializationRule("TemplateSignatureCS", 101,
				createSerializationMatchSteps(
					258		/* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
					65		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					180		/* 1*1-steps || value */,
					121		/* '<' || soft-space value soft-space */,
					55		/* TemplateSignatureCS::ownedParameters+=114 || value */,
					198		/* V00*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					55		/* TemplateSignatureCS::ownedParameters+=114 || value */,
					180		/* 1*1-steps || value */,
					123		/* '>' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						58) /* TypeParameterCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(114, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::TopLevelCS(oclinecorecs::TopLevelCS): { { "module" }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] }
		serializationRules[154] =
			new SerializationRule("TopLevelCS", 102,
				createSerializationMatchSteps(
					250		/* check-rule basecs::RootCS.ownedImports : 38 */,
					245		/* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
					161		/* assign V2 = |PackageOwnerCS::ownedPackages| */,
					125		/* assign V1 = |RootCS::ownedImports| */,
					70		/* assign V0 = 0 */
				),
				createSerializationSteps(
					260		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					197		/* V00*2-steps || value */,
					180		/* 1*1-steps || value */,
					156		/* 'module' || soft-space value soft-space */,
					203		/* V01*1-steps || value */,
					36		/* RootCS::ownedImports+=38 || value */,
					219		/* V02*1-steps || value */,
					53		/* PackageOwnerCS::ownedPackages+=71 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						12) /* ImportCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						25) /* PackageCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(38, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(71, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::TypedMultiplicityRefCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[155] =
			new SerializationRule("TypedMultiplicityRefCS", 116,
				createSerializationMatchSteps(
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					100		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					43		/* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					6		/* PrimitiveTypeRefCS::name=82 || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */
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
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::TypedMultiplicityRefCS(basecs::TupleTypeCS): { { name="Tuple" { "(" { ownedParts+=TuplePartCS { "," ownedParts+=TuplePartCS }[*] }[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[156] =
			new SerializationRule("TypedMultiplicityRefCS", 116,
				createSerializationMatchSteps(
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					260		/* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
					181		/* assign V3 = |TypedRefCS::ownedMultiplicity| */,
					48		/* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
					67		/* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
					110		/* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
					153		/* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					128		/* 'Tuple' || soft-space value soft-space */,
					196		/* V00*10-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					207		/* V01*5-steps || value */,
					61		/* TupleTypeCS::ownedParts+=105 || value */,
					221		/* V02*3-steps || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					61		/* TupleTypeCS::ownedParts+=105 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					226		/* V03*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						10	/* 'Tuple' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						50) /* TuplePartCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(10 /* 'Tuple' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[157] =
			new SerializationRule("TypedMultiplicityRefCS", 116,
				createSerializationMatchSteps(
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					266		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
					100		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					55		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					66		/* TypedTypeRefCS::ownedPathName=73 || value */,
					195		/* V00*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[158] =
			new SerializationRule("TypedMultiplicityRefCS", 116,
				createSerializationMatchSteps(
					265		/* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					266		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
					100		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					54		/* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
					55		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					66		/* TypedTypeRefCS::ownedPathName=73 || value */,
					190		/* 1*5-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					11		/* TypedTypeRefCS::ownedBinding=99 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					195		/* V00*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						46) /* TemplateBindingCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[159] =
			new SerializationRule("TypedMultiplicityRefCS", 116,
				createSerializationMatchSteps(
					265		/* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					266		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
					100		/* assign V0 = |TypedRefCS::ownedMultiplicity| */,
					54		/* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
					55		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					66		/* TypedTypeRefCS::ownedPathName=73 || value */,
					190		/* 1*5-steps || value */,
					180		/* 1*1-steps || value */,
					121		/* '<' || soft-space value soft-space */,
					11		/* TypedTypeRefCS::ownedBinding=99 || value */,
					180		/* 1*1-steps || value */,
					123		/* '>' || soft-space value soft-space */,
					195		/* V00*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						46) /* TemplateBindingCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::TypedMultiplicityRefCS(essentialoclcs::CollectionTypeCS): { { name=CollectionTypeIdentifier { "(" ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[160] =
			new SerializationRule("TypedMultiplicityRefCS", 116,
				createSerializationMatchSteps(
					279		/* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					280		/* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
					165		/* assign V2 = |TypedRefCS::ownedMultiplicity| */,
					75		/* assign V0 = |CollectionTypeCS::ownedType| */,
					6		/* assert (|CollectionTypeCS::name| - 1) == 0 */,
					115		/* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					5		/* CollectionTypeCS::name=12 || soft-space value soft-space */,
					201		/* V00*7-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					85		/* CollectionTypeCS::ownedType=108 || value */,
					203		/* V01*1-steps || value */,
					15		/* CollectionTypeCS::ownedCollectionMultiplicity=56 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					219		/* V02*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						52) /* TypeExpWithoutMultiplicityCS */
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
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::TypedMultiplicityRefCS(essentialoclcs::MapTypeCS): { { name="Map" { "(" ownedKeyType=TypeExpCS "," ownedValueType=TypeExpCS ")" }[?] } ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[161] =
			new SerializationRule("TypedMultiplicityRefCS", 116,
				createSerializationMatchSteps(
					299		/* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
					264		/* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
					300		/* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
					132		/* assign V1 = |TypedRefCS::ownedMultiplicity| */,
					82		/* assign V0 = |MapTypeCS::ownedValueType| */,
					25		/* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
					24		/* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
				),
				createSerializationSteps(
					180		/* 1*1-steps || value */,
					127		/* 'Map' || soft-space value soft-space */,
					202		/* V00*8-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					43		/* MapTypeCS::ownedKeyType=107 || value */,
					180		/* 1*1-steps || value */,
					113		/* ',' || no-space value soft-space */,
					91		/* MapTypeCS::ownedValueType=107 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */,
					203		/* V01*1-steps || value */,
					49		/* TypedRefCS::ownedMultiplicity=56 || value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						9	/* 'Map' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						51) /* TypeExpCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						19) /* MultiplicityCS */,
					createEReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						51) /* TypeExpCS */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(9 /* 'Map' */, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
			;
		// OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS
		serializationRules[162] =
			new SerializationRule("TypedTypeRefCS", 118,
				createSerializationMatchSteps(
					266		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
					55		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					66		/* TypedTypeRefCS::ownedPathName=73 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" } }
		serializationRules[163] =
			new SerializationRule("TypedTypeRefCS", 118,
				createSerializationMatchSteps(
					265		/* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
					266		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
					54		/* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
					55		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					66		/* TypedTypeRefCS::ownedPathName=73 || value */,
					190		/* 1*5-steps || value */,
					180		/* 1*1-steps || value */,
					109		/* '(' || no-space value no-space */,
					11		/* TypedTypeRefCS::ownedBinding=99 || value */,
					180		/* 1*1-steps || value */,
					110		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						46) /* TemplateBindingCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
						}
					)
				});
			;
		// OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "<" ownedBinding=TemplateBindingCS ">" } }
		serializationRules[164] =
			new SerializationRule("TypedTypeRefCS", 118,
				createSerializationMatchSteps(
					265		/* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
					266		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
					54		/* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
					55		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					66		/* TypedTypeRefCS::ownedPathName=73 || value */,
					190		/* 1*5-steps || value */,
					180		/* 1*1-steps || value */,
					121		/* '<' || soft-space value soft-space */,
					11		/* TypedTypeRefCS::ownedBinding=99 || value */,
					180		/* 1*1-steps || value */,
					123		/* '>' || soft-space value soft-space */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						46) /* TemplateBindingCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						27) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
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
		// StructuralFeatureCS::default=87 || soft-space value soft-space
		serializationSteps[0] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, 87 /*SINGLE_QUOTED_STRING*/, 7);
		// SpecificationCS::exprString=119 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[1] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, 119 /*UNQUOTED_STRING*/, 2);
		// ClassCS::instanceClassName=87 || soft-space value soft-space
		serializationSteps[2] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, 87 /*SINGLE_QUOTED_STRING*/, 7);
		// EnumerationLiteralCS::literal=87 || soft-space value soft-space
		serializationSteps[3] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, 87 /*SINGLE_QUOTED_STRING*/, 7);
		// MultiplicityBoundsCS::lowerBound=43 || soft-space value soft-space
		serializationSteps[4] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 43 /*LOWER*/, 7);
		// CollectionTypeCS::name=12 || soft-space value soft-space
		serializationSteps[5] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 12 /*CollectionTypeIdentifier*/, 7);
		// PrimitiveTypeRefCS::name=82 || soft-space value soft-space
		serializationSteps[6] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 82 /*PrimitiveTypeIdentifier*/, 7);
		// PackageCS::nsPrefix=127 || soft-space value soft-space
		serializationSteps[7] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, 127 /*UnrestrictedName*/, 7);
		// PackageCS::nsURI=121 || soft-space value soft-space
		serializationSteps[8] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__NS_URI, 121 /*URI*/, 7);
		// TemplateParameterSubstitutionCS::ownedActualParameter=115 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[9] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 115 /*TypeRefCS*/, 2);
		// ModelElementCS::ownedAnnotations+=2 || value
		serializationSteps[10] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /*AnnotationElementCS*/, 0);
		// TypedTypeRefCS::ownedBinding=99 || value
		serializationSteps[11] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 99 /*TemplateBindingCS*/, 0);
		// OperationCS::ownedBodyExpressions+=92 || value
		serializationSteps[12] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 92 /*SpecificationCS*/, 0);
		// PackageCS::ownedClasses+=6 || half-new-line value half-new-line
		serializationSteps[13] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 6 /*ClassCS*/, 3);
		// NavigatingArgCS::ownedCoIterator=7 || value
		serializationSteps[14] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 7 /*CoIteratorVariableCS*/, 0);
		// CollectionTypeCS::ownedCollectionMultiplicity=56 || value
		serializationSteps[15] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 56 /*MultiplicityCS*/, 0);
		// IfExpCS::ownedCondition=30|74 || value
		serializationSteps[16] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, -1, new @NonNull Integer [] { 30/*ExpCS*/,74/*PatternExpCS*/}, 0);
		// IfThenExpCS::ownedCondition=30 || value
		serializationSteps[17] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 30 /*ExpCS*/, 0);
		// ClassCS::ownedConstraints+=41 || value
		serializationSteps[18] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 41 /*InvariantConstraintCS*/, 0);
		// AnnotationCS::ownedContents+=53 || value
		serializationSteps[19] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, 53 /*ModelElementCS*/, 0);
		// AbstractNameExpCS::ownedCurlyBracketedClause=13 || value
		serializationSteps[20] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /*CurlyBracketedClauseCS*/, 0);
		// TypeNameExpCS::ownedCurlyBracketedClause=13 || value
		serializationSteps[21] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /*CurlyBracketedClauseCS*/, 0);
		// StructuralFeatureCS::ownedDefaultExpressions+=92 || value
		serializationSteps[22] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /*SpecificationCS*/, 0);
		// AnnotationElementCS::ownedDetails+=16 || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 16 /*DetailCS*/, 0);
		// IfExpCS::ownedElseExpression=30 || value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 30 /*ExpCS*/, 0);
		// OperationCS::ownedExceptions+=117 || value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 117 /*TypedRefCS*/, 0);
		// CollectionLiteralPartCS::ownedExpression=30 || value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 30 /*ExpCS*/, 0);
		// CollectionLiteralPartCS::ownedExpression=74 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[27] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 74 /*PatternExpCS*/, 2);
		// ContextCS::ownedExpression=30 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 30 /*ExpCS*/, 2);
		// ExpSpecificationCS::ownedExpression=30 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 30 /*ExpCS*/, 2);
		// NestedExpCS::ownedExpression=30 || value
		serializationSteps[30] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 30 /*ExpCS*/, 0);
		// LambdaLiteralExpCS::ownedExpressionCS=30 || value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 30 /*ExpCS*/, 0);
		// TypeParameterCS::ownedExtends+=117 || value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 117 /*TypedRefCS*/, 0);
		// WildcardTypeRefCS::ownedExtends=117 || value
		serializationSteps[33] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 117 /*TypedRefCS*/, 0);
		// IfExpCS::ownedIfThenExpressions+=20 || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 20 /*ElseIfThenExpCS*/, 0);
		// ReferenceCS::ownedImplicitOpposites+=37 || value
		serializationSteps[35] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, 37 /*ImplicitOppositeCS*/, 0);
		// RootCS::ownedImports+=38 || value
		serializationSteps[36] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 38 /*ImportCS*/, 0);
		// LetExpCS::ownedInExpression=30 || value
		serializationSteps[37] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 30 /*ExpCS*/, 0);
		// NavigatingArgCS::ownedInitExpression=30 || value
		serializationSteps[38] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 30 /*ExpCS*/, 0);
		// ShadowPartCS::ownedInitExpression=30|74 || value
		serializationSteps[39] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, -1, new @NonNull Integer [] { 30/*ExpCS*/,74/*PatternExpCS*/}, 0);
		// ShadowPartCS::ownedInitExpression=95 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[40] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 95 /*StringLiteralExpCS*/, 2);
		// VariableCS::ownedInitExpression=30 || value
		serializationSteps[41] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 30 /*ExpCS*/, 0);
		// MapLiteralPartCS::ownedKey=30 || value
		serializationSteps[42] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 30 /*ExpCS*/, 0);
		// MapTypeCS::ownedKeyType=107 || value
		serializationSteps[43] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 107 /*TypeExpCS*/, 0);
		// CollectionLiteralPartCS::ownedLastExpression=30 || value
		serializationSteps[44] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 30 /*ExpCS*/, 0);
		// InfixExpCS::ownedLeft=78 || value
		serializationSteps[45] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 78 /*PrefixedPrimaryExpCS*/, 0);
		// EnumerationCS::ownedLiterals+=22 || value
		serializationSteps[46] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 22 /*EnumerationLiteralCS*/, 0);
		// ConstraintCS::ownedMessageSpecification=92 || value
		serializationSteps[47] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 92 /*SpecificationCS*/, 0);
		// TemplateBindingCS::ownedMultiplicity=56 || value
		serializationSteps[48] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 56 /*MultiplicityCS*/, 0);
		// TypedRefCS::ownedMultiplicity=56 || value
		serializationSteps[49] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /*MultiplicityCS*/, 0);
		// NavigatingArgCS::ownedNameExpression=61 || value
		serializationSteps[50] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 61 /*NavigatingArgExpCS*/, 0);
		// NavigatingArgCS::ownedNameExpression=61 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[51] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 61 /*NavigatingArgExpCS*/, 2);
		// StructuredClassCS::ownedOperations+=70 || value
		serializationSteps[52] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 70 /*OperationCS*/, 0);
		// PackageOwnerCS::ownedPackages+=71 || value
		serializationSteps[53] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 71 /*PackageCS*/, 0);
		// OperationCS::ownedParameters+=72 || value
		serializationSteps[54] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 72 /*ParameterCS*/, 0);
		// TemplateSignatureCS::ownedParameters+=114 || value
		serializationSteps[55] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 114 /*TypeParameterCS*/, 0);
		// CollectionLiteralExpCS::ownedParts+=9 || value
		serializationSteps[56] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 9 /*CollectionLiteralPartCS*/, 0);
		// CollectionPatternCS::ownedParts+=74 || value
		serializationSteps[57] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 74 /*PatternExpCS*/, 0);
		// CurlyBracketedClauseCS::ownedParts+=90 || value
		serializationSteps[58] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 90 /*ShadowPartCS*/, 0);
		// MapLiteralExpCS::ownedParts+=50 || value
		serializationSteps[59] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 50 /*MapLiteralPartCS*/, 0);
		// TupleLiteralExpCS::ownedParts+=104 || value
		serializationSteps[60] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 104 /*TupleLiteralPartCS*/, 0);
		// TupleTypeCS::ownedParts+=105 || value
		serializationSteps[61] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 105 /*TuplePartCS*/, 0);
		// AbstractNameExpCS::ownedPathName=73 || value
		serializationSteps[62] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 73 /*PathNameCS*/, 0);
		// ImportCS::ownedPathName=123 || value
		serializationSteps[63] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 123 /*URIPathNameCS*/, 0);
		// ModelElementRefCS::ownedPathName=73 || value
		serializationSteps[64] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, 73 /*PathNameCS*/, 0);
		// TypeNameExpCS::ownedPathName=73 || value
		serializationSteps[65] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 73 /*PathNameCS*/, 0);
		// TypedTypeRefCS::ownedPathName=73 || value
		serializationSteps[66] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 73 /*PathNameCS*/, 0);
		// TypeNameExpCS::ownedPatternGuard=30 || value
		serializationSteps[67] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 30 /*ExpCS*/, 0);
		// PatternExpCS::ownedPatternType=107 || value
		serializationSteps[68] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 107 /*TypeExpCS*/, 0);
		// OperationCS::ownedPostconditions+=75 || value
		serializationSteps[69] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 75 /*PostconditionConstraintCS*/, 0);
		// OperationCS::ownedPreconditions+=76 || value
		serializationSteps[70] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 76 /*PreconditionConstraintCS*/, 0);
		// StructuredClassCS::ownedProperties+=96 || value
		serializationSteps[71] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 96 /*StructuralFeatureCS*/, 0);
		// AnnotationCS::ownedReferences+=54 || value
		serializationSteps[72] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, 54 /*ModelElementRefCS*/, 0);
		// AbstractNameExpCS::ownedRoundBracketedClause=84 || value
		serializationSteps[73] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 84 /*RoundBracketedClauseCS*/, 0);
		// LetVariableCS::ownedRoundBracketedClause=84 || value
		serializationSteps[74] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 84 /*RoundBracketedClauseCS*/, 0);
		// TemplateableElementCS::ownedSignature=101 || value
		serializationSteps[75] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 101 /*TemplateSignatureCS*/, 0);
		// ConstraintCS::ownedSpecification=92 || value
		serializationSteps[76] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 92 /*SpecificationCS*/, 0);
		// AbstractNameExpCS::ownedSquareBracketedClauses+=93 || value
		serializationSteps[77] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 93 /*SquareBracketedClauseCS*/, 0);
		// TemplateBindingCS::ownedSubstitutions+=100 || value
		serializationSteps[78] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 100 /*TemplateParameterSubstitutionCS*/, 0);
		// StructuredClassCS::ownedSuperTypes+=117 || value
		serializationSteps[79] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 117 /*TypedRefCS*/, 0);
		// SquareBracketedClauseCS::ownedTerms+=30 || value
		serializationSteps[80] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 30 /*ExpCS*/, 0);
		// IfExpCS::ownedThenExpression=30 || value
		serializationSteps[81] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 30 /*ExpCS*/, 0);
		// IfThenExpCS::ownedThenExpression=30 || value
		serializationSteps[82] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 30 /*ExpCS*/, 0);
		// CollectionLiteralExpCS::ownedType=11 || value
		serializationSteps[83] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 11 /*CollectionTypeCS*/, 0);
		// CollectionPatternCS::ownedType=11 || value
		serializationSteps[84] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 11 /*CollectionTypeCS*/, 0);
		// CollectionTypeCS::ownedType=108 || value
		serializationSteps[85] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 108 /*TypeExpWithoutMultiplicityCS*/, 0);
		// MapLiteralExpCS::ownedType=51 || value
		serializationSteps[86] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 51 /*MapTypeCS*/, 0);
		// NavigatingArgCS::ownedType=107 || value
		serializationSteps[87] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 107 /*TypeExpCS*/, 0);
		// TypeLiteralExpCS::ownedType=112 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[88] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 112 /*TypeLiteralWithMultiplicityCS*/, 2);
		// VariableCS::ownedType=107 || value
		serializationSteps[89] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 107 /*TypeExpCS*/, 0);
		// MapLiteralPartCS::ownedValue=30 || value
		serializationSteps[90] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 30 /*ExpCS*/, 0);
		// MapTypeCS::ownedValueType=107 || value
		serializationSteps[91] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 107 /*TypeExpCS*/, 0);
		// LetExpCS::ownedVariables+=46 || value
		serializationSteps[92] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 46 /*LetVariableCS*/, 0);
		// PatternExpCS::patternVariableName=127 || soft-space value soft-space
		serializationSteps[93] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 127 /*UnrestrictedName*/, 7);
		// ReferenceCS::referredKeys+=UnrestrictedName || soft-space value soft-space
		serializationSteps[94] = createSerializationStepCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "UnrestrictedName"), 7);
		// ReferenceCS::referredOpposite=UnrestrictedName || soft-space value soft-space
		serializationSteps[95] = createSerializationStepCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, "UnrestrictedName"), 7);
		// ShadowPartCS::referredProperty=UnrestrictedName || soft-space value soft-space
		serializationSteps[96] = createSerializationStepCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"), 7);
		// CollectionPatternCS::restVariableName=35 || soft-space value soft-space
		serializationSteps[97] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 35 /*Identifier*/, 7);
		// StringLiteralExpCS::segments+=94 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[98] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 94 /*StringLiteral*/, 2);
		// MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[99] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 4 /* '*|+|?' */, 7);
		// BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[100] = createSerializationStepAssignKeyword(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 14 /* 'false|true' */, 2);
		// NumberLiteralExpCS::symbol=58 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[101] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 58 /*NUMBER_LITERAL*/, 2);
		// MultiplicityBoundsCS::upperBound=120 || soft-space value soft-space
		serializationSteps[102] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 120 /*UPPER*/, 7);
		// DocumentationCS::value=87 || soft-space value soft-space
		serializationSteps[103] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, 87 /*SINGLE_QUOTED_STRING*/, 7);
		// EnumerationLiteralCS::value=85 || soft-space value soft-space
		serializationSteps[104] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, 85 /*SIGNED*/, 7);
		// DetailCS::values+=87|48 || soft-space value soft-space
		serializationSteps[105] = createSerializationStepAssigns(BaseCSPackage.Literals.DETAIL_CS__VALUES, -1, new @NonNull Integer [] { 87/*SINGLE_QUOTED_STRING*/,48/*ML_SINGLE_QUOTED_STRING*/}, 7);
		// '!serializable' || soft-space value soft-space
		serializationSteps[106] = createSerializationStepKeyword("!serializable", 7);
		// '#' || no-space value no-space
		serializationSteps[107] = createSerializationStepKeyword("#", 4);
		// '&&' || soft-space value soft-space
		serializationSteps[108] = createSerializationStepKeyword("&&", 7);
		// '(' || no-space value no-space
		serializationSteps[109] = createSerializationStepKeyword("(", 4);
		// ')' || no-space value
		serializationSteps[110] = createSerializationStepKeyword(")", 1);
		// '*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[111] = createSerializationStepKeyword("*", 2);
		// '++' || soft-space value soft-space
		serializationSteps[112] = createSerializationStepKeyword("++", 7);
		// ',' || no-space value soft-space
		serializationSteps[113] = createSerializationStepKeyword(",", 6);
		// '..' || no-space value no-space
		serializationSteps[114] = createSerializationStepKeyword("..", 4);
		// ':' || no-space value soft-space
		serializationSteps[115] = createSerializationStepKeyword(":", 6);
		// ':' || soft-space value soft-space
		serializationSteps[116] = createSerializationStepKeyword(":", 7);
		// '::*' || soft-space value soft-space
		serializationSteps[117] = createSerializationStepKeyword("::*", 7);
		// '::' || no-space value no-space
		serializationSteps[118] = createSerializationStepKeyword("::", 4);
		// ';' || no-space value soft-new-line
		serializationSteps[119] = createSerializationStepKeyword(";", 5);
		// '<-' || soft-space value soft-space
		serializationSteps[120] = createSerializationStepKeyword("<-", 7);
		// '<' || soft-space value soft-space
		serializationSteps[121] = createSerializationStepKeyword("<", 7);
		// '=' || soft-space value soft-space
		serializationSteps[122] = createSerializationStepKeyword("=", 7);
		// '>' || soft-space value soft-space
		serializationSteps[123] = createSerializationStepKeyword(">", 7);
		// '?' || soft-space value soft-space
		serializationSteps[124] = createSerializationStepKeyword("?", 7);
		// '@' || soft-space value soft-space
		serializationSteps[125] = createSerializationStepKeyword("@", 7);
		// 'Lambda' || soft-space value soft-space
		serializationSteps[126] = createSerializationStepKeyword("Lambda", 7);
		// 'Map' || soft-space value soft-space
		serializationSteps[127] = createSerializationStepKeyword("Map", 7);
		// 'Tuple' || soft-space value soft-space
		serializationSteps[128] = createSerializationStepKeyword("Tuple", 7);
		// '[' || no-space value no-space
		serializationSteps[129] = createSerializationStepKeyword("[", 4);
		// ']' || no-space value
		serializationSteps[130] = createSerializationStepKeyword("]", 1);
		// 'abstract' || soft-space value soft-space
		serializationSteps[131] = createSerializationStepKeyword("abstract", 7);
		// 'annotation' || soft-space value soft-space
		serializationSteps[132] = createSerializationStepKeyword("annotation", 7);
		// 'attribute' || soft-space value soft-space
		serializationSteps[133] = createSerializationStepKeyword("attribute", 7);
		// 'body' || soft-space value soft-space
		serializationSteps[134] = createSerializationStepKeyword("body", 7);
		// 'callable' || soft-space value soft-space
		serializationSteps[135] = createSerializationStepKeyword("callable", 7);
		// 'class' || soft-space value soft-space
		serializationSteps[136] = createSerializationStepKeyword("class", 7);
		// 'datatype' || soft-space value soft-space
		serializationSteps[137] = createSerializationStepKeyword("datatype", 7);
		// 'definition' || soft-space value soft-space
		serializationSteps[138] = createSerializationStepKeyword("definition", 7);
		// 'derivation' || soft-space value soft-space
		serializationSteps[139] = createSerializationStepKeyword("derivation", 7);
		// 'documentation' || soft-space value soft-space
		serializationSteps[140] = createSerializationStepKeyword("documentation", 7);
		// 'else' || soft-space value soft-space
		serializationSteps[141] = createSerializationStepKeyword("else", 7);
		// 'elseif' || soft-space value soft-space
		serializationSteps[142] = createSerializationStepKeyword("elseif", 7);
		// 'endif' || soft-space value soft-space
		serializationSteps[143] = createSerializationStepKeyword("endif", 7);
		// 'enum' || soft-space value soft-space
		serializationSteps[144] = createSerializationStepKeyword("enum", 7);
		// 'extends' || soft-space value soft-space
		serializationSteps[145] = createSerializationStepKeyword("extends", 7);
		// 'if' || soft-space value soft-space
		serializationSteps[146] = createSerializationStepKeyword("if", 7);
		// 'import' || value
		serializationSteps[147] = createSerializationStepKeyword("import", 0);
		// 'in' || soft-space value soft-space
		serializationSteps[148] = createSerializationStepKeyword("in", 7);
		// 'initial' || soft-space value soft-space
		serializationSteps[149] = createSerializationStepKeyword("initial", 7);
		// 'interface' || soft-space value soft-space
		serializationSteps[150] = createSerializationStepKeyword("interface", 7);
		// 'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[151] = createSerializationStepKeyword("invalid", 2);
		// 'invariant' || soft-space value soft-space
		serializationSteps[152] = createSerializationStepKeyword("invariant", 7);
		// 'key' || soft-space value soft-space
		serializationSteps[153] = createSerializationStepKeyword("key", 7);
		// 'let' || soft-space value soft-space
		serializationSteps[154] = createSerializationStepKeyword("let", 7);
		// 'literal' || soft-space value soft-space
		serializationSteps[155] = createSerializationStepKeyword("literal", 7);
		// 'module' || soft-space value soft-space
		serializationSteps[156] = createSerializationStepKeyword("module", 7);
		// 'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[157] = createSerializationStepKeyword("null", 2);
		// 'operation' || soft-space value soft-space
		serializationSteps[158] = createSerializationStepKeyword("operation", 7);
		// 'opposite' || soft-space value soft-space
		serializationSteps[159] = createSerializationStepKeyword("opposite", 7);
		// 'package' || soft-space value soft-space
		serializationSteps[160] = createSerializationStepKeyword("package", 7);
		// 'postcondition' || soft-space value soft-space
		serializationSteps[161] = createSerializationStepKeyword("postcondition", 7);
		// 'pre' || soft-space value soft-space
		serializationSteps[162] = createSerializationStepKeyword("pre", 7);
		// 'precondition' || soft-space value soft-space
		serializationSteps[163] = createSerializationStepKeyword("precondition", 7);
		// 'primitive' || soft-space value soft-space
		serializationSteps[164] = createSerializationStepKeyword("primitive", 7);
		// 'property' || soft-space value soft-space
		serializationSteps[165] = createSerializationStepKeyword("property", 7);
		// 'reference' || soft-space value soft-space
		serializationSteps[166] = createSerializationStepKeyword("reference", 7);
		// 'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[167] = createSerializationStepKeyword("self", 2);
		// 'serializable' || soft-space value soft-space
		serializationSteps[168] = createSerializationStepKeyword("serializable", 7);
		// 'static' || soft-space value soft-space
		serializationSteps[169] = createSerializationStepKeyword("static", 7);
		// 'sysml' || soft-space value soft-space
		serializationSteps[170] = createSerializationStepKeyword("sysml", 7);
		// 'then' || soft-space value soft-space
		serializationSteps[171] = createSerializationStepKeyword("then", 7);
		// 'throws' || soft-space value soft-space
		serializationSteps[172] = createSerializationStepKeyword("throws", 7);
		// '{' || soft-space value soft-space
		serializationSteps[173] = createSerializationStepKeyword("{", 7);
		// '{' || soft-space value push soft-new-line
		serializationSteps[174] = createSerializationStepKeyword("{", 9);
		// '|' || soft-space value soft-space
		serializationSteps[175] = createSerializationStepKeyword("|", 7);
		// '|1' || soft-space value soft-space
		serializationSteps[176] = createSerializationStepKeyword("|1", 7);
		// '|?' || soft-space value soft-space
		serializationSteps[177] = createSerializationStepKeyword("|?", 7);
		// '}' || soft-space value soft-space
		serializationSteps[178] = createSerializationStepKeyword("}", 7);
		// '}' || pop soft-space value soft-new-line
		serializationSteps[179] = createSerializationStepKeyword("}", 8);
		// 1*1-steps || value
		serializationSteps[180] = createSerializationStepSequence(-1, 1, 0);
		// 1*11-steps || value
		serializationSteps[181] = createSerializationStepSequence(-1, 11, 0);
		// 1*13-steps || value
		serializationSteps[182] = createSerializationStepSequence(-1, 13, 0);
		// 1*15-steps || value
		serializationSteps[183] = createSerializationStepSequence(-1, 15, 0);
		// 1*20-steps || value
		serializationSteps[184] = createSerializationStepSequence(-1, 20, 0);
		// 1*25-steps || value
		serializationSteps[185] = createSerializationStepSequence(-1, 25, 0);
		// 1*3-steps || value
		serializationSteps[186] = createSerializationStepSequence(-1, 3, 0);
		// 1*36-steps || value
		serializationSteps[187] = createSerializationStepSequence(-1, 36, 0);
		// 1*4-steps || value
		serializationSteps[188] = createSerializationStepSequence(-1, 4, 0);
		// 1*41-steps || value
		serializationSteps[189] = createSerializationStepSequence(-1, 41, 0);
		// 1*5-steps || value
		serializationSteps[190] = createSerializationStepSequence(-1, 5, 0);
		// 1*6-steps || value
		serializationSteps[191] = createSerializationStepSequence(-1, 6, 0);
		// 1*7-steps || value
		serializationSteps[192] = createSerializationStepSequence(-1, 7, 0);
		// 1*8-steps || value
		serializationSteps[193] = createSerializationStepSequence(-1, 8, 0);
		// 1*9-steps || value
		serializationSteps[194] = createSerializationStepSequence(-1, 9, 0);
		// V00*1-steps || value
		serializationSteps[195] = createSerializationStepSequence(0, 1, 0);
		// V00*10-steps || value
		serializationSteps[196] = createSerializationStepSequence(0, 10, 0);
		// V00*2-steps || value
		serializationSteps[197] = createSerializationStepSequence(0, 2, 0);
		// V00*3-steps || value
		serializationSteps[198] = createSerializationStepSequence(0, 3, 0);
		// V00*4-steps || value
		serializationSteps[199] = createSerializationStepSequence(0, 4, 0);
		// V00*5-steps || value
		serializationSteps[200] = createSerializationStepSequence(0, 5, 0);
		// V00*7-steps || value
		serializationSteps[201] = createSerializationStepSequence(0, 7, 0);
		// V00*8-steps || value
		serializationSteps[202] = createSerializationStepSequence(0, 8, 0);
		// V01*1-steps || value
		serializationSteps[203] = createSerializationStepSequence(1, 1, 0);
		// V01*2-steps || value
		serializationSteps[204] = createSerializationStepSequence(1, 2, 0);
		// V01*3-steps || value
		serializationSteps[205] = createSerializationStepSequence(1, 3, 0);
		// V01*4-steps || value
		serializationSteps[206] = createSerializationStepSequence(1, 4, 0);
		// V01*5-steps || value
		serializationSteps[207] = createSerializationStepSequence(1, 5, 0);
		// V01*7-steps || value
		serializationSteps[208] = createSerializationStepSequence(1, 7, 0);
		// V01*8-steps || value
		serializationSteps[209] = createSerializationStepSequence(1, 8, 0);
		// V01*9-steps || value
		serializationSteps[210] = createSerializationStepSequence(1, 9, 0);
		// V10*1-steps || value
		serializationSteps[211] = createSerializationStepSequence(10, 1, 0);
		// V10*8-steps || value
		serializationSteps[212] = createSerializationStepSequence(10, 8, 0);
		// V11*1-steps || value
		serializationSteps[213] = createSerializationStepSequence(11, 1, 0);
		// V11*8-steps || value
		serializationSteps[214] = createSerializationStepSequence(11, 8, 0);
		// V12*1-steps || value
		serializationSteps[215] = createSerializationStepSequence(12, 1, 0);
		// V12*3-steps || value
		serializationSteps[216] = createSerializationStepSequence(12, 3, 0);
		// V13*1-steps || value
		serializationSteps[217] = createSerializationStepSequence(13, 1, 0);
		// V13*3-steps || value
		serializationSteps[218] = createSerializationStepSequence(13, 3, 0);
		// V02*1-steps || value
		serializationSteps[219] = createSerializationStepSequence(2, 1, 0);
		// V02*2-steps || value
		serializationSteps[220] = createSerializationStepSequence(2, 2, 0);
		// V02*3-steps || value
		serializationSteps[221] = createSerializationStepSequence(2, 3, 0);
		// V02*4-steps || value
		serializationSteps[222] = createSerializationStepSequence(2, 4, 0);
		// V02*5-steps || value
		serializationSteps[223] = createSerializationStepSequence(2, 5, 0);
		// V02*6-steps || value
		serializationSteps[224] = createSerializationStepSequence(2, 6, 0);
		// V02*7-steps || value
		serializationSteps[225] = createSerializationStepSequence(2, 7, 0);
		// V03*1-steps || value
		serializationSteps[226] = createSerializationStepSequence(3, 1, 0);
		// V03*2-steps || value
		serializationSteps[227] = createSerializationStepSequence(3, 2, 0);
		// V03*3-steps || value
		serializationSteps[228] = createSerializationStepSequence(3, 3, 0);
		// V03*4-steps || value
		serializationSteps[229] = createSerializationStepSequence(3, 4, 0);
		// V03*6-steps || value
		serializationSteps[230] = createSerializationStepSequence(3, 6, 0);
		// V03*7-steps || value
		serializationSteps[231] = createSerializationStepSequence(3, 7, 0);
		// V04*1-steps || value
		serializationSteps[232] = createSerializationStepSequence(4, 1, 0);
		// V04*2-steps || value
		serializationSteps[233] = createSerializationStepSequence(4, 2, 0);
		// V04*3-steps || value
		serializationSteps[234] = createSerializationStepSequence(4, 3, 0);
		// V04*4-steps || value
		serializationSteps[235] = createSerializationStepSequence(4, 4, 0);
		// V04*7-steps || value
		serializationSteps[236] = createSerializationStepSequence(4, 7, 0);
		// V05*1-steps || value
		serializationSteps[237] = createSerializationStepSequence(5, 1, 0);
		// V05*2-steps || value
		serializationSteps[238] = createSerializationStepSequence(5, 2, 0);
		// V05*3-steps || value
		serializationSteps[239] = createSerializationStepSequence(5, 3, 0);
		// V05*6-steps || value
		serializationSteps[240] = createSerializationStepSequence(5, 6, 0);
		// V05*7-steps || value
		serializationSteps[241] = createSerializationStepSequence(5, 7, 0);
		// V05*8-steps || value
		serializationSteps[242] = createSerializationStepSequence(5, 8, 0);
		// V06*1-steps || value
		serializationSteps[243] = createSerializationStepSequence(6, 1, 0);
		// V06*11-steps || value
		serializationSteps[244] = createSerializationStepSequence(6, 11, 0);
		// V06*3-steps || value
		serializationSteps[245] = createSerializationStepSequence(6, 3, 0);
		// V06*7-steps || value
		serializationSteps[246] = createSerializationStepSequence(6, 7, 0);
		// V06*8-steps || value
		serializationSteps[247] = createSerializationStepSequence(6, 8, 0);
		// V07*1-steps || value
		serializationSteps[248] = createSerializationStepSequence(7, 1, 0);
		// V07*11-steps || value
		serializationSteps[249] = createSerializationStepSequence(7, 11, 0);
		// V07*2-steps || value
		serializationSteps[250] = createSerializationStepSequence(7, 2, 0);
		// V07*4-steps || value
		serializationSteps[251] = createSerializationStepSequence(7, 4, 0);
		// V07*7-steps || value
		serializationSteps[252] = createSerializationStepSequence(7, 7, 0);
		// V07*8-steps || value
		serializationSteps[253] = createSerializationStepSequence(7, 8, 0);
		// V08*1-steps || value
		serializationSteps[254] = createSerializationStepSequence(8, 1, 0);
		// V08*2-steps || value
		serializationSteps[255] = createSerializationStepSequence(8, 2, 0);
		// V08*4-steps || value
		serializationSteps[256] = createSerializationStepSequence(8, 4, 0);
		// V08*8-steps || value
		serializationSteps[257] = createSerializationStepSequence(8, 8, 0);
		// V09*1-steps || value
		serializationSteps[258] = createSerializationStepSequence(9, 1, 0);
		// V09*8-steps || value
		serializationSteps[259] = createSerializationStepSequence(9, 8, 0);
		// wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[260] = createSerializationStepWrapper(2);
		// NamedElementCS::name=124 || soft-space value soft-space
		serializationSteps[261] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 124 /*UnaryOperatorName*/, 7);
		// NamedElementCS::name=127 || soft-space value soft-space
		serializationSteps[262] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 127 /*UnrestrictedName*/, 7);
		// NamedElementCS::name=127|87 || soft-space value soft-space
		serializationSteps[263] = createSerializationStepAssigns(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, -1, new @NonNull Integer [] { 127/*UnrestrictedName*/,87/*SINGLE_QUOTED_STRING*/}, 7);
		// NamedElementCS::name=127|87 || soft-space value soft-space
		serializationSteps[264] = createSerializationStepAssigns(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, -1, new @NonNull Integer [] { 127/*UnrestrictedName*/,87/*SINGLE_QUOTED_STRING*/}, 7);
		// NamedElementCS::name=23 || soft-space value soft-space
		serializationSteps[265] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 23 /*EnumerationLiteralName*/, 7);
		// NamedElementCS::name=4 || soft-space value soft-space
		serializationSteps[266] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 4 /*BinaryOperatorName*/, 7);
		// RoundBracketedClauseCS::ownedArguments+=60 || value
		serializationSteps[267] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 60 /*NavigatingArgCS*/, 0);
		// RoundBracketedClauseCS::ownedArguments+=63|64|62 || value
		serializationSteps[268] = createSerializationStepAssigns(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, -1, new @NonNull Integer [] { 63/*NavigatingCommaArgCS*/,64/*NavigatingSemiArgCS*/,62/*NavigatingBarArgCS*/}, 0);
		// PathNameCS::ownedPathElements+=122 || value
		serializationSteps[269] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 122 /*URIFirstPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=31 || value
		serializationSteps[270] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 31 /*FirstPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=67 || value
		serializationSteps[271] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 67 /*NextPathElementCS*/, 0);
		// OperatorExpCS::ownedRight=30 || value
		serializationSteps[272] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 30 /*ExpCS*/, 0);
		// OperatorExpCS::ownedRight=77 || value
		serializationSteps[273] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 77 /*PrefixedLetExpCS*/, 0);
		// OperatorExpCS::ownedRight=78 || value
		serializationSteps[274] = createSerializationStepAssignedRuleCall(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 78 /*PrefixedPrimaryExpCS*/, 0);
		// TypedElementCS::ownedType=107 || value
		serializationSteps[275] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 107 /*TypeExpCS*/, 0);
		// TypedElementCS::ownedType=116 || value
		serializationSteps[276] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 116 /*TypedMultiplicityRefCS*/, 0);
		// TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || soft-space value soft-space
		serializationSteps[277] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 0 /* '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' */, 7);
		// TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || soft-space value soft-space
		serializationSteps[278] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 1 /* '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' */, 7);
		// TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || soft-space value soft-space
		serializationSteps[279] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 2 /* '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' */, 7);
		// TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique' || soft-space value soft-space
		serializationSteps[280] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 3 /* '!ordered|!unique|ordered|unique' */, 7);
		// PathElementCS::referredElement=URI || soft-space value soft-space
		serializationSteps[281] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"), 7);
		// PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[282] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 7);
		// PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[283] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 7);
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
//	import OCLinEcoreCSPackage;
