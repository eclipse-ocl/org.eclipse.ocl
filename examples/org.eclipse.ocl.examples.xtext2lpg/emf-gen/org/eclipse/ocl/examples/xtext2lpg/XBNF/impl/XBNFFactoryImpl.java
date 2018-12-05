/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext2lpg.XBNF.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XBNFFactoryImpl extends EFactoryImpl implements XBNFFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static XBNFFactory init() {
		try {
			XBNFFactory theXBNFFactory = (XBNFFactory)EPackage.Registry.INSTANCE.getEFactory(XBNFPackage.eNS_URI);
			if (theXBNFFactory != null) {
				return theXBNFFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new XBNFFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XBNFFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case XBNFPackage.ABSTRACT_RULE: return createAbstractRule();
			case XBNFPackage.ACTION_ASSIGNMENT: return createActionAssignment();
			case XBNFPackage.CHARACTER_RANGE: return createCharacterRange();
			case XBNFPackage.CONJUNCTION: return createConjunction();
			case XBNFPackage.DISJUNCTION: return createDisjunction();
			case XBNFPackage.EOF: return createEOF();
			case XBNFPackage.EPSILON: return createEpsilon();
			case XBNFPackage.GRAMMAR: return createGrammar();
			case XBNFPackage.KEYWORD: return createKeyword();
			case XBNFPackage.KEYWORD_ASSIGNMENT: return createKeywordAssignment();
			case XBNFPackage.LEXER_GRAMMAR: return createLexerGrammar();
			case XBNFPackage.NEGATED_TOKEN: return createNegatedToken();
			case XBNFPackage.PARSER_GRAMMAR: return createParserGrammar();
			case XBNFPackage.PARSER_RULE: return createParserRule();
			case XBNFPackage.RULE_CALL: return createRuleCall();
			case XBNFPackage.RULE_CALL_ASSIGNMENT: return createRuleCallAssignment();
			case XBNFPackage.SYNTAX: return createSyntax();
			case XBNFPackage.TERMINAL_RULE: return createTerminalRule();
			case XBNFPackage.UNTIL_TOKEN: return createUntilToken();
			case XBNFPackage.UNTYPED_RULE: return createUntypedRule();
			case XBNFPackage.WILDCARD: return createWildcard();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractRule createAbstractRule() {
		AbstractRuleImpl abstractRule = new AbstractRuleImpl();
		return abstractRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionAssignment createActionAssignment() {
		ActionAssignmentImpl actionAssignment = new ActionAssignmentImpl();
		return actionAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterRange createCharacterRange() {
		CharacterRangeImpl characterRange = new CharacterRangeImpl();
		return characterRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Conjunction createConjunction() {
		ConjunctionImpl conjunction = new ConjunctionImpl();
		return conjunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Disjunction createDisjunction() {
		DisjunctionImpl disjunction = new DisjunctionImpl();
		return disjunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOF createEOF() {
		EOFImpl eof = new EOFImpl();
		return eof;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Epsilon createEpsilon() {
		EpsilonImpl epsilon = new EpsilonImpl();
		return epsilon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Grammar createGrammar() {
		GrammarImpl grammar = new GrammarImpl();
		return grammar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Keyword createKeyword() {
		KeywordImpl keyword = new KeywordImpl();
		return keyword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeywordAssignment createKeywordAssignment() {
		KeywordAssignmentImpl keywordAssignment = new KeywordAssignmentImpl();
		return keywordAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LexerGrammar createLexerGrammar() {
		LexerGrammarImpl lexerGrammar = new LexerGrammarImpl();
		return lexerGrammar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NegatedToken createNegatedToken() {
		NegatedTokenImpl negatedToken = new NegatedTokenImpl();
		return negatedToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParserGrammar createParserGrammar() {
		ParserGrammarImpl parserGrammar = new ParserGrammarImpl();
		return parserGrammar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParserRule createParserRule() {
		ParserRuleImpl parserRule = new ParserRuleImpl();
		return parserRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleCall createRuleCall() {
		RuleCallImpl ruleCall = new RuleCallImpl();
		return ruleCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleCallAssignment createRuleCallAssignment() {
		RuleCallAssignmentImpl ruleCallAssignment = new RuleCallAssignmentImpl();
		return ruleCallAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Syntax createSyntax() {
		SyntaxImpl syntax = new SyntaxImpl();
		return syntax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TerminalRule createTerminalRule() {
		TerminalRuleImpl terminalRule = new TerminalRuleImpl();
		return terminalRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UntilToken createUntilToken() {
		UntilTokenImpl untilToken = new UntilTokenImpl();
		return untilToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UntypedRule createUntypedRule() {
		UntypedRuleImpl untypedRule = new UntypedRuleImpl();
		return untypedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Wildcard createWildcard() {
		WildcardImpl wildcard = new WildcardImpl();
		return wildcard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XBNFPackage getXBNFPackage() {
		return (XBNFPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static XBNFPackage getPackage() {
		return XBNFPackage.eINSTANCE;
	}

} //XBNFFactoryImpl
