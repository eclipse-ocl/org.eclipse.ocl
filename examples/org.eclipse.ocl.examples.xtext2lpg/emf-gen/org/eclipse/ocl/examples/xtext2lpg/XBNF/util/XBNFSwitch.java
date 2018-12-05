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
package org.eclipse.ocl.examples.xtext2lpg.XBNF.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage
 * @generated
 */
public class XBNFSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static XBNFPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XBNFSwitch() {
		if (modelPackage == null) {
			modelPackage = XBNFPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case XBNFPackage.ABSTRACT_ELEMENT: {
				AbstractElement abstractElement = (AbstractElement)theEObject;
				T result = caseAbstractElement(abstractElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.ABSTRACT_RULE: {
				AbstractRule abstractRule = (AbstractRule)theEObject;
				T result = caseAbstractRule(abstractRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.ACTION_ASSIGNMENT: {
				ActionAssignment actionAssignment = (ActionAssignment)theEObject;
				T result = caseActionAssignment(actionAssignment);
				if (result == null) result = caseAssignment(actionAssignment);
				if (result == null) result = caseAbstractElement(actionAssignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.ASSIGNMENT: {
				Assignment assignment = (Assignment)theEObject;
				T result = caseAssignment(assignment);
				if (result == null) result = caseAbstractElement(assignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.CHARACTER_RANGE: {
				CharacterRange characterRange = (CharacterRange)theEObject;
				T result = caseCharacterRange(characterRange);
				if (result == null) result = caseAbstractElement(characterRange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.CONJUNCTION: {
				Conjunction conjunction = (Conjunction)theEObject;
				T result = caseConjunction(conjunction);
				if (result == null) result = caseAbstractElement(conjunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.DISJUNCTION: {
				Disjunction disjunction = (Disjunction)theEObject;
				T result = caseDisjunction(disjunction);
				if (result == null) result = caseAbstractElement(disjunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.EOF: {
				EOF eof = (EOF)theEObject;
				T result = caseEOF(eof);
				if (result == null) result = caseAbstractElement(eof);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.EPSILON: {
				Epsilon epsilon = (Epsilon)theEObject;
				T result = caseEpsilon(epsilon);
				if (result == null) result = caseAbstractElement(epsilon);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.GRAMMAR: {
				Grammar grammar = (Grammar)theEObject;
				T result = caseGrammar(grammar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.KEYWORD: {
				Keyword keyword = (Keyword)theEObject;
				T result = caseKeyword(keyword);
				if (result == null) result = caseAbstractElement(keyword);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.KEYWORD_ASSIGNMENT: {
				KeywordAssignment keywordAssignment = (KeywordAssignment)theEObject;
				T result = caseKeywordAssignment(keywordAssignment);
				if (result == null) result = caseAssignment(keywordAssignment);
				if (result == null) result = caseKeyword(keywordAssignment);
				if (result == null) result = caseAbstractElement(keywordAssignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.LEXER_GRAMMAR: {
				LexerGrammar lexerGrammar = (LexerGrammar)theEObject;
				T result = caseLexerGrammar(lexerGrammar);
				if (result == null) result = caseGrammar(lexerGrammar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.NEGATED_TOKEN: {
				NegatedToken negatedToken = (NegatedToken)theEObject;
				T result = caseNegatedToken(negatedToken);
				if (result == null) result = caseAbstractElement(negatedToken);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.PARSER_GRAMMAR: {
				ParserGrammar parserGrammar = (ParserGrammar)theEObject;
				T result = caseParserGrammar(parserGrammar);
				if (result == null) result = caseGrammar(parserGrammar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.PARSER_RULE: {
				ParserRule parserRule = (ParserRule)theEObject;
				T result = caseParserRule(parserRule);
				if (result == null) result = caseTypedRule(parserRule);
				if (result == null) result = caseAbstractRule(parserRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.RULE_CALL: {
				RuleCall ruleCall = (RuleCall)theEObject;
				T result = caseRuleCall(ruleCall);
				if (result == null) result = caseAbstractElement(ruleCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.RULE_CALL_ASSIGNMENT: {
				RuleCallAssignment ruleCallAssignment = (RuleCallAssignment)theEObject;
				T result = caseRuleCallAssignment(ruleCallAssignment);
				if (result == null) result = caseAssignment(ruleCallAssignment);
				if (result == null) result = caseRuleCall(ruleCallAssignment);
				if (result == null) result = caseAbstractElement(ruleCallAssignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.SYNTAX: {
				Syntax syntax = (Syntax)theEObject;
				T result = caseSyntax(syntax);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.TERMINAL_RULE: {
				TerminalRule terminalRule = (TerminalRule)theEObject;
				T result = caseTerminalRule(terminalRule);
				if (result == null) result = caseTypedRule(terminalRule);
				if (result == null) result = caseAbstractRule(terminalRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.TYPED_RULE: {
				TypedRule typedRule = (TypedRule)theEObject;
				T result = caseTypedRule(typedRule);
				if (result == null) result = caseAbstractRule(typedRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.UNTIL_TOKEN: {
				UntilToken untilToken = (UntilToken)theEObject;
				T result = caseUntilToken(untilToken);
				if (result == null) result = caseAbstractElement(untilToken);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.UNTYPED_RULE: {
				UntypedRule untypedRule = (UntypedRule)theEObject;
				T result = caseUntypedRule(untypedRule);
				if (result == null) result = caseAbstractRule(untypedRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XBNFPackage.WILDCARD: {
				Wildcard wildcard = (Wildcard)theEObject;
				T result = caseWildcard(wildcard);
				if (result == null) result = caseAbstractElement(wildcard);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractElement(AbstractElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractRule(AbstractRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionAssignment(ActionAssignment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssignment(Assignment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Character Range</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Character Range</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCharacterRange(CharacterRange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conjunction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conjunction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConjunction(Conjunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Disjunction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Disjunction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDisjunction(Disjunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EOF</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EOF</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEOF(EOF object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Epsilon</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Epsilon</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEpsilon(Epsilon object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Grammar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Grammar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGrammar(Grammar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Keyword</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Keyword</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKeyword(Keyword object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Keyword Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Keyword Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKeywordAssignment(KeywordAssignment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lexer Grammar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lexer Grammar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLexerGrammar(LexerGrammar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Negated Token</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Negated Token</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNegatedToken(NegatedToken object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parser Grammar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parser Grammar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParserGrammar(ParserGrammar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parser Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parser Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParserRule(ParserRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuleCall(RuleCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule Call Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule Call Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuleCallAssignment(RuleCallAssignment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Syntax</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Syntax</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSyntax(Syntax object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Terminal Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Terminal Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTerminalRule(TerminalRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypedRule(TypedRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Until Token</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Until Token</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUntilToken(UntilToken object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Untyped Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Untyped Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUntypedRule(UntypedRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wildcard</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wildcard</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWildcard(Wildcard object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //XBNFSwitch
