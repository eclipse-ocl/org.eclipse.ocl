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
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve000 // assert (|exprString| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve001 // assert (|lowerBound| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve002 // assert (|name.'Map'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve003 // assert (|name.'Tuple'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve004 // assert (|name| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve005 // assert (|name| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve006 // assert (|name| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve007 // assert (|ownedActualParameter| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve008 // assert (|ownedBinding| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve009 // assert (|ownedCoIterator| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve010 // assert (|ownedCondition| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve011 // assert (|ownedCondition| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve012 // assert (|ownedDetails| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve013 // assert (|ownedElseExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve014 // assert (|ownedExpressionCS| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve015 // assert (|ownedExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve016 // assert (|ownedExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve017 // assert (|ownedExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve018 // assert (|ownedExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve019 // assert (|ownedInExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve020 // assert (|ownedInitExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve021 // assert (|ownedInitExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve022 // assert (|ownedInitExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve023 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve024 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve025 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve026 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve027 // assert (|ownedKeyType| - C00) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve028 // assert (|ownedKey| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve029 // assert (|ownedLeft| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve030 // assert (|ownedNameExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve031 // assert (|ownedPathElements| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve032 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve033 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve034 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve035 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve036 // assert (|ownedPathName| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve037 // assert (|ownedPatternType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve038 // assert (|ownedRight| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve039 // assert (|ownedThenExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve040 // assert (|ownedThenExpression| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve041 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve042 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve043 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve044 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve045 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve046 // assert (|ownedType| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve047 // assert (|ownedValue| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve048 // assert (|prefix.','| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve049 // assert (|prefix.';'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve050 // assert (|prefix.'|'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve051 // assert (|qualifiers.'definition'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve052 // assert (|qualifiers.'static'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve053 // assert (|referredElement| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve054 // assert (|referredProperty| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve055 // assert (|stereotype.'invariant'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve056 // assert (|stereotype.'postcondition'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve057 // assert (|stereotype.'precondition'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve058 // assert (|stringBounds.'*|+|?'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve059 // assert (|symbol.'false|true'| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve060 // assert (|symbol| - 1) == 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve061 // assign C00:ElseIfThenExpCS[*] = |ownedIfThenExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve062 // assign C00:MultiplicityCS[?] = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve063 // assign C00:RoundBracketedClauseCS[?] = |ownedRoundBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve064 // assign C00:SINGLE_QUOTED_STRING[?] = |value|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve065 // assign C00:SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING[*] = |values|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve066 // assign C00:SquareBracketedClauseCS[*] = |ownedSquareBracketedClauses|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve067 // assign C00:StringLiteral[+] = |segments|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve068 // assign C00:TemplateSignatureCS[?] = |ownedSignature|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve069 // assign C00:UnrestrictedName[?] = |patternVariableName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve070 // assign C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] = |name|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve071 // assign C00[*] = (|ownedParameters| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve072 // assign C00[*] = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve073 // assign C00[*] = (|ownedPathElements| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve074 // assign C00[*] = (|ownedSubstitutions| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve075 // assign C00[*] = (|ownedTerms| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve076 // assign C00[*] = (|ownedVariables| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve077 // assign C00[*] = |ownedDetails|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve078 // assign C00[?] = (|ownedArguments| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve079 // assign C00[?] = (|ownedExtends| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve080 // assign C00[?] = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve081 // assign C00[?] = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve082 // assign C00[?] = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve083 // assign C00[?] = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve084 // assign C00[?] = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve085 // assign C00[?] = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve086 // assign C00[?] = |isAbstract.'abstract'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve087 // assign C00[?] = |isCallable.'callable'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve088 // assign C00[?] = |isNullFree.'|1'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve089 // assign C00[?] = |isPrimitive.'primitive'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve090 // assign C00[?] = |literal|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve091 // assign C00[?] = |nsPrefix|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve092 // assign C00[?] = |ownedCoIterator|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve093 // assign C00[?] = |ownedCurlyBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve094 // assign C00[?] = |ownedExtends|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve095 // assign C00[?] = |ownedInitExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve096 // assign C00[?] = |ownedLastExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve097 // assign C00[?] = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve098 // assign C00[?] = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve099 // assign C00[?] = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve100 // assign C00[?] = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve101 // assign C00[?] = |ownedValueType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve102 // assign C00[?] = |qualifiers.'definition'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve103 // assign C00[?] = |qualifiers.'static'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve104 // assign C00[?] = |referredOpposite|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve105 // assign C00[?] = |restVariableName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve106 // assign C00[?] = |upperBound|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve107 // assign C01:ImportCS[*] = |ownedImports|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve108 // assign C01:MultiplicityCS[?] = |ownedCollectionMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve109 // assign C01:MultiplicityCS[?] = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve110 // assign C01:MultiplicityCS[?] = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve111 // assign C01:NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS[*] = (|ownedArguments| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve112 // assign C01:RoundBracketedClauseCS[?] = |ownedRoundBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve113 // assign C01:TemplateSignatureCS[?] = |ownedSignature|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve114 // assign C01[*] = (|ownedExtends| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve115 // assign C01[*] = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve116 // assign C01[*] = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve117 // assign C01[*] = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve118 // assign C01[*] = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve119 // assign C01[+] = |qualifiers.'!ordered|!unique|ordered|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve120 // assign C01[?] = (|ownedDetails| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve121 // assign C01[?] = (|ownedParameters| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve122 // assign C01[?] = (|ownedParts| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve123 // assign C01[?] = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve124 // assign C01[?] = |default|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve125 // assign C01[?] = |instanceClassName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve126 // assign C01[?] = |isAll.'::*'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve127 // assign C01[?] = |isNullFree.'|1'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve128 // assign C01[?] = |name|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve129 // assign C01[?] = |nsURI|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve130 // assign C01[?] = |ownedCoIterator|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve131 // assign C01[?] = |ownedInitExpression|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve132 // assign C01[?] = |ownedMessageSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve133 // assign C01[?] = |ownedPatternGuard|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve134 // assign C01[?] = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve135 // assign C01[?] = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve136 // assign C01[?] = |referredOpposite|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve137 // assign C01[?] = |value|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve138 // assign C02:AnnotationElementCS[*] = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve139 // assign C02:CurlyBracketedClauseCS[?] = |ownedCurlyBracketedClause|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve140 // assign C02:MultiplicityCS[?] = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve141 // assign C02:PackageCS[*] = |ownedPackages|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve142 // assign C02:SpecificationCS[?] = |ownedSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve143 // assign C02[*] = (|ownedDetails| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve144 // assign C02[*] = (|ownedParameters| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve145 // assign C02[*] = (|ownedParts| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve146 // assign C02[+] = |qualifiers.'!ordered|!unique|ordered|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve147 // assign C02[?] = (|isSerializable.'serializable'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve148 // assign C02[?] = (|ownedParameters| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve149 // assign C02[?] = (|ownedSuperTypes| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve150 // assign C02[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve151 // assign C02[?] = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve152 // assign C02[?] = |default|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve153 // assign C02[?] = |instanceClassName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve154 // assign C02[?] = |isSerializable.'serializable'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve155 // assign C02[?] = |ownedMessageSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve156 // assign C02[?] = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve157 // assign C03:AnnotationElementCS[*] = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve158 // assign C03:MultiplicityCS[?] = |ownedMultiplicity|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve159 // assign C03:PackageCS[*] = |ownedPackages|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve160 // assign C03:SpecificationCS[?] = |ownedSpecification|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve161 // assign C03[*] = (|ownedParameters| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve162 // assign C03[*] = (|ownedSuperTypes| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve163 // assign C03[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve164 // assign C03[?] = (|isSerializable.'serializable'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve165 // assign C03[?] = (|isSerializable.'serializable'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve166 // assign C03[?] = (|ownedAnnotations| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve167 // assign C03[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve168 // assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve169 // assign C03[?] = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve170 // assign C03[?] = |default|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve171 // assign C03[?] = |isPre.'@'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve172 // assign C03[?] = |isSerializable.'serializable'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve173 // assign C03[?] = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve174 // assign C04:AnnotationElementCS[*] = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve175 // assign C04:ClassCS[*] = |ownedClasses|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve176 // assign C04:EnumerationLiteralCS[*] = |ownedLiterals|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve177 // assign C04:ModelElementCS[*] = |ownedContents|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve178 // assign C04[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve179 // assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve180 // assign C04[?] = (|ownedExceptions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve181 // assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve182 // assign C04[?] = |instanceClassName|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve183 // assign C04[?] = |isSerializable.'serializable'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve184 // assign C04[?] = |ownedType|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve185 // assign C05:AnnotationElementCS[*] = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve186 // assign C05:EnumerationLiteralCS[*] = |ownedLiterals|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve187 // assign C05:InvariantConstraintCS[*] = |ownedConstraints|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve188 // assign C05:ModelElementRefCS[+] = |ownedReferences|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve189 // assign C05[*] = (|ownedExceptions| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve190 // assign C05[*] = |ownedDefaultExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve191 // assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve192 // assign C05[?] = (|isInterface.'interface'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve193 // assign C05[?] = (|ownedExceptions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve194 // assign C05[?] = |isInterface.'interface'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve195 // assign C06:AnnotationElementCS[*] = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve196 // assign C06:InvariantConstraintCS[*] = |ownedConstraints|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve197 // assign C06:SpecificationCS[?] = (|ownedDefaultExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve198 // assign C06[*] = (|ownedExceptions| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve199 // assign C06[*] = (|referredKeys| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve200 // assign C06[*] = |ownedDefaultExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve201 // assign C06[?] = (|isInterface.'interface'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve202 // assign C06[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve203 // assign C06[?] = |isInterface.'interface'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve204 // assign C07:AnnotationElementCS[*] = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve205 // assign C07:SpecificationCS[?] = (|ownedDefaultExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve206 // assign C07[*] = (|referredKeys| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve207 // assign C07[*] = (|referredKeys| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve208 // assign C07[*] = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve209 // assign C07[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve210 // assign C07[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve211 // assign C08:AnnotationElementCS[*] = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve212 // assign C08:OperationCS[*] = |ownedOperations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve213 // assign C08:SpecificationCS[?] = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve214 // assign C08[*] = (|referredKeys| - 1)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve215 // assign C08[*] = |ownedDefaultExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve216 // assign C08[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve217 // assign C09:AnnotationElementCS[*] = |ownedAnnotations|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve218 // assign C09:PreconditionConstraintCS[*] = |ownedPreconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve219 // assign C09:SpecificationCS[?] = (|ownedDefaultExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve220 // assign C09:SpecificationCS[?] = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve221 // assign C09:StructuralFeatureCS[*] = |ownedProperties|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve222 // assign C09[*] = |ownedDefaultExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve223 // assign C10:InvariantConstraintCS[*] = |ownedConstraints|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve224 // assign C10:PreconditionConstraintCS[*] = |ownedPreconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve225 // assign C10:SpecificationCS[?] = (|ownedDefaultExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve226 // assign C10[*] = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve227 // assign C10[*] = |ownedBodyExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve228 // assign C11:SpecificationCS[?] = (|ownedBodyExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve229 // assign C11:SpecificationCS[?] = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve230 // assign C11[*] = |ownedBodyExpressions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve231 // assign C12:PostconditionConstraintCS[*] = |ownedPostconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve232 // assign C12:SpecificationCS[?] = (|ownedBodyExpressions| > 0)
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve233 // assign C12:SpecificationCS[?] = 0
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve234 // assign C12[*] = |ownedImplicitOpposites|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve235 // assign C13:PostconditionConstraintCS[*] = |ownedPostconditions|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve236 // assign C13[*] = |ownedImplicitOpposites|
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve237 // check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve238 // check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve239 // check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve240 // check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve241 // check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve242 // check-rule basecs::ConstraintCS.ownedSpecification : OCLinEcore::SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve243 // check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve244 // check-rule basecs::ImportCS.ownedPathName : EssentialOCL::URIPathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve245 // check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve246 // check-rule basecs::ModelElementRefCS.ownedPathName : Base::PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve247 // check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve248 // check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve249 // check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve250 // check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve251 // check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve252 // check-rule basecs::PackageCS.ownedClasses : OCLinEcore::ClassCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve253 // check-rule basecs::PackageOwnerCS.ownedPackages : OCLinEcore::PackageCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve254 // check-rule basecs::PathNameCS.ownedPathElements : Base::FirstPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve255 // check-rule basecs::PathNameCS.ownedPathElements : Base::FirstPathElementCS|Base::NextPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve256 // check-rule basecs::PathNameCS.ownedPathElements : EssentialOCL::URIFirstPathElementCS|Base::NextPathElementCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve257 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve258 // check-rule basecs::RootCS.ownedImports : OCLinEcore::ImportCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve259 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve260 // check-rule basecs::StructuredClassCS.ownedOperations : OCLinEcore::OperationCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve261 // check-rule basecs::StructuredClassCS.ownedProperties : OCLinEcore::StructuralFeatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve262 // check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve263 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : Base::MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve264 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : Base::TemplateParameterSubstitutionCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve265 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : Base::TypeRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve266 // check-rule basecs::TemplateSignatureCS.ownedParameters : Base::TypeParameterCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve267 // check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve268 // check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve269 // check-rule basecs::TypeParameterCS.ownedExtends : OCLinEcore::TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve270 // check-rule basecs::TypedElementCS.ownedType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve271 // check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve272 // check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve273 // check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve274 // check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve275 // check-rule basecs::WildcardTypeRefCS.ownedExtends : OCLinEcore::TypedRefCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve276 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve277 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve278 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve279 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve280 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve281 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve282 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve283 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : EssentialOCL::PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve284 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve285 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : EssentialOCL::PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve286 // check-rule essentialoclcs::CollectionPatternCS.ownedType : EssentialOCL::CollectionTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve287 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve288 // check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve289 // check-rule essentialoclcs::ContextCS.ownedExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve290 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : EssentialOCL::ShadowPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve291 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve292 // check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve293 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve294 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve295 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve296 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve297 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve298 // check-rule essentialoclcs::InfixExpCS.ownedLeft : EssentialOCL::PrefixedPrimaryExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve299 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve300 // check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve301 // check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve302 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve303 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve304 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve305 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve306 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve307 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve308 // check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve309 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve310 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve311 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve312 // check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve313 // check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve314 // check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve315 // check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedLetExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve316 // check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedPrimaryExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve317 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve318 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : EssentialOCL::NavigatingCommaArgCS|EssentialOCL::NavigatingArgCS|EssentialOCL::NavigatingSemiArgCS|EssentialOCL::NavigatingBarArgCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve319 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve320 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : EssentialOCL::StringLiteralExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve321 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve322 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve323 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve324 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve325 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : Base::PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve326 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve327 // check-rule essentialoclcs::VariableCS.ownedInitExpression : EssentialOCL::ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
		private static final /* @@NonNull*/ CardinalitySolutionStep Solve328 // check-rule essentialoclcs::VariableCS.ownedType : EssentialOCL::TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(null);
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
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step090 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step091 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step092 // 1*ownedExpression=ExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, "EssentialOCL::ExpCS");
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
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, "Base::PathNameCS");
		private static final /* @@NonNull*/ RTSerializationStep Step121 // 1*ownedPathName=PathNameCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, "Base::PathNameCS");
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
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, "EssentialOCL::CollectionTypeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step136 // 1*ownedType=CollectionTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, "EssentialOCL::CollectionTypeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step137 // 1*ownedType=MapTypeCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, "EssentialOCL::MapTypeCS");
		private static final /* @@NonNull*/ RTSerializationStep Step138 // 1*ownedType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step139 // 1*ownedType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, "EssentialOCL::TypeExpCS");
		private static final /* @@NonNull*/ RTSerializationStep Step140 // 1*ownedType=TypeExpCS
			= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, "EssentialOCL::TypeExpCS");
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
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve070 /* assign C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] = |name| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve237 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve238 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve188 /* assign C05:ModelElementRefCS[+] = |ownedReferences| */,
						_Steps.Solve177 /* assign C04:ModelElementCS[*] = |ownedContents| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve070 /* assign C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] = |name| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve237 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve238 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve188 /* assign C05:ModelElementRefCS[*] = |ownedReferences| */,
						_Steps.Solve177 /* assign C04:ModelElementCS[+] = |ownedContents| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve070 /* assign C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] = |name| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve237 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve238 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve188 /* assign C05:ModelElementRefCS[*] = |ownedReferences| */,
						_Steps.Solve177 /* assign C04:ModelElementCS[*] = |ownedContents| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[+] = |ownedAnnotations| */,
						_Steps.Solve070 /* assign C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] = |name| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve070 /* assign C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] = |name| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve237 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve238 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve188 /* assign C05:ModelElementRefCS[+] = |ownedReferences| */,
						_Steps.Solve177 /* assign C04:ModelElementCS[*] = |ownedContents| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve070 /* assign C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] = |name| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve237 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve238 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve188 /* assign C05:ModelElementRefCS[*] = |ownedReferences| */,
						_Steps.Solve177 /* assign C04:ModelElementCS[+] = |ownedContents| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve070 /* assign C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] = |name| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve237 /* check-rule basecs::AnnotationCS.ownedContents : OCLinEcore::ModelElementCS */,
						_Steps.Solve238 /* check-rule basecs::AnnotationCS.ownedReferences : OCLinEcore::ModelElementRefCS */,
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve188 /* assign C05:ModelElementRefCS[*] = |ownedReferences| */,
						_Steps.Solve177 /* assign C04:ModelElementCS[*] = |ownedContents| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[+] = |ownedAnnotations| */,
						_Steps.Solve070 /* assign C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] = |name| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve124 /* assign C01[?] = |default| */,
						_Steps.Solve100 /* assign C00[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve150 /* assign C02[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve163 /* assign C03[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve124 /* assign C01[?] = |default| */,
						_Steps.Solve100 /* assign C00[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve150 /* assign C02[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve163 /* assign C03[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve190 /* assign C05[*] = |ownedDefaultExpressions| */,
						_Steps.Solve197 /* assign C06:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve208 /* assign C07[*] = 0 */,
						_Steps.Solve213 /* assign C08:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve200 /* assign C06[*] = |ownedDefaultExpressions| */,
						_Steps.Solve205 /* assign C07:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve213 /* assign C08[*] = 0 */,
						_Steps.Solve220 /* assign C09:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve200 /* assign C06[*] = |ownedDefaultExpressions| */,
						_Steps.Solve205 /* assign C07:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve213 /* assign C08[*] = 0 */,
						_Steps.Solve220 /* assign C09:SpecificationCS[?] = 0 */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve124 /* assign C01[?] = |default| */,
						_Steps.Solve100 /* assign C00[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve150 /* assign C02[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve163 /* assign C03[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve124 /* assign C01[?] = |default| */,
						_Steps.Solve100 /* assign C00[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve150 /* assign C02[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve163 /* assign C03[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve190 /* assign C05[*] = |ownedDefaultExpressions| */,
						_Steps.Solve197 /* assign C06:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve208 /* assign C07[*] = 0 */,
						_Steps.Solve213 /* assign C08:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve200 /* assign C06[*] = |ownedDefaultExpressions| */,
						_Steps.Solve205 /* assign C07:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve213 /* assign C08[*] = 0 */,
						_Steps.Solve220 /* assign C09:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve200 /* assign C06[*] = |ownedDefaultExpressions| */,
						_Steps.Solve205 /* assign C07:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve213 /* assign C08[*] = 0 */,
						_Steps.Solve220 /* assign C09:SpecificationCS[?] = 0 */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve124 /* assign C01[?] = |default| */,
						_Steps.Solve100 /* assign C00[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve150 /* assign C02[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve163 /* assign C03[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve124 /* assign C01[?] = |default| */,
						_Steps.Solve100 /* assign C00[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve150 /* assign C02[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve163 /* assign C03[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve190 /* assign C05[*] = |ownedDefaultExpressions| */,
						_Steps.Solve197 /* assign C06:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve208 /* assign C07[*] = 0 */,
						_Steps.Solve213 /* assign C08:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve200 /* assign C06[*] = |ownedDefaultExpressions| */,
						_Steps.Solve205 /* assign C07:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve213 /* assign C08[*] = 0 */,
						_Steps.Solve220 /* assign C09:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve168 /* assign C03[?] = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve179 /* assign C04[+] = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						_Steps.Solve200 /* assign C06[*] = |ownedDefaultExpressions| */,
						_Steps.Solve205 /* assign C07:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve213 /* assign C08[*] = 0 */,
						_Steps.Solve220 /* assign C09:SpecificationCS[?] = 0 */
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
						_Steps.Solve281 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve045 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve082 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve117 /* assign C01[*] = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step136 /* 1*ownedType=CollectionTypeCS */,
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
						_Steps.Solve281 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve045 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve082 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve117 /* assign C01[*] = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step136 /* 1*ownedType=CollectionTypeCS */,
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
						_Steps.Solve281 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve045 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve082 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve117 /* assign C01[*] = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step136 /* 1*ownedType=CollectionTypeCS */,
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
						_Steps.Solve281 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve045 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve082 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve117 /* assign C01[*] = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step136 /* 1*ownedType=CollectionTypeCS */,
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
						_Steps.Solve281 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve280 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : EssentialOCL::CollectionLiteralPartCS */,
						_Steps.Solve045 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve082 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve117 /* assign C01[*] = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step136 /* 1*ownedType=CollectionTypeCS */,
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
						_Steps.Solve283 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : EssentialOCL::PatternExpCS */,
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
						_Steps.Solve284 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : EssentialOCL::ExpCS */,
						_Steps.Solve282 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve096 /* assign C00[?] = |ownedLastExpression| */,
						_Steps.Solve017 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step092 /* 1*ownedExpression=ExpCS */,
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
						_Steps.Solve286 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve285 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : EssentialOCL::PatternExpCS */,
						_Steps.Solve105 /* assign C00[?] = |restVariableName| */,
						_Steps.Solve118 /* assign C01[*] = (|ownedParts| - 1) */,
						_Steps.Solve042 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step135 /* 1*ownedType=CollectionTypeCS */,
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
						_Steps.Solve286 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve285 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : EssentialOCL::PatternExpCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve140 /* assign C02:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve105 /* assign C00[?] = |restVariableName| */,
						_Steps.Solve118 /* assign C01[*] = (|ownedParts| - 1) */,
						_Steps.Solve042 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-11-steps */,
						_Steps.Step135 /* 1*ownedType=CollectionTypeCS */,
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
						_Steps.Solve286 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : EssentialOCL::CollectionTypeCS */,
						_Steps.Solve285 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : EssentialOCL::PatternExpCS */,
						_Steps.Solve105 /* assign C00[?] = |restVariableName| */,
						_Steps.Solve118 /* assign C01[*] = (|ownedParts| - 1) */,
						_Steps.Solve042 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step135 /* 1*ownedType=CollectionTypeCS */,
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
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve098 /* assign C00[?] = |ownedType| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */,
						_Steps.Solve108 /* assign C01:MultiplicityCS[?] = |ownedCollectionMultiplicity| */
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
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve098 /* assign C00[?] = |ownedType| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */,
						_Steps.Solve108 /* assign C01:MultiplicityCS[?] = |ownedCollectionMultiplicity| */
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
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve140 /* assign C02:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve098 /* assign C00[?] = |ownedType| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */,
						_Steps.Solve108 /* assign C01:MultiplicityCS[?] = |ownedCollectionMultiplicity| */
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
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve098 /* assign C00[?] = |ownedType| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */,
						_Steps.Solve108 /* assign C01:MultiplicityCS[?] = |ownedCollectionMultiplicity| */
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
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve098 /* assign C00[?] = |ownedType| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */,
						_Steps.Solve108 /* assign C01:MultiplicityCS[?] = |ownedCollectionMultiplicity| */
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
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve140 /* assign C02:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve098 /* assign C00[?] = |ownedType| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */,
						_Steps.Solve108 /* assign C01:MultiplicityCS[?] = |ownedCollectionMultiplicity| */
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
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve140 /* assign C02:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve098 /* assign C00[?] = |ownedType| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */,
						_Steps.Solve108 /* assign C01:MultiplicityCS[?] = |ownedCollectionMultiplicity| */
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
						_Steps.Solve287 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve288 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : EssentialOCL::TypeExpWithoutMultiplicityCS */,
						_Steps.Solve098 /* assign C00[?] = |ownedType| */,
						_Steps.Solve005 /* assert (|name| - 1) == 0 */,
						_Steps.Solve108 /* assign C01:MultiplicityCS[?] = |ownedCollectionMultiplicity| */
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
						_Steps.Solve289 /* check-rule essentialoclcs::ContextCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve015 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step091 /* 1*ownedExpression=ExpCS */
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
						_Steps.Solve290 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : EssentialOCL::ShadowPartCS */,
						_Steps.Solve080 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve116 /* assign C01[*] = (|ownedParts| - 1) */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve164 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve183 /* assign C04[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve196 /* assign C06:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve164 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve183 /* assign C04[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve164 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve183 /* assign C04[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve196 /* assign C06:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve164 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve183 /* assign C04[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve164 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve183 /* assign C04[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve169 /* assign C03[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve196 /* assign C06:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve153 /* assign C02[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve089 /* assign C00[?] = |isPrimitive.'primitive'| */,
						_Steps.Solve164 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve183 /* assign C04[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve065 /* assign C00:SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING[*] = |values| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve064 /* assign C00:SINGLE_QUOTED_STRING[?] = |value| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve064 /* assign C00:SINGLE_QUOTED_STRING[?] = |value| */,
						_Steps.Solve120 /* assign C01[?] = (|ownedDetails| > 0) */,
						_Steps.Solve143 /* assign C02[*] = (|ownedDetails| - 1) */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve147 /* assign C02[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve172 /* assign C03[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve243 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve176 /* assign C04:EnumerationLiteralCS[*] = |ownedLiterals| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve243 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve176 /* assign C04:EnumerationLiteralCS[*] = |ownedLiterals| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve243 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve196 /* assign C06:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve186 /* assign C05:EnumerationLiteralCS[*] = |ownedLiterals| */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve165 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve154 /* assign C02[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve165 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve154 /* assign C02[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve243 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve176 /* assign C04:EnumerationLiteralCS[*] = |ownedLiterals| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve243 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve176 /* assign C04:EnumerationLiteralCS[*] = |ownedLiterals| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve243 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve196 /* assign C06:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve186 /* assign C05:EnumerationLiteralCS[*] = |ownedLiterals| */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve165 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve154 /* assign C02[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve147 /* assign C02[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve172 /* assign C03[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve243 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve176 /* assign C04:EnumerationLiteralCS[*] = |ownedLiterals| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve243 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve187 /* assign C05:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve176 /* assign C04:EnumerationLiteralCS[*] = |ownedLiterals| */,
						_Steps.Solve157 /* assign C03:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve151 /* assign C02[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve243 /* check-rule basecs::EnumerationCS.ownedLiterals : OCLinEcore::EnumerationLiteralCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve196 /* assign C06:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve186 /* assign C05:EnumerationLiteralCS[*] = |ownedLiterals| */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve125 /* assign C01[?] = |instanceClassName| */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve165 /* assign C03[?] = (|isSerializable.'serializable'| > 0) */,
						_Steps.Solve154 /* assign C02[?] = |isSerializable.'serializable'| */
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
						_Steps.Solve137 /* assign C01[?] = |value| */,
						_Steps.Solve090 /* assign C00[?] = |literal| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve137 /* assign C01[?] = |value| */,
						_Steps.Solve090 /* assign C00[?] = |literal| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve138 /* assign C02:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve137 /* assign C01[?] = |value| */,
						_Steps.Solve090 /* assign C00[?] = |literal| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve138 /* assign C02:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve137 /* assign C01[?] = |value| */,
						_Steps.Solve090 /* assign C00[?] = |literal| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve137 /* assign C01[?] = |value| */,
						_Steps.Solve090 /* assign C00[?] = |literal| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve137 /* assign C01[?] = |value| */,
						_Steps.Solve090 /* assign C00[?] = |literal| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve138 /* assign C02:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve137 /* assign C01[?] = |value| */,
						_Steps.Solve090 /* assign C00[?] = |literal| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve138 /* assign C02:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve137 /* assign C01[?] = |value| */,
						_Steps.Solve090 /* assign C00[?] = |literal| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve291 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve016 /* assert (|ownedExpression| - 1) == 0 */
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
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve292 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve293 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve061 /* assign C00:ElseIfThenExpCS[*] = |ownedIfThenExpressions| */,
						_Steps.Solve040 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve011 /* assert (|ownedCondition| - 1) == 0 */
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
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve292 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve293 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve061 /* assign C00:ElseIfThenExpCS[*] = |ownedIfThenExpressions| */,
						_Steps.Solve040 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve011 /* assert (|ownedCondition| - 1) == 0 */
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
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve292 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve293 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve061 /* assign C00:ElseIfThenExpCS[*] = |ownedIfThenExpressions| */,
						_Steps.Solve040 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve011 /* assert (|ownedCondition| - 1) == 0 */
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
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve292 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve293 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve061 /* assign C00:ElseIfThenExpCS[*] = |ownedIfThenExpressions| */,
						_Steps.Solve040 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve011 /* assert (|ownedCondition| - 1) == 0 */
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
						_Steps.Solve294 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : EssentialOCL::ElseIfThenExpCS */,
						_Steps.Solve292 /* check-rule essentialoclcs::IfExpCS.ownedCondition : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
						_Steps.Solve295 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve293 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : EssentialOCL::ExpCS */,
						_Steps.Solve013 /* assert (|ownedElseExpression| - 1) == 0 */,
						_Steps.Solve061 /* assign C00:ElseIfThenExpCS[*] = |ownedIfThenExpressions| */,
						_Steps.Solve040 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve011 /* assert (|ownedCondition| - 1) == 0 */
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
						_Steps.Solve296 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : EssentialOCL::ExpCS */,
						_Steps.Solve297 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : EssentialOCL::ExpCS */,
						_Steps.Solve039 /* assert (|ownedThenExpression| - 1) == 0 */,
						_Steps.Solve010 /* assert (|ownedCondition| - 1) == 0 */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve044 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve084 /* assign C00[?] = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
						_Steps.Solve119 /* assign C01[+] = |qualifiers.'!ordered|!unique|ordered|unique'| */
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
						_Steps.Solve244 /* check-rule basecs::ImportCS.ownedPathName : EssentialOCL::URIPathNameCS */,
						_Steps.Solve126 /* assign C01[?] = |isAll.'::*'| */,
						_Steps.Solve033 /* assert (|ownedPathName| - 1) == 0 */,
						_Steps.Solve070 /* assign C00[?] = |name| */
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
						_Steps.Solve298 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve314 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::ExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
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
						_Steps.Solve298 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve314 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::ExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
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
						_Steps.Solve299 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
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
						_Steps.Solve299 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
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
						_Steps.Solve299 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
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
						_Steps.Solve299 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
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
						_Steps.Solve299 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : EssentialOCL::ExpCS */,
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
						_Steps.Solve301 /* check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS */,
						_Steps.Solve300 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS */,
						_Steps.Solve019 /* assert (|ownedInExpression| - 1) == 0 */,
						_Steps.Solve076 /* assign C00[*] = (|ownedVariables| - 1) */
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
						_Steps.Solve301 /* check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS */,
						_Steps.Solve300 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS */,
						_Steps.Solve019 /* assert (|ownedInExpression| - 1) == 0 */,
						_Steps.Solve076 /* assign C00[*] = (|ownedVariables| - 1) */
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
						_Steps.Solve301 /* check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS */,
						_Steps.Solve300 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS */,
						_Steps.Solve019 /* assert (|ownedInExpression| - 1) == 0 */,
						_Steps.Solve076 /* assign C00[*] = (|ownedVariables| - 1) */
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
						_Steps.Solve301 /* check-rule essentialoclcs::LetExpCS.ownedVariables : EssentialOCL::LetVariableCS */,
						_Steps.Solve300 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : EssentialOCL::ExpCS */,
						_Steps.Solve019 /* assert (|ownedInExpression| - 1) == 0 */,
						_Steps.Solve076 /* assign C00[*] = (|ownedVariables| - 1) */
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
						_Steps.Solve302 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve328 /* check-rule essentialoclcs::VariableCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve327 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve021 /* assert (|ownedInitExpression| - 1) == 0 */,
						_Steps.Solve134 /* assign C01[?] = |ownedType| */,
						_Steps.Solve063 /* assign C00:RoundBracketedClauseCS[?] = |ownedRoundBracketedClause| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step172 /* V00*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						_Steps.Step181 /* V01*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */,
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
						_Steps.Solve303 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve304 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve046 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve081 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve115 /* assign C01[*] = (|ownedParts| - 1) */
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
						_Steps.Solve303 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve304 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve046 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve081 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve115 /* assign C01[*] = (|ownedParts| - 1) */
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
						_Steps.Solve303 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve304 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve046 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve081 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve115 /* assign C01[*] = (|ownedParts| - 1) */
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
						_Steps.Solve303 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve304 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve046 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve081 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve115 /* assign C01[*] = (|ownedParts| - 1) */
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
						_Steps.Solve303 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : EssentialOCL::MapLiteralPartCS */,
						_Steps.Solve304 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : EssentialOCL::MapTypeCS */,
						_Steps.Solve046 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve081 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve115 /* assign C01[*] = (|ownedParts| - 1) */
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
						_Steps.Solve305 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : EssentialOCL::ExpCS */,
						_Steps.Solve306 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : EssentialOCL::ExpCS */,
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
						_Steps.Solve307 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve308 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve101 /* assign C00[?] = |ownedValueType| */,
						_Steps.Solve026 /* assert (|ownedKeyType| - C00) == 0 */,
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
						_Steps.Solve307 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve308 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve101 /* assign C00[?] = |ownedValueType| */,
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
						_Steps.Solve307 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve308 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve110 /* assign C01:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve101 /* assign C00[?] = |ownedValueType| */,
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
						_Steps.Solve307 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve308 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve101 /* assign C00[?] = |ownedValueType| */,
						_Steps.Solve026 /* assert (|ownedKeyType| - C00) == 0 */,
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
						_Steps.Solve307 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve308 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve101 /* assign C00[?] = |ownedValueType| */,
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
						_Steps.Solve307 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve308 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve110 /* assign C01:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve101 /* assign C00[?] = |ownedValueType| */,
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
				// { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						_Steps.Solve307 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve308 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve110 /* assign C01:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve101 /* assign C00[?] = |ownedValueType| */,
						_Steps.Solve023 /* assert (|ownedKeyType| - C00) == 0 */,
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
						_Steps.Solve307 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : EssentialOCL::TypeExpCS */,
						_Steps.Solve308 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : EssentialOCL::TypeExpCS */,
						_Steps.Solve101 /* assign C00[?] = |ownedValueType| */,
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
						_Steps.Solve246 /* check-rule basecs::ModelElementRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve036 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step054 /* 1*'reference' */,
						_Steps.Step121 /* 1*ownedPathName=PathNameCS */,
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
						_Steps.Solve106 /* assign C00[?] = |upperBound| */,
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
						_Steps.Solve106 /* assign C00[?] = |upperBound| */,
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
						_Steps.Solve106 /* assign C00[?] = |upperBound| */,
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
						_Steps.Solve127 /* assign C01[?] = |isNullFree.'|1'| */,
						_Steps.Solve106 /* assign C00[?] = |upperBound| */,
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
						_Steps.Solve088 /* assign C00[?] = |isNullFree.'|1'| */,
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
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve276 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve277 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve171 /* assign C03[?] = |isPre.'@'| */,
						_Steps.Solve139 /* assign C02:CurlyBracketedClauseCS[?] = |ownedCurlyBracketedClause| */,
						_Steps.Solve112 /* assign C01:RoundBracketedClauseCS[?] = |ownedRoundBracketedClause| */,
						_Steps.Solve066 /* assign C00:SquareBracketedClauseCS[*] = |ownedSquareBracketedClauses| */,
						_Steps.Solve034 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step120 /* 1*ownedPathName=PathNameCS */,
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
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve276 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve277 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve171 /* assign C03[?] = |isPre.'@'| */,
						_Steps.Solve139 /* assign C02:CurlyBracketedClauseCS[?] = |ownedCurlyBracketedClause| */,
						_Steps.Solve112 /* assign C01:RoundBracketedClauseCS[?] = |ownedRoundBracketedClause| */,
						_Steps.Solve066 /* assign C00:SquareBracketedClauseCS[*] = |ownedSquareBracketedClauses| */,
						_Steps.Solve034 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step120 /* 1*ownedPathName=PathNameCS */,
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
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve276 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve277 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve171 /* assign C03[?] = |isPre.'@'| */,
						_Steps.Solve139 /* assign C02:CurlyBracketedClauseCS[?] = |ownedCurlyBracketedClause| */,
						_Steps.Solve112 /* assign C01:RoundBracketedClauseCS[?] = |ownedRoundBracketedClause| */,
						_Steps.Solve066 /* assign C00:SquareBracketedClauseCS[*] = |ownedSquareBracketedClauses| */,
						_Steps.Solve034 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step120 /* 1*ownedPathName=PathNameCS */,
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
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve276 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve277 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve171 /* assign C03[?] = |isPre.'@'| */,
						_Steps.Solve139 /* assign C02:CurlyBracketedClauseCS[?] = |ownedCurlyBracketedClause| */,
						_Steps.Solve112 /* assign C01:RoundBracketedClauseCS[?] = |ownedRoundBracketedClause| */,
						_Steps.Solve066 /* assign C00:SquareBracketedClauseCS[*] = |ownedSquareBracketedClauses| */,
						_Steps.Solve034 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step120 /* 1*ownedPathName=PathNameCS */,
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
						_Steps.Solve279 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : EssentialOCL::SquareBracketedClauseCS */,
						_Steps.Solve276 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve278 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : EssentialOCL::RoundBracketedClauseCS */,
						_Steps.Solve277 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve171 /* assign C03[?] = |isPre.'@'| */,
						_Steps.Solve139 /* assign C02:CurlyBracketedClauseCS[?] = |ownedCurlyBracketedClause| */,
						_Steps.Solve112 /* assign C01:RoundBracketedClauseCS[?] = |ownedRoundBracketedClause| */,
						_Steps.Solve066 /* assign C00:SquareBracketedClauseCS[*] = |ownedSquareBracketedClauses| */,
						_Steps.Solve034 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-7-steps */,
						_Steps.Step120 /* 1*ownedPathName=PathNameCS */,
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
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
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve043 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve309 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve095 /* assign C00[?] = |ownedInitExpression| */,
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve309 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve131 /* assign C01[?] = |ownedInitExpression| */,
						_Steps.Solve092 /* assign C00[?] = |ownedCoIterator| */,
						_Steps.Solve043 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */,
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve309 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve022 /* assert (|ownedInitExpression| - 1) == 0 */,
						_Steps.Solve130 /* assign C01[?] = |ownedCoIterator| */,
						_Steps.Solve099 /* assign C00[?] = |ownedType| */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-9-steps */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */,
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve099 /* assign C00[?] = |ownedType| */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve050 /* assert (|prefix.'|'| - 1) == 0 */,
						_Steps.Solve131 /* assign C01[?] = |ownedInitExpression| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step061 /* 1*'|' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */,
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve309 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve095 /* assign C00[?] = |ownedInitExpression| */,
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve309 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve131 /* assign C01[?] = |ownedInitExpression| */,
						_Steps.Solve092 /* assign C00[?] = |ownedCoIterator| */,
						_Steps.Solve043 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve048 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */,
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve309 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : EssentialOCL::CoIteratorVariableCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve022 /* assert (|ownedInitExpression| - 1) == 0 */,
						_Steps.Solve130 /* assign C01[?] = |ownedCoIterator| */,
						_Steps.Solve099 /* assign C00[?] = |ownedType| */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve048 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-10-steps */,
						_Steps.Step007 /* 1*',' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */,
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
						_Steps.Solve311 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : EssentialOCL::NavigatingArgExpCS */,
						_Steps.Solve312 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve310 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve099 /* assign C00[?] = |ownedType| */,
						_Steps.Solve030 /* assert (|ownedNameExpression| - 1) == 0 */,
						_Steps.Solve049 /* assert (|prefix.';'| - 1) == 0 */,
						_Steps.Solve131 /* assign C01[?] = |ownedInitExpression| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-8-steps */,
						_Steps.Step011 /* 1*';' */,
						_Steps.Step108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						_Steps.Step169 /* V00*next-5-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step138 /* 1*ownedType=TypeExpCS */,
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
						_Steps.Solve313 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step089 /* 1*ownedExpression=ExpCS */,
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
						_Steps.Solve313 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step089 /* 1*ownedExpression=ExpCS */,
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
						_Steps.Solve313 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step089 /* 1*ownedExpression=ExpCS */,
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
						_Steps.Solve313 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step089 /* 1*ownedExpression=ExpCS */,
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
						_Steps.Solve313 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : EssentialOCL::ExpCS */,
						_Steps.Solve018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-3-steps */,
						_Steps.Step003 /* 1*'(' */,
						_Steps.Step089 /* 1*ownedExpression=ExpCS */,
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
						_Steps.Solve241 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve128 /* assign C01[?] = |name| */,
						_Steps.Solve055 /* assert (|stereotype.'invariant'| - 1) == 0 */,
						_Steps.Solve087 /* assign C00[?] = |isCallable.'callable'| */,
						_Steps.Solve155 /* assign C02[?] = |ownedMessageSpecification| */
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
						_Steps.Solve241 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve242 /* check-rule basecs::ConstraintCS.ownedSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve160 /* assign C03:SpecificationCS[?] = |ownedSpecification| */,
						_Steps.Solve128 /* assign C01[?] = |name| */,
						_Steps.Solve055 /* assert (|stereotype.'invariant'| - 1) == 0 */,
						_Steps.Solve087 /* assign C00[?] = |isCallable.'callable'| */,
						_Steps.Solve155 /* assign C02[?] = |ownedMessageSpecification| */
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
						_Steps.Solve241 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve242 /* check-rule basecs::ConstraintCS.ownedSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve142 /* assign C02:SpecificationCS[?] = |ownedSpecification| */,
						_Steps.Solve070 /* assign C00[?] = |name| */,
						_Steps.Solve056 /* assert (|stereotype.'postcondition'| - 1) == 0 */,
						_Steps.Solve132 /* assign C01[?] = |ownedMessageSpecification| */
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
						_Steps.Solve241 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve242 /* check-rule basecs::ConstraintCS.ownedSpecification : OCLinEcore::SpecificationCS */,
						_Steps.Solve142 /* assign C02:SpecificationCS[?] = |ownedSpecification| */,
						_Steps.Solve070 /* assign C00[?] = |name| */,
						_Steps.Solve057 /* assert (|stereotype.'precondition'| - 1) == 0 */,
						_Steps.Solve132 /* assign C01[?] = |ownedMessageSpecification| */
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
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve173 /* assign C03[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve202 /* assign C06[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve209 /* assign C07[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						_Steps.Solve180 /* assign C04[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve189 /* assign C05[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve121 /* assign C01[?] = (|ownedParameters| > 0) */,
						_Steps.Solve144 /* assign C02[*] = (|ownedParameters| - 1) */
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
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve184 /* assign C04[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve193 /* assign C05[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve198 /* assign C06[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve148 /* assign C02[?] = (|ownedParameters| > 0) */,
						_Steps.Solve161 /* assign C03[*] = (|ownedParameters| - 1) */,
						_Steps.Solve210 /* assign C07[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve216 /* assign C08[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
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
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve184 /* assign C04[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve193 /* assign C05[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve198 /* assign C06[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve148 /* assign C02[?] = (|ownedParameters| > 0) */,
						_Steps.Solve161 /* assign C03[*] = (|ownedParameters| - 1) */,
						_Steps.Solve210 /* assign C07[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve216 /* assign C08[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve247 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve231 /* assign C12:PostconditionConstraintCS[*] = |ownedPostconditions| */,
						_Steps.Solve218 /* assign C09:PreconditionConstraintCS[*] = |ownedPreconditions| */,
						_Steps.Solve211 /* assign C08:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve173 /* assign C03[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve228 /* assign C11:SpecificationCS[?] = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve227 /* assign C10[*] = |ownedBodyExpressions| */,
						_Steps.Solve202 /* assign C06[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve209 /* assign C07[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						_Steps.Solve180 /* assign C04[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve189 /* assign C05[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve121 /* assign C01[?] = (|ownedParameters| > 0) */,
						_Steps.Solve144 /* assign C02[*] = (|ownedParameters| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve247 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve235 /* assign C13:PostconditionConstraintCS[*] = |ownedPostconditions| */,
						_Steps.Solve224 /* assign C10:PreconditionConstraintCS[*] = |ownedPreconditions| */,
						_Steps.Solve217 /* assign C09:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve184 /* assign C04[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve232 /* assign C12:SpecificationCS[?] = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve230 /* assign C11[*] = |ownedBodyExpressions| */,
						_Steps.Solve193 /* assign C05[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve198 /* assign C06[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve148 /* assign C02[?] = (|ownedParameters| > 0) */,
						_Steps.Solve161 /* assign C03[*] = (|ownedParameters| - 1) */,
						_Steps.Solve210 /* assign C07[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve216 /* assign C08[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve247 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve235 /* assign C13:PostconditionConstraintCS[*] = |ownedPostconditions| */,
						_Steps.Solve224 /* assign C10:PreconditionConstraintCS[*] = |ownedPreconditions| */,
						_Steps.Solve217 /* assign C09:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve184 /* assign C04[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve232 /* assign C12:SpecificationCS[?] = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve230 /* assign C11[*] = |ownedBodyExpressions| */,
						_Steps.Solve193 /* assign C05[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve198 /* assign C06[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve148 /* assign C02[?] = (|ownedParameters| > 0) */,
						_Steps.Solve161 /* assign C03[*] = (|ownedParameters| - 1) */,
						_Steps.Solve210 /* assign C07[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve216 /* assign C08[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
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
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve173 /* assign C03[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve202 /* assign C06[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve209 /* assign C07[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						_Steps.Solve180 /* assign C04[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve189 /* assign C05[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve121 /* assign C01[?] = (|ownedParameters| > 0) */,
						_Steps.Solve144 /* assign C02[*] = (|ownedParameters| - 1) */
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
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve184 /* assign C04[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve193 /* assign C05[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve198 /* assign C06[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve148 /* assign C02[?] = (|ownedParameters| > 0) */,
						_Steps.Solve161 /* assign C03[*] = (|ownedParameters| - 1) */,
						_Steps.Solve210 /* assign C07[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve216 /* assign C08[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
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
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve184 /* assign C04[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve193 /* assign C05[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve198 /* assign C06[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve148 /* assign C02[?] = (|ownedParameters| > 0) */,
						_Steps.Solve161 /* assign C03[*] = (|ownedParameters| - 1) */,
						_Steps.Solve210 /* assign C07[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve216 /* assign C08[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve247 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve231 /* assign C12:PostconditionConstraintCS[*] = |ownedPostconditions| */,
						_Steps.Solve218 /* assign C09:PreconditionConstraintCS[*] = |ownedPreconditions| */,
						_Steps.Solve211 /* assign C08:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve173 /* assign C03[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve068 /* assign C00:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve228 /* assign C11:SpecificationCS[?] = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve227 /* assign C10[*] = |ownedBodyExpressions| */,
						_Steps.Solve202 /* assign C06[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve209 /* assign C07[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						_Steps.Solve180 /* assign C04[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve189 /* assign C05[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve121 /* assign C01[?] = (|ownedParameters| > 0) */,
						_Steps.Solve144 /* assign C02[*] = (|ownedParameters| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve247 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve235 /* assign C13:PostconditionConstraintCS[*] = |ownedPostconditions| */,
						_Steps.Solve224 /* assign C10:PreconditionConstraintCS[*] = |ownedPreconditions| */,
						_Steps.Solve217 /* assign C09:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve184 /* assign C04[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve232 /* assign C12:SpecificationCS[?] = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve230 /* assign C11[*] = |ownedBodyExpressions| */,
						_Steps.Solve193 /* assign C05[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve198 /* assign C06[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve148 /* assign C02[?] = (|ownedParameters| > 0) */,
						_Steps.Solve161 /* assign C03[*] = (|ownedParameters| - 1) */,
						_Steps.Solve210 /* assign C07[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve216 /* assign C08[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve249 /* check-rule basecs::OperationCS.ownedParameters : OCLinEcore::ParameterCS */,
						_Steps.Solve251 /* check-rule basecs::OperationCS.ownedPreconditions : OCLinEcore::PreconditionConstraintCS */,
						_Steps.Solve247 /* check-rule basecs::OperationCS.ownedBodyExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve250 /* check-rule basecs::OperationCS.ownedPostconditions : OCLinEcore::PostconditionConstraintCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve248 /* check-rule basecs::OperationCS.ownedExceptions : OCLinEcore::TypedRefCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve235 /* assign C13:PostconditionConstraintCS[*] = |ownedPostconditions| */,
						_Steps.Solve224 /* assign C10:PreconditionConstraintCS[*] = |ownedPreconditions| */,
						_Steps.Solve217 /* assign C09:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve184 /* assign C04[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve232 /* assign C12:SpecificationCS[?] = (|ownedBodyExpressions| > 0) */,
						_Steps.Solve230 /* assign C11[*] = |ownedBodyExpressions| */,
						_Steps.Solve193 /* assign C05[?] = (|ownedExceptions| > 0) */,
						_Steps.Solve198 /* assign C06[*] = (|ownedExceptions| - 1) */,
						_Steps.Solve148 /* assign C02[?] = (|ownedParameters| > 0) */,
						_Steps.Solve161 /* assign C03[*] = (|ownedParameters| - 1) */,
						_Steps.Solve210 /* assign C07[?] = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						_Steps.Solve216 /* assign C08[+] = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
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
						_Steps.Solve129 /* assign C01[?] = |nsURI| */,
						_Steps.Solve091 /* assign C00[?] = |nsPrefix| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve253 /* check-rule basecs::PackageOwnerCS.ownedPackages : OCLinEcore::PackageCS */,
						_Steps.Solve252 /* check-rule basecs::PackageCS.ownedClasses : OCLinEcore::ClassCS */,
						_Steps.Solve175 /* assign C04:ClassCS[*] = |ownedClasses| */,
						_Steps.Solve159 /* assign C03:PackageCS[*] = |ownedPackages| */,
						_Steps.Solve138 /* assign C02:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve129 /* assign C01[?] = |nsURI| */,
						_Steps.Solve091 /* assign C00[?] = |nsPrefix| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve129 /* assign C01[?] = |nsURI| */,
						_Steps.Solve091 /* assign C00[?] = |nsPrefix| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve253 /* check-rule basecs::PackageOwnerCS.ownedPackages : OCLinEcore::PackageCS */,
						_Steps.Solve252 /* check-rule basecs::PackageCS.ownedClasses : OCLinEcore::ClassCS */,
						_Steps.Solve175 /* assign C04:ClassCS[*] = |ownedClasses| */,
						_Steps.Solve159 /* assign C03:PackageCS[*] = |ownedPackages| */,
						_Steps.Solve138 /* assign C02:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve129 /* assign C01[?] = |nsURI| */,
						_Steps.Solve091 /* assign C00[?] = |nsPrefix| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve100 /* assign C00[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve166 /* assign C03[?] = (|ownedAnnotations| > 0) */,
						_Steps.Solve174 /* assign C04:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve123 /* assign C01[?] = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
						_Steps.Solve146 /* assign C02[+] = |qualifiers.'!ordered|!unique|ordered|unique'| */
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
						_Steps.Step150 /* 1*referredElement=UnrestrictedName */
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
						_Steps.Step151 /* 1*referredElement=UnrestrictedName */
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
						_Steps.Solve255 /* check-rule basecs::PathNameCS.ownedPathElements : Base::FirstPathElementCS|Base::NextPathElementCS */,
						_Steps.Solve073 /* assign C00[*] = (|ownedPathElements| - 1) */
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
						_Steps.Solve254 /* check-rule basecs::PathNameCS.ownedPathElements : Base::FirstPathElementCS */,
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
						_Steps.Solve256 /* check-rule basecs::PathNameCS.ownedPathElements : EssentialOCL::URIFirstPathElementCS|Base::NextPathElementCS */,
						_Steps.Solve073 /* assign C00[*] = (|ownedPathElements| - 1) */
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
						_Steps.Solve317 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : EssentialOCL::TypeExpCS */,
						_Steps.Solve037 /* assert (|ownedPatternType| - 1) == 0 */,
						_Steps.Solve069 /* assign C00:UnrestrictedName[?] = |patternVariableName| */
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
						_Steps.Solve316 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve315 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedLetExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve316 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve315 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedLetExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve315 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedLetExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve316 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : EssentialOCL::PrefixedPrimaryExpCS */,
						_Steps.Solve038 /* assert (|ownedRight| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve062 /* assign C00:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve062 /* assign C00:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve062 /* assign C00:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve006 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve104 /* assign C00[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve167 /* assign C03[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve178 /* assign C04[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve257 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve234 /* assign C12[*] = |ownedImplicitOpposites| */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve104 /* assign C00[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve167 /* assign C03[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve178 /* assign C04[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve199 /* assign C06[*] = (|referredKeys| > 0) */,
						_Steps.Solve206 /* assign C07[*] = (|referredKeys| - 1) */,
						_Steps.Solve215 /* assign C08[*] = |ownedDefaultExpressions| */,
						_Steps.Solve219 /* assign C09:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve226 /* assign C10[*] = 0 */,
						_Steps.Solve229 /* assign C11:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve257 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve236 /* assign C13[*] = |ownedImplicitOpposites| */,
						_Steps.Solve195 /* assign C06:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve207 /* assign C07[*] = (|referredKeys| > 0) */,
						_Steps.Solve214 /* assign C08[*] = (|referredKeys| - 1) */,
						_Steps.Solve222 /* assign C09[*] = |ownedDefaultExpressions| */,
						_Steps.Solve225 /* assign C10:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve229 /* assign C11[*] = 0 */,
						_Steps.Solve233 /* assign C12:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve257 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve236 /* assign C13[*] = |ownedImplicitOpposites| */,
						_Steps.Solve195 /* assign C06:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve207 /* assign C07[*] = (|referredKeys| > 0) */,
						_Steps.Solve214 /* assign C08[*] = (|referredKeys| - 1) */,
						_Steps.Solve222 /* assign C09[*] = |ownedDefaultExpressions| */,
						_Steps.Solve225 /* assign C10:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve229 /* assign C11[*] = 0 */,
						_Steps.Solve233 /* assign C12:SpecificationCS[?] = 0 */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve104 /* assign C00[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve167 /* assign C03[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve178 /* assign C04[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve257 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve234 /* assign C12[*] = |ownedImplicitOpposites| */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve104 /* assign C00[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve167 /* assign C03[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve178 /* assign C04[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve199 /* assign C06[*] = (|referredKeys| > 0) */,
						_Steps.Solve206 /* assign C07[*] = (|referredKeys| - 1) */,
						_Steps.Solve215 /* assign C08[*] = |ownedDefaultExpressions| */,
						_Steps.Solve219 /* assign C09:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve226 /* assign C10[*] = 0 */,
						_Steps.Solve229 /* assign C11:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve257 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve236 /* assign C13[*] = |ownedImplicitOpposites| */,
						_Steps.Solve195 /* assign C06:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve207 /* assign C07[*] = (|referredKeys| > 0) */,
						_Steps.Solve214 /* assign C08[*] = (|referredKeys| - 1) */,
						_Steps.Solve222 /* assign C09[*] = |ownedDefaultExpressions| */,
						_Steps.Solve225 /* assign C10:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve229 /* assign C11[*] = 0 */,
						_Steps.Solve233 /* assign C12:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve257 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve236 /* assign C13[*] = |ownedImplicitOpposites| */,
						_Steps.Solve195 /* assign C06:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve207 /* assign C07[*] = (|referredKeys| > 0) */,
						_Steps.Solve214 /* assign C08[*] = (|referredKeys| - 1) */,
						_Steps.Solve222 /* assign C09[*] = |ownedDefaultExpressions| */,
						_Steps.Solve225 /* assign C10:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve229 /* assign C11[*] = 0 */,
						_Steps.Solve233 /* assign C12:SpecificationCS[?] = 0 */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve104 /* assign C00[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve167 /* assign C03[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve178 /* assign C04[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve257 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve234 /* assign C12[*] = |ownedImplicitOpposites| */,
						_Steps.Solve185 /* assign C05:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve152 /* assign C02[?] = |default| */,
						_Steps.Solve135 /* assign C01[?] = |ownedType| */,
						_Steps.Solve104 /* assign C00[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve167 /* assign C03[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve178 /* assign C04[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve199 /* assign C06[*] = (|referredKeys| > 0) */,
						_Steps.Solve206 /* assign C07[*] = (|referredKeys| - 1) */,
						_Steps.Solve215 /* assign C08[*] = |ownedDefaultExpressions| */,
						_Steps.Solve219 /* assign C09:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve226 /* assign C10[*] = 0 */,
						_Steps.Solve229 /* assign C11:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve257 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve236 /* assign C13[*] = |ownedImplicitOpposites| */,
						_Steps.Solve195 /* assign C06:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve103 /* assign C00[?] = |qualifiers.'static'| */,
						_Steps.Solve051 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve207 /* assign C07[*] = (|referredKeys| > 0) */,
						_Steps.Solve214 /* assign C08[*] = (|referredKeys| - 1) */,
						_Steps.Solve222 /* assign C09[*] = |ownedDefaultExpressions| */,
						_Steps.Solve225 /* assign C10:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve229 /* assign C11[*] = 0 */,
						_Steps.Solve233 /* assign C12:SpecificationCS[?] = 0 */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve257 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : OCLinEcore::ImplicitOppositeCS */,
						_Steps.Solve259 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : OCLinEcore::SpecificationCS */,
						_Steps.Solve271 /* check-rule basecs::TypedElementCS.ownedType : OCLinEcore::TypedMultiplicityRefCS */,
						_Steps.Solve236 /* assign C13[*] = |ownedImplicitOpposites| */,
						_Steps.Solve195 /* assign C06:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve170 /* assign C03[?] = |default| */,
						_Steps.Solve156 /* assign C02[?] = |ownedType| */,
						_Steps.Solve136 /* assign C01[?] = |referredOpposite| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve102 /* assign C00[?] = |qualifiers.'definition'| */,
						_Steps.Solve052 /* assert (|qualifiers.'static'| - 1) == 0 */,
						_Steps.Solve181 /* assign C04[?] = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						_Steps.Solve191 /* assign C05[+] = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						_Steps.Solve207 /* assign C07[*] = (|referredKeys| > 0) */,
						_Steps.Solve214 /* assign C08[*] = (|referredKeys| - 1) */,
						_Steps.Solve222 /* assign C09[*] = |ownedDefaultExpressions| */,
						_Steps.Solve225 /* assign C10:SpecificationCS[?] = (|ownedDefaultExpressions| > 0) */,
						_Steps.Solve229 /* assign C11[*] = 0 */,
						_Steps.Solve233 /* assign C12:SpecificationCS[?] = 0 */
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
						_Steps.Solve318 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : EssentialOCL::NavigatingCommaArgCS|EssentialOCL::NavigatingArgCS|EssentialOCL::NavigatingSemiArgCS|EssentialOCL::NavigatingBarArgCS */,
						_Steps.Solve078 /* assign C00[?] = (|ownedArguments| > 0) */,
						_Steps.Solve111 /* assign C01:NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS[*] = (|ownedArguments| - 1) */
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
						_Steps.Solve320 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : EssentialOCL::StringLiteralExpCS */,
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
						_Steps.Solve319 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : EssentialOCL::ExpCS|EssentialOCL::PatternExpCS */,
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
						_Steps.Solve321 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : EssentialOCL::ExpCS */,
						_Steps.Solve075 /* assign C00[*] = (|ownedTerms| - 1) */
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
						_Steps.Solve067 /* assign C00:StringLiteral[+] = |segments| */
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
						_Steps.Solve067 /* assign C00:StringLiteral[+] = |segments| */
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
						_Steps.Solve067 /* assign C00:StringLiteral[+] = |segments| */
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
						_Steps.Solve067 /* assign C00:StringLiteral[+] = |segments| */
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
						_Steps.Solve067 /* assign C00:StringLiteral[+] = |segments| */
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
						_Steps.Solve067 /* assign C00:StringLiteral[+] = |segments| */
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
						_Steps.Solve262 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve182 /* assign C04[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve086 /* assign C00[?] = |isAbstract.'abstract'| */,
						_Steps.Solve201 /* assign C06[?] = (|isInterface.'interface'| > 0) */,
						_Steps.Solve194 /* assign C05[?] = |isInterface.'interface'| */,
						_Steps.Solve149 /* assign C02[?] = (|ownedSuperTypes| > 0) */,
						_Steps.Solve162 /* assign C03[*] = (|ownedSuperTypes| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve262 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve260 /* check-rule basecs::StructuredClassCS.ownedOperations : OCLinEcore::OperationCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve261 /* check-rule basecs::StructuredClassCS.ownedProperties : OCLinEcore::StructuralFeatureCS */,
						_Steps.Solve223 /* assign C10:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve221 /* assign C09:StructuralFeatureCS[*] = |ownedProperties| */,
						_Steps.Solve212 /* assign C08:OperationCS[*] = |ownedOperations| */,
						_Steps.Solve204 /* assign C07:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve182 /* assign C04[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve086 /* assign C00[?] = |isAbstract.'abstract'| */,
						_Steps.Solve201 /* assign C06[?] = (|isInterface.'interface'| > 0) */,
						_Steps.Solve194 /* assign C05[?] = |isInterface.'interface'| */,
						_Steps.Solve149 /* assign C02[?] = (|ownedSuperTypes| > 0) */,
						_Steps.Solve162 /* assign C03[*] = (|ownedSuperTypes| - 1) */
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
						_Steps.Solve262 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve182 /* assign C04[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve086 /* assign C00[?] = |isAbstract.'abstract'| */,
						_Steps.Solve201 /* assign C06[?] = (|isInterface.'interface'| > 0) */,
						_Steps.Solve194 /* assign C05[?] = |isInterface.'interface'| */,
						_Steps.Solve149 /* assign C02[?] = (|ownedSuperTypes| > 0) */,
						_Steps.Solve162 /* assign C03[*] = (|ownedSuperTypes| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve262 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve260 /* check-rule basecs::StructuredClassCS.ownedOperations : OCLinEcore::OperationCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve261 /* check-rule basecs::StructuredClassCS.ownedProperties : OCLinEcore::StructuralFeatureCS */,
						_Steps.Solve223 /* assign C10:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve221 /* assign C09:StructuralFeatureCS[*] = |ownedProperties| */,
						_Steps.Solve212 /* assign C08:OperationCS[*] = |ownedOperations| */,
						_Steps.Solve204 /* assign C07:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve182 /* assign C04[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve086 /* assign C00[?] = |isAbstract.'abstract'| */,
						_Steps.Solve201 /* assign C06[?] = (|isInterface.'interface'| > 0) */,
						_Steps.Solve194 /* assign C05[?] = |isInterface.'interface'| */,
						_Steps.Solve149 /* assign C02[?] = (|ownedSuperTypes| > 0) */,
						_Steps.Solve162 /* assign C03[*] = (|ownedSuperTypes| - 1) */
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
						_Steps.Solve262 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve182 /* assign C04[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve086 /* assign C00[?] = |isAbstract.'abstract'| */,
						_Steps.Solve201 /* assign C06[?] = (|isInterface.'interface'| > 0) */,
						_Steps.Solve194 /* assign C05[?] = |isInterface.'interface'| */,
						_Steps.Solve149 /* assign C02[?] = (|ownedSuperTypes| > 0) */,
						_Steps.Solve162 /* assign C03[*] = (|ownedSuperTypes| - 1) */
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
						_Steps.Solve245 /* check-rule basecs::ModelElementCS.ownedAnnotations : OCLinEcore::AnnotationElementCS */,
						_Steps.Solve262 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : OCLinEcore::TypedRefCS */,
						_Steps.Solve260 /* check-rule basecs::StructuredClassCS.ownedOperations : OCLinEcore::OperationCS */,
						_Steps.Solve267 /* check-rule basecs::TemplateableElementCS.ownedSignature : OCLinEcore::TemplateSignatureCS */,
						_Steps.Solve240 /* check-rule basecs::ClassCS.ownedConstraints : OCLinEcore::InvariantConstraintCS */,
						_Steps.Solve261 /* check-rule basecs::StructuredClassCS.ownedProperties : OCLinEcore::StructuralFeatureCS */,
						_Steps.Solve223 /* assign C10:InvariantConstraintCS[*] = |ownedConstraints| */,
						_Steps.Solve221 /* assign C09:StructuralFeatureCS[*] = |ownedProperties| */,
						_Steps.Solve212 /* assign C08:OperationCS[*] = |ownedOperations| */,
						_Steps.Solve204 /* assign C07:AnnotationElementCS[*] = |ownedAnnotations| */,
						_Steps.Solve182 /* assign C04[?] = |instanceClassName| */,
						_Steps.Solve113 /* assign C01:TemplateSignatureCS[?] = |ownedSignature| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve086 /* assign C00[?] = |isAbstract.'abstract'| */,
						_Steps.Solve192 /* assign C05[?] = (|isInterface.'interface'| > 0) */,
						_Steps.Solve203 /* assign C06[?] = |isInterface.'interface'| */,
						_Steps.Solve149 /* assign C02[?] = (|ownedSuperTypes| > 0) */,
						_Steps.Solve162 /* assign C03[*] = (|ownedSuperTypes| - 1) */
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
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
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
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve077 /* assign C00[*] = |ownedDetails| */
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
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
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
						_Steps.Solve239 /* check-rule basecs::AnnotationElementCS.ownedDetails : OCLinEcore::DetailCS */,
						_Steps.Solve077 /* assign C00[*] = |ownedDetails| */
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
						_Steps.Solve263 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve264 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : Base::TemplateParameterSubstitutionCS */,
						_Steps.Solve109 /* assign C01:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve074 /* assign C00[*] = (|ownedSubstitutions| - 1) */
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
						_Steps.Solve265 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : Base::TypeRefCS */,
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
						_Steps.Solve266 /* check-rule basecs::TemplateSignatureCS.ownedParameters : Base::TypeParameterCS */,
						_Steps.Solve071 /* assign C00[*] = (|ownedParameters| - 1) */
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
						_Steps.Solve266 /* check-rule basecs::TemplateSignatureCS.ownedParameters : Base::TypeParameterCS */,
						_Steps.Solve071 /* assign C00[*] = (|ownedParameters| - 1) */
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
						_Steps.Solve258 /* check-rule basecs::RootCS.ownedImports : OCLinEcore::ImportCS */,
						_Steps.Solve253 /* check-rule basecs::PackageOwnerCS.ownedPackages : OCLinEcore::PackageCS */,
						_Steps.Solve141 /* assign C02:PackageCS[*] = |ownedPackages| */,
						_Steps.Solve107 /* assign C01:ImportCS[*] = |ownedImports| */,
						_Steps.Solve085 /* assign C00[?] = 0 */
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
						_Steps.Solve322 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve072 /* assign C00[*] = (|ownedParts| - 1) */
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
						_Steps.Solve322 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve072 /* assign C00[*] = (|ownedParts| - 1) */
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
						_Steps.Solve322 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve072 /* assign C00[*] = (|ownedParts| - 1) */
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
						_Steps.Solve322 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve072 /* assign C00[*] = (|ownedParts| - 1) */
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
						_Steps.Solve322 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : EssentialOCL::TupleLiteralPartCS */,
						_Steps.Solve072 /* assign C00[*] = (|ownedParts| - 1) */
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
						_Steps.Solve328 /* check-rule essentialoclcs::VariableCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve327 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : EssentialOCL::ExpCS */,
						_Steps.Solve021 /* assert (|ownedInitExpression| - 1) == 0 */,
						_Steps.Solve097 /* assign C00[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-6-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */,
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
						_Steps.Solve270 /* check-rule basecs::TypedElementCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve044 /* assert (|ownedType| - 1) == 0 */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
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
						_Steps.Solve268 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve083 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve122 /* assign C01[?] = (|ownedParts| > 0) */,
						_Steps.Solve145 /* assign C02[*] = (|ownedParts| - 1) */
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
						_Steps.Solve268 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve122 /* assign C01[?] = (|ownedParts| > 0) */,
						_Steps.Solve083 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve145 /* assign C02[*] = (|ownedParts| - 1) */
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
						_Steps.Solve268 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve158 /* assign C03:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve083 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve122 /* assign C01[?] = (|ownedParts| > 0) */,
						_Steps.Solve145 /* assign C02[*] = (|ownedParts| - 1) */
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
						_Steps.Solve268 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve083 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve122 /* assign C01[?] = (|ownedParts| > 0) */,
						_Steps.Solve145 /* assign C02[*] = (|ownedParts| - 1) */
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
						_Steps.Solve268 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve122 /* assign C01[?] = (|ownedParts| > 0) */,
						_Steps.Solve083 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve145 /* assign C02[*] = (|ownedParts| - 1) */
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
						_Steps.Solve268 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve158 /* assign C03:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve122 /* assign C01[?] = (|ownedParts| > 0) */,
						_Steps.Solve083 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve145 /* assign C02[*] = (|ownedParts| - 1) */
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
						_Steps.Solve268 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve158 /* assign C03:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve122 /* assign C01[?] = (|ownedParts| > 0) */,
						_Steps.Solve083 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve145 /* assign C02[*] = (|ownedParts| - 1) */
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
						_Steps.Solve268 /* check-rule basecs::TupleTypeCS.ownedParts : EssentialOCL::TuplePartCS */,
						_Steps.Solve003 /* assert (|name.'Tuple'| - 1) == 0 */,
						_Steps.Solve122 /* assign C01[?] = (|ownedParts| > 0) */,
						_Steps.Solve083 /* assign C00[?] = (|ownedParts| > 0) */,
						_Steps.Solve145 /* assign C02[*] = (|ownedParts| - 1) */
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
						_Steps.Solve323 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */
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
						_Steps.Solve323 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */
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
						_Steps.Solve323 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */
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
						_Steps.Solve323 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */
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
						_Steps.Solve323 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : EssentialOCL::TypeLiteralWithMultiplicityCS */,
						_Steps.Solve041 /* assert (|ownedType| - 1) == 0 */
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
						_Steps.Solve325 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve324 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve326 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : EssentialOCL::ExpCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve140 /* assign C02:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve093 /* assign C00[?] = |ownedCurlyBracketedClause| */,
						_Steps.Solve035 /* assert (|ownedPathName| - 1) == 0 */,
						_Steps.Solve133 /* assign C01[?] = |ownedPatternGuard| */
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
						_Steps.Solve325 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve324 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve326 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : EssentialOCL::ExpCS */,
						_Steps.Solve093 /* assign C00[?] = |ownedCurlyBracketedClause| */,
						_Steps.Solve035 /* assert (|ownedPathName| - 1) == 0 */,
						_Steps.Solve133 /* assign C01[?] = |ownedPatternGuard| */
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
						_Steps.Solve325 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve324 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : EssentialOCL::CurlyBracketedClauseCS */,
						_Steps.Solve326 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : EssentialOCL::ExpCS */,
						_Steps.Solve093 /* assign C00[?] = |ownedCurlyBracketedClause| */,
						_Steps.Solve035 /* assert (|ownedPathName| - 1) == 0 */,
						_Steps.Solve133 /* assign C01[?] = |ownedPatternGuard| */
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
						_Steps.Solve269 /* check-rule basecs::TypeParameterCS.ownedExtends : OCLinEcore::TypedRefCS */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */,
						_Steps.Solve079 /* assign C00[?] = (|ownedExtends| > 0) */,
						_Steps.Solve114 /* assign C01[*] = (|ownedExtends| - 1) */
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
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve273 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve273 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve062 /* assign C00:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve273 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve062 /* assign C00:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve273 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve272 /* check-rule basecs::TypedRefCS.ownedMultiplicity : Base::MultiplicityCS */,
						_Steps.Solve062 /* assign C00:MultiplicityCS[?] = |ownedMultiplicity| */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve273 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve273 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve273 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve273 /* check-rule basecs::TypedTypeRefCS.ownedBinding : Base::TemplateBindingCS */,
						_Steps.Solve274 /* check-rule basecs::TypedTypeRefCS.ownedPathName : Base::PathNameCS */,
						_Steps.Solve008 /* assert (|ownedBinding| - 1) == 0 */,
						_Steps.Solve032 /* assert (|ownedPathName| - 1) == 0 */
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
						_Steps.Solve328 /* check-rule essentialoclcs::VariableCS.ownedType : EssentialOCL::TypeExpCS */,
						_Steps.Solve097 /* assign C00[?] = |ownedType| */,
						_Steps.Solve004 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						_Steps.Step076 /* 1*next-4-steps */,
						_Steps.Step074 /* 1*name=UnrestrictedName */,
						_Steps.Step169 /* V00*next-2-steps */,
						_Steps.Step009 /* 1*':' */,
						_Steps.Step140 /* 1*ownedType=TypeExpCS */
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
						_Steps.Solve275 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : OCLinEcore::TypedRefCS */,
						_Steps.Solve094 /* assign C00[?] = |ownedExtends| */
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
						_Steps.Solve275 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : OCLinEcore::TypedRefCS */,
						_Steps.Solve094 /* assign C00[?] = |ownedExtends| */
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
