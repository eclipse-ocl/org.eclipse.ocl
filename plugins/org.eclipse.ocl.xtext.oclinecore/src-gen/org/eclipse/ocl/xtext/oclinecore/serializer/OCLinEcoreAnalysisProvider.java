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

import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis;

public class OCLinEcoreAnalysisProvider extends AbstractAnalysisProvider
{
	private static RTGrammarAnalysis analysis = null;

	@Override
	public RTGrammarAnalysis getAnalysis() {
		if (analysis == null) {
			analysis = new RTGrammarAnalysis();
		}
		analysis.addSerializationRules(create_AnnotationCS_Rules());
		analysis.addSerializationRules(create_AttributeCS_Rules());
		analysis.addSerializationRules(create_BooleanLiteralExpCS_Rules());
		analysis.addSerializationRules(create_CollectionLiteralExpCS_Rules());
		analysis.addSerializationRules(create_CollectionLiteralPartCS_Rules());
		analysis.addSerializationRules(create_CollectionPatternCS_Rules());
		analysis.addSerializationRules(create_CollectionTypeCS_Rules());
		analysis.addSerializationRules(create_ContextCS_Rules());
		analysis.addSerializationRules(create_CurlyBracketedClauseCS_Rules());
		analysis.addSerializationRules(create_DataTypeCS_Rules());
		analysis.addSerializationRules(create_DetailCS_Rules());
		analysis.addSerializationRules(create_DocumentationCS_Rules());
		analysis.addSerializationRules(create_EnumerationCS_Rules());
		analysis.addSerializationRules(create_EnumerationLiteralCS_Rules());
		analysis.addSerializationRules(create_ExpCS_Rules());
		analysis.addSerializationRules(create_ExpSpecificationCS_Rules());
		analysis.addSerializationRules(create_IfExpCS_Rules());
		analysis.addSerializationRules(create_IfThenExpCS_Rules());
		analysis.addSerializationRules(create_ImplicitOppositeCS_Rules());
		analysis.addSerializationRules(create_ImportCS_Rules());
		analysis.addSerializationRules(create_InfixExpCS_Rules());
		analysis.addSerializationRules(create_InvalidLiteralExpCS_Rules());
		analysis.addSerializationRules(create_LambdaLiteralExpCS_Rules());
		analysis.addSerializationRules(create_LetExpCS_Rules());
		analysis.addSerializationRules(create_LetVariableCS_Rules());
		analysis.addSerializationRules(create_MapLiteralExpCS_Rules());
		analysis.addSerializationRules(create_MapLiteralPartCS_Rules());
		analysis.addSerializationRules(create_MapTypeCS_Rules());
		analysis.addSerializationRules(create_ModelElementRefCS_Rules());
		analysis.addSerializationRules(create_MultiplicityBoundsCS_Rules());
		analysis.addSerializationRules(create_MultiplicityStringCS_Rules());
		analysis.addSerializationRules(create_NameExpCS_Rules());
		analysis.addSerializationRules(create_NavigatingArgCS_Rules());
		analysis.addSerializationRules(create_NestedExpCS_Rules());
		analysis.addSerializationRules(create_NullLiteralExpCS_Rules());
		analysis.addSerializationRules(create_NumberLiteralExpCS_Rules());
		analysis.addSerializationRules(create_OCLinEcoreConstraintCS_Rules());
		analysis.addSerializationRules(create_OperationCS_Rules());
		analysis.addSerializationRules(create_PackageCS_Rules());
		analysis.addSerializationRules(create_ParameterCS_Rules());
		analysis.addSerializationRules(create_PathElementCS_Rules());
		analysis.addSerializationRules(create_PathElementWithURICS_Rules());
		analysis.addSerializationRules(create_PathNameCS_Rules());
		analysis.addSerializationRules(create_PatternExpCS_Rules());
		analysis.addSerializationRules(create_PrefixExpCS_Rules());
		analysis.addSerializationRules(create_PrimitiveLiteralExpCS_Rules());
		analysis.addSerializationRules(create_PrimitiveTypeRefCS_Rules());
		analysis.addSerializationRules(create_ReferenceCS_Rules());
		analysis.addSerializationRules(create_RoundBracketedClauseCS_Rules());
		analysis.addSerializationRules(create_SelfExpCS_Rules());
		analysis.addSerializationRules(create_ShadowPartCS_Rules());
		analysis.addSerializationRules(create_SquareBracketedClauseCS_Rules());
		analysis.addSerializationRules(create_StringLiteralExpCS_Rules());
		analysis.addSerializationRules(create_StructuredClassCS_Rules());
		analysis.addSerializationRules(create_SysMLCS_Rules());
		analysis.addSerializationRules(create_TemplateBindingCS_Rules());
		analysis.addSerializationRules(create_TemplateParameterSubstitutionCS_Rules());
		analysis.addSerializationRules(create_TemplateSignatureCS_Rules());
		analysis.addSerializationRules(create_TopLevelCS_Rules());
		analysis.addSerializationRules(create_TupleLiteralExpCS_Rules());
		analysis.addSerializationRules(create_TupleLiteralPartCS_Rules());
		analysis.addSerializationRules(create_TuplePartCS_Rules());
		analysis.addSerializationRules(create_TupleTypeCS_Rules());
		analysis.addSerializationRules(create_TypeLiteralExpCS_Rules());
		analysis.addSerializationRules(create_TypeNameExpCS_Rules());
		analysis.addSerializationRules(create_TypeParameterCS_Rules());
		analysis.addSerializationRules(create_TypedTypeRefCS_Rules());
		analysis.addSerializationRules(create_UnlimitedNaturalLiteralExpCS_Rules());
		analysis.addSerializationRules(create_VariableCS_Rules());
		analysis.addSerializationRules(create_WildcardTypeRefCS_Rules());
		return analysis;
	}

	/**
	 * AnnotationCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_AnnotationCS_Rules() {
	// OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
	// OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' }
	// OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' }
	// OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' }
	// OCLinEcore::AnnotationElementCS => OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
	// OCLinEcore::AnnotationElementCS => OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' }
	// OCLinEcore::AnnotationElementCS => OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' }
	// OCLinEcore::AnnotationElementCS => OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS, new java.util.ArrayList<>());
	}
	/**
	 * AttributeCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_AttributeCS_Rules() {
	// OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
	// OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
	// OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ATTRIBUTE_CS, new java.util.ArrayList<>());
	}
	/**
	 * BooleanLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_BooleanLiteralExpCS_Rules() {
	// EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
	// EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
	// EssentialOCL::PrimaryExpCS => EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
	// EssentialOCL::PrimitiveLiteralExpCS => EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * CollectionLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_CollectionLiteralExpCS_Rules() {
	// EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
	// EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
	// EssentialOCL::PrimaryExpCS => EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * CollectionLiteralPartCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_CollectionLiteralPartCS_Rules() {
	// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS
	// EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS, new java.util.ArrayList<>());
	}
	/**
	 * CollectionPatternCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_CollectionPatternCS_Rules() {
	// EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
	// EssentialOCL::TypeExpCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
	// EssentialOCL::TypeExpWithoutMultiplicityCS => EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS, new java.util.ArrayList<>());
	}
	/**
	 * CollectionTypeCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_CollectionTypeCS_Rules() {
	// Base::TypeRefCS => EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
	// EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
	// EssentialOCL::TypeExpCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
	// EssentialOCL::TypeExpWithoutMultiplicityCS => EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
	// EssentialOCL::TypeLiteralCS => EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
	// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedMultiplicityRefCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedRefCS => EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS, new java.util.ArrayList<>());
	}
	/**
	 * ContextCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_ContextCS_Rules() {
	// EssentialOCL::Model(essentialoclcs::ContextCS): ownedExpression=ExpCS
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS, new java.util.ArrayList<>());
	}
	/**
	 * CurlyBracketedClauseCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_CurlyBracketedClauseCS_Rules() {
	// EssentialOCL::CurlyBracketedClauseCS(essentialoclcs::CurlyBracketedClauseCS): { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS, new java.util.ArrayList<>());
	}
	/**
	 * DataTypeCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_DataTypeCS_Rules() {
	// OCLinEcore::ClassCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
	// OCLinEcore::ClassCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
	// OCLinEcore::ClassCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
	// OCLinEcore::ClassCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ClassCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ClassCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
	// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
	// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
	// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS, new java.util.ArrayList<>());
	}
	/**
	 * DetailCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_DetailCS_Rules() {
	// OCLinEcore::DetailCS(basecs::DetailCS): { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS, new java.util.ArrayList<>());
	}
	/**
	 * DocumentationCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_DocumentationCS_Rules() {
	// OCLinEcore::AnnotationElementCS => OCLinEcore::DocumentationCS(basecs::DocumentationCS): { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
	// OCLinEcore::DocumentationCS(basecs::DocumentationCS): { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS, new java.util.ArrayList<>());
	}
	/**
	 * EnumerationCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_EnumerationCS_Rules() {
	// OCLinEcore::ClassCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
	// OCLinEcore::ClassCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
	// OCLinEcore::ClassCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
	// OCLinEcore::ClassCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ClassCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ClassCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
	// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
	// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
	// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS, new java.util.ArrayList<>());
	}
	/**
	 * EnumerationLiteralCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_EnumerationLiteralCS_Rules() {
	// OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
	// OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
	// OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
	// OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS, new java.util.ArrayList<>());
	}
	/**
	 * ExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_ExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::ExpCS): '*'
	// EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'invalid'
	// EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'null'
	// EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'self'
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::ExpCS): '*'
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'invalid'
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'null'
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'self'
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid'
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null'
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): 'self'
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*'
	// EssentialOCL::PrimaryExpCS => EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid'
	// EssentialOCL::PrimaryExpCS => EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null'
	// EssentialOCL::PrimaryExpCS => EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): 'self'
	// EssentialOCL::PrimaryExpCS => EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*'
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * ExpSpecificationCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_ExpSpecificationCS_Rules() {
	// OCLinEcore::SpecificationCS(essentialoclcs::ExpSpecificationCS): exprString=UNQUOTED_STRING
	// OCLinEcore::SpecificationCS(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS, new java.util.ArrayList<>());
	}
	/**
	 * IfExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_IfExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
	// EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
	// EssentialOCL::PrimaryExpCS => EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * IfThenExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_IfThenExpCS_Rules() {
	// EssentialOCL::ElseIfThenExpCS(essentialoclcs::IfThenExpCS): { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * ImplicitOppositeCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_ImplicitOppositeCS_Rules() {
	// OCLinEcore::ImplicitOppositeCS(basecs::ImplicitOppositeCS): { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPLICIT_OPPOSITE_CS, new java.util.ArrayList<>());
	}
	/**
	 * ImportCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_ImportCS_Rules() {
	// OCLinEcore::ImportCS(basecs::ImportCS): { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS, new java.util.ArrayList<>());
	}
	/**
	 * InfixExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_InfixExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * InvalidLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_InvalidLiteralExpCS_Rules() {
	// EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid'
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * LambdaLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_LambdaLiteralExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
	// EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
	// EssentialOCL::PrimaryExpCS => EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * LetExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_LetExpCS_Rules() {
	// EssentialOCL::ExpCS => EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
	// EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
	// EssentialOCL::PrefixedLetExpCS => EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * LetVariableCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_LetVariableCS_Rules() {
	// EssentialOCL::LetVariableCS(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS, new java.util.ArrayList<>());
	}
	/**
	 * MapLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_MapLiteralExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
	// EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
	// EssentialOCL::PrimaryExpCS => EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * MapLiteralPartCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_MapLiteralPartCS_Rules() {
	// EssentialOCL::MapLiteralPartCS(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS '<-' ownedValue=ExpCS }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS, new java.util.ArrayList<>());
	}
	/**
	 * MapTypeCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_MapTypeCS_Rules() {
	// Base::TypeRefCS => EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
	// EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
	// EssentialOCL::TypeExpCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
	// EssentialOCL::TypeExpWithoutMultiplicityCS => EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
	// EssentialOCL::TypeLiteralCS => EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
	// EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedMultiplicityRefCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedRefCS => EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS, new java.util.ArrayList<>());
	}
	/**
	 * ModelElementRefCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_ModelElementRefCS_Rules() {
	// OCLinEcore::ModelElementRefCS(basecs::ModelElementRefCS): { 'reference' ownedPathName=PathNameCS ';' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS, new java.util.ArrayList<>());
	}
	/**
	 * MultiplicityBoundsCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_MultiplicityBoundsCS_Rules() {
	// Base::MultiplicityBoundsCS(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { '..' upperBound=UPPER }[?] }
	// Base::MultiplicityCS(basecs::MultiplicityBoundsCS): { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' }
	// Base::MultiplicityCS(basecs::MultiplicityBoundsCS): { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
	// Base::MultiplicityCS(basecs::MultiplicityBoundsCS): { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS, new java.util.ArrayList<>());
	}
	/**
	 * MultiplicityStringCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_MultiplicityStringCS_Rules() {
	// Base::MultiplicityCS(basecs::MultiplicityStringCS): { '[' stringBounds={'*|+|?'} ']' }
	// Base::MultiplicityCS(basecs::MultiplicityStringCS): { '[' stringBounds={'*|+|?'} '|?' ']' }
	// Base::MultiplicityCS(basecs::MultiplicityStringCS): { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
	// Base::MultiplicityStringCS(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS, new java.util.ArrayList<>());
	}
	/**
	 * NameExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_NameExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
	// EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
	// EssentialOCL::PrimaryExpCS => EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAME_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * NavigatingArgCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_NavigatingArgCS_Rules() {
	// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS
	// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ':' ownedType=TypeExpCS }
	// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
	// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
	// EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
	// EssentialOCL::NavigatingBarArgCS(essentialoclcs::NavigatingArgCS): { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
	// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix=',' ownedNameExpression=NavigatingArgExpCS }
	// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
	// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
	// EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
	// EssentialOCL::NavigatingSemiArgCS(essentialoclcs::NavigatingArgCS): { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS, new java.util.ArrayList<>());
	}
	/**
	 * NestedExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_NestedExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' }
	// EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' }
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' }
	// EssentialOCL::PrimaryExpCS => EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * NullLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_NullLiteralExpCS_Rules() {
	// EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null'
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * NumberLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_NumberLiteralExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
	// EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
	// EssentialOCL::PrimaryExpCS => EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
	// EssentialOCL::PrimitiveLiteralExpCS => EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * OCLinEcoreConstraintCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_OCLinEcoreConstraintCS_Rules() {
	// OCLinEcore::InvariantConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' }
	// OCLinEcore::InvariantConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
	// OCLinEcore::PostconditionConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
	// OCLinEcore::PreconditionConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS, new java.util.ArrayList<>());
	}
	/**
	 * OperationCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_OperationCS_Rules() {
	// OCLinEcore::ModelElementCS => OCLinEcore::OperationCS(basecs::OperationCS): { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::OperationCS(basecs::OperationCS): { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
	// OCLinEcore::OperationCS(basecs::OperationCS): { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
	// OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
	// OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
	// OCLinEcore::OperationCS(basecs::OperationCS): { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
	// OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
	// OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS, new java.util.ArrayList<>());
	}
	/**
	 * PackageCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_PackageCS_Rules() {
	// OCLinEcore::ModelElementCS => OCLinEcore::PackageCS(basecs::PackageCS): { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::PackageCS(basecs::PackageCS): { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
	// OCLinEcore::PackageCS(basecs::PackageCS): { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' }
	// OCLinEcore::PackageCS(basecs::PackageCS): { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS, new java.util.ArrayList<>());
	}
	/**
	 * ParameterCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_ParameterCS_Rules() {
	// OCLinEcore::ParameterCS(basecs::ParameterCS): { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PARAMETER_CS, new java.util.ArrayList<>());
	}
	/**
	 * PathElementCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_PathElementCS_Rules() {
	// Base::FirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
	// Base::NextPathElementCS(basecs::PathElementCS): referredElement=UnreservedName
	// EssentialOCL::URIFirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS, new java.util.ArrayList<>());
	}
	/**
	 * PathElementWithURICS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_PathElementWithURICS_Rules() {
	// EssentialOCL::URIFirstPathElementCS(basecs::PathElementWithURICS): referredElement=URI
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS, new java.util.ArrayList<>());
	}
	/**
	 * PathNameCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_PathNameCS_Rules() {
	// Base::PathNameCS(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
	// EssentialOCL::SimplePathNameCS(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS
	// EssentialOCL::URIPathNameCS(basecs::PathNameCS): { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS, new java.util.ArrayList<>());
	}
	/**
	 * PatternExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_PatternExpCS_Rules() {
	// EssentialOCL::PatternExpCS(essentialoclcs::PatternExpCS): { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * PrefixExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_PrefixExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
	// EssentialOCL::ExpCS => EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
	// EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
	// EssentialOCL::PrefixedPrimaryExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PREFIX_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * PrimitiveLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_PrimitiveLiteralExpCS_Rules() {
	// EssentialOCL::PrimitiveLiteralExpCS => EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid'
	// EssentialOCL::PrimitiveLiteralExpCS => EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null'
	// EssentialOCL::PrimitiveLiteralExpCS => EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*'
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PRIMITIVE_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * PrimitiveTypeRefCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_PrimitiveTypeRefCS_Rules() {
	// Base::TypeRefCS => EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
	// EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
	// EssentialOCL::TypeExpCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
	// EssentialOCL::TypeExpWithoutMultiplicityCS => EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
	// EssentialOCL::TypeLiteralCS => EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
	// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedMultiplicityRefCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedRefCS => EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS, new java.util.ArrayList<>());
	}
	/**
	 * ReferenceCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_ReferenceCS_Rules() {
	// OCLinEcore::ModelElementCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
	// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
	// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
	// OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
	// OCLinEcore::StructuralFeatureCS => OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS, new java.util.ArrayList<>());
	}
	/**
	 * RoundBracketedClauseCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_RoundBracketedClauseCS_Rules() {
	// EssentialOCL::RoundBracketedClauseCS(essentialoclcs::RoundBracketedClauseCS): { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS, new java.util.ArrayList<>());
	}
	/**
	 * SelfExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_SelfExpCS_Rules() {
	// EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): 'self'
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SELF_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * ShadowPartCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_ShadowPartCS_Rules() {
	// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS
	// EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS, new java.util.ArrayList<>());
	}
	/**
	 * SquareBracketedClauseCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_SquareBracketedClauseCS_Rules() {
	// EssentialOCL::SquareBracketedClauseCS(essentialoclcs::SquareBracketedClauseCS): { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS, new java.util.ArrayList<>());
	}
	/**
	 * StringLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_StringLiteralExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
	// EssentialOCL::PrimaryExpCS => EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
	// EssentialOCL::PrimitiveLiteralExpCS => EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
	// EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * StructuredClassCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_StructuredClassCS_Rules() {
	// OCLinEcore::ClassCS => OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
	// OCLinEcore::ClassCS => OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::ModelElementCS => OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
	// OCLinEcore::ModelElementCS => OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
	// OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
	// OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS, new java.util.ArrayList<>());
	}
	/**
	 * SysMLCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_SysMLCS_Rules() {
	// OCLinEcore::AnnotationElementCS => OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { 'sysml' ownedDetails+=DetailCS ';' }
	// OCLinEcore::AnnotationElementCS => OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
	// OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { 'sysml' ownedDetails+=DetailCS ';' }
	// OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.SYS_MLCS, new java.util.ArrayList<>());
	}
	/**
	 * TemplateBindingCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TemplateBindingCS_Rules() {
	// Base::TemplateBindingCS(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS, new java.util.ArrayList<>());
	}
	/**
	 * TemplateParameterSubstitutionCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TemplateParameterSubstitutionCS_Rules() {
	// Base::TemplateParameterSubstitutionCS(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS, new java.util.ArrayList<>());
	}
	/**
	 * TemplateSignatureCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TemplateSignatureCS_Rules() {
	// OCLinEcore::TemplateSignatureCS(basecs::TemplateSignatureCS): { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
	// OCLinEcore::TemplateSignatureCS(basecs::TemplateSignatureCS): { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS, new java.util.ArrayList<>());
	}
	/**
	 * TopLevelCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TopLevelCS_Rules() {
	// OCLinEcore::TopLevelCS(oclinecorecs::TopLevelCS): { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.TOP_LEVEL_CS, new java.util.ArrayList<>());
	}
	/**
	 * TupleLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TupleLiteralExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
	// EssentialOCL::PrimaryExpCS => EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
	// EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * TupleLiteralPartCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TupleLiteralPartCS_Rules() {
	// EssentialOCL::TupleLiteralPartCS(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS, new java.util.ArrayList<>());
	}
	/**
	 * TuplePartCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TuplePartCS_Rules() {
	// EssentialOCL::TuplePartCS(basecs::TuplePartCS): { name=UnrestrictedName ':' ownedType=TypeExpCS }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_PART_CS, new java.util.ArrayList<>());
	}
	/**
	 * TupleTypeCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TupleTypeCS_Rules() {
	// Base::TypeRefCS => EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
	// EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
	// EssentialOCL::TypeExpCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
	// EssentialOCL::TypeExpWithoutMultiplicityCS => EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
	// EssentialOCL::TypeLiteralCS => EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
	// EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedMultiplicityRefCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedRefCS => EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS, new java.util.ArrayList<>());
	}
	/**
	 * TypeLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TypeLiteralExpCS_Rules() {
	// EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
	// EssentialOCL::NavigatingArgExpCS => EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
	// EssentialOCL::PrefixedPrimaryExpCS => EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
	// EssentialOCL::PrimaryExpCS => EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
	// EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * TypeNameExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TypeNameExpCS_Rules() {
	// EssentialOCL::TypeExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
	// EssentialOCL::TypeExpWithoutMultiplicityCS => EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
	// EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * TypeParameterCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TypeParameterCS_Rules() {
	// Base::TypeParameterCS(basecs::TypeParameterCS): { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS, new java.util.ArrayList<>());
	}
	/**
	 * TypedTypeRefCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_TypedTypeRefCS_Rules() {
	// Base::TypeRefCS => OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS
	// Base::TypeRefCS => OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
	// Base::TypeRefCS => OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
	// OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] }
	// OCLinEcore::TypedRefCS => OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS
	// OCLinEcore::TypedRefCS => OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
	// OCLinEcore::TypedRefCS => OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
	// OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS
	// OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
	// OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS, new java.util.ArrayList<>());
	}
	/**
	 * UnlimitedNaturalLiteralExpCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_UnlimitedNaturalLiteralExpCS_Rules() {
	// EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*'
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS, new java.util.ArrayList<>());
	}
	/**
	 * VariableCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_VariableCS_Rules() {
	// EssentialOCL::CoIteratorVariableCS(essentialoclcs::VariableCS): { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS, new java.util.ArrayList<>());
	}
	/**
	 * WildcardTypeRefCS
	 */
	private org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules create_WildcardTypeRefCS_Rules() {
	// Base::TypeRefCS => Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
	// Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		return new org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS, new java.util.ArrayList<>());
	}
}
