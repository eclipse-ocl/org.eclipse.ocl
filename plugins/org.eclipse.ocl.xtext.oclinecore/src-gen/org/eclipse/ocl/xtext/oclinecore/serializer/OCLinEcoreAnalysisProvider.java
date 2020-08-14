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
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
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

	private static class _Solutions
	{
		private static final /* @@NonNull*/ CardinalitySolution Solution000 // (|exprString| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution106, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution001 // (|isInterface.'interface'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution111, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution002 // (|isSerializable.'serializable'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution115, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution003 // (|isSerializable.'serializable'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution116, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution004 // (|lowerBound| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution118, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution005 // (|name.'Map'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution119, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution006 // (|name.'Tuple'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution120, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution007 // (|name| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution123, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution008 // (|name| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution121, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution009 // (|name| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution122, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution010 // (|ownedActualParameter| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution126, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution011 // (|ownedAnnotations| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution127, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution012 // (|ownedArguments| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution128, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution013 // (|ownedArguments| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution128, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution014 // (|ownedBinding| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution129, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution015 // (|ownedBodyExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution130, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution016 // (|ownedCoIterator| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution132, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution017 // (|ownedCondition| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution134, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution018 // (|ownedCondition| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution135, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution019 // (|ownedDefaultExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution140, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution020 // (|ownedDetails| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution141, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution021 // (|ownedDetails| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution141, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution022 // (|ownedElseExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution142, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution023 // (|ownedExceptions| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution143, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution024 // (|ownedExceptions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution143, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution025 // (|ownedExpressionCS| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution144, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution026 // (|ownedExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution147, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution027 // (|ownedExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution146, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution028 // (|ownedExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution145, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution029 // (|ownedExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution148, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution030 // (|ownedExtends| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution149, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution031 // (|ownedExtends| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution149, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution032 // (|ownedInExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution154, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution033 // (|ownedInitExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution156, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution034 // (|ownedInitExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution155, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution035 // (|ownedInitExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution157, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution036 // (|ownedKeyType| - C00)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution158, Solution100);
		private static final /* @@NonNull*/ CardinalitySolution Solution037 // (|ownedKeyType| - C00)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution158, Solution101);
		private static final /* @@NonNull*/ CardinalitySolution Solution038 // (|ownedKeyType| - C00)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution158, Solution102);
		private static final /* @@NonNull*/ CardinalitySolution Solution039 // (|ownedKeyType| - C00)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution158, Solution103);
		private static final /* @@NonNull*/ CardinalitySolution Solution040 // (|ownedKeyType| - C00)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution158, Solution104);
		private static final /* @@NonNull*/ CardinalitySolution Solution041 // (|ownedKey| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution159, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution042 // (|ownedLeft| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution161, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution043 // (|ownedNameExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution166, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution044 // (|ownedParameters| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution169, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution045 // (|ownedParameters| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution170, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution046 // (|ownedParameters| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution170, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution047 // (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution173, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution048 // (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution171, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution049 // (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution174, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution050 // (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution175, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution051 // (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution172, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution052 // (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution176, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution053 // (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution172, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution054 // (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution176, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution055 // (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution174, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution056 // (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution171, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution057 // (|ownedPathElements| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution177, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution058 // (|ownedPathName| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution180, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution059 // (|ownedPathName| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution182, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution060 // (|ownedPathName| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution178, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution061 // (|ownedPathName| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution179, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution062 // (|ownedPathName| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution181, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution063 // (|ownedPatternType| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution184, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution064 // (|ownedRight| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution189, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution065 // (|ownedSubstitutions| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution195, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution066 // (|ownedSuperTypes| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution196, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution067 // (|ownedSuperTypes| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution196, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution068 // (|ownedTerms| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution197, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution069 // (|ownedThenExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution199, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution070 // (|ownedThenExpression| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution198, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution071 // (|ownedType| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution204, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution072 // (|ownedType| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution202, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution073 // (|ownedType| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution201, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution074 // (|ownedType| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution203, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution075 // (|ownedType| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution200, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution076 // (|ownedType| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution205, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution077 // (|ownedValue| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution209, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution078 // (|ownedVariables| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution210, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution079 // (|prefix.','| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution212, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution080 // (|prefix.';'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution213, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution081 // (|prefix.'|'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution214, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution082 // (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution215, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution083 // (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution216, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution084 // (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution217, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution085 // (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution218, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution086 // (|qualifiers.'definition'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution219, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution087 // (|qualifiers.'static'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution220, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution088 // (|referredElement| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution221, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution089 // (|referredKeys| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution222, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution090 // (|referredKeys| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(Solution222, Solution098);
		private static final /* @@NonNull*/ CardinalitySolution Solution091 // (|referredProperty| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution224, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution092 // (|stereotype.'invariant'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution227, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution093 // (|stereotype.'postcondition'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution228, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution094 // (|stereotype.'precondition'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution229, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution095 // (|stringBounds.'*|+|?'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution230, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution096 // (|symbol.'false|true'| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution231, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution097 // (|symbol| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(Solution232, Solution099);
		private static final /* @@NonNull*/ CardinalitySolution Solution098 // 0
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution(0);
		private static final /* @@NonNull*/ CardinalitySolution Solution099 // 1
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution(1);
		private static final /* @@NonNull*/ CardinalitySolution Solution100 // C00
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution(0);
		private static final /* @@NonNull*/ CardinalitySolution Solution101 // C00
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution(0);
		private static final /* @@NonNull*/ CardinalitySolution Solution102 // C00
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution(0);
		private static final /* @@NonNull*/ CardinalitySolution Solution103 // C00
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution(0);
		private static final /* @@NonNull*/ CardinalitySolution Solution104 // C00
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution(0);
		private static final /* @@NonNull*/ CardinalitySolution Solution105 // |default|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		private static final /* @@NonNull*/ CardinalitySolution Solution106 // |exprString|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		private static final /* @@NonNull*/ CardinalitySolution Solution107 // |instanceClassName|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution108 // |isAbstract.'abstract'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, 'abstract');
		private static final /* @@NonNull*/ CardinalitySolution Solution109 // |isAll.'::*'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__IS_ALL, '::*');
		private static final /* @@NonNull*/ CardinalitySolution Solution110 // |isCallable.'callable'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, 'callable');
		private static final /* @@NonNull*/ CardinalitySolution Solution111 // |isInterface.'interface'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, 'interface');
		private static final /* @@NonNull*/ CardinalitySolution Solution112 // |isNullFree.'|1'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, '|1');
		private static final /* @@NonNull*/ CardinalitySolution Solution113 // |isPre.'@'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, '@');
		private static final /* @@NonNull*/ CardinalitySolution Solution114 // |isPrimitive.'primitive'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, 'primitive');
		private static final /* @@NonNull*/ CardinalitySolution Solution115 // |isSerializable.'serializable'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, 'serializable');
		private static final /* @@NonNull*/ CardinalitySolution Solution116 // |isSerializable.'serializable'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, 'serializable');
		private static final /* @@NonNull*/ CardinalitySolution Solution117 // |literal|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
		private static final /* @@NonNull*/ CardinalitySolution Solution118 // |lowerBound|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private static final /* @@NonNull*/ CardinalitySolution Solution119 // |name.'Map'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, 'Map');
		private static final /* @@NonNull*/ CardinalitySolution Solution120 // |name.'Tuple'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, 'Tuple');
		private static final /* @@NonNull*/ CardinalitySolution Solution121 // |name|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution122 // |name|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution123 // |name|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution124 // |nsPrefix|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		private static final /* @@NonNull*/ CardinalitySolution Solution125 // |nsURI|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		private static final /* @@NonNull*/ CardinalitySolution Solution126 // |ownedActualParameter|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private static final /* @@NonNull*/ CardinalitySolution Solution127 // |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private static final /* @@NonNull*/ CardinalitySolution Solution128 // |ownedArguments|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution129 // |ownedBinding|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private static final /* @@NonNull*/ CardinalitySolution Solution130 // |ownedBodyExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		private static final /* @@NonNull*/ CardinalitySolution Solution131 // |ownedClasses|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		private static final /* @@NonNull*/ CardinalitySolution Solution132 // |ownedCoIterator|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private static final /* @@NonNull*/ CardinalitySolution Solution133 // |ownedCollectionMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private static final /* @@NonNull*/ CardinalitySolution Solution134 // |ownedCondition|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private static final /* @@NonNull*/ CardinalitySolution Solution135 // |ownedCondition|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		private static final /* @@NonNull*/ CardinalitySolution Solution136 // |ownedConstraints|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution137 // |ownedContents|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution138 // |ownedCurlyBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private static final /* @@NonNull*/ CardinalitySolution Solution139 // |ownedCurlyBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private static final /* @@NonNull*/ CardinalitySolution Solution140 // |ownedDefaultExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private static final /* @@NonNull*/ CardinalitySolution Solution141 // |ownedDetails|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		private static final /* @@NonNull*/ CardinalitySolution Solution142 // |ownedElseExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution143 // |ownedExceptions|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		private static final /* @@NonNull*/ CardinalitySolution Solution144 // |ownedExpressionCS|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private static final /* @@NonNull*/ CardinalitySolution Solution145 // |ownedExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution146 // |ownedExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution147 // |ownedExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution148 // |ownedExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution149 // |ownedExtends|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private static final /* @@NonNull*/ CardinalitySolution Solution150 // |ownedExtends|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private static final /* @@NonNull*/ CardinalitySolution Solution151 // |ownedIfThenExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private static final /* @@NonNull*/ CardinalitySolution Solution152 // |ownedImplicitOpposites|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
		private static final /* @@NonNull*/ CardinalitySolution Solution153 // |ownedImports|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution154 // |ownedInExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution155 // |ownedInitExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution156 // |ownedInitExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution157 // |ownedInitExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution158 // |ownedKeyType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution159 // |ownedKey|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private static final /* @@NonNull*/ CardinalitySolution Solution160 // |ownedLastExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution161 // |ownedLeft|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private static final /* @@NonNull*/ CardinalitySolution Solution162 // |ownedLiterals|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		private static final /* @@NonNull*/ CardinalitySolution Solution163 // |ownedMessageSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private static final /* @@NonNull*/ CardinalitySolution Solution164 // |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private static final /* @@NonNull*/ CardinalitySolution Solution165 // |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private static final /* @@NonNull*/ CardinalitySolution Solution166 // |ownedNameExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution167 // |ownedOperations|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		private static final /* @@NonNull*/ CardinalitySolution Solution168 // |ownedPackages|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		private static final /* @@NonNull*/ CardinalitySolution Solution169 // |ownedParameters|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private static final /* @@NonNull*/ CardinalitySolution Solution170 // |ownedParameters|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		private static final /* @@NonNull*/ CardinalitySolution Solution171 // |ownedParts|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution172 // |ownedParts|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution173 // |ownedParts|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution174 // |ownedParts|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution175 // |ownedParts|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution176 // |ownedParts|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution177 // |ownedPathElements|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution178 // |ownedPathName|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution179 // |ownedPathName|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution180 // |ownedPathName|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution181 // |ownedPathName|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution182 // |ownedPathName|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution183 // |ownedPatternGuard|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private static final /* @@NonNull*/ CardinalitySolution Solution184 // |ownedPatternType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution185 // |ownedPostconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		private static final /* @@NonNull*/ CardinalitySolution Solution186 // |ownedPreconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		private static final /* @@NonNull*/ CardinalitySolution Solution187 // |ownedProperties|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		private static final /* @@NonNull*/ CardinalitySolution Solution188 // |ownedReferences|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
		private static final /* @@NonNull*/ CardinalitySolution Solution189 // |ownedRight|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private static final /* @@NonNull*/ CardinalitySolution Solution190 // |ownedRoundBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private static final /* @@NonNull*/ CardinalitySolution Solution191 // |ownedRoundBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private static final /* @@NonNull*/ CardinalitySolution Solution192 // |ownedSignature|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private static final /* @@NonNull*/ CardinalitySolution Solution193 // |ownedSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private static final /* @@NonNull*/ CardinalitySolution Solution194 // |ownedSquareBracketedClauses|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private static final /* @@NonNull*/ CardinalitySolution Solution195 // |ownedSubstitutions|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private static final /* @@NonNull*/ CardinalitySolution Solution196 // |ownedSuperTypes|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		private static final /* @@NonNull*/ CardinalitySolution Solution197 // |ownedTerms|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private static final /* @@NonNull*/ CardinalitySolution Solution198 // |ownedThenExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution199 // |ownedThenExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private static final /* @@NonNull*/ CardinalitySolution Solution200 // |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution201 // |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution202 // |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution203 // |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution204 // |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution205 // |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution206 // |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution207 // |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution208 // |ownedValueType|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private static final /* @@NonNull*/ CardinalitySolution Solution209 // |ownedValue|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private static final /* @@NonNull*/ CardinalitySolution Solution210 // |ownedVariables|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private static final /* @@NonNull*/ CardinalitySolution Solution211 // |patternVariableName|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution212 // |prefix.','|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ',');
		private static final /* @@NonNull*/ CardinalitySolution Solution213 // |prefix.';'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ';');
		private static final /* @@NonNull*/ CardinalitySolution Solution214 // |prefix.'|'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, '|');
		private static final /* @@NonNull*/ CardinalitySolution Solution215 // |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile');
		private static final /* @@NonNull*/ CardinalitySolution Solution216 // |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile');
		private static final /* @@NonNull*/ CardinalitySolution Solution217 // |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique');
		private static final /* @@NonNull*/ CardinalitySolution Solution218 // |qualifiers.'!ordered|!unique|ordered|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, '!ordered|!unique|ordered|unique');
		private static final /* @@NonNull*/ CardinalitySolution Solution219 // |qualifiers.'definition'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 'definition');
		private static final /* @@NonNull*/ CardinalitySolution Solution220 // |qualifiers.'static'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, 'static');
		private static final /* @@NonNull*/ CardinalitySolution Solution221 // |referredElement|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private static final /* @@NonNull*/ CardinalitySolution Solution222 // |referredKeys|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		private static final /* @@NonNull*/ CardinalitySolution Solution223 // |referredOpposite|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
		private static final /* @@NonNull*/ CardinalitySolution Solution224 // |referredProperty|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private static final /* @@NonNull*/ CardinalitySolution Solution225 // |restVariableName|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private static final /* @@NonNull*/ CardinalitySolution Solution226 // |segments|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private static final /* @@NonNull*/ CardinalitySolution Solution227 // |stereotype.'invariant'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 'invariant');
		private static final /* @@NonNull*/ CardinalitySolution Solution228 // |stereotype.'postcondition'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 'postcondition');
		private static final /* @@NonNull*/ CardinalitySolution Solution229 // |stereotype.'precondition'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, 'precondition');
		private static final /* @@NonNull*/ CardinalitySolution Solution230 // |stringBounds.'*|+|?'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, '*|+|?');
		private static final /* @@NonNull*/ CardinalitySolution Solution231 // |symbol.'false|true'|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, 'false|true');
		private static final /* @@NonNull*/ CardinalitySolution Solution232 // |symbol|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private static final /* @@NonNull*/ CardinalitySolution Solution233 // |upperBound|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private static final /* @@NonNull*/ CardinalitySolution Solution234 // |values|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS__VALUES);
		private static final /* @@NonNull*/ CardinalitySolution Solution235 // |value|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		private static final /* @@NonNull*/ CardinalitySolution Solution236 // |value|
			= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);

		private static final /* @@NonNull*/ CardinalitySolutionStep Solve000 // assert (|exprString| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution000);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve001 // assert (|lowerBound| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution004);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve002 // assert (|name.'Map'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution005);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve003 // assert (|name.'Tuple'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution006);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve004 // assert (|name| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution007);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve005 // assert (|name| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution008);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve006 // assert (|name| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution009);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve007 // assert (|ownedActualParameter| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution010);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve008 // assert (|ownedBinding| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution014);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve009 // assert (|ownedCoIterator| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution016);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve010 // assert (|ownedCondition| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution018);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve011 // assert (|ownedCondition| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution017);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve012 // assert (|ownedDetails| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution020);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve013 // assert (|ownedElseExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution022);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve014 // assert (|ownedExpressionCS| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution025);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve015 // assert (|ownedExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution026);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve016 // assert (|ownedExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution029);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve017 // assert (|ownedExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution027);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve018 // assert (|ownedExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution028);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve019 // assert (|ownedInExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution032);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve020 // assert (|ownedInitExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution034);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve021 // assert (|ownedInitExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution033);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve022 // assert (|ownedInitExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution035);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve023 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution036);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve024 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution037);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve025 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution038);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve026 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution039);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve027 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution040);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve028 // assert (|ownedKey| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution041);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve029 // assert (|ownedLeft| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution042);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve030 // assert (|ownedNameExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution043);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve031 // assert (|ownedPathElements| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution057);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve032 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution062);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve033 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution058);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve034 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution059);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve035 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution060);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve036 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution061);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve037 // assert (|ownedPatternType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution063);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve038 // assert (|ownedRight| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution064);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve039 // assert (|ownedThenExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution069);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve040 // assert (|ownedThenExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution070);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve041 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution076);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve042 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution074);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve043 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution075);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve044 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution073);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve045 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution071);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve046 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution072);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve047 // assert (|ownedValue| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution077);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve048 // assert (|prefix.','| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution079);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve049 // assert (|prefix.';'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution080);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve050 // assert (|prefix.'|'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution081);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve051 // assert (|qualifiers.'definition'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution086);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve052 // assert (|qualifiers.'static'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution087);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve053 // assert (|referredElement| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution088);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve054 // assert (|referredProperty| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution091);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve055 // assert (|stereotype.'invariant'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution092);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve056 // assert (|stereotype.'postcondition'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution093);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve057 // assert (|stereotype.'precondition'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution094);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve058 // assert (|stringBounds.'*|+|?'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution095);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve059 // assert (|symbol.'false|true'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution096);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve060 // assert (|symbol| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(Solution097);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve061 // assign V0 = (|ownedArguments| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution013);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve062 // assign V0 = (|ownedExtends| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution031);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve063 // assign V0 = (|ownedParameters| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution044);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve064 // assign V0 = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution047);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve065 // assign V0 = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution054);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve066 // assign V0 = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution053);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve067 // assign V0 = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution055);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve068 // assign V0 = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution056);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve069 // assign V0 = (|ownedPathElements| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution057);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve070 // assign V0 = (|ownedSubstitutions| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution065);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve071 // assign V0 = (|ownedTerms| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution068);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve072 // assign V0 = (|ownedVariables| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution078);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve073 // assign V0 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution085);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve074 // assign V0 = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution098);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve075 // assign V0 = |isAbstract.'abstract'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution108);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve076 // assign V0 = |isCallable.'callable'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution110);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve077 // assign V0 = |isNullFree.'|1'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution112);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve078 // assign V0 = |isPrimitive.'primitive'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution114);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve079 // assign V0 = |literal|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution117);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve080 // assign V0 = |name|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution122);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve081 // assign V0 = |nsPrefix|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution124);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve082 // assign V0 = |ownedCoIterator|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution132);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve083 // assign V0 = |ownedCurlyBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution138);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve084 // assign V0 = |ownedDetails|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution141);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve085 // assign V0 = |ownedExtends|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution150);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve086 // assign V0 = |ownedIfThenExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution151);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve087 // assign V0 = |ownedInitExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution156);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve088 // assign V0 = |ownedLastExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution160);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve089 // assign V0 = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution164);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve090 // assign V0 = |ownedRoundBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution190);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve091 // assign V0 = |ownedSignature|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution192);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve092 // assign V0 = |ownedSquareBracketedClauses|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution194);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve093 // assign V0 = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution207);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve094 // assign V0 = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution206);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve095 // assign V0 = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution202);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve096 // assign V0 = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution204);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve097 // assign V0 = |ownedValueType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution208);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve098 // assign V0 = |patternVariableName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution211);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve099 // assign V0 = |qualifiers.'definition'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution219);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve100 // assign V0 = |qualifiers.'static'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution220);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve101 // assign V0 = |referredOpposite|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution223);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve102 // assign V0 = |restVariableName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution225);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve103 // assign V0 = |segments|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution226);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve104 // assign V0 = |upperBound|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution233);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve105 // assign V0 = |values|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution234);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve106 // assign V0 = |value|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, Solution235);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve107 // assign V1 = (|ownedArguments| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution012);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve108 // assign V1 = (|ownedDetails| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution021);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve109 // assign V1 = (|ownedExtends| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution030);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve110 // assign V1 = (|ownedParameters| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution046);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve111 // assign V1 = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution050);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve112 // assign V1 = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution051);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve113 // assign V1 = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution052);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve114 // assign V1 = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution049);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve115 // assign V1 = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution056);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve116 // assign V1 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution085);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve117 // assign V1 = |default|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution105);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve118 // assign V1 = |instanceClassName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution107);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve119 // assign V1 = |isAll.'::*'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution109);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve120 // assign V1 = |isNullFree.'|1'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution112);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve121 // assign V1 = |name|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution122);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve122 // assign V1 = |nsURI|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution125);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve123 // assign V1 = |ownedCoIterator|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution132);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve124 // assign V1 = |ownedCollectionMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution133);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve125 // assign V1 = |ownedImports|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution153);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve126 // assign V1 = |ownedInitExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution156);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve127 // assign V1 = |ownedMessageSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution163);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve128 // assign V1 = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution165);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve129 // assign V1 = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution164);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve130 // assign V1 = |ownedPatternGuard|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution183);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve131 // assign V1 = |ownedRoundBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution191);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve132 // assign V1 = |ownedSignature|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution192);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve133 // assign V1 = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution207);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve134 // assign V1 = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution204);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve135 // assign V1 = |qualifiers.'!ordered|!unique|ordered|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution218);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve136 // assign V1 = |referredOpposite|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution223);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve137 // assign V1 = |value|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, Solution236);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve138 // assign V10 = (|ownedDefaultExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, Solution019);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve139 // assign V10 = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, Solution098);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve140 // assign V10 = |ownedBodyExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, Solution130);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve141 // assign V10 = |ownedConstraints|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, Solution136);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve142 // assign V10 = |ownedPreconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, Solution186);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve143 // assign V11 = (|ownedBodyExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(11, Solution015);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve144 // assign V11 = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(11, Solution098);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve145 // assign V11 = |ownedBodyExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(11, Solution130);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve146 // assign V12 = (|ownedBodyExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, Solution015);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve147 // assign V12 = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, Solution098);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve148 // assign V12 = |ownedImplicitOpposites|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, Solution152);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve149 // assign V12 = |ownedPostconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, Solution185);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve150 // assign V13 = |ownedImplicitOpposites|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(13, Solution152);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve151 // assign V13 = |ownedPostconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(13, Solution185);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve152 // assign V2 = (|isSerializable.'serializable'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution003);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve153 // assign V2 = (|ownedDetails| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution020);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve154 // assign V2 = (|ownedParameters| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution045);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve155 // assign V2 = (|ownedParameters| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution046);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve156 // assign V2 = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution048);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve157 // assign V2 = (|ownedSuperTypes| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution067);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve158 // assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution083);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve159 // assign V2 = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution098);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve160 // assign V2 = |default|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution105);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve161 // assign V2 = |instanceClassName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution107);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve162 // assign V2 = |isSerializable.'serializable'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution116);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve163 // assign V2 = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution127);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve164 // assign V2 = |ownedCurlyBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution139);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve165 // assign V2 = |ownedMessageSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution163);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve166 // assign V2 = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution164);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve167 // assign V2 = |ownedPackages|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution168);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve168 // assign V2 = |ownedSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution193);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve169 // assign V2 = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution204);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve170 // assign V2 = |qualifiers.'!ordered|!unique|ordered|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, Solution218);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve171 // assign V3 = (|isSerializable.'serializable'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution002);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve172 // assign V3 = (|isSerializable.'serializable'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution003);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve173 // assign V3 = (|ownedAnnotations| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution011);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve174 // assign V3 = (|ownedParameters| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution045);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve175 // assign V3 = (|ownedSuperTypes| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution066);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve176 // assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution082);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve177 // assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution083);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve178 // assign V3 = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution098);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve179 // assign V3 = |default|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution105);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve180 // assign V3 = |isPre.'@'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution113);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve181 // assign V3 = |isSerializable.'serializable'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution115);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve182 // assign V3 = |isSerializable.'serializable'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution116);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve183 // assign V3 = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution127);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve184 // assign V3 = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution164);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve185 // assign V3 = |ownedPackages|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution168);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve186 // assign V3 = |ownedSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution193);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve187 // assign V3 = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution204);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve188 // assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, Solution216);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve189 // assign V4 = (|isSerializable.'serializable'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution002);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve190 // assign V4 = (|ownedExceptions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution024);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve191 // assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution082);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve192 // assign V4 = |instanceClassName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution107);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve193 // assign V4 = |isSerializable.'serializable'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution115);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve194 // assign V4 = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution127);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve195 // assign V4 = |ownedClasses|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution131);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve196 // assign V4 = |ownedContents|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution137);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve197 // assign V4 = |ownedLiterals|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution162);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve198 // assign V4 = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution204);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve199 // assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution215);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve200 // assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, Solution216);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve201 // assign V5 = (|isInterface.'interface'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution001);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve202 // assign V5 = (|ownedExceptions| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution023);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve203 // assign V5 = (|ownedExceptions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution024);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve204 // assign V5 = |isInterface.'interface'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution111);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve205 // assign V5 = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution127);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve206 // assign V5 = |ownedConstraints|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution136);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve207 // assign V5 = |ownedDefaultExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution140);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve208 // assign V5 = |ownedLiterals|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution162);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve209 // assign V5 = |ownedReferences|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution188);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve210 // assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, Solution215);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve211 // assign V6 = (|isInterface.'interface'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, Solution001);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve212 // assign V6 = (|ownedDefaultExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, Solution019);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve213 // assign V6 = (|ownedExceptions| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, Solution023);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve214 // assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, Solution084);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve215 // assign V6 = (|referredKeys| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, Solution090);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve216 // assign V6 = |isInterface.'interface'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, Solution111);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve217 // assign V6 = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, Solution127);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve218 // assign V6 = |ownedConstraints|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, Solution136);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve219 // assign V6 = |ownedDefaultExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, Solution140);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve220 // assign V7 = (|ownedDefaultExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, Solution019);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve221 // assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, Solution084);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve222 // assign V7 = (|referredKeys| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, Solution089);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve223 // assign V7 = (|referredKeys| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, Solution090);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve224 // assign V7 = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, Solution098);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve225 // assign V7 = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, Solution127);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve226 // assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, Solution217);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve227 // assign V8 = (|referredKeys| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, Solution089);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve228 // assign V8 = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, Solution098);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve229 // assign V8 = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, Solution127);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve230 // assign V8 = |ownedDefaultExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, Solution140);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve231 // assign V8 = |ownedOperations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, Solution167);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve232 // assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, Solution217);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve233 // assign V9 = (|ownedDefaultExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, Solution019);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve234 // assign V9 = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, Solution098);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve235 // assign V9 = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, Solution127);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve236 // assign V9 = |ownedDefaultExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, Solution140);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve237 // assign V9 = |ownedPreconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, Solution186);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve238 // assign V9 = |ownedProperties|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, Solution187);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve239 // check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, [ModelElementCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve240 // check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, [ModelElementRefCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve241 // check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, [DetailCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve242 // check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, [InvariantConstraintCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve243 // check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, [SpecificationCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve244 // check-rule basecs::ConstraintCS.ownedSpecification : OCLinEcore::SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, [SpecificationCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve245 // check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, [EnumerationLiteralCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve246 // check-rule basecs::ImportCS.ownedPathName : EssentialOCL::URIPathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, [URIPathNameCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve247 // check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, [AnnotationElementCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve248 // check-rule basecs::ModelElementRefCS.ownedPathName : Base::PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, [PathNameCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve249 // check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, [SpecificationCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve250 // check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, [TypedRefCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve251 // check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, [ParameterCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve252 // check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, [PostconditionConstraintCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve253 // check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, [PreconditionConstraintCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve254 // check-rule basecs::PackageCS.ownedClasses : OCLinEcore::ClassCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, [ClassCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve255 // check-rule basecs::PackageOwnerCS.ownedPackages : OCLinEcore::PackageCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, [PackageCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve256 // check-rule basecs::PathNameCS.ownedPathElements : Base::FirstPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, [FirstPathElementCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve257 // check-rule basecs::PathNameCS.ownedPathElements : Base::FirstPathElementCS|Base::NextPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, [FirstPathElementCS, NextPathElementCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve258 // check-rule basecs::PathNameCS.ownedPathElements : EssentialOCL::URIFirstPathElementCS|Base::NextPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, [URIFirstPathElementCS, NextPathElementCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve259 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, [ImplicitOppositeCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve260 // check-rule basecs::RootCS.ownedImports : OCLinEcore::ImportCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, [ImportCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve261 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, [SpecificationCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve262 // check-rule basecs::StructuredClassCS.ownedOperations : OCLinEcore::OperationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, [OperationCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve263 // check-rule basecs::StructuredClassCS.ownedProperties : OCLinEcore::StructuralFeatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, [StructuralFeatureCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve264 // check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, [TypedRefCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve265 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : Base::MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, [MultiplicityCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve266 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : Base::TemplateParameterSubstitutionCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, [TemplateParameterSubstitutionCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve267 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : Base::TypeRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, [TypeRefCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve268 // check-rule basecs::TemplateSignatureCS.ownedParameters : Base::TypeParameterCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, [TypeParameterCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve269 // check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, [TemplateSignatureCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve270 // check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, [TuplePartCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve271 // check-rule basecs::TypeParameterCS.ownedExtends : OCLinEcore::TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, [TypedRefCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve272 // check-rule basecs::TypedElementCS.ownedType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, [TypeExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve273 // check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, [TypedMultiplicityRefCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve274 // check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, [MultiplicityCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve275 // check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, [TemplateBindingCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve276 // check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, [PathNameCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve277 // check-rule basecs::WildcardTypeRefCS.ownedExtends : OCLinEcore::TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, [TypedRefCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve278 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, [CurlyBracketedClauseCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve279 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, [PathNameCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve280 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, [RoundBracketedClauseCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve281 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, [SquareBracketedClauseCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve282 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, [CollectionLiteralPartCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve283 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, [CollectionTypeCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve284 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve285 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : EssentialOCL::PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, [PatternExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve286 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve287 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : EssentialOCL::PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, [PatternExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve288 // check-rule essentialoclcs::CollectionPatternCS.ownedType : EssentialOCL::CollectionTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, [CollectionTypeCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve289 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, [MultiplicityCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve290 // check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, [TypeExpWithoutMultiplicityCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve291 // check-rule essentialoclcs::ContextCS.ownedExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve292 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : EssentialOCL::ShadowPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, [ShadowPartCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve293 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve294 // check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, [ExpCS, PatternExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve295 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve296 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, [ElseIfThenExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve297 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve298 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve299 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve300 // check-rule essentialoclcs::InfixExpCS.ownedLeft : EssentialOCL::PrefixedPrimaryExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, [PrefixedPrimaryExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve301 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve302 // check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve303 // check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, [LetVariableCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve304 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, [RoundBracketedClauseCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve305 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, [MapLiteralPartCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve306 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, [MapTypeCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve307 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve308 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve309 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, [TypeExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve310 // check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, [TypeExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve311 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, [CoIteratorVariableCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve312 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve313 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, [NavigatingArgExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve314 // check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, [TypeExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve315 // check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve316 // check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve317 // check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedLetExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, [PrefixedLetExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve318 // check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedPrimaryExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, [PrefixedPrimaryExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve319 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, [TypeExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve320 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : EssentialOCL::NavigatingArgCS|EssentialOCL::NavigatingBarArgCS|EssentialOCL::NavigatingSemiArgCS|EssentialOCL::NavigatingCommaArgCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, [NavigatingArgCS, NavigatingBarArgCS, NavigatingSemiArgCS, NavigatingCommaArgCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve321 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, [ExpCS, PatternExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve322 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : EssentialOCL::StringLiteralExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, [StringLiteralExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve323 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve324 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, [TupleLiteralPartCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve325 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, [TypeLiteralWithMultiplicityCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve326 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, [CurlyBracketedClauseCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve327 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : Base::PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, [PathNameCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve328 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve329 // check-rule essentialoclcs::VariableCS.ownedInitExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, [ExpCS]);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve330 // check-rule essentialoclcs::VariableCS.ownedType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, [TypeExpCS]);
	}

	private static class _Steps
	{
		private static final /* @@NonNull*/ RTSerializationStep Step000 // 1*'!serializable'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "!serializable");
		private static final /* @@NonNull*/ RTSerializationStep Step001 // 1*'#'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "#");
		private static final /* @@NonNull*/ RTSerializationStep Step002 // 1*'&&'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "&&");
		private static final /* @@NonNull*/ RTSerializationStep Step003 // 1*'('
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "(");
		private static final /* @@NonNull*/ RTSerializationStep Step004 // 1*')'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ")");
		private static final /* @@NonNull*/ RTSerializationStep Step005 // 1*'*'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "*");
		private static final /* @@NonNull*/ RTSerializationStep Step006 // 1*'++'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "++");
		private static final /* @@NonNull*/ RTSerializationStep Step007 // 1*','
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ",");
		private static final /* @@NonNull*/ RTSerializationStep Step008 // 1*'..'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "..");
		private static final /* @@NonNull*/ RTSerializationStep Step009 // 1*':'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ":");
		private static final /* @@NonNull*/ RTSerializationStep Step010 // 1*'::'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "::");
		private static final /* @@NonNull*/ RTSerializationStep Step011 // 1*';'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ";");
		private static final /* @@NonNull*/ RTSerializationStep Step012 // 1*'<'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "<");
		private static final /* @@NonNull*/ RTSerializationStep Step013 // 1*'<-'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "<-");
		private static final /* @@NonNull*/ RTSerializationStep Step014 // 1*'='
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "=");
		private static final /* @@NonNull*/ RTSerializationStep Step015 // 1*'>'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ">");
		private static final /* @@NonNull*/ RTSerializationStep Step016 // 1*'?'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "?");
		private static final /* @@NonNull*/ RTSerializationStep Step017 // 1*'@'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "@");
		private static final /* @@NonNull*/ RTSerializationStep Step018 // 1*'Lambda'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Lambda");
		private static final /* @@NonNull*/ RTSerializationStep Step019 // 1*'Map'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Map");
		private static final /* @@NonNull*/ RTSerializationStep Step020 // 1*'Tuple'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Tuple");
		private static final /* @@NonNull*/ RTSerializationStep Step021 // 1*'['
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "[");
		private static final /* @@NonNull*/ RTSerializationStep Step022 // 1*']'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "]");
		private static final /* @@NonNull*/ RTSerializationStep Step023 // 1*'annotation'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "annotation");
		private static final /* @@NonNull*/ RTSerializationStep Step024 // 1*'attribute'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "attribute");
		private static final /* @@NonNull*/ RTSerializationStep Step025 // 1*'body'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "body");
		private static final /* @@NonNull*/ RTSerializationStep Step026 // 1*'class'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "class");
		private static final /* @@NonNull*/ RTSerializationStep Step027 // 1*'datatype'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "datatype");
		private static final /* @@NonNull*/ RTSerializationStep Step028 // 1*'definition'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "definition");
		private static final /* @@NonNull*/ RTSerializationStep Step029 // 1*'derivation'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "derivation");
		private static final /* @@NonNull*/ RTSerializationStep Step030 // 1*'documentation'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "documentation");
		private static final /* @@NonNull*/ RTSerializationStep Step031 // 1*'else'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "else");
		private static final /* @@NonNull*/ RTSerializationStep Step032 // 1*'elseif'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "elseif");
		private static final /* @@NonNull*/ RTSerializationStep Step033 // 1*'endif'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "endif");
		private static final /* @@NonNull*/ RTSerializationStep Step034 // 1*'enum'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "enum");
		private static final /* @@NonNull*/ RTSerializationStep Step035 // 1*'extends'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "extends");
		private static final /* @@NonNull*/ RTSerializationStep Step036 // 1*'if'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "if");
		private static final /* @@NonNull*/ RTSerializationStep Step037 // 1*'import'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "import");
		private static final /* @@NonNull*/ RTSerializationStep Step038 // 1*'in'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "in");
		private static final /* @@NonNull*/ RTSerializationStep Step039 // 1*'initial'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "initial");
		private static final /* @@NonNull*/ RTSerializationStep Step040 // 1*'invalid'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "invalid");
		private static final /* @@NonNull*/ RTSerializationStep Step041 // 1*'invariant'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "invariant");
		private static final /* @@NonNull*/ RTSerializationStep Step042 // 1*'key'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "key");
		private static final /* @@NonNull*/ RTSerializationStep Step043 // 1*'let'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "let");
		private static final /* @@NonNull*/ RTSerializationStep Step044 // 1*'literal'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "literal");
		private static final /* @@NonNull*/ RTSerializationStep Step045 // 1*'module'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "module");
		private static final /* @@NonNull*/ RTSerializationStep Step046 // 1*'null'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "null");
		private static final /* @@NonNull*/ RTSerializationStep Step047 // 1*'operation'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "operation");
		private static final /* @@NonNull*/ RTSerializationStep Step048 // 1*'opposite'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "opposite");
		private static final /* @@NonNull*/ RTSerializationStep Step049 // 1*'package'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "package");
		private static final /* @@NonNull*/ RTSerializationStep Step050 // 1*'postcondition'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "postcondition");
		private static final /* @@NonNull*/ RTSerializationStep Step051 // 1*'pre'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "pre");
		private static final /* @@NonNull*/ RTSerializationStep Step052 // 1*'precondition'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "precondition");
		private static final /* @@NonNull*/ RTSerializationStep Step053 // 1*'property'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "property");
		private static final /* @@NonNull*/ RTSerializationStep Step054 // 1*'reference'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "reference");
		private static final /* @@NonNull*/ RTSerializationStep Step055 // 1*'self'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "self");
		private static final /* @@NonNull*/ RTSerializationStep Step056 // 1*'static'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "static");
		private static final /* @@NonNull*/ RTSerializationStep Step057 // 1*'sysml'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "sysml");
		private static final /* @@NonNull*/ RTSerializationStep Step058 // 1*'then'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "then");
		private static final /* @@NonNull*/ RTSerializationStep Step059 // 1*'throws'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "throws");
		private static final /* @@NonNull*/ RTSerializationStep Step060 // 1*'{'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "{");
		private static final /* @@NonNull*/ RTSerializationStep Step061 // 1*'|'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "|");
		private static final /* @@NonNull*/ RTSerializationStep Step062 // 1*'|?'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "|?");
		private static final /* @@NonNull*/ RTSerializationStep Step063 // 1*'}'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "}");
		private static final /* @@NonNull*/ RTSerializationStep Step064 // 1*default=SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step065 // 1*exprString=UNQUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, "OCLinEcore::UNQUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step066 // 1*instanceClassName=SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step067 // 1*literal=SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step068 // 1*lowerBound=LOWER
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, "Base::LOWER");
		private static final /* @@NonNull*/ RTSerializationStep Step069 // 1*name=BinaryOperatorName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, "EssentialOCL::BinaryOperatorName");
		private static final /* @@NonNull*/ RTSerializationStep Step070 // 1*name=CollectionTypeIdentifier
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, "EssentialOCL::CollectionTypeIdentifier");
		private static final /* @@NonNull*/ RTSerializationStep Step071 // 1*name=EnumerationLiteralName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, "OCLinEcore::EnumerationLiteralName");
		private static final /* @@NonNull*/ RTSerializationStep Step072 // 1*name=PrimitiveTypeIdentifier
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, "EssentialOCL::PrimitiveTypeIdentifier");
		private static final /* @@NonNull*/ RTSerializationStep Step073 // 1*name=UnaryOperatorName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, "EssentialOCL::UnaryOperatorName");
		private static final /* @@NonNull*/ RTSerializationStep Step074 // 1*name=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, "OCLinEcore::UnrestrictedName");
		private static final /* @@NonNull*/ RTSerializationStep Step075 // 1*name=UnrestrictedName|SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME , "OCLinEcore::UnrestrictedName", "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step076 // 1*next-10-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(-1, 1, 11);
		private static final /* @@NonNull*/ RTSerializationStep Step077 // 1*nsPrefix=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, "OCLinEcore::UnrestrictedName");
		private static final /* @@NonNull*/ RTSerializationStep Step078 // 1*nsURI=URI
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_URI, "Base::URI");
		private static final /* @@NonNull*/ RTSerializationStep Step079 // 1*ownedActualParameter=TypeRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, "Base::TypeRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step080 // 1*ownedArguments+=NavigatingArgCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, "EssentialOCL::NavigatingArgCS");
		private static final /* @@NonNull*/ RTSerializationStep Step081 // 1*ownedBinding=TemplateBindingCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, "Base::TemplateBindingCS");
		private static final /* @@NonNull*/ RTSerializationStep Step082 // 1*ownedCoIterator=CoIteratorVariableCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, "EssentialOCL::CoIteratorVariableCS");
		private static final /* @@NonNull*/ RTSerializationStep Step083 // 1*ownedCondition=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step084 // 1*ownedCondition=ExpCS|PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION , "EssentialOCL::ExpCS", "EssentialOCL::PatternExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step085 // 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, "EssentialOCL::CurlyBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step086 // 1*ownedDetails+=DetailCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, "OCLinEcore::DetailCS");
		private static final /* @@NonNull*/ RTSerializationStep Step087 // 1*ownedElseExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step088 // 1*ownedExceptions+=TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, "OCLinEcore::TypedRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step089 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step090 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step091 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step092 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step093 // 1*ownedExpression=PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, "EssentialOCL::PatternExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step094 // 1*ownedExpressionCS=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step095 // 1*ownedExtends+=TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, "OCLinEcore::TypedRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step096 // 1*ownedExtends=TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, "OCLinEcore::TypedRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step097 // 1*ownedImplicitOpposites+=ImplicitOppositeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, "OCLinEcore::ImplicitOppositeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step098 // 1*ownedInExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step099 // 1*ownedInitExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step100 // 1*ownedInitExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step101 // 1*ownedInitExpression=ExpCS|PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION , "EssentialOCL::ExpCS", "EssentialOCL::PatternExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step102 // 1*ownedInitExpression=StringLiteralExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, "EssentialOCL::StringLiteralExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step103 // 1*ownedKey=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step104 // 1*ownedKeyType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step105 // 1*ownedLastExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step106 // 1*ownedLeft=PrefixedPrimaryExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, "EssentialOCL::PrefixedPrimaryExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step107 // 1*ownedMessageSpecification=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step108 // 1*ownedNameExpression=NavigatingArgExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, "EssentialOCL::NavigatingArgExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step109 // 1*ownedParameters+=ParameterCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, "OCLinEcore::ParameterCS");
		private static final /* @@NonNull*/ RTSerializationStep Step110 // 1*ownedParameters+=TypeParameterCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, "Base::TypeParameterCS");
		private static final /* @@NonNull*/ RTSerializationStep Step111 // 1*ownedParts+=CollectionLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, "EssentialOCL::CollectionLiteralPartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step112 // 1*ownedParts+=MapLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, "EssentialOCL::MapLiteralPartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step113 // 1*ownedParts+=PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, "EssentialOCL::PatternExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step114 // 1*ownedParts+=ShadowPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, "EssentialOCL::ShadowPartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step115 // 1*ownedParts+=TupleLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, "EssentialOCL::TupleLiteralPartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step116 // 1*ownedParts+=TuplePartCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, "EssentialOCL::TuplePartCS");
		private static final /* @@NonNull*/ RTSerializationStep Step117 // 1*ownedPathElements+=FirstPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, "Base::FirstPathElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step118 // 1*ownedPathElements+=NextPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, "Base::NextPathElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step119 // 1*ownedPathElements+=URIFirstPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, "EssentialOCL::URIFirstPathElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step120 // 1*ownedPathName=PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, "Base::PathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step121 // 1*ownedPathName=PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, "Base::PathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step122 // 1*ownedPathName=PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, "Base::PathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step123 // 1*ownedPathName=PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, "Base::PathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step124 // 1*ownedPathName=URIPathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, "EssentialOCL::URIPathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step125 // 1*ownedPatternGuard=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step126 // 1*ownedPatternType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step127 // 1*ownedRight=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step128 // 1*ownedRight=PrefixedLetExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, "EssentialOCL::PrefixedLetExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step129 // 1*ownedRight=PrefixedPrimaryExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, "EssentialOCL::PrefixedPrimaryExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step130 // 1*ownedSubstitutions+=TemplateParameterSubstitutionCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, "Base::TemplateParameterSubstitutionCS");
		private static final /* @@NonNull*/ RTSerializationStep Step131 // 1*ownedSuperTypes+=TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, "OCLinEcore::TypedRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step132 // 1*ownedTerms+=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step133 // 1*ownedThenExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step134 // 1*ownedThenExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step135 // 1*ownedType=CollectionTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, "EssentialOCL::CollectionTypeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step136 // 1*ownedType=CollectionTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, "EssentialOCL::CollectionTypeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step137 // 1*ownedType=MapTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, "EssentialOCL::MapTypeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step138 // 1*ownedType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step139 // 1*ownedType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step140 // 1*ownedType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step141 // 1*ownedType=TypeExpWithoutMultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, "EssentialOCL::TypeExpWithoutMultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step142 // 1*ownedType=TypeLiteralWithMultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, "EssentialOCL::TypeLiteralWithMultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step143 // 1*ownedType=TypedMultiplicityRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, "OCLinEcore::TypedMultiplicityRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step144 // 1*ownedValue=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step145 // 1*ownedValueType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step146 // 1*ownedVariables+=LetVariableCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, "EssentialOCL::LetVariableCS");
		private static final /* @@NonNull*/ RTSerializationStep Step147 // 1*qualifiers
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		private static final /* @@NonNull*/ RTSerializationStep Step148 // 1*referredElement=URI
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private static final /* @@NonNull*/ RTSerializationStep Step149 // 1*referredElement=UnreservedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private static final /* @@NonNull*/ RTSerializationStep Step150 // 1*referredElement=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private static final /* @@NonNull*/ RTSerializationStep Step151 // 1*referredElement=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private static final /* @@NonNull*/ RTSerializationStep Step152 // 1*referredKeys+=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		private static final /* @@NonNull*/ RTSerializationStep Step153 // 1*referredKeys+=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		private static final /* @@NonNull*/ RTSerializationStep Step154 // 1*referredOpposite=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
		private static final /* @@NonNull*/ RTSerializationStep Step155 // 1*referredProperty=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private static final /* @@NonNull*/ RTSerializationStep Step156 // 1*restVariableName=Identifier
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, "Base::Identifier");
		private static final /* @@NonNull*/ RTSerializationStep Step157 // 1*stringBounds
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
		private static final /* @@NonNull*/ RTSerializationStep Step158 // 1*symbol
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL);
		private static final /* @@NonNull*/ RTSerializationStep Step159 // 1*symbol=NUMBER_LITERAL
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, "Base::NUMBER_LITERAL");
		private static final /* @@NonNull*/ RTSerializationStep Step160 // 1*upperBound=UPPER
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, "Base::UPPER");
		private static final /* @@NonNull*/ RTSerializationStep Step161 // 1*value=SIGNED
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, "OCLinEcore::SIGNED");
		private static final /* @@NonNull*/ RTSerializationStep Step162 // V00*'abstract'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "abstract");
		private static final /* @@NonNull*/ RTSerializationStep Step163 // V00*'callable'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "callable");
		private static final /* @@NonNull*/ RTSerializationStep Step164 // V00*'definition'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "definition");
		private static final /* @@NonNull*/ RTSerializationStep Step165 // V00*'primitive'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "primitive");
		private static final /* @@NonNull*/ RTSerializationStep Step166 // V00*'static'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "static");
		private static final /* @@NonNull*/ RTSerializationStep Step167 // V00*'|1'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "|1");
		private static final /* @@NonNull*/ RTSerializationStep Step168 // V00*name=UnrestrictedName|SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME , "OCLinEcore::UnrestrictedName", "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step169 // V00*next-2-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(0, 4, 6);
		private static final /* @@NonNull*/ RTSerializationStep Step170 // V00*ownedIfThenExpressions+=ElseIfThenExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, "EssentialOCL::ElseIfThenExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step171 // V00*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step172 // V00*ownedRoundBracketedClause=RoundBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, "EssentialOCL::RoundBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step173 // V00*ownedSignature=TemplateSignatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, "OCLinEcore::TemplateSignatureCS");
		private static final /* @@NonNull*/ RTSerializationStep Step174 // V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, "EssentialOCL::SquareBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step175 // V00*patternVariableName=UnrestrictedName
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, "OCLinEcore::UnrestrictedName");
		private static final /* @@NonNull*/ RTSerializationStep Step176 // V00*segments+=StringLiteral
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, "Base::StringLiteral");
		private static final /* @@NonNull*/ RTSerializationStep Step177 // V00*value=SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, "Base::SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step178 // V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS__VALUES , "Base::SINGLE_QUOTED_STRING", "Base::ML_SINGLE_QUOTED_STRING");
		private static final /* @@NonNull*/ RTSerializationStep Step179 // V01*'::*'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(1, "::*");
		private static final /* @@NonNull*/ RTSerializationStep Step180 // V01*'|1'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(1, "|1");
		private static final /* @@NonNull*/ RTSerializationStep Step181 // V01*next-6-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(1, 4, 10);
		private static final /* @@NonNull*/ RTSerializationStep Step182 // V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS , "EssentialOCL::NavigatingCommaArgCS", "EssentialOCL::NavigatingSemiArgCS", "EssentialOCL::NavigatingBarArgCS");
		private static final /* @@NonNull*/ RTSerializationStep Step183 // V01*ownedCollectionMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step184 // V01*ownedImports+=ImportCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, "OCLinEcore::ImportCS");
		private static final /* @@NonNull*/ RTSerializationStep Step185 // V01*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step186 // V01*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step187 // V01*ownedRoundBracketedClause=RoundBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, "EssentialOCL::RoundBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step188 // V01*ownedSignature=TemplateSignatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, "OCLinEcore::TemplateSignatureCS");
		private static final /* @@NonNull*/ RTSerializationStep Step189 // V02*next-2-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(2, 7, 9);
		private static final /* @@NonNull*/ RTSerializationStep Step190 // V02*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step191 // V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, "EssentialOCL::CurlyBracketedClauseCS");
		private static final /* @@NonNull*/ RTSerializationStep Step192 // V02*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step193 // V02*ownedPackages+=PackageCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, "OCLinEcore::PackageCS");
		private static final /* @@NonNull*/ RTSerializationStep Step194 // V02*ownedSpecification=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step195 // V03*'serializable'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(3, "serializable");
		private static final /* @@NonNull*/ RTSerializationStep Step196 // V03*next-1-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(3, 12, 13);
		private static final /* @@NonNull*/ RTSerializationStep Step197 // V03*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step198 // V03*ownedMultiplicity=MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, "Base::MultiplicityCS");
		private static final /* @@NonNull*/ RTSerializationStep Step199 // V03*ownedPackages+=PackageCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, "OCLinEcore::PackageCS");
		private static final /* @@NonNull*/ RTSerializationStep Step200 // V03*ownedSpecification=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step201 // V04*'serializable'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(4, "serializable");
		private static final /* @@NonNull*/ RTSerializationStep Step202 // V04*next-1-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(4, 14, 15);
		private static final /* @@NonNull*/ RTSerializationStep Step203 // V04*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step204 // V04*ownedClasses+=ClassCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, "OCLinEcore::ClassCS");
		private static final /* @@NonNull*/ RTSerializationStep Step205 // V04*ownedContents+=ModelElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, "OCLinEcore::ModelElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step206 // V04*ownedLiterals+=EnumerationLiteralCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, "OCLinEcore::EnumerationLiteralCS");
		private static final /* @@NonNull*/ RTSerializationStep Step207 // V05*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(5, 17, 21);
		private static final /* @@NonNull*/ RTSerializationStep Step208 // V05*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step209 // V05*ownedConstraints+=InvariantConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, "OCLinEcore::InvariantConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step210 // V05*ownedLiterals+=EnumerationLiteralCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, "OCLinEcore::EnumerationLiteralCS");
		private static final /* @@NonNull*/ RTSerializationStep Step211 // V05*ownedReferences+=ModelElementRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, "OCLinEcore::ModelElementRefCS");
		private static final /* @@NonNull*/ RTSerializationStep Step212 // V06*'interface'
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(6, "interface");
		private static final /* @@NonNull*/ RTSerializationStep Step213 // V06*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(6, 19, 23);
		private static final /* @@NonNull*/ RTSerializationStep Step214 // V06*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step215 // V06*ownedConstraints+=InvariantConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, "OCLinEcore::InvariantConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step216 // V06*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step217 // V07*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(7, 22, 26);
		private static final /* @@NonNull*/ RTSerializationStep Step218 // V07*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(7, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step219 // V07*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(7, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step220 // V08*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(8, 24, 28);
		private static final /* @@NonNull*/ RTSerializationStep Step221 // V08*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step222 // V08*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step223 // V08*ownedOperations+=OperationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, "OCLinEcore::OperationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step224 // V09*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(9, 29, 33);
		private static final /* @@NonNull*/ RTSerializationStep Step225 // V09*ownedAnnotations+=AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, "OCLinEcore::AnnotationElementCS");
		private static final /* @@NonNull*/ RTSerializationStep Step226 // V09*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step227 // V09*ownedPreconditions+=PreconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, "OCLinEcore::PreconditionConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step228 // V09*ownedProperties+=StructuralFeatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, "OCLinEcore::StructuralFeatureCS");
		private static final /* @@NonNull*/ RTSerializationStep Step229 // V10*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(10, 29, 33);
		private static final /* @@NonNull*/ RTSerializationStep Step230 // V10*ownedConstraints+=InvariantConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, "OCLinEcore::InvariantConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step231 // V10*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step232 // V10*ownedPreconditions+=PreconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, "OCLinEcore::PreconditionConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step233 // V11*next-4-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(11, 31, 35);
		private static final /* @@NonNull*/ RTSerializationStep Step234 // V11*ownedBodyExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(11, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step235 // V11*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(11, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step236 // V12*next-2-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(12, 37, 39);
		private static final /* @@NonNull*/ RTSerializationStep Step237 // V12*ownedBodyExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step238 // V12*ownedDefaultExpressions+=SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, "OCLinEcore::SpecificationCS");
		private static final /* @@NonNull*/ RTSerializationStep Step239 // V12*ownedPostconditions+=PostconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, "OCLinEcore::PostconditionConstraintCS");
		private static final /* @@NonNull*/ RTSerializationStep Step240 // V13*next-2-steps
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(13, 39, 41);
		private static final /* @@NonNull*/ RTSerializationStep Step241 // V13*ownedPostconditions+=PostconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(13, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, "OCLinEcore::PostconditionConstraintCS");
	}

	private static class _Segments
	{
		private static final /* @@NonNull*/ Segment [] Segments0 // []
			= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
				};
		private static final /* @@NonNull*/ Segment [] Segments1 // [supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport, «value»]
			= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.createCustomSegment(null, org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport.class) /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */};
		private static final /* @@NonNull*/ Segment [] Segments2 // [«! », «value», «! »]
			= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */};
		private static final /* @@NonNull*/ Segment [] Segments3 // [«! », «value», «? »]
			= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */};
		private static final /* @@NonNull*/ Segment [] Segments4 // [«! », «value», «?\n»]
			= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_NEW_LINE /* «?\n» */};
		private static final /* @@NonNull*/ Segment [] Segments5 // [«! », «value»]
			= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */};
		private static final /* @@NonNull*/ Segment [] Segments6 // [«-», «? », «value», «?\n»]
			= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.POP /* «-» */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_NEW_LINE /* «?\n» */};
		private static final /* @@NonNull*/ Segment [] Segments7 // [«? », «value», «+», «?\n»]
			= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.PUSH /* «+» */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_NEW_LINE /* «?\n» */};
		private static final /* @@NonNull*/ Segment [] Segments8 // [«? », «value», «? »]
			= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
				org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */};
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step023 /* 1*'annotation' */,
						_Steps.Step168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve240 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve209 /* assign V5 = |ownedReferences| */,
						_Steps.Solve196 /* assign V4 = |ownedContents| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step023 /* 1*'annotation' */,
						_Steps.Step168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve240 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve209 /* assign V5 = |ownedReferences| */,
						_Steps.Solve196 /* assign V4 = |ownedContents| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step023 /* 1*'annotation' */,
						_Steps.Step168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve240 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve209 /* assign V5 = |ownedReferences| */,
						_Steps.Solve196 /* assign V4 = |ownedContents| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step023 /* 1*'annotation' */,
						_Steps.Step168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step023 /* 1*'annotation' */,
						_Steps.Step168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve240 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve209 /* assign V5 = |ownedReferences| */,
						_Steps.Solve196 /* assign V4 = |ownedContents| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step023 /* 1*'annotation' */,
						_Steps.Step168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve240 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve209 /* assign V5 = |ownedReferences| */,
						_Steps.Solve196 /* assign V4 = |ownedContents| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step023 /* 1*'annotation' */,
						_Steps.Step168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve240 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve209 /* assign V5 = |ownedReferences| */,
						_Steps.Solve196 /* assign V4 = |ownedContents| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step023 /* 1*'annotation' */,
						_Steps.Step168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step205 /* V04*ownedContents+=ModelElementCS */,
						_Steps.Step211 /* V05*ownedReferences+=ModelElementRefCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve117 /* assign V1 = |default| */,
						_Steps.Solve096 /* assign V0 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve158 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve188 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step196 /* V03*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-16-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-16-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve117 /* assign V1 = |default| */,
						_Steps.Solve096 /* assign V0 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve158 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve188 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve207 /* assign V5 = |ownedDefaultExpressions| */,
						_Steps.Solve212 /* assign V6 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve224 /* assign V7 = 0 */,
						_Steps.Solve228 /* assign V8 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-26-steps */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step196 /* V03*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step207 /* V05*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step216 /* V06*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step222 /* V08*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve219 /* assign V6 = |ownedDefaultExpressions| */,
						_Steps.Solve220 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve228 /* assign V8 = 0 */,
						_Steps.Solve234 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-28-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step220 /* V08*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve219 /* assign V6 = |ownedDefaultExpressions| */,
						_Steps.Solve220 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve228 /* assign V8 = 0 */,
						_Steps.Solve234 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-28-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step220 /* V08*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve117 /* assign V1 = |default| */,
						_Steps.Solve096 /* assign V0 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve158 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve188 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step196 /* V03*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-16-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-16-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve117 /* assign V1 = |default| */,
						_Steps.Solve096 /* assign V0 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve158 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve188 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve207 /* assign V5 = |ownedDefaultExpressions| */,
						_Steps.Solve212 /* assign V6 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve224 /* assign V7 = 0 */,
						_Steps.Solve228 /* assign V8 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-26-steps */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step196 /* V03*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step207 /* V05*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step216 /* V06*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step222 /* V08*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve219 /* assign V6 = |ownedDefaultExpressions| */,
						_Steps.Solve220 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve228 /* assign V8 = 0 */,
						_Steps.Solve234 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-28-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step220 /* V08*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve219 /* assign V6 = |ownedDefaultExpressions| */,
						_Steps.Solve220 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve228 /* assign V8 = 0 */,
						_Steps.Solve234 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-28-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step220 /* V08*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve117 /* assign V1 = |default| */,
						_Steps.Solve096 /* assign V0 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve158 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve188 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step196 /* V03*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-16-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-16-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve117 /* assign V1 = |default| */,
						_Steps.Solve096 /* assign V0 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve158 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve188 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve207 /* assign V5 = |ownedDefaultExpressions| */,
						_Steps.Solve212 /* assign V6 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve224 /* assign V7 = 0 */,
						_Steps.Solve228 /* assign V8 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-26-steps */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step196 /* V03*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step207 /* V05*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step216 /* V06*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step222 /* V08*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve219 /* assign V6 = |ownedDefaultExpressions| */,
						_Steps.Solve220 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve228 /* assign V8 = 0 */,
						_Steps.Solve234 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-28-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step220 /* V08*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve177 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve200 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve219 /* assign V6 = |ownedDefaultExpressions| */,
						_Steps.Solve220 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve228 /* assign V8 = 0 */,
						_Steps.Solve234 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-28-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step024 /* 1*'attribute' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step220 /* V08*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve059 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve059 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve059 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve059 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve059 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol={'false|true'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve059 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve283 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve282 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve067 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve114 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve283 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve282 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve067 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve114 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve283 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve282 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve067 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve114 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve283 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve282 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve067 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve114 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve283 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve282 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve067 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve114 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step135 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve285 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : EssentialOCL::PatternExpCS */,
						_Steps.Solve017 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step093 /* 1*ownedExpression=PatternExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve286 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : EssentialOCL::ExpCS */,
						_Steps.Solve284 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve088 /* assign V0 = |ownedLastExpression| */,
						_Steps.Solve017 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step089 /* 1*ownedExpression=ExpCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step008 /* 1*'..' */,
						_Steps.Step105 /* 1*ownedLastExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : EssentialOCL::PatternExpCS */,
						_Steps.Solve102 /* assign V0 = |restVariableName| */,
						_Steps.Solve111 /* assign V1 = (|ownedParts| - 1) */,
						_Steps.Solve043 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step136 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-6-steps */,
						_Steps.Step113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step006 /* 1*'++' */,
						_Steps.Step156 /* 1*restVariableName=Identifier */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : EssentialOCL::PatternExpCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve166 /* assign V2 = |ownedMultiplicity| */,
						_Steps.Solve102 /* assign V0 = |restVariableName| */,
						_Steps.Solve111 /* assign V1 = (|ownedParts| - 1) */,
						_Steps.Solve043 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step136 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-6-steps */,
						_Steps.Step113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step006 /* 1*'++' */,
						_Steps.Step156 /* 1*restVariableName=Identifier */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						null
					}
				),
				// { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : EssentialOCL::PatternExpCS */,
						_Steps.Solve102 /* assign V0 = |restVariableName| */,
						_Steps.Solve111 /* assign V1 = (|ownedParts| - 1) */,
						_Steps.Solve043 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step136 /* 1*ownedType=CollectionTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-6-steps */,
						_Steps.Step113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step113 /* 1*ownedParts+=PatternExpCS */,
						_Steps.Step006 /* 1*'++' */,
						_Steps.Step156 /* 1*restVariableName=Identifier */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve289 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve290 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve094 /* assign V0 = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve124 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve289 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve290 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve094 /* assign V0 = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve124 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve289 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve290 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve166 /* assign V2 = |ownedMultiplicity| */,
						_Steps.Solve094 /* assign V0 = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve124 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve289 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve290 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve094 /* assign V0 = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve124 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve289 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve290 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve094 /* assign V0 = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve124 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve289 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve290 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve166 /* assign V2 = |ownedMultiplicity| */,
						_Steps.Solve094 /* assign V0 = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve124 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve289 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve290 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve166 /* assign V2 = |ownedMultiplicity| */,
						_Steps.Solve094 /* assign V0 = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve124 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve289 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve290 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve094 /* assign V0 = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve124 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step070 /* 1*name=CollectionTypeIdentifier */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						_Steps.Step183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve291 /* check-rule essentialoclcs::ContextCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve015 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step092 /* 1*ownedExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve292 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : EssentialOCL::ShadowPartCS */,
						_Steps.Solve065 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve113 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step114 /* 1*ownedParts+=ShadowPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step114 /* 1*ownedParts+=ShadowPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-12-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve189 /* assign V4 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve181 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-12-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step201 /* V04*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve218 /* assign V6 = |ownedConstraints| */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve171 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve193 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step201 /* V04*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-12-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve171 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve193 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-12-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step201 /* V04*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve218 /* assign V6 = |ownedConstraints| */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve189 /* assign V4 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve181 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step201 /* V04*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-12-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve189 /* assign V4 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve181 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-12-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step201 /* V04*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve178 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve218 /* assign V6 = |ownedConstraints| */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve161 /* assign V2 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve078 /* assign V0 = |isPrimitive.'primitive'| */,
						_Steps.Solve171 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve193 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step165 /* V00*'primitive' */,
						_Steps.Step027 /* 1*'datatype' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step201 /* V04*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve105 /* assign V0 = |values| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step075 /* 1*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step178 /* V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve106 /* assign V0 = |value| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step030 /* 1*'documentation' */,
						_Steps.Step177 /* V00*value=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve106 /* assign V0 = |value| */,
						_Steps.Solve108 /* assign V1 = (|ownedDetails| > 0) */,
						_Steps.Solve153 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step030 /* 1*'documentation' */,
						_Steps.Step177 /* V00*value=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve172 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve162 /* assign V2 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step195 /* V03*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve245 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve197 /* assign V4 = |ownedLiterals| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve245 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve197 /* assign V4 = |ownedLiterals| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve245 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve218 /* assign V6 = |ownedConstraints| */,
						_Steps.Solve208 /* assign V5 = |ownedLiterals| */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve172 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve162 /* assign V2 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step195 /* V03*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve172 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve162 /* assign V2 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step195 /* V03*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve245 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve197 /* assign V4 = |ownedLiterals| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve245 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve197 /* assign V4 = |ownedLiterals| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve245 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve218 /* assign V6 = |ownedConstraints| */,
						_Steps.Solve208 /* assign V5 = |ownedLiterals| */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve152 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve182 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step195 /* V03*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve172 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve162 /* assign V2 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step195 /* V03*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve245 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve197 /* assign V4 = |ownedLiterals| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-14-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve245 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve206 /* assign V5 = |ownedConstraints| */,
						_Steps.Solve197 /* assign V4 = |ownedLiterals| */,
						_Steps.Solve183 /* assign V3 = |ownedAnnotations| */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve159 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step000 /* 1*'!serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve245 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve218 /* assign V6 = |ownedConstraints| */,
						_Steps.Solve208 /* assign V5 = |ownedLiterals| */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve118 /* assign V1 = |instanceClassName| */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve172 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve162 /* assign V2 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-15-steps */,
						_Steps.Step034 /* 1*'enum' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step195 /* V03*'serializable' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						_Steps.Step215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve137 /* assign V1 = |value| */,
						_Steps.Solve079 /* assign V0 = |literal| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step071 /* 1*name=EnumerationLiteralName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step161 /* 1*value=SIGNED */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve137 /* assign V1 = |value| */,
						_Steps.Solve079 /* assign V0 = |literal| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step044 /* 1*'literal' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step161 /* 1*value=SIGNED */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve163 /* assign V2 = |ownedAnnotations| */,
						_Steps.Solve137 /* assign V1 = |value| */,
						_Steps.Solve079 /* assign V0 = |literal| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step071 /* 1*name=EnumerationLiteralName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step161 /* 1*value=SIGNED */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve163 /* assign V2 = |ownedAnnotations| */,
						_Steps.Solve137 /* assign V1 = |value| */,
						_Steps.Solve079 /* assign V0 = |literal| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step044 /* 1*'literal' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step161 /* 1*value=SIGNED */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve137 /* assign V1 = |value| */,
						_Steps.Solve079 /* assign V0 = |literal| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step071 /* 1*name=EnumerationLiteralName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step161 /* 1*value=SIGNED */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve137 /* assign V1 = |value| */,
						_Steps.Solve079 /* assign V0 = |literal| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step044 /* 1*'literal' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step161 /* 1*value=SIGNED */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve163 /* assign V2 = |ownedAnnotations| */,
						_Steps.Solve137 /* assign V1 = |value| */,
						_Steps.Solve079 /* assign V0 = |literal| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step071 /* 1*name=EnumerationLiteralName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step161 /* 1*value=SIGNED */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve163 /* assign V2 = |ownedAnnotations| */,
						_Steps.Solve137 /* assign V1 = |value| */,
						_Steps.Solve079 /* assign V0 = |literal| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step044 /* 1*'literal' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step067 /* 1*literal=SINGLE_QUOTED_STRING */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step161 /* 1*value=SIGNED */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'self'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'self'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'self'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'invalid'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'self'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve000 /* assert (|exprString| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step065 /* 1*exprString=UNQUOTED_STRING */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// ownedExpression=ExpCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve293 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step090 /* 1*ownedExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve296 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve297 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve086 /* assign V0 = |ownedIfThenExpressions| */,
						_Steps.Solve039 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve010 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step036 /* 1*'if' */,
						_Steps.Step084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step058 /* 1*'then' */,
						_Steps.Step133 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step031 /* 1*'else' */,
						_Steps.Step087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve296 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve297 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve086 /* assign V0 = |ownedIfThenExpressions| */,
						_Steps.Solve039 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve010 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step036 /* 1*'if' */,
						_Steps.Step084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step058 /* 1*'then' */,
						_Steps.Step133 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step031 /* 1*'else' */,
						_Steps.Step087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve296 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve297 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve086 /* assign V0 = |ownedIfThenExpressions| */,
						_Steps.Solve039 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve010 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step036 /* 1*'if' */,
						_Steps.Step084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step058 /* 1*'then' */,
						_Steps.Step133 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step031 /* 1*'else' */,
						_Steps.Step087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve296 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve297 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve086 /* assign V0 = |ownedIfThenExpressions| */,
						_Steps.Solve039 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve010 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step036 /* 1*'if' */,
						_Steps.Step084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step058 /* 1*'then' */,
						_Steps.Step133 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step031 /* 1*'else' */,
						_Steps.Step087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve296 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve297 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve086 /* assign V0 = |ownedIfThenExpressions| */,
						_Steps.Solve039 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve010 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step036 /* 1*'if' */,
						_Steps.Step084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						_Steps.Step058 /* 1*'then' */,
						_Steps.Step133 /* 1*ownedThenExpression=ExpCS */,
						_Steps.Step170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						_Steps.Step031 /* 1*'else' */,
						_Steps.Step087 /* 1*ownedElseExpression=ExpCS */,
						_Steps.Step033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve298 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : EssentialOCL::ExpCS */,
						_Steps.Solve299 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve040 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve011 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step032 /* 1*'elseif' */,
						_Steps.Step083 /* 1*ownedCondition=ExpCS */,
						_Steps.Step058 /* 1*'then' */,
						_Steps.Step134 /* 1*ownedThenExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve045 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve073 /* assign V0 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
						_Steps.Solve135 /* assign V1 = |qualifiers.'!ordered|!unique|ordered|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step048 /* 1*'opposite' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step181 /* V01*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve246 /* check-rule basecs::ImportCS.ownedPathName : EssentialOCL::URIPathNameCS */,
						_Steps.Solve119 /* assign V1 = |isAll.'::*'| */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */,
						_Steps.Solve080 /* assign V0 = |name| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step037 /* 1*'import' */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step124 /* 1*ownedPathName=URIPathNameCS */,
						_Steps.Step179 /* V01*'::*' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve300 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve316 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::ExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve029 /* assert (|ownedLeft| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step106 /* 1*ownedLeft=PrefixedPrimaryExpCS */,
						_Steps.Step069 /* 1*name=BinaryOperatorName */,
						_Steps.Step127 /* 1*ownedRight=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve300 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve316 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::ExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve029 /* assert (|ownedLeft| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step106 /* 1*ownedLeft=PrefixedPrimaryExpCS */,
						_Steps.Step069 /* 1*name=BinaryOperatorName */,
						_Steps.Step127 /* 1*ownedRight=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve301 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
						_Steps.Solve014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step018 /* 1*'Lambda' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve301 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
						_Steps.Solve014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step018 /* 1*'Lambda' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve301 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
						_Steps.Solve014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step018 /* 1*'Lambda' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve301 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
						_Steps.Solve014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step018 /* 1*'Lambda' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve301 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
						_Steps.Solve014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step018 /* 1*'Lambda' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step094 /* 1*ownedExpressionCS=ExpCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve303 /* check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS */,
						_Steps.Solve302 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS */,
						_Steps.Solve019 /* assert (|ownedInExpression| - 1) == 0 */,
						_Steps.Solve072 /* assign V0 = (|ownedVariables| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step043 /* 1*'let' */,
						_Steps.Step146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step038 /* 1*'in' */,
						_Steps.Step098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve303 /* check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS */,
						_Steps.Solve302 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS */,
						_Steps.Solve019 /* assert (|ownedInExpression| - 1) == 0 */,
						_Steps.Solve072 /* assign V0 = (|ownedVariables| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step043 /* 1*'let' */,
						_Steps.Step146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step038 /* 1*'in' */,
						_Steps.Step098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve303 /* check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS */,
						_Steps.Solve302 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS */,
						_Steps.Solve019 /* assert (|ownedInExpression| - 1) == 0 */,
						_Steps.Solve072 /* assign V0 = (|ownedVariables| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step043 /* 1*'let' */,
						_Steps.Step146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step038 /* 1*'in' */,
						_Steps.Step098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve303 /* check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS */,
						_Steps.Solve302 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS */,
						_Steps.Solve019 /* assert (|ownedInExpression| - 1) == 0 */,
						_Steps.Solve072 /* assign V0 = (|ownedVariables| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step043 /* 1*'let' */,
						_Steps.Step146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step146 /* 1*ownedVariables+=LetVariableCS */,
						_Steps.Step038 /* 1*'in' */,
						_Steps.Step098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve304 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve330 /* check-rule essentialoclcs::VariableCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve329 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve022 /* assert (|ownedInitExpression| - 1) == 0 */,
						_Steps.Solve133 /* assign V1 = |ownedType| */,
						_Steps.Solve090 /* assign V0 = |ownedRoundBracketedClause| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step172 /* V00*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve305 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve306 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve042 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve066 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve112 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve305 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve306 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve042 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve066 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve112 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve305 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve306 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve042 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve066 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve112 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve305 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve306 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve042 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve066 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve112 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve305 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve306 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve042 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve066 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve112 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step137 /* 1*ownedType=MapTypeCS */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-4-steps */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step112 /* 1*ownedParts+=MapLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve307 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : EssentialOCL::ExpCS */,
						_Steps.Solve308 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : EssentialOCL::ExpCS */,
						_Steps.Solve047 /* assert (|ownedValue| - 1) == 0 */,
						_Steps.Solve028 /* assert (|ownedKey| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step103 /* 1*ownedKey=ExpCS */,
						_Steps.Step013 /* 1*'<-' */,
						_Steps.Step144 /* 1*ownedValue=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve309 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve097 /* assign V0 = |ownedValueType| */,
						_Steps.Solve023 /* assert (|ownedKeyType| - C00) == 0 */,
						_Steps.Solve002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step019 /* 1*'Map' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve309 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve097 /* assign V0 = |ownedValueType| */,
						_Steps.Solve024 /* assert (|ownedKeyType| - C00) == 0 */,
						_Steps.Solve002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step019 /* 1*'Map' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve309 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve129 /* assign V1 = |ownedMultiplicity| */,
						_Steps.Solve097 /* assign V0 = |ownedValueType| */,
						_Steps.Solve025 /* assert (|ownedKeyType| - C00) == 0 */,
						_Steps.Solve002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step019 /* 1*'Map' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve309 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve097 /* assign V0 = |ownedValueType| */,
						_Steps.Solve023 /* assert (|ownedKeyType| - C00) == 0 */,
						_Steps.Solve002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step019 /* 1*'Map' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve309 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve097 /* assign V0 = |ownedValueType| */,
						_Steps.Solve024 /* assert (|ownedKeyType| - C00) == 0 */,
						_Steps.Solve002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step019 /* 1*'Map' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve309 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve129 /* assign V1 = |ownedMultiplicity| */,
						_Steps.Solve097 /* assign V0 = |ownedValueType| */,
						_Steps.Solve026 /* assert (|ownedKeyType| - C00) == 0 */,
						_Steps.Solve002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step019 /* 1*'Map' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve309 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve129 /* assign V1 = |ownedMultiplicity| */,
						_Steps.Solve097 /* assign V0 = |ownedValueType| */,
						_Steps.Solve027 /* assert (|ownedKeyType| - C00) == 0 */,
						_Steps.Solve002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step019 /* 1*'Map' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve309 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve097 /* assign V0 = |ownedValueType| */,
						_Steps.Solve024 /* assert (|ownedKeyType| - C00) == 0 */,
						_Steps.Solve002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step019 /* 1*'Map' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step104 /* 1*ownedKeyType=TypeExpCS */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step145 /* 1*ownedValueType=TypeExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve248 /* check-rule basecs::ModelElementRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve034 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step054 /* 1*'reference' */,
						_Steps.Step120 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve104 /* assign V0 = |upperBound| */,
						_Steps.Solve001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step068 /* 1*lowerBound=LOWER */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step008 /* 1*'..' */,
						_Steps.Step160 /* 1*upperBound=UPPER */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve104 /* assign V0 = |upperBound| */,
						_Steps.Solve001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step021 /* 1*'[' */,
						_Steps.Step068 /* 1*lowerBound=LOWER */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step008 /* 1*'..' */,
						_Steps.Step160 /* 1*upperBound=UPPER */,
						_Steps.Step022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve104 /* assign V0 = |upperBound| */,
						_Steps.Solve001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step021 /* 1*'[' */,
						_Steps.Step068 /* 1*lowerBound=LOWER */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step008 /* 1*'..' */,
						_Steps.Step160 /* 1*upperBound=UPPER */,
						_Steps.Step062 /* 1*'|?' */,
						_Steps.Step022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve120 /* assign V1 = |isNullFree.'|1'| */,
						_Steps.Solve104 /* assign V0 = |upperBound| */,
						_Steps.Solve001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step021 /* 1*'[' */,
						_Steps.Step068 /* 1*lowerBound=LOWER */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step008 /* 1*'..' */,
						_Steps.Step160 /* 1*upperBound=UPPER */,
						_Steps.Step180 /* V01*'|1' */,
						_Steps.Step022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments5 /* «! » + «value» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve058 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step021 /* 1*'[' */,
						_Steps.Step157 /* 1*stringBounds */,
						_Steps.Step022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { '[' stringBounds={'*|+|?'} '|?' ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve058 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step021 /* 1*'[' */,
						_Steps.Step157 /* 1*stringBounds */,
						_Steps.Step062 /* 1*'|?' */,
						_Steps.Step022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve077 /* assign V0 = |isNullFree.'|1'| */,
						_Steps.Solve058 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step021 /* 1*'[' */,
						_Steps.Step157 /* 1*stringBounds */,
						_Steps.Step167 /* V00*'|1' */,
						_Steps.Step022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// stringBounds={'*|+|?'}
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve058 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step157 /* 1*stringBounds */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve281 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve180 /* assign V3 = |isPre.'@'| */,
						_Steps.Solve164 /* assign V2 = |ownedCurlyBracketedClause| */,
						_Steps.Solve131 /* assign V1 = |ownedRoundBracketedClause| */,
						_Steps.Solve092 /* assign V0 = |ownedSquareBracketedClauses| */,
						_Steps.Solve035 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step017 /* 1*'@' */,
						_Steps.Step051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve281 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve180 /* assign V3 = |isPre.'@'| */,
						_Steps.Solve164 /* assign V2 = |ownedCurlyBracketedClause| */,
						_Steps.Solve131 /* assign V1 = |ownedRoundBracketedClause| */,
						_Steps.Solve092 /* assign V0 = |ownedSquareBracketedClauses| */,
						_Steps.Solve035 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step017 /* 1*'@' */,
						_Steps.Step051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve281 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve180 /* assign V3 = |isPre.'@'| */,
						_Steps.Solve164 /* assign V2 = |ownedCurlyBracketedClause| */,
						_Steps.Solve131 /* assign V1 = |ownedRoundBracketedClause| */,
						_Steps.Solve092 /* assign V0 = |ownedSquareBracketedClauses| */,
						_Steps.Solve035 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step017 /* 1*'@' */,
						_Steps.Step051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve281 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve180 /* assign V3 = |isPre.'@'| */,
						_Steps.Solve164 /* assign V2 = |ownedCurlyBracketedClause| */,
						_Steps.Solve131 /* assign V1 = |ownedRoundBracketedClause| */,
						_Steps.Solve092 /* assign V0 = |ownedSquareBracketedClauses| */,
						_Steps.Solve035 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step017 /* 1*'@' */,
						_Steps.Step051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve281 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve180 /* assign V3 = |isPre.'@'| */,
						_Steps.Solve164 /* assign V2 = |ownedCurlyBracketedClause| */,
						_Steps.Solve131 /* assign V1 = |ownedRoundBracketedClause| */,
						_Steps.Solve092 /* assign V0 = |ownedSquareBracketedClauses| */,
						_Steps.Solve035 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step121 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						_Steps.Step187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step017 /* 1*'@' */,
						_Steps.Step051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// { ':' ownedType=TypeExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve314 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve046 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve087 /* assign V0 = |ownedInitExpression| */,
						_Steps.Solve009 /* assert (|ownedCoIterator| - 1) == 0 */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step013 /* 1*'<-' */,
						_Steps.Step082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve314 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve126 /* assign V1 = |ownedInitExpression| */,
						_Steps.Solve082 /* assign V0 = |ownedCoIterator| */,
						_Steps.Solve046 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step013 /* 1*'<-' */,
						_Steps.Step082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve314 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve021 /* assert (|ownedInitExpression| - 1) == 0 */,
						_Steps.Solve123 /* assign V1 = |ownedCoIterator| */,
						_Steps.Solve095 /* assign V0 = |ownedType| */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step013 /* 1*'<-' */,
						_Steps.Step082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step038 /* 1*'in' */,
						_Steps.Step100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve314 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve095 /* assign V0 = |ownedType| */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve050 /* assert (|prefix.'|'| - 1) == 0 */,
						_Steps.Solve126 /* assign V1 = |ownedInitExpression| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step061 /* 1*'|' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { prefix=',' ownedNameExpression=NavigatingArgExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve048 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null
					}
				),
				// { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve087 /* assign V0 = |ownedInitExpression| */,
						_Steps.Solve009 /* assert (|ownedCoIterator| - 1) == 0 */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve048 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step013 /* 1*'<-' */,
						_Steps.Step082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve314 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve126 /* assign V1 = |ownedInitExpression| */,
						_Steps.Solve082 /* assign V0 = |ownedCoIterator| */,
						_Steps.Solve046 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve048 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step013 /* 1*'<-' */,
						_Steps.Step082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve314 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve021 /* assert (|ownedInitExpression| - 1) == 0 */,
						_Steps.Solve123 /* assign V1 = |ownedCoIterator| */,
						_Steps.Solve095 /* assign V0 = |ownedType| */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve048 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step013 /* 1*'<-' */,
						_Steps.Step082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						_Steps.Step038 /* 1*'in' */,
						_Steps.Step100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve313 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve314 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve095 /* assign V0 = |ownedType| */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve049 /* assert (|prefix.';'| - 1) == 0 */,
						_Steps.Solve126 /* assign V1 = |ownedInitExpression| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve315 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve016 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step091 /* 1*ownedExpression=ExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { '(' ownedExpression=ExpCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve315 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve016 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step091 /* 1*ownedExpression=ExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { '(' ownedExpression=ExpCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve315 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve016 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step091 /* 1*ownedExpression=ExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { '(' ownedExpression=ExpCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve315 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve016 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step091 /* 1*ownedExpression=ExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { '(' ownedExpression=ExpCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve315 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve016 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step091 /* 1*ownedExpression=ExpCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve060 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve060 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve060 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve060 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve060 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// symbol=NUMBER_LITERAL
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve060 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve243 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve121 /* assign V1 = |name| */,
						_Steps.Solve055 /* assert (|stereotype.'invariant'| - 1) == 0 */,
						_Steps.Solve076 /* assign V0 = |isCallable.'callable'| */,
						_Steps.Solve165 /* assign V2 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step163 /* V00*'callable' */,
						_Steps.Step041 /* 1*'invariant' */,
						_Steps.Step181 /* V01*next-5-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step107 /* 1*ownedMessageSpecification=SpecificationCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve243 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve244 /* check-rule basecs::ConstraintCS.ownedSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve186 /* assign V3 = |ownedSpecification| */,
						_Steps.Solve121 /* assign V1 = |name| */,
						_Steps.Solve055 /* assert (|stereotype.'invariant'| - 1) == 0 */,
						_Steps.Solve076 /* assign V0 = |isCallable.'callable'| */,
						_Steps.Solve165 /* assign V2 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step163 /* V00*'callable' */,
						_Steps.Step041 /* 1*'invariant' */,
						_Steps.Step181 /* V01*next-5-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step189 /* V02*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step107 /* 1*ownedMessageSpecification=SpecificationCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step200 /* V03*ownedSpecification=SpecificationCS */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve243 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve244 /* check-rule basecs::ConstraintCS.ownedSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve168 /* assign V2 = |ownedSpecification| */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve056 /* assert (|stereotype.'postcondition'| - 1) == 0 */,
						_Steps.Solve127 /* assign V1 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step050 /* 1*'postcondition' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step107 /* 1*ownedMessageSpecification=SpecificationCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step194 /* V02*ownedSpecification=SpecificationCS */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve243 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve244 /* check-rule basecs::ConstraintCS.ownedSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve168 /* assign V2 = |ownedSpecification| */,
						_Steps.Solve080 /* assign V0 = |name| */,
						_Steps.Solve057 /* assert (|stereotype.'precondition'| - 1) == 0 */,
						_Steps.Solve127 /* assign V1 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step052 /* 1*'precondition' */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step107 /* 1*ownedMessageSpecification=SpecificationCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step194 /* V02*ownedSpecification=SpecificationCS */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve187 /* assign V3 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve214 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve226 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						_Steps.Solve190 /* assign V4 = (|ownedExceptions| > 0) */,
						_Steps.Solve202 /* assign V5 = (|ownedExceptions| - 1) */,
						_Steps.Solve110 /* assign V1 = (|ownedParameters| > 0) */,
						_Steps.Solve154 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-25-steps */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step202 /* V04*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step207 /* V05*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step217 /* V07*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve198 /* assign V4 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve203 /* assign V5 = (|ownedExceptions| > 0) */,
						_Steps.Solve213 /* assign V6 = (|ownedExceptions| - 1) */,
						_Steps.Solve155 /* assign V2 = (|ownedParameters| > 0) */,
						_Steps.Solve174 /* assign V3 = (|ownedParameters| - 1) */,
						_Steps.Solve221 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve232 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-27-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step207 /* V05*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step220 /* V08*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve198 /* assign V4 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve203 /* assign V5 = (|ownedExceptions| > 0) */,
						_Steps.Solve213 /* assign V6 = (|ownedExceptions| - 1) */,
						_Steps.Solve155 /* assign V2 = (|ownedParameters| > 0) */,
						_Steps.Solve174 /* assign V3 = (|ownedParameters| - 1) */,
						_Steps.Solve221 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve232 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-27-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step207 /* V05*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step220 /* V08*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve253 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve252 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve149 /* assign V12 = |ownedPostconditions| */,
						_Steps.Solve237 /* assign V9 = |ownedPreconditions| */,
						_Steps.Solve229 /* assign V8 = |ownedAnnotations| */,
						_Steps.Solve187 /* assign V3 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve143 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve140 /* assign V10 = |ownedBodyExpressions| */,
						_Steps.Solve214 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve226 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						_Steps.Solve190 /* assign V4 = (|ownedExceptions| > 0) */,
						_Steps.Solve202 /* assign V5 = (|ownedExceptions| - 1) */,
						_Steps.Solve110 /* assign V1 = (|ownedParameters| > 0) */,
						_Steps.Solve154 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-34-steps */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step202 /* V04*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step207 /* V05*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step217 /* V07*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step221 /* V08*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step229 /* V10*next-4-steps */,
						_Steps.Step025 /* 1*'body' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step234 /* V11*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve253 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve252 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve151 /* assign V13 = |ownedPostconditions| */,
						_Steps.Solve142 /* assign V10 = |ownedPreconditions| */,
						_Steps.Solve235 /* assign V9 = |ownedAnnotations| */,
						_Steps.Solve198 /* assign V4 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve146 /* assign V12 = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve145 /* assign V11 = |ownedBodyExpressions| */,
						_Steps.Solve203 /* assign V5 = (|ownedExceptions| > 0) */,
						_Steps.Solve213 /* assign V6 = (|ownedExceptions| - 1) */,
						_Steps.Solve155 /* assign V2 = (|ownedParameters| > 0) */,
						_Steps.Solve174 /* assign V3 = (|ownedParameters| - 1) */,
						_Steps.Solve221 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve232 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-36-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step207 /* V05*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step220 /* V08*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step025 /* 1*'body' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve253 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve252 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve151 /* assign V13 = |ownedPostconditions| */,
						_Steps.Solve142 /* assign V10 = |ownedPreconditions| */,
						_Steps.Solve235 /* assign V9 = |ownedAnnotations| */,
						_Steps.Solve198 /* assign V4 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve146 /* assign V12 = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve145 /* assign V11 = |ownedBodyExpressions| */,
						_Steps.Solve203 /* assign V5 = (|ownedExceptions| > 0) */,
						_Steps.Solve213 /* assign V6 = (|ownedExceptions| - 1) */,
						_Steps.Solve155 /* assign V2 = (|ownedParameters| > 0) */,
						_Steps.Solve174 /* assign V3 = (|ownedParameters| - 1) */,
						_Steps.Solve221 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve232 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-36-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step207 /* V05*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step220 /* V08*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step025 /* 1*'body' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve187 /* assign V3 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve214 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve226 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						_Steps.Solve190 /* assign V4 = (|ownedExceptions| > 0) */,
						_Steps.Solve202 /* assign V5 = (|ownedExceptions| - 1) */,
						_Steps.Solve110 /* assign V1 = (|ownedParameters| > 0) */,
						_Steps.Solve154 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-25-steps */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step202 /* V04*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step207 /* V05*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step217 /* V07*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve198 /* assign V4 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve203 /* assign V5 = (|ownedExceptions| > 0) */,
						_Steps.Solve213 /* assign V6 = (|ownedExceptions| - 1) */,
						_Steps.Solve155 /* assign V2 = (|ownedParameters| > 0) */,
						_Steps.Solve174 /* assign V3 = (|ownedParameters| - 1) */,
						_Steps.Solve221 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve232 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-27-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step207 /* V05*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step220 /* V08*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve198 /* assign V4 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve203 /* assign V5 = (|ownedExceptions| > 0) */,
						_Steps.Solve213 /* assign V6 = (|ownedExceptions| - 1) */,
						_Steps.Solve155 /* assign V2 = (|ownedParameters| > 0) */,
						_Steps.Solve174 /* assign V3 = (|ownedParameters| - 1) */,
						_Steps.Solve221 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve232 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-27-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step207 /* V05*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step220 /* V08*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve253 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve252 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve149 /* assign V12 = |ownedPostconditions| */,
						_Steps.Solve237 /* assign V9 = |ownedPreconditions| */,
						_Steps.Solve229 /* assign V8 = |ownedAnnotations| */,
						_Steps.Solve187 /* assign V3 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve091 /* assign V0 = |ownedSignature| */,
						_Steps.Solve143 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve140 /* assign V10 = |ownedBodyExpressions| */,
						_Steps.Solve214 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve226 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						_Steps.Solve190 /* assign V4 = (|ownedExceptions| > 0) */,
						_Steps.Solve202 /* assign V5 = (|ownedExceptions| - 1) */,
						_Steps.Solve110 /* assign V1 = (|ownedParameters| > 0) */,
						_Steps.Solve154 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-34-steps */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step173 /* V00*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step202 /* V04*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step207 /* V05*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step217 /* V07*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step221 /* V08*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step229 /* V10*next-4-steps */,
						_Steps.Step025 /* 1*'body' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step234 /* V11*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve253 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve252 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve151 /* assign V13 = |ownedPostconditions| */,
						_Steps.Solve142 /* assign V10 = |ownedPreconditions| */,
						_Steps.Solve235 /* assign V9 = |ownedAnnotations| */,
						_Steps.Solve198 /* assign V4 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve146 /* assign V12 = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve145 /* assign V11 = |ownedBodyExpressions| */,
						_Steps.Solve203 /* assign V5 = (|ownedExceptions| > 0) */,
						_Steps.Solve213 /* assign V6 = (|ownedExceptions| - 1) */,
						_Steps.Solve155 /* assign V2 = (|ownedParameters| > 0) */,
						_Steps.Solve174 /* assign V3 = (|ownedParameters| - 1) */,
						_Steps.Solve221 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve232 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-36-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step207 /* V05*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step220 /* V08*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step025 /* 1*'body' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve253 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve252 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve151 /* assign V13 = |ownedPostconditions| */,
						_Steps.Solve142 /* assign V10 = |ownedPreconditions| */,
						_Steps.Solve235 /* assign V9 = |ownedAnnotations| */,
						_Steps.Solve198 /* assign V4 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve146 /* assign V12 = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve145 /* assign V11 = |ownedBodyExpressions| */,
						_Steps.Solve203 /* assign V5 = (|ownedExceptions| > 0) */,
						_Steps.Solve213 /* assign V6 = (|ownedExceptions| - 1) */,
						_Steps.Solve155 /* assign V2 = (|ownedParameters| > 0) */,
						_Steps.Solve174 /* assign V3 = (|ownedParameters| - 1) */,
						_Steps.Solve221 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve232 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-36-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step047 /* 1*'operation' */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step189 /* V02*next-4-steps */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step109 /* 1*ownedParameters+=ParameterCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step207 /* V05*next-5-steps */,
						_Steps.Step059 /* 1*'throws' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step213 /* V06*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step088 /* 1*ownedExceptions+=TypedRefCS */,
						_Steps.Step217 /* V07*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step220 /* V08*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step025 /* 1*'body' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve122 /* assign V1 = |nsURI| */,
						_Steps.Solve081 /* assign V0 = |nsPrefix| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step049 /* 1*'package' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step077 /* 1*nsPrefix=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step078 /* 1*nsURI=URI */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve255 /* check-rule basecs::PackageOwnerCS.ownedPackages : OCLinEcore::PackageCS */,
						_Steps.Solve254 /* check-rule basecs::PackageCS.ownedClasses : OCLinEcore::ClassCS */,
						_Steps.Solve195 /* assign V4 = |ownedClasses| */,
						_Steps.Solve185 /* assign V3 = |ownedPackages| */,
						_Steps.Solve163 /* assign V2 = |ownedAnnotations| */,
						_Steps.Solve122 /* assign V1 = |nsURI| */,
						_Steps.Solve081 /* assign V0 = |nsPrefix| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-13-steps */,
						_Steps.Step049 /* 1*'package' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step077 /* 1*nsPrefix=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step078 /* 1*nsURI=URI */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step199 /* V03*ownedPackages+=PackageCS */,
						_Steps.Step204 /* V04*ownedClasses+=ClassCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve122 /* assign V1 = |nsURI| */,
						_Steps.Solve081 /* assign V0 = |nsPrefix| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step049 /* 1*'package' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step077 /* 1*nsPrefix=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step078 /* 1*nsURI=URI */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve255 /* check-rule basecs::PackageOwnerCS.ownedPackages : OCLinEcore::PackageCS */,
						_Steps.Solve254 /* check-rule basecs::PackageCS.ownedClasses : OCLinEcore::ClassCS */,
						_Steps.Solve195 /* assign V4 = |ownedClasses| */,
						_Steps.Solve185 /* assign V3 = |ownedPackages| */,
						_Steps.Solve163 /* assign V2 = |ownedAnnotations| */,
						_Steps.Solve122 /* assign V1 = |nsURI| */,
						_Steps.Solve081 /* assign V0 = |nsPrefix| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-13-steps */,
						_Steps.Step049 /* 1*'package' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step077 /* 1*nsPrefix=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step078 /* 1*nsURI=URI */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step199 /* V03*ownedPackages+=PackageCS */,
						_Steps.Step204 /* V04*ownedClasses+=ClassCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve096 /* assign V0 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve173 /* assign V3 = (|ownedAnnotations| > 0) */,
						_Steps.Solve194 /* assign V4 = |ownedAnnotations| */,
						_Steps.Solve116 /* assign V1 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
						_Steps.Solve170 /* assign V2 = |qualifiers.'!ordered|!unique|ordered|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-13-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step189 /* V02*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step196 /* V03*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve053 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step151 /* 1*referredElement=UnrestrictedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// referredElement=UnreservedName
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve053 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step149 /* 1*referredElement=UnreservedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// referredElement=UnrestrictedName
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve053 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step150 /* 1*referredElement=UnrestrictedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve053 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step148 /* 1*referredElement=URI */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve257 /* check-rule basecs::PathNameCS.ownedPathElements : Base::FirstPathElementCS|Base::NextPathElementCS */,
						_Steps.Solve069 /* assign V0 = (|ownedPathElements| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step117 /* 1*ownedPathElements+=FirstPathElementCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step010 /* 1*'::' */,
						_Steps.Step118 /* 1*ownedPathElements+=NextPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null
					}
				),
				// ownedPathElements+=FirstPathElementCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve256 /* check-rule basecs::PathNameCS.ownedPathElements : Base::FirstPathElementCS */,
						_Steps.Solve031 /* assert (|ownedPathElements| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step117 /* 1*ownedPathElements+=FirstPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve258 /* check-rule basecs::PathNameCS.ownedPathElements : EssentialOCL::URIFirstPathElementCS|Base::NextPathElementCS */,
						_Steps.Solve069 /* assign V0 = (|ownedPathElements| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step119 /* 1*ownedPathElements+=URIFirstPathElementCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step010 /* 1*'::' */,
						_Steps.Step118 /* 1*ownedPathElements+=NextPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve319 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : EssentialOCL::TypeExpCS */,
						_Steps.Solve037 /* assert (|ownedPatternType| - 1) == 0 */,
						_Steps.Solve098 /* assign V0 = |patternVariableName| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step175 /* V00*patternVariableName=UnrestrictedName */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step126 /* 1*ownedPatternType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve318 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step073 /* 1*name=UnaryOperatorName */,
						_Steps.Step129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve317 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedLetExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step073 /* 1*name=UnaryOperatorName */,
						_Steps.Step128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve318 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step073 /* 1*name=UnaryOperatorName */,
						_Steps.Step129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve317 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedLetExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step073 /* 1*name=UnaryOperatorName */,
						_Steps.Step128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve317 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedLetExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step073 /* 1*name=UnaryOperatorName */,
						_Steps.Step128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve318 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step073 /* 1*name=UnaryOperatorName */,
						_Steps.Step129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// 'null'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// '*'
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// name=PrimitiveTypeIdentifier
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve089 /* assign V0 = |ownedMultiplicity| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step072 /* 1*name=PrimitiveTypeIdentifier */,
						_Steps.Step171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// name=PrimitiveTypeIdentifier
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// name=PrimitiveTypeIdentifier
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve089 /* assign V0 = |ownedMultiplicity| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step072 /* 1*name=PrimitiveTypeIdentifier */,
						_Steps.Step171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve089 /* assign V0 = |ownedMultiplicity| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step072 /* 1*name=PrimitiveTypeIdentifier */,
						_Steps.Step171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// name=PrimitiveTypeIdentifier
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve101 /* assign V0 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve176 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve199 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-17-steps */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-19-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-19-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve148 /* assign V12 = |ownedImplicitOpposites| */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve101 /* assign V0 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve176 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve199 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve215 /* assign V6 = (|referredKeys| > 0) */,
						_Steps.Solve222 /* assign V7 = (|referredKeys| - 1) */,
						_Steps.Solve230 /* assign V8 = |ownedDefaultExpressions| */,
						_Steps.Solve233 /* assign V9 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve139 /* assign V10 = 0 */,
						_Steps.Solve144 /* assign V11 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-39-steps */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step213 /* V06*next-6-steps */,
						_Steps.Step042 /* 1*'key' */,
						_Steps.Step153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step217 /* V07*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step220 /* V08*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step229 /* V10*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step236 /* V12*next-2-steps */,
						_Steps.Step097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve150 /* assign V13 = |ownedImplicitOpposites| */,
						_Steps.Solve217 /* assign V6 = |ownedAnnotations| */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve223 /* assign V7 = (|referredKeys| > 0) */,
						_Steps.Solve227 /* assign V8 = (|referredKeys| - 1) */,
						_Steps.Solve236 /* assign V9 = |ownedDefaultExpressions| */,
						_Steps.Solve138 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve144 /* assign V11 = 0 */,
						_Steps.Solve147 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-41-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step217 /* V07*next-6-steps */,
						_Steps.Step042 /* 1*'key' */,
						_Steps.Step153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step220 /* V08*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step224 /* V09*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step240 /* V13*next-2-steps */,
						_Steps.Step097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve150 /* assign V13 = |ownedImplicitOpposites| */,
						_Steps.Solve217 /* assign V6 = |ownedAnnotations| */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve223 /* assign V7 = (|referredKeys| > 0) */,
						_Steps.Solve227 /* assign V8 = (|referredKeys| - 1) */,
						_Steps.Solve236 /* assign V9 = |ownedDefaultExpressions| */,
						_Steps.Solve138 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve144 /* assign V11 = 0 */,
						_Steps.Solve147 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-41-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step217 /* V07*next-6-steps */,
						_Steps.Step042 /* 1*'key' */,
						_Steps.Step153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step220 /* V08*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step224 /* V09*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step240 /* V13*next-2-steps */,
						_Steps.Step097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve101 /* assign V0 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve176 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve199 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-17-steps */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-19-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-19-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve148 /* assign V12 = |ownedImplicitOpposites| */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve101 /* assign V0 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve176 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve199 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve215 /* assign V6 = (|referredKeys| > 0) */,
						_Steps.Solve222 /* assign V7 = (|referredKeys| - 1) */,
						_Steps.Solve230 /* assign V8 = |ownedDefaultExpressions| */,
						_Steps.Solve233 /* assign V9 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve139 /* assign V10 = 0 */,
						_Steps.Solve144 /* assign V11 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-39-steps */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step213 /* V06*next-6-steps */,
						_Steps.Step042 /* 1*'key' */,
						_Steps.Step153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step217 /* V07*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step220 /* V08*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step229 /* V10*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step236 /* V12*next-2-steps */,
						_Steps.Step097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve150 /* assign V13 = |ownedImplicitOpposites| */,
						_Steps.Solve217 /* assign V6 = |ownedAnnotations| */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve223 /* assign V7 = (|referredKeys| > 0) */,
						_Steps.Solve227 /* assign V8 = (|referredKeys| - 1) */,
						_Steps.Solve236 /* assign V9 = |ownedDefaultExpressions| */,
						_Steps.Solve138 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve144 /* assign V11 = 0 */,
						_Steps.Solve147 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-41-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step217 /* V07*next-6-steps */,
						_Steps.Step042 /* 1*'key' */,
						_Steps.Step153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step220 /* V08*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step224 /* V09*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step240 /* V13*next-2-steps */,
						_Steps.Step097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve150 /* assign V13 = |ownedImplicitOpposites| */,
						_Steps.Solve217 /* assign V6 = |ownedAnnotations| */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve223 /* assign V7 = (|referredKeys| > 0) */,
						_Steps.Solve227 /* assign V8 = (|referredKeys| - 1) */,
						_Steps.Solve236 /* assign V9 = |ownedDefaultExpressions| */,
						_Steps.Solve138 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve144 /* assign V11 = 0 */,
						_Steps.Solve147 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-41-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step217 /* V07*next-6-steps */,
						_Steps.Step042 /* 1*'key' */,
						_Steps.Step153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step220 /* V08*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step224 /* V09*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step240 /* V13*next-2-steps */,
						_Steps.Step097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve101 /* assign V0 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve176 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve199 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-17-steps */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-19-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-19-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve148 /* assign V12 = |ownedImplicitOpposites| */,
						_Steps.Solve205 /* assign V5 = |ownedAnnotations| */,
						_Steps.Solve160 /* assign V2 = |default| */,
						_Steps.Solve134 /* assign V1 = |ownedType| */,
						_Steps.Solve101 /* assign V0 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve176 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve199 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve215 /* assign V6 = (|referredKeys| > 0) */,
						_Steps.Solve222 /* assign V7 = (|referredKeys| - 1) */,
						_Steps.Solve230 /* assign V8 = |ownedDefaultExpressions| */,
						_Steps.Solve233 /* assign V9 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve139 /* assign V10 = 0 */,
						_Steps.Solve144 /* assign V11 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-39-steps */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step196 /* V03*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step202 /* V04*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step213 /* V06*next-6-steps */,
						_Steps.Step042 /* 1*'key' */,
						_Steps.Step153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step217 /* V07*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step220 /* V08*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step229 /* V10*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step236 /* V12*next-2-steps */,
						_Steps.Step097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve150 /* assign V13 = |ownedImplicitOpposites| */,
						_Steps.Solve217 /* assign V6 = |ownedAnnotations| */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve100 /* assign V0 = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve223 /* assign V7 = (|referredKeys| > 0) */,
						_Steps.Solve227 /* assign V8 = (|referredKeys| - 1) */,
						_Steps.Solve236 /* assign V9 = |ownedDefaultExpressions| */,
						_Steps.Solve138 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve144 /* assign V11 = 0 */,
						_Steps.Solve147 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-41-steps */,
						_Steps.Step028 /* 1*'definition' */,
						_Steps.Step166 /* V00*'static' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step217 /* V07*next-6-steps */,
						_Steps.Step042 /* 1*'key' */,
						_Steps.Step153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step220 /* V08*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step224 /* V09*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step240 /* V13*next-2-steps */,
						_Steps.Step097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve261 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve273 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve150 /* assign V13 = |ownedImplicitOpposites| */,
						_Steps.Solve217 /* assign V6 = |ownedAnnotations| */,
						_Steps.Solve179 /* assign V3 = |default| */,
						_Steps.Solve169 /* assign V2 = |ownedType| */,
						_Steps.Solve136 /* assign V1 = |referredOpposite| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve099 /* assign V0 = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve191 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve210 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve223 /* assign V7 = (|referredKeys| > 0) */,
						_Steps.Solve227 /* assign V8 = (|referredKeys| - 1) */,
						_Steps.Solve236 /* assign V9 = |ownedDefaultExpressions| */,
						_Steps.Solve138 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve144 /* assign V11 = 0 */,
						_Steps.Solve147 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-41-steps */,
						_Steps.Step056 /* 1*'static' */,
						_Steps.Step164 /* V00*'definition' */,
						_Steps.Step053 /* 1*'property' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step001 /* 1*'#' */,
						_Steps.Step154 /* 1*referredOpposite=UnrestrictedName */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step143 /* 1*ownedType=TypedMultiplicityRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step064 /* 1*default=SINGLE_QUOTED_STRING */,
						_Steps.Step202 /* V04*next-4-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step207 /* V05*next-1-steps */,
						_Steps.Step147 /* 1*qualifiers */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step217 /* V07*next-6-steps */,
						_Steps.Step042 /* 1*'key' */,
						_Steps.Step153 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step220 /* V08*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step152 /* 1*referredKeys+=UnrestrictedName */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step224 /* V09*next-4-steps */,
						_Steps.Step039 /* 1*'initial' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step233 /* V11*next-4-steps */,
						_Steps.Step029 /* 1*'derivation' */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step240 /* V13*next-2-steps */,
						_Steps.Step097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve320 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : EssentialOCL::NavigatingArgCS|EssentialOCL::NavigatingBarArgCS|EssentialOCL::NavigatingSemiArgCS|EssentialOCL::NavigatingCommaArgCS */,
						_Steps.Solve061 /* assign V0 = (|ownedArguments| > 0) */,
						_Steps.Solve107 /* assign V1 = (|ownedArguments| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-5-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step080 /* 1*ownedArguments+=NavigatingArgCS */,
						_Steps.Step182 /* V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve322 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : EssentialOCL::StringLiteralExpCS */,
						_Steps.Solve020 /* assert (|ownedInitExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step102 /* 1*ownedInitExpression=StringLiteralExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve321 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve020 /* assert (|ownedInitExpression| - 1) == 0 */,
						_Steps.Solve054 /* assert (|referredProperty| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step155 /* 1*referredProperty=UnrestrictedName */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step101 /* 1*ownedInitExpression=ExpCS|PatternExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve323 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : EssentialOCL::ExpCS */,
						_Steps.Solve071 /* assign V0 = (|ownedTerms| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step021 /* 1*'[' */,
						_Steps.Step132 /* 1*ownedTerms+=ExpCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step132 /* 1*ownedTerms+=ExpCS */,
						_Steps.Step022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve103 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve103 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve103 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve103 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve103 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// segments+=StringLiteral[+]
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve103 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve264 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve192 /* assign V4 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve075 /* assign V0 = |isAbstract.'abstract'| */,
						_Steps.Solve201 /* assign V5 = (|isInterface.'interface'| > 0) */,
						_Steps.Solve216 /* assign V6 = |isInterface.'interface'| */,
						_Steps.Solve157 /* assign V2 = (|ownedSuperTypes| > 0) */,
						_Steps.Solve175 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-18-steps */,
						_Steps.Step162 /* V00*'abstract' */,
						_Steps.Step026 /* 1*'class' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-5-steps */,
						_Steps.Step035 /* 1*'extends' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step207 /* V05*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step212 /* V06*'interface' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve264 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve262 /* check-rule basecs::StructuredClassCS.ownedOperations : OCLinEcore::OperationCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve263 /* check-rule basecs::StructuredClassCS.ownedProperties : OCLinEcore::StructuralFeatureCS */,
						_Steps.Solve141 /* assign V10 = |ownedConstraints| */,
						_Steps.Solve238 /* assign V9 = |ownedProperties| */,
						_Steps.Solve231 /* assign V8 = |ownedOperations| */,
						_Steps.Solve225 /* assign V7 = |ownedAnnotations| */,
						_Steps.Solve192 /* assign V4 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve075 /* assign V0 = |isAbstract.'abstract'| */,
						_Steps.Solve211 /* assign V6 = (|isInterface.'interface'| > 0) */,
						_Steps.Solve204 /* assign V5 = |isInterface.'interface'| */,
						_Steps.Solve157 /* assign V2 = (|ownedSuperTypes| > 0) */,
						_Steps.Solve175 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-23-steps */,
						_Steps.Step162 /* V00*'abstract' */,
						_Steps.Step026 /* 1*'class' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-5-steps */,
						_Steps.Step035 /* 1*'extends' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step207 /* V05*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step212 /* V06*'interface' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step223 /* V08*ownedOperations+=OperationCS */,
						_Steps.Step228 /* V09*ownedProperties+=StructuralFeatureCS */,
						_Steps.Step230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve264 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve192 /* assign V4 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve075 /* assign V0 = |isAbstract.'abstract'| */,
						_Steps.Solve201 /* assign V5 = (|isInterface.'interface'| > 0) */,
						_Steps.Solve216 /* assign V6 = |isInterface.'interface'| */,
						_Steps.Solve157 /* assign V2 = (|ownedSuperTypes| > 0) */,
						_Steps.Solve175 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-18-steps */,
						_Steps.Step162 /* V00*'abstract' */,
						_Steps.Step026 /* 1*'class' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-5-steps */,
						_Steps.Step035 /* 1*'extends' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step207 /* V05*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step212 /* V06*'interface' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve264 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve262 /* check-rule basecs::StructuredClassCS.ownedOperations : OCLinEcore::OperationCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve263 /* check-rule basecs::StructuredClassCS.ownedProperties : OCLinEcore::StructuralFeatureCS */,
						_Steps.Solve141 /* assign V10 = |ownedConstraints| */,
						_Steps.Solve238 /* assign V9 = |ownedProperties| */,
						_Steps.Solve231 /* assign V8 = |ownedOperations| */,
						_Steps.Solve225 /* assign V7 = |ownedAnnotations| */,
						_Steps.Solve192 /* assign V4 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve075 /* assign V0 = |isAbstract.'abstract'| */,
						_Steps.Solve211 /* assign V6 = (|isInterface.'interface'| > 0) */,
						_Steps.Solve204 /* assign V5 = |isInterface.'interface'| */,
						_Steps.Solve157 /* assign V2 = (|ownedSuperTypes| > 0) */,
						_Steps.Solve175 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-23-steps */,
						_Steps.Step162 /* V00*'abstract' */,
						_Steps.Step026 /* 1*'class' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-5-steps */,
						_Steps.Step035 /* 1*'extends' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step207 /* V05*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step212 /* V06*'interface' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step223 /* V08*ownedOperations+=OperationCS */,
						_Steps.Step228 /* V09*ownedProperties+=StructuralFeatureCS */,
						_Steps.Step230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve264 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve192 /* assign V4 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve075 /* assign V0 = |isAbstract.'abstract'| */,
						_Steps.Solve201 /* assign V5 = (|isInterface.'interface'| > 0) */,
						_Steps.Solve216 /* assign V6 = |isInterface.'interface'| */,
						_Steps.Solve157 /* assign V2 = (|ownedSuperTypes| > 0) */,
						_Steps.Solve175 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-18-steps */,
						_Steps.Step162 /* V00*'abstract' */,
						_Steps.Step026 /* 1*'class' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-5-steps */,
						_Steps.Step035 /* 1*'extends' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step207 /* V05*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step212 /* V06*'interface' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve247 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve264 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve262 /* check-rule basecs::StructuredClassCS.ownedOperations : OCLinEcore::OperationCS */,
						_Steps.Solve269 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve242 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve263 /* check-rule basecs::StructuredClassCS.ownedProperties : OCLinEcore::StructuralFeatureCS */,
						_Steps.Solve141 /* assign V10 = |ownedConstraints| */,
						_Steps.Solve238 /* assign V9 = |ownedProperties| */,
						_Steps.Solve231 /* assign V8 = |ownedOperations| */,
						_Steps.Solve225 /* assign V7 = |ownedAnnotations| */,
						_Steps.Solve192 /* assign V4 = |instanceClassName| */,
						_Steps.Solve132 /* assign V1 = |ownedSignature| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve075 /* assign V0 = |isAbstract.'abstract'| */,
						_Steps.Solve211 /* assign V6 = (|isInterface.'interface'| > 0) */,
						_Steps.Solve204 /* assign V5 = |isInterface.'interface'| */,
						_Steps.Solve157 /* assign V2 = (|ownedSuperTypes| > 0) */,
						_Steps.Solve175 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-23-steps */,
						_Steps.Step162 /* V00*'abstract' */,
						_Steps.Step026 /* 1*'class' */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step188 /* V01*ownedSignature=TemplateSignatureCS */,
						_Steps.Step189 /* V02*next-5-steps */,
						_Steps.Step035 /* 1*'extends' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step196 /* V03*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step131 /* 1*ownedSuperTypes+=TypedRefCS */,
						_Steps.Step202 /* V04*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						_Steps.Step207 /* V05*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step212 /* V06*'interface' */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						_Steps.Step223 /* V08*ownedOperations+=OperationCS */,
						_Steps.Step228 /* V09*ownedProperties+=StructuralFeatureCS */,
						_Steps.Step230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve012 /* assert (|ownedDetails| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step057 /* 1*'sysml' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve084 /* assign V0 = |ownedDetails| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step057 /* 1*'sysml' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'sysml' ownedDetails+=DetailCS ';' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve012 /* assert (|ownedDetails| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step057 /* 1*'sysml' */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */
					}
				),
				// { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve241 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve084 /* assign V0 = |ownedDetails| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step057 /* 1*'sysml' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step086 /* 1*ownedDetails+=DetailCS */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments4 /* «! » + «value» + «?\n» */,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve265 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve266 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : Base::TemplateParameterSubstitutionCS */,
						_Steps.Solve128 /* assign V1 = |ownedMultiplicity| */,
						_Steps.Solve070 /* assign V0 = (|ownedSubstitutions| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-5-steps */,
						_Steps.Step130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
						_Steps.Step186 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve267 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : Base::TypeRefCS */,
						_Steps.Solve007 /* assert (|ownedActualParameter| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step079 /* 1*ownedActualParameter=TypeRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve268 /* check-rule basecs::TemplateSignatureCS.ownedParameters : Base::TypeParameterCS */,
						_Steps.Solve063 /* assign V0 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step110 /* 1*ownedParameters+=TypeParameterCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step110 /* 1*ownedParameters+=TypeParameterCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve268 /* check-rule basecs::TemplateSignatureCS.ownedParameters : Base::TypeParameterCS */,
						_Steps.Solve063 /* assign V0 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step012 /* 1*'<' */,
						_Steps.Step110 /* 1*ownedParameters+=TypeParameterCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step110 /* 1*ownedParameters+=TypeParameterCS */,
						_Steps.Step015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve260 /* check-rule basecs::RootCS.ownedImports : OCLinEcore::ImportCS */,
						_Steps.Solve255 /* check-rule basecs::PackageOwnerCS.ownedPackages : OCLinEcore::PackageCS */,
						_Steps.Solve167 /* assign V2 = |ownedPackages| */,
						_Steps.Solve125 /* assign V1 = |ownedImports| */,
						_Steps.Solve074 /* assign V0 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step169 /* V00*next-1-steps */,
						_Steps.Step045 /* 1*'module' */,
						_Steps.Step184 /* V01*ownedImports+=ImportCS */,
						_Steps.Step193 /* V02*ownedPackages+=PackageCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve324 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve064 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve324 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve064 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve324 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve064 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve324 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve064 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve324 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve064 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step115 /* 1*ownedParts+=TupleLiteralPartCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve330 /* check-rule essentialoclcs::VariableCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve329 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve022 /* assert (|ownedInitExpression| - 1) == 0 */,
						_Steps.Solve093 /* assign V0 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */,
						_Steps.Step014 /* 1*'=' */,
						_Steps.Step099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve272 /* check-rule basecs::TypedElementCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve045 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step139 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve270 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve068 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve115 /* assign V1 = (|ownedParts| > 0) */,
						_Steps.Solve156 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step169 /* V00*next-7-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve270 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve115 /* assign V1 = (|ownedParts| > 0) */,
						_Steps.Solve068 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve156 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step169 /* V00*next-7-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve270 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve184 /* assign V3 = |ownedMultiplicity| */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve115 /* assign V1 = (|ownedParts| > 0) */,
						_Steps.Solve068 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve156 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step169 /* V00*next-7-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve270 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve068 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve115 /* assign V1 = (|ownedParts| > 0) */,
						_Steps.Solve156 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step169 /* V00*next-7-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve270 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve115 /* assign V1 = (|ownedParts| > 0) */,
						_Steps.Solve068 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve156 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step169 /* V00*next-7-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve270 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve184 /* assign V3 = |ownedMultiplicity| */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve068 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve115 /* assign V1 = (|ownedParts| > 0) */,
						_Steps.Solve156 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step169 /* V00*next-7-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve270 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve184 /* assign V3 = |ownedMultiplicity| */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve068 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve115 /* assign V1 = (|ownedParts| > 0) */,
						_Steps.Solve156 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step169 /* V00*next-7-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve270 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve115 /* assign V1 = (|ownedParts| > 0) */,
						_Steps.Solve068 /* assign V0 = (|ownedParts| > 0) */,
						_Steps.Solve156 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step020 /* 1*'Tuple' */,
						_Steps.Step169 /* V00*next-7-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step181 /* V01*next-4-steps */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step189 /* V02*next-2-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step116 /* 1*ownedParts+=TuplePartCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						_Segments.Segments3 /* «! » + «value» + «? » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve325 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve044 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// ownedType=TypeLiteralWithMultiplicityCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve325 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve044 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// ownedType=TypeLiteralWithMultiplicityCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve325 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve044 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// ownedType=TypeLiteralWithMultiplicityCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve325 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve044 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// ownedType=TypeLiteralWithMultiplicityCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve325 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve044 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve327 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve326 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve328 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : EssentialOCL::ExpCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve166 /* assign V2 = |ownedMultiplicity| */,
						_Steps.Solve083 /* assign V0 = |ownedCurlyBracketedClause| */,
						_Steps.Solve033 /* assert (|ownedPathName| - 1) == 0 */,
						_Steps.Solve130 /* assign V1 = |ownedPatternGuard| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step122 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step181 /* V01*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step125 /* 1*ownedPatternGuard=ExpCS */,
						_Steps.Step063 /* 1*'}' */,
						_Steps.Step192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */,
						null
					}
				),
				// { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve327 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve326 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve328 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : EssentialOCL::ExpCS */,
						_Steps.Solve083 /* assign V0 = |ownedCurlyBracketedClause| */,
						_Steps.Solve033 /* assert (|ownedPathName| - 1) == 0 */,
						_Steps.Solve130 /* assign V1 = |ownedPatternGuard| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step122 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step181 /* V01*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step125 /* 1*ownedPatternGuard=ExpCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
				),
				// { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve327 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve326 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve328 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : EssentialOCL::ExpCS */,
						_Steps.Solve083 /* assign V0 = |ownedCurlyBracketedClause| */,
						_Steps.Solve033 /* assert (|ownedPathName| - 1) == 0 */,
						_Steps.Solve130 /* assign V1 = |ownedPatternGuard| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step122 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						_Steps.Step181 /* V01*next-3-steps */,
						_Steps.Step060 /* 1*'{' */,
						_Steps.Step125 /* 1*ownedPatternGuard=ExpCS */,
						_Steps.Step063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						_Segments.Segments7 /* «? » + «value» + «+» + «?\n» */,
						null,
						_Segments.Segments6 /* «-» + «? » + «value» + «?\n» */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve271 /* check-rule basecs::TypeParameterCS.ownedExtends : OCLinEcore::TypedRefCS */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */,
						_Steps.Solve062 /* assign V0 = (|ownedExtends| > 0) */,
						_Steps.Solve109 /* assign V1 = (|ownedExtends| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step035 /* 1*'extends' */,
						_Steps.Step095 /* 1*ownedExtends+=TypedRefCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step002 /* 1*'&&' */,
						_Steps.Step095 /* 1*ownedExtends+=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve275 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve275 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step012 /* 1*'<' */,
						_Steps.Step081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve089 /* assign V0 = |ownedMultiplicity| */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null
					}
				),
				// { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve275 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve089 /* assign V0 = |ownedMultiplicity| */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-5-steps */,
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step004 /* 1*')' */,
						_Steps.Step171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */,
						null
					}
				),
				// { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve275 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve274 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve089 /* assign V0 = |ownedMultiplicity| */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-5-steps */,
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step012 /* 1*'<' */,
						_Steps.Step081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step015 /* 1*'>' */,
						_Steps.Step171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// ownedPathName=PathNameCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve275 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve275 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step012 /* 1*'<' */,
						_Steps.Step081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
				),
				// ownedPathName=PathNameCS
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				),
				// { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve275 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments2 /* «! » + «value» + «! » */,
						null,
						_Segments.Segments5 /* «! » + «value» */
					}
				),
				// { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve275 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve276 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step123 /* 1*ownedPathName=PathNameCS */,
						_Steps.Step012 /* 1*'<' */,
						_Steps.Step081 /* 1*ownedBinding=TemplateBindingCS */,
						_Steps.Step015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						_Segments.Segments8 /* «? » + «value» + «? » */
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve330 /* check-rule essentialoclcs::VariableCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve093 /* assign V0 = |ownedType| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
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
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve277 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : OCLinEcore::TypedRefCS */,
						_Steps.Solve085 /* assign V0 = |ownedExtends| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step016 /* 1*'?' */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step035 /* 1*'extends' */,
						_Steps.Step096 /* 1*ownedExtends=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				),
				// { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve277 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : OCLinEcore::TypedRefCS */,
						_Steps.Solve085 /* assign V0 = |ownedExtends| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step016 /* 1*'?' */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step035 /* 1*'extends' */,
						_Steps.Step096 /* 1*ownedExtends=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null,
						_Segments.Segments8 /* «? » + «value» + «? » */,
						null
					}
				)
			), 0);
		}
	}
}
