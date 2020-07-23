/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess;
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
public abstract class AbstractCompleteOCLSyntacticSequencer extends AbstractSyntacticSequencer {

	protected CompleteOCLGrammarAccess grammarAccess;
	protected AbstractElementAlias match_BodySpecificationCS_UnrestrictedNameParserRuleCall_2_q;
	protected AbstractElementAlias match_DefOperationCS_UnrestrictedNameParserRuleCall_3_q;
	protected AbstractElementAlias match_DefPropertyCS_UnrestrictedNameParserRuleCall_3_q;
	protected AbstractElementAlias match_DeriveSpecificationCS_UnrestrictedNameParserRuleCall_2_q;
	protected AbstractElementAlias match_ImportCS_ImportKeyword_1_0_or_IncludeKeyword_1_1_or_LibraryKeyword_1_2;
	protected AbstractElementAlias match_InitSpecificationCS_UnrestrictedNameParserRuleCall_2_q;
	protected AbstractElementAlias match_MultiplicityCS_VerticalLineQuestionMarkKeyword_2_0_q;
	protected AbstractElementAlias match_TupleTypeCS___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q;

	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (CompleteOCLGrammarAccess) access;
		match_BodySpecificationCS_UnrestrictedNameParserRuleCall_2_q = new TokenAlias(false, true, grammarAccess.getBodySpecificationCSAccess().getUnrestrictedNameParserRuleCall_2());
		match_DefOperationCS_UnrestrictedNameParserRuleCall_3_q = new TokenAlias(false, true, grammarAccess.getDefOperationCSAccess().getUnrestrictedNameParserRuleCall_3());
		match_DefPropertyCS_UnrestrictedNameParserRuleCall_3_q = new TokenAlias(false, true, grammarAccess.getDefPropertyCSAccess().getUnrestrictedNameParserRuleCall_3());
		match_DeriveSpecificationCS_UnrestrictedNameParserRuleCall_2_q = new TokenAlias(false, true, grammarAccess.getDeriveSpecificationCSAccess().getUnrestrictedNameParserRuleCall_2());
		match_ImportCS_ImportKeyword_1_0_or_IncludeKeyword_1_1_or_LibraryKeyword_1_2 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getImportCSAccess().getImportKeyword_1_0()), new TokenAlias(false, false, grammarAccess.getImportCSAccess().getIncludeKeyword_1_1()), new TokenAlias(false, false, grammarAccess.getImportCSAccess().getLibraryKeyword_1_2()));
		match_InitSpecificationCS_UnrestrictedNameParserRuleCall_2_q = new TokenAlias(false, true, grammarAccess.getInitSpecificationCSAccess().getUnrestrictedNameParserRuleCall_2());
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
	 * UnrestrictedName returns ecore::EString:
	 * 	EssentialOCLUnrestrictedName
	 * 						| 'import'
	 * 	| 'include'
	 * 			| 'library'
	 * 				;
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
			if (match_BodySpecificationCS_UnrestrictedNameParserRuleCall_2_q.equals(syntax))
				emit_BodySpecificationCS_UnrestrictedNameParserRuleCall_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DefOperationCS_UnrestrictedNameParserRuleCall_3_q.equals(syntax))
				emit_DefOperationCS_UnrestrictedNameParserRuleCall_3_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DefPropertyCS_UnrestrictedNameParserRuleCall_3_q.equals(syntax))
				emit_DefPropertyCS_UnrestrictedNameParserRuleCall_3_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DeriveSpecificationCS_UnrestrictedNameParserRuleCall_2_q.equals(syntax))
				emit_DeriveSpecificationCS_UnrestrictedNameParserRuleCall_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ImportCS_ImportKeyword_1_0_or_IncludeKeyword_1_1_or_LibraryKeyword_1_2.equals(syntax))
				emit_ImportCS_ImportKeyword_1_0_or_IncludeKeyword_1_1_or_LibraryKeyword_1_2(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_InitSpecificationCS_UnrestrictedNameParserRuleCall_2_q.equals(syntax))
				emit_InitSpecificationCS_UnrestrictedNameParserRuleCall_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_MultiplicityCS_VerticalLineQuestionMarkKeyword_2_0_q.equals(syntax))
				emit_MultiplicityCS_VerticalLineQuestionMarkKeyword_2_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_TupleTypeCS___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q.equals(syntax))
				emit_TupleTypeCS___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     UnrestrictedName?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) 'body' (ambiguity) ':' exprString=UNQUOTED_STRING
	 *     (rule start) 'body' (ambiguity) ':' ownedExpression=ExpCS
	 *     ownedAnnotations+=CommentCS 'body' (ambiguity) ':' exprString=UNQUOTED_STRING
	 *     ownedAnnotations+=CommentCS 'body' (ambiguity) ':' ownedExpression=ExpCS
	 */
	protected void emit_BodySpecificationCS_UnrestrictedNameParserRuleCall_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     UnrestrictedName?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) 'def' (ambiguity) ':' name=UnrestrictedName
	 *     (rule start) 'def' (ambiguity) ':' ownedSignature=TemplateSignatureCS
	 *     isStatic?='static' 'def' (ambiguity) ':' name=UnrestrictedName
	 *     isStatic?='static' 'def' (ambiguity) ':' ownedSignature=TemplateSignatureCS
	 *     ownedAnnotations+=CommentCS 'def' (ambiguity) ':' name=UnrestrictedName
	 *     ownedAnnotations+=CommentCS 'def' (ambiguity) ':' ownedSignature=TemplateSignatureCS
	 */
	protected void emit_DefOperationCS_UnrestrictedNameParserRuleCall_3_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     UnrestrictedName?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) 'def' (ambiguity) ':' name=UnrestrictedName
	 *     isStatic?='static' 'def' (ambiguity) ':' name=UnrestrictedName
	 *     ownedAnnotations+=CommentCS 'def' (ambiguity) ':' name=UnrestrictedName
	 */
	protected void emit_DefPropertyCS_UnrestrictedNameParserRuleCall_3_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     UnrestrictedName?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) 'derive' (ambiguity) ':' exprString=UNQUOTED_STRING
	 *     (rule start) 'derive' (ambiguity) ':' ownedExpression=ExpCS
	 *     ownedAnnotations+=CommentCS 'derive' (ambiguity) ':' exprString=UNQUOTED_STRING
	 *     ownedAnnotations+=CommentCS 'derive' (ambiguity) ':' ownedExpression=ExpCS
	 */
	protected void emit_DeriveSpecificationCS_UnrestrictedNameParserRuleCall_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     'import' | 'include' | 'library'
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) name=Identifier
	 *     (rule start) (ambiguity) ownedPathName=URIPathNameCS
	 *     ownedAnnotations+=CommentCS (ambiguity) name=Identifier
	 *     ownedAnnotations+=CommentCS (ambiguity) ownedPathName=URIPathNameCS
	 */
	protected void emit_ImportCS_ImportKeyword_1_0_or_IncludeKeyword_1_1_or_LibraryKeyword_1_2(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}

	/**
	 * Ambiguous syntax:
	 *     UnrestrictedName?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) 'init' (ambiguity) ':' exprString=UNQUOTED_STRING
	 *     (rule start) 'init' (ambiguity) ':' ownedExpression=ExpCS
	 *     ownedAnnotations+=CommentCS 'init' (ambiguity) ':' exprString=UNQUOTED_STRING
	 *     ownedAnnotations+=CommentCS 'init' (ambiguity) ':' ownedExpression=ExpCS
	 */
	protected void emit_InitSpecificationCS_UnrestrictedNameParserRuleCall_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
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
