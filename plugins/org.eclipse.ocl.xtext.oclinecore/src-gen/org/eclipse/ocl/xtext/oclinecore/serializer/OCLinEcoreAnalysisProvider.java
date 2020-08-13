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
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis;

public class OCLinEcoreAnalysisProvider extends AbstractAnalysisProvider
{
	private static RTGrammarAnalysis analysis = null;

	@Override
	public RTGrammarAnalysis getAnalysis() {
		if (analysis == null) {
			analysis = new RTGrammarAnalysis();
		}
		analysis.addSerializationRules(_Rules.create_AnnotationCS_Rules());
		analysis.addSerializationRules(_Rules.create_AttributeCS_Rules());
		analysis.addSerializationRules(_Rules.create_BooleanLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_CollectionLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_CollectionLiteralPartCS_Rules());
		analysis.addSerializationRules(_Rules.create_CollectionPatternCS_Rules());
		analysis.addSerializationRules(_Rules.create_CollectionTypeCS_Rules());
		analysis.addSerializationRules(_Rules.create_ContextCS_Rules());
		analysis.addSerializationRules(_Rules.create_CurlyBracketedClauseCS_Rules());
		analysis.addSerializationRules(_Rules.create_DataTypeCS_Rules());
		analysis.addSerializationRules(_Rules.create_DetailCS_Rules());
		analysis.addSerializationRules(_Rules.create_DocumentationCS_Rules());
		analysis.addSerializationRules(_Rules.create_EnumerationCS_Rules());
		analysis.addSerializationRules(_Rules.create_EnumerationLiteralCS_Rules());
		analysis.addSerializationRules(_Rules.create_ExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_ExpSpecificationCS_Rules());
		analysis.addSerializationRules(_Rules.create_IfExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_IfThenExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_ImplicitOppositeCS_Rules());
		analysis.addSerializationRules(_Rules.create_ImportCS_Rules());
		analysis.addSerializationRules(_Rules.create_InfixExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_InvalidLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_LambdaLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_LetExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_LetVariableCS_Rules());
		analysis.addSerializationRules(_Rules.create_MapLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_MapLiteralPartCS_Rules());
		analysis.addSerializationRules(_Rules.create_MapTypeCS_Rules());
		analysis.addSerializationRules(_Rules.create_ModelElementRefCS_Rules());
		analysis.addSerializationRules(_Rules.create_MultiplicityBoundsCS_Rules());
		analysis.addSerializationRules(_Rules.create_MultiplicityStringCS_Rules());
		analysis.addSerializationRules(_Rules.create_NameExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_NavigatingArgCS_Rules());
		analysis.addSerializationRules(_Rules.create_NestedExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_NullLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_NumberLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_OCLinEcoreConstraintCS_Rules());
		analysis.addSerializationRules(_Rules.create_OperationCS_Rules());
		analysis.addSerializationRules(_Rules.create_PackageCS_Rules());
		analysis.addSerializationRules(_Rules.create_ParameterCS_Rules());
		analysis.addSerializationRules(_Rules.create_PathElementCS_Rules());
		analysis.addSerializationRules(_Rules.create_PathElementWithURICS_Rules());
		analysis.addSerializationRules(_Rules.create_PathNameCS_Rules());
		analysis.addSerializationRules(_Rules.create_PatternExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_PrefixExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_PrimitiveLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_PrimitiveTypeRefCS_Rules());
		analysis.addSerializationRules(_Rules.create_ReferenceCS_Rules());
		analysis.addSerializationRules(_Rules.create_RoundBracketedClauseCS_Rules());
		analysis.addSerializationRules(_Rules.create_SelfExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_ShadowPartCS_Rules());
		analysis.addSerializationRules(_Rules.create_SquareBracketedClauseCS_Rules());
		analysis.addSerializationRules(_Rules.create_StringLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_StructuredClassCS_Rules());
		analysis.addSerializationRules(_Rules.create_SysMLCS_Rules());
		analysis.addSerializationRules(_Rules.create_TemplateBindingCS_Rules());
		analysis.addSerializationRules(_Rules.create_TemplateParameterSubstitutionCS_Rules());
		analysis.addSerializationRules(_Rules.create_TemplateSignatureCS_Rules());
		analysis.addSerializationRules(_Rules.create_TopLevelCS_Rules());
		analysis.addSerializationRules(_Rules.create_TupleLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_TupleLiteralPartCS_Rules());
		analysis.addSerializationRules(_Rules.create_TuplePartCS_Rules());
		analysis.addSerializationRules(_Rules.create_TupleTypeCS_Rules());
		analysis.addSerializationRules(_Rules.create_TypeLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_TypeNameExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_TypeParameterCS_Rules());
		analysis.addSerializationRules(_Rules.create_TypedTypeRefCS_Rules());
		analysis.addSerializationRules(_Rules.create_UnlimitedNaturalLiteralExpCS_Rules());
		analysis.addSerializationRules(_Rules.create_VariableCS_Rules());
		analysis.addSerializationRules(_Rules.create_WildcardTypeRefCS_Rules());
		return analysis;
	}

	private static class _Steps
	{
		private static final /* @@NonNull*/ RTSerializationStep Step0000 // 1*'!serializable'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "!serializable");
		private static final /* @@NonNull*/ RTSerializationStep Step0001 // 1*'#'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "#");
		private static final /* @@NonNull*/ RTSerializationStep Step0002 // 1*'&&'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "&&");
		private static final /* @@NonNull*/ RTSerializationStep Step0003 // 1*'('
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "(");
		private static final /* @@NonNull*/ RTSerializationStep Step0004 // 1*')'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ")");
		private static final /* @@NonNull*/ RTSerializationStep Step0005 // 1*'*'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "*");
		private static final /* @@NonNull*/ RTSerializationStep Step0006 // 1*'++'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "++");
		private static final /* @@NonNull*/ RTSerializationStep Step0007 // 1*','
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ",");
		private static final /* @@NonNull*/ RTSerializationStep Step0008 // 1*'..'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "..");
		private static final /* @@NonNull*/ RTSerializationStep Step0009 // 1*':'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ":");
		private static final /* @@NonNull*/ RTSerializationStep Step0010 // 1*'::'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "::");
		private static final /* @@NonNull*/ RTSerializationStep Step0011 // 1*';'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ";");
		private static final /* @@NonNull*/ RTSerializationStep Step0012 // 1*'<'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "<");
		private static final /* @@NonNull*/ RTSerializationStep Step0013 // 1*'<-'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "<-");
		private static final /* @@NonNull*/ RTSerializationStep Step0014 // 1*'='
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "=");
		private static final /* @@NonNull*/ RTSerializationStep Step0015 // 1*'>'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ">");
		private static final /* @@NonNull*/ RTSerializationStep Step0016 // 1*'?'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "?");
		private static final /* @@NonNull*/ RTSerializationStep Step0017 // 1*'@'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "@");
		private static final /* @@NonNull*/ RTSerializationStep Step0018 // 1*'Lambda'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Lambda");
		private static final /* @@NonNull*/ RTSerializationStep Step0019 // 1*'Map'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Map");
		private static final /* @@NonNull*/ RTSerializationStep Step0020 // 1*'Tuple'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Tuple");
		private static final /* @@NonNull*/ RTSerializationStep Step0021 // 1*'['
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "[");
		private static final /* @@NonNull*/ RTSerializationStep Step0022 // 1*']'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "]");
		private static final /* @@NonNull*/ RTSerializationStep Step0023 // 1*'annotation'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "annotation");
		private static final /* @@NonNull*/ RTSerializationStep Step0024 // 1*'attribute'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "attribute");
		private static final /* @@NonNull*/ RTSerializationStep Step0025 // 1*'body'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "body");
		private static final /* @@NonNull*/ RTSerializationStep Step0026 // 1*'class'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "class");
		private static final /* @@NonNull*/ RTSerializationStep Step0027 // 1*'datatype'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "datatype");
		private static final /* @@NonNull*/ RTSerializationStep Step0028 // 1*'definition'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "definition");
		private static final /* @@NonNull*/ RTSerializationStep Step0029 // 1*'derivation'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "derivation");
		private static final /* @@NonNull*/ RTSerializationStep Step0030 // 1*'documentation'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "documentation");
		private static final /* @@NonNull*/ RTSerializationStep Step0031 // 1*'else'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "else");
		private static final /* @@NonNull*/ RTSerializationStep Step0032 // 1*'elseif'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "elseif");
		private static final /* @@NonNull*/ RTSerializationStep Step0033 // 1*'endif'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "endif");
		private static final /* @@NonNull*/ RTSerializationStep Step0034 // 1*'enum'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "enum");
		private static final /* @@NonNull*/ RTSerializationStep Step0035 // 1*'extends'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "extends");
		private static final /* @@NonNull*/ RTSerializationStep Step0036 // 1*'if'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "if");
		private static final /* @@NonNull*/ RTSerializationStep Step0037 // 1*'import'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "import");
		private static final /* @@NonNull*/ RTSerializationStep Step0038 // 1*'in'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "in");
		private static final /* @@NonNull*/ RTSerializationStep Step0039 // 1*'initial'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "initial");
		private static final /* @@NonNull*/ RTSerializationStep Step0040 // 1*'invalid'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "invalid");
		private static final /* @@NonNull*/ RTSerializationStep Step0041 // 1*'invariant'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "invariant");
		private static final /* @@NonNull*/ RTSerializationStep Step0042 // 1*'key'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "key");
		private static final /* @@NonNull*/ RTSerializationStep Step0043 // 1*'let'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "let");
		private static final /* @@NonNull*/ RTSerializationStep Step0044 // 1*'literal'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "literal");
		private static final /* @@NonNull*/ RTSerializationStep Step0045 // 1*'module'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "module");
		private static final /* @@NonNull*/ RTSerializationStep Step0046 // 1*'null'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "null");
		private static final /* @@NonNull*/ RTSerializationStep Step0047 // 1*'operation'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "operation");
		private static final /* @@NonNull*/ RTSerializationStep Step0048 // 1*'opposite'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "opposite");
		private static final /* @@NonNull*/ RTSerializationStep Step0049 // 1*'package'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "package");
		private static final /* @@NonNull*/ RTSerializationStep Step0050 // 1*'postcondition'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "postcondition");
		private static final /* @@NonNull*/ RTSerializationStep Step0051 // 1*'pre'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "pre");
		private static final /* @@NonNull*/ RTSerializationStep Step0052 // 1*'precondition'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "precondition");
		private static final /* @@NonNull*/ RTSerializationStep Step0053 // 1*'property'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "property");
		private static final /* @@NonNull*/ RTSerializationStep Step0054 // 1*'reference'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "reference");
		private static final /* @@NonNull*/ RTSerializationStep Step0055 // 1*'self'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "self");
		private static final /* @@NonNull*/ RTSerializationStep Step0056 // 1*'static'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "static");
		private static final /* @@NonNull*/ RTSerializationStep Step0057 // 1*'sysml'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "sysml");
		private static final /* @@NonNull*/ RTSerializationStep Step0058 // 1*'then'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "then");
		private static final /* @@NonNull*/ RTSerializationStep Step0059 // 1*'throws'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "throws");
		private static final /* @@NonNull*/ RTSerializationStep Step0060 // 1*'{'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "{");
		private static final /* @@NonNull*/ RTSerializationStep Step0061 // 1*'|'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "|");
		private static final /* @@NonNull*/ RTSerializationStep Step0062 // 1*'|?'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "|?");
		private static final /* @@NonNull*/ RTSerializationStep Step0063 // 1*'}'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "}");
		private static final /* @@NonNull*/ RTSerializationStep Step0064 // 1*default=SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step0065 // 1*exprString=UNQUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, "OCLinEcore::UNQUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step0066 // 1*instanceClassName=SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step0067 // 1*literal=SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step0068 // 1*lowerBound=LOWER
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, "Base::LOWER");
		private static final /* @@NonNull*/ RTSerializationStep Step0069 // 1*name=BinaryOperatorName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, "EssentialOCL::BinaryOperatorName");
		private static final /* @@NonNull*/ RTSerializationStep Step0070 // 1*name=CollectionTypeIdentifier
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, "EssentialOCL::CollectionTypeIdentifier");
		private static final /* @@NonNull*/ RTSerializationStep Step0071 // 1*name=EnumerationLiteralName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, "OCLinEcore::EnumerationLiteralName");
		private static final /* @@NonNull*/ RTSerializationStep Step0072 // 1*name=PrimitiveTypeIdentifier
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, "EssentialOCL::PrimitiveTypeIdentifier");
		private static final /* @@NonNull*/ RTSerializationStep Step0073 // 1*name=UnaryOperatorName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, "EssentialOCL::UnaryOperatorName");
		private static final /* @@NonNull*/ RTSerializationStep Step0074 // 1*name=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, "OCLinEcore::UnrestrictedName");
		private static final /* @@NonNull*/ RTSerializationStep Step0075 // 1*name=UnrestrictedName|SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME , "OCLinEcore::UnrestrictedName", "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step0076 // 1*next-10-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(-1, 1, 11);
		private static final /* @@NonNull*/ RTSerializationStep Step0077 // 1*nsPrefix=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, "OCLinEcore::UnrestrictedName");
		private static final /* @@NonNull*/ RTSerializationStep Step0078 // 1*nsURI=URI
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_URI, "Base::URI");
		private static final /* @@NonNull*/ RTSerializationStep Step0079 // 1*ownedActualParameter=TypeRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, "Base::TypeRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0080 // 1*ownedArguments+=NavigatingArgCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, "EssentialOCL::NavigatingArgCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0081 // 1*ownedBinding=TemplateBindingCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, "Base::TemplateBindingCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0082 // 1*ownedCoIterator=CoIteratorVariableCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, "EssentialOCL::CoIteratorVariableCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0083 // 1*ownedCondition=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0084 // 1*ownedCondition=ExpCS|PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION , "EssentialOCL::ExpCS", "EssentialOCL::PatternExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0085 // 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, "EssentialOCL::CurlyBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0086 // 1*ownedDetails+=DetailCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, "OCLinEcore::DetailCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0087 // 1*ownedElseExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0088 // 1*ownedExceptions+=TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, "OCLinEcore::TypedRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0089 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0090 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0091 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0092 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0093 // 1*ownedExpression=PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, "EssentialOCL::PatternExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0094 // 1*ownedExpressionCS=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0095 // 1*ownedExtends+=TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, "OCLinEcore::TypedRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0096 // 1*ownedExtends=TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, "OCLinEcore::TypedRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0097 // 1*ownedImplicitOpposites+=ImplicitOppositeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, "OCLinEcore::ImplicitOppositeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0098 // 1*ownedInExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0099 // 1*ownedInitExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0100 // 1*ownedInitExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0101 // 1*ownedInitExpression=ExpCS|PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION , "EssentialOCL::ExpCS", "EssentialOCL::PatternExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0102 // 1*ownedInitExpression=StringLiteralExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, "EssentialOCL::StringLiteralExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0103 // 1*ownedKey=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0104 // 1*ownedKeyType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0105 // 1*ownedLastExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0106 // 1*ownedLeft=PrefixedPrimaryExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, "EssentialOCL::PrefixedPrimaryExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0107 // 1*ownedMessageSpecification=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0108 // 1*ownedNameExpression=NavigatingArgExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, "EssentialOCL::NavigatingArgExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0109 // 1*ownedParameters+=ParameterCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, "OCLinEcore::ParameterCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0110 // 1*ownedParameters+=TypeParameterCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, "Base::TypeParameterCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0111 // 1*ownedParts+=CollectionLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, "EssentialOCL::CollectionLiteralPartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0112 // 1*ownedParts+=MapLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, "EssentialOCL::MapLiteralPartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0113 // 1*ownedParts+=PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, "EssentialOCL::PatternExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0114 // 1*ownedParts+=ShadowPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, "EssentialOCL::ShadowPartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0115 // 1*ownedParts+=TupleLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, "EssentialOCL::TupleLiteralPartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0116 // 1*ownedParts+=TuplePartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, "EssentialOCL::TuplePartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0117 // 1*ownedPathElements+=FirstPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, "Base::FirstPathElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0118 // 1*ownedPathElements+=NextPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, "Base::NextPathElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0119 // 1*ownedPathElements+=URIFirstPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, "EssentialOCL::URIFirstPathElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0120 // 1*ownedPathName=PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, "Base::PathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0121 // 1*ownedPathName=PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, "Base::PathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0122 // 1*ownedPathName=PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, "Base::PathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0123 // 1*ownedPathName=PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, "Base::PathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0124 // 1*ownedPathName=URIPathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, "EssentialOCL::URIPathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0125 // 1*ownedPatternGuard=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0126 // 1*ownedPatternType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0127 // 1*ownedRight=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0128 // 1*ownedRight=PrefixedLetExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, "EssentialOCL::PrefixedLetExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0129 // 1*ownedRight=PrefixedPrimaryExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, "EssentialOCL::PrefixedPrimaryExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0130 // 1*ownedSubstitutions+=TemplateParameterSubstitutionCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, "Base::TemplateParameterSubstitutionCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0131 // 1*ownedSuperTypes+=TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, "OCLinEcore::TypedRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0132 // 1*ownedTerms+=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0133 // 1*ownedThenExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0134 // 1*ownedThenExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0135 // 1*ownedType=CollectionTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, "EssentialOCL::CollectionTypeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0136 // 1*ownedType=CollectionTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, "EssentialOCL::CollectionTypeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0137 // 1*ownedType=MapTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, "EssentialOCL::MapTypeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0138 // 1*ownedType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0139 // 1*ownedType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0140 // 1*ownedType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0141 // 1*ownedType=TypeExpWithoutMultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, "EssentialOCL::TypeExpWithoutMultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0142 // 1*ownedType=TypeLiteralWithMultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, "EssentialOCL::TypeLiteralWithMultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0143 // 1*ownedType=TypedMultiplicityRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, "OCLinEcore::TypedMultiplicityRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0144 // 1*ownedValue=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0145 // 1*ownedValueType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0146 // 1*ownedVariables+=LetVariableCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, "EssentialOCL::LetVariableCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0147 // 1*qualifiers
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		private static final /* @@NonNull*/ RTSerializationStep Step0148 // 1*referredElement=URI
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private static final /* @@NonNull*/ RTSerializationStep Step0149 // 1*referredElement=UnreservedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private static final /* @@NonNull*/ RTSerializationStep Step0150 // 1*referredElement=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private static final /* @@NonNull*/ RTSerializationStep Step0151 // 1*referredElement=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private static final /* @@NonNull*/ RTSerializationStep Step0152 // 1*referredKeys+=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		private static final /* @@NonNull*/ RTSerializationStep Step0153 // 1*referredKeys+=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		private static final /* @@NonNull*/ RTSerializationStep Step0154 // 1*referredOpposite=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
		private static final /* @@NonNull*/ RTSerializationStep Step0155 // 1*referredProperty=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private static final /* @@NonNull*/ RTSerializationStep Step0156 // 1*restVariableName=Identifier
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, "Base::Identifier");
		private static final /* @@NonNull*/ RTSerializationStep Step0157 // 1*stringBounds
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
		private static final /* @@NonNull*/ RTSerializationStep Step0158 // 1*symbol
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL);
		private static final /* @@NonNull*/ RTSerializationStep Step0159 // 1*symbol=NUMBER_LITERAL
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, "Base::NUMBER_LITERAL");
		private static final /* @@NonNull*/ RTSerializationStep Step0160 // 1*upperBound=UPPER
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, "Base::UPPER");
		private static final /* @@NonNull*/ RTSerializationStep Step0161 // 1*value=SIGNED
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, "OCLinEcore::SIGNED");
		private static final /* @@NonNull*/ RTSerializationStep Step0162 // V00*'abstract'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "abstract");
		private static final /* @@NonNull*/ RTSerializationStep Step0163 // V00*'callable'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "callable");
		private static final /* @@NonNull*/ RTSerializationStep Step0164 // V00*'definition'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "definition");
		private static final /* @@NonNull*/ RTSerializationStep Step0165 // V00*'primitive'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "primitive");
		private static final /* @@NonNull*/ RTSerializationStep Step0166 // V00*'static'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "static");
		private static final /* @@NonNull*/ RTSerializationStep Step0167 // V00*'|1'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "|1");
		private static final /* @@NonNull*/ RTSerializationStep Step0168 // V00*name=UnrestrictedName|SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME , "OCLinEcore::UnrestrictedName", "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step0169 // V00*next-2-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(0, 4, 6);
		private static final /* @@NonNull*/ RTSerializationStep Step0170 // V00*ownedIfThenExpressions+=ElseIfThenExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, "EssentialOCL::ElseIfThenExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0171 // V00*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0172 // V00*ownedRoundBracketedClause=RoundBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, "EssentialOCL::RoundBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0173 // V00*ownedSignature=TemplateSignatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, "OCLinEcore::TemplateSignatureCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0174 // V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, "EssentialOCL::SquareBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0175 // V00*patternVariableName=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, "OCLinEcore::UnrestrictedName");
		private static final /* @@NonNull*/ RTSerializationStep Step0176 // V00*segments+=StringLiteral
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, "Base::StringLiteral");
		private static final /* @@NonNull*/ RTSerializationStep Step0177 // V00*value=SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step0178 // V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS__VALUES , "Base::SINGLE_QUOTED_STRING", "Base::ML_SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step0179 // V01*'::*'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(1, "::*");
		private static final /* @@NonNull*/ RTSerializationStep Step0180 // V01*'|1'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(1, "|1");
		private static final /* @@NonNull*/ RTSerializationStep Step0181 // V01*next-6-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(1, 4, 10);
		private static final /* @@NonNull*/ RTSerializationStep Step0182 // V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS , "EssentialOCL::NavigatingCommaArgCS", "EssentialOCL::NavigatingSemiArgCS", "EssentialOCL::NavigatingBarArgCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0183 // V01*ownedCollectionMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0184 // V01*ownedImports+=ImportCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, "OCLinEcore::ImportCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0185 // V01*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0186 // V01*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0187 // V01*ownedRoundBracketedClause=RoundBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, "EssentialOCL::RoundBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0188 // V01*ownedSignature=TemplateSignatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, "OCLinEcore::TemplateSignatureCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0189 // V02*next-2-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(2, 7, 9);
		private static final /* @@NonNull*/ RTSerializationStep Step0190 // V02*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0191 // V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, "EssentialOCL::CurlyBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0192 // V02*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0193 // V02*ownedPackages+=PackageCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, "OCLinEcore::PackageCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0194 // V02*ownedSpecification=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0195 // V03*'serializable'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(3, "serializable");
		private static final /* @@NonNull*/ RTSerializationStep Step0196 // V03*next-1-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(3, 12, 13);
		private static final /* @@NonNull*/ RTSerializationStep Step0197 // V03*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0198 // V03*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0199 // V03*ownedPackages+=PackageCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, "OCLinEcore::PackageCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0200 // V03*ownedSpecification=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0201 // V04*'serializable'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(4, "serializable");
		private static final /* @@NonNull*/ RTSerializationStep Step0202 // V04*next-1-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(4, 14, 15);
		private static final /* @@NonNull*/ RTSerializationStep Step0203 // V04*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0204 // V04*ownedClasses+=ClassCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, "OCLinEcore::ClassCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0205 // V04*ownedContents+=ModelElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, "OCLinEcore::ModelElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0206 // V04*ownedLiterals+=EnumerationLiteralCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, "OCLinEcore::EnumerationLiteralCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0207 // V05*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(5, 17, 21);
		private static final /* @@NonNull*/ RTSerializationStep Step0208 // V05*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0209 // V05*ownedConstraints+=InvariantConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, "OCLinEcore::InvariantConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0210 // V05*ownedLiterals+=EnumerationLiteralCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, "OCLinEcore::EnumerationLiteralCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0211 // V05*ownedReferences+=ModelElementRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, "OCLinEcore::ModelElementRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0212 // V06*'interface'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(6, "interface");
		private static final /* @@NonNull*/ RTSerializationStep Step0213 // V06*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(6, 19, 23);
		private static final /* @@NonNull*/ RTSerializationStep Step0214 // V06*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0215 // V06*ownedConstraints+=InvariantConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, "OCLinEcore::InvariantConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0216 // V06*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0217 // V07*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(7, 22, 26);
		private static final /* @@NonNull*/ RTSerializationStep Step0218 // V07*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(7, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0219 // V07*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(7, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0220 // V08*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(8, 24, 28);
		private static final /* @@NonNull*/ RTSerializationStep Step0221 // V08*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0222 // V08*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0223 // V08*ownedOperations+=OperationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, "OCLinEcore::OperationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0224 // V09*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(9, 29, 33);
		private static final /* @@NonNull*/ RTSerializationStep Step0225 // V09*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0226 // V09*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0227 // V09*ownedPreconditions+=PreconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, "OCLinEcore::PreconditionConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0228 // V09*ownedProperties+=StructuralFeatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, "OCLinEcore::StructuralFeatureCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0229 // V10*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(10, 29, 33);
		private static final /* @@NonNull*/ RTSerializationStep Step0230 // V10*ownedConstraints+=InvariantConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, "OCLinEcore::InvariantConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0231 // V10*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0232 // V10*ownedPreconditions+=PreconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, "OCLinEcore::PreconditionConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0233 // V11*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(11, 31, 35);
		private static final /* @@NonNull*/ RTSerializationStep Step0234 // V11*ownedBodyExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(11, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0235 // V11*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(11, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0236 // V12*next-2-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(12, 37, 39);
		private static final /* @@NonNull*/ RTSerializationStep Step0237 // V12*ownedBodyExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0238 // V12*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0239 // V12*ownedPostconditions+=PostconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, "OCLinEcore::PostconditionConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step0240 // V13*next-2-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(13, 39, 41);
		private static final /* @@NonNull*/ RTSerializationStep Step0241 // V13*ownedPostconditions+=PostconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(13, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, "OCLinEcore::PostconditionConstraintCS");
	}

	private static class _Rules
	{

		/**
		 * AnnotationCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_AnnotationCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS, com.google.common.collect.Lists.newArrayList(
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0023 /* 1*'annotation' */,
						_Steps.Step0168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0023 /* 1*'annotation' */,
						_Steps.Step0168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step0211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0023 /* 1*'annotation' */,
						_Steps.Step0168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step0211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0023 /* 1*'annotation' */,
						_Steps.Step0168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step0211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0023 /* 1*'annotation' */,
						_Steps.Step0168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0023 /* 1*'annotation' */,
						_Steps.Step0168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step0211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0023 /* 1*'annotation' */,
						_Steps.Step0168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step0211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0023 /* 1*'annotation' */,
						_Steps.Step0168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step0211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * AttributeCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_AttributeCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ATTRIBUTE_CS, com.google.common.collect.Lists.newArrayList(
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0196 /* V03*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-16-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-16-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-26-steps */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0196 /* V03*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0207 /* V05*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0216 /* V06*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0222 /* V08*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-28-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0220 /* V08*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-28-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0220 /* V08*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0196 /* V03*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-16-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-16-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-26-steps */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0196 /* V03*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0207 /* V05*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0216 /* V06*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0222 /* V08*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-28-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0220 /* V08*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-28-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0220 /* V08*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0196 /* V03*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-16-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-16-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-26-steps */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0196 /* V03*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0207 /* V05*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0216 /* V06*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0222 /* V08*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-28-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0220 /* V08*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-28-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0024 /* 1*'attribute' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0220 /* V08*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * BooleanLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_BooleanLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * CollectionLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_CollectionLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * CollectionLiteralPartCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_CollectionLiteralPartCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS, com.google.common.collect.Lists.newArrayList(
				// ownedExpression=PatternExpCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0093 /* 1*ownedExpression=PatternExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0091 /* 1*ownedExpression=ExpCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0008 /* 1*'..' */,
						_Steps.Step0105 /* 1*ownedLastExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * CollectionPatternCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_CollectionPatternCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS, com.google.common.collect.Lists.newArrayList(
				// { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0136 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-6-steps */,
						_Steps.Step0113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step0006 /* 1*'++' */,
						_Steps.Step0156 /* 1*restVariableName=Identifier */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0136 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-6-steps */,
						_Steps.Step0113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step0006 /* 1*'++' */,
						_Steps.Step0156 /* 1*restVariableName=Identifier */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0136 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-6-steps */,
						_Steps.Step0113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step0006 /* 1*'++' */,
						_Steps.Step0156 /* 1*restVariableName=Identifier */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * CollectionTypeCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_CollectionTypeCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS, com.google.common.collect.Lists.newArrayList(
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step0183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step0183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step0183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step0183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step0183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step0183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step0183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step0183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * ContextCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_ContextCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS, com.google.common.collect.Lists.newArrayList(
				// ownedExpression=ExpCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0089 /* 1*ownedExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * CurlyBracketedClauseCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_CurlyBracketedClauseCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS, com.google.common.collect.Lists.newArrayList(
				// { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0114 /* 1*ownedParts+=ShadowPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0114 /* 1*ownedParts+=ShadowPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * DataTypeCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_DataTypeCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS, com.google.common.collect.Lists.newArrayList(
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-12-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-12-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0201 /* V04*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0201 /* V04*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-12-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-12-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0201 /* V04*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0201 /* V04*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-12-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-12-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0201 /* V04*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0165 /* V00*'primitive' */,
						_Steps.Step0027 /* 1*'datatype' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0201 /* V04*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * DetailCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_DetailCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS, com.google.common.collect.Lists.newArrayList(
				// { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0075 /* 1*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0178 /* V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * DocumentationCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_DocumentationCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS, com.google.common.collect.Lists.newArrayList(
				// { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0030 /* 1*'documentation' */,
						_Steps.Step0177 /* V00*value=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0030 /* 1*'documentation' */,
						_Steps.Step0177 /* V00*value=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * EnumerationCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_EnumerationCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS, com.google.common.collect.Lists.newArrayList(
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0195 /* V03*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0195 /* V03*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step0215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0195 /* V03*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0195 /* V03*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step0215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0195 /* V03*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-14-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0000 /* 1*'!serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step0209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-15-steps */,
						_Steps.Step0034 /* 1*'enum' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0195 /* V03*'serializable' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step0215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * EnumerationLiteralCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_EnumerationLiteralCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS, com.google.common.collect.Lists.newArrayList(
				// { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0071 /* 1*name=EnumerationLiteralName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0161 /* 1*value=SIGNED */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0044 /* 1*'literal' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0161 /* 1*value=SIGNED */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0071 /* 1*name=EnumerationLiteralName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0161 /* 1*value=SIGNED */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0044 /* 1*'literal' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0161 /* 1*value=SIGNED */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0071 /* 1*name=EnumerationLiteralName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0161 /* 1*value=SIGNED */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0044 /* 1*'literal' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0161 /* 1*value=SIGNED */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0071 /* 1*name=EnumerationLiteralName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0161 /* 1*value=SIGNED */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0044 /* 1*'literal' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0161 /* 1*value=SIGNED */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * ExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_ExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_CS, com.google.common.collect.Lists.newArrayList(
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'self'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'self'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'self'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'self'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * ExpSpecificationCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_ExpSpecificationCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS, com.google.common.collect.Lists.newArrayList(
				// exprString=UNQUOTED_STRING
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0065 /* 1*exprString=UNQUOTED_STRING */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// ownedExpression=ExpCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0092 /* 1*ownedExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * IfExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_IfExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0036 /* 1*'if' */,
						_Steps.Step0084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step0058 /* 1*'then' */,
						_Steps.Step0134 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step0170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step0031 /* 1*'else' */,
						_Steps.Step0087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step0033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0036 /* 1*'if' */,
						_Steps.Step0084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step0058 /* 1*'then' */,
						_Steps.Step0134 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step0170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step0031 /* 1*'else' */,
						_Steps.Step0087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step0033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0036 /* 1*'if' */,
						_Steps.Step0084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step0058 /* 1*'then' */,
						_Steps.Step0134 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step0170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step0031 /* 1*'else' */,
						_Steps.Step0087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step0033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0036 /* 1*'if' */,
						_Steps.Step0084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step0058 /* 1*'then' */,
						_Steps.Step0134 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step0170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step0031 /* 1*'else' */,
						_Steps.Step0087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step0033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0036 /* 1*'if' */,
						_Steps.Step0084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step0058 /* 1*'then' */,
						_Steps.Step0134 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step0170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step0031 /* 1*'else' */,
						_Steps.Step0087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step0033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * IfThenExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_IfThenExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0032 /* 1*'elseif' */,
						_Steps.Step0083 /* 1*ownedCondition=ExpCS */,
						_Steps.Step0058 /* 1*'then' */,
						_Steps.Step0133 /* 1*ownedThenExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * ImplicitOppositeCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_ImplicitOppositeCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPLICIT_OPPOSITE_CS, com.google.common.collect.Lists.newArrayList(
				// { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0048 /* 1*'opposite' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0181 /* V01*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * ImportCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_ImportCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS, com.google.common.collect.Lists.newArrayList(
				// { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0037 /* 1*'import' */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0124 /* 1*ownedPathName=URIPathNameCS */,
						_Steps.Step0179 /* V01*'::*' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * InfixExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_InfixExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0106 /* 1*ownedLeft=PrefixedPrimaryExpCS */,
						_Steps.Step0069 /* 1*name=BinaryOperatorName */,
						_Steps.Step0127 /* 1*ownedRight=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0106 /* 1*ownedLeft=PrefixedPrimaryExpCS */,
						_Steps.Step0069 /* 1*name=BinaryOperatorName */,
						_Steps.Step0127 /* 1*ownedRight=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * InvalidLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_InvalidLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * LambdaLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_LambdaLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0018 /* 1*'Lambda' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0018 /* 1*'Lambda' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0018 /* 1*'Lambda' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0018 /* 1*'Lambda' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0018 /* 1*'Lambda' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * LetExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_LetExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0043 /* 1*'let' */,
						_Steps.Step0146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step0038 /* 1*'in' */,
						_Steps.Step0098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0043 /* 1*'let' */,
						_Steps.Step0146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step0038 /* 1*'in' */,
						_Steps.Step0098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0043 /* 1*'let' */,
						_Steps.Step0146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step0038 /* 1*'in' */,
						_Steps.Step0098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0043 /* 1*'let' */,
						_Steps.Step0146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step0038 /* 1*'in' */,
						_Steps.Step0098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * LetVariableCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_LetVariableCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS, com.google.common.collect.Lists.newArrayList(
				// { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0172 /* V00*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0138 /* 1*ownedType=TypeExpCS */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * MapLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_MapLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-4-steps */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * MapLiteralPartCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_MapLiteralPartCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS, com.google.common.collect.Lists.newArrayList(
				// { ownedKey=ExpCS '<-' ownedValue=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0103 /* 1*ownedKey=ExpCS */,
						_Steps.Step0013 /* 1*'<-' */,
						_Steps.Step0144 /* 1*ownedValue=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * MapTypeCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_MapTypeCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS, com.google.common.collect.Lists.newArrayList(
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0019 /* 1*'Map' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0019 /* 1*'Map' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0019 /* 1*'Map' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0019 /* 1*'Map' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0019 /* 1*'Map' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0019 /* 1*'Map' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0019 /* 1*'Map' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0019 /* 1*'Map' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * ModelElementRefCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_ModelElementRefCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS, com.google.common.collect.Lists.newArrayList(
				// { 'reference' ownedPathName=PathNameCS ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0054 /* 1*'reference' */,
						_Steps.Step0123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * MultiplicityBoundsCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_MultiplicityBoundsCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS, com.google.common.collect.Lists.newArrayList(
				// { lowerBound=LOWER { '..' upperBound=UPPER }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0068 /* 1*lowerBound=LOWER */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0008 /* 1*'..' */,
						_Steps.Step0160 /* 1*upperBound=UPPER */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0021 /* 1*'[' */,
						_Steps.Step0068 /* 1*lowerBound=LOWER */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0008 /* 1*'..' */,
						_Steps.Step0160 /* 1*upperBound=UPPER */,
						_Steps.Step0022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0021 /* 1*'[' */,
						_Steps.Step0068 /* 1*lowerBound=LOWER */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0008 /* 1*'..' */,
						_Steps.Step0160 /* 1*upperBound=UPPER */,
						_Steps.Step0062 /* 1*'|?' */,
						_Steps.Step0022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0021 /* 1*'[' */,
						_Steps.Step0068 /* 1*lowerBound=LOWER */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0008 /* 1*'..' */,
						_Steps.Step0160 /* 1*upperBound=UPPER */,
						_Steps.Step0180 /* V01*'|1' */,
						_Steps.Step0022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * MultiplicityStringCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_MultiplicityStringCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS, com.google.common.collect.Lists.newArrayList(
				// { '[' stringBounds={'*|+|?'} ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0021 /* 1*'[' */,
						_Steps.Step0157 /* 1*stringBounds */,
						_Steps.Step0022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				),
				// { '[' stringBounds={'*|+|?'} '|?' ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0021 /* 1*'[' */,
						_Steps.Step0157 /* 1*stringBounds */,
						_Steps.Step0062 /* 1*'|?' */,
						_Steps.Step0022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0021 /* 1*'[' */,
						_Steps.Step0157 /* 1*stringBounds */,
						_Steps.Step0167 /* V00*'|1' */,
						_Steps.Step0022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// stringBounds={'*|+|?'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0157 /* 1*stringBounds */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * NameExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_NameExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAME_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0122 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step0187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step0191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0017 /* 1*'@' */,
						_Steps.Step0051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0122 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step0187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step0191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0017 /* 1*'@' */,
						_Steps.Step0051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0122 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step0187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step0191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0017 /* 1*'@' */,
						_Steps.Step0051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0122 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step0187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step0191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0017 /* 1*'@' */,
						_Steps.Step0051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0122 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step0187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step0191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0017 /* 1*'@' */,
						_Steps.Step0051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * NavigatingArgCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_NavigatingArgCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS, com.google.common.collect.Lists.newArrayList(
				// ownedNameExpression=NavigatingArgExpCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// { ':' ownedType=TypeExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0140 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step0013 /* 1*'<-' */,
						_Steps.Step0082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0013 /* 1*'<-' */,
						_Steps.Step0082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0013 /* 1*'<-' */,
						_Steps.Step0082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step0038 /* 1*'in' */,
						_Steps.Step0099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0061 /* 1*'|' */,
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { prefix=',' ownedNameExpression=NavigatingArgExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step0013 /* 1*'<-' */,
						_Steps.Step0082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0013 /* 1*'<-' */,
						_Steps.Step0082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0013 /* 1*'<-' */,
						_Steps.Step0082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step0038 /* 1*'in' */,
						_Steps.Step0099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * NestedExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_NestedExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { '(' ownedExpression=ExpCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0090 /* 1*ownedExpression=ExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				),
				// { '(' ownedExpression=ExpCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0090 /* 1*ownedExpression=ExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				),
				// { '(' ownedExpression=ExpCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0090 /* 1*ownedExpression=ExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				),
				// { '(' ownedExpression=ExpCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0090 /* 1*ownedExpression=ExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				),
				// { '(' ownedExpression=ExpCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0090 /* 1*ownedExpression=ExpCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * NullLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_NullLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * NumberLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_NumberLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * OCLinEcoreConstraintCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_OCLinEcoreConstraintCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS, com.google.common.collect.Lists.newArrayList(
				// { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0163 /* V00*'callable' */,
						_Steps.Step0041 /* 1*'invariant' */,
						_Steps.Step0181 /* V01*next-5-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0107 /* 1*ownedMessageSpecification=SpecificationCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-11-steps */,
						_Steps.Step0163 /* V00*'callable' */,
						_Steps.Step0041 /* 1*'invariant' */,
						_Steps.Step0181 /* V01*next-5-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-3-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0107 /* 1*ownedMessageSpecification=SpecificationCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0200 /* V03*ownedSpecification=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0050 /* 1*'postcondition' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-3-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0107 /* 1*ownedMessageSpecification=SpecificationCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0194 /* V02*ownedSpecification=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0052 /* 1*'precondition' */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-3-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0107 /* 1*ownedMessageSpecification=SpecificationCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0194 /* V02*ownedSpecification=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * OperationCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_OperationCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS, com.google.common.collect.Lists.newArrayList(
				// { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-25-steps */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0202 /* V04*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0207 /* V05*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0217 /* V07*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-27-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0207 /* V05*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0220 /* V08*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-27-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0207 /* V05*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0220 /* V08*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-34-steps */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0202 /* V04*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0207 /* V05*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0217 /* V07*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0221 /* V08*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step0229 /* V10*next-4-steps */,
						_Steps.Step0025 /* 1*'body' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0234 /* V11*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-36-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0207 /* V05*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0220 /* V08*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0025 /* 1*'body' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-36-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0207 /* V05*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0220 /* V08*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0025 /* 1*'body' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-25-steps */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0202 /* V04*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0207 /* V05*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0217 /* V07*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-27-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0207 /* V05*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0220 /* V08*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-27-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0207 /* V05*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0220 /* V08*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-34-steps */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0202 /* V04*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0207 /* V05*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0217 /* V07*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0221 /* V08*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step0229 /* V10*next-4-steps */,
						_Steps.Step0025 /* 1*'body' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0234 /* V11*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-36-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0207 /* V05*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0220 /* V08*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0025 /* 1*'body' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-36-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0047 /* 1*'operation' */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0189 /* V02*next-4-steps */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0207 /* V05*next-5-steps */,
						_Steps.Step0059 /* 1*'throws' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0213 /* V06*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step0217 /* V07*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0220 /* V08*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0025 /* 1*'body' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * PackageCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_PackageCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS, com.google.common.collect.Lists.newArrayList(
				// { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0049 /* 1*'package' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0077 /* 1*nsPrefix=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0078 /* 1*nsURI=URI */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-13-steps */,
						_Steps.Step0049 /* 1*'package' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0077 /* 1*nsPrefix=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0078 /* 1*nsURI=URI */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0199 /* V03*ownedPackages+=PackageCS */,
						_Steps.Step0204 /* V04*ownedClasses+=ClassCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0049 /* 1*'package' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0077 /* 1*nsPrefix=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0078 /* 1*nsURI=URI */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-13-steps */,
						_Steps.Step0049 /* 1*'package' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0077 /* 1*nsPrefix=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0078 /* 1*nsURI=URI */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0199 /* V03*ownedPackages+=PackageCS */,
						_Steps.Step0204 /* V04*ownedClasses+=ClassCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * ParameterCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_ParameterCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PARAMETER_CS, com.google.common.collect.Lists.newArrayList(
				// { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-13-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0189 /* V02*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0196 /* V03*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * PathElementCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_PathElementCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS, com.google.common.collect.Lists.newArrayList(
				// referredElement=UnrestrictedName
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0151 /* 1*referredElement=UnrestrictedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// referredElement=UnreservedName
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0149 /* 1*referredElement=UnreservedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// referredElement=UnrestrictedName
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0150 /* 1*referredElement=UnrestrictedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * PathElementWithURICS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_PathElementWithURICS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS, com.google.common.collect.Lists.newArrayList(
				// referredElement=URI
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0148 /* 1*referredElement=URI */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * PathNameCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_PathNameCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS, com.google.common.collect.Lists.newArrayList(
				// { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0117 /* 1*ownedPathElements+=FirstPathElementCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0010 /* 1*'::' */,
						_Steps.Step0118 /* 1*ownedPathElements+=NextPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// ownedPathElements+=FirstPathElementCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0117 /* 1*ownedPathElements+=FirstPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0119 /* 1*ownedPathElements+=URIFirstPathElementCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0010 /* 1*'::' */,
						_Steps.Step0118 /* 1*ownedPathElements+=NextPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * PatternExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_PatternExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0175 /* V00*patternVariableName=UnrestrictedName */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0126 /* 1*ownedPatternType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * PrefixExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_PrefixExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PREFIX_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0073 /* 1*name=UnaryOperatorName */,
						_Steps.Step0129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0073 /* 1*name=UnaryOperatorName */,
						_Steps.Step0128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0073 /* 1*name=UnaryOperatorName */,
						_Steps.Step0129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0073 /* 1*name=UnaryOperatorName */,
						_Steps.Step0128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0073 /* 1*name=UnaryOperatorName */,
						_Steps.Step0128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0073 /* 1*name=UnaryOperatorName */,
						_Steps.Step0129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * PrimitiveLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_PrimitiveLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PRIMITIVE_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * PrimitiveTypeRefCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_PrimitiveTypeRefCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS, com.google.common.collect.Lists.newArrayList(
				// name=PrimitiveTypeIdentifier
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// name=PrimitiveTypeIdentifier
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0072 /* 1*name=PrimitiveTypeIdentifier */,
						_Steps.Step0171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// name=PrimitiveTypeIdentifier
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// name=PrimitiveTypeIdentifier
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0072 /* 1*name=PrimitiveTypeIdentifier */,
						_Steps.Step0171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0072 /* 1*name=PrimitiveTypeIdentifier */,
						_Steps.Step0171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// name=PrimitiveTypeIdentifier
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * ReferenceCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_ReferenceCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS, com.google.common.collect.Lists.newArrayList(
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-17-steps */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-19-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-19-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-39-steps */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0213 /* V06*next-6-steps */,
						_Steps.Step0042 /* 1*'key' */,
						_Steps.Step0152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0217 /* V07*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0220 /* V08*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0229 /* V10*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0236 /* V12*next-2-steps */,
						_Steps.Step0097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-41-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0217 /* V07*next-6-steps */,
						_Steps.Step0042 /* 1*'key' */,
						_Steps.Step0152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0220 /* V08*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0224 /* V09*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0240 /* V13*next-2-steps */,
						_Steps.Step0097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-41-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0217 /* V07*next-6-steps */,
						_Steps.Step0042 /* 1*'key' */,
						_Steps.Step0152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0220 /* V08*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0224 /* V09*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0240 /* V13*next-2-steps */,
						_Steps.Step0097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-17-steps */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-19-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-19-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-39-steps */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0213 /* V06*next-6-steps */,
						_Steps.Step0042 /* 1*'key' */,
						_Steps.Step0152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0217 /* V07*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0220 /* V08*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0229 /* V10*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0236 /* V12*next-2-steps */,
						_Steps.Step0097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-41-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0217 /* V07*next-6-steps */,
						_Steps.Step0042 /* 1*'key' */,
						_Steps.Step0152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0220 /* V08*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0224 /* V09*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0240 /* V13*next-2-steps */,
						_Steps.Step0097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-41-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0217 /* V07*next-6-steps */,
						_Steps.Step0042 /* 1*'key' */,
						_Steps.Step0152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0220 /* V08*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0224 /* V09*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0240 /* V13*next-2-steps */,
						_Steps.Step0097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-17-steps */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-19-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-19-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-39-steps */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0196 /* V03*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0202 /* V04*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0213 /* V06*next-6-steps */,
						_Steps.Step0042 /* 1*'key' */,
						_Steps.Step0152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0217 /* V07*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0220 /* V08*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0229 /* V10*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0236 /* V12*next-2-steps */,
						_Steps.Step0097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-41-steps */,
						_Steps.Step0028 /* 1*'definition' */,
						_Steps.Step0166 /* V00*'static' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0217 /* V07*next-6-steps */,
						_Steps.Step0042 /* 1*'key' */,
						_Steps.Step0152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0220 /* V08*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0224 /* V09*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0240 /* V13*next-2-steps */,
						_Steps.Step0097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-41-steps */,
						_Steps.Step0056 /* 1*'static' */,
						_Steps.Step0164 /* V00*'definition' */,
						_Steps.Step0053 /* 1*'property' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0001 /* 1*'#' */,
						_Steps.Step0154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step0202 /* V04*next-4-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0207 /* V05*next-1-steps */,
						_Steps.Step0147 /* 1*qualifiers */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0217 /* V07*next-6-steps */,
						_Steps.Step0042 /* 1*'key' */,
						_Steps.Step0152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0220 /* V08*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0224 /* V09*next-4-steps */,
						_Steps.Step0039 /* 1*'initial' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0233 /* V11*next-4-steps */,
						_Steps.Step0029 /* 1*'derivation' */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0240 /* V13*next-2-steps */,
						_Steps.Step0097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * RoundBracketedClauseCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_RoundBracketedClauseCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS, com.google.common.collect.Lists.newArrayList(
				// { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-5-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0080 /* 1*ownedArguments+=NavigatingArgCS */,
						_Steps.Step0182 /* V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * SelfExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_SelfExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SELF_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// 'self'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * ShadowPartCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_ShadowPartCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS, com.google.common.collect.Lists.newArrayList(
				// ownedInitExpression=StringLiteralExpCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0102 /* 1*ownedInitExpression=StringLiteralExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0155 /* 1*referredProperty=UnrestrictedName */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0101 /* 1*ownedInitExpression=ExpCS|PatternExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * SquareBracketedClauseCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_SquareBracketedClauseCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS, com.google.common.collect.Lists.newArrayList(
				// { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0021 /* 1*'[' */,
						_Steps.Step0132 /* 1*ownedTerms+=ExpCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0132 /* 1*ownedTerms+=ExpCS */,
						_Steps.Step0022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * StringLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_StringLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * StructuredClassCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_StructuredClassCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS, com.google.common.collect.Lists.newArrayList(
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-18-steps */,
						_Steps.Step0162 /* V00*'abstract' */,
						_Steps.Step0026 /* 1*'class' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-5-steps */,
						_Steps.Step0035 /* 1*'extends' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0207 /* V05*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0212 /* V06*'interface' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-23-steps */,
						_Steps.Step0162 /* V00*'abstract' */,
						_Steps.Step0026 /* 1*'class' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-5-steps */,
						_Steps.Step0035 /* 1*'extends' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0207 /* V05*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0212 /* V06*'interface' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0223 /* V08*ownedOperations+=OperationCS */,
						_Steps.Step0228 /* V09*ownedProperties+=StructuralFeatureCS */,
						_Steps.Step0230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-18-steps */,
						_Steps.Step0162 /* V00*'abstract' */,
						_Steps.Step0026 /* 1*'class' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-5-steps */,
						_Steps.Step0035 /* 1*'extends' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0207 /* V05*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0212 /* V06*'interface' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-23-steps */,
						_Steps.Step0162 /* V00*'abstract' */,
						_Steps.Step0026 /* 1*'class' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-5-steps */,
						_Steps.Step0035 /* 1*'extends' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0207 /* V05*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0212 /* V06*'interface' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0223 /* V08*ownedOperations+=OperationCS */,
						_Steps.Step0228 /* V09*ownedProperties+=StructuralFeatureCS */,
						_Steps.Step0230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-18-steps */,
						_Steps.Step0162 /* V00*'abstract' */,
						_Steps.Step0026 /* 1*'class' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-5-steps */,
						_Steps.Step0035 /* 1*'extends' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0207 /* V05*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0212 /* V06*'interface' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-23-steps */,
						_Steps.Step0162 /* V00*'abstract' */,
						_Steps.Step0026 /* 1*'class' */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step0189 /* V02*next-5-steps */,
						_Steps.Step0035 /* 1*'extends' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0196 /* V03*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step0202 /* V04*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step0207 /* V05*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0212 /* V06*'interface' */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step0223 /* V08*ownedOperations+=OperationCS */,
						_Steps.Step0228 /* V09*ownedProperties+=StructuralFeatureCS */,
						_Steps.Step0230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * SysMLCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_SysMLCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.SYS_MLCS, com.google.common.collect.Lists.newArrayList(
				// { 'sysml' ownedDetails+=DetailCS ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0057 /* 1*'sysml' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0057 /* 1*'sysml' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'sysml' ownedDetails+=DetailCS ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0057 /* 1*'sysml' */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0057 /* 1*'sysml' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step0011 /* 1*';' */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TemplateBindingCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TemplateBindingCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS, com.google.common.collect.Lists.newArrayList(
				// { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-5-steps */,
						_Steps.Step0130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
						_Steps.Step0186 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TemplateParameterSubstitutionCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TemplateParameterSubstitutionCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS, com.google.common.collect.Lists.newArrayList(
				// ownedActualParameter=TypeRefCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0079 /* 1*ownedActualParameter=TypeRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TemplateSignatureCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TemplateSignatureCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS, com.google.common.collect.Lists.newArrayList(
				// { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0110 /* 1*ownedParameters+=TypeParameterCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0110 /* 1*ownedParameters+=TypeParameterCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0012 /* 1*'<' */,
						_Steps.Step0110 /* 1*ownedParameters+=TypeParameterCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0110 /* 1*ownedParameters+=TypeParameterCS */,
						_Steps.Step0015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TopLevelCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TopLevelCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.TOP_LEVEL_CS, com.google.common.collect.Lists.newArrayList(
				// { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0169 /* V00*next-1-steps */,
						_Steps.Step0045 /* 1*'module' */,
						_Steps.Step0184 /* V01*ownedImports+=ImportCS */,
						_Steps.Step0193 /* V02*ownedPackages+=PackageCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TupleLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TupleLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TupleLiteralPartCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TupleLiteralPartCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS, com.google.common.collect.Lists.newArrayList(
				// { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-6-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0138 /* 1*ownedType=TypeExpCS */,
						_Steps.Step0014 /* 1*'=' */,
						_Steps.Step0100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TuplePartCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TuplePartCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_PART_CS, com.google.common.collect.Lists.newArrayList(
				// { name=UnrestrictedName ':' ownedType=TypeExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-3-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0139 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TupleTypeCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TupleTypeCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS, com.google.common.collect.Lists.newArrayList(
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0169 /* V00*next-7-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0169 /* V00*next-7-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0169 /* V00*next-7-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0169 /* V00*next-7-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0169 /* V00*next-7-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0169 /* V00*next-7-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-10-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0169 /* V00*next-7-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-9-steps */,
						_Steps.Step0020 /* 1*'Tuple' */,
						_Steps.Step0169 /* V00*next-7-steps */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0181 /* V01*next-4-steps */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0189 /* V02*next-2-steps */,
						_Steps.Step0007 /* 1*',' */,
						_Steps.Step0116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TypeLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TypeLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// ownedType=TypeLiteralWithMultiplicityCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// ownedType=TypeLiteralWithMultiplicityCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// ownedType=TypeLiteralWithMultiplicityCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// ownedType=TypeLiteralWithMultiplicityCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// ownedType=TypeLiteralWithMultiplicityCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TypeNameExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TypeNameExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-8-steps */,
						_Steps.Step0120 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step0181 /* V01*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0125 /* 1*ownedPatternGuard=ExpCS */,
						_Steps.Step0063 /* 1*'}' */,
						_Steps.Step0192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0120 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step0181 /* V01*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0125 /* 1*ownedPatternGuard=ExpCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0120 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step0181 /* V01*next-3-steps */,
						_Steps.Step0060 /* 1*'{' */,
						_Steps.Step0125 /* 1*ownedPatternGuard=ExpCS */,
						_Steps.Step0063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TypeParameterCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TypeParameterCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS, com.google.common.collect.Lists.newArrayList(
				// { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-7-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-5-steps */,
						_Steps.Step0035 /* 1*'extends' */,
						_Steps.Step0095 /* 1*ownedExtends+=TypedRefCS */,
						_Steps.Step0181 /* V01*next-2-steps */,
						_Steps.Step0002 /* 1*'&&' */,
						_Steps.Step0095 /* 1*ownedExtends+=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * TypedTypeRefCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_TypedTypeRefCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS, com.google.common.collect.Lists.newArrayList(
				// ownedPathName=PathNameCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0012 /* 1*'<' */,
						_Steps.Step0081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step0015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-2-steps */,
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-5-steps */,
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step0004 /* 1*')' */,
						_Steps.Step0171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-5-steps */,
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0012 /* 1*'<' */,
						_Steps.Step0081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step0015 /* 1*'>' */,
						_Steps.Step0171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// ownedPathName=PathNameCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0012 /* 1*'<' */,
						_Steps.Step0081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step0015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// ownedPathName=PathNameCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				),
				// { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0003 /* 1*'(' */,
						_Steps.Step0081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step0004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step0012 /* 1*'<' */,
						_Steps.Step0081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step0015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * UnlimitedNaturalLiteralExpCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_UnlimitedNaturalLiteralExpCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS, com.google.common.collect.Lists.newArrayList(
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
					*/}
				)
			), 0);
		}

		/**
		 * VariableCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_VariableCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS, com.google.common.collect.Lists.newArrayList(
				// { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0074 /* 1*name=UnrestrictedName */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0009 /* 1*':' */,
						_Steps.Step0138 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}

		/**
		 * WildcardTypeRefCS
		 */
		private static org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules create_WildcardTypeRefCS_Rules() {
			return new org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS, com.google.common.collect.Lists.newArrayList(
				// { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0016 /* 1*'?' */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0035 /* 1*'extends' */,
						_Steps.Step0096 /* 1*ownedExtends=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				),
				// { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step0076 /* 1*next-4-steps */,
						_Steps.Step0016 /* 1*'?' */,
						_Steps.Step0169 /* V00*next-2-steps */,
						_Steps.Step0035 /* 1*'extends' */,
						_Steps.Step0096 /* 1*ownedExtends=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom /*@NonNull*/ []{/*
						// null
						// null
						// null
						// null
						// null
					*/}
				)
			), 0);
		}
	}
}
