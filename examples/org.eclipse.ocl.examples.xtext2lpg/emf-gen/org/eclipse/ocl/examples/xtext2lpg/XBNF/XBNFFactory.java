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
package org.eclipse.ocl.examples.xtext2lpg.XBNF;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage
 * @generated
 */
public interface XBNFFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	XBNFFactory eINSTANCE = org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Abstract Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Rule</em>'.
	 * @generated
	 */
	AbstractRule createAbstractRule();

	/**
	 * Returns a new object of class '<em>Action Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Assignment</em>'.
	 * @generated
	 */
	ActionAssignment createActionAssignment();

	/**
	 * Returns a new object of class '<em>Character Range</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Character Range</em>'.
	 * @generated
	 */
	CharacterRange createCharacterRange();

	/**
	 * Returns a new object of class '<em>Conjunction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conjunction</em>'.
	 * @generated
	 */
	Conjunction createConjunction();

	/**
	 * Returns a new object of class '<em>Disjunction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Disjunction</em>'.
	 * @generated
	 */
	Disjunction createDisjunction();

	/**
	 * Returns a new object of class '<em>EOF</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EOF</em>'.
	 * @generated
	 */
	EOF createEOF();

	/**
	 * Returns a new object of class '<em>Epsilon</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Epsilon</em>'.
	 * @generated
	 */
	Epsilon createEpsilon();

	/**
	 * Returns a new object of class '<em>Grammar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grammar</em>'.
	 * @generated
	 */
	Grammar createGrammar();

	/**
	 * Returns a new object of class '<em>Keyword</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Keyword</em>'.
	 * @generated
	 */
	Keyword createKeyword();

	/**
	 * Returns a new object of class '<em>Keyword Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Keyword Assignment</em>'.
	 * @generated
	 */
	KeywordAssignment createKeywordAssignment();

	/**
	 * Returns a new object of class '<em>Lexer Grammar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lexer Grammar</em>'.
	 * @generated
	 */
	LexerGrammar createLexerGrammar();

	/**
	 * Returns a new object of class '<em>Negated Token</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Negated Token</em>'.
	 * @generated
	 */
	NegatedToken createNegatedToken();

	/**
	 * Returns a new object of class '<em>Parser Grammar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parser Grammar</em>'.
	 * @generated
	 */
	ParserGrammar createParserGrammar();

	/**
	 * Returns a new object of class '<em>Parser Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parser Rule</em>'.
	 * @generated
	 */
	ParserRule createParserRule();

	/**
	 * Returns a new object of class '<em>Rule Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rule Call</em>'.
	 * @generated
	 */
	RuleCall createRuleCall();

	/**
	 * Returns a new object of class '<em>Rule Call Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rule Call Assignment</em>'.
	 * @generated
	 */
	RuleCallAssignment createRuleCallAssignment();

	/**
	 * Returns a new object of class '<em>Syntax</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Syntax</em>'.
	 * @generated
	 */
	Syntax createSyntax();

	/**
	 * Returns a new object of class '<em>Terminal Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Terminal Rule</em>'.
	 * @generated
	 */
	TerminalRule createTerminalRule();

	/**
	 * Returns a new object of class '<em>Until Token</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Until Token</em>'.
	 * @generated
	 */
	UntilToken createUntilToken();

	/**
	 * Returns a new object of class '<em>Untyped Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Untyped Rule</em>'.
	 * @generated
	 */
	UntypedRule createUntypedRule();

	/**
	 * Returns a new object of class '<em>Wildcard</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wildcard</em>'.
	 * @generated
	 */
	Wildcard createWildcard();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	XBNFPackage getXBNFPackage();

} //XBNFFactory
