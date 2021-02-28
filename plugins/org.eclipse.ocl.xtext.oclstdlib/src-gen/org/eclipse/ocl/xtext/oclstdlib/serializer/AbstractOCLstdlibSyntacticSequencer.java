/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclstdlib.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.oclstdlib.services.OCLstdlibGrammarAccess;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public abstract class AbstractOCLstdlibSyntacticSequencer extends AbstractSyntacticSequencer {

	protected OCLstdlibGrammarAccess grammarAccess;
	protected AbstractElementAlias match_LibCoercionCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__;
	protected AbstractElementAlias match_LibIterationCS_SemicolonKeyword_14_1_or___LeftCurlyBracketKeyword_14_0_0_RightCurlyBracketKeyword_14_0_2__;
	protected AbstractElementAlias match_LibOperationCS_SemicolonKeyword_13_1_or___LeftCurlyBracketKeyword_13_0_0_RightCurlyBracketKeyword_13_0_2__;
	protected AbstractElementAlias match_LibOperationCS_UnrestrictedNameParserRuleCall_13_0_1_1_1_q;
	protected AbstractElementAlias match_LibPackageCS___SemicolonKeyword_4_1_2_PrecedenceKeyword_4_1_0__q;
	protected AbstractElementAlias match_LibPropertyCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__;
	protected AbstractElementAlias match_MultiplicityCS_VerticalLineQuestionMarkKeyword_2_0_q;
	protected AbstractElementAlias match_TupleTypeCS___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q;

	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (OCLstdlibGrammarAccess) access;
		match_LibCoercionCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getLibCoercionCSAccess().getLeftCurlyBracketKeyword_7_0_0()), new TokenAlias(false, false, grammarAccess.getLibCoercionCSAccess().getRightCurlyBracketKeyword_7_0_2())), new TokenAlias(false, false, grammarAccess.getLibCoercionCSAccess().getSemicolonKeyword_7_1()));
		match_LibIterationCS_SemicolonKeyword_14_1_or___LeftCurlyBracketKeyword_14_0_0_RightCurlyBracketKeyword_14_0_2__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getLibIterationCSAccess().getLeftCurlyBracketKeyword_14_0_0()), new TokenAlias(false, false, grammarAccess.getLibIterationCSAccess().getRightCurlyBracketKeyword_14_0_2())), new TokenAlias(false, false, grammarAccess.getLibIterationCSAccess().getSemicolonKeyword_14_1()));
		match_LibOperationCS_SemicolonKeyword_13_1_or___LeftCurlyBracketKeyword_13_0_0_RightCurlyBracketKeyword_13_0_2__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getLibOperationCSAccess().getLeftCurlyBracketKeyword_13_0_0()), new TokenAlias(false, false, grammarAccess.getLibOperationCSAccess().getRightCurlyBracketKeyword_13_0_2())), new TokenAlias(false, false, grammarAccess.getLibOperationCSAccess().getSemicolonKeyword_13_1()));
		match_LibOperationCS_UnrestrictedNameParserRuleCall_13_0_1_1_1_q = new TokenAlias(false, true, grammarAccess.getLibOperationCSAccess().getUnrestrictedNameParserRuleCall_13_0_1_1_1());
		match_LibPackageCS___SemicolonKeyword_4_1_2_PrecedenceKeyword_4_1_0__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getLibPackageCSAccess().getSemicolonKeyword_4_1_2()), new TokenAlias(false, false, grammarAccess.getLibPackageCSAccess().getPrecedenceKeyword_4_1_0()));
		match_LibPropertyCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getLibPropertyCSAccess().getLeftCurlyBracketKeyword_7_0_0()), new TokenAlias(false, false, grammarAccess.getLibPropertyCSAccess().getRightCurlyBracketKeyword_7_0_2())), new TokenAlias(false, false, grammarAccess.getLibPropertyCSAccess().getSemicolonKeyword_7_1()));
		match_MultiplicityCS_VerticalLineQuestionMarkKeyword_2_0_q = new TokenAlias(false, true, grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0());
		match_TupleTypeCS___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getTupleTypeCSAccess().getLeftParenthesisKeyword_1_0()), new TokenAlias(false, false, grammarAccess.getTupleTypeCSAccess().getRightParenthesisKeyword_1_2()));
	}

	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (ruleCall.getRule() == grammarAccess.getUnrestrictedNameRule())
			return getUnrestrictedNameToken(semanticObject, ruleCall, node);
		return "";
	}

	/**
	 * @Override
	 * UnrestrictedName returns ecore::EString: 	EssentialOCLUnrestrictedName;
	 */
	protected String getUnrestrictedNameToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}

	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_LibCoercionCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__.equals(syntax))
				emit_LibCoercionCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_LibIterationCS_SemicolonKeyword_14_1_or___LeftCurlyBracketKeyword_14_0_0_RightCurlyBracketKeyword_14_0_2__.equals(syntax))
				emit_LibIterationCS_SemicolonKeyword_14_1_or___LeftCurlyBracketKeyword_14_0_0_RightCurlyBracketKeyword_14_0_2__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_LibOperationCS_SemicolonKeyword_13_1_or___LeftCurlyBracketKeyword_13_0_0_RightCurlyBracketKeyword_13_0_2__.equals(syntax))
				emit_LibOperationCS_SemicolonKeyword_13_1_or___LeftCurlyBracketKeyword_13_0_0_RightCurlyBracketKeyword_13_0_2__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_LibOperationCS_UnrestrictedNameParserRuleCall_13_0_1_1_1_q.equals(syntax))
				emit_LibOperationCS_UnrestrictedNameParserRuleCall_13_0_1_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_LibPackageCS___SemicolonKeyword_4_1_2_PrecedenceKeyword_4_1_0__q.equals(syntax))
				emit_LibPackageCS___SemicolonKeyword_4_1_2_PrecedenceKeyword_4_1_0__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_LibPropertyCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__.equals(syntax))
				emit_LibPropertyCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_MultiplicityCS_VerticalLineQuestionMarkKeyword_2_0_q.equals(syntax))
				emit_MultiplicityCS_VerticalLineQuestionMarkKeyword_2_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_TupleTypeCS___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q.equals(syntax))
				emit_TupleTypeCS___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     ('{' '}') | ';'
	 *
	 * This ambiguous syntax occurs at:
	 *     implementation=[JavaClassCS|SINGLE_QUOTED_STRING] (ambiguity) (rule end)
	 *     ownedType=TypedMultiplicityRefCS (ambiguity) (rule end)
	 */
	protected void emit_LibCoercionCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     ('{' '}') | ';'
	 *
	 * This ambiguous syntax occurs at:
	 *     implementation=[JavaClassCS|SINGLE_QUOTED_STRING] (ambiguity) (rule end)
	 *     isInvalidating?='invalidating' (ambiguity) (rule end)
	 *     isValidating?='validating' (ambiguity) (rule end)
	 *     ownedType=TypedMultiplicityRefCS (ambiguity) (rule end)
	 */
	protected void emit_LibIterationCS_SemicolonKeyword_14_1_or___LeftCurlyBracketKeyword_14_0_0_RightCurlyBracketKeyword_14_0_2__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     ('{' '}') | ';'
	 *
	 * This ambiguous syntax occurs at:
	 *     implementation=[JavaClassCS|SINGLE_QUOTED_STRING] (ambiguity) (rule end)
	 *     isInvalidating?='invalidating' (ambiguity) (rule end)
	 *     isValidating?='validating' (ambiguity) (rule end)
	 *     ownedType=TypedMultiplicityRefCS (ambiguity) (rule end)
	 *     precedence=[Precedence|Name] (ambiguity) (rule end)
	 */
	protected void emit_LibOperationCS_SemicolonKeyword_13_1_or___LeftCurlyBracketKeyword_13_0_0_RightCurlyBracketKeyword_13_0_2__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     UnrestrictedName?
	 *
	 * This ambiguous syntax occurs at:
	 *     implementation=[JavaClassCS|SINGLE_QUOTED_STRING] '{' 'body' (ambiguity) ':' ownedBodyExpressions+=SpecificationCS
	 *     isInvalidating?='invalidating' '{' 'body' (ambiguity) ':' ownedBodyExpressions+=SpecificationCS
	 *     isValidating?='validating' '{' 'body' (ambiguity) ':' ownedBodyExpressions+=SpecificationCS
	 *     ownedAnnotations+=AnnotationElementCS 'body' (ambiguity) ':' ownedBodyExpressions+=SpecificationCS
	 *     ownedBodyExpressions+=SpecificationCS ';' 'body' (ambiguity) ':' ownedBodyExpressions+=SpecificationCS
	 *     ownedPostconditions+=PostCS 'body' (ambiguity) ':' ownedBodyExpressions+=SpecificationCS
	 *     ownedPreconditions+=PreCS 'body' (ambiguity) ':' ownedBodyExpressions+=SpecificationCS
	 *     ownedType=TypedMultiplicityRefCS '{' 'body' (ambiguity) ':' ownedBodyExpressions+=SpecificationCS
	 *     precedence=[Precedence|Name] '{' 'body' (ambiguity) ':' ownedBodyExpressions+=SpecificationCS
	 */
	protected void emit_LibOperationCS_UnrestrictedNameParserRuleCall_13_0_1_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     (';' 'precedence')?
	 *
	 * This ambiguous syntax occurs at:
	 *     ownedPrecedences+=PrecedenceCS (ambiguity) ownedPrecedences+=PrecedenceCS
	 */
	protected void emit_LibPackageCS___SemicolonKeyword_4_1_2_PrecedenceKeyword_4_1_0__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     ('{' '}') | ';'
	 *
	 * This ambiguous syntax occurs at:
	 *     implementation=[JavaClassCS|SINGLE_QUOTED_STRING] (ambiguity) (rule end)
	 *     ownedOpposite=LibOppositeCS (ambiguity) (rule end)
	 *     ownedType=TypedMultiplicityRefCS (ambiguity) (rule end)
	 */
	protected void emit_LibPropertyCS_SemicolonKeyword_7_1_or___LeftCurlyBracketKeyword_7_0_0_RightCurlyBracketKeyword_7_0_2__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     '|?'?
	 *
	 * This ambiguous syntax occurs at:
	 *     lowerBound=LOWER (ambiguity) ']' (rule end)
	 *     stringBounds='*' (ambiguity) ']' (rule end)
	 *     stringBounds='+' (ambiguity) ']' (rule end)
	 *     stringBounds='?' (ambiguity) ']' (rule end)
	 *     upperBound=UPPER (ambiguity) ']' (rule end)
	 */
	protected void emit_MultiplicityCS_VerticalLineQuestionMarkKeyword_2_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name='Tuple' (ambiguity) (rule end)
	 *     name='Tuple' (ambiguity) ownedMultiplicity=MultiplicityCS
	 */
	protected void emit_TupleTypeCS___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

}
