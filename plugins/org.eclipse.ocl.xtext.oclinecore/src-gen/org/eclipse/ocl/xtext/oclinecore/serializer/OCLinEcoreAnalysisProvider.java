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
import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;

//	import Inject;

	public class OCLinEcoreAnalysisProvider extends AbstractAnalysisProvider
	{
	//	@Inject
	//	private GrammarProvider grammarProvider;

		private static RTGrammarAnalysis analysis = null;

		@Override
		public RTGrammarAnalysis getAnalysis() {
			if (analysis == null) {
				analysis = new RTGrammarAnalysis(
					new EClassData [] {
						ec._00  /* basecs::AnnotationCS */,
						ec._01  /* basecs::AttributeCS */,
						ec._02  /* essentialoclcs::BooleanLiteralExpCS */,
						ec._03  /* essentialoclcs::CollectionLiteralExpCS */,
						ec._04  /* essentialoclcs::CollectionLiteralPartCS */,
						ec._05  /* essentialoclcs::CollectionPatternCS */,
						ec._06  /* essentialoclcs::CollectionTypeCS */,
						ec._07  /* essentialoclcs::ContextCS */,
						ec._08  /* essentialoclcs::CurlyBracketedClauseCS */,
						ec._09  /* basecs::DataTypeCS */,
						ec._10  /* basecs::DetailCS */,
						ec._11  /* basecs::DocumentationCS */,
						ec._12  /* basecs::EnumerationCS */,
						ec._13  /* basecs::EnumerationLiteralCS */,
						ec._14  /* essentialoclcs::ExpCS */,
						ec._15  /* essentialoclcs::ExpSpecificationCS */,
						ec._16  /* essentialoclcs::IfExpCS */,
						ec._17  /* essentialoclcs::IfThenExpCS */,
						ec._18  /* basecs::ImplicitOppositeCS */,
						ec._19  /* basecs::ImportCS */,
						ec._20  /* essentialoclcs::InfixExpCS */,
						ec._21  /* essentialoclcs::InvalidLiteralExpCS */,
						ec._22  /* essentialoclcs::LambdaLiteralExpCS */,
						ec._23  /* essentialoclcs::LetExpCS */,
						ec._24  /* essentialoclcs::LetVariableCS */,
						ec._25  /* essentialoclcs::MapLiteralExpCS */,
						ec._26  /* essentialoclcs::MapLiteralPartCS */,
						ec._27  /* essentialoclcs::MapTypeCS */,
						ec._28  /* basecs::ModelElementRefCS */,
						ec._29  /* basecs::MultiplicityBoundsCS */,
						ec._30  /* basecs::MultiplicityStringCS */,
						ec._31  /* essentialoclcs::NameExpCS */,
						ec._32  /* essentialoclcs::NavigatingArgCS */,
						ec._33  /* essentialoclcs::NestedExpCS */,
						ec._34  /* essentialoclcs::NullLiteralExpCS */,
						ec._35  /* essentialoclcs::NumberLiteralExpCS */,
						ec._36  /* oclinecorecs::OCLinEcoreConstraintCS */,
						ec._37  /* basecs::OperationCS */,
						ec._38  /* basecs::PackageCS */,
						ec._39  /* basecs::ParameterCS */,
						ec._40  /* basecs::PathElementCS */,
						ec._41  /* basecs::PathElementWithURICS */,
						ec._42  /* basecs::PathNameCS */,
						ec._43  /* essentialoclcs::PatternExpCS */,
						ec._44  /* essentialoclcs::PrefixExpCS */,
						ec._45  /* basecs::PrimitiveTypeRefCS */,
						ec._46  /* basecs::ReferenceCS */,
						ec._47  /* essentialoclcs::RoundBracketedClauseCS */,
						ec._48  /* essentialoclcs::SelfExpCS */,
						ec._49  /* essentialoclcs::ShadowPartCS */,
						ec._50  /* essentialoclcs::SquareBracketedClauseCS */,
						ec._51  /* essentialoclcs::StringLiteralExpCS */,
						ec._52  /* basecs::StructuredClassCS */,
						ec._53  /* oclinecorecs::SysMLCS */,
						ec._54  /* basecs::TemplateBindingCS */,
						ec._55  /* basecs::TemplateParameterSubstitutionCS */,
						ec._56  /* basecs::TemplateSignatureCS */,
						ec._57  /* oclinecorecs::TopLevelCS */,
						ec._58  /* essentialoclcs::TupleLiteralExpCS */,
						ec._59  /* essentialoclcs::TupleLiteralPartCS */,
						ec._60  /* basecs::TuplePartCS */,
						ec._61  /* basecs::TupleTypeCS */,
						ec._62  /* essentialoclcs::TypeLiteralExpCS */,
						ec._63  /* essentialoclcs::TypeNameExpCS */,
						ec._64  /* basecs::TypeParameterCS */,
						ec._65  /* basecs::TypedTypeRefCS */,
						ec._66  /* essentialoclcs::UnlimitedNaturalLiteralExpCS */,
						ec._67  /* essentialoclcs::VariableCS */,
						ec._68  /* basecs::WildcardTypeRefCS */
					},
					new AbstractRuleValue [] {
						rv._000  /* ANY_OTHER */,
						rv._001  /* AnnotationCS */,
						rv._002  /* AnnotationElementCS */,
						rv._003  /* AttributeCS */,
						rv._004  /* BinaryOperatorName */,
						rv._005  /* BooleanLiteralExpCS */,
						rv._006  /* ClassCS */,
						rv._007  /* CoIteratorVariableCS */,
						rv._008  /* CollectionLiteralExpCS */,
						rv._009  /* CollectionLiteralPartCS */,
						rv._010  /* CollectionPatternCS */,
						rv._011  /* CollectionTypeCS */,
						rv._012  /* CollectionTypeIdentifier */,
						rv._013  /* CurlyBracketedClauseCS */,
						rv._014  /* DOUBLE_QUOTED_STRING */,
						rv._015  /* DataTypeCS */,
						rv._016  /* DetailCS */,
						rv._017  /* DocumentationCS */,
						rv._018  /* ESCAPED_CHARACTER */,
						rv._019  /* ESCAPED_ID */,
						rv._020  /* ElseIfThenExpCS */,
						rv._021  /* EnumerationCS */,
						rv._022  /* EnumerationLiteralCS */,
						rv._023  /* EnumerationLiteralName */,
						rv._024  /* EssentialOCLInfixOperatorName */,
						rv._025  /* EssentialOCLNavigationOperatorName */,
						rv._026  /* EssentialOCLReservedKeyword */,
						rv._027  /* EssentialOCLUnaryOperatorName */,
						rv._028  /* EssentialOCLUnreservedName */,
						rv._029  /* EssentialOCLUnrestrictedName */,
						rv._030  /* ExpCS */,
						rv._031  /* FirstPathElementCS */,
						rv._032  /* ID */,
						rv._033  /* INT */,
						rv._034  /* INTEGER */,
						rv._035  /* Identifier */,
						rv._036  /* IfExpCS */,
						rv._037  /* ImplicitOppositeCS */,
						rv._038  /* ImportCS */,
						rv._039  /* InfixOperatorName */,
						rv._040  /* InvalidLiteralExpCS */,
						rv._041  /* InvariantConstraintCS */,
						rv._042  /* LETTER_CHARACTER */,
						rv._043  /* LOWER */,
						rv._044  /* LambdaLiteralExpCS */,
						rv._045  /* LetExpCS */,
						rv._046  /* LetVariableCS */,
						rv._047  /* ML_COMMENT */,
						rv._048  /* ML_SINGLE_QUOTED_STRING */,
						rv._049  /* MapLiteralExpCS */,
						rv._050  /* MapLiteralPartCS */,
						rv._051  /* MapTypeCS */,
						rv._052  /* Model */,
						rv._053  /* ModelElementCS */,
						rv._054  /* ModelElementRefCS */,
						rv._055  /* MultiplicityBoundsCS */,
						rv._056  /* MultiplicityCS */,
						rv._057  /* MultiplicityStringCS */,
						rv._058  /* NUMBER_LITERAL */,
						rv._059  /* NameExpCS */,
						rv._060  /* NavigatingArgCS */,
						rv._061  /* NavigatingArgExpCS */,
						rv._062  /* NavigatingBarArgCS */,
						rv._063  /* NavigatingCommaArgCS */,
						rv._064  /* NavigatingSemiArgCS */,
						rv._065  /* NavigationOperatorName */,
						rv._066  /* NestedExpCS */,
						rv._067  /* NextPathElementCS */,
						rv._068  /* NullLiteralExpCS */,
						rv._069  /* NumberLiteralExpCS */,
						rv._070  /* OperationCS */,
						rv._071  /* PackageCS */,
						rv._072  /* ParameterCS */,
						rv._073  /* PathNameCS */,
						rv._074  /* PatternExpCS */,
						rv._075  /* PostconditionConstraintCS */,
						rv._076  /* PreconditionConstraintCS */,
						rv._077  /* PrefixedLetExpCS */,
						rv._078  /* PrefixedPrimaryExpCS */,
						rv._079  /* PrimaryExpCS */,
						rv._080  /* PrimitiveLiteralExpCS */,
						rv._081  /* PrimitiveTypeCS */,
						rv._082  /* PrimitiveTypeIdentifier */,
						rv._083  /* ReferenceCS */,
						rv._084  /* RoundBracketedClauseCS */,
						rv._085  /* SIGNED */,
						rv._086  /* SIMPLE_ID */,
						rv._087  /* SINGLE_QUOTED_STRING */,
						rv._088  /* SL_COMMENT */,
						rv._089  /* SelfExpCS */,
						rv._090  /* ShadowPartCS */,
						rv._091  /* SimplePathNameCS */,
						rv._092  /* SpecificationCS */,
						rv._093  /* SquareBracketedClauseCS */,
						rv._094  /* StringLiteral */,
						rv._095  /* StringLiteralExpCS */,
						rv._096  /* StructuralFeatureCS */,
						rv._097  /* StructuredClassCS */,
						rv._098  /* SysMLCS */,
						rv._099  /* TemplateBindingCS */,
						rv._100  /* TemplateParameterSubstitutionCS */,
						rv._101  /* TemplateSignatureCS */,
						rv._102  /* TopLevelCS */,
						rv._103  /* TupleLiteralExpCS */,
						rv._104  /* TupleLiteralPartCS */,
						rv._105  /* TuplePartCS */,
						rv._106  /* TupleTypeCS */,
						rv._107  /* TypeExpCS */,
						rv._108  /* TypeExpWithoutMultiplicityCS */,
						rv._109  /* TypeIdentifier */,
						rv._110  /* TypeLiteralCS */,
						rv._111  /* TypeLiteralExpCS */,
						rv._112  /* TypeLiteralWithMultiplicityCS */,
						rv._113  /* TypeNameExpCS */,
						rv._114  /* TypeParameterCS */,
						rv._115  /* TypeRefCS */,
						rv._116  /* TypedMultiplicityRefCS */,
						rv._117  /* TypedRefCS */,
						rv._118  /* TypedTypeRefCS */,
						rv._119  /* UNQUOTED_STRING */,
						rv._120  /* UPPER */,
						rv._121  /* URI */,
						rv._122  /* URIFirstPathElementCS */,
						rv._123  /* URIPathNameCS */,
						rv._124  /* UnaryOperatorName */,
						rv._125  /* UnlimitedNaturalLiteralExpCS */,
						rv._126  /* UnreservedName */,
						rv._127  /* UnrestrictedName */,
						rv._128  /* WS */,
						rv._129  /* WildcardTypeRefCS */
					}
				);
			}
			return analysis;
		}

		private class _EnumValues
		{
			private final /*@NonNull*/ EnumerationValue _00 // '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"!composes", "!derived", "!ordered", "!readonly", "!resolve", "!transient", "!unique", "!unsettable", "!volatile", "composes", "derived", "ordered", "readonly", "resolve", "transient", "unique", "unsettable", "volatile"});
			private final /*@NonNull*/ EnumerationValue _01 // '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"!derived", "!id", "!ordered", "!readonly", "!transient", "!unique", "!unsettable", "!volatile", "derived", "id", "ordered", "readonly", "transient", "unique", "unsettable", "volatile"});
			private final /*@NonNull*/ EnumerationValue _02 // '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"!derived", "!ordered", "!transient", "!unique", "derived", "ordered", "transient", "unique"});
			private final /*@NonNull*/ EnumerationValue _03 // '!ordered|!unique|ordered|unique'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"!ordered", "!unique", "ordered", "unique"});
			private final /*@NonNull*/ EnumerationValue _04 // '*|+|?'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"*", "+", "?"});
			private final /*@NonNull*/ EnumerationValue _05 // ','
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue(",");
			private final /*@NonNull*/ EnumerationValue _06 // '::*'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("::*");
			private final /*@NonNull*/ EnumerationValue _07 // ';'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue(";");
			private final /*@NonNull*/ EnumerationValue _08 // '@'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("@");
			private final /*@NonNull*/ EnumerationValue _09 // 'Map'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("Map");
			private final /*@NonNull*/ EnumerationValue _10 // 'Tuple'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("Tuple");
			private final /*@NonNull*/ EnumerationValue _11 // 'abstract'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("abstract");
			private final /*@NonNull*/ EnumerationValue _12 // 'callable'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("callable");
			private final /*@NonNull*/ EnumerationValue _13 // 'definition'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("definition");
			private final /*@NonNull*/ EnumerationValue _14 // 'false|true'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"false", "true"});
			private final /*@NonNull*/ EnumerationValue _15 // 'interface'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("interface");
			private final /*@NonNull*/ EnumerationValue _16 // 'invariant'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("invariant");
			private final /*@NonNull*/ EnumerationValue _17 // 'postcondition'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("postcondition");
			private final /*@NonNull*/ EnumerationValue _18 // 'precondition'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("precondition");
			private final /*@NonNull*/ EnumerationValue _19 // 'primitive'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("primitive");
			private final /*@NonNull*/ EnumerationValue _20 // 'serializable'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("serializable");
			private final /*@NonNull*/ EnumerationValue _21 // 'static'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("static");
			private final /*@NonNull*/ EnumerationValue _22 // '|'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("|");
			private final /*@NonNull*/ EnumerationValue _23 // '|1'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("|1");
		}

		private class _MatchTerms
		{
			private final /*@NonNull*/ CardinalitySolution _000 // 0
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution(0);
			private final /*@NonNull*/ CardinalitySolution _001 // 1
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution(1);
			private final /*@NonNull*/ CardinalitySolution _002 // V0
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution(0);
			private final /*@NonNull*/ CardinalitySolution _003 // |default|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
			private final /*@NonNull*/ CardinalitySolution _004 // |exprString|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
			private final /*@NonNull*/ CardinalitySolution _005 // |instanceClassName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
			private final /*@NonNull*/ CardinalitySolution _006 // |isAbstract.'abstract'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, ev._11);
			private final /*@NonNull*/ CardinalitySolution _007 // |isAll.'::*'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__IS_ALL, ev._06);
			private final /*@NonNull*/ CardinalitySolution _008 // |isCallable.'callable'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, ev._12);
			private final /*@NonNull*/ CardinalitySolution _009 // |isInterface.'interface'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, ev._15);
			private final /*@NonNull*/ CardinalitySolution _010 // |isNullFree.'|1'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._23);
			private final /*@NonNull*/ CardinalitySolution _011 // |isPre.'@'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, ev._08);
			private final /*@NonNull*/ CardinalitySolution _012 // |isPrimitive.'primitive'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, ev._19);
			private final /*@NonNull*/ CardinalitySolution _013 // |isSerializable.'serializable'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, ev._20);
			private final /*@NonNull*/ CardinalitySolution _014 // |isSerializable.'serializable'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, ev._20);
			private final /*@NonNull*/ CardinalitySolution _015 // |literal|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
			private final /*@NonNull*/ CardinalitySolution _016 // |lowerBound|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
			private final /*@NonNull*/ CardinalitySolution _017 // |name.'Map'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._09);
			private final /*@NonNull*/ CardinalitySolution _018 // |name.'Tuple'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._10);
			private final /*@NonNull*/ CardinalitySolution _019 // |name|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
			private final /*@NonNull*/ CardinalitySolution _020 // |name|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ CardinalitySolution _021 // |name|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
			private final /*@NonNull*/ CardinalitySolution _022 // |nsPrefix|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
			private final /*@NonNull*/ CardinalitySolution _023 // |nsURI|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
			private final /*@NonNull*/ CardinalitySolution _024 // |ownedActualParameter|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
			private final /*@NonNull*/ CardinalitySolution _025 // |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ CardinalitySolution _026 // |ownedArguments|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
			private final /*@NonNull*/ CardinalitySolution _027 // |ownedBinding|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
			private final /*@NonNull*/ CardinalitySolution _028 // |ownedBodyExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
			private final /*@NonNull*/ CardinalitySolution _029 // |ownedClasses|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
			private final /*@NonNull*/ CardinalitySolution _030 // |ownedCoIterator|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
			private final /*@NonNull*/ CardinalitySolution _031 // |ownedCollectionMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
			private final /*@NonNull*/ CardinalitySolution _032 // |ownedCondition|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
			private final /*@NonNull*/ CardinalitySolution _033 // |ownedCondition|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
			private final /*@NonNull*/ CardinalitySolution _034 // |ownedConstraints|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
			private final /*@NonNull*/ CardinalitySolution _035 // |ownedContents|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
			private final /*@NonNull*/ CardinalitySolution _036 // |ownedCurlyBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
			private final /*@NonNull*/ CardinalitySolution _037 // |ownedCurlyBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
			private final /*@NonNull*/ CardinalitySolution _038 // |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ CardinalitySolution _039 // |ownedDetails|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
			private final /*@NonNull*/ CardinalitySolution _040 // |ownedElseExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _041 // |ownedExceptions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
			private final /*@NonNull*/ CardinalitySolution _042 // |ownedExpressionCS|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
			private final /*@NonNull*/ CardinalitySolution _043 // |ownedExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _044 // |ownedExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _045 // |ownedExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _046 // |ownedExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _047 // |ownedExtends|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
			private final /*@NonNull*/ CardinalitySolution _048 // |ownedExtends|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
			private final /*@NonNull*/ CardinalitySolution _049 // |ownedIfThenExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
			private final /*@NonNull*/ CardinalitySolution _050 // |ownedImplicitOpposites|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
			private final /*@NonNull*/ CardinalitySolution _051 // |ownedImports|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
			private final /*@NonNull*/ CardinalitySolution _052 // |ownedInExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _053 // |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _054 // |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _055 // |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _056 // |ownedKeyType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
			private final /*@NonNull*/ CardinalitySolution _057 // |ownedKey|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
			private final /*@NonNull*/ CardinalitySolution _058 // |ownedLastExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _059 // |ownedLeft|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
			private final /*@NonNull*/ CardinalitySolution _060 // |ownedLiterals|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
			private final /*@NonNull*/ CardinalitySolution _061 // |ownedMessageSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
			private final /*@NonNull*/ CardinalitySolution _062 // |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ CardinalitySolution _063 // |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ CardinalitySolution _064 // |ownedNameExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _065 // |ownedOperations|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
			private final /*@NonNull*/ CardinalitySolution _066 // |ownedPackages|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
			private final /*@NonNull*/ CardinalitySolution _067 // |ownedParameters|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
			private final /*@NonNull*/ CardinalitySolution _068 // |ownedParameters|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
			private final /*@NonNull*/ CardinalitySolution _069 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _070 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _071 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _072 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _073 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _074 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _075 // |ownedPathElements|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
			private final /*@NonNull*/ CardinalitySolution _076 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _077 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _078 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _079 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _080 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _081 // |ownedPatternGuard|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
			private final /*@NonNull*/ CardinalitySolution _082 // |ownedPatternType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
			private final /*@NonNull*/ CardinalitySolution _083 // |ownedPostconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
			private final /*@NonNull*/ CardinalitySolution _084 // |ownedPreconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
			private final /*@NonNull*/ CardinalitySolution _085 // |ownedProperties|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
			private final /*@NonNull*/ CardinalitySolution _086 // |ownedReferences|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
			private final /*@NonNull*/ CardinalitySolution _087 // |ownedRight|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
			private final /*@NonNull*/ CardinalitySolution _088 // |ownedRoundBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
			private final /*@NonNull*/ CardinalitySolution _089 // |ownedRoundBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
			private final /*@NonNull*/ CardinalitySolution _090 // |ownedSignature|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
			private final /*@NonNull*/ CardinalitySolution _091 // |ownedSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
			private final /*@NonNull*/ CardinalitySolution _092 // |ownedSquareBracketedClauses|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
			private final /*@NonNull*/ CardinalitySolution _093 // |ownedSubstitutions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
			private final /*@NonNull*/ CardinalitySolution _094 // |ownedSuperTypes|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
			private final /*@NonNull*/ CardinalitySolution _095 // |ownedTerms|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
			private final /*@NonNull*/ CardinalitySolution _096 // |ownedThenExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _097 // |ownedThenExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _098 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _099 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _100 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _101 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _102 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _103 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _104 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _105 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _106 // |ownedValueType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
			private final /*@NonNull*/ CardinalitySolution _107 // |ownedValue|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
			private final /*@NonNull*/ CardinalitySolution _108 // |ownedVariables|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
			private final /*@NonNull*/ CardinalitySolution _109 // |patternVariableName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
			private final /*@NonNull*/ CardinalitySolution _110 // |prefix.','|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._05);
			private final /*@NonNull*/ CardinalitySolution _111 // |prefix.';'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._07);
			private final /*@NonNull*/ CardinalitySolution _112 // |prefix.'|'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._22);
			private final /*@NonNull*/ CardinalitySolution _113 // |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._00);
			private final /*@NonNull*/ CardinalitySolution _114 // |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._01);
			private final /*@NonNull*/ CardinalitySolution _115 // |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._02);
			private final /*@NonNull*/ CardinalitySolution _116 // |qualifiers.'!ordered|!unique|ordered|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._03);
			private final /*@NonNull*/ CardinalitySolution _117 // |qualifiers.'definition'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._13);
			private final /*@NonNull*/ CardinalitySolution _118 // |qualifiers.'static'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._21);
			private final /*@NonNull*/ CardinalitySolution _119 // |referredElement|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
			private final /*@NonNull*/ CardinalitySolution _120 // |referredKeys|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
			private final /*@NonNull*/ CardinalitySolution _121 // |referredOpposite|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
			private final /*@NonNull*/ CardinalitySolution _122 // |referredProperty|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
			private final /*@NonNull*/ CardinalitySolution _123 // |restVariableName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
			private final /*@NonNull*/ CardinalitySolution _124 // |segments|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
			private final /*@NonNull*/ CardinalitySolution _125 // |stereotype.'invariant'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._16);
			private final /*@NonNull*/ CardinalitySolution _126 // |stereotype.'postcondition'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._17);
			private final /*@NonNull*/ CardinalitySolution _127 // |stereotype.'precondition'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._18);
			private final /*@NonNull*/ CardinalitySolution _128 // |stringBounds.'*|+|?'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._04);
			private final /*@NonNull*/ CardinalitySolution _129 // |symbol.'false|true'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._14);
			private final /*@NonNull*/ CardinalitySolution _130 // |symbol|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
			private final /*@NonNull*/ CardinalitySolution _131 // |upperBound|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
			private final /*@NonNull*/ CardinalitySolution _132 // |values|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS__VALUES);
			private final /*@NonNull*/ CardinalitySolution _133 // |value|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
			private final /*@NonNull*/ CardinalitySolution _134 // |value|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
			private final /*@NonNull*/ CardinalitySolution _135 // (|exprString| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_004, _001);
			private final /*@NonNull*/ CardinalitySolution _136 // (|isInterface.'interface'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_009, _000);
			private final /*@NonNull*/ CardinalitySolution _137 // (|isSerializable.'serializable'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_013, _000);
			private final /*@NonNull*/ CardinalitySolution _138 // (|isSerializable.'serializable'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_014, _000);
			private final /*@NonNull*/ CardinalitySolution _139 // (|lowerBound| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_016, _001);
			private final /*@NonNull*/ CardinalitySolution _140 // (|name.'Map'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_017, _001);
			private final /*@NonNull*/ CardinalitySolution _141 // (|name.'Tuple'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_018, _001);
			private final /*@NonNull*/ CardinalitySolution _142 // (|name| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_020, _001);
			private final /*@NonNull*/ CardinalitySolution _143 // (|name| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_021, _001);
			private final /*@NonNull*/ CardinalitySolution _144 // (|name| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_019, _001);
			private final /*@NonNull*/ CardinalitySolution _145 // (|ownedActualParameter| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_024, _001);
			private final /*@NonNull*/ CardinalitySolution _146 // (|ownedAnnotations| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_025, _000);
			private final /*@NonNull*/ CardinalitySolution _147 // (|ownedArguments| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_026, _001);
			private final /*@NonNull*/ CardinalitySolution _148 // (|ownedArguments| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_026, _000);
			private final /*@NonNull*/ CardinalitySolution _149 // (|ownedBinding| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_027, _001);
			private final /*@NonNull*/ CardinalitySolution _150 // (|ownedBodyExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_028, _000);
			private final /*@NonNull*/ CardinalitySolution _151 // (|ownedCoIterator| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_030, _001);
			private final /*@NonNull*/ CardinalitySolution _152 // (|ownedCondition| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_032, _001);
			private final /*@NonNull*/ CardinalitySolution _153 // (|ownedCondition| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_033, _001);
			private final /*@NonNull*/ CardinalitySolution _154 // (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_038, _000);
			private final /*@NonNull*/ CardinalitySolution _155 // (|ownedDetails| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_039, _001);
			private final /*@NonNull*/ CardinalitySolution _156 // (|ownedDetails| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_039, _000);
			private final /*@NonNull*/ CardinalitySolution _157 // (|ownedElseExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_040, _001);
			private final /*@NonNull*/ CardinalitySolution _158 // (|ownedExceptions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_041, _001);
			private final /*@NonNull*/ CardinalitySolution _159 // (|ownedExceptions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_041, _000);
			private final /*@NonNull*/ CardinalitySolution _160 // (|ownedExpressionCS| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_042, _001);
			private final /*@NonNull*/ CardinalitySolution _161 // (|ownedExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_044, _001);
			private final /*@NonNull*/ CardinalitySolution _162 // (|ownedExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_045, _001);
			private final /*@NonNull*/ CardinalitySolution _163 // (|ownedExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_046, _001);
			private final /*@NonNull*/ CardinalitySolution _164 // (|ownedExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_043, _001);
			private final /*@NonNull*/ CardinalitySolution _165 // (|ownedExtends| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_048, _001);
			private final /*@NonNull*/ CardinalitySolution _166 // (|ownedExtends| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_048, _000);
			private final /*@NonNull*/ CardinalitySolution _167 // (|ownedInExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_052, _001);
			private final /*@NonNull*/ CardinalitySolution _168 // (|ownedInitExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_054, _001);
			private final /*@NonNull*/ CardinalitySolution _169 // (|ownedInitExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_055, _001);
			private final /*@NonNull*/ CardinalitySolution _170 // (|ownedInitExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_053, _001);
			private final /*@NonNull*/ CardinalitySolution _171 // (|ownedKeyType| - V0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_056, _002);
			private final /*@NonNull*/ CardinalitySolution _172 // (|ownedKey| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_057, _001);
			private final /*@NonNull*/ CardinalitySolution _173 // (|ownedLeft| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_059, _001);
			private final /*@NonNull*/ CardinalitySolution _174 // (|ownedNameExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_064, _001);
			private final /*@NonNull*/ CardinalitySolution _175 // (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_068, _001);
			private final /*@NonNull*/ CardinalitySolution _176 // (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_067, _001);
			private final /*@NonNull*/ CardinalitySolution _177 // (|ownedParameters| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_067, _000);
			private final /*@NonNull*/ CardinalitySolution _178 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_073, _001);
			private final /*@NonNull*/ CardinalitySolution _179 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_069, _001);
			private final /*@NonNull*/ CardinalitySolution _180 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_071, _001);
			private final /*@NonNull*/ CardinalitySolution _181 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_074, _001);
			private final /*@NonNull*/ CardinalitySolution _182 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_070, _001);
			private final /*@NonNull*/ CardinalitySolution _183 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_072, _001);
			private final /*@NonNull*/ CardinalitySolution _184 // (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_069, _000);
			private final /*@NonNull*/ CardinalitySolution _185 // (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_071, _000);
			private final /*@NonNull*/ CardinalitySolution _186 // (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_073, _000);
			private final /*@NonNull*/ CardinalitySolution _187 // (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_074, _000);
			private final /*@NonNull*/ CardinalitySolution _188 // (|ownedPathElements| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_075, _001);
			private final /*@NonNull*/ CardinalitySolution _189 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_076, _001);
			private final /*@NonNull*/ CardinalitySolution _190 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_078, _001);
			private final /*@NonNull*/ CardinalitySolution _191 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_077, _001);
			private final /*@NonNull*/ CardinalitySolution _192 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_079, _001);
			private final /*@NonNull*/ CardinalitySolution _193 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_080, _001);
			private final /*@NonNull*/ CardinalitySolution _194 // (|ownedPatternType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_082, _001);
			private final /*@NonNull*/ CardinalitySolution _195 // (|ownedRight| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_087, _001);
			private final /*@NonNull*/ CardinalitySolution _196 // (|ownedSubstitutions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_093, _001);
			private final /*@NonNull*/ CardinalitySolution _197 // (|ownedSuperTypes| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_094, _001);
			private final /*@NonNull*/ CardinalitySolution _198 // (|ownedSuperTypes| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_094, _000);
			private final /*@NonNull*/ CardinalitySolution _199 // (|ownedTerms| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_095, _001);
			private final /*@NonNull*/ CardinalitySolution _200 // (|ownedThenExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_097, _001);
			private final /*@NonNull*/ CardinalitySolution _201 // (|ownedThenExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_096, _001);
			private final /*@NonNull*/ CardinalitySolution _202 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_102, _001);
			private final /*@NonNull*/ CardinalitySolution _203 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_103, _001);
			private final /*@NonNull*/ CardinalitySolution _204 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_098, _001);
			private final /*@NonNull*/ CardinalitySolution _205 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_105, _001);
			private final /*@NonNull*/ CardinalitySolution _206 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_100, _001);
			private final /*@NonNull*/ CardinalitySolution _207 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_104, _001);
			private final /*@NonNull*/ CardinalitySolution _208 // (|ownedValue| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_107, _001);
			private final /*@NonNull*/ CardinalitySolution _209 // (|ownedVariables| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_108, _001);
			private final /*@NonNull*/ CardinalitySolution _210 // (|prefix.','| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_110, _001);
			private final /*@NonNull*/ CardinalitySolution _211 // (|prefix.';'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_111, _001);
			private final /*@NonNull*/ CardinalitySolution _212 // (|prefix.'|'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_112, _001);
			private final /*@NonNull*/ CardinalitySolution _213 // (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_113, _000);
			private final /*@NonNull*/ CardinalitySolution _214 // (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_114, _000);
			private final /*@NonNull*/ CardinalitySolution _215 // (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_115, _000);
			private final /*@NonNull*/ CardinalitySolution _216 // (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_116, _000);
			private final /*@NonNull*/ CardinalitySolution _217 // (|qualifiers.'definition'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_117, _001);
			private final /*@NonNull*/ CardinalitySolution _218 // (|qualifiers.'static'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_118, _001);
			private final /*@NonNull*/ CardinalitySolution _219 // (|referredElement| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_119, _001);
			private final /*@NonNull*/ CardinalitySolution _220 // (|referredKeys| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_120, _001);
			private final /*@NonNull*/ CardinalitySolution _221 // (|referredKeys| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_120, _000);
			private final /*@NonNull*/ CardinalitySolution _222 // (|referredProperty| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_122, _001);
			private final /*@NonNull*/ CardinalitySolution _223 // (|stereotype.'invariant'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_125, _001);
			private final /*@NonNull*/ CardinalitySolution _224 // (|stereotype.'postcondition'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_126, _001);
			private final /*@NonNull*/ CardinalitySolution _225 // (|stereotype.'precondition'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_127, _001);
			private final /*@NonNull*/ CardinalitySolution _226 // (|stringBounds.'*|+|?'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_128, _001);
			private final /*@NonNull*/ CardinalitySolution _227 // (|symbol.'false|true'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_129, _001);
			private final /*@NonNull*/ CardinalitySolution _228 // (|symbol| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_130, _001);
		}

		private class _MatchSteps
		{
			private final /*@NonNull*/ CardinalitySolutionStep _000 // assert (|exprString| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._135);
			private final /*@NonNull*/ CardinalitySolutionStep _001 // assert (|lowerBound| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._139);
			private final /*@NonNull*/ CardinalitySolutionStep _002 // assert (|name.'Map'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._140);
			private final /*@NonNull*/ CardinalitySolutionStep _003 // assert (|name.'Tuple'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._141);
			private final /*@NonNull*/ CardinalitySolutionStep _004 // assert (|name| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._143);
			private final /*@NonNull*/ CardinalitySolutionStep _005 // assert (|name| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._144);
			private final /*@NonNull*/ CardinalitySolutionStep _006 // assert (|name| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._142);
			private final /*@NonNull*/ CardinalitySolutionStep _007 // assert (|ownedActualParameter| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._145);
			private final /*@NonNull*/ CardinalitySolutionStep _008 // assert (|ownedBinding| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._149);
			private final /*@NonNull*/ CardinalitySolutionStep _009 // assert (|ownedCoIterator| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._151);
			private final /*@NonNull*/ CardinalitySolutionStep _010 // assert (|ownedCondition| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._152);
			private final /*@NonNull*/ CardinalitySolutionStep _011 // assert (|ownedCondition| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._153);
			private final /*@NonNull*/ CardinalitySolutionStep _012 // assert (|ownedDetails| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._155);
			private final /*@NonNull*/ CardinalitySolutionStep _013 // assert (|ownedElseExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._157);
			private final /*@NonNull*/ CardinalitySolutionStep _014 // assert (|ownedExpressionCS| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._160);
			private final /*@NonNull*/ CardinalitySolutionStep _015 // assert (|ownedExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._161);
			private final /*@NonNull*/ CardinalitySolutionStep _016 // assert (|ownedExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._162);
			private final /*@NonNull*/ CardinalitySolutionStep _017 // assert (|ownedExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._163);
			private final /*@NonNull*/ CardinalitySolutionStep _018 // assert (|ownedExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._164);
			private final /*@NonNull*/ CardinalitySolutionStep _019 // assert (|ownedInExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._167);
			private final /*@NonNull*/ CardinalitySolutionStep _020 // assert (|ownedInitExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._170);
			private final /*@NonNull*/ CardinalitySolutionStep _021 // assert (|ownedInitExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._168);
			private final /*@NonNull*/ CardinalitySolutionStep _022 // assert (|ownedInitExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._169);
			private final /*@NonNull*/ CardinalitySolutionStep _023 // assert (|ownedKeyType| - V0) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._171);
			private final /*@NonNull*/ CardinalitySolutionStep _024 // assert (|ownedKey| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._172);
			private final /*@NonNull*/ CardinalitySolutionStep _025 // assert (|ownedLeft| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._173);
			private final /*@NonNull*/ CardinalitySolutionStep _026 // assert (|ownedNameExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._174);
			private final /*@NonNull*/ CardinalitySolutionStep _027 // assert (|ownedPathElements| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._188);
			private final /*@NonNull*/ CardinalitySolutionStep _028 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._190);
			private final /*@NonNull*/ CardinalitySolutionStep _029 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._189);
			private final /*@NonNull*/ CardinalitySolutionStep _030 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._193);
			private final /*@NonNull*/ CardinalitySolutionStep _031 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._191);
			private final /*@NonNull*/ CardinalitySolutionStep _032 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._192);
			private final /*@NonNull*/ CardinalitySolutionStep _033 // assert (|ownedPatternType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._194);
			private final /*@NonNull*/ CardinalitySolutionStep _034 // assert (|ownedRight| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._195);
			private final /*@NonNull*/ CardinalitySolutionStep _035 // assert (|ownedThenExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._201);
			private final /*@NonNull*/ CardinalitySolutionStep _036 // assert (|ownedThenExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._200);
			private final /*@NonNull*/ CardinalitySolutionStep _037 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._203);
			private final /*@NonNull*/ CardinalitySolutionStep _038 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._202);
			private final /*@NonNull*/ CardinalitySolutionStep _039 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._205);
			private final /*@NonNull*/ CardinalitySolutionStep _040 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._206);
			private final /*@NonNull*/ CardinalitySolutionStep _041 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._204);
			private final /*@NonNull*/ CardinalitySolutionStep _042 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._207);
			private final /*@NonNull*/ CardinalitySolutionStep _043 // assert (|ownedValue| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._208);
			private final /*@NonNull*/ CardinalitySolutionStep _044 // assert (|prefix.','| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._210);
			private final /*@NonNull*/ CardinalitySolutionStep _045 // assert (|prefix.';'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._211);
			private final /*@NonNull*/ CardinalitySolutionStep _046 // assert (|prefix.'|'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._212);
			private final /*@NonNull*/ CardinalitySolutionStep _047 // assert (|qualifiers.'definition'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._217);
			private final /*@NonNull*/ CardinalitySolutionStep _048 // assert (|qualifiers.'static'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._218);
			private final /*@NonNull*/ CardinalitySolutionStep _049 // assert (|referredElement| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._219);
			private final /*@NonNull*/ CardinalitySolutionStep _050 // assert (|referredProperty| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._222);
			private final /*@NonNull*/ CardinalitySolutionStep _051 // assert (|stereotype.'invariant'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._223);
			private final /*@NonNull*/ CardinalitySolutionStep _052 // assert (|stereotype.'postcondition'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._224);
			private final /*@NonNull*/ CardinalitySolutionStep _053 // assert (|stereotype.'precondition'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._225);
			private final /*@NonNull*/ CardinalitySolutionStep _054 // assert (|stringBounds.'*|+|?'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._226);
			private final /*@NonNull*/ CardinalitySolutionStep _055 // assert (|symbol.'false|true'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._227);
			private final /*@NonNull*/ CardinalitySolutionStep _056 // assert (|symbol| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._228);
			private final /*@NonNull*/ CardinalitySolutionStep _057 // assign V0 = (|ownedArguments| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._148);
			private final /*@NonNull*/ CardinalitySolutionStep _058 // assign V0 = (|ownedExtends| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._166);
			private final /*@NonNull*/ CardinalitySolutionStep _059 // assign V0 = (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._175);
			private final /*@NonNull*/ CardinalitySolutionStep _060 // assign V0 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._183);
			private final /*@NonNull*/ CardinalitySolutionStep _061 // assign V0 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._184);
			private final /*@NonNull*/ CardinalitySolutionStep _062 // assign V0 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._186);
			private final /*@NonNull*/ CardinalitySolutionStep _063 // assign V0 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._187);
			private final /*@NonNull*/ CardinalitySolutionStep _064 // assign V0 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._185);
			private final /*@NonNull*/ CardinalitySolutionStep _065 // assign V0 = (|ownedPathElements| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._188);
			private final /*@NonNull*/ CardinalitySolutionStep _066 // assign V0 = (|ownedSubstitutions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._196);
			private final /*@NonNull*/ CardinalitySolutionStep _067 // assign V0 = (|ownedTerms| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._199);
			private final /*@NonNull*/ CardinalitySolutionStep _068 // assign V0 = (|ownedVariables| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._209);
			private final /*@NonNull*/ CardinalitySolutionStep _069 // assign V0 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._216);
			private final /*@NonNull*/ CardinalitySolutionStep _070 // assign V0 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _071 // assign V0 = |isAbstract.'abstract'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._006);
			private final /*@NonNull*/ CardinalitySolutionStep _072 // assign V0 = |isCallable.'callable'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._008);
			private final /*@NonNull*/ CardinalitySolutionStep _073 // assign V0 = |isNullFree.'|1'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._010);
			private final /*@NonNull*/ CardinalitySolutionStep _074 // assign V0 = |isPrimitive.'primitive'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._012);
			private final /*@NonNull*/ CardinalitySolutionStep _075 // assign V0 = |literal|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._015);
			private final /*@NonNull*/ CardinalitySolutionStep _076 // assign V0 = |name|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._020);
			private final /*@NonNull*/ CardinalitySolutionStep _077 // assign V0 = |nsPrefix|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._022);
			private final /*@NonNull*/ CardinalitySolutionStep _078 // assign V0 = |ownedCoIterator|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._030);
			private final /*@NonNull*/ CardinalitySolutionStep _079 // assign V0 = |ownedCurlyBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._036);
			private final /*@NonNull*/ CardinalitySolutionStep _080 // assign V0 = |ownedDetails|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._039);
			private final /*@NonNull*/ CardinalitySolutionStep _081 // assign V0 = |ownedExtends|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._047);
			private final /*@NonNull*/ CardinalitySolutionStep _082 // assign V0 = |ownedIfThenExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._049);
			private final /*@NonNull*/ CardinalitySolutionStep _083 // assign V0 = |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._054);
			private final /*@NonNull*/ CardinalitySolutionStep _084 // assign V0 = |ownedLastExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._058);
			private final /*@NonNull*/ CardinalitySolutionStep _085 // assign V0 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._063);
			private final /*@NonNull*/ CardinalitySolutionStep _086 // assign V0 = |ownedRoundBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._089);
			private final /*@NonNull*/ CardinalitySolutionStep _087 // assign V0 = |ownedSignature|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._090);
			private final /*@NonNull*/ CardinalitySolutionStep _088 // assign V0 = |ownedSquareBracketedClauses|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._092);
			private final /*@NonNull*/ CardinalitySolutionStep _089 // assign V0 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._102);
			private final /*@NonNull*/ CardinalitySolutionStep _090 // assign V0 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._099);
			private final /*@NonNull*/ CardinalitySolutionStep _091 // assign V0 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._100);
			private final /*@NonNull*/ CardinalitySolutionStep _092 // assign V0 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._101);
			private final /*@NonNull*/ CardinalitySolutionStep _093 // assign V0 = |ownedValueType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._106);
			private final /*@NonNull*/ CardinalitySolutionStep _094 // assign V0 = |patternVariableName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._109);
			private final /*@NonNull*/ CardinalitySolutionStep _095 // assign V0 = |qualifiers.'definition'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._117);
			private final /*@NonNull*/ CardinalitySolutionStep _096 // assign V0 = |qualifiers.'static'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._118);
			private final /*@NonNull*/ CardinalitySolutionStep _097 // assign V0 = |referredOpposite|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._121);
			private final /*@NonNull*/ CardinalitySolutionStep _098 // assign V0 = |restVariableName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._123);
			private final /*@NonNull*/ CardinalitySolutionStep _099 // assign V0 = |segments|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._124);
			private final /*@NonNull*/ CardinalitySolutionStep _100 // assign V0 = |upperBound|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._131);
			private final /*@NonNull*/ CardinalitySolutionStep _101 // assign V0 = |values|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._132);
			private final /*@NonNull*/ CardinalitySolutionStep _102 // assign V0 = |value|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._134);
			private final /*@NonNull*/ CardinalitySolutionStep _103 // assign V1 = (|ownedArguments| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._147);
			private final /*@NonNull*/ CardinalitySolutionStep _104 // assign V1 = (|ownedDetails| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._156);
			private final /*@NonNull*/ CardinalitySolutionStep _105 // assign V1 = (|ownedExtends| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._165);
			private final /*@NonNull*/ CardinalitySolutionStep _106 // assign V1 = (|ownedParameters| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._177);
			private final /*@NonNull*/ CardinalitySolutionStep _107 // assign V1 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._178);
			private final /*@NonNull*/ CardinalitySolutionStep _108 // assign V1 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._181);
			private final /*@NonNull*/ CardinalitySolutionStep _109 // assign V1 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._182);
			private final /*@NonNull*/ CardinalitySolutionStep _110 // assign V1 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._180);
			private final /*@NonNull*/ CardinalitySolutionStep _111 // assign V1 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._184);
			private final /*@NonNull*/ CardinalitySolutionStep _112 // assign V1 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._216);
			private final /*@NonNull*/ CardinalitySolutionStep _113 // assign V1 = |default|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._003);
			private final /*@NonNull*/ CardinalitySolutionStep _114 // assign V1 = |instanceClassName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._005);
			private final /*@NonNull*/ CardinalitySolutionStep _115 // assign V1 = |isAll.'::*'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._007);
			private final /*@NonNull*/ CardinalitySolutionStep _116 // assign V1 = |isNullFree.'|1'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._010);
			private final /*@NonNull*/ CardinalitySolutionStep _117 // assign V1 = |name|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._020);
			private final /*@NonNull*/ CardinalitySolutionStep _118 // assign V1 = |nsURI|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._023);
			private final /*@NonNull*/ CardinalitySolutionStep _119 // assign V1 = |ownedCoIterator|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._030);
			private final /*@NonNull*/ CardinalitySolutionStep _120 // assign V1 = |ownedCollectionMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._031);
			private final /*@NonNull*/ CardinalitySolutionStep _121 // assign V1 = |ownedImports|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._051);
			private final /*@NonNull*/ CardinalitySolutionStep _122 // assign V1 = |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._054);
			private final /*@NonNull*/ CardinalitySolutionStep _123 // assign V1 = |ownedMessageSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._061);
			private final /*@NonNull*/ CardinalitySolutionStep _124 // assign V1 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._062);
			private final /*@NonNull*/ CardinalitySolutionStep _125 // assign V1 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._063);
			private final /*@NonNull*/ CardinalitySolutionStep _126 // assign V1 = |ownedPatternGuard|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._081);
			private final /*@NonNull*/ CardinalitySolutionStep _127 // assign V1 = |ownedRoundBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._088);
			private final /*@NonNull*/ CardinalitySolutionStep _128 // assign V1 = |ownedSignature|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._090);
			private final /*@NonNull*/ CardinalitySolutionStep _129 // assign V1 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._102);
			private final /*@NonNull*/ CardinalitySolutionStep _130 // assign V1 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._099);
			private final /*@NonNull*/ CardinalitySolutionStep _131 // assign V1 = |qualifiers.'!ordered|!unique|ordered|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._116);
			private final /*@NonNull*/ CardinalitySolutionStep _132 // assign V1 = |referredOpposite|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._121);
			private final /*@NonNull*/ CardinalitySolutionStep _133 // assign V1 = |value|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._133);
			private final /*@NonNull*/ CardinalitySolutionStep _134 // assign V10 = (|ownedBodyExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._150);
			private final /*@NonNull*/ CardinalitySolutionStep _135 // assign V10 = (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._154);
			private final /*@NonNull*/ CardinalitySolutionStep _136 // assign V10 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _137 // assign V10 = |ownedConstraints|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._034);
			private final /*@NonNull*/ CardinalitySolutionStep _138 // assign V10 = |ownedPreconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._084);
			private final /*@NonNull*/ CardinalitySolutionStep _139 // assign V11 = (|ownedBodyExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(11, mt._150);
			private final /*@NonNull*/ CardinalitySolutionStep _140 // assign V11 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(11, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _141 // assign V11 = |ownedBodyExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(11, mt._028);
			private final /*@NonNull*/ CardinalitySolutionStep _142 // assign V12 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _143 // assign V12 = |ownedBodyExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, mt._028);
			private final /*@NonNull*/ CardinalitySolutionStep _144 // assign V12 = |ownedImplicitOpposites|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, mt._050);
			private final /*@NonNull*/ CardinalitySolutionStep _145 // assign V12 = |ownedPostconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, mt._083);
			private final /*@NonNull*/ CardinalitySolutionStep _146 // assign V13 = |ownedImplicitOpposites|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(13, mt._050);
			private final /*@NonNull*/ CardinalitySolutionStep _147 // assign V13 = |ownedPostconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(13, mt._083);
			private final /*@NonNull*/ CardinalitySolutionStep _148 // assign V2 = (|isSerializable.'serializable'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._138);
			private final /*@NonNull*/ CardinalitySolutionStep _149 // assign V2 = (|ownedDetails| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._155);
			private final /*@NonNull*/ CardinalitySolutionStep _150 // assign V2 = (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._176);
			private final /*@NonNull*/ CardinalitySolutionStep _151 // assign V2 = (|ownedParameters| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._177);
			private final /*@NonNull*/ CardinalitySolutionStep _152 // assign V2 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._179);
			private final /*@NonNull*/ CardinalitySolutionStep _153 // assign V2 = (|ownedSuperTypes| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._198);
			private final /*@NonNull*/ CardinalitySolutionStep _154 // assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._214);
			private final /*@NonNull*/ CardinalitySolutionStep _155 // assign V2 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _156 // assign V2 = |default|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._003);
			private final /*@NonNull*/ CardinalitySolutionStep _157 // assign V2 = |instanceClassName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._005);
			private final /*@NonNull*/ CardinalitySolutionStep _158 // assign V2 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _159 // assign V2 = |ownedCurlyBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._037);
			private final /*@NonNull*/ CardinalitySolutionStep _160 // assign V2 = |ownedMessageSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._061);
			private final /*@NonNull*/ CardinalitySolutionStep _161 // assign V2 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._063);
			private final /*@NonNull*/ CardinalitySolutionStep _162 // assign V2 = |ownedPackages|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._066);
			private final /*@NonNull*/ CardinalitySolutionStep _163 // assign V2 = |ownedSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._091);
			private final /*@NonNull*/ CardinalitySolutionStep _164 // assign V2 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._102);
			private final /*@NonNull*/ CardinalitySolutionStep _165 // assign V2 = |qualifiers.'!ordered|!unique|ordered|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._116);
			private final /*@NonNull*/ CardinalitySolutionStep _166 // assign V3 = (|isSerializable.'serializable'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._137);
			private final /*@NonNull*/ CardinalitySolutionStep _167 // assign V3 = (|ownedAnnotations| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._146);
			private final /*@NonNull*/ CardinalitySolutionStep _168 // assign V3 = (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._176);
			private final /*@NonNull*/ CardinalitySolutionStep _169 // assign V3 = (|ownedSuperTypes| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._197);
			private final /*@NonNull*/ CardinalitySolutionStep _170 // assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._213);
			private final /*@NonNull*/ CardinalitySolutionStep _171 // assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._214);
			private final /*@NonNull*/ CardinalitySolutionStep _172 // assign V3 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _173 // assign V3 = |default|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._003);
			private final /*@NonNull*/ CardinalitySolutionStep _174 // assign V3 = |isPre.'@'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._011);
			private final /*@NonNull*/ CardinalitySolutionStep _175 // assign V3 = |isSerializable.'serializable'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._014);
			private final /*@NonNull*/ CardinalitySolutionStep _176 // assign V3 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _177 // assign V3 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._063);
			private final /*@NonNull*/ CardinalitySolutionStep _178 // assign V3 = |ownedPackages|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._066);
			private final /*@NonNull*/ CardinalitySolutionStep _179 // assign V3 = |ownedSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._091);
			private final /*@NonNull*/ CardinalitySolutionStep _180 // assign V3 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._102);
			private final /*@NonNull*/ CardinalitySolutionStep _181 // assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._114);
			private final /*@NonNull*/ CardinalitySolutionStep _182 // assign V4 = (|ownedExceptions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._159);
			private final /*@NonNull*/ CardinalitySolutionStep _183 // assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._213);
			private final /*@NonNull*/ CardinalitySolutionStep _184 // assign V4 = |instanceClassName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._005);
			private final /*@NonNull*/ CardinalitySolutionStep _185 // assign V4 = |isSerializable.'serializable'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._013);
			private final /*@NonNull*/ CardinalitySolutionStep _186 // assign V4 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _187 // assign V4 = |ownedClasses|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._029);
			private final /*@NonNull*/ CardinalitySolutionStep _188 // assign V4 = |ownedContents|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._035);
			private final /*@NonNull*/ CardinalitySolutionStep _189 // assign V4 = |ownedLiterals|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._060);
			private final /*@NonNull*/ CardinalitySolutionStep _190 // assign V4 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._102);
			private final /*@NonNull*/ CardinalitySolutionStep _191 // assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._113);
			private final /*@NonNull*/ CardinalitySolutionStep _192 // assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._114);
			private final /*@NonNull*/ CardinalitySolutionStep _193 // assign V5 = (|isInterface.'interface'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._136);
			private final /*@NonNull*/ CardinalitySolutionStep _194 // assign V5 = (|ownedExceptions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._158);
			private final /*@NonNull*/ CardinalitySolutionStep _195 // assign V5 = (|ownedExceptions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._159);
			private final /*@NonNull*/ CardinalitySolutionStep _196 // assign V5 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _197 // assign V5 = |ownedConstraints|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._034);
			private final /*@NonNull*/ CardinalitySolutionStep _198 // assign V5 = |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._038);
			private final /*@NonNull*/ CardinalitySolutionStep _199 // assign V5 = |ownedLiterals|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._060);
			private final /*@NonNull*/ CardinalitySolutionStep _200 // assign V5 = |ownedReferences|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._086);
			private final /*@NonNull*/ CardinalitySolutionStep _201 // assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._113);
			private final /*@NonNull*/ CardinalitySolutionStep _202 // assign V6 = (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._154);
			private final /*@NonNull*/ CardinalitySolutionStep _203 // assign V6 = (|ownedExceptions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._158);
			private final /*@NonNull*/ CardinalitySolutionStep _204 // assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._215);
			private final /*@NonNull*/ CardinalitySolutionStep _205 // assign V6 = (|referredKeys| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._221);
			private final /*@NonNull*/ CardinalitySolutionStep _206 // assign V6 = |isInterface.'interface'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._009);
			private final /*@NonNull*/ CardinalitySolutionStep _207 // assign V6 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _208 // assign V6 = |ownedConstraints|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._034);
			private final /*@NonNull*/ CardinalitySolutionStep _209 // assign V6 = |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._038);
			private final /*@NonNull*/ CardinalitySolutionStep _210 // assign V7 = (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._154);
			private final /*@NonNull*/ CardinalitySolutionStep _211 // assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._215);
			private final /*@NonNull*/ CardinalitySolutionStep _212 // assign V7 = (|referredKeys| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._220);
			private final /*@NonNull*/ CardinalitySolutionStep _213 // assign V7 = (|referredKeys| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._221);
			private final /*@NonNull*/ CardinalitySolutionStep _214 // assign V7 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _215 // assign V7 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _216 // assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._115);
			private final /*@NonNull*/ CardinalitySolutionStep _217 // assign V8 = (|referredKeys| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._220);
			private final /*@NonNull*/ CardinalitySolutionStep _218 // assign V8 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _219 // assign V8 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _220 // assign V8 = |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._038);
			private final /*@NonNull*/ CardinalitySolutionStep _221 // assign V8 = |ownedOperations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._065);
			private final /*@NonNull*/ CardinalitySolutionStep _222 // assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._115);
			private final /*@NonNull*/ CardinalitySolutionStep _223 // assign V9 = (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._154);
			private final /*@NonNull*/ CardinalitySolutionStep _224 // assign V9 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _225 // assign V9 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _226 // assign V9 = |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._038);
			private final /*@NonNull*/ CardinalitySolutionStep _227 // assign V9 = |ownedPreconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._084);
			private final /*@NonNull*/ CardinalitySolutionStep _228 // assign V9 = |ownedProperties|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._085);
			private final /*@NonNull*/ CardinalitySolutionStep _229 // check-rule basecs::AnnotationCS.ownedContents : 53
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x20000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _230 // check-rule basecs::AnnotationCS.ownedReferences : 54
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _231 // check-rule basecs::AnnotationElementCS.ownedDetails : 16
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x10000L));
			private final /*@NonNull*/ CardinalitySolutionStep _232 // check-rule basecs::ClassCS.ownedConstraints : 41
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x20000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _233 // check-rule basecs::ConstraintCS.ownedMessageSpecification : 92
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x10000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _234 // check-rule basecs::ConstraintCS.ownedSpecification : 92
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x10000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _235 // check-rule basecs::EnumerationCS.ownedLiterals : 22
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x400000L));
			private final /*@NonNull*/ CardinalitySolutionStep _236 // check-rule basecs::ImportCS.ownedPathName : 123
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x800000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _237 // check-rule basecs::ModelElementCS.ownedAnnotations : 2
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x4L));
			private final /*@NonNull*/ CardinalitySolutionStep _238 // check-rule basecs::ModelElementRefCS.ownedPathName : 73
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x200L));
			private final /*@NonNull*/ CardinalitySolutionStep _239 // check-rule basecs::OperationCS.ownedBodyExpressions : 92
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x10000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _240 // check-rule basecs::OperationCS.ownedExceptions : 117
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x20000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _241 // check-rule basecs::OperationCS.ownedParameters : 72
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x100L));
			private final /*@NonNull*/ CardinalitySolutionStep _242 // check-rule basecs::OperationCS.ownedPostconditions : 75
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x800L));
			private final /*@NonNull*/ CardinalitySolutionStep _243 // check-rule basecs::OperationCS.ownedPreconditions : 76
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x1000L));
			private final /*@NonNull*/ CardinalitySolutionStep _244 // check-rule basecs::PackageCS.ownedClasses : 6
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40L));
			private final /*@NonNull*/ CardinalitySolutionStep _245 // check-rule basecs::PackageOwnerCS.ownedPackages : 71
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x80L));
			private final /*@NonNull*/ CardinalitySolutionStep _246 // check-rule basecs::PathNameCS.ownedPathElements : 31
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x80000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _247 // check-rule basecs::PathNameCS.ownedPathElements : 31|67
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x80000000L,0x8L));
			private final /*@NonNull*/ CardinalitySolutionStep _248 // check-rule basecs::PathNameCS.ownedPathElements : 67|122
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x400000000000008L));
			private final /*@NonNull*/ CardinalitySolutionStep _249 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x2000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _250 // check-rule basecs::RootCS.ownedImports : 38
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x4000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _251 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x10000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _252 // check-rule basecs::StructuredClassCS.ownedOperations : 70
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x40L));
			private final /*@NonNull*/ CardinalitySolutionStep _253 // check-rule basecs::StructuredClassCS.ownedProperties : 96
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x100000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _254 // check-rule basecs::StructuredClassCS.ownedSuperTypes : 117
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x20000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _255 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x100000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _256 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x1000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _257 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x8000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _258 // check-rule basecs::TemplateSignatureCS.ownedParameters : 114
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x4000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _259 // check-rule basecs::TemplateableElementCS.ownedSignature : 101
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x2000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _260 // check-rule basecs::TupleTypeCS.ownedParts : 105
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x20000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _261 // check-rule basecs::TypeParameterCS.ownedExtends : 117
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x20000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _262 // check-rule basecs::TypedElementCS.ownedType : 107
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x80000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _263 // check-rule basecs::TypedElementCS.ownedType : 116
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x10000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _264 // check-rule basecs::TypedRefCS.ownedMultiplicity : 56
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x100000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _265 // check-rule basecs::TypedTypeRefCS.ownedBinding : 99
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x800000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _266 // check-rule basecs::TypedTypeRefCS.ownedPathName : 73
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x200L));
			private final /*@NonNull*/ CardinalitySolutionStep _267 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 117
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x20000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _268 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x2000L));
			private final /*@NonNull*/ CardinalitySolutionStep _269 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x200L));
			private final /*@NonNull*/ CardinalitySolutionStep _270 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x100000L));
			private final /*@NonNull*/ CardinalitySolutionStep _271 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x20000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _272 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x200L));
			private final /*@NonNull*/ CardinalitySolutionStep _273 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x800L));
			private final /*@NonNull*/ CardinalitySolutionStep _274 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _275 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x400L));
			private final /*@NonNull*/ CardinalitySolutionStep _276 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _277 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x400L));
			private final /*@NonNull*/ CardinalitySolutionStep _278 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 11
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x800L));
			private final /*@NonNull*/ CardinalitySolutionStep _279 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x100000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _280 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 108
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x100000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _281 // check-rule essentialoclcs::ContextCS.ownedExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _282 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x4000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _283 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _284 // check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L,0x400L));
			private final /*@NonNull*/ CardinalitySolutionStep _285 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _286 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x100000L));
			private final /*@NonNull*/ CardinalitySolutionStep _287 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _288 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _289 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _290 // check-rule essentialoclcs::InfixExpCS.ownedLeft : 78
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x4000L));
			private final /*@NonNull*/ CardinalitySolutionStep _291 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _292 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _293 // check-rule essentialoclcs::LetExpCS.ownedVariables : 46
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x400000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _294 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x100000L));
			private final /*@NonNull*/ CardinalitySolutionStep _295 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x4000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _296 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x8000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _297 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _298 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _299 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x80000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _300 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 107
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x80000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _301 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x80L));
			private final /*@NonNull*/ CardinalitySolutionStep _302 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _303 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x2000000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _304 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 107
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x80000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _305 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _306 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _307 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 77
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x2000L));
			private final /*@NonNull*/ CardinalitySolutionStep _308 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 78
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x4000L));
			private final /*@NonNull*/ CardinalitySolutionStep _309 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x80000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _310 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0xd000000000000000L,0x1L));
			private final /*@NonNull*/ CardinalitySolutionStep _311 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L,0x400L));
			private final /*@NonNull*/ CardinalitySolutionStep _312 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x80000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _313 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _314 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x10000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _315 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x1000000000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _316 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x2000L));
			private final /*@NonNull*/ CardinalitySolutionStep _317 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x200L));
			private final /*@NonNull*/ CardinalitySolutionStep _318 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _319 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 30
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x40000000L));
			private final /*@NonNull*/ CardinalitySolutionStep _320 // check-rule essentialoclcs::VariableCS.ownedType : 107
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(0x0L,0x80000000000L));
		}

		private class _SerializationTerms
		{
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _000 // 1*'!serializable'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "!serializable");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _001 // 1*'#'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "#");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _002 // 1*'&&'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "&&");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _003 // 1*'('
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "(");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _004 // 1*')'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ")");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _005 // 1*'*'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "*");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _006 // 1*'++'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "++");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _007 // 1*','
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ",");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _008 // 1*'..'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "..");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _009 // 1*':'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ":");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _010 // 1*'::'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "::");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _011 // 1*';'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ";");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _012 // 1*'<'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "<");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _013 // 1*'<-'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "<-");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _014 // 1*'='
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "=");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _015 // 1*'>'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ">");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _016 // 1*'?'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "?");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _017 // 1*'@'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "@");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _018 // 1*'Lambda'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Lambda");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _019 // 1*'Map'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Map");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _020 // 1*'Tuple'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Tuple");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _021 // 1*'['
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "[");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _022 // 1*']'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "]");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _023 // 1*'annotation'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "annotation");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _024 // 1*'attribute'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "attribute");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _025 // 1*'body'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "body");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _026 // 1*'class'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "class");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _027 // 1*'datatype'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "datatype");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _028 // 1*'definition'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "definition");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _029 // 1*'derivation'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "derivation");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _030 // 1*'documentation'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "documentation");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _031 // 1*'else'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "else");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _032 // 1*'elseif'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "elseif");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _033 // 1*'endif'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "endif");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _034 // 1*'enum'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "enum");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _035 // 1*'extends'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "extends");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _036 // 1*'if'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "if");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _037 // 1*'import'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "import");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _038 // 1*'in'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "in");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _039 // 1*'initial'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "initial");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _040 // 1*'invalid'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "invalid");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _041 // 1*'invariant'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "invariant");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _042 // 1*'key'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "key");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _043 // 1*'let'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "let");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _044 // 1*'literal'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "literal");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _045 // 1*'module'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "module");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _046 // 1*'null'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "null");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _047 // 1*'operation'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "operation");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _048 // 1*'opposite'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "opposite");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _049 // 1*'package'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "package");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _050 // 1*'postcondition'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "postcondition");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _051 // 1*'pre'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "pre");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _052 // 1*'precondition'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "precondition");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _053 // 1*'property'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "property");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _054 // 1*'reference'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "reference");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _055 // 1*'self'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "self");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _056 // 1*'static'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "static");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _057 // 1*'sysml'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "sysml");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _058 // 1*'then'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "then");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _059 // 1*'throws'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "throws");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _060 // 1*'{'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "{");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _061 // 1*'|'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "|");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _062 // 1*'|?'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "|?");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _063 // 1*'}'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "}");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _064 // 1*default=SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _065 // 1*exprString=UNQUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _066 // 1*instanceClassName=SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _067 // 1*literal=SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _068 // 1*lowerBound=LOWER
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _069 // 1*name=BinaryOperatorName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _070 // 1*name=CollectionTypeIdentifier
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _071 // 1*name=EnumerationLiteralName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _072 // 1*name=PrimitiveTypeIdentifier
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _073 // 1*name=UnaryOperatorName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _074 // 1*name=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _075 // 1*name=UnrestrictedName|SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _076 // 1*next-10-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(-1, 1, 11);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _077 // 1*nsPrefix=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _078 // 1*nsURI=URI
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _079 // 1*ownedActualParameter=TypeRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _080 // 1*ownedArguments+=NavigatingArgCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _081 // 1*ownedBinding=TemplateBindingCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _082 // 1*ownedCoIterator=CoIteratorVariableCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _083 // 1*ownedCondition=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _084 // 1*ownedCondition=ExpCS|PatternExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _085 // 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _086 // 1*ownedDetails+=DetailCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _087 // 1*ownedElseExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _088 // 1*ownedExceptions+=TypedRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _089 // 1*ownedExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _090 // 1*ownedExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _091 // 1*ownedExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _092 // 1*ownedExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _093 // 1*ownedExpression=PatternExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _094 // 1*ownedExpressionCS=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _095 // 1*ownedExtends+=TypedRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _096 // 1*ownedExtends=TypedRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _097 // 1*ownedImplicitOpposites+=ImplicitOppositeCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _098 // 1*ownedInExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _099 // 1*ownedInitExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _100 // 1*ownedInitExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _101 // 1*ownedInitExpression=ExpCS|PatternExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _102 // 1*ownedInitExpression=StringLiteralExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _103 // 1*ownedKey=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _104 // 1*ownedKeyType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _105 // 1*ownedLastExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _106 // 1*ownedLeft=PrefixedPrimaryExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _107 // 1*ownedMessageSpecification=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _108 // 1*ownedNameExpression=NavigatingArgExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _109 // 1*ownedParameters+=ParameterCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _110 // 1*ownedParameters+=TypeParameterCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _111 // 1*ownedParts+=CollectionLiteralPartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _112 // 1*ownedParts+=MapLiteralPartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _113 // 1*ownedParts+=PatternExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _114 // 1*ownedParts+=ShadowPartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _115 // 1*ownedParts+=TupleLiteralPartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _116 // 1*ownedParts+=TuplePartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _117 // 1*ownedPathElements+=FirstPathElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _118 // 1*ownedPathElements+=NextPathElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _119 // 1*ownedPathElements+=URIFirstPathElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _120 // 1*ownedPathName=PathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _121 // 1*ownedPathName=PathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _122 // 1*ownedPathName=PathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _123 // 1*ownedPathName=PathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _124 // 1*ownedPathName=URIPathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _125 // 1*ownedPatternGuard=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _126 // 1*ownedPatternType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _127 // 1*ownedRight=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _128 // 1*ownedRight=PrefixedLetExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _129 // 1*ownedRight=PrefixedPrimaryExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _130 // 1*ownedSubstitutions+=TemplateParameterSubstitutionCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _131 // 1*ownedSuperTypes+=TypedRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _132 // 1*ownedTerms+=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _133 // 1*ownedThenExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _134 // 1*ownedThenExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _135 // 1*ownedType=CollectionTypeCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _136 // 1*ownedType=CollectionTypeCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _137 // 1*ownedType=MapTypeCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _138 // 1*ownedType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _139 // 1*ownedType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _140 // 1*ownedType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _141 // 1*ownedType=TypeExpWithoutMultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _142 // 1*ownedType=TypeLiteralWithMultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _143 // 1*ownedType=TypedMultiplicityRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _144 // 1*ownedValue=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _145 // 1*ownedValueType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _146 // 1*ownedVariables+=LetVariableCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep _147 // 1*qualifiers
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _148 // 1*referredElement=URI
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._121");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _149 // 1*referredElement=UnreservedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._126");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _150 // 1*referredElement=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _151 // 1*referredElement=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _152 // 1*referredKeys+=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _153 // 1*referredKeys+=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _154 // 1*referredOpposite=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _155 // 1*referredProperty=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _156 // 1*restVariableName=Identifier
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep _157 // 1*stringBounds
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep _158 // 1*symbol
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _159 // 1*symbol=NUMBER_LITERAL
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _160 // 1*upperBound=UPPER
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _161 // 1*value=SIGNED
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _162 // V00*'abstract'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "abstract");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _163 // V00*'callable'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "callable");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _164 // V00*'definition'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "definition");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _165 // V00*'primitive'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "primitive");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _166 // V00*'static'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "static");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _167 // V00*'|1'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "|1");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _168 // V00*name=UnrestrictedName|SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _169 // V00*next-2-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(0, 4, 6);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _170 // V00*ownedIfThenExpressions+=ElseIfThenExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _171 // V00*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _172 // V00*ownedRoundBracketedClause=RoundBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _173 // V00*ownedSignature=TemplateSignatureCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _174 // V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _175 // V00*patternVariableName=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _176 // V00*segments+=StringLiteral
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _177 // V00*value=SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _178 // V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS__VALUES,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _179 // V01*'::*'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(1, "::*");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _180 // V01*'|1'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(1, "|1");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _181 // V01*next-6-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(1, 4, 10);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _182 // V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [3]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _183 // V01*ownedCollectionMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _184 // V01*ownedImports+=ImportCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _185 // V01*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _186 // V01*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _187 // V01*ownedRoundBracketedClause=RoundBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _188 // V01*ownedSignature=TemplateSignatureCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _189 // V02*next-2-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(2, 7, 9);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _190 // V02*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _191 // V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _192 // V02*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _193 // V02*ownedPackages+=PackageCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _194 // V02*ownedSpecification=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _195 // V03*'serializable'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(3, "serializable");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _196 // V03*next-1-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(3, 12, 13);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _197 // V03*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _198 // V03*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _199 // V03*ownedPackages+=PackageCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _200 // V03*ownedSpecification=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _201 // V04*'serializable'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(4, "serializable");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _202 // V04*next-1-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(4, 14, 15);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _203 // V04*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _204 // V04*ownedClasses+=ClassCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _205 // V04*ownedContents+=ModelElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _206 // V04*ownedLiterals+=EnumerationLiteralCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _207 // V05*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(5, 17, 21);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _208 // V05*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _209 // V05*ownedConstraints+=InvariantConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _210 // V05*ownedLiterals+=EnumerationLiteralCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _211 // V05*ownedReferences+=ModelElementRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _212 // V06*'interface'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(6, "interface");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _213 // V06*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(6, 19, 23);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _214 // V06*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _215 // V06*ownedConstraints+=InvariantConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _216 // V06*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _217 // V07*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(7, 22, 26);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _218 // V07*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(7, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _219 // V07*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(7, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _220 // V08*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(8, 24, 28);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _221 // V08*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _222 // V08*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _223 // V08*ownedOperations+=OperationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _224 // V09*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(9, 29, 33);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _225 // V09*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _226 // V09*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _227 // V09*ownedPreconditions+=PreconditionConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _228 // V09*ownedProperties+=StructuralFeatureCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _229 // V10*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(10, 29, 33);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _230 // V10*ownedConstraints+=InvariantConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _231 // V10*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _232 // V10*ownedPreconditions+=PreconditionConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _233 // V11*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(11, 31, 35);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _234 // V11*ownedBodyExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(11, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _235 // V11*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(11, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _236 // V12*next-2-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(12, 37, 39);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _237 // V12*ownedBodyExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _238 // V12*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _239 // V12*ownedPostconditions+=PostconditionConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _240 // V13*next-2-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(13, 39, 41);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _241 // V13*ownedPostconditions+=PostconditionConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(13, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);

			private final void init() {
				_064.init(rv._087);
				_065.init(rv._119);
				_066.init(rv._087);
				_067.init(rv._087);
				_068.init(rv._043);
				_069.init(rv._004);
				_070.init(rv._012);
				_071.init(rv._023);
				_072.init(rv._082);
				_073.init(rv._124);
				_074.init(rv._127);
				_075.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._127/*UnrestrictedName*/, rv._087/*SINGLE_QUOTED_STRING*/});
				_077.init(rv._127);
				_078.init(rv._121);
				_079.init(rv._115);
				_080.init(rv._060);
				_081.init(rv._099);
				_082.init(rv._007);
				_083.init(rv._030);
				_084.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._030/*ExpCS*/, rv._074/*PatternExpCS*/});
				_085.init(rv._013);
				_086.init(rv._016);
				_087.init(rv._030);
				_088.init(rv._117);
				_089.init(rv._030);
				_090.init(rv._030);
				_091.init(rv._030);
				_092.init(rv._030);
				_093.init(rv._074);
				_094.init(rv._030);
				_095.init(rv._117);
				_096.init(rv._117);
				_097.init(rv._037);
				_098.init(rv._030);
				_099.init(rv._030);
				_100.init(rv._030);
				_101.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._030/*ExpCS*/, rv._074/*PatternExpCS*/});
				_102.init(rv._095);
				_103.init(rv._030);
				_104.init(rv._107);
				_105.init(rv._030);
				_106.init(rv._078);
				_107.init(rv._092);
				_108.init(rv._061);
				_109.init(rv._072);
				_110.init(rv._114);
				_111.init(rv._009);
				_112.init(rv._050);
				_113.init(rv._074);
				_114.init(rv._090);
				_115.init(rv._104);
				_116.init(rv._105);
				_117.init(rv._031);
				_118.init(rv._067);
				_119.init(rv._122);
				_120.init(rv._073);
				_121.init(rv._073);
				_122.init(rv._073);
				_123.init(rv._073);
				_124.init(rv._123);
				_125.init(rv._030);
				_126.init(rv._107);
				_127.init(rv._030);
				_128.init(rv._077);
				_129.init(rv._078);
				_130.init(rv._100);
				_131.init(rv._117);
				_132.init(rv._030);
				_133.init(rv._030);
				_134.init(rv._030);
				_135.init(rv._011);
				_136.init(rv._011);
				_137.init(rv._051);
				_138.init(rv._107);
				_139.init(rv._107);
				_140.init(rv._107);
				_141.init(rv._108);
				_142.init(rv._112);
				_143.init(rv._116);
				_144.init(rv._030);
				_145.init(rv._107);
				_146.init(rv._046);
				_156.init(rv._035);
				_159.init(rv._058);
				_160.init(rv._120);
				_161.init(rv._085);
				_168.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._127/*UnrestrictedName*/, rv._087/*SINGLE_QUOTED_STRING*/});
				_170.init(rv._020);
				_171.init(rv._056);
				_172.init(rv._084);
				_173.init(rv._101);
				_174.init(rv._093);
				_175.init(rv._127);
				_176.init(rv._094);
				_177.init(rv._087);
				_178.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._087/*SINGLE_QUOTED_STRING*/, rv._048/*ML_SINGLE_QUOTED_STRING*/});
				_182.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._063/*NavigatingCommaArgCS*/, rv._064/*NavigatingSemiArgCS*/, rv._062/*NavigatingBarArgCS*/});
				_183.init(rv._056);
				_184.init(rv._038);
				_185.init(rv._056);
				_186.init(rv._056);
				_187.init(rv._084);
				_188.init(rv._101);
				_190.init(rv._002);
				_191.init(rv._013);
				_192.init(rv._056);
				_193.init(rv._071);
				_194.init(rv._092);
				_197.init(rv._002);
				_198.init(rv._056);
				_199.init(rv._071);
				_200.init(rv._092);
				_203.init(rv._002);
				_204.init(rv._006);
				_205.init(rv._053);
				_206.init(rv._022);
				_208.init(rv._002);
				_209.init(rv._041);
				_210.init(rv._022);
				_211.init(rv._054);
				_214.init(rv._002);
				_215.init(rv._041);
				_216.init(rv._092);
				_218.init(rv._002);
				_219.init(rv._092);
				_221.init(rv._002);
				_222.init(rv._092);
				_223.init(rv._070);
				_225.init(rv._002);
				_226.init(rv._092);
				_227.init(rv._076);
				_228.init(rv._096);
				_230.init(rv._041);
				_231.init(rv._092);
				_232.init(rv._076);
				_234.init(rv._092);
				_235.init(rv._092);
				_237.init(rv._092);
				_238.init(rv._092);
				_239.init(rv._075);
				_241.init(rv._075);
			}
		}

		private class _SerializationSegments
		{
			private final /*@NonNull*/ Segment [] _0 // []
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					}
				;
			private final /*@NonNull*/ Segment [] _1 // [supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport, «value»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.createCustomSegment(null, org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport.class) /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */}
				;
			private final /*@NonNull*/ Segment [] _2 // [«! », «value», «! »]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */}
				;
			private final /*@NonNull*/ Segment [] _3 // [«! », «value», «? »]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */}
				;
			private final /*@NonNull*/ Segment [] _4 // [«! », «value», «?\n»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_NEW_LINE /* «?\n» */}
				;
			private final /*@NonNull*/ Segment [] _5 // [«! », «value»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */}
				;
			private final /*@NonNull*/ Segment [] _6 // [«-», «? », «value», «?\n»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.POP /* «-» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_NEW_LINE /* «?\n» */}
				;
			private final /*@NonNull*/ Segment [] _7 // [«? », «value», «+», «?\n»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.PUSH /* «+» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_NEW_LINE /* «?\n» */}
				;
			private final /*@NonNull*/ Segment [] _8 // [«? », «value», «? »]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */}
				;
		}

		private class _RuleValues
		{
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _000 // ANY_OTHER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(0, "ANY_OTHER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _001 // AnnotationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(1, "AnnotationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._118 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
				sr._120 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
				sr._119 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
				sr._117 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _002 // AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(2, "AnnotationElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._201 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
				sr._200 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
				sr._118 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
				sr._141 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
				sr._120 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
				sr._119 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
				sr._117 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x20006L,0x400000000L})); // AnnotationCS,AnnotationElementCS,DocumentationCS,SysMLCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _003 // AttributeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(3, "AttributeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._121 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._125 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _004 // BinaryOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(4, "BinaryOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _005 // BooleanLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(5, "BooleanLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._016 /* symbol={'false|true'} */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _006 // ClassCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(6, "ClassCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._152 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._137 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._149 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._144 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._130 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._129 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._136 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._153 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._131 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._132 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._142 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._146 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._199 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
				sr._198 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x208040L,0x200000000L})); // ClassCS,DataTypeCS,EnumerationCS,StructuredClassCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _007 // CoIteratorVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(7, "CoIteratorVariableCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._017 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _008 // CollectionLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(8, "CollectionLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _009 // CollectionLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(9, "CollectionLiteralPartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._019 /* ownedExpression=PatternExpCS */,
				sr._020 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _010 // CollectionPatternCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(10, "CollectionPatternCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _011 // CollectionTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(11, "CollectionTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _012 // CollectionTypeIdentifier
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(12, "CollectionTypeIdentifier");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _013 // CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(13, "CurlyBracketedClauseCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._024 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _014 // DOUBLE_QUOTED_STRING
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(14, "DOUBLE_QUOTED_STRING");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _015 // DataTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(15, "DataTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._127 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._133 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._128 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._138 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._134 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._135 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _016 // DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(16, "DetailCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._139 /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _017 // DocumentationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(17, "DocumentationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._140 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _018 // ESCAPED_CHARACTER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(18, "ESCAPED_CHARACTER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _019 // ESCAPED_ID
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(19, "ESCAPED_ID");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _020 // ElseIfThenExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(20, "ElseIfThenExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._025 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _021 // EnumerationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(21, "EnumerationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._147 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._151 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._148 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._143 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._145 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._150 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _022 // EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(22, "EnumerationLiteralCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._156 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._155 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._157 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
				sr._154 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _023 // EnumerationLiteralName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(23, "EnumerationLiteralName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _024 // EssentialOCLInfixOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(24, "EssentialOCLInfixOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _025 // EssentialOCLNavigationOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(25, "EssentialOCLNavigationOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _026 // EssentialOCLReservedKeyword
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(26, "EssentialOCLReservedKeyword");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _027 // EssentialOCLUnaryOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(27, "EssentialOCLUnaryOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _028 // EssentialOCLUnreservedName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(28, "EssentialOCLUnreservedName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _029 // EssentialOCLUnrestrictedName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(29, "EssentialOCLUnrestrictedName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _030 // ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(30, "ExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._029 /* symbol={'false|true'} */,
				sr._038 /* '*' */,
				sr._030 /* 'invalid' */,
				sr._035 /* 'null' */,
				sr._027 /* 'self' */,
				sr._026 /* symbol=NUMBER_LITERAL */,
				sr._028 /* segments+=StringLiteral[+] */,
				sr._036 /* ownedType=TypeLiteralWithMultiplicityCS */,
				sr._041 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				sr._078 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				sr._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
				sr._034 /* { '(' ownedExpression=ExpCS ')' } */,
				sr._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._032 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._042 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._037 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._050 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
				sr._039 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._040 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x802311040000120L,0x200080808201e034L})); // BooleanLiteralExpCS,CollectionLiteralExpCS,ExpCS,IfExpCS,InvalidLiteralExpCS,LambdaLiteralExpCS,LetExpCS,MapLiteralExpCS,NameExpCS,NestedExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrefixedLetExpCS,PrefixedPrimaryExpCS,PrimaryExpCS,PrimitiveLiteralExpCS,SelfExpCS,StringLiteralExpCS,TupleLiteralExpCS,TypeLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _031 // FirstPathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(31, "FirstPathElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._000 /* referredElement=UnrestrictedName */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _032 // ID
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(32, "ID");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _033 // INT
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(33, "INT");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _034 // INTEGER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(34, "INTEGER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _035 // Identifier
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(35, "Identifier");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _036 // IfExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(36, "IfExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._044 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _037 // ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(37, "ImplicitOppositeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._158 /* { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _038 // ImportCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(38, "ImportCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._159 /* { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _039 // InfixOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(39, "InfixOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _040 // InvalidLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(40, "InvalidLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._046 /* 'invalid' */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _041 // InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(41, "InvariantConstraintCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._160 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */,
				sr._161 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _042 // LETTER_CHARACTER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(42, "LETTER_CHARACTER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _043 // LOWER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(43, "LOWER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _044 // LambdaLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(44, "LambdaLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._048 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _045 // LetExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(45, "LetExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._049 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _046 // LetVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(46, "LetVariableCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._051 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _047 // ML_COMMENT
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(47, "ML_COMMENT");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _048 // ML_SINGLE_QUOTED_STRING
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(48, "ML_SINGLE_QUOTED_STRING");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _049 // MapLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(49, "MapLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._053 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _050 // MapLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(50, "MapLiteralPartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._054 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _051 // MapTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(51, "MapTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _052 // Model
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(52, "Model",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._057 /* ownedExpression=ExpCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _053 // ModelElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(53, "ModelElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._156 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._155 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._177 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
				sr._152 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._157 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
				sr._137 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._154 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
				sr._149 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._144 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._130 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._129 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._178 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */,
				sr._136 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._153 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._131 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._132 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._142 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._146 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._121 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._191 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._187 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._185 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._199 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
				sr._198 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._125 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._168 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._174 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._172 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._163 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._173 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._164 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._188 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._189 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._190 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x20000000608048L,0x3000800c0L})); // AttributeCS,ClassCS,DataTypeCS,EnumerationCS,EnumerationLiteralCS,ModelElementCS,OperationCS,PackageCS,ReferenceCS,StructuralFeatureCS,StructuredClassCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _054 // ModelElementRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(54, "ModelElementRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._162 /* { 'reference' ownedPathName=PathNameCS ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _055 // MultiplicityBoundsCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(55, "MultiplicityBoundsCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._001 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _056 // MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(56, "MultiplicityCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._006 /* { '[' stringBounds={'*|+|?'} ']' } */,
				sr._007 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
				sr._004 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
				sr._005 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
				sr._002 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
				sr._003 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _057 // MultiplicityStringCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(57, "MultiplicityStringCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._008 /* stringBounds={'*|+|?'} */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _058 // NUMBER_LITERAL
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(58, "NUMBER_LITERAL");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _059 // NameExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(59, "NameExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._058 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _060 // NavigatingArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(60, "NavigatingArgCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._064 /* ownedNameExpression=NavigatingArgExpCS */,
				sr._061 /* { ':' ownedType=TypeExpCS } */,
				sr._060 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._062 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._063 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _061 // NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(61, "NavigatingArgExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._029 /* symbol={'false|true'} */,
				sr._038 /* '*' */,
				sr._030 /* 'invalid' */,
				sr._035 /* 'null' */,
				sr._027 /* 'self' */,
				sr._026 /* symbol=NUMBER_LITERAL */,
				sr._028 /* segments+=StringLiteral[+] */,
				sr._036 /* ownedType=TypeLiteralWithMultiplicityCS */,
				sr._041 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				sr._078 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				sr._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
				sr._034 /* { '(' ownedExpression=ExpCS ')' } */,
				sr._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._032 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._042 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._037 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._050 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
				sr._039 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._040 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x2802311040000120L,0x200080808201e034L})); // BooleanLiteralExpCS,CollectionLiteralExpCS,ExpCS,IfExpCS,InvalidLiteralExpCS,LambdaLiteralExpCS,LetExpCS,MapLiteralExpCS,NameExpCS,NavigatingArgExpCS,NestedExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrefixedLetExpCS,PrefixedPrimaryExpCS,PrimaryExpCS,PrimitiveLiteralExpCS,SelfExpCS,StringLiteralExpCS,TupleLiteralExpCS,TypeLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _062 // NavigatingBarArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(62, "NavigatingBarArgCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._065 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _063 // NavigatingCommaArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(63, "NavigatingCommaArgCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._067 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
				sr._068 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._066 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._069 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _064 // NavigatingSemiArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(64, "NavigatingSemiArgCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._070 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _065 // NavigationOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(65, "NavigationOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _066 // NestedExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(66, "NestedExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._071 /* { '(' ownedExpression=ExpCS ')' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _067 // NextPathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(67, "NextPathElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._009 /* referredElement=UnreservedName */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _068 // NullLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(68, "NullLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._073 /* 'null' */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _069 // NumberLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(69, "NumberLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._076 /* symbol=NUMBER_LITERAL */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _070 // OperationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(70, "OperationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._169 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._165 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._166 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._170 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._167 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._171 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _071 // PackageCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(71, "PackageCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._176 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
				sr._175 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _072 // ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(72, "ParameterCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._179 /* { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _073 // PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(73, "PathNameCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._010 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _074 // PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(74, "PatternExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._077 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _075 // PostconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(75, "PostconditionConstraintCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._180 /* { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _076 // PreconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(76, "PreconditionConstraintCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._181 /* { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _077 // PrefixedLetExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(77, "PrefixedLetExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._079 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				sr._049 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x200000000000L,0x2000L})); // LetExpCS,PrefixedLetExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _078 // PrefixedPrimaryExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(78, "PrefixedPrimaryExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._016 /* symbol={'false|true'} */,
				sr._045 /* 'invalid' */,
				sr._074 /* 'null' */,
				sr._075 /* symbol=NUMBER_LITERAL */,
				sr._085 /* 'self' */,
				sr._091 /* segments+=StringLiteral[+] */,
				sr._105 /* ownedType=TypeLiteralWithMultiplicityCS */,
				sr._115 /* '*' */,
				sr._080 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				sr._072 /* { '(' ownedExpression=ExpCS ')' } */,
				sr._047 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._043 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._059 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._093 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._052 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x802111000000120L,0x200080808201c034L})); // BooleanLiteralExpCS,CollectionLiteralExpCS,IfExpCS,InvalidLiteralExpCS,LambdaLiteralExpCS,MapLiteralExpCS,NameExpCS,NestedExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrefixedPrimaryExpCS,PrimaryExpCS,PrimitiveLiteralExpCS,SelfExpCS,StringLiteralExpCS,TupleLiteralExpCS,TypeLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _079 // PrimaryExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(79, "PrimaryExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._016 /* symbol={'false|true'} */,
				sr._045 /* 'invalid' */,
				sr._074 /* 'null' */,
				sr._075 /* symbol=NUMBER_LITERAL */,
				sr._085 /* 'self' */,
				sr._091 /* segments+=StringLiteral[+] */,
				sr._105 /* ownedType=TypeLiteralWithMultiplicityCS */,
				sr._115 /* '*' */,
				sr._071 /* { '(' ownedExpression=ExpCS ')' } */,
				sr._048 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._044 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._058 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._093 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._053 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x802111000000120L,0x2000808082018034L})); // BooleanLiteralExpCS,CollectionLiteralExpCS,IfExpCS,InvalidLiteralExpCS,LambdaLiteralExpCS,MapLiteralExpCS,NameExpCS,NestedExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrimaryExpCS,PrimitiveLiteralExpCS,SelfExpCS,StringLiteralExpCS,TupleLiteralExpCS,TypeLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _080 // PrimitiveLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(80, "PrimitiveLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._016 /* symbol={'false|true'} */,
				sr._046 /* 'invalid' */,
				sr._073 /* 'null' */,
				sr._076 /* symbol=NUMBER_LITERAL */,
				sr._091 /* segments+=StringLiteral[+] */,
				sr._115 /* '*' */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x10000000020L,0x2000000080010030L})); // BooleanLiteralExpCS,InvalidLiteralExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrimitiveLiteralExpCS,StringLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _081 // PrimitiveTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(81, "PrimitiveTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._081 /* name=PrimitiveTypeIdentifier */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _082 // PrimitiveTypeIdentifier
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(82, "PrimitiveTypeIdentifier");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _083 // ReferenceCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(83, "ReferenceCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._186 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._184 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._182 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._193 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._183 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._192 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _084 // RoundBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(84, "RoundBracketedClauseCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._083 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _085 // SIGNED
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(85, "SIGNED");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _086 // SIMPLE_ID
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(86, "SIMPLE_ID");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _087 // SINGLE_QUOTED_STRING
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(87, "SINGLE_QUOTED_STRING");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _088 // SL_COMMENT
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(88, "SL_COMMENT");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _089 // SelfExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(89, "SelfExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._084 /* 'self' */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _090 // ShadowPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(90, "ShadowPartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._087 /* ownedInitExpression=StringLiteralExpCS */,
				sr._086 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _091 // SimplePathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(91, "SimplePathNameCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._088 /* ownedPathElements+=FirstPathElementCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _092 // SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(92, "SpecificationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._195 /* exprString=UNQUOTED_STRING */,
				sr._194 /* ownedExpression=ExpCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _093 // SquareBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(93, "SquareBracketedClauseCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._089 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _094 // StringLiteral
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(94, "StringLiteral");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _095 // StringLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(95, "StringLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._090 /* segments+=StringLiteral[+] */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _096 // StructuralFeatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(96, "StructuralFeatureCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._121 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._186 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._184 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._182 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._125 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._193 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._183 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._192 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8L,0x100080000L})); // AttributeCS,ReferenceCS,StructuralFeatureCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _097 // StructuredClassCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(97, "StructuredClassCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._197 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
				sr._196 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _098 // SysMLCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(98, "SysMLCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._203 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
				sr._202 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _099 // TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(99, "TemplateBindingCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._011 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _100 // TemplateParameterSubstitutionCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(100, "TemplateParameterSubstitutionCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._012 /* ownedActualParameter=TypeRefCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _101 // TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(101, "TemplateSignatureCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._205 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
				sr._204 /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _102 // TopLevelCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(102, "TopLevelCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._206 /* { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _103 // TupleLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(103, "TupleLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._092 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _104 // TupleLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(104, "TupleLiteralPartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._094 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _105 // TuplePartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(105, "TuplePartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._095 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _106 // TupleTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(106, "TupleTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _107 // TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(107, "TypeExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._102 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				sr._100 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._098 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._103 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._101 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
				sr._099 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000c00L,0x25c0000020000L})); // CollectionPatternCS,CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeExpCS,TypeExpWithoutMultiplicityCS,TypeLiteralCS,TypeNameExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _108 // TypeExpWithoutMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(108, "TypeExpWithoutMultiplicityCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._082 /* name=PrimitiveTypeIdentifier */,
				sr._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._056 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._111 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */,
				sr._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
				sr._096 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000c00L,0x2540000020000L})); // CollectionPatternCS,CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeExpWithoutMultiplicityCS,TypeLiteralCS,TypeNameExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _109 // TypeIdentifier
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(109, "TypeIdentifier");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _110 // TypeLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(110, "TypeLiteralCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._081 /* name=PrimitiveTypeIdentifier */,
				sr._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x440000020000L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _111 // TypeLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(111, "TypeLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._104 /* ownedType=TypeLiteralWithMultiplicityCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _112 // TypeLiteralWithMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(112, "TypeLiteralWithMultiplicityCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._106 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				sr._107 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._109 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._108 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x1440000020000L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS,TypeLiteralWithMultiplicityCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _113 // TypeNameExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(113, "TypeNameExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._110 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _114 // TypeParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(114, "TypeParameterCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._013 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _115 // TypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(115, "TypeRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._082 /* name=PrimitiveTypeIdentifier */,
				sr._218 /* ownedPathName=PathNameCS */,
				sr._219 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
				sr._215 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
				sr._015 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
				sr._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._056 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._096 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x68440000020000L,0x2L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS,TypeRefCS,TypedRefCS,TypedTypeRefCS,WildcardTypeRefCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _116 // TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(116, "TypedMultiplicityRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._212 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				sr._208 /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
				sr._213 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
				sr._209 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */,
				sr._207 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._210 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._211 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x70440000020000L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS,TypedMultiplicityRefCS,TypedRefCS,TypedTypeRefCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _117 // TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(117, "TypedRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._081 /* name=PrimitiveTypeIdentifier */,
				sr._218 /* ownedPathName=PathNameCS */,
				sr._219 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
				sr._215 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
				sr._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x60440000020000L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS,TypedRefCS,TypedTypeRefCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _118 // TypedTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(118, "TypedTypeRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._217 /* ownedPathName=PathNameCS */,
				sr._216 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
				sr._214 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _119 // UNQUOTED_STRING
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(119, "UNQUOTED_STRING");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _120 // UPPER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(120, "UPPER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _121 // URI
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(121, "URI");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _122 // URIFirstPathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(122, "URIFirstPathElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._112 /* referredElement=UnrestrictedName */,
				sr._113 /* referredElement=URI */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _123 // URIPathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(123, "URIPathNameCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._114 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _124 // UnaryOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(124, "UnaryOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _125 // UnlimitedNaturalLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(125, "UnlimitedNaturalLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._116 /* '*' */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _126 // UnreservedName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(126, "UnreservedName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _127 // UnrestrictedName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(127, "UnrestrictedName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _128 // WS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(128, "WS");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _129 // WildcardTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(129, "WildcardTypeRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._014 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
		}

		private class _EClassData
		{
			private final /*@NonNull*/ EClassData _00 // AnnotationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._118 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): {
				+	'annotation'[1]
				+	name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._120 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): {
				+	'annotation'[1]
				+	name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedContents+=ModelElementCS[*]
				+	ownedReferences+=ModelElementRefCS[+]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._119 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): {
				+	'annotation'[1]
				+	name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedContents+=ModelElementCS[+]
				+	ownedReferences+=ModelElementRefCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._117 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): {
				+	'annotation'[1]
				+	name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[+]
				+	ownedContents+=ModelElementCS[*]
				+	ownedReferences+=ModelElementRefCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._118 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): {
				+	'annotation'[1]
				+	name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._120 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): {
				+	'annotation'[1]
				+	name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedContents+=ModelElementCS[*]
				+	ownedReferences+=ModelElementRefCS[+]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._119 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): {
				+	'annotation'[1]
				+	name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedContents+=ModelElementCS[+]
				+	ownedReferences+=ModelElementRefCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._117 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): {
				+	'annotation'[1]
				+	name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[+]
				+	ownedContents+=ModelElementCS[*]
				+	ownedReferences+=ModelElementRefCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._097 /* StructuredClassCS */,
						rv._021 /* EnumerationCS */,
						rv._022 /* EnumerationLiteralCS */,
						rv._083 /* ReferenceCS */,
						rv._053 /* ModelElementCS */,
						rv._096 /* StructuralFeatureCS */,
						rv._070 /* OperationCS */,
						rv._003 /* AttributeCS */,
						rv._015 /* DataTypeCS */,
						rv._071 /* PackageCS */,
						rv._006 /* ClassCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._054 /* ModelElementRefCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._016 /* DetailCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _01 // AttributeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ATTRIBUTE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._121 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._122 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._126 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._124 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._123 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._125 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._121 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._122 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._126 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._124 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._123 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._125 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._121 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._122 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._126 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._124 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._123 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._125 /* OCLinEcore::AttributeCS(basecs::AttributeCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'attribute'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._092 /* SpecificationCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._116 /* TypedMultiplicityRefCS */,
						rv._117 /* TypedRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _02 // BooleanLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._016 /* EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}[1]
				Serialization Steps */,
				sr._029 /* EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}[1]
				Serialization Steps */,
				sr._029 /* EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}[1]
				Serialization Steps */,
				sr._016 /* EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}[1]
				Serialization Steps */,
				sr._016 /* EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}[1]
				Serialization Steps */,
				sr._016 /* EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'}[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _03 // CollectionLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._018 /* EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): {
				+	ownedType=CollectionTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=CollectionLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=CollectionLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._039 /* EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): {
				+	ownedType=CollectionTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=CollectionLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=CollectionLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._039 /* EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): {
				+	ownedType=CollectionTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=CollectionLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=CollectionLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._018 /* EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): {
				+	ownedType=CollectionTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=CollectionLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=CollectionLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._018 /* EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): {
				+	ownedType=CollectionTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=CollectionLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=CollectionLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._009 /* CollectionLiteralPartCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._011 /* CollectionTypeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _04 // CollectionLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._019 /* EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS[1]
				Serialization Steps */,
				sr._020 /* EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): {
				+	ownedExpression=ExpCS[1]
				+	{
				    +	'..'[1]
				    +	ownedLastExpression=ExpCS[1]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._074 /* PatternExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _05 // CollectionPatternCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._021 /* EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): {
				+	ownedType=CollectionTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=PatternExpCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=PatternExpCS[1]
				         }[*]
				    +	'++'[1]
				    +	restVariableName=Identifier[1]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._101 /* EssentialOCL::TypeExpCS(essentialoclcs::CollectionPatternCS): {
				+	ownedType=CollectionTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=PatternExpCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=PatternExpCS[1]
				         }[*]
				    +	'++'[1]
				    +	restVariableName=Identifier[1]
				     }[?]
				+	'}'[1]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._021 /* EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): {
				+	ownedType=CollectionTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=PatternExpCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=PatternExpCS[1]
				         }[*]
				    +	'++'[1]
				    +	restVariableName=Identifier[1]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._074 /* PatternExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._056 /* MultiplicityCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._011 /* CollectionTypeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _06 // CollectionTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._022 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): {
				+	name=CollectionTypeIdentifier[1]
				+	{
				    +	'('[1]
				    +	ownedType=TypeExpWithoutMultiplicityCS[1]
				    +	ownedCollectionMultiplicity=MultiplicityCS[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._023 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): {
				+	name=CollectionTypeIdentifier[1]
				+	{
				    +	'('[1]
				    +	ownedType=TypeExpWithoutMultiplicityCS[1]
				    +	ownedCollectionMultiplicity=MultiplicityCS[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._100 /* EssentialOCL::TypeExpCS(essentialoclcs::CollectionTypeCS): {
				+	name=CollectionTypeIdentifier[1]
				+	{
				    +	'('[1]
				    +	ownedType=TypeExpWithoutMultiplicityCS[1]
				    +	ownedCollectionMultiplicity=MultiplicityCS[?]
				    +	')'[1]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._022 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): {
				+	name=CollectionTypeIdentifier[1]
				+	{
				    +	'('[1]
				    +	ownedType=TypeExpWithoutMultiplicityCS[1]
				    +	ownedCollectionMultiplicity=MultiplicityCS[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._023 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): {
				+	name=CollectionTypeIdentifier[1]
				+	{
				    +	'('[1]
				    +	ownedType=TypeExpWithoutMultiplicityCS[1]
				    +	ownedCollectionMultiplicity=MultiplicityCS[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._107 /* EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::CollectionTypeCS): {
				+	name=CollectionTypeIdentifier[1]
				+	{
				    +	'('[1]
				    +	ownedType=TypeExpWithoutMultiplicityCS[1]
				    +	ownedCollectionMultiplicity=MultiplicityCS[?]
				    +	')'[1]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._207 /* OCLinEcore::TypedMultiplicityRefCS(essentialoclcs::CollectionTypeCS): {
				+	name=CollectionTypeIdentifier[1]
				+	{
				    +	'('[1]
				    +	ownedType=TypeExpWithoutMultiplicityCS[1]
				    +	ownedCollectionMultiplicity=MultiplicityCS[?]
				    +	')'[1]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._023 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): {
				+	name=CollectionTypeIdentifier[1]
				+	{
				    +	'('[1]
				    +	ownedType=TypeExpWithoutMultiplicityCS[1]
				    +	ownedCollectionMultiplicity=MultiplicityCS[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._113 /* TypeNameExpCS */,
						rv._106 /* TupleTypeCS */,
						rv._010 /* CollectionPatternCS */,
						rv._081 /* PrimitiveTypeCS */,
						rv._108 /* TypeExpWithoutMultiplicityCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._056 /* MultiplicityCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._056 /* MultiplicityCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _07 // ContextCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._057 /* EssentialOCL::Model(essentialoclcs::ContextCS): ownedExpression=ExpCS[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _08 // CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._024 /* EssentialOCL::CurlyBracketedClauseCS(essentialoclcs::CurlyBracketedClauseCS): {
				+	'{'[1]
				+	{
				    +	ownedParts+=ShadowPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=ShadowPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._090 /* ShadowPartCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _09 // DataTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._137 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._130 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._129 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._136 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._131 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._132 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._127 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._133 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._128 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._138 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._134 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._135 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._137 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._130 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._129 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._136 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._131 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._132 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): {
				+	isPrimitive='primitive'[?]
				+	'datatype'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._041 /* InvariantConstraintCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._101 /* TemplateSignatureCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _10 // DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._139 /* OCLinEcore::DetailCS(basecs::DetailCS): {
				+	name=(UnrestrictedName|SINGLE_QUOTED_STRING)[1]
				+	'='[1]
				+	values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*]
				 }[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _11 // DocumentationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._141 /* OCLinEcore::DocumentationCS(basecs::DocumentationCS): {
				+	'documentation'[1]
				+	value=SINGLE_QUOTED_STRING[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._140 /* OCLinEcore::DocumentationCS(basecs::DocumentationCS): {
				+	'documentation'[1]
				+	value=SINGLE_QUOTED_STRING[?]
				+	{
				    +	'('[1]
				    +	ownedDetails+=DetailCS[1]
				    +	{
				        +	','[1]
				        +	ownedDetails+=DetailCS[1]
				         }[*]
				    +	')'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._016 /* DetailCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _12 // EnumerationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._152 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._149 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._144 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._153 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedLiterals+=EnumerationLiteralCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._142 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedLiterals+=EnumerationLiteralCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._146 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedLiterals+=EnumerationLiteralCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._147 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._151 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._148 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._143 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedLiterals+=EnumerationLiteralCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._145 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedLiterals+=EnumerationLiteralCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._150 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedLiterals+=EnumerationLiteralCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._152 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._149 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._144 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._153 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedLiterals+=EnumerationLiteralCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._142 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	'!serializable'[1]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedLiterals+=EnumerationLiteralCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._146 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): {
				+	'enum'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isSerializable='serializable'[?]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedLiterals+=EnumerationLiteralCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._022 /* EnumerationLiteralCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._041 /* InvariantConstraintCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._101 /* TemplateSignatureCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _13 // EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._156 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): {
				+	name=EnumerationLiteralName[1]
				+	{
				    +	':'[1]
				    +	literal=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	value=SIGNED[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._155 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): {
				+	'literal'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	literal=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	value=SIGNED[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._157 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): {
				+	name=EnumerationLiteralName[1]
				+	{
				    +	':'[1]
				    +	literal=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	value=SIGNED[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._154 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): {
				+	'literal'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	literal=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	value=SIGNED[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._156 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): {
				+	name=EnumerationLiteralName[1]
				+	{
				    +	':'[1]
				    +	literal=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	value=SIGNED[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._155 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): {
				+	'literal'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	literal=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	value=SIGNED[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._157 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): {
				+	name=EnumerationLiteralName[1]
				+	{
				    +	':'[1]
				    +	literal=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	value=SIGNED[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._154 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): {
				+	'literal'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	literal=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	value=SIGNED[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _14 // ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._038 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): '*'[1]
				Serialization Steps */,
				sr._030 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'invalid'[1]
				Serialization Steps */,
				sr._035 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'null'[1]
				Serialization Steps */,
				sr._027 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'self'[1]
				Serialization Steps */,
				sr._038 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): '*'[1]
				Serialization Steps */,
				sr._030 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'invalid'[1]
				Serialization Steps */,
				sr._035 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'null'[1]
				Serialization Steps */,
				sr._027 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'self'[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _15 // ExpSpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._195 /* OCLinEcore::SpecificationCS(essentialoclcs::ExpSpecificationCS): exprString=UNQUOTED_STRING[1]
				Serialization Steps */,
				sr._194 /* OCLinEcore::SpecificationCS(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _16 // IfExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._032 /* EssentialOCL::ExpCS(essentialoclcs::IfExpCS): {
				+	'if'[1]
				+	ownedCondition=(ExpCS|PatternExpCS)[1]
				+	'then'[1]
				+	ownedThenExpression=ExpCS[1]
				+	ownedIfThenExpressions+=ElseIfThenExpCS[*]
				+	'else'[1]
				+	ownedElseExpression=ExpCS[1]
				+	'endif'[1]
				 }[1]
				Serialization Steps */,
				sr._044 /* EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): {
				+	'if'[1]
				+	ownedCondition=(ExpCS|PatternExpCS)[1]
				+	'then'[1]
				+	ownedThenExpression=ExpCS[1]
				+	ownedIfThenExpressions+=ElseIfThenExpCS[*]
				+	'else'[1]
				+	ownedElseExpression=ExpCS[1]
				+	'endif'[1]
				 }[1]
				Serialization Steps */,
				sr._032 /* EssentialOCL::ExpCS(essentialoclcs::IfExpCS): {
				+	'if'[1]
				+	ownedCondition=(ExpCS|PatternExpCS)[1]
				+	'then'[1]
				+	ownedThenExpression=ExpCS[1]
				+	ownedIfThenExpressions+=ElseIfThenExpCS[*]
				+	'else'[1]
				+	ownedElseExpression=ExpCS[1]
				+	'endif'[1]
				 }[1]
				Serialization Steps */,
				sr._043 /* EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): {
				+	'if'[1]
				+	ownedCondition=(ExpCS|PatternExpCS)[1]
				+	'then'[1]
				+	ownedThenExpression=ExpCS[1]
				+	ownedIfThenExpressions+=ElseIfThenExpCS[*]
				+	'else'[1]
				+	ownedElseExpression=ExpCS[1]
				+	'endif'[1]
				 }[1]
				Serialization Steps */,
				sr._044 /* EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): {
				+	'if'[1]
				+	ownedCondition=(ExpCS|PatternExpCS)[1]
				+	'then'[1]
				+	ownedThenExpression=ExpCS[1]
				+	ownedIfThenExpressions+=ElseIfThenExpCS[*]
				+	'else'[1]
				+	ownedElseExpression=ExpCS[1]
				+	'endif'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._020 /* ElseIfThenExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._074 /* PatternExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _17 // IfThenExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._025 /* EssentialOCL::ElseIfThenExpCS(essentialoclcs::IfThenExpCS): {
				+	'elseif'[1]
				+	ownedCondition=ExpCS[1]
				+	'then'[1]
				+	ownedThenExpression=ExpCS[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _18 // ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPLICIT_OPPOSITE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._158 /* OCLinEcore::ImplicitOppositeCS(basecs::ImplicitOppositeCS): {
				+	'opposite'[1]
				+	name=UnrestrictedName[1]
				+	':'[1]
				+	ownedType=TypedMultiplicityRefCS[1]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!ordered|!unique|ordered|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._116 /* TypedMultiplicityRefCS */,
						rv._117 /* TypedRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _19 // ImportCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._159 /* OCLinEcore::ImportCS(basecs::ImportCS): {
				+	{'import'|'library'}[1]
				+	{
				    +	name=UnrestrictedName[1]
				    +	':'[1]
				     }[?]
				+	ownedPathName=URIPathNameCS[1]
				+	isAll='::*'[?]
				+	';'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._123 /* URIPathNameCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _20 // InfixExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._031 /* EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): {
				+	ownedLeft=PrefixedPrimaryExpCS[1]
				+	name=BinaryOperatorName[1]
				+	ownedRight=ExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._031 /* EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): {
				+	ownedLeft=PrefixedPrimaryExpCS[1]
				+	name=BinaryOperatorName[1]
				+	ownedRight=ExpCS[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _21 // InvalidLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._046 /* EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid'[1]
				Serialization Steps */,
				sr._045 /* EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid'[1]
				Serialization Steps */,
				sr._045 /* EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid'[1]
				Serialization Steps */,
				sr._046 /* EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid'[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _22 // LambdaLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._033 /* EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): {
				+	'Lambda'[1]
				+	'{'[1]
				+	ownedExpressionCS=ExpCS[1]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._048 /* EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): {
				+	'Lambda'[1]
				+	'{'[1]
				+	ownedExpressionCS=ExpCS[1]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._033 /* EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): {
				+	'Lambda'[1]
				+	'{'[1]
				+	ownedExpressionCS=ExpCS[1]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._047 /* EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): {
				+	'Lambda'[1]
				+	'{'[1]
				+	ownedExpressionCS=ExpCS[1]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._048 /* EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): {
				+	'Lambda'[1]
				+	'{'[1]
				+	ownedExpressionCS=ExpCS[1]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _23 // LetExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._050 /* EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): {
				+	'let'[1]
				+	ownedVariables+=LetVariableCS[1]
				+	{
				    +	','[1]
				    +	ownedVariables+=LetVariableCS[1]
				     }[*]
				+	'in'[1]
				+	ownedInExpression=ExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._049 /* EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): {
				+	'let'[1]
				+	ownedVariables+=LetVariableCS[1]
				+	{
				    +	','[1]
				    +	ownedVariables+=LetVariableCS[1]
				     }[*]
				+	'in'[1]
				+	ownedInExpression=ExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._050 /* EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): {
				+	'let'[1]
				+	ownedVariables+=LetVariableCS[1]
				+	{
				    +	','[1]
				    +	ownedVariables+=LetVariableCS[1]
				     }[*]
				+	'in'[1]
				+	ownedInExpression=ExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._049 /* EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): {
				+	'let'[1]
				+	ownedVariables+=LetVariableCS[1]
				+	{
				    +	','[1]
				    +	ownedVariables+=LetVariableCS[1]
				     }[*]
				+	'in'[1]
				+	ownedInExpression=ExpCS[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._046 /* LetVariableCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _24 // LetVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._051 /* EssentialOCL::LetVariableCS(essentialoclcs::LetVariableCS): {
				+	name=UnrestrictedName[1]
				+	ownedRoundBracketedClause=RoundBracketedClauseCS[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypeExpCS[1]
				     }[?]
				+	'='[1]
				+	ownedInitExpression=ExpCS[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._107 /* TypeExpCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._113 /* TypeNameExpCS */,
						rv._106 /* TupleTypeCS */,
						rv._010 /* CollectionPatternCS */,
						rv._081 /* PrimitiveTypeCS */,
						rv._108 /* TypeExpWithoutMultiplicityCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._084 /* RoundBracketedClauseCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _25 // MapLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._040 /* EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): {
				+	ownedType=MapTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=MapLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=MapLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._053 /* EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): {
				+	ownedType=MapTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=MapLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=MapLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._040 /* EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): {
				+	ownedType=MapTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=MapLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=MapLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._052 /* EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): {
				+	ownedType=MapTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=MapLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=MapLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._053 /* EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): {
				+	ownedType=MapTypeCS[1]
				+	'{'[1]
				+	{
				    +	ownedParts+=MapLiteralPartCS[1]
				    +	{
				        +	','[1]
				        +	ownedParts+=MapLiteralPartCS[1]
				         }[*]
				     }[?]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._051 /* MapTypeCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._050 /* MapLiteralPartCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _26 // MapLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._054 /* EssentialOCL::MapLiteralPartCS(essentialoclcs::MapLiteralPartCS): {
				+	ownedKey=ExpCS[1]
				+	'<-'[1]
				+	ownedValue=ExpCS[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _27 // MapTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._056 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): {
				+	name='Map'[1]
				+	{
				    +	'('[1]
				    +	ownedKeyType=TypeExpCS[1]
				    +	','[1]
				    +	ownedValueType=TypeExpCS[1]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._055 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): {
				+	name='Map'[1]
				+	{
				    +	'('[1]
				    +	ownedKeyType=TypeExpCS[1]
				    +	','[1]
				    +	ownedValueType=TypeExpCS[1]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._098 /* EssentialOCL::TypeExpCS(essentialoclcs::MapTypeCS): {
				+	name='Map'[1]
				+	{
				    +	'('[1]
				    +	ownedKeyType=TypeExpCS[1]
				    +	','[1]
				    +	ownedValueType=TypeExpCS[1]
				    +	')'[1]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._056 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): {
				+	name='Map'[1]
				+	{
				    +	'('[1]
				    +	ownedKeyType=TypeExpCS[1]
				    +	','[1]
				    +	ownedValueType=TypeExpCS[1]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._055 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): {
				+	name='Map'[1]
				+	{
				    +	'('[1]
				    +	ownedKeyType=TypeExpCS[1]
				    +	','[1]
				    +	ownedValueType=TypeExpCS[1]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._109 /* EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::MapTypeCS): {
				+	name='Map'[1]
				+	{
				    +	'('[1]
				    +	ownedKeyType=TypeExpCS[1]
				    +	','[1]
				    +	ownedValueType=TypeExpCS[1]
				    +	')'[1]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._210 /* OCLinEcore::TypedMultiplicityRefCS(essentialoclcs::MapTypeCS): {
				+	name='Map'[1]
				+	{
				    +	'('[1]
				    +	ownedKeyType=TypeExpCS[1]
				    +	','[1]
				    +	ownedValueType=TypeExpCS[1]
				    +	')'[1]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._055 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): {
				+	name='Map'[1]
				+	{
				    +	'('[1]
				    +	ownedKeyType=TypeExpCS[1]
				    +	','[1]
				    +	ownedValueType=TypeExpCS[1]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._107 /* TypeExpCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._113 /* TypeNameExpCS */,
						rv._106 /* TupleTypeCS */,
						rv._010 /* CollectionPatternCS */,
						rv._081 /* PrimitiveTypeCS */,
						rv._108 /* TypeExpWithoutMultiplicityCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._107 /* TypeExpCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._113 /* TypeNameExpCS */,
						rv._106 /* TupleTypeCS */,
						rv._010 /* CollectionPatternCS */,
						rv._081 /* PrimitiveTypeCS */,
						rv._108 /* TypeExpWithoutMultiplicityCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._056 /* MultiplicityCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _28 // ModelElementRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._162 /* OCLinEcore::ModelElementRefCS(basecs::ModelElementRefCS): {
				+	'reference'[1]
				+	ownedPathName=PathNameCS[1]
				+	';'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._073 /* PathNameCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _29 // MultiplicityBoundsCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._001 /* Base::MultiplicityBoundsCS(basecs::MultiplicityBoundsCS): {
				+	lowerBound=LOWER[1]
				+	{
				    +	'..'[1]
				    +	upperBound=UPPER[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._005 /* Base::MultiplicityCS(basecs::MultiplicityBoundsCS): {
				+	'['[1]
				+	lowerBound=LOWER[1]
				+	{
				    +	'..'[1]
				    +	upperBound=UPPER[1]
				     }[?]
				+	']'[1]
				 }[1]
				Serialization Steps */,
				sr._002 /* Base::MultiplicityCS(basecs::MultiplicityBoundsCS): {
				+	'['[1]
				+	lowerBound=LOWER[1]
				+	{
				    +	'..'[1]
				    +	upperBound=UPPER[1]
				     }[?]
				+	'|?'[1]
				+	']'[1]
				 }[1]
				Serialization Steps */,
				sr._003 /* Base::MultiplicityCS(basecs::MultiplicityBoundsCS): {
				+	'['[1]
				+	lowerBound=LOWER[1]
				+	{
				    +	'..'[1]
				    +	upperBound=UPPER[1]
				     }[?]
				+	isNullFree='|1'[?]
				+	']'[1]
				 }[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _30 // MultiplicityStringCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._006 /* Base::MultiplicityCS(basecs::MultiplicityStringCS): {
				+	'['[1]
				+	stringBounds={'*|+|?'}[1]
				+	']'[1]
				 }[1]
				Serialization Steps */,
				sr._007 /* Base::MultiplicityCS(basecs::MultiplicityStringCS): {
				+	'['[1]
				+	stringBounds={'*|+|?'}[1]
				+	'|?'[1]
				+	']'[1]
				 }[1]
				Serialization Steps */,
				sr._004 /* Base::MultiplicityCS(basecs::MultiplicityStringCS): {
				+	'['[1]
				+	stringBounds={'*|+|?'}[1]
				+	isNullFree='|1'[?]
				+	']'[1]
				 }[1]
				Serialization Steps */,
				sr._008 /* Base::MultiplicityStringCS(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _31 // NameExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._042 /* EssentialOCL::ExpCS(essentialoclcs::NameExpCS): {
				+	ownedPathName=PathNameCS[1]
				+	ownedSquareBracketedClauses+=SquareBracketedClauseCS[*]
				+	ownedRoundBracketedClause=RoundBracketedClauseCS[?]
				+	ownedCurlyBracketedClause=CurlyBracketedClauseCS[?]
				+	{
				    +	isPre='@'[1]
				    +	'pre'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._058 /* EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): {
				+	ownedPathName=PathNameCS[1]
				+	ownedSquareBracketedClauses+=SquareBracketedClauseCS[*]
				+	ownedRoundBracketedClause=RoundBracketedClauseCS[?]
				+	ownedCurlyBracketedClause=CurlyBracketedClauseCS[?]
				+	{
				    +	isPre='@'[1]
				    +	'pre'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._042 /* EssentialOCL::ExpCS(essentialoclcs::NameExpCS): {
				+	ownedPathName=PathNameCS[1]
				+	ownedSquareBracketedClauses+=SquareBracketedClauseCS[*]
				+	ownedRoundBracketedClause=RoundBracketedClauseCS[?]
				+	ownedCurlyBracketedClause=CurlyBracketedClauseCS[?]
				+	{
				    +	isPre='@'[1]
				    +	'pre'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._059 /* EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): {
				+	ownedPathName=PathNameCS[1]
				+	ownedSquareBracketedClauses+=SquareBracketedClauseCS[*]
				+	ownedRoundBracketedClause=RoundBracketedClauseCS[?]
				+	ownedCurlyBracketedClause=CurlyBracketedClauseCS[?]
				+	{
				    +	isPre='@'[1]
				    +	'pre'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._058 /* EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): {
				+	ownedPathName=PathNameCS[1]
				+	ownedSquareBracketedClauses+=SquareBracketedClauseCS[*]
				+	ownedRoundBracketedClause=RoundBracketedClauseCS[?]
				+	ownedCurlyBracketedClause=CurlyBracketedClauseCS[?]
				+	{
				    +	isPre='@'[1]
				    +	'pre'[1]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._073 /* PathNameCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._013 /* CurlyBracketedClauseCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._084 /* RoundBracketedClauseCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._093 /* SquareBracketedClauseCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _32 // NavigatingArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._064 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS[1]
				Serialization Steps */,
				sr._061 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): {
				+	':'[1]
				+	ownedType=TypeExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._060 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): {
				+	ownedNameExpression=NavigatingArgExpCS[1]
				+	'<-'[1]
				+	ownedCoIterator=CoIteratorVariableCS[1]
				+	{
				    +	'='[1]
				    +	ownedInitExpression=ExpCS[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._062 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): {
				+	ownedNameExpression=NavigatingArgExpCS[1]
				+	':'[1]
				+	ownedType=TypeExpCS[1]
				+	{
				    +	'<-'[1]
				    +	ownedCoIterator=CoIteratorVariableCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	ownedInitExpression=ExpCS[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._063 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): {
				+	ownedNameExpression=NavigatingArgExpCS[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypeExpCS[1]
				     }[?]
				+	{
				    +	'<-'[1]
				    +	ownedCoIterator=CoIteratorVariableCS[1]
				     }[?]
				+	'in'[1]
				+	ownedInitExpression=ExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._065 /* EssentialOCL::NavigatingBarArgCS(essentialoclcs::NavigatingArgCS): {
				+	prefix='|'[1]
				+	ownedNameExpression=NavigatingArgExpCS[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypeExpCS[1]
				    +	{
				        +	'='[1]
				        +	ownedInitExpression=ExpCS[1]
				         }[?]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._067 /* EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): {
				+	prefix=','[1]
				+	ownedNameExpression=NavigatingArgExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._068 /* EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): {
				+	prefix=','[1]
				+	ownedNameExpression=NavigatingArgExpCS[1]
				+	'<-'[1]
				+	ownedCoIterator=CoIteratorVariableCS[1]
				+	{
				    +	'='[1]
				    +	ownedInitExpression=ExpCS[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._066 /* EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): {
				+	prefix=','[1]
				+	ownedNameExpression=NavigatingArgExpCS[1]
				+	':'[1]
				+	ownedType=TypeExpCS[1]
				+	{
				    +	'<-'[1]
				    +	ownedCoIterator=CoIteratorVariableCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	ownedInitExpression=ExpCS[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._069 /* EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): {
				+	prefix=','[1]
				+	ownedNameExpression=NavigatingArgExpCS[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypeExpCS[1]
				     }[?]
				+	{
				    +	'<-'[1]
				    +	ownedCoIterator=CoIteratorVariableCS[1]
				     }[?]
				+	'in'[1]
				+	ownedInitExpression=ExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._070 /* EssentialOCL::NavigatingSemiArgCS(essentialoclcs::NavigatingArgCS): {
				+	prefix=';'[1]
				+	ownedNameExpression=NavigatingArgExpCS[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypeExpCS[1]
				    +	{
				        +	'='[1]
				        +	ownedInitExpression=ExpCS[1]
				         }[?]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._107 /* TypeExpCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._113 /* TypeNameExpCS */,
						rv._106 /* TupleTypeCS */,
						rv._010 /* CollectionPatternCS */,
						rv._081 /* PrimitiveTypeCS */,
						rv._108 /* TypeExpWithoutMultiplicityCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._007 /* CoIteratorVariableCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._061 /* NavigatingArgExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _33 // NestedExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._034 /* EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): {
				+	'('[1]
				+	ownedExpression=ExpCS[1]
				+	')'[1]
				 }[1]
				Serialization Steps */,
				sr._034 /* EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): {
				+	'('[1]
				+	ownedExpression=ExpCS[1]
				+	')'[1]
				 }[1]
				Serialization Steps */,
				sr._071 /* EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): {
				+	'('[1]
				+	ownedExpression=ExpCS[1]
				+	')'[1]
				 }[1]
				Serialization Steps */,
				sr._072 /* EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): {
				+	'('[1]
				+	ownedExpression=ExpCS[1]
				+	')'[1]
				 }[1]
				Serialization Steps */,
				sr._071 /* EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): {
				+	'('[1]
				+	ownedExpression=ExpCS[1]
				+	')'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _34 // NullLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._073 /* EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null'[1]
				Serialization Steps */,
				sr._074 /* EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null'[1]
				Serialization Steps */,
				sr._074 /* EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null'[1]
				Serialization Steps */,
				sr._073 /* EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null'[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _35 // NumberLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._026 /* EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL[1]
				Serialization Steps */,
				sr._026 /* EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL[1]
				Serialization Steps */,
				sr._076 /* EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL[1]
				Serialization Steps */,
				sr._075 /* EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL[1]
				Serialization Steps */,
				sr._075 /* EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL[1]
				Serialization Steps */,
				sr._076 /* EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _36 // OCLinEcoreConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._160 /* OCLinEcore::InvariantConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): {
				+	isCallable='callable'[?]
				+	stereotype='invariant'[1]
				+	{
				    +	name=UnrestrictedName[1]
				    +	{
				        +	'('[1]
				        +	ownedMessageSpecification=SpecificationCS[1]
				        +	')'[1]
				         }[?]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._161 /* OCLinEcore::InvariantConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): {
				+	isCallable='callable'[?]
				+	stereotype='invariant'[1]
				+	{
				    +	name=UnrestrictedName[1]
				    +	{
				        +	'('[1]
				        +	ownedMessageSpecification=SpecificationCS[1]
				        +	')'[1]
				         }[?]
				     }[?]
				+	':'[1]
				+	ownedSpecification=SpecificationCS[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._180 /* OCLinEcore::PostconditionConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): {
				+	stereotype='postcondition'[1]
				+	{
				    +	name=UnrestrictedName[1]
				    +	{
				        +	'('[1]
				        +	ownedMessageSpecification=SpecificationCS[1]
				        +	')'[1]
				         }[?]
				     }[?]
				+	':'[1]
				+	ownedSpecification=SpecificationCS[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._181 /* OCLinEcore::PreconditionConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): {
				+	stereotype='precondition'[1]
				+	{
				    +	name=UnrestrictedName[1]
				    +	{
				        +	'('[1]
				        +	ownedMessageSpecification=SpecificationCS[1]
				        +	')'[1]
				         }[?]
				     }[?]
				+	':'[1]
				+	ownedSpecification=SpecificationCS[?]
				+	';'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._092 /* SpecificationCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._092 /* SpecificationCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _37 // OperationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._168 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._174 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._172 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._163 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedPreconditions+=PreconditionConstraintCS[*]
				+	{
				    +	'body'[1]
				    +	':'[1]
				    +	ownedBodyExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	ownedPostconditions+=PostconditionConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._173 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedPreconditions+=PreconditionConstraintCS[*]
				+	{
				    +	'body'[1]
				    +	':'[1]
				    +	ownedBodyExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	ownedPostconditions+=PostconditionConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._164 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedPreconditions+=PreconditionConstraintCS[*]
				+	{
				    +	'body'[1]
				    +	':'[1]
				    +	ownedBodyExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	ownedPostconditions+=PostconditionConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._169 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._165 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._166 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._170 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedPreconditions+=PreconditionConstraintCS[*]
				+	{
				    +	'body'[1]
				    +	':'[1]
				    +	ownedBodyExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	ownedPostconditions+=PostconditionConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._167 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedPreconditions+=PreconditionConstraintCS[*]
				+	{
				    +	'body'[1]
				    +	':'[1]
				    +	ownedBodyExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	ownedPostconditions+=PostconditionConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._171 /* OCLinEcore::OperationCS(basecs::OperationCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'operation'[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	name=UnrestrictedName[1]
				+	'('[1]
				+	{
				    +	ownedParameters+=ParameterCS[1]
				    +	{
				        +	','[1]
				        +	ownedParameters+=ParameterCS[1]
				         }[*]
				     }[?]
				+	')'[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'throws'[1]
				    +	ownedExceptions+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedExceptions+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedPreconditions+=PreconditionConstraintCS[*]
				+	{
				    +	'body'[1]
				    +	':'[1]
				    +	ownedBodyExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	ownedPostconditions+=PostconditionConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._076 /* PreconditionConstraintCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._072 /* ParameterCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._075 /* PostconditionConstraintCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._092 /* SpecificationCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._117 /* TypedRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._101 /* TemplateSignatureCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._116 /* TypedMultiplicityRefCS */,
						rv._117 /* TypedRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _38 // PackageCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._177 /* OCLinEcore::PackageCS(basecs::PackageCS): {
				+	'package'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	nsPrefix=UnrestrictedName[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	nsURI=URI[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._178 /* OCLinEcore::PackageCS(basecs::PackageCS): {
				+	'package'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	nsPrefix=UnrestrictedName[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	nsURI=URI[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedPackages+=PackageCS[*]
				+	ownedClasses+=ClassCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._176 /* OCLinEcore::PackageCS(basecs::PackageCS): {
				+	'package'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	nsPrefix=UnrestrictedName[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	nsURI=URI[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._175 /* OCLinEcore::PackageCS(basecs::PackageCS): {
				+	'package'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	nsPrefix=UnrestrictedName[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	nsURI=URI[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedPackages+=PackageCS[*]
				+	ownedClasses+=ClassCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._071 /* PackageCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._097 /* StructuredClassCS */,
						rv._021 /* EnumerationCS */,
						rv._015 /* DataTypeCS */,
						rv._006 /* ClassCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _39 // ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PARAMETER_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._179 /* OCLinEcore::ParameterCS(basecs::ParameterCS): {
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!ordered|!unique|ordered|unique'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	ownedAnnotations+=AnnotationElementCS[*]
				    +	'}'[1]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._116 /* TypedMultiplicityRefCS */,
						rv._117 /* TypedRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _40 // PathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._000 /* Base::FirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName[1]
				Serialization Steps */,
				sr._009 /* Base::NextPathElementCS(basecs::PathElementCS): referredElement=UnreservedName[1]
				Serialization Steps */,
				sr._112 /* EssentialOCL::URIFirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _41 // PathElementWithURICS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._113 /* EssentialOCL::URIFirstPathElementCS(basecs::PathElementWithURICS): referredElement=URI[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _42 // PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._010 /* Base::PathNameCS(basecs::PathNameCS): {
				+	ownedPathElements+=FirstPathElementCS[1]
				+	{
				    +	'::'[1]
				    +	ownedPathElements+=NextPathElementCS[1]
				     }[*]
				 }[1]
				Serialization Steps */,
				sr._088 /* EssentialOCL::SimplePathNameCS(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS[1]
				Serialization Steps */,
				sr._114 /* EssentialOCL::URIPathNameCS(basecs::PathNameCS): {
				+	ownedPathElements+=URIFirstPathElementCS[1]
				+	{
				    +	'::'[1]
				    +	ownedPathElements+=NextPathElementCS[1]
				     }[*]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._122 /* URIFirstPathElementCS */,
						rv._031 /* FirstPathElementCS */,
						rv._067 /* NextPathElementCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _43 // PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._077 /* EssentialOCL::PatternExpCS(essentialoclcs::PatternExpCS): {
				+	patternVariableName=UnrestrictedName[?]
				+	':'[1]
				+	ownedPatternType=TypeExpCS[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._107 /* TypeExpCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._113 /* TypeNameExpCS */,
						rv._106 /* TupleTypeCS */,
						rv._010 /* CollectionPatternCS */,
						rv._081 /* PrimitiveTypeCS */,
						rv._108 /* TypeExpWithoutMultiplicityCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _44 // PrefixExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._041 /* EssentialOCL::ExpCS(essentialoclcs::PrefixExpCS): {
				+	name=UnaryOperatorName[1]
				+	ownedRight=PrefixedPrimaryExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._078 /* EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): {
				+	name=UnaryOperatorName[1]
				+	ownedRight=PrefixedLetExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._041 /* EssentialOCL::ExpCS(essentialoclcs::PrefixExpCS): {
				+	name=UnaryOperatorName[1]
				+	ownedRight=PrefixedPrimaryExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._078 /* EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): {
				+	name=UnaryOperatorName[1]
				+	ownedRight=PrefixedLetExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._079 /* EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): {
				+	name=UnaryOperatorName[1]
				+	ownedRight=PrefixedLetExpCS[1]
				 }[1]
				Serialization Steps */,
				sr._080 /* EssentialOCL::PrefixedPrimaryExpCS(essentialoclcs::PrefixExpCS): {
				+	name=UnaryOperatorName[1]
				+	ownedRight=PrefixedPrimaryExpCS[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _45 // PrimitiveTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._082 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier[1]
				Serialization Steps */,
				sr._081 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier[1]
				Serialization Steps */,
				sr._102 /* EssentialOCL::TypeExpCS(basecs::PrimitiveTypeRefCS): {
				+	name=PrimitiveTypeIdentifier[1]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._082 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier[1]
				Serialization Steps */,
				sr._081 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier[1]
				Serialization Steps */,
				sr._106 /* EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::PrimitiveTypeRefCS): {
				+	name=PrimitiveTypeIdentifier[1]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._212 /* OCLinEcore::TypedMultiplicityRefCS(basecs::PrimitiveTypeRefCS): {
				+	name=PrimitiveTypeIdentifier[1]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._081 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._056 /* MultiplicityCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _46 // ReferenceCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._191 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._187 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._185 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._188 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'key'[1]
				    +	referredKeys+=UnrestrictedName[1]
				    +	{
				        +	','[1]
				        +	referredKeys+=UnrestrictedName[1]
				         }[*]
				    +	';'[1]
				     }[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	ownedImplicitOpposites+=ImplicitOppositeCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._189 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'key'[1]
				    +	referredKeys+=UnrestrictedName[1]
				    +	{
				        +	','[1]
				        +	referredKeys+=UnrestrictedName[1]
				         }[*]
				    +	';'[1]
				     }[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	ownedImplicitOpposites+=ImplicitOppositeCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._190 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'key'[1]
				    +	referredKeys+=UnrestrictedName[1]
				    +	{
				        +	','[1]
				        +	referredKeys+=UnrestrictedName[1]
				         }[*]
				    +	';'[1]
				     }[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	ownedImplicitOpposites+=ImplicitOppositeCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._186 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._184 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._182 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._193 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'key'[1]
				    +	referredKeys+=UnrestrictedName[1]
				    +	{
				        +	','[1]
				        +	referredKeys+=UnrestrictedName[1]
				         }[*]
				    +	';'[1]
				     }[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	ownedImplicitOpposites+=ImplicitOppositeCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._183 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'key'[1]
				    +	referredKeys+=UnrestrictedName[1]
				    +	{
				        +	','[1]
				        +	referredKeys+=UnrestrictedName[1]
				         }[*]
				    +	';'[1]
				     }[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	ownedImplicitOpposites+=ImplicitOppositeCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._192 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'key'[1]
				    +	referredKeys+=UnrestrictedName[1]
				    +	{
				        +	','[1]
				        +	referredKeys+=UnrestrictedName[1]
				         }[*]
				    +	';'[1]
				     }[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	ownedImplicitOpposites+=ImplicitOppositeCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._186 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._184 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._182 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._193 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'key'[1]
				    +	referredKeys+=UnrestrictedName[1]
				    +	{
				        +	','[1]
				        +	referredKeys+=UnrestrictedName[1]
				         }[*]
				    +	';'[1]
				     }[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	ownedImplicitOpposites+=ImplicitOppositeCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._183 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='definition'[1]
				+	qualifiers+='static'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'key'[1]
				    +	referredKeys+=UnrestrictedName[1]
				    +	{
				        +	','[1]
				        +	referredKeys+=UnrestrictedName[1]
				         }[*]
				    +	';'[1]
				     }[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	ownedImplicitOpposites+=ImplicitOppositeCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._192 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): {
				+	qualifiers+='static'[1]
				+	qualifiers+='definition'[?]
				+	'property'[1]
				+	name=UnrestrictedName[1]
				+	{
				    +	'#'[1]
				    +	referredOpposite=UnrestrictedName[1]
				     }[?]
				+	{
				    +	':'[1]
				    +	ownedType=TypedMultiplicityRefCS[1]
				     }[?]
				+	{
				    +	'='[1]
				    +	default=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	{
				        +	qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'}[1]
				         }[+]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	{
				    +	'key'[1]
				    +	referredKeys+=UnrestrictedName[1]
				    +	{
				        +	','[1]
				        +	referredKeys+=UnrestrictedName[1]
				         }[*]
				    +	';'[1]
				     }[*]
				+	{
				    +	'initial'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	'derivation'[1]
				    +	':'[1]
				    +	ownedDefaultExpressions+=SpecificationCS[?]
				    +	';'[1]
				     }[*]
				+	{
				    +	ownedImplicitOpposites+=ImplicitOppositeCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._092 /* SpecificationCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._116 /* TypedMultiplicityRefCS */,
						rv._117 /* TypedRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._037 /* ImplicitOppositeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _47 // RoundBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._083 /* EssentialOCL::RoundBracketedClauseCS(essentialoclcs::RoundBracketedClauseCS): {
				+	'('[1]
				+	{
				    +	ownedArguments+=NavigatingArgCS[1]
				    +	ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*]
				     }[?]
				+	')'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._060 /* NavigatingArgCS */,
						rv._064 /* NavigatingSemiArgCS */,
						rv._063 /* NavigatingCommaArgCS */,
						rv._062 /* NavigatingBarArgCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _48 // SelfExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SELF_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._085 /* EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): 'self'[1]
				Serialization Steps */,
				sr._085 /* EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): 'self'[1]
				Serialization Steps */,
				sr._084 /* EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): 'self'[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _49 // ShadowPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._087 /* EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS[1]
				Serialization Steps */,
				sr._086 /* EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): {
				+	referredProperty=UnrestrictedName[1]
				+	'='[1]
				+	ownedInitExpression=(ExpCS|PatternExpCS)[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._074 /* PatternExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _50 // SquareBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._089 /* EssentialOCL::SquareBracketedClauseCS(essentialoclcs::SquareBracketedClauseCS): {
				+	'['[1]
				+	ownedTerms+=ExpCS[1]
				+	{
				    +	','[1]
				    +	ownedTerms+=ExpCS[1]
				     }[*]
				+	']'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _51 // StringLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._028 /* EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
				Serialization Steps */,
				sr._028 /* EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
				Serialization Steps */,
				sr._091 /* EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
				Serialization Steps */,
				sr._091 /* EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
				Serialization Steps */,
				sr._091 /* EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
				Serialization Steps */,
				sr._090 /* EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _52 // StructuredClassCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._199 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): {
				+	isAbstract='abstract'[?]
				+	'class'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	'extends'[1]
				    +	ownedSuperTypes+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedSuperTypes+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isInterface='interface'[?]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._198 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): {
				+	isAbstract='abstract'[?]
				+	'class'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	'extends'[1]
				    +	ownedSuperTypes+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedSuperTypes+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isInterface='interface'[?]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedOperations+=OperationCS[*]
				+	ownedProperties+=StructuralFeatureCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._199 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): {
				+	isAbstract='abstract'[?]
				+	'class'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	'extends'[1]
				    +	ownedSuperTypes+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedSuperTypes+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isInterface='interface'[?]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._198 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): {
				+	isAbstract='abstract'[?]
				+	'class'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	'extends'[1]
				    +	ownedSuperTypes+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedSuperTypes+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isInterface='interface'[?]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedOperations+=OperationCS[*]
				+	ownedProperties+=StructuralFeatureCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._197 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): {
				+	isAbstract='abstract'[?]
				+	'class'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	'extends'[1]
				    +	ownedSuperTypes+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedSuperTypes+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isInterface='interface'[?]
				    +	'}'[1]
				     }[?]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._196 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): {
				+	isAbstract='abstract'[?]
				+	'class'[1]
				+	name=UnrestrictedName[1]
				+	ownedSignature=TemplateSignatureCS[?]
				+	{
				    +	'extends'[1]
				    +	ownedSuperTypes+=TypedRefCS[1]
				    +	{
				        +	','[1]
				        +	ownedSuperTypes+=TypedRefCS[1]
				         }[*]
				     }[?]
				+	{
				    +	':'[1]
				    +	instanceClassName=SINGLE_QUOTED_STRING[1]
				     }[?]
				+	{
				    +	'{'[1]
				    +	isInterface='interface'[?]
				    +	'}'[1]
				     }[?]
				+	'{'[1]
				+	ownedAnnotations+=AnnotationElementCS[*]
				+	ownedOperations+=OperationCS[*]
				+	ownedProperties+=StructuralFeatureCS[*]
				+	ownedConstraints+=InvariantConstraintCS[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._041 /* InvariantConstraintCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._117 /* TypedRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._101 /* TemplateSignatureCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._083 /* ReferenceCS */,
						rv._096 /* StructuralFeatureCS */,
						rv._003 /* AttributeCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._098 /* SysMLCS */,
						rv._017 /* DocumentationCS */,
						rv._001 /* AnnotationCS */,
						rv._002 /* AnnotationElementCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._070 /* OperationCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _53 // SysMLCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.SYS_MLCS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._201 /* OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): {
				+	'sysml'[1]
				+	ownedDetails+=DetailCS[1]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._200 /* OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): {
				+	'sysml'[1]
				+	'{'[1]
				+	{
				    +	ownedDetails+=DetailCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._203 /* OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): {
				+	'sysml'[1]
				+	ownedDetails+=DetailCS[1]
				+	';'[1]
				 }[1]
				Serialization Steps */,
				sr._202 /* OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): {
				+	'sysml'[1]
				+	'{'[1]
				+	{
				    +	ownedDetails+=DetailCS[1]
				    +	';'[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._016 /* DetailCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _54 // TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._011 /* Base::TemplateBindingCS(basecs::TemplateBindingCS): {
				+	ownedSubstitutions+=TemplateParameterSubstitutionCS[1]
				+	{
				    +	','[1]
				    +	ownedSubstitutions+=TemplateParameterSubstitutionCS[1]
				     }[*]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._100 /* TemplateParameterSubstitutionCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._056 /* MultiplicityCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _55 // TemplateParameterSubstitutionCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._012 /* Base::TemplateParameterSubstitutionCS(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._117 /* TypedRefCS */,
						rv._129 /* WildcardTypeRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._115 /* TypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _56 // TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._205 /* OCLinEcore::TemplateSignatureCS(basecs::TemplateSignatureCS): {
				+	'('[1]
				+	ownedParameters+=TypeParameterCS[1]
				+	{
				    +	','[1]
				    +	ownedParameters+=TypeParameterCS[1]
				     }[*]
				+	')'[1]
				 }[1]
				Serialization Steps */,
				sr._204 /* OCLinEcore::TemplateSignatureCS(basecs::TemplateSignatureCS): {
				+	'<'[1]
				+	ownedParameters+=TypeParameterCS[1]
				+	{
				    +	','[1]
				    +	ownedParameters+=TypeParameterCS[1]
				     }[*]
				+	'>'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._114 /* TypeParameterCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _57 // TopLevelCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.TOP_LEVEL_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._206 /* OCLinEcore::TopLevelCS(oclinecorecs::TopLevelCS): {
				+	{
				    +	'module'[1]
				     }[?]
				+	ownedImports+=ImportCS[*]
				+	ownedPackages+=PackageCS[*]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._071 /* PackageCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._038 /* ImportCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _58 // TupleLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._037 /* EssentialOCL::ExpCS(essentialoclcs::TupleLiteralExpCS): {
				+	'Tuple'[1]
				+	'{'[1]
				+	ownedParts+=TupleLiteralPartCS[1]
				+	{
				    +	','[1]
				    +	ownedParts+=TupleLiteralPartCS[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._037 /* EssentialOCL::ExpCS(essentialoclcs::TupleLiteralExpCS): {
				+	'Tuple'[1]
				+	'{'[1]
				+	ownedParts+=TupleLiteralPartCS[1]
				+	{
				    +	','[1]
				    +	ownedParts+=TupleLiteralPartCS[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._093 /* EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): {
				+	'Tuple'[1]
				+	'{'[1]
				+	ownedParts+=TupleLiteralPartCS[1]
				+	{
				    +	','[1]
				    +	ownedParts+=TupleLiteralPartCS[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._093 /* EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): {
				+	'Tuple'[1]
				+	'{'[1]
				+	ownedParts+=TupleLiteralPartCS[1]
				+	{
				    +	','[1]
				    +	ownedParts+=TupleLiteralPartCS[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */,
				sr._092 /* EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): {
				+	'Tuple'[1]
				+	'{'[1]
				+	ownedParts+=TupleLiteralPartCS[1]
				+	{
				    +	','[1]
				    +	ownedParts+=TupleLiteralPartCS[1]
				     }[*]
				+	'}'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._104 /* TupleLiteralPartCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _59 // TupleLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._094 /* EssentialOCL::TupleLiteralPartCS(essentialoclcs::TupleLiteralPartCS): {
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypeExpCS[1]
				     }[?]
				+	'='[1]
				+	ownedInitExpression=ExpCS[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._107 /* TypeExpCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._113 /* TypeNameExpCS */,
						rv._106 /* TupleTypeCS */,
						rv._010 /* CollectionPatternCS */,
						rv._081 /* PrimitiveTypeCS */,
						rv._108 /* TypeExpWithoutMultiplicityCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _60 // TuplePartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._095 /* EssentialOCL::TuplePartCS(basecs::TuplePartCS): {
				+	name=UnrestrictedName[1]
				+	':'[1]
				+	ownedType=TypeExpCS[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._107 /* TypeExpCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._113 /* TypeNameExpCS */,
						rv._106 /* TupleTypeCS */,
						rv._010 /* CollectionPatternCS */,
						rv._081 /* PrimitiveTypeCS */,
						rv._108 /* TypeExpWithoutMultiplicityCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _61 // TupleTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._096 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): {
				+	name='Tuple'[1]
				+	{
				    +	'('[1]
				    +	{
				        +	ownedParts+=TuplePartCS[1]
				        +	{
				            +	','[1]
				            +	ownedParts+=TuplePartCS[1]
				             }[*]
				         }[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._097 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): {
				+	name='Tuple'[1]
				+	{
				    +	'('[1]
				    +	{
				        +	ownedParts+=TuplePartCS[1]
				        +	{
				            +	','[1]
				            +	ownedParts+=TuplePartCS[1]
				             }[*]
				         }[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._099 /* EssentialOCL::TypeExpCS(basecs::TupleTypeCS): {
				+	name='Tuple'[1]
				+	{
				    +	'('[1]
				    +	{
				        +	ownedParts+=TuplePartCS[1]
				        +	{
				            +	','[1]
				            +	ownedParts+=TuplePartCS[1]
				             }[*]
				         }[?]
				    +	')'[1]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._096 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): {
				+	name='Tuple'[1]
				+	{
				    +	'('[1]
				    +	{
				        +	ownedParts+=TuplePartCS[1]
				        +	{
				            +	','[1]
				            +	ownedParts+=TuplePartCS[1]
				             }[*]
				         }[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._097 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): {
				+	name='Tuple'[1]
				+	{
				    +	'('[1]
				    +	{
				        +	ownedParts+=TuplePartCS[1]
				        +	{
				            +	','[1]
				            +	ownedParts+=TuplePartCS[1]
				             }[*]
				         }[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._108 /* EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::TupleTypeCS): {
				+	name='Tuple'[1]
				+	{
				    +	'('[1]
				    +	{
				        +	ownedParts+=TuplePartCS[1]
				        +	{
				            +	','[1]
				            +	ownedParts+=TuplePartCS[1]
				             }[*]
				         }[?]
				    +	')'[1]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._211 /* OCLinEcore::TypedMultiplicityRefCS(basecs::TupleTypeCS): {
				+	name='Tuple'[1]
				+	{
				    +	'('[1]
				    +	{
				        +	ownedParts+=TuplePartCS[1]
				        +	{
				            +	','[1]
				            +	ownedParts+=TuplePartCS[1]
				             }[*]
				         }[?]
				    +	')'[1]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._097 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): {
				+	name='Tuple'[1]
				+	{
				    +	'('[1]
				    +	{
				        +	ownedParts+=TuplePartCS[1]
				        +	{
				            +	','[1]
				            +	ownedParts+=TuplePartCS[1]
				             }[*]
				         }[?]
				    +	')'[1]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._105 /* TuplePartCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._056 /* MultiplicityCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _62 // TypeLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._036 /* EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS[1]
				Serialization Steps */,
				sr._036 /* EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS[1]
				Serialization Steps */,
				sr._105 /* EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS[1]
				Serialization Steps */,
				sr._105 /* EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS[1]
				Serialization Steps */,
				sr._104 /* EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._112 /* TypeLiteralWithMultiplicityCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._081 /* PrimitiveTypeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _63 // TypeNameExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._103 /* EssentialOCL::TypeExpCS(essentialoclcs::TypeNameExpCS): {
				+	ownedPathName=PathNameCS[1]
				+	{
				    +	ownedCurlyBracketedClause=CurlyBracketedClauseCS[1]
				    +	{
				        +	'{'[1]
				        +	ownedPatternGuard=ExpCS[1]
				        +	'}'[1]
				         }[?]
				     }[?]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._111 /* EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): {
				+	ownedPathName=PathNameCS[1]
				+	{
				    +	ownedCurlyBracketedClause=CurlyBracketedClauseCS[1]
				    +	{
				        +	'{'[1]
				        +	ownedPatternGuard=ExpCS[1]
				        +	'}'[1]
				         }[?]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._110 /* EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): {
				+	ownedPathName=PathNameCS[1]
				+	{
				    +	ownedCurlyBracketedClause=CurlyBracketedClauseCS[1]
				    +	{
				        +	'{'[1]
				        +	ownedPatternGuard=ExpCS[1]
				        +	'}'[1]
				         }[?]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._073 /* PathNameCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._080 /* PrimitiveLiteralExpCS */,
						rv._008 /* CollectionLiteralExpCS */,
						rv._079 /* PrimaryExpCS */,
						rv._044 /* LambdaLiteralExpCS */,
						rv._036 /* IfExpCS */,
						rv._068 /* NullLiteralExpCS */,
						rv._077 /* PrefixedLetExpCS */,
						rv._078 /* PrefixedPrimaryExpCS */,
						rv._111 /* TypeLiteralExpCS */,
						rv._066 /* NestedExpCS */,
						rv._040 /* InvalidLiteralExpCS */,
						rv._125 /* UnlimitedNaturalLiteralExpCS */,
						rv._089 /* SelfExpCS */,
						rv._049 /* MapLiteralExpCS */,
						rv._103 /* TupleLiteralExpCS */,
						rv._005 /* BooleanLiteralExpCS */,
						rv._045 /* LetExpCS */,
						rv._095 /* StringLiteralExpCS */,
						rv._030 /* ExpCS */,
						rv._059 /* NameExpCS */,
						rv._069 /* NumberLiteralExpCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._056 /* MultiplicityCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._013 /* CurlyBracketedClauseCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _64 // TypeParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._013 /* Base::TypeParameterCS(basecs::TypeParameterCS): {
				+	name=UnrestrictedName[1]
				+	{
				    +	'extends'[1]
				    +	ownedExtends+=TypedRefCS[1]
				    +	{
				        +	'&&'[1]
				        +	ownedExtends+=TypedRefCS[1]
				         }[*]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._117 /* TypedRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _65 // TypedTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._218 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS[1]
				Serialization Steps */,
				sr._219 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): {
				+	ownedPathName=PathNameCS[1]
				+	'('[1]
				+	ownedBinding=TemplateBindingCS[1]
				+	')'[1]
				 }[1]
				Serialization Steps */,
				sr._215 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): {
				+	ownedPathName=PathNameCS[1]
				+	'<'[1]
				+	ownedBinding=TemplateBindingCS[1]
				+	'>'[1]
				 }[1]
				Serialization Steps */,
				sr._208 /* OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): {
				+	ownedPathName=PathNameCS[1]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._213 /* OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): {
				+	ownedPathName=PathNameCS[1]
				+	'('[1]
				+	ownedBinding=TemplateBindingCS[1]
				+	')'[1]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._209 /* OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): {
				+	ownedPathName=PathNameCS[1]
				+	'<'[1]
				+	ownedBinding=TemplateBindingCS[1]
				+	'>'[1]
				+	ownedMultiplicity=MultiplicityCS[?]
				 }[1]
				Serialization Steps */,
				sr._218 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS[1]
				Serialization Steps */,
				sr._219 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): {
				+	ownedPathName=PathNameCS[1]
				+	'('[1]
				+	ownedBinding=TemplateBindingCS[1]
				+	')'[1]
				 }[1]
				Serialization Steps */,
				sr._215 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): {
				+	ownedPathName=PathNameCS[1]
				+	'<'[1]
				+	ownedBinding=TemplateBindingCS[1]
				+	'>'[1]
				 }[1]
				Serialization Steps */,
				sr._217 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS[1]
				Serialization Steps */,
				sr._216 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): {
				+	ownedPathName=PathNameCS[1]
				+	'('[1]
				+	ownedBinding=TemplateBindingCS[1]
				+	')'[1]
				 }[1]
				Serialization Steps */,
				sr._214 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): {
				+	ownedPathName=PathNameCS[1]
				+	'<'[1]
				+	ownedBinding=TemplateBindingCS[1]
				+	'>'[1]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._073 /* PathNameCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._099 /* TemplateBindingCS */
					}),
						new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._056 /* MultiplicityCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _66 // UnlimitedNaturalLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._115 /* EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*'[1]
				Serialization Steps */,
				sr._115 /* EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*'[1]
				Serialization Steps */,
				sr._115 /* EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*'[1]
				Serialization Steps */,
				sr._116 /* EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*'[1]
				Serialization Steps */
				},
				null
				);
			private final /*@NonNull*/ EClassData _67 // VariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._017 /* EssentialOCL::CoIteratorVariableCS(essentialoclcs::VariableCS): {
				+	name=UnrestrictedName[1]
				+	{
				    +	':'[1]
				    +	ownedType=TypeExpCS[1]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._107 /* TypeExpCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._113 /* TypeNameExpCS */,
						rv._106 /* TupleTypeCS */,
						rv._010 /* CollectionPatternCS */,
						rv._081 /* PrimitiveTypeCS */,
						rv._108 /* TypeExpWithoutMultiplicityCS */
					})
					}
						);
			private final /*@NonNull*/ EClassData _68 // WildcardTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule [] {
				sr._015 /* Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): {
				+	'?'[1]
				+	{
				    +	'extends'[1]
				    +	ownedExtends=TypedRefCS[1]
				     }[?]
				 }[1]
				Serialization Steps */,
				sr._014 /* Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): {
				+	'?'[1]
				+	{
				    +	'extends'[1]
				    +	ownedExtends=TypedRefCS[1]
				     }[?]
				 }[1]
				Serialization Steps */
				},
				new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData[] {
					new org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue[] {
						rv._117 /* TypedRefCS */,
						rv._051 /* MapTypeCS */,
						rv._011 /* CollectionTypeCS */,
						rv._110 /* TypeLiteralCS */,
						rv._106 /* TupleTypeCS */,
						rv._118 /* TypedTypeRefCS */,
						rv._081 /* PrimitiveTypeCS */
					})
					}
						);
		}

		private class _SerializationRules
		{
			private final /*@NonNull*/ SerializationRule _000
				= /* referredElement=UnrestrictedName */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(31, /* Base::FirstPathElementCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._049 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._150 /* 1*referredElement=UnrestrictedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _001
				= /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(55, /* Base::MultiplicityBoundsCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._100 /* assign V0 = |upperBound| */,
						ms._001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._068 /* 1*lowerBound=LOWER */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._160 /* 1*upperBound=UPPER */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _002
				= /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._100 /* assign V0 = |upperBound| */,
						ms._001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._021 /* 1*'[' */,
						st._068 /* 1*lowerBound=LOWER */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._160 /* 1*upperBound=UPPER */,
						st._062 /* 1*'|?' */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _003
				= /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._116 /* assign V1 = |isNullFree.'|1'| */,
						ms._100 /* assign V0 = |upperBound| */,
						ms._001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._021 /* 1*'[' */,
						st._068 /* 1*lowerBound=LOWER */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._160 /* 1*upperBound=UPPER */,
						st._180 /* V01*'|1' */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _004
				= /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._073 /* assign V0 = |isNullFree.'|1'| */,
						ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._021 /* 1*'[' */,
						st._157 /* 1*stringBounds */,
						st._167 /* V00*'|1' */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _005
				= /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._100 /* assign V0 = |upperBound| */,
						ms._001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._021 /* 1*'[' */,
						st._068 /* 1*lowerBound=LOWER */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._160 /* 1*upperBound=UPPER */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _006
				= /* { '[' stringBounds={'*|+|?'} ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._021 /* 1*'[' */,
						st._157 /* 1*stringBounds */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _007
				= /* { '[' stringBounds={'*|+|?'} '|?' ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._021 /* 1*'[' */,
						st._157 /* 1*stringBounds */,
						st._062 /* 1*'|?' */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _008
				= /* stringBounds={'*|+|?'} */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(57, /* Base::MultiplicityStringCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._157 /* 1*stringBounds */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _009
				= /* referredElement=UnreservedName */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(67, /* Base::NextPathElementCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._049 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._149 /* 1*referredElement=UnreservedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _010
				= /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(73, /* Base::PathNameCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._247 /* check-rule basecs::PathNameCS.ownedPathElements : 31|67 */,
						ms._065 /* assign V0 = (|ownedPathElements| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._117 /* 1*ownedPathElements+=FirstPathElementCS */,
						st._169 /* V00*next-2-steps */,
						st._010 /* 1*'::' */,
						st._118 /* 1*ownedPathElements+=NextPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _011
				= /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(99, /* Base::TemplateBindingCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._256 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100 */,
						ms._255 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56 */,
						ms._124 /* assign V1 = |ownedMultiplicity| */,
						ms._066 /* assign V0 = (|ownedSubstitutions| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-5-steps */,
						st._130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
						st._186 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _012
				= /* ownedActualParameter=TypeRefCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(100, /* Base::TemplateParameterSubstitutionCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._257 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115 */,
						ms._007 /* assert (|ownedActualParameter| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._079 /* 1*ownedActualParameter=TypeRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _013
				= /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(114, /* Base::TypeParameterCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._261 /* check-rule basecs::TypeParameterCS.ownedExtends : 117 */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._058 /* assign V0 = (|ownedExtends| > 0) */,
						ms._105 /* assign V1 = (|ownedExtends| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._095 /* 1*ownedExtends+=TypedRefCS */,
						st._181 /* V01*next-2-steps */,
						st._002 /* 1*'&&' */,
						st._095 /* 1*ownedExtends+=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _014
				= /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(129, /* Base::WildcardTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._267 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 117 */,
						ms._081 /* assign V0 = |ownedExtends| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._016 /* 1*'?' */,
						st._169 /* V00*next-2-steps */,
						st._035 /* 1*'extends' */,
						st._096 /* 1*ownedExtends=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _015
				= /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(129, /* Base::WildcardTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._267 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 117 */,
						ms._081 /* assign V0 = |ownedExtends| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._016 /* 1*'?' */,
						st._169 /* V00*next-2-steps */,
						st._035 /* 1*'extends' */,
						st._096 /* 1*ownedExtends=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _016
				= /* symbol={'false|true'} */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(5, /* EssentialOCL::BooleanLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._055 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _017
				= /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(7, /* EssentialOCL::CoIteratorVariableCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
						ms._090 /* assign V0 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _018
				= /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(8, /* EssentialOCL::CollectionLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._272 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
						ms._273 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
						ms._042 /* assert (|ownedType| - 1) == 0 */,
						ms._063 /* assign V0 = (|ownedParts| > 0) */,
						ms._108 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._136 /* 1*ownedType=CollectionTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _019
				= /* ownedExpression=PatternExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(9, /* EssentialOCL::CollectionLiteralPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._275 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74 */,
						ms._018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._093 /* 1*ownedExpression=PatternExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _020
				= /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(9, /* EssentialOCL::CollectionLiteralPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._276 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30 */,
						ms._274 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30 */,
						ms._084 /* assign V0 = |ownedLastExpression| */,
						ms._018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._089 /* 1*ownedExpression=ExpCS */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._105 /* 1*ownedLastExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _021
				= /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(10, /* EssentialOCL::CollectionPatternCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._277 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
						ms._278 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
						ms._098 /* assign V0 = |restVariableName| */,
						ms._109 /* assign V1 = (|ownedParts| - 1) */,
						ms._041 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._135 /* 1*ownedType=CollectionTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-6-steps */,
						st._113 /* 1*ownedParts+=PatternExpCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._113 /* 1*ownedParts+=PatternExpCS */,
						st._006 /* 1*'++' */,
						st._156 /* 1*restVariableName=Identifier */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _022
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(11, /* EssentialOCL::CollectionTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
						ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
						ms._092 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _023
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(11, /* EssentialOCL::CollectionTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
						ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
						ms._092 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _024
				= /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(13, /* EssentialOCL::CurlyBracketedClauseCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._282 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90 */,
						ms._062 /* assign V0 = (|ownedParts| > 0) */,
						ms._107 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._114 /* 1*ownedParts+=ShadowPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._114 /* 1*ownedParts+=ShadowPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _025
				= /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(20, /* EssentialOCL::ElseIfThenExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._289 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30 */,
						ms._288 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30 */,
						ms._036 /* assert (|ownedThenExpression| - 1) == 0 */,
						ms._011 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._032 /* 1*'elseif' */,
						st._083 /* 1*ownedCondition=ExpCS */,
						st._058 /* 1*'then' */,
						st._134 /* 1*ownedThenExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _026
				= /* symbol=NUMBER_LITERAL */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._056 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _027
				= /* 'self' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _028
				= /* segments+=StringLiteral[+] */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._099 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _029
				= /* symbol={'false|true'} */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._055 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _030
				= /* 'invalid' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _031
				= /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._290 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : 78 */,
						ms._306 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 30 */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._025 /* assert (|ownedLeft| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._106 /* 1*ownedLeft=PrefixedPrimaryExpCS */,
						st._069 /* 1*name=BinaryOperatorName */,
						st._127 /* 1*ownedRight=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _032
				= /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
						ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
						ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
						ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
						ms._013 /* assert (|ownedElseExpression| - 1) == 0 */,
						ms._082 /* assign V0 = |ownedIfThenExpressions| */,
						ms._035 /* assert (|ownedThenExpression| - 1) == 0 */,
						ms._010 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._036 /* 1*'if' */,
						st._084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						st._058 /* 1*'then' */,
						st._133 /* 1*ownedThenExpression=ExpCS */,
						st._170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						st._031 /* 1*'else' */,
						st._087 /* 1*ownedElseExpression=ExpCS */,
						st._033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _033
				= /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
						ms._014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._018 /* 1*'Lambda' */,
						st._060 /* 1*'{' */,
						st._094 /* 1*ownedExpressionCS=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _034
				= /* { '(' ownedExpression=ExpCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
						ms._016 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._003 /* 1*'(' */,
						st._091 /* 1*ownedExpression=ExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _035
				= /* 'null' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _036
				= /* ownedType=TypeLiteralWithMultiplicityCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
						ms._037 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _037
				= /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
						ms._060 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._020 /* 1*'Tuple' */,
						st._060 /* 1*'{' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _038
				= /* '*' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _039
				= /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._272 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
						ms._273 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
						ms._042 /* assert (|ownedType| - 1) == 0 */,
						ms._063 /* assign V0 = (|ownedParts| > 0) */,
						ms._108 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._136 /* 1*ownedType=CollectionTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _040
				= /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
						ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
						ms._039 /* assert (|ownedType| - 1) == 0 */,
						ms._064 /* assign V0 = (|ownedParts| > 0) */,
						ms._110 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._137 /* 1*ownedType=MapTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _041
				= /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._308 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._073 /* 1*name=UnaryOperatorName */,
						st._129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _042
				= /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
						ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
						ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
						ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
						ms._174 /* assign V3 = |isPre.'@'| */,
						ms._159 /* assign V2 = |ownedCurlyBracketedClause| */,
						ms._127 /* assign V1 = |ownedRoundBracketedClause| */,
						ms._088 /* assign V0 = |ownedSquareBracketedClauses| */,
						ms._028 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._123 /* 1*ownedPathName=PathNameCS */,
						st._174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						st._187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						st._191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._196 /* V03*next-2-steps */,
						st._017 /* 1*'@' */,
						st._051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _043
				= /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(36, /* EssentialOCL::IfExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
						ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
						ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
						ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
						ms._013 /* assert (|ownedElseExpression| - 1) == 0 */,
						ms._082 /* assign V0 = |ownedIfThenExpressions| */,
						ms._035 /* assert (|ownedThenExpression| - 1) == 0 */,
						ms._010 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._036 /* 1*'if' */,
						st._084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						st._058 /* 1*'then' */,
						st._133 /* 1*ownedThenExpression=ExpCS */,
						st._170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						st._031 /* 1*'else' */,
						st._087 /* 1*ownedElseExpression=ExpCS */,
						st._033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _044
				= /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(36, /* EssentialOCL::IfExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
						ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
						ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
						ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
						ms._013 /* assert (|ownedElseExpression| - 1) == 0 */,
						ms._082 /* assign V0 = |ownedIfThenExpressions| */,
						ms._035 /* assert (|ownedThenExpression| - 1) == 0 */,
						ms._010 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._036 /* 1*'if' */,
						st._084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						st._058 /* 1*'then' */,
						st._133 /* 1*ownedThenExpression=ExpCS */,
						st._170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						st._031 /* 1*'else' */,
						st._087 /* 1*ownedElseExpression=ExpCS */,
						st._033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _045
				= /* 'invalid' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(40, /* EssentialOCL::InvalidLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _046
				= /* 'invalid' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(40, /* EssentialOCL::InvalidLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _047
				= /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(44, /* EssentialOCL::LambdaLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
						ms._014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._018 /* 1*'Lambda' */,
						st._060 /* 1*'{' */,
						st._094 /* 1*ownedExpressionCS=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _048
				= /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(44, /* EssentialOCL::LambdaLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
						ms._014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._018 /* 1*'Lambda' */,
						st._060 /* 1*'{' */,
						st._094 /* 1*ownedExpressionCS=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _049
				= /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(45, /* EssentialOCL::LetExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._292 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 30 */,
						ms._293 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 46 */,
						ms._019 /* assert (|ownedInExpression| - 1) == 0 */,
						ms._068 /* assign V0 = (|ownedVariables| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._043 /* 1*'let' */,
						st._146 /* 1*ownedVariables+=LetVariableCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._146 /* 1*ownedVariables+=LetVariableCS */,
						st._038 /* 1*'in' */,
						st._098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _050
				= /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(45, /* EssentialOCL::LetExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._292 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 30 */,
						ms._293 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 46 */,
						ms._019 /* assert (|ownedInExpression| - 1) == 0 */,
						ms._068 /* assign V0 = (|ownedVariables| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._043 /* 1*'let' */,
						st._146 /* 1*ownedVariables+=LetVariableCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._146 /* 1*ownedVariables+=LetVariableCS */,
						st._038 /* 1*'in' */,
						st._098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _051
				= /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(46, /* EssentialOCL::LetVariableCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
						ms._319 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
						ms._294 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84 */,
						ms._020 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._130 /* assign V1 = |ownedType| */,
						ms._086 /* assign V0 = |ownedRoundBracketedClause| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._172 /* V00*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */,
						st._014 /* 1*'=' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _052
				= /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(49, /* EssentialOCL::MapLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
						ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
						ms._039 /* assert (|ownedType| - 1) == 0 */,
						ms._064 /* assign V0 = (|ownedParts| > 0) */,
						ms._110 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._137 /* 1*ownedType=MapTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _053
				= /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(49, /* EssentialOCL::MapLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
						ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
						ms._039 /* assert (|ownedType| - 1) == 0 */,
						ms._064 /* assign V0 = (|ownedParts| > 0) */,
						ms._110 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._137 /* 1*ownedType=MapTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _054
				= /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(50, /* EssentialOCL::MapLiteralPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._297 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30 */,
						ms._298 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30 */,
						ms._043 /* assert (|ownedValue| - 1) == 0 */,
						ms._024 /* assert (|ownedKey| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._103 /* 1*ownedKey=ExpCS */,
						st._013 /* 1*'<-' */,
						st._144 /* 1*ownedValue=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _055
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(51, /* EssentialOCL::MapTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
						ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _056
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(51, /* EssentialOCL::MapTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
						ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _057
				= /* ownedExpression=ExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(52, /* EssentialOCL::Model */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._281 /* check-rule essentialoclcs::ContextCS.ownedExpression : 30 */,
						ms._017 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._092 /* 1*ownedExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _058
				= /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(59, /* EssentialOCL::NameExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
						ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
						ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
						ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
						ms._174 /* assign V3 = |isPre.'@'| */,
						ms._159 /* assign V2 = |ownedCurlyBracketedClause| */,
						ms._127 /* assign V1 = |ownedRoundBracketedClause| */,
						ms._088 /* assign V0 = |ownedSquareBracketedClauses| */,
						ms._028 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._123 /* 1*ownedPathName=PathNameCS */,
						st._174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						st._187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						st._191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._196 /* V03*next-2-steps */,
						st._017 /* 1*'@' */,
						st._051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _059
				= /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(59, /* EssentialOCL::NameExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
						ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
						ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
						ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
						ms._174 /* assign V3 = |isPre.'@'| */,
						ms._159 /* assign V2 = |ownedCurlyBracketedClause| */,
						ms._127 /* assign V1 = |ownedRoundBracketedClause| */,
						ms._088 /* assign V0 = |ownedSquareBracketedClauses| */,
						ms._028 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._123 /* 1*ownedPathName=PathNameCS */,
						st._174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						st._187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						st._191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._196 /* V03*next-2-steps */,
						st._017 /* 1*'@' */,
						st._051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _060
				= /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
						ms._083 /* assign V0 = |ownedInitExpression| */,
						ms._009 /* assert (|ownedCoIterator| - 1) == 0 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._169 /* V00*next-2-steps */,
						st._014 /* 1*'=' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _061
				= /* { ':' ownedType=TypeExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
						ms._040 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _062
				= /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
						ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
						ms._122 /* assign V1 = |ownedInitExpression| */,
						ms._078 /* assign V0 = |ownedCoIterator| */,
						ms._040 /* assert (|ownedType| - 1) == 0 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */,
						st._169 /* V00*next-2-steps */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _063
				= /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
						ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
						ms._021 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._119 /* assign V1 = |ownedCoIterator| */,
						ms._091 /* assign V0 = |ownedType| */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */,
						st._181 /* V01*next-2-steps */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._038 /* 1*'in' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _064
				= /* ownedNameExpression=NavigatingArgExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _065
				= /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(62, /* EssentialOCL::NavigatingBarArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
						ms._091 /* assign V0 = |ownedType| */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._046 /* assert (|prefix.'|'| - 1) == 0 */,
						ms._122 /* assign V1 = |ownedInitExpression| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._061 /* 1*'|' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._169 /* V00*next-5-steps */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _066
				= /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(63, /* EssentialOCL::NavigatingCommaArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
						ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
						ms._122 /* assign V1 = |ownedInitExpression| */,
						ms._078 /* assign V0 = |ownedCoIterator| */,
						ms._040 /* assert (|ownedType| - 1) == 0 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._044 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._007 /* 1*',' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */,
						st._169 /* V00*next-2-steps */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _067
				= /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(63, /* EssentialOCL::NavigatingCommaArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._044 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._007 /* 1*',' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._3 /* «! » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _068
				= /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(63, /* EssentialOCL::NavigatingCommaArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
						ms._083 /* assign V0 = |ownedInitExpression| */,
						ms._009 /* assert (|ownedCoIterator| - 1) == 0 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._044 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._007 /* 1*',' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._169 /* V00*next-2-steps */,
						st._014 /* 1*'=' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _069
				= /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(63, /* EssentialOCL::NavigatingCommaArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
						ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
						ms._021 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._119 /* assign V1 = |ownedCoIterator| */,
						ms._091 /* assign V0 = |ownedType| */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._044 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._007 /* 1*',' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */,
						st._181 /* V01*next-2-steps */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._038 /* 1*'in' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _070
				= /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(64, /* EssentialOCL::NavigatingSemiArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
						ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
						ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
						ms._091 /* assign V0 = |ownedType| */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._045 /* assert (|prefix.';'| - 1) == 0 */,
						ms._122 /* assign V1 = |ownedInitExpression| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._011 /* 1*';' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._169 /* V00*next-5-steps */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _071
				= /* { '(' ownedExpression=ExpCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(66, /* EssentialOCL::NestedExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
						ms._016 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._003 /* 1*'(' */,
						st._091 /* 1*ownedExpression=ExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _072
				= /* { '(' ownedExpression=ExpCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(66, /* EssentialOCL::NestedExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
						ms._016 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._003 /* 1*'(' */,
						st._091 /* 1*ownedExpression=ExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _073
				= /* 'null' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(68, /* EssentialOCL::NullLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _074
				= /* 'null' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(68, /* EssentialOCL::NullLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _075
				= /* symbol=NUMBER_LITERAL */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(69, /* EssentialOCL::NumberLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._056 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _076
				= /* symbol=NUMBER_LITERAL */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(69, /* EssentialOCL::NumberLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._056 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _077
				= /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(74, /* EssentialOCL::PatternExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._309 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107 */,
						ms._033 /* assert (|ownedPatternType| - 1) == 0 */,
						ms._094 /* assign V0 = |patternVariableName| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._175 /* V00*patternVariableName=UnrestrictedName */,
						st._009 /* 1*':' */,
						st._126 /* 1*ownedPatternType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _078
				= /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(77, /* EssentialOCL::PrefixedLetExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._307 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 77 */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._073 /* 1*name=UnaryOperatorName */,
						st._128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _079
				= /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(77, /* EssentialOCL::PrefixedLetExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._307 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 77 */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._073 /* 1*name=UnaryOperatorName */,
						st._128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _080
				= /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(78, /* EssentialOCL::PrefixedPrimaryExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._308 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._073 /* 1*name=UnaryOperatorName */,
						st._129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _081
				= /* name=PrimitiveTypeIdentifier */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(81, /* EssentialOCL::PrimitiveTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _082
				= /* name=PrimitiveTypeIdentifier */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(81, /* EssentialOCL::PrimitiveTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _083
				= /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(84, /* EssentialOCL::RoundBracketedClauseCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._310 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64 */,
						ms._057 /* assign V0 = (|ownedArguments| > 0) */,
						ms._103 /* assign V1 = (|ownedArguments| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-5-steps */,
						st._003 /* 1*'(' */,
						st._169 /* V00*next-2-steps */,
						st._080 /* 1*ownedArguments+=NavigatingArgCS */,
						st._182 /* V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _084
				= /* 'self' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(89, /* EssentialOCL::SelfExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _085
				= /* 'self' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(89, /* EssentialOCL::SelfExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _086
				= /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(90, /* EssentialOCL::ShadowPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._311 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74 */,
						ms._022 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._050 /* assert (|referredProperty| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._155 /* 1*referredProperty=UnrestrictedName */,
						st._014 /* 1*'=' */,
						st._101 /* 1*ownedInitExpression=ExpCS|PatternExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _087
				= /* ownedInitExpression=StringLiteralExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(90, /* EssentialOCL::ShadowPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._312 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95 */,
						ms._022 /* assert (|ownedInitExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._102 /* 1*ownedInitExpression=StringLiteralExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _088
				= /* ownedPathElements+=FirstPathElementCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(91, /* EssentialOCL::SimplePathNameCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._246 /* check-rule basecs::PathNameCS.ownedPathElements : 31 */,
						ms._027 /* assert (|ownedPathElements| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._117 /* 1*ownedPathElements+=FirstPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _089
				= /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(93, /* EssentialOCL::SquareBracketedClauseCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._313 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30 */,
						ms._067 /* assign V0 = (|ownedTerms| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._021 /* 1*'[' */,
						st._132 /* 1*ownedTerms+=ExpCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._132 /* 1*ownedTerms+=ExpCS */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _090
				= /* segments+=StringLiteral[+] */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(95, /* EssentialOCL::StringLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._099 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _091
				= /* segments+=StringLiteral[+] */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(95, /* EssentialOCL::StringLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._099 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _092
				= /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(103, /* EssentialOCL::TupleLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
						ms._060 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._020 /* 1*'Tuple' */,
						st._060 /* 1*'{' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _093
				= /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(103, /* EssentialOCL::TupleLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
						ms._060 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._020 /* 1*'Tuple' */,
						st._060 /* 1*'{' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _094
				= /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(104, /* EssentialOCL::TupleLiteralPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
						ms._319 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
						ms._020 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._090 /* assign V0 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */,
						st._014 /* 1*'=' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _095
				= /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(105, /* EssentialOCL::TuplePartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._262 /* check-rule basecs::TypedElementCS.ownedType : 107 */,
						ms._038 /* assert (|ownedType| - 1) == 0 */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._009 /* 1*':' */,
						st._140 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _096
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(106, /* EssentialOCL::TupleTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._061 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _097
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(106, /* EssentialOCL::TupleTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._061 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _098
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
						ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._125 /* assign V1 = |ownedMultiplicity| */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */,
						st._185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _099
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._177 /* assign V3 = |ownedMultiplicity| */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._061 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */,
						st._198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _100
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
						ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._092 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _101
				= /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._277 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
						ms._278 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._098 /* assign V0 = |restVariableName| */,
						ms._109 /* assign V1 = (|ownedParts| - 1) */,
						ms._041 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._135 /* 1*ownedType=CollectionTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-6-steps */,
						st._113 /* 1*ownedParts+=PatternExpCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._113 /* 1*ownedParts+=PatternExpCS */,
						st._006 /* 1*'++' */,
						st._156 /* 1*restVariableName=Identifier */,
						st._063 /* 1*'}' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _102
				= /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._072 /* 1*name=PrimitiveTypeIdentifier */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _103
				= /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
						ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._079 /* assign V0 = |ownedCurlyBracketedClause| */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */,
						ms._126 /* assign V1 = |ownedPatternGuard| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._120 /* 1*ownedPathName=PathNameCS */,
						st._169 /* V00*next-5-steps */,
						st._085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._181 /* V01*next-3-steps */,
						st._060 /* 1*'{' */,
						st._125 /* 1*ownedPatternGuard=ExpCS */,
						st._063 /* 1*'}' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _104
				= /* ownedType=TypeLiteralWithMultiplicityCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(111, /* EssentialOCL::TypeLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
						ms._037 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _105
				= /* ownedType=TypeLiteralWithMultiplicityCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(111, /* EssentialOCL::TypeLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
						ms._037 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _106
				= /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(112, /* EssentialOCL::TypeLiteralWithMultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._072 /* 1*name=PrimitiveTypeIdentifier */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _107
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(112, /* EssentialOCL::TypeLiteralWithMultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
						ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._092 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _108
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(112, /* EssentialOCL::TypeLiteralWithMultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._177 /* assign V3 = |ownedMultiplicity| */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._061 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */,
						st._198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _109
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(112, /* EssentialOCL::TypeLiteralWithMultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
						ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._125 /* assign V1 = |ownedMultiplicity| */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */,
						st._185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _110
				= /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(113, /* EssentialOCL::TypeNameExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
						ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
						ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
						ms._079 /* assign V0 = |ownedCurlyBracketedClause| */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */,
						ms._126 /* assign V1 = |ownedPatternGuard| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._120 /* 1*ownedPathName=PathNameCS */,
						st._169 /* V00*next-5-steps */,
						st._085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._181 /* V01*next-3-steps */,
						st._060 /* 1*'{' */,
						st._125 /* 1*ownedPatternGuard=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _111
				= /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(113, /* EssentialOCL::TypeNameExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
						ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
						ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
						ms._079 /* assign V0 = |ownedCurlyBracketedClause| */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */,
						ms._126 /* assign V1 = |ownedPatternGuard| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._120 /* 1*ownedPathName=PathNameCS */,
						st._169 /* V00*next-5-steps */,
						st._085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._181 /* V01*next-3-steps */,
						st._060 /* 1*'{' */,
						st._125 /* 1*ownedPatternGuard=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _112
				= /* referredElement=UnrestrictedName */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(122, /* EssentialOCL::URIFirstPathElementCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._049 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._151 /* 1*referredElement=UnrestrictedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _113
				= /* referredElement=URI */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(122, /* EssentialOCL::URIFirstPathElementCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._049 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._148 /* 1*referredElement=URI */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _114
				= /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(123, /* EssentialOCL::URIPathNameCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._248 /* check-rule basecs::PathNameCS.ownedPathElements : 67|122 */,
						ms._065 /* assign V0 = (|ownedPathElements| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._119 /* 1*ownedPathElements+=URIFirstPathElementCS */,
						st._169 /* V00*next-2-steps */,
						st._010 /* 1*'::' */,
						st._118 /* 1*ownedPathElements+=NextPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _115
				= /* '*' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(125, /* EssentialOCL::UnlimitedNaturalLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _116
				= /* '*' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(125, /* EssentialOCL::UnlimitedNaturalLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _117
				= /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(1, /* OCLinEcore::AnnotationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
						ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._200 /* assign V5 = |ownedReferences| */,
						ms._188 /* assign V4 = |ownedContents| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._076 /* assign V0 = |name| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._023 /* 1*'annotation' */,
						st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._205 /* V04*ownedContents+=ModelElementCS */,
						st._211 /* V05*ownedReferences+=ModelElementRefCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _118
				= /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(1, /* OCLinEcore::AnnotationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._076 /* assign V0 = |name| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._023 /* 1*'annotation' */,
						st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _119
				= /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(1, /* OCLinEcore::AnnotationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
						ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._200 /* assign V5 = |ownedReferences| */,
						ms._188 /* assign V4 = |ownedContents| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._076 /* assign V0 = |name| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._023 /* 1*'annotation' */,
						st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._205 /* V04*ownedContents+=ModelElementCS */,
						st._211 /* V05*ownedReferences+=ModelElementRefCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _120
				= /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(1, /* OCLinEcore::AnnotationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
						ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._200 /* assign V5 = |ownedReferences| */,
						ms._188 /* assign V4 = |ownedContents| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._076 /* assign V0 = |name| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._023 /* 1*'annotation' */,
						st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._205 /* V04*ownedContents+=ModelElementCS */,
						st._211 /* V05*ownedReferences+=ModelElementRefCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _121
				= /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._113 /* assign V1 = |default| */,
						ms._089 /* assign V0 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._154 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._181 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-4-steps */,
						st._060 /* 1*'{' */,
						st._196 /* V03*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _122
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._156 /* assign V2 = |default| */,
						ms._129 /* assign V1 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-16-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _123
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._156 /* assign V2 = |default| */,
						ms._129 /* assign V1 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						ms._209 /* assign V6 = |ownedDefaultExpressions| */,
						ms._210 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						ms._218 /* assign V8 = 0 */,
						ms._224 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-28-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._213 /* V06*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._220 /* V08*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _124
				= /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._113 /* assign V1 = |default| */,
						ms._089 /* assign V0 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._154 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._181 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						ms._198 /* assign V5 = |ownedDefaultExpressions| */,
						ms._202 /* assign V6 = (|ownedDefaultExpressions| > 0) */,
						ms._214 /* assign V7 = 0 */,
						ms._218 /* assign V8 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-26-steps */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-4-steps */,
						st._060 /* 1*'{' */,
						st._196 /* V03*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._207 /* V05*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._216 /* V06*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._217 /* V07*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._222 /* V08*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _125
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._156 /* assign V2 = |default| */,
						ms._129 /* assign V1 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						ms._209 /* assign V6 = |ownedDefaultExpressions| */,
						ms._210 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						ms._218 /* assign V8 = 0 */,
						ms._224 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-28-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._213 /* V06*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._220 /* V08*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _126
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._156 /* assign V2 = |default| */,
						ms._129 /* assign V1 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-16-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _127
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _128
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						ms._185 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-12-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._201 /* V04*'serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _129
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						ms._185 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-12-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._201 /* V04*'serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _130
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-12-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _131
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _132
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._208 /* assign V6 = |ownedConstraints| */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						ms._185 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._201 /* V04*'serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _133
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-12-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _134
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _135
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._208 /* assign V6 = |ownedConstraints| */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						ms._185 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._201 /* V04*'serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _136
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _137
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _138
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _139
				= /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(16, /* OCLinEcore::DetailCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._101 /* assign V0 = |values| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._075 /* 1*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._014 /* 1*'=' */,
						st._178 /* V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _140
				= /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(17, /* OCLinEcore::DocumentationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._102 /* assign V0 = |value| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._030 /* 1*'documentation' */,
						st._177 /* V00*value=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _141
				= /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(17, /* OCLinEcore::DocumentationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._102 /* assign V0 = |value| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._030 /* 1*'documentation' */,
						st._177 /* V00*value=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _142
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._189 /* assign V4 = |ownedLiterals| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _143
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._189 /* assign V4 = |ownedLiterals| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _144
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
						ms._175 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._195 /* V03*'serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _145
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._189 /* assign V4 = |ownedLiterals| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _146
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._208 /* assign V6 = |ownedConstraints| */,
						ms._199 /* assign V5 = |ownedLiterals| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
						ms._175 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._195 /* V03*'serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _147
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _148
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
						ms._175 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._195 /* V03*'serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _149
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _150
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._208 /* assign V6 = |ownedConstraints| */,
						ms._199 /* assign V5 = |ownedLiterals| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
						ms._175 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._195 /* V03*'serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _151
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _152
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _153
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._189 /* assign V4 = |ownedLiterals| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _154
				= /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(22, /* OCLinEcore::EnumerationLiteralCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._158 /* assign V2 = |ownedAnnotations| */,
						ms._133 /* assign V1 = |value| */,
						ms._075 /* assign V0 = |literal| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._044 /* 1*'literal' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._161 /* 1*value=SIGNED */,
						st._060 /* 1*'{' */,
						st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _155
				= /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(22, /* OCLinEcore::EnumerationLiteralCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._133 /* assign V1 = |value| */,
						ms._075 /* assign V0 = |literal| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._044 /* 1*'literal' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._161 /* 1*value=SIGNED */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _156
				= /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(22, /* OCLinEcore::EnumerationLiteralCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._133 /* assign V1 = |value| */,
						ms._075 /* assign V0 = |literal| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._071 /* 1*name=EnumerationLiteralName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._161 /* 1*value=SIGNED */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _157
				= /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(22, /* OCLinEcore::EnumerationLiteralCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._158 /* assign V2 = |ownedAnnotations| */,
						ms._133 /* assign V1 = |value| */,
						ms._075 /* assign V0 = |literal| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._071 /* 1*name=EnumerationLiteralName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._161 /* 1*value=SIGNED */,
						st._060 /* 1*'{' */,
						st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _158
				= /* { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(37, /* OCLinEcore::ImplicitOppositeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._038 /* assert (|ownedType| - 1) == 0 */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._069 /* assign V0 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
						ms._131 /* assign V1 = |qualifiers.'!ordered|!unique|ordered|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._048 /* 1*'opposite' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._169 /* V00*next-4-steps */,
						st._060 /* 1*'{' */,
						st._181 /* V01*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _159
				= /* { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(38, /* OCLinEcore::ImportCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._236 /* check-rule basecs::ImportCS.ownedPathName : 123 */,
						ms._115 /* assign V1 = |isAll.'::*'| */,
						ms._029 /* assert (|ownedPathName| - 1) == 0 */,
						ms._076 /* assign V0 = |name| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._037 /* 1*'import' */,
						st._169 /* V00*next-2-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._009 /* 1*':' */,
						st._124 /* 1*ownedPathName=URIPathNameCS */,
						st._179 /* V01*'::*' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _160
				= /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(41, /* OCLinEcore::InvariantConstraintCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
						ms._117 /* assign V1 = |name| */,
						ms._051 /* assert (|stereotype.'invariant'| - 1) == 0 */,
						ms._072 /* assign V0 = |isCallable.'callable'| */,
						ms._160 /* assign V2 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._163 /* V00*'callable' */,
						st._041 /* 1*'invariant' */,
						st._181 /* V01*next-5-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._189 /* V02*next-3-steps */,
						st._003 /* 1*'(' */,
						st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
						st._004 /* 1*')' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _161
				= /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(41, /* OCLinEcore::InvariantConstraintCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
						ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
						ms._179 /* assign V3 = |ownedSpecification| */,
						ms._117 /* assign V1 = |name| */,
						ms._051 /* assert (|stereotype.'invariant'| - 1) == 0 */,
						ms._072 /* assign V0 = |isCallable.'callable'| */,
						ms._160 /* assign V2 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._163 /* V00*'callable' */,
						st._041 /* 1*'invariant' */,
						st._181 /* V01*next-5-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._189 /* V02*next-3-steps */,
						st._003 /* 1*'(' */,
						st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
						st._004 /* 1*')' */,
						st._009 /* 1*':' */,
						st._200 /* V03*ownedSpecification=SpecificationCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _162
				= /* { 'reference' ownedPathName=PathNameCS ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(54, /* OCLinEcore::ModelElementRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._238 /* check-rule basecs::ModelElementRefCS.ownedPathName : 73 */,
						ms._032 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._054 /* 1*'reference' */,
						st._121 /* 1*ownedPathName=PathNameCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _163
				= /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
						ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._145 /* assign V12 = |ownedPostconditions| */,
						ms._227 /* assign V9 = |ownedPreconditions| */,
						ms._219 /* assign V8 = |ownedAnnotations| */,
						ms._180 /* assign V3 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._134 /* assign V10 = (|ownedBodyExpressions| > 0) */,
						ms._141 /* assign V11 = |ownedBodyExpressions| */,
						ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
						ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
						ms._106 /* assign V1 = (|ownedParameters| > 0) */,
						ms._150 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-34-steps */,
						st._047 /* 1*'operation' */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._196 /* V03*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._202 /* V04*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._207 /* V05*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-4-steps */,
						st._060 /* 1*'{' */,
						st._217 /* V07*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._221 /* V08*ownedAnnotations+=AnnotationElementCS */,
						st._227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
						st._229 /* V10*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._234 /* V11*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _164
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
						ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._147 /* assign V13 = |ownedPostconditions| */,
						ms._138 /* assign V10 = |ownedPreconditions| */,
						ms._225 /* assign V9 = |ownedAnnotations| */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						ms._143 /* assign V12 = |ownedBodyExpressions| */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-36-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						st._233 /* V11*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _165
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-27-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _166
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-27-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _167
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
						ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._147 /* assign V13 = |ownedPostconditions| */,
						ms._138 /* assign V10 = |ownedPreconditions| */,
						ms._225 /* assign V9 = |ownedAnnotations| */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						ms._143 /* assign V12 = |ownedBodyExpressions| */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-36-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						st._233 /* V11*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _168
				= /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._180 /* assign V3 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
						ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
						ms._106 /* assign V1 = (|ownedParameters| > 0) */,
						ms._150 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-25-steps */,
						st._047 /* 1*'operation' */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._196 /* V03*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._202 /* V04*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._207 /* V05*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-4-steps */,
						st._060 /* 1*'{' */,
						st._217 /* V07*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _169
				= /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._180 /* assign V3 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
						ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
						ms._106 /* assign V1 = (|ownedParameters| > 0) */,
						ms._150 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-25-steps */,
						st._047 /* 1*'operation' */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._196 /* V03*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._202 /* V04*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._207 /* V05*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-4-steps */,
						st._060 /* 1*'{' */,
						st._217 /* V07*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _170
				= /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
						ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._145 /* assign V12 = |ownedPostconditions| */,
						ms._227 /* assign V9 = |ownedPreconditions| */,
						ms._219 /* assign V8 = |ownedAnnotations| */,
						ms._180 /* assign V3 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._134 /* assign V10 = (|ownedBodyExpressions| > 0) */,
						ms._141 /* assign V11 = |ownedBodyExpressions| */,
						ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
						ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
						ms._106 /* assign V1 = (|ownedParameters| > 0) */,
						ms._150 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-34-steps */,
						st._047 /* 1*'operation' */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._196 /* V03*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._202 /* V04*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._207 /* V05*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-4-steps */,
						st._060 /* 1*'{' */,
						st._217 /* V07*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._221 /* V08*ownedAnnotations+=AnnotationElementCS */,
						st._227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
						st._229 /* V10*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._234 /* V11*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _171
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
						ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._147 /* assign V13 = |ownedPostconditions| */,
						ms._138 /* assign V10 = |ownedPreconditions| */,
						ms._225 /* assign V9 = |ownedAnnotations| */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						ms._143 /* assign V12 = |ownedBodyExpressions| */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-36-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						st._233 /* V11*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _172
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-27-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _173
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
						ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._147 /* assign V13 = |ownedPostconditions| */,
						ms._138 /* assign V10 = |ownedPreconditions| */,
						ms._225 /* assign V9 = |ownedAnnotations| */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						ms._143 /* assign V12 = |ownedBodyExpressions| */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-36-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						st._233 /* V11*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _174
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
						ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-27-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _175
				= /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(71, /* OCLinEcore::PackageCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
						ms._244 /* check-rule basecs::PackageCS.ownedClasses : 6 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._187 /* assign V4 = |ownedClasses| */,
						ms._178 /* assign V3 = |ownedPackages| */,
						ms._158 /* assign V2 = |ownedAnnotations| */,
						ms._118 /* assign V1 = |nsURI| */,
						ms._077 /* assign V0 = |nsPrefix| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-13-steps */,
						st._049 /* 1*'package' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._077 /* 1*nsPrefix=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._078 /* 1*nsURI=URI */,
						st._060 /* 1*'{' */,
						st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						st._199 /* V03*ownedPackages+=PackageCS */,
						st._204 /* V04*ownedClasses+=ClassCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _176
				= /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(71, /* OCLinEcore::PackageCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._118 /* assign V1 = |nsURI| */,
						ms._077 /* assign V0 = |nsPrefix| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._049 /* 1*'package' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._077 /* 1*nsPrefix=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._078 /* 1*nsURI=URI */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _177
				= /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(71, /* OCLinEcore::PackageCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._118 /* assign V1 = |nsURI| */,
						ms._077 /* assign V0 = |nsPrefix| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._049 /* 1*'package' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._077 /* 1*nsPrefix=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._078 /* 1*nsURI=URI */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _178
				= /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(71, /* OCLinEcore::PackageCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
						ms._244 /* check-rule basecs::PackageCS.ownedClasses : 6 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._187 /* assign V4 = |ownedClasses| */,
						ms._178 /* assign V3 = |ownedPackages| */,
						ms._158 /* assign V2 = |ownedAnnotations| */,
						ms._118 /* assign V1 = |nsURI| */,
						ms._077 /* assign V0 = |nsPrefix| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-13-steps */,
						st._049 /* 1*'package' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._077 /* 1*nsPrefix=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._078 /* 1*nsURI=URI */,
						st._060 /* 1*'{' */,
						st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						st._199 /* V03*ownedPackages+=PackageCS */,
						st._204 /* V04*ownedClasses+=ClassCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _179
				= /* { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(72, /* OCLinEcore::ParameterCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._089 /* assign V0 = |ownedType| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._167 /* assign V3 = (|ownedAnnotations| > 0) */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._112 /* assign V1 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
						ms._165 /* assign V2 = |qualifiers.'!ordered|!unique|ordered|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-13-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._181 /* V01*next-4-steps */,
						st._060 /* 1*'{' */,
						st._189 /* V02*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _180
				= /* { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(75, /* OCLinEcore::PostconditionConstraintCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
						ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
						ms._163 /* assign V2 = |ownedSpecification| */,
						ms._076 /* assign V0 = |name| */,
						ms._052 /* assert (|stereotype.'postcondition'| - 1) == 0 */,
						ms._123 /* assign V1 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._050 /* 1*'postcondition' */,
						st._169 /* V00*next-5-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-3-steps */,
						st._003 /* 1*'(' */,
						st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
						st._004 /* 1*')' */,
						st._009 /* 1*':' */,
						st._194 /* V02*ownedSpecification=SpecificationCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _181
				= /* { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(76, /* OCLinEcore::PreconditionConstraintCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
						ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
						ms._163 /* assign V2 = |ownedSpecification| */,
						ms._076 /* assign V0 = |name| */,
						ms._053 /* assert (|stereotype.'precondition'| - 1) == 0 */,
						ms._123 /* assign V1 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._052 /* 1*'precondition' */,
						st._169 /* V00*next-5-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-3-steps */,
						st._003 /* 1*'(' */,
						st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
						st._004 /* 1*')' */,
						st._009 /* 1*':' */,
						st._194 /* V02*ownedSpecification=SpecificationCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _182
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-19-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _183
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
						ms._146 /* assign V13 = |ownedImplicitOpposites| */,
						ms._207 /* assign V6 = |ownedAnnotations| */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._213 /* assign V7 = (|referredKeys| > 0) */,
						ms._217 /* assign V8 = (|referredKeys| - 1) */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._226 /* assign V9 = |ownedDefaultExpressions| */,
						ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						ms._140 /* assign V11 = 0 */,
						ms._142 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-41-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						st._217 /* V07*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._220 /* V08*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._224 /* V09*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._233 /* V11*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._240 /* V13*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _184
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-19-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _185
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-19-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _186
				= /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._156 /* assign V2 = |default| */,
						ms._129 /* assign V1 = |ownedType| */,
						ms._097 /* assign V0 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-17-steps */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _187
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-19-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _188
				= /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
						ms._144 /* assign V12 = |ownedImplicitOpposites| */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._156 /* assign V2 = |default| */,
						ms._129 /* assign V1 = |ownedType| */,
						ms._097 /* assign V0 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._205 /* assign V6 = (|referredKeys| > 0) */,
						ms._212 /* assign V7 = (|referredKeys| - 1) */,
						ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._220 /* assign V8 = |ownedDefaultExpressions| */,
						ms._223 /* assign V9 = (|ownedDefaultExpressions| > 0) */,
						ms._136 /* assign V10 = 0 */,
						ms._140 /* assign V11 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-39-steps */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._213 /* V06*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._217 /* V07*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._220 /* V08*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._229 /* V10*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._236 /* V12*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _189
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
						ms._146 /* assign V13 = |ownedImplicitOpposites| */,
						ms._207 /* assign V6 = |ownedAnnotations| */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._213 /* assign V7 = (|referredKeys| > 0) */,
						ms._217 /* assign V8 = (|referredKeys| - 1) */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._226 /* assign V9 = |ownedDefaultExpressions| */,
						ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						ms._140 /* assign V11 = 0 */,
						ms._142 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-41-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						st._217 /* V07*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._220 /* V08*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._224 /* V09*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._233 /* V11*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._240 /* V13*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _190
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
						ms._146 /* assign V13 = |ownedImplicitOpposites| */,
						ms._207 /* assign V6 = |ownedAnnotations| */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._213 /* assign V7 = (|referredKeys| > 0) */,
						ms._217 /* assign V8 = (|referredKeys| - 1) */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._226 /* assign V9 = |ownedDefaultExpressions| */,
						ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						ms._140 /* assign V11 = 0 */,
						ms._142 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-41-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						st._217 /* V07*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._220 /* V08*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._224 /* V09*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._233 /* V11*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._240 /* V13*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _191
				= /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._156 /* assign V2 = |default| */,
						ms._129 /* assign V1 = |ownedType| */,
						ms._097 /* assign V0 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-17-steps */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _192
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
						ms._146 /* assign V13 = |ownedImplicitOpposites| */,
						ms._207 /* assign V6 = |ownedAnnotations| */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._213 /* assign V7 = (|referredKeys| > 0) */,
						ms._217 /* assign V8 = (|referredKeys| - 1) */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._226 /* assign V9 = |ownedDefaultExpressions| */,
						ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						ms._140 /* assign V11 = 0 */,
						ms._142 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-41-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						st._217 /* V07*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._220 /* V08*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._224 /* V09*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._233 /* V11*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._240 /* V13*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _193
				= /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
						ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
						ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
						ms._144 /* assign V12 = |ownedImplicitOpposites| */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._156 /* assign V2 = |default| */,
						ms._129 /* assign V1 = |ownedType| */,
						ms._097 /* assign V0 = |referredOpposite| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._205 /* assign V6 = (|referredKeys| > 0) */,
						ms._212 /* assign V7 = (|referredKeys| - 1) */,
						ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._220 /* assign V8 = |ownedDefaultExpressions| */,
						ms._223 /* assign V9 = (|ownedDefaultExpressions| > 0) */,
						ms._136 /* assign V10 = 0 */,
						ms._140 /* assign V11 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-39-steps */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._213 /* V06*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._217 /* V07*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._220 /* V08*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._229 /* V10*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._236 /* V12*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _194
				= /* ownedExpression=ExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(92, /* OCLinEcore::SpecificationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._283 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30 */,
						ms._015 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._090 /* 1*ownedExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _195
				= /* exprString=UNQUOTED_STRING */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(92, /* OCLinEcore::SpecificationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._000 /* assert (|exprString| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._065 /* 1*exprString=UNQUOTED_STRING */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _196
				= /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(97, /* OCLinEcore::StructuredClassCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._253 /* check-rule basecs::StructuredClassCS.ownedProperties : 96 */,
						ms._252 /* check-rule basecs::StructuredClassCS.ownedOperations : 70 */,
						ms._137 /* assign V10 = |ownedConstraints| */,
						ms._228 /* assign V9 = |ownedProperties| */,
						ms._221 /* assign V8 = |ownedOperations| */,
						ms._215 /* assign V7 = |ownedAnnotations| */,
						ms._184 /* assign V4 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._071 /* assign V0 = |isAbstract.'abstract'| */,
						ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
						ms._206 /* assign V6 = |isInterface.'interface'| */,
						ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
						ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-23-steps */,
						st._162 /* V00*'abstract' */,
						st._026 /* 1*'class' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._207 /* V05*next-3-steps */,
						st._060 /* 1*'{' */,
						st._212 /* V06*'interface' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						st._223 /* V08*ownedOperations+=OperationCS */,
						st._228 /* V09*ownedProperties+=StructuralFeatureCS */,
						st._230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _197
				= /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(97, /* OCLinEcore::StructuredClassCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._184 /* assign V4 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._071 /* assign V0 = |isAbstract.'abstract'| */,
						ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
						ms._206 /* assign V6 = |isInterface.'interface'| */,
						ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
						ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-18-steps */,
						st._162 /* V00*'abstract' */,
						st._026 /* 1*'class' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._207 /* V05*next-3-steps */,
						st._060 /* 1*'{' */,
						st._212 /* V06*'interface' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _198
				= /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(97, /* OCLinEcore::StructuredClassCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
						ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
						ms._253 /* check-rule basecs::StructuredClassCS.ownedProperties : 96 */,
						ms._252 /* check-rule basecs::StructuredClassCS.ownedOperations : 70 */,
						ms._137 /* assign V10 = |ownedConstraints| */,
						ms._228 /* assign V9 = |ownedProperties| */,
						ms._221 /* assign V8 = |ownedOperations| */,
						ms._215 /* assign V7 = |ownedAnnotations| */,
						ms._184 /* assign V4 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._071 /* assign V0 = |isAbstract.'abstract'| */,
						ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
						ms._206 /* assign V6 = |isInterface.'interface'| */,
						ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
						ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-23-steps */,
						st._162 /* V00*'abstract' */,
						st._026 /* 1*'class' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._207 /* V05*next-3-steps */,
						st._060 /* 1*'{' */,
						st._212 /* V06*'interface' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						st._223 /* V08*ownedOperations+=OperationCS */,
						st._228 /* V09*ownedProperties+=StructuralFeatureCS */,
						st._230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _199
				= /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(97, /* OCLinEcore::StructuredClassCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
						ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
						ms._184 /* assign V4 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._006 /* assert (|name| - 1) == 0 */,
						ms._071 /* assign V0 = |isAbstract.'abstract'| */,
						ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
						ms._206 /* assign V6 = |isInterface.'interface'| */,
						ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
						ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-18-steps */,
						st._162 /* V00*'abstract' */,
						st._026 /* 1*'class' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._207 /* V05*next-3-steps */,
						st._060 /* 1*'{' */,
						st._212 /* V06*'interface' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _200
				= /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(98, /* OCLinEcore::SysMLCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._080 /* assign V0 = |ownedDetails| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._057 /* 1*'sysml' */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-2-steps */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _201
				= /* { 'sysml' ownedDetails+=DetailCS ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(98, /* OCLinEcore::SysMLCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._012 /* assert (|ownedDetails| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._057 /* 1*'sysml' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _202
				= /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(98, /* OCLinEcore::SysMLCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._080 /* assign V0 = |ownedDetails| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._057 /* 1*'sysml' */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-2-steps */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _203
				= /* { 'sysml' ownedDetails+=DetailCS ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(98, /* OCLinEcore::SysMLCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
						ms._012 /* assert (|ownedDetails| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._057 /* 1*'sysml' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ SerializationRule _204
				= /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(101, /* OCLinEcore::TemplateSignatureCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._258 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
						ms._059 /* assign V0 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._012 /* 1*'<' */,
						st._110 /* 1*ownedParameters+=TypeParameterCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._110 /* 1*ownedParameters+=TypeParameterCS */,
						st._015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _205
				= /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(101, /* OCLinEcore::TemplateSignatureCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._258 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
						ms._059 /* assign V0 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._003 /* 1*'(' */,
						st._110 /* 1*ownedParameters+=TypeParameterCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._110 /* 1*ownedParameters+=TypeParameterCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _206
				= /* { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(102, /* OCLinEcore::TopLevelCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
						ms._250 /* check-rule basecs::RootCS.ownedImports : 38 */,
						ms._162 /* assign V2 = |ownedPackages| */,
						ms._121 /* assign V1 = |ownedImports| */,
						ms._070 /* assign V0 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._169 /* V00*next-1-steps */,
						st._045 /* 1*'module' */,
						st._184 /* V01*ownedImports+=ImportCS */,
						st._193 /* V02*ownedPackages+=PackageCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _207
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
						ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._092 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _208
				= /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _209
				= /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
						ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-5-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._012 /* 1*'<' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._015 /* 1*'>' */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _210
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
						ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._125 /* assign V1 = |ownedMultiplicity| */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */,
						st._185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _211
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._177 /* assign V3 = |ownedMultiplicity| */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._061 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */,
						st._198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _212
				= /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._072 /* 1*name=PrimitiveTypeIdentifier */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _213
				= /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
						ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
						ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-5-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._003 /* 1*'(' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._004 /* 1*')' */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _214
				= /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
						ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._012 /* 1*'<' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _215
				= /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
						ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._012 /* 1*'<' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ SerializationRule _216
				= /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
						ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._003 /* 1*'(' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ SerializationRule _217
				= /* ownedPathName=PathNameCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._122 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _218
				= /* ownedPathName=PathNameCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._122 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ SerializationRule _219
				= /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
						ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._003 /* 1*'(' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
		}

		private _EnumValues ev;
		private _MatchTerms mt;
		private _MatchSteps ms;
		private _SerializationTerms st;
		private _SerializationSegments ss;
		private _SerializationRules sr;
		private _RuleValues rv;
		private _EClassData ec;

		@Inject
		public void init() {
		//	Grammar grammar = grammarProvider.getGrammar(this);
			ev = new _EnumValues();
			mt = new _MatchTerms();
			ms = new _MatchSteps();
			st = new _SerializationTerms();
			ss = new _SerializationSegments();
			sr = new _SerializationRules();
			rv = new _RuleValues();
			ec = new _EClassData();
			st.init();
		}
	}
